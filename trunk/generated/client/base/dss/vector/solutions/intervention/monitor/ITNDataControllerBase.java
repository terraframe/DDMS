package dss.vector.solutions.intervention.monitor;

@com.runwaysdk.business.ClassSignature(hash = 1602378164)
public class ITNDataControllerBase implements com.runwaysdk.generation.loader.Reloadable
{
  public static final String CLASS = "dss.vector.solutions.intervention.monitor.ITNDataController";
  protected javax.servlet.http.HttpServletRequest req;
  protected javax.servlet.http.HttpServletResponse resp;
  protected java.lang.Boolean isAsynchronous;
  protected java.lang.String dir;
  protected java.lang.String layout;
  
  private static final long serialVersionUID = 1602378164;
  
  public ITNDataControllerBase(javax.servlet.http.HttpServletRequest req, javax.servlet.http.HttpServletResponse resp, java.lang.Boolean isAsynchronous)
  {
    this(req, resp, isAsynchronous, "","");
  }
  
  public ITNDataControllerBase(javax.servlet.http.HttpServletRequest req, javax.servlet.http.HttpServletResponse resp, java.lang.Boolean isAsynchronous, java.lang.String dir, java.lang.String layout)
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
  
  @com.runwaysdk.controller.ActionParameters(parameters="dss.vector.solutions.intervention.monitor.ITNDataViewDTO:dto", post=true)
  public void cancel(dss.vector.solutions.intervention.monitor.ITNDataViewDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.intervention.monitor.ITNDataController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.intervention.monitor.ITNDataController.cancel");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="dss.vector.solutions.intervention.monitor.ITNDataViewDTO:dto", post=true)
  public void failCancel(dss.vector.solutions.intervention.monitor.ITNDataViewDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.intervention.monitor.ITNDataController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.intervention.monitor.ITNDataController.failCancel");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="dss.vector.solutions.intervention.monitor.ITNDataViewDTO:dto, [Ldss.vector.solutions.intervention.monitor.ITNNetDTO;:nets, [Ldss.vector.solutions.intervention.monitor.ITNTargetGroupDTO;:targetGroups, [Ldss.vector.solutions.intervention.monitor.ITNServiceDTO;:services", post=true)
  public void create(dss.vector.solutions.intervention.monitor.ITNDataViewDTO dto, dss.vector.solutions.intervention.monitor.ITNNetDTO[] nets, dss.vector.solutions.intervention.monitor.ITNTargetGroupDTO[] targetGroups, dss.vector.solutions.intervention.monitor.ITNServiceDTO[] services) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.intervention.monitor.ITNDataController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.intervention.monitor.ITNDataController.create");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="dss.vector.solutions.intervention.monitor.ITNDataViewDTO:dto, [Ldss.vector.solutions.intervention.monitor.ITNNetDTO;:nets, [Ldss.vector.solutions.intervention.monitor.ITNTargetGroupDTO;:targetGroups, [Ldss.vector.solutions.intervention.monitor.ITNServiceDTO;:services", post=true)
  public void failCreate(dss.vector.solutions.intervention.monitor.ITNDataViewDTO dto, dss.vector.solutions.intervention.monitor.ITNNetDTO[] nets, dss.vector.solutions.intervention.monitor.ITNTargetGroupDTO[] targetGroups, dss.vector.solutions.intervention.monitor.ITNServiceDTO[] services) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.intervention.monitor.ITNDataController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.intervention.monitor.ITNDataController.failCreate");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="dss.vector.solutions.intervention.monitor.ITNDataViewDTO:dto", post=true)
  public void delete(dss.vector.solutions.intervention.monitor.ITNDataViewDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.intervention.monitor.ITNDataController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.intervention.monitor.ITNDataController.delete");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="dss.vector.solutions.intervention.monitor.ITNDataViewDTO:dto", post=true)
  public void failDelete(dss.vector.solutions.intervention.monitor.ITNDataViewDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.intervention.monitor.ITNDataController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.intervention.monitor.ITNDataController.failDelete");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="java.lang.String:id", post=false)
  public void edit(java.lang.String id) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.intervention.monitor.ITNDataController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.intervention.monitor.ITNDataController.edit");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="java.lang.String:id", post=false)
  public void failEdit(java.lang.String id) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.intervention.monitor.ITNDataController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.intervention.monitor.ITNDataController.failEdit");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="", post=false)
  public void newInstance() throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.intervention.monitor.ITNDataController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.intervention.monitor.ITNDataController.newInstance");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="", post=false)
  public void failNewInstance() throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.intervention.monitor.ITNDataController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.intervention.monitor.ITNDataController.failNewInstance");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="", post=false)
  public void search() throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.intervention.monitor.ITNDataController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.intervention.monitor.ITNDataController.search");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="", post=false)
  public void failSearch() throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.intervention.monitor.ITNDataController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.intervention.monitor.ITNDataController.failSearch");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="java.lang.String:geoId, java.lang.String:periodType, java.lang.Integer:period, java.lang.Integer:year", post=true)
  public void searchByGeoIdAndPeriod(java.lang.String geoId, java.lang.String periodType, java.lang.Integer period, java.lang.Integer year) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.intervention.monitor.ITNDataController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.intervention.monitor.ITNDataController.searchByGeoIdAndPeriod");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="java.lang.String:geoId, java.lang.String:periodType, java.lang.String:period, java.lang.String:year", post=true)
  public void failSearchByGeoIdAndPeriod(java.lang.String geoId, java.lang.String periodType, java.lang.String period, java.lang.String year) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.intervention.monitor.ITNDataController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.intervention.monitor.ITNDataController.failSearchByGeoIdAndPeriod");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="dss.vector.solutions.intervention.monitor.ITNDataViewDTO:dto", post=true)
  public void searchByView(dss.vector.solutions.intervention.monitor.ITNDataViewDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.intervention.monitor.ITNDataController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.intervention.monitor.ITNDataController.searchByView");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="dss.vector.solutions.intervention.monitor.ITNDataViewDTO:dto", post=true)
  public void failSearchByView(dss.vector.solutions.intervention.monitor.ITNDataViewDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.intervention.monitor.ITNDataController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.intervention.monitor.ITNDataController.failSearchByView");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="dss.vector.solutions.intervention.monitor.ITNDataViewDTO:dto, [Ldss.vector.solutions.intervention.monitor.ITNNetDTO;:nets, [Ldss.vector.solutions.intervention.monitor.ITNTargetGroupDTO;:targetGroups, [Ldss.vector.solutions.intervention.monitor.ITNServiceDTO;:services", post=true)
  public void update(dss.vector.solutions.intervention.monitor.ITNDataViewDTO dto, dss.vector.solutions.intervention.monitor.ITNNetDTO[] nets, dss.vector.solutions.intervention.monitor.ITNTargetGroupDTO[] targetGroups, dss.vector.solutions.intervention.monitor.ITNServiceDTO[] services) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.intervention.monitor.ITNDataController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.intervention.monitor.ITNDataController.update");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="dss.vector.solutions.intervention.monitor.ITNDataViewDTO:dto, [Ldss.vector.solutions.intervention.monitor.ITNNetDTO;:nets, [Ldss.vector.solutions.intervention.monitor.ITNTargetGroupDTO;:targetGroups, [Ldss.vector.solutions.intervention.monitor.ITNServiceDTO;:services", post=true)
  public void failUpdate(dss.vector.solutions.intervention.monitor.ITNDataViewDTO dto, dss.vector.solutions.intervention.monitor.ITNNetDTO[] nets, dss.vector.solutions.intervention.monitor.ITNTargetGroupDTO[] targetGroups, dss.vector.solutions.intervention.monitor.ITNServiceDTO[] services) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.intervention.monitor.ITNDataController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.intervention.monitor.ITNDataController.failUpdate");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="java.lang.String:id", post=false)
  public void view(java.lang.String id) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.intervention.monitor.ITNDataController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.intervention.monitor.ITNDataController.view");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="java.lang.String:id", post=false)
  public void failView(java.lang.String id) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.intervention.monitor.ITNDataController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.intervention.monitor.ITNDataController.failView");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="", post=false)
  public void viewAll() throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.intervention.monitor.ITNDataController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.intervention.monitor.ITNDataController.viewAll");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="", post=false)
  public void failViewAll() throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.intervention.monitor.ITNDataController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.intervention.monitor.ITNDataController.failViewAll");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="java.lang.String:sortAttribute, java.lang.Boolean:isAscending, java.lang.Integer:pageSize, java.lang.Integer:pageNumber", post=false)
  public void viewPage(java.lang.String sortAttribute, java.lang.Boolean isAscending, java.lang.Integer pageSize, java.lang.Integer pageNumber) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.intervention.monitor.ITNDataController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.intervention.monitor.ITNDataController.viewPage");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="java.lang.String:sortAttribute, java.lang.String:isAscending, java.lang.String:pageSize, java.lang.String:pageNumber", post=false)
  public void failViewPage(java.lang.String sortAttribute, java.lang.String isAscending, java.lang.String pageSize, java.lang.String pageNumber) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.intervention.monitor.ITNDataController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.intervention.monitor.ITNDataController.failViewPage");
  }
  
}
