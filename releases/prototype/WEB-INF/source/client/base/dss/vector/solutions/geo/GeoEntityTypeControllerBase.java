package dss.vector.solutions.geo;

public class GeoEntityTypeControllerBase implements com.terraframe.mojo.generation.loader.Reloadable
{
  public static final String CLASS = "dss.vector.solutions.geo.GeoEntityTypeController";
  protected javax.servlet.http.HttpServletRequest req;
  protected javax.servlet.http.HttpServletResponse resp;
  protected java.lang.Boolean isAsynchronous;
  protected java.lang.String dir;
  protected java.lang.String layout;
  
  private static final long serialVersionUID = 1237314866791L;
  
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
    if(this.isAsynchronous())
    {
      req.getRequestDispatcher(dir+jsp).forward(req, resp);
    }
    else
    {
      req.setAttribute("jsp", jsp);
      req.getRequestDispatcher(layout).forward(req, resp);
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
  
  public com.terraframe.mojo.constants.ClientRequestIF getClientRequest()
  {
    return (com.terraframe.mojo.constants.ClientRequestIF) req.getAttribute(com.terraframe.mojo.constants.ClientConstants.CLIENTREQUEST);
  }
  
  public com.terraframe.mojo.ClientSession getClientSession()
  {
    return (com.terraframe.mojo.ClientSession) req.getSession().getAttribute(com.terraframe.mojo.constants.ClientConstants.CLIENTSESSION);
  }
  
  @com.terraframe.mojo.controller.ActionParameters(parameters="java.lang.String:parentGeoHierarchyId", post=false)
  public void newDefinition(java.lang.String parentGeoHierarchyId) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.geo.GeoEntityTypeController.java";
    throw new com.terraframe.mojo.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.geo.GeoEntityTypeController.newDefinition");
  }
  
  @com.terraframe.mojo.controller.ActionParameters(parameters="java.lang.String:parentGeoHierarchyId", post=false)
  public void failNewDefinition(java.lang.String parentGeoHierarchyId) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.geo.GeoEntityTypeController.java";
    throw new com.terraframe.mojo.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.geo.GeoEntityTypeController.failNewDefinition");
  }
  
  @com.terraframe.mojo.controller.ActionParameters(parameters="dss.vector.solutions.geo.GeoEntityDefinitionDTO:definition", post=true)
  public void createDefinition(dss.vector.solutions.geo.GeoEntityDefinitionDTO definition) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.geo.GeoEntityTypeController.java";
    throw new com.terraframe.mojo.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.geo.GeoEntityTypeController.createDefinition");
  }
  
  @com.terraframe.mojo.controller.ActionParameters(parameters="dss.vector.solutions.geo.GeoEntityDefinitionDTO:definition", post=true)
  public void failCreateDefinition(dss.vector.solutions.geo.GeoEntityDefinitionDTO definition) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.geo.GeoEntityTypeController.java";
    throw new com.terraframe.mojo.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.geo.GeoEntityTypeController.failCreateDefinition");
  }
  
  @com.terraframe.mojo.controller.ActionParameters(parameters="", post=true)
  public void cancelCreateDefinition() throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.geo.GeoEntityTypeController.java";
    throw new com.terraframe.mojo.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.geo.GeoEntityTypeController.cancelCreateDefinition");
  }
  
  @com.terraframe.mojo.controller.ActionParameters(parameters="", post=true)
  public void failCancelCreateDefinition() throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.geo.GeoEntityTypeController.java";
    throw new com.terraframe.mojo.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.geo.GeoEntityTypeController.failCancelCreateDefinition");
  }
  
  @com.terraframe.mojo.controller.ActionParameters(parameters="", post=false)
  public void viewAllDefinitions() throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.geo.GeoEntityTypeController.java";
    throw new com.terraframe.mojo.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.geo.GeoEntityTypeController.viewAllDefinitions");
  }
  
  @com.terraframe.mojo.controller.ActionParameters(parameters="", post=false)
  public void failViewAllDefinitions() throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.geo.GeoEntityTypeController.java";
    throw new com.terraframe.mojo.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.geo.GeoEntityTypeController.failViewAllDefinitions");
  }
  
  @com.terraframe.mojo.controller.ActionParameters(parameters="java.lang.String:sortAttribute, java.lang.Boolean:isAscending, java.lang.Integer:pageSize, java.lang.Integer:pageNumber", post=false)
  public void viewPageDefinitions(java.lang.String sortAttribute, java.lang.Boolean isAscending, java.lang.Integer pageSize, java.lang.Integer pageNumber) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.geo.GeoEntityTypeController.java";
    throw new com.terraframe.mojo.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.geo.GeoEntityTypeController.viewPageDefinitions");
  }
  
  @com.terraframe.mojo.controller.ActionParameters(parameters="java.lang.String:sortAttribute, java.lang.String:isAscending, java.lang.String:pageSize, java.lang.String:pageNumber", post=false)
  public void failViewPageDefinitions(java.lang.String sortAttribute, java.lang.String isAscending, java.lang.String pageSize, java.lang.String pageNumber) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.geo.GeoEntityTypeController.java";
    throw new com.terraframe.mojo.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.geo.GeoEntityTypeController.failViewPageDefinitions");
  }
  
  @com.terraframe.mojo.controller.ActionParameters(parameters="java.lang.String:rootGeoHierarchyId", post=false)
  public void viewHierarchyTree(java.lang.String rootGeoHierarchyId) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.geo.GeoEntityTypeController.java";
    throw new com.terraframe.mojo.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.geo.GeoEntityTypeController.viewHierarchyTree");
  }
  
  @com.terraframe.mojo.controller.ActionParameters(parameters="java.lang.String:rootGeoHierarchyId", post=false)
  public void failViewHierarchyTree(java.lang.String rootGeoHierarchyId) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.geo.GeoEntityTypeController.java";
    throw new com.terraframe.mojo.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.geo.GeoEntityTypeController.failViewHierarchyTree");
  }
  
}
