package dss.vector.solutions.mo;

import com.terraframe.mojo.ProblemExceptionDTO;

import dss.vector.solutions.util.ErrorUtility;
import dss.vector.solutions.util.RedirectUtility;

public class IdentificationMethodController extends IdentificationMethodControllerBase implements
    com.terraframe.mojo.generation.loader.Reloadable
{
  public static final String JSP_DIR          = "/WEB-INF/dss/vector/solutions/mo/IdentificationMethod/";

  public static final String LAYOUT           = "/layout.jsp";

  private static final long  serialVersionUID = 1243484850743L;

  public IdentificationMethodController(javax.servlet.http.HttpServletRequest req,
      javax.servlet.http.HttpServletResponse resp, java.lang.Boolean isAsynchronous)
  {
    super(req, resp, isAsynchronous, JSP_DIR, LAYOUT);
  }

  public void view(java.lang.String id) throws java.io.IOException, javax.servlet.ServletException
  {
    RedirectUtility utility = new RedirectUtility(req, resp);
    utility.put("id", id);
    utility.checkURL(this.getClass().getSimpleName(), "view");

    com.terraframe.mojo.constants.ClientRequestIF clientRequest = super.getClientRequest();
    req.setAttribute("item", dss.vector.solutions.mo.IdentificationMethodDTO.get(clientRequest, id));
    render("viewComponent.jsp");
  }

  public void failView(java.lang.String id) throws java.io.IOException, javax.servlet.ServletException
  {
    this.viewAll();
  }

  public void update(dss.vector.solutions.mo.IdentificationMethodDTO dto) throws java.io.IOException,
      javax.servlet.ServletException
  {
    try
    {
      dto.apply();
      this.view(dto.getId());
    }
    catch (com.terraframe.mojo.ProblemExceptionDTO e)
    {
      this.failUpdate(dto);
    }
  }

  public void failUpdate(dss.vector.solutions.mo.IdentificationMethodDTO dto)
      throws java.io.IOException, javax.servlet.ServletException
  {
    req.setAttribute("item", dto);
    render("editComponent.jsp");
  }

  public void delete(dss.vector.solutions.mo.IdentificationMethodDTO dto) throws java.io.IOException,
      javax.servlet.ServletException
  {
    try
    {
      dto.delete();
      this.viewAll();
    }
    catch (com.terraframe.mojo.ProblemExceptionDTO e)
    {
      this.failDelete(dto);
    }
  }

  public void failDelete(dss.vector.solutions.mo.IdentificationMethodDTO dto)
      throws java.io.IOException, javax.servlet.ServletException
  {
    req.setAttribute("item", dto);
    render("editComponent.jsp");
  }

  public void edit(java.lang.String id) throws java.io.IOException, javax.servlet.ServletException
  {
    try
    {
      dss.vector.solutions.mo.IdentificationMethodDTO dto = dss.vector.solutions.mo.IdentificationMethodDTO
          .lock(super.getClientRequest(), id);
      req.setAttribute("item", dto);
      render("editComponent.jsp");
    }
    catch (ProblemExceptionDTO e)
    {
      ErrorUtility.prepareProblems(e, req);

      this.failEdit(id);
    }
    catch (Throwable t)
    {
      ErrorUtility.prepareThrowable(t, req);

      this.failEdit(id);
    }

  }

  public void failEdit(java.lang.String id) throws java.io.IOException, javax.servlet.ServletException
  {
    this.view(id);
  }

  public void viewPage(java.lang.String sortAttribute, java.lang.Boolean isAscending,
      java.lang.Integer pageSize, java.lang.Integer pageNumber) throws java.io.IOException,
      javax.servlet.ServletException
  {
    com.terraframe.mojo.constants.ClientRequestIF clientRequest = super.getClientRequest();
    dss.vector.solutions.mo.IdentificationMethodQueryDTO query = dss.vector.solutions.mo.IdentificationMethodDTO
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

  public void cancel(dss.vector.solutions.mo.IdentificationMethodDTO dto) throws java.io.IOException,
      javax.servlet.ServletException
  {
    dto.unlock();
    this.view(dto.getId());
  }

  public void failCancel(dss.vector.solutions.mo.IdentificationMethodDTO dto)
      throws java.io.IOException, javax.servlet.ServletException
  {
    this.edit(dto.getId());
  }

  public void create(dss.vector.solutions.mo.IdentificationMethodDTO dto) throws java.io.IOException,
      javax.servlet.ServletException
  {
    try
    {
      dto.apply();
      this.view(dto.getId());
    }
    catch (com.terraframe.mojo.ProblemExceptionDTO e)
    {
      this.failCreate(dto);
    }
  }

  public void failCreate(dss.vector.solutions.mo.IdentificationMethodDTO dto)
      throws java.io.IOException, javax.servlet.ServletException
  {
    req.setAttribute("item", dto);
    render("createComponent.jsp");
  }

  public void newInstance() throws java.io.IOException, javax.servlet.ServletException
  {
    com.terraframe.mojo.constants.ClientRequestIF clientRequest = super.getClientRequest();
    dss.vector.solutions.mo.IdentificationMethodDTO dto = new dss.vector.solutions.mo.IdentificationMethodDTO(
        clientRequest);
    req.setAttribute("item", dto);
    render("createComponent.jsp");
  }

  public void failNewInstance() throws java.io.IOException, javax.servlet.ServletException
  {
    this.viewAll();
  }

  public void viewAll() throws java.io.IOException, javax.servlet.ServletException
  {
    new RedirectUtility(req, resp).checkURL(this.getClass().getSimpleName(), "viewAll");

    com.terraframe.mojo.constants.ClientRequestIF clientRequest = super.getClientRequest();
    dss.vector.solutions.mo.IdentificationMethodQueryDTO query = dss.vector.solutions.mo.IdentificationMethodDTO
        .getAllInstances(clientRequest, null, true, 20, 1);
    req.setAttribute("query", query);
    render("viewAllComponent.jsp");
  }

  public void failViewAll() throws java.io.IOException, javax.servlet.ServletException
  {
    resp.sendError(500);
  }
}
