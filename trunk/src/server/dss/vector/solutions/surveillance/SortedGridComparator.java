/*******************************************************************************
 * Copyright (C) 2018 IVCC
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 ******************************************************************************/
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
