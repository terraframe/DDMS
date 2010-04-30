package dss.vector.solutions.util.yui;

import java.util.List;

import org.json.JSONArray;

import com.runwaysdk.generation.loader.Reloadable;

import dss.vector.solutions.util.Halp;

public abstract class DataGrid implements Reloadable
{
  public abstract List<String> getColumns();

  public abstract String getMetadata();

  public abstract List<String> getKeys();

  protected abstract String getMetadata(int offset);

  protected abstract int size();

  public abstract JSONArray getData();

  public abstract int getDataLength();
  
  protected abstract List<String> getDefaultValuesAsList();
  
  protected abstract List<String> getDropDownOptions();
  
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
}
