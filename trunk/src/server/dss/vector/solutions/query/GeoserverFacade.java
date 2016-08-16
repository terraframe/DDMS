package dss.vector.solutions.query;

import it.geosolutions.geoserver.rest.GeoServerRESTPublisher;
import it.geosolutions.geoserver.rest.GeoServerRESTReader;
import it.geosolutions.geoserver.rest.decoder.RESTLayerList;
import it.geosolutions.geoserver.rest.encoder.GSLayerEncoder;
import it.geosolutions.geoserver.rest.encoder.datastore.GSPostGISDatastoreEncoder;
import it.geosolutions.geoserver.rest.encoder.feature.GSFeatureTypeEncoder;
import it.geosolutions.geoserver.rest.manager.GeoServerRESTStoreManager;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.concurrent.locks.ReentrantLock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.runwaysdk.business.Business;
import com.runwaysdk.configuration.RunwayConfigurationException;
import com.runwaysdk.constants.CommonProperties;
import com.runwaysdk.constants.DatabaseProperties;
import com.runwaysdk.dataaccess.ValueObject;
import com.runwaysdk.generation.loader.Reloadable;
import com.runwaysdk.query.OIterator;
import com.runwaysdk.query.QueryFactory;
import com.runwaysdk.query.ValueQuery;
import com.runwaysdk.system.gis.ConfigurationException;
import com.terraframe.utf8.UTF8ResourceBundle;

import dss.vector.solutions.geo.GeoServerReloadException;

public class GeoserverFacade implements Reloadable
{
  private Logger logger = LoggerFactory.getLogger(GeoserverFacade.class);
  
  public final int    SRS_CODE   = 4326;

  public final String SRS        = "EPSG:" + SRS_CODE;

  public static final String      GEOM_COLUMN = QueryConstants.GEOMETRY_NAME_COLUMN;
  
  public int          MINX_INDEX = 0;

  public int          MINY_INDEX = 1;

  public int          MAXX_INDEX = 2;

  public int          MAXY_INDEX = 3;
  
  /**
   * Default geoserver username
   */
  private static final String  USERNAME = "admin";

  /**
   * Default geoserver password
   */
  private static final String  PASSWORD = "geoserver";

  /**
   * Bundle for reading properties file
   */
  private static final ResourceBundle bundle   = UTF8ResourceBundle.getBundle("GeoServer", Locale.getDefault(), Business.class.getClassLoader());

  /**
   * Lock used for creating the workspace
   */
  private final ReentrantLock  lock;

  /**
   * Set of workspaces
   */
  private Set<String>          workspaceSet;
  
  private static GeoServerRESTStoreManager manager = null;
  
  private static GeoServerRESTPublisher publisher = null;
  
  private static GeoServerRESTReader restReader = null;

  public GeoserverFacade()
  {
    this.lock = new ReentrantLock();
  }

  /**
   * Returns the url to access GeoServer locally.
   * 
   * @return
   */
  public static String getGeoServerLocalURL()
  {
    return bundle.getString("geoserver.local.path");
  }

  /**
   * Returns the url to access GeoServer remotely.
   * 
   * @return
   */
  public String getGeoServerRemoteURL()
  {
    return bundle.getString("geoserver.remote.path");
  }

  public GeoServerRESTReader getGeoServerReader() throws MalformedURLException
  {
    String geoserverPath = getGeoServerLocalURL();
    GeoServerRESTReader reader = new GeoServerRESTReader(geoserverPath, USERNAME, PASSWORD);

    return reader;
  }
  
  public static synchronized GeoServerRESTReader getReader()
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
//        publisher.publishDBLayer(CommonProperties.getDeployAppName(), QueryConstants.getNamespacedDataStore(), viewName, "EPSG:4326", defaultStyle);
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
  public boolean publishLayer(String layer, String styleName)
  {
    double[] bbox = getBBOX(layer);

    GSFeatureTypeEncoder fte = new GSFeatureTypeEncoder();
    fte.setEnabled(true);
    fte.setName(layer);
    fte.setSRS(SRS);
    fte.setTitle(layer);
    fte.addKeyword(layer);

    if (bbox != null)
    {
      double minX = bbox[MINX_INDEX];
      double minY = bbox[MINY_INDEX];
      double maxX = bbox[MAXX_INDEX];
      double maxY = bbox[MAXY_INDEX];

      fte.setNativeBoundingBox(minX, minY, maxX, maxY, SRS);
      fte.setLatLonBoundingBox(minX, minY, maxX, maxY, SRS);
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
//      throw new MapLayerException("The map has no layers");
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
      bbox[MINX_INDEX] = Double.parseDouble(o.getValue("minx"));
      bbox[MINY_INDEX] = Double.parseDouble(o.getValue("miny"));
      bbox[MAXX_INDEX] = Double.parseDouble(o.getValue("maxx"));
      bbox[MAXY_INDEX] = Double.parseDouble(o.getValue("maxy"));

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
        publisher.removeLayer(CommonProperties.getDeployAppName(), layerName);
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
  
  public static synchronized GeoServerRESTPublisher getPublisher()
  {
    if (publisher == null)
    {
      String geoserverPath = getGeoServerLocalURL();
      publisher = new GeoServerRESTPublisher(geoserverPath, "admin", "geoserver");
    }

    return publisher;
  }
  
  public static synchronized GeoServerRESTStoreManager getManager()
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
    String uri = this.getGeoServerRemoteURL() + "/" + appName;
    
    try
    {
      
      if (getPublisher().createWorkspace(appName, new URI(uri)))
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

}
