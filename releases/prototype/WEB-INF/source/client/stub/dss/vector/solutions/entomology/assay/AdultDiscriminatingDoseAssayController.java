package dss.vector.solutions.entomology.assay;

import com.terraframe.mojo.ProblemExceptionDTO;

import dss.vector.solutions.entomology.MosquitoCollectionDTO;
import dss.vector.solutions.util.ErrorUtility;




public class AdultDiscriminatingDoseAssayController extends AdultDiscriminatingDoseAssayControllerBase implements com.terraframe.mojo.generation.loader.Reloadable
{
  public static final String JSP_DIR = "WEB-INF/dss/vector/solutions/entomology/assay/AdultDiscriminatingDoseAssay/";
  public static final String LAYOUT = JSP_DIR + "layout.jsp";

  private static final long serialVersionUID = 1235419628808L;

  public AdultDiscriminatingDoseAssayController(javax.servlet.http.HttpServletRequest req, javax.servlet.http.HttpServletResponse resp, java.lang.Boolean isAsynchronous)
  {
    super(req, resp, isAsynchronous, JSP_DIR, LAYOUT);
  }

  public void cancel(dss.vector.solutions.entomology.assay.AdultDiscriminatingDoseAssayDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    dto.unlock();

    this.view(dto);
  }
  public void failCancel(dss.vector.solutions.entomology.assay.AdultDiscriminatingDoseAssayDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    resp.sendError(500);
  }
  public void viewAll() throws java.io.IOException, javax.servlet.ServletException
  {
    com.terraframe.mojo.constants.ClientRequestIF clientRequest = super.getClientRequest();
    dss.vector.solutions.entomology.assay.AdultDiscriminatingDoseAssayQueryDTO query = dss.vector.solutions.entomology.assay.AdultDiscriminatingDoseAssayDTO.getAllInstances(clientRequest, null, true, 20, 1);
    req.setAttribute("query", query);

    render("viewAllComponent.jsp");
  }
  public void failViewAll() throws java.io.IOException, javax.servlet.ServletException
  {
    resp.sendError(500);
  }

  public void view(java.lang.String id) throws java.io.IOException, javax.servlet.ServletException
  {
    com.terraframe.mojo.constants.ClientRequestIF clientRequest = super.getClientRequest();

    this.view(AdultDiscriminatingDoseAssayDTO.get(clientRequest, id));
  }

