package dss.vector.solutions.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import com.runwaysdk.generation.loader.Reloadable;
import com.runwaysdk.system.metadata.MdBusiness;

import dss.vector.solutions.geo.GeoHierarchy;
import dss.vector.solutions.geo.generated.Earth;

/**
 * HierarchyBuilder accepts GeoHierarchy end points, and recursively builds and
 * merges the located in tree above them. This has the effect of flattening the
 * located in graph to a linear structure, where each entry is deeper in the
 * graph than the entry to the left of it.
 * 
 * @author Eric
 */
public class HierarchyBuilder implements Reloadable
{
  /**
   * The list of all GeoEntries. Not sorted by default, but sorts by depth if
   * Collections.sort() is called
   */
  private List<GeoEntry> entryList;

  /**
   * Constructor for the builder
   */
  public HierarchyBuilder()
  {
    entryList = new LinkedList<GeoEntry>();
  }

  /**
   * Takes the given endpoint and recursively walks up the located in tree. Also
   * recursively adds any isa children.
   * 
   * @param endPoint
   */
  public void add(GeoHierarchy endPoint)
  {
    recurseIsA(endPoint);
  }

  /**
   * Gets all children of the given endpoint, and adds them to the entry list
   * 
   * @param endPoint
   */
  private void recurseIsA(GeoHierarchy endPoint)
  {
    // Add this GeoHierarchy
    recurseLocatedIn(endPoint, 0);

    // Add this Hierarchy's children
    for (MdBusiness child : endPoint.getGeoEntityClass().getAllSubClass())
    {
      recurseIsA(GeoHierarchy.getGeoHierarchyFromType(child));
    }
  }

  /**
   * The primary method for building, traverses up the tree until Earth is
   * found. At that point a depth counter is returned, and as the recusrive
   * stack unrolls, the counter is incremented and stored, resulting in a
   * pairing of Entity - Depth.
   * 
   * @param child
   * @param depth
   * @return
   */
  private int recurseLocatedIn(GeoHierarchy child, int depth)
  {
    // Termination condition
    if (child.getGeoEntityClass().definesType().equals(Earth.CLASS))
    {
      // Earth is always depth 0. Thus any child of Earth must be depth 1.
      return 1;
    }

    // Get all of the geo types the child is allowed in
    for (GeoHierarchy parent : child.getAllAllowedInGeoEntity().getAll())
    {
      // Recurse on the allowed in parents
      depth = recurseLocatedIn(parent, depth);
      // Store the calculated depth for this geo type
      put(child, depth);
    }

    return depth + 1;
  }

  /**
   * Stores the GeoHierarchy in the entry list, but only preserves the deepest
   * occurance of any GeoHierarchy
   * 
   * @param child
   * @param depth
   */
  private void put(GeoHierarchy child, int depth)
  {
    GeoEntry entry = new GeoEntry(child, depth);
    int index = entryList.indexOf(entry);

    // If this type is already in the hierarchy
    if (index >= 0)
    {
      GeoEntry existing = entryList.get(index);
      if (entry.isDeeper(existing))
      {
        entryList.set(index, entry);
      }
    }
    else
    {
      // This Geo Type is not yet listed. Just add it.
      entryList.add(entry);
    }
  }

  /**
   * Sorts the entry list by depth and returns it
   * 
   * @return
   */
  public List<GeoHierarchy> getHierarchy()
  {
    // Sort by depth
    Collections.sort(entryList);

    // Copies the <GeoEntry> list into a <GeoHierarchy> list
    ArrayList<GeoHierarchy> newList = new ArrayList<GeoHierarchy>(entryList.size());
    for (GeoEntry entry : entryList)
    {
      newList.add(entry.hierarchy);
    }

    return newList;
  }

  /**
   * A small helper class to keep track of the depth of GeoHierarchies
   *
   * @author Eric
   */
  private class GeoEntry implements Comparable<GeoEntry>, Reloadable
  {
    private GeoHierarchy hierarchy;

    private Integer      depth;

    private GeoEntry(GeoHierarchy h, Integer depth)
    {
      this.hierarchy = h;
      this.depth = depth;
    }

    public boolean isDeeper(GeoEntry other)
    {
      return ( this.depth > other.depth );
    }

    @Override
    public boolean equals(Object obj)
    {
      if (obj instanceof GeoEntry)
      {
        String thisType = hierarchy.getQualifiedType();
        String otherType = ( (GeoEntry) obj ).hierarchy.getQualifiedType();
        return thisType.equals(otherType);
      }
      return false;
    }

    /**
     * Compares on depth
     * 
     * @see java.lang.Comparable#compareTo(java.lang.Object)
     */
    public int compareTo(GeoEntry o)
    {
      return this.depth.compareTo(o.depth);
    }

    @Override
    public String toString()
    {
      return depth + " " + hierarchy;
    }
  }
}
