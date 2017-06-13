package dss.vector.solutions.geoserver;

import java.io.File;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.runwaysdk.query.ValueQuery;

import dss.vector.solutions.query.Layer;
import dss.vector.solutions.query.MapConfiguration;

public class NullGeoserverService implements GeoserverService
{

  @Override
  public void refresh()
  {

  }

  @Override
  public void removeStore()
  {

  }
  
  @Override
  public void removeStore(String workspaceName, String storeName)
  {

  }

  @Override
  public void removeWorkspace()
  {

  }
  
  @Override
  public void removeWorkspace(String workspaceName)
  {

  }

  @Override
  public void publishWorkspace()
  {

  }
  
  @Override
  public void publishWorkspace(String workspaceName)
  {

  }

  @Override
  public boolean geoserverExists()
  {

    return true;
  }

  @Override
  public void publishStore()
  {

  }
  
  @Override
  public void publishStore(String storeName, String dbName, String schemaName)
  {

  }

  @Override
  public boolean styleExists(String styleName)
  {

    return true;
  }

  @Override
  public boolean cacheExists(String cacheName)
  {

    return true;
  }

  @Override
  public List<String> getLayers()
  {

    return new LinkedList<String>();
  }

  @Override
  public List<String> getStyles()
  {

    return new LinkedList<String>();
  }

  @Override
  public void removeStyle(String styleName)
  {

  }
  
  @Override
  public boolean publishStyle(String styleName, File sldFile, boolean force)
  {

    return true;
  }

  @Override
  public boolean publishStyle(String styleName, String body, boolean force)
  {

    return true;
  }

  @Override
  public boolean publishStyle(String styleName, String body)
  {

    return true;
  }

  @Override
  public void pushUpdates(GeoserverBatch batch)
  {

  }
  
  @Override
  public boolean publishLayerGroup(String[] layers, String workspace, String groupName)
  {

    return true;
  }

  @Override
  public boolean publishLayer(String layer, String styleName)
  {

    return true;
  }
  
  @Override
  public boolean publishOSMLayer(String layer, String styleName)
  {

    return true;
  }

  @Override
  public void removeLayer(String layer)
  {

  }

  @Override
  public void publishCache(String layer)
  {

  }

  @Override
  public File[] getCaches()
  {

    return new File[0];
  }

  @Override
  public void removeCache(File cache)
  {

  }

  @Override
  public void removeCache(String cacheName, String workspace)
  {

  }
  
  @Override
  public void rePublishCache(String layer, String workspace) 
  {
	  
  }
  
  @Override
  public boolean layerGroupExists(String layerGroup)
  {

    return false;
  }
  
  @Override
  public boolean layerGroupExists(String layerGroup, String workspace)
  {

    return false;
  }

  @Override
  public boolean layerExists(String layer)
  {

    return false;
  }
  
  @Override
  public boolean layerExists(String layer, String workspace)
  {

    return false;
  }

  @Override
  public boolean viewExists(String viewName)
  {

    return false;
  }

  @Override
  public boolean workspaceExists()
  {
    return false;
  }
  
  @Override
  public boolean workspaceExists(String workspaceName)
  {
    return false;
  }

  @Override
  public double[] getBBOX(String... views)
  {
    return new double[] { 0, 0, 0, 0 };
  }

  @Override
  public double[] getExpandedBBOX(List<String> views, double expandVal)
  {
    return new double[] { 0, 0, 0, 0 };
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
    return true;
  }

  @Override
  public void reload(Map<Layer, ValueQuery> reloads, MapConfiguration configuration)
  {
    // Do nothing
  }

  @Override
  public String getGeoServerLocalURL()
  {
    return "http://localhost/geoserver";
  }

  @Override
  public String getGeoServerRemoteURL()
  {
    return "http://remotehost/geoserver";
  }
  
  @Override
  public String getAppName()
  {
    return "geoserver";
  }
}
