package mdss.entomology;

public class MosquitoCollectionControllerBase implements com.terraframe.mojo.generation.loader.Reloadable
{
  public static final String CLASS = "mdss.entomology.MosquitoCollectionController";
  protected javax.servlet.http.HttpServletRequest req;
  protected javax.servlet.http.HttpServletResponse resp;
  protected java.lang.Boolean isAsynchronous;
  
  private static final long serialVersionUID = 1234294593442L;
  
  public MosquitoCollectionControllerBase(javax.servlet.http.HttpServletRequest req, javax.servlet.http.HttpServletResponse resp, java.lang.Boolean isAsynchronous)
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
  
  @com.terraframe.mojo.controller.ActionParameters(parameters="mdss.entomology.MosquitoCollectionDTO:dto", post=true)
  public void delete(mdss.entomology.MosquitoCollectionDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in mdss.entomology.MosquitoCollectionController.java";
    throw new com.terraframe.mojo.controller.UndefinedControllerActionException(msg, req.getLocale(), "mdss.entomology.MosquitoCollectionController.delete");
  }
  
  @com.terraframe.mojo.controller.ActionParameters(parameters="mdss.entomology.MosquitoCollectionDTO:dto", post=true)
  public void failDelete(mdss.entomology.MosquitoCollectionDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in mdss.entomology.MosquitoCollectionController.java";
    throw new com.terraframe.mojo.controller.UndefinedControllerActionException(msg, req.getLocale(), "mdss.entomology.MosquitoCollectionController.failDelete");
  }
  
