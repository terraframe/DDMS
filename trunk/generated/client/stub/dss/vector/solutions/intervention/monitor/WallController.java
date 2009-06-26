package dss.vector.solutions.intervention.monitor;

import java.io.IOException;

import javax.servlet.ServletException;

import com.terraframe.mojo.constants.ClientRequestIF;

public class WallController extends WallControllerBase implements com.terraframe.mojo.generation.loader.Reloadable
{
  public static final String JSP_DIR = "WEB-INF/dss/vector/solutions/intervention/monitor/Wall/";
  public static final String LAYOUT = "/layout.jsp";
  
  private static final long serialVersionUID = 1244156182552L;
  
  public WallController(javax.servlet.http.HttpServletRequest req, javax.servlet.http.HttpServletResponse resp, Boolean isAsynchronous)
  {
    super(req, resp, isAsynchronous, JSP_DIR, LAYOUT);
  }

  public void newInstance() throws IOException, ServletException
  {
    ClientRequestIF clientRequest = super.getClientRequest();
    WallDTO dto = new WallDTO(clientRequest);
    req.setAttribute("item", dto);
    req.setAttribute("parentWalls", WallDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    render("createComponent.jsp");
  }
  
  public void cancel(WallDTO dto) throws IOException, ServletException
  {
    dto.unlock();
    this.view(dto.getId());
  }
  public void failCancel(WallDTO dto) throws IOException, ServletException
  {
    this.edit(dto.getId());
  }
  public void create(WallDTO dto) throws IOException, ServletException
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
  public void failCreate(WallDTO dto) throws IOException, ServletException
  {
    req.setAttribute("item", dto);
    render("createComponent.jsp");
  }
  public void update(WallDTO dto) throws IOException, ServletException
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
  public void failUpdate(WallDTO dto) throws IOException, ServletException
  {
    req.setAttribute("item", dto);
    render("editComponent.jsp");
  }
  public void delete(WallDTO dto) throws IOException, ServletException
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
  public void failDelete(WallDTO dto) throws IOException, ServletException
  {
    req.setAttribute("item", dto);
    render("editComponent.jsp");
  }
  public void edit(String id) throws IOException, ServletException
  {
    WallDTO dto = WallDTO.lock(super.getClientRequest(), id);
    req.setAttribute("item", dto);
    render("editComponent.jsp");
  }
  public void failEdit(String id) throws IOException, ServletException
  {
    this.view(id);
  }
  public void view(String id) throws IOException, ServletException
  {
    ClientRequestIF clientRequest = super.getClientRequest();
    req.setAttribute("item", WallDTO.get(clientRequest, id));
    render("viewComponent.jsp");
  }
  public void failView(String id) throws IOException, ServletException
  {
    this.viewAll();
  }
  public void failNewInstance() throws IOException, ServletException
  {
    this.viewAll();
  }
  public void viewPage(String sortAttribute, Boolean isAscending, Integer pageSize, Integer pageNumber) throws IOException, ServletException
  {
    ClientRequestIF clientRequest = super.getClientRequest();
    WallQueryDTO query = WallDTO.getAllInstances(clientRequest, sortAttribute, isAscending, pageSize, pageNumber);
    req.setAttribute("query", query);
    render("viewAllComponent.jsp");
  }
  public void failViewPage(String sortAttribute, String isAscending, String pageSize, String pageNumber) throws IOException, ServletException
  {
    resp.sendError(500);
  }
  public void viewAll() throws IOException, ServletException
  {
    ClientRequestIF clientRequest = super.getClientRequest();
    WallQueryDTO query = WallDTO.getAllInstances(clientRequest, null, true, 20, 1);
    req.setAttribute("query", query);
    render("viewAllComponent.jsp");
  }
  public void failViewAll() throws IOException, ServletException
  {
    resp.sendError(500);
  }
}
