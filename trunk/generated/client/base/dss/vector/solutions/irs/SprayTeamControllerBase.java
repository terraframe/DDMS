package dss.vector.solutions.irs;

@com.runwaysdk.business.ClassSignature(hash = -1807921145)
public class SprayTeamControllerBase implements com.runwaysdk.generation.loader.Reloadable
{
  public static final String CLASS = "dss.vector.solutions.irs.SprayTeamController";
  protected javax.servlet.http.HttpServletRequest req;
  protected javax.servlet.http.HttpServletResponse resp;
  protected java.lang.Boolean isAsynchronous;
  protected java.lang.String dir;
  protected java.lang.String layout;
  
  private static final long serialVersionUID = -1807921145;
  
  public SprayTeamControllerBase(javax.servlet.http.HttpServletRequest req, javax.servlet.http.HttpServletResponse resp, java.lang.Boolean isAsynchronous)
  {
    this(req, resp, isAsynchronous, "","");
  }
  
  public SprayTeamControllerBase(javax.servlet.http.HttpServletRequest req, javax.servlet.http.HttpServletResponse resp, java.lang.Boolean isAsynchronous, java.lang.String dir, java.lang.String layout)
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
  
  @com.runwaysdk.controller.ActionParameters(parameters="dss.vector.solutions.irs.SprayTeamDTO:dto", post=true)
  public void cancel(dss.vector.solutions.irs.SprayTeamDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.irs.SprayTeamController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.irs.SprayTeamController.cancel");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="dss.vector.solutions.irs.SprayTeamDTO:dto", post=true)
  public void failCancel(dss.vector.solutions.irs.SprayTeamDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.irs.SprayTeamController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.irs.SprayTeamController.failCancel");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="dss.vector.solutions.irs.SprayTeamDTO:dto", post=true)
  public void create(dss.vector.solutions.irs.SprayTeamDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.irs.SprayTeamController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.irs.SprayTeamController.create");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="dss.vector.solutions.irs.SprayTeamDTO:dto", post=true)
  public void failCreate(dss.vector.solutions.irs.SprayTeamDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.irs.SprayTeamController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.irs.SprayTeamController.failCreate");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="dss.vector.solutions.irs.SprayTeamDTO:team, java.lang.String:geoId, java.lang.String:leaderId, [Ljava.lang.String;:operatorIds", post=true)
  public void createAndAssign(dss.vector.solutions.irs.SprayTeamDTO team, java.lang.String geoId, java.lang.String leaderId, java.lang.String[] operatorIds) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.irs.SprayTeamController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.irs.SprayTeamController.createAndAssign");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="dss.vector.solutions.irs.SprayTeamDTO:team, java.lang.String:geoId, java.lang.String:leaderId, [Ljava.lang.String;:operatorIds", post=true)
  public void failCreateAndAssign(dss.vector.solutions.irs.SprayTeamDTO team, java.lang.String geoId, java.lang.String leaderId, java.lang.String[] operatorIds) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.irs.SprayTeamController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.irs.SprayTeamController.failCreateAndAssign");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="dss.vector.solutions.irs.SprayTeamDTO:dto", post=true)
  public void delete(dss.vector.solutions.irs.SprayTeamDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.irs.SprayTeamController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.irs.SprayTeamController.delete");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="dss.vector.solutions.irs.SprayTeamDTO:dto", post=true)
  public void failDelete(dss.vector.solutions.irs.SprayTeamDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.irs.SprayTeamController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.irs.SprayTeamController.failDelete");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="java.lang.String:id", post=false)
  public void edit(java.lang.String id) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.irs.SprayTeamController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.irs.SprayTeamController.edit");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="java.lang.String:id", post=false)
  public void failEdit(java.lang.String id) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.irs.SprayTeamController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.irs.SprayTeamController.failEdit");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="", post=false)
  public void newInstance() throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.irs.SprayTeamController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.irs.SprayTeamController.newInstance");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="", post=false)
  public void failNewInstance() throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.irs.SprayTeamController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.irs.SprayTeamController.failNewInstance");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="dss.vector.solutions.irs.SprayTeamDTO:dto", post=true)
  public void update(dss.vector.solutions.irs.SprayTeamDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.irs.SprayTeamController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.irs.SprayTeamController.update");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="dss.vector.solutions.irs.SprayTeamDTO:dto", post=true)
  public void failUpdate(dss.vector.solutions.irs.SprayTeamDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.irs.SprayTeamController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.irs.SprayTeamController.failUpdate");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="dss.vector.solutions.irs.SprayTeamDTO:team, java.lang.String:geoId, java.lang.String:leaderId, [Ljava.lang.String;:operatorIds, [Ljava.lang.String;:removedIds", post=true)
  public void updateAssignments(dss.vector.solutions.irs.SprayTeamDTO team, java.lang.String geoId, java.lang.String leaderId, java.lang.String[] operatorIds, java.lang.String[] removedIds) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.irs.SprayTeamController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.irs.SprayTeamController.updateAssignments");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="dss.vector.solutions.irs.SprayTeamDTO:team, java.lang.String:geoId, java.lang.String:leaderId, [Ljava.lang.String;:operatorIds, [Ljava.lang.String;:removedIds", post=true)
  public void failUpdateAssignments(dss.vector.solutions.irs.SprayTeamDTO team, java.lang.String geoId, java.lang.String leaderId, java.lang.String[] operatorIds, java.lang.String[] removedIds) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.irs.SprayTeamController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.irs.SprayTeamController.failUpdateAssignments");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="java.lang.String:id", post=false)
  public void view(java.lang.String id) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.irs.SprayTeamController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.irs.SprayTeamController.view");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="java.lang.String:id", post=false)
  public void failView(java.lang.String id) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.irs.SprayTeamController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.irs.SprayTeamController.failView");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="", post=false)
  public void viewAll() throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.irs.SprayTeamController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.irs.SprayTeamController.viewAll");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="", post=false)
  public void failViewAll() throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.irs.SprayTeamController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.irs.SprayTeamController.failViewAll");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="java.lang.String:sortAttribute, java.lang.Boolean:isAscending, java.lang.Integer:pageSize, java.lang.Integer:pageNumber", post=false)
  public void viewPage(java.lang.String sortAttribute, java.lang.Boolean isAscending, java.lang.Integer pageSize, java.lang.Integer pageNumber) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.irs.SprayTeamController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.irs.SprayTeamController.viewPage");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="java.lang.String:sortAttribute, java.lang.String:isAscending, java.lang.String:pageSize, java.lang.String:pageNumber", post=false)
  public void failViewPage(java.lang.String sortAttribute, java.lang.String isAscending, java.lang.String pageSize, java.lang.String pageNumber) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.irs.SprayTeamController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.irs.SprayTeamController.failViewPage");
  }
  
}
