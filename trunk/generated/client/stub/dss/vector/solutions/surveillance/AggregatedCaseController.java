package dss.vector.solutions.surveillance;

import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.runwaysdk.ProblemExceptionDTO;
import com.runwaysdk.business.ProblemDTOIF;
import com.runwaysdk.constants.ClientRequestIF;
import com.runwaysdk.generation.loader.Reloadable;

import dss.vector.solutions.geo.generated.GeoEntityDTO;
import dss.vector.solutions.geo.generated.HealthFacilityDTO;
import dss.vector.solutions.util.ErrorUtility;
import dss.vector.solutions.util.RedirectUtility;

public class AggregatedCaseController extends AggregatedCaseControllerBase implements Reloadable
{
  public static final String JSP_DIR          = "WEB-INF/dss/vector/solutions/surveillance/AggregatedCase/";

  public static final String LAYOUT           = "/layout.jsp";

  private static final long  serialVersionUID = 1239022146651L;

  public AggregatedCaseController(HttpServletRequest req, HttpServletResponse resp, Boolean isAsynchronous)
  {
    super(req, resp, isAsynchronous, JSP_DIR, LAYOUT);
  }

  @Override
  public void create(AggregatedCaseViewDTO dto, CaseTreatmentDTO[] treatments, CaseTreatmentMethodDTO[] treatmentMethods, CaseTreatmentStockDTO[] stock, CaseDiagnosticDTO[] diagnosticMethods, CaseReferralDTO[] referrals) throws IOException, ServletException
  {
    try
    {
      dto.applyAll(treatments, treatmentMethods, stock, diagnosticMethods, referrals);
      this.view(dto);
    }
    catch (ProblemExceptionDTO e)
    {
      ErrorUtility.prepareProblems(e, req);

      this.failCreate(dto, treatments, treatmentMethods, stock, diagnosticMethods, referrals);
    }
    catch (Throwable t)
    {
      ErrorUtility.prepareThrowable(t, req);

      this.failCreate(dto, treatments, treatmentMethods, stock, diagnosticMethods, referrals);
    }
  }

  @Override
  public void failCreate(AggregatedCaseViewDTO dto, CaseTreatmentDTO[] treatments, CaseTreatmentMethodDTO[] treatmentMethods, CaseTreatmentStockDTO[] stock, CaseDiagnosticDTO[] diagnosticMethods, CaseReferralDTO[] referrals) throws IOException, ServletException
  {
    this.setupRequest(dto, treatments, treatmentMethods, stock, diagnosticMethods, referrals);

    req.setAttribute("item", dto);
    render("createComponent.jsp");
  }

  private void setupRequest(AggregatedCaseViewDTO dto, CaseTreatmentDTO[] treatments, CaseTreatmentMethodDTO[] treatmentMethods, CaseTreatmentStockDTO[] stock, CaseDiagnosticDTO[] diagnosticMethods, CaseReferralDTO[] referrals)
  {
    ClientRequestIF request = this.getClientSession().getRequest();
    AggregatedAgeGroupDTO[] ageGroups = AggregatedAgeGroupDTO.getAll(request);

    req.setAttribute("diagnostics", Arrays.asList(diagnosticMethods));
    req.setAttribute("referrals", Arrays.asList(referrals));
    req.setAttribute("treatments", Arrays.asList(treatments));
    req.setAttribute("treatmentMethods", Arrays.asList(treatmentMethods));
    req.setAttribute("stock", Arrays.asList(stock));
    req.setAttribute("ageGroups", Arrays.asList(ageGroups));
    req.setAttribute("search", dto.getSearchDTO());
  }

  public void update(AggregatedCaseViewDTO dto, CaseTreatmentDTO[] treatments, CaseTreatmentMethodDTO[] treatmentMethods, CaseTreatmentStockDTO[] stock, CaseDiagnosticDTO[] diagnosticMethods, CaseReferralDTO[] referrals) throws IOException, ServletException
  {
    try
    {
      dto.applyAll(treatments, treatmentMethods, stock, diagnosticMethods, referrals);
      this.view(dto);
    }
    catch (ProblemExceptionDTO e)
    {
      ErrorUtility.prepareProblems(e, req);

      this.failUpdate(dto, treatments, treatmentMethods, stock, diagnosticMethods, referrals);
    }
    catch (Throwable t)
    {
      ErrorUtility.prepareThrowable(t, req);

      this.failUpdate(dto, treatments, treatmentMethods, stock, diagnosticMethods, referrals);
    }
  }

  public void failUpdate(AggregatedCaseViewDTO dto, CaseTreatmentDTO[] treatments, CaseTreatmentMethodDTO[] treatmentMethods, CaseTreatmentStockDTO[] stock, CaseDiagnosticDTO[] diagnosticMethods, CaseReferralDTO[] referrals) throws IOException, ServletException
  {
    this.setupRequest(dto, treatments, treatmentMethods, stock, diagnosticMethods, referrals);
    req.setAttribute("item", dto);
    render("editComponent.jsp");
  }

  public void cancel(AggregatedCaseViewDTO dto) throws IOException, ServletException
  {
    try
    {
      dto.unlockCase();
      this.view(dto);
    }
    catch (Throwable t)
    {
      boolean redirected = ErrorUtility.prepareThrowable(t, req, resp, this.isAsynchronous());
      
      if(!redirected)
      {
        this.failCancel(dto);
      }
    }
  }

  public void failCancel(AggregatedCaseViewDTO dto) throws IOException, ServletException
  {
    this.edit(dto.getCaseId());
  }

