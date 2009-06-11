package dss.vector.solutions.intervention.monitor;

import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.ServletException;

import com.terraframe.mojo.ProblemExceptionDTO;
import com.terraframe.mojo.business.ProblemDTOIF;
import com.terraframe.mojo.constants.ClientRequestIF;

import dss.vector.solutions.geo.generated.GeoEntityDTO;
import dss.vector.solutions.surveillance.PeriodTypeDTO;
import dss.vector.solutions.surveillance.PeriodTypeMasterDTO;
import dss.vector.solutions.surveillance.RequiredGeoIdProblemDTO;
import dss.vector.solutions.surveillance.RequiredPeriodProblemDTO;
import dss.vector.solutions.surveillance.RequiredPeriodTypeProblemDTO;
import dss.vector.solutions.surveillance.RequiredYearProblemDTO;
import dss.vector.solutions.util.ErrorUtility;

public class AggregatedIPTController extends AggregatedIPTControllerBase implements com.terraframe.mojo.generation.loader.Reloadable
{
  public static final String JSP_DIR = "WEB-INF/dss/vector/solutions/intervention/monitor/AggregatedIPT/";
  public static final String LAYOUT = "/layout.jsp";
  
  private static final long serialVersionUID = 1244737056550L;
  
  public AggregatedIPTController(javax.servlet.http.HttpServletRequest req, javax.servlet.http.HttpServletResponse resp, java.lang.Boolean isAsynchronous)
  {
    super(req, resp, isAsynchronous, JSP_DIR, LAYOUT);
  }
  
  public void searchByGeoIdAndPeriod(String geoId, String periodType, java.lang.Integer period, java.lang.Integer year) throws IOException, ServletException
  {
    try
    {
      ClientRequestIF request = this.getClientSession().getRequest();
      validateParameters(geoId, periodType, period, year);

      PeriodTypeDTO type = PeriodTypeDTO.valueOf(periodType);
      GeoEntityDTO geoEntity = GeoEntityDTO.searchByGeoId(this.getClientRequest(), geoId);

      AggregatedIPTViewDTO dto = AggregatedIPTDTO.searchByGeoEntityAndEpiDate(request, geoEntity, type, period, year);

      String jsp = "createComponent.jsp";

      if (dto.hasConcreteId())
      {
        jsp = "viewComponent.jsp";
      }

      // Load all of the corresponding grid values
      this.prepareRelationships(dto);
      req.setAttribute("item", dto);
      render(jsp);
    }
    catch (ProblemExceptionDTO e)
    {
      ErrorUtility.prepareProblems(e, req);

      String failPeriod = period == null ? null : period.toString();
      String failYear = year == null ? null : year.toString();

      this.failSearchByGeoIdAndPeriod(geoId, periodType, failPeriod, failYear);
    }
    catch (Throwable t)
    {
      ErrorUtility.prepareThrowable(t, req);

      String failPeriod = period == null ? null : period.toString();
      String failYear = year == null ? null : year.toString();

      this.failSearchByGeoIdAndPeriod(geoId, periodType, failPeriod, failYear);
    }

  }

  private void prepareRelationships(AggregatedIPTViewDTO dto)
  {
    prepareRelationships(dto.getIPTPatients(), dto.getIPTANCVisits(), dto.getIPTDoses(), dto.getIPTTreatments());
  }
  
  private void prepareRelationships(IPTPatientsDTO[] patients, IPTANCVisitDTO[] visits, IPTDoseDTO[] doses, IPTTreatmentDTO[] treatments)
  {
    req.setAttribute("patients", Arrays.asList(patients));
    req.setAttribute("visits", Arrays.asList(visits));
    req.setAttribute("doses", Arrays.asList(doses));
    req.setAttribute("treatments", Arrays.asList(treatments));   
  }
  
  public void failSearchByGeoIdAndPeriod(String geoId, String periodType, String period, String year) throws IOException, ServletException
  {
    ClientRequestIF clientRequest = super.getClientSession().getRequest();
    List<PeriodTypeMasterDTO> allItems = PeriodTypeDTO.allItems(clientRequest);

    req.setAttribute("periodType", allItems);
    req.setAttribute("period", period);
    req.setAttribute("year", year);
    req.setAttribute("geoId", geoId);
    req.setAttribute("checkedType", periodType);

    render("searchComponent.jsp");  }

  public void search() throws IOException, ServletException
  {
    ClientRequestIF clientRequest = super.getClientSession().getRequest();

    List<PeriodTypeMasterDTO> allItems = PeriodTypeDTO.allItems(clientRequest);

    req.setAttribute("periodType", allItems);
    req.setAttribute("checkedType", PeriodTypeDTO.MONTH.getName());

    render("searchComponent.jsp");
  }
  
  public void failSearch() throws IOException, ServletException
  {
    // This should never occur
    super.failSearch();
  }
  
