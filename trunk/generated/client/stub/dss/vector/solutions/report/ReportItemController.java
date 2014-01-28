package dss.vector.solutions.report;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.runwaysdk.constants.ClientRequestIF;
import com.runwaysdk.controller.MultipartFileParameter;

import dss.vector.solutions.util.ErrorUtility;
import dss.vector.solutions.util.RedirectUtility;

public class ReportItemController extends ReportItemControllerBase implements com.runwaysdk.generation.loader.Reloadable
{
  public static final String JSP_DIR = "WEB-INF/dss/vector/solutions/report/ReportItem/";

  public static final String LAYOUT  = "/layout.jsp";

  public ReportItemController(HttpServletRequest req, HttpServletResponse resp, Boolean isAsynchronous)
  {
    super(req, resp, isAsynchronous, JSP_DIR, LAYOUT);
  }

  public void cancel(ReportItemDTO dto) throws IOException, ServletException
  {
    dto.unlock();
    this.view(dto.getId());
  }

  public void failCancel(ReportItemDTO dto) throws IOException, ServletException
  {
    this.edit(dto);
  }

  @Override
  public void create(ReportItemDTO dto, MultipartFileParameter design) throws IOException, ServletException
  {
    try
    {
      if (design != null)
      {
        dto.setReportName(design.getFilename());
        dto.applyWithFile(design.getInputStream());
      }
      else
      {
        dto.applyWithFile(null);
      }

      this.view(dto.getId());
    }
    catch (Throwable t)
    {
      boolean redirect = dss.vector.solutions.util.ErrorUtility.prepareThrowable(t, req, resp, this.isAsynchronous());
      if (!redirect)
      {
        this.failCreate(dto, design);
      }
    }
  }

  @Override
  public void failCreate(ReportItemDTO dto, MultipartFileParameter design) throws IOException, ServletException
  {
    this.newInstance(dto);
  }

  public void delete(ReportItemDTO dto) throws IOException, ServletException
  {
    try
    {
      dto.delete();
      this.viewAll();
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

  public void failDelete(ReportItemDTO dto) throws IOException, ServletException
  {
    this.edit(dto);
  }

  public void edit(String id) throws IOException, ServletException
  {
    try
    {
      this.edit(ReportItemDTO.lock(super.getClientRequest(), id));
    }
    catch (Throwable t)
    {
      boolean redirect = dss.vector.solutions.util.ErrorUtility.prepareThrowable(t, req, resp, this.isAsynchronous());
      if (!redirect)
      {
        this.failEdit(id);
      }
    }
  }

  private void edit(ReportItemDTO dto) throws IOException, ServletException
  {
    try
    {
      req.setAttribute("outputFormat", OutputFormatDTO.allItems(this.getClientRequest()));
      req.setAttribute("item", dto);
      
      render("editComponent.jsp");
    }
    catch (Throwable t)
    {
      boolean redirect = dss.vector.solutions.util.ErrorUtility.prepareThrowable(t, req, resp, this.isAsynchronous());
      if (!redirect)
      {
        this.failEdit(dto.getId());
      }
    }
  }

  public void failEdit(String id) throws IOException, ServletException
  {
    this.view(id);
  }

  public void newInstance() throws IOException, ServletException
  {
    try
    {
      this.newInstance(new ReportItemDTO(this.getClientRequest()));
    }
    catch (Throwable t)
    {
      boolean redirect = dss.vector.solutions.util.ErrorUtility.prepareThrowable(t, req, resp, this.isAsynchronous());
      if (!redirect)
      {
        this.failNewInstance();
      }
    }
  }

  private void newInstance(ReportItemDTO dto) throws IOException, ServletException
  {
    try
    {
      req.setAttribute("outputFormat", OutputFormatDTO.allItems(this.getClientRequest()));
      req.setAttribute("item", dto);

      render("createComponent.jsp");
    }
    catch (Throwable t)
    {
      boolean redirect = dss.vector.solutions.util.ErrorUtility.prepareThrowable(t, req, resp, this.isAsynchronous());
      if (!redirect)
      {
        this.failNewInstance();
      }
    }
  }

  public void failNewInstance() throws IOException, ServletException
  {
    this.viewAll();
  }

  @Override
  public void update(ReportItemDTO dto, MultipartFileParameter design) throws IOException, ServletException
  {
    try
    {
      if (design != null)
      {
        dto.setReportName(design.getFilename());
        dto.applyWithFile(design.getInputStream());
      }
      else
      {
        dto.applyWithFile(null);
      }

      this.view(dto.getId());
    }
    catch (Throwable t)
    {
      boolean redirect = dss.vector.solutions.util.ErrorUtility.prepareThrowable(t, req, resp, this.isAsynchronous());

      if (!redirect)
      {
        this.failUpdate(dto, design);
      }
    }
  }

  public void failUpdate(ReportItemDTO dto) throws IOException, ServletException
  {
    this.edit(dto);
  }

  public void view(String id) throws IOException, ServletException
  {
    try
    {
      this.view(ReportItemDTO.get(super.getClientRequest(), id));
    }
    catch (Throwable t)
    {
      boolean redirect = dss.vector.solutions.util.ErrorUtility.prepareThrowable(t, req, resp, this.isAsynchronous());
      if (!redirect)
      {
        this.failView(id);
      }
    }
  }

  private void view(ReportItemDTO dto) throws IOException, ServletException
  {
    try
    {
      RedirectUtility utility = new RedirectUtility(req, resp);
      utility.put("id", dto.getId());
      utility.checkURL(this.getClass().getSimpleName(), "view");

      req.setAttribute("item", dto);
      render("viewComponent.jsp");
    }
    catch (Throwable t)
    {
      boolean redirect = dss.vector.solutions.util.ErrorUtility.prepareThrowable(t, req, resp, this.isAsynchronous());
      if (!redirect)
      {
        this.failView(dto.getId());
      }
    }
  }

  public void failView(String id) throws IOException, ServletException
  {
    this.viewAll();
  }

  public void viewAll() throws IOException, ServletException
  {
    ClientRequestIF clientRequest = super.getClientRequest();
    dss.vector.solutions.report.ReportItemQueryDTO query = ReportItemDTO.getAllInstances(clientRequest, null, true, 20, 1);
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
    dss.vector.solutions.report.ReportItemQueryDTO query = ReportItemDTO.getAllInstances(clientRequest, sortAttribute, isAscending, pageSize, pageNumber);
    req.setAttribute("query", query);
    render("viewAllComponent.jsp");
  }

  public void failViewPage(String sortAttribute, String isAscending, String pageSize, String pageNumber) throws IOException, ServletException
  {
    resp.sendError(500);
  }
}
