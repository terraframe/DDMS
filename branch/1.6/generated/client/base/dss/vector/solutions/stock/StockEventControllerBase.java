package dss.vector.solutions.stock;

@com.runwaysdk.business.ClassSignature(hash = -1801818703)
public class StockEventControllerBase implements com.runwaysdk.generation.loader.Reloadable
{
  public static final String CLASS = "dss.vector.solutions.stock.StockEventController";
  protected javax.servlet.http.HttpServletRequest req;
  protected javax.servlet.http.HttpServletResponse resp;
  protected java.lang.Boolean isAsynchronous;
  protected java.lang.String dir;
  protected java.lang.String layout;
  
  public StockEventControllerBase(javax.servlet.http.HttpServletRequest req, javax.servlet.http.HttpServletResponse resp, java.lang.Boolean isAsynchronous)
  {
    this(req, resp, isAsynchronous, "","");
  }
  
  public StockEventControllerBase(javax.servlet.http.HttpServletRequest req, javax.servlet.http.HttpServletResponse resp, java.lang.Boolean isAsynchronous, java.lang.String dir, java.lang.String layout)
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
  
  @com.runwaysdk.controller.ActionParameters(parameters="java.lang.String:geoId, dss.vector.solutions.ontology.TermDTO:item, java.util.Date:date, java.util.Date:endDate", post=true)
  public void deleteAll(java.lang.String geoId, dss.vector.solutions.ontology.TermDTO item, java.util.Date date, java.util.Date endDate) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.stock.StockEventController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.stock.StockEventController.deleteAll");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="java.lang.String:geoId, dss.vector.solutions.ontology.TermDTO:item, java.lang.String:date, java.lang.String:endDate", post=true)
  public void failDeleteAll(java.lang.String geoId, dss.vector.solutions.ontology.TermDTO item, java.lang.String date, java.lang.String endDate) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.stock.StockEventController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.stock.StockEventController.failDeleteAll");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="java.lang.String:sortAttribute, java.lang.Boolean:isAscending, java.lang.Integer:pageSize, java.lang.Integer:pageNumber, java.lang.String:geoId, java.lang.String:item, java.util.Date:startDate, java.util.Date:endDate", post=false)
  public void getPage(java.lang.String sortAttribute, java.lang.Boolean isAscending, java.lang.Integer pageSize, java.lang.Integer pageNumber, java.lang.String geoId, java.lang.String item, java.util.Date startDate, java.util.Date endDate) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.stock.StockEventController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.stock.StockEventController.getPage");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="java.lang.String:sortAttribute, java.lang.String:isAscending, java.lang.String:pageSize, java.lang.String:pageNumber, java.lang.String:geoId, java.lang.String:item, java.lang.String:startDate, java.lang.String:endDate", post=false)
  public void failGetPage(java.lang.String sortAttribute, java.lang.String isAscending, java.lang.String pageSize, java.lang.String pageNumber, java.lang.String geoId, java.lang.String item, java.lang.String startDate, java.lang.String endDate) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.stock.StockEventController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.stock.StockEventController.failGetPage");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="", post=false)
  public void search() throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.stock.StockEventController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.stock.StockEventController.search");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="", post=false)
  public void failSearch() throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.stock.StockEventController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.stock.StockEventController.failSearch");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="java.lang.String:geoId, dss.vector.solutions.ontology.TermDTO:item, java.util.Date:date", post=true)
  public void searchInStock(java.lang.String geoId, dss.vector.solutions.ontology.TermDTO item, java.util.Date date) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.stock.StockEventController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.stock.StockEventController.searchInStock");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="java.lang.String:geoId, dss.vector.solutions.ontology.TermDTO:item, java.lang.String:date", post=true)
  public void failSearchInStock(java.lang.String geoId, dss.vector.solutions.ontology.TermDTO item, java.lang.String date) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.stock.StockEventController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.stock.StockEventController.failSearchInStock");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="java.lang.String:geoId, dss.vector.solutions.ontology.TermDTO:item, java.util.Date:date", post=true)
  public void searchOutStock(java.lang.String geoId, dss.vector.solutions.ontology.TermDTO item, java.util.Date date) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.stock.StockEventController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.stock.StockEventController.searchOutStock");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="java.lang.String:geoId, dss.vector.solutions.ontology.TermDTO:item, java.lang.String:date", post=true)
  public void failSearchOutStock(java.lang.String geoId, dss.vector.solutions.ontology.TermDTO item, java.lang.String date) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.stock.StockEventController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.stock.StockEventController.failSearchOutStock");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="java.lang.String:geoId, dss.vector.solutions.ontology.TermDTO:item, java.util.Date:date, java.util.Date:endDate", post=true)
  public void searchPage(java.lang.String geoId, dss.vector.solutions.ontology.TermDTO item, java.util.Date date, java.util.Date endDate) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.stock.StockEventController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.stock.StockEventController.searchPage");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="java.lang.String:geoId, dss.vector.solutions.ontology.TermDTO:item, java.lang.String:date, java.lang.String:endDate", post=true)
  public void failSearchPage(java.lang.String geoId, dss.vector.solutions.ontology.TermDTO item, java.lang.String date, java.lang.String endDate) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.stock.StockEventController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.stock.StockEventController.failSearchPage");
  }
  
}
