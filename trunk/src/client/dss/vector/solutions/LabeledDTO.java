package dss.vector.solutions;

import com.runwaysdk.generation.loader.Reloadable;

public interface LabeledDTO extends Reloadable
{
  public String getOptionId();

  public String getLabel();
}
