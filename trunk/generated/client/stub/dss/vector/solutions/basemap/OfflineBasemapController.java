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
