package dss.vector.solutions.surveillance;

import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.ServletException;

import com.terraframe.mojo.ProblemExceptionDTO;
import com.terraframe.mojo.business.ProblemDTOIF;
import com.terraframe.mojo.constants.ClientRequestIF;

import dss.vector.solutions.general.EpiDateDTO;
import dss.vector.solutions.geo.generated.GeoEntityDTO;
import dss.vector.solutions.util.ErrorUtility;

public class AggregatedCaseController extends AggregatedCaseControllerBase implements
    com.terraframe.mojo.generation.loader.Reloadable
{
  public static final String JSP_DIR          = "WEB-INF/dss/vector/solutions/surveillance/AggregatedCase/";

  public static final String LAYOUT           = JSP_DIR + "layout.jsp";

  private static final long  serialVersionUID = 1239022146651L;

  public AggregatedCaseController(javax.servlet.http.HttpServletRequest req,
      javax.servlet.http.HttpServletResponse resp, java.lang.Boolean isAsynchronous)
  {
    super(req, resp, isAsynchronous, JSP_DIR, LAYOUT);
  }

  @Override
  public void create(AggregatedCaseViewDTO dto, CaseTreatmentDTO[] treatments,
      CaseTreatmentMethodDTO[] treatmentMethods, CaseTreatmentStockDTO[] stock,
      CaseDiagnosticDTO[] diagnosticMethods, CaseReferralDTO[] referrals) throws IOException,
      ServletException
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
  public void failCreate(AggregatedCaseViewDTO dto, CaseTreatmentDTO[] treatments,
      CaseTreatmentMethodDTO[] treatmentMethods, CaseTreatmentStockDTO[] stock,
      CaseDiagnosticDTO[] diagnosticMethods, CaseReferralDTO[] referrals) throws IOException,
      ServletException
  {
    AggregatedAgeGroupDTO[] ageGroups = AggregatedAgeGroupDTO.getAll(this.getClientSession().getRequest());

    req.setAttribute("diagnostics", Arrays.asList(diagnosticMethods));
    req.setAttribute("referrals", Arrays.asList(referrals));
    req.setAttribute("treatments", Arrays.asList(treatments));
    req.setAttribute("treatmentMethods", Arrays.asList(treatmentMethods));
    req.setAttribute("stock", Arrays.asList(stock));
    req.setAttribute("ageGroups", Arrays.asList(ageGroups));
    req.setAttribute("item", dto);
    req.setAttribute("page_title", "Error Creating Aggregated Case");
    render("createComponent.jsp");
  }

  public void update(AggregatedCaseViewDTO dto, CaseTreatmentDTO[] treatments,
      CaseTreatmentMethodDTO[] treatmentMethods, CaseTreatmentStockDTO[] stock,
      CaseDiagnosticDTO[] diagnosticMethods, CaseReferralDTO[] referrals) throws IOException,
      ServletException
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

  public void failUpdate(AggregatedCaseViewDTO dto, CaseTreatmentDTO[] treatments,
      CaseTreatmentMethodDTO[] treatmentMethods, CaseTreatmentStockDTO[] stock,
      CaseDiagnosticDTO[] diagnosticMethods, CaseReferralDTO[] referrals) throws IOException,
      ServletException
  {
    req.setAttribute("diagnostics", Arrays.asList(diagnosticMethods));
    req.setAttribute("referrals", Arrays.asList(referrals));
    req.setAttribute("treatments", Arrays.asList(treatments));
    req.setAttribute("treatmentMethods", Arrays.asList(treatmentMethods));
    req.setAttribute("stock", Arrays.asList(stock));
    req.setAttribute("item", dto);
    req.setAttribute("page_title", "Error Updating Aggregated Case");
    render("editComponent.jsp");
  }

  public void cancel(AggregatedCaseViewDTO dto) throws java.io.IOException,
      javax.servlet.ServletException
  {
    dto.unlockCase();
    this.view(dto);
  }

  public void failCancel(AggregatedCaseViewDTO dto) throws java.io.IOException,
      javax.servlet.ServletException
  {
    resp.sendError(500);
  }

  public void delete(AggregatedCaseViewDTO dto) throws java.io.IOException,
      javax.servlet.ServletException
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

  public void failDelete(AggregatedCaseViewDTO dto) throws java.io.IOException,
      javax.servlet.ServletException
  {
    req.setAttribute("diagnostics", Arrays.asList(dto.getDiagnosticMethods()));
    req.setAttribute("referrals", Arrays.asList(dto.getReferrals()));
    req.setAttribute("treatments", Arrays.asList(dto.getTreatments()));
    req.setAttribute("treatmentMethods", Arrays.asList(dto.getTreatmentMethods()));
    req.setAttribute("stock", Arrays.asList(dto.getTreatmentStocks()));
    req.setAttribute("item", dto);
    req.setAttribute("page_title", "Edit Aggregated Case");
    render("editComponent.jsp");
  }

  public void edit(java.lang.String id) throws java.io.IOException, javax.servlet.ServletException
  {
    AggregatedCaseViewDTO c = AggregatedCaseDTO.lockView(this.getClientRequest(), id);
    ClientRequestIF request = this.getClientSession().getRequest();

    EpiDateDTO epiDate = EpiDateDTO.getInstanceByPeriod(request,c.getPeriodType().get(0), c.getPeriod(), c.getPeriodYear());
    AggregatedAgeGroupDTO[] ageGroups = AggregatedAgeGroupDTO.getAll(this.getClientSession()
        .getRequest());

    // Load all of the corresponding grid values
    req.setAttribute("ageGroups", Arrays.asList(ageGroups));
    req.setAttribute("diagnostics", Arrays.asList(c.getDiagnosticMethods()));
    req.setAttribute("referrals", Arrays.asList(c.getReferrals()));
    req.setAttribute("treatments", Arrays.asList(c.getTreatments()));
    req.setAttribute("treatmentMethods", Arrays.asList(c.getTreatmentMethods()));
    req.setAttribute("stock", Arrays.asList(c.getTreatmentStocks()));
    req.setAttribute("item", c);
    req.setAttribute("page_title", "Edit Aggregated Case " + epiDate.getDisplayLabel(this.getClientRequest()));
    render("editComponent.jsp");
  }

  public void failEdit(java.lang.String id) throws java.io.IOException, javax.servlet.ServletException
  {
    this.view(id);
  }

  public void view(java.lang.String id) throws java.io.IOException, javax.servlet.ServletException
  {
    com.terraframe.mojo.constants.ClientRequestIF clientRequest = super.getClientRequest();
    this.view(AggregatedCaseDTO.getView(clientRequest, id));
  }

  public void view(AggregatedCaseViewDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    // Load all of the corresponding grid values
    AggregatedAgeGroupDTO[] ageGroups = AggregatedAgeGroupDTO.getAll(this.getClientSession().getRequest());

    req.setAttribute("ageGroups", Arrays.asList(ageGroups));
    req.setAttribute("diagnostics", Arrays.asList(dto.getDiagnosticMethods()));
    req.setAttribute("referrals", Arrays.asList(dto.getReferrals()));
    req.setAttribute("treatments", Arrays.asList(dto.getTreatments()));
    req.setAttribute("treatmentMethods", Arrays.asList(dto.getTreatmentMethods()));
    req.setAttribute("stock", Arrays.asList(dto.getTreatmentStocks()));
    req.setAttribute("item", dto);
    req.setAttribute("page_title", "View an Aggregated Case");
    render("viewComponent.jsp");
  }

  public void failView(java.lang.String id) throws java.io.IOException, javax.servlet.ServletException
  {
    this.viewAll();
  }

  @Override
  public void search() throws IOException, ServletException
  {
    ClientRequestIF clientRequest = super.getClientSession().getRequest();

    List<PeriodTypeMasterDTO> allItems = PeriodTypeDTO.allItems(clientRequest);

    req.setAttribute("periodType", allItems);
    req.setAttribute("checkedType", PeriodTypeDTO.MONTH.getName());
    req.setAttribute("page_title", "Search for an Aggregated Case");

    render("searchComponent.jsp");
  }

  public void searchByGeoEntityAndDate(GeoEntityDTO geoEntity, EpiDateDTO date,
      AggregatedAgeGroupDTO ageGroup) throws IOException, ServletException
  {
    ClientRequestIF request = this.getClientSession().getRequest();
    String label = date.getDisplayLabel(request);

    AggregatedCaseViewDTO c = AggregatedCaseDTO.searchByGeoEntityAndEpiDate(this.getClientRequest(), geoEntity, date.getPeriodType().get(0), date.getPeriod(), date.getEpiYear(), ageGroup);
    AggregatedAgeGroupDTO[] ageGroups = AggregatedAgeGroupDTO.getAll(request);

    String jsp = "createComponent.jsp";
    req.setAttribute("page_title", "New Aggregated Case " + label);

    if (c.hasCaseId())
    {
      jsp = "viewComponent.jsp";
      req.setAttribute("page_title", "Aggregated Case " + label);
    }

    // Load all of the corresponding grid values
    req.setAttribute("diagnostics", Arrays.asList(c.getDiagnosticMethods()));
    req.setAttribute("referrals", Arrays.asList(c.getReferrals()));
    req.setAttribute("treatments", Arrays.asList(c.getTreatments()));
    req.setAttribute("treatmentMethods", Arrays.asList(c.getTreatmentMethods()));
    req.setAttribute("stock", Arrays.asList(c.getTreatmentStocks()));
    req.setAttribute("ageGroups", Arrays.asList(ageGroups));
    req.setAttribute("item", c);
    render(jsp);
  }

  public void selectAgeGroup(String geoId, String periodType, Integer period, Integer year)
      throws IOException, ServletException
  {
    ClientRequestIF request = this.getClientSession().getRequest();
    AggregatedAgeGroupDTO[] ageGroups = AggregatedAgeGroupDTO.getAll(request);

    req.setAttribute("geoId", geoId);
    req.setAttribute("periodType", periodType);
    req.setAttribute("period", period);
    req.setAttribute("year", year);
    req.setAttribute("ageGroups", Arrays.asList(ageGroups));

    render("selectComponent.jsp");
  }

  public void searchByGeoIdAndEpiWeek(String geoId, String periodType, Integer period, Integer year,
      AggregatedAgeGroupDTO ageGroup) throws IOException, ServletException
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

  private void validateParameters(String geoId, String periodType, Integer period, Integer year,
      AggregatedAgeGroupDTO ageGroup)
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

  public void failSearchByGeoIdAndEpiWeek(String geoId, String periodType, String period, String year,
      AggregatedAgeGroupDTO ageGroup) throws IOException, ServletException
  {
    ClientRequestIF clientRequest = super.getClientSession().getRequest();
    List<PeriodTypeMasterDTO> allItems = PeriodTypeDTO.allItems(clientRequest);

    req.setAttribute("periodType", allItems);
    req.setAttribute("period", period);
    req.setAttribute("year", year);
    req.setAttribute("geoId", geoId);
    req.setAttribute("checkedType", periodType);
    req.setAttribute("ageGroup", ageGroup);
    req.setAttribute("page_title", "Search for an Aggregated Case");

    render("searchComponent.jsp");
  }

  @Override
  public void failSearch() throws IOException, ServletException
  {
    // This should never occur
    super.failSearch();
  }
}
