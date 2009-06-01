package dss.vector.solutions.general;

import com.terraframe.mojo.ProblemExceptionDTO;

import dss.vector.solutions.util.ErrorUtility;

public class MalariaSeasonController extends MalariaSeasonControllerBase implements com.terraframe.mojo.generation.loader.Reloadable
{
  public static final String JSP_DIR = "WEB-INF/dss/vector/solutions/general/MalariaSeason/";
  public static final String LAYOUT = "/layout.jsp";

  private static final long serialVersionUID = 1242259543111L;

  public MalariaSeasonController(javax.servlet.http.HttpServletRequest req, javax.servlet.http.HttpServletResponse resp, java.lang.Boolean isAsynchronous)
  {
    super(req, resp, isAsynchronous, JSP_DIR, LAYOUT);
  }

  public void create(dss.vector.solutions.general.MalariaSeasonDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    try
    {
      dto.apply();
      this.viewAll();
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
  public void failCreate(dss.vector.solutions.general.MalariaSeasonDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    req.setAttribute("item", dto);
    req.setAttribute("page_title", "Create MalariaSeasonController");
    render("createComponent.jsp");
  }
  public void viewAll() throws java.io.IOException, javax.servlet.ServletException
  {
    if (!req.getRequestURI().contains(".viewAll.mojo"))
    {
      String path = req.getRequestURL().toString();
      path = path.replaceFirst("(\\w+)Controller", this.getClass().getSimpleName());
      resp.sendRedirect(path.replaceFirst("\\.[a-zA-Z]+\\.mojo", ".viewAll.mojo"));
      return;
    }
    
    com.terraframe.mojo.constants.ClientRequestIF clientRequest = super.getClientRequest();
    dss.vector.solutions.general.MalariaSeasonQueryDTO query = dss.vector.solutions.general.MalariaSeasonDTO.getAllInstances(clientRequest, null, true, 20, 1);
    req.setAttribute("query", query);
    req.setAttribute("page_title", "View All MalariaSeasonController Objects");
    render("viewAllComponent.jsp");
  }
  public void failViewAll() throws java.io.IOException, javax.servlet.ServletException
  {
    resp.sendError(500);
  }
  public void newInstance() throws java.io.IOException, javax.servlet.ServletException
  {
    com.terraframe.mojo.constants.ClientRequestIF clientRequest = super.getClientRequest();
    dss.vector.solutions.general.MalariaSeasonDTO dto = new dss.vector.solutions.general.MalariaSeasonDTO(clientRequest);
    req.setAttribute("item", dto);
    req.setAttribute("page_title", "Create MalariaSeasonController");
    render("createComponent.jsp");
  }
  public void failNewInstance() throws java.io.IOException, javax.servlet.ServletException
  {
    this.viewAll();
  }
  public void edit(java.lang.String id) throws java.io.IOException, javax.servlet.ServletException
  {
    dss.vector.solutions.general.MalariaSeasonDTO dto = dss.vector.solutions.general.MalariaSeasonDTO.lock(super.getClientRequest(), id);
    req.setAttribute("item", dto);
    req.setAttribute("page_title", "Edit MalariaSeasonController");
    render("editComponent.jsp");
  }
  public void failEdit(java.lang.String id) throws java.io.IOException, javax.servlet.ServletException
  {
    this.view(id);
  }
  public void delete(dss.vector.solutions.general.MalariaSeasonDTO dto) throws java.io.IOException, javax.servlet.ServletException
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
  public void failDelete(dss.vector.solutions.general.MalariaSeasonDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    req.setAttribute("item", dto);
    req.setAttribute("page_title", "Edit MalariaSeasonController");
    render("editComponent.jsp");
  }
  public void cancel(dss.vector.solutions.general.MalariaSeasonDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    dto.unlock();
    this.viewAll();
  }
  public void failCancel(dss.vector.solutions.general.MalariaSeasonDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    this.edit(dto.getId());
  }
  public void update(dss.vector.solutions.general.MalariaSeasonDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    try
    {
      dto.apply();
      this.viewAll();
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
  public void failUpdate(dss.vector.solutions.general.MalariaSeasonDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    req.setAttribute("item", dto);
    req.setAttribute("page_title", "Update MalariaSeasonController");
    render("editComponent.jsp");
  }
  public void viewPage(java.lang.String sortAttribute, java.lang.Boolean isAscending, java.lang.Integer pageSize, java.lang.Integer pageNumber) throws java.io.IOException, javax.servlet.ServletException
  {
    com.terraframe.mojo.constants.ClientRequestIF clientRequest = super.getClientRequest();
    dss.vector.solutions.general.MalariaSeasonQueryDTO query = dss.vector.solutions.general.MalariaSeasonDTO.getAllInstances(clientRequest, sortAttribute, isAscending, pageSize, pageNumber);
    req.setAttribute("query", query);
    req.setAttribute("page_title", "View All MalariaSeasonController Objects");
    render("viewAllComponent.jsp");
  }
  public void failViewPage(java.lang.String sortAttribute, java.lang.String isAscending, java.lang.String pageSize, java.lang.String pageNumber) throws java.io.IOException, javax.servlet.ServletException
  {
    resp.sendError(500);
  }
  public void view(java.lang.String id) throws java.io.IOException, javax.servlet.ServletException
  {
    if (!req.getRequestURI().contains(".view.mojo"))
    {
      String path = req.getRequestURL().toString();
      path = path.replaceFirst("(\\w+)Controller", this.getClass().getSimpleName());
      resp.sendRedirect(path.replaceFirst("\\.[a-zA-Z]+\\.mojo", ".view.mojo") + "?id=" + id);
      return;
    }
    
    com.terraframe.mojo.constants.ClientRequestIF clientRequest = super.getClientRequest();
    req.setAttribute("item", dss.vector.solutions.general.MalariaSeasonDTO.get(clientRequest, id));
    req.setAttribute("page_title", "View MalariaSeasonController");
    render("viewComponent.jsp");
  }
  public void failView(java.lang.String id) throws java.io.IOException, javax.servlet.ServletException
  {
    this.viewAll();
  }
}
