package dss.vector.solutions.geoserver;

import java.util.List;
import java.util.Map;

import com.runwaysdk.generation.loader.Reloadable;
import com.runwaysdk.query.ValueQuery;

import dss.vector.solutions.query.Layer;
import dss.vector.solutions.query.MapConfiguration;

public class GeoserverFacade implements Reloadable
{
  public static final String      GEOM_COLUMN = "geom";

  public static int               MINX_INDEX  = 0;

  public static int               MINY_INDEX  = 1;

  public static int               MAXX_INDEX  = 2;

  public static int               MAXY_INDEX  = 3;

  private static GeoserverService service     = new GeoserverRestService();

  public synchronized static GeoserverService getService()
  {
    return service;
  }

  public synchronized static void setService(GeoserverService _service)
  {
    service = _service;
  }

  public static void refresh()
  {
    getService().refresh();
  }

  public static void removeStore()
  {
    getService().removeStore();
  }

  public static boolean workspaceExists()
  {
    return getService().workspaceExists();
  }

  public static void removeWorkspace()
  {
    getService().removeWorkspace();
  }

  public static void publishWorkspace()
  {
    getService().publishWorkspace();
  }

  /**
   * Checks if Geoserver is available.
   * 
   * @return
   */
  public static boolean geoserverExists()
  {
    return getService().geoserverExists();
  }

  public static void publishStore()
  {
    getService().publishStore();
  }

  /**
   * Checks if the given style exists in geoserver.
   * 
   * @param styleName
   * @return
   */
  public static boolean styleExists(String styleName)
  {
    return getService().styleExists(styleName);
  }

  /**
   * Checks if the cache directory exists. This method does not check what tiles or zoom levels have been cached.
   * 
   * @param cacheName
   * @return
   */
  public static boolean cacheExists(String cacheName)
  {
    return getService().cacheExists(cacheName);
  }

  /**
   * Gets all layers declared in Geoserver for the workspace.
   * 
   * @return
   */
  public static List<String> getLayers()
  {
    return getService().getLayers();
  }

  /**
   * Gets all styles declared in Geoserver for the workspace.
   */
  public static List<String> getStyles()
  {
    return getService().getStyles();
  }

  /**
   * Removes the style defined in Geoserver, including the .sld and .xml file artifacts.
   * 
   * @param styleName
   *          The name of the style to delete.
   */
  public static void removeStyle(String styleName)
  {
    getService().removeStyle(styleName);
  }

  public static boolean publishStyle(String styleName, String body, boolean force)
  {
    return getService().publishStyle(styleName, body, force);
  }

  /**
   * Publishes the SQL of the given name with the XML body.
   * 
   * @param styleName
   * @param body
   */
  public static boolean publishStyle(String styleName, String body)
  {
    return getService().publishStyle(styleName, body);
  }

  public static void pushUpdates(GeoserverBatch batch)
  {
    getService().pushUpdates(batch);
  }

  /**
   * Adds a database view and publishes the layer if necessary.
   * 
   * @param layer
   */
  public static boolean publishLayer(String layer, String styleName)
  {
    return getService().publishLayer(layer, styleName);
  }

  /**
   * Publishes the layer if necessary.
   * 
   * @param layer
   */
  public static boolean publishLayer(GeoserverLayerIF layer)
  {
    return getService().publishLayer(layer.getLayerName(), layer.getStyleName());
  }

  /**
   * Removes the layer from geoserver.
   * 
   * @param layer
   * @return
   */
  public static void removeLayer(String layer)
  {
    getService().removeLayer(layer);
  }

  /**
   * Checks if the given layer exists in Geoserver.
   * 
   * @param layer
   * @return
   */
  public static boolean layerExists(String layer)
  {
    return getService().layerExists(layer);
  }

  /**
   * Checks if the given layer has a database view.
   * 
   * @param layer
   * @return
   */
  public static boolean viewExists(String viewName)
  {
    return getService().viewExists(viewName);
  }

  /**
   * Calculates the bounding box of all the layers.
   * 
   * @param views
   * @return double[] {minx, miny, maxx, maxy}
   */
  public static double[] getBBOX(String... views)
  {
    return getService().getBBOX(views);
  }

  /**
   * Calculates the bounding box of all the layers.
   * 
   * @param views
   * @return double[] {minx, miny, maxx, maxy}
   */
  public static double[] getExpandedBBOX(List<String> views, double expandVal)
  {
    return getService().getExpandedBBOX(views, expandVal);
  }

  public static boolean existGeoserver()
  {
    return getService().existGeoserver();
  }

  public static void reload(Map<Layer, ValueQuery> reloads, MapConfiguration configuration)
  {
    getService().reload(reloads, configuration);
  }

  public static String getLocalURL()
  {
    return getService().getGeoServerLocalURL();
  }

  public static String getRemoteURL()
  {
    return getService().getGeoServerRemoteURL();
  }

  public static Integer getDecimalLength()
  {
    return getService().getDecimalLength();
  }

  public static Integer getDecimalPrecision()
  {
    return getService().getDecimalPrecision();
  }
}
