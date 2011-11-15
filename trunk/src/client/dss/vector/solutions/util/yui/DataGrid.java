package dss.vector.solutions.util.yui;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.runwaysdk.generation.loader.Reloadable;

import dss.vector.solutions.util.Halp;

public abstract class DataGrid implements Reloadable
{
  private String  tableId;

  private boolean excelButtons;

  private boolean addButton;

  private boolean saveButton;

  private boolean readable;

  public DataGrid()
  {
    this("", true);
  }

  public DataGrid(String tableId, boolean readable)
  {
    this.tableId = tableId;
    this.readable = readable;

    this.excelButtons = false;
    this.addButton = false;
    this.saveButton = false;
  }

  public abstract List<String> getColumns();

  public abstract String getMetadata();

  public abstract List<String> getKeys();

  protected abstract String getMetadata(int offset);

  protected abstract int size();

  public abstract JSONArray getData();

  public abstract int getDataLength();

  protected abstract List<String> getDefaultValuesAsList();

  protected abstract List<String> getDropDownOptions();

  protected boolean isExcelButtons()
  {
    return excelButtons;
  }

  protected void setExcelButtons(boolean excelButtons)
  {
    this.excelButtons = excelButtons;
  }

  protected boolean isAddButton()
  {
    return addButton;
  }

  protected void setAddButton(boolean addButton)
  {
    this.addButton = addButton;
  }

  protected boolean isSaveButton()
  {
    return saveButton;
  }

  protected void setSaveButton(boolean saveButton)
  {
    this.saveButton = saveButton;
  }

  public String getColumnSetupWithDelete()
  {
    return this.getColumnSetup(DataGrid.getDeleteColumn());
  }

  public String getColumnSetup(String extraColumns)
  {
    List<String> columns = this.getColumns();

    if (extraColumns.length() > 0)
    {
      columns.add(extraColumns);
    }

    return ( "[" + Halp.join(columns, ",\n") + "]" );
  }

  public String getDefaultValues()
  {
    List<String> defaults = this.getDefaultValuesAsList();

    return "{" + Halp.join(defaults, ",") + "}";
  }

  public String getDropDownMap()
  {
    List<String> options = this.getDropDownOptions();

    return Halp.join(options, "\n");
  }

  public static String getDeleteColumn()
  {
    return "{key:'delete', label:' ', className: 'delete-button', action:'delete', madeUp:true}";
  }
  
  public String getTableId()
  {
    return this.tableId;
  }
  
  public JSONObject getJSON() throws JSONException
  {
    List<String> cols = this.getColumns();
    JSONArray colsArr = new JSONArray();
    for(String col : cols)
    {
      colsArr.put(new JSONObject(col));
    }
    
    JSONObject config = new JSONObject();
    config.put("columnDefs", colsArr);
    config.put("defaults" ,  new JSONObject( this.getDefaultValues()));
    config.put("div_id" , this.tableId);
    config.put("excelButtons" , this.excelButtons);
    config.put("addButton" , this.addButton);
    config.put("saveButton" , this.saveButton);
    
    JSONObject json = new JSONObject();
    json.put("config", config);
    json.put("metadata", new JSONArray( this.getMetadata()));
    json.put("data", this.getData());
    
    return json;
  }
  
  public String getJavascript()
  {
    StringBuffer buffer = new StringBuffer();

    if (this.readable)
    {
      buffer.append("var " + this.tableId + "Data = {\n");
      buffer.append("  columnDefs:" + this.getColumns() + ",\n");
      buffer.append("  defaults:" + this.getDefaultValues() + ",\n");
      buffer.append("  div_id:'" + this.tableId + "',\n");
      buffer.append("  excelButtons:" + this.excelButtons + ",\n");
      buffer.append("  addButton:" + this.addButton + ",\n");
      buffer.append("  saveButton:" + this.saveButton + "\n");
      buffer.append("};\n");
      buffer.append("var " + this.tableId + "Grid = new MDSS.DataGrid(new MDSS.DataGridModel(new MDSS.ModelMetadata.init(" + this.getMetadata() + "), " + this.getData().toString() + ", null), " + this.tableId + "Data);\n");
      buffer.append(this.tableId+"Grid;");
    }
    else
    {
      buffer.append("var " + this.tableId + "Grid = null;\n");
    }

    return buffer.toString();
  }
}
