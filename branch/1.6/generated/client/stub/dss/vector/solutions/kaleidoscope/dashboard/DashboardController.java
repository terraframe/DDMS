package dss.vector.solutions.kaleidoscope.dashboard;

import java.io.IOException;

import javax.servlet.ServletException;

import org.json.JSONObject;

import com.runwaysdk.transport.conversion.json.JSONReturnObject;

import dss.vector.solutions.kaleidoscope.JSONControllerUtil;

public class DashboardController extends DashboardControllerBase implements com.runwaysdk.generation.loader.Reloadable
{
  public static final String JSP_DIR = "/WEB-INF/net/geoprism/dashboard/Dashboard/";

  public static final String LAYOUT  = "WEB-INF/templates/layout.jsp";

  public DashboardController(javax.servlet.http.HttpServletRequest req, javax.servlet.http.HttpServletResponse resp, java.lang.Boolean isAsynchronous)
  {
    super(req, resp, isAsynchronous, JSP_DIR, LAYOUT);
  }

  public void cancel(DashboardDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    if (!dto.isNewInstance())
    {
      dto.unlock();
    }
  }

  public void failCancel(DashboardDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    this.edit(dto.getId());
  }

  public void create(DashboardDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    try
    {
      DashboardDTO applied = DashboardDTO.create(getClientRequest(), dto);

      JSONReturnObject jsonReturn = new JSONReturnObject(applied);
      jsonReturn.setInformation(this.getClientRequest().getInformation());
      jsonReturn.setWarnings(this.getClientRequest().getWarnings());

      this.getResponse().setStatus(200);
      this.getResponse().setContentType("application/json");

      this.getResponse().getWriter().print(jsonReturn.toString());
    }
    catch (com.runwaysdk.ProblemExceptionDTO e)
    {
      this.failCreate(dto);
    }
  }

  public void failCreate(DashboardDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    req.setAttribute("dashboard", dto);
    render("createComponent.jsp");
  }

  public void delete(DashboardDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    try
    {
      dto.delete();
      this.viewAll();
    }
    catch (com.runwaysdk.ProblemExceptionDTO e)
    {
      this.failDelete(dto);
    }
  }

  public void failDelete(DashboardDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    req.setAttribute("item", dto);
    render("editComponent.jsp");
  }

  public void edit(java.lang.String id) throws java.io.IOException, javax.servlet.ServletException
  {
    DashboardDTO dto = DashboardDTO.lock(super.getClientRequest(), id);

    req.setAttribute("dashboard", dto);

    render("editComponent.jsp");
  }

  public void failEdit(java.lang.String id) throws java.io.IOException, javax.servlet.ServletException
  {
    this.view(id);
  }

  public void newInstance() throws java.io.IOException, javax.servlet.ServletException
  {
    com.runwaysdk.constants.ClientRequestIF clientRequest = super.getClientRequest();
    DashboardDTO dto = new DashboardDTO(clientRequest);
    req.setAttribute("dashboard", dto);

    render("createComponent.jsp");
  }

  public void failNewInstance() throws java.io.IOException, javax.servlet.ServletException
  {
    this.viewAll();
  }

  public void update(DashboardDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    try
    {
      dto.apply();
      this.view(dto.getId());
    }
    catch (com.runwaysdk.ProblemExceptionDTO e)
    {
      this.failUpdate(dto);
    }
  }

  public void failUpdate(DashboardDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    req.setAttribute("item", dto);
    render("editComponent.jsp");
  }

  public void view(java.lang.String id) throws java.io.IOException, javax.servlet.ServletException
  {
    com.runwaysdk.constants.ClientRequestIF clientRequest = super.getClientRequest();
    req.setAttribute("item", DashboardDTO.get(clientRequest, id));
    render("viewComponent.jsp");
  }

  public void failView(java.lang.String id) throws java.io.IOException, javax.servlet.ServletException
  {
    this.viewAll();
  }

  public void viewAll() throws java.io.IOException, javax.servlet.ServletException
  {
    com.runwaysdk.constants.ClientRequestIF clientRequest = super.getClientRequest();
    DashboardQueryDTO query = DashboardDTO.getAllInstances(clientRequest, null, true, 20, 1);
    req.setAttribute("query", query);
    render("viewAllComponent.jsp");
  }

  public void failViewAll() throws java.io.IOException, javax.servlet.ServletException
  {
    resp.sendError(500);
  }

  public void viewPage(java.lang.String sortAttribute, java.lang.Boolean isAscending, java.lang.Integer pageSize, java.lang.Integer pageNumber) throws java.io.IOException, javax.servlet.ServletException
  {
    com.runwaysdk.constants.ClientRequestIF clientRequest = super.getClientRequest();
    DashboardQueryDTO query = DashboardDTO.getAllInstances(clientRequest, sortAttribute, isAscending, pageSize, pageNumber);
    req.setAttribute("query", query);
    render("viewAllComponent.jsp");
  }

  public void failViewPage(java.lang.String sortAttribute, java.lang.String isAscending, java.lang.String pageSize, java.lang.String pageNumber) throws java.io.IOException, javax.servlet.ServletException
  {
    resp.sendError(500);
  }

  @Override
  public void newClone(String dashboardId) throws IOException, ServletException
  {
    try
    {
      DashboardDTO dashboard = DashboardDTO.get(this.getClientRequest(), dashboardId);

      JSONObject object = new JSONObject();
      object.put("label", dashboard.getDisplayLabel().getValue());
      object.put("id", dashboard.getId());

      JSONObject response = new JSONObject();
      response.put("dashboard", object);

      JSONControllerUtil.writeReponse(this.resp, response);
    }
    catch (Throwable t)
    {
      JSONControllerUtil.handleException(this.resp, t, this.getClientRequest());
    }
  }

  @Override
  public void clone(String dashboardId, String label) throws IOException, ServletException
  {
    try
    {
      DashboardDTO dashboard = DashboardDTO.clone(this.getClientRequest(), dashboardId, label);

      JSONObject response = new JSONObject(dashboard.getDashboardInformation());
      response.put("id", dashboard.getId());
      response.put("isLastDashboard", true);

      JSONControllerUtil.writeReponse(this.resp, response);
    }
    catch (Throwable t)
    {
      JSONControllerUtil.handleException(this.resp, t, this.getClientRequest());
    }
  }
}
