package dss.vector.solutions.entomology;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.runwaysdk.constants.ClientRequestIF;
import com.runwaysdk.generation.loader.Reloadable;

import dss.vector.solutions.util.ErrorUtility;
import dss.vector.solutions.util.RedirectUtility;

public class ResistancePropertyController extends ResistancePropertyControllerBase implements Reloadable
{
  public static final String JSP_DIR          = "WEB-INF/dss/vector/solutions/entomology/ResistanceProperty/";

  public static final String LAYOUT           = "/layout.jsp";

  private static final long  serialVersionUID = -689267388;

  public ResistancePropertyController(HttpServletRequest req, HttpServletResponse resp, Boolean isAsynchronous)
  {
    super(req, resp, isAsynchronous, JSP_DIR, LAYOUT);
  }

  public void cancel(ResistancePropertyDTO dto) throws IOException, ServletException
  {
    try
    {
      dto.unlock();

      this.viewAll();
    }
    catch (Throwable t)
    {
      boolean redirected = ErrorUtility.prepareThrowable(t, req, resp, this.isAsynchronous());

      if (!redirected)
      {
        this.failCancel(dto);
      }
    }

  }

  public void failCancel(ResistancePropertyDTO dto) throws IOException, ServletException
  {
    this.edit(dto.getId());
  }

  public void create(ResistancePropertyDTO dto) throws IOException, ServletException
  {
    try
    {
      dto.apply();
      this.view(dto.getId());
    }
    catch (Throwable t)
    {
      boolean redirected = ErrorUtility.prepareThrowable(t, req, resp, this.isAsynchronous());

      if (!redirected)
      {
        this.failCreate(dto);
      }
    }
  }

  public void failCreate(ResistancePropertyDTO dto) throws IOException, ServletException
  {
    req.setAttribute("item", dto);
    render("createComponent.jsp");
  }

  public void delete(ResistancePropertyDTO dto) throws IOException, ServletException
  {
    this.edit(dto.getId());
  }

  public void failDelete(ResistancePropertyDTO dto) throws IOException, ServletException
  {
    req.setAttribute("item", dto);
    render("editComponent.jsp");
  }

  public void edit(String id) throws IOException, ServletException
  {
    try
    {
      ResistancePropertyDTO dto = ResistancePropertyDTO.lock(super.getClientRequest(), id);
      req.setAttribute("item", dto);
      render("editComponent.jsp");
    }
    catch (Throwable t)
    {
      boolean redirected = ErrorUtility.prepareThrowable(t, req, resp, this.isAsynchronous());

      if (!redirected)
      {
        this.failEdit(id);
      }
    }
  }

  public void failEdit(String id) throws IOException, ServletException
  {
    this.viewAll();
  }

  public void newInstance() throws IOException, ServletException
  {
    this.viewAll();
  }

  public void failNewInstance() throws IOException, ServletException
  {
    this.viewAll();
  }

  public void update(ResistancePropertyDTO dto) throws IOException, ServletException
  {
    try
    {
      dto.apply();

      this.viewAll();
    }
    catch (Throwable t)
    {
      boolean redirected = ErrorUtility.prepareThrowable(t, req, resp, this.isAsynchronous());

      if (!redirected)
      {
        this.failUpdate(dto);
      }
    }
  }

  public void failUpdate(ResistancePropertyDTO dto) throws IOException, ServletException
  {
    req.setAttribute("item", dto);
    render("editComponent.jsp");
  }

  public void view(String id) throws IOException, ServletException
  {
    try
    {
      RedirectUtility utility = new RedirectUtility(req, resp);
      utility.put("id", id);
      utility.checkURL(this.getClass().getSimpleName(), "view");
      ClientRequestIF clientRequest = super.getClientRequest();

      ResistancePropertyDTO dto = ResistancePropertyDTO.get(clientRequest, id);
      req.setAttribute("item", dto);
      render("viewComponent.jsp");
    }
    catch (Throwable t)
    {
      boolean redirected = ErrorUtility.prepareThrowable(t, req, resp, this.isAsynchronous());

      if (!redirected)
      {
        this.failView(id);
      }
    }
  }

  public void failView(String id) throws IOException, ServletException
  {
    this.viewAll();
  }

  public void viewAll() throws IOException, ServletException
  {
    RedirectUtility utility = new RedirectUtility(req, resp);
    utility.checkURL(this.getClass().getSimpleName(), "viewAll");

    ClientRequestIF clientRequest = super.getClientRequest();
    ResistancePropertyQueryDTO query = ResistancePropertyDTO.getAllInstances(clientRequest, ResistancePropertyDTO.PROPERTYNAME, true, 20, 1);
    req.setAttribute("query", query);
    render("viewAllComponent.jsp");
  }

  public void failViewAll() throws IOException, ServletException
  {
    resp.sendError(500);
  }

  public void viewPage(String sortAttribute, Boolean isAscending, Integer pageSize, Integer pageNumber) throws IOException, ServletException
  {
    ClientRequestIF clientRequest = super.getClientRequest();
    ResistancePropertyQueryDTO query = ResistancePropertyDTO.getAllInstances(clientRequest, sortAttribute, isAscending, pageSize, pageNumber);
    req.setAttribute("query", query);
    render("viewAllComponent.jsp");
  }

  public void failViewPage(String sortAttribute, String isAscending, String pageSize, String pageNumber) throws IOException, ServletException
  {
    resp.sendError(500);
  }
}
