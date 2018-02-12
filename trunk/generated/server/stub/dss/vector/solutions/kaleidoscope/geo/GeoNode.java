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
package dss.vector.solutions.kaleidoscope.geo;

import com.runwaysdk.generation.loader.Reloadable;
import com.runwaysdk.system.gis.metadata.MdAttributeMultiPolygon;
import com.runwaysdk.system.gis.metadata.MdAttributePoint;
import com.runwaysdk.system.metadata.MdAttribute;
import com.runwaysdk.system.metadata.MdAttributeReference;

public abstract class GeoNode extends GeoNodeBase implements Reloadable
{
  private static final long serialVersionUID = 1975242459;

  public GeoNode()
  {
    super();
  }

  @Override
  protected String buildKey()
  {
    MdAttributeReference attribute = this.getGeoEntityAttribute();

    if (attribute != null)
    {
      return attribute.getKey();
    }

    return this.getId();
  }

  public abstract MdAttribute getIdentifierAttribute();

  public abstract MdAttribute getDisplayLabelAttribute();

  public abstract MdAttribute getGeometryAttribute();

  public abstract MdAttributeMultiPolygon getMultiPolygonAttribute();

  public abstract MdAttributePoint getPointAttribute();
}
