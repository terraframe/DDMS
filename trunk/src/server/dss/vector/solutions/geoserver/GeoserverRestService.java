/*******************************************************************************
 * Copyright (C) 2018 IVCC
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 ******************************************************************************/
package dss.vector.solutions.geoserver;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.MissingResourceException;
import java.util.Set;
import java.util.concurrent.locks.ReentrantLock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.runwaysdk.configuration.RunwayConfigurationException;
import com.runwaysdk.constants.CommonProperties;
import com.runwaysdk.constants.DatabaseProperties;
import com.runwaysdk.dataaccess.ProgrammingErrorException;
import com.runwaysdk.dataaccess.ValueObject;
import com.runwaysdk.generation.loader.Reloadable;
import com.runwaysdk.gis.mapping.gwc.SeedRequest;
import com.runwaysdk.query.OIterator;
import com.runwaysdk.query.QueryFactory;
import com.runwaysdk.query.ValueQuery;
import com.runwaysdk.system.gis.ConfigurationException;
import com.runwaysdk.util.FileIO;

import dss.vector.solutions.geo.GeoServerReloadException;
import dss.vector.solutions.kaleidoscope.dashboard.DashboardStyle;
import dss.vector.solutions.kaleidoscope.dashboard.layer.DashboardLayer;
import dss.vector.solutions.kaleidoscope.gis.geoserver.MapLayerException;
import dss.vector.solutions.query.AllRenderTypes;
import dss.vector.solutions.query.Layer;
import dss.vector.solutions.query.MapConfiguration;
import dss.vector.solutions.query.QueryConstants;
import it.geosolutions.geoserver.rest.GeoServerRESTPublisher;
import it.geosolutions.geoserver.rest.GeoServerRESTReader;
import it.geosolutions.geoserver.rest.decoder.RESTLayerList;
import it.geosolutions.geoserver.rest.encoder.GSLayerEncoder;
import it.geosolutions.geoserver.rest.encoder.GSLayerGroupEncoder23;
import it.geosolutions.geoserver.rest.encoder.datastore.GSPostGISDatastoreEncoder;
import it.geosolutions.geoserver.rest.encoder.feature.GSFeatureTypeEncoder;
import it.geosolutions.geoserver.rest.manager.GeoServerRESTStoreManager;

public class GeoserverRestService implements GeoserverService, Reloadable
{

  /**
   * Checks if a given File is a cache directory for the workspace.
   */
  private class CacheFilter implements FileFilter, Reloadable
  {
    @Override
    public boolean accept(File file)
    {
      String workspace = GeoserverRestService.this.getWorkspace();

      return file.isDirectory() && file.getName().startsWith(workspace);
    }
  }

  private Logger                           logger     = LoggerFactory.getLogger(GeoserverFacade.class);

  /**
   * Default geoserver username
   */
  private static final String              USERNAME   = "admin";

  /**
   * Default geoserver password
   */
  private static final String              PASSWORD   = "geoserver";

  /**
   * Lock used for creating the workspace
   */
  private final ReentrantLock              lock       = new ReentrantLock();

  /**
   * Set of workspaces
   */
  private Set<String>                      workspaceSet;

  private static GeoServerRESTStoreManager manager    = null;

  private static GeoServerRESTPublisher    publisher  = null;

  private static GeoServerRESTReader       restReader = null;

  public void refresh()
  {
    if (this.getPublisher().reload())
    {
      logger.info("Reloaded geoserver.");
    }
    else
    {
      logger.warn("Failed to reload geoserver.");
    }
  }

  private String getGeoserverGWCDir()
  {
    return GeoserverProperties.getGeoserverGWCDir();
  }

  public String getWorkspace()
  {
    return CommonProperties.getDeployAppName();
  }

  public void removeStore()
  {
    String store = QueryConstants.getNamespacedDataStore();

    if (this.getPublisher().removeDatastore(this.getWorkspace(), store, true))
    {
      logger.info("Removed the datastore [" + store + "].");
    }
    else
    {
      logger.warn("Failed to remove the datastore [" + store + "].");
    }
  }

  public void removeStore(String workspaceName, String storeName)
  {

    if (this.getPublisher().removeDatastore(workspaceName, storeName, true))
    {
      logger.info("Removed the datastore [" + storeName + "].");
    }
    else
    {
      logger.warn("Failed to remove the datastore [" + storeName + "].");
    }
  }

