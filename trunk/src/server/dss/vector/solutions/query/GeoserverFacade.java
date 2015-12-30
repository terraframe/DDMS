package dss.vector.solutions.query;

import it.geosolutions.geoserver.rest.GeoServerRESTPublisher;
import it.geosolutions.geoserver.rest.GeoServerRESTReader;
import it.geosolutions.geoserver.rest.decoder.RESTLayerList;
import it.geosolutions.geoserver.rest.encoder.datastore.GSPostGISDatastoreEncoder;
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
import com.runwaysdk.generation.loader.Reloadable;
import com.runwaysdk.query.ValueQuery;
import com.runwaysdk.system.gis.ConfigurationException;
import com.terraframe.utf8.UTF8ResourceBundle;

import dss.vector.solutions.geo.GeoServerReloadException;

public class GeoserverFacade implements Reloadable
{
  private Logger logger = LoggerFactory.getLogger(GeoserverFacade.class);
  
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

  @SuppressWarnings("deprecation")
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

  public void removeLayers(String... layerNames)
  {
    try
    {
      GeoServerRESTPublisher publisher = this.getPublisher();

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
    try
    {
      String appName = CommonProperties.getDeployAppName();
      if (getPublisher().createWorkspace(appName, new URI(getGeoServerLocalURL())))
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

    GSPostGISDatastoreEncoder datastore = new GSPostGISDatastoreEncoder(QueryConstants.getNamespacedDataStore());
    datastore.setName(QueryConstants.getNamespacedDataStore());
    datastore.setDatabase(DatabaseProperties.getDatabaseName());
    datastore.setPort(DatabaseProperties.getPort());
    datastore.setUser(DatabaseProperties.getUser());
    datastore.setPassword(DatabaseProperties.getPassword());
    datastore.setDatabaseType("postgis");
    datastore.setHost("localhost");
    datastore.setValidateConnections(false);
    datastore.setMaxConnections(10);
    datastore.setNamespace(appName);
    datastore.setSchema(DatabaseProperties.getNamespace());
    datastore.setLooseBBox(true);
    datastore.setPreparedStatements(false);
    datastore.setExposePrimaryKeys(false);
    datastore.setMinConnections(4);
    datastore.setEnabled(true);

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
