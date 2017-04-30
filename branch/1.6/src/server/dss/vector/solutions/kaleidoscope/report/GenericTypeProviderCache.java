package dss.vector.solutions.kaleidoscope.report;

import java.util.HashMap;
import java.util.List;

import com.runwaysdk.generation.loader.Reloadable;
import com.runwaysdk.system.metadata.MdClass;

import dss.vector.solutions.kaleidoscope.dashboard.MetadataWrapper;
import dss.vector.solutions.kaleidoscope.geo.GeoNode;

public class GenericTypeProviderCache implements Reloadable
{
  private static GenericTypeProviderCache instance;

  /**
   * List of MdClass objects which have geo nodes
   */
  private List<MdClass>                   classes;

  /**
   * Map of the GeoNode objectsz for each type
   */
  private HashMap<String, List<GeoNode>>  nodes;

  public GenericTypeProviderCache()
  {
    this.classes = MetadataWrapper.getMdClassesWithGeoNodes();
    this.nodes = new HashMap<String, List<GeoNode>>();
  }

  public List<MdClass> getMdClassesWithGeoNodes()
  {
    return classes;
  }

  public List<GeoNode> getGeoNodes(String type)
  {
    if (!this.nodes.containsKey(type))
    {
      this.nodes.put(type, MetadataWrapper.getGeoNodes(type));
    }

    return this.nodes.get(type);
  }

  public static synchronized GenericTypeProviderCache getInstance()
  {
    if (instance == null)
    {
      instance = new GenericTypeProviderCache();
    }

    return instance;
  }

}
