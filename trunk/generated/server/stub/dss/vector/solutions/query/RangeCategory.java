package dss.vector.solutions.query;

public class RangeCategory extends RangeCategoryBase implements
    com.terraframe.mojo.generation.loader.Reloadable
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
    
    if(!hasLower && !hasUpper)
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
    
    /*
    if(hasLower && hasUpper
        && isLowerNum && isUpperNum
        && Double.valueOf(lower) > Double.valueOf(upper))
    {
      throw new LowerGreaterThanUpperBoundsException();
    }
    else if(hasLower && hasUpper
      && lower.compareTo(upper) > 0)
    {
      throw new LowerGreaterThanUpperBoundsException();
    }
    */
  }

  protected void checkAgainstRangeNumbers(Double lowerD, Double upperD, Double lowerD2, Double upperD2)
  {
    if((lowerD >= lowerD2 && lowerD < upperD2)
        || (upperD >= lowerD2 && lowerD < upperD2))
    {
      this.throwsOverlapException(lowerD.toString(), upperD.toString(), lowerD2.toString(), upperD2.toString());
    }
  }

  protected void checkAgainstRangeStrings(String lower, String upper, String lower2, String upper2)
  {
    if (lower.compareTo(lower2) >= 0 && lower.compareTo(upper2) <= 0)
    {
      this.throwsOverlapException(lower, upper, lower2, upper2);
    }
    else if (upper.compareTo(lower2) >= 0 && upper.compareTo(upper2) <= 0)
    {
      this.throwsOverlapException(lower, upper, lower2, upper2);
    }

  }

  @Override
  protected void checkBounds(AbstractCategory category)
  {
    String lower = this.getLowerBoundStr();
    String upper = this.getUpperBoundStr();

    Double lowerD = null;
    Double upperD = null;
    try
    {
      lowerD = Double.valueOf(lower);
      upperD = Double.valueOf(upper);
    }
    catch (NumberFormatException e)
    {
      // The Range is not numeric
    }

    if (category instanceof NonRangeCategory)
    {
      NonRangeCategory nonRange = (NonRangeCategory) category;
      nonRange.checkBounds(this);
    }
    else
    {
      RangeCategory range = (RangeCategory) category;
      String lower2 = range.getLowerBoundStr();
      String upper2 = range.getUpperBoundStr();

      try
      {
        Double lowerD2 = Double.valueOf(lower2);
        Double upperD2 = Double.valueOf(upper2);

        // Numeric comparison
        if (lowerD != null)
        {
          this.checkAgainstRangeNumbers(lowerD, upperD, lowerD2, upperD2);
        }
      }
      catch (NumberFormatException e)
      {
        if (lowerD == null)
        {
          // The values are not numeric, so try a string comparison
          checkAgainstRangeStrings(lower, upper, lower2, upper2);
        }
      }
    }
  }
}
