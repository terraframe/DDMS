package dss.vector.solutions.irs;

import java.util.Map;

import com.runwaysdk.constants.ClientRequestIF;
import com.runwaysdk.generation.loader.Reloadable;

import dss.vector.solutions.GridBuilder;
import dss.vector.solutions.util.yui.ColumnSetup;
import dss.vector.solutions.util.yui.DataGrid;
import dss.vector.solutions.util.yui.ViewDataGrid;

public class ConfigurationGridBuilder extends GridBuilder implements Reloadable
{
  private ClientRequestIF request;

  public ConfigurationGridBuilder(ClientRequestIF request)
  {
    this.request = request;
  }

  @Override
  public DataGrid build()
  {
    InsecticideNozzleViewDTO view = new InsecticideNozzleViewDTO(request);
    InsecticideNozzleViewDTO[] data = InsecticideNozzleViewDTO.getAll(request);

    String[] keys = this.getKeys();

    Map<String, ColumnSetup> map = getColumns(keys, 3, false);

    ColumnSetup brandSetup = GridBuilder.getSetup(map, InsecticideNozzleViewDTO.BRAND);
    brandSetup.setType(InsecticideBrandViewDTO.class.getName());
    brandSetup.setMethod("getNozzleInsecticideBrands");
    brandSetup.setGetter("getBrandView");
    brandSetup.setValidator("validateImmutable");

    ColumnSetup nozzleSetup = GridBuilder.getSetup(map, InsecticideNozzleViewDTO.NOZZLE);
    nozzleSetup.setType(NozzleViewDTO.class.getName());
    nozzleSetup.setMethod("getAllActive");
    nozzleSetup.setGetter("getNozzleView");
    nozzleSetup.setValidator("validateImmutable");

    return new ViewDataGrid(view, map, keys, data);
  }

  private String[] getKeys()
  {
    String[] keys = { InsecticideNozzleViewDTO.INSECTICIDENOZZLEID, InsecticideNozzleViewDTO.BRANDLABEL, InsecticideNozzleViewDTO.NOZZLELABEL, InsecticideNozzleViewDTO.CONFIGURATIONDATE, InsecticideNozzleViewDTO.BRAND, InsecticideNozzleViewDTO.NOZZLE, InsecticideNozzleViewDTO.ENABLED };

    upperFirstCharacter(keys);

    return keys;
  }
}
