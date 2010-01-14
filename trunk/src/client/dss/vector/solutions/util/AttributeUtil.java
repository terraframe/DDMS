package dss.vector.solutions.util;

import java.lang.reflect.InvocationTargetException;

import com.terraframe.mojo.business.MutableDTO;
import com.terraframe.mojo.constants.ClientRequestIF;
import com.terraframe.mojo.controller.DTOFacade;
import com.terraframe.mojo.dataaccess.attributes.ClientReadAttributePermissionException;
import com.terraframe.mojo.generation.loader.Reloadable;

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
    catch (ClientReadAttributePermissionException e)
    {
      return null;
    }
    catch (Throwable t)
    {
      // TODO Change exception type
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
    
    if(geoId != null && !geoId.equals(""))
    {
      ClientRequestIF request = mutableDTO.getRequest();
      
      return GeoEntityDTO.searchByGeoId(request, geoId);
    }
    
    return null;
  }
}
