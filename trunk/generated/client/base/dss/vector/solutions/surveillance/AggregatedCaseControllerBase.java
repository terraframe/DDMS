package dss.vector.solutions.surveillance;

@com.runwaysdk.business.ClassSignature(hash = -1194479199)
public class AggregatedCaseControllerBase implements com.runwaysdk.generation.loader.Reloadable
{
  public static final String CLASS = "dss.vector.solutions.surveillance.AggregatedCaseController";
  protected javax.servlet.http.HttpServletRequest req;
  protected javax.servlet.http.HttpServletResponse resp;
  protected java.lang.Boolean isAsynchronous;
  protected java.lang.String dir;
  protected java.lang.String layout;
  
  private static final long serialVersionUID = -1194479199;
  
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
  
  public com.runwaysdk.constants.ClientRequestIF getClientRequest()
  {
    return (com.runwaysdk.constants.ClientRequestIF) req.getAttribute(com.runwaysdk.constants.ClientConstants.CLIENTREQUEST);
  }
  
  public com.runwaysdk.ClientSession getClientSession()
  {
    return (com.runwaysdk.ClientSession) req.getSession().getAttribute(com.runwaysdk.constants.ClientConstants.CLIENTSESSION);
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="dss.vector.solutions.surveillance.AggregatedCaseViewDTO:dto", post=true)
  public void cancel(dss.vector.solutions.surveillance.AggregatedCaseViewDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.surveillance.AggregatedCaseController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.surveillance.AggregatedCaseController.cancel");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="dss.vector.solutions.surveillance.AggregatedCaseViewDTO:dto", post=true)
  public void failCancel(dss.vector.solutions.surveillance.AggregatedCaseViewDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.surveillance.AggregatedCaseController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.surveillance.AggregatedCaseController.failCancel");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="dss.vector.solutions.surveillance.AggregatedCaseViewDTO:dto, [Ldss.vector.solutions.surveillance.CaseTreatmentDTO;:treatments, [Ldss.vector.solutions.surveillance.CaseTreatmentMethodDTO;:treatmentMethods, [Ldss.vector.solutions.surveillance.CaseTreatmentStockDTO;:stock, [Ldss.vector.solutions.surveillance.CaseDiagnosticDTO;:diagnosticMethods, [Ldss.vector.solutions.surveillance.CaseReferralDTO;:referrals", post=true)
  public void create(dss.vector.solutions.surveillance.AggregatedCaseViewDTO dto, dss.vector.solutions.surveillance.CaseTreatmentDTO[] treatments, dss.vector.solutions.surveillance.CaseTreatmentMethodDTO[] treatmentMethods, dss.vector.solutions.surveillance.CaseTreatmentStockDTO[] stock, dss.vector.solutions.surveillance.CaseDiagnosticDTO[] diagnosticMethods, dss.vector.solutions.surveillance.CaseReferralDTO[] referrals) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.surveillance.AggregatedCaseController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.surveillance.AggregatedCaseController.create");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="dss.vector.solutions.surveillance.AggregatedCaseViewDTO:dto, [Ldss.vector.solutions.surveillance.CaseTreatmentDTO;:treatments, [Ldss.vector.solutions.surveillance.CaseTreatmentMethodDTO;:treatmentMethods, [Ldss.vector.solutions.surveillance.CaseTreatmentStockDTO;:stock, [Ldss.vector.solutions.surveillance.CaseDiagnosticDTO;:diagnosticMethods, [Ldss.vector.solutions.surveillance.CaseReferralDTO;:referrals", post=true)
  public void failCreate(dss.vector.solutions.surveillance.AggregatedCaseViewDTO dto, dss.vector.solutions.surveillance.CaseTreatmentDTO[] treatments, dss.vector.solutions.surveillance.CaseTreatmentMethodDTO[] treatmentMethods, dss.vector.solutions.surveillance.CaseTreatmentStockDTO[] stock, dss.vector.solutions.surveillance.CaseDiagnosticDTO[] diagnosticMethods, dss.vector.solutions.surveillance.CaseReferralDTO[] referrals) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.surveillance.AggregatedCaseController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.surveillance.AggregatedCaseController.failCreate");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="dss.vector.solutions.surveillance.AggregatedCaseViewDTO:dto", post=true)
  public void delete(dss.vector.solutions.surveillance.AggregatedCaseViewDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.surveillance.AggregatedCaseController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.surveillance.AggregatedCaseController.delete");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="dss.vector.solutions.surveillance.AggregatedCaseViewDTO:dto", post=true)
  public void failDelete(dss.vector.solutions.surveillance.AggregatedCaseViewDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.surveillance.AggregatedCaseController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.surveillance.AggregatedCaseController.failDelete");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="java.lang.String:id", post=false)
  public void edit(java.lang.String id) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.surveillance.AggregatedCaseController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.surveillance.AggregatedCaseController.edit");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="java.lang.String:id", post=false)
  public void failEdit(java.lang.String id) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.surveillance.AggregatedCaseController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.surveillance.AggregatedCaseController.failEdit");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="dss.vector.solutions.surveillance.AggregatedAgeGroupDTO:ageGroup", post=true)
  public void newInstance(dss.vector.solutions.surveillance.AggregatedAgeGroupDTO ageGroup) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.surveillance.AggregatedCaseController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.surveillance.AggregatedCaseController.newInstance");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="dss.vector.solutions.surveillance.AggregatedAgeGroupDTO:ageGroup", post=true)
  public void failNewInstance(dss.vector.solutions.surveillance.AggregatedAgeGroupDTO ageGroup) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.surveillance.AggregatedCaseController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.surveillance.AggregatedCaseController.failNewInstance");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="", post=false)
  public void search() throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.surveillance.AggregatedCaseController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.surveillance.AggregatedCaseController.search");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="", post=false)
  public void failSearch() throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.surveillance.AggregatedCaseController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.surveillance.AggregatedCaseController.failSearch");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="java.lang.String:geoId, java.lang.String:periodType, java.lang.Integer:period, java.lang.Integer:year, dss.vector.solutions.surveillance.AggregatedAgeGroupDTO:ageGroup", post=true)
  public void searchByGeoIdAndEpiWeek(java.lang.String geoId, java.lang.String periodType, java.lang.Integer period, java.lang.Integer year, dss.vector.solutions.surveillance.AggregatedAgeGroupDTO ageGroup) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.surveillance.AggregatedCaseController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.surveillance.AggregatedCaseController.searchByGeoIdAndEpiWeek");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="java.lang.String:geoId, java.lang.String:periodType, java.lang.String:period, java.lang.String:year, dss.vector.solutions.surveillance.AggregatedAgeGroupDTO:ageGroup", post=true)
  public void failSearchByGeoIdAndEpiWeek(java.lang.String geoId, java.lang.String periodType, java.lang.String period, java.lang.String year, dss.vector.solutions.surveillance.AggregatedAgeGroupDTO ageGroup) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.surveillance.AggregatedCaseController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.surveillance.AggregatedCaseController.failSearchByGeoIdAndEpiWeek");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="dss.vector.solutions.surveillance.AggregatedCaseSearchViewDTO:dto", post=true)
  public void searchByView(dss.vector.solutions.surveillance.AggregatedCaseSearchViewDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.surveillance.AggregatedCaseController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.surveillance.AggregatedCaseController.searchByView");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="dss.vector.solutions.surveillance.AggregatedCaseSearchViewDTO:dto", post=true)
  public void failSearchByView(dss.vector.solutions.surveillance.AggregatedCaseSearchViewDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.surveillance.AggregatedCaseController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.surveillance.AggregatedCaseController.failSearchByView");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="java.lang.String:geoId, java.lang.String:periodType, java.lang.Integer:period, java.lang.Integer:year", post=false)
  public void selectAgeGroup(java.lang.String geoId, java.lang.String periodType, java.lang.Integer period, java.lang.Integer year) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.surveillance.AggregatedCaseController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.surveillance.AggregatedCaseController.selectAgeGroup");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="java.lang.String:geoId, java.lang.String:periodType, java.lang.String:period, java.lang.String:year", post=false)
  public void failSelectAgeGroup(java.lang.String geoId, java.lang.String periodType, java.lang.String period, java.lang.String year) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.surveillance.AggregatedCaseController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.surveillance.AggregatedCaseController.failSelectAgeGroup");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="dss.vector.solutions.surveillance.AggregatedCaseViewDTO:dto, [Ldss.vector.solutions.surveillance.CaseTreatmentDTO;:treatments, [Ldss.vector.solutions.surveillance.CaseTreatmentMethodDTO;:treatmentMethods, [Ldss.vector.solutions.surveillance.CaseTreatmentStockDTO;:stock, [Ldss.vector.solutions.surveillance.CaseDiagnosticDTO;:diagnosticMethods, [Ldss.vector.solutions.surveillance.CaseReferralDTO;:referrals", post=true)
  public void update(dss.vector.solutions.surveillance.AggregatedCaseViewDTO dto, dss.vector.solutions.surveillance.CaseTreatmentDTO[] treatments, dss.vector.solutions.surveillance.CaseTreatmentMethodDTO[] treatmentMethods, dss.vector.solutions.surveillance.CaseTreatmentStockDTO[] stock, dss.vector.solutions.surveillance.CaseDiagnosticDTO[] diagnosticMethods, dss.vector.solutions.surveillance.CaseReferralDTO[] referrals) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.surveillance.AggregatedCaseController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.surveillance.AggregatedCaseController.update");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="dss.vector.solutions.surveillance.AggregatedCaseViewDTO:dto, [Ldss.vector.solutions.surveillance.CaseTreatmentDTO;:treatments, [Ldss.vector.solutions.surveillance.CaseTreatmentMethodDTO;:treatmentMethods, [Ldss.vector.solutions.surveillance.CaseTreatmentStockDTO;:stock, [Ldss.vector.solutions.surveillance.CaseDiagnosticDTO;:diagnosticMethods, [Ldss.vector.solutions.surveillance.CaseReferralDTO;:referrals", post=true)
  public void failUpdate(dss.vector.solutions.surveillance.AggregatedCaseViewDTO dto, dss.vector.solutions.surveillance.CaseTreatmentDTO[] treatments, dss.vector.solutions.surveillance.CaseTreatmentMethodDTO[] treatmentMethods, dss.vector.solutions.surveillance.CaseTreatmentStockDTO[] stock, dss.vector.solutions.surveillance.CaseDiagnosticDTO[] diagnosticMethods, dss.vector.solutions.surveillance.CaseReferralDTO[] referrals) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.surveillance.AggregatedCaseController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.surveillance.AggregatedCaseController.failUpdate");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="java.lang.String:id", post=false)
  public void view(java.lang.String id) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.surveillance.AggregatedCaseController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.surveillance.AggregatedCaseController.view");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="java.lang.String:id", post=false)
  public void failView(java.lang.String id) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.surveillance.AggregatedCaseController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.surveillance.AggregatedCaseController.failView");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="", post=false)
  public void viewAll() throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.surveillance.AggregatedCaseController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.surveillance.AggregatedCaseController.viewAll");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="", post=false)
  public void failViewAll() throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.surveillance.AggregatedCaseController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.surveillance.AggregatedCaseController.failViewAll");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="java.lang.String:sortAttribute, java.lang.Boolean:isAscending, java.lang.Integer:pageSize, java.lang.Integer:pageNumber", post=false)
  public void viewPage(java.lang.String sortAttribute, java.lang.Boolean isAscending, java.lang.Integer pageSize, java.lang.Integer pageNumber) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.surveillance.AggregatedCaseController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.surveillance.AggregatedCaseController.viewPage");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="java.lang.String:sortAttribute, java.lang.String:isAscending, java.lang.String:pageSize, java.lang.String:pageNumber", post=false)
  public void failViewPage(java.lang.String sortAttribute, java.lang.String isAscending, java.lang.String pageSize, java.lang.String pageNumber) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.surveillance.AggregatedCaseController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.surveillance.AggregatedCaseController.failViewPage");
  }
  
}
