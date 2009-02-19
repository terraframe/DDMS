package mdss.entomology;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;

import mdss.test.GeoEntityDTO;

public class MosquitoCollectionController extends MosquitoCollectionControllerBase implements com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1234726077320L; 
  private static final String jsp_dir = "WEB-INF/mdss/entomology/MosquitoCollection/";
  private static final String layout =  jsp_dir + "layout.jsp";
  
  public MosquitoCollectionController(javax.servlet.http.HttpServletRequest req, javax.servlet.http.HttpServletResponse resp, java.lang.Boolean isAsynchronous)
  {
    super(req, resp, isAsynchronous);
  }
  
  private void render(String jsp ) throws java.io.IOException, javax.servlet.ServletException
  {  
    if(this.isAsynchronous())
    {
      req.getRequestDispatcher(jsp_dir+jsp).forward(req, resp);
    }
    else
    {
      req.setAttribute("jsp", jsp);
      req.getRequestDispatcher(layout).forward(req, resp);
    }
  }
  
  private void render() throws java.io.IOException, javax.servlet.ServletException
  {
      String[] split_path = req.getServletPath().split("\\.");
	  String action = split_path[split_path.length - 2];
      render(action + "Component.jsp");
  }
  
  
  public void viewAll() throws java.io.IOException, javax.servlet.ServletException
  {
	com.terraframe.mojo.constants.ClientRequestIF clientRequest = super.getClientRequest();
    mdss.entomology.MosquitoCollectionQueryDTO query = mdss.entomology.MosquitoCollectionDTO.getAllInstances(clientRequest, null, true, 20, 1);
    req.setAttribute("query", query);  
    req.setAttribute("page_title", "View All");
    render();
  }
  public void failViewAll() throws java.io.IOException, javax.servlet.ServletException
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
  
  public void newInstance() throws java.io.IOException, javax.servlet.ServletException
  {
    com.terraframe.mojo.constants.ClientRequestIF clientRequest = super.getClientRequest();
    mdss.entomology.MosquitoCollectionDTO dto = new mdss.entomology.MosquitoCollectionDTO(clientRequest);
    req.setAttribute("mdss_entomology_MosquitoCollection_collectionMethod", mdss.entomology.CollectionMethodDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("mdss_entomology_AbstractMosquitoCollection_geoEntity", mdss.test.GeoEntityDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("item", dto);
    req.setAttribute("page_title", "Create");
    render();
   
  }
  public void failNewInstance() throws java.io.IOException, javax.servlet.ServletException
  {
    this.viewAll();
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
    req.setAttribute("mdss_entomology_MosquitoCollection_collectionMethod", mdss.entomology.CollectionMethodDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("mdss_entomology_AbstractMosquitoCollection_geoEntity", mdss.test.GeoEntityDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("item", dto);
    req.setAttribute("page_title", "Update Failed ");
    render();
  }
  
  public void viewPage(java.lang.String sortAttribute, java.lang.Boolean isAscending, java.lang.Integer pageSize, java.lang.Integer pageNumber) throws java.io.IOException, javax.servlet.ServletException
  {
    com.terraframe.mojo.constants.ClientRequestIF clientRequest = super.getClientRequest();
    mdss.entomology.MosquitoCollectionQueryDTO query = mdss.entomology.MosquitoCollectionDTO.getAllInstances(clientRequest, sortAttribute, isAscending, pageSize, pageNumber);
    req.setAttribute("query", query);
    req.setAttribute("page_title", "View All ");
    render("viewAll.jsp");
  }
  
  public void failViewPage(java.lang.String sortAttribute, java.lang.String isAscending, java.lang.String pageSize, java.lang.String pageNumber) throws java.io.IOException, javax.servlet.ServletException
  {
    resp.sendError(500);
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
    req.setAttribute("mdss_entomology_MosquitoCollection_collectionMethod", mdss.entomology.CollectionMethodDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("mdss_entomology_AbstractMosquitoCollection_geoEntity", mdss.test.GeoEntityDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("item", dto);
    req.setAttribute("page_title", "Create Failed");
    render("createComponent.jsp");
  }
  public void failSearchByGeoEntityAndDate(mdss.test.GeoEntityDTO geoEntity, java.lang.String collectionDate) throws java.io.IOException, javax.servlet.ServletException
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
    req.setAttribute("mdss_entomology_MosquitoCollection_collectionMethod", mdss.entomology.CollectionMethodDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("mdss_entomology_AbstractMosquitoCollection_geoEntity", mdss.test.GeoEntityDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("item", dto);
    req.setAttribute("page_title", "Delete Failed");
    render("edit.jsp");
  }
  public void edit(java.lang.String id) throws java.io.IOException, javax.servlet.ServletException
  {
    mdss.entomology.MosquitoCollectionDTO dto = mdss.entomology.MosquitoCollectionDTO.lock(super.getClientRequest(), id);
    req.setAttribute("mdss_entomology_MosquitoCollection_collectionMethod", mdss.entomology.CollectionMethodDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("mdss_entomology_AbstractMosquitoCollection_geoEntity", mdss.test.GeoEntityDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("item", dto);
    req.setAttribute("page_title", "Edit");
    render();
  }
  public void failEdit(java.lang.String id) throws java.io.IOException, javax.servlet.ServletException
  {
    this.view(id);
  }
  public void view(java.lang.String id) throws java.io.IOException, javax.servlet.ServletException
  {
    com.terraframe.mojo.constants.ClientRequestIF clientRequest = super.getClientRequest();
    req.setAttribute("mdss_entomology_MosquitoCollection_collectionMethod", mdss.entomology.CollectionMethodDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("mdss_entomology_AbstractMosquitoCollection_geoEntity", mdss.test.GeoEntityDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("item", mdss.entomology.MosquitoCollectionDTO.get(clientRequest, id));
    req.setAttribute("page_title", "View");
    render();
  }
  public void failView(java.lang.String id) throws java.io.IOException, javax.servlet.ServletException
  {
    this.viewAll();
  }
  
  @Override
  public void searchByGeoIdAndDate(String geoId, Date collectionDate) throws IOException,
      ServletException
  {
    this.searchByGeoEntityAndDate(GeoEntityDTO.searchByGeoId(super.getClientRequest(), geoId), collectionDate);
  }
  
  public void searchByGeoEntityAndDate(GeoEntityDTO geoEntity, Date collectionDate) throws IOException, ServletException
  {
    MosquitoCollectionDTO collection = MosquitoCollectionDTO.searchByGeoEntityAndDate(super.getClientRequest(), geoEntity, collectionDate);
    //String jsp = (this.isAsynchronous() ? "viewComponent.jsp" : "view.jsp");
    String jsp =  "view.jsp";
    if(collection == null)
    {
      collection = new MosquitoCollectionDTO(super.getClientRequest());
      collection.setDateCollected(collectionDate);
      collection.setGeoEntity(geoEntity);

      //jsp = (this.isAsynchronous() ? "createComponent.jsp" : "create.jsp");
      jsp = "createComponent.jsp";
      
    }

    req.setAttribute("mdss_entomology_MosquitoCollection_collectionMethod", mdss.entomology.CollectionMethodDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("mdss_entomology_AbstractMosquitoCollection_geoEntity", mdss.test.GeoEntityDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("item", collection);
    req.setAttribute("page_title", "Find");
    //req.getRequestDispatcher("WEB-INF/mdss/entomology/MosquitoCollection/" + jsp).forward(req, resp);
    render(jsp);
  }
}
