package mdss.entomology;

public class MosquitoCollectionPointController extends MosquitoCollectionPointControllerBase implements com.terraframe.mojo.generation.loader.Reloadable
{
  public static final String JSP_DIR = "WEB-INF/mdss/entomology/MosquitoCollectionPoint/";
  public static final String LAYOUT = JSP_DIR + "layout.jsp";
  
  private static final long serialVersionUID = 1235073586764L;
  
  public MosquitoCollectionPointController(javax.servlet.http.HttpServletRequest req, javax.servlet.http.HttpServletResponse resp, java.lang.Boolean isAsynchronous)
  {
    super(req, resp, isAsynchronous, JSP_DIR, LAYOUT);
  }
  
  public void create(mdss.entomology.MosquitoCollectionPointDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    try
    {
      dto.apply();
      this.view(dto.getId());
    }
    catch(com.terraframe.mojo.ProblemExceptionDTO e)
    {
      this.failCreate(dto);
    }
  }
  public void failCreate(mdss.entomology.MosquitoCollectionPointDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    req.setAttribute("mdss_entomology_AbstractMosquitoCollection_geoEntity", mdss.test.GeoEntityDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("item", dto);
    req.setAttribute("page_title", "Create MosquitoCollectionPointController");
    render("createComponent.jsp");
  }
  public void newInstance() throws java.io.IOException, javax.servlet.ServletException
  {
    com.terraframe.mojo.constants.ClientRequestIF clientRequest = super.getClientRequest();
    mdss.entomology.MosquitoCollectionPointDTO dto = new mdss.entomology.MosquitoCollectionPointDTO(clientRequest);
    req.setAttribute("mdss_entomology_AbstractMosquitoCollection_geoEntity", mdss.test.GeoEntityDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("item", dto);
    req.setAttribute("page_title", "Create MosquitoCollectionPointController");
    render("createComponent.jsp");
  }
  public void failNewInstance() throws java.io.IOException, javax.servlet.ServletException
  {
    this.viewAll();
  }
  public void cancel(mdss.entomology.MosquitoCollectionPointDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    dto.unlock();
    this.view(dto.getId());
  }
  public void failCancel(mdss.entomology.MosquitoCollectionPointDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    resp.sendError(500);
  }
  public void viewPage(java.lang.String sortAttribute, java.lang.Boolean isAscending, java.lang.Integer pageSize, java.lang.Integer pageNumber) throws java.io.IOException, javax.servlet.ServletException
  {
    com.terraframe.mojo.constants.ClientRequestIF clientRequest = super.getClientRequest();
    mdss.entomology.MosquitoCollectionPointQueryDTO query = mdss.entomology.MosquitoCollectionPointDTO.getAllInstances(clientRequest, sortAttribute, isAscending, pageSize, pageNumber);
    req.setAttribute("query", query);
    req.setAttribute("page_title", "View All MosquitoCollectionPointController Objects");
    render("viewAllComponent.jsp");
  }
  public void failViewPage(java.lang.String sortAttribute, java.lang.String isAscending, java.lang.String pageSize, java.lang.String pageNumber) throws java.io.IOException, javax.servlet.ServletException
  {
    resp.sendError(500);
  }
  public void view(java.lang.String id) throws java.io.IOException, javax.servlet.ServletException
  {
    com.terraframe.mojo.constants.ClientRequestIF clientRequest = super.getClientRequest();
    req.setAttribute("mdss_entomology_AbstractMosquitoCollection_geoEntity", mdss.test.GeoEntityDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("item", mdss.entomology.MosquitoCollectionPointDTO.get(clientRequest, id));
    req.setAttribute("page_title", "View MosquitoCollectionPointController");
    render("viewComponent.jsp");
  }
  public void failView(java.lang.String id) throws java.io.IOException, javax.servlet.ServletException
  {
    this.viewAll();
  }
  public void edit(java.lang.String id) throws java.io.IOException, javax.servlet.ServletException
  {
    mdss.entomology.MosquitoCollectionPointDTO dto = mdss.entomology.MosquitoCollectionPointDTO.lock(super.getClientRequest(), id);
    req.setAttribute("mdss_entomology_AbstractMosquitoCollection_geoEntity", mdss.test.GeoEntityDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("item", dto);
    req.setAttribute("page_title", "Edit MosquitoCollectionPointController");
    render("editComponent.jsp");
  }
  public void failEdit(java.lang.String id) throws java.io.IOException, javax.servlet.ServletException
  {
    this.view(id);
  }
  public void update(mdss.entomology.MosquitoCollectionPointDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    try
    {
      dto.apply();
      this.view(dto.getId());
    }
    catch(com.terraframe.mojo.ProblemExceptionDTO e)
    {
      this.failUpdate(dto);
    }
  }
  public void failUpdate(mdss.entomology.MosquitoCollectionPointDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    req.setAttribute("mdss_entomology_AbstractMosquitoCollection_geoEntity", mdss.test.GeoEntityDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("item", dto);
    req.setAttribute("page_title", "Update MosquitoCollectionPointController");
    render("updateComponent.jsp");
  }
  public void delete(mdss.entomology.MosquitoCollectionPointDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    try
    {
      dto.delete();
      this.viewAll();
    }
    catch(com.terraframe.mojo.ProblemExceptionDTO e)
    {
      this.failDelete(dto);
    }
  }
  public void failDelete(mdss.entomology.MosquitoCollectionPointDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    req.setAttribute("mdss_entomology_AbstractMosquitoCollection_geoEntity", mdss.test.GeoEntityDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("item", dto);
    req.setAttribute("page_title", "Edit MosquitoCollectionPointController");
    render("editComponent.jsp");
  }
  public void viewAll() throws java.io.IOException, javax.servlet.ServletException
  {
    com.terraframe.mojo.constants.ClientRequestIF clientRequest = super.getClientRequest();
    mdss.entomology.MosquitoCollectionPointQueryDTO query = mdss.entomology.MosquitoCollectionPointDTO.getAllInstances(clientRequest, null, true, 20, 1);
    req.setAttribute("query", query);
    req.setAttribute("page_title", "View All MosquitoCollectionPointController Objects");
    render("viewAllComponent.jsp");
  }
  public void failViewAll() throws java.io.IOException, javax.servlet.ServletException
  {
    resp.sendError(500);
  }
}
