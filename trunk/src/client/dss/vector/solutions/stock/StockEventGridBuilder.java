package dss.vector.solutions.stock;

import java.util.Map;

import com.runwaysdk.generation.loader.Reloadable;

import dss.vector.solutions.GridBuilder;
import dss.vector.solutions.util.yui.ColumnSetup;
import dss.vector.solutions.util.yui.DataGrid;
import dss.vector.solutions.util.yui.ViewDataGrid;

public class StockEventGridBuilder extends GridBuilder implements Reloadable
{
  private StockEventViewDTO dto;
  
  private StockEventViewDTO[] data;

  public StockEventGridBuilder(StockEventViewDTO dto, StockEventViewDTO[] data)
  {
    this.dto = dto;
    this.data = data;
  }


  @Override
  public DataGrid build()
  {    
    String[] keys = this.getKeys();
    Map<String, ColumnSetup> columns = getColumns(keys, 8, false);

    GridBuilder.setEditable(columns, StockEventViewDTO.ITEMLABEL, false);
    GridBuilder.setEditable(columns, StockEventViewDTO.AVAILABLESTOCK, false);
    GridBuilder.setValidator(columns, StockEventViewDTO.QUANTITY, "MDSS.validateNumber");
    GridBuilder.setValidator(columns, StockEventViewDTO.COST, "MDSS.validateNumber");
       
    return new ViewDataGrid(dto, columns, keys, data);
  }
  
  private String[] getKeys()
  {
    String[] keys = { StockEventViewDTO.CONCRETEID, StockEventViewDTO.STOCKDEPOT, StockEventViewDTO.STAFF, StockEventViewDTO.STAFFLABEL, StockEventViewDTO.OTHERPARTY, StockEventViewDTO.EVENTDATE, StockEventViewDTO.ITEM, StockEventViewDTO.TRANSACTIONTYPE, StockEventViewDTO.ITEMLABEL, StockEventViewDTO.AVAILABLESTOCK, StockEventViewDTO.QUANTITY, StockEventViewDTO.COST};

    upperFirstCharacter(keys);

    return keys;
  }



}
