package dss.vector.solutions.basemap;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;

import com.runwaysdk.dataaccess.transaction.Transaction;

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
	File[] flatFilesOnDisk = LocalBasemapBuilder.getBasemapFiles();
	
	OfflineBasemapManagement basemapManager = OfflineBasemapManagement.getByKey("Singledon");
	String persistedBasemaps = basemapManager.getConfig();
	
	// TODO: Resolve difference between the two storage mechanisms. 
  }
  
  @Override
  @Transaction
  public void submit() throws IOException, ServletException {
	OfflineBasemapManagement basemapManager = OfflineBasemapManagement.getByKey("Singledon");
    basemapManager.setConfig("");  // TODO: set the actual value dynamically. 
  }
}
