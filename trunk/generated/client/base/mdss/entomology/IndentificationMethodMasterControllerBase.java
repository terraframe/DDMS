package mdss.entomology;

public class IndentificationMethodMasterControllerBase implements com.terraframe.mojo.generation.loader.Reloadable
{
  public static final String CLASS = "mdss.entomology.IndentificationMethodMasterController";
  protected javax.servlet.http.HttpServletRequest req;
  protected javax.servlet.http.HttpServletResponse resp;
  
  private static final long serialVersionUID = 1234203358211L;
  
  public IndentificationMethodMasterControllerBase(javax.servlet.http.HttpServletRequest req, javax.servlet.http.HttpServletResponse resp)
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
  
  @com.terraframe.mojo.controller.ActionParameters(parameters="mdss.entomology.IndentificationMethodMasterDTO:dto", post=true)
  public void create(mdss.entomology.IndentificationMethodMasterDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in mdss.entomology.IndentificationMethodMasterController.java";
    throw new com.terraframe.mojo.controller.UndefinedControllerActionException(msg, req.getLocale(), "mdss.entomology.IndentificationMethodMasterController.create");
  }
  
  @com.terraframe.mojo.controller.ActionParameters(parameters="mdss.entomology.IndentificationMethodMasterDTO:dto", post=true)
  public void failCreate(mdss.entomology.IndentificationMethodMasterDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in mdss.entomology.IndentificationMethodMasterController.java";
    throw new com.terraframe.mojo.controller.UndefinedControllerActionException(msg, req.getLocale(), "mdss.entomology.IndentificationMethodMasterController.failCreate");
  }
  
  @com.terraframe.mojo.controller.ActionParameters(parameters="mdss.entomology.IndentificationMethodMasterDTO:dto", post=true)
  public void update(mdss.entomology.IndentificationMethodMasterDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in mdss.entomology.IndentificationMethodMasterController.java";
    throw new com.terraframe.mojo.controller.UndefinedControllerActionException(msg, req.getLocale(), "mdss.entomology.IndentificationMethodMasterController.update");
  }
  
  @com.terraframe.mojo.controller.ActionParameters(parameters="mdss.entomology.IndentificationMethodMasterDTO:dto", post=true)
  public void failUpdate(mdss.entomology.IndentificationMethodMasterDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in mdss.entomology.IndentificationMethodMasterController.java";
    throw new com.terraframe.mojo.controller.UndefinedControllerActionException(msg, req.getLocale(), "mdss.entomology.IndentificationMethodMasterController.failUpdate");
  }
  
