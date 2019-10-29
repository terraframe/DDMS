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
package dss.vector.solutions.util.yui;

import com.runwaysdk.business.ComponentDTO;
import com.runwaysdk.generation.loader.Reloadable;

import dss.vector.solutions.geo.generated.GeoEntityDTO;

public class YUIReferenceEditor extends YUITextEditor implements Reloadable
{
  @Override
  public String getValue(Object object)
  {
    ComponentDTO componentDTO = (ComponentDTO) object;

    return componentDTO.getId();
  }

  @Override
  public String getDefaultValue(Object object)
  {
    if (object instanceof GeoEntityDTO)
    {
      GeoEntityDTO dto = (GeoEntityDTO) object;

      return YUIColumn.getDefaultValue(dto.getId(), dto.getDisplayString());
    }
    
    return "'" + ((ComponentDTO) object).getId() + "'";
  }  
}
