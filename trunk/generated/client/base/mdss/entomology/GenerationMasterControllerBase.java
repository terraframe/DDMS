package mdss.entomology;

public class GenerationMasterControllerBase implements com.terraframe.mojo.generation.loader.Reloadable
{
  public static final String CLASS = "mdss.entomology.GenerationMasterController";
  protected javax.servlet.http.HttpServletRequest req;
  protected javax.servlet.http.HttpServletResponse resp;
  
  private static final long serialVersionUID = 1234203355509L;
  
  public GenerationMasterControllerBase(javax.servlet.http.HttpServletRequest req, javax.servlet.http.HttpServletResponse resp)
  {
    this.req = req;
    this.resp = resp;
  }
  
  public javax.servlet.http.HttpServletRequest getRequest()
  {
    return this.req;
  }
  
  public javax.servlet.http.HttpServletResponse getResponse()
  {
    return this.resp;
  }
  
  public com.terraframe.mojo.constants.ClientRequestIF getClientRequest()
  {
    return (com.terraframe.mojo.constants.ClientRequestIF) req.getAttribute(com.terraframe.mojo.constants.ClientConstants.CLIENTREQUEST);
  }
  
  public com.terraframe.mojo.ClientSession getClientSession()
  {
    return (com.terraframe.mojo.ClientSession) req.getSession().getAttribute(com.terraframe.mojo.constants.ClientConstants.CLIENTSESSION);
  }
  
