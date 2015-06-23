package dss.vector.solutions.intervention.monitor;

@com.runwaysdk.business.ClassSignature(hash = -1791135526)
public class AggregatedIPTControllerBase implements com.runwaysdk.generation.loader.Reloadable
{
  public static final String CLASS = "dss.vector.solutions.intervention.monitor.AggregatedIPTController";
  protected javax.servlet.http.HttpServletRequest req;
  protected javax.servlet.http.HttpServletResponse resp;
  protected java.lang.Boolean isAsynchronous;
  protected java.lang.String dir;
  protected java.lang.String layout;
  
  public AggregatedIPTControllerBase(javax.servlet.http.HttpServletRequest req, javax.servlet.http.HttpServletResponse resp, java.lang.Boolean isAsynchronous)
  {
    this(req, resp, isAsynchronous, "","");
  }
  
  public AggregatedIPTControllerBase(javax.servlet.http.HttpServletRequest req, javax.servlet.http.HttpServletResponse resp, java.lang.Boolean isAsynchronous, java.lang.String dir, java.lang.String layout)
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
  
  @com.runwaysdk.controller.ActionParameters(parameters="dss.vector.solutions.intervention.monitor.AggregatedIPTViewDTO:dto", post=true)
  public void cancel(dss.vector.solutions.intervention.monitor.AggregatedIPTViewDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.intervention.monitor.AggregatedIPTController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.intervention.monitor.AggregatedIPTController.cancel");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="dss.vector.solutions.intervention.monitor.AggregatedIPTViewDTO:dto", post=true)
  public void failCancel(dss.vector.solutions.intervention.monitor.AggregatedIPTViewDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.intervention.monitor.AggregatedIPTController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.intervention.monitor.AggregatedIPTController.failCancel");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="dss.vector.solutions.intervention.monitor.AggregatedIPTViewDTO:dto, [Ldss.vector.solutions.intervention.monitor.IPTPatientsDTO;:patients, [Ldss.vector.solutions.intervention.monitor.IPTANCVisitDTO;:visits, [Ldss.vector.solutions.intervention.monitor.IPTDoseDTO;:doses, [Ldss.vector.solutions.intervention.monitor.IPTTreatmentDTO;:treatments", post=true)
  public void create(dss.vector.solutions.intervention.monitor.AggregatedIPTViewDTO dto, dss.vector.solutions.intervention.monitor.IPTPatientsDTO[] patients, dss.vector.solutions.intervention.monitor.IPTANCVisitDTO[] visits, dss.vector.solutions.intervention.monitor.IPTDoseDTO[] doses, dss.vector.solutions.intervention.monitor.IPTTreatmentDTO[] treatments) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.intervention.monitor.AggregatedIPTController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.intervention.monitor.AggregatedIPTController.create");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="dss.vector.solutions.intervention.monitor.AggregatedIPTViewDTO:dto, [Ldss.vector.solutions.intervention.monitor.IPTPatientsDTO;:patients, [Ldss.vector.solutions.intervention.monitor.IPTANCVisitDTO;:visits, [Ldss.vector.solutions.intervention.monitor.IPTDoseDTO;:doses, [Ldss.vector.solutions.intervention.monitor.IPTTreatmentDTO;:treatments", post=true)
  public void failCreate(dss.vector.solutions.intervention.monitor.AggregatedIPTViewDTO dto, dss.vector.solutions.intervention.monitor.IPTPatientsDTO[] patients, dss.vector.solutions.intervention.monitor.IPTANCVisitDTO[] visits, dss.vector.solutions.intervention.monitor.IPTDoseDTO[] doses, dss.vector.solutions.intervention.monitor.IPTTreatmentDTO[] treatments) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.intervention.monitor.AggregatedIPTController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.intervention.monitor.AggregatedIPTController.failCreate");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="dss.vector.solutions.intervention.monitor.AggregatedIPTViewDTO:dto", post=true)
  public void delete(dss.vector.solutions.intervention.monitor.AggregatedIPTViewDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.intervention.monitor.AggregatedIPTController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.intervention.monitor.AggregatedIPTController.delete");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="dss.vector.solutions.intervention.monitor.AggregatedIPTViewDTO:dto", post=true)
  public void failDelete(dss.vector.solutions.intervention.monitor.AggregatedIPTViewDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.intervention.monitor.AggregatedIPTController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.intervention.monitor.AggregatedIPTController.failDelete");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="java.lang.String:id", post=false)
  public void edit(java.lang.String id) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.intervention.monitor.AggregatedIPTController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.intervention.monitor.AggregatedIPTController.edit");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="java.lang.String:id", post=false)
  public void failEdit(java.lang.String id) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.intervention.monitor.AggregatedIPTController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.intervention.monitor.AggregatedIPTController.failEdit");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="", post=false)
  public void newInstance() throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.intervention.monitor.AggregatedIPTController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.intervention.monitor.AggregatedIPTController.newInstance");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="", post=false)
  public void failNewInstance() throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.intervention.monitor.AggregatedIPTController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.intervention.monitor.AggregatedIPTController.failNewInstance");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="", post=false)
  public void search() throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.intervention.monitor.AggregatedIPTController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.intervention.monitor.AggregatedIPTController.search");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="", post=false)
  public void failSearch() throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.intervention.monitor.AggregatedIPTController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.intervention.monitor.AggregatedIPTController.failSearch");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="java.lang.String:geoId, java.lang.String:periodType, java.lang.Integer:period, java.lang.Integer:year", post=true)
  public void searchByGeoIdAndPeriod(java.lang.String geoId, java.lang.String periodType, java.lang.Integer period, java.lang.Integer year) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.intervention.monitor.AggregatedIPTController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.intervention.monitor.AggregatedIPTController.searchByGeoIdAndPeriod");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="java.lang.String:geoId, java.lang.String:periodType, java.lang.String:period, java.lang.String:year", post=true)
  public void failSearchByGeoIdAndPeriod(java.lang.String geoId, java.lang.String periodType, java.lang.String period, java.lang.String year) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.intervention.monitor.AggregatedIPTController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.intervention.monitor.AggregatedIPTController.failSearchByGeoIdAndPeriod");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="dss.vector.solutions.intervention.monitor.AggregatedIPTViewDTO:dto", post=true)
  public void searchByView(dss.vector.solutions.intervention.monitor.AggregatedIPTViewDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.intervention.monitor.AggregatedIPTController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.intervention.monitor.AggregatedIPTController.searchByView");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="dss.vector.solutions.intervention.monitor.AggregatedIPTViewDTO:dto", post=true)
  public void failSearchByView(dss.vector.solutions.intervention.monitor.AggregatedIPTViewDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.intervention.monitor.AggregatedIPTController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.intervention.monitor.AggregatedIPTController.failSearchByView");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="dss.vector.solutions.intervention.monitor.AggregatedIPTViewDTO:dto, [Ldss.vector.solutions.intervention.monitor.IPTPatientsDTO;:patients, [Ldss.vector.solutions.intervention.monitor.IPTANCVisitDTO;:visits, [Ldss.vector.solutions.intervention.monitor.IPTDoseDTO;:doses, [Ldss.vector.solutions.intervention.monitor.IPTTreatmentDTO;:treatments", post=true)
  public void update(dss.vector.solutions.intervention.monitor.AggregatedIPTViewDTO dto, dss.vector.solutions.intervention.monitor.IPTPatientsDTO[] patients, dss.vector.solutions.intervention.monitor.IPTANCVisitDTO[] visits, dss.vector.solutions.intervention.monitor.IPTDoseDTO[] doses, dss.vector.solutions.intervention.monitor.IPTTreatmentDTO[] treatments) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.intervention.monitor.AggregatedIPTController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.intervention.monitor.AggregatedIPTController.update");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="dss.vector.solutions.intervention.monitor.AggregatedIPTViewDTO:dto, [Ldss.vector.solutions.intervention.monitor.IPTPatientsDTO;:patients, [Ldss.vector.solutions.intervention.monitor.IPTANCVisitDTO;:visits, [Ldss.vector.solutions.intervention.monitor.IPTDoseDTO;:doses, [Ldss.vector.solutions.intervention.monitor.IPTTreatmentDTO;:treatments", post=true)
  public void failUpdate(dss.vector.solutions.intervention.monitor.AggregatedIPTViewDTO dto, dss.vector.solutions.intervention.monitor.IPTPatientsDTO[] patients, dss.vector.solutions.intervention.monitor.IPTANCVisitDTO[] visits, dss.vector.solutions.intervention.monitor.IPTDoseDTO[] doses, dss.vector.solutions.intervention.monitor.IPTTreatmentDTO[] treatments) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.intervention.monitor.AggregatedIPTController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.intervention.monitor.AggregatedIPTController.failUpdate");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="java.lang.String:id", post=false)
  public void view(java.lang.String id) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.intervention.monitor.AggregatedIPTController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.intervention.monitor.AggregatedIPTController.view");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="java.lang.String:id", post=false)
  public void failView(java.lang.String id) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.intervention.monitor.AggregatedIPTController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.intervention.monitor.AggregatedIPTController.failView");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="", post=false)
  public void viewAll() throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.intervention.monitor.AggregatedIPTController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.intervention.monitor.AggregatedIPTController.viewAll");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="", post=false)
  public void failViewAll() throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.intervention.monitor.AggregatedIPTController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.intervention.monitor.AggregatedIPTController.failViewAll");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="java.lang.String:sortAttribute, java.lang.Boolean:isAscending, java.lang.Integer:pageSize, java.lang.Integer:pageNumber", post=false)
  public void viewPage(java.lang.String sortAttribute, java.lang.Boolean isAscending, java.lang.Integer pageSize, java.lang.Integer pageNumber) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.intervention.monitor.AggregatedIPTController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.intervention.monitor.AggregatedIPTController.viewPage");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="java.lang.String:sortAttribute, java.lang.String:isAscending, java.lang.String:pageSize, java.lang.String:pageNumber", post=false)
  public void failViewPage(java.lang.String sortAttribute, java.lang.String isAscending, java.lang.String pageSize, java.lang.String pageNumber) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.intervention.monitor.AggregatedIPTController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.intervention.monitor.AggregatedIPTController.failViewPage");
  }
  
}