  public void removeWorkspace()
  {
    if (this.getPublisher().removeWorkspace(this.getWorkspace(), true))
    {
      logger.info("Removed the workspace [" + this.getWorkspace() + "].");
    }
    else
    {
      logger.warn("Failed to remove the workspace [" + this.getWorkspace() + "].");
    }
  }

  public void removeWorkspace(String workspaceName)
  {
    if (this.getPublisher().removeWorkspace(workspaceName, true))
    {
      logger.info("Removed the workspace [" + workspaceName + "].");
    }
    else
    {
      logger.warn("Failed to remove the workspace [" + workspaceName + "].");
    }
  }

  /**
   * Checks if Geoserver is available.
   * 
   * @return
   */
  public boolean geoserverExists()
  {
    String path = this.getGeoServerLocalURL();

    if (this.getReader().existGeoserver())
    {
      logger.info("A geoserver instance is running at [" + path + "].");
      return true;
    }
    else
    {
      logger.warn("A geoserver instance is NOT running at [" + path + "].");
      return false;
    }
  }

  /**
   * Checks if the given workspace exists.
   * 
   * @return
   */
  public boolean workspaceExists()
  {
    GeoServerRESTReader reader = this.getReader();

    return reader.existsWorkspace(this.getWorkspace());
  }

  /**
   * Checks if the given workspace exists.
   * 
   * @return
   */
  public boolean workspaceExists(String workspaceName)
  {
    GeoServerRESTReader reader = this.getReader();

    return reader.existsWorkspace(workspaceName);
  }

  /**
   * Checks if the given style exists in geoserver.
   * 
   * @param styleName
   * @return
   */
  public boolean styleExists(String styleName)
  {
    GeoServerRESTReader reader = this.getReader();

    return reader.existsStyle(styleName);
  }

  /**
   * Checks if the cache directory exists. This method does not check what tiles or zoom levels have been cached.
   * 
   * @param cacheName
   * @return
   */
  public boolean cacheExists(String cacheName)
  {
    String cacheDir = this.getGeoserverGWCDir() + cacheName;
    File cache = new File(cacheDir);
    return cache.exists();
  }

  /**
   * Gets all layers declared in Geoserver for the workspace.
   * 
   * @return
   */
  public List<String> getLayers()
  {
    GeoServerRESTReader reader = this.getReader();

    return reader.getLayers().getNames();
  }

  /**
   * Gets all styles declared in Geoserver for the workspace.
   */
  public List<String> getStyles()
  {
    return this.getReader().getStyles().getNames();
  }

  /**
   * Removes the style defined in Geoserver, including the .sld and .xml file artifacts.
   * 
   * @param styleName
   *          The name of the style to delete.
   */
  public void removeStyle(String styleName)
  {
//    if (styleExists(styleName))
    {
      if (this.getPublisher().removeStyle(styleName, true))
      {
        logger.info("Removed the SLD [" + styleName + "].");
      }
      else
      {
        logger.warn("Failed to remove the SLD [" + styleName + "].");
      }

    }
  }

  public boolean publishStyle(String styleName, File sldFile, boolean force)
  {
    GeoServerRESTPublisher publisher = this.getPublisher();

    if (force && styleExists(styleName))
    {
      publisher.removeStyle(styleName, true);
    }

    if (publisher.publishStyle(sldFile, styleName, true))
    {
      logger.info("Published the SLD [" + styleName + "].");
      return true;
    }
    else
    {
      logger.warn("Failed to publish the SLD [" + styleName + "].");
      return false;
    }
  }

  public boolean publishStyle(String styleName, String body, boolean force)
  {
    GeoServerRESTPublisher publisher = this.getPublisher();

    if (force && styleExists(styleName))
    {
      publisher.removeStyle(styleName, true);
    }

    if (publisher.publishStyle(body, styleName, true))
    {
      logger.info("Published the SLD [" + styleName + "].");
      return true;
    }
    else
    {
      logger.warn("Failed to publish the SLD [" + styleName + "].");
      return false;
    }
  }

  /**
   * Publishes the SQL of the given name with the XML body.
   * 
   * @param styleName
   * @param body
   */
  public boolean publishStyle(String styleName, String body)
  {
    return publishStyle(styleName, body, true);
  }

