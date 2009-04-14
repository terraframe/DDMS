package dss.vector.solutions.entomology.assay;

import com.terraframe.mojo.ProblemExceptionDTO;

import dss.vector.solutions.entomology.MosquitoCollectionDTO;
import dss.vector.solutions.general.InsecticideDTO;
import dss.vector.solutions.mo.GenerationDTO;
import dss.vector.solutions.mo.IdentificationMethodDTO;
import dss.vector.solutions.mo.LarvaeAgeDTO;
import dss.vector.solutions.mo.ResistanceMethodologyDTO;
import dss.vector.solutions.mo.SpecieDTO;
import dss.vector.solutions.util.ErrorUtility;

public class LarvaeDiscriminatingDoseAssayController extends LarvaeDiscriminatingDoseAssayControllerBase implements com.terraframe.mojo.generation.loader.Reloadable
{
  public static final String JSP_DIR = "WEB-INF/dss/vector/solutions/entomology/assay/LarvaeDiscriminatingDoseAssay/";
  public static final String LAYOUT = JSP_DIR + "layout.jsp";

  private static final long serialVersionUID = 1236962666744L;

  public LarvaeDiscriminatingDoseAssayController(javax.servlet.http.HttpServletRequest req, javax.servlet.http.HttpServletResponse resp, java.lang.Boolean isAsynchronous)
  {
    super(req, resp, isAsynchronous, JSP_DIR, LAYOUT);
  }

