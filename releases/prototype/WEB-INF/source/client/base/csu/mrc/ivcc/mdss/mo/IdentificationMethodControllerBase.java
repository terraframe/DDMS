package csu.mrc.ivcc.mdss.mo;

public class IdentificationMethodControllerBase implements com.terraframe.mojo.generation.loader.Reloadable
{
  public static final String CLASS = "csu.mrc.ivcc.mdss.mo.IdentificationMethodController";
  protected javax.servlet.http.HttpServletRequest req;
  protected javax.servlet.http.HttpServletResponse resp;
  protected java.lang.Boolean isAsynchronous;
  protected java.lang.String dir;
  protected java.lang.String layout;
  
  private static final long serialVersionUID = 1236360387019L;
  
  public IdentificationMethodControllerBase(javax.servlet.http.HttpServletRequest req, javax.servlet.http.HttpServletResponse resp, java.lang.Boolean isAsynchronous)
  {
    this(req, resp, isAsynchronous, "","");
  }
  
  public IdentificationMethodControllerBase(javax.servlet.http.HttpServletRequest req, javax.servlet.http.HttpServletResponse resp, java.lang.Boolean isAsynchronous, java.lang.String dir, java.lang.String layout)
  {
    this.req = req;
    this.resp = resp;
    this.isAsynchronous = isAsynchronous;
    this.dir = dir;
    this.layout = layout;
  }
  
  protected void render(String jsp) throws java.io.IOException, javax.servlet.ServletException
  {
    if(this.isAsynchronous())
    {
      req.getRequestDispatcher(dir+jsp).forward(req, resp);
    }
    else
    {
      req.setAttribute("jsp", jsp);
      req.getRequestDispatcher(layout).forward(req, resp);
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
  
  @com.terraframe.mojo.controller.ActionParameters(parameters="java.lang.String:sortAttribute, java.lang.Boolean:isAscending, java.lang.Integer:pageSize, java.lang.Integer:pageNumber", post=false)
  public void viewPage(java.lang.String sortAttribute, java.lang.Boolean isAscending, java.lang.Integer pageSize, java.lang.Integer pageNumber) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in csu.mrc.ivcc.mdss.mo.IdentificationMethodController.java";
    throw new com.terraframe.mojo.controller.UndefinedControllerActionException(msg, req.getLocale(), "csu.mrc.ivcc.mdss.mo.IdentificationMethodController.viewPage");
  }
  
  @com.terraframe.mojo.controller.ActionParameters(parameters="java.lang.String:sortAttribute, java.lang.String:isAscending, java.lang.String:pageSize, java.lang.String:pageNumber", post=false)
  public void failViewPage(java.lang.String sortAttribute, java.lang.String isAscending, java.lang.String pageSize, java.lang.String pageNumber) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in csu.mrc.ivcc.mdss.mo.IdentificationMethodController.java";
    throw new com.terraframe.mojo.controller.UndefinedControllerActionException(msg, req.getLocale(), "csu.mrc.ivcc.mdss.mo.IdentificationMethodController.failViewPage");
  }
  
