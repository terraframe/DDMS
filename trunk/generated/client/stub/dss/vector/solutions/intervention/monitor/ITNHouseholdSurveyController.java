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
import dss.vector.solutions.util.RedirectUtility;

public class ITNHouseholdSurveyController extends ITNHouseholdSurveyControllerBase implements Reloadable
{
  public static final String JSP_DIR          = "WEB-INF/dss/vector/solutions/intervention/monitor/ITNHouseholdSurvey/";

  public static final String LAYOUT           = "/layout.jsp";

  private static final long  serialVersionUID = 1252970162915L;

  public ITNHouseholdSurveyController(HttpServletRequest req, HttpServletResponse resp, Boolean isAsynchronous)
  {
    super(req, resp, isAsynchronous, JSP_DIR, LAYOUT);
  }

  public void cancel(ITNHouseholdSurveyViewDTO dto) throws IOException, ServletException
  {
    this.view(ITNHouseholdSurveyDTO.unlockView(this.getClientRequest(), dto.getConcreteId()));
  }

  public void failCancel(ITNHouseholdSurveyViewDTO dto) throws IOException, ServletException
  {
    this.edit(dto.getId());
  }

  public void viewPage(String sortAttribute, Boolean isAscending, Integer pageSize, Integer pageNumber) throws IOException, ServletException
  {
    ClientRequestIF clientRequest = super.getClientRequest();
    
    ITNHouseholdSurveyViewQueryDTO query = ITNHouseholdSurveyViewDTO.getPage(clientRequest, sortAttribute, isAscending, pageSize, pageNumber);
    req.setAttribute("query", query);
    render("viewAllComponent.jsp");
  }

  public void failViewPage(String sortAttribute, String isAscending, String pageSize, String pageNumber) throws IOException, ServletException
  {
    resp.sendError(500);
  }

  public void update(ITNHouseholdSurveyViewDTO dto, ITNHouseholdSurveyNetDTO[] nets, ITNHouseholdSurveyTargetGroupDTO[] targetGroups, ITNHouseholdSurveyNonUseReasonDTO[] reasons) throws IOException, ServletException
  {
    try
    {
      dto.applyAll(nets, targetGroups, reasons);
      this.view(dto);
    }
    catch (ProblemExceptionDTO e)
    {
      ErrorUtility.prepareProblems(e, req);
      this.failUpdate(dto, nets, targetGroups, reasons);
    }
    catch (Throwable t)
    {
      ErrorUtility.prepareThrowable(t, req);
      this.failUpdate(dto, nets, targetGroups, reasons);
    }
  }

  public void failUpdate(ITNHouseholdSurveyViewDTO dto, ITNHouseholdSurveyNetDTO[] nets, ITNHouseholdSurveyTargetGroupDTO[] targetGroups, ITNHouseholdSurveyNonUseReasonDTO[] reasons) throws IOException, ServletException
  {
    this.setupReferences(dto);
    this.prepareRelationships(nets, targetGroups, reasons);
    
    req.setAttribute("item", dto);
    render("editComponent.jsp");
  }

  public void viewAll() throws IOException, ServletException
  {
    ClientRequestIF clientRequest = super.getClientRequest();
    
    ITNHouseholdSurveyViewQueryDTO query = ITNHouseholdSurveyViewDTO.getPage(clientRequest, null, true, 20, 1);
    req.setAttribute("query", query);
    render("viewAllComponent.jsp");
  }

  public void failViewAll() throws IOException, ServletException
  {
    resp.sendError(500);
  }

  public void view(String id) throws IOException, ServletException
  {
    this.view(ITNHouseholdSurveyDTO.getView(this.getClientRequest(), id));
  }

  private void view(ITNHouseholdSurveyViewDTO dto) throws IOException, ServletException
  {
    RedirectUtility utility = new RedirectUtility(req, resp);
    utility.put("id", dto.getConcreteId());
    utility.checkURL(this.getClass().getSimpleName(), "view");

    this.setupReferences(dto);
    this.prepareRelationships(dto);
    
    req.setAttribute("item", dto);
    render("viewComponent.jsp");
  }

