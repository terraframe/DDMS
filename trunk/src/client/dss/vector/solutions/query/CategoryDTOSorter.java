package dss.vector.solutions.query;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.terraframe.mojo.dataaccess.ProgrammingErrorException;
import com.terraframe.mojo.generation.loader.Reloadable;

public class CategoryDTOSorter implements Reloadable
{
  private static class AbstractComparator implements Comparator<AbstractCategoryDTO>, Reloadable
  {

    public int compare(AbstractCategoryDTO o1, AbstractCategoryDTO o2)
    {
      int ret;

      // Exact Vs Exact
      if (o1 instanceof NonRangeCategoryDTO && o2 instanceof NonRangeCategoryDTO)
      {
        ret = exactVsExact((NonRangeCategoryDTO) o1, (NonRangeCategoryDTO) o2);
      }
      // Range Vs Range
      else if (o1 instanceof RangeCategoryDTO && o2 instanceof RangeCategoryDTO)
      {
        ret = rangeVsRange((RangeCategoryDTO) o1, (RangeCategoryDTO) o2);
      }
      // Range Vs Exact
      else if (o1 instanceof RangeCategoryDTO && o2 instanceof NonRangeCategoryDTO)
      {
        ret = rangeVsExact((RangeCategoryDTO) o1, (NonRangeCategoryDTO) o2);
      }
      // Exact Vs Range
      else if (o1 instanceof NonRangeCategoryDTO && o2 instanceof RangeCategoryDTO)
      {
        ret = exactVsRange((NonRangeCategoryDTO) o1, (RangeCategoryDTO) o2);
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

  private static int exactVsExact(NonRangeCategoryDTO e1, NonRangeCategoryDTO e2)
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

  private static int rangeVsRange(RangeCategoryDTO r1, RangeCategoryDTO r2)
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

  private static int rangeVsExact(RangeCategoryDTO r, NonRangeCategoryDTO e)
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

  private static int exactVsRange(NonRangeCategoryDTO e, RangeCategoryDTO r)
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

  public static void sort(List<? extends AbstractCategoryDTO> categories)
  {
    Collections.sort(categories, new AbstractComparator());
    Collections.reverse(categories);
  }
}
