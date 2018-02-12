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

  private boolean deletable;

  private String  saveHandler;

  public DataGrid()
  {
    this("", true);
  }

  public DataGrid(String tableId, boolean readable)
  {
    this.tableId = tableId;
    this.readable = readable;
    this.deletable = false;

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

  public void setExcelButtons(boolean excelButtons)
  {
    this.excelButtons = excelButtons;
  }

  protected boolean isAddButton()
  {
    return addButton;
  }

  public void setAddButton(boolean addButton)
  {
    this.addButton = addButton;
  }

  protected boolean isSaveButton()
  {
    return saveButton;
  }

  public void setSaveButton(boolean saveButton)
  {
    this.saveButton = saveButton;
  }

  public void setDeletable(boolean deletable)
  {
    this.deletable = deletable;
  }

  public boolean isDeletable()
  {
    return deletable;
  }

  public void setSaveHandler(String saveHandler)
  {
    this.saveHandler = saveHandler;
  }

  public String getSaveHandler()
  {
    return saveHandler;
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
    for (String col : cols)
    {
      colsArr.put(new JSONObject(col));
    }

    JSONObject config = new JSONObject();
    config.put("columnDefs", colsArr);
    config.put("defaults", new JSONObject(this.getDefaultValues()));
    config.put("div_id", this.tableId);
    config.put("excelButtons", this.excelButtons);
    config.put("addButton", this.addButton);
    config.put("saveButton", this.saveButton);

    JSONObject json = new JSONObject();
    json.put("config", config);
    json.put("metadata", new JSONArray(this.getMetadata()));
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

      if (saveHandler != null)
      {
        buffer.append("  saveHandler:" + this.saveHandler + "\n");
      }

      buffer.append("};\n");
      buffer.append("var " + this.tableId + "Grid = new MDSS.DataGrid(new MDSS.DataGridModel(new MDSS.ModelMetadata.init(" + this.getMetadata() + "), " + this.getData().toString() + ", null), " + this.tableId + "Data);\n");
      buffer.append(this.tableId + "Grid;");
    }
    else
    {
      buffer.append("var " + this.tableId + "Grid = null;\n");
    }

    return buffer.toString();
  }
}
