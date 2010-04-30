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
