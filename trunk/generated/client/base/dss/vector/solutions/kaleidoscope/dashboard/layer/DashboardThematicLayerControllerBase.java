package dss.vector.solutions.kaleidoscope.dashboard.layer;

@com.runwaysdk.business.ClassSignature(hash = 1810305124)
public class DashboardThematicLayerControllerBase implements com.runwaysdk.generation.loader.Reloadable
{
  public static final String CLASS = "dss.vector.solutions.kaleidoscope.dashboard.layer.DashboardThematicLayerController";
  protected javax.servlet.http.HttpServletRequest req;
  protected javax.servlet.http.HttpServletResponse resp;
  protected java.lang.Boolean isAsynchronous;
  protected java.lang.String dir;
  protected java.lang.String layout;
  
  public DashboardThematicLayerControllerBase(javax.servlet.http.HttpServletRequest req, javax.servlet.http.HttpServletResponse resp, java.lang.Boolean isAsynchronous)
  {
    this(req, resp, isAsynchronous, "","");
  }
  
  public DashboardThematicLayerControllerBase(javax.servlet.http.HttpServletRequest req, javax.servlet.http.HttpServletResponse resp, java.lang.Boolean isAsynchronous, java.lang.String dir, java.lang.String layout)
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
  
  @com.runwaysdk.controller.ActionParameters(parameters="dss.vector.solutions.kaleidoscope.dashboard.layer.DashboardThematicLayerDTO:layer, dss.vector.solutions.kaleidoscope.dashboard.DashboardStyleDTO:style, java.lang.String:mapId, dss.vector.solutions.kaleidoscope.dashboard.AggregationStrategyDTO:strategy, java.lang.String:state", post=true)
  public void applyWithStyle(dss.vector.solutions.kaleidoscope.dashboard.layer.DashboardThematicLayerDTO layer, dss.vector.solutions.kaleidoscope.dashboard.DashboardStyleDTO style, java.lang.String mapId, dss.vector.solutions.kaleidoscope.dashboard.AggregationStrategyDTO strategy, java.lang.String state) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.kaleidoscope.dashboard.layer.DashboardThematicLayerController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.kaleidoscope.dashboard.layer.DashboardThematicLayerController.applyWithStyle");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="dss.vector.solutions.kaleidoscope.dashboard.layer.DashboardThematicLayerDTO:layer, dss.vector.solutions.kaleidoscope.dashboard.DashboardStyleDTO:style, java.lang.String:mapId, dss.vector.solutions.kaleidoscope.dashboard.AggregationStrategyDTO:strategy, java.lang.String:state", post=true)
  public void failApplyWithStyle(dss.vector.solutions.kaleidoscope.dashboard.layer.DashboardThematicLayerDTO layer, dss.vector.solutions.kaleidoscope.dashboard.DashboardStyleDTO style, java.lang.String mapId, dss.vector.solutions.kaleidoscope.dashboard.AggregationStrategyDTO strategy, java.lang.String state) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.kaleidoscope.dashboard.layer.DashboardThematicLayerController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.kaleidoscope.dashboard.layer.DashboardThematicLayerController.failApplyWithStyle");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="dss.vector.solutions.kaleidoscope.dashboard.layer.DashboardThematicLayerDTO:dto", post=true)
  public void cancel(dss.vector.solutions.kaleidoscope.dashboard.layer.DashboardThematicLayerDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.kaleidoscope.dashboard.layer.DashboardThematicLayerController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.kaleidoscope.dashboard.layer.DashboardThematicLayerController.cancel");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="dss.vector.solutions.kaleidoscope.dashboard.layer.DashboardThematicLayerDTO:dto", post=true)
  public void failCancel(dss.vector.solutions.kaleidoscope.dashboard.layer.DashboardThematicLayerDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.kaleidoscope.dashboard.layer.DashboardThematicLayerController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.kaleidoscope.dashboard.layer.DashboardThematicLayerController.failCancel");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="dss.vector.solutions.kaleidoscope.dashboard.layer.DashboardThematicLayerDTO:dto", post=true)
  public void create(dss.vector.solutions.kaleidoscope.dashboard.layer.DashboardThematicLayerDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.kaleidoscope.dashboard.layer.DashboardThematicLayerController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.kaleidoscope.dashboard.layer.DashboardThematicLayerController.create");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="dss.vector.solutions.kaleidoscope.dashboard.layer.DashboardThematicLayerDTO:dto", post=true)
  public void failCreate(dss.vector.solutions.kaleidoscope.dashboard.layer.DashboardThematicLayerDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.kaleidoscope.dashboard.layer.DashboardThematicLayerController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.kaleidoscope.dashboard.layer.DashboardThematicLayerController.failCreate");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="dss.vector.solutions.kaleidoscope.dashboard.layer.DashboardThematicLayerDTO:dto", post=true)
  public void delete(dss.vector.solutions.kaleidoscope.dashboard.layer.DashboardThematicLayerDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.kaleidoscope.dashboard.layer.DashboardThematicLayerController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.kaleidoscope.dashboard.layer.DashboardThematicLayerController.delete");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="dss.vector.solutions.kaleidoscope.dashboard.layer.DashboardThematicLayerDTO:dto", post=true)
  public void failDelete(dss.vector.solutions.kaleidoscope.dashboard.layer.DashboardThematicLayerDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.kaleidoscope.dashboard.layer.DashboardThematicLayerController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.kaleidoscope.dashboard.layer.DashboardThematicLayerController.failDelete");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="java.lang.String:id", post=false)
  public void edit(java.lang.String id) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.kaleidoscope.dashboard.layer.DashboardThematicLayerController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.kaleidoscope.dashboard.layer.DashboardThematicLayerController.edit");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="java.lang.String:id", post=false)
  public void failEdit(java.lang.String id) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.kaleidoscope.dashboard.layer.DashboardThematicLayerController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.kaleidoscope.dashboard.layer.DashboardThematicLayerController.failEdit");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="", post=false)
  public void newInstance() throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.kaleidoscope.dashboard.layer.DashboardThematicLayerController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.kaleidoscope.dashboard.layer.DashboardThematicLayerController.newInstance");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="", post=false)
  public void failNewInstance() throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.kaleidoscope.dashboard.layer.DashboardThematicLayerController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.kaleidoscope.dashboard.layer.DashboardThematicLayerController.failNewInstance");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="java.lang.String:mdAttribute, java.lang.String:mapId", post=true)
  public void newThematicInstance(java.lang.String mdAttribute, java.lang.String mapId) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.kaleidoscope.dashboard.layer.DashboardThematicLayerController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.kaleidoscope.dashboard.layer.DashboardThematicLayerController.newThematicInstance");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="java.lang.String:mdAttribute, java.lang.String:mapId", post=true)
  public void failNewThematicInstance(java.lang.String mdAttribute, java.lang.String mapId) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.kaleidoscope.dashboard.layer.DashboardThematicLayerController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.kaleidoscope.dashboard.layer.DashboardThematicLayerController.failNewThematicInstance");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="dss.vector.solutions.kaleidoscope.dashboard.layer.DashboardThematicLayerDTO:dto", post=true)
  public void update(dss.vector.solutions.kaleidoscope.dashboard.layer.DashboardThematicLayerDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.kaleidoscope.dashboard.layer.DashboardThematicLayerController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.kaleidoscope.dashboard.layer.DashboardThematicLayerController.update");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="dss.vector.solutions.kaleidoscope.dashboard.layer.DashboardThematicLayerDTO:dto", post=true)
  public void failUpdate(dss.vector.solutions.kaleidoscope.dashboard.layer.DashboardThematicLayerDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.kaleidoscope.dashboard.layer.DashboardThematicLayerController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.kaleidoscope.dashboard.layer.DashboardThematicLayerController.failUpdate");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="java.lang.String:id", post=false)
  public void view(java.lang.String id) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.kaleidoscope.dashboard.layer.DashboardThematicLayerController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.kaleidoscope.dashboard.layer.DashboardThematicLayerController.view");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="java.lang.String:id", post=false)
  public void failView(java.lang.String id) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.kaleidoscope.dashboard.layer.DashboardThematicLayerController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.kaleidoscope.dashboard.layer.DashboardThematicLayerController.failView");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="", post=false)
  public void viewAll() throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.kaleidoscope.dashboard.layer.DashboardThematicLayerController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.kaleidoscope.dashboard.layer.DashboardThematicLayerController.viewAll");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="", post=false)
  public void failViewAll() throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.kaleidoscope.dashboard.layer.DashboardThematicLayerController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.kaleidoscope.dashboard.layer.DashboardThematicLayerController.failViewAll");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="java.lang.String:sortAttribute, java.lang.Boolean:isAscending, java.lang.Integer:pageSize, java.lang.Integer:pageNumber", post=false)
  public void viewPage(java.lang.String sortAttribute, java.lang.Boolean isAscending, java.lang.Integer pageSize, java.lang.Integer pageNumber) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.kaleidoscope.dashboard.layer.DashboardThematicLayerController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.kaleidoscope.dashboard.layer.DashboardThematicLayerController.viewPage");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="java.lang.String:sortAttribute, java.lang.String:isAscending, java.lang.String:pageSize, java.lang.String:pageNumber", post=false)
  public void failViewPage(java.lang.String sortAttribute, java.lang.String isAscending, java.lang.String pageSize, java.lang.String pageNumber) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.kaleidoscope.dashboard.layer.DashboardThematicLayerController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.kaleidoscope.dashboard.layer.DashboardThematicLayerController.failViewPage");
  }
  
}
