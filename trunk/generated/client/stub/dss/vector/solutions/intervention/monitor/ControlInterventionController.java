package dss.vector.solutions.intervention.monitor;

import java.io.IOException;
import java.util.Arrays;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.runwaysdk.ProblemExceptionDTO;
import com.runwaysdk.constants.ClientRequestIF;

import dss.vector.solutions.geo.GeoHierarchyDTO;
import dss.vector.solutions.geo.GeoHierarchyViewDTO;
import dss.vector.solutions.geo.generated.GeoEntityDTO;
import dss.vector.solutions.util.DefaultConverter;
import dss.vector.solutions.util.ErrorUtility;
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
    String id = dto.getValue(attribute);

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
  public void searchByView(String sortAttribute, Boolean isAscending, Integer pageSize, Integer pageNumber, ControlInterventionViewDTO view) throws IOException, ServletException
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
}
