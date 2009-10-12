package dss.vector.solutions.entomology;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.terraframe.mojo.ProblemExceptionDTO;
import com.terraframe.mojo.business.ProblemDTOIF;
import com.terraframe.mojo.constants.ClientRequestIF;
import com.terraframe.mojo.dataaccess.InvalidIdExceptionDTO;
import com.terraframe.mojo.generation.loader.Reloadable;

import dss.vector.solutions.entomology.assay.AdultDiscriminatingDoseAssayDTO;
import dss.vector.solutions.entomology.assay.AdultDiscriminatingDoseAssayQueryDTO;
import dss.vector.solutions.entomology.assay.KnockDownAssayDTO;
import dss.vector.solutions.entomology.assay.KnockDownAssayQueryDTO;
import dss.vector.solutions.entomology.assay.LarvaeDiscriminatingDoseAssayDTO;
import dss.vector.solutions.entomology.assay.LarvaeDiscriminatingDoseAssayQueryDTO;
import dss.vector.solutions.geo.generated.GeoEntityDTO;
import dss.vector.solutions.ontology.TermDTO;
import dss.vector.solutions.util.ColumnSetup;
import dss.vector.solutions.util.ErrorUtility;
import dss.vector.solutions.util.Halp;
import dss.vector.solutions.util.RedirectUtility;

public class MosquitoCollectionController extends MosquitoCollectionControllerBase implements Reloadable
{
  public static final String JSP_DIR          = "WEB-INF/dss/vector/solutions/entomology/MosquitoCollection/";

  public static final String LAYOUT           = "/layout.jsp";

  private static final long  serialVersionUID = 1235073590401L;

  public MosquitoCollectionController(HttpServletRequest req, HttpServletResponse resp, Boolean isAsynchronous)
  {
    super(req, resp, isAsynchronous, JSP_DIR, LAYOUT);
  }

  public void delete(MosquitoCollectionDTO dto) throws IOException, ServletException
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

  public void failDelete(MosquitoCollectionDTO dto) throws IOException, ServletException
  {
    req.setAttribute("item", dto);

    render("editComponent.jsp");
  }

  public void newInstance() throws IOException, ServletException
  {
    ClientRequestIF clientRequest = super.getClientRequest();

    MosquitoCollectionDTO dto = new MosquitoCollectionDTO(clientRequest);
    req.setAttribute("item", dto);

    render("createComponent.jsp");
  }

  public void failNewInstance() throws IOException, ServletException
  {
    this.viewAll();
  }