  @com.terraframe.mojo.controller.ActionParameters(parameters="java.lang.String:sortAttribute, java.lang.Boolean:isAscending, java.lang.Integer:pageSize, java.lang.Integer:pageNumber", post=false)
  public void viewPage(java.lang.String sortAttribute, java.lang.Boolean isAscending, java.lang.Integer pageSize, java.lang.Integer pageNumber) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in mdss.entomology.MosquitoCollectionController.java";
    throw new com.terraframe.mojo.controller.UndefinedControllerActionException(msg, req.getLocale(), "mdss.entomology.MosquitoCollectionController.viewPage");
  }
  
  @com.terraframe.mojo.controller.ActionParameters(parameters="java.lang.String:sortAttribute, java.lang.String:isAscending, java.lang.String:pageSize, java.lang.String:pageNumber", post=false)
  public void failViewPage(java.lang.String sortAttribute, java.lang.String isAscending, java.lang.String pageSize, java.lang.String pageNumber) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in mdss.entomology.MosquitoCollectionController.java";
    throw new com.terraframe.mojo.controller.UndefinedControllerActionException(msg, req.getLocale(), "mdss.entomology.MosquitoCollectionController.failViewPage");
  }
  
  @com.terraframe.mojo.controller.ActionParameters(parameters="mdss.entomology.MosquitoCollectionDTO:dto", post=true)
  public void update(mdss.entomology.MosquitoCollectionDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in mdss.entomology.MosquitoCollectionController.java";
    throw new com.terraframe.mojo.controller.UndefinedControllerActionException(msg, req.getLocale(), "mdss.entomology.MosquitoCollectionController.update");
  }
  
  @com.terraframe.mojo.controller.ActionParameters(parameters="mdss.entomology.MosquitoCollectionDTO:dto", post=true)
  public void failUpdate(mdss.entomology.MosquitoCollectionDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in mdss.entomology.MosquitoCollectionController.java";
    throw new com.terraframe.mojo.controller.UndefinedControllerActionException(msg, req.getLocale(), "mdss.entomology.MosquitoCollectionController.failUpdate");
  }
  
  @com.terraframe.mojo.controller.ActionParameters(parameters="java.lang.String:id", post=true)
  public void edit(java.lang.String id) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in mdss.entomology.MosquitoCollectionController.java";
    throw new com.terraframe.mojo.controller.UndefinedControllerActionException(msg, req.getLocale(), "mdss.entomology.MosquitoCollectionController.edit");
  }
  
  @com.terraframe.mojo.controller.ActionParameters(parameters="java.lang.String:id", post=true)
  public void failEdit(java.lang.String id) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in mdss.entomology.MosquitoCollectionController.java";
    throw new com.terraframe.mojo.controller.UndefinedControllerActionException(msg, req.getLocale(), "mdss.entomology.MosquitoCollectionController.failEdit");
  }
  
  @com.terraframe.mojo.controller.ActionParameters(parameters="java.lang.String:id", post=false)
  public void view(java.lang.String id) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in mdss.entomology.MosquitoCollectionController.java";
    throw new com.terraframe.mojo.controller.UndefinedControllerActionException(msg, req.getLocale(), "mdss.entomology.MosquitoCollectionController.view");
  }
  
  @com.terraframe.mojo.controller.ActionParameters(parameters="java.lang.String:id", post=false)
  public void failView(java.lang.String id) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in mdss.entomology.MosquitoCollectionController.java";
    throw new com.terraframe.mojo.controller.UndefinedControllerActionException(msg, req.getLocale(), "mdss.entomology.MosquitoCollectionController.failView");
  }
  
  @com.terraframe.mojo.controller.ActionParameters(parameters="", post=false)
  public void newInstance() throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in mdss.entomology.MosquitoCollectionController.java";
    throw new com.terraframe.mojo.controller.UndefinedControllerActionException(msg, req.getLocale(), "mdss.entomology.MosquitoCollectionController.newInstance");
  }
  
  @com.terraframe.mojo.controller.ActionParameters(parameters="", post=false)
  public void failNewInstance() throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in mdss.entomology.MosquitoCollectionController.java";
    throw new com.terraframe.mojo.controller.UndefinedControllerActionException(msg, req.getLocale(), "mdss.entomology.MosquitoCollectionController.failNewInstance");
  }
  
  @com.terraframe.mojo.controller.ActionParameters(parameters="", post=false)
  public void viewAll() throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in mdss.entomology.MosquitoCollectionController.java";
    throw new com.terraframe.mojo.controller.UndefinedControllerActionException(msg, req.getLocale(), "mdss.entomology.MosquitoCollectionController.viewAll");
  }
  
  @com.terraframe.mojo.controller.ActionParameters(parameters="", post=false)
  public void failViewAll() throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in mdss.entomology.MosquitoCollectionController.java";
    throw new com.terraframe.mojo.controller.UndefinedControllerActionException(msg, req.getLocale(), "mdss.entomology.MosquitoCollectionController.failViewAll");
  }
  
  @com.terraframe.mojo.controller.ActionParameters(parameters="mdss.entomology.MosquitoCollectionDTO:dto", post=true)
  public void create(mdss.entomology.MosquitoCollectionDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in mdss.entomology.MosquitoCollectionController.java";
    throw new com.terraframe.mojo.controller.UndefinedControllerActionException(msg, req.getLocale(), "mdss.entomology.MosquitoCollectionController.create");
  }
  
  @com.terraframe.mojo.controller.ActionParameters(parameters="mdss.entomology.MosquitoCollectionDTO:dto", post=true)
  public void failCreate(mdss.entomology.MosquitoCollectionDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in mdss.entomology.MosquitoCollectionController.java";
    throw new com.terraframe.mojo.controller.UndefinedControllerActionException(msg, req.getLocale(), "mdss.entomology.MosquitoCollectionController.failCreate");
  }
  
  @com.terraframe.mojo.controller.ActionParameters(parameters="mdss.entomology.MosquitoCollectionDTO:dto", post=true)
  public void cancel(mdss.entomology.MosquitoCollectionDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in mdss.entomology.MosquitoCollectionController.java";
    throw new com.terraframe.mojo.controller.UndefinedControllerActionException(msg, req.getLocale(), "mdss.entomology.MosquitoCollectionController.cancel");
  }
  
  @com.terraframe.mojo.controller.ActionParameters(parameters="mdss.entomology.MosquitoCollectionDTO:dto", post=true)
  public void failCancel(mdss.entomology.MosquitoCollectionDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in mdss.entomology.MosquitoCollectionController.java";
    throw new com.terraframe.mojo.controller.UndefinedControllerActionException(msg, req.getLocale(), "mdss.entomology.MosquitoCollectionController.failCancel");
  }
  
  @com.terraframe.mojo.controller.ActionParameters(parameters="mdss.test.GeoEntityDTO:geoEntity, java.util.Date:collectionDate", post=true)
  public void searchByGeoEntityAndDate(mdss.test.GeoEntityDTO geoEntity, java.util.Date collectionDate) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in mdss.entomology.MosquitoCollectionController.java";
    throw new com.terraframe.mojo.controller.UndefinedControllerActionException(msg, req.getLocale(), "mdss.entomology.MosquitoCollectionController.searchByGeoEntityAndDate");
  }
  
  @com.terraframe.mojo.controller.ActionParameters(parameters="mdss.test.GeoEntityDTO:geoEntity, java.lang.String:collectionDate", post=true)
  public void failSearchByGeoEntityAndDate(mdss.test.GeoEntityDTO geoEntity, java.lang.String collectionDate) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in mdss.entomology.MosquitoCollectionController.java";
    throw new com.terraframe.mojo.controller.UndefinedControllerActionException(msg, req.getLocale(), "mdss.entomology.MosquitoCollectionController.failSearchByGeoEntityAndDate");
  }
  
}
