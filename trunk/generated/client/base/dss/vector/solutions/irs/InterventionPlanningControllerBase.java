/*******************************************************************************
 * Copyright (C) 2018 IVCC
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 ******************************************************************************/
package dss.vector.solutions.irs;

@com.runwaysdk.business.ClassSignature(hash = 2053190576)
public class InterventionPlanningControllerBase implements com.runwaysdk.generation.loader.Reloadable
{
  public static final String CLASS = "dss.vector.solutions.irs.InterventionPlanningController";
  protected javax.servlet.http.HttpServletRequest req;
  protected javax.servlet.http.HttpServletResponse resp;
  protected java.lang.Boolean isAsynchronous;
  protected java.lang.String dir;
  protected java.lang.String layout;
  
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
  
  @com.runwaysdk.controller.ActionParameters(parameters="[Ldss.vector.solutions.irs.InsecticideInterventionPlanningViewDTO;:views", post=true)
  public void exportInsecticidePlanning(dss.vector.solutions.irs.InsecticideInterventionPlanningViewDTO[] views) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.irs.InterventionPlanningController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.irs.InterventionPlanningController.exportInsecticidePlanning");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="[Ldss.vector.solutions.irs.InsecticideInterventionPlanningViewDTO;:views", post=true)
  public void failExportInsecticidePlanning(dss.vector.solutions.irs.InsecticideInterventionPlanningViewDTO[] views) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.irs.InterventionPlanningController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.irs.InterventionPlanningController.failExportInsecticidePlanning");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="[Ldss.vector.solutions.irs.OperatorInterventionPlanningViewDTO;:views", post=true)
  public void exportOperatorPlanning(dss.vector.solutions.irs.OperatorInterventionPlanningViewDTO[] views) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.irs.InterventionPlanningController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.irs.InterventionPlanningController.exportOperatorPlanning");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="[Ldss.vector.solutions.irs.OperatorInterventionPlanningViewDTO;:views", post=true)
  public void failExportOperatorPlanning(dss.vector.solutions.irs.OperatorInterventionPlanningViewDTO[] views) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.irs.InterventionPlanningController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.irs.InterventionPlanningController.failExportOperatorPlanning");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="[Ldss.vector.solutions.irs.TimeInterventionPlanningViewDTO;:views", post=true)
  public void exportTimePlanning(dss.vector.solutions.irs.TimeInterventionPlanningViewDTO[] views) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.irs.InterventionPlanningController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.irs.InterventionPlanningController.exportTimePlanning");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="[Ldss.vector.solutions.irs.TimeInterventionPlanningViewDTO;:views", post=true)
  public void failExportTimePlanning(dss.vector.solutions.irs.TimeInterventionPlanningViewDTO[] views) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.irs.InterventionPlanningController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.irs.InterventionPlanningController.failExportTimePlanning");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="java.lang.String:option", post=false)
  public void search(java.lang.String option) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.irs.InterventionPlanningController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.irs.InterventionPlanningController.search");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="java.lang.String:option", post=false)
  public void failSearch(java.lang.String option) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.irs.InterventionPlanningController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.irs.InterventionPlanningController.failSearch");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="java.lang.String:geoId, dss.vector.solutions.general.MalariaSeasonDTO:season", post=true)
  public void searchForInsceticidePlanning(java.lang.String geoId, dss.vector.solutions.general.MalariaSeasonDTO season) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.irs.InterventionPlanningController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.irs.InterventionPlanningController.searchForInsceticidePlanning");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="java.lang.String:geoId, dss.vector.solutions.general.MalariaSeasonDTO:season", post=true)
  public void failSearchForInsceticidePlanning(java.lang.String geoId, dss.vector.solutions.general.MalariaSeasonDTO season) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.irs.InterventionPlanningController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.irs.InterventionPlanningController.failSearchForInsceticidePlanning");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="java.lang.String:geoId, dss.vector.solutions.general.MalariaSeasonDTO:season", post=true)
  public void searchForOperatorPlanning(java.lang.String geoId, dss.vector.solutions.general.MalariaSeasonDTO season) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.irs.InterventionPlanningController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.irs.InterventionPlanningController.searchForOperatorPlanning");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="java.lang.String:geoId, dss.vector.solutions.general.MalariaSeasonDTO:season", post=true)
  public void failSearchForOperatorPlanning(java.lang.String geoId, dss.vector.solutions.general.MalariaSeasonDTO season) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.irs.InterventionPlanningController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.irs.InterventionPlanningController.failSearchForOperatorPlanning");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="java.lang.String:geoId, dss.vector.solutions.general.MalariaSeasonDTO:season", post=true)
  public void searchForTimePlanning(java.lang.String geoId, dss.vector.solutions.general.MalariaSeasonDTO season) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.irs.InterventionPlanningController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.irs.InterventionPlanningController.searchForTimePlanning");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="java.lang.String:geoId, dss.vector.solutions.general.MalariaSeasonDTO:season", post=true)
  public void failSearchForTimePlanning(java.lang.String geoId, dss.vector.solutions.general.MalariaSeasonDTO season) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.irs.InterventionPlanningController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.irs.InterventionPlanningController.failSearchForTimePlanning");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="java.lang.Integer:unitsPerDay", post=true)
  public void setSprayedUnitsPerDay(java.lang.Integer unitsPerDay) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.irs.InterventionPlanningController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.irs.InterventionPlanningController.setSprayedUnitsPerDay");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="java.lang.String:unitsPerDay", post=true)
  public void failSetSprayedUnitsPerDay(java.lang.String unitsPerDay) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.irs.InterventionPlanningController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.irs.InterventionPlanningController.failSetSprayedUnitsPerDay");
  }
  
}
