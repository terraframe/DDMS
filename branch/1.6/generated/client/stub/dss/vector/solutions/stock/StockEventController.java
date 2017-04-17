package dss.vector.solutions.stock;

import java.io.IOException;
import java.util.Arrays;
import java.util.Currency;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.ServletException;

import com.runwaysdk.ProblemExceptionDTO;
import com.runwaysdk.business.ProblemDTOIF;
import com.runwaysdk.constants.ClientRequestIF;
import com.runwaysdk.format.AbstractFormatFactory;
import com.runwaysdk.format.Format;

import dss.vector.solutions.MDSSUserDTO;
import dss.vector.solutions.entomology.RequiredEndDateProblemDTO;
import dss.vector.solutions.geo.generated.GeoEntityDTO;
import dss.vector.solutions.geo.generated.StockDepotDTO;
import dss.vector.solutions.ontology.TermDTO;
import dss.vector.solutions.util.ErrorUtility;

public class StockEventController extends StockEventControllerBase implements com.runwaysdk.generation.loader.Reloadable
{
  public static final long   serialVersionUID = 1257355364287L;

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
      this.setupSearchParameters();

      req.setAttribute("view", new StockEventViewDTO(this.getClientRequest()));
      req.setAttribute("canDeleteAll", MDSSUserDTO.canDeleteAll(this.getClientRequest()));

      render("searchComponent.jsp");
    }
  }

  private void setupSearchParameters()
  {
    String geoId = req.getParameter("geoId");
    String itemId = req.getParameter("itemId");
    String dateString = req.getParameter("date");
    String endDateString = req.getParameter("endDate");

    if (geoId != null && !geoId.equals(""))
    {
      req.setAttribute("geoId", geoId);
    }

    if (req.getAttribute("item") == null)
    {
      if (itemId != null && !itemId.equals(""))
      {
        TermDTO item = TermDTO.get(this.getClientRequest(), itemId);

        req.setAttribute("item", item);
      }
      else
      {
        this.setupSearchDefaults();
      }
    }

    Format<Date> f = AbstractFormatFactory.getFormatFactory().getFormat(Date.class);

    if (dateString != null && !dateString.equals(""))
    {
      Object date = f.parse(dateString, req.getLocale());

      req.setAttribute("date", date);
    }

    if (endDateString != null && !endDateString.equals(""))
    {
      Object date = f.parse(endDateString, req.getLocale());

      req.setAttribute("endDate", date);
    }

    List<String> entityUniversals = Arrays.asList(new String[] { StockDepotDTO.CLASS });

    req.setAttribute("StockDepot", StockDepotDTO.CLASS);
    req.setAttribute("entityUniversals", entityUniversals);
  }

  private void setupSearchDefaults()
  {
    StockItemViewDTO stockItem = new StockItemViewDTO(this.getClientRequest());
    req.setAttribute("item", stockItem.getItemName());
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
    catch (Throwable t)
    {
      boolean redirected = ErrorUtility.prepareThrowable(t, req, resp, this.isAsynchronous());

      if (!redirected)
      {
        Format<Date> f = AbstractFormatFactory.getFormatFactory().getFormat(Date.class);
        String failDate = f.format(date, req.getLocale());

        this.failSearchInStock(geoId, item, failDate);
      }
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
    catch (Throwable t)
    {
      boolean redirected = ErrorUtility.prepareThrowable(t, req, resp, this.isAsynchronous());

      if (!redirected)
      {
        Format<Date> f = AbstractFormatFactory.getFormatFactory().getFormat(Date.class);
        String failDate = f.format(date, req.getLocale());

        this.failSearchInStock(geoId, item, failDate);
      }
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

    // Ensure the user has the ability to create stock events
    new StockEventDTO(request);

    StockEventViewDTO view = new StockEventViewDTO(request);
    StockEventViewDTO[] data = StockEventViewDTO.getViews(request, geoId, item, date, option);
    StockStaffDTO[] staff = StockStaffDTO.getAll(request);

    // We must refresh the term in order to get the term display label
    item = TermDTO.get(request, item.getId());

    this.req.setAttribute("entity", GeoEntityDTO.searchByGeoId(request, geoId));
    this.req.setAttribute("term", item);

    // fix #2716 ... let JavaScript format the ${date} NOT here in Java.
    // However, the link back to search expects a specific format which we put
    // in ${searchDate}
    this.req.setAttribute("date", date);
    Format<Date> f = AbstractFormatFactory.getFormatFactory().getFormat(Date.class);
    this.req.setAttribute("searchDate", f.format(date, req.getLocale()));

    this.req.setAttribute(ITEM, view);
    this.req.setAttribute("grid", new StockEventGridBuilder(view, data).build());
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
      this.req.setAttribute("currency", Currency.getInstance(req.getLocale()).getCurrencyCode());

      req.setAttribute("query", query);
      render("viewAllComponent.jsp");
    }
    catch (Throwable t)
    {
      boolean redirected = ErrorUtility.prepareThrowable(t, req, resp, this.isAsynchronous());

      if (!redirected)
      {
        Format<Date> f = AbstractFormatFactory.getFormatFactory().getFormat(Date.class);
        String failDate = f.format(date, req.getLocale());
        String failEndDate = f.format(endDate, req.getLocale());

        this.failSearchPage(geoId, item, failDate, failEndDate);
      }
    }
  }

  @Override
  public void deleteAll(String geoId, TermDTO item, Date date, Date endDate) throws IOException, ServletException
  {
    try
    {
      validateParameters(geoId, item, date, endDate);

      ClientRequestIF clientRequest = super.getClientRequest();
      StockEventViewDTO.deleteAll(clientRequest, geoId, item.getId(), date, endDate);

      this.search();
    }
    catch (Throwable t)
    {
      boolean redirected = ErrorUtility.prepareThrowable(t, req, resp, this.isAsynchronous());

      if (!redirected)
      {
        this.search();
      }
    }
  }

  @Override
  public void failSearchPage(String geoId, TermDTO item, String date, String endDate) throws IOException, ServletException
  {
    ClientRequestIF request = this.getClientRequest();

    if (item != null)
    {
      item = TermDTO.get(request, item.getId());
    }

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
    Format<Date> f = AbstractFormatFactory.getFormatFactory().getFormat(Date.class);

    req.setAttribute("geoId", geoId);
    req.setAttribute("item", item);
    req.setAttribute("startDate", f.format(date, req.getLocale()));
    req.setAttribute("endDate", f.format(endDate, req.getLocale()));
  }

  private void setupFailParameters(String geoId, TermDTO item, String date)
  {
    req.setAttribute("geoId", geoId);
    req.setAttribute("item", item);
    req.setAttribute("date", date);
  }
}
