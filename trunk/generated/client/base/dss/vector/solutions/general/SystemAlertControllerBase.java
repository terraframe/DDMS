package dss.vector.solutions.general;

@com.runwaysdk.business.ClassSignature(hash = -2067904833)
public class SystemAlertControllerBase implements com.runwaysdk.generation.loader.Reloadable
{
  public static final String CLASS = "dss.vector.solutions.general.SystemAlertController";
  protected javax.servlet.http.HttpServletRequest req;
  protected javax.servlet.http.HttpServletResponse resp;
  protected java.lang.Boolean isAsynchronous;
  protected java.lang.String dir;
  protected java.lang.String layout;
  
  private static final long serialVersionUID = -2067904833;
  
  public SystemAlertControllerBase(javax.servlet.http.HttpServletRequest req, javax.servlet.http.HttpServletResponse resp, java.lang.Boolean isAsynchronous)
  {
    this(req, resp, isAsynchronous, "","");
  }
  
  public SystemAlertControllerBase(javax.servlet.http.HttpServletRequest req, javax.servlet.http.HttpServletResponse resp, java.lang.Boolean isAsynchronous, java.lang.String dir, java.lang.String layout)
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
  
  @com.runwaysdk.controller.ActionParameters(parameters="dss.vector.solutions.general.SystemAlertDTO:dto", post=true)
  public void cancel(dss.vector.solutions.general.SystemAlertDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.general.SystemAlertController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.general.SystemAlertController.cancel");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="dss.vector.solutions.general.SystemAlertDTO:dto", post=true)
  public void failCancel(dss.vector.solutions.general.SystemAlertDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.general.SystemAlertController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.general.SystemAlertController.failCancel");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="dss.vector.solutions.general.SystemAlertDTO:dto", post=true)
  public void create(dss.vector.solutions.general.SystemAlertDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.general.SystemAlertController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.general.SystemAlertController.create");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="dss.vector.solutions.general.SystemAlertDTO:dto", post=true)
  public void failCreate(dss.vector.solutions.general.SystemAlertDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.general.SystemAlertController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.general.SystemAlertController.failCreate");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="dss.vector.solutions.general.SystemAlertDTO:dto", post=true)
  public void delete(dss.vector.solutions.general.SystemAlertDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.general.SystemAlertController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.general.SystemAlertController.delete");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="dss.vector.solutions.general.SystemAlertDTO:dto", post=true)
  public void failDelete(dss.vector.solutions.general.SystemAlertDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.general.SystemAlertController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.general.SystemAlertController.failDelete");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="java.lang.String:id", post=false)
  public void edit(java.lang.String id) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.general.SystemAlertController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.general.SystemAlertController.edit");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="java.lang.String:id", post=false)
  public void failEdit(java.lang.String id) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.general.SystemAlertController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.general.SystemAlertController.failEdit");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="", post=false)
  public void newInstance() throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.general.SystemAlertController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.general.SystemAlertController.newInstance");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="", post=false)
  public void failNewInstance() throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.general.SystemAlertController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.general.SystemAlertController.failNewInstance");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="dss.vector.solutions.general.SystemAlertDTO:dto", post=true)
  public void update(dss.vector.solutions.general.SystemAlertDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.general.SystemAlertController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.general.SystemAlertController.update");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="dss.vector.solutions.general.SystemAlertDTO:dto", post=true)
  public void failUpdate(dss.vector.solutions.general.SystemAlertDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.general.SystemAlertController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.general.SystemAlertController.failUpdate");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="java.lang.String:id", post=false)
  public void view(java.lang.String id) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.general.SystemAlertController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.general.SystemAlertController.view");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="java.lang.String:id", post=false)
  public void failView(java.lang.String id) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.general.SystemAlertController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.general.SystemAlertController.failView");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="", post=false)
  public void viewAll() throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.general.SystemAlertController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.general.SystemAlertController.viewAll");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="", post=false)
  public void failViewAll() throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.general.SystemAlertController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.general.SystemAlertController.failViewAll");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="java.lang.String:sortAttribute, java.lang.Boolean:isAscending, java.lang.Integer:pageSize, java.lang.Integer:pageNumber", post=false)
  public void viewPage(java.lang.String sortAttribute, java.lang.Boolean isAscending, java.lang.Integer pageSize, java.lang.Integer pageNumber) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.general.SystemAlertController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.general.SystemAlertController.viewPage");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="java.lang.String:sortAttribute, java.lang.String:isAscending, java.lang.String:pageSize, java.lang.String:pageNumber", post=false)
  public void failViewPage(java.lang.String sortAttribute, java.lang.String isAscending, java.lang.String pageSize, java.lang.String pageNumber) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.general.SystemAlertController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.general.SystemAlertController.failViewPage");
  }
  
}
