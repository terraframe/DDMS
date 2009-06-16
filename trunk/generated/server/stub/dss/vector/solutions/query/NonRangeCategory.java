package dss.vector.solutions.query;

import org.apache.commons.lang.math.NumberRange;

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
    return new NumberRange(this.getExactValue());
  }



}
