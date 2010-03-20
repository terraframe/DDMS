package dss.vector.solutions.surveillance;

import java.util.Comparator;

import com.runwaysdk.generation.loader.Reloadable;

public class OptionComparator implements Comparator<OptionIF>, Reloadable
{
  public int compare(OptionIF o1, OptionIF o2)
  {
    return o1.getOptionName().compareTo(o2.getOptionName());
  }
}
