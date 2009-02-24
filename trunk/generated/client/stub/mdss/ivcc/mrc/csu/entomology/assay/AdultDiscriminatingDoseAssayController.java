package mdss.ivcc.mrc.csu.entomology.assay;

import mdss.ivcc.mrc.csu.entomology.assay.AdultDiscriminatingDoseAssayControllerBase;

public class AdultDiscriminatingDoseAssayController extends AdultDiscriminatingDoseAssayControllerBase implements com.terraframe.mojo.generation.loader.Reloadable
{
  public static final String JSP_DIR = "WEB-INF/mdss/ivcc/mrc/csu/entomology/assay/AdultDiscriminatingDoseAssay/";
  public static final String LAYOUT = JSP_DIR + "layout.jsp";
  
  private static final long serialVersionUID = 1235419628808L;
  
  public AdultDiscriminatingDoseAssayController(javax.servlet.http.HttpServletRequest req, javax.servlet.http.HttpServletResponse resp, java.lang.Boolean isAsynchronous)
  {
    super(req, resp, isAsynchronous, JSP_DIR, LAYOUT);
  }
  
  public void cancel(mdss.ivcc.mrc.csu.entomology.assay.AdultDiscriminatingDoseAssayDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    dto.unlock();
    
    this.view(dto);
  }
  public void failCancel(mdss.ivcc.mrc.csu.entomology.assay.AdultDiscriminatingDoseAssayDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    resp.sendError(500);
  }
  public void viewAll() throws java.io.IOException, javax.servlet.ServletException
  {
    com.terraframe.mojo.constants.ClientRequestIF clientRequest = super.getClientRequest();
    mdss.ivcc.mrc.csu.entomology.assay.AdultDiscriminatingDoseAssayQueryDTO query = mdss.ivcc.mrc.csu.entomology.assay.AdultDiscriminatingDoseAssayDTO.getAllInstances(clientRequest, null, true, 20, 1);
    req.setAttribute("query", query);
    req.setAttribute("page_title", "View All Adult Discriminating Dose Assays");
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
    req.setAttribute("mdss_entomology_assay_AdultAssay_generation", mdss.ivcc.mrc.csu.mo.GenerationDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("mdss_entomology_assay_AdultAssay_insecticide", mdss.ivcc.mrc.csu.mo.InsecticideDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("mdss_entomology_assay_AdultAssay_units", mdss.ivcc.mrc.csu.entomology.assay.UnitDTO.allItems(super.getClientSession().getRequest()));
    req.setAttribute("mdss_entomology_assay_AbstractAssay_collection", mdss.ivcc.mrc.csu.entomology.MosquitoCollectionDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("mdss_entomology_assay_AbstractAssay_identificationMethod", mdss.ivcc.mrc.csu.mo.IdentificationMethodDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("mdss_entomology_assay_AbstractAssay_sex", mdss.ivcc.mrc.csu.entomology.AssaySexDTO.allItems(super.getClientSession().getRequest()));
    req.setAttribute("mdss_entomology_assay_AbstractAssay_specie", mdss.ivcc.mrc.csu.mo.SpecieDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("mdss_entomology_assay_AbstractAssay_testMethod", mdss.ivcc.mrc.csu.mo.ResistanceMethodologyDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());

    req.setAttribute("item", dto);
    req.setAttribute("page_title", "View AdultDiscriminatingDoseAssayController");
    render("viewComponent.jsp");
  }
  
  public void failView(java.lang.String id) throws java.io.IOException, javax.servlet.ServletException
  {
    this.viewAll();
  }
  public void delete(mdss.ivcc.mrc.csu.entomology.assay.AdultDiscriminatingDoseAssayDTO dto) throws java.io.IOException, javax.servlet.ServletException
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
  public void failDelete(mdss.ivcc.mrc.csu.entomology.assay.AdultDiscriminatingDoseAssayDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    req.setAttribute("mdss_entomology_assay_AdultAssay_generation", mdss.ivcc.mrc.csu.mo.GenerationDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("mdss_entomology_assay_AdultAssay_insecticide", mdss.ivcc.mrc.csu.mo.InsecticideDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("mdss_entomology_assay_AdultAssay_units", mdss.ivcc.mrc.csu.entomology.assay.UnitDTO.allItems(super.getClientSession().getRequest()));
    req.setAttribute("mdss_entomology_assay_AbstractAssay_collection", mdss.ivcc.mrc.csu.entomology.MosquitoCollectionDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("mdss_entomology_assay_AbstractAssay_identificationMethod", mdss.ivcc.mrc.csu.mo.IdentificationMethodDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("mdss_entomology_assay_AbstractAssay_sex", mdss.ivcc.mrc.csu.entomology.AssaySexDTO.allItems(super.getClientSession().getRequest()));
    req.setAttribute("mdss_entomology_assay_AbstractAssay_specie", mdss.ivcc.mrc.csu.mo.SpecieDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("mdss_entomology_assay_AbstractAssay_testMethod", mdss.ivcc.mrc.csu.mo.ResistanceMethodologyDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("item", dto);
    req.setAttribute("page_title", "Edit AdultDiscriminatingDoseAssayController");
    render("editComponent.jsp");
  }
  public void create(mdss.ivcc.mrc.csu.entomology.assay.AdultDiscriminatingDoseAssayDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    try
    {
      dto.apply();
      this.view(dto);
    }
    catch(com.terraframe.mojo.ProblemExceptionDTO e)
    {
      this.failCreate(dto);
    }
  }
  public void failCreate(mdss.ivcc.mrc.csu.entomology.assay.AdultDiscriminatingDoseAssayDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    req.setAttribute("mdss_entomology_assay_AdultAssay_generation", mdss.ivcc.mrc.csu.mo.GenerationDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("mdss_entomology_assay_AdultAssay_insecticide", mdss.ivcc.mrc.csu.mo.InsecticideDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("mdss_entomology_assay_AdultAssay_units", mdss.ivcc.mrc.csu.entomology.assay.UnitDTO.allItems(super.getClientSession().getRequest()));
    req.setAttribute("mdss_entomology_assay_AbstractAssay_collection", mdss.ivcc.mrc.csu.entomology.MosquitoCollectionDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("mdss_entomology_assay_AbstractAssay_identificationMethod", mdss.ivcc.mrc.csu.mo.IdentificationMethodDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("mdss_entomology_assay_AbstractAssay_sex", mdss.ivcc.mrc.csu.entomology.AssaySexDTO.allItems(super.getClientSession().getRequest()));
    req.setAttribute("mdss_entomology_assay_AbstractAssay_specie", mdss.ivcc.mrc.csu.mo.SpecieDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("mdss_entomology_assay_AbstractAssay_testMethod", mdss.ivcc.mrc.csu.mo.ResistanceMethodologyDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("item", dto);
    req.setAttribute("page_title", "Create AdultDiscriminatingDoseAssayController");
    render("createComponent.jsp");
  }
  public void viewPage(java.lang.String sortAttribute, java.lang.Boolean isAscending, java.lang.Integer pageSize, java.lang.Integer pageNumber) throws java.io.IOException, javax.servlet.ServletException
  {
    com.terraframe.mojo.constants.ClientRequestIF clientRequest = super.getClientRequest();
    mdss.ivcc.mrc.csu.entomology.assay.AdultDiscriminatingDoseAssayQueryDTO query = mdss.ivcc.mrc.csu.entomology.assay.AdultDiscriminatingDoseAssayDTO.getAllInstances(clientRequest, sortAttribute, isAscending, pageSize, pageNumber);
    req.setAttribute("query", query);
    req.setAttribute("page_title", "View All AdultDiscriminatingDoseAssayController Objects");
    render("viewAllComponent.jsp");
  }
  public void failViewPage(java.lang.String sortAttribute, java.lang.String isAscending, java.lang.String pageSize, java.lang.String pageNumber) throws java.io.IOException, javax.servlet.ServletException
  {
    resp.sendError(500);
  }
  public void newInstance() throws java.io.IOException, javax.servlet.ServletException
  {
    com.terraframe.mojo.constants.ClientRequestIF clientRequest = super.getClientRequest();
    mdss.ivcc.mrc.csu.entomology.assay.AdultDiscriminatingDoseAssayDTO dto = new mdss.ivcc.mrc.csu.entomology.assay.AdultDiscriminatingDoseAssayDTO(clientRequest);
    req.setAttribute("mdss_entomology_assay_AdultAssay_generation", mdss.ivcc.mrc.csu.mo.GenerationDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("mdss_entomology_assay_AdultAssay_insecticide", mdss.ivcc.mrc.csu.mo.InsecticideDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("mdss_entomology_assay_AdultAssay_units", mdss.ivcc.mrc.csu.entomology.assay.UnitDTO.allItems(super.getClientSession().getRequest()));
    req.setAttribute("mdss_entomology_assay_AbstractAssay_collection", mdss.ivcc.mrc.csu.entomology.MosquitoCollectionDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("mdss_entomology_assay_AbstractAssay_identificationMethod", mdss.ivcc.mrc.csu.mo.IdentificationMethodDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("mdss_entomology_assay_AbstractAssay_sex", mdss.ivcc.mrc.csu.entomology.AssaySexDTO.allItems(super.getClientSession().getRequest()));
    req.setAttribute("mdss_entomology_assay_AbstractAssay_specie", mdss.ivcc.mrc.csu.mo.SpecieDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("mdss_entomology_assay_AbstractAssay_testMethod", mdss.ivcc.mrc.csu.mo.ResistanceMethodologyDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("item", dto);
    req.setAttribute("page_title", "Create AdultDiscriminatingDoseAssayController");
    render("createComponent.jsp");
  }
  public void failNewInstance() throws java.io.IOException, javax.servlet.ServletException
  {
    this.viewAll();
  }
  public void update(mdss.ivcc.mrc.csu.entomology.assay.AdultDiscriminatingDoseAssayDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    try
    {
      dto.apply();
      this.view(dto);
    }
    catch(com.terraframe.mojo.ProblemExceptionDTO e)
    {
      this.failUpdate(dto);
    }
  }
  public void failUpdate(mdss.ivcc.mrc.csu.entomology.assay.AdultDiscriminatingDoseAssayDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    req.setAttribute("mdss_entomology_assay_AdultAssay_generation", mdss.ivcc.mrc.csu.mo.GenerationDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("mdss_entomology_assay_AdultAssay_insecticide", mdss.ivcc.mrc.csu.mo.InsecticideDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("mdss_entomology_assay_AdultAssay_units", mdss.ivcc.mrc.csu.entomology.assay.UnitDTO.allItems(super.getClientSession().getRequest()));
    req.setAttribute("mdss_entomology_assay_AbstractAssay_collection", mdss.ivcc.mrc.csu.entomology.MosquitoCollectionDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("mdss_entomology_assay_AbstractAssay_identificationMethod", mdss.ivcc.mrc.csu.mo.IdentificationMethodDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("mdss_entomology_assay_AbstractAssay_sex", mdss.ivcc.mrc.csu.entomology.AssaySexDTO.allItems(super.getClientSession().getRequest()));
    req.setAttribute("mdss_entomology_assay_AbstractAssay_specie", mdss.ivcc.mrc.csu.mo.SpecieDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("mdss_entomology_assay_AbstractAssay_testMethod", mdss.ivcc.mrc.csu.mo.ResistanceMethodologyDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("item", dto);
    req.setAttribute("page_title", "Update AdultDiscriminatingDoseAssayController");
    render("updateComponent.jsp");
  }
  public void edit(java.lang.String id) throws java.io.IOException, javax.servlet.ServletException
  {
    mdss.ivcc.mrc.csu.entomology.assay.AdultDiscriminatingDoseAssayDTO dto = mdss.ivcc.mrc.csu.entomology.assay.AdultDiscriminatingDoseAssayDTO.lock(super.getClientRequest(), id);
    req.setAttribute("mdss_entomology_assay_AdultAssay_generation", mdss.ivcc.mrc.csu.mo.GenerationDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("mdss_entomology_assay_AdultAssay_insecticide", mdss.ivcc.mrc.csu.mo.InsecticideDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("mdss_entomology_assay_AdultAssay_units", mdss.ivcc.mrc.csu.entomology.assay.UnitDTO.allItems(super.getClientSession().getRequest()));
    req.setAttribute("mdss_entomology_assay_AbstractAssay_collection", mdss.ivcc.mrc.csu.entomology.MosquitoCollectionDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("mdss_entomology_assay_AbstractAssay_identificationMethod", mdss.ivcc.mrc.csu.mo.IdentificationMethodDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("mdss_entomology_assay_AbstractAssay_sex", mdss.ivcc.mrc.csu.entomology.AssaySexDTO.allItems(super.getClientSession().getRequest()));
    req.setAttribute("mdss_entomology_assay_AbstractAssay_specie", mdss.ivcc.mrc.csu.mo.SpecieDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("mdss_entomology_assay_AbstractAssay_testMethod", mdss.ivcc.mrc.csu.mo.ResistanceMethodologyDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("item", dto);
    req.setAttribute("page_title", "Edit AdultDiscriminatingDoseAssayController");
    render("editComponent.jsp");
  }
  public void failEdit(java.lang.String id) throws java.io.IOException, javax.servlet.ServletException
  {
    this.view(id);
  }
}
