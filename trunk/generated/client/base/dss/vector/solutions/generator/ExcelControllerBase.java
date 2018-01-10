package dss.vector.solutions.generator;

@com.runwaysdk.business.ClassSignature(hash = 864394283)
public class ExcelControllerBase implements com.runwaysdk.generation.loader.Reloadable
{
  public static final String CLASS = "dss.vector.solutions.generator.ExcelController";
  protected javax.servlet.http.HttpServletRequest req;
  protected javax.servlet.http.HttpServletResponse resp;
  protected java.lang.Boolean isAsynchronous;
  protected java.lang.String dir;
  protected java.lang.String layout;
  
  public ExcelControllerBase(javax.servlet.http.HttpServletRequest req, javax.servlet.http.HttpServletResponse resp, java.lang.Boolean isAsynchronous)
  {
    this(req, resp, isAsynchronous, "","");
  }
  
  public ExcelControllerBase(javax.servlet.http.HttpServletRequest req, javax.servlet.http.HttpServletResponse resp, java.lang.Boolean isAsynchronous, java.lang.String dir, java.lang.String layout)
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
  public void clearHistory() throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.generator.ExcelController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.generator.ExcelController.clearHistory");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="", post=false)
  public void failClearHistory() throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.generator.ExcelController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.generator.ExcelController.failClearHistory");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="java.lang.String:historyId", post=false)
  public void downloadErrorSpreadsheet(java.lang.String historyId) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.generator.ExcelController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.generator.ExcelController.downloadErrorSpreadsheet");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="java.lang.String:historyId", post=false)
  public void failDownloadErrorSpreadsheet(java.lang.String historyId) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.generator.ExcelController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.generator.ExcelController.failDownloadErrorSpreadsheet");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="java.lang.String:excelType", post=false)
  public void excelExport(java.lang.String excelType) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.generator.ExcelController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.generator.ExcelController.excelExport");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="java.lang.String:excelType", post=false)
  public void failExcelExport(java.lang.String excelType) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.generator.ExcelController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.generator.ExcelController.failExcelExport");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="java.lang.String:excelType, com.runwaysdk.controller.MultipartFileParameter:upfile", post=true)
  public void excelImport(java.lang.String excelType, com.runwaysdk.controller.MultipartFileParameter upfile) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.generator.ExcelController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.generator.ExcelController.excelImport");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="java.lang.String:excelType, com.runwaysdk.controller.MultipartFileParameter:upfile", post=true)
  public void failExcelImport(java.lang.String excelType, com.runwaysdk.controller.MultipartFileParameter upfile) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.generator.ExcelController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.generator.ExcelController.failExcelImport");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="", post=false)
  public void getAllHistory() throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.generator.ExcelController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.generator.ExcelController.getAllHistory");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="", post=false)
  public void failGetAllHistory() throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.generator.ExcelController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.generator.ExcelController.failGetAllHistory");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="java.lang.String:excelType", post=false)
  public void importType(java.lang.String excelType) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.generator.ExcelController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.generator.ExcelController.importType");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="java.lang.String:excelType", post=false)
  public void failImportType(java.lang.String excelType) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.generator.ExcelController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.generator.ExcelController.failImportType");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="", post=false)
  public void surveyExcelExport() throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.generator.ExcelController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.generator.ExcelController.surveyExcelExport");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="", post=false)
  public void failSurveyExcelExport() throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.generator.ExcelController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.generator.ExcelController.failSurveyExcelExport");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="java.lang.String:type, com.runwaysdk.controller.MultipartFileParameter:upfile", post=true)
  public void surveyExcelImport(java.lang.String type, com.runwaysdk.controller.MultipartFileParameter upfile) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.generator.ExcelController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.generator.ExcelController.surveyExcelImport");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="java.lang.String:type, com.runwaysdk.controller.MultipartFileParameter:upfile", post=true)
  public void failSurveyExcelImport(java.lang.String type, com.runwaysdk.controller.MultipartFileParameter upfile) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.generator.ExcelController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.generator.ExcelController.failSurveyExcelImport");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="", post=false)
  public void surveyImportType() throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.generator.ExcelController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.generator.ExcelController.surveyImportType");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="", post=false)
  public void failSurveyImportType() throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.generator.ExcelController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.generator.ExcelController.failSurveyImportType");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="", post=false)
  public void viewManager() throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.generator.ExcelController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.generator.ExcelController.viewManager");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="", post=false)
  public void failViewManager() throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.generator.ExcelController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.generator.ExcelController.failViewManager");
  }
  
}