  public void edit(String id) throws IOException, ServletException
  {
    AggregatedIPTViewDTO dto = AggregatedIPTDTO.lockView(super.getClientRequest(), id);

    this.prepareRelationships(dto);
    req.setAttribute("item", dto);
    render("editComponent.jsp");
  }
  
  public void failEdit(String id) throws IOException, ServletException
  {
    this.view(id);
  }
  
  public void newInstance() throws IOException, ServletException
  {
    ClientRequestIF clientRequest = super.getClientRequest();
    AggregatedIPTViewDTO dto = new AggregatedIPTViewDTO(clientRequest);

    this.prepareRelationships(dto);
    req.setAttribute("item", dto);
    render("createComponent.jsp");
  }
  
  public void failNewInstance() throws IOException, ServletException
  {
    this.viewAll();
  }
  
  public void cancel(AggregatedIPTViewDTO dto) throws IOException, ServletException
  {
    this.view(AggregatedIPTDTO.unlockView(dto.getRequest(), dto.getConcreteId()));
  }
  
  public void failCancel(AggregatedIPTViewDTO dto) throws IOException, ServletException
  {
    this.edit(dto.getId());
  }
  
  public void view(String id) throws IOException, ServletException
  {
    this.view(AggregatedIPTDTO.getView(super.getClientRequest(), id));
  }
  
  public void view(AggregatedIPTViewDTO dto) throws IOException, ServletException
  {
    if (!req.getRequestURI().contains(this.getClass().getName() + ".view.mojo"))
    {
      String path = req.getRequestURL().toString();
      path = path.replaceFirst(req.getServletPath(), "/" + this.getClass().getName() + ".view.mojo");
      path = path.replaceFirst("mojo\\?*.*", "mojo" + "?id=" + dto.getConcreteId());

      resp.sendRedirect(path);
      return;
    }
    
    this.prepareRelationships(dto);
    req.setAttribute("item", dto);
    render("viewComponent.jsp");    
  }
  
  public void failView(String id) throws IOException, ServletException
  {
    this.search();
  }
  
  public void delete(AggregatedIPTViewDTO dto) throws IOException, ServletException
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
  
  public void failDelete(AggregatedIPTViewDTO dto) throws IOException, ServletException
  {
    this.prepareRelationships(dto);
    req.setAttribute("item", dto);
    render("editComponent.jsp");
  }
  
  public void update(AggregatedIPTViewDTO dto, IPTPatientsDTO[] patients, IPTANCVisitDTO[] visits, IPTDoseDTO[] doses, IPTTreatmentDTO[] treatments) throws IOException, ServletException
  {
    try
    {
      dto.applyAll(patients, visits, doses, treatments);
      this.view(dto);
    }
    catch (ProblemExceptionDTO e)
    {
      ErrorUtility.prepareProblems(e, req);

      this.failUpdate(dto, patients, visits, doses, treatments);
    }
    catch (Throwable t)
    {
      ErrorUtility.prepareThrowable(t, req);

      this.failUpdate(dto, patients, visits, doses, treatments);
    }
  }
  
  public void failUpdate(AggregatedIPTViewDTO dto, IPTPatientsDTO[] patients, IPTANCVisitDTO[] visits, IPTDoseDTO[] doses, IPTTreatmentDTO[] treatments) throws IOException, ServletException
  {
    this.prepareRelationships(patients, visits, doses, treatments);
    req.setAttribute("item", dto);
    render("editComponent.jsp");
  }
  
  public void create(AggregatedIPTViewDTO dto, IPTPatientsDTO[] patients, IPTANCVisitDTO[] visits, IPTDoseDTO[] doses, IPTTreatmentDTO[] treatments) throws IOException, ServletException
  {
    try
    {
      dto.applyAll(patients, visits, doses, treatments);
      this.view(dto);
    }
    catch (ProblemExceptionDTO e)
    {
      ErrorUtility.prepareProblems(e, req);

      this.failCreate(dto, patients, visits, doses, treatments);
    }
    catch (Throwable t)
    {
      ErrorUtility.prepareThrowable(t, req);

      this.failCreate(dto, patients, visits, doses, treatments);
    }
  }
  
  public void failCreate(AggregatedIPTViewDTO dto, IPTPatientsDTO[] patients, IPTANCVisitDTO[] visits, IPTDoseDTO[] doses, IPTTreatmentDTO[] treatments) throws IOException, ServletException
  {
    this.prepareRelationships(patients, visits, doses, treatments);
    req.setAttribute("item", dto);
    render("createComponent.jsp");
  }
  
  private void validateParameters(String geoId, String periodType, Integer period, Integer year)
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

    if (problems.size() > 0)
    {
      throw new ProblemExceptionDTO("", problems);
    }
  }

}