  public void pushUpdates(GeoserverBatch batch)
  {
    ArrayList<String> layersToDrop = batch.getLayersToDrop();
    ArrayList<String> stylesToDrop = batch.getStylesToDrop();
    ArrayList<DashboardLayer> layersToPublish = batch.getLayersToPublish();

    for (String layerName : layersToDrop)
    {
      removeLayer(layerName);
    }

    for (String styleName : stylesToDrop)
    {
      removeStyle(styleName);
    }

    // GeoServer will say these layers already exist if we don't refresh here.
    if (layersToDrop.size() > 0 && layersToPublish.size() > 0)
    {
      refresh();
    }

    for (DashboardLayer layer : layersToPublish)
    {
      String viewName = layer.getViewName();

      List<? extends DashboardStyle> styles = layer.getStyles();
      for (int i = 0; i < styles.size(); ++i)
      {
        DashboardStyle style = styles.get(i);

        publishStyle(style.getName(), style.generateSLD(), false);
      }

      publishLayer(viewName, viewName);
    }
  }

  /**
   * Removes the layer from geoserver.
   * 
   * @param layer
   * @return
   */
  public void removeLayer(String layer)
  {
    String workspace = this.getWorkspace();

//    if (GeoserverFacade.layerExists(workspace + ":" + layer, workspace))
    {
      GeoServerRESTPublisher publisher = this.getPublisher();

      if (publisher.removeLayer(workspace, layer))
      {
        logger.info("Removed the layer for [" + layer + "].");
      }
      else
      {
        logger.warn("Failed to remove the layer for [" + layer + "].");
      }
    }
  }

  /**
   * Removes the layer from geoserver.
   * 
   * @param layer
   * @return
   */
  public boolean removeLayer(String layer, String workspace)
  {
//    if (GeoserverFacade.layerExists(layer, workspace))
    {
      GeoServerRESTPublisher publisher = this.getPublisher();

      if (publisher.removeLayer(workspace, layer))
      {
        logger.info("Removed the layer for [" + layer + "].");
        return true;
      }
      else
      {
        logger.warn("Failed to remove the layer for [" + layer + "].");
        return false;
      }
    }

//    return false;
  }

  public void publishCache(String layer)
  {
    SeedRequest request = new SeedRequest(layer);
    if (request.doRequest())
    {
      logger.info("Started seeding layer [" + layer + "].");
    }
    else
    {
      logger.warn("Could not seed layer [" + layer + "]. Response code [" + request.getCode() + "].");
    }
  }

  public void rePublishCache(String layer, String workspace)
  {
    ReseedRequest request = new ReseedRequest(layer, workspace);
    if (request.doRequest())
    {
      logger.info("Started seeding layer [" + layer + "].");
    }
    else
    {
      logger.warn("Could not seed layer [" + layer + "]. Response code [" + request.getCode() + "].");
    }
  }

  /**
   * Returns a list of all cache directories.
   * 
   * @return
   */
  public File[] getCaches()
  {
    File cacheRoot = new File(this.getGeoserverGWCDir());
    return cacheRoot.listFiles(new CacheFilter());
  }

  public void removeCache(File cache)
  {
    if (cache.exists())
    {
      try
      {
        FileIO.deleteDirectory(cache);

        if (cache.exists())
        {
          logger.info("Deleted the cache [" + cache + "].");
        }
        else
        {
          logger.warn("Failed to delete the cache [" + cache + "].");
        }
      }
      catch (IOException e)
      {
        logger.error("Error deleting the cache [" + cache + "].", e);
      }
    }
    else
    {
      logger.info("The cache [" + cache + "] does not exist.");
    }
  }

  /**
   * Removes all cache files and directories.
   * 
   * @param cacheName
   */
  public void removeCache(String cacheName, String workspace)
  {
    String cacheDir = null;
    try
    {
      cacheDir = GeoserverProperties.getDefaultGeoWebCacheDirPath() + File.separator + workspace + "_" + cacheName;
    }
    catch (MissingResourceException e)
    {
      throw new ProgrammingErrorException(e);
    }

    if (cacheDir != null)
    {
      removeCache(new File(cacheDir));
    }
  }

  /**
   * Checks if the given layer group exists in Geoserver.
   * 
   * @param layerGroup
   * @return
   */
  public boolean layerGroupExists(String layerGroup)
  {
    GeoServerRESTReader reader = this.getReader();

    boolean exists = reader.existsLayerGroup(this.getWorkspace(), layerGroup);

    return exists;
  }

  /**
   * Checks if the given layer group exists in Geoserver.
   * 
   * @param layerGroup
   * @return
   */
  public boolean layerGroupExists(String layerGroup, String workspace)
  {
    GeoServerRESTReader reader = this.getReader();

    boolean exists = reader.existsLayerGroup(layerGroup, workspace);

    return exists;
  }

