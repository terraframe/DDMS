package dss.vector.solutions.entomology.assay;

import dss.vector.solutions.entomology.assay.LarvaeDiscriminatingDoseAssayControllerBase;

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
    dss.vector.solutions.entomology.assay.LarvaeDiscriminatingDoseAssayDTO dto = dss.vector.solutions.entomology.assay.LarvaeDiscriminatingDoseAssayDTO.lock(super.getClientRequest(), id);
    req.setAttribute("csu_mrc_ivcc_mdss_entomology_assay_LarvaeDiscriminatingDoseAssay_ageRange", dss.vector.solutions.entomology.assay.LarvaeAgeRangeDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("csu_mrc_ivcc_mdss_entomology_assay_CollectionAssay_collection", dss.vector.solutions.entomology.MosquitoCollectionDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("csu_mrc_ivcc_mdss_entomology_assay_CollectionAssay_generation", dss.vector.solutions.mo.GenerationDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("csu_mrc_ivcc_mdss_entomology_assay_CollectionAssay_identificationMethod", dss.vector.solutions.mo.IdentificationMethodDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("csu_mrc_ivcc_mdss_entomology_assay_CollectionAssay_testMethod", dss.vector.solutions.mo.ResistanceMethodologyDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("csu_mrc_ivcc_mdss_entomology_assay_CollectionAssay_units", dss.vector.solutions.entomology.assay.UnitDTO.allItems(super.getClientSession().getRequest()));
    req.setAttribute("csu_mrc_ivcc_mdss_entomology_assay_AbstractAssay_insecticide", dss.vector.solutions.mo.InsecticideDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("csu_mrc_ivcc_mdss_entomology_assay_AbstractAssay_specie", dss.vector.solutions.mo.SpecieDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("item", dto);
    req.setAttribute("page_title", "Edit LarvaeDiscriminatingDoseAssayController");
    render("editComponent.jsp");
  }
  public void failEdit(java.lang.String id) throws java.io.IOException, javax.servlet.ServletException
  {
    this.view(id);
  }
  public void newInstance() throws java.io.IOException, javax.servlet.ServletException
  {
    com.terraframe.mojo.constants.ClientRequestIF clientRequest = super.getClientRequest();
    dss.vector.solutions.entomology.assay.LarvaeDiscriminatingDoseAssayDTO dto = new dss.vector.solutions.entomology.assay.LarvaeDiscriminatingDoseAssayDTO(clientRequest);
    req.setAttribute("csu_mrc_ivcc_mdss_entomology_assay_LarvaeDiscriminatingDoseAssay_ageRange", dss.vector.solutions.entomology.assay.LarvaeAgeRangeDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("csu_mrc_ivcc_mdss_entomology_assay_CollectionAssay_collection", dss.vector.solutions.entomology.MosquitoCollectionDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("csu_mrc_ivcc_mdss_entomology_assay_CollectionAssay_generation", dss.vector.solutions.mo.GenerationDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("csu_mrc_ivcc_mdss_entomology_assay_CollectionAssay_identificationMethod", dss.vector.solutions.mo.IdentificationMethodDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("csu_mrc_ivcc_mdss_entomology_assay_CollectionAssay_testMethod", dss.vector.solutions.mo.ResistanceMethodologyDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("csu_mrc_ivcc_mdss_entomology_assay_CollectionAssay_units", dss.vector.solutions.entomology.assay.UnitDTO.allItems(super.getClientSession().getRequest()));
    req.setAttribute("csu_mrc_ivcc_mdss_entomology_assay_AbstractAssay_insecticide", dss.vector.solutions.mo.InsecticideDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("csu_mrc_ivcc_mdss_entomology_assay_AbstractAssay_specie", dss.vector.solutions.mo.SpecieDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("item", dto);
    req.setAttribute("page_title", "Create LarvaeDiscriminatingDoseAssayController");
    render("createComponent.jsp");
  }
  public void failNewInstance() throws java.io.IOException, javax.servlet.ServletException
  {
    this.viewAll();
  }
  public void viewAll() throws java.io.IOException, javax.servlet.ServletException
  {
    com.terraframe.mojo.constants.ClientRequestIF clientRequest = super.getClientRequest();
    dss.vector.solutions.entomology.assay.LarvaeDiscriminatingDoseAssayQueryDTO query = dss.vector.solutions.entomology.assay.LarvaeDiscriminatingDoseAssayDTO.getAllInstances(clientRequest, null, true, 20, 1);
    req.setAttribute("query", query);
    req.setAttribute("page_title", "View All LarvaeDiscriminatingDoseAssayController Objects");
    render("viewAllComponent.jsp");
  }
  public void failViewAll() throws java.io.IOException, javax.servlet.ServletException
  {
    resp.sendError(500);
  }
  public void viewPage(java.lang.String sortAttribute, java.lang.Boolean isAscending, java.lang.Integer pageSize, java.lang.Integer pageNumber) throws java.io.IOException, javax.servlet.ServletException
  {
    com.terraframe.mojo.constants.ClientRequestIF clientRequest = super.getClientRequest();
    dss.vector.solutions.entomology.assay.LarvaeDiscriminatingDoseAssayQueryDTO query = dss.vector.solutions.entomology.assay.LarvaeDiscriminatingDoseAssayDTO.getAllInstances(clientRequest, sortAttribute, isAscending, pageSize, pageNumber);
    req.setAttribute("query", query);
    req.setAttribute("page_title", "View All LarvaeDiscriminatingDoseAssayController Objects");
    render("viewAllComponent.jsp");
  }
  public void failViewPage(java.lang.String sortAttribute, java.lang.String isAscending, java.lang.String pageSize, java.lang.String pageNumber) throws java.io.IOException, javax.servlet.ServletException
  {
    resp.sendError(500);
  }
  public void update(dss.vector.solutions.entomology.assay.LarvaeDiscriminatingDoseAssayDTO dto) throws java.io.IOException, javax.servlet.ServletException
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
  public void failUpdate(dss.vector.solutions.entomology.assay.LarvaeDiscriminatingDoseAssayDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    req.setAttribute("csu_mrc_ivcc_mdss_entomology_assay_LarvaeDiscriminatingDoseAssay_ageRange", dss.vector.solutions.entomology.assay.LarvaeAgeRangeDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("csu_mrc_ivcc_mdss_entomology_assay_CollectionAssay_collection", dss.vector.solutions.entomology.MosquitoCollectionDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("csu_mrc_ivcc_mdss_entomology_assay_CollectionAssay_generation", dss.vector.solutions.mo.GenerationDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("csu_mrc_ivcc_mdss_entomology_assay_CollectionAssay_identificationMethod", dss.vector.solutions.mo.IdentificationMethodDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("csu_mrc_ivcc_mdss_entomology_assay_CollectionAssay_testMethod", dss.vector.solutions.mo.ResistanceMethodologyDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("csu_mrc_ivcc_mdss_entomology_assay_CollectionAssay_units", dss.vector.solutions.entomology.assay.UnitDTO.allItems(super.getClientSession().getRequest()));
    req.setAttribute("csu_mrc_ivcc_mdss_entomology_assay_AbstractAssay_insecticide", dss.vector.solutions.mo.InsecticideDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("csu_mrc_ivcc_mdss_entomology_assay_AbstractAssay_specie", dss.vector.solutions.mo.SpecieDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("item", dto);
    req.setAttribute("page_title", "Update LarvaeDiscriminatingDoseAssayController");
    render("updateComponent.jsp");
  }
  public void create(dss.vector.solutions.entomology.assay.LarvaeDiscriminatingDoseAssayDTO dto) throws java.io.IOException, javax.servlet.ServletException
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
  public void failCreate(dss.vector.solutions.entomology.assay.LarvaeDiscriminatingDoseAssayDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    req.setAttribute("csu_mrc_ivcc_mdss_entomology_assay_LarvaeDiscriminatingDoseAssay_ageRange", dss.vector.solutions.entomology.assay.LarvaeAgeRangeDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("csu_mrc_ivcc_mdss_entomology_assay_CollectionAssay_collection", dss.vector.solutions.entomology.MosquitoCollectionDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("csu_mrc_ivcc_mdss_entomology_assay_CollectionAssay_generation", dss.vector.solutions.mo.GenerationDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("csu_mrc_ivcc_mdss_entomology_assay_CollectionAssay_identificationMethod", dss.vector.solutions.mo.IdentificationMethodDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("csu_mrc_ivcc_mdss_entomology_assay_CollectionAssay_testMethod", dss.vector.solutions.mo.ResistanceMethodologyDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("csu_mrc_ivcc_mdss_entomology_assay_CollectionAssay_units", dss.vector.solutions.entomology.assay.UnitDTO.allItems(super.getClientSession().getRequest()));
    req.setAttribute("csu_mrc_ivcc_mdss_entomology_assay_AbstractAssay_insecticide", dss.vector.solutions.mo.InsecticideDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("csu_mrc_ivcc_mdss_entomology_assay_AbstractAssay_specie", dss.vector.solutions.mo.SpecieDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("item", dto);
    req.setAttribute("page_title", "Create LarvaeDiscriminatingDoseAssayController");
    render("createComponent.jsp");
  }
  public void delete(dss.vector.solutions.entomology.assay.LarvaeDiscriminatingDoseAssayDTO dto) throws java.io.IOException, javax.servlet.ServletException
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
  public void failDelete(dss.vector.solutions.entomology.assay.LarvaeDiscriminatingDoseAssayDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    req.setAttribute("csu_mrc_ivcc_mdss_entomology_assay_LarvaeDiscriminatingDoseAssay_ageRange", dss.vector.solutions.entomology.assay.LarvaeAgeRangeDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("csu_mrc_ivcc_mdss_entomology_assay_CollectionAssay_collection", dss.vector.solutions.entomology.MosquitoCollectionDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("csu_mrc_ivcc_mdss_entomology_assay_CollectionAssay_generation", dss.vector.solutions.mo.GenerationDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("csu_mrc_ivcc_mdss_entomology_assay_CollectionAssay_identificationMethod", dss.vector.solutions.mo.IdentificationMethodDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("csu_mrc_ivcc_mdss_entomology_assay_CollectionAssay_testMethod", dss.vector.solutions.mo.ResistanceMethodologyDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("csu_mrc_ivcc_mdss_entomology_assay_CollectionAssay_units", dss.vector.solutions.entomology.assay.UnitDTO.allItems(super.getClientSession().getRequest()));
    req.setAttribute("csu_mrc_ivcc_mdss_entomology_assay_AbstractAssay_insecticide", dss.vector.solutions.mo.InsecticideDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("csu_mrc_ivcc_mdss_entomology_assay_AbstractAssay_specie", dss.vector.solutions.mo.SpecieDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("item", dto);
    req.setAttribute("page_title", "Edit LarvaeDiscriminatingDoseAssayController");
    render("editComponent.jsp");
  }
  public void view(java.lang.String id) throws java.io.IOException, javax.servlet.ServletException
  {
    com.terraframe.mojo.constants.ClientRequestIF clientRequest = super.getClientRequest();
    req.setAttribute("csu_mrc_ivcc_mdss_entomology_assay_LarvaeDiscriminatingDoseAssay_ageRange", dss.vector.solutions.entomology.assay.LarvaeAgeRangeDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("csu_mrc_ivcc_mdss_entomology_assay_CollectionAssay_collection", dss.vector.solutions.entomology.MosquitoCollectionDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("csu_mrc_ivcc_mdss_entomology_assay_CollectionAssay_generation", dss.vector.solutions.mo.GenerationDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("csu_mrc_ivcc_mdss_entomology_assay_CollectionAssay_identificationMethod", dss.vector.solutions.mo.IdentificationMethodDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("csu_mrc_ivcc_mdss_entomology_assay_CollectionAssay_testMethod", dss.vector.solutions.mo.ResistanceMethodologyDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("csu_mrc_ivcc_mdss_entomology_assay_CollectionAssay_units", dss.vector.solutions.entomology.assay.UnitDTO.allItems(super.getClientSession().getRequest()));
    req.setAttribute("csu_mrc_ivcc_mdss_entomology_assay_AbstractAssay_insecticide", dss.vector.solutions.mo.InsecticideDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("csu_mrc_ivcc_mdss_entomology_assay_AbstractAssay_specie", dss.vector.solutions.mo.SpecieDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("item", dss.vector.solutions.entomology.assay.LarvaeDiscriminatingDoseAssayDTO.get(clientRequest, id));
    req.setAttribute("page_title", "View LarvaeDiscriminatingDoseAssayController");
    render("viewComponent.jsp");
  }
  public void failView(java.lang.String id) throws java.io.IOException, javax.servlet.ServletException
  {
    this.viewAll();
  }
  public void cancel(dss.vector.solutions.entomology.assay.LarvaeDiscriminatingDoseAssayDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    dto.unlock();
    this.view(dto.getId());
  }
  public void failCancel(dss.vector.solutions.entomology.assay.LarvaeDiscriminatingDoseAssayDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    resp.sendError(500);
  }
}