package dss.vector.solutions.query;

import it.geosolutions.geoserver.rest.GeoServerRESTPublisher;
import it.geosolutions.geoserver.rest.encoder.GSPostGISDatastoreEncoder;

import java.lang.reflect.InvocationTargetException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.json.JSONArray;
import org.json.JSONException;

import com.runwaysdk.business.Business;
import com.runwaysdk.constants.CommonProperties;
import com.runwaysdk.constants.DatabaseProperties;
import com.runwaysdk.constants.LocalProperties;
import com.runwaysdk.dataaccess.ProgrammingErrorException;
import com.runwaysdk.dataaccess.database.Database;
import com.runwaysdk.dataaccess.database.DatabaseException;
import com.runwaysdk.dataaccess.transaction.Transaction;
import com.runwaysdk.query.QueryException;
import com.runwaysdk.query.Selectable;
import com.runwaysdk.query.SelectableDecimal;
import com.runwaysdk.query.SelectableDouble;
import com.runwaysdk.query.SelectableFloat;
import com.runwaysdk.query.SelectableSQLCharacter;
import com.runwaysdk.query.SelectableSQLDouble;
import com.runwaysdk.query.ValueQuery;
import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.Envelope;

import dss.vector.solutions.geo.DuplicateMapDataException;
import dss.vector.solutions.geo.GeoServerReloadException;