  public void update(MosquitoCollectionDTO dto) throws IOException, ServletException
  {
    try
    {
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

  public void failUpdate(MosquitoCollectionDTO dto) throws IOException, ServletException
  {
    req.setAttribute("item", dto);

    render("editComponent.jsp");
  }

  public void failSearchByGeoEntityAndDate(dss.vector.solutions.geo.generated.GeoEntityDTO geoEntity, String collectionDate) throws IOException, ServletException
  {
    resp.sendError(500);
  }

  public void view(String id) throws IOException, ServletException
  {
    // if this method is being accessed from create or edit, redirect so the url
    // will be correct and refresh will
    // not create a new object

    view(MosquitoCollectionDTO.get(super.getClientRequest(), id));
  }

  public void view(MosquitoCollectionDTO dto) throws IOException, ServletException
  {
    RedirectUtility utility = new RedirectUtility(req, resp);
    utility.put("id", dto.getId());
    utility.checkURL(this.getClass().getSimpleName(), "view");

    AdultDiscriminatingDoseAssayQueryDTO ada = dto.getAdultDoseAssays(AdultDiscriminatingDoseAssayDTO.TESTDATE, true, 5, 1);
    LarvaeDiscriminatingDoseAssayQueryDTO lda = dto.getLarvaeDoseAssays(LarvaeDiscriminatingDoseAssayDTO.TESTDATE, true, 5, 1);
    KnockDownAssayQueryDTO kda = dto.getKnockDownAssays(KnockDownAssayDTO.TESTDATE, true, 5, 1);

    req.setAttribute("ada", ada);
    req.setAttribute("lda", lda);
    req.setAttribute("kda", kda);
    req.setAttribute("item", dto);

    this.setupMO(dto);
    
    render("viewComponent.jsp");
  }

  public void viewADAPage(String sortAttribute, Boolean isAscending, Integer pageSize, Integer pageNumber, String collectionId) throws IOException, ServletException
  {
    MosquitoCollectionDTO dto = MosquitoCollectionDTO.get(super.getClientRequest(), collectionId);

    AdultDiscriminatingDoseAssayQueryDTO ada = dto.getAdultDoseAssays(sortAttribute, isAscending, pageSize, pageNumber);
    LarvaeDiscriminatingDoseAssayQueryDTO lda = dto.getLarvaeDoseAssays(LarvaeDiscriminatingDoseAssayDTO.TESTDATE, true, 5, 1);
    KnockDownAssayQueryDTO kda = dto.getKnockDownAssays(KnockDownAssayDTO.TESTDATE, true, 5, 1);

    req.setAttribute("ada", ada);
    req.setAttribute("lda", lda);
    req.setAttribute("kda", kda);
    req.setAttribute("item", dto);

    render("viewComponent.jsp");
  }

  public void viewLDAPage(String sortAttribute, Boolean isAscending, Integer pageSize, Integer pageNumber, String collectionId) throws IOException, ServletException
  {
    MosquitoCollectionDTO dto = MosquitoCollectionDTO.get(super.getClientRequest(), collectionId);

    AdultDiscriminatingDoseAssayQueryDTO ada = dto.getAdultDoseAssays(AdultDiscriminatingDoseAssayDTO.TESTDATE, true, 5, 1);
    KnockDownAssayQueryDTO kda = dto.getKnockDownAssays(KnockDownAssayDTO.TESTDATE, true, 5, 1);
    LarvaeDiscriminatingDoseAssayQueryDTO lda = dto.getLarvaeDoseAssays(sortAttribute, isAscending, pageSize, pageNumber);

    req.setAttribute("ada", ada);
    req.setAttribute("lda", lda);
    req.setAttribute("kda", kda);
    req.setAttribute("item", dto);

    render("viewComponent.jsp");
  }

  public void viewKDAPage(String sortAttribute, Boolean isAscending, Integer pageSize, Integer pageNumber, String collectionId) throws IOException, ServletException
  {
    MosquitoCollectionDTO dto = MosquitoCollectionDTO.get(super.getClientRequest(), collectionId);

    AdultDiscriminatingDoseAssayQueryDTO ada = dto.getAdultDoseAssays(AdultDiscriminatingDoseAssayDTO.TESTDATE, true, 5, 1);
    LarvaeDiscriminatingDoseAssayQueryDTO lda = dto.getLarvaeDoseAssays(LarvaeDiscriminatingDoseAssayDTO.TESTDATE, true, 5, 1);
    KnockDownAssayQueryDTO kda = dto.getKnockDownAssays(sortAttribute, isAscending, pageSize, pageNumber);

    req.setAttribute("ada", ada);
    req.setAttribute("lda", lda);
    req.setAttribute("kda", kda);
    req.setAttribute("item", dto);

    render("viewComponent.jsp");
  }

  public void viewAssays(String id) throws IOException, ServletException
  {
    RedirectUtility utility = new RedirectUtility(req, resp);
    utility.put("id", id);
    utility.checkURL(this.getClass().getSimpleName(), "viewAssays");

    try
    {
      ClientRequestIF request = super.getClientRequest();

      try
      {
        MosquitoViewDTO view = new MosquitoViewDTO(request);
        HashMap<String, ColumnSetup> map = new HashMap<String, ColumnSetup>();

        req.setAttribute("editors", Halp.getEditorDefinitions(view, map));
        req.setAttribute("item", ConcreteMosquitoCollectionDTO.get(request, id));

        render("viewAssaysComponent.jsp");
      }
      catch (InvalidIdExceptionDTO e)
      {
        throw new UnknownCollectionDTO(request, req.getLocale());
      }
    }
    catch (ProblemExceptionDTO e)
    {
      ErrorUtility.prepareProblems(e, req);

      this.failViewAssays(id);
    }
    catch (Throwable t)
    {
      ErrorUtility.prepareThrowable(t, req);

      this.failViewAssays(id);
    }
  }

  @Override
  public void failViewAssays(String id) throws IOException, ServletException
  {
    render("searchAssaysComponent.jsp");
  }

  public void failView(String id) throws IOException, ServletException
  {
    this.viewAll();
  }

  public void cancel(MosquitoCollectionDTO dto) throws IOException, ServletException
  {
    dto.unlock();
    this.view(dto.getId());
  }

  public void failCancel(MosquitoCollectionDTO dto) throws IOException, ServletException
  {
    this.edit(dto.getId());
  }

  public void viewAll() throws IOException, ServletException
  {
    new RedirectUtility(req, resp).checkURL(this.getClass().getSimpleName(), "viewAll");

    ClientRequestIF clientRequest = super.getClientRequest();
    MosquitoCollectionQueryDTO query = MosquitoCollectionDTO.getAllInstances(clientRequest, null, true, 20, 1);
    req.setAttribute("query", query);

    render("viewAllComponent.jsp");
  }

  public void failViewAll() throws IOException, ServletException
  {
    resp.sendError(500);
  }

  public void viewPage(String sortAttribute, Boolean isAscending, Integer pageSize, Integer pageNumber) throws IOException, ServletException
  {
    ClientRequestIF clientRequest = super.getClientRequest();
    MosquitoCollectionQueryDTO query = MosquitoCollectionDTO.getAllInstances(clientRequest, sortAttribute, isAscending, pageSize, pageNumber);
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
      MosquitoCollectionDTO dto = MosquitoCollectionDTO.lock(super.getClientRequest(), id);
      req.setAttribute("item", dto);
      
      this.setupMO(dto);

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

  public void create(MosquitoCollectionDTO dto) throws IOException, ServletException
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

  public void failCreate(MosquitoCollectionDTO dto) throws IOException, ServletException
  {
    req.setAttribute("item", dto);

    render("createComponent.jsp");
  }

  @Override
  public void searchByGeoIdAndDate(String geoId, Date collectionDate, TermDTO collectionMethod) throws IOException, ServletException
  {
    try
    {
      validateParameters(geoId, collectionDate, collectionMethod);

      GeoEntityDTO geoEntity = GeoEntityDTO.searchByGeoId(super.getClientRequest(), geoId);

      this.searchByGeoEntityAndDate(geoEntity, collectionDate, collectionMethod);
    }
    catch (ProblemExceptionDTO e)
    {
      ErrorUtility.prepareProblems(e, req);

      String failDate = ( collectionDate == null ) ? null : collectionDate.toString();
      String failGeoId = ( geoId == null ) ? null : geoId.toString();

      this.failSearchByGeoIdAndDate(failGeoId, failDate, collectionMethod);
    }
    catch (Throwable t)
    {
      ErrorUtility.prepareThrowable(t, req);

      String failDate = ( collectionDate == null ) ? null : collectionDate.toString();
      String failGeoId = ( geoId == null ) ? null : geoId.toString();

      this.failSearchByGeoIdAndDate(failGeoId, failDate, collectionMethod);
    }
  }

  public void failSearchByGeoIdAndDate(String geoId, String collectionDate, TermDTO collectionMethod) throws IOException, ServletException
  {
    this.search();
  }

  public void search() throws IOException, ServletException
  {
    new RedirectUtility(req, resp).checkURL(this.getClass().getSimpleName(), "search");

    ClientRequestIF clientRequest = super.getClientSession().getRequest();
    MosquitoCollectionQueryDTO query = MosquitoCollectionDTO.getAllInstances(clientRequest, "createDate", false, 10, 1);

    req.setAttribute("query", query);

    render("searchComponent.jsp");
  }

  public void searchAssays() throws IOException, ServletException
  {
    new RedirectUtility(req, resp).checkURL(this.getClass().getSimpleName(), "searchAssays");

    render("searchAssaysComponent.jsp");
  }

  @Override
  public void searchByGeoEntityAndDate(GeoEntityDTO geoEntity, Date collectionDate, TermDTO collectionMethod) throws IOException, ServletException
  {
    try
    {
      ClientRequestIF request = super.getClientRequest();

      MosquitoCollectionDTO collection = MosquitoCollectionDTO.searchByGeoEntityAndDateAndCollectionMethod(request, geoEntity, collectionDate, collectionMethod);

      if (collection == null)
      {
        collection = new MosquitoCollectionDTO(request);
        collection.setDateCollected(collectionDate);
        collection.setGeoEntity(geoEntity);
        collection.setCollectionMethod(collectionMethod);

        req.setAttribute("item", collection);
        
        this.setupMO(collection);
        
        render("createComponent.jsp");
      }
      else
      {
        this.setupMO(collection);
        
        this.view(collection);
      }
    }
    catch (ProblemExceptionDTO e)
    {
      String failDate = collectionDate == null ? null : collectionDate.toString();

      ErrorUtility.prepareProblems(e, req);

      this.failSearchByGeoEntityAndDate(geoEntity, failDate, collectionMethod);
    }
    catch (Throwable t)
    {
      String failDate = collectionDate == null ? null : collectionDate.toString();

      ErrorUtility.prepareThrowable(t, req);

      this.failSearchByGeoEntityAndDate(geoEntity, failDate, collectionMethod);
    }
  }

  private void setupMO(MosquitoCollectionDTO dto)
  {
    req.setAttribute("collectionMethod", dto.getCollectionMethod());
  }

  @Override
  public void failSearchByGeoEntityAndDate(GeoEntityDTO geoEntity, String collectionDate, TermDTO collectionMethod) throws IOException, ServletException
  {
    this.search();
  }

  private void validateParameters(String geoId, Date collectionDate, TermDTO collectionMethod)
  {
    List<ProblemDTOIF> problems = new LinkedList<ProblemDTOIF>();

    if (geoId == null)
    {
      ClientRequestIF request = this.getClientSession().getRequest();
      problems.add(new RequiredGeoIdProblemDTO(request, req.getLocale()));
    }

    if (collectionDate == null)
    {
      ClientRequestIF request = this.getClientSession().getRequest();
      problems.add(new RequiredCollectionDateProblemDTO(request, req.getLocale()));
    }

    if (collectionMethod == null)
    {
      ClientRequestIF request = this.getClientSession().getRequest();
      problems.add(new RequiredCollectionMethodProblemDTO(request, req.getLocale()));
    }

    if (problems.size() > 0)
    {
      throw new ProblemExceptionDTO("", problems);
    }
  }
}
