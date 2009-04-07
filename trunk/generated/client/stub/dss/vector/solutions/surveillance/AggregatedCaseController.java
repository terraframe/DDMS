package dss.vector.solutions.surveillance;

import java.io.IOException;
import java.util.Collection;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.LinkedList;

import javax.servlet.ServletException;

import com.terraframe.mojo.ProblemExceptionDTO;
import com.terraframe.mojo.constants.ClientRequestIF;

import dss.vector.solutions.geo.GeoEntityTreeController;
import dss.vector.solutions.geo.generated.EarthDTO;
import dss.vector.solutions.geo.generated.GeoEntityDTO;
import dss.vector.solutions.util.DateConverter;
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
  public void create(AggregatedCaseDTO dto, CaseTreatmentDTO[] treatments,
      CaseTreatmentMethodDTO[] treatmentMethods, CaseTreatmentStockDTO[] stock,
      CaseDiagnosticDTO[] diagnosticMethods, CaseReferralDTO[] referrals) throws IOException,
      ServletException
  {
    try
    {
      dto.applyAll(treatments, treatmentMethods, stock, diagnosticMethods, referrals);
      this.view(dto.getId());
    }
    catch(ProblemExceptionDTO e)
    {
      ErrorUtility.prepareProblems(e, req);

      this.failCreate(dto, treatments, treatmentMethods, stock, diagnosticMethods, referrals);
    }
    catch(Throwable t)
    {
      ErrorUtility.prepareThrowable(t, req);

      this.failCreate(dto, treatments, treatmentMethods, stock, diagnosticMethods, referrals);
    }
  }

  @Override
  public void failCreate(AggregatedCaseDTO dto, CaseTreatmentDTO[] treatments,
      CaseTreatmentMethodDTO[] treatmentMethods, CaseTreatmentStockDTO[] stock,
      CaseDiagnosticDTO[] diagnosticMethods, CaseReferralDTO[] referrals) throws IOException,
      ServletException
  {
    req.setAttribute("diagnostics", diagnosticMethods);
    req.setAttribute("referrals", referrals);
    req.setAttribute("treatments", treatments);
    req.setAttribute("treatmentMethods", treatmentMethods);
    req.setAttribute("stock", stock);
    req.setAttribute("item", dto);
    req.setAttribute("page_title", "Error Creating Aggregated Case");
    render("createComponent.jsp");
  }

  @Override
  public void update(AggregatedCaseDTO dto, CaseTreatmentDTO[] treatments,
      CaseTreatmentMethodDTO[] treatmentMethods, CaseTreatmentStockDTO[] stock,
      CaseDiagnosticDTO[] diagnosticMethods, CaseReferralDTO[] referrals) throws IOException,
      ServletException
  {
    try
    {
      dto.applyAll(treatments, treatmentMethods, stock, diagnosticMethods, referrals);
      this.view(dto.getId());
    }
    catch(ProblemExceptionDTO e)
    {
      ErrorUtility.prepareProblems(e, req);

      this.failUpdate(dto, treatments, treatmentMethods, stock, diagnosticMethods, referrals);
    }
    catch(Throwable t)
    {
      ErrorUtility.prepareThrowable(t, req);

      this.failUpdate(dto, treatments, treatmentMethods, stock, diagnosticMethods, referrals);
    }
  }

  @Override
  public void failUpdate(AggregatedCaseDTO dto, CaseTreatmentDTO[] treatments,
      CaseTreatmentMethodDTO[] treatmentMethods, CaseTreatmentStockDTO[] stock,
      CaseDiagnosticDTO[] diagnosticMethods, CaseReferralDTO[] referrals) throws IOException,
      ServletException
  {
    req.setAttribute("diagnostics", diagnosticMethods);
    req.setAttribute("referrals", referrals);
    req.setAttribute("treatments", treatments);
    req.setAttribute("treatmentMethods", treatmentMethods);
    req.setAttribute("stock", stock);
    req.setAttribute("item", dto);
    req.setAttribute("page_title", "Error Updating Aggregated Case Controller");
    render("editComponent.jsp");
  }

  @Override
  public void newInstance(AggregatedAgeGroupDTO ageGroup) throws IOException, ServletException
  {
    com.terraframe.mojo.constants.ClientRequestIF clientRequest = super.getClientRequest();
    AggregatedCaseDTO dto = new AggregatedCaseDTO(clientRequest);
    req.setAttribute("item", dto);
    req.setAttribute("page_title", "Create a new Aggregated Case");
    render("createComponent.jsp");
  }

  public void failNewInstance(AggregatedAgeGroupDTO ageGroup) throws java.io.IOException,
      javax.servlet.ServletException
  {
    this.viewAll();
  }

  public void viewPage(java.lang.String sortAttribute, java.lang.Boolean isAscending,
      java.lang.Integer pageSize, java.lang.Integer pageNumber) throws java.io.IOException,
      javax.servlet.ServletException
  {
    com.terraframe.mojo.constants.ClientRequestIF clientRequest = super.getClientRequest();
    dss.vector.solutions.surveillance.AggregatedAgeGroupQueryDTO query = dss.vector.solutions.surveillance.AggregatedAgeGroupDTO
        .getAllInstances(clientRequest, sortAttribute, isAscending, pageSize, pageNumber);
    req.setAttribute("query", query);
    req.setAttribute("page_title", "View All AggregatedAgeGroupController Objects");
    render("viewAllComponent.jsp");
  }

  public void failViewPage(java.lang.String sortAttribute, java.lang.String isAscending,
      java.lang.String pageSize, java.lang.String pageNumber) throws java.io.IOException,
      javax.servlet.ServletException
  {
    resp.sendError(500);
  }

  public void cancel(AggregatedCaseDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    dto.unlock();
    this.view(dto);
  }

  public void failCancel(AggregatedCaseDTO dto) throws java.io.IOException,
      javax.servlet.ServletException
  {
    resp.sendError(500);
  }

  public void delete(AggregatedCaseDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    try
    {
      dto.delete();
      this.search();
    }
    catch(ProblemExceptionDTO e)
    {
      ErrorUtility.prepareProblems(e, req);

      this.failDelete(dto);
    }
    catch(Throwable t)
    {
      ErrorUtility.prepareThrowable(t, req);

      this.failDelete(dto);
    }
  }
  public void failDelete(AggregatedCaseDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    req.setAttribute("diagnostics", this.getDiagnosticRelationships(dto));
    req.setAttribute("referrals", this.getReferralRelationships(dto));
    req.setAttribute("treatments", this.getTreatmentRelationships(dto));
    req.setAttribute("treatmentMethods", this.getTreatmentMethodRelationships(dto));
    req.setAttribute("stock", this.getTreatmentStockRelationships(dto));
    req.setAttribute("item", dto);
    req.setAttribute("page_title", "Edit Aggregated Case");
    render("editComponent.jsp");
  }


  public void viewAll() throws java.io.IOException, javax.servlet.ServletException
  {
    com.terraframe.mojo.constants.ClientRequestIF clientRequest = super.getClientRequest();
    AggregatedCaseQueryDTO query = AggregatedCaseDTO.getAllInstances(clientRequest, null, true, 20, 1);
    req.setAttribute("query", query);
    req.setAttribute("page_title", "View All Aggregated Cases");
    render("viewAllComponent.jsp");
  }

  public void failViewAll() throws java.io.IOException, javax.servlet.ServletException
  {
    resp.sendError(500);
  }

  public void edit(java.lang.String id) throws java.io.IOException, javax.servlet.ServletException
  {
    AggregatedCaseDTO c = AggregatedCaseDTO.lock(this.getClientRequest(), id);

    // Load all of the corresponding grid values
    req.setAttribute("diagnostics", this.getDiagnosticRelationships(c));
    req.setAttribute("referrals", this.getReferralRelationships(c));
    req.setAttribute("treatments", this.getTreatmentRelationships(c));
    req.setAttribute("treatmentMethods", this.getTreatmentMethodRelationships(c));
    req.setAttribute("stock", this.getTreatmentStockRelationships(c));
    req.setAttribute("item", c);
    req.setAttribute("page_title", "Edit Aggregated Case Controller");
    render("editComponent.jsp");
  }

  public void failEdit(java.lang.String id) throws java.io.IOException, javax.servlet.ServletException
  {
    this.view(id);
  }

  public void view(java.lang.String id) throws java.io.IOException, javax.servlet.ServletException
  {
    com.terraframe.mojo.constants.ClientRequestIF clientRequest = super.getClientRequest();
    this.view(AggregatedCaseDTO.get(clientRequest, id));
  }

  public void view(AggregatedCaseDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    // Load all of the corresponding grid values
    req.setAttribute("diagnostics", this.getDiagnosticRelationships(dto));
    req.setAttribute("referrals", this.getReferralRelationships(dto));
    req.setAttribute("treatments", this.getTreatmentRelationships(dto));
    req.setAttribute("treatmentMethods", this.getTreatmentMethodRelationships(dto));
    req.setAttribute("stock", this.getTreatmentStockRelationships(dto));
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
    ClientRequestIF clientRequest = super.getClientRequest();

    EarthDTO earth = EarthDTO.getEarthInstance(clientRequest);

    AggregatedAgeGroupQueryDTO query = AggregatedAgeGroupDTO.getAllInstances(clientRequest,
        AggregatedAgeGroupDTO.STARTAGE, true, 0, 0);
    req.setAttribute(GeoEntityTreeController.ROOT_GEO_ENTITY_ID, earth.getId());
    req.setAttribute("ageGroup", query.getResultSet());
    req.setAttribute("page_title", "Search for an Aggregated Case");
    render("searchComponent.jsp");
  }

  @Override
  public void searchByGeoIdAndDate(String geoId, Date startDate, Date endDate,
      AggregatedAgeGroupDTO ageGroup) throws IOException, ServletException
  {
    GeoEntityDTO geoEntity = GeoEntityDTO.searchByGeoId(super.getClientRequest(), geoId);

    if (geoEntity == null)
    {
      this.viewAll();
    }
    else
    {
      this.searchByGeoEntityAndDate(geoEntity, startDate, endDate, ageGroup);
    }
  }

  public void failSearchByGeoIdAndDate(String geoId, String startDate, String endDate,
      AggregatedAgeGroupDTO ageGroup) throws java.io.IOException, javax.servlet.ServletException
  {
    try
    {
      Date start = (Date) new DateConverter("Start Date")
          .parse(startDate, this.getRequest().getLocale());
      Date end = (Date) new DateConverter("End Date").parse(endDate, this.getRequest().getLocale());
      this.searchByGeoIdAndDate(geoId, start, end, ageGroup);
    }
    catch (Exception e)
    {
      this.search();
    }
  }

  public void searchByGeoEntityAndDate(GeoEntityDTO geoEntity, Date startDate, Date endDate, AggregatedAgeGroupDTO ageGroup) throws IOException, ServletException
  {
    AggregatedCaseDTO c = AggregatedCaseDTO.searchByGeoEntityAndDate(super.getClientRequest(),
        geoEntity, startDate, endDate, ageGroup);

    String jsp = "viewComponent.jsp";
    req.setAttribute("page_title", "View Aggregated Case");

    if (c == null)
    {
      c = new AggregatedCaseDTO(this.getClientRequest());
      c.setStartDate(startDate);
      c.setEndDate(endDate);
      c.setStartAge(ageGroup.getStartAge());
      c.setEndAge(ageGroup.getEndAge());
      c.setGeoEntity(geoEntity);

      req.setAttribute("page_title", "Aggregated Case Not Found - Creating New");
      jsp = "createComponent.jsp";
    }

    // Load all of the corresponding grid values
    req.setAttribute("diagnostics", this.getDiagnosticRelationships(c));
    req.setAttribute("referrals", this.getReferralRelationships(c));
    req.setAttribute("treatments", this.getTreatmentRelationships(c));
    req.setAttribute("treatmentMethods", this.getTreatmentMethodRelationships(c));
    req.setAttribute("stock", this.getTreatmentStockRelationships(c));
    req.setAttribute("item", c);
    render(jsp);
  }

  private Collection<CaseDiagnosticDTO> getDiagnosticRelationships(AggregatedCaseDTO c)
  {
    LinkedHashMap<String, CaseDiagnosticDTO> map = new LinkedHashMap<String, CaseDiagnosticDTO>();

    if (!c.isNewInstance())
    {
      for (CaseDiagnosticDTO d : c.getAllDiagnosticMethodRelationships())
      {
        map.put(d.getChildId(), d);
      }
    }

    for (DiagnosticGridDTO d : DiagnosticGridDTO.getAll(this.getClientRequest()))
    {
      if (!map.containsKey(d.getId()))
      {
        map.put(d.getId(), new CaseDiagnosticDTO(this.getClientRequest(), c.getId(), d.getId()));
      }
    }

    return new LinkedList<CaseDiagnosticDTO>(map.values());
  }

  private Collection<CaseReferralDTO> getReferralRelationships(AggregatedCaseDTO c)
  {
    LinkedHashMap<String, CaseReferralDTO> map = new LinkedHashMap<String, CaseReferralDTO>();

    if (!c.isNewInstance())
    {
      for (CaseReferralDTO d : c.getAllReferralRelationships())
      {
        map.put(d.getChildId(), d);
      }
    }

    for (ReferralGridDTO d : ReferralGridDTO.getAll(this.getClientRequest()))
    {
      if (!map.containsKey(d.getId()))
      {
        map.put(d.getId(), new CaseReferralDTO(this.getClientRequest(), c.getId(), d.getId()));
      }
    }

    return new LinkedList<CaseReferralDTO>(map.values());
  }

  private Collection<CaseTreatmentDTO> getTreatmentRelationships(AggregatedCaseDTO c)
  {
    LinkedHashMap<String, CaseTreatmentDTO> map = new LinkedHashMap<String, CaseTreatmentDTO>();

    if (!c.isNewInstance())
    {
      for (CaseTreatmentDTO d : c.getAllTreatmentRelationships())
      {
        map.put(d.getChildId(), d);
      }
    }

    for (TreatmentGridDTO d : TreatmentGridDTO.getAll(this.getClientRequest()))
    {
      if (!map.containsKey(d.getId()))
      {
        map.put(d.getId(), new CaseTreatmentDTO(this.getClientRequest(), c.getId(), d.getId()));
      }
    }

    return new LinkedList<CaseTreatmentDTO>(map.values());
  }

  private Collection<CaseTreatmentMethodDTO> getTreatmentMethodRelationships(AggregatedCaseDTO c)
  {
    LinkedHashMap<String, CaseTreatmentMethodDTO> map = new LinkedHashMap<String, CaseTreatmentMethodDTO>();

    if (!c.isNewInstance())
    {
      for (CaseTreatmentMethodDTO d : c.getAllTreatmentMethodRelationships())
      {
        map.put(d.getChildId(), d);
      }
    }

    for (TreatmentMethodGridDTO d : TreatmentMethodGridDTO.getAll(this.getClientRequest()))
    {
      if (!map.containsKey(d.getId()))
      {
        map.put(d.getId(), new CaseTreatmentMethodDTO(this.getClientRequest(), c.getId(), d.getId()));
      }
    }

    return new LinkedList<CaseTreatmentMethodDTO>(map.values());
  }

  private Collection<CaseTreatmentStockDTO> getTreatmentStockRelationships(AggregatedCaseDTO c)
  {
    LinkedHashMap<String, CaseTreatmentStockDTO> map = new LinkedHashMap<String, CaseTreatmentStockDTO>();

    if (!c.isNewInstance())
    {
      for (CaseTreatmentStockDTO d : c.getAllTreatmentStockRelationships())
      {
        map.put(d.getChildId(), d);
      }
    }

    for (TreatmentGridDTO d : TreatmentGridDTO.getAll(this.getClientRequest()))
    {
      if (!map.containsKey(d.getId()))
      {
        map.put(d.getId(), new CaseTreatmentStockDTO(this.getClientRequest(), c.getId(), d.getId()));
      }
    }

    return new LinkedList<CaseTreatmentStockDTO>(map.values());
  }

}
