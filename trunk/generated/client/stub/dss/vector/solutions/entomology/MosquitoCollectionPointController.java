package dss.vector.solutions.entomology;

import java.io.IOException;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.ServletException;

import com.terraframe.mojo.ProblemExceptionDTO;
import com.terraframe.mojo.business.ProblemDTOIF;
import com.terraframe.mojo.constants.ClientRequestIF;

import dss.vector.solutions.geo.generated.GeoEntityDTO;
import dss.vector.solutions.util.ErrorUtility;

public class MosquitoCollectionPointController extends MosquitoCollectionPointControllerBase implements
    com.terraframe.mojo.generation.loader.Reloadable
{
  public static final String JSP_DIR          = "WEB-INF/dss/vector/solutions/entomology/MosquitoCollectionPoint/";

  public static final String LAYOUT           = JSP_DIR + "layout.jsp";

  private static final long  serialVersionUID = 1235073586764L;

  public MosquitoCollectionPointController(javax.servlet.http.HttpServletRequest req,
      javax.servlet.http.HttpServletResponse resp, java.lang.Boolean isAsynchronous)
  {
    super(req, resp, isAsynchronous, JSP_DIR, LAYOUT);
  }

  public void create(MosquitoCollectionPointDTO dto) throws java.io.IOException,
      javax.servlet.ServletException
  {
    try
    {
      dto.apply();
      this.view(dto.getId());
    }
    catch (com.terraframe.mojo.ProblemExceptionDTO e)
    {
      this.failCreate(dto);
    }
  }

  public void failCreate(MosquitoCollectionPointDTO dto) throws java.io.IOException,
      javax.servlet.ServletException
  {
    req.setAttribute("item", dto);
    
    render("createComponent.jsp");
  }

  public void newInstance() throws java.io.IOException, javax.servlet.ServletException
  {
    ClientRequestIF clientRequest = super.getClientRequest();
    MosquitoCollectionPointDTO dto = new MosquitoCollectionPointDTO(clientRequest);
    req.setAttribute("item", dto);
    
    render("createComponent.jsp");
  }

  public void failNewInstance() throws java.io.IOException, javax.servlet.ServletException
  {
    this.viewAll();
  }

  public void cancel(MosquitoCollectionPointDTO dto) throws java.io.IOException,
      javax.servlet.ServletException
  {
    dto.unlock();
    this.view(dto.getId());
  }

  public void failCancel(MosquitoCollectionPointDTO dto) throws java.io.IOException,
      javax.servlet.ServletException
  {
    resp.sendError(500);
  }

  public void viewPage(java.lang.String sortAttribute, java.lang.Boolean isAscending,
      java.lang.Integer pageSize, java.lang.Integer pageNumber) throws java.io.IOException,
      javax.servlet.ServletException
  {
    ClientRequestIF clientRequest = super.getClientRequest();
    MosquitoCollectionPointQueryDTO query = MosquitoCollectionPointDTO.getAllInstances(clientRequest,
        sortAttribute, isAscending, pageSize, pageNumber);
    req.setAttribute("query", query);
    
    render("viewAllComponent.jsp");
  }

  public void failViewPage(java.lang.String sortAttribute, java.lang.String isAscending,
      java.lang.String pageSize, java.lang.String pageNumber) throws java.io.IOException,
      javax.servlet.ServletException
  {
    resp.sendError(500);
  }

  public void view(java.lang.String id) throws java.io.IOException, javax.servlet.ServletException
  {
    ClientRequestIF clientRequest = super.getClientRequest();
    req.setAttribute("item", MosquitoCollectionPointDTO.get(clientRequest, id));
    
    render("viewComponent.jsp");
  }

  public void failView(java.lang.String id) throws java.io.IOException, javax.servlet.ServletException
  {
    this.viewAll();
  }

  public void edit(java.lang.String id) throws java.io.IOException, javax.servlet.ServletException
  {
    MosquitoCollectionPointDTO dto = MosquitoCollectionPointDTO.lock(super.getClientRequest(), id);
    req.setAttribute("item", dto);
    
    render("editComponent.jsp");
  }

  public void failEdit(java.lang.String id) throws java.io.IOException, javax.servlet.ServletException
  {
    this.view(id);
  }

  public void update(MosquitoCollectionPointDTO dto) throws java.io.IOException,
      javax.servlet.ServletException
  {
    try
    {
      dto.apply();
      this.view(dto.getId());
    }
    catch (com.terraframe.mojo.ProblemExceptionDTO e)
    {
      this.failUpdate(dto);
    }
  }

  public void failUpdate(MosquitoCollectionPointDTO dto) throws java.io.IOException,
      javax.servlet.ServletException
  {
    req.setAttribute("item", dto);
    
    render("editComponent.jsp");
  }

  public void delete(MosquitoCollectionPointDTO dto) throws java.io.IOException,
      javax.servlet.ServletException
  {
    try
    {
      dto.delete();
      this.viewAll();
    }
    catch (com.terraframe.mojo.ProblemExceptionDTO e)
    {
      this.failDelete(dto);
    }
  }

  public void failDelete(MosquitoCollectionPointDTO dto) throws java.io.IOException,
      javax.servlet.ServletException
  {
    req.setAttribute("item", dto);
    
    render("editComponent.jsp");
  }

  public void viewAll() throws java.io.IOException, javax.servlet.ServletException
  {
    ClientRequestIF clientRequest = super.getClientRequest();
    MosquitoCollectionPointQueryDTO query = MosquitoCollectionPointDTO.getAllInstances(clientRequest,
        null, true, 20, 1);
    req.setAttribute("query", query);
    
    render("viewAllComponent.jsp");
  }

  public void failViewAll() throws java.io.IOException, javax.servlet.ServletException
  {
    resp.sendError(500);
  }

  @Override
  public void searchByGeoIdAndDate(String geoId, Date startDate, Date endDate) throws IOException,
      ServletException
  {
    try
    {
      validateParameters(geoId, startDate, endDate);

      GeoEntityDTO geoEntity = GeoEntityDTO.searchByGeoId(super.getClientRequest(), geoId);
      if (geoEntity == null)
      {
        this.newInstance();
      }
      else
      {
        this.searchByGeoEntityAndDate(geoEntity, startDate, endDate);
      }
    }
    catch (ProblemExceptionDTO e)
    {
      ErrorUtility.prepareProblems(e, req);

      String failGeoId = ( geoId == null ) ? null : geoId.toString();
      String failStartDate = ( startDate == null ) ? null : startDate.toString();
      String failEndDate = ( endDate == null ) ? null : endDate.toString();

      this.failSearchByGeoIdAndDate(failGeoId, failStartDate, failEndDate);
    }
    catch (Throwable t)
    {
      ErrorUtility.prepareThrowable(t, req);

      String failGeoId = ( geoId == null ) ? null : geoId.toString();
      String failStartDate = ( startDate == null ) ? null : startDate.toString();
      String failEndDate = ( endDate == null ) ? null : endDate.toString();

      this.failSearchByGeoIdAndDate(failGeoId, failStartDate, failEndDate);
    }

  }

  private void validateParameters(String geoId, Date startDate, Date endDate)
  {
    List<ProblemDTOIF> problems = new LinkedList<ProblemDTOIF>();

    if (geoId == null)
    {
      ClientRequestIF request = this.getClientSession().getRequest();
      problems.add(new RequiredGeoIdProblemDTO(request, req.getLocale()));
    }

    if (startDate == null)
    {
      ClientRequestIF request = this.getClientSession().getRequest();
      problems.add(new RequiredStartDateProblemDTO(request, req.getLocale()));
    }

    if (endDate == null)
    {
      ClientRequestIF request = this.getClientSession().getRequest();
      problems.add(new RequiredEndDateProblemDTO(request, req.getLocale()));
    }

    if(problems.size() > 0)
    {
      throw new ProblemExceptionDTO("", problems);
    }
  }

  public void failSearchByGeoIdAndDate(String geoId, String startDate, String endDate) throws java.io.IOException, javax.servlet.ServletException
  {
    this.search();
  }

  public void search() throws java.io.IOException, javax.servlet.ServletException
  {    
    render("searchComponent.jsp");
  }

  public void searchByGeoEntityAndDate(GeoEntityDTO geoEntity, Date startDate, Date endDate)
      throws IOException, ServletException
  {
    MorphologicalSpecieGroupViewDTO[] collection = MosquitoCollectionPointDTO.searchByGeoEntityAndDate(
        super.getClientRequest(), geoEntity, startDate, endDate);
    String jsp = "viewAllComponent.jsp";
    
    req.setAttribute("geoEntity", geoEntity);
    req.setAttribute("startDate", startDate);
    req.setAttribute("endDate", endDate);
    req.setAttribute("collection_points", collection);

    render(jsp);
  }
}
