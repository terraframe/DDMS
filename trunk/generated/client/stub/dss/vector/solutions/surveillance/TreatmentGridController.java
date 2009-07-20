package dss.vector.solutions.surveillance;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.terraframe.mojo.ProblemExceptionDTO;
import com.terraframe.mojo.constants.ClientRequestIF;

import dss.vector.solutions.util.ErrorUtility;
import dss.vector.solutions.util.RedirectUtility;

public class TreatmentGridController extends TreatmentGridControllerBase implements
    com.terraframe.mojo.generation.loader.Reloadable
{
  public static final String JSP_DIR          = "/WEB-INF/dss/vector/solutions/surveillance/TreatmentGrid/";

  public static final String LAYOUT           = "/layout.jsp";

  private static final long  serialVersionUID = 1243484795492L;

  public TreatmentGridController(HttpServletRequest req, HttpServletResponse resp, Boolean isAsynchronous)
  {
    super(req, resp, isAsynchronous, JSP_DIR, LAYOUT);
  }

  public void cancel(TreatmentGridDTO dto) throws IOException, ServletException
  {
    dto.unlock();
    this.view(dto.getId());
  }

  public void failCancel(TreatmentGridDTO dto) throws IOException, ServletException
  {
    this.edit(dto.getId());
  }

  public void newInstance() throws IOException, ServletException
  {
    ClientRequestIF clientRequest = super.getClientRequest();
    TreatmentGridDTO dto = new TreatmentGridDTO(clientRequest);
    req.setAttribute("item", dto);
    render("createComponent.jsp");
  }

  public void failNewInstance() throws IOException, ServletException
  {
    this.viewAll();
  }

  public void create(TreatmentGridDTO dto) throws IOException, ServletException
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

  public void failCreate(TreatmentGridDTO dto) throws IOException, ServletException
  {
    req.setAttribute("item", dto);
    render("createComponent.jsp");
  }

  public void viewAll() throws IOException, ServletException
  {
    new RedirectUtility(req, resp).checkURL(this.getClass().getSimpleName(), "viewAll");

    ClientRequestIF clientRequest = super.getClientRequest();
    TreatmentGridQueryDTO query = TreatmentGridDTO.getAllInstances(clientRequest, null, true, 20, 1);
    req.setAttribute("query", query);
    render("viewAllComponent.jsp");
  }

  public void failViewAll() throws IOException, ServletException
  {
    resp.sendError(500);
  }

  public void delete(TreatmentGridDTO dto) throws IOException, ServletException
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

  public void failDelete(TreatmentGridDTO dto) throws IOException, ServletException
  {
    req.setAttribute("item", dto);
    render("editComponent.jsp");
  }

  public void viewPage(String sortAttribute, Boolean isAscending, Integer pageSize, Integer pageNumber)
      throws IOException, ServletException
  {
    RedirectUtility utility = new RedirectUtility(req, resp);
    utility.put("sortAttriubte", sortAttribute);
    utility.put("isAscending", isAscending);
    utility.put("pageSize", pageSize);
    utility.put("pageNumber", pageNumber);    

    utility.checkURL(this.getClass().getSimpleName(), "viewPage");

    ClientRequestIF clientRequest = super.getClientRequest();
    TreatmentGridQueryDTO query = TreatmentGridDTO.getAllInstances(clientRequest, sortAttribute,
        isAscending, pageSize, pageNumber);
    req.setAttribute("query", query);
    render("viewAllComponent.jsp");
  }

  public void failViewPage(String sortAttribute, String isAscending, String pageSize, String pageNumber)
      throws IOException, ServletException
  {
    resp.sendError(500);
  }

  public void view(String id) throws IOException, ServletException
  {
    RedirectUtility utility = new RedirectUtility(req, resp);
    utility.put("id", id);
    utility.checkURL(this.getClass().getSimpleName(), "view");

    req.setAttribute("item", TreatmentGridDTO.get(super.getClientRequest(), id));
    render("viewComponent.jsp");
  }

  public void failView(String id) throws IOException, ServletException
  {
    this.viewAll();
  }

  public void update(TreatmentGridDTO dto) throws IOException, ServletException
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

  public void failUpdate(TreatmentGridDTO dto) throws IOException, ServletException
  {
    req.setAttribute("item", dto);
    render("editComponent.jsp");
  }

  public void edit(String id) throws IOException, ServletException
  {
    try
    {
      TreatmentGridDTO dto = TreatmentGridDTO.lock(super.getClientRequest(), id);
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

  public void failEdit(String id) throws IOException, ServletException
  {
    this.view(id);
  }
}
