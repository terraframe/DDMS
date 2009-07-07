package dss.vector.solutions.intervention.monitor;

import java.io.IOException;
import java.util.Arrays;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.terraframe.mojo.ProblemExceptionDTO;
import com.terraframe.mojo.constants.ClientRequestIF;

import dss.vector.solutions.util.ErrorUtility;

public class RoofController extends RoofControllerBase implements
    com.terraframe.mojo.generation.loader.Reloadable
{
  public static final String JSP_DIR          = "WEB-INF/dss/vector/solutions/intervention/monitor/Roof/";

  public static final String LAYOUT           = "/layout.jsp";

  private static final long  serialVersionUID = 1244156203949L;

  public RoofController(HttpServletRequest req, HttpServletResponse resp, Boolean isAsynchronous)
  {
    super(req, resp, isAsynchronous, JSP_DIR, LAYOUT);
  }

  public void viewAll() throws IOException, ServletException
  {
    if (!req.getRequestURI().contains(".viewAll.mojo"))
    {
      String path = req.getRequestURL().toString();
      path = path.replaceFirst("(\\w+)Controller", this.getClass().getSimpleName());
      resp.sendRedirect(path.replaceFirst("\\.[a-zA-Z]+\\.mojo", ".viewAll.mojo"));
      return;
    }

    ClientRequestIF clientRequest = super.getClientRequest();
    RoofQueryDTO query = RoofDTO.getAllInstances(clientRequest, null, true, 20, 1);
    req.setAttribute("query", query);
    render("viewAllComponent.jsp");
  }

  public void viewPage(String sortAttribute, Boolean isAscending, Integer pageSize, Integer pageNumber)
      throws IOException, ServletException
  {
    ClientRequestIF clientRequest = super.getClientRequest();
    RoofQueryDTO query = RoofDTO.getAllInstances(clientRequest, sortAttribute, isAscending, pageSize,
        pageNumber);
    req.setAttribute("query", query);
    render("viewAllComponent.jsp");
  }

  public void failViewPage(String sortAttribute, String isAscending, String pageSize, String pageNumber)
      throws IOException, ServletException
  {
    resp.sendError(500);
  }

  public void edit(String id) throws IOException, ServletException
  {
    RoofDTO dto = RoofDTO.lock(super.getClientRequest(), id);
    this.setupRequest();
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
      this.view(dto);
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

  public void failCreate(RoofDTO dto) throws IOException, ServletException
  {
    this.setupRequest();
    req.setAttribute("item", dto);
    render("createComponent.jsp");
  }

  public void update(RoofDTO dto) throws IOException, ServletException
  {
    try
    {
      dto.apply();
      this.view(dto);
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

  public void failUpdate(RoofDTO dto) throws IOException, ServletException
  {
    this.setupRequest();
    req.setAttribute("item", dto);
    render("editComponent.jsp");
  }

  public void cancel(RoofDTO dto) throws IOException, ServletException
  {
    dto.unlock();
    this.view(dto);
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

  public void failDelete(RoofDTO dto) throws IOException, ServletException
  {
    this.setupRequest();
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
    this.view(RoofDTO.get(clientRequest, id));
  }

  public void view(RoofDTO dto) throws IOException, ServletException
  {
    if (!req.getRequestURI().contains(this.getClass().getName() + ".view.mojo"))
    {
      String path = req.getRequestURL().toString();
      path = path.replaceFirst(req.getServletPath(), "/" + this.getClass().getName() + ".view.mojo");
      path = path.replaceFirst("mojo\\?*.*", "mojo" + "?id=" + dto.getId());

      resp.sendRedirect(path);
      return;
    }

    req.setAttribute("item", dto);
    render("viewComponent.jsp");
  }

  public void failView(String id) throws IOException, ServletException
  {
    this.viewAll();
  }

  public void newInstance() throws IOException, ServletException
  {
    this.setupRequest();
    req.setAttribute("item", new RoofDTO(super.getClientRequest()));
    render("createComponent.jsp");
  }

  public void failNewInstance() throws IOException, ServletException
  {
    this.viewAll();
  }

  private void setupRequest()
  {
    ClientRequestIF request = super.getClientSession().getRequest();

    req.setAttribute("roofs", Arrays.asList(RoofDTO.getAll(request)));
  }
}
