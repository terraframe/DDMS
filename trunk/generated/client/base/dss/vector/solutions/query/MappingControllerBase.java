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
package dss.vector.solutions.query;

@com.runwaysdk.business.ClassSignature(hash = -1219441020)
public class MappingControllerBase implements com.runwaysdk.generation.loader.Reloadable
{
  public static final String CLASS = "dss.vector.solutions.query.MappingController";
  protected javax.servlet.http.HttpServletRequest req;
  protected javax.servlet.http.HttpServletResponse resp;
  protected java.lang.Boolean isAsynchronous;
  protected java.lang.String dir;
  protected java.lang.String layout;
  
  public MappingControllerBase(javax.servlet.http.HttpServletRequest req, javax.servlet.http.HttpServletResponse resp, java.lang.Boolean isAsynchronous)
  {
    this(req, resp, isAsynchronous, "","");
  }
  
  public MappingControllerBase(javax.servlet.http.HttpServletRequest req, javax.servlet.http.HttpServletResponse resp, java.lang.Boolean isAsynchronous, java.lang.String dir, java.lang.String layout)
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
  
  @com.runwaysdk.controller.ActionParameters(parameters="java.lang.String:layerId, dss.vector.solutions.query.AbstractCategoryDTO:category", post=true)
  public void addCategoryToLayer(java.lang.String layerId, dss.vector.solutions.query.AbstractCategoryDTO category) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.query.MappingController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.query.MappingController.addCategoryToLayer");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="java.lang.String:layerId, dss.vector.solutions.query.AbstractCategoryDTO:category", post=true)
  public void failAddCategoryToLayer(java.lang.String layerId, dss.vector.solutions.query.AbstractCategoryDTO category) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.query.MappingController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.query.MappingController.failAddCategoryToLayer");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="", post=true)
  public void addText() throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.query.MappingController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.query.MappingController.addText");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="", post=true)
  public void failAddText() throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.query.MappingController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.query.MappingController.failAddText");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="java.lang.String:layerId, java.lang.Boolean:doUnlock", post=true)
  public void cancelLayer(java.lang.String layerId, java.lang.Boolean doUnlock) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.query.MappingController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.query.MappingController.cancelLayer");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="java.lang.String:layerId, java.lang.String:doUnlock", post=true)
  public void failCancelLayer(java.lang.String layerId, java.lang.String doUnlock) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.query.MappingController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.query.MappingController.failCancelLayer");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="java.lang.String:layerId", post=true)
  public void deleteLayer(java.lang.String layerId) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.query.MappingController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.query.MappingController.deleteLayer");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="java.lang.String:layerId", post=true)
  public void failDeleteLayer(java.lang.String layerId) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.query.MappingController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.query.MappingController.failDeleteLayer");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="java.lang.String:thematicLayerId, [Ldss.vector.solutions.query.ThematicVariableDTO;:thematicVariables", post=true)
  public void editThematicLayer(java.lang.String thematicLayerId, dss.vector.solutions.query.ThematicVariableDTO[] thematicVariables) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.query.MappingController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.query.MappingController.editThematicLayer");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="java.lang.String:thematicLayerId, [Ldss.vector.solutions.query.ThematicVariableDTO;:thematicVariables", post=true)
  public void failEditThematicLayer(java.lang.String thematicLayerId, dss.vector.solutions.query.ThematicVariableDTO[] thematicVariables) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.query.MappingController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.query.MappingController.failEditThematicLayer");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="java.lang.String:mapId, java.lang.String:namedMapId", post=true)
  public void exportShapefile(java.lang.String mapId, java.lang.String namedMapId) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.query.MappingController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.query.MappingController.exportShapefile");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="java.lang.String:mapId, java.lang.String:namedMapId", post=true)
  public void failExportShapefile(java.lang.String mapId, java.lang.String namedMapId) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.query.MappingController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.query.MappingController.failExportShapefile");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="", post=false)
  public void generateMaps() throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.query.MappingController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.query.MappingController.generateMaps");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="", post=false)
  public void failGenerateMaps() throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.query.MappingController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.query.MappingController.failGenerateMaps");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="java.lang.String:savedSearchId", post=true)
  public void getLegend(java.lang.String savedSearchId) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.query.MappingController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.query.MappingController.getLegend");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="java.lang.String:savedSearchId", post=true)
  public void failGetLegend(java.lang.String savedSearchId) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.query.MappingController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.query.MappingController.failGetLegend");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="java.lang.String:queryXML, java.lang.String:thematicLayerType, [Ljava.lang.String;:universalLayers, java.lang.String:savedSearchId", post=true)
  public void mapAggregatedCaseQuery(java.lang.String queryXML, java.lang.String thematicLayerType, java.lang.String[] universalLayers, java.lang.String savedSearchId) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.query.MappingController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.query.MappingController.mapAggregatedCaseQuery");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="java.lang.String:queryXML, java.lang.String:thematicLayerType, [Ljava.lang.String;:universalLayers, java.lang.String:savedSearchId", post=true)
  public void failMapAggregatedCaseQuery(java.lang.String queryXML, java.lang.String thematicLayerType, java.lang.String[] universalLayers, java.lang.String savedSearchId) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.query.MappingController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.query.MappingController.failMapAggregatedCaseQuery");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="java.lang.String:queryXML, java.lang.String:thematicLayerType, [Ljava.lang.String;:universalLayers, java.lang.String:savedSearchId", post=true)
  public void mapEntomologyQuery(java.lang.String queryXML, java.lang.String thematicLayerType, java.lang.String[] universalLayers, java.lang.String savedSearchId) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.query.MappingController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.query.MappingController.mapEntomologyQuery");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="java.lang.String:queryXML, java.lang.String:thematicLayerType, [Ljava.lang.String;:universalLayers, java.lang.String:savedSearchId", post=true)
  public void failMapEntomologyQuery(java.lang.String queryXML, java.lang.String thematicLayerType, java.lang.String[] universalLayers, java.lang.String savedSearchId) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.query.MappingController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.query.MappingController.failMapEntomologyQuery");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="java.lang.String:mapId, java.lang.String:outFileName, java.lang.String:outFileFormat, java.lang.String:mapBounds, java.lang.String:mapSize", post=true)
  public void mapExport(java.lang.String mapId, java.lang.String outFileName, java.lang.String outFileFormat, java.lang.String mapBounds, java.lang.String mapSize) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.query.MappingController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.query.MappingController.mapExport");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="java.lang.String:mapId, java.lang.String:outFileName, java.lang.String:outFileFormat, java.lang.String:mapBounds, java.lang.String:mapSize", post=true)
  public void failMapExport(java.lang.String mapId, java.lang.String outFileName, java.lang.String outFileFormat, java.lang.String mapBounds, java.lang.String mapSize) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.query.MappingController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.query.MappingController.failMapExport");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="", post=true)
  public void mapExportModal() throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.query.MappingController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.query.MappingController.mapExportModal");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="", post=true)
  public void failMapExportModal() throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.query.MappingController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.query.MappingController.failMapExportModal");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="java.lang.String:queryXML, java.lang.String:config, [Ljava.lang.String;:universalLayers, java.lang.String:savedSearchId", post=true)
  public void mapIRSQuery(java.lang.String queryXML, java.lang.String config, java.lang.String[] universalLayers, java.lang.String savedSearchId) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.query.MappingController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.query.MappingController.mapIRSQuery");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="java.lang.String:queryXML, java.lang.String:config, [Ljava.lang.String;:universalLayers, java.lang.String:savedSearchId", post=true)
  public void failMapIRSQuery(java.lang.String queryXML, java.lang.String config, java.lang.String[] universalLayers, java.lang.String savedSearchId) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.query.MappingController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.query.MappingController.failMapIRSQuery");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="java.lang.String:queryXML, java.lang.String:config, [Ljava.lang.String;:universalLayers, java.lang.String:savedSearchId", post=true)
  public void mapResistanceQuery(java.lang.String queryXML, java.lang.String config, java.lang.String[] universalLayers, java.lang.String savedSearchId) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.query.MappingController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.query.MappingController.mapResistanceQuery");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="java.lang.String:queryXML, java.lang.String:config, [Ljava.lang.String;:universalLayers, java.lang.String:savedSearchId", post=true)
  public void failMapResistanceQuery(java.lang.String queryXML, java.lang.String config, java.lang.String[] universalLayers, java.lang.String savedSearchId) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.query.MappingController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.query.MappingController.failMapResistanceQuery");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="java.lang.String:queryXML, java.lang.String:config, [Ljava.lang.String;:universalLayers, java.lang.String:savedSearchId", post=true)
  public void mapSurveyQuery(java.lang.String queryXML, java.lang.String config, java.lang.String[] universalLayers, java.lang.String savedSearchId) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.query.MappingController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.query.MappingController.mapSurveyQuery");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="java.lang.String:queryXML, java.lang.String:config, [Ljava.lang.String;:universalLayers, java.lang.String:savedSearchId", post=true)
  public void failMapSurveyQuery(java.lang.String queryXML, java.lang.String config, java.lang.String[] universalLayers, java.lang.String savedSearchId) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.query.MappingController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.query.MappingController.failMapSurveyQuery");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="java.lang.String:savedMapId, java.lang.String:currentMapId", post=true)
  public void refreshMap(java.lang.String savedMapId, java.lang.String currentMapId) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.query.MappingController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.query.MappingController.refreshMap");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="java.lang.String:savedMapId, java.lang.String:currentMapId", post=true)
  public void failRefreshMap(java.lang.String savedMapId, java.lang.String currentMapId) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.query.MappingController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.query.MappingController.failRefreshMap");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="", post=true)
  public void setText() throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.query.MappingController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.query.MappingController.setText");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="", post=true)
  public void failSetText() throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.query.MappingController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.query.MappingController.failSetText");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="java.lang.String:layerId, dss.vector.solutions.query.ThematicVariableDTO:thematicVariable, [Ldss.vector.solutions.query.AbstractCategoryDTO;:categories", post=true)
  public void updateThematicVariable(java.lang.String layerId, dss.vector.solutions.query.ThematicVariableDTO thematicVariable, dss.vector.solutions.query.AbstractCategoryDTO[] categories) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.query.MappingController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.query.MappingController.updateThematicVariable");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="java.lang.String:layerId, dss.vector.solutions.query.ThematicVariableDTO:thematicVariable, [Ldss.vector.solutions.query.AbstractCategoryDTO;:categories", post=true)
  public void failUpdateThematicVariable(java.lang.String layerId, dss.vector.solutions.query.ThematicVariableDTO thematicVariable, dss.vector.solutions.query.AbstractCategoryDTO[] categories) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.query.MappingController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.query.MappingController.failUpdateThematicVariable");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="com.runwaysdk.controller.MultipartFileParameter:imageFile, java.lang.String:mapId", post=true)
  public void uploadMapImage(com.runwaysdk.controller.MultipartFileParameter imageFile, java.lang.String mapId) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.query.MappingController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.query.MappingController.uploadMapImage");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="com.runwaysdk.controller.MultipartFileParameter:imageFile, java.lang.String:mapId", post=true)
  public void failUploadMapImage(com.runwaysdk.controller.MultipartFileParameter imageFile, java.lang.String mapId) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.query.MappingController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.query.MappingController.failUploadMapImage");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="java.lang.String:layerId", post=false)
  public void viewLayer(java.lang.String layerId) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.query.MappingController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.query.MappingController.viewLayer");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="java.lang.String:layerId", post=false)
  public void failViewLayer(java.lang.String layerId) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.query.MappingController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.query.MappingController.failViewLayer");
  }
  
}