  public void delete(AggregatedCaseViewDTO dto) throws IOException, ServletException
  {
    try
    {
      dto.deleteConcrete();
      this.search();
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

  public void failDelete(AggregatedCaseViewDTO dto) throws IOException, ServletException
  {
    this.setupRequest(dto);
    req.setAttribute("item", dto);
    render("editComponent.jsp");
  }

  public void edit(String id) throws IOException, ServletException
  {
    try
    {
      AggregatedCaseViewDTO c = AggregatedCaseDTO.lockView(this.getClientRequest(), id);

      this.setupRequest(c);
      req.setAttribute("item", c);
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

  private void setupRequest(AggregatedCaseViewDTO c)
  {
    this.setupRequest(c, c.getTreatments(), c.getTreatmentMethods(), c.getTreatmentStocks(), c.getDiagnosticMethods(), c.getReferrals());
  }

  public void failEdit(String id) throws IOException, ServletException
  {
    this.view(id);
  }

  public void view(String id) throws IOException, ServletException
  {
    try
    {
      this.view(AggregatedCaseDTO.getView(super.getClientRequest(), id));
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

  public void view(AggregatedCaseViewDTO dto) throws IOException, ServletException
  {
    RedirectUtility utility = new RedirectUtility(req, resp);
    utility.put("id", dto.getCaseId());
    utility.checkURL(this.getClass().getSimpleName(), "view");

    this.setupRequest(dto);

    req.setAttribute("item", dto);
    render("viewComponent.jsp");
  }

  public void failView(String id) throws IOException, ServletException
  {
    this.search();
  }

  @Override
  public void search() throws IOException, ServletException
  {
    this.search(new AggregatedCaseSearchViewDTO(this.getClientRequest()));
  }

  private void search(AggregatedCaseSearchViewDTO item) throws IOException, ServletException
  {
    ClientRequestIF request = this.getClientSession().getRequest();
    AggregatedAgeGroupDTO[] ageGroups = AggregatedAgeGroupDTO.getAll(request);
    List<String> entityUniversals = Arrays.asList(new String[] { HealthFacilityDTO.CLASS });

    req.setAttribute("ageGroups", Arrays.asList(ageGroups));
    req.setAttribute("periodType", PeriodTypeDTO.allItems(request));
    req.setAttribute("checkedType", PeriodTypeDTO.MONTH.getName());
    req.setAttribute("item", item);
    req.setAttribute("entityUniversals", entityUniversals);

    render("searchComponent.jsp");
  }

  public void selectAgeGroup(String geoId, String periodType, Integer period, Integer year) throws IOException, ServletException
  {
    ClientRequestIF request = this.getClientSession().getRequest();

    try
    {
      AggregatedCaseViewDTO.validateSearchCriteria(request, geoId, periodType, period, year);

      AggregatedAgeGroupDTO[] ageGroups = AggregatedAgeGroupDTO.getAll(request);

      req.setAttribute("geoId", geoId);
      req.setAttribute("periodType", periodType);
      req.setAttribute("period", period);
      req.setAttribute("year", year);
      req.setAttribute("ageGroups", Arrays.asList(ageGroups));

      render("selectComponent.jsp");
    }
    catch (ProblemExceptionDTO e)
    {
      ErrorUtility.prepareProblems(e, req);

      String failPeriod = period == null ? null : period.toString();
      String failYear = year == null ? null : year.toString();

      this.failSelectAgeGroup(geoId, periodType, failPeriod, failYear);
    }
    catch (Throwable t)
    {
      ErrorUtility.prepareThrowable(t, req);

      String failPeriod = period == null ? null : period.toString();
      String failYear = year == null ? null : year.toString();

      this.failSelectAgeGroup(geoId, periodType, failPeriod, failYear);
    }
  }

  @Override
  public void searchByView(AggregatedCaseSearchViewDTO dto) throws IOException, ServletException
  {
    try
    {
      validateParameters(dto.getGeoEntity(), dto.getAgeGroup());

      AggregatedCaseViewDTO c = dto.searchByView();

      if (c.hasCaseId())
      {
        this.view(c);
      }
      else
      {
        // Ensure the user has permissions to create a Aggregated Case
        new AggregatedCaseDTO(this.getClientRequest());

        // Load all of the corresponding grid values
        this.setupRequest(c);
        req.setAttribute("item", c);
        render("createComponent.jsp");
      }
    }
    catch (ProblemExceptionDTO e)
    {
      ErrorUtility.prepareProblems(e, req);

      this.failSearchByView(dto);
    }
    catch (Throwable t)
    {
      ErrorUtility.prepareThrowable(t, req);

      this.failSearchByView(dto);
    }
  }

  @Override
  public void failSearchByView(AggregatedCaseSearchViewDTO dto) throws IOException, ServletException
  {
    this.search(dto);
  }

  public void failSelectAgeGroup(String geoId, String periodType, String period, String year) throws IOException, ServletException
  {
    this.search();
  }

  private void validateParameters(GeoEntityDTO geoEntityDTO, AggregatedAgeGroupDTO ageGroup)
  {
    List<ProblemDTOIF> problems = new LinkedList<ProblemDTOIF>();

    if (geoEntityDTO == null)
    {
      ClientRequestIF clientRequest = super.getClientSession().getRequest();
      problems.add(new RequiredGeoIdProblemDTO(clientRequest, req.getLocale()));
    }

    if (ageGroup == null)
    {
      ClientRequestIF clientRequest = super.getClientSession().getRequest();
      problems.add(new RequiredAgeGroupProblemDTO(clientRequest, req.getLocale()));
    }

    if (problems.size() > 0)
    {
      throw new ProblemExceptionDTO("", problems);
    }
  }

  @Override
  public void failSearch() throws IOException, ServletException
  {
    // This should never occur
    super.failSearch();
  }
}
