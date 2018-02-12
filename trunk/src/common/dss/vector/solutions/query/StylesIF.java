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

import java.math.BigDecimal;
import java.util.List;

import com.runwaysdk.generation.loader.Reloadable;

public interface StylesIF extends Reloadable
{

  public boolean isEnumerationAttribute(String attributeName);

  public String getValue(String name);

  public List<String> getAttributeNames();

  public List<String> getEnumNames(String attributeName);

  public void addEnumItem(String attributeName, String enumName);

  public void setValue(String attributeName, String value);

  public StylesIF createMergedStyle();

  public String getFontFamily();

  public Integer getFontSize();

  public String getFontStylesName();

  public String getFill();

  public BigDecimal getAnchorPointX();

  public BigDecimal getAnchorPointY();

  public Integer getDisplacementX();

  public Integer getDisplacementY();

  public Integer getLabelRotation();

  public Integer getLabelHaloRadius();

  public String getLabelHaloFill();

  public BigDecimal getLabelHaloOpacity();

  public BigDecimal getGoodnessOfFit();

  public Boolean getConflictResolution();

  public Integer getSpaceAround();

  public String getPointStroke();

  public Integer getPointWidth();

  public String getPointMarkerName();

  public BigDecimal getPointStrokeOpacity();

  public Integer getPointSize();

  public Integer getPointRotation();

  public Boolean getEnable_anchorPointX();

  public Boolean getEnable_anchorPointY();

  public Boolean getEnable_conflictResolution();

  public Boolean getEnable_displacementX();

  public Boolean getEnable_displacementY();

  public Boolean getEnable_fill();

  public Boolean getEnable_fontFamily();

  public Boolean getEnable_fontSize();

  public Boolean getEnable_fontStyles();

  public Boolean getEnable_goodnessOfFit();

  public Boolean getEnable_labelHaloFill();

  public Boolean getEnable_labelHaloOpacity();

  public Boolean getEnable_labelHaloRadius();

  public Boolean getEnable_labelRotation();

  public Boolean getEnable_pointMarker();

  public Boolean getEnable_pointRotation();

  public Boolean getEnable_pointSize();

  public Boolean getEnable_pointStroke();

  public Boolean getEnable_pointStrokeOpacity();

  public Boolean getEnable_pointWidth();

  public Boolean getEnable_polygonFill();

  public Boolean getEnable_polygonFillOpacity();

  public Boolean getEnable_polygonStroke();

  public Boolean getEnable_polygonStrokeOpacity();

  public Boolean getEnable_polygonWidth();

  public Boolean getEnable_spaceAround();

  public String getPolygonFill();

  public String getPolygonStroke();

  public Integer getPolygonWidth();

  public BigDecimal getPolygonStrokeOpacity();

  public BigDecimal getPolygonFillOpacity();

}
