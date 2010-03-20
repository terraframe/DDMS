package dss.vector.solutions.query;



public class NonRangeCategory extends NonRangeCategoryBase implements com.runwaysdk.generation.loader.Reloadable, NonRangeCategoryIF
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
  
  @Override
  protected void checkBounds(AbstractCategory category)
  {
    if(category instanceof NonRangeCategory)
    {
      String exact1 = this.getExactValueStr();
      String exact2 = ((NonRangeCategory) category).getExactValueStr();
      
      try
      {
        if(Double.parseDouble(exact1) == Double.parseDouble(exact2))
        {
          this.throwsOverlapException(exact1, exact2);
        }
      }
      catch(NumberFormatException e)
      {
        if(exact1.equals(exact2))
        {
          this.throwsOverlapException(exact1, exact2);
        }
      }
    }
    else
    {
      RangeCategory range = (RangeCategory) category;
      range.checkBounds(this);
    }
  }
}
