package dss.vector.solutions.query;



public class NonRangeCategory extends NonRangeCategoryBase implements com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1241158039274L;

  public NonRangeCategory()
  {
    super();
  }
  
  @Override
  protected String buildKey()
  {
    //TODO: Naifeh needs to define this key
    return this.getId();
  }
  
  public String toString()
  {
    return this.getExactValueStr();
  }
  
  @Override
  protected void preValidate()
  {
    this.validateExactValueStr();
  }
  
  /**
   * Checks two NonRangeCategory values for overlapping. This is a simple string
   * comparison.
   * 
   * @param exactValue
   * @param exactValue2
   */
  private void checkAgainstNonRange(String exactValue, String exactValue2)
  {
    if(exactValue.equals(exactValue2))
    {
      this.throwsOverlapException(exactValue, exactValue2);
    }
  }
  
  private void checkAgainstRangeNumbers(Double exactValue, Double lowerBound, Double upperBound)
  {
    if(exactValue >= lowerBound && exactValue < upperBound)
    {
      this.throwsOverlapException(exactValue.toString(), lowerBound.toString(), upperBound.toString());
    }
  }
  
  private void checkAgainstRangeStrings(String exactValue, String lowerStr, String upperStr)
  {
    if(exactValue.compareTo(lowerStr) >= 0 && exactValue.compareTo(upperStr) <= 0)
    {
      this.throwsOverlapException(exactValue, lowerStr, upperStr);
    }
  }

  @Override
  protected void checkBounds(AbstractCategory category)
  {
    String exactValue = this.getExactValueStr();
    
    Double exactD = null;
    try
    {
      exactD = Double.parseDouble(exactValue);
    }
    catch(NumberFormatException e)
    {
      // We know the value is not numeric
    }
    
    if(category instanceof NonRangeCategory)
    {
      NonRangeCategory exact = (NonRangeCategory) category;
      this.checkAgainstNonRange(exactValue, exact.getExactValueStr());
    }
    else
    {
      RangeCategory range = (RangeCategory) category;
      String lower = range.getLowerBoundStr();
      String upper = range.getUpperBoundStr();
      
      try
      {
        Double lowerD = Double.parseDouble(lower); 
        Double upperD = Double.parseDouble(upper);
        
        if(exactD != null)
        {
          // Compare as numbers
          this.checkAgainstRangeNumbers(exactD, lowerD, upperD);
        }
      }
      catch(NumberFormatException e)
      {
        // The values are not numeric, so try a string comparison
        if(exactD == null)
        {
          checkAgainstRangeStrings(exactValue, lower, upper);
        }
      }
    }
  }
}
