package csu.mrc.ivcc.mdss.entomology;

import csu.mrc.ivcc.mdss.entomology.MorphologicalSpecieGroupControllerBase;

public class MorphologicalSpecieGroupController extends MorphologicalSpecieGroupControllerBase implements com.terraframe.mojo.generation.loader.Reloadable
{
  public static final String JSP_DIR = "WEB-INF/csu/mrc/ivcc/mdss/entomology/MorphologicalSpecieGroup/";
  public static final String LAYOUT = JSP_DIR + "layout.jsp";
  
  private static final long serialVersionUID = 1235073592699L;
  
  public MorphologicalSpecieGroupController(javax.servlet.http.HttpServletRequest req, javax.servlet.http.HttpServletResponse resp, java.lang.Boolean isAsynchronous)
  {
    super(req, resp, isAsynchronous, JSP_DIR, LAYOUT);
  }
  
  public void cancel(csu.mrc.ivcc.mdss.entomology.MorphologicalSpecieGroupDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    dto.unlock();
    this.view(dto.getId());
  }
  public void failCancel(csu.mrc.ivcc.mdss.entomology.MorphologicalSpecieGroupDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    resp.sendError(500);
  }
  public void create(csu.mrc.ivcc.mdss.entomology.MorphologicalSpecieGroupDTO dto) throws java.io.IOException, javax.servlet.ServletException
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
  public void failCreate(csu.mrc.ivcc.mdss.entomology.MorphologicalSpecieGroupDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    req.setAttribute("mdss_ivcc_mrc_csu_entomology_MorphologicalSpecieGroup_collection", csu.mrc.ivcc.mdss.entomology.AbstractMosquitoCollectionDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("mdss_ivcc_mrc_csu_entomology_MorphologicalSpecieGroup_identificationMethod", csu.mrc.ivcc.mdss.mo.IdentificationMethodDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("mdss_ivcc_mrc_csu_entomology_MorphologicalSpecieGroup_specie", csu.mrc.ivcc.mdss.mo.SpecieDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("item", dto);
    req.setAttribute("page_title", "Create MorphologicalSpecieGroupController");
    render("createComponent.jsp");
  }
  public void newInstance() throws java.io.IOException, javax.servlet.ServletException
  {
    com.terraframe.mojo.constants.ClientRequestIF clientRequest = super.getClientRequest();
    csu.mrc.ivcc.mdss.entomology.MorphologicalSpecieGroupDTO dto = new csu.mrc.ivcc.mdss.entomology.MorphologicalSpecieGroupDTO(clientRequest);
    req.setAttribute("mdss_ivcc_mrc_csu_entomology_MorphologicalSpecieGroup_collection", csu.mrc.ivcc.mdss.entomology.AbstractMosquitoCollectionDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("mdss_ivcc_mrc_csu_entomology_MorphologicalSpecieGroup_identificationMethod", csu.mrc.ivcc.mdss.mo.IdentificationMethodDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("mdss_ivcc_mrc_csu_entomology_MorphologicalSpecieGroup_specie", csu.mrc.ivcc.mdss.mo.SpecieDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("item", dto);
    req.setAttribute("page_title", "Create MorphologicalSpecieGroupController");
    render("createComponent.jsp");
  }
  public void failNewInstance() throws java.io.IOException, javax.servlet.ServletException
  {
    this.viewAll();
  }
  public void view(java.lang.String id) throws java.io.IOException, javax.servlet.ServletException
  {
    com.terraframe.mojo.constants.ClientRequestIF clientRequest = super.getClientRequest();
    req.setAttribute("mdss_ivcc_mrc_csu_entomology_MorphologicalSpecieGroup_collection", csu.mrc.ivcc.mdss.entomology.AbstractMosquitoCollectionDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("mdss_ivcc_mrc_csu_entomology_MorphologicalSpecieGroup_identificationMethod", csu.mrc.ivcc.mdss.mo.IdentificationMethodDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("mdss_ivcc_mrc_csu_entomology_MorphologicalSpecieGroup_specie", csu.mrc.ivcc.mdss.mo.SpecieDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("item", csu.mrc.ivcc.mdss.entomology.MorphologicalSpecieGroupDTO.get(clientRequest, id));
    req.setAttribute("page_title", "View MorphologicalSpecieGroupController");
    render("viewComponent.jsp");
  }
  public void failView(java.lang.String id) throws java.io.IOException, javax.servlet.ServletException
  {
    this.viewAll();
  }
  public void viewPage(java.lang.String sortAttribute, java.lang.Boolean isAscending, java.lang.Integer pageSize, java.lang.Integer pageNumber) throws java.io.IOException, javax.servlet.ServletException
  {
    com.terraframe.mojo.constants.ClientRequestIF clientRequest = super.getClientRequest();
    csu.mrc.ivcc.mdss.entomology.MorphologicalSpecieGroupQueryDTO query = csu.mrc.ivcc.mdss.entomology.MorphologicalSpecieGroupDTO.getAllInstances(clientRequest, sortAttribute, isAscending, pageSize, pageNumber);
    req.setAttribute("query", query);
    req.setAttribute("page_title", "View All MorphologicalSpecieGroupController Objects");
    render("viewAllComponent.jsp");
  }
  public void failViewPage(java.lang.String sortAttribute, java.lang.String isAscending, java.lang.String pageSize, java.lang.String pageNumber) throws java.io.IOException, javax.servlet.ServletException
  {
    resp.sendError(500);
  }
  public void update(csu.mrc.ivcc.mdss.entomology.MorphologicalSpecieGroupDTO dto) throws java.io.IOException, javax.servlet.ServletException
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
  public void failUpdate(csu.mrc.ivcc.mdss.entomology.MorphologicalSpecieGroupDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    req.setAttribute("mdss_ivcc_mrc_csu_entomology_MorphologicalSpecieGroup_collection", csu.mrc.ivcc.mdss.entomology.AbstractMosquitoCollectionDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("mdss_ivcc_mrc_csu_entomology_MorphologicalSpecieGroup_identificationMethod", csu.mrc.ivcc.mdss.mo.IdentificationMethodDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("mdss_ivcc_mrc_csu_entomology_MorphologicalSpecieGroup_specie", csu.mrc.ivcc.mdss.mo.SpecieDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("item", dto);
    req.setAttribute("page_title", "Update MorphologicalSpecieGroupController");
    render("updateComponent.jsp");
  }
  public void delete(csu.mrc.ivcc.mdss.entomology.MorphologicalSpecieGroupDTO dto) throws java.io.IOException, javax.servlet.ServletException
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
  public void failDelete(csu.mrc.ivcc.mdss.entomology.MorphologicalSpecieGroupDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    req.setAttribute("mdss_ivcc_mrc_csu_entomology_MorphologicalSpecieGroup_collection", csu.mrc.ivcc.mdss.entomology.AbstractMosquitoCollectionDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("mdss_ivcc_mrc_csu_entomology_MorphologicalSpecieGroup_identificationMethod", csu.mrc.ivcc.mdss.mo.IdentificationMethodDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("mdss_ivcc_mrc_csu_entomology_MorphologicalSpecieGroup_specie", csu.mrc.ivcc.mdss.mo.SpecieDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("item", dto);
    req.setAttribute("page_title", "Edit MorphologicalSpecieGroupController");
    render("editComponent.jsp");
  }
  public void viewAll() throws java.io.IOException, javax.servlet.ServletException
  {
    com.terraframe.mojo.constants.ClientRequestIF clientRequest = super.getClientRequest();
    csu.mrc.ivcc.mdss.entomology.MorphologicalSpecieGroupQueryDTO query = csu.mrc.ivcc.mdss.entomology.MorphologicalSpecieGroupDTO.getAllInstances(clientRequest, null, true, 20, 1);
    req.setAttribute("query", query);
    req.setAttribute("page_title", "View All MorphologicalSpecieGroupController Objects");
    render("viewAllComponent.jsp");
  }
  public void failViewAll() throws java.io.IOException, javax.servlet.ServletException
  {
    resp.sendError(500);
  }
  public void edit(java.lang.String id) throws java.io.IOException, javax.servlet.ServletException
  {
    csu.mrc.ivcc.mdss.entomology.MorphologicalSpecieGroupDTO dto = csu.mrc.ivcc.mdss.entomology.MorphologicalSpecieGroupDTO.lock(super.getClientRequest(), id);
    req.setAttribute("mdss_ivcc_mrc_csu_entomology_MorphologicalSpecieGroup_collection", csu.mrc.ivcc.mdss.entomology.AbstractMosquitoCollectionDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("mdss_ivcc_mrc_csu_entomology_MorphologicalSpecieGroup_identificationMethod", csu.mrc.ivcc.mdss.mo.IdentificationMethodDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("mdss_ivcc_mrc_csu_entomology_MorphologicalSpecieGroup_specie", csu.mrc.ivcc.mdss.mo.SpecieDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("item", dto);
    req.setAttribute("page_title", "Edit MorphologicalSpecieGroupController");
    render("editComponent.jsp");
  }
  public void failEdit(java.lang.String id) throws java.io.IOException, javax.servlet.ServletException
  {
    this.view(id);
  }
}