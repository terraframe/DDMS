package dss.vector.solutions.query;

import java.util.HashMap;
import java.util.Map;

import com.runwaysdk.generation.loader.Reloadable;

import dss.vector.solutions.general.Disease;

public class MapConfiguration implements Reloadable, MapConfigurationIF
{
  private Map<String, String> override;

  private Disease             disease;

  private Integer             layerWidth;

  private Integer             layerHeight;

  public MapConfiguration()
  {
    this(new HashMap<String, String>(), null);
  }

  public MapConfiguration(Disease disease)
  {
    this(new HashMap<String, String>(), disease);
  }

  public MapConfiguration(Map<String, String> override, Disease disease)
  {
    this.override = override;
    this.disease = disease;
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

  public boolean includeLayer(LayerIF layer)
  {
    if (override.size() > 0)
    {
      return this.hasOverride(layer);
    }

    return true;
  }

  public Disease getDisease()
  {
    return this.disease;
  }

  public void setDisease(Disease disease)
  {
    this.disease = disease;
  }

  public void setLayerWidth(int layerWidth)
  {
    this.layerWidth = layerWidth;
  }

  public Integer getLayerWidth(int layerWidth)
  {
    if (this.layerWidth != null)
    {
      return this.layerWidth;
    }

    return layerWidth;
  }

  public void setLayerHeight(int layerHeight)
  {
    this.layerHeight = layerHeight;
  }

  public Integer getLayerHeight(int layerHeight)
  {
    if (this.layerHeight != null)
    {
      return this.layerHeight;
    }

    return layerHeight;
  }

}
