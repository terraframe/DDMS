package dss.vector.solutions.query;

import org.apache.commons.lang.math.NumberRange;

public class RangeCategory extends RangeCategoryBase implements com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1241158118735L;

  public RangeCategory()
  {
    super();
  }

  @Override
  public NumberRange getAsNumberRange()
  {
    return new NumberRange(this.getLowerBound(), this.getUpperBound());
  }

}
