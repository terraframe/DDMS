package dss.vector.solutions.kaleidoscope.dashboard;

@com.runwaysdk.business.ClassSignature(hash = -1673130762)
public class DashboardMapControllerBase implements com.runwaysdk.generation.loader.Reloadable
{
  public static final String CLASS = "dss.vector.solutions.kaleidoscope.dashboard.DashboardMapController";
  protected javax.servlet.http.HttpServletRequest req;
  protected javax.servlet.http.HttpServletResponse resp;
  protected java.lang.Boolean isAsynchronous;
  protected java.lang.String dir;
  protected java.lang.String layout;
  
  public DashboardMapControllerBase(javax.servlet.http.HttpServletRequest req, javax.servlet.http.HttpServletResponse resp, java.lang.Boolean isAsynchronous)
  {
    this(req, resp, isAsynchronous, "","");
  }
  
  public DashboardMapControllerBase(javax.servlet.http.HttpServletRequest req, javax.servlet.http.HttpServletResponse resp, java.lang.Boolean isAsynchronous, java.lang.String dir, java.lang.String layout)
  {
    this.req = req;
    this.resp = resp;
    this.isAsynchronous = isAsynchronous;
    this.dir = dir;
    this.layout = layout;
  }
  
  protected void render(String jsp) throws java.io.IOException, javax.servlet.ServletException
  {
    if(!resp.isCommitted())
    {
      if(this.isAsynchronous())
      {
        req.getRequestDispatcher(dir+jsp).forward(req, resp);
      }
      else
      {
        req.setAttribute(com.runwaysdk.controller.JSPFetcher.INNER_JSP, dir+jsp);
        req.getRequestDispatcher(layout).forward(req, resp);
      }
    }
  }
  
  public javax.servlet.http.HttpServletRequest getRequest()
  {
    return this.req;
  }
  
  public javax.servlet.http.HttpServletResponse getResponse()
  {
    return this.resp;
  }
  
  public java.lang.Boolean isAsynchronous()
  {
    return this.isAsynchronous;
  }
  
  public com.runwaysdk.constants.ClientRequestIF getClientRequest()
  {
    return (com.runwaysdk.constants.ClientRequestIF) req.getAttribute(com.runwaysdk.constants.ClientConstants.CLIENTREQUEST);
  }
  
