package dss.vector.solutions.intervention.monitor;

import java.io.IOException;
import java.util.Arrays;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.runwaysdk.constants.ClientRequestIF;
import com.runwaysdk.generation.loader.Reloadable;

import dss.vector.solutions.PersonDTO;
import dss.vector.solutions.PersonViewDTO;
import dss.vector.solutions.PersonWithDelegatesViewDTO;
import dss.vector.solutions.PersonWithDelegatesViewQueryDTO;
import dss.vector.solutions.geo.generated.HealthFacilityDTO;
import dss.vector.solutions.util.AttributeUtil;
import dss.vector.solutions.util.DefaultConverter;
import dss.vector.solutions.util.ErrorUtility;
import dss.vector.solutions.util.RedirectUtility;

public class ITNDistributionController extends ITNDistributionControllerBase implements Reloadable
{
  public static final String JSP_DIR          = "WEB-INF/dss/vector/solutions/intervention/monitor/ITNDistribution/";

  public static final String LAYOUT           = "/layout.jsp";

  private static final long  serialVersionUID = 1253148381756L;

  public ITNDistributionController(HttpServletRequest req, HttpServletResponse resp, Boolean isAsynchronous)
  {
    super(req, resp, isAsynchronous, JSP_DIR, LAYOUT);
  }

