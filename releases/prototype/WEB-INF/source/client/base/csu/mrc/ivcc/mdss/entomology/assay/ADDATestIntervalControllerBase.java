package csu.mrc.ivcc.mdss.entomology.assay;

public class ADDATestIntervalControllerBase implements com.terraframe.mojo.generation.loader.Reloadable
{
  public static final String CLASS = "csu.mrc.ivcc.mdss.entomology.assay.ADDATestIntervalController";
  protected javax.servlet.http.HttpServletRequest req;
  protected javax.servlet.http.HttpServletResponse resp;
  protected java.lang.Boolean isAsynchronous;
  protected java.lang.String dir;
  protected java.lang.String layout;
  
  private static final long serialVersionUID = 1236612258447L;
  
  public ADDATestIntervalControllerBase(javax.servlet.http.HttpServletRequest req, javax.servlet.http.HttpServletResponse resp, java.lang.Boolean isAsynchronous)
  {
    this(req, resp, isAsynchronous, "","");
  }
  
  public ADDATestIntervalControllerBase(javax.servlet.http.HttpServletRequest req, javax.servlet.http.HttpServletResponse resp, java.lang.Boolean isAsynchronous, java.lang.String dir, java.lang.String layout)
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
    String msg = "This method should never be invoked.  It should be overwritten in csu.mrc.ivcc.mdss.entomology.assay.ADDATestIntervalController.java";
    throw new com.terraframe.mojo.controller.UndefinedControllerActionException(msg, req.getLocale(), "csu.mrc.ivcc.mdss.entomology.assay.ADDATestIntervalController.viewPage");
  }
  
  @com.terraframe.mojo.controller.ActionParameters(parameters="java.lang.String:sortAttribute, java.lang.String:isAscending, java.lang.String:pageSize, java.lang.String:pageNumber", post=false)
  public void failViewPage(java.lang.String sortAttribute, java.lang.String isAscending, java.lang.String pageSize, java.lang.String pageNumber) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in csu.mrc.ivcc.mdss.entomology.assay.ADDATestIntervalController.java";
    throw new com.terraframe.mojo.controller.UndefinedControllerActionException(msg, req.getLocale(), "csu.mrc.ivcc.mdss.entomology.assay.ADDATestIntervalController.failViewPage");
  }
  
  @com.terraframe.mojo.controller.ActionParameters(parameters="", post=false)
  public void viewAll() throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in csu.mrc.ivcc.mdss.entomology.assay.ADDATestIntervalController.java";
    throw new com.terraframe.mojo.controller.UndefinedControllerActionException(msg, req.getLocale(), "csu.mrc.ivcc.mdss.entomology.assay.ADDATestIntervalController.viewAll");
  }
  
  @com.terraframe.mojo.controller.ActionParameters(parameters="", post=false)
  public void failViewAll() throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in csu.mrc.ivcc.mdss.entomology.assay.ADDATestIntervalController.java";
    throw new com.terraframe.mojo.controller.UndefinedControllerActionException(msg, req.getLocale(), "csu.mrc.ivcc.mdss.entomology.assay.ADDATestIntervalController.failViewAll");
  }
  
  @com.terraframe.mojo.controller.ActionParameters(parameters="java.lang.String:id", post=false)
  public void view(java.lang.String id) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in csu.mrc.ivcc.mdss.entomology.assay.ADDATestIntervalController.java";
    throw new com.terraframe.mojo.controller.UndefinedControllerActionException(msg, req.getLocale(), "csu.mrc.ivcc.mdss.entomology.assay.ADDATestIntervalController.view");
  }
  
  @com.terraframe.mojo.controller.ActionParameters(parameters="java.lang.String:id", post=false)
  public void failView(java.lang.String id) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in csu.mrc.ivcc.mdss.entomology.assay.ADDATestIntervalController.java";
    throw new com.terraframe.mojo.controller.UndefinedControllerActionException(msg, req.getLocale(), "csu.mrc.ivcc.mdss.entomology.assay.ADDATestIntervalController.failView");
  }
  
  @com.terraframe.mojo.controller.ActionParameters(parameters="", post=false)
  public void newInstance() throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in csu.mrc.ivcc.mdss.entomology.assay.ADDATestIntervalController.java";
    throw new com.terraframe.mojo.controller.UndefinedControllerActionException(msg, req.getLocale(), "csu.mrc.ivcc.mdss.entomology.assay.ADDATestIntervalController.newInstance");
  }
  
  @com.terraframe.mojo.controller.ActionParameters(parameters="", post=false)
  public void failNewInstance() throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in csu.mrc.ivcc.mdss.entomology.assay.ADDATestIntervalController.java";
    throw new com.terraframe.mojo.controller.UndefinedControllerActionException(msg, req.getLocale(), "csu.mrc.ivcc.mdss.entomology.assay.ADDATestIntervalController.failNewInstance");
  }
  
  @com.terraframe.mojo.controller.ActionParameters(parameters="csu.mrc.ivcc.mdss.entomology.assay.ADDATestIntervalDTO:dto", post=true)
  public void create(csu.mrc.ivcc.mdss.entomology.assay.ADDATestIntervalDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in csu.mrc.ivcc.mdss.entomology.assay.ADDATestIntervalController.java";
    throw new com.terraframe.mojo.controller.UndefinedControllerActionException(msg, req.getLocale(), "csu.mrc.ivcc.mdss.entomology.assay.ADDATestIntervalController.create");
  }
  
  @com.terraframe.mojo.controller.ActionParameters(parameters="csu.mrc.ivcc.mdss.entomology.assay.ADDATestIntervalDTO:dto", post=true)
  public void failCreate(csu.mrc.ivcc.mdss.entomology.assay.ADDATestIntervalDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in csu.mrc.ivcc.mdss.entomology.assay.ADDATestIntervalController.java";
    throw new com.terraframe.mojo.controller.UndefinedControllerActionException(msg, req.getLocale(), "csu.mrc.ivcc.mdss.entomology.assay.ADDATestIntervalController.failCreate");
  }
  
  @com.terraframe.mojo.controller.ActionParameters(parameters="java.lang.String:id", post=true)
  public void edit(java.lang.String id) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in csu.mrc.ivcc.mdss.entomology.assay.ADDATestIntervalController.java";
    throw new com.terraframe.mojo.controller.UndefinedControllerActionException(msg, req.getLocale(), "csu.mrc.ivcc.mdss.entomology.assay.ADDATestIntervalController.edit");
  }
  
  @com.terraframe.mojo.controller.ActionParameters(parameters="java.lang.String:id", post=true)
  public void failEdit(java.lang.String id) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in csu.mrc.ivcc.mdss.entomology.assay.ADDATestIntervalController.java";
    throw new com.terraframe.mojo.controller.UndefinedControllerActionException(msg, req.getLocale(), "csu.mrc.ivcc.mdss.entomology.assay.ADDATestIntervalController.failEdit");
  }
  
  @com.terraframe.mojo.controller.ActionParameters(parameters="csu.mrc.ivcc.mdss.entomology.assay.ADDATestIntervalDTO:dto", post=true)
  public void update(csu.mrc.ivcc.mdss.entomology.assay.ADDATestIntervalDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in csu.mrc.ivcc.mdss.entomology.assay.ADDATestIntervalController.java";
    throw new com.terraframe.mojo.controller.UndefinedControllerActionException(msg, req.getLocale(), "csu.mrc.ivcc.mdss.entomology.assay.ADDATestIntervalController.update");
  }
  
  @com.terraframe.mojo.controller.ActionParameters(parameters="csu.mrc.ivcc.mdss.entomology.assay.ADDATestIntervalDTO:dto", post=true)
  public void failUpdate(csu.mrc.ivcc.mdss.entomology.assay.ADDATestIntervalDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in csu.mrc.ivcc.mdss.entomology.assay.ADDATestIntervalController.java";
    throw new com.terraframe.mojo.controller.UndefinedControllerActionException(msg, req.getLocale(), "csu.mrc.ivcc.mdss.entomology.assay.ADDATestIntervalController.failUpdate");
  }
  
  @com.terraframe.mojo.controller.ActionParameters(parameters="csu.mrc.ivcc.mdss.entomology.assay.ADDATestIntervalDTO:dto", post=true)
  public void cancel(csu.mrc.ivcc.mdss.entomology.assay.ADDATestIntervalDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in csu.mrc.ivcc.mdss.entomology.assay.ADDATestIntervalController.java";
    throw new com.terraframe.mojo.controller.UndefinedControllerActionException(msg, req.getLocale(), "csu.mrc.ivcc.mdss.entomology.assay.ADDATestIntervalController.cancel");
  }
  
  @com.terraframe.mojo.controller.ActionParameters(parameters="csu.mrc.ivcc.mdss.entomology.assay.ADDATestIntervalDTO:dto", post=true)
  public void failCancel(csu.mrc.ivcc.mdss.entomology.assay.ADDATestIntervalDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in csu.mrc.ivcc.mdss.entomology.assay.ADDATestIntervalController.java";
    throw new com.terraframe.mojo.controller.UndefinedControllerActionException(msg, req.getLocale(), "csu.mrc.ivcc.mdss.entomology.assay.ADDATestIntervalController.failCancel");
  }
  
  @com.terraframe.mojo.controller.ActionParameters(parameters="csu.mrc.ivcc.mdss.entomology.assay.ADDATestIntervalDTO:dto", post=true)
  public void delete(csu.mrc.ivcc.mdss.entomology.assay.ADDATestIntervalDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in csu.mrc.ivcc.mdss.entomology.assay.ADDATestIntervalController.java";
    throw new com.terraframe.mojo.controller.UndefinedControllerActionException(msg, req.getLocale(), "csu.mrc.ivcc.mdss.entomology.assay.ADDATestIntervalController.delete");
  }
  
  @com.terraframe.mojo.controller.ActionParameters(parameters="csu.mrc.ivcc.mdss.entomology.assay.ADDATestIntervalDTO:dto", post=true)
  public void failDelete(csu.mrc.ivcc.mdss.entomology.assay.ADDATestIntervalDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in csu.mrc.ivcc.mdss.entomology.assay.ADDATestIntervalController.java";
    throw new com.terraframe.mojo.controller.UndefinedControllerActionException(msg, req.getLocale(), "csu.mrc.ivcc.mdss.entomology.assay.ADDATestIntervalController.failDelete");
  }
  
}
