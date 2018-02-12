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
package dss.vector.solutions.util;

import java.lang.reflect.InvocationTargetException;

import com.runwaysdk.business.MutableDTO;
import com.runwaysdk.constants.ClientRequestIF;
import com.runwaysdk.controller.DTOFacade;
import com.runwaysdk.generation.loader.Reloadable;
import com.runwaysdk.session.AttributeReadPermissionExceptionDTO;

import dss.vector.solutions.geo.generated.GeoEntityDTO;

public class AttributeUtil implements Reloadable
{
  public static Object getValue(String accessorName, MutableDTO mutableDTO)
  {
    try
    {
      try
      {
        return new DTOFacade(accessorName, mutableDTO).getValue();
      }
      catch (InvocationTargetException e)
      {
        throw e.getTargetException();
      }
    }
    catch (AttributeReadPermissionExceptionDTO e)
    {
      return null;
    }
    catch (Throwable t)
    {
      // Propigate the excepection
      if (t instanceof RuntimeException)
      {
        throw (RuntimeException) t;
      }

      // TODO This code will only be reached if an invalid accessorName is
      // supplied. As such change exception type to the client layer equivalent
      // of Application Exception
      throw new RuntimeException(t);
    }
  }

  public static String getString(String accessorName, MutableDTO mutableDTO)
  {
    return (String) AttributeUtil.getValue(accessorName, mutableDTO);
  }

  public static GeoEntityDTO getGeoEntityFromGeoId(String accessorName, MutableDTO mutableDTO)
  {
    String geoId = (String) AttributeUtil.getValue(accessorName, mutableDTO);

    if (geoId != null && !geoId.equals(""))
    {
      ClientRequestIF request = mutableDTO.getRequest();

      return GeoEntityDTO.searchByGeoId(request, geoId);
    }

    return null;
  }
}
