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
package dss.vector.solutions.etl.dhis2.exporter;

import org.json.JSONException;
import org.json.JSONObject;

import com.runwaysdk.generation.loader.Reloadable;

/**
 * Represents a piece of DHIS2 metadata. Ideally should be the base class of all operations dealing with DHIS2 metadata.
 * 
 * @author rrowlands
 *
 */
public class MetadataElement implements Reloadable
{
  private static final String append = " ";
  
  private JSONObject json;
  
  public MetadataElement()
  {
    this.json = new JSONObject();
  }
  
  public JSONObject put(String key, Object value) throws JSONException
  {
    return this.json.put(key, value);
  }
  
  public JSONObject setName(String name) throws JSONException
  {
    return this.json.put("name", name + append);
  }
  
  public JSONObject setCode(String code) throws JSONException
  {
    if (code.length() > (50 - append.length()))
    {
      code = code.substring(0, (50 - append.length()));
    }
    
    code = code + append;
    
    return this.json.put("code", code);
  }
  
  public JSONObject setShortName(String shortName) throws JSONException
  {
    if (shortName.length() > (50 - append.length()))
    {
      shortName = shortName.substring(0, (50 - append.length()));
    }
    
    shortName = shortName + append;
    
    return this.json.put("shortName", shortName);
  }
  
  public JSONObject setId(String id) throws JSONException
  {
    return this.json.put("id", id);
  }
  
  public String toString()
  {
    return this.json.toString();
  }
  
  public JSONObject getJSON()
  {
    return this.json;
  }
}
