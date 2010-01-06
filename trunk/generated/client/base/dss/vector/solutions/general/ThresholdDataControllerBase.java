package dss.vector.solutions.general;

@com.terraframe.mojo.business.ClassSignature(hash = 463226895)
public class ThresholdDataControllerBase implements com.terraframe.mojo.generation.loader.Reloadable
{
  public static final String CLASS = "dss.vector.solutions.general.ThresholdDataController";
  protected javax.servlet.http.HttpServletRequest req;
  protected javax.servlet.http.HttpServletResponse resp;
  protected java.lang.Boolean isAsynchronous;
  protected java.lang.String dir;
  protected java.lang.String layout;
  
  private static final long serialVersionUID = 463226895;
  
  public ThresholdDataControllerBase(javax.servlet.http.HttpServletRequest req, javax.servlet.http.HttpServletResponse resp, java.lang.Boolean isAsynchronous)
  {
    this(req, resp, isAsynchronous, "","");
  }
  
  public ThresholdDataControllerBase(javax.servlet.http.HttpServletRequest req, javax.servlet.http.HttpServletResponse resp, java.lang.Boolean isAsynchronous, java.lang.String dir, java.lang.String layout)
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
  
  @com.terraframe.mojo.controller.ActionParameters(parameters="dss.vector.solutions.general.ThresholdCalculationTypeViewDTO:thresholdCalculation, java.lang.Boolean:currentYear", post=true)
  public void calculateFacilityThresholds(dss.vector.solutions.general.ThresholdCalculationTypeViewDTO thresholdCalculation, java.lang.Boolean currentYear) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.general.ThresholdDataController.java";
    throw new com.terraframe.mojo.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.general.ThresholdDataController.calculateFacilityThresholds");
  }
  
  @com.terraframe.mojo.controller.ActionParameters(parameters="dss.vector.solutions.general.ThresholdCalculationTypeViewDTO:thresholdCalculation, java.lang.String:currentYear", post=true)
  public void failCalculateFacilityThresholds(dss.vector.solutions.general.ThresholdCalculationTypeViewDTO thresholdCalculation, java.lang.String currentYear) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.general.ThresholdDataController.java";
    throw new com.terraframe.mojo.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.general.ThresholdDataController.failCalculateFacilityThresholds");
  }
  
  @com.terraframe.mojo.controller.ActionParameters(parameters="dss.vector.solutions.general.ThresholdCalculationTypeViewDTO:thresholdCalculation, java.lang.Boolean:currentYear", post=true)
  public void calculatePoliticalThresholds(dss.vector.solutions.general.ThresholdCalculationTypeViewDTO thresholdCalculation, java.lang.Boolean currentYear) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.general.ThresholdDataController.java";
    throw new com.terraframe.mojo.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.general.ThresholdDataController.calculatePoliticalThresholds");
  }
  
  @com.terraframe.mojo.controller.ActionParameters(parameters="dss.vector.solutions.general.ThresholdCalculationTypeViewDTO:thresholdCalculation, java.lang.String:currentYear", post=true)
  public void failCalculatePoliticalThresholds(dss.vector.solutions.general.ThresholdCalculationTypeViewDTO thresholdCalculation, java.lang.String currentYear) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.general.ThresholdDataController.java";
    throw new com.terraframe.mojo.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.general.ThresholdDataController.failCalculatePoliticalThresholds");
  }
  
  @com.terraframe.mojo.controller.ActionParameters(parameters="dss.vector.solutions.general.ThresholdCalculationTypeViewDTO:thresholdCalculation, java.lang.Boolean:currentYear", post=true)
  public void calculateThresholds(dss.vector.solutions.general.ThresholdCalculationTypeViewDTO thresholdCalculation, java.lang.Boolean currentYear) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.general.ThresholdDataController.java";
    throw new com.terraframe.mojo.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.general.ThresholdDataController.calculateThresholds");
  }
  
  @com.terraframe.mojo.controller.ActionParameters(parameters="dss.vector.solutions.general.ThresholdCalculationTypeViewDTO:thresholdCalculation, java.lang.String:currentYear", post=true)
  public void failCalculateThresholds(dss.vector.solutions.general.ThresholdCalculationTypeViewDTO thresholdCalculation, java.lang.String currentYear) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.general.ThresholdDataController.java";
    throw new com.terraframe.mojo.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.general.ThresholdDataController.failCalculateThresholds");
  }
  
  @com.terraframe.mojo.controller.ActionParameters(parameters="", post=false)
  public void editThresholdConfiguration() throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.general.ThresholdDataController.java";
    throw new com.terraframe.mojo.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.general.ThresholdDataController.editThresholdConfiguration");
  }
  
  @com.terraframe.mojo.controller.ActionParameters(parameters="", post=false)
  public void failEditThresholdConfiguration() throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.general.ThresholdDataController.java";
    throw new com.terraframe.mojo.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.general.ThresholdDataController.failEditThresholdConfiguration");
  }
  
  @com.terraframe.mojo.controller.ActionParameters(parameters="[Ldss.vector.solutions.general.ThresholdDataViewDTO;:views", post=true)
  public void exportThresholdData(dss.vector.solutions.general.ThresholdDataViewDTO[] views) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.general.ThresholdDataController.java";
    throw new com.terraframe.mojo.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.general.ThresholdDataController.exportThresholdData");
  }
  
  @com.terraframe.mojo.controller.ActionParameters(parameters="[Ldss.vector.solutions.general.ThresholdDataViewDTO;:views", post=true)
  public void failExportThresholdData(dss.vector.solutions.general.ThresholdDataViewDTO[] views) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.general.ThresholdDataController.java";
    throw new com.terraframe.mojo.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.general.ThresholdDataController.failExportThresholdData");
  }
  
  @com.terraframe.mojo.controller.ActionParameters(parameters="", post=false)
  public void search() throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.general.ThresholdDataController.java";
    throw new com.terraframe.mojo.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.general.ThresholdDataController.search");
  }
  
  @com.terraframe.mojo.controller.ActionParameters(parameters="", post=false)
  public void failSearch() throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.general.ThresholdDataController.java";
    throw new com.terraframe.mojo.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.general.ThresholdDataController.failSearch");
  }
  
  @com.terraframe.mojo.controller.ActionParameters(parameters="java.lang.String:geoId, dss.vector.solutions.general.MalariaSeasonDTO:season, java.lang.Boolean:thresholdType", post=true)
  public void searchForThresholdData(java.lang.String geoId, dss.vector.solutions.general.MalariaSeasonDTO season, java.lang.Boolean thresholdType) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.general.ThresholdDataController.java";
    throw new com.terraframe.mojo.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.general.ThresholdDataController.searchForThresholdData");
  }
  
  @com.terraframe.mojo.controller.ActionParameters(parameters="java.lang.String:geoId, dss.vector.solutions.general.MalariaSeasonDTO:season, java.lang.String:thresholdType", post=true)
  public void failSearchForThresholdData(java.lang.String geoId, dss.vector.solutions.general.MalariaSeasonDTO season, java.lang.String thresholdType) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.general.ThresholdDataController.java";
    throw new com.terraframe.mojo.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.general.ThresholdDataController.failSearchForThresholdData");
  }
  
  @com.terraframe.mojo.controller.ActionParameters(parameters="java.lang.String:universal, java.lang.String:calulationMethod, dss.vector.solutions.general.ThresholdCalculationTypeViewDTO:thresholdCalculation", post=true)
  public void setThresholdConfiguration(java.lang.String universal, java.lang.String calulationMethod, dss.vector.solutions.general.ThresholdCalculationTypeViewDTO thresholdCalculation) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.general.ThresholdDataController.java";
    throw new com.terraframe.mojo.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.general.ThresholdDataController.setThresholdConfiguration");
  }
  
  @com.terraframe.mojo.controller.ActionParameters(parameters="java.lang.String:universal, java.lang.String:calulationMethod, dss.vector.solutions.general.ThresholdCalculationTypeViewDTO:thresholdCalculation", post=true)
  public void failSetThresholdConfiguration(java.lang.String universal, java.lang.String calulationMethod, dss.vector.solutions.general.ThresholdCalculationTypeViewDTO thresholdCalculation) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.general.ThresholdDataController.java";
    throw new com.terraframe.mojo.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.general.ThresholdDataController.failSetThresholdConfiguration");
  }
  
}
