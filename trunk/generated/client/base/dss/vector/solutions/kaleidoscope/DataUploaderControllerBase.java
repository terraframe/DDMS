package dss.vector.solutions.kaleidoscope;

@com.runwaysdk.business.ClassSignature(hash = -1620833631)
public class DataUploaderControllerBase implements com.runwaysdk.generation.loader.Reloadable
{
  public static final String CLASS = "dss.vector.solutions.kaleidoscope.DataUploaderController";
  protected javax.servlet.http.HttpServletRequest req;
  protected javax.servlet.http.HttpServletResponse resp;
  protected java.lang.Boolean isAsynchronous;
  protected java.lang.String dir;
  protected java.lang.String layout;
  
  public DataUploaderControllerBase(javax.servlet.http.HttpServletRequest req, javax.servlet.http.HttpServletResponse resp, java.lang.Boolean isAsynchronous)
  {
    this(req, resp, isAsynchronous, "","");
  }
  
  public DataUploaderControllerBase(javax.servlet.http.HttpServletRequest req, javax.servlet.http.HttpServletResponse resp, java.lang.Boolean isAsynchronous, java.lang.String dir, java.lang.String layout)
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
  
  @com.runwaysdk.controller.ActionParameters(parameters="java.lang.String:configuration", post=true)
  public void cancelImport(java.lang.String configuration) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.kaleidoscope.DataUploaderController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.kaleidoscope.DataUploaderController.cancelImport");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="java.lang.String:configuration", post=true)
  public void failCancelImport(java.lang.String configuration) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.kaleidoscope.DataUploaderController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.kaleidoscope.DataUploaderController.failCancelImport");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="java.lang.String:parentId, java.lang.String:universalId, java.lang.String:label", post=true)
  public void createGeoEntity(java.lang.String parentId, java.lang.String universalId, java.lang.String label) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.kaleidoscope.DataUploaderController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.kaleidoscope.DataUploaderController.createGeoEntity");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="java.lang.String:parentId, java.lang.String:universalId, java.lang.String:label", post=true)
  public void failCreateGeoEntity(java.lang.String parentId, java.lang.String universalId, java.lang.String label) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.kaleidoscope.DataUploaderController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.kaleidoscope.DataUploaderController.failCreateGeoEntity");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="java.lang.String:entityId, java.lang.String:label", post=true)
  public void createGeoEntitySynonym(java.lang.String entityId, java.lang.String label) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.kaleidoscope.DataUploaderController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.kaleidoscope.DataUploaderController.createGeoEntitySynonym");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="java.lang.String:entityId, java.lang.String:label", post=true)
  public void failCreateGeoEntitySynonym(java.lang.String entityId, java.lang.String label) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.kaleidoscope.DataUploaderController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.kaleidoscope.DataUploaderController.failCreateGeoEntitySynonym");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="com.runwaysdk.controller.MultipartFileParameter:file", post=true)
  public void getAttributeInformation(com.runwaysdk.controller.MultipartFileParameter file) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.kaleidoscope.DataUploaderController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.kaleidoscope.DataUploaderController.getAttributeInformation");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="com.runwaysdk.controller.MultipartFileParameter:file", post=true)
  public void failGetAttributeInformation(com.runwaysdk.controller.MultipartFileParameter file) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.kaleidoscope.DataUploaderController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.kaleidoscope.DataUploaderController.failGetAttributeInformation");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="java.lang.String:id, java.lang.String:sheetName", post=true)
  public void getSavedConfiguration(java.lang.String id, java.lang.String sheetName) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.kaleidoscope.DataUploaderController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.kaleidoscope.DataUploaderController.getSavedConfiguration");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="java.lang.String:id, java.lang.String:sheetName", post=true)
  public void failGetSavedConfiguration(java.lang.String id, java.lang.String sheetName) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.kaleidoscope.DataUploaderController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.kaleidoscope.DataUploaderController.failGetSavedConfiguration");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="java.lang.String:configuration", post=true)
  public void importData(java.lang.String configuration) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.kaleidoscope.DataUploaderController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.kaleidoscope.DataUploaderController.importData");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="java.lang.String:configuration", post=true)
  public void failImportData(java.lang.String configuration) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.kaleidoscope.DataUploaderController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.kaleidoscope.DataUploaderController.failImportData");
  }
  
}
