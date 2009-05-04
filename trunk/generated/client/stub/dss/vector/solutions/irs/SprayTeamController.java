package dss.vector.solutions.irs;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.terraframe.mojo.constants.ClientRequestIF;

public class SprayTeamController extends SprayTeamControllerBase implements com.terraframe.mojo.generation.loader.Reloadable
{
  public static final String JSP_DIR = "WEB-INF/dss/vector/solutions/irs/SprayTeam/";
  public static final String LAYOUT = JSP_DIR + "layout.jsp";
  
  private static final long serialVersionUID = 1241327457793L;
  
  public SprayTeamController(HttpServletRequest req, HttpServletResponse resp, Boolean isAsynchronous)
  {
    super(req, resp, isAsynchronous, JSP_DIR, LAYOUT);
  }
  
  public void newInstance() throws IOException, ServletException
  {
    ClientRequestIF clientRequest = super.getClientRequest();
    SprayTeamDTO dto = new SprayTeamDTO(clientRequest);
    req.setAttribute("item", dto);
    req.setAttribute("page_title", "Create SprayTeamController");
    req.setAttribute("leaders", SprayLeaderDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("operators", SprayOperatorDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    render("createComponent.jsp");
  }
  
  @Override
  public void createAndAssign(SprayTeamDTO team, String leaderId, String operatorId) throws IOException, ServletException
  {
    team.apply();
    SprayLeaderDTO.addLeadsTeam(super.getClientRequest(), leaderId, team).apply();
    SprayOperatorDTO.addSprayTeam(super.getClientRequest(), operatorId, team).apply();
    renderView(team);
  }

  private void renderView(SprayTeamDTO sprayTeamDTO) throws IOException, ServletException
  {
    req.setAttribute("item", sprayTeamDTO);
    req.setAttribute("page_title", "View SprayTeamController");
    render("viewComponent.jsp");
  }
  
  public void create(SprayTeamDTO dto) throws IOException, ServletException
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
  public void failCreate(SprayTeamDTO dto) throws IOException, ServletException
  {
    req.setAttribute("item", dto);
    req.setAttribute("page_title", "Create SprayTeamController");
    render("createComponent.jsp");
  }
  public void update(SprayTeamDTO dto) throws IOException, ServletException
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
  public void failUpdate(SprayTeamDTO dto) throws IOException, ServletException
  {
    req.setAttribute("item", dto);
    req.setAttribute("page_title", "Update SprayTeamController");
    render("editComponent.jsp");
  }
  public void viewPage(String sortAttribute, Boolean isAscending, Integer pageSize, Integer pageNumber) throws IOException, ServletException
  {
    ClientRequestIF clientRequest = super.getClientRequest();
    SprayTeamQueryDTO query = SprayTeamDTO.getAllInstances(clientRequest, sortAttribute, isAscending, pageSize, pageNumber);
    req.setAttribute("query", query);
    req.setAttribute("page_title", "View All SprayTeamController Objects");
    render("viewAllComponent.jsp");
  }
  public void failViewPage(String sortAttribute, String isAscending, String pageSize, String pageNumber) throws IOException, ServletException
  {
    resp.sendError(500);
  }
  public void viewAll() throws IOException, ServletException
  {
    ClientRequestIF clientRequest = super.getClientRequest();
    SprayTeamQueryDTO query = SprayTeamDTO.getAllInstances(clientRequest, null, true, 20, 1);
    req.setAttribute("query", query);
    req.setAttribute("page_title", "View All SprayTeamController Objects");
    render("viewAllComponent.jsp");
  }
  public void failViewAll() throws IOException, ServletException
  {
    resp.sendError(500);
  }
  public void edit(String id) throws IOException, ServletException
  {
    SprayTeamDTO dto = SprayTeamDTO.lock(super.getClientRequest(), id);
    req.setAttribute("item", dto);
    req.setAttribute("page_title", "Edit SprayTeamController");
    render("editComponent.jsp");
  }
  public void failEdit(String id) throws IOException, ServletException
  {
    this.view(id);
  }
  public void view(String id) throws IOException, ServletException
  {
    ClientRequestIF clientRequest = super.getClientRequest();
    SprayTeamDTO sprayTeamDTO = SprayTeamDTO.get(clientRequest, id);
    renderView(sprayTeamDTO);
  }
  public void failView(String id) throws IOException, ServletException
  {
    this.viewAll();
  }
  public void cancel(SprayTeamDTO dto) throws IOException, ServletException
  {
    dto.unlock();
    this.view(dto.getId());
  }
  public void failCancel(SprayTeamDTO dto) throws IOException, ServletException
  {
    resp.sendError(500);
  }
  public void failNewInstance() throws IOException, ServletException
  {
    this.viewAll();
  }
  public void delete(SprayTeamDTO dto) throws IOException, ServletException
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
  public void failDelete(SprayTeamDTO dto) throws IOException, ServletException
  {
    req.setAttribute("item", dto);
    req.setAttribute("page_title", "Edit SprayTeamController");
    render("editComponent.jsp");
  }
}
