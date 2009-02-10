package mdss.entomology;

public class AbstractMosquitoCollectionControllerBase implements com.terraframe.mojo.generation.loader.Reloadable
{
  public static final String CLASS = "mdss.entomology.AbstractMosquitoCollectionController";
  protected javax.servlet.http.HttpServletRequest req;
  protected javax.servlet.http.HttpServletResponse resp;
  protected java.lang.Boolean isAsynchronous;
  
  private static final long serialVersionUID = 1234288140089L;
  
  public AbstractMosquitoCollectionControllerBase(javax.servlet.http.HttpServletRequest req, javax.servlet.http.HttpServletResponse resp, java.lang.Boolean isAsynchronous)
  {
    this.req = req;
    this.resp = resp;
    this.isAsynchronous = isAsynchronous;
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
  
  @com.terraframe.mojo.controller.ActionParameters(parameters="mdss.entomology.AbstractMosquitoCollectionDTO:dto", post=true)
  public void create(mdss.entomology.AbstractMosquitoCollectionDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in mdss.entomology.AbstractMosquitoCollectionController.java";
    throw new com.terraframe.mojo.controller.UndefinedControllerActionException(msg, req.getLocale(), "mdss.entomology.AbstractMosquitoCollectionController.create");
  }
  
  @com.terraframe.mojo.controller.ActionParameters(parameters="mdss.entomology.AbstractMosquitoCollectionDTO:dto", post=true)
  public void failCreate(mdss.entomology.AbstractMosquitoCollectionDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in mdss.entomology.AbstractMosquitoCollectionController.java";
    throw new com.terraframe.mojo.controller.UndefinedControllerActionException(msg, req.getLocale(), "mdss.entomology.AbstractMosquitoCollectionController.failCreate");
  }
  
  @com.terraframe.mojo.controller.ActionParameters(parameters="", post=false)
  public void viewAll() throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in mdss.entomology.AbstractMosquitoCollectionController.java";
    throw new com.terraframe.mojo.controller.UndefinedControllerActionException(msg, req.getLocale(), "mdss.entomology.AbstractMosquitoCollectionController.viewAll");
  }
  
