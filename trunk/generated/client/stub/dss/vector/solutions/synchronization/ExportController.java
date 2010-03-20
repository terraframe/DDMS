package dss.vector.solutions.synchronization;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.runwaysdk.constants.ClientRequestIF;
import com.runwaysdk.generation.loader.Reloadable;

public class ExportController extends ExportControllerBase implements Reloadable
{
  public static final String JSP_DIR          = "WEB-INF/dss/vector/solutions/synchronization/Export/";

  public static final String LAYOUT           = "/layout.jsp";
  
  private static final long serialVersionUID = 702644845;
  
  public ExportController(HttpServletRequest req, HttpServletResponse resp, Boolean isAsynchronous)
  {
    super(req, resp, isAsynchronous, JSP_DIR, LAYOUT);
  }
  
  private void renderViewAll(SynchronizedTypeViewQueryDTO query) throws IOException, ServletException
  {
    req.setAttribute("query", query);
    render("viewAllComponent.jsp");
  }
  
  private void renderSearch(SynchronizedTypeViewQueryDTO results) throws IOException, ServletException
  {
    req.setAttribute("results", results);
    render("search.jsp");
  }
  
  private void renderConfirm(SynchronizedTypeViewDTO[] views) throws IOException, ServletException
  {
    List<SynchronizedTypeViewDTO> asList = Arrays.asList(views);
    req.setAttribute("views", asList);
    render("confirm.jsp");
  }
  
  private void renderSuccess() throws IOException, ServletException
  {
    render("success.jsp");
  }
  
  public void search(String query) throws IOException, ServletException
  {
    SynchronizedTypeViewQueryDTO results = SynchronizedTypeViewDTO.search(getClientRequest(), query);
    renderSearch(results);
  }
  
  public void viewAll() throws IOException, ServletException
  {
    ClientRequestIF clientRequest = super.getClientRequest();
    SynchronizedTypeViewQueryDTO query = SynchronizedTypeViewDTO.getQuery(clientRequest, SynchronizedTypeViewDTO.QUALIFIEDTYPE, true, 20, 1);
    renderViewAll(query);
  }
  
  public void failViewAll() throws IOException, ServletException
  {
    resp.sendError(500);
  }
  
  public void viewPage(String sortAttribute, Boolean isAscending, Integer pageSize, Integer pageNumber) throws IOException, ServletException
  {
    ClientRequestIF clientRequest = super.getClientRequest();
    SynchronizedTypeViewQueryDTO query = SynchronizedTypeViewDTO.getQuery(clientRequest, sortAttribute, isAscending, pageSize, pageNumber);
    renderViewAll(query);
  }
  
  public void failViewPage(String sortAttribute, String isAscending, String pageSize, String pageNumber) throws IOException, ServletException
  {
    resp.sendError(500);
  }
  
  public void checkDependencies(String[] types) throws IOException, ServletException
  {
    renderConfirm(SynchronizedTypeViewDTO.getDependencies(getClientRequest(), types));
  }
  
  @Override
  public void failCheckDependencies(String[] types) throws IOException, ServletException
  {
    viewAll();
  }
  
  @Override
  public void confirm(SynchronizedTypeViewDTO[] types) throws IOException, ServletException
  {
    SynchronizedTypeViewDTO.confirmAll(getClientRequest(), types);
    renderSuccess();
  }
}
