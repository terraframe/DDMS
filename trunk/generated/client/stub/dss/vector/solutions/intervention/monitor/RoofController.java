package dss.vector.solutions.intervention.monitor;

import java.io.IOException;

import javax.servlet.ServletException;

import com.terraframe.mojo.constants.ClientRequestIF;

public class RoofController extends RoofControllerBase implements com.terraframe.mojo.generation.loader.Reloadable
{
  public static final String JSP_DIR = "WEB-INF/dss/vector/solutions/intervention/monitor/Roof/";
  public static final String LAYOUT = "/layout.jsp";
  
  private static final long serialVersionUID = 1244156203949L;
  
  public RoofController(javax.servlet.http.HttpServletRequest req, javax.servlet.http.HttpServletResponse resp, Boolean isAsynchronous)
  {
    super(req, resp, isAsynchronous, JSP_DIR, LAYOUT);
  }

  public void viewAll() throws IOException, ServletException
  {
    ClientRequestIF clientRequest = super.getClientRequest();
    RoofQueryDTO query = RoofDTO.getAllInstances(clientRequest, null, true, 20, 1);
    req.setAttribute("query", query);
    req.setAttribute("parentRoofs", RoofDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    render("viewAllComponent.jsp");
  }
  
  public void viewPage(String sortAttribute, Boolean isAscending, Integer pageSize, Integer pageNumber) throws IOException, ServletException
  {
    ClientRequestIF clientRequest = super.getClientRequest();
    RoofQueryDTO query = RoofDTO.getAllInstances(clientRequest, sortAttribute, isAscending, pageSize, pageNumber);
    req.setAttribute("query", query);
    render("viewAllComponent.jsp");
  }
  public void failViewPage(String sortAttribute, String isAscending, String pageSize, String pageNumber) throws IOException, ServletException
  {
    resp.sendError(500);
  }
  public void edit(String id) throws IOException, ServletException
  {
    RoofDTO dto = RoofDTO.lock(super.getClientRequest(), id);
    req.setAttribute("item", dto);
    render("editComponent.jsp");
  }
  public void failEdit(String id) throws IOException, ServletException
  {
    this.view(id);
  }
  public void create(RoofDTO dto) throws IOException, ServletException
  {
    try
    {
      dto.apply();
      this.view(dto.getId());
    }
    catch(com.terraframe.mojo.ProblemExceptionDTO e)
    {
      this.failCreate(dto);
    }
  }
  public void failCreate(RoofDTO dto) throws IOException, ServletException
  {
    req.setAttribute("item", dto);
    render("createComponent.jsp");
  }
  public void update(RoofDTO dto) throws IOException, ServletException
  {
    try
    {
      dto.apply();
      this.view(dto.getId());
    }
    catch(com.terraframe.mojo.ProblemExceptionDTO e)
    {
      this.failUpdate(dto);
    }
  }
  public void failUpdate(RoofDTO dto) throws IOException, ServletException
  {
    req.setAttribute("item", dto);
    render("editComponent.jsp");
  }
  public void cancel(RoofDTO dto) throws IOException, ServletException
  {
    dto.unlock();
    this.view(dto.getId());
  }
  public void failCancel(RoofDTO dto) throws IOException, ServletException
  {
    this.edit(dto.getId());
  }
  public void delete(RoofDTO dto) throws IOException, ServletException
  {
    try
    {
      dto.delete();
      this.viewAll();
    }
    catch(com.terraframe.mojo.ProblemExceptionDTO e)
    {
      this.failDelete(dto);
    }
  }
  public void failDelete(RoofDTO dto) throws IOException, ServletException
  {
    req.setAttribute("item", dto);
    render("editComponent.jsp");
  }
  public void failViewAll() throws IOException, ServletException
  {
    resp.sendError(500);
  }
  public void view(String id) throws IOException, ServletException
  {
    ClientRequestIF clientRequest = super.getClientRequest();
    req.setAttribute("item", RoofDTO.get(clientRequest, id));
    render("viewComponent.jsp");
  }
  public void failView(String id) throws IOException, ServletException
  {
    this.viewAll();
  }
  public void newInstance() throws IOException, ServletException
  {
    ClientRequestIF clientRequest = super.getClientRequest();
    RoofDTO dto = new RoofDTO(clientRequest);
    req.setAttribute("item", dto);
    render("createComponent.jsp");
  }
  public void failNewInstance() throws IOException, ServletException
  {
    this.viewAll();
  }
}
