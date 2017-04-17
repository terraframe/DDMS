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
