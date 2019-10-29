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
package dss.vector.solutions.basemap;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class OfflineBasemapController extends OfflineBasemapControllerBase implements com.runwaysdk.generation.loader.Reloadable
{
  public static final String JSP_DIR = "WEB-INF/dss/vector/solutions/basemap/OfflineBasemapManagement/";
  public static final String LAYOUT = "/layout.jsp";
	  
  public OfflineBasemapController(javax.servlet.http.HttpServletRequest req, javax.servlet.http.HttpServletResponse resp, java.lang.Boolean isAsynchronous)
  {
    super(req, resp, isAsynchronous, JSP_DIR, LAYOUT);
  }
  
  @Override
  public void viewAll() throws IOException, ServletException {
    try {
      Boolean isRunning = OfflineBasemapJobDTO.isBasemapJobRunning(getClientRequest());
      req.setAttribute("isRunning", isRunning);
      
      Map<String, Boolean> flatFilesOnDisk = jsonToMap(new JSONObject(OfflineBasemapManagementDTO.getFlatFilesOnDisk(this.getClientRequest())));
      
      req.setAttribute("availableFiles", flatFilesOnDisk);
      req.setAttribute("uploadStatus", false);
      
      render("viewAllComponent.jsp");
    } catch (JSONException e) {
      throw new RuntimeException(e);
    }
  }
  
  @Override
  public void submit() throws IOException, ServletException {
  	ArrayList<String> config = new ArrayList<String>();
  	Enumeration<String> inputFields = this.getRequest().getParameterNames();
  	while(inputFields.hasMoreElements())
  	{
  		String inputField = inputFields.nextElement();
  		String checkedFile = this.getRequest().getParameter(inputField);
  		config.add(checkedFile);
  	}
	
  	if(config != null)
  	{
      OfflineBasemapManagementDTO.importBasemapFiles(this.getClientRequest(), config.toArray(new String[config.size()]));
  	}
	
//  	try
//  	{
//  	  Map<String, Boolean> flatFilesOnDisk = jsonToMap(new JSONObject(OfflineBasemapManagementDTO.getFlatFilesOnDisk(this.getClientRequest())));
  	
//      req.setAttribute("availableFiles", flatFilesOnDisk);
//      req.setAttribute("uploadStatus", true);
  	
//  	  render("viewAllComponent.jsp");
  	  
  	  req.setAttribute("SchedulerPage", "history");
  	  this.renderExternal("viewAllComponent.jsp", "/WEB-INF/dss/vector/solutions/report/ReportJob/");
//  	}
//  	catch (JSONException e)
//  	{
//  	  throw new RuntimeException(e);
//  	}
  }

  protected void renderExternal(String jsp, String dir) throws java.io.IOException, javax.servlet.ServletException
  {
    if(!resp.isCommitted())
    {
      if(this.isAsynchronous())
      {
        req.getRequestDispatcher(dir+jsp).forward(req, resp);
      }
      else
      {
        req.setAttribute(com.runwaysdk.controller.JSPFetcher.INNER_JSP, dir+jsp);
        req.getRequestDispatcher(layout).forward(req, resp);
      }
    }
  }
  
  public void failViewAll() throws java.io.IOException, javax.servlet.ServletException
  {
    resp.sendError(500);
  }
  
  public static Map<String, Boolean> jsonToMap(JSONObject json) {
    Map<String, Boolean> retMap = new HashMap<String, Boolean>();

    if(json != JSONObject.NULL) {
        retMap = toMap(json);
    }
    return retMap;
  }

  public static Map<String, Boolean> toMap(JSONObject object) {
    try
    {
      Map<String, Boolean> map = new HashMap<String, Boolean>();
  
      Iterator<String> keysItr = object.keys();
      while(keysItr.hasNext()) {
          String key = keysItr.next();
          Boolean value = object.getBoolean(key);
  
          map.put(key, value);
      }
      return map;
    }
    catch (JSONException e)
    {
      throw new RuntimeException(e);
    }
  }
  
  public static List<Object> toList(JSONArray array) throws JSONException {
      List<Object> list = new ArrayList<Object>();
      for(int i = 0; i < array.length(); i++) {
          Object value = array.get(i);
          if(value instanceof JSONArray) {
              value = toList((JSONArray) value);
          }
  
          else if(value instanceof JSONObject) {
              value = toMap((JSONObject) value);
          }
          list.add(value);
      }
      return list;
  }
}