  @com.terraframe.mojo.controller.ActionParameters(parameters="", post=false)
  public void failViewAll() throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in mdss.entomology.AbstractMosquitoCollectionController.java";
    throw new com.terraframe.mojo.controller.UndefinedControllerActionException(msg, req.getLocale(), "mdss.entomology.AbstractMosquitoCollectionController.failViewAll");
  }
  
  @com.terraframe.mojo.controller.ActionParameters(parameters="mdss.entomology.AbstractMosquitoCollectionDTO:dto", post=true)
  public void update(mdss.entomology.AbstractMosquitoCollectionDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in mdss.entomology.AbstractMosquitoCollectionController.java";
    throw new com.terraframe.mojo.controller.UndefinedControllerActionException(msg, req.getLocale(), "mdss.entomology.AbstractMosquitoCollectionController.update");
  }
  
  @com.terraframe.mojo.controller.ActionParameters(parameters="mdss.entomology.AbstractMosquitoCollectionDTO:dto", post=true)
  public void failUpdate(mdss.entomology.AbstractMosquitoCollectionDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in mdss.entomology.AbstractMosquitoCollectionController.java";
    throw new com.terraframe.mojo.controller.UndefinedControllerActionException(msg, req.getLocale(), "mdss.entomology.AbstractMosquitoCollectionController.failUpdate");
  }
  
  @com.terraframe.mojo.controller.ActionParameters(parameters="java.lang.String:id", post=false)
  public void view(java.lang.String id) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in mdss.entomology.AbstractMosquitoCollectionController.java";
    throw new com.terraframe.mojo.controller.UndefinedControllerActionException(msg, req.getLocale(), "mdss.entomology.AbstractMosquitoCollectionController.view");
  }
  
  @com.terraframe.mojo.controller.ActionParameters(parameters="java.lang.String:id", post=false)
  public void failView(java.lang.String id) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in mdss.entomology.AbstractMosquitoCollectionController.java";
    throw new com.terraframe.mojo.controller.UndefinedControllerActionException(msg, req.getLocale(), "mdss.entomology.AbstractMosquitoCollectionController.failView");
  }
  
  @com.terraframe.mojo.controller.ActionParameters(parameters="java.lang.String:id", post=true)
  public void edit(java.lang.String id) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in mdss.entomology.AbstractMosquitoCollectionController.java";
    throw new com.terraframe.mojo.controller.UndefinedControllerActionException(msg, req.getLocale(), "mdss.entomology.AbstractMosquitoCollectionController.edit");
  }
  
  @com.terraframe.mojo.controller.ActionParameters(parameters="java.lang.String:id", post=true)
  public void failEdit(java.lang.String id) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in mdss.entomology.AbstractMosquitoCollectionController.java";
    throw new com.terraframe.mojo.controller.UndefinedControllerActionException(msg, req.getLocale(), "mdss.entomology.AbstractMosquitoCollectionController.failEdit");
  }
  
  @com.terraframe.mojo.controller.ActionParameters(parameters="mdss.entomology.AbstractMosquitoCollectionDTO:dto", post=true)
  public void cancel(mdss.entomology.AbstractMosquitoCollectionDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in mdss.entomology.AbstractMosquitoCollectionController.java";
    throw new com.terraframe.mojo.controller.UndefinedControllerActionException(msg, req.getLocale(), "mdss.entomology.AbstractMosquitoCollectionController.cancel");
  }
  
  @com.terraframe.mojo.controller.ActionParameters(parameters="mdss.entomology.AbstractMosquitoCollectionDTO:dto", post=true)
  public void failCancel(mdss.entomology.AbstractMosquitoCollectionDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in mdss.entomology.AbstractMosquitoCollectionController.java";
    throw new com.terraframe.mojo.controller.UndefinedControllerActionException(msg, req.getLocale(), "mdss.entomology.AbstractMosquitoCollectionController.failCancel");
  }
  
  @com.terraframe.mojo.controller.ActionParameters(parameters="java.lang.String:sortAttribute, java.lang.Boolean:isAscending, java.lang.Integer:pageSize, java.lang.Integer:pageNumber", post=false)
  public void viewPage(java.lang.String sortAttribute, java.lang.Boolean isAscending, java.lang.Integer pageSize, java.lang.Integer pageNumber) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in mdss.entomology.AbstractMosquitoCollectionController.java";
    throw new com.terraframe.mojo.controller.UndefinedControllerActionException(msg, req.getLocale(), "mdss.entomology.AbstractMosquitoCollectionController.viewPage");
  }
  
  @com.terraframe.mojo.controller.ActionParameters(parameters="java.lang.String:sortAttribute, java.lang.String:isAscending, java.lang.String:pageSize, java.lang.String:pageNumber", post=false)
  public void failViewPage(java.lang.String sortAttribute, java.lang.String isAscending, java.lang.String pageSize, java.lang.String pageNumber) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in mdss.entomology.AbstractMosquitoCollectionController.java";
    throw new com.terraframe.mojo.controller.UndefinedControllerActionException(msg, req.getLocale(), "mdss.entomology.AbstractMosquitoCollectionController.failViewPage");
  }
  
  @com.terraframe.mojo.controller.ActionParameters(parameters="mdss.entomology.AbstractMosquitoCollectionDTO:dto", post=true)
  public void delete(mdss.entomology.AbstractMosquitoCollectionDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in mdss.entomology.AbstractMosquitoCollectionController.java";
    throw new com.terraframe.mojo.controller.UndefinedControllerActionException(msg, req.getLocale(), "mdss.entomology.AbstractMosquitoCollectionController.delete");
  }
  
  @com.terraframe.mojo.controller.ActionParameters(parameters="mdss.entomology.AbstractMosquitoCollectionDTO:dto", post=true)
  public void failDelete(mdss.entomology.AbstractMosquitoCollectionDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in mdss.entomology.AbstractMosquitoCollectionController.java";
    throw new com.terraframe.mojo.controller.UndefinedControllerActionException(msg, req.getLocale(), "mdss.entomology.AbstractMosquitoCollectionController.failDelete");
  }
  
}
