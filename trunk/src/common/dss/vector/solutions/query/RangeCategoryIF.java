package dss.vector.solutions.query;

import com.terraframe.mojo.generation.loader.Reloadable;

public interface RangeCategoryIF extends AbstractCategoryIF, Reloadable
{
  public String getLowerBoundStr();
  public String getUpperBoundStr();
}
