package dss.vector.solutions.intervention.monitor;

import java.io.IOException;
import java.util.Arrays;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.terraframe.mojo.ProblemExceptionDTO;
import com.terraframe.mojo.constants.ClientRequestIF;
import com.terraframe.mojo.generation.loader.Reloadable;

import dss.vector.solutions.entomology.assay.EfficacyAssayViewDTO;
import dss.vector.solutions.geo.generated.GeoEntityDTO;
import dss.vector.solutions.util.AttributeUtil;
import dss.vector.solutions.util.ErrorUtility;
import dss.vector.solutions.util.RedirectUtility;

public class ITNCommunityDistributionController extends ITNCommunityDistributionControllerBase implements Reloadable
{
  public static final String JSP_DIR          = "WEB-INF/dss/vector/solutions/intervention/monitor/ITNCommunityDistribution/";

  public static final String LAYOUT           = "/layout.jsp";

  private static final long  serialVersionUID = 1252612170678L;

  public ITNCommunityDistributionController(HttpServletRequest req, HttpServletResponse resp, Boolean isAsynchronous)
  {
    super(req, resp, isAsynchronous, JSP_DIR, LAYOUT);
  }

  public void viewPage(String sortAttribute, Boolean isAscending, Integer pageSize, Integer pageNumber) throws IOException, ServletException
  {
    ClientRequestIF clientRequest = super.getClientRequest();
    ITNCommunityDistributionViewQueryDTO query = ITNCommunityDistributionViewDTO.getPage(clientRequest, sortAttribute, isAscending, pageSize, pageNumber);
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
      ITNCommunityDistributionViewQueryDTO query = ITNCommunityDistributionViewDTO.getPage(clientRequest, null, true, 20, 1);
      req.setAttribute("query", query);
      render("viewAllComponent.jsp");
    }
    catch (ProblemExceptionDTO e)
    {
      ErrorUtility.forceProblems(e, req);
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
    resp.sendError(500);
  }

  public void view(String id) throws IOException, ServletException
  {
    view(ITNCommunityDistributionDTO.getView(this.getClientRequest(), id));
  }

  private void view(ITNCommunityDistributionViewDTO dto) throws IOException, ServletException
  {
    RedirectUtility utility = new RedirectUtility(req, resp);
    utility.put("id", dto.getConcreteId());
    utility.checkURL(this.getClass().getSimpleName(), "view");

    this.prepareRelationships(dto);
    this.getGeoEntities(dto);

    req.setAttribute("item", dto);
    render("viewComponent.jsp");
  }

  private void getGeoEntities(ITNCommunityDistributionViewDTO dto)
  {
    req.setAttribute("distributionLocation", AttributeUtil.getGeoEntityFromGeoId(ITNCommunityDistributionViewDTO.DISTRIBUTIONLOCATION, dto));
    req.setAttribute("householdAddress", AttributeUtil.getGeoEntityFromGeoId(ITNCommunityDistributionViewDTO.HOUSEHOLDADDRESS, dto));
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

      // Ensure the user has permissions to create a new ITN Community
      // Distribution
      new ITNCommunityDistributionDTO(clientRequest);

      ITNCommunityDistributionViewDTO dto = new ITNCommunityDistributionViewDTO(clientRequest);

      this.prepareRelationships(dto);
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

  public void failNewInstance() throws IOException, ServletException
  {
    this.viewAll();
  }

  public void create(ITNCommunityDistributionViewDTO dto, ITNCommunityNetDTO[] nets, ITNCommunityTargetGroupDTO[] targetGroups) throws IOException, ServletException
  {
    try
    {
      dto.applyAll(nets, targetGroups);

      this.view(dto);
    }
    catch (ProblemExceptionDTO e)
    {
      ErrorUtility.prepareProblems(e, req);
      this.failCreate(dto, nets, targetGroups);
    }
    catch (Throwable t)
    {
      ErrorUtility.prepareThrowable(t, req);
      this.failCreate(dto, nets, targetGroups);
    }
  }

  public void failCreate(ITNCommunityDistributionViewDTO dto, ITNCommunityNetDTO[] nets, ITNCommunityTargetGroupDTO[] targetGroups) throws IOException, ServletException
  {
    this.prepareRelationships(targetGroups, nets);

    req.setAttribute("item", dto);
    render("createComponent.jsp");
  }

  public void edit(String id) throws IOException, ServletException
  {
    try
    {
      ITNCommunityDistributionViewDTO dto = ITNCommunityDistributionDTO.lockView(super.getClientRequest(), id);

      this.prepareRelationships(dto);
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

  public void update(ITNCommunityDistributionViewDTO dto, ITNCommunityNetDTO[] nets, ITNCommunityTargetGroupDTO[] targetGroups) throws IOException, ServletException
  {
    try
    {
      dto.applyAll(nets, targetGroups);
      this.view(dto);
    }
    catch (ProblemExceptionDTO e)
    {
      ErrorUtility.prepareProblems(e, req);
      this.failUpdate(dto, nets, targetGroups);
    }
    catch (Throwable t)
    {
      ErrorUtility.prepareThrowable(t, req);
      this.failUpdate(dto, nets, targetGroups);
    }
  }

  public void failUpdate(ITNCommunityDistributionViewDTO dto, ITNCommunityNetDTO[] nets, ITNCommunityTargetGroupDTO[] targetGroups) throws IOException, ServletException
  {
    this.prepareRelationships(targetGroups, nets);

    req.setAttribute("item", dto);
    render("editComponent.jsp");
  }

  public void cancel(ITNCommunityDistributionViewDTO dto) throws IOException, ServletException
  {
    this.view(ITNCommunityDistributionDTO.unlockView(this.getClientRequest(), dto.getConcreteId()));
  }

  public void failCancel(ITNCommunityDistributionViewDTO dto) throws IOException, ServletException
  {
    this.edit(dto.getId());
  }

  public void delete(ITNCommunityDistributionViewDTO dto) throws IOException, ServletException
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

  public void failDelete(ITNCommunityDistributionViewDTO dto) throws IOException, ServletException
  {
    this.prepareRelationships(dto);

    req.setAttribute("item", dto);
    render("editComponent.jsp");
  }

  private void prepareRelationships(ITNCommunityDistributionViewDTO dto)
  {
    prepareRelationships(dto.getITNCommunityTargetGroups(), dto.getITNCommunityNets());
  }

  private void prepareRelationships(ITNCommunityTargetGroupDTO[] targetGroups, ITNCommunityNetDTO[] nets)
  {
    req.setAttribute("targetGroups", Arrays.asList(targetGroups));
    req.setAttribute("nets", Arrays.asList(nets));
  }
}