  @com.terraframe.mojo.controller.ActionParameters(parameters="java.lang.String:sortAttribute, java.lang.Boolean:isAscending, java.lang.Integer:pageSize, java.lang.Integer:pageNumber", post=false)
  public void viewPage(java.lang.String sortAttribute, java.lang.Boolean isAscending, java.lang.Integer pageSize, java.lang.Integer pageNumber) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in mdss.entomology.IndentificationMethodMasterController.java";
    throw new com.terraframe.mojo.controller.UndefinedControllerActionException(msg, req.getLocale(), "mdss.entomology.IndentificationMethodMasterController.viewPage");
  }
  
  @com.terraframe.mojo.controller.ActionParameters(parameters="java.lang.String:sortAttribute, java.lang.String:isAscending, java.lang.String:pageSize, java.lang.String:pageNumber", post=false)
  public void failViewPage(java.lang.String sortAttribute, java.lang.String isAscending, java.lang.String pageSize, java.lang.String pageNumber) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in mdss.entomology.IndentificationMethodMasterController.java";
    throw new com.terraframe.mojo.controller.UndefinedControllerActionException(msg, req.getLocale(), "mdss.entomology.IndentificationMethodMasterController.failViewPage");
  }
  
  @com.terraframe.mojo.controller.ActionParameters(parameters="", post=false)
  public void viewAll() throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in mdss.entomology.IndentificationMethodMasterController.java";
    throw new com.terraframe.mojo.controller.UndefinedControllerActionException(msg, req.getLocale(), "mdss.entomology.IndentificationMethodMasterController.viewAll");
  }
  
  @com.terraframe.mojo.controller.ActionParameters(parameters="", post=false)
  public void failViewAll() throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in mdss.entomology.IndentificationMethodMasterController.java";
    throw new com.terraframe.mojo.controller.UndefinedControllerActionException(msg, req.getLocale(), "mdss.entomology.IndentificationMethodMasterController.failViewAll");
  }
  
  @com.terraframe.mojo.controller.ActionParameters(parameters="", post=false)
  public void newInstance() throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in mdss.entomology.IndentificationMethodMasterController.java";
    throw new com.terraframe.mojo.controller.UndefinedControllerActionException(msg, req.getLocale(), "mdss.entomology.IndentificationMethodMasterController.newInstance");
  }
  
  @com.terraframe.mojo.controller.ActionParameters(parameters="", post=false)
  public void failNewInstance() throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in mdss.entomology.IndentificationMethodMasterController.java";
    throw new com.terraframe.mojo.controller.UndefinedControllerActionException(msg, req.getLocale(), "mdss.entomology.IndentificationMethodMasterController.failNewInstance");
  }
  
  @com.terraframe.mojo.controller.ActionParameters(parameters="mdss.entomology.IndentificationMethodMasterDTO:dto", post=true)
  public void cancel(mdss.entomology.IndentificationMethodMasterDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in mdss.entomology.IndentificationMethodMasterController.java";
    throw new com.terraframe.mojo.controller.UndefinedControllerActionException(msg, req.getLocale(), "mdss.entomology.IndentificationMethodMasterController.cancel");
  }
  
  @com.terraframe.mojo.controller.ActionParameters(parameters="mdss.entomology.IndentificationMethodMasterDTO:dto", post=true)
  public void failCancel(mdss.entomology.IndentificationMethodMasterDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in mdss.entomology.IndentificationMethodMasterController.java";
    throw new com.terraframe.mojo.controller.UndefinedControllerActionException(msg, req.getLocale(), "mdss.entomology.IndentificationMethodMasterController.failCancel");
  }
  
  @com.terraframe.mojo.controller.ActionParameters(parameters="mdss.entomology.IndentificationMethodMasterDTO:dto", post=true)
  public void delete(mdss.entomology.IndentificationMethodMasterDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in mdss.entomology.IndentificationMethodMasterController.java";
    throw new com.terraframe.mojo.controller.UndefinedControllerActionException(msg, req.getLocale(), "mdss.entomology.IndentificationMethodMasterController.delete");
  }
  
  @com.terraframe.mojo.controller.ActionParameters(parameters="mdss.entomology.IndentificationMethodMasterDTO:dto", post=true)
  public void failDelete(mdss.entomology.IndentificationMethodMasterDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in mdss.entomology.IndentificationMethodMasterController.java";
    throw new com.terraframe.mojo.controller.UndefinedControllerActionException(msg, req.getLocale(), "mdss.entomology.IndentificationMethodMasterController.failDelete");
  }
  
  @com.terraframe.mojo.controller.ActionParameters(parameters="java.lang.String:id", post=false)
  public void view(java.lang.String id) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in mdss.entomology.IndentificationMethodMasterController.java";
    throw new com.terraframe.mojo.controller.UndefinedControllerActionException(msg, req.getLocale(), "mdss.entomology.IndentificationMethodMasterController.view");
  }
  
  @com.terraframe.mojo.controller.ActionParameters(parameters="java.lang.String:id", post=false)
  public void failView(java.lang.String id) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in mdss.entomology.IndentificationMethodMasterController.java";
    throw new com.terraframe.mojo.controller.UndefinedControllerActionException(msg, req.getLocale(), "mdss.entomology.IndentificationMethodMasterController.failView");
  }
  
  @com.terraframe.mojo.controller.ActionParameters(parameters="java.lang.String:id", post=true)
  public void edit(java.lang.String id) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in mdss.entomology.IndentificationMethodMasterController.java";
    throw new com.terraframe.mojo.controller.UndefinedControllerActionException(msg, req.getLocale(), "mdss.entomology.IndentificationMethodMasterController.edit");
  }
  
  @com.terraframe.mojo.controller.ActionParameters(parameters="java.lang.String:id", post=true)
  public void failEdit(java.lang.String id) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in mdss.entomology.IndentificationMethodMasterController.java";
    throw new com.terraframe.mojo.controller.UndefinedControllerActionException(msg, req.getLocale(), "mdss.entomology.IndentificationMethodMasterController.failEdit");
  }
  
}
