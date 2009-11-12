package dss.vector.solutions.irs;

@com.terraframe.mojo.business.ClassSignature(hash = -944822265)
public class InterventionPlanningControllerBase implements com.terraframe.mojo.generation.loader.Reloadable
{
  public static final String CLASS = "dss.vector.solutions.irs.InterventionPlanningController";
  protected javax.servlet.http.HttpServletRequest req;
  protected javax.servlet.http.HttpServletResponse resp;
  protected java.lang.Boolean isAsynchronous;
  protected java.lang.String dir;
  protected java.lang.String layout;
  
  private static final long serialVersionUID = -944822265;
  
  public InterventionPlanningControllerBase(javax.servlet.http.HttpServletRequest req, javax.servlet.http.HttpServletResponse resp, java.lang.Boolean isAsynchronous)
  {
    this(req, resp, isAsynchronous, "","");
  }
  
  public InterventionPlanningControllerBase(javax.servlet.http.HttpServletRequest req, javax.servlet.http.HttpServletResponse resp, java.lang.Boolean isAsynchronous, java.lang.String dir, java.lang.String layout)
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
  
  @com.terraframe.mojo.controller.ActionParameters(parameters="[Ldss.vector.solutions.irs.InsecticideInterventionPlanningViewDTO;:views", post=true)
  public void exportInsecticidePlanning(dss.vector.solutions.irs.InsecticideInterventionPlanningViewDTO[] views) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.irs.InterventionPlanningController.java";
    throw new com.terraframe.mojo.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.irs.InterventionPlanningController.exportInsecticidePlanning");
  }
  
  @com.terraframe.mojo.controller.ActionParameters(parameters="[Ldss.vector.solutions.irs.InsecticideInterventionPlanningViewDTO;:views", post=true)
  public void failExportInsecticidePlanning(dss.vector.solutions.irs.InsecticideInterventionPlanningViewDTO[] views) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.irs.InterventionPlanningController.java";
    throw new com.terraframe.mojo.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.irs.InterventionPlanningController.failExportInsecticidePlanning");
  }
  
  @com.terraframe.mojo.controller.ActionParameters(parameters="[Ldss.vector.solutions.irs.OperatorInterventionPlanningViewDTO;:views", post=true)
  public void exportOperatorPlanning(dss.vector.solutions.irs.OperatorInterventionPlanningViewDTO[] views) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.irs.InterventionPlanningController.java";
    throw new com.terraframe.mojo.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.irs.InterventionPlanningController.exportOperatorPlanning");
  }
  
  @com.terraframe.mojo.controller.ActionParameters(parameters="[Ldss.vector.solutions.irs.OperatorInterventionPlanningViewDTO;:views", post=true)
  public void failExportOperatorPlanning(dss.vector.solutions.irs.OperatorInterventionPlanningViewDTO[] views) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.irs.InterventionPlanningController.java";
    throw new com.terraframe.mojo.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.irs.InterventionPlanningController.failExportOperatorPlanning");
  }
  
  @com.terraframe.mojo.controller.ActionParameters(parameters="[Ldss.vector.solutions.irs.TimeInterventionPlanningViewDTO;:views", post=true)
  public void exportTimePlanning(dss.vector.solutions.irs.TimeInterventionPlanningViewDTO[] views) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.irs.InterventionPlanningController.java";
    throw new com.terraframe.mojo.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.irs.InterventionPlanningController.exportTimePlanning");
  }
  
  @com.terraframe.mojo.controller.ActionParameters(parameters="[Ldss.vector.solutions.irs.TimeInterventionPlanningViewDTO;:views", post=true)
  public void failExportTimePlanning(dss.vector.solutions.irs.TimeInterventionPlanningViewDTO[] views) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.irs.InterventionPlanningController.java";
    throw new com.terraframe.mojo.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.irs.InterventionPlanningController.failExportTimePlanning");
  }
  
  @com.terraframe.mojo.controller.ActionParameters(parameters="java.lang.String:option", post=false)
  public void search(java.lang.String option) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.irs.InterventionPlanningController.java";
    throw new com.terraframe.mojo.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.irs.InterventionPlanningController.search");
  }
  
  @com.terraframe.mojo.controller.ActionParameters(parameters="java.lang.String:option", post=false)
  public void failSearch(java.lang.String option) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.irs.InterventionPlanningController.java";
    throw new com.terraframe.mojo.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.irs.InterventionPlanningController.failSearch");
  }
  
  @com.terraframe.mojo.controller.ActionParameters(parameters="java.lang.String:geoId, dss.vector.solutions.general.MalariaSeasonDTO:season", post=true)
  public void searchForInsceticidePlanning(java.lang.String geoId, dss.vector.solutions.general.MalariaSeasonDTO season) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.irs.InterventionPlanningController.java";
    throw new com.terraframe.mojo.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.irs.InterventionPlanningController.searchForInsceticidePlanning");
  }
  
  @com.terraframe.mojo.controller.ActionParameters(parameters="java.lang.String:geoId, dss.vector.solutions.general.MalariaSeasonDTO:season", post=true)
  public void failSearchForInsceticidePlanning(java.lang.String geoId, dss.vector.solutions.general.MalariaSeasonDTO season) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.irs.InterventionPlanningController.java";
    throw new com.terraframe.mojo.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.irs.InterventionPlanningController.failSearchForInsceticidePlanning");
  }
  
  @com.terraframe.mojo.controller.ActionParameters(parameters="java.lang.String:geoId, dss.vector.solutions.general.MalariaSeasonDTO:season", post=true)
  public void searchForOperatorPlanning(java.lang.String geoId, dss.vector.solutions.general.MalariaSeasonDTO season) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.irs.InterventionPlanningController.java";
    throw new com.terraframe.mojo.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.irs.InterventionPlanningController.searchForOperatorPlanning");
  }
  
  @com.terraframe.mojo.controller.ActionParameters(parameters="java.lang.String:geoId, dss.vector.solutions.general.MalariaSeasonDTO:season", post=true)
  public void failSearchForOperatorPlanning(java.lang.String geoId, dss.vector.solutions.general.MalariaSeasonDTO season) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.irs.InterventionPlanningController.java";
    throw new com.terraframe.mojo.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.irs.InterventionPlanningController.failSearchForOperatorPlanning");
  }
  
  @com.terraframe.mojo.controller.ActionParameters(parameters="java.lang.String:geoId, dss.vector.solutions.general.MalariaSeasonDTO:season", post=true)
  public void searchForTimePlanning(java.lang.String geoId, dss.vector.solutions.general.MalariaSeasonDTO season) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.irs.InterventionPlanningController.java";
    throw new com.terraframe.mojo.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.irs.InterventionPlanningController.searchForTimePlanning");
  }
  
  @com.terraframe.mojo.controller.ActionParameters(parameters="java.lang.String:geoId, dss.vector.solutions.general.MalariaSeasonDTO:season", post=true)
  public void failSearchForTimePlanning(java.lang.String geoId, dss.vector.solutions.general.MalariaSeasonDTO season) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.irs.InterventionPlanningController.java";
    throw new com.terraframe.mojo.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.irs.InterventionPlanningController.failSearchForTimePlanning");
  }
  
  @com.terraframe.mojo.controller.ActionParameters(parameters="java.lang.Integer:unitsPerDay", post=true)
  public void setSprayedUnitsPerDay(java.lang.Integer unitsPerDay) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.irs.InterventionPlanningController.java";
    throw new com.terraframe.mojo.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.irs.InterventionPlanningController.setSprayedUnitsPerDay");
  }
  
  @com.terraframe.mojo.controller.ActionParameters(parameters="java.lang.String:unitsPerDay", post=true)
  public void failSetSprayedUnitsPerDay(java.lang.String unitsPerDay) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.irs.InterventionPlanningController.java";
    throw new com.terraframe.mojo.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.irs.InterventionPlanningController.failSetSprayedUnitsPerDay");
  }
  
}
