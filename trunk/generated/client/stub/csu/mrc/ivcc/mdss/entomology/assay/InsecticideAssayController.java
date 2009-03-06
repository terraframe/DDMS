package csu.mrc.ivcc.mdss.entomology.assay;

public class InsecticideAssayController extends InsecticideAssayControllerBase implements com.terraframe.mojo.generation.loader.Reloadable
{
  public static final String JSP_DIR = "WEB-INF/csu/mrc/ivcc/mdss/entomology/assay/InsecticideAssay/";
  public static final String LAYOUT = JSP_DIR + "layout.jsp";
  
  private static final long serialVersionUID = 1236363369663L;
  
  public InsecticideAssayController(javax.servlet.http.HttpServletRequest req, javax.servlet.http.HttpServletResponse resp, java.lang.Boolean isAsynchronous)
  {
    super(req, resp, isAsynchronous, JSP_DIR, LAYOUT);
  }
  
  public void view(java.lang.String id) throws java.io.IOException, javax.servlet.ServletException
  {
    com.terraframe.mojo.constants.ClientRequestIF clientRequest = super.getClientRequest();
    req.setAttribute("csu_mrc_ivcc_mdss_entomology_assay_InsecticideAssay_insecticide", csu.mrc.ivcc.mdss.mo.InsecticideDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("csu_mrc_ivcc_mdss_entomology_assay_InsecticideAssay_units", csu.mrc.ivcc.mdss.entomology.assay.UnitDTO.allItems(super.getClientSession().getRequest()));
    req.setAttribute("csu_mrc_ivcc_mdss_entomology_assay_AdultAssay_generation", csu.mrc.ivcc.mdss.mo.GenerationDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("csu_mrc_ivcc_mdss_entomology_assay_AbstractAssay_identificationMethod", csu.mrc.ivcc.mdss.mo.IdentificationMethodDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("csu_mrc_ivcc_mdss_entomology_assay_AbstractAssay_sex", csu.mrc.ivcc.mdss.entomology.AssaySexDTO.allItems(super.getClientSession().getRequest()));
    req.setAttribute("csu_mrc_ivcc_mdss_entomology_assay_AbstractAssay_specie", csu.mrc.ivcc.mdss.mo.SpecieDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("csu_mrc_ivcc_mdss_entomology_assay_AbstractAssay_testMethod", csu.mrc.ivcc.mdss.mo.ResistanceMethodologyDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("item", csu.mrc.ivcc.mdss.entomology.assay.InsecticideAssayDTO.get(clientRequest, id));
    req.setAttribute("page_title", "View InsecticideAssayController");
    render("viewComponent.jsp");
  }
  public void failView(java.lang.String id) throws java.io.IOException, javax.servlet.ServletException
  {
    this.viewAll();
  }
  public void delete(csu.mrc.ivcc.mdss.entomology.assay.InsecticideAssayDTO dto) throws java.io.IOException, javax.servlet.ServletException
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
  public void failDelete(csu.mrc.ivcc.mdss.entomology.assay.InsecticideAssayDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    req.setAttribute("csu_mrc_ivcc_mdss_entomology_assay_InsecticideAssay_insecticide", csu.mrc.ivcc.mdss.mo.InsecticideDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("csu_mrc_ivcc_mdss_entomology_assay_InsecticideAssay_units", csu.mrc.ivcc.mdss.entomology.assay.UnitDTO.allItems(super.getClientSession().getRequest()));
    req.setAttribute("csu_mrc_ivcc_mdss_entomology_assay_AdultAssay_generation", csu.mrc.ivcc.mdss.mo.GenerationDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("csu_mrc_ivcc_mdss_entomology_assay_AbstractAssay_identificationMethod", csu.mrc.ivcc.mdss.mo.IdentificationMethodDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("csu_mrc_ivcc_mdss_entomology_assay_AbstractAssay_sex", csu.mrc.ivcc.mdss.entomology.AssaySexDTO.allItems(super.getClientSession().getRequest()));
    req.setAttribute("csu_mrc_ivcc_mdss_entomology_assay_AbstractAssay_specie", csu.mrc.ivcc.mdss.mo.SpecieDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("csu_mrc_ivcc_mdss_entomology_assay_AbstractAssay_testMethod", csu.mrc.ivcc.mdss.mo.ResistanceMethodologyDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("item", dto);
    req.setAttribute("page_title", "Edit InsecticideAssayController");
    render("editComponent.jsp");
  }
  public void edit(java.lang.String id) throws java.io.IOException, javax.servlet.ServletException
  {
    csu.mrc.ivcc.mdss.entomology.assay.InsecticideAssayDTO dto = csu.mrc.ivcc.mdss.entomology.assay.InsecticideAssayDTO.lock(super.getClientRequest(), id);
    req.setAttribute("csu_mrc_ivcc_mdss_entomology_assay_InsecticideAssay_insecticide", csu.mrc.ivcc.mdss.mo.InsecticideDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("csu_mrc_ivcc_mdss_entomology_assay_InsecticideAssay_units", csu.mrc.ivcc.mdss.entomology.assay.UnitDTO.allItems(super.getClientSession().getRequest()));
    req.setAttribute("csu_mrc_ivcc_mdss_entomology_assay_AdultAssay_generation", csu.mrc.ivcc.mdss.mo.GenerationDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("csu_mrc_ivcc_mdss_entomology_assay_AbstractAssay_identificationMethod", csu.mrc.ivcc.mdss.mo.IdentificationMethodDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("csu_mrc_ivcc_mdss_entomology_assay_AbstractAssay_sex", csu.mrc.ivcc.mdss.entomology.AssaySexDTO.allItems(super.getClientSession().getRequest()));
    req.setAttribute("csu_mrc_ivcc_mdss_entomology_assay_AbstractAssay_specie", csu.mrc.ivcc.mdss.mo.SpecieDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("csu_mrc_ivcc_mdss_entomology_assay_AbstractAssay_testMethod", csu.mrc.ivcc.mdss.mo.ResistanceMethodologyDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("item", dto);
    req.setAttribute("page_title", "Edit InsecticideAssayController");
    render("editComponent.jsp");
  }
  public void failEdit(java.lang.String id) throws java.io.IOException, javax.servlet.ServletException
  {
    this.view(id);
  }
  public void update(csu.mrc.ivcc.mdss.entomology.assay.InsecticideAssayDTO dto) throws java.io.IOException, javax.servlet.ServletException
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
  public void failUpdate(csu.mrc.ivcc.mdss.entomology.assay.InsecticideAssayDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    req.setAttribute("csu_mrc_ivcc_mdss_entomology_assay_InsecticideAssay_insecticide", csu.mrc.ivcc.mdss.mo.InsecticideDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("csu_mrc_ivcc_mdss_entomology_assay_InsecticideAssay_units", csu.mrc.ivcc.mdss.entomology.assay.UnitDTO.allItems(super.getClientSession().getRequest()));
    req.setAttribute("csu_mrc_ivcc_mdss_entomology_assay_AdultAssay_generation", csu.mrc.ivcc.mdss.mo.GenerationDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("csu_mrc_ivcc_mdss_entomology_assay_AbstractAssay_identificationMethod", csu.mrc.ivcc.mdss.mo.IdentificationMethodDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("csu_mrc_ivcc_mdss_entomology_assay_AbstractAssay_sex", csu.mrc.ivcc.mdss.entomology.AssaySexDTO.allItems(super.getClientSession().getRequest()));
    req.setAttribute("csu_mrc_ivcc_mdss_entomology_assay_AbstractAssay_specie", csu.mrc.ivcc.mdss.mo.SpecieDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("csu_mrc_ivcc_mdss_entomology_assay_AbstractAssay_testMethod", csu.mrc.ivcc.mdss.mo.ResistanceMethodologyDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("item", dto);
    req.setAttribute("page_title", "Update InsecticideAssayController");
    render("updateComponent.jsp");
  }
  public void create(csu.mrc.ivcc.mdss.entomology.assay.InsecticideAssayDTO dto) throws java.io.IOException, javax.servlet.ServletException
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
  public void failCreate(csu.mrc.ivcc.mdss.entomology.assay.InsecticideAssayDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    req.setAttribute("csu_mrc_ivcc_mdss_entomology_assay_InsecticideAssay_insecticide", csu.mrc.ivcc.mdss.mo.InsecticideDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("csu_mrc_ivcc_mdss_entomology_assay_InsecticideAssay_units", csu.mrc.ivcc.mdss.entomology.assay.UnitDTO.allItems(super.getClientSession().getRequest()));
    req.setAttribute("csu_mrc_ivcc_mdss_entomology_assay_AdultAssay_generation", csu.mrc.ivcc.mdss.mo.GenerationDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("csu_mrc_ivcc_mdss_entomology_assay_AbstractAssay_identificationMethod", csu.mrc.ivcc.mdss.mo.IdentificationMethodDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("csu_mrc_ivcc_mdss_entomology_assay_AbstractAssay_sex", csu.mrc.ivcc.mdss.entomology.AssaySexDTO.allItems(super.getClientSession().getRequest()));
    req.setAttribute("csu_mrc_ivcc_mdss_entomology_assay_AbstractAssay_specie", csu.mrc.ivcc.mdss.mo.SpecieDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("csu_mrc_ivcc_mdss_entomology_assay_AbstractAssay_testMethod", csu.mrc.ivcc.mdss.mo.ResistanceMethodologyDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("item", dto);
    req.setAttribute("page_title", "Create InsecticideAssayController");
    render("createComponent.jsp");
  }
  public void viewAll() throws java.io.IOException, javax.servlet.ServletException
  {
    com.terraframe.mojo.constants.ClientRequestIF clientRequest = super.getClientRequest();
    csu.mrc.ivcc.mdss.entomology.assay.InsecticideAssayQueryDTO query = csu.mrc.ivcc.mdss.entomology.assay.InsecticideAssayDTO.getAllInstances(clientRequest, null, true, 20, 1);
    req.setAttribute("query", query);
    req.setAttribute("page_title", "View All InsecticideAssayController Objects");
    render("viewAllComponent.jsp");
  }
  public void failViewAll() throws java.io.IOException, javax.servlet.ServletException
  {
    resp.sendError(500);
  }
  public void viewPage(java.lang.String sortAttribute, java.lang.Boolean isAscending, java.lang.Integer pageSize, java.lang.Integer pageNumber) throws java.io.IOException, javax.servlet.ServletException
  {
    com.terraframe.mojo.constants.ClientRequestIF clientRequest = super.getClientRequest();
    csu.mrc.ivcc.mdss.entomology.assay.InsecticideAssayQueryDTO query = csu.mrc.ivcc.mdss.entomology.assay.InsecticideAssayDTO.getAllInstances(clientRequest, sortAttribute, isAscending, pageSize, pageNumber);
    req.setAttribute("query", query);
    req.setAttribute("page_title", "View All InsecticideAssayController Objects");
    render("viewAllComponent.jsp");
  }
  public void failViewPage(java.lang.String sortAttribute, java.lang.String isAscending, java.lang.String pageSize, java.lang.String pageNumber) throws java.io.IOException, javax.servlet.ServletException
  {
    resp.sendError(500);
  }
  public void cancel(csu.mrc.ivcc.mdss.entomology.assay.InsecticideAssayDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    dto.unlock();
    this.view(dto.getId());
  }
  public void failCancel(csu.mrc.ivcc.mdss.entomology.assay.InsecticideAssayDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    resp.sendError(500);
  }
}
