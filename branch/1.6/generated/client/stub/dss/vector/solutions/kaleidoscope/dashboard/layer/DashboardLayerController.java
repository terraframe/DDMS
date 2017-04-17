package dss.vector.solutions.kaleidoscope.dashboard.layer;

public class DashboardLayerController extends DashboardLayerControllerBase implements com.runwaysdk.generation.loader.Reloadable
{
  public static final String JSP_DIR = "WEB-INF/dss/vector/solutions/kaleidoscope/dashboard/layer/DashboardLayer/";
  public static final String LAYOUT = "/layout.jsp";
  
  public DashboardLayerController(javax.servlet.http.HttpServletRequest req, javax.servlet.http.HttpServletResponse resp, java.lang.Boolean isAsynchronous)
  {
    super(req, resp, isAsynchronous, JSP_DIR, LAYOUT);
  }
  
  public void cancel(dss.vector.solutions.kaleidoscope.dashboard.layer.DashboardLayerDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    dto.unlock();
    this.view(dto.getId());
  }
  public void failCancel(dss.vector.solutions.kaleidoscope.dashboard.layer.DashboardLayerDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    this.edit(dto.getId());
  }
  public void create(dss.vector.solutions.kaleidoscope.dashboard.layer.DashboardLayerDTO dto) throws java.io.IOException, javax.servlet.ServletException
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
  public void failCreate(dss.vector.solutions.kaleidoscope.dashboard.layer.DashboardLayerDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    req.setAttribute("dashboardMap", dss.vector.solutions.kaleidoscope.dashboard.DashboardMapDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("_layerType", dss.vector.solutions.kaleidoscope.dashboard.layer.AllLayerTypeDTO.allItems(super.getClientSession().getRequest()));
    req.setAttribute("item", dto);
    render("createComponent.jsp");
  }
  public void delete(dss.vector.solutions.kaleidoscope.dashboard.layer.DashboardLayerDTO dto) throws java.io.IOException, javax.servlet.ServletException
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
  public void failDelete(dss.vector.solutions.kaleidoscope.dashboard.layer.DashboardLayerDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    req.setAttribute("dashboardMap", dss.vector.solutions.kaleidoscope.dashboard.DashboardMapDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("_layerType", dss.vector.solutions.kaleidoscope.dashboard.layer.AllLayerTypeDTO.allItems(super.getClientSession().getRequest()));
    req.setAttribute("item", dto);
    render("editComponent.jsp");
  }
  public void edit(java.lang.String id) throws java.io.IOException, javax.servlet.ServletException
  {
    try
    {
      dss.vector.solutions.kaleidoscope.dashboard.layer.DashboardLayerDTO dto = dss.vector.solutions.kaleidoscope.dashboard.layer.DashboardLayerDTO.lock(super.getClientRequest(), id);
      req.setAttribute("dashboardMap", dss.vector.solutions.kaleidoscope.dashboard.DashboardMapDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
      req.setAttribute("_layerType", dss.vector.solutions.kaleidoscope.dashboard.layer.AllLayerTypeDTO.allItems(super.getClientSession().getRequest()));
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
  public void update(dss.vector.solutions.kaleidoscope.dashboard.layer.DashboardLayerDTO dto) throws java.io.IOException, javax.servlet.ServletException
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
  public void failUpdate(dss.vector.solutions.kaleidoscope.dashboard.layer.DashboardLayerDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    req.setAttribute("dashboardMap", dss.vector.solutions.kaleidoscope.dashboard.DashboardMapDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("_layerType", dss.vector.solutions.kaleidoscope.dashboard.layer.AllLayerTypeDTO.allItems(super.getClientSession().getRequest()));
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
      dss.vector.solutions.kaleidoscope.dashboard.layer.DashboardLayerDTO dto = dss.vector.solutions.kaleidoscope.dashboard.layer.DashboardLayerDTO.get(clientRequest, id);
      req.setAttribute("dashboardMap", dss.vector.solutions.kaleidoscope.dashboard.DashboardMapDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
      req.setAttribute("_layerType", dss.vector.solutions.kaleidoscope.dashboard.layer.AllLayerTypeDTO.allItems(super.getClientSession().getRequest()));
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
    dss.vector.solutions.kaleidoscope.dashboard.layer.DashboardLayerQueryDTO query = dss.vector.solutions.kaleidoscope.dashboard.layer.DashboardLayerDTO.getAllInstances(clientRequest, null, true, 20, 1);
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
    dss.vector.solutions.kaleidoscope.dashboard.layer.DashboardLayerQueryDTO query = dss.vector.solutions.kaleidoscope.dashboard.layer.DashboardLayerDTO.getAllInstances(clientRequest, sortAttribute, isAscending, pageSize, pageNumber);
    req.setAttribute("query", query);
    render("viewAllComponent.jsp");
  }
  public void failViewPage(java.lang.String sortAttribute, java.lang.String isAscending, java.lang.String pageSize, java.lang.String pageNumber) throws java.io.IOException, javax.servlet.ServletException
  {
    resp.sendError(500);
  }
}
