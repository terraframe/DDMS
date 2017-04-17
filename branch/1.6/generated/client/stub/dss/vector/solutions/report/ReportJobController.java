package dss.vector.solutions.report;

import dss.vector.solutions.util.ErrorUtility;

public class ReportJobController extends ReportJobControllerBase implements com.runwaysdk.generation.loader.Reloadable
{
  public static final String JSP_DIR = "WEB-INF/dss/vector/solutions/report/ReportJob/";

  public static final String LAYOUT  = "/layout.jsp";

  public ReportJobController(javax.servlet.http.HttpServletRequest req, javax.servlet.http.HttpServletResponse resp, java.lang.Boolean isAsynchronous)
  {
    super(req, resp, isAsynchronous, JSP_DIR, LAYOUT);
  }

  public void cancel(dss.vector.solutions.report.ReportJobDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    dto.unlock();
    this.view(dto.getId());
  }

  public void failCancel(dss.vector.solutions.report.ReportJobDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    this.edit(dto.getId());
  }

  public void create(dss.vector.solutions.report.ReportJobDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    try
    {
      dto.apply();
      this.view(dto.getId());
    }
    catch (java.lang.Throwable t)
    {
      boolean redirect = dss.vector.solutions.util.ErrorUtility.prepareThrowable(t, req, resp, this.isAsynchronous());
      if (!redirect)
      {
        this.failCreate(dto);
      }
    }
  }

  public void failCreate(dss.vector.solutions.report.ReportJobDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    req.setAttribute("reportItem", dss.vector.solutions.report.ReportItemDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("item", dto);
    render("createComponent.jsp");
  }

  public void delete(dss.vector.solutions.report.ReportJobDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    try
    {
      dto.delete();
      this.viewAll();
    }
    catch (java.lang.Throwable t)
    {
      boolean redirect = dss.vector.solutions.util.ErrorUtility.prepareThrowable(t, req, resp, this.isAsynchronous());
      if (!redirect)
      {
        this.failDelete(dto);
      }
    }
  }

  public void failDelete(dss.vector.solutions.report.ReportJobDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    req.setAttribute("reportItem", dss.vector.solutions.report.ReportItemDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("item", dto);
    render("editComponent.jsp");
  }

  public void edit(java.lang.String id) throws java.io.IOException, javax.servlet.ServletException
  {
    try
    {
      dss.vector.solutions.report.ReportJobDTO dto = dss.vector.solutions.report.ReportJobDTO.lock(super.getClientRequest(), id);
      req.setAttribute("reportItem", dss.vector.solutions.report.ReportItemDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
      req.setAttribute("item", dto);
      render("editComponent.jsp");
    }
    catch (java.lang.Throwable t)
    {
      boolean redirect = dss.vector.solutions.util.ErrorUtility.prepareThrowable(t, req, resp, this.isAsynchronous());
      if (!redirect)
      {
        this.failEdit(id);
      }
    }
  }

  public void failEdit(java.lang.String id) throws java.io.IOException, javax.servlet.ServletException
  {
    this.view(id);
  }

  public void newInstance() throws java.io.IOException, javax.servlet.ServletException
  {
    try
    {
      com.runwaysdk.constants.ClientRequestIF clientRequest = super.getClientRequest();
      dss.vector.solutions.report.ReportJobDTO dto = new dss.vector.solutions.report.ReportJobDTO(clientRequest);
      req.setAttribute("reportItem", dss.vector.solutions.report.ReportItemDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
      req.setAttribute("item", dto);
      render("createComponent.jsp");
    }
    catch (java.lang.Throwable t)
    {
      boolean redirect = dss.vector.solutions.util.ErrorUtility.prepareThrowable(t, req, resp, this.isAsynchronous());
      if (!redirect)
      {
        this.failNewInstance();
      }
    }
  }

  public void failNewInstance() throws java.io.IOException, javax.servlet.ServletException
  {
    this.viewAll();
  }

  public void update(dss.vector.solutions.report.ReportJobDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    try
    {
      dto.apply();
      this.view(dto.getId());
    }
    catch (java.lang.Throwable t)
    {
      boolean redirect = dss.vector.solutions.util.ErrorUtility.prepareThrowable(t, req, resp, this.isAsynchronous());
      if (!redirect)
      {
        this.failUpdate(dto);
      }
    }
  }

  public void failUpdate(dss.vector.solutions.report.ReportJobDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    req.setAttribute("reportItem", dss.vector.solutions.report.ReportItemDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("item", dto);
    render("editComponent.jsp");
  }

  public void view(java.lang.String id) throws java.io.IOException, javax.servlet.ServletException
  {
    try
    {
      dss.vector.solutions.util.RedirectUtility utility = new dss.vector.solutions.util.RedirectUtility(req, resp);
      utility.put("id", id);
      utility.checkURL(this.getClass().getSimpleName(), "view");
      com.runwaysdk.constants.ClientRequestIF clientRequest = super.getClientRequest();
      dss.vector.solutions.report.ReportJobDTO dto = dss.vector.solutions.report.ReportJobDTO.get(clientRequest, id);
      req.setAttribute("reportItem", dss.vector.solutions.report.ReportItemDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
      req.setAttribute("item", dto);
      render("viewComponent.jsp");
    }
    catch (java.lang.Throwable t)
    {
      boolean redirect = dss.vector.solutions.util.ErrorUtility.prepareThrowable(t, req, resp, this.isAsynchronous());
      if (!redirect)
      {
        this.failView(id);
      }
    }
  }

  public void failView(java.lang.String id) throws java.io.IOException, javax.servlet.ServletException
  {
    this.viewAll();
  }

  public void viewAll() throws java.io.IOException, javax.servlet.ServletException
  {
    try
    {
      // Check read permissions
      ReportJobDTO.getAllInstances(this.getClientRequest(), null, true, 1, 1);

      render("viewAllComponent.jsp");
    }
    catch (Throwable t)
    {
      boolean redirected = ErrorUtility.prepareThrowable(t, req, resp, this.isAsynchronous());

      if (!redirected)
      {
        this.failViewAll();
      }
    }

  }

  public void failViewAll() throws java.io.IOException, javax.servlet.ServletException
  {
    req.getRequestDispatcher("/index.jsp").forward(req, resp);
  }

  public void viewPage(java.lang.String sortAttribute, java.lang.Boolean isAscending, java.lang.Integer pageSize, java.lang.Integer pageNumber) throws java.io.IOException, javax.servlet.ServletException
  {
    com.runwaysdk.constants.ClientRequestIF clientRequest = super.getClientRequest();
    dss.vector.solutions.report.ReportJobQueryDTO query = dss.vector.solutions.report.ReportJobDTO.getAllInstances(clientRequest, sortAttribute, isAscending, pageSize, pageNumber);
    req.setAttribute("query", query);
    render("viewAllComponent.jsp");
  }

  public void failViewPage(java.lang.String sortAttribute, java.lang.String isAscending, java.lang.String pageSize, java.lang.String pageNumber) throws java.io.IOException, javax.servlet.ServletException
  {
    resp.sendError(500);
  }
}
