package dss.vector.solutions.util;

@com.runwaysdk.business.ClassSignature(hash = -1189661302)
public class LocalizationControllerBase implements com.runwaysdk.generation.loader.Reloadable
{
  public static final String CLASS = "dss.vector.solutions.util.LocalizationController";
  protected javax.servlet.http.HttpServletRequest req;
  protected javax.servlet.http.HttpServletResponse resp;
  protected java.lang.Boolean isAsynchronous;
  protected java.lang.String dir;
  protected java.lang.String layout;
  
  public LocalizationControllerBase(javax.servlet.http.HttpServletRequest req, javax.servlet.http.HttpServletResponse resp, java.lang.Boolean isAsynchronous)
  {
    this(req, resp, isAsynchronous, "","");
  }
  
  public LocalizationControllerBase(javax.servlet.http.HttpServletRequest req, javax.servlet.http.HttpServletResponse resp, java.lang.Boolean isAsynchronous, java.lang.String dir, java.lang.String layout)
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
  
  @com.runwaysdk.controller.ActionParameters(parameters="[Ljava.lang.String;:locales", post=true)
  public void exportFile(java.lang.String[] locales) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.util.LocalizationController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.util.LocalizationController.exportFile");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="[Ljava.lang.String;:locales", post=true)
  public void failExportFile(java.lang.String[] locales) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.util.LocalizationController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.util.LocalizationController.failExportFile");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="com.runwaysdk.controller.MultipartFileParameter:upfile", post=true)
  public void importFile(com.runwaysdk.controller.MultipartFileParameter upfile) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.util.LocalizationController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.util.LocalizationController.importFile");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="com.runwaysdk.controller.MultipartFileParameter:upfile", post=true)
  public void failImportFile(com.runwaysdk.controller.MultipartFileParameter upfile) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.util.LocalizationController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.util.LocalizationController.failImportFile");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="java.lang.String:language, java.lang.String:country, java.lang.String:variant", post=true)
  public void installLocale(java.lang.String language, java.lang.String country, java.lang.String variant) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.util.LocalizationController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.util.LocalizationController.installLocale");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="java.lang.String:language, java.lang.String:country, java.lang.String:variant", post=true)
  public void failInstallLocale(java.lang.String language, java.lang.String country, java.lang.String variant) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.util.LocalizationController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.util.LocalizationController.failInstallLocale");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="", post=false)
  public void newLocale() throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.util.LocalizationController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.util.LocalizationController.newLocale");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="", post=false)
  public void failNewLocale() throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.util.LocalizationController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.util.LocalizationController.failNewLocale");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="", post=false)
  public void selectLocales() throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.util.LocalizationController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.util.LocalizationController.selectLocales");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="", post=false)
  public void failSelectLocales() throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.util.LocalizationController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.util.LocalizationController.failSelectLocales");
  }
  
}
