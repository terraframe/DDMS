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
package dss.vector.solutions.query;

import java.util.LinkedList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.runwaysdk.generation.loader.Reloadable;
import com.runwaysdk.transport.conversion.ConversionExceptionDTO;

public class SelectableGroup implements Reloadable
{
  private String                 label;

  private String                 group;

  private String                 classType;

  private List<SelectableOption> options;

  public SelectableGroup()
  {
    this.label = "Default Label";
    this.group = "root";
    this.classType = "test.class";
    this.options = new LinkedList<SelectableOption>();
  }

  public String getLabel()
  {
    return label;
  }

  public void setLabel(String label)
  {
    this.label = label;
  }

  public String getGroupName()
  {
    return group;
  }

  public void setGroup(String group)
  {
    this.group = group;
  }

  public String getClassType()
  {
    return classType;
  }

  public void setClassType(String classType)
  {
    this.classType = classType;
  }

  protected void addOption(SelectableOption option)
  {
    this.options.add(option);
  }

  public String serialize()
  {
    try
    {
      JSONArray array = new JSONArray();

      for (SelectableOption option : this.options)
      {
        array.put(option.serialize());
      }

      JSONObject map = new JSONObject();
      map.put("title", this.label);
      map.put("group", this.group);
      map.put("klass", this.classType);
      map.put("values", array);

      return map.toString();
    }
    catch (JSONException e)
    {
      throw new ConversionExceptionDTO("Error converting instance of [" + this.getClass().getName()
          + "] to JSON.", e);
    }
  }
}
