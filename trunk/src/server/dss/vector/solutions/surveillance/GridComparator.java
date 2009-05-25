package dss.vector.solutions.surveillance;

import java.util.Comparator;

import com.terraframe.mojo.generation.loader.Reloadable;

public class GridComparator implements Comparator<CaseGrid>, Reloadable
{
  public int compare(CaseGrid o1, CaseGrid o2)
  {
    return o1.getChild().getOptionName().compareTo(o2.getChild().getOptionName());
  }
}