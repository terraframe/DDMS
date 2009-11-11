package dss.vector.solutions.stock;

import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.ServletException;

import com.terraframe.mojo.ProblemExceptionDTO;
import com.terraframe.mojo.business.ProblemDTOIF;
import com.terraframe.mojo.constants.ClientRequestIF;

import dss.vector.solutions.entomology.RequiredEndDateProblemDTO;
import dss.vector.solutions.geo.generated.GeoEntityDTO;
import dss.vector.solutions.ontology.TermDTO;
import dss.vector.solutions.util.DefaultConverter;
import dss.vector.solutions.util.ErrorUtility;

public class StockEventController extends StockEventControllerBase implements com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long  serialVersionUID = 1257355364287L;

  public static final String JSP_DIR          = "WEB-INF/dss/vector/solutions/stock/StockEvent/";

  public static final String VIEWS            = "views";

  public static final String ITEM             = "item";

  public static final String LAYOUT           = "/layout.jsp";

  public StockEventController(javax.servlet.http.HttpServletRequest req, javax.servlet.http.HttpServletResponse resp, java.lang.Boolean isAsynchronous)
  {
    super(req, resp, isAsynchronous, JSP_DIR, LAYOUT);
  }

  @Override
  public void search() throws IOException, ServletException
  {
    if (!this.isAsynchronous())
    {
      req.setAttribute("view", new StockEventViewDTO(this.getClientRequest()));
      render("searchComponent.jsp");
    }
  }

  @Override
  public void failSearch() throws IOException, ServletException
  {
    // This should never happen
    req.getRequestDispatcher("index.jsp").forward(req, resp);
  }

  @Override
  public void searchInStock(String geoId, TermDTO item, Date date) throws IOException, ServletException
  {
    try
    {
      validateParameters(geoId, item, date);

      searchByParameters(geoId, item, date, EventOptionDTO.STOCK_IN);
    }
    catch (ProblemExceptionDTO e)
    {
      ErrorUtility.prepareProblems(e, req);

      String failDate = new DefaultConverter(Date.class).format(date, req.getLocale());

      this.failSearchInStock(geoId, item, failDate);
    }
    catch (Throwable t)
    {
      ErrorUtility.prepareThrowable(t, req);

      String failDate = new DefaultConverter(Date.class).format(date, req.getLocale());

      this.failSearchInStock(geoId, item, failDate);
    }
  }

  @Override
  public void failSearchInStock(String geoId, TermDTO item, String date) throws IOException, ServletException
  {
    this.setupFailParameters(geoId, item, date);

    this.search();
  }

  @Override
  public void searchOutStock(String geoId, TermDTO item, Date date) throws IOException, ServletException
  {
    try
    {
      validateParameters(geoId, item, date);

      searchByParameters(geoId, item, date, EventOptionDTO.STOCK_OUT);
    }
    catch (ProblemExceptionDTO e)
    {
      ErrorUtility.prepareProblems(e, req);

      String failDate = new DefaultConverter(Date.class).format(date, req.getLocale());

      this.failSearchInStock(geoId, item, failDate);
    }
    catch (Throwable t)
    {
      ErrorUtility.prepareThrowable(t, req);

      String failDate = new DefaultConverter(Date.class).format(date, req.getLocale());

      this.failSearchInStock(geoId, item, failDate);
    }
  }

  @Override
  public void failSearchOutStock(String geoId, TermDTO item, String date) throws IOException, ServletException
  {
    this.setupFailParameters(geoId, item, date);

    this.search();
  }

  private void searchByParameters(String geoId, TermDTO item, Date date, EventOptionDTO option) throws IOException, ServletException
  {
    ClientRequestIF request = this.getClientRequest();
    StockEventViewDTO[] views = StockEventViewDTO.getViews(request, geoId, item, date, option);
    StockStaffDTO[] staff = StockStaffDTO.getAll(request);

    this.req.setAttribute("entity", GeoEntityDTO.searchByGeoId(request, geoId));
    this.req.setAttribute("term", item);
    this.req.setAttribute("date", date);

    this.req.setAttribute(ITEM, new StockEventViewDTO(request));
    this.req.setAttribute(VIEWS, views);
    this.req.setAttribute("staff", Arrays.asList(staff));

    if (option.equals(EventOptionDTO.STOCK_IN))
    {
      this.render("viewInStockComponent.jsp");
    }
    else
    {
      this.render("viewOutStockComponent.jsp");
    }
  }

  private void validateParameters(String geoId, TermDTO item, Date date)
  {
    LinkedList<ProblemDTOIF> problems = new LinkedList<ProblemDTOIF>();

    this.validateParameters(geoId, item, date, problems);

    if (problems.size() > 0)
    {
      throw new ProblemExceptionDTO("", problems);
    }
  }

  private void validateParameters(String geoId, TermDTO item, Date date, List<ProblemDTOIF> problems)
  {
    if (geoId == null)
    {
      ClientRequestIF clientRequest = super.getClientSession().getRequest();
      problems.add(new RequiredStockDepotProblemDTO(clientRequest, req.getLocale()));
    }

    if (item == null)
    {
      ClientRequestIF clientRequest = super.getClientSession().getRequest();
      problems.add(new RequiredStockItemProblemDTO(clientRequest, req.getLocale()));
    }

    if (date == null)
    {
      ClientRequestIF clientRequest = super.getClientSession().getRequest();
      problems.add(new RequiredTransactionDateProblemDTO(clientRequest, req.getLocale()));
    }
  }

  private void validateParameters(String geoId, TermDTO item, Date date, Date endDate)
  {
    LinkedList<ProblemDTOIF> problems = new LinkedList<ProblemDTOIF>();

    this.validateParameters(geoId, item, date, problems);

    if (endDate == null)
    {
      ClientRequestIF clientRequest = super.getClientSession().getRequest();
      problems.add(new RequiredEndDateProblemDTO(clientRequest, req.getLocale()));
    }

    if (problems.size() > 0)
    {
      throw new ProblemExceptionDTO("", problems);
    }
  }

  @Override
  public void searchPage(String geoId, TermDTO item, Date date, Date endDate) throws IOException, ServletException
  {
    try
    {
      validateParameters(geoId, item, date, endDate);

      ClientRequestIF clientRequest = super.getClientRequest();
      StockEventViewQueryDTO query = StockEventViewDTO.getPage(clientRequest, StockEventViewDTO.EVENTDATE, false, 20, 0, geoId, item.getId(), date, endDate);

      this.setupContext(geoId, item.getId(), date, endDate);

      req.setAttribute("query", query);
      render("viewAllComponent.jsp");
    }
    catch (ProblemExceptionDTO e)
    {
      ErrorUtility.prepareProblems(e, req);

      String failDate = new DefaultConverter(Date.class).format(date, req.getLocale());
      String failEndDate = new DefaultConverter(Date.class).format(endDate, req.getLocale());

      this.failSearchPage(geoId, item, failDate, failEndDate);
    }
    catch (Throwable t)
    {
      ErrorUtility.prepareThrowable(t, req);

      String failDate = new DefaultConverter(Date.class).format(date, req.getLocale());
      String failEndDate = new DefaultConverter(Date.class).format(endDate, req.getLocale());

      this.failSearchPage(geoId, item, failDate, failEndDate);
    }
  }
  
  @Override
  public void failSearchPage(String geoId, TermDTO item, String date, String endDate) throws IOException, ServletException
  {
    this.setupFailParameters(geoId, item, date);
    req.setAttribute("endDate", endDate);
    
    this.search();
  }

  @Override
  public void getPage(String sortAttribute, Boolean isAscending, Integer pageSize, Integer pageNumber, String geoId, String item, Date startDate, Date endDate) throws IOException, ServletException
  {
    ClientRequestIF clientRequest = super.getClientRequest();
    StockEventViewQueryDTO query = StockEventViewDTO.getPage(clientRequest, sortAttribute, isAscending, pageSize, pageNumber, geoId, item, startDate, endDate);

    this.setupContext(geoId, item, startDate, endDate);

    req.setAttribute("query", query);
    render("viewAllComponent.jsp");
  }

  private void setupContext(String geoId, String item, Date date, Date endDate)
  {
    req.setAttribute("geoId", geoId);
    req.setAttribute("item", item);
    req.setAttribute("startDate", new DefaultConverter(Date.class).format(date, req.getLocale()));
    req.setAttribute("endDate", new DefaultConverter(Date.class).format(endDate, req.getLocale()));
  }

  private void setupFailParameters(String geoId, TermDTO item, String date)
  {
    req.setAttribute("geoId", geoId);
    req.setAttribute("item", item);
    req.setAttribute("date", date);
  }
}
