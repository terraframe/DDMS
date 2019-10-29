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

import org.json.JSONException;
import org.json.JSONObject;

import com.runwaysdk.generation.loader.Reloadable;
import com.runwaysdk.transport.conversion.ConversionExceptionDTO;

public abstract class SelectableOption implements Reloadable
{
  private String attributeName;

  private String displayLabel;

  private String key;

  public SelectableOption(String attributeName, String displayLabel, String key)
  {
    this.attributeName = attributeName;
    this.displayLabel = displayLabel;
    this.key = key;
  }

  protected String getAttributeName()
  {
    return attributeName;
  }

  protected String getDisplayLabel()
  {
    return displayLabel;
  }

  protected String getKey()
  {
    return key;
  }

  protected JSONObject getSerializationMap() throws JSONException
  {
    JSONObject map = new JSONObject();
    
    map.put("attributeName", this.getAttributeName());
    map.put("displayLabel", this.getDisplayLabel());
    map.put("dtoType", this.getDTOType());
    map.put("key",this.getKey());
    map.put("type",this.getType()); 

    return map;
  }

  public final JSONObject serialize()
  {
    try
    {
      return this.getSerializationMap();
    }
    catch (JSONException e)
    {
      throw new ConversionExceptionDTO("Error converting instance of ["+this.getClass().getName()+"] to JSON.", e);
    }
  }

  public abstract String getDTOType();

  public abstract String getType();
}
