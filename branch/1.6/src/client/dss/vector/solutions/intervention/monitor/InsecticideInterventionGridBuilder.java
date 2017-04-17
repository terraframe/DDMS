package dss.vector.solutions.intervention.monitor;

import java.util.Map;

import com.runwaysdk.constants.ClientRequestIF;
import com.runwaysdk.generation.loader.Reloadable;

import dss.vector.solutions.GridBuilder;
import dss.vector.solutions.irs.InsecticideBrandViewDTO;
import dss.vector.solutions.util.yui.ColumnSetup;
import dss.vector.solutions.util.yui.DataGrid;
import dss.vector.solutions.util.yui.ViewDataGrid;

public class InsecticideInterventionGridBuilder extends GridBuilder implements Reloadable
{
  private ClientRequestIF            request;

  private ControlInterventionViewDTO dto;

  public InsecticideInterventionGridBuilder(ClientRequestIF request, ControlInterventionViewDTO dto)
  {
    this.request = request;
    this.dto = dto;
  }

  public DataGrid build()
  {
    InsecticideInterventionViewDTO view = new InsecticideInterventionViewDTO(request);
    InsecticideInterventionViewDTO[] data = dto.getInsecticideInterventionViews();
    
    String[] keys = getKeys();
    Map<String, ColumnSetup> columns = getColumns(keys, 1, false);
    
    ColumnSetup setup = GridBuilder.getSetup(columns, InsecticideInterventionViewDTO.INSECTICIDE);
    setup.setType(InsecticideBrandViewDTO.class.getName());
    setup.setMethod("getControlInterventionInsecticideBrands");
    setup.setGetter("getInsecticideView");
    setup.setIncludeBlank(true);
    
    GridBuilder.setEditable(columns, InsecticideInterventionViewDTO.INTERVENTIONMETHOD, false);
    GridBuilder.setEditable(columns, InsecticideInterventionViewDTO.ACTIVEINGREDIENT, false);
    GridBuilder.setEditable(columns, InsecticideInterventionViewDTO.CONCENTRATIONQUANTIFIER, false);
    GridBuilder.setEditable(columns, InsecticideInterventionViewDTO.CONCENTRATIONQUALIFIER, false);
    GridBuilder.setValidator(columns, InsecticideInterventionViewDTO.QUANTITY, "MDSS.validateNumber");
       
    return new ViewDataGrid(view, columns, keys, data);
  }


  
  private String[] getKeys()
  {
    String[] keys = new String[] { InsecticideInterventionViewDTO.CONCRETEID, InsecticideInterventionViewDTO.INTERVENTIONMETHOD, InsecticideInterventionViewDTO.INSECTICIDE, InsecticideInterventionViewDTO.ACTIVEINGREDIENT, InsecticideInterventionViewDTO.CONCENTRATIONQUANTIFIER, InsecticideInterventionViewDTO.CONCENTRATIONQUALIFIER, InsecticideInterventionViewDTO.QUANTITY, InsecticideInterventionViewDTO.UNIT };

    upperFirstCharacter(keys);

    return keys;
  }

}
