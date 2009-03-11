package csu.mrc.ivcc.mdss.entomology;


import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;

import com.terraframe.mojo.ProblemExceptionDTO;

import csu.mrc.ivcc.mdss.geo.GeoEntityTreeController;
import csu.mrc.ivcc.mdss.geo.generated.CountryDTO;
import csu.mrc.ivcc.mdss.geo.generated.CountryQueryDTO;
import csu.mrc.ivcc.mdss.geo.generated.GeoEntityDTO;
import csu.mrc.ivcc.mdss.util.DateConverter;
import csu.mrc.ivcc.mdss.util.ErrorUtility;


public class MosquitoCollectionController extends MosquitoCollectionControllerBase implements com.terraframe.mojo.generation.loader.Reloadable
{
  public static final String JSP_DIR = "WEB-INF/csu/mrc/ivcc/mdss/entomology/MosquitoCollection/";
  public static final String LAYOUT = JSP_DIR + "layout.jsp";
  
  private static final long serialVersionUID = 1235073590401L;
  
  public MosquitoCollectionController(javax.servlet.http.HttpServletRequest req, javax.servlet.http.HttpServletResponse resp, java.lang.Boolean isAsynchronous)
  {
    super(req, resp, isAsynchronous, JSP_DIR, LAYOUT);
  }
  
