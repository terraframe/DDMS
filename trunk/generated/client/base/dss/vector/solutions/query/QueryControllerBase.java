package dss.vector.solutions.query;

@com.terraframe.mojo.business.ClassSignature(hash = 1012007668)
public class QueryControllerBase implements com.terraframe.mojo.generation.loader.Reloadable
{
  public static final String CLASS = "dss.vector.solutions.query.QueryController";
  protected javax.servlet.http.HttpServletRequest req;
  protected javax.servlet.http.HttpServletResponse resp;
  protected java.lang.Boolean isAsynchronous;
  protected java.lang.String dir;
  protected java.lang.String layout;
  
  private static final long serialVersionUID = 1012007668;
  
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
  
  @com.terraframe.mojo.controller.ActionParameters(parameters="dss.vector.solutions.query.SavedSearchViewDTO:savedQueryView", post=true)
  public void cancelQuery(dss.vector.solutions.query.SavedSearchViewDTO savedQueryView) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.query.QueryController.java";
    throw new com.terraframe.mojo.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.query.QueryController.cancelQuery");
  }
  
  @com.terraframe.mojo.controller.ActionParameters(parameters="dss.vector.solutions.query.SavedSearchViewDTO:savedQueryView", post=true)
  public void failCancelQuery(dss.vector.solutions.query.SavedSearchViewDTO savedQueryView) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.query.QueryController.java";
    throw new com.terraframe.mojo.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.query.QueryController.failCancelQuery");
  }
  
  @com.terraframe.mojo.controller.ActionParameters(parameters="dss.vector.solutions.query.SavedSearchViewDTO:savedQueryView", post=true)
  public void deleteQuery(dss.vector.solutions.query.SavedSearchViewDTO savedQueryView) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.query.QueryController.java";
    throw new com.terraframe.mojo.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.query.QueryController.deleteQuery");
  }
  
  @com.terraframe.mojo.controller.ActionParameters(parameters="dss.vector.solutions.query.SavedSearchViewDTO:savedQueryView", post=true)
  public void failDeleteQuery(dss.vector.solutions.query.SavedSearchViewDTO savedQueryView) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.query.QueryController.java";
    throw new com.terraframe.mojo.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.query.QueryController.failDeleteQuery");
  }
  
  @com.terraframe.mojo.controller.ActionParameters(parameters="java.lang.String:queryXML, java.lang.String:config, java.lang.String:savedSearchId", post=true)
  public void exportAggregatedCaseQueryToCSV(java.lang.String queryXML, java.lang.String config, java.lang.String savedSearchId) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.query.QueryController.java";
    throw new com.terraframe.mojo.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.query.QueryController.exportAggregatedCaseQueryToCSV");
  }
  
  @com.terraframe.mojo.controller.ActionParameters(parameters="java.lang.String:queryXML, java.lang.String:config, java.lang.String:savedSearchId", post=true)
  public void failExportAggregatedCaseQueryToCSV(java.lang.String queryXML, java.lang.String config, java.lang.String savedSearchId) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.query.QueryController.java";
    throw new com.terraframe.mojo.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.query.QueryController.failExportAggregatedCaseQueryToCSV");
  }
  
  @com.terraframe.mojo.controller.ActionParameters(parameters="java.lang.String:queryXML, java.lang.String:config, java.lang.String:savedSearchId", post=true)
  public void exportAggregatedCaseQueryToExcel(java.lang.String queryXML, java.lang.String config, java.lang.String savedSearchId) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.query.QueryController.java";
    throw new com.terraframe.mojo.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.query.QueryController.exportAggregatedCaseQueryToExcel");
  }
  
  @com.terraframe.mojo.controller.ActionParameters(parameters="java.lang.String:queryXML, java.lang.String:config, java.lang.String:savedSearchId", post=true)
  public void failExportAggregatedCaseQueryToExcel(java.lang.String queryXML, java.lang.String config, java.lang.String savedSearchId) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.query.QueryController.java";
    throw new com.terraframe.mojo.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.query.QueryController.failExportAggregatedCaseQueryToExcel");
  }
  
  @com.terraframe.mojo.controller.ActionParameters(parameters="java.lang.String:queryXML, java.lang.String:config, java.lang.String:savedSearchId", post=true)
  public void exportAggregatedIPTQueryToCSV(java.lang.String queryXML, java.lang.String config, java.lang.String savedSearchId) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.query.QueryController.java";
    throw new com.terraframe.mojo.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.query.QueryController.exportAggregatedIPTQueryToCSV");
  }
  
  @com.terraframe.mojo.controller.ActionParameters(parameters="java.lang.String:queryXML, java.lang.String:config, java.lang.String:savedSearchId", post=true)
  public void failExportAggregatedIPTQueryToCSV(java.lang.String queryXML, java.lang.String config, java.lang.String savedSearchId) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.query.QueryController.java";
    throw new com.terraframe.mojo.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.query.QueryController.failExportAggregatedIPTQueryToCSV");
  }
  
  @com.terraframe.mojo.controller.ActionParameters(parameters="java.lang.String:queryXML, java.lang.String:config, java.lang.String:savedSearchId", post=true)
  public void exportAggregatedIPTQueryToExcel(java.lang.String queryXML, java.lang.String config, java.lang.String savedSearchId) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.query.QueryController.java";
    throw new com.terraframe.mojo.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.query.QueryController.exportAggregatedIPTQueryToExcel");
  }
  
  @com.terraframe.mojo.controller.ActionParameters(parameters="java.lang.String:queryXML, java.lang.String:config, java.lang.String:savedSearchId", post=true)
  public void failExportAggregatedIPTQueryToExcel(java.lang.String queryXML, java.lang.String config, java.lang.String savedSearchId) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.query.QueryController.java";
    throw new com.terraframe.mojo.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.query.QueryController.failExportAggregatedIPTQueryToExcel");
  }
  
  @com.terraframe.mojo.controller.ActionParameters(parameters="java.lang.String:queryXML, java.lang.String:config, java.lang.String:savedSearchId", post=true)
  public void exportEntomologyQueryToCSV(java.lang.String queryXML, java.lang.String config, java.lang.String savedSearchId) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.query.QueryController.java";
    throw new com.terraframe.mojo.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.query.QueryController.exportEntomologyQueryToCSV");
  }
  
  @com.terraframe.mojo.controller.ActionParameters(parameters="java.lang.String:queryXML, java.lang.String:config, java.lang.String:savedSearchId", post=true)
  public void failExportEntomologyQueryToCSV(java.lang.String queryXML, java.lang.String config, java.lang.String savedSearchId) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.query.QueryController.java";
    throw new com.terraframe.mojo.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.query.QueryController.failExportEntomologyQueryToCSV");
  }
  
  @com.terraframe.mojo.controller.ActionParameters(parameters="java.lang.String:queryXML, java.lang.String:config, java.lang.String:savedSearchId", post=true)
  public void exportEntomologyQueryToExcel(java.lang.String queryXML, java.lang.String config, java.lang.String savedSearchId) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.query.QueryController.java";
    throw new com.terraframe.mojo.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.query.QueryController.exportEntomologyQueryToExcel");
  }
  
  @com.terraframe.mojo.controller.ActionParameters(parameters="java.lang.String:queryXML, java.lang.String:config, java.lang.String:savedSearchId", post=true)
  public void failExportEntomologyQueryToExcel(java.lang.String queryXML, java.lang.String config, java.lang.String savedSearchId) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.query.QueryController.java";
    throw new com.terraframe.mojo.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.query.QueryController.failExportEntomologyQueryToExcel");
  }
  
  @com.terraframe.mojo.controller.ActionParameters(parameters="java.lang.String:queryXML, java.lang.String:config, java.lang.String:savedSearchId", post=true)
  public void exportIRSQueryToCSV(java.lang.String queryXML, java.lang.String config, java.lang.String savedSearchId) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.query.QueryController.java";
    throw new com.terraframe.mojo.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.query.QueryController.exportIRSQueryToCSV");
  }
  
  @com.terraframe.mojo.controller.ActionParameters(parameters="java.lang.String:queryXML, java.lang.String:config, java.lang.String:savedSearchId", post=true)
  public void failExportIRSQueryToCSV(java.lang.String queryXML, java.lang.String config, java.lang.String savedSearchId) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.query.QueryController.java";
    throw new com.terraframe.mojo.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.query.QueryController.failExportIRSQueryToCSV");
  }
  
  @com.terraframe.mojo.controller.ActionParameters(parameters="java.lang.String:queryXML, java.lang.String:config, java.lang.String:savedSearchId", post=true)
  public void exportIRSQueryToExcel(java.lang.String queryXML, java.lang.String config, java.lang.String savedSearchId) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.query.QueryController.java";
    throw new com.terraframe.mojo.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.query.QueryController.exportIRSQueryToExcel");
  }
  
  @com.terraframe.mojo.controller.ActionParameters(parameters="java.lang.String:queryXML, java.lang.String:config, java.lang.String:savedSearchId", post=true)
  public void failExportIRSQueryToExcel(java.lang.String queryXML, java.lang.String config, java.lang.String savedSearchId) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.query.QueryController.java";
    throw new com.terraframe.mojo.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.query.QueryController.failExportIRSQueryToExcel");
  }
  
  @com.terraframe.mojo.controller.ActionParameters(parameters="java.lang.String:queryClass, java.lang.String:queryXML, java.lang.String:config, java.lang.String:savedSearchId", post=true)
  public void exportQueryToCSV(java.lang.String queryClass, java.lang.String queryXML, java.lang.String config, java.lang.String savedSearchId) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.query.QueryController.java";
    throw new com.terraframe.mojo.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.query.QueryController.exportQueryToCSV");
  }
  
  @com.terraframe.mojo.controller.ActionParameters(parameters="java.lang.String:queryClass, java.lang.String:queryXML, java.lang.String:config, java.lang.String:savedSearchId", post=true)
  public void failExportQueryToCSV(java.lang.String queryClass, java.lang.String queryXML, java.lang.String config, java.lang.String savedSearchId) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.query.QueryController.java";
    throw new com.terraframe.mojo.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.query.QueryController.failExportQueryToCSV");
  }
  
  @com.terraframe.mojo.controller.ActionParameters(parameters="java.lang.String:queryClass, java.lang.String:queryXML, java.lang.String:config, java.lang.String:savedSearchId", post=true)
  public void exportQueryToExcel(java.lang.String queryClass, java.lang.String queryXML, java.lang.String config, java.lang.String savedSearchId) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.query.QueryController.java";
    throw new com.terraframe.mojo.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.query.QueryController.exportQueryToExcel");
  }
  
  @com.terraframe.mojo.controller.ActionParameters(parameters="java.lang.String:queryClass, java.lang.String:queryXML, java.lang.String:config, java.lang.String:savedSearchId", post=true)
  public void failExportQueryToExcel(java.lang.String queryClass, java.lang.String queryXML, java.lang.String config, java.lang.String savedSearchId) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.query.QueryController.java";
    throw new com.terraframe.mojo.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.query.QueryController.failExportQueryToExcel");
  }
  
  @com.terraframe.mojo.controller.ActionParameters(parameters="java.lang.String:queryXML, java.lang.String:config, java.lang.String:savedSearchId", post=true)
  public void exportResistanceQueryToCSV(java.lang.String queryXML, java.lang.String config, java.lang.String savedSearchId) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.query.QueryController.java";
    throw new com.terraframe.mojo.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.query.QueryController.exportResistanceQueryToCSV");
  }
  
  @com.terraframe.mojo.controller.ActionParameters(parameters="java.lang.String:queryXML, java.lang.String:config, java.lang.String:savedSearchId", post=true)
  public void failExportResistanceQueryToCSV(java.lang.String queryXML, java.lang.String config, java.lang.String savedSearchId) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.query.QueryController.java";
    throw new com.terraframe.mojo.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.query.QueryController.failExportResistanceQueryToCSV");
  }
  
  @com.terraframe.mojo.controller.ActionParameters(parameters="java.lang.String:queryXML, java.lang.String:config, java.lang.String:savedSearchId", post=true)
  public void exportResistanceQueryToExcel(java.lang.String queryXML, java.lang.String config, java.lang.String savedSearchId) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.query.QueryController.java";
    throw new com.terraframe.mojo.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.query.QueryController.exportResistanceQueryToExcel");
  }
  
  @com.terraframe.mojo.controller.ActionParameters(parameters="java.lang.String:queryXML, java.lang.String:config, java.lang.String:savedSearchId", post=true)
  public void failExportResistanceQueryToExcel(java.lang.String queryXML, java.lang.String config, java.lang.String savedSearchId) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.query.QueryController.java";
    throw new com.terraframe.mojo.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.query.QueryController.failExportResistanceQueryToExcel");
  }
  
  @com.terraframe.mojo.controller.ActionParameters(parameters="java.lang.String:queryXML, java.lang.String:config, java.lang.String:savedSearchId", post=true)
  public void exportSurveyQueryToCSV(java.lang.String queryXML, java.lang.String config, java.lang.String savedSearchId) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.query.QueryController.java";
    throw new com.terraframe.mojo.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.query.QueryController.exportSurveyQueryToCSV");
  }
  
  @com.terraframe.mojo.controller.ActionParameters(parameters="java.lang.String:queryXML, java.lang.String:config, java.lang.String:savedSearchId", post=true)
  public void failExportSurveyQueryToCSV(java.lang.String queryXML, java.lang.String config, java.lang.String savedSearchId) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.query.QueryController.java";
    throw new com.terraframe.mojo.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.query.QueryController.failExportSurveyQueryToCSV");
  }
  
  @com.terraframe.mojo.controller.ActionParameters(parameters="java.lang.String:queryXML, java.lang.String:config, java.lang.String:savedSearchId", post=true)
  public void exportSurveyQueryToExcel(java.lang.String queryXML, java.lang.String config, java.lang.String savedSearchId) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.query.QueryController.java";
    throw new com.terraframe.mojo.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.query.QueryController.exportSurveyQueryToExcel");
  }
  
  @com.terraframe.mojo.controller.ActionParameters(parameters="java.lang.String:queryXML, java.lang.String:config, java.lang.String:savedSearchId", post=true)
  public void failExportSurveyQueryToExcel(java.lang.String queryXML, java.lang.String config, java.lang.String savedSearchId) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.query.QueryController.java";
    throw new com.terraframe.mojo.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.query.QueryController.failExportSurveyQueryToExcel");
  }
  
  @com.terraframe.mojo.controller.ActionParameters(parameters="", post=false)
  public void newQuery() throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.query.QueryController.java";
    throw new com.terraframe.mojo.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.query.QueryController.newQuery");
  }
  
  @com.terraframe.mojo.controller.ActionParameters(parameters="", post=false)
  public void failNewQuery() throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.query.QueryController.java";
    throw new com.terraframe.mojo.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.query.QueryController.failNewQuery");
  }
  
  @com.terraframe.mojo.controller.ActionParameters(parameters="", post=false)
  public void queryAggregatedCases() throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.query.QueryController.java";
    throw new com.terraframe.mojo.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.query.QueryController.queryAggregatedCases");
  }
  
  @com.terraframe.mojo.controller.ActionParameters(parameters="", post=false)
  public void failQueryAggregatedCases() throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.query.QueryController.java";
    throw new com.terraframe.mojo.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.query.QueryController.failQueryAggregatedCases");
  }
  
  @com.terraframe.mojo.controller.ActionParameters(parameters="", post=false)
  public void queryAggregatedIPT() throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.query.QueryController.java";
    throw new com.terraframe.mojo.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.query.QueryController.queryAggregatedIPT");
  }
  
  @com.terraframe.mojo.controller.ActionParameters(parameters="", post=false)
  public void failQueryAggregatedIPT() throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.query.QueryController.java";
    throw new com.terraframe.mojo.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.query.QueryController.failQueryAggregatedIPT");
  }
  
  @com.terraframe.mojo.controller.ActionParameters(parameters="", post=false)
  public void queryEfficacyAssay() throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.query.QueryController.java";
    throw new com.terraframe.mojo.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.query.QueryController.queryEfficacyAssay");
  }
  
  @com.terraframe.mojo.controller.ActionParameters(parameters="", post=false)
  public void failQueryEfficacyAssay() throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.query.QueryController.java";
    throw new com.terraframe.mojo.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.query.QueryController.failQueryEfficacyAssay");
  }
  
  @com.terraframe.mojo.controller.ActionParameters(parameters="", post=false)
  public void queryEntomology() throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.query.QueryController.java";
    throw new com.terraframe.mojo.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.query.QueryController.queryEntomology");
  }
  
  @com.terraframe.mojo.controller.ActionParameters(parameters="", post=false)
  public void failQueryEntomology() throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.query.QueryController.java";
    throw new com.terraframe.mojo.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.query.QueryController.failQueryEntomology");
  }
  
  @com.terraframe.mojo.controller.ActionParameters(parameters="", post=false)
  public void queryIRS() throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.query.QueryController.java";
    throw new com.terraframe.mojo.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.query.QueryController.queryIRS");
  }
  
  @com.terraframe.mojo.controller.ActionParameters(parameters="", post=false)
  public void failQueryIRS() throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.query.QueryController.java";
    throw new com.terraframe.mojo.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.query.QueryController.failQueryIRS");
  }
  
  @com.terraframe.mojo.controller.ActionParameters(parameters="", post=false)
  public void queryIndicatorSurvey() throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.query.QueryController.java";
    throw new com.terraframe.mojo.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.query.QueryController.queryIndicatorSurvey");
  }
  
  @com.terraframe.mojo.controller.ActionParameters(parameters="", post=false)
  public void failQueryIndicatorSurvey() throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.query.QueryController.java";
    throw new com.terraframe.mojo.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.query.QueryController.failQueryIndicatorSurvey");
  }
  
  @com.terraframe.mojo.controller.ActionParameters(parameters="", post=false)
  public void queryIndividualCases() throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.query.QueryController.java";
    throw new com.terraframe.mojo.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.query.QueryController.queryIndividualCases");
  }
  
  @com.terraframe.mojo.controller.ActionParameters(parameters="", post=false)
  public void failQueryIndividualCases() throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.query.QueryController.java";
    throw new com.terraframe.mojo.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.query.QueryController.failQueryIndividualCases");
  }
  
  @com.terraframe.mojo.controller.ActionParameters(parameters="", post=false)
  public void queryIndividualIPT() throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.query.QueryController.java";
    throw new com.terraframe.mojo.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.query.QueryController.queryIndividualIPT");
  }
  
  @com.terraframe.mojo.controller.ActionParameters(parameters="", post=false)
  public void failQueryIndividualIPT() throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.query.QueryController.java";
    throw new com.terraframe.mojo.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.query.QueryController.failQueryIndividualIPT");
  }
  
  @com.terraframe.mojo.controller.ActionParameters(parameters="", post=false)
  public void queryResistance() throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.query.QueryController.java";
    throw new com.terraframe.mojo.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.query.QueryController.queryResistance");
  }
  
  @com.terraframe.mojo.controller.ActionParameters(parameters="", post=false)
  public void failQueryResistance() throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.query.QueryController.java";
    throw new com.terraframe.mojo.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.query.QueryController.failQueryResistance");
  }
  
  @com.terraframe.mojo.controller.ActionParameters(parameters="", post=false)
  public void querySurvey() throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.query.QueryController.java";
    throw new com.terraframe.mojo.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.query.QueryController.querySurvey");
  }
  
  @com.terraframe.mojo.controller.ActionParameters(parameters="", post=false)
  public void failQuerySurvey() throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.query.QueryController.java";
    throw new com.terraframe.mojo.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.query.QueryController.failQuerySurvey");
  }
  
  @com.terraframe.mojo.controller.ActionParameters(parameters="", post=true)
  public void saveQuery() throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.query.QueryController.java";
    throw new com.terraframe.mojo.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.query.QueryController.saveQuery");
  }
  
  @com.terraframe.mojo.controller.ActionParameters(parameters="", post=true)
  public void failSaveQuery() throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.query.QueryController.java";
    throw new com.terraframe.mojo.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.query.QueryController.failSaveQuery");
  }
  
  @com.terraframe.mojo.controller.ActionParameters(parameters="java.lang.String:savedSearchId", post=true)
  public void uploadTemplate(java.lang.String savedSearchId) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.query.QueryController.java";
    throw new com.terraframe.mojo.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.query.QueryController.uploadTemplate");
  }
  
  @com.terraframe.mojo.controller.ActionParameters(parameters="java.lang.String:savedSearchId", post=true)
  public void failUploadTemplate(java.lang.String savedSearchId) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.query.QueryController.java";
    throw new com.terraframe.mojo.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.query.QueryController.failUploadTemplate");
  }
  
}
