package dss.vector.solutions.query;

import org.apache.commons.lang.math.NumberRange;

import com.terraframe.mojo.dataaccess.transaction.AbortIfProblem;

public class NonRangeCategory extends NonRangeCategoryBase implements com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1241158039274L;

  public NonRangeCategory()
  {
    super();
  }

  @Override
  public NumberRange getAsNumberRange()
  {
    this.validateExactValue();
    return getNumberRange();
  }
  
  @AbortIfProblem
  private NumberRange getNumberRange()
  {
    return new NumberRange(this.getExactValue());
  }
  
  
  @Override
  protected String buildKey()
  {
    //TODO: Naifeh needs to define this key
    return this.getId();
  }
}
