package dss.vector.solutions.query;

import com.runwaysdk.generation.loader.Reloadable;

public interface MapConfigurationIF extends Reloadable
{
  public String getViewName(BasicLayerIF layer);

}
