package dss.vector.solutions.entomology.assay;

import java.io.IOException;
import java.util.Arrays;

import javax.servlet.ServletException;

import com.terraframe.mojo.ProblemExceptionDTO;
import com.terraframe.mojo.constants.ClientRequestIF;

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

  public void create(EfficacyAssayViewDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    try
    {
      dto.apply();
      this.view(dto);
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

  public void failCreate(EfficacyAssayViewDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    this.loadRequestParameters();
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
  
  public void cancel(EfficacyAssayViewDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {    
    this.view(EfficacyAssayDTO.unlockView(this.getClientRequest(), dto.getConcreteId()));
  }
  
  public void failCancel(EfficacyAssayViewDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    resp.sendError(500);
  }
  public void edit(java.lang.String id) throws java.io.IOException, javax.servlet.ServletException
  {
    EfficacyAssayViewDTO dto = EfficacyAssayDTO.lockView(super.getClientRequest(), id);

    this.loadRequestParameters();
    req.setAttribute("item", dto);

    render("editComponent.jsp");
  }
  public void failEdit(java.lang.String id) throws java.io.IOException, javax.servlet.ServletException
  {
    this.view(id);
  }
  public void update(EfficacyAssayViewDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    try
    {
      dto.apply();
      this.view(dto);
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
  
  public void failUpdate(EfficacyAssayViewDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    this.loadRequestParameters();
    req.setAttribute("item", dto);

    render("editComponent.jsp");
  }
  
  public void view(java.lang.String id) throws java.io.IOException, javax.servlet.ServletException
  {
    this.view(EfficacyAssayDTO.getView(super.getClientRequest(), id));
  }
  
  public void view(EfficacyAssayViewDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    if (!req.getRequestURI().contains(".view.mojo"))
    {
      String path = req.getRequestURL().toString();
      path = path.replaceFirst("(\\w+)Controller", "EfficacyAssayController");
      resp.sendRedirect(path.replaceFirst("\\.[a-zA-Z]+\\.mojo", ".view.mojo") + "?id=" + dto.getConcreteId());
      return;
    }

    req.setAttribute("item", dto);
    render("viewComponent.jsp");
  }
  
  public void failView(java.lang.String id) throws java.io.IOException, javax.servlet.ServletException
  {
    this.viewAll();
  }
  
  public void delete(EfficacyAssayViewDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    try
    {
      dto.deleteConcrete();
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
  
  public void failDelete(EfficacyAssayViewDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    this.loadRequestParameters();
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
    EfficacyAssayViewDTO dto = new EfficacyAssayViewDTO(super.getClientRequest());

    this.loadRequestParameters();
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
    this.cloneAssay(EfficacyAssayDTO.getView(this.getClientRequest(), id));
  }


  public void cloneAssay(EfficacyAssayViewDTO dto) throws IOException, ServletException
  {
    ClientRequestIF clientRequest = super.getClientRequest();

    EfficacyAssayViewDTO clone = new EfficacyAssayViewDTO(clientRequest);
    clone.setGeoId(dto.getGeoId());
    clone.setTestDate(dto.getTestDate());
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

    this.loadRequestParameters();
    req.setAttribute("item", clone);

    render("createComponent.jsp");
  }
  
  private void loadRequestParameters()
  {
    req.setAttribute("insecticide", InsecticideDTO.getAll(super.getClientSession().getRequest()));
    req.setAttribute("surfacePostion", SurfacePositionDTO.allItems(super.getClientSession().getRequest()));
    req.setAttribute("generation", Arrays.asList(GenerationDTO.getAll(super.getClientSession().getRequest())));
    req.setAttribute("identificationMethod", Arrays.asList(IdentificationMethodDTO.getAll(super.getClientSession().getRequest())));
    req.setAttribute("sex", AssaySexDTO.allItems(super.getClientSession().getRequest()));
    req.setAttribute("specie", Arrays.asList(SpecieDTO.getAll(super.getClientSession().getRequest())));
    req.setAttribute("testMethod", Arrays.asList(ResistanceMethodologyDTO.getAll(super.getClientSession().getRequest())));
  }

}