  public void view(String id) throws IOException, ServletException
  {
    try
    {
      view(ITNDistributionDTO.getView(super.getClientRequest(), id));
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

  private void view(ITNDistributionViewDTO dto) throws IOException, ServletException
  {
    RedirectUtility utility = new RedirectUtility(req, resp);
    utility.put("id", dto.getConcreteId());
    utility.checkURL(this.getClass().getSimpleName(), "view");

    this.setupReferences(dto);

    req.setAttribute("facility", AttributeUtil.getGeoEntityFromGeoId(ITNDistributionViewDTO.FACILITY, dto));
    req.setAttribute("targetGroups", Arrays.asList(dto.getDistributionTargetGroups()));
    req.setAttribute("item", dto);

    render("viewComponent.jsp");
  }

  public void failView(String id) throws IOException, ServletException
  {
    this.viewAll();
  }

  @Override
  public void create(ITNDistributionViewDTO dto, ITNDistributionTargetGroupDTO[] targetGroups) throws IOException, ServletException
  {
    try
    {
      dto.applyAll(targetGroups);
      this.view(dto);
    }
    catch (Throwable t)
    {
      boolean redirected = ErrorUtility.prepareThrowable(t, req, resp, this.isAsynchronous());

      if (!redirected)
      {
        this.failCreate(dto, targetGroups);
      }
    }
  }

  @Override
  public void failCreate(ITNDistributionViewDTO dto, ITNDistributionTargetGroupDTO[] targetGroups) throws IOException, ServletException
  {
    renderCreate(dto, targetGroups);
  }

  @Override
  public void delete(ITNDistributionViewDTO dto) throws IOException, ServletException
  {
    try
    {
      dto.deleteConcrete();
      this.search();
    }
    catch (Throwable t)
    {
      boolean redirected = ErrorUtility.prepareThrowable(t, req, resp, this.isAsynchronous());

      if (!redirected)
      {
        this.failDelete(dto);
      }
    }
  }

  @Override
  public void failDelete(ITNDistributionViewDTO dto) throws IOException, ServletException
  {
    this.setupReferences(dto);

    req.setAttribute("recipient", ITNRecipientDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("item", dto);
    render("editComponent.jsp");
  }

  public void viewPage(String sortAttribute, Boolean isAscending, Integer pageSize, Integer pageNumber) throws IOException, ServletException
  {
    ClientRequestIF clientRequest = super.getClientRequest();
    ITNDistributionQueryDTO query = ITNDistributionDTO.getAllInstances(clientRequest, sortAttribute, isAscending, pageSize, pageNumber);
    req.setAttribute("query", query);
    render("viewAllComponent.jsp");
  }

  public void failViewPage(String sortAttribute, String isAscending, String pageSize, String pageNumber) throws IOException, ServletException
  {
    resp.sendError(500);
  }

  @Override
  public void searchRecipient(ITNDistributionViewDTO itn, PersonViewDTO recipient) throws IOException, ServletException
  {
    try
    {
      PersonWithDelegatesViewQueryDTO query = recipient.searchForDuplicates();

      if (query.getCount() == 0)
      {
        renderConfirm(itn, recipient);
      }
      else if (query.getCount() == 1)
      {
        // Method chaining here is ok because we know there's exactly 1 result
        PersonWithDelegatesViewDTO delegates = query.getResultSet().get(0);

        renderConfirm(itn, PersonDTO.getView(this.getClientRequest(), delegates.getPersonId()));
      }
      else if (query.getCount() > 1)
      {
        req.setAttribute("query", query);
        render("selectRecipient.jsp");
      }
    }
    catch (Throwable t)
    {
      boolean redirected = ErrorUtility.prepareThrowable(t, req, resp, this.isAsynchronous());

      if (!redirected)
      {
        this.failSearchRecipient(itn, recipient);
      }
    }
  }

  private void renderConfirm(ITNDistributionViewDTO itn, PersonViewDTO recipient) throws IOException, ServletException
  {
    req.setAttribute("itn", itn);
    req.setAttribute("recipient", recipient);
    req.setAttribute("sex", recipient.getSex());
    render("confirmRecipient.jsp");
  }

  @Override
  public void newInstance(String person, String facility, String batchNumber, Date distributionDate) throws IOException, ServletException
  {
    try
    {
      ClientRequestIF clientRequest = super.getClientRequest();

      // Ensure the user has permissions to create a new ITN Distribution
      new ITNDistributionDTO(clientRequest);

      ITNDistributionViewDTO view = new ITNDistributionViewDTO(clientRequest);
      view.setValue(ITNDistributionViewDTO.PERSON, person);
      view.setValue(ITNDistributionViewDTO.FACILITY, facility);
      view.setBatchNumber(batchNumber);
      view.setDistributionDate(distributionDate);

      ITNDistributionTargetGroupDTO[] groups = view.getDistributionTargetGroups();

      req.setAttribute("targetGroups", Arrays.asList(groups));
      req.setAttribute("item", view);
      req.setAttribute("healthFacility", HealthFacilityDTO.CLASS);

      render("createComponent.jsp");
    }
    catch (Throwable t)
    {
      boolean redirected = ErrorUtility.prepareThrowable(t, req, resp, this.isAsynchronous());

      if (!redirected)
      {
        String failDistributionDate = ( distributionDate == null ) ? null : new DefaultConverter(Date.class).format(distributionDate, req.getLocale());

        this.failNewInstance(person, facility, batchNumber, failDistributionDate);
      }
    }
  }

  @Override
  public void failNewInstance(String person, String facility, String batchNumber, String distributionDate) throws IOException, ServletException
  {
    ClientRequestIF request = this.getClientSession().getRequest();

    Date date = (Date) new DefaultConverter(Date.class).parse(distributionDate, req.getLocale());
    ITNDistributionViewDTO view = new ITNDistributionViewDTO(request);
    view.setValue(ITNDistributionViewDTO.PERSON, person);
    view.setValue(ITNDistributionViewDTO.FACILITY, facility);
    view.setValue(ITNDistributionViewDTO.BATCHNUMBER, batchNumber);
    view.setDistributionDate(date);

    this.viewHistory(view);
  }

  public void edit(String id) throws IOException, ServletException
  {
    try
    {
      edit(ITNDistributionDTO.lockView(super.getClientRequest(), id));
    }
    catch (Throwable t)
    {
      boolean redirected = ErrorUtility.prepareThrowable(t, req, resp, this.isAsynchronous());

      if (!redirected)
      {
        this.failEdit(id);
      }
    }

  }

  private void edit(ITNDistributionViewDTO itn) throws IOException, ServletException
  {
    edit(itn, itn.getDistributionTargetGroups());
  }

  private void edit(ITNDistributionViewDTO itn, ITNDistributionTargetGroupDTO[] targetGroups) throws IOException, ServletException
  {
    this.setupReferences(itn);

    req.setAttribute("healthFacility", HealthFacilityDTO.CLASS);
    req.setAttribute("targetGroups", Arrays.asList(targetGroups));
    req.setAttribute("item", itn);

    render("editComponent.jsp");
  }

  public void failEdit(String id) throws IOException, ServletException
  {
    this.view(id);
  }

  @Override
  public void cancel(ITNDistributionViewDTO dto) throws IOException, ServletException
  {
    try
    {
      this.view(ITNDistributionDTO.unlockView(this.getClientRequest(), dto.getConcreteId()));
    }
    catch (Throwable t)
    {
      boolean redirected = ErrorUtility.prepareThrowable(t, req, resp, this.isAsynchronous());

      if (!redirected)
      {
        this.failCancel(dto);
      }
    }
  }

  @Override
  public void failCancel(ITNDistributionViewDTO dto) throws IOException, ServletException
  {
    this.edit(dto.getConcreteId());
  }

  public void viewAll() throws IOException, ServletException
  {
    ClientRequestIF clientRequest = super.getClientRequest();
    ITNDistributionQueryDTO query = ITNDistributionDTO.getAllInstances(clientRequest, null, true, 20, 1);
    req.setAttribute("query", query);
    render("viewAllComponent.jsp");
  }

  public void failViewAll() throws IOException, ServletException
  {
    resp.sendError(500);
  }

  @Override
  public void update(ITNDistributionViewDTO dto, ITNDistributionTargetGroupDTO[] targetGroups) throws IOException, ServletException
  {
    try
    {
      dto.applyAll(targetGroups);
      this.view(dto);
    }
    catch (Throwable t)
    {
      boolean redirected = ErrorUtility.prepareThrowable(t, req, resp, this.isAsynchronous());

      if (!redirected)
      {
        this.failUpdate(dto, targetGroups);
      }
    }
  }

  @Override
  public void failUpdate(ITNDistributionViewDTO dto, ITNDistributionTargetGroupDTO[] targetGroups) throws IOException, ServletException
  {
    this.edit(dto, targetGroups);
  }

  private void renderCreate(ITNDistributionViewDTO itn, ITNDistributionTargetGroupDTO[] distributionTargetGroups) throws IOException, ServletException
  {
    this.setupReferences(itn);

    req.setAttribute("targetGroups", Arrays.asList(distributionTargetGroups));
    req.setAttribute("item", itn);
    render("createComponent.jsp");
  }

  private void setupReferences(ITNDistributionViewDTO itn)
  {
    req.setAttribute("net", AttributeUtil.getValue(ITNDistributionViewDTO.NET, itn));
    req.setAttribute("service", AttributeUtil.getValue(ITNDistributionViewDTO.SERVICE, itn));
  }

  @Override
  public void search() throws IOException, ServletException
  {
    ClientRequestIF request = this.getClientRequest();

    req.setAttribute("item", new ITNDistributionViewDTO(request));
    req.setAttribute("healthFacility", HealthFacilityDTO.CLASS);

    // need this for labels
    req.setAttribute("person", new PersonViewDTO(this.getClientRequest()));

    render("searchComponent.jsp");
  }

  @Override
  public void failSearch() throws IOException, ServletException
  {
    // This should never happen
    req.getRequestDispatcher("index.jsp").forward(req, resp);
  }

  @Override
  public void viewHistory(ITNDistributionViewDTO view) throws IOException, ServletException
  {
    try
    {
      ClientRequestIF request = this.getClientRequest();

      ITNDistributionViewQueryDTO query = ITNDistributionViewDTO.searchHistory(request, view);

      Date date = view.getDistributionDate();
      String batchNumber = view.getBatchNumber();
      String facility = view.getFacility();

      if (date != null)
      {
        req.setAttribute("distributionDate", new DefaultConverter(Date.class).format(date, req.getLocale()));
      }

      if (facility != null && !facility.equals(""))
      {
        req.setAttribute("facility", facility);
      }

      if (batchNumber != null && !batchNumber.equals(""))
      {
        req.setAttribute("batchNumber", batchNumber);
      }

      req.setAttribute("item", view);
      req.setAttribute("query", query);
      render("viewAllComponent.jsp");
    }
    catch (Throwable t)
    {
      boolean redirected = ErrorUtility.prepareThrowable(t, req, resp, this.isAsynchronous());

      if (!redirected)
      {
        this.failViewHistory(view);
      }
    }
  }

  @Override
  public void failViewHistory(ITNDistributionViewDTO view) throws IOException, ServletException
  {
    this.search();
  }

}