  @com.terraframe.mojo.controller.ActionParameters(parameters="java.lang.String:id", post=true)
  public void edit(java.lang.String id) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in mdss.entomology.GenerationMasterController.java";
    throw new com.terraframe.mojo.controller.UndefinedControllerActionException(msg, req.getLocale(), "mdss.entomology.GenerationMasterController.edit");
  }
  
  @com.terraframe.mojo.controller.ActionParameters(parameters="java.lang.String:id", post=true)
  public void failEdit(java.lang.String id) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in mdss.entomology.GenerationMasterController.java";
    throw new com.terraframe.mojo.controller.UndefinedControllerActionException(msg, req.getLocale(), "mdss.entomology.GenerationMasterController.failEdit");
  }
  
  @com.terraframe.mojo.controller.ActionParameters(parameters="java.lang.String:sortAttribute, java.lang.Boolean:isAscending, java.lang.Integer:pageSize, java.lang.Integer:pageNumber", post=false)
  public void viewPage(java.lang.String sortAttribute, java.lang.Boolean isAscending, java.lang.Integer pageSize, java.lang.Integer pageNumber) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in mdss.entomology.GenerationMasterController.java";
    throw new com.terraframe.mojo.controller.UndefinedControllerActionException(msg, req.getLocale(), "mdss.entomology.GenerationMasterController.viewPage");
  }
  
  @com.terraframe.mojo.controller.ActionParameters(parameters="java.lang.String:sortAttribute, java.lang.String:isAscending, java.lang.String:pageSize, java.lang.String:pageNumber", post=false)
  public void failViewPage(java.lang.String sortAttribute, java.lang.String isAscending, java.lang.String pageSize, java.lang.String pageNumber) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in mdss.entomology.GenerationMasterController.java";
    throw new com.terraframe.mojo.controller.UndefinedControllerActionException(msg, req.getLocale(), "mdss.entomology.GenerationMasterController.failViewPage");
  }
  
  @com.terraframe.mojo.controller.ActionParameters(parameters="mdss.entomology.GenerationMasterDTO:dto", post=true)
  public void cancel(mdss.entomology.GenerationMasterDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in mdss.entomology.GenerationMasterController.java";
    throw new com.terraframe.mojo.controller.UndefinedControllerActionException(msg, req.getLocale(), "mdss.entomology.GenerationMasterController.cancel");
  }
  
  @com.terraframe.mojo.controller.ActionParameters(parameters="mdss.entomology.GenerationMasterDTO:dto", post=true)
  public void failCancel(mdss.entomology.GenerationMasterDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in mdss.entomology.GenerationMasterController.java";
    throw new com.terraframe.mojo.controller.UndefinedControllerActionException(msg, req.getLocale(), "mdss.entomology.GenerationMasterController.failCancel");
  }
  
  @com.terraframe.mojo.controller.ActionParameters(parameters="", post=false)
  public void viewAll() throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in mdss.entomology.GenerationMasterController.java";
    throw new com.terraframe.mojo.controller.UndefinedControllerActionException(msg, req.getLocale(), "mdss.entomology.GenerationMasterController.viewAll");
  }
  
  @com.terraframe.mojo.controller.ActionParameters(parameters="", post=false)
  public void failViewAll() throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in mdss.entomology.GenerationMasterController.java";
    throw new com.terraframe.mojo.controller.UndefinedControllerActionException(msg, req.getLocale(), "mdss.entomology.GenerationMasterController.failViewAll");
  }
  
  @com.terraframe.mojo.controller.ActionParameters(parameters="", post=false)
  public void newInstance() throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in mdss.entomology.GenerationMasterController.java";
    throw new com.terraframe.mojo.controller.UndefinedControllerActionException(msg, req.getLocale(), "mdss.entomology.GenerationMasterController.newInstance");
  }
  
  @com.terraframe.mojo.controller.ActionParameters(parameters="", post=false)
  public void failNewInstance() throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in mdss.entomology.GenerationMasterController.java";
    throw new com.terraframe.mojo.controller.UndefinedControllerActionException(msg, req.getLocale(), "mdss.entomology.GenerationMasterController.failNewInstance");
  }
  
  @com.terraframe.mojo.controller.ActionParameters(parameters="mdss.entomology.GenerationMasterDTO:dto", post=true)
  public void update(mdss.entomology.GenerationMasterDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in mdss.entomology.GenerationMasterController.java";
    throw new com.terraframe.mojo.controller.UndefinedControllerActionException(msg, req.getLocale(), "mdss.entomology.GenerationMasterController.update");
  }
  
  @com.terraframe.mojo.controller.ActionParameters(parameters="mdss.entomology.GenerationMasterDTO:dto", post=true)
  public void failUpdate(mdss.entomology.GenerationMasterDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in mdss.entomology.GenerationMasterController.java";
    throw new com.terraframe.mojo.controller.UndefinedControllerActionException(msg, req.getLocale(), "mdss.entomology.GenerationMasterController.failUpdate");
  }
  
  @com.terraframe.mojo.controller.ActionParameters(parameters="java.lang.String:id", post=false)
  public void view(java.lang.String id) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in mdss.entomology.GenerationMasterController.java";
    throw new com.terraframe.mojo.controller.UndefinedControllerActionException(msg, req.getLocale(), "mdss.entomology.GenerationMasterController.view");
  }
  
  @com.terraframe.mojo.controller.ActionParameters(parameters="java.lang.String:id", post=false)
  public void failView(java.lang.String id) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in mdss.entomology.GenerationMasterController.java";
    throw new com.terraframe.mojo.controller.UndefinedControllerActionException(msg, req.getLocale(), "mdss.entomology.GenerationMasterController.failView");
  }
  
  @com.terraframe.mojo.controller.ActionParameters(parameters="mdss.entomology.GenerationMasterDTO:dto", post=true)
  public void create(mdss.entomology.GenerationMasterDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in mdss.entomology.GenerationMasterController.java";
    throw new com.terraframe.mojo.controller.UndefinedControllerActionException(msg, req.getLocale(), "mdss.entomology.GenerationMasterController.create");
  }
  
  @com.terraframe.mojo.controller.ActionParameters(parameters="mdss.entomology.GenerationMasterDTO:dto", post=true)
  public void failCreate(mdss.entomology.GenerationMasterDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in mdss.entomology.GenerationMasterController.java";
    throw new com.terraframe.mojo.controller.UndefinedControllerActionException(msg, req.getLocale(), "mdss.entomology.GenerationMasterController.failCreate");
  }
  
  @com.terraframe.mojo.controller.ActionParameters(parameters="mdss.entomology.GenerationMasterDTO:dto", post=true)
  public void delete(mdss.entomology.GenerationMasterDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in mdss.entomology.GenerationMasterController.java";
    throw new com.terraframe.mojo.controller.UndefinedControllerActionException(msg, req.getLocale(), "mdss.entomology.GenerationMasterController.delete");
  }
  
  @com.terraframe.mojo.controller.ActionParameters(parameters="mdss.entomology.GenerationMasterDTO:dto", post=true)
  public void failDelete(mdss.entomology.GenerationMasterDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in mdss.entomology.GenerationMasterController.java";
    throw new com.terraframe.mojo.controller.UndefinedControllerActionException(msg, req.getLocale(), "mdss.entomology.GenerationMasterController.failDelete");
  }
  
}
