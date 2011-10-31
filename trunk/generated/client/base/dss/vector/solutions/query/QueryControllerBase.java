package dss.vector.solutions.query;

@com.runwaysdk.business.ClassSignature(hash = -1152760523)
public class QueryControllerBase implements com.runwaysdk.generation.loader.Reloadable
{
  public static final String CLASS = "dss.vector.solutions.query.QueryController";
  protected javax.servlet.http.HttpServletRequest req;
  protected javax.servlet.http.HttpServletResponse resp;
  protected java.lang.Boolean isAsynchronous;
  protected java.lang.String dir;
  protected java.lang.String layout;
  
  private static final long serialVersionUID = -1152760523;
  
  public QueryControllerBase(javax.servlet.http.HttpServletRequest req, javax.servlet.http.HttpServletResponse resp, java.lang.Boolean isAsynchronous)
  {
    this(req, resp, isAsynchronous, "","");
  }
  
  public QueryControllerBase(javax.servlet.http.HttpServletRequest req, javax.servlet.http.HttpServletResponse resp, java.lang.Boolean isAsynchronous, java.lang.String dir, java.lang.String layout)
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
  
  @com.runwaysdk.controller.ActionParameters(parameters="dss.vector.solutions.query.SavedSearchViewDTO:savedQueryView", post=true)
  public void cancelQuery(dss.vector.solutions.query.SavedSearchViewDTO savedQueryView) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.query.QueryController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.query.QueryController.cancelQuery");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="dss.vector.solutions.query.SavedSearchViewDTO:savedQueryView", post=true)
  public void failCancelQuery(dss.vector.solutions.query.SavedSearchViewDTO savedQueryView) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.query.QueryController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.query.QueryController.failCancelQuery");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="dss.vector.solutions.query.SavedSearchViewDTO:savedQueryView", post=true)
  public void deleteQuery(dss.vector.solutions.query.SavedSearchViewDTO savedQueryView) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.query.QueryController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.query.QueryController.deleteQuery");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="dss.vector.solutions.query.SavedSearchViewDTO:savedQueryView", post=true)
  public void failDeleteQuery(dss.vector.solutions.query.SavedSearchViewDTO savedQueryView) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.query.QueryController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.query.QueryController.failDeleteQuery");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="java.lang.String:queryXML, java.lang.String:config, java.lang.String:savedSearchId", post=true)
  public void exportAggregatedCaseQueryToCSV(java.lang.String queryXML, java.lang.String config, java.lang.String savedSearchId) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.query.QueryController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.query.QueryController.exportAggregatedCaseQueryToCSV");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="java.lang.String:queryXML, java.lang.String:config, java.lang.String:savedSearchId", post=true)
  public void failExportAggregatedCaseQueryToCSV(java.lang.String queryXML, java.lang.String config, java.lang.String savedSearchId) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.query.QueryController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.query.QueryController.failExportAggregatedCaseQueryToCSV");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="java.lang.String:queryXML, java.lang.String:config, java.lang.String:savedSearchId", post=true)
  public void exportAggregatedCaseQueryToExcel(java.lang.String queryXML, java.lang.String config, java.lang.String savedSearchId) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.query.QueryController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.query.QueryController.exportAggregatedCaseQueryToExcel");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="java.lang.String:queryXML, java.lang.String:config, java.lang.String:savedSearchId", post=true)
  public void failExportAggregatedCaseQueryToExcel(java.lang.String queryXML, java.lang.String config, java.lang.String savedSearchId) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.query.QueryController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.query.QueryController.failExportAggregatedCaseQueryToExcel");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="java.lang.String:queryXML, java.lang.String:config, java.lang.String:savedSearchId", post=true)
  public void exportAggregatedIPTQueryToCSV(java.lang.String queryXML, java.lang.String config, java.lang.String savedSearchId) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.query.QueryController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.query.QueryController.exportAggregatedIPTQueryToCSV");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="java.lang.String:queryXML, java.lang.String:config, java.lang.String:savedSearchId", post=true)
  public void failExportAggregatedIPTQueryToCSV(java.lang.String queryXML, java.lang.String config, java.lang.String savedSearchId) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.query.QueryController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.query.QueryController.failExportAggregatedIPTQueryToCSV");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="java.lang.String:queryXML, java.lang.String:config, java.lang.String:savedSearchId", post=true)
  public void exportAggregatedIPTQueryToExcel(java.lang.String queryXML, java.lang.String config, java.lang.String savedSearchId) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.query.QueryController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.query.QueryController.exportAggregatedIPTQueryToExcel");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="java.lang.String:queryXML, java.lang.String:config, java.lang.String:savedSearchId", post=true)
  public void failExportAggregatedIPTQueryToExcel(java.lang.String queryXML, java.lang.String config, java.lang.String savedSearchId) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.query.QueryController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.query.QueryController.failExportAggregatedIPTQueryToExcel");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="java.lang.String:queryXML, java.lang.String:config, java.lang.String:savedSearchId", post=true)
  public void exportEntomologyQueryToCSV(java.lang.String queryXML, java.lang.String config, java.lang.String savedSearchId) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.query.QueryController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.query.QueryController.exportEntomologyQueryToCSV");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="java.lang.String:queryXML, java.lang.String:config, java.lang.String:savedSearchId", post=true)
  public void failExportEntomologyQueryToCSV(java.lang.String queryXML, java.lang.String config, java.lang.String savedSearchId) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.query.QueryController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.query.QueryController.failExportEntomologyQueryToCSV");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="java.lang.String:queryXML, java.lang.String:config, java.lang.String:savedSearchId", post=true)
  public void exportEntomologyQueryToExcel(java.lang.String queryXML, java.lang.String config, java.lang.String savedSearchId) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.query.QueryController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.query.QueryController.exportEntomologyQueryToExcel");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="java.lang.String:queryXML, java.lang.String:config, java.lang.String:savedSearchId", post=true)
  public void failExportEntomologyQueryToExcel(java.lang.String queryXML, java.lang.String config, java.lang.String savedSearchId) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.query.QueryController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.query.QueryController.failExportEntomologyQueryToExcel");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="java.lang.String:queryXML, java.lang.String:config, java.lang.String:savedSearchId", post=true)
  public void exportIRSQueryToCSV(java.lang.String queryXML, java.lang.String config, java.lang.String savedSearchId) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.query.QueryController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.query.QueryController.exportIRSQueryToCSV");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="java.lang.String:queryXML, java.lang.String:config, java.lang.String:savedSearchId", post=true)
  public void failExportIRSQueryToCSV(java.lang.String queryXML, java.lang.String config, java.lang.String savedSearchId) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.query.QueryController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.query.QueryController.failExportIRSQueryToCSV");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="java.lang.String:queryXML, java.lang.String:config, java.lang.String:savedSearchId", post=true)
  public void exportIRSQueryToExcel(java.lang.String queryXML, java.lang.String config, java.lang.String savedSearchId) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.query.QueryController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.query.QueryController.exportIRSQueryToExcel");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="java.lang.String:queryXML, java.lang.String:config, java.lang.String:savedSearchId", post=true)
  public void failExportIRSQueryToExcel(java.lang.String queryXML, java.lang.String config, java.lang.String savedSearchId) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.query.QueryController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.query.QueryController.failExportIRSQueryToExcel");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="java.lang.String:queryClass, java.lang.String:queryXML, java.lang.String:config, java.lang.String:savedSearchId", post=true)
  public void exportQueryToCSV(java.lang.String queryClass, java.lang.String queryXML, java.lang.String config, java.lang.String savedSearchId) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.query.QueryController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.query.QueryController.exportQueryToCSV");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="java.lang.String:queryClass, java.lang.String:queryXML, java.lang.String:config, java.lang.String:savedSearchId", post=true)
  public void failExportQueryToCSV(java.lang.String queryClass, java.lang.String queryXML, java.lang.String config, java.lang.String savedSearchId) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.query.QueryController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.query.QueryController.failExportQueryToCSV");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="java.lang.String:queryClass, java.lang.String:queryXML, java.lang.String:config, java.lang.String:savedSearchId", post=true)
  public void exportQueryToExcel(java.lang.String queryClass, java.lang.String queryXML, java.lang.String config, java.lang.String savedSearchId) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.query.QueryController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.query.QueryController.exportQueryToExcel");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="java.lang.String:queryClass, java.lang.String:queryXML, java.lang.String:config, java.lang.String:savedSearchId", post=true)
  public void failExportQueryToExcel(java.lang.String queryClass, java.lang.String queryXML, java.lang.String config, java.lang.String savedSearchId) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.query.QueryController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.query.QueryController.failExportQueryToExcel");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="java.lang.String:queryXML, java.lang.String:config, java.lang.String:savedSearchId", post=true)
  public void exportResistanceQueryToCSV(java.lang.String queryXML, java.lang.String config, java.lang.String savedSearchId) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.query.QueryController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.query.QueryController.exportResistanceQueryToCSV");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="java.lang.String:queryXML, java.lang.String:config, java.lang.String:savedSearchId", post=true)
  public void failExportResistanceQueryToCSV(java.lang.String queryXML, java.lang.String config, java.lang.String savedSearchId) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.query.QueryController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.query.QueryController.failExportResistanceQueryToCSV");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="java.lang.String:queryXML, java.lang.String:config, java.lang.String:savedSearchId", post=true)
  public void exportResistanceQueryToExcel(java.lang.String queryXML, java.lang.String config, java.lang.String savedSearchId) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.query.QueryController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.query.QueryController.exportResistanceQueryToExcel");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="java.lang.String:queryXML, java.lang.String:config, java.lang.String:savedSearchId", post=true)
  public void failExportResistanceQueryToExcel(java.lang.String queryXML, java.lang.String config, java.lang.String savedSearchId) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.query.QueryController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.query.QueryController.failExportResistanceQueryToExcel");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="java.lang.String:queryXML, java.lang.String:config, java.lang.String:savedSearchId", post=true)
  public void exportSurveyQueryToCSV(java.lang.String queryXML, java.lang.String config, java.lang.String savedSearchId) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.query.QueryController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.query.QueryController.exportSurveyQueryToCSV");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="java.lang.String:queryXML, java.lang.String:config, java.lang.String:savedSearchId", post=true)
  public void failExportSurveyQueryToCSV(java.lang.String queryXML, java.lang.String config, java.lang.String savedSearchId) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.query.QueryController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.query.QueryController.failExportSurveyQueryToCSV");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="java.lang.String:queryXML, java.lang.String:config, java.lang.String:savedSearchId", post=true)
  public void exportSurveyQueryToExcel(java.lang.String queryXML, java.lang.String config, java.lang.String savedSearchId) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.query.QueryController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.query.QueryController.exportSurveyQueryToExcel");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="java.lang.String:queryXML, java.lang.String:config, java.lang.String:savedSearchId", post=true)
  public void failExportSurveyQueryToExcel(java.lang.String queryXML, java.lang.String config, java.lang.String savedSearchId) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.query.QueryController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.query.QueryController.failExportSurveyQueryToExcel");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="", post=false)
  public void newQuery() throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.query.QueryController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.query.QueryController.newQuery");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="", post=false)
  public void failNewQuery() throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.query.QueryController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.query.QueryController.failNewQuery");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="", post=false)
  public void queryAggregatedCases() throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.query.QueryController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.query.QueryController.queryAggregatedCases");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="", post=false)
  public void failQueryAggregatedCases() throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.query.QueryController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.query.QueryController.failQueryAggregatedCases");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="", post=false)
  public void queryAggregatedIPT() throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.query.QueryController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.query.QueryController.queryAggregatedIPT");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="", post=false)
  public void failQueryAggregatedIPT() throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.query.QueryController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.query.QueryController.failQueryAggregatedIPT");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="", post=false)
  public void queryAggregatedITN() throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.query.QueryController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.query.QueryController.queryAggregatedITN");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="", post=false)
  public void failQueryAggregatedITN() throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.query.QueryController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.query.QueryController.failQueryAggregatedITN");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="", post=false)
  public void queryEfficacyAssay() throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.query.QueryController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.query.QueryController.queryEfficacyAssay");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="", post=false)
  public void failQueryEfficacyAssay() throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.query.QueryController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.query.QueryController.failQueryEfficacyAssay");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="", post=false)
  public void queryEntomology() throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.query.QueryController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.query.QueryController.queryEntomology");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="", post=false)
  public void failQueryEntomology() throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.query.QueryController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.query.QueryController.failQueryEntomology");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="", post=false)
  public void queryFormSurvey() throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.query.QueryController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.query.QueryController.queryFormSurvey");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="", post=false)
  public void failQueryFormSurvey() throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.query.QueryController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.query.QueryController.failQueryFormSurvey");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="", post=false)
  public void queryIRS() throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.query.QueryController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.query.QueryController.queryIRS");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="", post=false)
  public void failQueryIRS() throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.query.QueryController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.query.QueryController.failQueryIRS");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="", post=false)
  public void queryITNCommunityDistribution() throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.query.QueryController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.query.QueryController.queryITNCommunityDistribution");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="", post=false)
  public void failQueryITNCommunityDistribution() throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.query.QueryController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.query.QueryController.failQueryITNCommunityDistribution");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="", post=false)
  public void queryITNDistribution() throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.query.QueryController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.query.QueryController.queryITNDistribution");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="", post=false)
  public void failQueryITNDistribution() throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.query.QueryController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.query.QueryController.failQueryITNDistribution");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="", post=false)
  public void queryImmatureContainerCollection() throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.query.QueryController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.query.QueryController.queryImmatureContainerCollection");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="", post=false)
  public void failQueryImmatureContainerCollection() throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.query.QueryController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.query.QueryController.failQueryImmatureContainerCollection");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="", post=false)
  public void queryIndicatorSurvey() throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.query.QueryController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.query.QueryController.queryIndicatorSurvey");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="", post=false)
  public void failQueryIndicatorSurvey() throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.query.QueryController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.query.QueryController.failQueryIndicatorSurvey");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="", post=false)
  public void queryIndividualCases() throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.query.QueryController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.query.QueryController.queryIndividualCases");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="", post=false)
  public void failQueryIndividualCases() throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.query.QueryController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.query.QueryController.failQueryIndividualCases");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="", post=false)
  public void queryIndividualIPT() throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.query.QueryController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.query.QueryController.queryIndividualIPT");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="", post=false)
  public void failQueryIndividualIPT() throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.query.QueryController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.query.QueryController.failQueryIndividualIPT");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="", post=false)
  public void queryInterventionControl() throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.query.QueryController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.query.QueryController.queryInterventionControl");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="", post=false)
  public void failQueryInterventionControl() throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.query.QueryController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.query.QueryController.failQueryInterventionControl");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="", post=false)
  public void queryLarvacide() throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.query.QueryController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.query.QueryController.queryLarvacide");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="", post=false)
  public void failQueryLarvacide() throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.query.QueryController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.query.QueryController.failQueryLarvacide");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="", post=false)
  public void queryMosquitoCollections() throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.query.QueryController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.query.QueryController.queryMosquitoCollections");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="", post=false)
  public void failQueryMosquitoCollections() throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.query.QueryController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.query.QueryController.failQueryMosquitoCollections");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="", post=false)
  public void queryPupalContainerCollection() throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.query.QueryController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.query.QueryController.queryPupalContainerCollection");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="", post=false)
  public void failQueryPupalContainerCollection() throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.query.QueryController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.query.QueryController.failQueryPupalContainerCollection");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="", post=false)
  public void queryResistance() throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.query.QueryController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.query.QueryController.queryResistance");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="", post=false)
  public void failQueryResistance() throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.query.QueryController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.query.QueryController.failQueryResistance");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="", post=false)
  public void queryResistanceBioassay() throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.query.QueryController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.query.QueryController.queryResistanceBioassay");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="", post=false)
  public void failQueryResistanceBioassay() throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.query.QueryController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.query.QueryController.failQueryResistanceBioassay");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="", post=false)
  public void queryStock() throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.query.QueryController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.query.QueryController.queryStock");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="", post=false)
  public void failQueryStock() throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.query.QueryController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.query.QueryController.failQueryStock");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="", post=false)
  public void querySurvey() throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.query.QueryController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.query.QueryController.querySurvey");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="", post=false)
  public void failQuerySurvey() throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.query.QueryController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.query.QueryController.failQuerySurvey");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="java.lang.String:type", post=false)
  public void queryType(java.lang.String type) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.query.QueryController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.query.QueryController.queryType");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="java.lang.String:type", post=false)
  public void failQueryType(java.lang.String type) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.query.QueryController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.query.QueryController.failQueryType");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="", post=true)
  public void saveQuery() throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.query.QueryController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.query.QueryController.saveQuery");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="", post=true)
  public void failSaveQuery() throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.query.QueryController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.query.QueryController.failSaveQuery");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="java.lang.String:savedSearchId", post=true)
  public void uploadTemplate(java.lang.String savedSearchId) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.query.QueryController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.query.QueryController.uploadTemplate");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="java.lang.String:savedSearchId", post=true)
  public void failUploadTemplate(java.lang.String savedSearchId) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.query.QueryController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.query.QueryController.failUploadTemplate");
  }
  
}
