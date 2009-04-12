package dss.vector.solutions.surveillance;

public class AggregatedCaseControllerBase implements com.terraframe.mojo.generation.loader.Reloadable
{
  public static final String CLASS = "dss.vector.solutions.surveillance.AggregatedCaseController";
  protected javax.servlet.http.HttpServletRequest req;
  protected javax.servlet.http.HttpServletResponse resp;
  protected java.lang.Boolean isAsynchronous;
  protected java.lang.String dir;
  protected java.lang.String layout;
  
  private static final long serialVersionUID = 1239517558322L;
  
  public AggregatedCaseControllerBase(javax.servlet.http.HttpServletRequest req, javax.servlet.http.HttpServletResponse resp, java.lang.Boolean isAsynchronous)
  {
    this(req, resp, isAsynchronous, "","");
  }
  
  public AggregatedCaseControllerBase(javax.servlet.http.HttpServletRequest req, javax.servlet.http.HttpServletResponse resp, java.lang.Boolean isAsynchronous, java.lang.String dir, java.lang.String layout)
  {
    this.req = req;
    this.resp = resp;
    this.isAsynchronous = isAsynchronous;
    this.dir = dir;
    this.layout = layout;
  }
  
  protected void render(String jsp) throws java.io.IOException, javax.servlet.ServletException
  {
    if(this.isAsynchronous())
    {
      req.getRequestDispatcher(dir+jsp).forward(req, resp);
    }
    else
    {
      req.setAttribute("jsp", jsp);
      req.getRequestDispatcher(layout).forward(req, resp);
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
  
  @com.terraframe.mojo.controller.ActionParameters(parameters="dss.vector.solutions.surveillance.AggregatedCaseViewDTO:dto", post=true)
  public void delete(dss.vector.solutions.surveillance.AggregatedCaseViewDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.surveillance.AggregatedCaseController.java";
    throw new com.terraframe.mojo.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.surveillance.AggregatedCaseController.delete");
  }
  
  @com.terraframe.mojo.controller.ActionParameters(parameters="dss.vector.solutions.surveillance.AggregatedCaseViewDTO:dto", post=true)
  public void failDelete(dss.vector.solutions.surveillance.AggregatedCaseViewDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.surveillance.AggregatedCaseController.java";
    throw new com.terraframe.mojo.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.surveillance.AggregatedCaseController.failDelete");
  }
  
  @com.terraframe.mojo.controller.ActionParameters(parameters="", post=false)
  public void viewAll() throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.surveillance.AggregatedCaseController.java";
    throw new com.terraframe.mojo.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.surveillance.AggregatedCaseController.viewAll");
  }
  
  @com.terraframe.mojo.controller.ActionParameters(parameters="", post=false)
  public void failViewAll() throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.surveillance.AggregatedCaseController.java";
    throw new com.terraframe.mojo.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.surveillance.AggregatedCaseController.failViewAll");
  }
  
