package mdss.entomology;

public class MosquitoCollectionController extends MosquitoCollectionControllerBase implements com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1234288138696L;
  
  public MosquitoCollectionController(javax.servlet.http.HttpServletRequest req, javax.servlet.http.HttpServletResponse resp, java.lang.Boolean isAsynchronous)
  {
    super(req, resp, isAsynchronous);
  }
  
  public void edit(java.lang.String id) throws java.io.IOException, javax.servlet.ServletException
  {
    mdss.entomology.MosquitoCollectionDTO dto = mdss.entomology.MosquitoCollectionDTO.lock(super.getClientRequest(), id);
    req.setAttribute("mdss_entomology_MosquitoCollection_collectionMethod", mdss.entomology.CollectionMethodDTO.allItems(super.getClientSession().getRequest()));
    req.setAttribute("mdss_entomology_AbstractMosquitoCollection_geoEntity", mdss.test.GeoEntityDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("item", dto);
    if(this.isAsynchronous())
    {
      req.getRequestDispatcher("WEB-INF/mdss/entomology/MosquitoCollection/editComponent.jsp").forward(req, resp);
    }
    else
    {
      req.getRequestDispatcher("WEB-INF/mdss/entomology/MosquitoCollection/edit.jsp").forward(req, resp);
    }
  }
  public void failEdit(java.lang.String id) throws java.io.IOException, javax.servlet.ServletException
  {
    this.view(id);
  }
  public void create(mdss.entomology.MosquitoCollectionDTO dto) throws java.io.IOException, javax.servlet.ServletException
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
  public void failCreate(mdss.entomology.MosquitoCollectionDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    req.setAttribute("mdss_entomology_MosquitoCollection_collectionMethod", mdss.entomology.CollectionMethodDTO.allItems(super.getClientSession().getRequest()));
    req.setAttribute("mdss_entomology_AbstractMosquitoCollection_geoEntity", mdss.test.GeoEntityDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("item", dto);
    if(this.isAsynchronous())
    {
      req.getRequestDispatcher("WEB-INF/mdss/entomology/MosquitoCollection/createComponent.jsp").forward(req, resp);
    }
    else
    {
      req.getRequestDispatcher("WEB-INF/mdss/entomology/MosquitoCollection/create.jsp").forward(req, resp);
    }
  }
  public void newInstance() throws java.io.IOException, javax.servlet.ServletException
  {
    com.terraframe.mojo.constants.ClientRequestIF clientRequest = super.getClientRequest();
    mdss.entomology.MosquitoCollectionDTO dto = new mdss.entomology.MosquitoCollectionDTO(clientRequest);
    req.setAttribute("mdss_entomology_MosquitoCollection_collectionMethod", mdss.entomology.CollectionMethodDTO.allItems(super.getClientSession().getRequest()));
    req.setAttribute("mdss_entomology_AbstractMosquitoCollection_geoEntity", mdss.test.GeoEntityDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("item", dto);
    if(this.isAsynchronous())
    {
      req.getRequestDispatcher("WEB-INF/mdss/entomology/MosquitoCollection/createComponent.jsp").forward(req, resp);
    }
    else
    {
      req.getRequestDispatcher("WEB-INF/mdss/entomology/MosquitoCollection/create.jsp").forward(req, resp);
    }
  }
  public void failNewInstance() throws java.io.IOException, javax.servlet.ServletException
  {
    this.viewAll();
  }
  public void viewAll() throws java.io.IOException, javax.servlet.ServletException
  {
    com.terraframe.mojo.constants.ClientRequestIF clientRequest = super.getClientRequest();
    mdss.entomology.MosquitoCollectionQueryDTO query = mdss.entomology.MosquitoCollectionDTO.getAllInstances(clientRequest, null, true, 20, 1);
    req.setAttribute("query", query);
    if(this.isAsynchronous())
    {
      req.getRequestDispatcher("WEB-INF/mdss/entomology/MosquitoCollection/viewAllComponent.jsp").forward(req, resp);
    }
    else
    {
      req.getRequestDispatcher("WEB-INF/mdss/entomology/MosquitoCollection/viewAll.jsp").forward(req, resp);
    }
  }
  public void failViewAll() throws java.io.IOException, javax.servlet.ServletException
  {
    resp.sendError(500);
  }
  public void view(java.lang.String id) throws java.io.IOException, javax.servlet.ServletException
  {
    com.terraframe.mojo.constants.ClientRequestIF clientRequest = super.getClientRequest();
    req.setAttribute("mdss_entomology_MosquitoCollection_collectionMethod", mdss.entomology.CollectionMethodDTO.allItems(super.getClientSession().getRequest()));
    req.setAttribute("mdss_entomology_AbstractMosquitoCollection_geoEntity", mdss.test.GeoEntityDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("item", mdss.entomology.MosquitoCollectionDTO.get(clientRequest, id));
    if(this.isAsynchronous())
    {
      req.getRequestDispatcher("WEB-INF/mdss/entomology/MosquitoCollection/viewComponent.jsp").forward(req, resp);
    }
    else
    {
      req.getRequestDispatcher("WEB-INF/mdss/entomology/MosquitoCollection/view.jsp").forward(req, resp);
    }
  }
  public void failView(java.lang.String id) throws java.io.IOException, javax.servlet.ServletException
  {
    this.viewAll();
  }
  public void viewPage(java.lang.String sortAttribute, java.lang.Boolean isAscending, java.lang.Integer pageSize, java.lang.Integer pageNumber) throws java.io.IOException, javax.servlet.ServletException
  {
    com.terraframe.mojo.constants.ClientRequestIF clientRequest = super.getClientRequest();
    mdss.entomology.MosquitoCollectionQueryDTO query = mdss.entomology.MosquitoCollectionDTO.getAllInstances(clientRequest, sortAttribute, isAscending, pageSize, pageNumber);
    req.setAttribute("query", query);
    if(this.isAsynchronous())
    {
      req.getRequestDispatcher("WEB-INF/mdss/entomology/MosquitoCollection/viewAllComponent.jsp").forward(req, resp);
    }
    else
    {
      req.getRequestDispatcher("WEB-INF/mdss/entomology/MosquitoCollection/viewAll.jsp").forward(req, resp);
    }
  }
  public void failViewPage(java.lang.String sortAttribute, java.lang.String isAscending, java.lang.String pageSize, java.lang.String pageNumber) throws java.io.IOException, javax.servlet.ServletException
  {
    resp.sendError(500);
  }
  public void cancel(mdss.entomology.MosquitoCollectionDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    dto.unlock();
    this.view(dto.getId());
  }
  public void failCancel(mdss.entomology.MosquitoCollectionDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    resp.sendError(500);
  }
  public void delete(mdss.entomology.MosquitoCollectionDTO dto) throws java.io.IOException, javax.servlet.ServletException
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
  public void failDelete(mdss.entomology.MosquitoCollectionDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    req.setAttribute("mdss_entomology_MosquitoCollection_collectionMethod", mdss.entomology.CollectionMethodDTO.allItems(super.getClientSession().getRequest()));
    req.setAttribute("mdss_entomology_AbstractMosquitoCollection_geoEntity", mdss.test.GeoEntityDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("item", dto);
    if(this.isAsynchronous())
    {
      req.getRequestDispatcher("WEB-INF/mdss/entomology/MosquitoCollection/editComponent.jsp").forward(req, resp);
    }
    else
    {
      req.getRequestDispatcher("WEB-INF/mdss/entomology/MosquitoCollection/edit.jsp").forward(req, resp);
    }
  }
  public void update(mdss.entomology.MosquitoCollectionDTO dto) throws java.io.IOException, javax.servlet.ServletException
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
  public void failUpdate(mdss.entomology.MosquitoCollectionDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    req.setAttribute("mdss_entomology_MosquitoCollection_collectionMethod", mdss.entomology.CollectionMethodDTO.allItems(super.getClientSession().getRequest()));
    req.setAttribute("mdss_entomology_AbstractMosquitoCollection_geoEntity", mdss.test.GeoEntityDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("item", dto);
    if(this.isAsynchronous())
    {
      req.getRequestDispatcher("WEB-INF/mdss/entomology/MosquitoCollection/editComponent.jsp").forward(req, resp);
    }
    else
    {
      req.getRequestDispatcher("WEB-INF/mdss/entomology/MosquitoCollection/edit.jsp").forward(req, resp);
    }
  }
}
