package dss.vector.solutions.intervention.monitor;

import com.terraframe.mojo.ProblemExceptionDTO;

import dss.vector.solutions.util.ErrorUtility;

public class DoseGridController extends DoseGridControllerBase implements com.terraframe.mojo.generation.loader.Reloadable
{
  public static final String JSP_DIR = "WEB-INF/dss/vector/solutions/intervention/monitor/DoseGrid/";
  public static final String LAYOUT = "/layout.jsp";
  
  private static final long serialVersionUID = 1244737056905L;
  
  public DoseGridController(javax.servlet.http.HttpServletRequest req, javax.servlet.http.HttpServletResponse resp, java.lang.Boolean isAsynchronous)
  {
    super(req, resp, isAsynchronous, JSP_DIR, LAYOUT);
  }
  
  public void create(dss.vector.solutions.intervention.monitor.DoseGridDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    try
    {
      dto.apply();
      this.view(dto.getId());
    }
    catch (ProblemExceptionDTO e)
    {
      ErrorUtility.prepareProblems(e, req);

      this.failCreate(dto);
    }
    catch (Throwable t)
    {
      ErrorUtility.prepareThrowable(t, req);

      this.failCreate(dto);
    }
  }
  public void failCreate(dss.vector.solutions.intervention.monitor.DoseGridDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    req.setAttribute("item", dto);
    render("createComponent.jsp");
  }
  public void viewPage(java.lang.String sortAttribute, java.lang.Boolean isAscending, java.lang.Integer pageSize, java.lang.Integer pageNumber) throws java.io.IOException, javax.servlet.ServletException
  {
    com.terraframe.mojo.constants.ClientRequestIF clientRequest = super.getClientRequest();
    dss.vector.solutions.intervention.monitor.DoseGridQueryDTO query = dss.vector.solutions.intervention.monitor.DoseGridDTO.getAllInstances(clientRequest, sortAttribute, isAscending, pageSize, pageNumber);
    req.setAttribute("query", query);
    render("viewAllComponent.jsp");
  }
  public void failViewPage(java.lang.String sortAttribute, java.lang.String isAscending, java.lang.String pageSize, java.lang.String pageNumber) throws java.io.IOException, javax.servlet.ServletException
  {
    resp.sendError(500);
  }
  public void cancel(dss.vector.solutions.intervention.monitor.DoseGridDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    dto.unlock();
    this.view(dto.getId());
  }
  public void failCancel(dss.vector.solutions.intervention.monitor.DoseGridDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    this.edit(dto.getId());
  }
  public void edit(java.lang.String id) throws java.io.IOException, javax.servlet.ServletException
  {
    dss.vector.solutions.intervention.monitor.DoseGridDTO dto = dss.vector.solutions.intervention.monitor.DoseGridDTO.lock(super.getClientRequest(), id);
    req.setAttribute("item", dto);
    render("editComponent.jsp");
  }
  public void failEdit(java.lang.String id) throws java.io.IOException, javax.servlet.ServletException
  {
    this.view(id);
  }
  public void view(java.lang.String id) throws java.io.IOException, javax.servlet.ServletException
  {
	    if (!req.getRequestURI().contains(this.getClass().getName() + ".view.mojo"))
	    {
	      String path = req.getRequestURL().toString();
	      path = path.replaceFirst(req.getServletPath(), "/" + this.getClass().getName() + ".view.mojo");
	      path = path.replaceFirst("mojo\\?*.*", "mojo" + "?id=" + id);

	      resp.sendRedirect(path);
	      return;
	    }
    com.terraframe.mojo.constants.ClientRequestIF clientRequest = super.getClientRequest();
    req.setAttribute("item", dss.vector.solutions.intervention.monitor.DoseGridDTO.get(clientRequest, id));
    render("viewComponent.jsp");
  }
  public void failView(java.lang.String id) throws java.io.IOException, javax.servlet.ServletException
  {
    this.viewAll();
  }
  public void newInstance() throws java.io.IOException, javax.servlet.ServletException
  {
    com.terraframe.mojo.constants.ClientRequestIF clientRequest = super.getClientRequest();
    dss.vector.solutions.intervention.monitor.DoseGridDTO dto = new dss.vector.solutions.intervention.monitor.DoseGridDTO(clientRequest);
    req.setAttribute("item", dto);
    render("createComponent.jsp");
  }
  public void failNewInstance() throws java.io.IOException, javax.servlet.ServletException
  {
    this.viewAll();
  }
  public void update(dss.vector.solutions.intervention.monitor.DoseGridDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    try
    {
      dto.apply();
      this.view(dto.getId());
    }
    catch (ProblemExceptionDTO e)
    {
      ErrorUtility.prepareProblems(e, req);

      this.failUpdate(dto);
    }
    catch (Throwable t)
    {
      ErrorUtility.prepareThrowable(t, req);

      this.failUpdate(dto);
    }
  }
  public void failUpdate(dss.vector.solutions.intervention.monitor.DoseGridDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    req.setAttribute("item", dto);
    req.setAttribute("page_title", "Update DoseGrid");
    render("editComponent.jsp");
  }
  public void viewAll() throws java.io.IOException, javax.servlet.ServletException
  {
	    if (!req.getRequestURI().contains(this.getClass().getName() + ".viewAll.mojo"))
	    {
	      String path = req.getRequestURL().toString();
	      path = path.replaceFirst(req.getServletPath(), "/" + this.getClass().getName() + ".viewAll.mojo");

	      resp.sendRedirect(path);
	      return;
	    }

    com.terraframe.mojo.constants.ClientRequestIF clientRequest = super.getClientRequest();
    dss.vector.solutions.intervention.monitor.DoseGridQueryDTO query = dss.vector.solutions.intervention.monitor.DoseGridDTO.getAllInstances(clientRequest, null, true, 20, 1);
    req.setAttribute("query", query);
    req.setAttribute("page_title", "View All DoseGrid Objects");
    render("viewAllComponent.jsp");
  }
  public void failViewAll() throws java.io.IOException, javax.servlet.ServletException
  {
    resp.sendError(500);
  }
  public void delete(dss.vector.solutions.intervention.monitor.DoseGridDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    try
    {
      dto.delete();
      this.viewAll();
    }
    catch (ProblemExceptionDTO e)
    {
      ErrorUtility.prepareProblems(e, req);

      this.failDelete(dto);
    }
    catch (Throwable t)
    {
      ErrorUtility.prepareThrowable(t, req);

      this.failDelete(dto);
    }
  }
  public void failDelete(dss.vector.solutions.intervention.monitor.DoseGridDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    req.setAttribute("item", dto);
    req.setAttribute("page_title", "Edit DoseGrid");
    render("editComponent.jsp");
  }
}
