package dss.vector.solutions.kaleidoscope.wrapper;

import java.util.HashMap;

import com.runwaysdk.generation.loader.Reloadable;

public interface ThematicLayer extends Reloadable, Layer
{
  public AttributeType getAttributeType();

  public String getAttribute();

  public HashMap<String, Double> getLayerMinMax(String attribute);

}
