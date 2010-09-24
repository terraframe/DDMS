package dss.vector.solutions.intervention.monitor;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.runwaysdk.constants.ClientRequestIF;
import com.runwaysdk.session.AttributeReadPermissionExceptionDTO;

import dss.vector.solutions.entomology.PupalCollectionViewDTO;
import dss.vector.solutions.geo.GeoHierarchyDTO;
import dss.vector.solutions.geo.GeoHierarchyViewDTO;
import dss.vector.solutions.geo.generated.GeoEntityDTO;
import dss.vector.solutions.util.DefaultConverter;
import dss.vector.solutions.util.ErrorUtility;
import dss.vector.solutions.util.FacadeDTO;
import dss.vector.solutions.util.FileDownloadUtil;
import dss.vector.solutions.util.RedirectUtility;
import dss.vector.solutions.util.yui.DataGrid;

public class ControlInterventionController extends ControlInterventionControllerBase implements com.runwaysdk.generation.loader.Reloadable
{
  private static final long  serialVersionUID = -905723824;

  public static final String JSP_DIR          = "WEB-INF/dss/vector/solutions/intervention/monitor/ControlIntervention/";

  public static final String LAYOUT           = "/layout.jsp";

  public ControlInterventionController(HttpServletRequest req, HttpServletResponse resp, Boolean isAsynchronous)
  {
    super(req, resp, isAsynchronous, JSP_DIR, LAYOUT);
  }

  public void view(String id) throws IOException, ServletException
  {
    try
    {
      RedirectUtility utility = new RedirectUtility(req, resp);
      utility.put("id", id);
      utility.checkURL(this.getClass().getSimpleName(), "view");

      view(ControlInterventionDTO.getView(super.getClientRequest(), id));
    }
    catch (Throwable t)
    {
      boolean redirected = ErrorUtility.prepareThrowable(t, req, resp, this.isAsynchronous());

      if (!redirected)
      {
        this.failView(id);
      }
    }

  }

  public void view(ControlInterventionViewDTO dto) throws IOException, ServletException
  {
    ClientRequestIF clientRequest = this.getClientRequest();

    GeoEntityDTO entity = dto.getGeoEntity();
    GeoHierarchyViewDTO[] universals = GeoHierarchyViewDTO.getUrbanHierarchies(clientRequest, entity.getId());

    this.setUniversal(dto, clientRequest, ControlInterventionViewDTO.INDIVIDULPREMISEUNIVERSAL);
    this.setUniversal(dto, clientRequest, ControlInterventionViewDTO.AGGREGATEDPREMISEUNIVERSAL);

    req.setAttribute("universals", Arrays.asList(universals));
    req.setAttribute("item", dto);
    render("viewComponent.jsp");
  }

  private void setUniversal(ControlInterventionViewDTO dto, ClientRequestIF clientRequest, String attribute)
  {
    GeoHierarchyViewDTO universal = null;
    
    String id = null;
    
    try
    {
      id = dto.getValue(attribute);
    }
    catch (AttributeReadPermissionExceptionDTO e)
    {
      // Do nothing
    }

    if (id != null && id.length() > 0)
    {
      universal = GeoHierarchyDTO.getViewForGeoHierarchy(clientRequest, id);
    }

    req.setAttribute(attribute, universal);
  }

  public void failView(String id) throws IOException, ServletException
  {
    this.search();
  }

  @Override
  public void search() throws IOException, ServletException
  {
    RedirectUtility utility = new RedirectUtility(req, resp);
    utility.checkURL(this.getClass().getSimpleName(), "search");

    ClientRequestIF clientRequest = this.getClientRequest();

    this.search(new ControlInterventionViewDTO(clientRequest));
  }

  private void search(ControlInterventionViewDTO item) throws IOException, ServletException
  {
    ControlInterventionViewQueryDTO query = ControlInterventionViewDTO.getMostRecent(this.getClientRequest());

    this.setupDates(item);

    req.setAttribute("query", query);
    req.setAttribute("item", item);

    render("searchComponent.jsp");
  }

  @Override
  public void searchByParameters(String sortAttribute, Boolean isAscending, Integer pageSize, Integer pageNumber, String geoId, Date startDate, Date endDate) throws IOException, ServletException
  {
    ControlInterventionViewDTO view = new ControlInterventionViewDTO(this.getClientRequest());
    view.setValue(PupalCollectionViewDTO.GEOENTITY, geoId);
    view.setStartDate(startDate);
    view.setEndDate(endDate);

    this.searchByView(sortAttribute, isAscending, pageSize, pageNumber, view);
  }

  @Override
  public void searchByView(String sortAttribute, Boolean isAscending, Integer pageSize, Integer pageNumber, ControlInterventionViewDTO view) throws IOException, ServletException
  {
    try
    {
      isAscending = ( isAscending == null ? true : isAscending );
      pageSize = ( pageSize == null ? 15 : pageSize );
      pageNumber = ( pageNumber == null ? 1 : pageNumber );

      ClientRequestIF request = this.getClientRequest();

      ControlInterventionViewQueryDTO query = ControlInterventionViewDTO.search(request, view, sortAttribute, isAscending, pageSize, pageNumber);

      this.setupDates(view);
      req.setAttribute("query", query);
      req.setAttribute("item", view);

      render("searchComponent.jsp");
    }
    catch (Throwable t)
    {
      boolean redirected = ErrorUtility.prepareThrowable(t, req, resp, this.isAsynchronous());
      
      if(!redirected)
      {
        this.failSearchByView(null, null, null, null, view);
      }
    }
  }
  