  public void failView(String id) throws IOException, ServletException
  {
    this.viewAll();
  }

  public void newInstance() throws IOException, ServletException
  {
    ClientRequestIF clientRequest = super.getClientRequest();
    ITNHouseholdSurveyViewDTO dto = new ITNHouseholdSurveyViewDTO(clientRequest);
    
    this.setupReferences(dto);
    this.prepareRelationships(dto);
    
    req.setAttribute("item", dto);
    render("createComponent.jsp");
  }

  public void failNewInstance() throws IOException, ServletException
  {
    this.viewAll();
  }

  public void delete(ITNHouseholdSurveyViewDTO dto) throws IOException, ServletException
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

  public void failDelete(ITNHouseholdSurveyViewDTO dto) throws IOException, ServletException
  {
    this.setupReferences(dto);
    this.prepareRelationships(dto);
    
    req.setAttribute("item", dto);
    render("editComponent.jsp");
  }

  public void create(ITNHouseholdSurveyViewDTO dto, ITNHouseholdSurveyNetDTO[] nets, ITNHouseholdSurveyTargetGroupDTO[] targetGroups, ITNHouseholdSurveyNonUseReasonDTO[] reasons) throws IOException, ServletException
  {
    try
    {
      dto.applyAll(nets, targetGroups, reasons);
      this.view(dto);
    }
    catch (ProblemExceptionDTO e)
    {
      ErrorUtility.prepareProblems(e, req);
      this.failCreate(dto, nets, targetGroups, reasons);
    }
    catch (Throwable t)
    {
      ErrorUtility.prepareThrowable(t, req);
      this.failCreate(dto, nets, targetGroups, reasons);
    }
  }

  public void failCreate(ITNHouseholdSurveyViewDTO dto, ITNHouseholdSurveyNetDTO[] nets, ITNHouseholdSurveyTargetGroupDTO[] targetGroups, ITNHouseholdSurveyNonUseReasonDTO[] reasons) throws IOException, ServletException
  {
    this.setupReferences(dto);
    this.prepareRelationships(nets, targetGroups, reasons);
    
    req.setAttribute("item", dto);
    render("createComponent.jsp");
  }

  public void edit(String id) throws IOException, ServletException
  {
    ITNHouseholdSurveyViewDTO dto = ITNHouseholdSurveyDTO.lockView(super.getClientRequest(), id);

    this.setupReferences(dto);
    this.prepareRelationships(dto);
    
    req.setAttribute("item", dto);
    render("editComponent.jsp");
  }

  public void failEdit(String id) throws IOException, ServletException
  {
    this.view(id);
  }
  
  private void setupReferences(ITNHouseholdSurveyViewDTO dto)
  {
    req.setAttribute("boughtProvider", dto.getBoughtProvider());
    req.setAttribute("freeProvider", dto.getFreeProvider());
    req.setAttribute("retreatmentPeriod", dto.getRetreatmentPeriod());
    req.setAttribute("washInterval", dto.getWashInterval());
    req.setAttribute("washed", dto.getWashed());
  }
  
  private void prepareRelationships(ITNHouseholdSurveyViewDTO dto)
  {
    prepareRelationships(dto.getITNHouseholdSurveyNets(), dto.getITNHouseholdSurveyTargetGroups(), dto.getITNHouseholdSurveyNonUseReason());
  }

  private void prepareRelationships(ITNHouseholdSurveyNetDTO[] nets, ITNHouseholdSurveyTargetGroupDTO[] targetGroups, ITNHouseholdSurveyNonUseReasonDTO[] reasons)
  {
    req.setAttribute("targetGroups", Arrays.asList(targetGroups));
    req.setAttribute("nets", Arrays.asList(nets));
    req.setAttribute("reasons", Arrays.asList(reasons));
  }
}