  public void view(AdultDiscriminatingDoseAssayDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    //if this method is being accessed from create or edit, redirect so the url will be correct and refresh will
    //not create a new object
    if (! req.getRequestURI().contains(".view.mojo"))
    {
      String path = req.getRequestURL().toString();
      resp.sendRedirect(path.replaceFirst("\\.[a-zA-Z]+\\.mojo", ".view.mojo") + "?id="+dto.getId());
      return;
    }

    req.setAttribute("generation", dss.vector.solutions.mo.GenerationDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("insecticide", dss.vector.solutions.general.InsecticideDTO.getAll(super.getClientSession().getRequest()));
    req.setAttribute("units", dss.vector.solutions.entomology.assay.UnitDTO.allItems(super.getClientSession().getRequest()));
    req.setAttribute("collection", dss.vector.solutions.entomology.MosquitoCollectionDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("identificationMethod", dss.vector.solutions.mo.IdentificationMethodDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("sex", dss.vector.solutions.entomology.AssaySexDTO.allItems(super.getClientSession().getRequest()));
    req.setAttribute("specie", dss.vector.solutions.mo.SpecieDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("testMethod", dss.vector.solutions.mo.ResistanceMethodologyDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());

    req.setAttribute("item", dto);

    render("viewComponent.jsp");
  }

  public void failView(java.lang.String id) throws java.io.IOException, javax.servlet.ServletException
  {
    this.viewAll();
  }
  public void delete(dss.vector.solutions.entomology.assay.AdultDiscriminatingDoseAssayDTO dto) throws java.io.IOException, javax.servlet.ServletException
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
  public void failDelete(dss.vector.solutions.entomology.assay.AdultDiscriminatingDoseAssayDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    req.setAttribute("generation", dss.vector.solutions.mo.GenerationDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("insecticide", dss.vector.solutions.general.InsecticideDTO.getAll(super.getClientSession().getRequest()));
    req.setAttribute("units", dss.vector.solutions.entomology.assay.UnitDTO.allItems(super.getClientSession().getRequest()));
    req.setAttribute("collection", dss.vector.solutions.entomology.MosquitoCollectionDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("identificationMethod", dss.vector.solutions.mo.IdentificationMethodDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("sex", dss.vector.solutions.entomology.AssaySexDTO.allItems(super.getClientSession().getRequest()));
    req.setAttribute("specie", dss.vector.solutions.mo.SpecieDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("testMethod", dss.vector.solutions.mo.ResistanceMethodologyDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("item", dto);

    render("editComponent.jsp");
  }
  public void create(dss.vector.solutions.entomology.assay.AdultDiscriminatingDoseAssayDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    try
    {
      dto.apply();
      this.view(dto);
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
  public void failCreate(dss.vector.solutions.entomology.assay.AdultDiscriminatingDoseAssayDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    req.setAttribute("generation", dss.vector.solutions.mo.GenerationDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("insecticide", dss.vector.solutions.general.InsecticideDTO.getAll(super.getClientSession().getRequest()));
    req.setAttribute("units", dss.vector.solutions.entomology.assay.UnitDTO.allItems(super.getClientSession().getRequest()));
    req.setAttribute("collection", dss.vector.solutions.entomology.MosquitoCollectionDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("identificationMethod", dss.vector.solutions.mo.IdentificationMethodDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("sex", dss.vector.solutions.entomology.AssaySexDTO.allItems(super.getClientSession().getRequest()));
    req.setAttribute("specie", dss.vector.solutions.mo.SpecieDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("testMethod", dss.vector.solutions.mo.ResistanceMethodologyDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("item", dto);

    render("createComponent.jsp");
  }
  public void viewPage(java.lang.String sortAttribute, java.lang.Boolean isAscending, java.lang.Integer pageSize, java.lang.Integer pageNumber) throws java.io.IOException, javax.servlet.ServletException
  {
    com.terraframe.mojo.constants.ClientRequestIF clientRequest = super.getClientRequest();
    dss.vector.solutions.entomology.assay.AdultDiscriminatingDoseAssayQueryDTO query = dss.vector.solutions.entomology.assay.AdultDiscriminatingDoseAssayDTO.getAllInstances(clientRequest, sortAttribute, isAscending, pageSize, pageNumber);
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
    dss.vector.solutions.entomology.assay.AdultDiscriminatingDoseAssayDTO dto = new dss.vector.solutions.entomology.assay.AdultDiscriminatingDoseAssayDTO(clientRequest);
    if(req.getParameter("collection_id") != null)
    {
      dto.setCollection(MosquitoCollectionDTO.get(clientRequest, req.getParameter("collection_id")));
    }

    req.setAttribute("generation", dss.vector.solutions.mo.GenerationDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("insecticide", dss.vector.solutions.general.InsecticideDTO.getAll(super.getClientSession().getRequest()));
    req.setAttribute("units", dss.vector.solutions.entomology.assay.UnitDTO.allItems(super.getClientSession().getRequest()));
    req.setAttribute("collection", dss.vector.solutions.entomology.MosquitoCollectionDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("identificationMethod", dss.vector.solutions.mo.IdentificationMethodDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("sex", dss.vector.solutions.entomology.AssaySexDTO.allItems(super.getClientSession().getRequest()));
    req.setAttribute("specie", dss.vector.solutions.mo.SpecieDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("testMethod", dss.vector.solutions.mo.ResistanceMethodologyDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("item", dto);

    render("createComponent.jsp");
  }
  public void failNewInstance() throws java.io.IOException, javax.servlet.ServletException
  {
    this.viewAll();
  }
  public void update(dss.vector.solutions.entomology.assay.AdultDiscriminatingDoseAssayDTO dto) throws java.io.IOException, javax.servlet.ServletException
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
  public void failUpdate(dss.vector.solutions.entomology.assay.AdultDiscriminatingDoseAssayDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    req.setAttribute("generation", dss.vector.solutions.mo.GenerationDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("insecticide", dss.vector.solutions.general.InsecticideDTO.getAll(super.getClientSession().getRequest()));
    req.setAttribute("units", dss.vector.solutions.entomology.assay.UnitDTO.allItems(super.getClientSession().getRequest()));
    req.setAttribute("collection", dss.vector.solutions.entomology.MosquitoCollectionDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("identificationMethod", dss.vector.solutions.mo.IdentificationMethodDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("sex", dss.vector.solutions.entomology.AssaySexDTO.allItems(super.getClientSession().getRequest()));
    req.setAttribute("specie", dss.vector.solutions.mo.SpecieDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("testMethod", dss.vector.solutions.mo.ResistanceMethodologyDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("item", dto);

    render("editComponent.jsp");
  }

  public void edit(java.lang.String id) throws java.io.IOException, javax.servlet.ServletException
  {
    dss.vector.solutions.entomology.assay.AdultDiscriminatingDoseAssayDTO dto = dss.vector.solutions.entomology.assay.AdultDiscriminatingDoseAssayDTO.lock(super.getClientRequest(), id);
    req.setAttribute("generation", dss.vector.solutions.mo.GenerationDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("insecticide", dss.vector.solutions.general.InsecticideDTO.getAll(super.getClientSession().getRequest()));
    req.setAttribute("units", dss.vector.solutions.entomology.assay.UnitDTO.allItems(super.getClientSession().getRequest()));
    req.setAttribute("collection", dss.vector.solutions.entomology.MosquitoCollectionDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("identificationMethod", dss.vector.solutions.mo.IdentificationMethodDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("sex", dss.vector.solutions.entomology.AssaySexDTO.allItems(super.getClientSession().getRequest()));
    req.setAttribute("specie", dss.vector.solutions.mo.SpecieDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("testMethod", dss.vector.solutions.mo.ResistanceMethodologyDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("item", dto);

    render("editComponent.jsp");
  }

  public void failEdit(java.lang.String id) throws java.io.IOException, javax.servlet.ServletException
  {
    this.view(id);
  }
}
