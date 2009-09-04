package dss.vector.solutions.query;

import org.apache.commons.lang.math.NumberRange;

public abstract class AbstractCategory extends AbstractCategoryBase implements com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1241158096964L;

  public AbstractCategory()
  {
    super();
  }

  /**
   * Returns this AbstractCategory as a NumberRange implementation.
   *
   * @return
   */
  public abstract NumberRange getAsNumberRange();

  
  @Override
  protected String buildKey()
  {
    //TODO: Naifeh needs to define this key
    return this.getId();
  }

}
