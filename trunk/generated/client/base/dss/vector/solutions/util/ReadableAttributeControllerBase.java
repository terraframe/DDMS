package dss.vector.solutions.util;

@com.terraframe.mojo.business.ClassSignature(hash = 737396931)
public class ReadableAttributeControllerBase implements com.terraframe.mojo.generation.loader.Reloadable
{
  public static final String CLASS = "dss.vector.solutions.util.ReadableAttributeController";
  protected javax.servlet.http.HttpServletRequest req;
  protected javax.servlet.http.HttpServletResponse resp;
  protected java.lang.Boolean isAsynchronous;
  protected java.lang.String dir;
  protected java.lang.String layout;
  
  private static final long serialVersionUID = 737396931;
  
  public ReadableAttributeControllerBase(javax.servlet.http.HttpServletRequest req, javax.servlet.http.HttpServletResponse resp, java.lang.Boolean isAsynchronous)
  {
    this(req, resp, isAsynchronous, "","");
  }
  
  public ReadableAttributeControllerBase(javax.servlet.http.HttpServletRequest req, javax.servlet.http.HttpServletResponse resp, java.lang.Boolean isAsynchronous, java.lang.String dir, java.lang.String layout)
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
  
  @com.terraframe.mojo.controller.ActionParameters(parameters="java.lang.String:universal, java.lang.String:actor, [Ldss.vector.solutions.util.ReadableAttributeViewDTO;:attributeViews", post=true)
  public void setAttributes(java.lang.String universal, java.lang.String actor, dss.vector.solutions.util.ReadableAttributeViewDTO[] attributeViews) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.util.ReadableAttributeController.java";
    throw new com.terraframe.mojo.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.util.ReadableAttributeController.setAttributes");
  }
  
  @com.terraframe.mojo.controller.ActionParameters(parameters="java.lang.String:universal, java.lang.String:actor, [Ldss.vector.solutions.util.ReadableAttributeViewDTO;:attributeViews", post=true)
  public void failSetAttributes(java.lang.String universal, java.lang.String actor, dss.vector.solutions.util.ReadableAttributeViewDTO[] attributeViews) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.util.ReadableAttributeController.java";
    throw new com.terraframe.mojo.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.util.ReadableAttributeController.failSetAttributes");
  }
  
  @com.terraframe.mojo.controller.ActionParameters(parameters="", post=false)
  public void getActor() throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.util.ReadableAttributeController.java";
    throw new com.terraframe.mojo.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.util.ReadableAttributeController.getActor");
  }
  
  @com.terraframe.mojo.controller.ActionParameters(parameters="", post=false)
  public void failGetActor() throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.util.ReadableAttributeController.java";
    throw new com.terraframe.mojo.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.util.ReadableAttributeController.failGetActor");
  }
  
  @com.terraframe.mojo.controller.ActionParameters(parameters="java.lang.String:actor", post=false)
  public void getUniversal(java.lang.String actor) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.util.ReadableAttributeController.java";
    throw new com.terraframe.mojo.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.util.ReadableAttributeController.getUniversal");
  }
  
  @com.terraframe.mojo.controller.ActionParameters(parameters="java.lang.String:actor", post=false)
  public void failGetUniversal(java.lang.String actor) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.util.ReadableAttributeController.java";
    throw new com.terraframe.mojo.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.util.ReadableAttributeController.failGetUniversal");
  }
  
  @com.terraframe.mojo.controller.ActionParameters(parameters="java.lang.String:universal, java.lang.String:actor", post=false)
  public void getAttributes(java.lang.String universal, java.lang.String actor) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.util.ReadableAttributeController.java";
    throw new com.terraframe.mojo.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.util.ReadableAttributeController.getAttributes");
  }
  
  @com.terraframe.mojo.controller.ActionParameters(parameters="java.lang.String:universal, java.lang.String:actor", post=false)
  public void failGetAttributes(java.lang.String universal, java.lang.String actor) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.util.ReadableAttributeController.java";
    throw new com.terraframe.mojo.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.util.ReadableAttributeController.failGetAttributes");
  }
  
}
