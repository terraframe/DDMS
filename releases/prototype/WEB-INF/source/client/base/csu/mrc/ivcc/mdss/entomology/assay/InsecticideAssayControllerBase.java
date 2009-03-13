package csu.mrc.ivcc.mdss.entomology.assay;

public class InsecticideAssayControllerBase implements com.terraframe.mojo.generation.loader.Reloadable
{
  public static final String CLASS = "csu.mrc.ivcc.mdss.entomology.assay.InsecticideAssayController";
  protected javax.servlet.http.HttpServletRequest req;
  protected javax.servlet.http.HttpServletResponse resp;
  protected java.lang.Boolean isAsynchronous;
  protected java.lang.String dir;
  protected java.lang.String layout;
  
  private static final long serialVersionUID = 1236807468212L;
  
  public InsecticideAssayControllerBase(javax.servlet.http.HttpServletRequest req, javax.servlet.http.HttpServletResponse resp, java.lang.Boolean isAsynchronous)
  {
    this(req, resp, isAsynchronous, "","");
  }
  
  public InsecticideAssayControllerBase(javax.servlet.http.HttpServletRequest req, javax.servlet.http.HttpServletResponse resp, java.lang.Boolean isAsynchronous, java.lang.String dir, java.lang.String layout)
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
    String msg = "This method should never be invoked.  It should be overwritten in csu.mrc.ivcc.mdss.entomology.assay.InsecticideAssayController.java";
    throw new com.terraframe.mojo.controller.UndefinedControllerActionException(msg, req.getLocale(), "csu.mrc.ivcc.mdss.entomology.assay.InsecticideAssayController.viewPage");
  }
  
  @com.terraframe.mojo.controller.ActionParameters(parameters="java.lang.String:sortAttribute, java.lang.String:isAscending, java.lang.String:pageSize, java.lang.String:pageNumber", post=false)
  public void failViewPage(java.lang.String sortAttribute, java.lang.String isAscending, java.lang.String pageSize, java.lang.String pageNumber) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in csu.mrc.ivcc.mdss.entomology.assay.InsecticideAssayController.java";
    throw new com.terraframe.mojo.controller.UndefinedControllerActionException(msg, req.getLocale(), "csu.mrc.ivcc.mdss.entomology.assay.InsecticideAssayController.failViewPage");
  }
  
  @com.terraframe.mojo.controller.ActionParameters(parameters="", post=false)
  public void viewAll() throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in csu.mrc.ivcc.mdss.entomology.assay.InsecticideAssayController.java";
    throw new com.terraframe.mojo.controller.UndefinedControllerActionException(msg, req.getLocale(), "csu.mrc.ivcc.mdss.entomology.assay.InsecticideAssayController.viewAll");
  }
  
  @com.terraframe.mojo.controller.ActionParameters(parameters="", post=false)
  public void failViewAll() throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in csu.mrc.ivcc.mdss.entomology.assay.InsecticideAssayController.java";
    throw new com.terraframe.mojo.controller.UndefinedControllerActionException(msg, req.getLocale(), "csu.mrc.ivcc.mdss.entomology.assay.InsecticideAssayController.failViewAll");
  }
  
  @com.terraframe.mojo.controller.ActionParameters(parameters="java.lang.String:id", post=false)
  public void view(java.lang.String id) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in csu.mrc.ivcc.mdss.entomology.assay.InsecticideAssayController.java";
    throw new com.terraframe.mojo.controller.UndefinedControllerActionException(msg, req.getLocale(), "csu.mrc.ivcc.mdss.entomology.assay.InsecticideAssayController.view");
  }
  
  @com.terraframe.mojo.controller.ActionParameters(parameters="java.lang.String:id", post=false)
  public void failView(java.lang.String id) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in csu.mrc.ivcc.mdss.entomology.assay.InsecticideAssayController.java";
    throw new com.terraframe.mojo.controller.UndefinedControllerActionException(msg, req.getLocale(), "csu.mrc.ivcc.mdss.entomology.assay.InsecticideAssayController.failView");
  }
  
  @com.terraframe.mojo.controller.ActionParameters(parameters="csu.mrc.ivcc.mdss.entomology.assay.InsecticideAssayDTO:dto", post=true)
  public void create(csu.mrc.ivcc.mdss.entomology.assay.InsecticideAssayDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in csu.mrc.ivcc.mdss.entomology.assay.InsecticideAssayController.java";
    throw new com.terraframe.mojo.controller.UndefinedControllerActionException(msg, req.getLocale(), "csu.mrc.ivcc.mdss.entomology.assay.InsecticideAssayController.create");
  }
  
  @com.terraframe.mojo.controller.ActionParameters(parameters="csu.mrc.ivcc.mdss.entomology.assay.InsecticideAssayDTO:dto", post=true)
  public void failCreate(csu.mrc.ivcc.mdss.entomology.assay.InsecticideAssayDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in csu.mrc.ivcc.mdss.entomology.assay.InsecticideAssayController.java";
    throw new com.terraframe.mojo.controller.UndefinedControllerActionException(msg, req.getLocale(), "csu.mrc.ivcc.mdss.entomology.assay.InsecticideAssayController.failCreate");
  }
  
  @com.terraframe.mojo.controller.ActionParameters(parameters="java.lang.String:id", post=true)
  public void edit(java.lang.String id) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in csu.mrc.ivcc.mdss.entomology.assay.InsecticideAssayController.java";
    throw new com.terraframe.mojo.controller.UndefinedControllerActionException(msg, req.getLocale(), "csu.mrc.ivcc.mdss.entomology.assay.InsecticideAssayController.edit");
  }
  
  @com.terraframe.mojo.controller.ActionParameters(parameters="java.lang.String:id", post=true)
  public void failEdit(java.lang.String id) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in csu.mrc.ivcc.mdss.entomology.assay.InsecticideAssayController.java";
    throw new com.terraframe.mojo.controller.UndefinedControllerActionException(msg, req.getLocale(), "csu.mrc.ivcc.mdss.entomology.assay.InsecticideAssayController.failEdit");
  }
  
  @com.terraframe.mojo.controller.ActionParameters(parameters="csu.mrc.ivcc.mdss.entomology.assay.InsecticideAssayDTO:dto", post=true)
  public void update(csu.mrc.ivcc.mdss.entomology.assay.InsecticideAssayDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in csu.mrc.ivcc.mdss.entomology.assay.InsecticideAssayController.java";
    throw new com.terraframe.mojo.controller.UndefinedControllerActionException(msg, req.getLocale(), "csu.mrc.ivcc.mdss.entomology.assay.InsecticideAssayController.update");
  }
  
  @com.terraframe.mojo.controller.ActionParameters(parameters="csu.mrc.ivcc.mdss.entomology.assay.InsecticideAssayDTO:dto", post=true)
  public void failUpdate(csu.mrc.ivcc.mdss.entomology.assay.InsecticideAssayDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in csu.mrc.ivcc.mdss.entomology.assay.InsecticideAssayController.java";
    throw new com.terraframe.mojo.controller.UndefinedControllerActionException(msg, req.getLocale(), "csu.mrc.ivcc.mdss.entomology.assay.InsecticideAssayController.failUpdate");
  }
  
  @com.terraframe.mojo.controller.ActionParameters(parameters="csu.mrc.ivcc.mdss.entomology.assay.InsecticideAssayDTO:dto", post=true)
  public void cancel(csu.mrc.ivcc.mdss.entomology.assay.InsecticideAssayDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in csu.mrc.ivcc.mdss.entomology.assay.InsecticideAssayController.java";
    throw new com.terraframe.mojo.controller.UndefinedControllerActionException(msg, req.getLocale(), "csu.mrc.ivcc.mdss.entomology.assay.InsecticideAssayController.cancel");
  }
  
  @com.terraframe.mojo.controller.ActionParameters(parameters="csu.mrc.ivcc.mdss.entomology.assay.InsecticideAssayDTO:dto", post=true)
  public void failCancel(csu.mrc.ivcc.mdss.entomology.assay.InsecticideAssayDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in csu.mrc.ivcc.mdss.entomology.assay.InsecticideAssayController.java";
    throw new com.terraframe.mojo.controller.UndefinedControllerActionException(msg, req.getLocale(), "csu.mrc.ivcc.mdss.entomology.assay.InsecticideAssayController.failCancel");
  }
  
  @com.terraframe.mojo.controller.ActionParameters(parameters="csu.mrc.ivcc.mdss.entomology.assay.InsecticideAssayDTO:dto", post=true)
  public void delete(csu.mrc.ivcc.mdss.entomology.assay.InsecticideAssayDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in csu.mrc.ivcc.mdss.entomology.assay.InsecticideAssayController.java";
    throw new com.terraframe.mojo.controller.UndefinedControllerActionException(msg, req.getLocale(), "csu.mrc.ivcc.mdss.entomology.assay.InsecticideAssayController.delete");
  }
  
  @com.terraframe.mojo.controller.ActionParameters(parameters="csu.mrc.ivcc.mdss.entomology.assay.InsecticideAssayDTO:dto", post=true)
  public void failDelete(csu.mrc.ivcc.mdss.entomology.assay.InsecticideAssayDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in csu.mrc.ivcc.mdss.entomology.assay.InsecticideAssayController.java";
    throw new com.terraframe.mojo.controller.UndefinedControllerActionException(msg, req.getLocale(), "csu.mrc.ivcc.mdss.entomology.assay.InsecticideAssayController.failDelete");
  }
  
}
