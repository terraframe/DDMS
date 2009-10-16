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

      ITNDistributionViewDTO dto = ITNDistributionDTO.getView(clientRequest, id);
      
      this.setupReferences(dto);
      
      req.setAttribute("targetGroups", Arrays.asList(dto.getDistributionTargetGroups()));
      req.setAttribute("item", dto);
      
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
  
  @Override
  public void create(ITNDistributionViewDTO dto, ITNDistributionTargetGroupDTO[] targetGroups) throws IOException, ServletException
  {
    try
    {
      dto.applyAll(targetGroups);
      this.view(dto.getConcreteId());
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
  
  @Override
  public void failSearchRecipient(ITNDistributionViewDTO itn, PersonViewDTO recipient) throws IOException, ServletException
  {
    renderSearch(itn, recipient);
  }
  
  private void renderConfirm(ITNDistributionViewDTO itn, PersonViewDTO recipient) throws IOException, ServletException
  {
    req.setAttribute("itn", itn);
    req.setAttribute("recipient", recipient);
    render("confirmRecipient.jsp");
  }

  public void newInstance() throws IOException, ServletException
  {
    ClientRequestIF clientRequest = super.getClientRequest();
    
    renderSearch(new ITNDistributionViewDTO(clientRequest), new PersonViewDTO(clientRequest));
  }

  private void renderSearch(ITNDistributionViewDTO itn, PersonViewDTO personView) throws IOException, ServletException
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
    ITNDistributionViewDTO itn = ITNDistributionDTO.lockView(super.getClientRequest(), id);

    this.setupReferences(itn);
    
    req.setAttribute("targetGroups", Arrays.asList(itn.getDistributionTargetGroups()));
    req.setAttribute("item", itn);
    render("editComponent.jsp");
  }

  public void failEdit(String id) throws IOException, ServletException
  {
    this.view(id);
  }

  public void selectRecipient(ITNDistributionViewDTO itn, String recipientId) throws IOException, ServletException
  {
    renderConfirm(itn, PersonDTO.getView(super.getClientRequest(), recipientId));
  }
  
  @Override
  public void failSelectRecipient(ITNDistributionViewDTO itn, String recipientId) throws IOException, ServletException
  {
    resp.sendError(500);
  }
  
  @Override
  public void cancel(ITNDistributionViewDTO dto) throws IOException, ServletException
  {
    ClientRequestIF request = this.getClientRequest();
    ITNDistributionDTO.lockView(request, dto.getConcreteId());

    this.view(dto.getId());
  }
  
  @Override
  public void failCancel(ITNDistributionViewDTO dto) throws IOException, ServletException
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
  
  @Override
  public void update(ITNDistributionViewDTO dto, ITNDistributionTargetGroupDTO[] targetGroups) throws IOException, ServletException
  {
    try
    {
      dto.applyAll(targetGroups);
      this.view(dto.getConcreteId());
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
  
  @Override
  public void failUpdate(ITNDistributionViewDTO dto, ITNDistributionTargetGroupDTO[] targetGroups) throws IOException, ServletException
  {
    setupReferences(dto);
    req.setAttribute("recipient", ITNRecipientDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("item", dto);
    render("editComponent.jsp");
  }
  
  @Override
  public void updateRecipient(ITNDistributionViewDTO itn, PersonViewDTO recipient) throws IOException, ServletException
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

  private void renderCreate(ITNDistributionViewDTO itn, ITNDistributionTargetGroupDTO[] distributionTargetGroups) throws IOException, ServletException
  {
    this.setupReferences(itn);
    
    req.setAttribute("targetGroups", Arrays.asList(distributionTargetGroups));
    req.setAttribute("item", itn);
    render("createComponent.jsp");
  }

  private void setupReferences(ITNDistributionViewDTO itn)
  {
    req.setAttribute("net", itn.getNet());
    req.setAttribute("service", itn.getService());
  }
  
  @Override
  public void failUpdateRecipient(ITNDistributionViewDTO itn, PersonViewDTO recipient) throws IOException, ServletException
  {
    renderConfirm(itn, recipient);
  }
}
