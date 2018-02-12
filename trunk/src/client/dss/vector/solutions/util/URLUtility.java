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
package dss.vector.solutions.util;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.runwaysdk.generation.loader.Reloadable;

public class URLUtility implements Reloadable
{
  private String baseURL;
  
  private Map<String, String> parameters;

  public URLUtility(String baseURL)
  {
    this(baseURL, new HashMap<String, String>());
  }
  
  public URLUtility(String baseURL, Map<String, String> parameters)
  {
    this.baseURL = baseURL;
    this.parameters = parameters;
  }

  public void addParameter(String key, String value)
  {
    parameters.put(key, value);
  }
  
  public String getURL()
  {
    StringBuffer buffer = new StringBuffer(baseURL);
    
    Set<String> keySet = parameters.keySet();

    if(keySet.size() > 0)
    {      
      buffer.append("?");
      
      String[] keys = keySet.toArray(new String[keySet.size()]);
      
      for(int i = 0; i < keys.length; i++)
      {
        if(i != 0)
        {
          buffer.append("&");
        }
        
        buffer.append(keys[i] + "=" + parameters.get(keys[i]));
      }
    }
    
    return buffer.toString();
  }
}
