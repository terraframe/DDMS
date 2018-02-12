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