  /**
   * Checks if the given layer exists in Geoserver.
   * 
   * @param layer
   * @return
   */
  public boolean layerExists(String layer)
  {
    GeoServerRESTReader reader = this.getReader();

    boolean exists = reader.existsLayer(this.getWorkspace(), layer);

    return exists;
  }

  /**
   * Checks if the given layer exists in Geoserver.
   * 
   * @param layer
   * @return
   */
  public boolean layerExists(String layer, String workspace)
  {
    GeoServerRESTReader reader = this.getReader();

    boolean exists = reader.existsLayer(layer, workspace);

    return exists;
  }

  /**
   * Checks if the given layer has a database view.
   * 
   * @param layer
   * @return
   */
  public boolean viewExists(String viewName)
  {
    // create the view if the view exists in the pg metadata
    ValueQuery check = new ValueQuery(new QueryFactory());

    check.SELECT(check.aSQLAggregateInteger("ct", "count(*)"));
    check.FROM("pg_views", "pg_views");
    check.WHERE(check.aSQLCharacter("viewname", "viewname").EQ(viewName));

    OIterator<? extends ValueObject> iter = check.getIterator();
    try
    {
      ValueObject vo = iter.next();
      if (Integer.valueOf(vo.getValue("ct")) == 1)
      {
        return true;
      }
      else
      {
        return false;
      }
    }
    finally
    {
      iter.close();
    }
  }

  @Override
  public double[] getExpandedBBOX(List<String> views, double expandVal)
  {
    // collect all the views and extend the bounding box
    ValueQuery union = new ValueQuery(new QueryFactory());
    if (views.size() == 1)
    {
      String view = views.iterator().next();
      union.SELECT(union.aSQLClob(GeoserverFacade.GEOM_COLUMN, GeoserverFacade.GEOM_COLUMN, GeoserverFacade.GEOM_COLUMN));
      union.FROM(view, view);
    }
    else if (views.size() > 1)
    {
      ValueQuery[] unionVQs = new ValueQuery[views.size()];

      for (int i = 0; i < unionVQs.length; i++)
      {
        String view = views.get(i);
        ValueQuery vq = new ValueQuery(union.getQueryFactory());
        vq.SELECT(vq.aSQLClob(GeoserverFacade.GEOM_COLUMN, GeoserverFacade.GEOM_COLUMN, GeoserverFacade.GEOM_COLUMN));
        vq.FROM(view, view);

        unionVQs[i] = vq;
      }

      union.UNION_ALL(unionVQs);
    }
    else
    {
      throw new MapLayerException("The map has no layers");
    }

    ValueQuery collected = new ValueQuery(union.getQueryFactory());
    collected.SELECT(collected.aSQLAggregateClob("collected", "ST_Expand(ST_Collect(" + GeoserverFacade.GEOM_COLUMN + "), " + expandVal + ")", "collected"));
    collected.FROM("(" + union.getSQL() + ")", "unioned");

    ValueQuery outer = new ValueQuery(union.getQueryFactory());
    outer.SELECT(union.aSQLAggregateDouble("minx", "st_xmin(collected)"), union.aSQLAggregateDouble("miny", "st_ymin(collected)"), union.aSQLAggregateDouble("maxx", "st_xmax(collected)"), union.aSQLAggregateDouble("maxy", "st_ymax(collected)"));

    outer.FROM("(" + collected.getSQL() + ")", "collected");

    OIterator<? extends ValueObject> iter = outer.getIterator();

    try
    {
      ValueObject o = iter.next();

      double[] bbox = new double[4];
      bbox[GeoserverFacade.MINX_INDEX] = Double.parseDouble(o.getValue("minx"));
      bbox[GeoserverFacade.MINY_INDEX] = Double.parseDouble(o.getValue("miny"));
      bbox[GeoserverFacade.MAXX_INDEX] = Double.parseDouble(o.getValue("maxx"));
      bbox[GeoserverFacade.MAXY_INDEX] = Double.parseDouble(o.getValue("maxy"));

      return bbox;
    }
    catch (Exception e)
    {
      return null;
      // throw new NoLayerDataException();
    }
    finally
    {
      iter.close();
    }
  }

  /**
   * Returns the url to access GeoServer locally.
   * 
   * @return
   */
  public String getGeoServerLocalURL()
  {
    return GeoserverProperties.getGeoServerLocalURL();
  }

  /**
   * Returns the url to access GeoServer remotely.
   * 
   * @return
   */
  public String getGeoServerRemoteURL()
  {
    return GeoserverProperties.getGeoServerRemoteURL();
  }