  public void edit(java.lang.String id) throws java.io.IOException, javax.servlet.ServletException
  {
    LarvaeDiscriminatingDoseAssayDTO dto = LarvaeDiscriminatingDoseAssayDTO.lock(super.getClientRequest(), id);
    req.setAttribute("ageRange", LarvaeAgeDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("collection", MosquitoCollectionDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("generation", GenerationDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("identificationMethod", IdentificationMethodDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("testMethod", ResistanceMethodologyDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("units", UnitDTO.allItems(super.getClientSession().getRequest()));
    req.setAttribute("insecticide", InsecticideDTO.getAll(super.getClientSession().getRequest()));
    req.setAttribute("specie", SpecieDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("item", dto);
    req.setAttribute("page_title", "Edit Larvae Discriminating Dose Assay");
    render("editComponent.jsp");
  }
  public void failEdit(java.lang.String id) throws java.io.IOException, javax.servlet.ServletException
  {
    this.view(id);
  }
  public void newInstance() throws java.io.IOException, javax.servlet.ServletException
  {
    com.terraframe.mojo.constants.ClientRequestIF clientRequest = super.getClientRequest();
    LarvaeDiscriminatingDoseAssayDTO dto = new LarvaeDiscriminatingDoseAssayDTO(clientRequest);
    if(req.getParameter("collection_id") != null)
    {
      dto.setCollection(MosquitoCollectionDTO.get(clientRequest, req.getParameter("collection_id")));
    }

    req.setAttribute("ageRange", LarvaeAgeDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("collection", MosquitoCollectionDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("generation", GenerationDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("identificationMethod", IdentificationMethodDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("testMethod", ResistanceMethodologyDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("units", UnitDTO.allItems(super.getClientSession().getRequest()));
    req.setAttribute("insecticide", InsecticideDTO.getAll(super.getClientSession().getRequest()));
    req.setAttribute("specie", SpecieDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("item", dto);
    req.setAttribute("page_title", "Create Larvae Discriminating Dose Assay");
    render("createComponent.jsp");
  }
  public void failNewInstance() throws java.io.IOException, javax.servlet.ServletException
  {
    this.viewAll();
  }
  public void viewAll() throws java.io.IOException, javax.servlet.ServletException
  {
    com.terraframe.mojo.constants.ClientRequestIF clientRequest = super.getClientRequest();
    LarvaeDiscriminatingDoseAssayQueryDTO query = LarvaeDiscriminatingDoseAssayDTO.getAllInstances(clientRequest, null, true, 20, 1);
    req.setAttribute("query", query);
    req.setAttribute("page_title", "View All Larvae Discriminating Dose Assay");
    render("viewAllComponent.jsp");
  }
  public void failViewAll() throws java.io.IOException, javax.servlet.ServletException
  {
    resp.sendError(500);
  }
  public void viewPage(java.lang.String sortAttribute, java.lang.Boolean isAscending, java.lang.Integer pageSize, java.lang.Integer pageNumber) throws java.io.IOException, javax.servlet.ServletException
  {
    com.terraframe.mojo.constants.ClientRequestIF clientRequest = super.getClientRequest();
    LarvaeDiscriminatingDoseAssayQueryDTO query = LarvaeDiscriminatingDoseAssayDTO.getAllInstances(clientRequest, sortAttribute, isAscending, pageSize, pageNumber);
    req.setAttribute("query", query);
    req.setAttribute("page_title", "View All Larvae Discriminating Dose Assay Objects");
    render("viewAllComponent.jsp");
  }
  public void failViewPage(java.lang.String sortAttribute, java.lang.String isAscending, java.lang.String pageSize, java.lang.String pageNumber) throws java.io.IOException, javax.servlet.ServletException
  {
    resp.sendError(500);
  }
  public void update(LarvaeDiscriminatingDoseAssayDTO dto) throws java.io.IOException, javax.servlet.ServletException
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
  public void failUpdate(LarvaeDiscriminatingDoseAssayDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    req.setAttribute("ageRange", LarvaeAgeDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("collection", MosquitoCollectionDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("generation", GenerationDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("identificationMethod", IdentificationMethodDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("testMethod", ResistanceMethodologyDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("units", UnitDTO.allItems(super.getClientSession().getRequest()));
    req.setAttribute("insecticide", InsecticideDTO.getAll(super.getClientSession().getRequest()));
    req.setAttribute("specie", SpecieDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("item", dto);
    req.setAttribute("page_title", "Update Larvae Discriminating Dose Assay");
    render("updateComponent.jsp");
  }
  public void create(LarvaeDiscriminatingDoseAssayDTO dto) throws java.io.IOException, javax.servlet.ServletException
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
  public void failCreate(LarvaeDiscriminatingDoseAssayDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    req.setAttribute("ageRange", LarvaeAgeDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("collection", MosquitoCollectionDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("generation", GenerationDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("identificationMethod", IdentificationMethodDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("testMethod", ResistanceMethodologyDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("units", UnitDTO.allItems(super.getClientSession().getRequest()));
    req.setAttribute("insecticide", InsecticideDTO.getAll(super.getClientSession().getRequest()));
    req.setAttribute("specie", SpecieDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("item", dto);
    req.setAttribute("page_title", "Create Larvae Discriminating Dose Assay");
    render("createComponent.jsp");
  }
  public void delete(LarvaeDiscriminatingDoseAssayDTO dto) throws java.io.IOException, javax.servlet.ServletException
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
  public void failDelete(LarvaeDiscriminatingDoseAssayDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    req.setAttribute("ageRange", LarvaeAgeDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("collection", MosquitoCollectionDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("generation", GenerationDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("identificationMethod", IdentificationMethodDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("testMethod", ResistanceMethodologyDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("units", UnitDTO.allItems(super.getClientSession().getRequest()));
    req.setAttribute("insecticide", InsecticideDTO.getAll(super.getClientSession().getRequest()));
    req.setAttribute("specie", SpecieDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("item", dto);
    req.setAttribute("page_title", "Edit Larvae Discriminating Dose Assay");
    render("editComponent.jsp");
  }

  public void view(java.lang.String id) throws java.io.IOException, javax.servlet.ServletException
  {
    view(LarvaeDiscriminatingDoseAssayDTO.get(this.getClientRequest(), id));
  }

  public void view(LarvaeDiscriminatingDoseAssayDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    req.setAttribute("ageRange", LarvaeAgeDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("collection", MosquitoCollectionDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("generation", GenerationDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("identificationMethod", IdentificationMethodDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("testMethod", ResistanceMethodologyDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("units", UnitDTO.allItems(super.getClientSession().getRequest()));
    req.setAttribute("insecticide", InsecticideDTO.getAll(super.getClientSession().getRequest()));
    req.setAttribute("specie", SpecieDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("item", dto);
    req.setAttribute("page_title", "View Larvae Discriminating Dose Assay");

    render("viewComponent.jsp");
  }

  public void failView(java.lang.String id) throws java.io.IOException, javax.servlet.ServletException
  {
    this.viewAll();
  }

  public void cancel(LarvaeDiscriminatingDoseAssayDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    dto.unlock();
    this.view(dto);
  }

  public void failCancel(LarvaeDiscriminatingDoseAssayDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    resp.sendError(500);
  }
}
