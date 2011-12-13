package dss.vector.solutions.util;

@com.runwaysdk.business.ClassSignature(hash = -823659548)
public class ReadableAttributeControllerBase implements com.runwaysdk.generation.loader.Reloadable
{
  public static final String CLASS = "dss.vector.solutions.util.ReadableAttributeController";
  protected javax.servlet.http.HttpServletRequest req;
  protected javax.servlet.http.HttpServletResponse resp;
  protected java.lang.Boolean isAsynchronous;
  protected java.lang.String dir;
  protected java.lang.String layout;
  
  private static final long serialVersionUID = -823659548;
  
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
  
  @com.runwaysdk.controller.ActionParameters(parameters="", post=false)
  public void getActor() throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.util.ReadableAttributeController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.util.ReadableAttributeController.getActor");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="", post=false)
  public void failGetActor() throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.util.ReadableAttributeController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.util.ReadableAttributeController.failGetActor");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="java.lang.String:universal, java.lang.String:actor", post=false)
  public void getAttributes(java.lang.String universal, java.lang.String actor) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.util.ReadableAttributeController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.util.ReadableAttributeController.getAttributes");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="java.lang.String:universal, java.lang.String:actor", post=false)
  public void failGetAttributes(java.lang.String universal, java.lang.String actor) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.util.ReadableAttributeController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.util.ReadableAttributeController.failGetAttributes");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="java.lang.String:actor", post=false)
  public void getFormAttributes(java.lang.String actor) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.util.ReadableAttributeController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.util.ReadableAttributeController.getFormAttributes");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="java.lang.String:actor", post=false)
  public void failGetFormAttributes(java.lang.String actor) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.util.ReadableAttributeController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.util.ReadableAttributeController.failGetFormAttributes");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="java.lang.String:actor", post=false)
  public void getUniversal(java.lang.String actor) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.util.ReadableAttributeController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.util.ReadableAttributeController.getUniversal");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="java.lang.String:actor", post=false)
  public void failGetUniversal(java.lang.String actor) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.util.ReadableAttributeController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.util.ReadableAttributeController.failGetUniversal");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="java.lang.String:universal, java.lang.String:actor, [Ldss.vector.solutions.util.ReadableAttributeViewDTO;:attributeViews", post=true)
  public void setAttributes(java.lang.String universal, java.lang.String actor, dss.vector.solutions.util.ReadableAttributeViewDTO[] attributeViews) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.util.ReadableAttributeController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.util.ReadableAttributeController.setAttributes");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="java.lang.String:universal, java.lang.String:actor, [Ldss.vector.solutions.util.ReadableAttributeViewDTO;:attributeViews", post=true)
  public void failSetAttributes(java.lang.String universal, java.lang.String actor, dss.vector.solutions.util.ReadableAttributeViewDTO[] attributeViews) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.util.ReadableAttributeController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.util.ReadableAttributeController.failSetAttributes");
  }
  
}
