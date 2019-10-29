/*******************************************************************************
 * Copyright (C) 2018 IVCC
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 ******************************************************************************/
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

  public String getViewName(BasicLayerIF layer)
  {
    if (hasOverride(layer))
    {
      return override.get(layer.getId());
    }

    return layer.getViewName();
  }

  public boolean hasOverride(BasicLayerIF layer)
  {
    return override.containsKey(layer.getId());
  }

  public boolean includeLayer(BasicLayerIF layer)
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
