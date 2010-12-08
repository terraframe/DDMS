package dss.vector.solutions.general;

import java.util.Map;

import com.runwaysdk.constants.ClientRequestIF;

import dss.vector.solutions.GridBuilder;
import dss.vector.solutions.util.yui.ColumnSetup;
import dss.vector.solutions.util.yui.DataGrid;
import dss.vector.solutions.util.yui.ViewDataGrid;

public class PopulationGridBuilder extends GridBuilder
{
  private ClientRequestIF       request;

  private PopulationDataViewDTO dto;

  public PopulationGridBuilder(ClientRequestIF request, PopulationDataViewDTO dto)
  {
    this.request = request;
    this.dto = dto;
  }

  public DataGrid build()
  {
    PopulationDataViewDTO[] data = null;

    if (dto.getPopulationType())
    {
      data = PopulationDataViewDTO.getViews(request, dto.getGeoEntity(), dto.getYearOfData());
    }
    else
    {
      data = PopulationDataViewDTO.getFacilityViews(request, dto.getGeoEntity(), dto.getYearOfData());
    }

    String[] keys = getKeys();

    Map<String, ColumnSetup> columns = getColumns(keys, 4, true);

    GridBuilder.setEditable(columns, PopulationDataViewDTO.ENTITYLABEL, false);
    GridBuilder.setValidator(columns, PopulationDataViewDTO.POPULATION, "YAHOO.widget.DataTable.validateNumber");
    GridBuilder.setValidator(columns, PopulationDataViewDTO.GROWTHRATE, "YAHOO.widget.DataTable.validateNumber");

    return new ViewDataGrid(dto, columns, keys, data);
  }

  private String[] getKeys()
  {
    String[] keys = new String[] { PopulationDataViewDTO.CONCRETEID, PopulationDataViewDTO.GEOENTITY, PopulationDataViewDTO.YEAROFDATA, PopulationDataViewDTO.ESTIMATED, PopulationDataViewDTO.ENTITYLABEL, PopulationDataViewDTO.POPULATION, PopulationDataViewDTO.GROWTHRATE };

    upperFirstCharacter(keys);

    return keys;
  }
}
