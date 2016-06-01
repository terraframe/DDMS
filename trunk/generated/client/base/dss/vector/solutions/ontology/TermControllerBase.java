package dss.vector.solutions.ontology;

@com.runwaysdk.business.ClassSignature(hash = -96264409)
public class TermControllerBase implements com.runwaysdk.generation.loader.Reloadable
{
  public static final String CLASS = "dss.vector.solutions.ontology.TermController";
  protected javax.servlet.http.HttpServletRequest req;
  protected javax.servlet.http.HttpServletResponse resp;
  protected java.lang.Boolean isAsynchronous;
  protected java.lang.String dir;
  protected java.lang.String layout;
  
  public TermControllerBase(javax.servlet.http.HttpServletRequest req, javax.servlet.http.HttpServletResponse resp, java.lang.Boolean isAsynchronous)
  {
    this(req, resp, isAsynchronous, "","");
  }
  
  public TermControllerBase(javax.servlet.http.HttpServletRequest req, javax.servlet.http.HttpServletResponse resp, java.lang.Boolean isAsynchronous, java.lang.String dir, java.lang.String layout)
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
  
  @com.runwaysdk.controller.ActionParameters(parameters="dss.vector.solutions.ontology.TermDTO:dto", post=true)
  public void cancel(dss.vector.solutions.ontology.TermDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.ontology.TermController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.ontology.TermController.cancel");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="dss.vector.solutions.ontology.TermDTO:dto", post=true)
  public void failCancel(dss.vector.solutions.ontology.TermDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.ontology.TermController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.ontology.TermController.failCancel");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="java.lang.String:childId, java.lang.String:parentId", post=true)
  public void confirmChangeParent(java.lang.String childId, java.lang.String parentId) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.ontology.TermController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.ontology.TermController.confirmChangeParent");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="java.lang.String:childId, java.lang.String:parentId", post=true)
  public void failConfirmChangeParent(java.lang.String childId, java.lang.String parentId) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.ontology.TermController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.ontology.TermController.failConfirmChangeParent");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="java.lang.String:parentId", post=true)
  public void confirmDeleteTerm(java.lang.String parentId) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.ontology.TermController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.ontology.TermController.confirmDeleteTerm");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="java.lang.String:parentId", post=true)
  public void failConfirmDeleteTerm(java.lang.String parentId) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.ontology.TermController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.ontology.TermController.failConfirmDeleteTerm");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="dss.vector.solutions.ontology.TermDTO:dto, java.lang.String:parentId, java.lang.Boolean:inactive", post=true)
  public void create(dss.vector.solutions.ontology.TermDTO dto, java.lang.String parentId, java.lang.Boolean inactive) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.ontology.TermController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.ontology.TermController.create");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="dss.vector.solutions.ontology.TermDTO:dto, java.lang.String:parentId, java.lang.String:inactive", post=true)
  public void failCreate(dss.vector.solutions.ontology.TermDTO dto, java.lang.String parentId, java.lang.String inactive) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.ontology.TermController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.ontology.TermController.failCreate");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="dss.vector.solutions.ontology.TermDTO:dto", post=true)
  public void delete(dss.vector.solutions.ontology.TermDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.ontology.TermController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.ontology.TermController.delete");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="dss.vector.solutions.ontology.TermDTO:dto", post=true)
  public void failDelete(dss.vector.solutions.ontology.TermDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.ontology.TermController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.ontology.TermController.failDelete");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="", post=true)
  public void deleteRelationship() throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.ontology.TermController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.ontology.TermController.deleteRelationship");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="", post=true)
  public void failDeleteRelationship() throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.ontology.TermController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.ontology.TermController.failDeleteRelationship");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="", post=true)
  public void deleteTerm() throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.ontology.TermController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.ontology.TermController.deleteTerm");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="", post=true)
  public void failDeleteTerm() throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.ontology.TermController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.ontology.TermController.failDeleteTerm");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="", post=true)
  public void doClone() throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.ontology.TermController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.ontology.TermController.doClone");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="", post=true)
  public void failDoClone() throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.ontology.TermController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.ontology.TermController.failDoClone");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="", post=true)
  public void doNotClone() throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.ontology.TermController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.ontology.TermController.doNotClone");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="", post=true)
  public void failDoNotClone() throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.ontology.TermController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.ontology.TermController.failDoNotClone");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="java.lang.String:id", post=false)
  public void edit(java.lang.String id) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.ontology.TermController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.ontology.TermController.edit");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="java.lang.String:id", post=false)
  public void failEdit(java.lang.String id) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.ontology.TermController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.ontology.TermController.failEdit");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="java.lang.String:term", post=false)
  public void exportTerm(java.lang.String term) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.ontology.TermController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.ontology.TermController.exportTerm");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="java.lang.String:term", post=false)
  public void failExportTerm(java.lang.String term) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.ontology.TermController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.ontology.TermController.failExportTerm");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="com.runwaysdk.controller.MultipartFileParameter:importFile", post=true)
  public void importTerms(com.runwaysdk.controller.MultipartFileParameter importFile) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.ontology.TermController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.ontology.TermController.importTerms");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="com.runwaysdk.controller.MultipartFileParameter:importFile", post=true)
  public void failImportTerms(com.runwaysdk.controller.MultipartFileParameter importFile) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.ontology.TermController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.ontology.TermController.failImportTerms");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="", post=false)
  public void newInstance() throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.ontology.TermController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.ontology.TermController.newInstance");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="", post=false)
  public void failNewInstance() throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.ontology.TermController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.ontology.TermController.failNewInstance");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="dss.vector.solutions.ontology.TermDTO:dto, java.lang.Boolean:inactive", post=true)
  public void update(dss.vector.solutions.ontology.TermDTO dto, java.lang.Boolean inactive) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.ontology.TermController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.ontology.TermController.update");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="dss.vector.solutions.ontology.TermDTO:dto, java.lang.String:inactive", post=true)
  public void failUpdate(dss.vector.solutions.ontology.TermDTO dto, java.lang.String inactive) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.ontology.TermController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.ontology.TermController.failUpdate");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="java.lang.String:id", post=false)
  public void view(java.lang.String id) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.ontology.TermController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.ontology.TermController.view");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="java.lang.String:id", post=false)
  public void failView(java.lang.String id) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.ontology.TermController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.ontology.TermController.failView");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="", post=false)
  public void viewAll() throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.ontology.TermController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.ontology.TermController.viewAll");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="", post=false)
  public void failViewAll() throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.ontology.TermController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.ontology.TermController.failViewAll");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="java.lang.String:sortAttribute, java.lang.Boolean:isAscending, java.lang.Integer:pageSize, java.lang.Integer:pageNumber", post=false)
  public void viewPage(java.lang.String sortAttribute, java.lang.Boolean isAscending, java.lang.Integer pageSize, java.lang.Integer pageNumber) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.ontology.TermController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.ontology.TermController.viewPage");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="java.lang.String:sortAttribute, java.lang.String:isAscending, java.lang.String:pageSize, java.lang.String:pageNumber", post=false)
  public void failViewPage(java.lang.String sortAttribute, java.lang.String isAscending, java.lang.String pageSize, java.lang.String pageNumber) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.ontology.TermController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.ontology.TermController.failViewPage");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="", post=false)
  public void viewTree() throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.ontology.TermController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.ontology.TermController.viewTree");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="", post=false)
  public void failViewTree() throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.ontology.TermController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.ontology.TermController.failViewTree");
  }
  
}
