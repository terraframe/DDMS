package dss.vector.solutions;

import java.util.HashMap;
import java.util.Map;

import com.runwaysdk.generation.CommonGenerationUtil;
import com.runwaysdk.generation.loader.Reloadable;

import dss.vector.solutions.util.yui.ColumnSetup;
import dss.vector.solutions.util.yui.DataGrid;

public abstract class GridBuilder implements Reloadable
{
  public abstract DataGrid build();


  public static Map<String, ColumnSetup> getColumns(String[] keys, int hidden, boolean editable)
  {
    Map<String, ColumnSetup> map = new HashMap<String, ColumnSetup>();

    for (int i = 0; i < keys.length; i++)
    {
      ColumnSetup setup = ( i < hidden ? new ColumnSetup(true, editable) : new ColumnSetup(false, true) );

      map.put(keys[i], setup);
    }

    return map;
  }

  public static void upperFirstCharacter(String[] array)
  {
    for (int i = 0; i < array.length; i++)
    {
      array[i] = CommonGenerationUtil.upperFirstCharacter(array[i]);
    }
  }

  public static void setValidator(Map<String, ColumnSetup> columns, String key, String validator)
  {
    ColumnSetup setup = getSetup(columns, key);
    setup.setValidator(validator);
  }

  public static void setEditable(Map<String, ColumnSetup> columns, String key, boolean editable)
  {
    ColumnSetup setup = getSetup(columns, key);
    setup.setEditable(editable);
  }


  public static ColumnSetup getSetup(Map<String, ColumnSetup> columns, String key)
  {
    return columns.get(CommonGenerationUtil.upperFirstCharacter(key));
  }

}