  public void delete(csu.mrc.ivcc.mdss.entomology.MosquitoCollectionDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    try
    {
      dto.delete();
      this.viewAll();
    }
    catch(ProblemExceptionDTO e)
    {
      ErrorUtility.prepareProblems(e, req);
      
      this.failDelete(dto);
    }
    catch(Throwable t)
    {
      ErrorUtility.prepareThrowable(t, req); 
     
      this.failDelete(dto);
    }
  }
  public void failDelete(csu.mrc.ivcc.mdss.entomology.MosquitoCollectionDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    req.setAttribute("MosquitoCollection_collectionMethod", csu.mrc.ivcc.mdss.mo.CollectionMethodDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("AbstractMosquitoCollection_geoEntity", csu.mrc.ivcc.mdss.geo.generated.GeoEntityDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("item", dto);
    req.setAttribute("page_title", "Edit Mosquito Collections");
    render("editComponent.jsp");
  }
  public void newInstance() throws java.io.IOException, javax.servlet.ServletException
  {
    com.terraframe.mojo.constants.ClientRequestIF clientRequest = super.getClientRequest();
    csu.mrc.ivcc.mdss.entomology.MosquitoCollectionDTO dto = new csu.mrc.ivcc.mdss.entomology.MosquitoCollectionDTO(clientRequest);
    req.setAttribute("MosquitoCollection_collectionMethod", csu.mrc.ivcc.mdss.mo.CollectionMethodDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("AbstractMosquitoCollection_geoEntity", csu.mrc.ivcc.mdss.geo.generated.GeoEntityDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("item", dto);
    req.setAttribute("page_title", "Create Mosquito Collections");
    render("createComponent.jsp");
  }
  public void failNewInstance() throws java.io.IOException, javax.servlet.ServletException
  {
    this.viewAll();
  }
  public void update(csu.mrc.ivcc.mdss.entomology.MosquitoCollectionDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    try
    {
      dto.apply();
      this.view(dto.getId());
    }
    catch(ProblemExceptionDTO e)
    {
      ErrorUtility.prepareProblems(e, req);
      
      this.failUpdate(dto);
    }
    catch(Throwable t)
    {
      ErrorUtility.prepareThrowable(t, req); 
     
      this.failUpdate(dto);
    }
  }
  public void failUpdate(csu.mrc.ivcc.mdss.entomology.MosquitoCollectionDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    req.setAttribute("MosquitoCollection_collectionMethod", csu.mrc.ivcc.mdss.mo.CollectionMethodDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("AbstractMosquitoCollection_geoEntity", csu.mrc.ivcc.mdss.geo.generated.GeoEntityDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("item", dto);
    req.setAttribute("page_title", "Update a Mosquito Collection");
    render("editComponent.jsp");
  }
  public void failSearchByGeoEntityAndDate(csu.mrc.ivcc.mdss.geo.generated.GeoEntityDTO geoEntity, java.lang.String collectionDate) throws java.io.IOException, javax.servlet.ServletException
  {
    resp.sendError(500);
  }
  public void view(java.lang.String id) throws java.io.IOException, javax.servlet.ServletException
  {
    com.terraframe.mojo.constants.ClientRequestIF clientRequest = super.getClientRequest();
    req.setAttribute("MosquitoCollection_collectionMethod", csu.mrc.ivcc.mdss.mo.CollectionMethodDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("AbstractMosquitoCollection_geoEntity", csu.mrc.ivcc.mdss.geo.generated.GeoEntityDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("item", csu.mrc.ivcc.mdss.entomology.MosquitoCollectionDTO.get(clientRequest, id));
    req.setAttribute("page_title", "View a Mosquito Collection");
    render("viewComponent.jsp");
  }
  public void viewAssays(java.lang.String id) throws java.io.IOException, javax.servlet.ServletException
  {
    com.terraframe.mojo.constants.ClientRequestIF clientRequest = super.getClientRequest();
    req.setAttribute("MosquitoCollection_collectionMethod", csu.mrc.ivcc.mdss.mo.CollectionMethodDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("AbstractMosquitoCollection_geoEntity", csu.mrc.ivcc.mdss.geo.generated.GeoEntityDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("item", csu.mrc.ivcc.mdss.entomology.MosquitoCollectionDTO.get(clientRequest, id));
    req.setAttribute("page_title", "View a Mosquito Collection");
    render("viewAssaysComponent.jsp");
  }
  public void failView(java.lang.String id) throws java.io.IOException, javax.servlet.ServletException
  {
    this.viewAll();
  }
  public void cancel(csu.mrc.ivcc.mdss.entomology.MosquitoCollectionDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    dto.unlock();
    this.view(dto.getId());
  }
  public void failCancel(csu.mrc.ivcc.mdss.entomology.MosquitoCollectionDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    resp.sendError(500);
  }
  public void viewAll() throws java.io.IOException, javax.servlet.ServletException
  {
    com.terraframe.mojo.constants.ClientRequestIF clientRequest = super.getClientRequest();
    csu.mrc.ivcc.mdss.entomology.MosquitoCollectionQueryDTO query = csu.mrc.ivcc.mdss.entomology.MosquitoCollectionDTO.getAllInstances(clientRequest, null, true, 20, 1);
    req.setAttribute("query", query);
    req.setAttribute("page_title", "View All Mosquito Collections");
    render("viewAllComponent.jsp");
  }
  public void failViewAll() throws java.io.IOException, javax.servlet.ServletException
  {
    resp.sendError(500);
  }
  public void viewPage(java.lang.String sortAttribute, java.lang.Boolean isAscending, java.lang.Integer pageSize, java.lang.Integer pageNumber) throws java.io.IOException, javax.servlet.ServletException
  {
    com.terraframe.mojo.constants.ClientRequestIF clientRequest = super.getClientRequest();
    csu.mrc.ivcc.mdss.entomology.MosquitoCollectionQueryDTO query = csu.mrc.ivcc.mdss.entomology.MosquitoCollectionDTO.getAllInstances(clientRequest, sortAttribute, isAscending, pageSize, pageNumber);
    req.setAttribute("query", query);
    req.setAttribute("page_title", "View All Mosquito Collections");
    render("viewAllComponent.jsp");
  }
  public void failViewPage(java.lang.String sortAttribute, java.lang.String isAscending, java.lang.String pageSize, java.lang.String pageNumber) throws java.io.IOException, javax.servlet.ServletException
  {
    resp.sendError(500);
  }
  public void edit(java.lang.String id) throws java.io.IOException, javax.servlet.ServletException
  {
    csu.mrc.ivcc.mdss.entomology.MosquitoCollectionDTO dto = csu.mrc.ivcc.mdss.entomology.MosquitoCollectionDTO.lock(super.getClientRequest(), id);
    req.setAttribute("MosquitoCollection_collectionMethod", csu.mrc.ivcc.mdss.mo.CollectionMethodDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("AbstractMosquitoCollection_geoEntity", csu.mrc.ivcc.mdss.geo.generated.GeoEntityDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("item", dto);
    req.setAttribute("page_title", "Edit Mosquito Collection");
    render("editComponent.jsp");
  }
  public void failEdit(java.lang.String id) throws java.io.IOException, javax.servlet.ServletException
  {
    this.view(id);
  }
  public void create(csu.mrc.ivcc.mdss.entomology.MosquitoCollectionDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    try
    {
      dto.apply();
      this.view(dto.getId());
    }
    catch(ProblemExceptionDTO e)
    {
      ErrorUtility.prepareProblems(e, req);
      
      this.failCreate(dto);
    }
    catch(Throwable t)
    {
      ErrorUtility.prepareThrowable(t, req); 
     
      this.failCreate(dto);
    }
  }
  public void failCreate(csu.mrc.ivcc.mdss.entomology.MosquitoCollectionDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    req.setAttribute("MosquitoCollection_collectionMethod", csu.mrc.ivcc.mdss.mo.CollectionMethodDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("AbstractMosquitoCollection_geoEntity", csu.mrc.ivcc.mdss.geo.generated.GeoEntityDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("item", dto);
    req.setAttribute("page_title", "Create Mosquito Collection");
    render("createComponent.jsp");
  }
  @Override
  public void searchByGeoIdAndDate(String geoId, Date collectionDate) throws IOException, ServletException
  {
	  GeoEntityDTO geoEntity = GeoEntityDTO.searchByGeoId(super.getClientRequest(), geoId);
	  if(geoEntity == null)
	  {
	    this.newInstance();
	  }
	  else
	  {
	    this.searchByGeoEntityAndDate(geoEntity, collectionDate);
	  }
  }
  
  public void failSearchByGeoIdAndDate(String geoId, String collectionDate) throws java.io.IOException, javax.servlet.ServletException
  {
	  try
	  {
		Date d =  (Date) new DateConverter("Collection Date").parse(collectionDate, this.getRequest().getLocale());
	    this.searchByGeoIdAndDate(geoId, d);
	  }
	  catch(Exception e)
	  {
	    req.setAttribute("page_title", "Search For Mosquito Collections");
	    render("searchComponent.jsp");	  
	  }
  }
  
  
  public void search() throws java.io.IOException, javax.servlet.ServletException
  {
    // The Country is the root id of the tree for searching
    CountryQueryDTO query = CountryDTO.getAllInstances(this.getClientRequest(), CountryDTO.KEYNAME, true, 1, 1);
    CountryDTO country = query.getResultSet().get(0); // There should always be one country
    String rootGeoEntityId = country.getId();
    req.setAttribute(GeoEntityTreeController.ROOT_GEO_ENTITY_ID, rootGeoEntityId);
    
    req.setAttribute("page_title", "Search For Mosquito Collections");
    render("searchComponent.jsp");
  }
  
  public void searchByGeoEntityAndDate(GeoEntityDTO geoEntity, Date collectionDate) throws IOException, ServletException
  {
    MosquitoCollectionDTO collection = MosquitoCollectionDTO.searchByGeoEntityAndDate(super.getClientRequest(), geoEntity, collectionDate);
    String jsp =  "viewComponent.jsp";
    req.setAttribute("page_title", "Found Mosquito Collections");
    if(collection == null)
    {
      collection = new MosquitoCollectionDTO(super.getClientRequest());
      collection.setDateCollected(collectionDate);
      collection.setGeoEntity(geoEntity);
      req.setAttribute("page_title", "Mosquito Collection Not Found - Creating New");
      jsp = "createComponent.jsp";      
    }

    req.setAttribute("MosquitoCollection_collectionMethod", csu.mrc.ivcc.mdss.mo.CollectionMethodDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("AbstractMosquitoCollection_geoEntity", csu.mrc.ivcc.mdss.geo.generated.GeoEntityDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("item", collection);
    
    render(jsp);
  }

}