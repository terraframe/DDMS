package dss.vector.solutions.query;

import it.geosolutions.geoserver.rest.GeoServerRESTPublisher;
import it.geosolutions.geoserver.rest.GeoServerRESTReader;
import it.geosolutions.geoserver.rest.decoder.RESTLayerList;
import it.geosolutions.geoserver.rest.encoder.GSPostGISDatastoreEncoder;

import java.net.MalformedURLException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.concurrent.locks.ReentrantLock;

import com.runwaysdk.business.Business;
import com.runwaysdk.constants.CommonProperties;
import com.runwaysdk.constants.DatabaseProperties;
import com.runwaysdk.generation.loader.Reloadable;
import com.runwaysdk.query.ValueQuery;
import com.terraframe.utf8.UTF8ResourceBundle;

import dss.vector.solutions.geo.GeoServerReloadException;

public class GeoserverFacade implements Reloadable
{
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
  private final ResourceBundle bundle   = UTF8ResourceBundle.getBundle("GeoServer", Locale.getDefault(), Business.class.getClassLoader());

  /**
   * Lock used for creating the workspace
   */
  private final ReentrantLock  lock;

  /**
   * Set of workspaces
   */
  private Set<String>          workspaceSet;

  public GeoserverFacade()
  {
    this.lock = new ReentrantLock();
  }

  /**
   * Returns the url to access GeoServer locally.
   * 
   * @return
   */
  public String getGeoServerLocalURL()
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

  public GeoServerRESTPublisher getGeoServerPublisher()
  {
    String geoserverPath = getGeoServerLocalURL();
    GeoServerRESTPublisher publisher = new GeoServerRESTPublisher(geoserverPath, "admin", "geoserver");
    return publisher;
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

      GeoServerRESTPublisher publisher = this.getGeoServerPublisher();

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
      GeoServerRESTPublisher publisher = this.getGeoServerPublisher();

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

  @SuppressWarnings("deprecation")
  public void createWorkspaceAndDatastore()
  {
    try
    {
      String appName = CommonProperties.getDeployAppName();

      GSPostGISDatastoreEncoder datastore = new GSPostGISDatastoreEncoder();
      datastore.setPort(5444);
      datastore.setPassword("mdssdeploy");
      datastore.setDatabaseType("postgis");
      datastore.setHost("localhost");
      datastore.setValidateConnections(false);
      datastore.setMaxConnections(10);
      datastore.setDatabase(appName.toLowerCase());
      datastore.setNamespace("http://" + appName + ".terraframe.com");
      datastore.setSchema(DatabaseProperties.getNamespace());
      datastore.setLooseBBox(true);
      datastore.setPreparedStatements(false);
      datastore.setExposePrimaryKeys(false);
      datastore.setUser("mdssdeploy");
      datastore.setMinConnections(4);
      datastore.setEnabled(true);
      datastore.setName(QueryConstants.getNamespacedDataStore());

      GeoServerRESTPublisher publisher = this.getGeoServerPublisher();
      publisher.createWorkspace(appName);
      publisher.createPostGISDatastore(appName, datastore);
    }
    catch (Exception e)
    {
      String error = "Could not create Geoserver artifacts";
      GeoServerReloadException ex = new GeoServerReloadException(error);
      throw ex;
    }
  }

}
