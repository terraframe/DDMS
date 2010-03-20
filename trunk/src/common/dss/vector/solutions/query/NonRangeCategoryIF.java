package dss.vector.solutions.query;

import com.runwaysdk.generation.loader.Reloadable;

public interface NonRangeCategoryIF extends AbstractCategoryIF, Reloadable
{
  public String getExactValueStr();
}
