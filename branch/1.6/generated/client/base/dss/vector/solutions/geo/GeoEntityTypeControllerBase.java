package dss.vector.solutions.geo;

@com.runwaysdk.business.ClassSignature(hash = -1973915890)
public class GeoEntityTypeControllerBase implements com.runwaysdk.generation.loader.Reloadable
{
  public static final String CLASS = "dss.vector.solutions.geo.GeoEntityTypeController";
  protected javax.servlet.http.HttpServletRequest req;
  protected javax.servlet.http.HttpServletResponse resp;
  protected java.lang.Boolean isAsynchronous;
  protected java.lang.String dir;
  protected java.lang.String layout;
  
  public GeoEntityTypeControllerBase(javax.servlet.http.HttpServletRequest req, javax.servlet.http.HttpServletResponse resp, java.lang.Boolean isAsynchronous)
  {
    this(req, resp, isAsynchronous, "","");
  }
  
  public GeoEntityTypeControllerBase(javax.servlet.http.HttpServletRequest req, javax.servlet.http.HttpServletResponse resp, java.lang.Boolean isAsynchronous, java.lang.String dir, java.lang.String layout)
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
  
  @com.runwaysdk.controller.ActionParameters(parameters="", post=true)
  public void cancelCreateDefinition() throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.geo.GeoEntityTypeController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.geo.GeoEntityTypeController.cancelCreateDefinition");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="", post=true)
  public void failCancelCreateDefinition() throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.geo.GeoEntityTypeController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.geo.GeoEntityTypeController.failCancelCreateDefinition");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="java.lang.String:geoHierarchyId", post=true)
  public void cancelUpdateDefinition(java.lang.String geoHierarchyId) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.geo.GeoEntityTypeController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.geo.GeoEntityTypeController.cancelUpdateDefinition");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="java.lang.String:geoHierarchyId", post=true)
  public void failCancelUpdateDefinition(java.lang.String geoHierarchyId) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.geo.GeoEntityTypeController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.geo.GeoEntityTypeController.failCancelUpdateDefinition");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="dss.vector.solutions.geo.GeoEntityDefinitionDTO:definition", post=true)
  public void createDefinition(dss.vector.solutions.geo.GeoEntityDefinitionDTO definition) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.geo.GeoEntityTypeController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.geo.GeoEntityTypeController.createDefinition");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="dss.vector.solutions.geo.GeoEntityDefinitionDTO:definition", post=true)
  public void failCreateDefinition(dss.vector.solutions.geo.GeoEntityDefinitionDTO definition) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.geo.GeoEntityTypeController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.geo.GeoEntityTypeController.failCreateDefinition");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="java.lang.String:geoHierarchyId", post=true)
  public void editDefinition(java.lang.String geoHierarchyId) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.geo.GeoEntityTypeController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.geo.GeoEntityTypeController.editDefinition");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="java.lang.String:geoHierarchyId", post=true)
  public void failEditDefinition(java.lang.String geoHierarchyId) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.geo.GeoEntityTypeController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.geo.GeoEntityTypeController.failEditDefinition");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="java.lang.String:hierarchyId, java.lang.Boolean:includeGeoData", post=false)
  public void export(java.lang.String hierarchyId, java.lang.Boolean includeGeoData) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.geo.GeoEntityTypeController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.geo.GeoEntityTypeController.export");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="java.lang.String:hierarchyId, java.lang.String:includeGeoData", post=false)
  public void failExport(java.lang.String hierarchyId, java.lang.String includeGeoData) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.geo.GeoEntityTypeController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.geo.GeoEntityTypeController.failExport");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="java.lang.String:parentGeoHierarchyId", post=false)
  public void newDefinition(java.lang.String parentGeoHierarchyId) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.geo.GeoEntityTypeController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.geo.GeoEntityTypeController.newDefinition");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="java.lang.String:parentGeoHierarchyId", post=false)
  public void failNewDefinition(java.lang.String parentGeoHierarchyId) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.geo.GeoEntityTypeController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.geo.GeoEntityTypeController.failNewDefinition");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="dss.vector.solutions.geo.GeoHierarchyViewDTO:view", post=true)
  public void updateDefinition(dss.vector.solutions.geo.GeoHierarchyViewDTO view) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.geo.GeoEntityTypeController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.geo.GeoEntityTypeController.updateDefinition");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="dss.vector.solutions.geo.GeoHierarchyViewDTO:view", post=true)
  public void failUpdateDefinition(dss.vector.solutions.geo.GeoHierarchyViewDTO view) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.geo.GeoEntityTypeController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.geo.GeoEntityTypeController.failUpdateDefinition");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="java.lang.String:geoHierarchyId", post=false)
  public void viewDefinition(java.lang.String geoHierarchyId) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.geo.GeoEntityTypeController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.geo.GeoEntityTypeController.viewDefinition");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="java.lang.String:geoHierarchyId", post=false)
  public void failViewDefinition(java.lang.String geoHierarchyId) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.geo.GeoEntityTypeController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.geo.GeoEntityTypeController.failViewDefinition");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="java.lang.String:rootGeoHierarchyId", post=false)
  public void viewHierarchyTree(java.lang.String rootGeoHierarchyId) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.geo.GeoEntityTypeController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.geo.GeoEntityTypeController.viewHierarchyTree");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="java.lang.String:rootGeoHierarchyId", post=false)
  public void failViewHierarchyTree(java.lang.String rootGeoHierarchyId) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.geo.GeoEntityTypeController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.geo.GeoEntityTypeController.failViewHierarchyTree");
  }
  
}
