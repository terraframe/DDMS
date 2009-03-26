package dss.vector.solutions.entomology;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;

import dss.vector.solutions.entomology.MosquitoCollectionPointControllerBase;
import dss.vector.solutions.geo.GeoEntityTreeController;
import dss.vector.solutions.geo.generated.EarthDTO;
import dss.vector.solutions.geo.generated.GeoEntityDTO;
import dss.vector.solutions.util.DateConverter;

public class MosquitoCollectionPointController extends MosquitoCollectionPointControllerBase implements com.terraframe.mojo.generation.loader.Reloadable
{
  public static final String JSP_DIR = "WEB-INF/dss/vector/solutions/entomology/MosquitoCollectionPoint/";
  public static final String LAYOUT = JSP_DIR + "layout.jsp";
  
  private static final long serialVersionUID = 1235073586764L;
  
  public MosquitoCollectionPointController(javax.servlet.http.HttpServletRequest req, javax.servlet.http.HttpServletResponse resp, java.lang.Boolean isAsynchronous)
  {
    super(req, resp, isAsynchronous, JSP_DIR, LAYOUT);
  }
  
  public void create(dss.vector.solutions.entomology.MosquitoCollectionPointDTO dto) throws java.io.IOException, javax.servlet.ServletException
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
  public void failCreate(dss.vector.solutions.entomology.MosquitoCollectionPointDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    req.setAttribute("mdss_ivcc_mrc_csu_entomology_AbstractMosquitoCollection_geoEntity", dss.vector.solutions.geo.generated.GeoEntityDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("item", dto);
    req.setAttribute("page_title", "Create MosquitoCollectionPointController");
    render("createComponent.jsp");
  }
  public void newInstance() throws java.io.IOException, javax.servlet.ServletException
  {
    com.terraframe.mojo.constants.ClientRequestIF clientRequest = super.getClientRequest();
    dss.vector.solutions.entomology.MosquitoCollectionPointDTO dto = new dss.vector.solutions.entomology.MosquitoCollectionPointDTO(clientRequest);
    req.setAttribute("mdss_ivcc_mrc_csu_entomology_AbstractMosquitoCollection_geoEntity", dss.vector.solutions.geo.generated.GeoEntityDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("item", dto);
    req.setAttribute("page_title", "Create MosquitoCollectionPointController");
    render("createComponent.jsp");
  }
  public void failNewInstance() throws java.io.IOException, javax.servlet.ServletException
  {
    this.viewAll();
  }
  public void cancel(dss.vector.solutions.entomology.MosquitoCollectionPointDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    dto.unlock();
    this.view(dto.getId());
  }
  public void failCancel(dss.vector.solutions.entomology.MosquitoCollectionPointDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    resp.sendError(500);
  }
  public void viewPage(java.lang.String sortAttribute, java.lang.Boolean isAscending, java.lang.Integer pageSize, java.lang.Integer pageNumber) throws java.io.IOException, javax.servlet.ServletException
  {
    com.terraframe.mojo.constants.ClientRequestIF clientRequest = super.getClientRequest();
    dss.vector.solutions.entomology.MosquitoCollectionPointQueryDTO query = dss.vector.solutions.entomology.MosquitoCollectionPointDTO.getAllInstances(clientRequest, sortAttribute, isAscending, pageSize, pageNumber);
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
    req.setAttribute("mdss_ivcc_mrc_csu_entomology_AbstractMosquitoCollection_geoEntity", dss.vector.solutions.geo.generated.GeoEntityDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("item", dss.vector.solutions.entomology.MosquitoCollectionPointDTO.get(clientRequest, id));
    req.setAttribute("page_title", "View MosquitoCollectionPointController");
    render("viewComponent.jsp");
  }
  public void failView(java.lang.String id) throws java.io.IOException, javax.servlet.ServletException
  {
    this.viewAll();
  }
  public void edit(java.lang.String id) throws java.io.IOException, javax.servlet.ServletException
  {
    dss.vector.solutions.entomology.MosquitoCollectionPointDTO dto = dss.vector.solutions.entomology.MosquitoCollectionPointDTO.lock(super.getClientRequest(), id);
    req.setAttribute("mdss_ivcc_mrc_csu_entomology_AbstractMosquitoCollection_geoEntity", dss.vector.solutions.geo.generated.GeoEntityDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("item", dto);
    req.setAttribute("page_title", "Edit MosquitoCollectionPointController");
    render("editComponent.jsp");
  }
  public void failEdit(java.lang.String id) throws java.io.IOException, javax.servlet.ServletException
  {
    this.view(id);
  }
  public void update(dss.vector.solutions.entomology.MosquitoCollectionPointDTO dto) throws java.io.IOException, javax.servlet.ServletException
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
  public void failUpdate(dss.vector.solutions.entomology.MosquitoCollectionPointDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    req.setAttribute("mdss_ivcc_mrc_csu_entomology_AbstractMosquitoCollection_geoEntity", dss.vector.solutions.geo.generated.GeoEntityDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("item", dto);
    req.setAttribute("page_title", "Update MosquitoCollectionPointController");
    render("editComponent.jsp");
  }
  public void delete(dss.vector.solutions.entomology.MosquitoCollectionPointDTO dto) throws java.io.IOException, javax.servlet.ServletException
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
  public void failDelete(dss.vector.solutions.entomology.MosquitoCollectionPointDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    req.setAttribute("mdss_ivcc_mrc_csu_entomology_AbstractMosquitoCollection_geoEntity", dss.vector.solutions.geo.generated.GeoEntityDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("item", dto);
    req.setAttribute("page_title", "Edit MosquitoCollectionPointController");
    render("editComponent.jsp");
  }
  public void viewAll() throws java.io.IOException, javax.servlet.ServletException
  {
    com.terraframe.mojo.constants.ClientRequestIF clientRequest = super.getClientRequest();
    dss.vector.solutions.entomology.MosquitoCollectionPointQueryDTO query = dss.vector.solutions.entomology.MosquitoCollectionPointDTO.getAllInstances(clientRequest, null, true, 20, 1);
    req.setAttribute("query", query);
    req.setAttribute("page_title", "View All MosquitoCollectionPointController Objects");
    render("viewAllComponent.jsp");
  }
  public void failViewAll() throws java.io.IOException, javax.servlet.ServletException
  {
    resp.sendError(500);
  }
  @Override
  public void searchByGeoIdAndDate(String geoId, Date startDate, Date endDate) throws IOException, ServletException
  {
	  GeoEntityDTO geoEntity = GeoEntityDTO.searchByGeoId(super.getClientRequest(), geoId);
	  if(geoEntity == null)
	  {
	    this.newInstance();
	  }
	  else
	  {
	    this.searchByGeoEntityAndDate(geoEntity, startDate, endDate);
	  }
  }
  
  public void failSearchByGeoIdAndDate(String geoId, String startDate, String endDate) throws java.io.IOException, javax.servlet.ServletException
  {
	  try
	  {
		Date sd =  (Date) new DateConverter("Start Date").parse(startDate, this.getRequest().getLocale());
		Date ed =  (Date) new DateConverter("End Date").parse(endDate, this.getRequest().getLocale());
	    this.searchByGeoIdAndDate(geoId, sd, ed);
	  }
	  catch(Exception e)
	  {
	    req.setAttribute("page_title", "Search For Mosquito Collections");
	    render("searchComponent.jsp");	  
	  }
  }
  
  
  public void search() throws java.io.IOException, javax.servlet.ServletException
  {
    // The Earth is the root.
    EarthDTO earth = EarthDTO.getEarthInstance(this.getClientRequest());
    req.setAttribute(GeoEntityTreeController.ROOT_GEO_ENTITY_ID, earth.getId());
    
    req.setAttribute("page_title", "Search For Mosquito Collection Points");
    render("searchComponent.jsp");
  }
  
  public void searchByGeoEntityAndDate(GeoEntityDTO geoEntity, Date startDate, Date endDate) throws IOException, ServletException
  {
	MorphologicalSpecieGroupViewDTO[] collection = MosquitoCollectionPointDTO.searchByGeoEntityAndDate(super.getClientRequest(), geoEntity,startDate,endDate);
    String jsp =  "viewAllComponent.jsp";
    req.setAttribute("page_title", "Mosquito Collection Points");
    req.setAttribute("geoEntity", geoEntity);
    req.setAttribute("startDate", startDate);
    req.setAttribute("endDate", endDate);
    req.setAttribute("collection_points", collection);
    
    render(jsp);
  }
}
