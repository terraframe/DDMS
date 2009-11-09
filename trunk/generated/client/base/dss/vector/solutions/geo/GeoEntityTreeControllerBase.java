package dss.vector.solutions.geo;

@com.terraframe.mojo.business.ClassSignature(hash = 1391794726)
public class GeoEntityTreeControllerBase implements com.terraframe.mojo.generation.loader.Reloadable
{
  public static final String CLASS = "dss.vector.solutions.geo.GeoEntityTreeController";
  protected javax.servlet.http.HttpServletRequest req;
  protected javax.servlet.http.HttpServletResponse resp;
  protected java.lang.Boolean isAsynchronous;
  protected java.lang.String dir;
  protected java.lang.String layout;
  
  private static final long serialVersionUID = 1391794726;
  
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
    if(!resp.isCommitted())
    {
      if(this.isAsynchronous())
      {
        req.getRequestDispatcher(dir+jsp).forward(req, resp);
      }
      else
      {
        req.setAttribute("jsp", dir+jsp);
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
  
  public com.terraframe.mojo.constants.ClientRequestIF getClientRequest()
  {
    return (com.terraframe.mojo.constants.ClientRequestIF) req.getAttribute(com.terraframe.mojo.constants.ClientConstants.CLIENTREQUEST);
  }
  
  public com.terraframe.mojo.ClientSession getClientSession()
  {
    return (com.terraframe.mojo.ClientSession) req.getSession().getAttribute(com.terraframe.mojo.constants.ClientConstants.CLIENTSESSION);
  }
  
  @com.terraframe.mojo.controller.ActionParameters(parameters="java.lang.String:rootGeoEntityId, java.lang.Boolean:political, java.lang.Boolean:sprayZoneAllowed, [Ljava.lang.String;:extraUniversals", post=true)
  public void displayMultipleSelectSearch(java.lang.String rootGeoEntityId, java.lang.Boolean political, java.lang.Boolean sprayZoneAllowed, java.lang.String[] extraUniversals) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.geo.GeoEntityTreeController.java";
    throw new com.terraframe.mojo.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.geo.GeoEntityTreeController.displayMultipleSelectSearch");
  }
  
  @com.terraframe.mojo.controller.ActionParameters(parameters="java.lang.String:rootGeoEntityId, java.lang.String:political, java.lang.String:sprayZoneAllowed, [Ljava.lang.String;:extraUniversals", post=true)
  public void failDisplayMultipleSelectSearch(java.lang.String rootGeoEntityId, java.lang.String political, java.lang.String sprayZoneAllowed, java.lang.String[] extraUniversals) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.geo.GeoEntityTreeController.java";
    throw new com.terraframe.mojo.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.geo.GeoEntityTreeController.failDisplayMultipleSelectSearch");
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
  
  @com.terraframe.mojo.controller.ActionParameters(parameters="java.lang.String:rootGeoEntityId, java.lang.Boolean:political, java.lang.Boolean:sprayZoneAllowed, [Ljava.lang.String;:extraUniversals", post=true)
  public void displaySingleSelectSearch(java.lang.String rootGeoEntityId, java.lang.Boolean political, java.lang.Boolean sprayZoneAllowed, java.lang.String[] extraUniversals) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.geo.GeoEntityTreeController.java";
    throw new com.terraframe.mojo.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.geo.GeoEntityTreeController.displaySingleSelectSearch");
  }
  
  @com.terraframe.mojo.controller.ActionParameters(parameters="java.lang.String:rootGeoEntityId, java.lang.String:political, java.lang.String:sprayZoneAllowed, [Ljava.lang.String;:extraUniversals", post=true)
  public void failDisplaySingleSelectSearch(java.lang.String rootGeoEntityId, java.lang.String political, java.lang.String sprayZoneAllowed, java.lang.String[] extraUniversals) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.geo.GeoEntityTreeController.java";
    throw new com.terraframe.mojo.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.geo.GeoEntityTreeController.failDisplaySingleSelectSearch");
  }
  
}
