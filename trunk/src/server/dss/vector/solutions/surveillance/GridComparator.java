package dss.vector.solutions.surveillance;

import java.util.Comparator;

import com.terraframe.mojo.generation.loader.Reloadable;

public class GridComparator implements Comparator<ChildOption>, Reloadable
{
  public int compare(ChildOption o1, ChildOption o2)
  {
    return new OptionComparator().compare(o1.getChild(), o2.getChild());
  }
}