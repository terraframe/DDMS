package dss.vector.solutions.ontology;

import com.terraframe.mojo.web.json.JSONMojoExceptionDTO;

public class BrowserRootController extends BrowserRootControllerBase implements
    com.terraframe.mojo.generation.loader.Reloadable
{
  public static final String JSP_DIR          = "WEB-INF/dss/vector/solutions/ontology/BrowserRoot/";

  public static final String LAYOUT           = "/layout.jsp";

  private static final long  serialVersionUID = 1252959712968L;

  public BrowserRootController(javax.servlet.http.HttpServletRequest req,
      javax.servlet.http.HttpServletResponse resp, java.lang.Boolean isAsynchronous)
  {
    super(req, resp, isAsynchronous, JSP_DIR, LAYOUT);
  }
  
  public void viewPage(java.lang.String sortAttribute, java.lang.Boolean isAscending,
      java.lang.Integer pageSize, java.lang.Integer pageNumber) throws java.io.IOException,
      javax.servlet.ServletException
  {
    com.terraframe.mojo.constants.ClientRequestIF clientRequest = super.getClientRequest();
    dss.vector.solutions.ontology.BrowserRootQueryDTO query = dss.vector.solutions.ontology.BrowserRootDTO
        .getAllInstances(clientRequest, sortAttribute, isAscending, pageSize, pageNumber);
    req.setAttribute("query", query);
    render("viewAllComponent.jsp");
  }

  public void failViewPage(java.lang.String sortAttribute, java.lang.String isAscending,
      java.lang.String pageSize, java.lang.String pageNumber) throws java.io.IOException,
      javax.servlet.ServletException
  {
    resp.sendError(500);
  }

  public void viewAll() throws java.io.IOException, javax.servlet.ServletException
  {
    com.terraframe.mojo.constants.ClientRequestIF clientRequest = super.getClientRequest();
    dss.vector.solutions.ontology.BrowserRootQueryDTO query = dss.vector.solutions.ontology.BrowserRootDTO
        .getAllInstances(clientRequest, null, true, 20, 1);
    req.setAttribute("query", query);
    render("viewAllComponent.jsp");
  }

  public void failViewAll() throws java.io.IOException, javax.servlet.ServletException
  {
    resp.sendError(500);
  }

  public void view(java.lang.String id) throws java.io.IOException, javax.servlet.ServletException
  {
    dss.vector.solutions.util.RedirectUtility utility = new dss.vector.solutions.util.RedirectUtility(
        req, resp);
    utility.put("id", id);
    utility.checkURL(this.getClass().getSimpleName(), "view");
    com.terraframe.mojo.constants.ClientRequestIF clientRequest = super.getClientRequest();
    req.setAttribute("item", dss.vector.solutions.ontology.BrowserRootDTO.get(clientRequest, id));
    render("viewComponent.jsp");
  }

  public void failView(java.lang.String id) throws java.io.IOException, javax.servlet.ServletException
  {
    this.viewAll();
  }

  public void newInstance() throws java.io.IOException, javax.servlet.ServletException
  {
    try
    {
      com.terraframe.mojo.constants.ClientRequestIF clientRequest = super.getClientRequest();
      dss.vector.solutions.ontology.BrowserRootDTO dto = new dss.vector.solutions.ontology.BrowserRootDTO(
          clientRequest);
      req.setAttribute("item", dto);
      render("createComponent.jsp");
    }
    catch (Throwable t)
    {
      JSONMojoExceptionDTO ex = new JSONMojoExceptionDTO(t);
      this.resp.setStatus(500);
      this.resp.getWriter().write(ex.getJSON());
    }
  }

  public void failNewInstance() throws java.io.IOException, javax.servlet.ServletException
  {
    this.viewAll();
  }

  public void create(dss.vector.solutions.ontology.BrowserRootDTO dto) throws java.io.IOException,
      javax.servlet.ServletException
  {
    try
    {
      dto.apply();
      this.view(dto.getId());
    }
    catch (com.terraframe.mojo.ProblemExceptionDTO e)
    {
      dss.vector.solutions.util.ErrorUtility.prepareProblems(e, req);
      this.failCreate(dto);
    }
    catch (java.lang.Throwable t)
    {
      dss.vector.solutions.util.ErrorUtility.prepareThrowable(t, req);
      this.failCreate(dto);
    }
  }

  public void failCreate(dss.vector.solutions.ontology.BrowserRootDTO dto) throws java.io.IOException,
      javax.servlet.ServletException
  {
    req.setAttribute("item", dto);
    render("createComponent.jsp");
  }

  public void edit(java.lang.String id) throws java.io.IOException, javax.servlet.ServletException
  {
    try
    {
      dss.vector.solutions.ontology.BrowserRootDTO dto = dss.vector.solutions.ontology.BrowserRootDTO
          .lock(super.getClientRequest(), id);
      req.setAttribute("item", dto);
      
      // get the term name // FIXME use BrowserRootView
      String name = dto.getTerm().getTermName();
      req.setAttribute("termName", name);
      render("editComponent.jsp");
    }
    catch (Throwable t)
    {
      JSONMojoExceptionDTO ex = new JSONMojoExceptionDTO(t);
      this.resp.setStatus(500);
      this.resp.getWriter().write(ex.getJSON());
    }
  }

  public void failEdit(java.lang.String id) throws java.io.IOException, javax.servlet.ServletException
  {
    this.view(id);
  }

  public void update(dss.vector.solutions.ontology.BrowserRootDTO dto) throws java.io.IOException,
      javax.servlet.ServletException
  {
    try
    {
      dto.apply();
      this.view(dto.getId());
    }
    catch (com.terraframe.mojo.ProblemExceptionDTO e)
    {
      dss.vector.solutions.util.ErrorUtility.prepareProblems(e, req);
      this.failUpdate(dto);
    }
    catch (java.lang.Throwable t)
    {
      dss.vector.solutions.util.ErrorUtility.prepareThrowable(t, req);
      this.failUpdate(dto);
    }
  }

  public void failUpdate(dss.vector.solutions.ontology.BrowserRootDTO dto) throws java.io.IOException,
      javax.servlet.ServletException
  {
    req.setAttribute("item", dto);
    render("editComponent.jsp");
  }

  public void cancel(dss.vector.solutions.ontology.BrowserRootDTO dto) throws java.io.IOException,
      javax.servlet.ServletException
  {
    dto.unlock();
    this.view(dto.getId());
  }

  public void failCancel(dss.vector.solutions.ontology.BrowserRootDTO dto) throws java.io.IOException,
      javax.servlet.ServletException
  {
    this.edit(dto.getId());
  }

  public void delete(dss.vector.solutions.ontology.BrowserRootDTO dto) throws java.io.IOException,
      javax.servlet.ServletException
  {
    try
    {
      dto.delete();
      this.viewAll();
    }
    catch (com.terraframe.mojo.ProblemExceptionDTO e)
    {
      dss.vector.solutions.util.ErrorUtility.prepareProblems(e, req);
      this.failDelete(dto);
    }
    catch (java.lang.Throwable t)
    {
      dss.vector.solutions.util.ErrorUtility.prepareThrowable(t, req);
      this.failDelete(dto);
    }
  }

  public void failDelete(dss.vector.solutions.ontology.BrowserRootDTO dto) throws java.io.IOException,
      javax.servlet.ServletException
  {
    req.setAttribute("item", dto);
    render("editComponent.jsp");
  }
}
