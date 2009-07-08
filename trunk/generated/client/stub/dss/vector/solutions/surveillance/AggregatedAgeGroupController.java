package dss.vector.solutions.surveillance;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.terraframe.mojo.ProblemExceptionDTO;
import com.terraframe.mojo.constants.ClientRequestIF;

import dss.vector.solutions.util.ErrorUtility;

public class AggregatedAgeGroupController extends AggregatedAgeGroupControllerBase implements
    com.terraframe.mojo.generation.loader.Reloadable
{
  public static final String JSP_DIR          = "WEB-INF/dss/vector/solutions/surveillance/AggregatedAgeGroup/";

  public static final String LAYOUT           = "/layout.jsp";

  private static final long  serialVersionUID = 1238693160988L;

  public AggregatedAgeGroupController(HttpServletRequest req, HttpServletResponse resp,
      Boolean isAsynchronous)
  {
    super(req, resp, isAsynchronous, JSP_DIR, LAYOUT);
  }

  public void newInstance() throws IOException, ServletException
  {
    ClientRequestIF clientRequest = super.getClientRequest();
    AggregatedAgeGroupDTO dto = new AggregatedAgeGroupDTO(clientRequest);
    req.setAttribute("item", dto);
    render("createComponent.jsp");
  }

  public void failNewInstance() throws IOException, ServletException
  {
    this.viewAll();
  }

  public void viewPage(String sortAttribute, Boolean isAscending, Integer pageSize, Integer pageNumber)
      throws IOException, ServletException
  {
    ClientRequestIF clientRequest = super.getClientRequest();
    AggregatedAgeGroupQueryDTO query = AggregatedAgeGroupDTO.getAllInstances(clientRequest, sortAttribute, isAscending, pageSize, pageNumber);
    req.setAttribute("query", query);
    render("viewAllComponent.jsp");
  }

  public void failViewPage(String sortAttribute, String isAscending, String pageSize, String pageNumber)
      throws IOException, ServletException
  {
    resp.sendError(500);
  }

  public void cancel(AggregatedAgeGroupDTO dto) throws IOException, ServletException
  {
    dto.unlock();
    this.view(dto.getId());
  }

  public void failCancel(AggregatedAgeGroupDTO dto) throws IOException, ServletException
  {
    this.edit(dto.getId());
  }

  public void update(AggregatedAgeGroupDTO dto) throws IOException, ServletException
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

  public void failUpdate(AggregatedAgeGroupDTO dto) throws IOException, ServletException
  {
    req.setAttribute("item", dto);
    render("updateComponent.jsp");
  }

  public void create(AggregatedAgeGroupDTO dto) throws IOException, ServletException
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

  public void failCreate(AggregatedAgeGroupDTO dto) throws IOException, ServletException
  {
    req.setAttribute("item", dto);
    render("createComponent.jsp");
  }

  public void delete(AggregatedAgeGroupDTO dto) throws IOException, ServletException
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

  public void failDelete(AggregatedAgeGroupDTO dto) throws IOException, ServletException
  {
    req.setAttribute("item", dto);
    render("editComponent.jsp");
  }

  public void edit(String id) throws IOException, ServletException
  {
    AggregatedAgeGroupDTO dto = AggregatedAgeGroupDTO.lock(super.getClientRequest(), id);
    req.setAttribute("item", dto);
    render("editComponent.jsp");
  }

  public void failEdit(String id) throws IOException, ServletException
  {
    this.view(id);
  }

  public void viewAll() throws IOException, ServletException
  {
    ClientRequestIF clientRequest = super.getClientRequest();
    AggregatedAgeGroupQueryDTO query = AggregatedAgeGroupDTO.getAllInstances(clientRequest, null, true, 20, 1);
    req.setAttribute("query", query);
    render("viewAllComponent.jsp");
  }

  public void failViewAll() throws IOException, ServletException
  {
    resp.sendError(500);
  }

  public void view(String id) throws IOException, ServletException
  {
    if (!req.getRequestURI().contains(".view.mojo"))
    {
      String path = req.getRequestURL().toString();
      path = path.replaceFirst("(\\w+)Controller", this.getClass().getSimpleName());
      resp.sendRedirect(path.replaceFirst("\\.[a-zA-Z]+\\.mojo", ".view.mojo") + "?id=" + id);
      return;
    }

    ClientRequestIF clientRequest = super.getClientRequest();
    req.setAttribute("item", AggregatedAgeGroupDTO.get(clientRequest, id));
    render("viewComponent.jsp");
  }

  public void failView(String id) throws IOException, ServletException
  {
    this.viewAll();
  }
}
