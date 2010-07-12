package dss.vector.solutions.util.yui;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.runwaysdk.ApplicationException;
import com.runwaysdk.business.ViewDTO;
import com.runwaysdk.business.generation.GenerationUtil;
import com.runwaysdk.constants.ClientRequestIF;
import com.runwaysdk.dataaccess.attributes.ClientReadAttributePermissionException;
import com.runwaysdk.generation.loader.Reloadable;

import dss.vector.solutions.util.Halp;

public class ViewDataGrid extends DataGrid implements Reloadable
{
  /**
   * MdView defining the attributes generated per term
   */
  private ViewDTO                          view;

  /**
   * Rows
   */
  private ViewDTO[]                        data;

  /**
   * Ordered Map of the YUI columns
   */
  private LinkedHashMap<String, YUIColumn> yuiColumns;

  public ViewDataGrid(ViewDTO view, Map<String, ColumnSetup> map, String[] keys, ViewDTO[] data)
  {
    this("", true, view, map, keys, data, "");
  }
  
  public ViewDataGrid(String tableId, boolean readable, ViewDTO view, Map<String, ColumnSetup> map, String[] keys, ViewDTO[] data)
  {
    this(tableId, readable, view, map, keys, data, "");
  }

  public ViewDataGrid(ViewDTO view, Map<String, ColumnSetup> map, String[] keys, ViewDTO[] data, String postfix)
  {
    this("", true, view, map, keys, data, postfix);    		
  }

  public ViewDataGrid(String tableId, boolean readable, ViewDTO view, Map<String, ColumnSetup> map, String[] keys, ViewDTO[] data, String postfix)
  {
    super(tableId, readable);
    
    this.view = view;
    this.data = data;
    this.yuiColumns = new LinkedHashMap<String, YUIColumn>();

    List<String> _keys = new LinkedList<String>(Arrays.asList(keys));

    for (String accessorName : view.getAttributeNames())
    {
      String key = GenerationUtil.upperFirstCharacter(accessorName);

      // Do not include columns for attributes which cannot be read
      if (!view.isReadable(accessorName))
      {
        _keys.remove(key);
      }
    }

    for (String key : _keys)
    {
      ColumnSetup setup = new ColumnSetup();

      if (map.containsKey(key))
      {
        setup = map.get(key);
      }

      yuiColumns.put(key, new YUIColumn(setup, view, key, postfix));
    }

  }

  public YUIColumn getColumn(String key)
  {
    return this.yuiColumns.get(key);
  }

  /**
   * @return
   */
  public List<String> getColumns()
  {
    List<String> columns = new ArrayList<String>();

    Set<String> keys = yuiColumns.keySet();

    for (String key : keys)
    {
      YUIColumn column = yuiColumns.get(key);
      YUIColumnGenerator generator = new YUIColumnGenerator(column);

      String buffer = generator.getColumnDefinition();

      columns.add("{" + buffer + "}");
    }

    return columns;
  }

  public String getMetadata()
  {
    return "[" + this.getMetadata(0) + "]";
  }

  @Override
  public List<String> getKeys()
  {
    List<String> list = new ArrayList<String>();
    Set<String> keys = yuiColumns.keySet();

    for (String key : keys)
    {
      YUIColumn column = yuiColumns.get(key);

      list.add("'" + column.getColumnKey() + "'");
    }

    return list;
  }

  protected int size()
  {
    return yuiColumns.size();
  }

  protected String getMetadata(int start)
  {
    List<String> buffer = this.getKeys();

    return "{start:" + start + ", end:" + ( start + yuiColumns.size() - 1 ) + ", type:'" + view.getType() + "', keys:[" + Halp.join(buffer) + "]}";
  }

  public ClientRequestIF getRequest()
  {
    return view.getRequest();
  }

  protected JSONObject convertToJSONObject(ViewDTO row)
  {
    Set<String> keys = this.yuiColumns.keySet();
    JSONObject element = new JSONObject();

    for (String key : keys)
    {
      YUIColumn column = this.yuiColumns.get(key);

      String value = column.getValue(row);

      if (value != null)
      {
        try
        {
          element.put(column.getColumnKey(), value);
        }
        catch (JSONException e)
        {
          throw new ApplicationException(e);
        }
      }
    }
    return element;
  }

  @Override
  public JSONArray getData()
  {
    JSONArray array = new JSONArray();

    for (ViewDTO view : data)
    {
      JSONObject element = this.convertToJSONObject(view);

      array.put(element);
    }

    return array;
  }

  @Override
  public int getDataLength()
  {
    return data.length;
  }

  @Override
  public List<String> getDefaultValuesAsList()
  {
    List<String> defaults = new LinkedList<String>();

    Set<String> keys = this.yuiColumns.keySet();

    for (String key : keys)
    {
      try
      {
        YUIColumn column = this.yuiColumns.get(key);

        String defaultValue = column.getDefaultValue();

        if (defaultValue != null)
        {
          defaults.add(defaultValue);
        }
      }
      catch (ClientReadAttributePermissionException e)
      {
        // Do nothing
      }
      catch (Exception e)
      {
        throw new ApplicationException(e);
      }
    }

    return defaults;
  }

  @Override
  protected List<String> getDropDownOptions()
  {
    List<String> list = new ArrayList<String>();
    Set<String> keys = this.yuiColumns.keySet();

    for (String key : keys)
    {
      YUIColumn column = this.yuiColumns.get(key);

      list.add(column.getDropDownOptions());
    }
    
    return list;
  }
}
