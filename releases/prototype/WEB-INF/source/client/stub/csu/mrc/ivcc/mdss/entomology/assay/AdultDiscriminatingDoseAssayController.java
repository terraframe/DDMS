package csu.mrc.ivcc.mdss.entomology.assay;


import com.terraframe.mojo.business.ProblemDTOIF;

import csu.mrc.ivcc.mdss.entomology.assay.AdultDiscriminatingDoseAssayControllerBase;


public class AdultDiscriminatingDoseAssayController extends AdultDiscriminatingDoseAssayControllerBase implements com.terraframe.mojo.generation.loader.Reloadable
{
  public static final String JSP_DIR = "WEB-INF/csu/mrc/ivcc/mdss/entomology/assay/AdultDiscriminatingDoseAssay/";
  public static final String LAYOUT = JSP_DIR + "layout.jsp";
  
  private static final long serialVersionUID = 1235419628808L;
  
  public AdultDiscriminatingDoseAssayController(javax.servlet.http.HttpServletRequest req, javax.servlet.http.HttpServletResponse resp, java.lang.Boolean isAsynchronous)
  {
    super(req, resp, isAsynchronous, JSP_DIR, LAYOUT);
  }
  
  public void cancel(csu.mrc.ivcc.mdss.entomology.assay.AdultDiscriminatingDoseAssayDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    dto.unlock();
    
    this.view(dto);
  }
  public void failCancel(csu.mrc.ivcc.mdss.entomology.assay.AdultDiscriminatingDoseAssayDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    resp.sendError(500);
  }
  public void viewAll() throws java.io.IOException, javax.servlet.ServletException
  {
    com.terraframe.mojo.constants.ClientRequestIF clientRequest = super.getClientRequest();
    csu.mrc.ivcc.mdss.entomology.assay.AdultDiscriminatingDoseAssayQueryDTO query = csu.mrc.ivcc.mdss.entomology.assay.AdultDiscriminatingDoseAssayDTO.getAllInstances(clientRequest, null, true, 20, 1);
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
    req.setAttribute("mdss_ivcc_mrc_csu_entomology_assay_AdultAssay_generation", csu.mrc.ivcc.mdss.mo.GenerationDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("mdss_ivcc_mrc_csu_entomology_assay_AdultAssay_insecticide", csu.mrc.ivcc.mdss.mo.InsecticideDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("mdss_ivcc_mrc_csu_entomology_assay_AdultAssay_units", csu.mrc.ivcc.mdss.entomology.assay.UnitDTO.allItems(super.getClientSession().getRequest()));
    req.setAttribute("mdss_ivcc_mrc_csu_entomology_assay_AbstractAssay_collection", csu.mrc.ivcc.mdss.entomology.MosquitoCollectionDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("mdss_ivcc_mrc_csu_entomology_assay_AbstractAssay_identificationMethod", csu.mrc.ivcc.mdss.mo.IdentificationMethodDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("mdss_ivcc_mrc_csu_entomology_assay_AbstractAssay_sex", csu.mrc.ivcc.mdss.entomology.AssaySexDTO.allItems(super.getClientSession().getRequest()));
    req.setAttribute("mdss_ivcc_mrc_csu_entomology_assay_AbstractAssay_specie", csu.mrc.ivcc.mdss.mo.SpecieDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("mdss_ivcc_mrc_csu_entomology_assay_AbstractAssay_testMethod", csu.mrc.ivcc.mdss.mo.ResistanceMethodologyDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());

    req.setAttribute("item", dto);
    req.setAttribute("page_title", "View AdultDiscriminatingDoseAssay");
    render("viewComponent.jsp");
  }
  
  public void failView(java.lang.String id) throws java.io.IOException, javax.servlet.ServletException
  {
    this.viewAll();
  }
  public void delete(csu.mrc.ivcc.mdss.entomology.assay.AdultDiscriminatingDoseAssayDTO dto) throws java.io.IOException, javax.servlet.ServletException
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
  public void failDelete(csu.mrc.ivcc.mdss.entomology.assay.AdultDiscriminatingDoseAssayDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    req.setAttribute("mdss_ivcc_mrc_csu_entomology_assay_AdultAssay_generation", csu.mrc.ivcc.mdss.mo.GenerationDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("mdss_ivcc_mrc_csu_entomology_assay_AdultAssay_insecticide", csu.mrc.ivcc.mdss.mo.InsecticideDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("mdss_ivcc_mrc_csu_entomology_assay_AdultAssay_units", csu.mrc.ivcc.mdss.entomology.assay.UnitDTO.allItems(super.getClientSession().getRequest()));
    req.setAttribute("mdss_ivcc_mrc_csu_entomology_assay_AbstractAssay_collection", csu.mrc.ivcc.mdss.entomology.MosquitoCollectionDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("mdss_ivcc_mrc_csu_entomology_assay_AbstractAssay_identificationMethod", csu.mrc.ivcc.mdss.mo.IdentificationMethodDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("mdss_ivcc_mrc_csu_entomology_assay_AbstractAssay_sex", csu.mrc.ivcc.mdss.entomology.AssaySexDTO.allItems(super.getClientSession().getRequest()));
    req.setAttribute("mdss_ivcc_mrc_csu_entomology_assay_AbstractAssay_specie", csu.mrc.ivcc.mdss.mo.SpecieDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("mdss_ivcc_mrc_csu_entomology_assay_AbstractAssay_testMethod", csu.mrc.ivcc.mdss.mo.ResistanceMethodologyDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("item", dto);
    req.setAttribute("page_title", "Edit AdultDiscriminatingDoseAssayController");
    render("editComponent.jsp");
  }
  public void create(csu.mrc.ivcc.mdss.entomology.assay.AdultDiscriminatingDoseAssayDTO dto) throws java.io.IOException, javax.servlet.ServletException
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
  public void failCreate(csu.mrc.ivcc.mdss.entomology.assay.AdultDiscriminatingDoseAssayDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    req.setAttribute("mdss_ivcc_mrc_csu_entomology_assay_AdultAssay_generation", csu.mrc.ivcc.mdss.mo.GenerationDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("mdss_ivcc_mrc_csu_entomology_assay_AdultAssay_insecticide", csu.mrc.ivcc.mdss.mo.InsecticideDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("mdss_ivcc_mrc_csu_entomology_assay_AdultAssay_units", csu.mrc.ivcc.mdss.entomology.assay.UnitDTO.allItems(super.getClientSession().getRequest()));
    req.setAttribute("mdss_ivcc_mrc_csu_entomology_assay_AbstractAssay_collection", csu.mrc.ivcc.mdss.entomology.MosquitoCollectionDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("mdss_ivcc_mrc_csu_entomology_assay_AbstractAssay_identificationMethod", csu.mrc.ivcc.mdss.mo.IdentificationMethodDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("mdss_ivcc_mrc_csu_entomology_assay_AbstractAssay_sex", csu.mrc.ivcc.mdss.entomology.AssaySexDTO.allItems(super.getClientSession().getRequest()));
    req.setAttribute("mdss_ivcc_mrc_csu_entomology_assay_AbstractAssay_specie", csu.mrc.ivcc.mdss.mo.SpecieDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("mdss_ivcc_mrc_csu_entomology_assay_AbstractAssay_testMethod", csu.mrc.ivcc.mdss.mo.ResistanceMethodologyDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("item", dto);
    req.setAttribute("page_title", "Create AdultDiscriminatingDoseAssayController");
    render("createComponent.jsp");
  }
  public void viewPage(java.lang.String sortAttribute, java.lang.Boolean isAscending, java.lang.Integer pageSize, java.lang.Integer pageNumber) throws java.io.IOException, javax.servlet.ServletException
  {
    com.terraframe.mojo.constants.ClientRequestIF clientRequest = super.getClientRequest();
    csu.mrc.ivcc.mdss.entomology.assay.AdultDiscriminatingDoseAssayQueryDTO query = csu.mrc.ivcc.mdss.entomology.assay.AdultDiscriminatingDoseAssayDTO.getAllInstances(clientRequest, sortAttribute, isAscending, pageSize, pageNumber);
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
    csu.mrc.ivcc.mdss.entomology.assay.AdultDiscriminatingDoseAssayDTO dto = new csu.mrc.ivcc.mdss.entomology.assay.AdultDiscriminatingDoseAssayDTO(clientRequest);
    req.setAttribute("mdss_ivcc_mrc_csu_entomology_assay_AdultAssay_generation", csu.mrc.ivcc.mdss.mo.GenerationDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("mdss_ivcc_mrc_csu_entomology_assay_AdultAssay_insecticide", csu.mrc.ivcc.mdss.mo.InsecticideDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("mdss_ivcc_mrc_csu_entomology_assay_AdultAssay_units", csu.mrc.ivcc.mdss.entomology.assay.UnitDTO.allItems(super.getClientSession().getRequest()));
    req.setAttribute("mdss_ivcc_mrc_csu_entomology_assay_AbstractAssay_collection", csu.mrc.ivcc.mdss.entomology.MosquitoCollectionDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("mdss_ivcc_mrc_csu_entomology_assay_AbstractAssay_identificationMethod", csu.mrc.ivcc.mdss.mo.IdentificationMethodDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("mdss_ivcc_mrc_csu_entomology_assay_AbstractAssay_sex", csu.mrc.ivcc.mdss.entomology.AssaySexDTO.allItems(super.getClientSession().getRequest()));
    req.setAttribute("mdss_ivcc_mrc_csu_entomology_assay_AbstractAssay_specie", csu.mrc.ivcc.mdss.mo.SpecieDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("mdss_ivcc_mrc_csu_entomology_assay_AbstractAssay_testMethod", csu.mrc.ivcc.mdss.mo.ResistanceMethodologyDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("item", dto);
    req.setAttribute("page_title", "Create AdultDiscriminatingDoseAssayController");
    render("createComponent.jsp");
  }
  public void failNewInstance() throws java.io.IOException, javax.servlet.ServletException
  {
    this.viewAll();
  }
  public void update(csu.mrc.ivcc.mdss.entomology.assay.AdultDiscriminatingDoseAssayDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    try
    {
      dto.apply();
      this.view(dto);
    }
    catch(com.terraframe.mojo.ProblemExceptionDTO e)
    {
     // for(ProblemDTOIF p : e.getProblems())
      //{
    //	  System.out.println(p.localMessage());
      //}
      this.failUpdate(dto);
    }
  }
  public void failUpdate(csu.mrc.ivcc.mdss.entomology.assay.AdultDiscriminatingDoseAssayDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    req.setAttribute("mdss_ivcc_mrc_csu_entomology_assay_AdultAssay_generation", csu.mrc.ivcc.mdss.mo.GenerationDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("mdss_ivcc_mrc_csu_entomology_assay_AdultAssay_insecticide", csu.mrc.ivcc.mdss.mo.InsecticideDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("mdss_ivcc_mrc_csu_entomology_assay_AdultAssay_units", csu.mrc.ivcc.mdss.entomology.assay.UnitDTO.allItems(super.getClientSession().getRequest()));
    req.setAttribute("mdss_ivcc_mrc_csu_entomology_assay_AbstractAssay_collection", csu.mrc.ivcc.mdss.entomology.MosquitoCollectionDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("mdss_ivcc_mrc_csu_entomology_assay_AbstractAssay_identificationMethod", csu.mrc.ivcc.mdss.mo.IdentificationMethodDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("mdss_ivcc_mrc_csu_entomology_assay_AbstractAssay_sex", csu.mrc.ivcc.mdss.entomology.AssaySexDTO.allItems(super.getClientSession().getRequest()));
    req.setAttribute("mdss_ivcc_mrc_csu_entomology_assay_AbstractAssay_specie", csu.mrc.ivcc.mdss.mo.SpecieDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("mdss_ivcc_mrc_csu_entomology_assay_AbstractAssay_testMethod", csu.mrc.ivcc.mdss.mo.ResistanceMethodologyDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("item", dto);
    req.setAttribute("page_title", "Could Not Update Adult Discriminating Dose Assay");
    render("editComponent.jsp");
  }
  public void edit(java.lang.String id) throws java.io.IOException, javax.servlet.ServletException
  {
    csu.mrc.ivcc.mdss.entomology.assay.AdultDiscriminatingDoseAssayDTO dto = csu.mrc.ivcc.mdss.entomology.assay.AdultDiscriminatingDoseAssayDTO.lock(super.getClientRequest(), id);
    req.setAttribute("mdss_ivcc_mrc_csu_entomology_assay_AdultAssay_generation", csu.mrc.ivcc.mdss.mo.GenerationDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("mdss_ivcc_mrc_csu_entomology_assay_AdultAssay_insecticide", csu.mrc.ivcc.mdss.mo.InsecticideDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("mdss_ivcc_mrc_csu_entomology_assay_AdultAssay_units", csu.mrc.ivcc.mdss.entomology.assay.UnitDTO.allItems(super.getClientSession().getRequest()));
    req.setAttribute("mdss_ivcc_mrc_csu_entomology_assay_AbstractAssay_collection", csu.mrc.ivcc.mdss.entomology.MosquitoCollectionDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("mdss_ivcc_mrc_csu_entomology_assay_AbstractAssay_identificationMethod", csu.mrc.ivcc.mdss.mo.IdentificationMethodDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("mdss_ivcc_mrc_csu_entomology_assay_AbstractAssay_sex", csu.mrc.ivcc.mdss.entomology.AssaySexDTO.allItems(super.getClientSession().getRequest()));
    req.setAttribute("mdss_ivcc_mrc_csu_entomology_assay_AbstractAssay_specie", csu.mrc.ivcc.mdss.mo.SpecieDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("mdss_ivcc_mrc_csu_entomology_assay_AbstractAssay_testMethod", csu.mrc.ivcc.mdss.mo.ResistanceMethodologyDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("item", dto);
    req.setAttribute("page_title", "Edit AdultDiscriminatingDoseAssay");
    render("editComponent.jsp");
  }
  public void failEdit(java.lang.String id) throws java.io.IOException, javax.servlet.ServletException
  {
    this.view(id);
  }
}