package com.terraframe.mojo.query;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.terraframe.mojo.dataaccess.ProgrammingErrorException;
import com.terraframe.mojo.generation.loader.Reloadable;

import dss.vector.solutions.query.AbstractCategory;
import dss.vector.solutions.query.NonRangeCategory;
import dss.vector.solutions.query.RangeCategory;

public class CategorySorter implements Reloadable
{
  
    private static class AbstractComparator implements Comparator<AbstractCategory>, Reloadable
    {

      public int compare(AbstractCategory o1, AbstractCategory o2)
      {
        int ret;

        // Exact Vs Exact
        if (o1 instanceof NonRangeCategory && o2 instanceof NonRangeCategory)
        {
          ret = exactVsExact((NonRangeCategory) o1, (NonRangeCategory) o2);
        }
        // Range Vs Range
        else if (o1 instanceof RangeCategory && o2 instanceof RangeCategory)
        {
          ret = rangeVsRange((RangeCategory) o1, (RangeCategory) o2);
        }
        // Range Vs Exact
        else if (o1 instanceof RangeCategory && o2 instanceof NonRangeCategory)
        {
          ret = rangeVsExact((RangeCategory) o1, (NonRangeCategory) o2);
        }
        // Exact Vs Range
        else if (o1 instanceof NonRangeCategory && o2 instanceof RangeCategory)
        {
          ret = exactVsRange((NonRangeCategory) o1, (RangeCategory) o2);
        }
        else
        {
          String error = "Could not compare the categories" + " [" + o1.getClass().getSimpleName()
              + "] and [" + o2.getClass().getSimpleName() + "]";
          throw new ProgrammingErrorException(error);
        }

        return ret;
      }

    }

    private static boolean isSet(String value)
    {
      return value != null && value.trim().length() > 0;
    }

    private static int exactVsExact(NonRangeCategory e1, NonRangeCategory e2)
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

    private static int rangeVsRange(RangeCategory r1, RangeCategory r2)
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
        throw new ProgrammingErrorException("Could not compare the ranges [" + r1 + "] and [" + r2 + "].");
      }

    }

    private static int rangeVsExact(RangeCategory r, NonRangeCategory e)
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

    private static int exactVsRange(NonRangeCategory e, RangeCategory r)
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

    public static void sort(List<? extends AbstractCategory> categories)
    {
      Collections.sort(categories, new AbstractComparator());
      Collections.reverse(categories);
    }
  
}