  @Override
  public void failSearchByView(String sortAttribute, String isAscending, String pageSize, String pageNumber, ControlInterventionViewDTO view) throws IOException, ServletException
  {
    this.search(view);
  }

  private void setupDates(ControlInterventionViewDTO dto)
  {
    if (dto.getStartDate() != null)
    {
      String startDate = new DefaultConverter(Date.class).format(dto.getStartDate(), req.getLocale());
      req.setAttribute("startDate", startDate);
    }

    if (dto.getEndDate() != null)
    {
      String endDate = new DefaultConverter(Date.class).format(dto.getEndDate(), req.getLocale());
      req.setAttribute("endDate", endDate);
    }
  }

  @Override
  public void forward(ControlInterventionViewDTO view) throws IOException, ServletException
  {
    try
    {
      ControlInterventionViewDTO dto = ControlInterventionViewDTO.getIntervention(this.getClientRequest(), view);

      String concreteId = dto.getConcreteId();

      if (concreteId == null || concreteId.equals(""))
      {
        // Ensure the user has permissions to create a ControlIntervention
        new ControlInterventionDTO(this.getClientRequest());
      }

      this.view(dto);
    }
    catch (Throwable t)
    {
      boolean redirect = ErrorUtility.prepareThrowable(t, req, resp, this.isAsynchronous());

      if (!redirect)
      {
        this.failForward(view);
      }
    }
  }

  @Override
  public void failForward(ControlInterventionViewDTO view) throws IOException, ServletException
  {
    this.search(view);
  }

  @Override
  public void delete(ControlInterventionViewDTO view) throws IOException, ServletException
  {
    try
    {
      view.deleteConcrete();

      this.search();
    }
    catch (Throwable t)
    {
      boolean redirect = ErrorUtility.prepareThrowable(t, req, resp, this.isAsynchronous());

      if (!redirect)
      {
        this.failDelete(view);
      }
    }
  }

  @Override
  public void failDelete(ControlInterventionViewDTO view) throws IOException, ServletException
  {
    this.view(view);
  }

  @Override
  public void getIndividualPremise(ControlInterventionViewDTO view) throws IOException, ServletException
  {
    try
    {
      ClientRequestIF request = this.getClientRequest();

      DataGrid grid = new IndividualPremiseGridBuilder(request, view).build();

      req.setAttribute("view", view);
      req.setAttribute("grid", grid);

      render("viewIndividualPremiseComponent.jsp");
    }
    catch (Throwable t)
    {
      boolean redirect = ErrorUtility.prepareThrowable(t, req, resp, this.isAsynchronous());

      if (!redirect)
      {
        this.failGetIndividualPremise(view);
      }
    }
  }

  @Override
  public void failGetIndividualPremise(ControlInterventionViewDTO view) throws IOException, ServletException
  {
    this.view(view);
  }

  @Override
  public void getAggregatedPremise(ControlInterventionViewDTO view) throws IOException, ServletException
  {
    try
    {
      ClientRequestIF request = this.getClientRequest();

      DataGrid grid = new AggregatedPremiseGridBuilder(request, view).build();

      req.setAttribute("view", view);
      req.setAttribute("grid", grid);

      render("viewAggregatedPremiseComponent.jsp");
    }
    catch (Throwable t)
    {
      boolean redirect = ErrorUtility.prepareThrowable(t, req, resp, this.isAsynchronous());

      if (!redirect)
      {
        this.failGetIndividualPremise(view);
      }
    }
  }

  @Override
  public void failGetAggregatedPremise(ControlInterventionViewDTO view) throws IOException, ServletException
  {
    this.view(view);
  }

  @Override
  public void getPersonIntervention(ControlInterventionViewDTO view) throws IOException, ServletException
  {
    try
    {
      ClientRequestIF request = this.getClientRequest();

      DataGrid grid = new PersonInterventionGridBuilder(request, view).build();

      req.setAttribute("view", view);
      req.setAttribute("grid", grid);

      render("viewPersonInterventionComponent.jsp");
    }
    catch (Throwable t)
    {
      boolean redirect = ErrorUtility.prepareThrowable(t, req, resp, this.isAsynchronous());

      if (!redirect)
      {
        this.failGetIndividualPremise(view);
      }
    }
  }

  @Override
  public void failGetPersonIntervention(ControlInterventionViewDTO view) throws IOException, ServletException
  {
    this.view(view);
  }

  @Override
  public void getInsecticideIntervention(ControlInterventionViewDTO view) throws IOException, ServletException
  {
    try
    {
      ClientRequestIF request = this.getClientRequest();

      DataGrid grid = new InsecticideInterventionGridBuilder(request, view).build();

      req.setAttribute("view", view);
      req.setAttribute("grid", grid);

      render("viewInsecticideInterventionComponent.jsp");
    }
    catch (Throwable t)
    {
      boolean redirect = ErrorUtility.prepareThrowable(t, req, resp, this.isAsynchronous());

      if (!redirect)
      {
        this.failGetIndividualPremise(view);
      }
    }
  }

  @Override
  public void failGetInsecticideIntervention(ControlInterventionViewDTO view) throws IOException, ServletException
  {
    this.view(view);
  }

  @Override
  public void exportExcelTemplate() throws IOException, ServletException
  {
    try
    {
      ClientRequestIF clientRequest = this.getClientRequest();

      InputStream stream = FacadeDTO.exportControlIntervention(clientRequest);

      FileDownloadUtil.writeXLS(resp, "ControlInterventionExcelView", stream);
    }
    catch (Throwable t)
    {
      resp.getWriter().write(t.getLocalizedMessage());
    }
  }
}
