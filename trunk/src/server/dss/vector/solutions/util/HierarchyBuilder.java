package dss.vector.solutions.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import dss.vector.solutions.geo.GeoHierarchy;
import dss.vector.solutions.geo.generated.Earth;

public class HierarchyBuilder
{
  private List<GeoEntry> entryList;

  public HierarchyBuilder()
  {
    entryList = new LinkedList<GeoEntry>();
    entryList.add(new GeoEntry(GeoHierarchy.getGeoHierarchyFromType(Earth.CLASS), 0));
  }

  public void add(GeoHierarchy endPoint)
  {
    recurse(endPoint, 0);
  }

  private int recurse(GeoHierarchy child, int depth)
  {
    // Termination condition
    if (child.getGeoEntityClass().definesType().equals(Earth.CLASS))
    {
      // Earth is always depth 0.  Thus any child of Earth must be depth 1.
      return 1;
    }

    for (GeoHierarchy parent : child.getAllAllowedInGeoEntity().getAll())
    {
      depth = recurse(parent, depth);
      put(child, depth);
    }

    return depth + 1;
  }

  private void put(GeoHierarchy child, int depth)
  {
    GeoEntry entry = new GeoEntry(child, depth);
    int index = entryList.indexOf(entry);

    // If this type is already in the hierarchy
    if (index>=0)
    {
      GeoEntry existing = entryList.get(index);
      if (entry.isDeeper(existing))
      {
        entryList.set(index, entry);
      }
    }
    else
    {
      // This Geo Type is not yet listed.  Just add it.
      entryList.add(entry);
    }
  }

  public List<GeoHierarchy> getHierarchy()
  {
    Collections.sort(entryList);
    ArrayList<GeoHierarchy> newList = new ArrayList<GeoHierarchy>(entryList.size());

    for (GeoEntry entry : entryList)
    {
      newList.add(entry.hierarchy);
    }

    return newList;
  }

  private class GeoEntry implements Comparable<GeoEntry>
  {
    private GeoHierarchy hierarchy;
    private Integer depth;

    private GeoEntry(GeoHierarchy h, Integer depth)
    {
      this.hierarchy = h;
      this.depth = depth;
    }

    public boolean isDeeper(GeoEntry other)
    {
      return (this.depth > other.depth);
    }

    @Override
    public boolean equals(Object obj)
    {
      if (obj instanceof GeoEntry)
      {
        String thisType = hierarchy.getQualifiedType();
        String otherType = ((GeoEntry)obj).hierarchy.getQualifiedType();
        return thisType.equals(otherType);
      }
      return false;
    }

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