  @Override
  public String getAppName()
  {
    return GeoserverProperties.getAppName();
  }

  public GeoServerRESTReader getGeoServerReader() throws MalformedURLException
  {
    String geoserverPath = getGeoServerLocalURL();
    GeoServerRESTReader reader = new GeoServerRESTReader(geoserverPath, USERNAME, PASSWORD);

    return reader;
  }

  public synchronized GeoServerRESTReader getReader()
  {
    if (restReader == null)
    {
      try
      {
        restReader = new GeoServerRESTReader(getGeoServerLocalURL(), USERNAME, PASSWORD);
      }
      catch (MalformedURLException e)
      {
        throw new RunwayConfigurationException(e);
      }
    }

    return restReader;
  }

  public void reload(Map<Layer, ValueQuery> reloads, MapConfiguration configuration)
  {
    try
    {
      GeoServerRESTReader reader = this.getGeoServerReader();

      try
      {
        this.lock.lock();

        if (this.workspaceSet == null)
        {
          this.workspaceSet = new HashSet<String>(reader.getWorkspaceNames());
        }

        if (!this.workspaceSet.contains(CommonProperties.getDeployAppName()))
        {
          createWorkspaceAndDatastore();

          this.workspaceSet = new HashSet<String>(reader.getWorkspaceNames());
        }
      }
      finally
      {
        this.lock.unlock();
      }

      GeoServerRESTPublisher publisher = getPublisher();

      // reload the catalog (this will force GeoServer to dump any cached
      // features in memory and avoid slowdown over time)
      publisher.reload();

      // Create each layer as a feature on GeoServer
      Iterator<Layer> iter = reloads.keySet().iterator();
      while (iter.hasNext())
      {
        Layer reload = iter.next();

        String viewName = configuration.getViewName(reload);
        String defaultStyle = reload.getRenderAs().get(0) == AllRenderTypes.POINT ? "point" : "polygon";
        // publisher.publishDBLayer(CommonProperties.getDeployAppName(), QueryConstants.getNamespacedDataStore(), viewName, "EPSG:4326",
        // defaultStyle);
        publishLayer(viewName, defaultStyle);
      }
    }
    catch (Exception e)
    {
      String error = "Could not reload GeoServer.";
      GeoServerReloadException ex = new GeoServerReloadException(error, e);
      throw ex;
    }
  }

  /**
   * Adds a database view and publishes the layer if necessary.
   * 
   * @param layer
   */
  public boolean publishLayerGroup(String[] layers, String workspace, String groupName)
  {

    GSLayerGroupEncoder23 groupWriter = new GSLayerGroupEncoder23();
    for (String layer : layers)
    {
      groupWriter.addLayer(workspace.concat(":").concat(layer));
    }

    if (getPublisher().createLayerGroup(workspace, groupName, groupWriter))
    {
      logger.debug("Created the layer group [" + groupName + "] in geoserver.");
      return true;
    }
    else
    {
      logger.error("Failed to create the layer group [" + groupName + "] in geoserver.");
      return false;
    }
  }

  /**
   * Adds a database view and publishes the layer if necessary.
   * 
   * @param layer
   */
  public boolean publishLayer(String layer, String styleName)
  {
    double[] bbox = getBBOX(layer);

    GSFeatureTypeEncoder fte = new GSFeatureTypeEncoder();
    fte.setEnabled(true);
    fte.setName(layer);
    fte.setSRS(GeoserverFacade.SRS);
    fte.setTitle(layer);
    fte.addKeyword(layer);

    if (bbox != null)
    {
      double minX = bbox[GeoserverFacade.MINX_INDEX];
      double minY = bbox[GeoserverFacade.MINY_INDEX];
      double maxX = bbox[GeoserverFacade.MAXX_INDEX];
      double maxY = bbox[GeoserverFacade.MAXY_INDEX];

      fte.setNativeBoundingBox(minX, minY, maxX, maxY, GeoserverFacade.SRS);
      fte.setLatLonBoundingBox(minX, minY, maxX, maxY, GeoserverFacade.SRS);
    }

    GSLayerEncoder le = new GSLayerEncoder();
    le.setDefaultStyle(styleName);
    le.setEnabled(true);

    if (getPublisher().publishDBLayer(CommonProperties.getDeployAppName(), QueryConstants.getNamespacedDataStore(), fte, le))
    {
      logger.debug("Created the layer [" + layer + "] in geoserver.");
      return true;
    }
    else
    {
      logger.error("Failed to create the layer [" + layer + "] in geoserver.");
      return false;
    }
  }

