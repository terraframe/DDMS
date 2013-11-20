package dss.vector.solutions.general;

@com.runwaysdk.business.ClassSignature(hash = 1579422890)
public class ReportModuleControllerBase implements com.runwaysdk.generation.loader.Reloadable
{
  public static final String CLASS = "dss.vector.solutions.general.ReportModuleController";
  protected javax.servlet.http.HttpServletRequest req;
  protected javax.servlet.http.HttpServletResponse resp;
  protected java.lang.Boolean isAsynchronous;
  protected java.lang.String dir;
  protected java.lang.String layout;
  
  public ReportModuleControllerBase(javax.servlet.http.HttpServletRequest req, javax.servlet.http.HttpServletResponse resp, java.lang.Boolean isAsynchronous)
  {
    this(req, resp, isAsynchronous, "","");
  }
  
  public ReportModuleControllerBase(javax.servlet.http.HttpServletRequest req, javax.servlet.http.HttpServletResponse resp, java.lang.Boolean isAsynchronous, java.lang.String dir, java.lang.String layout)
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
  
  @com.runwaysdk.controller.ActionParameters(parameters="dss.vector.solutions.general.ReportModuleViewDTO:view", post=true)
  public void buildDatabaseViews(dss.vector.solutions.general.ReportModuleViewDTO view) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.general.ReportModuleController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.general.ReportModuleController.buildDatabaseViews");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="dss.vector.solutions.general.ReportModuleViewDTO:view", post=true)
  public void failBuildDatabaseViews(dss.vector.solutions.general.ReportModuleViewDTO view) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.general.ReportModuleController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.general.ReportModuleController.failBuildDatabaseViews");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="", post=false)
  public void form() throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.general.ReportModuleController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.general.ReportModuleController.form");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="", post=false)
  public void failForm() throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.general.ReportModuleController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.general.ReportModuleController.failForm");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="java.lang.String:requestId", post=false)
  public void getProgress(java.lang.String requestId) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.general.ReportModuleController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.general.ReportModuleController.getProgress");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="java.lang.String:requestId", post=false)
  public void failGetProgress(java.lang.String requestId) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.general.ReportModuleController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.general.ReportModuleController.failGetProgress");
  }
  
}
