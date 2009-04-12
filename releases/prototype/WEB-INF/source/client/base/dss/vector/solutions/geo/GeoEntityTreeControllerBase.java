package dss.vector.solutions.geo;

public class GeoEntityTreeControllerBase implements com.terraframe.mojo.generation.loader.Reloadable
{
  public static final String CLASS = "dss.vector.solutions.geo.GeoEntityTreeController";
  protected javax.servlet.http.HttpServletRequest req;
  protected javax.servlet.http.HttpServletResponse resp;
  protected java.lang.Boolean isAsynchronous;
  protected java.lang.String dir;
  protected java.lang.String layout;
  
  private static final long serialVersionUID = 1239517536620L;
  
  public GeoEntityTreeControllerBase(javax.servlet.http.HttpServletRequest req, javax.servlet.http.HttpServletResponse resp, java.lang.Boolean isAsynchronous)
  {
    this(req, resp, isAsynchronous, "","");
  }
  
  public GeoEntityTreeControllerBase(javax.servlet.http.HttpServletRequest req, javax.servlet.http.HttpServletResponse resp, java.lang.Boolean isAsynchronous, java.lang.String dir, java.lang.String layout)
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
  
  @com.terraframe.mojo.controller.ActionParameters(parameters="java.lang.String:rootGeoEntityId", post=false)
  public void displayTree(java.lang.String rootGeoEntityId) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.geo.GeoEntityTreeController.java";
    throw new com.terraframe.mojo.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.geo.GeoEntityTreeController.displayTree");
  }
  
  @com.terraframe.mojo.controller.ActionParameters(parameters="java.lang.String:rootGeoEntityId", post=false)
  public void failDisplayTree(java.lang.String rootGeoEntityId) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.geo.GeoEntityTreeController.java";
    throw new com.terraframe.mojo.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.geo.GeoEntityTreeController.failDisplayTree");
  }
  
  @com.terraframe.mojo.controller.ActionParameters(parameters="java.lang.String:rootGeoEntityId", post=false)
  public void displaySingleSelectSearch(java.lang.String rootGeoEntityId) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.geo.GeoEntityTreeController.java";
    throw new com.terraframe.mojo.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.geo.GeoEntityTreeController.displaySingleSelectSearch");
  }
  
  @com.terraframe.mojo.controller.ActionParameters(parameters="java.lang.String:rootGeoEntityId", post=false)
  public void failDisplaySingleSelectSearch(java.lang.String rootGeoEntityId) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.geo.GeoEntityTreeController.java";
    throw new com.terraframe.mojo.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.geo.GeoEntityTreeController.failDisplaySingleSelectSearch");
  }
  
  @com.terraframe.mojo.controller.ActionParameters(parameters="java.lang.String:rootGeoEntityId", post=false)
  public void displayMultipleSelectSearch(java.lang.String rootGeoEntityId) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.geo.GeoEntityTreeController.java";
    throw new com.terraframe.mojo.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.geo.GeoEntityTreeController.displayMultipleSelectSearch");
  }
  
  @com.terraframe.mojo.controller.ActionParameters(parameters="java.lang.String:rootGeoEntityId", post=false)
  public void failDisplayMultipleSelectSearch(java.lang.String rootGeoEntityId) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.geo.GeoEntityTreeController.java";
    throw new com.terraframe.mojo.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.geo.GeoEntityTreeController.failDisplayMultipleSelectSearch");
  }
  
}