public class MapUtil extends MapUtilBase implements com.runwaysdk.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1242080109170L;

  public MapUtil()
  {
    super();
  }

  /**
   * Attempts to delete the view with the given name. A database level cascade
   * is also used to remove any dependencies on other views.
   * 
   * @param viewName
   */
  public static void deleteMapView(String viewName)
  {
    List<String> batch = new LinkedList<String>();
    batch.add("DROP VIEW IF EXISTS " + viewName + " CASCADE");
    Database.executeBatch(batch);
  }

  /**
   * Creates database views for each layer provided and returns a JSON string
   * with the correct mapping information for use with OpenLayers/GeoServer. The
   * returned Map maintains insertion order so it can be safely iterated over to
   * get the layers starting with the base layer and up.
   * 
   * @param layers
   * @return
   */
  @Transaction
  public static Map<Layer, ValueQuery> createDBViews(Layer[] layers, boolean infoOnly)
  {
    if (!infoOnly && layers.length == 0)
    {
      String error = "A user tried to create a map without layers.";
      throw new NoThematicLayerException(error);
    }

    // Map all Layers to their ValueQuery objects
    Map<String, ValueQuery> layerValueQueries = new HashMap<String, ValueQuery>();
    boolean isClipping = false;
    for (int i = 0; i < layers.length; i++)
    {
      Layer layer = layers[i];

      SavedSearch search = layer.getSavedSearch();
      String xml = search.getQueryXml();
      String config = search.getConfig();
      String queryType = search.getQueryType();

      // QueryBuilder.getValueQuery() takes in the query class for use with
      // reflection.
      // TODO pass in queryType and have getValueQuery deref the class
      String queryClass = QueryConstants.getQueryClass(queryType);

      ValueQuery valueQuery;
      try
      {
        valueQuery = QueryBuilder.getValueQuery(queryClass, xml, config, layer);
        
        // Format any decimal thematic variable to two decimal places
        if(layer.hasThematicVariable())
        {
          Selectable thematicSel = valueQuery.getSelectableRef(layer.getThematicUserAlias());
          
          if(thematicSel instanceof SelectableDouble
              || thematicSel instanceof SelectableFloat
              || thematicSel instanceof SelectableDecimal)
          {
            SelectableSQLDouble newSel;
            if(thematicSel.isAggregateFunction())
            {
              newSel = valueQuery.aSQLAggregateDouble(thematicSel.getDbColumnName(), "");
            }
            else
            {
              newSel = valueQuery.aSQLDouble(thematicSel.getDbColumnName(), "");
            }
            newSel.setSQL(thematicSel.getSQL()+"::decimal(20,2)");
            newSel.setColumnAlias(thematicSel.getColumnAlias());
            newSel.setUserDefinedAlias(thematicSel.getUserDefinedAlias());
            
            valueQuery.replaceSelectable(newSel);
          }
        }
      }
      catch (ProgrammingErrorException e)
      {
        String layerName = layer.getLayerName();
        String queryName = layer.getSavedSearch().getQueryName();
        
        String error = "The map could not be generated while getting the query ["
          + queryName + "] for layer [" + layerName + "]";
        
        if (e.getCause() instanceof InvocationTargetException)
        {
          InvocationTargetException ex = (InvocationTargetException) e.getCause();
          if (ex.getCause() instanceof QueryException)
          {
            if(i == 0)
            {
              BaseLayerQueryChangedException changeEx = new BaseLayerQueryChangedException();
              changeEx.setLayerName(layerName);
              changeEx.setQueryName(queryName);
              throw changeEx;
            }
            else
            {
              LayerOmittedQueryChangedInformation info = new LayerOmittedQueryChangedInformation();
              info.setLayerName(layerName);
              info.setQueryName(queryName);
              info.throwIt();
              
              layers[i] = null;
              continue;
            }
          }
        }
        
        GeoServerReloadException gsEX = new GeoServerReloadException(error, e.getCause());
        throw gsEX;
      }

      // The base layer must have geo entities or geoserver bombs out
      if (!infoOnly && i == 0 && valueQuery.getCount() == 0)
      {
        String error = "The thematic layer doesn't contain spatial data.";
        throw new NoEntitiesInThematicLayerException(error);
      }

      layerValueQueries.put(layer.getId(), valueQuery);

      if (i != 0 && !isClipping && layer.getClipToBaseLayer())
      {
        isClipping = true;
      }
    }

    Map<Layer, ValueQuery> reloads = new LinkedHashMap<Layer, ValueQuery>();
    String baseView = null;
    for (int i = 0; i < layers.length; i++)
    {
      Layer layer = layers[i];
      if(layer == null)
      {
        continue; // The layer failed the first pass
      }
      
      
      // Remove the old layer
      try
      {
        String viewName = layer.getViewName();
        deleteMapView(viewName);
      }
      catch (DatabaseException e)
      {
        // View doesn't exist, but that's okay. It may have never
        // been created or a cleanup task has removed it.
      }
      
      
      String layerName = layer.getLayerName();

      // Update the view name on the layer for this refresh cycle
      String newViewName = Layer.GEO_VIEW_PREFIX + System.currentTimeMillis();
      if(!infoOnly)
      {
        layer.appLock();
      }
        
      layer.setViewName(newViewName);
      
      if(!infoOnly)
      {
        layer.apply();
      }
      
      if(i == 0)
      {
        baseView = newViewName;
      }
      
      ValueQuery valueQuery = layerValueQueries.get(layer.getId());

      // Any empty non-base layer will be omitted from the map to
      // keep geoserver from acting funky.
      if (i > 0 && valueQuery.getCount() == 0)
      {
        LayerOmittedNoDataInformation info = new LayerOmittedNoDataInformation();
        info.setLayerName(layerName);
        info.throwIt();

        continue;
      }

      String sql;
      if(i !=0 && layer.getClipToBaseLayer())
      {
        List<Selectable> selectables = valueQuery.getSelectableRefs();
        Selectable geoSelectable = null;
        int count = 0;
        int removeInd = 0;
        for(Selectable selectable : selectables)
        {
          if(selectable.getColumnAlias().equals(QueryConstants.GEOMETRY_NAME_COLUMN))
          {
            geoSelectable = selectable;
            removeInd = count;
          }
          
          count++;
        }
        
        selectables.remove(removeInd);
        
        String geoAttr = geoSelectable.getDefiningTableAlias()+"."+geoSelectable.getDbColumnName();
        
        String clippingAlias = "geoentity_clipping";
        String clippingColumnAlias = "clipping_column";
        String clippingColumn = clippingAlias+"."+clippingColumnAlias;
        
        SelectableSQLCharacter geoS = valueQuery.aSQLAggregateCharacter(QueryConstants.GEOMETRY_NAME_COLUMN,
          "collect(intersection("+geoAttr+", "+clippingColumn+"))");
        selectables.add(geoS);
        
        valueQuery.clearSelectClause();
        valueQuery.SELECT(selectables.toArray(new Selectable[selectables.size()]));
        
        valueQuery.FROM("(SELECT "+QueryConstants.GEOMETRY_NAME_COLUMN+" AS "+clippingColumnAlias+" FROM "+baseView+")", clippingAlias);
        
        sql = valueQuery.getSQL();
      }
      else
      {
        sql = valueQuery.getSQL();
      }
      
      // Create a new view that will reflect the current state of the query.
      Database.createView(newViewName, sql);
      
      // make sure there are no duplicate geo entities
      String countSQL = "SELECT COUNT(*) " + Database.formatColumnAlias("ct") + " FROM " + newViewName;
      countSQL += " GROUP BY " + QueryConstants.GEO_ID_COLUMN + " HAVING COUNT(*) > 1";

      ResultSet resultSet = Database.query(countSQL);

      try
      {
        if (resultSet.next())
        {
          // We have duplicate data! Throw an exception if this is the base
          // layer,
          // but only omit the layer with info if non-base.
          if (i == 0)
          {
            DuplicateMapDataException ex = new DuplicateMapDataException();
            throw ex;
          }
          else
          {
            LayerOmittedDuplicateDataInformation info = new LayerOmittedDuplicateDataInformation();
            info.setLayerName(layerName);
            info.throwIt();
            
            continue;
          }
        }
      }
      catch (SQLException sqlEx1)
      {
        Database.throwDatabaseException(sqlEx1);
      }
      finally
      {
        try
        {
          java.sql.Statement statement = resultSet.getStatement();
          resultSet.close();
          statement.close();
        }
        catch (SQLException sqlEx2)
        {
          Database.throwDatabaseException(sqlEx2);
        }
      }

      reloads.put(layer, valueQuery);

    }

    return reloads;
  }

  /**
   * Formats the SLD file (pathing and filename concatenation) for the given
   * layer.
   * 
   * @param layer
   * @return
   */
  public static String formatSLD(Layer layer)
  {
    String fullWebDir = LocalProperties.getWebDirectory();

    if (fullWebDir.endsWith("/"))
    {
      fullWebDir = fullWebDir.substring(0, fullWebDir.length() - 1);
    }
    String webDir = fullWebDir.substring(fullWebDir.lastIndexOf("/"));
    if (webDir.startsWith("/"))
    {
      webDir = webDir.substring(1);
    }
    String filePath = webDir + "/" + QueryConstants.SLD_WEB_DIR + QueryConstants.createSLDName(layer.getId()) + "."
        + QueryConstants.SLD_EXTENSION;

    // Generated a random query string to force GeoServer to not cache the SLD
    String random = String.valueOf(Math.random());
    random = random.substring(random.length() - 6);

    return filePath + "?a=" + random;
  }

  private static final ResourceBundle bundle             = ResourceBundle.getBundle("GeoServer", Locale
                                                             .getDefault(), Business.class
                                                             .getClassLoader());

  /**
   * Returns the url to access GeoServer locally.
   * 
   * @return
   */
  public static final String getGeoServerLocalURL()
  {
    return bundle.getString("geoserver.local.path");
  }

  /**
   * Returns the url to access GeoServer remotely.
   * 
   * @return
   */
  public static final String getGeoServerRemoteURL()
  {
    return bundle.getString("geoserver.remote.path");
  }

  public static void reload(String sessionId, Map<Layer, ValueQuery> reloads)
  {
    try
    {
      String geoserverPath = getGeoServerLocalURL();
      GeoServerRESTPublisher publisher = new GeoServerRESTPublisher(geoserverPath, "admin", "geoserver");
      
      // reload the catalog (this will force GeoServer to dump any cached
      // features in memory and avoid slowdown over time)
      publisher.reload();

      // Create each layer as a feature on GeoServer
      Iterator<Layer> iter = reloads.keySet().iterator();
      while(iter.hasNext())
      {
        Layer reload = iter.next();
        
        String viewName = reload.getViewName();
        String defaultStyle = reload.getRenderAs().get(0) == AllRenderTypes.POINT ? "point" : "polygon";
        publisher.publishDBLayer(CommonProperties.getDeployAppName(), QueryConstants.getNamespacedDataStore(), viewName, "EPSG:4326", defaultStyle);
      }
    }
    catch (Exception e)
    {
      String error = "Could not reload GeoServer.";
      GeoServerReloadException ex = new GeoServerReloadException(error, e);
      throw ex;
    }
  }
  
  public static void createWorkspaceAndDatastore()
  {
    try
    {
      String appName = CommonProperties.getDeployAppName();
      String geoserverPath = getGeoServerLocalURL();
      GeoServerRESTPublisher publisher = new GeoServerRESTPublisher(geoserverPath, "admin", "geoserver");
      
      publisher.createWorkspace(appName);
      GSPostGISDatastoreEncoder datastore = new GSPostGISDatastoreEncoder();
      datastore.setPort(5444);
      datastore.setPassword("mdssdeploy");
      datastore.setDatabaseType("postgis");
      datastore.setHost("localhost");
      datastore.setValidateConnections(false);
      datastore.setMaxConnections(10);
      datastore.setDatabase(appName.toLowerCase());
      datastore.setNamespace("http://"+appName+".terraframe.com");
      datastore.setSchema(DatabaseProperties.getNamespace());
      datastore.setLooseBBox(true);
      datastore.setPreparedStatements(false);
      datastore.setExposePrimaryKeys(false);
      datastore.setUser("mdssdeploy");
      datastore.setMinConnections(4);
      datastore.setEnabled(true);
      datastore.setName(QueryConstants.getNamespacedDataStore());
      
      publisher.createPostGISDatastore(appName, datastore);
    }
    catch (Exception e)
    {
      String error = "Could not create Geoserver artifacts";
      GeoServerReloadException ex = new GeoServerReloadException(error);
      throw ex;
    }
  }

  /**
   * Gets the bounding box of the thematic layer.
   * 
   * @return
   */
  public static JSONArray getThematicBBox(List<Layer> layers)
  {
    JSONArray bboxArr = new JSONArray();
    if (layers.size() > 0)
    {
      String[] layerNames = new String[layers.size()];
      String sql;
      if (layers.size() == 1)
      {
        Layer layer = layers.get(0);
        String viewName = layer.getViewName();
        layerNames[0] = layer.getLayerName();

        sql = "SELECT AsText(extent(" + viewName + "." + QueryConstants.GEOMETRY_NAME_COLUMN
            + ")) AS bbox FROM " + viewName;
      }
      else
      {
        // More than one layer so union the geometry columns
        sql = "SELECT AsText(extent(geo_v)) AS bbox FROM (\n";

        for (int i = 0; i < layers.size(); i++)
        {
          Layer layer = layers.get(i);
          String viewName = layer.getViewName();
          layerNames[i] = layer.getLayerName();

          sql += "(SELECT " + QueryConstants.GEOMETRY_NAME_COLUMN + " AS geo_v FROM " + viewName
              + ") \n";

          if (i != layers.size() - 1)
          {
            sql += "UNION \n";
          }
        }

        sql += ") bbox_union";
      }

      ResultSet resultSet = Database.query(sql);

      try
      {
        if (resultSet.next())
        {
          String bbox = resultSet.getString("bbox");
          if (bbox != null)
          {
            Pattern p = Pattern.compile("POLYGON\\(\\((.*)\\)\\)");
            Matcher m = p.matcher(bbox);

            if (m.matches())
            {
              String coordinates = m.group(1);
              List<Coordinate> coords = new LinkedList<Coordinate>();

              for (String c : coordinates.split(","))
              {
                String[] xAndY = c.split(" ");
                double x = Double.valueOf(xAndY[0]);
                double y = Double.valueOf(xAndY[1]);

                coords.add(new Coordinate(x, y));
              }

              Envelope e = new Envelope(coords.get(0), coords.get(2));

              try
              {
                bboxArr.put(e.getMinX());
                bboxArr.put(e.getMinY());
                bboxArr.put(e.getMaxX());
                bboxArr.put(e.getMaxY());
              }
              catch (JSONException ex)
              {
                throw new ProgrammingErrorException(ex);
              }
            }
            else
            {
              // There will not be a match if there is a single point geo
              // entity.
              // In this case, return the x,y coordinates to OpenLayers.

              p = Pattern.compile("POINT\\((.*)\\)");
              m = p.matcher(bbox);
              if (m.matches())
              {
                String c = m.group(1);
                String[] xAndY = c.split(" ");
                double x = Double.valueOf(xAndY[0]);
                double y = Double.valueOf(xAndY[1]);

                try
                {
                  bboxArr.put(x);
                  bboxArr.put(y);
                }
                catch (JSONException ex)
                {
                  throw new ProgrammingErrorException(ex);
                }
              }
              else
              {
                String error = "The database view(s) [" + StringUtils.join(layerNames, ",")
                    + "] could not be used to create a valid bounding box";
                throw new GeoServerReloadException(error);
              }
            }
          }
        }

        return bboxArr;
      }
      catch (SQLException sqlEx1)
      {
        Database.throwDatabaseException(sqlEx1);
      }
      finally
      {
        try
        {
          java.sql.Statement statement = resultSet.getStatement();
          resultSet.close();
          statement.close();
        }
        catch (SQLException sqlEx2)
        {
          Database.throwDatabaseException(sqlEx2);
        }
      }
    }

    // Some problem occured and the bbox couldn't be calculated.
    // Just return the African defaults
    try
    {
      bboxArr.put(36.718452);
      bboxArr.put(-17.700377000000003);
      bboxArr.put(36.938452);
      bboxArr.put(-17.480376999999997);
    }
    catch (JSONException ex)
    {
      throw new ProgrammingErrorException(ex);
    }

    return bboxArr;
  }

}
