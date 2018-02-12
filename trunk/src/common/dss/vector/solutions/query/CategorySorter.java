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
package dss.vector.solutions.query;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.runwaysdk.CommonExceptionProcessor;
import com.runwaysdk.constants.ExceptionConstants;
import com.runwaysdk.generation.loader.Reloadable;

public class CategorySorter implements Reloadable
{

    private static class AbstractComparator implements Comparator<AbstractCategoryIF>, Reloadable
    {

      public int compare(AbstractCategoryIF o1, AbstractCategoryIF o2)
      {
        int ret;

        // Exact Vs Exact
        if (o1 instanceof NonRangeCategoryIF && o2 instanceof NonRangeCategoryIF)
        {
          ret = exactVsExact((NonRangeCategoryIF) o1, (NonRangeCategoryIF) o2);
        }
        // Range Vs Range
        else if (o1 instanceof RangeCategoryIF && o2 instanceof RangeCategoryIF)
        {
          ret = rangeVsRange((RangeCategoryIF) o1, (RangeCategoryIF) o2);
        }
        // Range Vs Exact
        else if (o1 instanceof RangeCategoryIF && o2 instanceof NonRangeCategoryIF)
        {
          ret = rangeVsExact((RangeCategoryIF) o1, (NonRangeCategoryIF) o2);
        }
        // Exact Vs Range
        else if (o1 instanceof NonRangeCategoryIF && o2 instanceof RangeCategoryIF)
        {
          ret = exactVsRange((NonRangeCategoryIF) o1, (RangeCategoryIF) o2);
        }
        else
        {
          String error = "Could not compare the categories" + " [" + o1.getClass().getSimpleName()
              + "] and [" + o2.getClass().getSimpleName() + "]";

          CommonExceptionProcessor.processException(
        	        ExceptionConstants.ConfigurationException.getExceptionClass(), error);
          return 0;
        }

        return ret;
      }

    }

    private static boolean isSet(String value)
    {
      return value != null && value.trim().length() > 0;
    }

    private static int exactVsExact(NonRangeCategoryIF e1, NonRangeCategoryIF e2)
    {
      try
      {
        return new Double(e1.getExactValueStr()).compareTo(new Double(e2.getExactValueStr()));
      }
      catch (NumberFormatException e)
      {
        return e1.getExactValueStr().compareTo(e2.getExactValueStr());
      }
    }

    private static int rangeVsRange(RangeCategoryIF r1, RangeCategoryIF r2)
    {
      String lower1 = r1.getLowerBoundStr();
      String upper1 = r1.getUpperBoundStr();

      String lower2 = r2.getLowerBoundStr();
      String upper2 = r2.getUpperBoundStr();

      // Standard range comparison (both lower and upper exist for the ranges)
      if (isSet(lower1) && isSet(upper2) && isSet(lower2) && isSet(upper2))
      {
        try
        {
          return new Double(lower1).compareTo(new Double(lower2));
        }
        catch (NumberFormatException e)
        {
          return lower1.compareTo(lower2);
        }
      }
      // Lower-based comparison
      else if (!isSet(upper1) || !isSet(upper2))
      {
        try
        {
          return new Double(lower1).compareTo(new Double(lower2));
        }
        catch (NumberFormatException e)
        {
          return lower1.compareTo(lower2);
        }
      }
      // Upper-based comparison
      else if (!isSet(lower1) || !isSet(lower2))
      {
        try
        {
          return new Double(upper1).compareTo(new Double(upper2));
        }
        catch (NumberFormatException e)
        {
          return upper1.compareTo(upper2);
        }
      }
      else
      {
        String error = "Could not compare the ranges [" + r1 + "] and [" + r2 + "].";

        CommonExceptionProcessor.processException(
        		ExceptionConstants.ConfigurationException.getExceptionClass(), error);

        return 0;
      }

    }

    private static int rangeVsExact(RangeCategoryIF r, NonRangeCategoryIF e)
    {
      String exact = e.getExactValueStr();
      String lower = r.getLowerBoundStr();
      String upper = r.getUpperBoundStr();

      if (isSet(lower))
      {
        try
        {
          return new Double(lower).compareTo(new Double(exact));
        }
        catch (NumberFormatException ex)
        {
          return lower.compareTo(exact);
        }
      }
      else
      {
        try
        {
          return new Double(upper).compareTo(new Double(exact));
        }
        catch (NumberFormatException ex)
        {
          return upper.compareTo(exact);
        }
      }
    }

    private static int exactVsRange(NonRangeCategoryIF e, RangeCategoryIF r)
    {
      String exact = e.getExactValueStr();
      String lower = r.getLowerBoundStr();
      String upper = r.getUpperBoundStr();

      if (isSet(lower))
      {
        try
        {
          return new Double(exact).compareTo(new Double(lower));
        }
        catch (NumberFormatException ex)
        {
          return exact.compareTo(lower);
        }
      }
      else
      {
        try
        {
          return new Double(exact).compareTo(new Double(upper));
        }
        catch (NumberFormatException ex)
        {
          return exact.compareTo(upper);
        }
      }
    }

    public static void sort(List<? extends AbstractCategoryIF> categories)
    {
      Collections.sort(categories, new AbstractComparator());
      Collections.reverse(categories);
    }

}
