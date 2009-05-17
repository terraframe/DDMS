package dss.vector.solutions.entomology.assay;

import java.io.IOException;

import javax.servlet.ServletException;

import com.terraframe.mojo.ProblemExceptionDTO;

import dss.vector.solutions.SurfacePositionDTO;
import dss.vector.solutions.entomology.AssaySexDTO;
import dss.vector.solutions.general.InsecticideDTO;
import dss.vector.solutions.mo.GenerationDTO;
import dss.vector.solutions.mo.IdentificationMethodDTO;
import dss.vector.solutions.mo.ResistanceMethodologyDTO;
import dss.vector.solutions.mo.SpecieDTO;
import dss.vector.solutions.util.ErrorUtility;

public class EfficacyAssayController extends EfficacyAssayControllerBase implements com.terraframe.mojo.generation.loader.Reloadable
{
  public static final String JSP_DIR = "WEB-INF/dss/vector/solutions/entomology/assay/EfficacyAssay/";
  public static final String LAYOUT = "/layout.jsp";

  private static final long serialVersionUID = 1236363373105L;

  public EfficacyAssayController(javax.servlet.http.HttpServletRequest req, javax.servlet.http.HttpServletResponse resp, java.lang.Boolean isAsynchronous)
  {
    super(req, resp, isAsynchronous, JSP_DIR, LAYOUT);
  }

