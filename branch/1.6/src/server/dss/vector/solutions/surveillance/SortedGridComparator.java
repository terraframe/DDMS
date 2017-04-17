package dss.vector.solutions.surveillance;

import java.util.Comparator;
import java.util.HashMap;

import com.runwaysdk.generation.loader.Reloadable;

public class SortedGridComparator implements Comparator<ChildOption>, Reloadable
{
  private HashMap<String, Integer> optionIds;

  public SortedGridComparator(OptionIF[] options)
  {
    this.optionIds = new HashMap<String, Integer>();

    for(int i = 0; i < options.length; i++)
    {
      this.optionIds.put(options[i].getOptionId(), i);
    }
  }

  public int compare(ChildOption o1, ChildOption o2)
  {
    String optionId1 = o1.getChild().getOptionId();
    String optionId2 = o2.getChild().getOptionId();

    Integer index1 = this.optionIds.get(optionId1);
    Integer index2 = this.optionIds.get(optionId2);

    if (index1 != null && index2 != null)
    {
      int compareTo = index1.compareTo(index2);

      if (compareTo > 0)
      {
        return 1;
      }
      else if (compareTo < 0)
      {
        return -1;
      }
    }
    else
    {
      int compareTo = optionId1.compareTo(optionId2);

      if (compareTo > 0)
      {
        return 1;
      }
      else if (compareTo < 0)
      {
        return -1;
      }
    }

    return 0;
  }
}