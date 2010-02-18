package dss.vector.solutions.ontology;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.terraframe.mojo.ProblemExceptionDTO;
import com.terraframe.mojo.constants.ClientRequestIF;
import com.terraframe.mojo.generation.loader.Reloadable;
import com.terraframe.mojo.web.json.JSONMojoExceptionDTO;

import dss.vector.solutions.util.ErrorUtility;
import dss.vector.solutions.util.RedirectUtility;

public class BrowserRootController extends BrowserRootControllerBase implements Reloadable
{
  public static final String JSP_DIR          = "WEB-INF/dss/vector/solutions/ontology/BrowserRoot/";

  public static final String LAYOUT           = "/layout.jsp";

  private static final long  serialVersionUID = 1252959712968L;

  public BrowserRootController(HttpServletRequest req, HttpServletResponse resp, Boolean isAsynchronous)
  {
    super(req, resp, isAsynchronous, JSP_DIR, LAYOUT);
  }

  public void viewPage(String sortAttribute, Boolean isAscending, Integer pageSize, Integer pageNumber) throws IOException, ServletException
  {
    ClientRequestIF clientRequest = super.getClientRequest();
    BrowserRootQueryDTO query = BrowserRootDTO.getAllInstances(clientRequest, sortAttribute, isAscending, pageSize, pageNumber);
    req.setAttribute("query", query);
    render("viewAllComponent.jsp");
  }

  public void failViewPage(String sortAttribute, String isAscending, String pageSize, String pageNumber) throws IOException, ServletException
  {
    resp.sendError(500);
  }

  public void viewAll() throws IOException, ServletException
  {
    try
    {
      ClientRequestIF clientRequest = super.getClientRequest();
      BrowserRootQueryDTO query = BrowserRootDTO.getAllInstances(clientRequest, null, true, 20, 1);
      req.setAttribute("query", query);
      render("viewAllComponent.jsp");
    }
    catch (ProblemExceptionDTO e)
    {
      ErrorUtility.prepareProblems(e, req);
      this.failViewAll();
    }
    catch (Throwable t)
    {
      ErrorUtility.prepareThrowable(t, req);
      this.failViewAll();
    }
  }

  public void failViewAll() throws IOException, ServletException
  {
    req.getRequestDispatcher("/index.jsp").forward(req, resp);
  }

  public void view(String id) throws IOException, ServletException
  {
    RedirectUtility utility = new RedirectUtility(req, resp);
    utility.put("id", id);
    utility.checkURL(this.getClass().getSimpleName(), "view");
    ClientRequestIF clientRequest = super.getClientRequest();
    req.setAttribute("item", BrowserRootDTO.get(clientRequest, id));
    render("viewComponent.jsp");
  }

  public void failView(String id) throws IOException, ServletException
  {
    this.viewAll();
  }

  public void newInstance() throws IOException, ServletException
  {
    try
    {
      ClientRequestIF clientRequest = super.getClientRequest();
      BrowserRootDTO dto = new BrowserRootDTO(clientRequest);
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

  public void failNewInstance() throws IOException, ServletException
  {
    this.viewAll();
  }

  public void create(BrowserRootDTO dto) throws IOException, ServletException
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

  public void failCreate(BrowserRootDTO dto) throws IOException, ServletException
  {
    req.setAttribute("item", dto);
    render("createComponent.jsp");
  }

  public void edit(String id) throws IOException, ServletException
  {
    try
    {
      BrowserRootDTO dto = BrowserRootDTO.lock(super.getClientRequest(), id);

      req.setAttribute("item", dto);
      req.setAttribute("term", dto.getTerm());

      render("editComponent.jsp");
    }
    catch (Throwable t)
    {
      JSONMojoExceptionDTO ex = new JSONMojoExceptionDTO(t);
      this.resp.setStatus(500);
      this.resp.getWriter().write(ex.getJSON());
    }
  }

  public void failEdit(String id) throws IOException, ServletException
  {
    this.view(id);
  }

  public void update(BrowserRootDTO dto) throws IOException, ServletException
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

  public void failUpdate(BrowserRootDTO dto) throws IOException, ServletException
  {
    req.setAttribute("item", dto);
    render("editComponent.jsp");
  }

  public void cancel(BrowserRootDTO dto) throws IOException, ServletException
  {
    dto.unlock();
    this.view(dto.getId());
  }

  public void failCancel(BrowserRootDTO dto) throws IOException, ServletException
  {
    this.edit(dto.getId());
  }

  public void delete(BrowserRootDTO dto) throws IOException, ServletException
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

  public void failDelete(BrowserRootDTO dto) throws IOException, ServletException
  {
    req.setAttribute("item", dto);
    render("editComponent.jsp");
  }
}
