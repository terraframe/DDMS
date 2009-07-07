package dss.vector.solutions.intervention.monitor;

import java.io.IOException;
import java.util.Arrays;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.terraframe.mojo.ProblemExceptionDTO;
import com.terraframe.mojo.constants.ClientRequestIF;
import com.terraframe.mojo.generation.loader.Reloadable;

import dss.vector.solutions.util.ErrorUtility;

public class WallController extends WallControllerBase implements Reloadable
{
  public static final String JSP_DIR          = "WEB-INF/dss/vector/solutions/intervention/monitor/Wall/";

  public static final String LAYOUT           = "/layout.jsp";

  private static final long  serialVersionUID = 1244156182552L;

  public WallController(HttpServletRequest req, HttpServletResponse resp, Boolean isAsynchronous)
  {
    super(req, resp, isAsynchronous, JSP_DIR, LAYOUT);
  }

  public void newInstance() throws IOException, ServletException
  {
    ClientRequestIF clientRequest = super.getClientRequest();
    WallDTO dto = new WallDTO(clientRequest);
    this.setupRequest();
    req.setAttribute("item", dto);

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

  public void failCreate(WallDTO dto) throws IOException, ServletException
  {
    this.setupRequest();
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

  public void failUpdate(WallDTO dto) throws IOException, ServletException
  {
    this.setupRequest();
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

  public void failDelete(WallDTO dto) throws IOException, ServletException
  {
    this.setupRequest();
    req.setAttribute("item", dto);
    render("editComponent.jsp");
  }

  public void edit(String id) throws IOException, ServletException
  {
    WallDTO dto = WallDTO.lock(super.getClientRequest(), id);
    this.setupRequest();
    req.setAttribute("item", dto);
    render("editComponent.jsp");
  }

  public void failEdit(String id) throws IOException, ServletException
  {
    this.view(id);
  }

  public void view(String id) throws IOException, ServletException
  {
    this.view(WallDTO.get(super.getClientRequest(), id));
  }

  public void view(WallDTO dto) throws IOException, ServletException
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

  public void failNewInstance() throws IOException, ServletException
  {
    this.viewAll();
  }

  public void viewPage(String sortAttribute, Boolean isAscending, Integer pageSize, Integer pageNumber)
      throws IOException, ServletException
  {
    ClientRequestIF clientRequest = super.getClientRequest();
    WallQueryDTO query = WallDTO.getAllInstances(clientRequest, sortAttribute, isAscending, pageSize,
        pageNumber);
    req.setAttribute("query", query);
    render("viewAllComponent.jsp");
  }

  public void failViewPage(String sortAttribute, String isAscending, String pageSize, String pageNumber)
      throws IOException, ServletException
  {
    resp.sendError(500);
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
    WallQueryDTO query = WallDTO.getAllInstances(clientRequest, null, true, 20, 1);
    req.setAttribute("query", query);
    render("viewAllComponent.jsp");
  }

  public void failViewAll() throws IOException, ServletException
  {
    resp.sendError(500);
  }

  private void setupRequest()
  {
    ClientRequestIF request = super.getClientSession().getRequest();

    req.setAttribute("walls", Arrays.asList(WallDTO.getAll(request)));
  }
}
