package dss.vector.solutions.intervention.monitor;

import java.io.IOException;

import com.terraframe.mojo.ProblemExceptionDTO;
import com.terraframe.mojo.constants.ClientRequestIF;

import dss.vector.solutions.util.ErrorUtility;
import dss.vector.solutions.util.RedirectUtility;

public class DoseGridController extends DoseGridControllerBase implements
    com.terraframe.mojo.generation.loader.Reloadable
{
  public static final String JSP_DIR          = "WEB-INF/dss/vector/solutions/intervention/monitor/DoseGrid/";

  public static final String LAYOUT           = "/layout.jsp";

  private static final long  serialVersionUID = 1244737056905L;

  public DoseGridController(javax.servlet.http.HttpServletRequest req,
      javax.servlet.http.HttpServletResponse resp, java.lang.Boolean isAsynchronous)
  {
    super(req, resp, isAsynchronous, JSP_DIR, LAYOUT);
  }

  public void create(DoseGridDTO dto) throws IOException, javax.servlet.ServletException
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

  public void failCreate(DoseGridDTO dto) throws IOException, javax.servlet.ServletException
  {
    req.setAttribute("item", dto);
    render("createComponent.jsp");
  }

  public void viewPage(java.lang.String sortAttribute, java.lang.Boolean isAscending,
      java.lang.Integer pageSize, java.lang.Integer pageNumber) throws IOException,
      javax.servlet.ServletException
  {
    ClientRequestIF clientRequest = super.getClientRequest();
    DoseGridQueryDTO query = DoseGridDTO.getAllInstances(clientRequest, sortAttribute, isAscending,
        pageSize, pageNumber);
    req.setAttribute("query", query);
    render("viewAllComponent.jsp");
  }

  public void failViewPage(java.lang.String sortAttribute, java.lang.String isAscending,
      java.lang.String pageSize, java.lang.String pageNumber) throws IOException,
      javax.servlet.ServletException
  {
    resp.sendError(500);
  }

  public void cancel(DoseGridDTO dto) throws IOException, javax.servlet.ServletException
  {
    dto.unlock();
    this.view(dto.getId());
  }

  public void failCancel(DoseGridDTO dto) throws IOException, javax.servlet.ServletException
  {
    this.edit(dto.getId());
  }

  public void edit(java.lang.String id) throws IOException, javax.servlet.ServletException
  {
    try
    {
      DoseGridDTO dto = DoseGridDTO.lock(super.getClientRequest(), id);
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

  public void failEdit(java.lang.String id) throws IOException, javax.servlet.ServletException
  {
    this.view(id);
  }

  public void view(java.lang.String id) throws IOException, javax.servlet.ServletException
  {
    RedirectUtility utility = new RedirectUtility(req, resp);
    utility.put("id", id);
    utility.checkURL(this.getClass().getSimpleName(), "view");

    ClientRequestIF clientRequest = super.getClientRequest();
    req.setAttribute("item", DoseGridDTO.get(clientRequest, id));
    render("viewComponent.jsp");
  }

  public void failView(java.lang.String id) throws IOException, javax.servlet.ServletException
  {
    this.viewAll();
  }

  public void newInstance() throws IOException, javax.servlet.ServletException
  {
    ClientRequestIF clientRequest = super.getClientRequest();
    DoseGridDTO dto = new DoseGridDTO(clientRequest);
    req.setAttribute("item", dto);
    render("createComponent.jsp");
  }

  public void failNewInstance() throws IOException, javax.servlet.ServletException
  {
    this.viewAll();
  }

  public void update(DoseGridDTO dto) throws IOException, javax.servlet.ServletException
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

  public void failUpdate(DoseGridDTO dto) throws IOException, javax.servlet.ServletException
  {
    req.setAttribute("item", dto);
    req.setAttribute("page_title", "Update DoseGrid");
    render("editComponent.jsp");
  }

  public void viewAll() throws IOException, javax.servlet.ServletException
  {
    new RedirectUtility(req, resp).checkURL(this.getClass().getSimpleName(), "viewAll");

    ClientRequestIF clientRequest = super.getClientRequest();
    DoseGridQueryDTO query = DoseGridDTO.getAllInstances(clientRequest, null, true, 20, 1);
    req.setAttribute("query", query);
    req.setAttribute("page_title", "View All DoseGrid Objects");
    render("viewAllComponent.jsp");
  }

  public void failViewAll() throws IOException, javax.servlet.ServletException
  {
    resp.sendError(500);
  }

  public void delete(DoseGridDTO dto) throws IOException, javax.servlet.ServletException
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

  public void failDelete(DoseGridDTO dto) throws IOException, javax.servlet.ServletException
  {
    req.setAttribute("item", dto);
    req.setAttribute("page_title", "Edit DoseGrid");
    render("editComponent.jsp");
  }
}
