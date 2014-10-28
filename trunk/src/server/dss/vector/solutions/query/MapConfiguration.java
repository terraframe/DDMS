package dss.vector.solutions.query;

import java.util.HashMap;
import java.util.Map;

import com.runwaysdk.generation.loader.Reloadable;

public class MapConfiguration implements Reloadable, MapConfigurationIF
{
  private Map<String, String> override; 

  public MapConfiguration()
  {
    this(new HashMap<String, String>());
  }

  public MapConfiguration(Map<String, String> override)
  {
    this.override = override;
  }

  public String getViewName(LayerIF layer)
  {
    if (hasOverride(layer))
    {
      return override.get(layer.getId());
    }

    return layer.getViewName();
  }

  public boolean hasOverride(LayerIF layer)
  {
    return override.containsKey(layer.getId());
  }
}
