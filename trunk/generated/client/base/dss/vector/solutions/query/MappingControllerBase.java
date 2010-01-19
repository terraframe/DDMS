package dss.vector.solutions.query;

@com.terraframe.mojo.business.ClassSignature(hash = -2134746273)
public class MappingControllerBase implements com.terraframe.mojo.generation.loader.Reloadable
{
  public static final String CLASS = "dss.vector.solutions.query.MappingController";
  protected javax.servlet.http.HttpServletRequest req;
  protected javax.servlet.http.HttpServletResponse resp;
  protected java.lang.Boolean isAsynchronous;
  protected java.lang.String dir;
  protected java.lang.String layout;
  
  private static final long serialVersionUID = -2134746273;
  
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
  
  @com.terraframe.mojo.controller.ActionParameters(parameters="java.lang.String:layerId, dss.vector.solutions.query.AbstractCategoryDTO:category", post=true)
  public void addCategoryToLayer(java.lang.String layerId, dss.vector.solutions.query.AbstractCategoryDTO category) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.query.MappingController.java";
    throw new com.terraframe.mojo.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.query.MappingController.addCategoryToLayer");
  }
  
  @com.terraframe.mojo.controller.ActionParameters(parameters="java.lang.String:layerId, dss.vector.solutions.query.AbstractCategoryDTO:category", post=true)
  public void failAddCategoryToLayer(java.lang.String layerId, dss.vector.solutions.query.AbstractCategoryDTO category) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.query.MappingController.java";
    throw new com.terraframe.mojo.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.query.MappingController.failAddCategoryToLayer");
  }
  
  @com.terraframe.mojo.controller.ActionParameters(parameters="java.lang.String:layerId, java.lang.Boolean:doUnlock", post=true)
  public void cancelLayer(java.lang.String layerId, java.lang.Boolean doUnlock) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.query.MappingController.java";
    throw new com.terraframe.mojo.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.query.MappingController.cancelLayer");
  }
  
  @com.terraframe.mojo.controller.ActionParameters(parameters="java.lang.String:layerId, java.lang.String:doUnlock", post=true)
  public void failCancelLayer(java.lang.String layerId, java.lang.String doUnlock) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.query.MappingController.java";
    throw new com.terraframe.mojo.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.query.MappingController.failCancelLayer");
  }
  
  @com.terraframe.mojo.controller.ActionParameters(parameters="java.lang.String:layerId", post=true)
  public void deleteLayer(java.lang.String layerId) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.query.MappingController.java";
    throw new com.terraframe.mojo.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.query.MappingController.deleteLayer");
  }
  
  @com.terraframe.mojo.controller.ActionParameters(parameters="java.lang.String:layerId", post=true)
  public void failDeleteLayer(java.lang.String layerId) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.query.MappingController.java";
    throw new com.terraframe.mojo.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.query.MappingController.failDeleteLayer");
  }
  
  @com.terraframe.mojo.controller.ActionParameters(parameters="java.lang.String:thematicLayerId, [Ldss.vector.solutions.query.ThematicVariableDTO;:thematicVariables", post=true)
  public void editThematicLayer(java.lang.String thematicLayerId, dss.vector.solutions.query.ThematicVariableDTO[] thematicVariables) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.query.MappingController.java";
    throw new com.terraframe.mojo.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.query.MappingController.editThematicLayer");
  }
  
  @com.terraframe.mojo.controller.ActionParameters(parameters="java.lang.String:thematicLayerId, [Ldss.vector.solutions.query.ThematicVariableDTO;:thematicVariables", post=true)
  public void failEditThematicLayer(java.lang.String thematicLayerId, dss.vector.solutions.query.ThematicVariableDTO[] thematicVariables) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.query.MappingController.java";
    throw new com.terraframe.mojo.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.query.MappingController.failEditThematicLayer");
  }
  
  @com.terraframe.mojo.controller.ActionParameters(parameters="java.lang.String:mapId", post=true)
  public void exportShapefile(java.lang.String mapId) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.query.MappingController.java";
    throw new com.terraframe.mojo.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.query.MappingController.exportShapefile");
  }
  
  @com.terraframe.mojo.controller.ActionParameters(parameters="java.lang.String:mapId", post=true)
  public void failExportShapefile(java.lang.String mapId) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.query.MappingController.java";
    throw new com.terraframe.mojo.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.query.MappingController.failExportShapefile");
  }
  
  @com.terraframe.mojo.controller.ActionParameters(parameters="", post=false)
  public void generateMaps() throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.query.MappingController.java";
    throw new com.terraframe.mojo.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.query.MappingController.generateMaps");
  }
  
  @com.terraframe.mojo.controller.ActionParameters(parameters="", post=false)
  public void failGenerateMaps() throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.query.MappingController.java";
    throw new com.terraframe.mojo.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.query.MappingController.failGenerateMaps");
  }
  
  @com.terraframe.mojo.controller.ActionParameters(parameters="java.lang.String:savedSearchId", post=true)
  public void getLegend(java.lang.String savedSearchId) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.query.MappingController.java";
    throw new com.terraframe.mojo.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.query.MappingController.getLegend");
  }
  
  @com.terraframe.mojo.controller.ActionParameters(parameters="java.lang.String:savedSearchId", post=true)
  public void failGetLegend(java.lang.String savedSearchId) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.query.MappingController.java";
    throw new com.terraframe.mojo.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.query.MappingController.failGetLegend");
  }
  
  @com.terraframe.mojo.controller.ActionParameters(parameters="java.lang.String:queryXML, java.lang.String:thematicLayerType, [Ljava.lang.String;:universalLayers, java.lang.String:savedSearchId", post=true)
  public void mapAggregatedCaseQuery(java.lang.String queryXML, java.lang.String thematicLayerType, java.lang.String[] universalLayers, java.lang.String savedSearchId) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.query.MappingController.java";
    throw new com.terraframe.mojo.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.query.MappingController.mapAggregatedCaseQuery");
  }
  
  @com.terraframe.mojo.controller.ActionParameters(parameters="java.lang.String:queryXML, java.lang.String:thematicLayerType, [Ljava.lang.String;:universalLayers, java.lang.String:savedSearchId", post=true)
  public void failMapAggregatedCaseQuery(java.lang.String queryXML, java.lang.String thematicLayerType, java.lang.String[] universalLayers, java.lang.String savedSearchId) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.query.MappingController.java";
    throw new com.terraframe.mojo.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.query.MappingController.failMapAggregatedCaseQuery");
  }
  
  @com.terraframe.mojo.controller.ActionParameters(parameters="java.lang.String:queryXML, java.lang.String:thematicLayerType, [Ljava.lang.String;:universalLayers, java.lang.String:savedSearchId", post=true)
  public void mapEntomologyQuery(java.lang.String queryXML, java.lang.String thematicLayerType, java.lang.String[] universalLayers, java.lang.String savedSearchId) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.query.MappingController.java";
    throw new com.terraframe.mojo.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.query.MappingController.mapEntomologyQuery");
  }
  
  @com.terraframe.mojo.controller.ActionParameters(parameters="java.lang.String:queryXML, java.lang.String:thematicLayerType, [Ljava.lang.String;:universalLayers, java.lang.String:savedSearchId", post=true)
  public void failMapEntomologyQuery(java.lang.String queryXML, java.lang.String thematicLayerType, java.lang.String[] universalLayers, java.lang.String savedSearchId) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.query.MappingController.java";
    throw new com.terraframe.mojo.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.query.MappingController.failMapEntomologyQuery");
  }
  
  @com.terraframe.mojo.controller.ActionParameters(parameters="java.lang.String:queryXML, java.lang.String:config, [Ljava.lang.String;:universalLayers, java.lang.String:savedSearchId", post=true)
  public void mapIRSQuery(java.lang.String queryXML, java.lang.String config, java.lang.String[] universalLayers, java.lang.String savedSearchId) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.query.MappingController.java";
    throw new com.terraframe.mojo.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.query.MappingController.mapIRSQuery");
  }
  
  @com.terraframe.mojo.controller.ActionParameters(parameters="java.lang.String:queryXML, java.lang.String:config, [Ljava.lang.String;:universalLayers, java.lang.String:savedSearchId", post=true)
  public void failMapIRSQuery(java.lang.String queryXML, java.lang.String config, java.lang.String[] universalLayers, java.lang.String savedSearchId) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.query.MappingController.java";
    throw new com.terraframe.mojo.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.query.MappingController.failMapIRSQuery");
  }
  
  @com.terraframe.mojo.controller.ActionParameters(parameters="java.lang.String:queryXML, java.lang.String:config, [Ljava.lang.String;:universalLayers, java.lang.String:savedSearchId", post=true)
  public void mapResistanceQuery(java.lang.String queryXML, java.lang.String config, java.lang.String[] universalLayers, java.lang.String savedSearchId) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.query.MappingController.java";
    throw new com.terraframe.mojo.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.query.MappingController.mapResistanceQuery");
  }
  
  @com.terraframe.mojo.controller.ActionParameters(parameters="java.lang.String:queryXML, java.lang.String:config, [Ljava.lang.String;:universalLayers, java.lang.String:savedSearchId", post=true)
  public void failMapResistanceQuery(java.lang.String queryXML, java.lang.String config, java.lang.String[] universalLayers, java.lang.String savedSearchId) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.query.MappingController.java";
    throw new com.terraframe.mojo.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.query.MappingController.failMapResistanceQuery");
  }
  
  @com.terraframe.mojo.controller.ActionParameters(parameters="java.lang.String:queryXML, java.lang.String:config, [Ljava.lang.String;:universalLayers, java.lang.String:savedSearchId", post=true)
  public void mapSurveyQuery(java.lang.String queryXML, java.lang.String config, java.lang.String[] universalLayers, java.lang.String savedSearchId) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.query.MappingController.java";
    throw new com.terraframe.mojo.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.query.MappingController.mapSurveyQuery");
  }
  
  @com.terraframe.mojo.controller.ActionParameters(parameters="java.lang.String:queryXML, java.lang.String:config, [Ljava.lang.String;:universalLayers, java.lang.String:savedSearchId", post=true)
  public void failMapSurveyQuery(java.lang.String queryXML, java.lang.String config, java.lang.String[] universalLayers, java.lang.String savedSearchId) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.query.MappingController.java";
    throw new com.terraframe.mojo.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.query.MappingController.failMapSurveyQuery");
  }
  
  @com.terraframe.mojo.controller.ActionParameters(parameters="java.lang.String:savedMapId", post=true)
  public void refreshMap(java.lang.String savedMapId) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.query.MappingController.java";
    throw new com.terraframe.mojo.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.query.MappingController.refreshMap");
  }
  
  @com.terraframe.mojo.controller.ActionParameters(parameters="java.lang.String:savedMapId", post=true)
  public void failRefreshMap(java.lang.String savedMapId) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.query.MappingController.java";
    throw new com.terraframe.mojo.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.query.MappingController.failRefreshMap");
  }
  
  @com.terraframe.mojo.controller.ActionParameters(parameters="java.lang.String:layerId, dss.vector.solutions.query.ThematicVariableDTO:thematicVariable, [Ldss.vector.solutions.query.AbstractCategoryDTO;:categories", post=true)
  public void updateThematicVariable(java.lang.String layerId, dss.vector.solutions.query.ThematicVariableDTO thematicVariable, dss.vector.solutions.query.AbstractCategoryDTO[] categories) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.query.MappingController.java";
    throw new com.terraframe.mojo.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.query.MappingController.updateThematicVariable");
  }
  
  @com.terraframe.mojo.controller.ActionParameters(parameters="java.lang.String:layerId, dss.vector.solutions.query.ThematicVariableDTO:thematicVariable, [Ldss.vector.solutions.query.AbstractCategoryDTO;:categories", post=true)
  public void failUpdateThematicVariable(java.lang.String layerId, dss.vector.solutions.query.ThematicVariableDTO thematicVariable, dss.vector.solutions.query.AbstractCategoryDTO[] categories) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.query.MappingController.java";
    throw new com.terraframe.mojo.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.query.MappingController.failUpdateThematicVariable");
  }
  
  @com.terraframe.mojo.controller.ActionParameters(parameters="java.lang.String:layerId", post=false)
  public void viewLayer(java.lang.String layerId) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.query.MappingController.java";
    throw new com.terraframe.mojo.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.query.MappingController.viewLayer");
  }
  
  @com.terraframe.mojo.controller.ActionParameters(parameters="java.lang.String:layerId", post=false)
  public void failViewLayer(java.lang.String layerId) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.query.MappingController.java";
    throw new com.terraframe.mojo.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.query.MappingController.failViewLayer");
  }
  
}
