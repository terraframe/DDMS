package dss.vector.solutions.query;

import com.terraframe.mojo.generation.loader.Reloadable;

public interface NonRangeCategoryIF extends AbstractCategoryIF, Reloadable
{
  public String getExactValueStr();
}
