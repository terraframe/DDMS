package dss.vector.solutions.surveillance;

import java.util.Comparator;

import com.runwaysdk.generation.loader.Reloadable;

public class OptionComparator implements Comparator<OptionIF>, Reloadable
{
  private boolean alphabetical;

  public OptionComparator(boolean alphabetical)
  {
    this.alphabetical = alphabetical;
  }

  public int compare(OptionIF o1, OptionIF o2)
  {
    if(alphabetical)
    {
      return o1.getOptionName().compareTo(o2.getOptionName());
    }
    
    return o1.getOptionId().compareTo(o2.getOptionId());
  }
}
