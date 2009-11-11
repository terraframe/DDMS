package dss.vector.solutions.intervention.monitor;

@com.terraframe.mojo.business.ClassSignature(hash = 1770910062)
public class ITNHouseholdSurveyControllerBase implements com.terraframe.mojo.generation.loader.Reloadable
{
  public static final String CLASS = "dss.vector.solutions.intervention.monitor.ITNHouseholdSurveyController";
  protected javax.servlet.http.HttpServletRequest req;
  protected javax.servlet.http.HttpServletResponse resp;
  protected java.lang.Boolean isAsynchronous;
  protected java.lang.String dir;
  protected java.lang.String layout;
  
  private static final long serialVersionUID = 1770910062;
  
  public ITNHouseholdSurveyControllerBase(javax.servlet.http.HttpServletRequest req, javax.servlet.http.HttpServletResponse resp, java.lang.Boolean isAsynchronous)
  {
    this(req, resp, isAsynchronous, "","");
  }
  
  public ITNHouseholdSurveyControllerBase(javax.servlet.http.HttpServletRequest req, javax.servlet.http.HttpServletResponse resp, java.lang.Boolean isAsynchronous, java.lang.String dir, java.lang.String layout)
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
  
  @com.terraframe.mojo.controller.ActionParameters(parameters="java.lang.String:id", post=false)
  public void view(java.lang.String id) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.intervention.monitor.ITNHouseholdSurveyController.java";
    throw new com.terraframe.mojo.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.intervention.monitor.ITNHouseholdSurveyController.view");
  }
  
  @com.terraframe.mojo.controller.ActionParameters(parameters="java.lang.String:id", post=false)
  public void failView(java.lang.String id) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.intervention.monitor.ITNHouseholdSurveyController.java";
    throw new com.terraframe.mojo.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.intervention.monitor.ITNHouseholdSurveyController.failView");
  }
  
  @com.terraframe.mojo.controller.ActionParameters(parameters="dss.vector.solutions.intervention.monitor.ITNHouseholdSurveyViewDTO:dto, [Ldss.vector.solutions.intervention.monitor.ITNHouseholdSurveyNetDTO;:nets, [Ldss.vector.solutions.intervention.monitor.ITNHouseholdSurveyTargetGroupDTO;:targetGroups, [Ldss.vector.solutions.intervention.monitor.ITNHouseholdSurveyNonUseReasonDTO;:reasons", post=true)
  public void create(dss.vector.solutions.intervention.monitor.ITNHouseholdSurveyViewDTO dto, dss.vector.solutions.intervention.monitor.ITNHouseholdSurveyNetDTO[] nets, dss.vector.solutions.intervention.monitor.ITNHouseholdSurveyTargetGroupDTO[] targetGroups, dss.vector.solutions.intervention.monitor.ITNHouseholdSurveyNonUseReasonDTO[] reasons) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.intervention.monitor.ITNHouseholdSurveyController.java";
    throw new com.terraframe.mojo.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.intervention.monitor.ITNHouseholdSurveyController.create");
  }
  
  @com.terraframe.mojo.controller.ActionParameters(parameters="dss.vector.solutions.intervention.monitor.ITNHouseholdSurveyViewDTO:dto, [Ldss.vector.solutions.intervention.monitor.ITNHouseholdSurveyNetDTO;:nets, [Ldss.vector.solutions.intervention.monitor.ITNHouseholdSurveyTargetGroupDTO;:targetGroups, [Ldss.vector.solutions.intervention.monitor.ITNHouseholdSurveyNonUseReasonDTO;:reasons", post=true)
  public void failCreate(dss.vector.solutions.intervention.monitor.ITNHouseholdSurveyViewDTO dto, dss.vector.solutions.intervention.monitor.ITNHouseholdSurveyNetDTO[] nets, dss.vector.solutions.intervention.monitor.ITNHouseholdSurveyTargetGroupDTO[] targetGroups, dss.vector.solutions.intervention.monitor.ITNHouseholdSurveyNonUseReasonDTO[] reasons) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.intervention.monitor.ITNHouseholdSurveyController.java";
    throw new com.terraframe.mojo.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.intervention.monitor.ITNHouseholdSurveyController.failCreate");
  }
  
  @com.terraframe.mojo.controller.ActionParameters(parameters="java.lang.String:id", post=false)
  public void edit(java.lang.String id) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.intervention.monitor.ITNHouseholdSurveyController.java";
    throw new com.terraframe.mojo.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.intervention.monitor.ITNHouseholdSurveyController.edit");
  }
  
  @com.terraframe.mojo.controller.ActionParameters(parameters="java.lang.String:id", post=false)
  public void failEdit(java.lang.String id) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.intervention.monitor.ITNHouseholdSurveyController.java";
    throw new com.terraframe.mojo.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.intervention.monitor.ITNHouseholdSurveyController.failEdit");
  }
  
  @com.terraframe.mojo.controller.ActionParameters(parameters="dss.vector.solutions.intervention.monitor.ITNHouseholdSurveyViewDTO:dto", post=true)
  public void cancel(dss.vector.solutions.intervention.monitor.ITNHouseholdSurveyViewDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.intervention.monitor.ITNHouseholdSurveyController.java";
    throw new com.terraframe.mojo.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.intervention.monitor.ITNHouseholdSurveyController.cancel");
  }
  
  @com.terraframe.mojo.controller.ActionParameters(parameters="dss.vector.solutions.intervention.monitor.ITNHouseholdSurveyViewDTO:dto", post=true)
  public void failCancel(dss.vector.solutions.intervention.monitor.ITNHouseholdSurveyViewDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.intervention.monitor.ITNHouseholdSurveyController.java";
    throw new com.terraframe.mojo.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.intervention.monitor.ITNHouseholdSurveyController.failCancel");
  }
  
  @com.terraframe.mojo.controller.ActionParameters(parameters="java.lang.String:sortAttribute, java.lang.Boolean:isAscending, java.lang.Integer:pageSize, java.lang.Integer:pageNumber", post=false)
  public void viewPage(java.lang.String sortAttribute, java.lang.Boolean isAscending, java.lang.Integer pageSize, java.lang.Integer pageNumber) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.intervention.monitor.ITNHouseholdSurveyController.java";
    throw new com.terraframe.mojo.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.intervention.monitor.ITNHouseholdSurveyController.viewPage");
  }
  
  @com.terraframe.mojo.controller.ActionParameters(parameters="java.lang.String:sortAttribute, java.lang.String:isAscending, java.lang.String:pageSize, java.lang.String:pageNumber", post=false)
  public void failViewPage(java.lang.String sortAttribute, java.lang.String isAscending, java.lang.String pageSize, java.lang.String pageNumber) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.intervention.monitor.ITNHouseholdSurveyController.java";
    throw new com.terraframe.mojo.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.intervention.monitor.ITNHouseholdSurveyController.failViewPage");
  }
  
  @com.terraframe.mojo.controller.ActionParameters(parameters="dss.vector.solutions.intervention.monitor.ITNHouseholdSurveyViewDTO:dto", post=true)
  public void delete(dss.vector.solutions.intervention.monitor.ITNHouseholdSurveyViewDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.intervention.monitor.ITNHouseholdSurveyController.java";
    throw new com.terraframe.mojo.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.intervention.monitor.ITNHouseholdSurveyController.delete");
  }
  
  @com.terraframe.mojo.controller.ActionParameters(parameters="dss.vector.solutions.intervention.monitor.ITNHouseholdSurveyViewDTO:dto", post=true)
  public void failDelete(dss.vector.solutions.intervention.monitor.ITNHouseholdSurveyViewDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.intervention.monitor.ITNHouseholdSurveyController.java";
    throw new com.terraframe.mojo.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.intervention.monitor.ITNHouseholdSurveyController.failDelete");
  }
  
  @com.terraframe.mojo.controller.ActionParameters(parameters="", post=false)
  public void newInstance() throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.intervention.monitor.ITNHouseholdSurveyController.java";
    throw new com.terraframe.mojo.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.intervention.monitor.ITNHouseholdSurveyController.newInstance");
  }
  
  @com.terraframe.mojo.controller.ActionParameters(parameters="", post=false)
  public void failNewInstance() throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.intervention.monitor.ITNHouseholdSurveyController.java";
    throw new com.terraframe.mojo.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.intervention.monitor.ITNHouseholdSurveyController.failNewInstance");
  }
  
  @com.terraframe.mojo.controller.ActionParameters(parameters="dss.vector.solutions.intervention.monitor.ITNHouseholdSurveyViewDTO:dto, [Ldss.vector.solutions.intervention.monitor.ITNHouseholdSurveyNetDTO;:nets, [Ldss.vector.solutions.intervention.monitor.ITNHouseholdSurveyTargetGroupDTO;:targetGroups, [Ldss.vector.solutions.intervention.monitor.ITNHouseholdSurveyNonUseReasonDTO;:reasons", post=true)
  public void update(dss.vector.solutions.intervention.monitor.ITNHouseholdSurveyViewDTO dto, dss.vector.solutions.intervention.monitor.ITNHouseholdSurveyNetDTO[] nets, dss.vector.solutions.intervention.monitor.ITNHouseholdSurveyTargetGroupDTO[] targetGroups, dss.vector.solutions.intervention.monitor.ITNHouseholdSurveyNonUseReasonDTO[] reasons) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.intervention.monitor.ITNHouseholdSurveyController.java";
    throw new com.terraframe.mojo.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.intervention.monitor.ITNHouseholdSurveyController.update");
  }
  
  @com.terraframe.mojo.controller.ActionParameters(parameters="dss.vector.solutions.intervention.monitor.ITNHouseholdSurveyViewDTO:dto, [Ldss.vector.solutions.intervention.monitor.ITNHouseholdSurveyNetDTO;:nets, [Ldss.vector.solutions.intervention.monitor.ITNHouseholdSurveyTargetGroupDTO;:targetGroups, [Ldss.vector.solutions.intervention.monitor.ITNHouseholdSurveyNonUseReasonDTO;:reasons", post=true)
  public void failUpdate(dss.vector.solutions.intervention.monitor.ITNHouseholdSurveyViewDTO dto, dss.vector.solutions.intervention.monitor.ITNHouseholdSurveyNetDTO[] nets, dss.vector.solutions.intervention.monitor.ITNHouseholdSurveyTargetGroupDTO[] targetGroups, dss.vector.solutions.intervention.monitor.ITNHouseholdSurveyNonUseReasonDTO[] reasons) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.intervention.monitor.ITNHouseholdSurveyController.java";
    throw new com.terraframe.mojo.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.intervention.monitor.ITNHouseholdSurveyController.failUpdate");
  }
  
  @com.terraframe.mojo.controller.ActionParameters(parameters="", post=false)
  public void viewAll() throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.intervention.monitor.ITNHouseholdSurveyController.java";
    throw new com.terraframe.mojo.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.intervention.monitor.ITNHouseholdSurveyController.viewAll");
  }
  
  @com.terraframe.mojo.controller.ActionParameters(parameters="", post=false)
  public void failViewAll() throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.intervention.monitor.ITNHouseholdSurveyController.java";
    throw new com.terraframe.mojo.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.intervention.monitor.ITNHouseholdSurveyController.failViewAll");
  }
  
}
