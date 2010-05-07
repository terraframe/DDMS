package dss.vector.solutions.entomology;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.runwaysdk.ProblemExceptionDTO;
import com.runwaysdk.constants.ClientRequestIF;

import dss.vector.solutions.geo.generated.SentinelSiteDTO;
import dss.vector.solutions.util.ErrorUtility;
import dss.vector.solutions.util.RedirectUtility;

public class ImmatureThresholdController extends ImmatureThresholdControllerBase implements com.runwaysdk.generation.loader.Reloadable
{
  public static final String JSP_DIR          = "WEB-INF/dss/vector/solutions/entomology/ImmatureThreshold/";

  public static final String LAYOUT           = "/layout.jsp";

  private static final long serialVersionUID = -1530348437;
  
  public ImmatureThresholdController(HttpServletRequest req, HttpServletResponse resp, Boolean isAsynchronous)
  {
    super(req, resp, isAsynchronous, JSP_DIR, LAYOUT);
  }

  public void update(ImmatureThresholdViewDTO dto) throws IOException, ServletException
  {
    try
    {
      dto.apply();
      this.view(dto);
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

  public void failUpdate(ImmatureThresholdViewDTO dto) throws IOException, ServletException
  {
    req.setAttribute("item", dto);
    render("editComponent.jsp");
  }

  public void viewAll() throws IOException, ServletException
  {
    new RedirectUtility(req, resp).checkURL(this.getClass().getSimpleName(), "viewAll");

    ClientRequestIF clientRequest = super.getClientRequest();
    ImmatureThresholdViewQueryDTO query = ImmatureThresholdViewDTO.getPage(clientRequest, ImmatureThresholdDTO.DISPLAYLABEL, true, 20, 1);
    req.setAttribute("query", query);
    render("viewAllComponent.jsp");
  }

  public void failViewAll() throws IOException, ServletException
  {
    resp.sendError(500);
  }

  public void create(ImmatureThresholdViewDTO dto) throws IOException, ServletException
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

  public void failCreate(ImmatureThresholdViewDTO dto) throws IOException, ServletException
  {
    this.newInstance(dto);
  }

  public void newInstance() throws IOException, ServletException
  {
    try
    {
      ClientRequestIF clientRequest = super.getClientRequest();

      // Ensure the user has permissions to create a survey point
      new ImmatureThresholdDTO(clientRequest);

      this.newInstance(new ImmatureThresholdViewDTO(clientRequest));
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

  private void newInstance(ImmatureThresholdViewDTO dto) throws IOException, ServletException
  {
    req.setAttribute("item", dto);
    render("createComponent.jsp");
  }

  public void failNewInstance() throws IOException, ServletException
  {
    this.viewAll();
  }

  public void view(String id) throws IOException, ServletException
  {
    try
    {
      view(ImmatureThresholdDTO.getView(super.getClientRequest(), id));
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

  public void view(ImmatureThresholdViewDTO dto) throws IOException, ServletException
  {
    RedirectUtility utility = new RedirectUtility(req, resp);
    utility.put("id", dto.getConcreteId());
    utility.checkURL(this.getClass().getSimpleName(), "view");

    req.setAttribute("item", dto);
    render("viewComponent.jsp");
  }

  public void failView(String id) throws IOException, ServletException
  {
    this.viewAll();
  }

  public void cancel(ImmatureThresholdViewDTO dto) throws IOException, ServletException
  {
    try
    {
      this.view(ImmatureThresholdDTO.unlockView(dto.getRequest(), dto.getConcreteId()));
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
  
  @Override
  public void failCancel(ImmatureThresholdViewDTO dto) throws IOException, ServletException
  {
    this.edit(dto.getConcreteId());
  }

  public void delete(ImmatureThresholdViewDTO dto) throws IOException, ServletException
  {
    try
    {
      dto.deleteConcrete();
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

  public void failDelete(ImmatureThresholdViewDTO dto) throws IOException, ServletException
  {
    List<String> entityUniversals = Arrays.asList(new String[] { SentinelSiteDTO.CLASS });
    req.setAttribute("entityUniversals", entityUniversals);
    req.setAttribute("SentinelSite", SentinelSiteDTO.CLASS);
    req.setAttribute("item", dto);

    render("editComponent.jsp");
  }

  public void viewPage(String sortAttribute, Boolean isAscending, Integer pageSize, Integer pageNumber) throws IOException, ServletException
  {
    ClientRequestIF clientRequest = super.getClientRequest();
    ImmatureThresholdViewQueryDTO query = ImmatureThresholdViewDTO.getPage(clientRequest, sortAttribute, isAscending, pageSize, pageNumber);
    req.setAttribute("query", query);
    render("viewAllComponent.jsp");
  }

  public void failViewPage(String sortAttribute, String isAscending, String pageSize, String pageNumber) throws IOException, ServletException
  {
    resp.sendError(500);
  }

  public void edit(String id) throws IOException, ServletException
  {
    try
    {
      ImmatureThresholdViewDTO dto = ImmatureThresholdDTO.lockView(super.getClientRequest(), id);

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
