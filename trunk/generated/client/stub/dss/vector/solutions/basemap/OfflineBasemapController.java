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

import javax.servlet.ServletException;

import dss.vector.solutions.geoserver.LocalBasemapBuilder;

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
    HashMap<String, Boolean> flatFilesOnDisk = LocalBasemapBuilder.getBasemapFilesUploadStatus();
    
    req.setAttribute("availableFiles", flatFilesOnDisk);
    req.setAttribute("uploadStatus", false);
    
    render("viewAllComponent.jsp");
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
	
	HashMap<String, Boolean> flatFilesOnDisk = LocalBasemapBuilder.getBasemapFilesUploadStatus();
	
    req.setAttribute("availableFiles", flatFilesOnDisk);
    req.setAttribute("uploadStatus", true);
	
	render("viewAllComponent.jsp");
  }

  
  public void failViewAll() throws java.io.IOException, javax.servlet.ServletException
  {
    resp.sendError(500);
  }
}
