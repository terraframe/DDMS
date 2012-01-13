package dss.vector.solutions.query;

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

import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.commons.lang.StringUtils;
import org.json.JSONArray;
import org.json.JSONException;

import com.runwaysdk.business.Business;
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
import dss.vector.solutions.global.CredentialsSingleton;

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
        if (layer.hasThematicVariable())
        {
          Selectable thematicSel = valueQuery.getSelectableRef(layer.getThematicUserAlias());

          if (thematicSel instanceof SelectableDouble || thematicSel instanceof SelectableFloat || thematicSel instanceof SelectableDecimal)
          {
            SelectableSQLDouble newSel;
            if (thematicSel.isAggregateFunction())
            {
              newSel = valueQuery.aSQLAggregateDouble(thematicSel.getDbColumnName(), "");
            }
            else
            {
              newSel = valueQuery.aSQLDouble(thematicSel.getDbColumnName(), "");
            }
            newSel.setSQL(thematicSel.getSQL() + "::decimal(20,2)");
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

        String error = "The map could not be generated while getting the query [" + queryName + "] for layer [" + layerName + "]";

        if (e.getCause() instanceof InvocationTargetException)
        {
          InvocationTargetException ex = (InvocationTargetException) e.getCause();
          if (ex.getCause() instanceof QueryException)
          {
            if (i == 0)
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
      if (layer == null)
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
      if (!infoOnly)
      {
        layer.appLock();
      }

      layer.setViewName(newViewName);

      if (!infoOnly)
      {
        layer.apply();
      }

      if (i == 0)
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
      if (i != 0 && layer.getClipToBaseLayer())
      {
        List<Selectable> selectables = valueQuery.getSelectableRefs();
        Selectable geoSelectable = null;
        int count = 0;
        int removeInd = 0;
        for (Selectable selectable : selectables)
        {
          if (selectable.getColumnAlias().equals(QueryConstants.GEOMETRY_NAME_COLUMN))
          {
            geoSelectable = selectable;
            removeInd = count;
          }

          count++;
        }

        selectables.remove(removeInd);

        String geoAttr = geoSelectable.getDefiningTableAlias() + "." + geoSelectable.getDbColumnName();

        String clippingAlias = "geoentity_clipping";
        String clippingColumnAlias = "clipping_column";
        String clippingColumn = clippingAlias + "." + clippingColumnAlias;

        SelectableSQLCharacter geoS = valueQuery.aSQLAggregateCharacter(QueryConstants.GEOMETRY_NAME_COLUMN, "collect(intersection(" + geoAttr + ", " + clippingColumn + "))");
        selectables.add(geoS);

        valueQuery.clearSelectClause();
        valueQuery.SELECT(selectables.toArray(new Selectable[selectables.size()]));

        valueQuery.FROM("(SELECT " + QueryConstants.GEOMETRY_NAME_COLUMN + " AS " + clippingColumnAlias + " FROM " + baseView + ")", clippingAlias);

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
    String filePath = webDir + "/" + QueryConstants.SLD_WEB_DIR + QueryConstants.createSLDName(layer.getId()) + "." + QueryConstants.SLD_EXTENSION;

    // Generated a random query string to force GeoServer to not cache the SLD
    String random = String.valueOf(Math.random());
    random = random.substring(random.length() - 6);

    return filePath + "?a=" + random;
  }

  private static final Pattern        JSESSIONID_PATTERN = Pattern.compile("(?:.*?)JSESSIONID=(\\w*);.*");

  private static final ResourceBundle bundle             = ResourceBundle.getBundle("GeoServer", Locale.getDefault(), Business.class.getClassLoader());

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
      HttpClient client = new HttpClient();

      String geoserverPath = getGeoServerLocalURL();

      // poke the server to get a valid JSESSIONID in the cookie
      GetMethod pokeGet = null;
      String value;
      try
      {
        pokeGet = new GetMethod(geoserverPath + "/welcome.do");
        pokeGet.getParams().setParameter(HttpMethodParams.RETRY_HANDLER, new DefaultHttpMethodRetryHandler(3, false));

        NameValuePair[] pokeQueryString = new NameValuePair[] { new NameValuePair(CredentialsSingleton.GLOBAL_SESSION_ID, sessionId) };
        pokeGet.setQueryString(pokeQueryString);

        client.executeMethod(pokeGet);
        Header cookies = pokeGet.getResponseHeader("Set-Cookie");
        pokeGet.getResponseBody(); // REQUIRED
        value = cookies.getValue();
      }
      catch (Throwable t)
      {
        String error = "Attempt failed to get a valid JSESSION id into the cookie.";
        GeoServerReloadException ex = new GeoServerReloadException(error, t);
        throw ex;
      }
      finally
      {
        if (pokeGet != null)
        {
          pokeGet.releaseConnection();
        }
      }

      // A valid jSessionId is required for the next two calls (this is just the
      // way GeoServer does it).
      Matcher matcher = JSESSIONID_PATTERN.matcher(value);
      matcher.matches();

      String jSessionId = matcher.group(1);

      // reload the catalog (this will force GeoServer to dump any cached
      // features in memory and avoid slowdown over time)
      GetMethod get1 = null;
      try
      {
        get1 = new GetMethod(geoserverPath + "/admin/loadFromXML.do");
        get1.getParams().setParameter(HttpMethodParams.RETRY_HANDLER, new DefaultHttpMethodRetryHandler(3, false));
        NameValuePair[] params = new NameValuePair[] { new NameValuePair(CredentialsSingleton.GLOBAL_SESSION_ID, sessionId) };
        get1.setQueryString(params);

        int responseCode3 = client.executeMethod(get1);
        get1.getResponseBody(); // REQUIRED
      }
      catch (Throwable t)
      {
        String error = "Attempt failed to reload the GeoServer catalog.";
        GeoServerReloadException ex = new GeoServerReloadException(error, t);
        throw ex;
      }
      finally
      {
        if (get1 != null)
        {
          get1.releaseConnection();
        }
      }

      // Create each layer as a feature on GeoServer
      Iterator<Layer> iter = reloads.keySet().iterator();
      while (iter.hasNext())
      {
        Layer reload = iter.next();

        String viewName = reload.getViewName();
        String defaultStyle = reload.getRenderAs().get(0) == AllRenderTypes.POINT ? "point" : "polygon";

        PostMethod newPost = null;
        try
        {
          // request a new feature
          newPost = new PostMethod(geoserverPath + "/config/data/typeNewSubmit.do");
          newPost.getParams().setParameter(HttpMethodParams.RETRY_HANDLER, new DefaultHttpMethodRetryHandler(3, false));
          newPost.addRequestHeader("Cookie", "JSESSIONID=" + jSessionId);
          newPost.addParameter(CredentialsSingleton.GLOBAL_SESSION_ID, sessionId);
          newPost.addParameter("selectedNewFeatureType", QueryConstants.getNamespacedDataStore() + ":::" + viewName.toLowerCase());

          int newCode = client.executeMethod(newPost);
          newPost.getResponseBody(); // REQUIRED
        }
        catch (Throwable t)
        {
          String error = "Attempt failed to request the feature [" + viewName + "].";
          GeoServerReloadException ex = new GeoServerReloadException(error, t);
          throw ex;
        }
        finally
        {
          if (newPost != null)
          {
            newPost.releaseConnection();
          }
        }

        // create the feature
        PostMethod createPost = null;
        try
        {
          createPost = new PostMethod(geoserverPath + "/config/data/typeEditorSubmit.do");
          createPost.getParams().setParameter(HttpMethodParams.RETRY_HANDLER, new DefaultHttpMethodRetryHandler(3, false));
          createPost.addParameter(CredentialsSingleton.GLOBAL_SESSION_ID, sessionId);
          createPost.addRequestHeader("Cookie", "JSESSIONID=" + jSessionId);

          createPost.addParameter("panelStyleIds", defaultStyle);
          createPost.addParameter("styleId", defaultStyle);

          createPost.addParameter("SRS", "4326");
          createPost.addParameter("abstract", "Generated from MDSS_maps");
          createPost.addParameter("alias", "");
          createPost.addParameter("autoGenerateExtent", "true");
          createPost.addParameter("cacheMaxAge", "");
          createPost.addParameter("keywords", viewName);
          createPost.addParameter("maxFeatures", "0");

          // createPost.addParameter("metadataLink[0].content", "");
          // createPost.addParameter("metadataLink[0].metadataType", "FGDC");
          // createPost.addParameter("metadataLink[0].type", "text/plain");
          // createPost.addParameter("metadataLink[1].content", "");
          // createPost.addParameter("metadataLink[1].metadataType", "FGDC");
          // createPost.addParameter("metadataLink[1].type", "text/plain");

          createPost.addParameter("nameTemplate", "null");
          createPost.addParameter("regionateAttribute", "null");
          createPost.addParameter("regionateFeatureLimit", "15");
          createPost.addParameter("regionateStrategy", "best_guess");
          createPost.addParameter("schemaBase", "--");
          createPost.addParameter("srsHandling", "Force declared SRS (native will be ignored)");
          createPost.addParameter("title", viewName);
          createPost.addParameter("wmsPath", "/");

          createPost.addParameter("action", "Submit");

          int createCode = client.executeMethod(createPost);
          String ret = createPost.getResponseBodyAsString();
          System.out.println(ret);
        }
        catch (Throwable t)
        {
          String error = "Attempt failed to create the feature [" + viewName + "].";
          GeoServerReloadException ex = new GeoServerReloadException(error, t);
          throw ex;
        }
        finally
        {
          if (createPost != null)
          {
            createPost.releaseConnection();
          }
        }
      }

      // Apply
      PostMethod applyPost = null;
      try
      {
        applyPost = new PostMethod(geoserverPath + "/admin/saveToGeoServer.do");
        applyPost.getParams().setParameter(HttpMethodParams.RETRY_HANDLER, new DefaultHttpMethodRetryHandler(3, false));
        applyPost.addParameter(CredentialsSingleton.GLOBAL_SESSION_ID, sessionId);
        applyPost.addParameter("submit", "Apply");

        int applyResponse = client.executeMethod(applyPost);
        applyPost.getResponseBody(); // REQUIRED
      }
      catch (Throwable t)
      {
        String error = "Attempt failed to apply changes to GeoServer.";
        GeoServerReloadException ex = new GeoServerReloadException(error, t);
        throw ex;
      }
      finally
      {
        if (applyPost != null)
        {
          applyPost.releaseConnection();
        }
      }

      // // Save
      // PostMethod savePost = null;
      // try
      // {
      // savePost = new PostMethod(geoserverPath + "/admin/saveToXML.do");
      // savePost.getParams().setParameter(HttpMethodParams.RETRY_HANDLER,
      // new DefaultHttpMethodRetryHandler(3, false));
      // savePost.addParameter(CredentialsSingleton.GLOBAL_SESSION_ID,
      // sessionId);
      // savePost.addParameter("submit", "Save");
      //
      // int saveResponse = client.executeMethod(savePost);
      // savePost.getResponseBody(); // REQUIRED
      // }
      // catch (Throwable t)
      // {
      // String error = "Attempt failed to save changes to GeoServer.";
      // GeoServerReloadException ex = new GeoServerReloadException(error, t);
      // throw ex;
      // }
      // finally
      // {
      // if (savePost != null)
      // {
      // savePost.releaseConnection();
      // }
      // }
    }
    catch (Exception e)
    {
      String error = "Could not reload GeoServer.";
      GeoServerReloadException ex = new GeoServerReloadException(error, e);
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

        sql = "SELECT AsText(extent(" + viewName + "." + QueryConstants.GEOMETRY_NAME_COLUMN + ")) AS bbox FROM " + viewName;
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

          sql += "(SELECT " + QueryConstants.GEOMETRY_NAME_COLUMN + " AS geo_v FROM " + viewName + ") \n";

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
                String error = "The database view(s) [" + StringUtils.join(layerNames, ",") + "] could not be used to create a valid bounding box";
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
