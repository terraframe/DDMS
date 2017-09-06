package dss.vector.solutions.kaleidoscope.dashboard;

public class MetadataWrapperController extends MetadataWrapperControllerBase implements com.runwaysdk.generation.loader.Reloadable
{
  public static final String JSP_DIR = "WEB-INF/dss/vector/solutions/kaleidoscope/dashboard/MetadataWrapper/";
  public static final String LAYOUT = "/layout.jsp";
  
  public MetadataWrapperController(javax.servlet.http.HttpServletRequest req, javax.servlet.http.HttpServletResponse resp, java.lang.Boolean isAsynchronous)
  {
    super(req, resp, isAsynchronous, JSP_DIR, LAYOUT);
  }
  
  public void cancel(dss.vector.solutions.kaleidoscope.dashboard.MetadataWrapperDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    dto.unlock();
    this.view(dto.getId());
  }
  public void failCancel(dss.vector.solutions.kaleidoscope.dashboard.MetadataWrapperDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    this.edit(dto.getId());
  }
  public void create(dss.vector.solutions.kaleidoscope.dashboard.MetadataWrapperDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    try
    {
      dto.apply();
      this.view(dto.getId());
    }
    catch(java.lang.Throwable t)
    {
      boolean redirect = dss.vector.solutions.util.ErrorUtility.prepareThrowable(t, req, resp, this.isAsynchronous());
      if (!redirect)
      {
        this.failCreate(dto);
      }
    }
  }
  public void failCreate(dss.vector.solutions.kaleidoscope.dashboard.MetadataWrapperDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    req.setAttribute("dashboard", dss.vector.solutions.kaleidoscope.dashboard.DashboardDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("wrappedMdClass", com.runwaysdk.system.metadata.MdClassDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("item", dto);
    render("createComponent.jsp");
  }
  public void delete(dss.vector.solutions.kaleidoscope.dashboard.MetadataWrapperDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    try
    {
      dto.delete();
      this.viewAll();
    }
    catch(java.lang.Throwable t)
    {
      boolean redirect = dss.vector.solutions.util.ErrorUtility.prepareThrowable(t, req, resp, this.isAsynchronous());
      if (!redirect)
      {
        this.failDelete(dto);
      }
    }
  }
  public void failDelete(dss.vector.solutions.kaleidoscope.dashboard.MetadataWrapperDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    req.setAttribute("dashboard", dss.vector.solutions.kaleidoscope.dashboard.DashboardDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("wrappedMdClass", com.runwaysdk.system.metadata.MdClassDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("item", dto);
    render("editComponent.jsp");
  }
  public void edit(java.lang.String id) throws java.io.IOException, javax.servlet.ServletException
  {
    try
    {
      dss.vector.solutions.kaleidoscope.dashboard.MetadataWrapperDTO dto = dss.vector.solutions.kaleidoscope.dashboard.MetadataWrapperDTO.lock(super.getClientRequest(), id);
      req.setAttribute("dashboard", dss.vector.solutions.kaleidoscope.dashboard.DashboardDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
      req.setAttribute("wrappedMdClass", com.runwaysdk.system.metadata.MdClassDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
      req.setAttribute("item", dto);
      render("editComponent.jsp");
    }
    catch(java.lang.Throwable t)
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
      dss.vector.solutions.kaleidoscope.dashboard.MetadataWrapperDTO dto = new dss.vector.solutions.kaleidoscope.dashboard.MetadataWrapperDTO(clientRequest);
      req.setAttribute("dashboard", dss.vector.solutions.kaleidoscope.dashboard.DashboardDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
      req.setAttribute("wrappedMdClass", com.runwaysdk.system.metadata.MdClassDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
      req.setAttribute("item", dto);
      render("createComponent.jsp");
    }
    catch(java.lang.Throwable t)
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
  public void update(dss.vector.solutions.kaleidoscope.dashboard.MetadataWrapperDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    try
    {
      dto.apply();
      this.view(dto.getId());
    }
    catch(java.lang.Throwable t)
    {
      boolean redirect = dss.vector.solutions.util.ErrorUtility.prepareThrowable(t, req, resp, this.isAsynchronous());
      if (!redirect)
      {
        this.failUpdate(dto);
      }
    }
  }
  public void failUpdate(dss.vector.solutions.kaleidoscope.dashboard.MetadataWrapperDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    req.setAttribute("dashboard", dss.vector.solutions.kaleidoscope.dashboard.DashboardDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("wrappedMdClass", com.runwaysdk.system.metadata.MdClassDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
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
      dss.vector.solutions.kaleidoscope.dashboard.MetadataWrapperDTO dto = dss.vector.solutions.kaleidoscope.dashboard.MetadataWrapperDTO.get(clientRequest, id);
      req.setAttribute("dashboard", dss.vector.solutions.kaleidoscope.dashboard.DashboardDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
      req.setAttribute("wrappedMdClass", com.runwaysdk.system.metadata.MdClassDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
      req.setAttribute("item", dto);
      render("viewComponent.jsp");
    }
    catch(java.lang.Throwable t)
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
    com.runwaysdk.constants.ClientRequestIF clientRequest = super.getClientRequest();
    dss.vector.solutions.kaleidoscope.dashboard.MetadataWrapperQueryDTO query = dss.vector.solutions.kaleidoscope.dashboard.MetadataWrapperDTO.getAllInstances(clientRequest, null, true, 20, 1);
    req.setAttribute("query", query);
    render("viewAllComponent.jsp");
  }
  public void failViewAll() throws java.io.IOException, javax.servlet.ServletException
  {
    resp.sendError(500);
  }
  public void viewPage(java.lang.String sortAttribute, java.lang.Boolean isAscending, java.lang.Integer pageSize, java.lang.Integer pageNumber) throws java.io.IOException, javax.servlet.ServletException
  {
    com.runwaysdk.constants.ClientRequestIF clientRequest = super.getClientRequest();
    dss.vector.solutions.kaleidoscope.dashboard.MetadataWrapperQueryDTO query = dss.vector.solutions.kaleidoscope.dashboard.MetadataWrapperDTO.getAllInstances(clientRequest, sortAttribute, isAscending, pageSize, pageNumber);
    req.setAttribute("query", query);
    render("viewAllComponent.jsp");
  }
  public void failViewPage(java.lang.String sortAttribute, java.lang.String isAscending, java.lang.String pageSize, java.lang.String pageNumber) throws java.io.IOException, javax.servlet.ServletException
  {
    resp.sendError(500);
  }
}
