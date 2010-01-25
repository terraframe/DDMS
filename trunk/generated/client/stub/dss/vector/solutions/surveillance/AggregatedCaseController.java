package dss.vector.solutions.surveillance;

import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.terraframe.mojo.ProblemExceptionDTO;
import com.terraframe.mojo.business.ProblemDTOIF;
import com.terraframe.mojo.constants.ClientRequestIF;
import com.terraframe.mojo.generation.loader.Reloadable;

import dss.vector.solutions.general.EpiDateDTO;
import dss.vector.solutions.geo.generated.GeoEntityDTO;
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
    dto.unlockCase();
    this.view(dto);
  }

  public void failCancel(AggregatedCaseViewDTO dto) throws IOException, ServletException
  {
    resp.sendError(500);
  }

  public void delete(AggregatedCaseViewDTO dto) throws IOException, ServletException
  {
    try
    {
      dto.delete();
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
    this.view(AggregatedCaseDTO.getView(super.getClientRequest(), id));
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
    this.viewAll();
  }

  @Override
  public void search() throws IOException, ServletException
  {
    ClientRequestIF clientRequest = super.getClientSession().getRequest();

    req.setAttribute("periodType", PeriodTypeDTO.allItems(clientRequest));
    req.setAttribute("checkedType", PeriodTypeDTO.MONTH.getName());
    req.setAttribute("item", new AggregatedCaseSearchViewDTO(clientRequest));

    render("searchComponent.jsp");
  }

  public void searchByGeoEntityAndDate(GeoEntityDTO geoEntity, EpiDateDTO date, AggregatedAgeGroupDTO ageGroup) throws IOException, ServletException
  {
    AggregatedCaseViewDTO c = AggregatedCaseDTO.searchByGeoEntityAndEpiDate(this.getClientRequest(), geoEntity, date.getPeriodType().get(0), date.getPeriod(), date.getEpiYear(), ageGroup);

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
    ClientRequestIF request = this.getClientSession().getRequest();

    try
    {
      if (dto.getAgeGroup() == null)
      {
        List<PeriodTypeDTO> periodType = dto.getPeriodType();
        if (periodType.size() > 0)
        {
          req.setAttribute("periodType", periodType.get(0).name());
        }

        req.setAttribute("ageGroups", Arrays.asList(AggregatedAgeGroupDTO.getAll(request)));
        req.setAttribute("search", dto);

        render("selectComponent.jsp");
      }
      else
      {
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
    this.search();
  }

  public void failSelectAgeGroup(String geoId, String periodType, String period, String year) throws IOException, ServletException
  {
    this.search();
  }

  public void searchByGeoIdAndEpiWeek(String geoId, String periodType, Integer period, Integer year, AggregatedAgeGroupDTO ageGroup) throws IOException, ServletException
  {
    ClientRequestIF request = this.getClientSession().getRequest();
    try
    {
      validateParameters(geoId, periodType, period, year, ageGroup);

      PeriodTypeDTO type = PeriodTypeDTO.valueOf(periodType);
      EpiDateDTO date = EpiDateDTO.getInstanceByPeriod(request, type, period, year);
      GeoEntityDTO geoEntity = GeoEntityDTO.searchByGeoId(this.getClientRequest(), geoId);

      this.searchByGeoEntityAndDate(geoEntity, date, ageGroup);
    }
    catch (ProblemExceptionDTO e)
    {
      ErrorUtility.prepareProblems(e, req);

      String failPeriod = period == null ? null : period.toString();
      String failYear = year == null ? null : year.toString();

      this.failSearchByGeoIdAndEpiWeek(geoId, periodType, failPeriod, failYear, ageGroup);
    }
    catch (Throwable t)
    {
      ErrorUtility.prepareThrowable(t, req);

      String failPeriod = period == null ? null : period.toString();
      String failYear = year == null ? null : year.toString();

      this.failSearchByGeoIdAndEpiWeek(geoId, periodType, failPeriod, failYear, ageGroup);
    }
  }

  private void validateParameters(String geoId, String periodType, Integer period, Integer year, AggregatedAgeGroupDTO ageGroup)
  {
    List<ProblemDTOIF> problems = new LinkedList<ProblemDTOIF>();

    if (geoId == null)
    {
      ClientRequestIF clientRequest = super.getClientSession().getRequest();
      problems.add(new RequiredGeoIdProblemDTO(clientRequest, req.getLocale()));
    }

    if (periodType == null)
    {
      ClientRequestIF clientRequest = super.getClientSession().getRequest();
      problems.add(new RequiredPeriodTypeProblemDTO(clientRequest, req.getLocale()));
    }

    if (period == null)
    {
      ClientRequestIF clientRequest = super.getClientSession().getRequest();
      problems.add(new RequiredPeriodProblemDTO(clientRequest, req.getLocale()));
    }

    if (year == null)
    {
      ClientRequestIF clientRequest = super.getClientSession().getRequest();
      problems.add(new RequiredYearProblemDTO(clientRequest, req.getLocale()));
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

  public void failSearchByGeoIdAndEpiWeek(String geoId, String periodType, String period, String year, AggregatedAgeGroupDTO ageGroup) throws IOException, ServletException
  {
    ClientRequestIF clientRequest = super.getClientSession().getRequest();
    List<PeriodTypeMasterDTO> allItems = PeriodTypeDTO.allItems(clientRequest);

    req.setAttribute("periodType", allItems);
    req.setAttribute("period", period);
    req.setAttribute("year", year);
    req.setAttribute("geoId", geoId);
    req.setAttribute("checkedType", periodType);
    req.setAttribute("ageGroup", ageGroup);

    render("searchComponent.jsp");
  }

  @Override
  public void failSearch() throws IOException, ServletException
  {
    // This should never occur
    super.failSearch();
  }
}
