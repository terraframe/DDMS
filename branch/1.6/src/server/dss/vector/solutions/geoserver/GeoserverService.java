package dss.vector.solutions.geoserver;

import java.io.File;
import java.util.List;
import java.util.Map;

import com.runwaysdk.generation.loader.Reloadable;
import com.runwaysdk.query.ValueQuery;

import dss.vector.solutions.query.Layer;
import dss.vector.solutions.query.MapConfiguration;

public interface GeoserverService extends Reloadable
{
  public void refresh();

  public void removeStore();

  public void removeWorkspace();

  public void publishWorkspace();

  public boolean geoserverExists();

  public void publishStore();

  /**
   * Checks if the given style exists in geoserver.
   * 
   * @param styleName
   * @return
   */
  public boolean styleExists(String styleName);

  /**
   * Checks if the cache directory exists. This method does not check what tiles or zoom levels have been cached.
   * 
   * @param cacheName
   * @return
   */
  public boolean cacheExists(String cacheName);

  /**
   * Gets all layers declared in Geoserver for the workspace.
   * 
   * @return
   */
  public List<String> getLayers();

  /**
   * Gets all styles declared in Geoserver for the workspace.
   */
  public List<String> getStyles();

  /**
   * Removes the style defined in Geoserver, including the .sld and .xml file artifacts.
   * 
   * @param styleName
   *          The name of the style to delete.
   */
  public void removeStyle(String styleName);

  public boolean publishStyle(String styleName, String body, boolean force);

  /**
   * Publishes the SQL of the given name with the XML body.
   * 
   * @param styleName
   * @param body
   */
  public boolean publishStyle(String styleName, String body);

  public void pushUpdates(GeoserverBatch batch);

  /**
   * Adds a database view and publishes the layer if necessary.
   * 
   * @param layer
   */
  public boolean publishLayer(String layer, String styleName);

  /**
   * Removes the layer from geoserver.
   * 
   * @param layer
   * @return
   */
  public void removeLayer(String layer);

  public void publishCache(String layer);

  /**
   * Returns a list of all cache directories.
   * 
   * @return
   */
  public File[] getCaches();

  public void removeCache(File cache);

  /**
   * Removes all cache files and directories.
   * 
   * @param cacheName
   */
  public void removeCache(String cacheName);

  /**
   * Checks if the given layer exists in Geoserver.
   * 
   * @param layer
   * @return
   */
  public boolean layerExists(String layer);

  /**
   * Checks if the given layer has a database view.
   * 
   * @param layer
   * @return
   */
  public boolean viewExists(String viewName);

  /**
   * Calculates the bounding box of all the layers.
   * 
   * @param views
   * @return double[] {minx, miny, maxx, maxy}
   */
  public double[] getBBOX(String... views);

  /**
   * Calculates the expanded bounding box of all the database views.
   * 
   * @param views
   *          List of view names
   * @return double[] {minx, miny, maxx, maxy}
   */
  public double[] getExpandedBBOX(List<String> views, double expandVal);

  public boolean workspaceExists();
  
  public Integer getDecimalLength();

  public Integer getDecimalPrecision();
  
  public void reload(Map<Layer, ValueQuery> reloads, MapConfiguration configuration);

  public boolean existGeoserver();

  public String getGeoServerLocalURL();

  public String getGeoServerRemoteURL();
}
