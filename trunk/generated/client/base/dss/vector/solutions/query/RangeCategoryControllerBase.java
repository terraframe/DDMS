package dss.vector.solutions.query;

@com.terraframe.mojo.business.ClassSignature(hash = -293037371)
public class RangeCategoryControllerBase implements com.terraframe.mojo.generation.loader.Reloadable
{
  public static final String CLASS = "dss.vector.solutions.query.RangeCategoryController";
  protected javax.servlet.http.HttpServletRequest req;
  protected javax.servlet.http.HttpServletResponse resp;
  protected java.lang.Boolean isAsynchronous;
  protected java.lang.String dir;
  protected java.lang.String layout;
  
  private static final long serialVersionUID = -293037371;
  
  public RangeCategoryControllerBase(javax.servlet.http.HttpServletRequest req, javax.servlet.http.HttpServletResponse resp, java.lang.Boolean isAsynchronous)
  {
    this(req, resp, isAsynchronous, "","");
  }
  
  public RangeCategoryControllerBase(javax.servlet.http.HttpServletRequest req, javax.servlet.http.HttpServletResponse resp, java.lang.Boolean isAsynchronous, java.lang.String dir, java.lang.String layout)
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
  
  @com.terraframe.mojo.controller.ActionParameters(parameters="dss.vector.solutions.query.RangeCategoryDTO:dto", post=true)
  public void cancel(dss.vector.solutions.query.RangeCategoryDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.query.RangeCategoryController.java";
    throw new com.terraframe.mojo.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.query.RangeCategoryController.cancel");
  }
  
  @com.terraframe.mojo.controller.ActionParameters(parameters="dss.vector.solutions.query.RangeCategoryDTO:dto", post=true)
  public void failCancel(dss.vector.solutions.query.RangeCategoryDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.query.RangeCategoryController.java";
    throw new com.terraframe.mojo.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.query.RangeCategoryController.failCancel");
  }
  
  @com.terraframe.mojo.controller.ActionParameters(parameters="dss.vector.solutions.query.RangeCategoryDTO:dto", post=true)
  public void create(dss.vector.solutions.query.RangeCategoryDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.query.RangeCategoryController.java";
    throw new com.terraframe.mojo.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.query.RangeCategoryController.create");
  }
  
  @com.terraframe.mojo.controller.ActionParameters(parameters="dss.vector.solutions.query.RangeCategoryDTO:dto", post=true)
  public void failCreate(dss.vector.solutions.query.RangeCategoryDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.query.RangeCategoryController.java";
    throw new com.terraframe.mojo.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.query.RangeCategoryController.failCreate");
  }
  
  @com.terraframe.mojo.controller.ActionParameters(parameters="dss.vector.solutions.query.RangeCategoryDTO:dto", post=true)
  public void delete(dss.vector.solutions.query.RangeCategoryDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.query.RangeCategoryController.java";
    throw new com.terraframe.mojo.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.query.RangeCategoryController.delete");
  }
  
  @com.terraframe.mojo.controller.ActionParameters(parameters="dss.vector.solutions.query.RangeCategoryDTO:dto", post=true)
  public void failDelete(dss.vector.solutions.query.RangeCategoryDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.query.RangeCategoryController.java";
    throw new com.terraframe.mojo.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.query.RangeCategoryController.failDelete");
  }
  
  @com.terraframe.mojo.controller.ActionParameters(parameters="java.lang.String:id", post=false)
  public void edit(java.lang.String id) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.query.RangeCategoryController.java";
    throw new com.terraframe.mojo.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.query.RangeCategoryController.edit");
  }
  
  @com.terraframe.mojo.controller.ActionParameters(parameters="java.lang.String:id", post=false)
  public void failEdit(java.lang.String id) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.query.RangeCategoryController.java";
    throw new com.terraframe.mojo.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.query.RangeCategoryController.failEdit");
  }
  
  @com.terraframe.mojo.controller.ActionParameters(parameters="", post=false)
  public void newInstance() throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.query.RangeCategoryController.java";
    throw new com.terraframe.mojo.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.query.RangeCategoryController.newInstance");
  }
  
  @com.terraframe.mojo.controller.ActionParameters(parameters="", post=false)
  public void failNewInstance() throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.query.RangeCategoryController.java";
    throw new com.terraframe.mojo.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.query.RangeCategoryController.failNewInstance");
  }
  
  @com.terraframe.mojo.controller.ActionParameters(parameters="dss.vector.solutions.query.RangeCategoryDTO:dto", post=true)
  public void update(dss.vector.solutions.query.RangeCategoryDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.query.RangeCategoryController.java";
    throw new com.terraframe.mojo.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.query.RangeCategoryController.update");
  }
  
  @com.terraframe.mojo.controller.ActionParameters(parameters="dss.vector.solutions.query.RangeCategoryDTO:dto", post=true)
  public void failUpdate(dss.vector.solutions.query.RangeCategoryDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.query.RangeCategoryController.java";
    throw new com.terraframe.mojo.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.query.RangeCategoryController.failUpdate");
  }
  
  @com.terraframe.mojo.controller.ActionParameters(parameters="java.lang.String:id", post=false)
  public void view(java.lang.String id) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.query.RangeCategoryController.java";
    throw new com.terraframe.mojo.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.query.RangeCategoryController.view");
  }
  
  @com.terraframe.mojo.controller.ActionParameters(parameters="java.lang.String:id", post=false)
  public void failView(java.lang.String id) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.query.RangeCategoryController.java";
    throw new com.terraframe.mojo.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.query.RangeCategoryController.failView");
  }
  
  @com.terraframe.mojo.controller.ActionParameters(parameters="", post=false)
  public void viewAll() throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.query.RangeCategoryController.java";
    throw new com.terraframe.mojo.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.query.RangeCategoryController.viewAll");
  }
  
  @com.terraframe.mojo.controller.ActionParameters(parameters="", post=false)
  public void failViewAll() throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.query.RangeCategoryController.java";
    throw new com.terraframe.mojo.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.query.RangeCategoryController.failViewAll");
  }
  
  @com.terraframe.mojo.controller.ActionParameters(parameters="java.lang.String:sortAttribute, java.lang.Boolean:isAscending, java.lang.Integer:pageSize, java.lang.Integer:pageNumber", post=false)
  public void viewPage(java.lang.String sortAttribute, java.lang.Boolean isAscending, java.lang.Integer pageSize, java.lang.Integer pageNumber) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.query.RangeCategoryController.java";
    throw new com.terraframe.mojo.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.query.RangeCategoryController.viewPage");
  }
  
  @com.terraframe.mojo.controller.ActionParameters(parameters="java.lang.String:sortAttribute, java.lang.String:isAscending, java.lang.String:pageSize, java.lang.String:pageNumber", post=false)
  public void failViewPage(java.lang.String sortAttribute, java.lang.String isAscending, java.lang.String pageSize, java.lang.String pageNumber) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.query.RangeCategoryController.java";
    throw new com.terraframe.mojo.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.query.RangeCategoryController.failViewPage");
  }
  
}
