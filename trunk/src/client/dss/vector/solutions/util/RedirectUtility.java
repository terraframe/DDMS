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

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.runwaysdk.generation.loader.Reloadable;

public class RedirectUtility implements Reloadable
{
  private HttpServletRequest req;
  private HttpServletResponse resp;
  private Map<String, String> map;
  
  public RedirectUtility(HttpServletRequest req, HttpServletResponse resp)
  {
    this.req = req;
    this.resp = resp;
    this.map = new HashMap<String, String>();
  }
  
  public void put(String key, Object value)
  {
    if(value != null)
    {
      map.put(key, value.toString());
    }
  }

  public boolean checkURL(String controller, String action) throws IOException
  {
    String mojoAction = "." + action + ".mojo";
    
    if (!req.getRequestURI().contains(mojoAction))
    {      
      String path = req.getRequestURL().toString();
      path = path.replaceFirst("(\\w+)Controller", controller);
      path = path.replaceFirst("\\.[a-zA-Z]+\\.mojo", mojoAction);

      this.sendRedirect(path);

      return true;
    }
    
    ErrorUtility.prepareMessages(req);
    
    return false;
  }
  
  public void sendRedirect(String url) throws IOException
  {
    URLUtility utility = new URLUtility(url, map);      
    ErrorUtility.addErrorMessages(req, utility);
    
    if(!resp.isCommitted())
    {
      resp.sendRedirect(utility.getURL());    
    }    
  }
}