  public void create(EfficacyAssayDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    try
    {
      dto.apply();
      this.view(dto.getId());
    }
    catch(ProblemExceptionDTO e)
    {
      ErrorUtility.forceProblems(e, req);

      this.failCreate(dto);
    }
    catch(Throwable t)
    {
      ErrorUtility.prepareThrowable(t, req);

      this.failCreate(dto);
    }
  }
  public void failCreate(EfficacyAssayDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
	//req.setAttribute("geoEntintys", GeoEntityDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
	req.setAttribute("insecticide", InsecticideDTO.getAll(super.getClientSession().getRequest()));
    req.setAttribute("surfacePostion", SurfacePositionDTO.allItems(super.getClientSession().getRequest()));
    req.setAttribute("generation", GenerationDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("identificationMethod", IdentificationMethodDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("sex", AssaySexDTO.allItems(super.getClientSession().getRequest()));
    req.setAttribute("specie", SpecieDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("testMethod", ResistanceMethodologyDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("item", dto);

    render("createComponent.jsp");
  }
  public void viewAll() throws java.io.IOException, javax.servlet.ServletException
  {
    com.terraframe.mojo.constants.ClientRequestIF clientRequest = super.getClientRequest();
    EfficacyAssayQueryDTO query = EfficacyAssayDTO.getAllInstances(clientRequest, null, true, 20, 1);
    req.setAttribute("query", query);

    render("viewAllComponent.jsp");
  }
  public void failViewAll() throws java.io.IOException, javax.servlet.ServletException
  {
    resp.sendError(500);
  }
  public void cancel(EfficacyAssayDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    dto.unlock();
    this.view(dto.getId());
  }
  public void failCancel(EfficacyAssayDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    resp.sendError(500);
  }
  public void edit(java.lang.String id) throws java.io.IOException, javax.servlet.ServletException
  {
    EfficacyAssayDTO dto = EfficacyAssayDTO.lock(super.getClientRequest(), id);
    //req.setAttribute("geoEntintys", GeoEntityDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("insecticide", InsecticideDTO.getAll(super.getClientSession().getRequest()));
    req.setAttribute("surfacePostion", SurfacePositionDTO.allItems(super.getClientSession().getRequest()));
    req.setAttribute("generation", GenerationDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("identificationMethod", IdentificationMethodDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("sex", AssaySexDTO.allItems(super.getClientSession().getRequest()));
    req.setAttribute("specie", SpecieDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("testMethod", ResistanceMethodologyDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("item", dto);

    render("editComponent.jsp");
  }
  public void failEdit(java.lang.String id) throws java.io.IOException, javax.servlet.ServletException
  {
    this.view(id);
  }
  public void update(EfficacyAssayDTO dto) throws java.io.IOException, javax.servlet.ServletException
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
  public void failUpdate(EfficacyAssayDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
	//req.setAttribute("geoEntintys", GeoEntityDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("insecticide", InsecticideDTO.getAll(super.getClientSession().getRequest()));
    req.setAttribute("surfacePostion", SurfacePositionDTO.allItems(super.getClientSession().getRequest()));
    req.setAttribute("generation", GenerationDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("identificationMethod", IdentificationMethodDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("sex", AssaySexDTO.allItems(super.getClientSession().getRequest()));
    req.setAttribute("specie", SpecieDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("testMethod", ResistanceMethodologyDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("item", dto);

    render("editComponent.jsp");
  }
  public void view(java.lang.String id) throws java.io.IOException, javax.servlet.ServletException
  {
    com.terraframe.mojo.constants.ClientRequestIF clientRequest = super.getClientRequest();
    //req.setAttribute("geoEntintys", GeoEntityDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("insecticide", InsecticideDTO.getAll(super.getClientSession().getRequest()));
    req.setAttribute("surfacePostion", SurfacePositionDTO.allItems(super.getClientSession().getRequest()));
    req.setAttribute("generation", GenerationDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("identificationMethod", IdentificationMethodDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("sex", AssaySexDTO.allItems(super.getClientSession().getRequest()));
    req.setAttribute("specie", SpecieDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("testMethod", ResistanceMethodologyDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("item", EfficacyAssayDTO.get(clientRequest, id));

    render("viewComponent.jsp");
  }
  public void failView(java.lang.String id) throws java.io.IOException, javax.servlet.ServletException
  {
    this.viewAll();
  }
  public void delete(EfficacyAssayDTO dto) throws java.io.IOException, javax.servlet.ServletException
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
  public void failDelete(EfficacyAssayDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    //req.setAttribute("geoEntintys", GeoEntityDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
	req.setAttribute("insecticide", InsecticideDTO.getAll(super.getClientSession().getRequest()));
    req.setAttribute("surfacePostion", SurfacePositionDTO.allItems(super.getClientSession().getRequest()));
    req.setAttribute("generation", GenerationDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("identificationMethod", IdentificationMethodDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("sex", AssaySexDTO.allItems(super.getClientSession().getRequest()));
    req.setAttribute("specie", SpecieDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("testMethod", ResistanceMethodologyDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("item", dto);

    render("editComponent.jsp");
  }
  public void viewPage(java.lang.String sortAttribute, java.lang.Boolean isAscending, java.lang.Integer pageSize, java.lang.Integer pageNumber) throws java.io.IOException, javax.servlet.ServletException
  {
    com.terraframe.mojo.constants.ClientRequestIF clientRequest = super.getClientRequest();
    EfficacyAssayQueryDTO query = EfficacyAssayDTO.getAllInstances(clientRequest, sortAttribute, isAscending, pageSize, pageNumber);
    req.setAttribute("query", query);

    render("viewAllComponent.jsp");
  }
  public void failViewPage(java.lang.String sortAttribute, java.lang.String isAscending, java.lang.String pageSize, java.lang.String pageNumber) throws java.io.IOException, javax.servlet.ServletException
  {
    resp.sendError(500);
  }
  public void newInstance() throws java.io.IOException, javax.servlet.ServletException
  {
    com.terraframe.mojo.constants.ClientRequestIF clientRequest = super.getClientRequest();
    EfficacyAssayDTO dto = new EfficacyAssayDTO(clientRequest);
    //req.setAttribute("geoEntintys", GeoEntityDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("insecticide", InsecticideDTO.getAll(super.getClientSession().getRequest()));
    req.setAttribute("surfacePostion", SurfacePositionDTO.allItems(super.getClientSession().getRequest()));
    req.setAttribute("generation", GenerationDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("identificationMethod", IdentificationMethodDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("sex", AssaySexDTO.allItems(super.getClientSession().getRequest()));
    req.setAttribute("specie", SpecieDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("testMethod", ResistanceMethodologyDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("item", dto);

    render("createComponent.jsp");
  }
  public void failNewInstance() throws java.io.IOException, javax.servlet.ServletException
  {
    this.viewAll();
  }

  @Override
  public void cloneAssay(String id) throws IOException, ServletException
  {
    this.cloneAssay(EfficacyAssayDTO.get(this.getClientRequest(), id));
  }


  public void cloneAssay(EfficacyAssayDTO dto) throws IOException, ServletException
  {
    com.terraframe.mojo.constants.ClientRequestIF clientRequest = super.getClientRequest();

    EfficacyAssayDTO clone = new EfficacyAssayDTO(clientRequest);
    clone.setGeoEntity(dto.getGeoEntity());
    clone.setTestMethod(dto.getTestMethod());
    clone.setSpecie(dto.getSpecie());
    clone.setColonyName(dto.getColonyName());
    clone.setTimeOnSurface(dto.getTimeOnSurface());
    clone.setGravid(dto.getGravid());
    clone.setFed(dto.getFed());
    clone.setInsecticide(dto.getInsecticide());
    clone.getAgeRange().setStartPoint(dto.getAgeRange().getStartPoint());
    clone.getAgeRange().setEndPoint(dto.getAgeRange().getEndPoint());

    for(AssaySexDTO sex : dto.getSex())
    {
      clone.addSex(sex);
    }

    //req.setAttribute("geoEntintys", GeoEntityDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("insecticide", InsecticideDTO.getAll(super.getClientSession().getRequest()));
    req.setAttribute("surfacePostion", SurfacePositionDTO.allItems(super.getClientSession().getRequest()));
    req.setAttribute("generation", GenerationDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("identificationMethod", IdentificationMethodDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("sex", AssaySexDTO.allItems(super.getClientSession().getRequest()));
    req.setAttribute("specie", SpecieDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("testMethod", ResistanceMethodologyDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("item", clone);


    render("createComponent.jsp");
  }
}
