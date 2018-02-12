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

import com.runwaysdk.dataaccess.ProgrammingErrorException;

public class RangeCategory extends RangeCategoryBase implements
    com.runwaysdk.generation.loader.Reloadable, RangeCategoryIF
{
  private static final long serialVersionUID = 1241158118735L;

  public RangeCategory()
  {
    super();
  }

  private boolean hasLowerBounds()
  {
    String lower = this.getLowerBoundStr();
    return lower != null && lower.length() > 0;
  }

  private boolean hasUpperBounds()
  {
    String upper = this.getUpperBoundStr();
    return upper != null && upper.length() > 0;
  }

  public String toString()
  {
    String s = "";
    if (hasLowerBounds())
    {
      s += this.getLowerBoundStr();
    }

    s += " < ";

    if (hasUpperBounds())
    {
      s += this.getUpperBoundStr();
    }

    return s;
  }

  /**
   * This method ensures that at least one bounds value is specified.
   */
  @Override
  public void preValidate()
  {
    boolean hasLower = this.hasLowerBounds();
    boolean hasUpper = this.hasUpperBounds();

    String lower = this.getLowerBoundStr();
    String upper = this.getUpperBoundStr();

    if (!hasLower && !hasUpper)
    {
      throw new MissingBoundsException();
    }

    boolean isLowerNum = false;
    boolean isUpperNum = false;

    try
    {
      Double.valueOf(lower);
      isLowerNum = true;
    }
    catch (NumberFormatException e)
    {
      // lower bound is a string
    }

    try
    {
      Double.valueOf(upper);
      isUpperNum = true;
    }
    catch (NumberFormatException e)
    {
      // lower bound is a string
    }

    if (hasLower && hasUpper && isLowerNum != isUpperNum)
    {
      String error = "The range bounds lower [" + lower + "] and upper [" + upper
          + "] are not compatible types";
      throw new IncompatibleBoundsException(error);
    }

    if (hasLower && hasUpper && isLowerNum && isUpperNum
        && Double.valueOf(lower) > Double.valueOf(upper))
    {
      throw new LowerGreaterThanUpperBoundsException();
    }
    else if (hasLower && hasUpper && !isLowerNum && !isUpperNum
        && lower.compareTo(upper) > 0)
    {
      throw new LowerGreaterThanUpperBoundsException();
    }
  }

  private void compareRangeToUpper(String l1Str, String u1Str, String u2Str)
  {
    try
    {
      double l1 = Double.parseDouble(l1Str);
      double u1 = Double.parseDouble(u1Str);
      double u2 = Double.parseDouble(u2Str);

      if (l1 < u2 || u1 <= u2)
      {
        this.throwsOverlapException(l1Str, u1Str, null, u2Str);
      }

    }
    catch (NumberFormatException e)
    {
      if (l1Str.compareTo(u2Str) <= 0 || u1Str.compareTo(u2Str) <= 0)
      {
        this.throwsOverlapException(l1Str, u1Str, null, u2Str);
      }
    }

  }

  private void compareRangeToLower(String l1Str, String u1Str, String l2Str)
  {
    try
    {
      double l1 = Double.parseDouble(l1Str);
      double u1 = Double.parseDouble(u1Str);
      double l2 = Double.parseDouble(l2Str);

      if (l1 >= l2 || u1 > l2)
      {
        this.throwsOverlapException(l1Str, u1Str, l2Str, null);
      }

    }
    catch (NumberFormatException e)
    {
      if (l1Str.compareTo(l2Str) >= 0 || u1Str.compareTo(l2Str) > 0)
      {
        this.throwsOverlapException(l1Str, u1Str, l2Str, null);
      }
    }
  }

  private void compareMixedBounds(String lower, String upper)
  {
    try
    {
      if (new Double(lower) < new Double(upper))
      {
        this.throwsOverlapException(lower, upper);
      }
    }
    catch (NumberFormatException e)
    {
      if (lower.compareTo(upper) > 0)
      {
        this.throwsOverlapException(lower, upper);
      }
    }
  }

  @Override
  protected void checkBounds(AbstractCategory category)
  {
    String lower = this.getLowerBoundStr();
    String upper = this.getUpperBoundStr();

    if (category instanceof RangeCategory)
    {
      RangeCategory cat = (RangeCategory) category;

      String lower2 = cat.getLowerBoundStr();
      String upper2 = cat.getUpperBoundStr();

      // Full range compare
      if (this.hasLowerBounds() && this.hasUpperBounds() && cat.hasLowerBounds() && cat.hasUpperBounds())
      {
        try
        {
          double l1 = Double.valueOf(lower);
          double u1 = Double.valueOf(upper);
          double l2 = Double.valueOf(lower2);
          double u2 = Double.valueOf(upper2);

          if ( ( l2 >= l1 && l2 < u1 ) || u2 > l1 && u2 < u1)
          {
            this.throwsOverlapException(lower, upper, lower2, upper2);
          }
        }
        catch (NumberFormatException e)
        {
          if ( ( lower2.compareTo(lower) >= 0 && lower2.compareTo(upper) < 0 )
              || ( upper2.compareTo(lower) > 0 && upper2.compareTo(upper) < 0 ))
          {
            this.throwsOverlapException(lower, upper, lower2, upper2);
          }
        }
      }
      // There cannot be more than one open-ended lower bound
      else if (!this.hasLowerBounds() && !cat.hasLowerBounds())
      {
        this.throwsOverlapException(upper, upper2);
      }
      // There cannot be more than on open-ended upper bound
      else if (!this.hasUpperBounds() && !cat.hasUpperBounds())
      {
        this.throwsOverlapException(lower, lower2);
      }
      // full range compared to lower bound
      else if (this.hasLowerBounds() && this.hasUpperBounds() && cat.hasLowerBounds())
      {
        this.compareRangeToLower(lower, upper, lower2);
      }
      // full range compared to upper bound
      else if (this.hasLowerBounds() && this.hasUpperBounds() && cat.hasUpperBounds())
      {
        this.compareRangeToUpper(lower, upper, upper2);
      }
      // lower bound compared to full range
      else if (this.hasLowerBounds() && cat.hasLowerBounds() && cat.hasUpperBounds())
      {
        this.compareRangeToLower(lower2, upper2, lower);
      }
      // upper bound compared to full range
      else if (this.hasUpperBounds() && cat.hasLowerBounds() && cat.hasUpperBounds())
      {
        this.compareRangeToUpper(lower2, upper2, upper);
      }
      // lower compared to upper
      else if (this.hasLowerBounds() && cat.hasUpperBounds())
      {
        this.compareMixedBounds(lower, upper2);
      }
      // upper compared to lower
      else if (this.hasUpperBounds() && cat.hasLowerBounds())
      {
        this.compareMixedBounds(lower2, upper);
      }
      else
      {
        String error = "Invalid comparison between ranges [" + this + "] and [" + cat + "]";
        throw new ProgrammingErrorException(error);
      }
    }
    else
    {
      NonRangeCategory cat = (NonRangeCategory) category;
      String exact = cat.getExactValueStr();
      
      if(this.hasUpperBounds() && this.hasUpperBounds())
      {
        try
        {
          double exactD = Double.parseDouble(exact);
          
          if(exactD >= Double.parseDouble(lower) && exactD < Double.parseDouble(upper))
          {
            this.throwsOverlapException(lower, upper, exact);
          }
        }
        catch(NumberFormatException e)
        {
          if(exact.compareTo(lower) >= 0 && exact.compareTo(upper) < 0)
          {
            this.throwsOverlapException(lower, upper, exact); 
          }
        }
      }
      else if(this.hasLowerBounds())
      {
        try
        {
          double exactD = Double.parseDouble(exact);
          if(exactD >= Double.parseDouble(lower))
          {
            this.throwsOverlapException(lower, null, exact);
          }
        }
        catch(NumberFormatException e)
        {
          this.throwsOverlapException(lower, null, exact);
        }
      }
      else if(this.hasUpperBounds())
      {
        try
        {
          double exactD = Double.parseDouble(exact);
          if(exactD < Double.parseDouble(upper))
          {
            this.throwsOverlapException(null, upper, exact);
          }
        }
        catch(NumberFormatException e)
        {
          if(exact.compareTo(upper) < 0)
          {
            this.throwsOverlapException(null, upper, exact);
          }
        }
      }
    }
  }
}
