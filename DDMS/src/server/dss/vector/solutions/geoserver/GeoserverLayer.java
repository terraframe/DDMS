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
package dss.vector.solutions.geoserver;

import org.json.JSONException;
import org.json.JSONObject;

public class GeoserverLayer implements GeoserverLayerIF
{
  public static enum LayerType {
    POINT, POLYGON, LINE
  }

  private String    layerName;

  private String    styleName;

  private LayerType layerType;

  public void setLayerName(String layerName)
  {
    this.layerName = layerName;
  }

  @Override
  public String getLayerName()
  {
    return this.layerName;
  }

  public void setStyleName(String styleName)
  {
    this.styleName = styleName;
  }

  @Override
  public String getStyleName()
  {
    return this.styleName;
  }

  public void setLayerType(LayerType layerType)
  {
    this.layerType = layerType;
  }

  public LayerType getLayerType()
  {
    return layerType;
  }

  @Override
  public JSONObject toJSON() throws JSONException
  {
    JSONObject object = new JSONObject();
    object.put("layerName", this.layerName);
    object.put("layerType", this.layerType.name());
    object.put("workspace", GeoserverProperties.getOSMWorkspace());

    return object;
  }
}
