package dss.vector.solutions.general;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.terraframe.mojo.ProblemExceptionDTO;
import com.terraframe.mojo.constants.ClientRequestIF;
import com.terraframe.mojo.generation.loader.Reloadable;

import dss.vector.solutions.util.ErrorUtility;
import dss.vector.solutions.util.RedirectUtility;

public class InsecticideController extends InsecticideControllerBase implements Reloadable
{
  public static final String JSP_DIR          = "WEB-INF/dss/vector/solutions/general/Insecticide/";

  public static final String LAYOUT           = "/layout.jsp";

  private static final long  serialVersionUID = 1237396167095L;

  public InsecticideController(HttpServletRequest req, HttpServletResponse resp, Boolean isAsynchronous)
  {
    super(req, resp, isAsynchronous, JSP_DIR, LAYOUT);
  }

  public void create(InsecticideDTO dto) throws IOException, ServletException
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

  public void failCreate(InsecticideDTO dto) throws IOException, ServletException
  {

    req.setAttribute("item", dto);

    render("createComponent.jsp");
  }

  public void viewPage(String sortAttribute, Boolean isAscending, Integer pageSize, Integer pageNumber) throws IOException, ServletException
  {
    ClientRequestIF clientRequest = super.getClientRequest();
    InsecticideQueryDTO query = InsecticideDTO.getAllInstances(clientRequest, sortAttribute, isAscending, pageSize, pageNumber);
    req.setAttribute("query", query);

    render("viewAllComponent.jsp");
  }

  public void failViewPage(String sortAttribute, String isAscending, String pageSize, String pageNumber) throws IOException, ServletException
  {
    resp.sendError(500);
  }

  public void viewAll() throws IOException, ServletException
  {
    new RedirectUtility(req, resp).checkURL(this.getClass().getSimpleName(), "viewAll");

    ClientRequestIF clientRequest = super.getClientRequest();
    InsecticideQueryDTO query = InsecticideDTO.getAllInstances(clientRequest, null, true, 20, 1);
    req.setAttribute("query", query);

    render("viewAllComponent.jsp");
  }

  public void failViewAll() throws IOException, ServletException
  {
    resp.sendError(500);
  }

  public void newInstance() throws IOException, ServletException
  {
    try
    {
      ClientRequestIF clientRequest = super.getClientRequest();
      InsecticideDTO dto = new InsecticideDTO(clientRequest);

      this.setupReferences(dto);

      req.setAttribute("item", dto);

      render("createComponent.jsp");
    }
    catch (ProblemExceptionDTO e)
    {
      ErrorUtility.prepareProblems(e, req);

      this.failNewInstance();
    }
    catch (Throwable t)
    {
      ErrorUtility.prepareThrowable(t, req);

      this.failNewInstance();
    }
  }

  private void setupReferences(InsecticideDTO dto)
  {
    req.setAttribute("activeIngredient", dto.getActiveIngredient());
    req.setAttribute("units", dto.getUnits());
  }

  public void failNewInstance() throws IOException, ServletException
  {
    this.viewAll();
  }

  public void update(InsecticideDTO dto) throws IOException, ServletException
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

  public void failUpdate(InsecticideDTO dto) throws IOException, ServletException
  {

    req.setAttribute("item", dto);

    render("updateComponent.jsp");
  }

  public void cancel(InsecticideDTO dto) throws IOException, ServletException
  {
    dto.unlock();
    this.view(dto.getId());
  }

  public void failCancel(InsecticideDTO dto) throws IOException, ServletException
  {
    this.edit(dto.getId());
  }

  public void delete(InsecticideDTO dto) throws IOException, ServletException
  {
    try
    {
      dto.delete();
      this.viewAll();
    }
    catch (ProblemExceptionDTO e)
    {
      ErrorUtility.prepareProblems(e, req);

      this.viewAll();
    }
    catch (Throwable t)
    {
      ErrorUtility.prepareThrowable(t, req);

      this.viewAll();
    }
  }

  public void failDelete(InsecticideDTO dto) throws IOException, ServletException
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

      InsecticideDTO dto = InsecticideDTO.get(super.getClientRequest(), id);
      this.setupReferences(dto);
      req.setAttribute("item", dto);

      render("viewComponent.jsp");
    }
    catch (ProblemExceptionDTO e)
    {
      ErrorUtility.prepareProblems(e, req);
      this.failView(id);
    }
    catch (Throwable t)
    {
      ErrorUtility.prepareThrowable(t, req);
      this.failView(id);
    }

  }

  public void failView(String id) throws IOException, ServletException
  {
    this.viewAll();
  }

  public void edit(String id) throws IOException, ServletException
  {
    try
    {
      InsecticideDTO dto = InsecticideDTO.lock(super.getClientRequest(), id);

      this.setupReferences(dto);

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
