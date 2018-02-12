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

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import com.runwaysdk.ClientException;
import com.runwaysdk.generation.loader.Reloadable;

import dss.vector.solutions.util.Halp;

public abstract class AbstractCompositeGrid extends DataGrid implements Reloadable
{
  public AbstractCompositeGrid()
  {
    super();
  }

  public AbstractCompositeGrid(String tableId, boolean readable)
  {
    super(tableId, readable);
  }

  public abstract DataGrid[] getGrids();

  @Override
  public List<String> getColumns()
  {
    List<String> columns = new ArrayList<String>();

    DataGrid[] generators = this.getGrids();

    for (DataGrid generator : generators)
    {
      columns.addAll(generator.getColumns());
    }

    return columns;
  }

  @Override
  public String getMetadata()
  {
    return this.getMetadata(0);
  }

  @Override
  protected String getMetadata(int offset)
  {
    List<String> list = new ArrayList<String>();

    int start = offset;

    DataGrid[] generators = this.getGrids();

    for (DataGrid generator : generators)
    {
      String _metadata = generator.getMetadata(start);

      list.add(_metadata);

      start += generator.size();
    }

    return "[" + Halp.join(list, ",") + "]";
  }

  @Override
  protected int size()
  {
    int size = 0;

    DataGrid[] generators = this.getGrids();

    for (DataGrid generator : generators)
    {
      size += generator.size();
    }

    return size;
  }

  @Override
  public List<String> getKeys()
  {
    List<String> keys = new ArrayList<String>();

    DataGrid[] generators = this.getGrids();

    for (DataGrid generator : generators)
    {
      keys.addAll(generator.getKeys());
    }

    return keys;
  }

  @Override
  public int getDataLength()
  {
    DataGrid[] generators = this.getGrids();

    if (generators.length > 0)
    {
      return generators[0].getDataLength();
    }

    return 0;
  }

  @Override
  public JSONArray getData()
  {
    JSONArray array = new JSONArray();

    DataGrid[] generators = this.getGrids();

    int length = this.getDataLength();

    for (int j = 0; j < length; j++)
    {
      JSONObject object = new JSONObject();

      for (int i = 0; i < generators.length; i++)
      {
        JSONArray data = generators[i].getData();

        if (data.length() == length)
        {
          try
          {
            JSONObject _object = data.getJSONObject(j);

            concat(object, _object);
          }
          catch (Exception e)
          {
            throw new ClientException(e);
          }
        }

      }

      array.put(object);
    }

    return array;
  }

  @SuppressWarnings("unchecked")
  public static void concat(JSONObject dest, JSONObject source)
  {
    try
    {
      Iterator<String> it = (Iterator<String>) source.keys();

      while (it.hasNext())
      {
        String key = it.next();

        Object value = source.get(key);

        dest.put(key, value);
      }
    }
    catch (Exception e)
    {
      throw new ClientException(e);
    }
  }

  @Override
  public List<String> getDefaultValuesAsList()
  {
    List<String> defaults = new ArrayList<String>();

    DataGrid[] generators = this.getGrids();

    for (DataGrid generator : generators)
    {
      defaults.addAll(generator.getDefaultValuesAsList());
    }

    return defaults;
  }

  @Override
  protected List<String> getDropDownOptions()
  {
    List<String> options = new ArrayList<String>();

    DataGrid[] generators = this.getGrids();

    for (DataGrid generator : generators)
    {
      options.addAll(generator.getDropDownOptions());
    }

    return options;
  }
}
