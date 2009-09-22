package dss.vector.solutions.intervention.monitor;

import java.io.IOException;
import java.util.Arrays;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.terraframe.mojo.constants.ClientRequestIF;
import com.terraframe.mojo.generation.loader.Reloadable;

import dss.vector.solutions.PersonDTO;
import dss.vector.solutions.PersonQueryDTO;
import dss.vector.solutions.PersonViewDTO;
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
      RedirectUtility utility = new RedirectUtility(req, resp);
      utility.put("id", id);
      utility.checkURL(this.getClass().getSimpleName(), "view");
      ClientRequestIF clientRequest = super.getClientRequest();

      ITNDistributionDTO itn = ITNDistributionDTO.get(clientRequest, id);
      req.setAttribute("targetGroups", Arrays.asList(itn.getDistributionTargetGroups()));
      req.setAttribute("item", itn);
      render("viewComponent.jsp");
    }
    catch (com.terraframe.mojo.ProblemExceptionDTO e)
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

  public void failView(String id) throws IOException, ServletException
  {
    this.viewAll();
  }

  public void create(ITNDistributionDTO dto, ITNDistributionTargetGroupDTO[] targetGroups) throws IOException, ServletException
  {
    try
    {
      dto.applyAll(targetGroups);
      this.view(dto.getId());
    }
    catch (com.terraframe.mojo.ProblemExceptionDTO e)
    {
      ErrorUtility.prepareProblems(e, req);
      this.failCreate(dto, targetGroups);
    }
    catch (Throwable t)
    {
      ErrorUtility.prepareThrowable(t, req);
      this.failCreate(dto, targetGroups);
    }
  }

  public void failCreate(ITNDistributionDTO dto, ITNDistributionTargetGroupDTO[] targetGroups) throws IOException, ServletException
  {
    renderCreate(dto, targetGroups);
  }

  public void delete(ITNDistributionDTO dto) throws IOException, ServletException
  {
    try
    {
      dto.delete();
      this.viewAll();
    }
    catch (com.terraframe.mojo.ProblemExceptionDTO e)
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

  public void failDelete(ITNDistributionDTO dto) throws IOException, ServletException
  {
    req.setAttribute("net", Arrays.asList(NetDTO.getAll(super.getClientSession().getRequest())));
    req.setAttribute("recipient", ITNRecipientDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("service", Arrays.asList(ServiceGridDTO.getAllActive(super.getClientSession().getRequest())));
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

  public void searchRecipient(ITNDistributionDTO itn, PersonViewDTO recipient) throws IOException, ServletException
  {
    try
    {
      PersonQueryDTO query = recipient.searchForDuplicates();
      if (query.getCount() == 0)
      {
        renderConfirm(itn, recipient);
      }
      else if (query.getCount() == 1)
      {
        // Method chaining here is ok because we know there's exactly 1 result
        PersonViewDTO view = query.getResultSet().get(0).getView();
        renderConfirm(itn, view);
      }
      else if (query.getCount() > 1)
      {
        req.setAttribute("query", query);
        render("selectRecipient.jsp");
      }
    }
    catch (com.terraframe.mojo.ProblemExceptionDTO e)
    {
      ErrorUtility.prepareProblems(e, req);
      this.failSearchRecipient(itn, recipient);
    }
    catch (Throwable t)
    {
      ErrorUtility.prepareThrowable(t, req);
      this.failSearchRecipient(itn, recipient);
    }
  }

  public void failSearchRecipient(ITNDistributionDTO itn, PersonViewDTO recipient) throws IOException, ServletException
  {
    renderSearch(itn, recipient);
  }
  
  private void renderConfirm(ITNDistributionDTO itn, PersonViewDTO recipient) throws IOException, ServletException
  {
    req.setAttribute("itn", itn);
    req.setAttribute("recipient", recipient);
    render("confirmRecipient.jsp");
  }

  public void newInstance() throws IOException, ServletException
  {
    ClientRequestIF clientRequest = super.getClientRequest();
    renderSearch(new ITNDistributionDTO(clientRequest), new PersonViewDTO(clientRequest));
  }

  private void renderSearch(ITNDistributionDTO itn, PersonViewDTO personView) throws IOException, ServletException
  {
    req.setAttribute("recipient", personView);
    req.setAttribute("item", itn);
    render("search.jsp");
  }

  public void failNewInstance() throws IOException, ServletException
  {
    this.viewAll();
  }

  public void edit(String id) throws IOException, ServletException
  {
    ITNDistributionDTO itn = ITNDistributionDTO.lock(super.getClientRequest(), id);
    req.setAttribute("net", Arrays.asList(NetDTO.getAll(super.getClientSession().getRequest())));
    req.setAttribute("service", Arrays.asList(ServiceGridDTO.getAllActive(super.getClientSession().getRequest())));
    req.setAttribute("targetGroups", Arrays.asList(itn.getDistributionTargetGroups()));
    req.setAttribute("item", itn);
    render("editComponent.jsp");
  }

  public void failEdit(String id) throws IOException, ServletException
  {
    this.view(id);
  }

  public void selectRecipient(ITNDistributionDTO itn, String recipientId) throws IOException, ServletException
  {
    renderConfirm(itn, PersonDTO.getView(super.getClientRequest(), recipientId));
  }

  public void failSelectRecipient(ITNDistributionDTO itn, String recipientId) throws IOException, ServletException
  {
    resp.sendError(500);
  }

  public void cancel(ITNDistributionDTO dto) throws IOException, ServletException
  {
    dto.unlock();
    this.view(dto.getId());
  }

  public void failCancel(ITNDistributionDTO dto) throws IOException, ServletException
  {
    this.edit(dto.getId());
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

  public void update(ITNDistributionDTO dto, ITNDistributionTargetGroupDTO[] targetGroups) throws IOException, ServletException
  {
    try
    {
      dto.applyAll(targetGroups);
      this.view(dto.getId());
    }
    catch (com.terraframe.mojo.ProblemExceptionDTO e)
    {
      ErrorUtility.prepareProblems(e, req);
      this.failUpdate(dto, targetGroups);
    }
    catch (Throwable t)
    {
      ErrorUtility.prepareThrowable(t, req);
      this.failUpdate(dto, targetGroups);
    }
  }

  public void failUpdate(ITNDistributionDTO dto, ITNDistributionTargetGroupDTO[] targetGroups) throws IOException, ServletException
  {
    req.setAttribute("net", Arrays.asList(NetDTO.getAll(super.getClientSession().getRequest())));
    req.setAttribute("recipient", ITNRecipientDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("service", Arrays.asList(ServiceGridDTO.getAllActive(super.getClientSession().getRequest())));
    req.setAttribute("item", dto);
    render("editComponent.jsp");
  }

  public void updateRecipient(ITNDistributionDTO itn, PersonViewDTO recipient) throws IOException, ServletException
  {
    try
    {
      recipient.setIsITNRecipient(true);
      recipient.apply();
      itn.setRecipient(PersonDTO.get(super.getClientRequest(), recipient.getPersonId()).getItnRecipientDelegate());

      renderCreate(itn, itn.getDistributionTargetGroups());
    }
    catch (com.terraframe.mojo.ProblemExceptionDTO e)
    {
      ErrorUtility.prepareProblems(e, req);
      this.failUpdateRecipient(itn, recipient);
    }
    catch (Throwable t)
    {
      ErrorUtility.prepareThrowable(t, req);
      this.failUpdateRecipient(itn, recipient);
    }
  }

  private void renderCreate(ITNDistributionDTO itn, ITNDistributionTargetGroupDTO[] distributionTargetGroups) throws IOException, ServletException
  {
    req.setAttribute("net", Arrays.asList(NetDTO.getAll(super.getClientSession().getRequest())));
    req.setAttribute("service", Arrays.asList(ServiceGridDTO.getAllActive(super.getClientSession().getRequest())));
    req.setAttribute("targetGroups", Arrays.asList(distributionTargetGroups));
    req.setAttribute("item", itn);
    render("createComponent.jsp");
  }

  public void failUpdateRecipient(ITNDistributionDTO itn, PersonViewDTO recipient) throws IOException, ServletException
  {
    renderConfirm(itn, recipient);
  }
}