  @com.terraframe.mojo.controller.ActionParameters(parameters="java.lang.String:id", post=false)
  public void edit(java.lang.String id) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.surveillance.AggregatedCaseController.java";
    throw new com.terraframe.mojo.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.surveillance.AggregatedCaseController.edit");
  }
  
  @com.terraframe.mojo.controller.ActionParameters(parameters="java.lang.String:id", post=false)
  public void failEdit(java.lang.String id) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.surveillance.AggregatedCaseController.java";
    throw new com.terraframe.mojo.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.surveillance.AggregatedCaseController.failEdit");
  }
  
  @com.terraframe.mojo.controller.ActionParameters(parameters="dss.vector.solutions.surveillance.AggregatedCaseViewDTO:dto, [Ldss.vector.solutions.surveillance.CaseTreatmentDTO;:treatments, [Ldss.vector.solutions.surveillance.CaseTreatmentMethodDTO;:treatmentMethods, [Ldss.vector.solutions.surveillance.CaseTreatmentStockDTO;:stock, [Ldss.vector.solutions.surveillance.CaseDiagnosticDTO;:diagnosticMethods, [Ldss.vector.solutions.surveillance.CaseReferralDTO;:referrals", post=true)
  public void create(dss.vector.solutions.surveillance.AggregatedCaseViewDTO dto, dss.vector.solutions.surveillance.CaseTreatmentDTO[] treatments, dss.vector.solutions.surveillance.CaseTreatmentMethodDTO[] treatmentMethods, dss.vector.solutions.surveillance.CaseTreatmentStockDTO[] stock, dss.vector.solutions.surveillance.CaseDiagnosticDTO[] diagnosticMethods, dss.vector.solutions.surveillance.CaseReferralDTO[] referrals) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.surveillance.AggregatedCaseController.java";
    throw new com.terraframe.mojo.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.surveillance.AggregatedCaseController.create");
  }
  
  @com.terraframe.mojo.controller.ActionParameters(parameters="dss.vector.solutions.surveillance.AggregatedCaseViewDTO:dto, [Ldss.vector.solutions.surveillance.CaseTreatmentDTO;:treatments, [Ldss.vector.solutions.surveillance.CaseTreatmentMethodDTO;:treatmentMethods, [Ldss.vector.solutions.surveillance.CaseTreatmentStockDTO;:stock, [Ldss.vector.solutions.surveillance.CaseDiagnosticDTO;:diagnosticMethods, [Ldss.vector.solutions.surveillance.CaseReferralDTO;:referrals", post=true)
  public void failCreate(dss.vector.solutions.surveillance.AggregatedCaseViewDTO dto, dss.vector.solutions.surveillance.CaseTreatmentDTO[] treatments, dss.vector.solutions.surveillance.CaseTreatmentMethodDTO[] treatmentMethods, dss.vector.solutions.surveillance.CaseTreatmentStockDTO[] stock, dss.vector.solutions.surveillance.CaseDiagnosticDTO[] diagnosticMethods, dss.vector.solutions.surveillance.CaseReferralDTO[] referrals) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.surveillance.AggregatedCaseController.java";
    throw new com.terraframe.mojo.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.surveillance.AggregatedCaseController.failCreate");
  }
  
  @com.terraframe.mojo.controller.ActionParameters(parameters="java.lang.String:geoId, java.lang.String:periodType, java.lang.Integer:period, java.lang.String:year, dss.vector.solutions.surveillance.AggregatedAgeGroupDTO:ageGroup", post=true)
  public void searchByGeoIdAndEpiWeek(java.lang.String geoId, java.lang.String periodType, java.lang.Integer period, java.lang.String year, dss.vector.solutions.surveillance.AggregatedAgeGroupDTO ageGroup) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.surveillance.AggregatedCaseController.java";
    throw new com.terraframe.mojo.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.surveillance.AggregatedCaseController.searchByGeoIdAndEpiWeek");
  }
  
  @com.terraframe.mojo.controller.ActionParameters(parameters="java.lang.String:geoId, java.lang.String:periodType, java.lang.String:period, java.lang.String:year, dss.vector.solutions.surveillance.AggregatedAgeGroupDTO:ageGroup", post=true)
  public void failSearchByGeoIdAndEpiWeek(java.lang.String geoId, java.lang.String periodType, java.lang.String period, java.lang.String year, dss.vector.solutions.surveillance.AggregatedAgeGroupDTO ageGroup) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.surveillance.AggregatedCaseController.java";
    throw new com.terraframe.mojo.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.surveillance.AggregatedCaseController.failSearchByGeoIdAndEpiWeek");
  }
  
  @com.terraframe.mojo.controller.ActionParameters(parameters="dss.vector.solutions.surveillance.AggregatedCaseViewDTO:dto, [Ldss.vector.solutions.surveillance.CaseTreatmentDTO;:treatments, [Ldss.vector.solutions.surveillance.CaseTreatmentMethodDTO;:treatmentMethods, [Ldss.vector.solutions.surveillance.CaseTreatmentStockDTO;:stock, [Ldss.vector.solutions.surveillance.CaseDiagnosticDTO;:diagnosticMethods, [Ldss.vector.solutions.surveillance.CaseReferralDTO;:referrals", post=true)
  public void update(dss.vector.solutions.surveillance.AggregatedCaseViewDTO dto, dss.vector.solutions.surveillance.CaseTreatmentDTO[] treatments, dss.vector.solutions.surveillance.CaseTreatmentMethodDTO[] treatmentMethods, dss.vector.solutions.surveillance.CaseTreatmentStockDTO[] stock, dss.vector.solutions.surveillance.CaseDiagnosticDTO[] diagnosticMethods, dss.vector.solutions.surveillance.CaseReferralDTO[] referrals) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.surveillance.AggregatedCaseController.java";
    throw new com.terraframe.mojo.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.surveillance.AggregatedCaseController.update");
  }
  
  @com.terraframe.mojo.controller.ActionParameters(parameters="dss.vector.solutions.surveillance.AggregatedCaseViewDTO:dto, [Ldss.vector.solutions.surveillance.CaseTreatmentDTO;:treatments, [Ldss.vector.solutions.surveillance.CaseTreatmentMethodDTO;:treatmentMethods, [Ldss.vector.solutions.surveillance.CaseTreatmentStockDTO;:stock, [Ldss.vector.solutions.surveillance.CaseDiagnosticDTO;:diagnosticMethods, [Ldss.vector.solutions.surveillance.CaseReferralDTO;:referrals", post=true)
  public void failUpdate(dss.vector.solutions.surveillance.AggregatedCaseViewDTO dto, dss.vector.solutions.surveillance.CaseTreatmentDTO[] treatments, dss.vector.solutions.surveillance.CaseTreatmentMethodDTO[] treatmentMethods, dss.vector.solutions.surveillance.CaseTreatmentStockDTO[] stock, dss.vector.solutions.surveillance.CaseDiagnosticDTO[] diagnosticMethods, dss.vector.solutions.surveillance.CaseReferralDTO[] referrals) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.surveillance.AggregatedCaseController.java";
    throw new com.terraframe.mojo.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.surveillance.AggregatedCaseController.failUpdate");
  }
  
  @com.terraframe.mojo.controller.ActionParameters(parameters="java.lang.String:id", post=false)
  public void view(java.lang.String id) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.surveillance.AggregatedCaseController.java";
    throw new com.terraframe.mojo.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.surveillance.AggregatedCaseController.view");
  }
  
  @com.terraframe.mojo.controller.ActionParameters(parameters="java.lang.String:id", post=false)
  public void failView(java.lang.String id) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.surveillance.AggregatedCaseController.java";
    throw new com.terraframe.mojo.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.surveillance.AggregatedCaseController.failView");
  }
  
  @com.terraframe.mojo.controller.ActionParameters(parameters="java.lang.String:sortAttribute, java.lang.Boolean:isAscending, java.lang.Integer:pageSize, java.lang.Integer:pageNumber", post=false)
  public void viewPage(java.lang.String sortAttribute, java.lang.Boolean isAscending, java.lang.Integer pageSize, java.lang.Integer pageNumber) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.surveillance.AggregatedCaseController.java";
    throw new com.terraframe.mojo.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.surveillance.AggregatedCaseController.viewPage");
  }
  
  @com.terraframe.mojo.controller.ActionParameters(parameters="java.lang.String:sortAttribute, java.lang.String:isAscending, java.lang.String:pageSize, java.lang.String:pageNumber", post=false)
  public void failViewPage(java.lang.String sortAttribute, java.lang.String isAscending, java.lang.String pageSize, java.lang.String pageNumber) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.surveillance.AggregatedCaseController.java";
    throw new com.terraframe.mojo.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.surveillance.AggregatedCaseController.failViewPage");
  }
  
  @com.terraframe.mojo.controller.ActionParameters(parameters="dss.vector.solutions.surveillance.AggregatedCaseViewDTO:dto", post=true)
  public void cancel(dss.vector.solutions.surveillance.AggregatedCaseViewDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.surveillance.AggregatedCaseController.java";
    throw new com.terraframe.mojo.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.surveillance.AggregatedCaseController.cancel");
  }
  
  @com.terraframe.mojo.controller.ActionParameters(parameters="dss.vector.solutions.surveillance.AggregatedCaseViewDTO:dto", post=true)
  public void failCancel(dss.vector.solutions.surveillance.AggregatedCaseViewDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.surveillance.AggregatedCaseController.java";
    throw new com.terraframe.mojo.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.surveillance.AggregatedCaseController.failCancel");
  }
  
  @com.terraframe.mojo.controller.ActionParameters(parameters="", post=false)
  public void search() throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.surveillance.AggregatedCaseController.java";
    throw new com.terraframe.mojo.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.surveillance.AggregatedCaseController.search");
  }
  
  @com.terraframe.mojo.controller.ActionParameters(parameters="", post=false)
  public void failSearch() throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.surveillance.AggregatedCaseController.java";
    throw new com.terraframe.mojo.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.surveillance.AggregatedCaseController.failSearch");
  }
  
  @com.terraframe.mojo.controller.ActionParameters(parameters="dss.vector.solutions.surveillance.AggregatedAgeGroupDTO:ageGroup", post=true)
  public void newInstance(dss.vector.solutions.surveillance.AggregatedAgeGroupDTO ageGroup) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.surveillance.AggregatedCaseController.java";
    throw new com.terraframe.mojo.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.surveillance.AggregatedCaseController.newInstance");
  }
  
  @com.terraframe.mojo.controller.ActionParameters(parameters="dss.vector.solutions.surveillance.AggregatedAgeGroupDTO:ageGroup", post=true)
  public void failNewInstance(dss.vector.solutions.surveillance.AggregatedAgeGroupDTO ageGroup) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.surveillance.AggregatedCaseController.java";
    throw new com.terraframe.mojo.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.surveillance.AggregatedCaseController.failNewInstance");
  }
  
}
