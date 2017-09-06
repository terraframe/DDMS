package dss.vector.solutions.query;

import com.runwaysdk.generation.loader.Reloadable;

public interface BasicLayerIF extends Reloadable
{
  public String getId();

  public String getLayerName();

  public String getViewName();
}
