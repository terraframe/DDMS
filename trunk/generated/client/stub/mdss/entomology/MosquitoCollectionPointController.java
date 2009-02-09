package mdss.entomology;

public class MosquitoCollectionPointController extends MosquitoCollectionPointControllerBase implements com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1234203358297L;
  
  public MosquitoCollectionPointController(javax.servlet.http.HttpServletRequest req, javax.servlet.http.HttpServletResponse resp)
  {
    super(req, resp);
  }
  
  public void viewPage(java.lang.String sortAttribute, java.lang.Boolean isAscending, java.lang.Integer pageSize, java.lang.Integer pageNumber) throws java.io.IOException, javax.servlet.ServletException
  {
    com.terraframe.mojo.constants.ClientRequestIF clientRequest = super.getClientRequest();
    mdss.entomology.MosquitoCollectionPointQueryDTO query = mdss.entomology.MosquitoCollectionPointDTO.getAllInstances(clientRequest, sortAttribute, isAscending, pageSize, pageNumber);
    req.setAttribute("query", query);
    req.getRequestDispatcher("WEB-INF/mdss/entomology/MosquitoCollectionPoint/viewAll.jsp").forward(req, resp);
  }
  public void failViewPage(java.lang.String sortAttribute, java.lang.String isAscending, java.lang.String pageSize, java.lang.String pageNumber) throws java.io.IOException, javax.servlet.ServletException
  {
    resp.sendError(500);
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
    req.getRequestDispatcher("WEB-INF/mdss/entomology/MosquitoCollectionPoint/edit.jsp").forward(req, resp);
  }
  public void viewAll() throws java.io.IOException, javax.servlet.ServletException
  {
    com.terraframe.mojo.constants.ClientRequestIF clientRequest = super.getClientRequest();
    mdss.entomology.MosquitoCollectionPointQueryDTO query = mdss.entomology.MosquitoCollectionPointDTO.getAllInstances(clientRequest, null, true, 20, 1);
    req.setAttribute("query", query);
    req.getRequestDispatcher("WEB-INF/mdss/entomology/MosquitoCollectionPoint/viewAll.jsp").forward(req, resp);
  }
  public void failViewAll() throws java.io.IOException, javax.servlet.ServletException
  {
    resp.sendError(500);
  }
  public void newInstance() throws java.io.IOException, javax.servlet.ServletException
  {
    com.terraframe.mojo.constants.ClientRequestIF clientRequest = super.getClientRequest();
    mdss.entomology.MosquitoCollectionPointDTO dto = new mdss.entomology.MosquitoCollectionPointDTO(clientRequest);
    req.setAttribute("mdss_entomology_AbstractMosquitoCollection_geoEntity", mdss.test.GeoEntityDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("item", dto);
    req.getRequestDispatcher("WEB-INF/mdss/entomology/MosquitoCollectionPoint/create.jsp").forward(req, resp);
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
    req.getRequestDispatcher("WEB-INF/mdss/entomology/MosquitoCollectionPoint/create.jsp").forward(req, resp);
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
    req.getRequestDispatcher("WEB-INF/mdss/entomology/MosquitoCollectionPoint/edit.jsp").forward(req, resp);
  }
  public void view(java.lang.String id) throws java.io.IOException, javax.servlet.ServletException
  {
    com.terraframe.mojo.constants.ClientRequestIF clientRequest = super.getClientRequest();
    req.setAttribute("mdss_entomology_AbstractMosquitoCollection_geoEntity", mdss.test.GeoEntityDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("item", mdss.entomology.MosquitoCollectionPointDTO.get(clientRequest, id));
    req.getRequestDispatcher("WEB-INF/mdss/entomology/MosquitoCollectionPoint/view.jsp").forward(req, resp);
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
    req.getRequestDispatcher("WEB-INF/mdss/entomology/MosquitoCollectionPoint/edit.jsp").forward(req, resp);
  }
  public void failEdit(java.lang.String id) throws java.io.IOException, javax.servlet.ServletException
  {
    this.view(id);
  }
}
