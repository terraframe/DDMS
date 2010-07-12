package dss.vector.solutions.synchronization;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.runwaysdk.constants.ClientRequestIF;
import com.runwaysdk.generation.loader.Reloadable;

import dss.vector.solutions.util.ErrorUtility;

public class ImportController extends ImportControllerBase implements Reloadable
{
  public static final String JSP_DIR          = "WEB-INF/dss/vector/solutions/synchronization/Import/";

  public static final String LAYOUT           = "/layout.jsp";
  
  private static final long serialVersionUID = -1339908979;
  
  public ImportController(HttpServletRequest req, HttpServletResponse resp, Boolean isAsynchronous)
  {
    super(req, resp, isAsynchronous, JSP_DIR, LAYOUT);
  }
  
  @Override
  public void viewLog() throws IOException, ServletException
  {
    try
    {
      renderViewAll(ImportLogViewDTO.getQuery(getClientRequest(), ImportLogViewDTO.SOURCESITE, true, 20, 1));
    }
    catch(Throwable t)
    {
      boolean redirected = ErrorUtility.prepareThrowable(t, req, resp, this.isAsynchronous());
      
      if(!redirected)
      {
        this.failViewLog();
      }
    }
  }
  
  @Override
  public void failViewLog() throws IOException, ServletException
  {
    req.getRequestDispatcher("/index.jsp").forward(req, resp);
  }
  
  public void viewLogPage(String sortAttribute, Boolean isAscending, Integer pageSize, Integer pageNumber) throws IOException, ServletException
  {
    ClientRequestIF clientRequest = super.getClientRequest();
    ImportLogViewQueryDTO query = ImportLogViewDTO.getQuery(clientRequest, sortAttribute, isAscending, pageSize, pageNumber);
    renderViewAll(query);
  }

  private void renderViewAll(ImportLogViewQueryDTO query) throws IOException, ServletException
  {
    req.setAttribute("query", query);
    render("viewImportLog.jsp");
  }
}