  public com.runwaysdk.ClientSession getClientSession()
  {
    return (com.runwaysdk.ClientSession) req.getSession().getAttribute(com.runwaysdk.constants.ClientConstants.CLIENTSESSION);
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="dss.vector.solutions.kaleidoscope.dashboard.DashboardMapDTO:dto", post=true)
  public void cancel(dss.vector.solutions.kaleidoscope.dashboard.DashboardMapDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.kaleidoscope.dashboard.DashboardMapController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.kaleidoscope.dashboard.DashboardMapController.cancel");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="dss.vector.solutions.kaleidoscope.dashboard.DashboardMapDTO:dto", post=true)
  public void failCancel(dss.vector.solutions.kaleidoscope.dashboard.DashboardMapDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.kaleidoscope.dashboard.DashboardMapController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.kaleidoscope.dashboard.DashboardMapController.failCancel");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="dss.vector.solutions.kaleidoscope.dashboard.DashboardMapDTO:dto", post=true)
  public void create(dss.vector.solutions.kaleidoscope.dashboard.DashboardMapDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.kaleidoscope.dashboard.DashboardMapController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.kaleidoscope.dashboard.DashboardMapController.create");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="dss.vector.solutions.kaleidoscope.dashboard.DashboardMapDTO:dto", post=true)
  public void failCreate(dss.vector.solutions.kaleidoscope.dashboard.DashboardMapDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.kaleidoscope.dashboard.DashboardMapController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.kaleidoscope.dashboard.DashboardMapController.failCreate");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="", post=false)
  public void createMapForSession() throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.kaleidoscope.dashboard.DashboardMapController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.kaleidoscope.dashboard.DashboardMapController.createMapForSession");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="", post=false)
  public void failCreateMapForSession() throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.kaleidoscope.dashboard.DashboardMapController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.kaleidoscope.dashboard.DashboardMapController.failCreateMapForSession");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="dss.vector.solutions.kaleidoscope.dashboard.DashboardMapDTO:dto", post=true)
  public void delete(dss.vector.solutions.kaleidoscope.dashboard.DashboardMapDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.kaleidoscope.dashboard.DashboardMapController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.kaleidoscope.dashboard.DashboardMapController.delete");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="dss.vector.solutions.kaleidoscope.dashboard.DashboardMapDTO:dto", post=true)
  public void failDelete(dss.vector.solutions.kaleidoscope.dashboard.DashboardMapDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.kaleidoscope.dashboard.DashboardMapController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.kaleidoscope.dashboard.DashboardMapController.failDelete");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="java.lang.String:id", post=false)
  public void edit(java.lang.String id) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.kaleidoscope.dashboard.DashboardMapController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.kaleidoscope.dashboard.DashboardMapController.edit");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="java.lang.String:id", post=false)
  public void failEdit(java.lang.String id) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.kaleidoscope.dashboard.DashboardMapController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.kaleidoscope.dashboard.DashboardMapController.failEdit");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="java.lang.String:mapId, java.lang.String:state, java.lang.String:layerId", post=false)
  public void exportLayerData(java.lang.String mapId, java.lang.String state, java.lang.String layerId) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.kaleidoscope.dashboard.DashboardMapController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.kaleidoscope.dashboard.DashboardMapController.exportLayerData");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="java.lang.String:mapId, java.lang.String:state, java.lang.String:layerId", post=false)
  public void failExportLayerData(java.lang.String mapId, java.lang.String state, java.lang.String layerId) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.kaleidoscope.dashboard.DashboardMapController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.kaleidoscope.dashboard.DashboardMapController.failExportLayerData");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="java.lang.String:mapId, java.lang.String:outFileName, java.lang.String:outFileFormat, java.lang.String:mapBounds, java.lang.String:mapSize, java.lang.String:activeBaseMap, java.lang.String:dashboardStateJson", post=false)
  public void exportMap(java.lang.String mapId, java.lang.String outFileName, java.lang.String outFileFormat, java.lang.String mapBounds, java.lang.String mapSize, java.lang.String activeBaseMap, java.lang.String dashboardStateJson) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.kaleidoscope.dashboard.DashboardMapController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.kaleidoscope.dashboard.DashboardMapController.exportMap");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="java.lang.String:mapId, java.lang.String:outFileName, java.lang.String:outFileFormat, java.lang.String:mapBounds, java.lang.String:mapSize, java.lang.String:activeBaseMap, java.lang.String:dashboardStateJson", post=false)
  public void failExportMap(java.lang.String mapId, java.lang.String outFileName, java.lang.String outFileFormat, java.lang.String mapBounds, java.lang.String mapSize, java.lang.String activeBaseMap, java.lang.String dashboardStateJson) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.kaleidoscope.dashboard.DashboardMapController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.kaleidoscope.dashboard.DashboardMapController.failExportMap");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="", post=false)
  public void newInstance() throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.kaleidoscope.dashboard.DashboardMapController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.kaleidoscope.dashboard.DashboardMapController.newInstance");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="", post=false)
  public void failNewInstance() throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.kaleidoscope.dashboard.DashboardMapController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.kaleidoscope.dashboard.DashboardMapController.failNewInstance");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="java.lang.String:state, java.lang.String:id", post=true)
  public void refresh(java.lang.String state, java.lang.String id) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.kaleidoscope.dashboard.DashboardMapController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.kaleidoscope.dashboard.DashboardMapController.refresh");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="java.lang.String:state, java.lang.String:id", post=true)
  public void failRefresh(java.lang.String state, java.lang.String id) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.kaleidoscope.dashboard.DashboardMapController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.kaleidoscope.dashboard.DashboardMapController.failRefresh");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="dss.vector.solutions.kaleidoscope.dashboard.DashboardMapDTO:dto", post=true)
  public void update(dss.vector.solutions.kaleidoscope.dashboard.DashboardMapDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.kaleidoscope.dashboard.DashboardMapController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.kaleidoscope.dashboard.DashboardMapController.update");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="dss.vector.solutions.kaleidoscope.dashboard.DashboardMapDTO:dto", post=true)
  public void failUpdate(dss.vector.solutions.kaleidoscope.dashboard.DashboardMapDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.kaleidoscope.dashboard.DashboardMapController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.kaleidoscope.dashboard.DashboardMapController.failUpdate");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="java.lang.String:id", post=false)
  public void view(java.lang.String id) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.kaleidoscope.dashboard.DashboardMapController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.kaleidoscope.dashboard.DashboardMapController.view");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="java.lang.String:id", post=false)
  public void failView(java.lang.String id) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.kaleidoscope.dashboard.DashboardMapController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.kaleidoscope.dashboard.DashboardMapController.failView");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="", post=false)
  public void viewAll() throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.kaleidoscope.dashboard.DashboardMapController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.kaleidoscope.dashboard.DashboardMapController.viewAll");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="", post=false)
  public void failViewAll() throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.kaleidoscope.dashboard.DashboardMapController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.kaleidoscope.dashboard.DashboardMapController.failViewAll");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="java.lang.String:sortAttribute, java.lang.Boolean:isAscending, java.lang.Integer:pageSize, java.lang.Integer:pageNumber", post=false)
  public void viewPage(java.lang.String sortAttribute, java.lang.Boolean isAscending, java.lang.Integer pageSize, java.lang.Integer pageNumber) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.kaleidoscope.dashboard.DashboardMapController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.kaleidoscope.dashboard.DashboardMapController.viewPage");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="java.lang.String:sortAttribute, java.lang.String:isAscending, java.lang.String:pageSize, java.lang.String:pageNumber", post=false)
  public void failViewPage(java.lang.String sortAttribute, java.lang.String isAscending, java.lang.String pageSize, java.lang.String pageNumber) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.kaleidoscope.dashboard.DashboardMapController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.kaleidoscope.dashboard.DashboardMapController.failViewPage");
  }
  
}
