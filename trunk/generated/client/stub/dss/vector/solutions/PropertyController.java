package dss.vector.solutions;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.terraframe.mojo.ProblemExceptionDTO;
import com.terraframe.mojo.constants.ClientRequestIF;

import dss.vector.solutions.general.EpiConfigurationDTO;
import dss.vector.solutions.util.ErrorUtility;
import dss.vector.solutions.util.RedirectUtility;

public class PropertyController extends PropertyControllerBase implements com.terraframe.mojo.generation.loader.Reloadable
{
  public static final String JSP_DIR          = "WEB-INF/dss/vector/solutions/Property/";

  public static final String LAYOUT           = "/layout.jsp";

  private static final long  serialVersionUID = 1236023121846L;

  public PropertyController(HttpServletRequest req, HttpServletResponse resp, Boolean isAsynchronous)
  {
    super(req, resp, isAsynchronous, JSP_DIR, LAYOUT);
  }

  @Override
  public void viewPackage(String propertyPackage) throws IOException, ServletException
  {
    ClientRequestIF clientRequest = super.getClientRequest();
    PropertyQueryDTO query = PropertyDTO.getAllByPackage(clientRequest, propertyPackage);
    req.setAttribute("query", query);
    render("viewAllComponent.jsp");
  }

  public void view(String id) throws IOException, ServletException
  {
    this.view(PropertyDTO.get(this.getClientRequest(), id));
  }

  private void view(PropertyDTO dto) throws IOException, ServletException
  {
    RedirectUtility utility = new RedirectUtility(req, resp);
    utility.put("id", dto.getId());
    utility.checkURL(this.getClass().getSimpleName(), "view");

    req.setAttribute("configuration", new EpiConfigurationDTO(this.getClientRequest()));
    req.setAttribute("item", dto);
    render("viewComponent.jsp");
  }

  public void failView(String id) throws IOException, ServletException
  {
    this.viewAll();
  }

  public void create(PropertyDTO dto) throws IOException, ServletException
  {
    try
    {
      // IMPORTANT: This action first locks and then applies the dto. As such,
      // it is a completely thread unsafe mechanism where the last user who
      // submitts will have their data perserved. Therefore it should on be used
      // for updating the EPI_WEEK property
      PropertyDTO locked = PropertyDTO.lock(this.getClientRequest(), dto.getId());
      locked.setPropertyValue(dto.getPropertyValue());
      locked.apply();

      this.newInstance();
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

  public void failCreate(PropertyDTO dto) throws IOException, ServletException
  {
    req.setAttribute("configuration", new EpiConfigurationDTO(this.getClientRequest()));
    req.setAttribute("item", dto);
    render("epiWeekComponent.jsp");
  }

  public void viewPage(String sortAttribute, Boolean isAscending, Integer pageSize, Integer pageNumber) throws IOException, ServletException
  {
    ClientRequestIF clientRequest = super.getClientRequest();
    PropertyDTO dto = PropertyDTO.getByPackageAndName(clientRequest, PropertyInfo.EPI_WEEK_PACKAGE, PropertyInfo.EPI_START_DAY);
    req.setAttribute("item", dto);
    req.getRequestDispatcher(dir + "epiWeekExcel.jsp").forward(req, resp);
  }

  public void failViewPage(String sortAttribute, String isAscending, String pageSize, String pageNumber) throws IOException, ServletException
  {
    resp.sendError(500);
  }

  public void cancel(PropertyDTO dto) throws IOException, ServletException
  {
    dto.unlock();

    this.view(dto);
  }

  public void failCancel(PropertyDTO dto) throws IOException, ServletException
  {
    this.edit(dto.getId());
  }

  public void viewAll() throws IOException, ServletException
  {
    ClientRequestIF clientRequest = super.getClientRequest();

    PropertyQueryDTO query = PropertyDTO.getAllEditable(clientRequest);

    req.setAttribute("query", query);
    render("viewAllComponent.jsp");
  }

  public void failViewAll() throws IOException, ServletException
  {
    resp.sendError(500);
  }

  public void update(PropertyDTO dto) throws IOException, ServletException
  {
    try
    {
      // dto.lock();
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

  public void failUpdate(PropertyDTO dto) throws IOException, ServletException
  {
    this.edit(dto.getId());
  }

  public void edit(String id) throws IOException, ServletException
  {
    try
    {
      PropertyDTO dto = PropertyDTO.lock(super.getClientRequest(), id);
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

  public void newInstance() throws IOException, ServletException
  {
    ClientRequestIF clientRequest = super.getClientRequest();
    /*
     * PropertyDTO dto = new PropertyDTO(clientRequest);
     */
    PropertyDTO dto = PropertyDTO.getByPackageAndName(clientRequest, PropertyInfo.EPI_WEEK_PACKAGE, PropertyInfo.EPI_START_DAY);

    req.setAttribute("item", dto);
    req.setAttribute("configuration", new EpiConfigurationDTO(clientRequest));
    render("epiWeekComponent.jsp");
  }

  public void failNewInstance() throws IOException, ServletException
  {
    this.viewAll();
  }
}
