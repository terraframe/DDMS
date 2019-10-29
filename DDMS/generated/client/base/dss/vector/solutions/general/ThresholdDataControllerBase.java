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
package dss.vector.solutions.general;

@com.runwaysdk.business.ClassSignature(hash = 307450889)
public class ThresholdDataControllerBase implements com.runwaysdk.generation.loader.Reloadable
{
  public static final String CLASS = "dss.vector.solutions.general.ThresholdDataController";
  protected javax.servlet.http.HttpServletRequest req;
  protected javax.servlet.http.HttpServletResponse resp;
  protected java.lang.Boolean isAsynchronous;
  protected java.lang.String dir;
  protected java.lang.String layout;
  
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
  
  @com.runwaysdk.controller.ActionParameters(parameters="dss.vector.solutions.general.ThresholdCalculationTypeViewDTO:thresholdCalculation, java.lang.Boolean:currentYear", post=true)
  public void calculateFacilityThresholds(dss.vector.solutions.general.ThresholdCalculationTypeViewDTO thresholdCalculation, java.lang.Boolean currentYear) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.general.ThresholdDataController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.general.ThresholdDataController.calculateFacilityThresholds");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="dss.vector.solutions.general.ThresholdCalculationTypeViewDTO:thresholdCalculation, java.lang.String:currentYear", post=true)
  public void failCalculateFacilityThresholds(dss.vector.solutions.general.ThresholdCalculationTypeViewDTO thresholdCalculation, java.lang.String currentYear) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.general.ThresholdDataController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.general.ThresholdDataController.failCalculateFacilityThresholds");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="dss.vector.solutions.general.ThresholdCalculationTypeViewDTO:thresholdCalculation, java.lang.Boolean:currentYear", post=true)
  public void calculatePoliticalThresholds(dss.vector.solutions.general.ThresholdCalculationTypeViewDTO thresholdCalculation, java.lang.Boolean currentYear) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.general.ThresholdDataController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.general.ThresholdDataController.calculatePoliticalThresholds");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="dss.vector.solutions.general.ThresholdCalculationTypeViewDTO:thresholdCalculation, java.lang.String:currentYear", post=true)
  public void failCalculatePoliticalThresholds(dss.vector.solutions.general.ThresholdCalculationTypeViewDTO thresholdCalculation, java.lang.String currentYear) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.general.ThresholdDataController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.general.ThresholdDataController.failCalculatePoliticalThresholds");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="dss.vector.solutions.general.ThresholdCalculationTypeViewDTO:thresholdCalculation, java.lang.Boolean:currentYear", post=true)
  public void calculateThresholds(dss.vector.solutions.general.ThresholdCalculationTypeViewDTO thresholdCalculation, java.lang.Boolean currentYear) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.general.ThresholdDataController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.general.ThresholdDataController.calculateThresholds");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="dss.vector.solutions.general.ThresholdCalculationTypeViewDTO:thresholdCalculation, java.lang.String:currentYear", post=true)
  public void failCalculateThresholds(dss.vector.solutions.general.ThresholdCalculationTypeViewDTO thresholdCalculation, java.lang.String currentYear) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.general.ThresholdDataController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.general.ThresholdDataController.failCalculateThresholds");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="", post=false)
  public void editThresholdConfiguration() throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.general.ThresholdDataController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.general.ThresholdDataController.editThresholdConfiguration");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="", post=false)
  public void failEditThresholdConfiguration() throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.general.ThresholdDataController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.general.ThresholdDataController.failEditThresholdConfiguration");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="", post=false)
  public void exportHistory() throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.general.ThresholdDataController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.general.ThresholdDataController.exportHistory");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="", post=false)
  public void failExportHistory() throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.general.ThresholdDataController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.general.ThresholdDataController.failExportHistory");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="[Ldss.vector.solutions.general.ThresholdDataViewDTO;:views", post=true)
  public void exportThresholdData(dss.vector.solutions.general.ThresholdDataViewDTO[] views) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.general.ThresholdDataController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.general.ThresholdDataController.exportThresholdData");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="[Ldss.vector.solutions.general.ThresholdDataViewDTO;:views", post=true)
  public void failExportThresholdData(dss.vector.solutions.general.ThresholdDataViewDTO[] views) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.general.ThresholdDataController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.general.ThresholdDataController.failExportThresholdData");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="", post=false)
  public void search() throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.general.ThresholdDataController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.general.ThresholdDataController.search");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="", post=false)
  public void failSearch() throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.general.ThresholdDataController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.general.ThresholdDataController.failSearch");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="java.lang.String:geoId, dss.vector.solutions.general.MalariaSeasonDTO:season, java.lang.Boolean:thresholdType", post=true)
  public void searchForThresholdData(java.lang.String geoId, dss.vector.solutions.general.MalariaSeasonDTO season, java.lang.Boolean thresholdType) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.general.ThresholdDataController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.general.ThresholdDataController.searchForThresholdData");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="java.lang.String:geoId, dss.vector.solutions.general.MalariaSeasonDTO:season, java.lang.String:thresholdType", post=true)
  public void failSearchForThresholdData(java.lang.String geoId, dss.vector.solutions.general.MalariaSeasonDTO season, java.lang.String thresholdType) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.general.ThresholdDataController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.general.ThresholdDataController.failSearchForThresholdData");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="dss.vector.solutions.general.ThresholdCalculationTypeViewDTO:thresholdCalculation", post=true)
  public void setThresholdConfiguration(dss.vector.solutions.general.ThresholdCalculationTypeViewDTO thresholdCalculation) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.general.ThresholdDataController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.general.ThresholdDataController.setThresholdConfiguration");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="dss.vector.solutions.general.ThresholdCalculationTypeViewDTO:thresholdCalculation", post=true)
  public void failSetThresholdConfiguration(dss.vector.solutions.general.ThresholdCalculationTypeViewDTO thresholdCalculation) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.general.ThresholdDataController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.general.ThresholdDataController.failSetThresholdConfiguration");
  }
  
}