  public boolean publishOSMLayer(String layer, String styleName)
  {
    if (layerExists(layer))
    {
      boolean removed = removeLayer(layer, "OSM");
      System.out.println(removed);
    }

    double[] bbox = null;
    try
    {
      bbox = getOSMBBOX(layer);
    }
    catch (SQLException e)
    {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

    GSFeatureTypeEncoder fte = new GSFeatureTypeEncoder();
    fte.setEnabled(true);
    fte.setName(layer);
    fte.setSRS(GeoserverFacade.SRS);
    fte.setTitle(layer);
    fte.addKeyword(layer);

    if (bbox != null)
    {
      double minX = bbox[GeoserverFacade.MINX_INDEX];
      double minY = bbox[GeoserverFacade.MINY_INDEX];
      double maxX = bbox[GeoserverFacade.MAXX_INDEX];
      double maxY = bbox[GeoserverFacade.MAXY_INDEX];

      fte.setNativeBoundingBox(minX, minY, maxX, maxY, GeoserverFacade.SRS);
      fte.setLatLonBoundingBox(minX, minY, maxX, maxY, GeoserverFacade.SRS);
    }

    GSLayerEncoder le = new GSLayerEncoder();
    le.setDefaultStyle(styleName);
    le.setEnabled(true);

    if (getPublisher().publishDBLayer("OSM", "OSM", fte, le))
    {
      logger.debug("Created the layer [" + layer + "] in geoserver.");
      return true;
    }
    else
    {
      logger.error("Failed to create the layer [" + layer + "] in geoserver.");
      return false;
    }
  }

  public double[] getOSMBBOX(String table) throws SQLException
  {
    Connection dbConnection = null;
    Statement statement = null;

    String query = "SELECT (st_xmin(collected)) as minx, (st_ymin(collected)) as miny, (st_xmax(collected)) as maxx, (st_ymax(collected)) as maxy ";
    query = query.concat("FROM ( SELECT (st_collect(way)) AS collected ");
    query = query.concat("FROM (SELECT (way) AS way FROM ".concat(table));
    query = query.concat(") unioned) collected;");

    try
    {
      dbConnection = getOSMDBConnection();
      statement = dbConnection.createStatement();

      ResultSet rs = statement.executeQuery(query);

      while (rs.next())
      {

        double[] bbox = new double[4];
        bbox[GeoserverFacade.MINX_INDEX] = Double.parseDouble(rs.getString("minx"));
        bbox[GeoserverFacade.MINY_INDEX] = Double.parseDouble(rs.getString("miny"));
        bbox[GeoserverFacade.MAXX_INDEX] = Double.parseDouble(rs.getString("maxx"));
        bbox[GeoserverFacade.MAXY_INDEX] = Double.parseDouble(rs.getString("maxy"));

        return bbox;
      }

    }
    catch (SQLException e)
    {
      System.out.println(e);
    }
    finally
    {

      if (statement != null)
      {
        statement.close();
      }

      if (dbConnection != null)
      {
        dbConnection.close();
      }

    }

    return null;
  }

  private static Connection getOSMDBConnection()
  {

    final String DB_DRIVER = "org.postgresql.Driver";
    final String DB_CONNECTION = "jdbc:postgresql://localhost:".concat(Integer.toString(DatabaseProperties.getPort())).concat("/osm");
    final String DB_USER = DatabaseProperties.getUser();
    final String DB_PASSWORD = DatabaseProperties.getPassword();

    Connection dbConnection = null;

    try
    {

      Class.forName(DB_DRIVER);

    }
    catch (ClassNotFoundException e)
    {

      System.out.println(e.getMessage());

    }

    try
    {

      dbConnection = DriverManager.getConnection(DB_CONNECTION, DB_USER, DB_PASSWORD);
      return dbConnection;

    }
    catch (SQLException e)
    {

      System.out.println(e.getMessage());

    }

    return dbConnection;

  }

  /**
   * Calculates the bounding box of all the layers.
   * 
   * @param views
   * @return double[] {minx, miny, maxx, maxy}
   */
  public double[] getBBOX(String... views)
  {
    // collect all the views and extend the bounding box
    ValueQuery union = new ValueQuery(new QueryFactory());
    if (views.length == 1)
    {
      String view = views[0];
      union.SELECT(union.aSQLClob(GeoserverFacade.GEOM_COLUMN, GeoserverFacade.GEOM_COLUMN, GeoserverFacade.GEOM_COLUMN));
      union.FROM(view, view);
    }
    else if (views.length > 1)
    {
      ValueQuery[] unionVQs = new ValueQuery[views.length];

      for (int i = 0; i < unionVQs.length; i++)
      {
        String view = views[i];
        ValueQuery vq = new ValueQuery(union.getQueryFactory());
        vq.SELECT(vq.aSQLClob(GeoserverFacade.GEOM_COLUMN, GeoserverFacade.GEOM_COLUMN, GeoserverFacade.GEOM_COLUMN));
        vq.FROM(view, view);

        unionVQs[i] = vq;
      }

      union.UNION_ALL(unionVQs);
    }
    else
    {
      // throw new MapLayerException("The map has no layers");
      return null;
    }

    ValueQuery collected = new ValueQuery(union.getQueryFactory());
    collected.SELECT(collected.aSQLAggregateClob("collected", "st_collect(" + GeoserverFacade.GEOM_COLUMN + ")", "collected"));
    collected.FROM("(" + union.getSQL() + ")", "unioned");

    ValueQuery outer = new ValueQuery(union.getQueryFactory());
    outer.SELECT(union.aSQLAggregateDouble("minx", "st_xmin(collected)"), union.aSQLAggregateDouble("miny", "st_ymin(collected)"), union.aSQLAggregateDouble("maxx", "st_xmax(collected)"), union.aSQLAggregateDouble("maxy", "st_ymax(collected)"));

    outer.FROM("(" + collected.getSQL() + ")", "collected");

    OIterator<? extends ValueObject> iter = outer.getIterator();

    try
    {
      ValueObject o = iter.next();

      double[] bbox = new double[4];
      bbox[GeoserverFacade.MINX_INDEX] = Double.parseDouble(o.getValue("minx"));
      bbox[GeoserverFacade.MINY_INDEX] = Double.parseDouble(o.getValue("miny"));
      bbox[GeoserverFacade.MAXX_INDEX] = Double.parseDouble(o.getValue("maxx"));
      bbox[GeoserverFacade.MAXY_INDEX] = Double.parseDouble(o.getValue("maxy"));

      return bbox;
    }
    catch (Exception e)
    {
      return null;
      // throw new NoLayerDataException();
    }
    finally
    {
      iter.close();
    }

  }

  public void removeLayers(String... layerNames)
  {
    try
    {
      GeoServerRESTPublisher publisher = getPublisher();

      for (String layerName : layerNames)
      {
        publisher.removeLayer(this.getWorkspace(), layerName);
      }
    }
    catch (Exception e)
    {
      // Do nothing
      e.printStackTrace();
    }

  }

  public List<String> getLayerNames()
  {
    try
    {
      GeoServerRESTReader reader = this.getGeoServerReader();
      RESTLayerList list = reader.getLayers();

      return list.getNames();
    }
    catch (Exception e)
    {
      // Do nothing
      e.printStackTrace();
    }

    return new LinkedList<String>();
  }

  public void cleanupLayers()
  {
    try
    {
      this.lock.lock();

      List<String> names = this.getLayerNames();
      Iterator<String> iterator = names.iterator();

      while (iterator.hasNext())
      {
        String layerName = iterator.next();

        if (!layerName.startsWith(Layer.GEO_VIEW_PREFIX))
        {
          iterator.remove();
        }
      }

      this.removeLayers(names.toArray(new String[names.size()]));
    }
    finally
    {
      this.lock.unlock();
    }
  }

  public synchronized GeoServerRESTPublisher getPublisher()
  {
    if (publisher == null)
    {
      String geoserverPath = getGeoServerLocalURL();
      publisher = new GeoServerRESTPublisher(geoserverPath, "admin", "geoserver");
    }

    return publisher;
  }

  public synchronized GeoServerRESTStoreManager getManager()
  {
    if (manager == null)
    {
      try
      {
        URL geoserverPath = new URL(getGeoServerLocalURL());
        manager = new GeoServerRESTStoreManager(geoserverPath, "admin", "geoserver");
      }
      catch (MalformedURLException e)
      {
        throw new RuntimeException(e);
      }
    }

    return manager;
  }

  public void publishWorkspace()
  {
    String appName = CommonProperties.getDeployAppName();

    try
    {

      if (getPublisher().createWorkspace(appName, new URI(appName)))
      {
        logger.info("Created the workspace [" + appName + "].");
      }
      else
      {
        logger.warn("Failed to create the workspace [" + appName + "].");
      }
    }
    catch (URISyntaxException e)
    {
      throw new ConfigurationException("The URI [" + getGeoServerLocalURL() + "] is invalid.", e);
    }
  }

  public void publishWorkspace(String workspaceName)
  {

    try
    {

      if (getPublisher().createWorkspace(workspaceName, new URI(workspaceName)))
      {
        logger.info("Created the workspace [" + workspaceName + "].");
      }
      else
      {
        logger.warn("Failed to create the workspace [" + workspaceName + "].");
      }
    }
    catch (URISyntaxException e)
    {
      throw new ConfigurationException("The URI [" + getGeoServerLocalURL() + "] is invalid.", e);
    }
  }

  public void publishStore()
  {
    String appName = CommonProperties.getDeployAppName();
    String dbSchema = DatabaseProperties.getNamespace().length() != 0 ? DatabaseProperties.getNamespace() : "public";

    GSPostGISDatastoreEncoder datastore = new GSPostGISDatastoreEncoder(QueryConstants.getNamespacedDataStore());
    datastore.setDatabase(DatabaseProperties.getDatabaseName());
    datastore.setUser(DatabaseProperties.getUser());
    datastore.setPassword(DatabaseProperties.getPassword());
    datastore.setName(QueryConstants.getNamespacedDataStore());
    datastore.setHost(DatabaseProperties.getServerName());
    datastore.setPort(DatabaseProperties.getPort());
    datastore.setSchema(dbSchema);
    datastore.setNamespace(appName);
    datastore.setEnabled(true);
    datastore.setMaxConnections(10);
    datastore.setMinConnections(1);
    datastore.setFetchSize(1000);
    datastore.setConnectionTimeout(20);
    datastore.setValidateConnections(true);
    datastore.setLooseBBox(true);
    datastore.setExposePrimaryKeys(false);
    datastore.setPreparedStatements(false);

    GeoServerRESTStoreManager manager = getManager();
    if (manager.create(appName, datastore))
    {
      logger.info("Published the store [" + QueryConstants.getNamespacedDataStore() + "].");
    }
    else
    {
      logger.warn("Failed to publish the store [" + QueryConstants.getNamespacedDataStore() + "].");
    }
  }

  public void publishStore(String storeName, String dbName, String schemaName)
  {
    String dbSchema = "public";
    if (schemaName != null)
    {
      dbSchema = schemaName;
    }
    else
    {
      dbSchema = DatabaseProperties.getNamespace().length() != 0 ? DatabaseProperties.getNamespace() : "public";
    }

    GSPostGISDatastoreEncoder datastore = new GSPostGISDatastoreEncoder(storeName);
    datastore.setDatabase(dbName);
    datastore.setUser(DatabaseProperties.getUser());
    datastore.setPassword(DatabaseProperties.getPassword());
    datastore.setName(storeName);
    datastore.setHost(DatabaseProperties.getServerName());
    datastore.setPort(DatabaseProperties.getPort());
    datastore.setSchema(dbSchema);
    datastore.setNamespace(storeName);
    datastore.setEnabled(true);
    datastore.setMaxConnections(10);
    datastore.setMinConnections(1);
    datastore.setFetchSize(1000);
    datastore.setConnectionTimeout(20);
    datastore.setValidateConnections(true);
    datastore.setLooseBBox(true);
    datastore.setExposePrimaryKeys(false);
    datastore.setPreparedStatements(false);

    GeoServerRESTStoreManager manager = getManager();
    if (manager.create(storeName, datastore))
    {
      logger.info("Published the store [" + storeName + "].");
    }
    else
    {
      logger.warn("Failed to publish the store [" + storeName + "].");
    }
  }

  public void createWorkspaceAndDatastore()
  {
    try
    {
      publishWorkspace();
      publishStore();
    }
    catch (Exception e)
    {
      String error = "Could not create Geoserver artifacts";
      GeoServerReloadException ex = new GeoServerReloadException(error);
      throw ex;
    }
  }

  @Override
  public Integer getDecimalLength()
  {
    return 20;
  }

  @Override
  public Integer getDecimalPrecision()
  {
    return 2;
  }

  @Override
  public boolean existGeoserver()
  {
    try
    {
      return this.getGeoServerReader().existGeoserver();
    }
    catch (MalformedURLException e)
    {
      throw new ConfigurationException("The URI [" + getGeoServerLocalURL() + "] is invalid.", e);
    }
  }
}