  @com.terraframe.mojo.controller.ActionParameters(parameters="", post=false)
  public void viewAll() throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in csu.mrc.ivcc.mdss.mo.IdentificationMethodController.java";
    throw new com.terraframe.mojo.controller.UndefinedControllerActionException(msg, req.getLocale(), "csu.mrc.ivcc.mdss.mo.IdentificationMethodController.viewAll");
  }
  
  @com.terraframe.mojo.controller.ActionParameters(parameters="", post=false)
  public void failViewAll() throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in csu.mrc.ivcc.mdss.mo.IdentificationMethodController.java";
    throw new com.terraframe.mojo.controller.UndefinedControllerActionException(msg, req.getLocale(), "csu.mrc.ivcc.mdss.mo.IdentificationMethodController.failViewAll");
  }
  
  @com.terraframe.mojo.controller.ActionParameters(parameters="java.lang.String:id", post=false)
  public void view(java.lang.String id) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in csu.mrc.ivcc.mdss.mo.IdentificationMethodController.java";
    throw new com.terraframe.mojo.controller.UndefinedControllerActionException(msg, req.getLocale(), "csu.mrc.ivcc.mdss.mo.IdentificationMethodController.view");
  }
  
  @com.terraframe.mojo.controller.ActionParameters(parameters="java.lang.String:id", post=false)
  public void failView(java.lang.String id) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in csu.mrc.ivcc.mdss.mo.IdentificationMethodController.java";
    throw new com.terraframe.mojo.controller.UndefinedControllerActionException(msg, req.getLocale(), "csu.mrc.ivcc.mdss.mo.IdentificationMethodController.failView");
  }
  
  @com.terraframe.mojo.controller.ActionParameters(parameters="", post=false)
  public void newInstance() throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in csu.mrc.ivcc.mdss.mo.IdentificationMethodController.java";
    throw new com.terraframe.mojo.controller.UndefinedControllerActionException(msg, req.getLocale(), "csu.mrc.ivcc.mdss.mo.IdentificationMethodController.newInstance");
  }
  
  @com.terraframe.mojo.controller.ActionParameters(parameters="", post=false)
  public void failNewInstance() throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in csu.mrc.ivcc.mdss.mo.IdentificationMethodController.java";
    throw new com.terraframe.mojo.controller.UndefinedControllerActionException(msg, req.getLocale(), "csu.mrc.ivcc.mdss.mo.IdentificationMethodController.failNewInstance");
  }
  
  @com.terraframe.mojo.controller.ActionParameters(parameters="csu.mrc.ivcc.mdss.mo.IdentificationMethodDTO:dto", post=true)
  public void create(csu.mrc.ivcc.mdss.mo.IdentificationMethodDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in csu.mrc.ivcc.mdss.mo.IdentificationMethodController.java";
    throw new com.terraframe.mojo.controller.UndefinedControllerActionException(msg, req.getLocale(), "csu.mrc.ivcc.mdss.mo.IdentificationMethodController.create");
  }
  
  @com.terraframe.mojo.controller.ActionParameters(parameters="csu.mrc.ivcc.mdss.mo.IdentificationMethodDTO:dto", post=true)
  public void failCreate(csu.mrc.ivcc.mdss.mo.IdentificationMethodDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in csu.mrc.ivcc.mdss.mo.IdentificationMethodController.java";
    throw new com.terraframe.mojo.controller.UndefinedControllerActionException(msg, req.getLocale(), "csu.mrc.ivcc.mdss.mo.IdentificationMethodController.failCreate");
  }
  
  @com.terraframe.mojo.controller.ActionParameters(parameters="java.lang.String:id", post=true)
  public void edit(java.lang.String id) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in csu.mrc.ivcc.mdss.mo.IdentificationMethodController.java";
    throw new com.terraframe.mojo.controller.UndefinedControllerActionException(msg, req.getLocale(), "csu.mrc.ivcc.mdss.mo.IdentificationMethodController.edit");
  }
  
  @com.terraframe.mojo.controller.ActionParameters(parameters="java.lang.String:id", post=true)
  public void failEdit(java.lang.String id) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in csu.mrc.ivcc.mdss.mo.IdentificationMethodController.java";
    throw new com.terraframe.mojo.controller.UndefinedControllerActionException(msg, req.getLocale(), "csu.mrc.ivcc.mdss.mo.IdentificationMethodController.failEdit");
  }
  
  @com.terraframe.mojo.controller.ActionParameters(parameters="csu.mrc.ivcc.mdss.mo.IdentificationMethodDTO:dto", post=true)
  public void update(csu.mrc.ivcc.mdss.mo.IdentificationMethodDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in csu.mrc.ivcc.mdss.mo.IdentificationMethodController.java";
    throw new com.terraframe.mojo.controller.UndefinedControllerActionException(msg, req.getLocale(), "csu.mrc.ivcc.mdss.mo.IdentificationMethodController.update");
  }
  
  @com.terraframe.mojo.controller.ActionParameters(parameters="csu.mrc.ivcc.mdss.mo.IdentificationMethodDTO:dto", post=true)
  public void failUpdate(csu.mrc.ivcc.mdss.mo.IdentificationMethodDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in csu.mrc.ivcc.mdss.mo.IdentificationMethodController.java";
    throw new com.terraframe.mojo.controller.UndefinedControllerActionException(msg, req.getLocale(), "csu.mrc.ivcc.mdss.mo.IdentificationMethodController.failUpdate");
  }
  
  @com.terraframe.mojo.controller.ActionParameters(parameters="csu.mrc.ivcc.mdss.mo.IdentificationMethodDTO:dto", post=true)
  public void cancel(csu.mrc.ivcc.mdss.mo.IdentificationMethodDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in csu.mrc.ivcc.mdss.mo.IdentificationMethodController.java";
    throw new com.terraframe.mojo.controller.UndefinedControllerActionException(msg, req.getLocale(), "csu.mrc.ivcc.mdss.mo.IdentificationMethodController.cancel");
  }
  
  @com.terraframe.mojo.controller.ActionParameters(parameters="csu.mrc.ivcc.mdss.mo.IdentificationMethodDTO:dto", post=true)
  public void failCancel(csu.mrc.ivcc.mdss.mo.IdentificationMethodDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in csu.mrc.ivcc.mdss.mo.IdentificationMethodController.java";
    throw new com.terraframe.mojo.controller.UndefinedControllerActionException(msg, req.getLocale(), "csu.mrc.ivcc.mdss.mo.IdentificationMethodController.failCancel");
  }
  
  @com.terraframe.mojo.controller.ActionParameters(parameters="csu.mrc.ivcc.mdss.mo.IdentificationMethodDTO:dto", post=true)
  public void delete(csu.mrc.ivcc.mdss.mo.IdentificationMethodDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in csu.mrc.ivcc.mdss.mo.IdentificationMethodController.java";
    throw new com.terraframe.mojo.controller.UndefinedControllerActionException(msg, req.getLocale(), "csu.mrc.ivcc.mdss.mo.IdentificationMethodController.delete");
  }
  
  @com.terraframe.mojo.controller.ActionParameters(parameters="csu.mrc.ivcc.mdss.mo.IdentificationMethodDTO:dto", post=true)
  public void failDelete(csu.mrc.ivcc.mdss.mo.IdentificationMethodDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in csu.mrc.ivcc.mdss.mo.IdentificationMethodController.java";
    throw new com.terraframe.mojo.controller.UndefinedControllerActionException(msg, req.getLocale(), "csu.mrc.ivcc.mdss.mo.IdentificationMethodController.failDelete");
  }
  
}
