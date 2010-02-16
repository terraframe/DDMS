package dss.vector.solutions.general;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.terraframe.mojo.ProblemExceptionDTO;
import com.terraframe.mojo.constants.ClientRequestIF;
import com.terraframe.mojo.generation.loader.Reloadable;

import dss.vector.solutions.util.ErrorUtility;
import dss.vector.solutions.util.RedirectUtility;

public class EmailConfigurationController extends EmailConfigurationControllerBase implements Reloadable
{
  public static final String JSP_DIR          = "WEB-INF/dss/vector/solutions/general/EmailConfiguration/";

  public static final String LAYOUT           = "/layout.jsp";

  private static final long  serialVersionUID = -1538736812;

  public EmailConfigurationController(HttpServletRequest req, HttpServletResponse resp, Boolean isAsynchronous)
  {
    super(req, resp, isAsynchronous, JSP_DIR, LAYOUT);
  }

  public void cancel(EmailConfigurationDTO dto) throws IOException, ServletException
  {
    dto.unlock();
    this.view(dto.getId());
  }

  public void failCancel(EmailConfigurationDTO dto) throws IOException, ServletException
  {
    this.edit(dto.getId());
  }

  public void create(EmailConfigurationDTO dto) throws IOException, ServletException
  {
    try
    {
      dto.apply();
      this.view(dto.getId());
    }
    catch (com.terraframe.mojo.ProblemExceptionDTO e)
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

  public void failCreate(EmailConfigurationDTO dto) throws IOException, ServletException
  {
    req.setAttribute("protocol", EmailProtocolDTO.allItems(super.getClientSession().getRequest()));
    req.setAttribute("item", dto);
    render("createComponent.jsp");
  }

  public void delete(EmailConfigurationDTO dto) throws IOException, ServletException
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

  public void failDelete(EmailConfigurationDTO dto) throws IOException, ServletException
  {
    req.setAttribute("protocol", EmailProtocolDTO.allItems(super.getClientSession().getRequest()));
    req.setAttribute("item", dto);
    render("editComponent.jsp");
  }

  public void edit(String id) throws IOException, ServletException
  {
    EmailConfigurationDTO dto = EmailConfigurationDTO.lock(super.getClientRequest(), id);
    req.setAttribute("protocol", EmailProtocolDTO.allItems(super.getClientSession().getRequest()));
    req.setAttribute("item", dto);
    render("editComponent.jsp");
  }

  public void failEdit(String id) throws IOException, ServletException
  {
    this.view(id);
  }

  public void newInstance() throws IOException, ServletException
  {
    ClientRequestIF clientRequest = super.getClientRequest();
    EmailConfigurationDTO dto = new EmailConfigurationDTO(clientRequest);
    req.setAttribute("protocol", EmailProtocolDTO.allItems(super.getClientSession().getRequest()));
    req.setAttribute("item", dto);
    render("createComponent.jsp");
  }

  public void failNewInstance() throws IOException, ServletException
  {
    this.viewAll();
  }

  public void update(EmailConfigurationDTO dto) throws IOException, ServletException
  {
    try
    {
      dto.apply();
      this.view(dto.getId());
    }
    catch (com.terraframe.mojo.ProblemExceptionDTO e)
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

  public void failUpdate(EmailConfigurationDTO dto) throws IOException, ServletException
  {
    req.setAttribute("protocol", EmailProtocolDTO.allItems(super.getClientSession().getRequest()));
    req.setAttribute("item", dto);
    render("editComponent.jsp");
  }

  public void view(String id) throws IOException, ServletException
  {
    try
    {
      RedirectUtility utility = new RedirectUtility(req, resp);
      utility.put("id", id);
      utility.checkURL(this.getClass().getSimpleName(), "view");
      ClientRequestIF clientRequest = super.getClientRequest();
      EmailConfigurationDTO dto = EmailConfigurationDTO.get(clientRequest, id);
      req.setAttribute("protocol", EmailProtocolDTO.allItems(super.getClientSession().getRequest()));
      req.setAttribute("item", dto);
      render("viewComponent.jsp");
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

  public void failView(String id) throws IOException, ServletException
  {
    this.viewAll();
  }

  public void viewAll() throws IOException, ServletException
  {
    ClientRequestIF clientRequest = super.getClientRequest();
    EmailConfigurationQueryDTO query = EmailConfigurationDTO.getAllInstances(clientRequest, null, true, 20, 1);
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
    EmailConfigurationQueryDTO query = EmailConfigurationDTO.getAllInstances(clientRequest, sortAttribute, isAscending, pageSize, pageNumber);
    req.setAttribute("query", query);
    render("viewAllComponent.jsp");
  }

  public void failViewPage(String sortAttribute, String isAscending, String pageSize, String pageNumber) throws IOException, ServletException
  {
    resp.sendError(500);
  }
}
