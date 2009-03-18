package dss.vector.solutions.entomology.assay;

import com.terraframe.mojo.ProblemExceptionDTO;

import dss.vector.solutions.util.ErrorUtility;

public class EfficacyAssayController extends EfficacyAssayControllerBase implements com.terraframe.mojo.generation.loader.Reloadable
{
  public static final String JSP_DIR = "WEB-INF/dss/vector/solutions/entomology/assay/EfficacyAssay/";
  public static final String LAYOUT = JSP_DIR + "layout.jsp";
  
  private static final long serialVersionUID = 1236363373105L;
  
  public EfficacyAssayController(javax.servlet.http.HttpServletRequest req, javax.servlet.http.HttpServletResponse resp, java.lang.Boolean isAsynchronous)
  {
    super(req, resp, isAsynchronous, JSP_DIR, LAYOUT);
  }
  
  public void create(dss.vector.solutions.entomology.assay.EfficacyAssayDTO dto) throws java.io.IOException, javax.servlet.ServletException
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
  public void failCreate(dss.vector.solutions.entomology.assay.EfficacyAssayDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
	req.setAttribute("geoEntintys", dss.vector.solutions.geo.generated.GeoEntityDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
	req.setAttribute("insecticide", dss.vector.solutions.general.InsecticideDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("surfacePostion", dss.vector.solutions.SurfacePositionDTO.allItems(super.getClientSession().getRequest()));
    req.setAttribute("generation", dss.vector.solutions.mo.GenerationDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("identificationMethod", dss.vector.solutions.mo.IdentificationMethodDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("sex", dss.vector.solutions.entomology.AssaySexDTO.allItems(super.getClientSession().getRequest()));
    req.setAttribute("specie", dss.vector.solutions.mo.SpecieDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("testMethod", dss.vector.solutions.mo.ResistanceMethodologyDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("item", dto);
    req.setAttribute("page_title", "Create EfficacyAssayController");
    render("createComponent.jsp");
  }
  public void viewAll() throws java.io.IOException, javax.servlet.ServletException
  {
    com.terraframe.mojo.constants.ClientRequestIF clientRequest = super.getClientRequest();
    dss.vector.solutions.entomology.assay.EfficacyAssayQueryDTO query = dss.vector.solutions.entomology.assay.EfficacyAssayDTO.getAllInstances(clientRequest, null, true, 20, 1);
    req.setAttribute("query", query);
    req.setAttribute("page_title", "View All EfficacyAssayController Objects");
    render("viewAllComponent.jsp");
  }
  public void failViewAll() throws java.io.IOException, javax.servlet.ServletException
  {
    resp.sendError(500);
  }
  public void cancel(dss.vector.solutions.entomology.assay.EfficacyAssayDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    dto.unlock();
    this.view(dto.getId());
  }
  public void failCancel(dss.vector.solutions.entomology.assay.EfficacyAssayDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    resp.sendError(500);
  }
  public void edit(java.lang.String id) throws java.io.IOException, javax.servlet.ServletException
  {
    dss.vector.solutions.entomology.assay.EfficacyAssayDTO dto = dss.vector.solutions.entomology.assay.EfficacyAssayDTO.lock(super.getClientRequest(), id);
    req.setAttribute("geoEntintys", dss.vector.solutions.geo.generated.GeoEntityDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("insecticide", dss.vector.solutions.general.InsecticideDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("surfacePostion", dss.vector.solutions.SurfacePositionDTO.allItems(super.getClientSession().getRequest()));
    req.setAttribute("generation", dss.vector.solutions.mo.GenerationDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("identificationMethod", dss.vector.solutions.mo.IdentificationMethodDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("sex", dss.vector.solutions.entomology.AssaySexDTO.allItems(super.getClientSession().getRequest()));
    req.setAttribute("specie", dss.vector.solutions.mo.SpecieDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("testMethod", dss.vector.solutions.mo.ResistanceMethodologyDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("item", dto);
    req.setAttribute("page_title", "Edit EfficacyAssayController");
    render("editComponent.jsp");
  }
  public void failEdit(java.lang.String id) throws java.io.IOException, javax.servlet.ServletException
  {
    this.view(id);
  }
  public void update(dss.vector.solutions.entomology.assay.EfficacyAssayDTO dto) throws java.io.IOException, javax.servlet.ServletException
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
  public void failUpdate(dss.vector.solutions.entomology.assay.EfficacyAssayDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
	req.setAttribute("geoEntintys", dss.vector.solutions.geo.generated.GeoEntityDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("insecticide", dss.vector.solutions.general.InsecticideDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("surfacePostion", dss.vector.solutions.SurfacePositionDTO.allItems(super.getClientSession().getRequest()));
    req.setAttribute("generation", dss.vector.solutions.mo.GenerationDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("identificationMethod", dss.vector.solutions.mo.IdentificationMethodDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("sex", dss.vector.solutions.entomology.AssaySexDTO.allItems(super.getClientSession().getRequest()));
    req.setAttribute("specie", dss.vector.solutions.mo.SpecieDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("testMethod", dss.vector.solutions.mo.ResistanceMethodologyDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("item", dto);
    req.setAttribute("page_title", "Update EfficacyAssayController");
    render("editComponent.jsp");
  }
  public void view(java.lang.String id) throws java.io.IOException, javax.servlet.ServletException
  {
    com.terraframe.mojo.constants.ClientRequestIF clientRequest = super.getClientRequest();
    req.setAttribute("geoEntintys", dss.vector.solutions.geo.generated.GeoEntityDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("insecticide", dss.vector.solutions.general.InsecticideDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("surfacePostion", dss.vector.solutions.SurfacePositionDTO.allItems(super.getClientSession().getRequest()));
    req.setAttribute("generation", dss.vector.solutions.mo.GenerationDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("identificationMethod", dss.vector.solutions.mo.IdentificationMethodDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("sex", dss.vector.solutions.entomology.AssaySexDTO.allItems(super.getClientSession().getRequest()));
    req.setAttribute("specie", dss.vector.solutions.mo.SpecieDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("testMethod", dss.vector.solutions.mo.ResistanceMethodologyDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("item", dss.vector.solutions.entomology.assay.EfficacyAssayDTO.get(clientRequest, id));
    req.setAttribute("page_title", "View EfficacyAssayController");
    render("viewComponent.jsp");
  }
  public void failView(java.lang.String id) throws java.io.IOException, javax.servlet.ServletException
  {
    this.viewAll();
  }
  public void delete(dss.vector.solutions.entomology.assay.EfficacyAssayDTO dto) throws java.io.IOException, javax.servlet.ServletException
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
  public void failDelete(dss.vector.solutions.entomology.assay.EfficacyAssayDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    req.setAttribute("geoEntintys", dss.vector.solutions.geo.generated.GeoEntityDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
	req.setAttribute("insecticide", dss.vector.solutions.general.InsecticideDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("surfacePostion", dss.vector.solutions.SurfacePositionDTO.allItems(super.getClientSession().getRequest()));
    req.setAttribute("generation", dss.vector.solutions.mo.GenerationDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("identificationMethod", dss.vector.solutions.mo.IdentificationMethodDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("sex", dss.vector.solutions.entomology.AssaySexDTO.allItems(super.getClientSession().getRequest()));
    req.setAttribute("specie", dss.vector.solutions.mo.SpecieDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("testMethod", dss.vector.solutions.mo.ResistanceMethodologyDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("item", dto);
    req.setAttribute("page_title", "Edit EfficacyAssayController");
    render("editComponent.jsp");
  }
  public void viewPage(java.lang.String sortAttribute, java.lang.Boolean isAscending, java.lang.Integer pageSize, java.lang.Integer pageNumber) throws java.io.IOException, javax.servlet.ServletException
  {
    com.terraframe.mojo.constants.ClientRequestIF clientRequest = super.getClientRequest();
    dss.vector.solutions.entomology.assay.EfficacyAssayQueryDTO query = dss.vector.solutions.entomology.assay.EfficacyAssayDTO.getAllInstances(clientRequest, sortAttribute, isAscending, pageSize, pageNumber);
    req.setAttribute("query", query);
    req.setAttribute("page_title", "View All EfficacyAssayController Objects");
    render("viewAllComponent.jsp");
  }
  public void failViewPage(java.lang.String sortAttribute, java.lang.String isAscending, java.lang.String pageSize, java.lang.String pageNumber) throws java.io.IOException, javax.servlet.ServletException
  {
    resp.sendError(500);
  }
  public void newInstance() throws java.io.IOException, javax.servlet.ServletException
  {
    com.terraframe.mojo.constants.ClientRequestIF clientRequest = super.getClientRequest();
    dss.vector.solutions.entomology.assay.EfficacyAssayDTO dto = new dss.vector.solutions.entomology.assay.EfficacyAssayDTO(clientRequest);
    req.setAttribute("geoEntintys", dss.vector.solutions.geo.generated.GeoEntityDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("insecticide", dss.vector.solutions.general.InsecticideDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("surfacePostion", dss.vector.solutions.SurfacePositionDTO.allItems(super.getClientSession().getRequest()));
    req.setAttribute("generation", dss.vector.solutions.mo.GenerationDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("identificationMethod", dss.vector.solutions.mo.IdentificationMethodDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("sex", dss.vector.solutions.entomology.AssaySexDTO.allItems(super.getClientSession().getRequest()));
    req.setAttribute("specie", dss.vector.solutions.mo.SpecieDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("testMethod", dss.vector.solutions.mo.ResistanceMethodologyDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("item", dto);
    req.setAttribute("page_title", "Create EfficacyAssayController");
    render("createComponent.jsp");
  }
  public void failNewInstance() throws java.io.IOException, javax.servlet.ServletException
  {
    this.viewAll();
  }
}
