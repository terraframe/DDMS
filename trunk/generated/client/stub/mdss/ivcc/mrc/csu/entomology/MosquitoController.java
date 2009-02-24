package mdss.ivcc.mrc.csu.entomology;

import mdss.ivcc.mrc.csu.entomology.MosquitoControllerBase;

public class MosquitoController extends MosquitoControllerBase implements com.terraframe.mojo.generation.loader.Reloadable
{
  public static final String JSP_DIR = "WEB-INF/mdss/entomology/Mosquito/";
  public static final String LAYOUT = JSP_DIR + "layout.jsp";
  
  private static final long serialVersionUID = 1235073594943L;
  
  public MosquitoController(javax.servlet.http.HttpServletRequest req, javax.servlet.http.HttpServletResponse resp, java.lang.Boolean isAsynchronous)
  {
    super(req, resp, isAsynchronous, JSP_DIR, LAYOUT);
  }
  
  public void cancel(mdss.ivcc.mrc.csu.entomology.MosquitoDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    dto.unlock();
    this.view(dto.getId());
  }
  public void failCancel(mdss.ivcc.mrc.csu.entomology.MosquitoDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    resp.sendError(500);
  }
  public void viewAll() throws java.io.IOException, javax.servlet.ServletException
  {
    com.terraframe.mojo.constants.ClientRequestIF clientRequest = super.getClientRequest();
    mdss.ivcc.mrc.csu.entomology.MosquitoQueryDTO query = mdss.ivcc.mrc.csu.entomology.MosquitoDTO.getAllInstances(clientRequest, null, true, 20, 1);
    req.setAttribute("query", query);
    req.setAttribute("page_title", "View All MosquitoController Objects");
    render("viewAllComponent.jsp");
  }
  public void failViewAll() throws java.io.IOException, javax.servlet.ServletException
  {
    resp.sendError(500);
  }
  public void viewPage(java.lang.String sortAttribute, java.lang.Boolean isAscending, java.lang.Integer pageSize, java.lang.Integer pageNumber) throws java.io.IOException, javax.servlet.ServletException
  {
    com.terraframe.mojo.constants.ClientRequestIF clientRequest = super.getClientRequest();
    mdss.ivcc.mrc.csu.entomology.MosquitoQueryDTO query = mdss.ivcc.mrc.csu.entomology.MosquitoDTO.getAllInstances(clientRequest, sortAttribute, isAscending, pageSize, pageNumber);
    req.setAttribute("query", query);
    req.setAttribute("page_title", "View All MosquitoController Objects");
    render("viewAllComponent.jsp");
  }
  public void failViewPage(java.lang.String sortAttribute, java.lang.String isAscending, java.lang.String pageSize, java.lang.String pageNumber) throws java.io.IOException, javax.servlet.ServletException
  {
    resp.sendError(500);
  }
  public void edit(java.lang.String id) throws java.io.IOException, javax.servlet.ServletException
  {
    mdss.ivcc.mrc.csu.entomology.MosquitoDTO dto = mdss.ivcc.mrc.csu.entomology.MosquitoDTO.lock(super.getClientRequest(), id);
    req.setAttribute("mdss_entomology_Mosquito_generation", mdss.ivcc.mrc.csu.mo.GenerationDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("mdss_entomology_Mosquito_sex", mdss.ivcc.mrc.csu.entomology.SexDTO.allItems(super.getClientSession().getRequest()));
    req.setAttribute("mdss_entomology_TrueSpecieEntity_collection", mdss.ivcc.mrc.csu.entomology.AbstractMosquitoCollectionDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("mdss_entomology_TrueSpecieEntity_identificationMethod", mdss.ivcc.mrc.csu.mo.IdentificationMethodDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("mdss_entomology_TrueSpecieEntity_specie", mdss.ivcc.mrc.csu.mo.SpecieDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("item", dto);
    req.setAttribute("page_title", "Edit MosquitoController");
    render("editComponent.jsp");
  }
  public void failEdit(java.lang.String id) throws java.io.IOException, javax.servlet.ServletException
  {
    this.view(id);
  }
  public void delete(mdss.ivcc.mrc.csu.entomology.MosquitoDTO dto) throws java.io.IOException, javax.servlet.ServletException
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
  public void failDelete(mdss.ivcc.mrc.csu.entomology.MosquitoDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    req.setAttribute("mdss_entomology_Mosquito_generation", mdss.ivcc.mrc.csu.mo.GenerationDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("mdss_entomology_Mosquito_sex", mdss.ivcc.mrc.csu.entomology.SexDTO.allItems(super.getClientSession().getRequest()));
    req.setAttribute("mdss_entomology_TrueSpecieEntity_collection", mdss.ivcc.mrc.csu.entomology.AbstractMosquitoCollectionDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("mdss_entomology_TrueSpecieEntity_identificationMethod", mdss.ivcc.mrc.csu.mo.IdentificationMethodDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("mdss_entomology_TrueSpecieEntity_specie", mdss.ivcc.mrc.csu.mo.SpecieDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("item", dto);
    req.setAttribute("page_title", "Edit MosquitoController");
    render("editComponent.jsp");
  }
  public void create(mdss.ivcc.mrc.csu.entomology.MosquitoDTO dto) throws java.io.IOException, javax.servlet.ServletException
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
  public void failCreate(mdss.ivcc.mrc.csu.entomology.MosquitoDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    req.setAttribute("mdss_entomology_Mosquito_generation", mdss.ivcc.mrc.csu.mo.GenerationDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("mdss_entomology_Mosquito_sex", mdss.ivcc.mrc.csu.entomology.SexDTO.allItems(super.getClientSession().getRequest()));
    req.setAttribute("mdss_entomology_TrueSpecieEntity_collection", mdss.ivcc.mrc.csu.entomology.AbstractMosquitoCollectionDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("mdss_entomology_TrueSpecieEntity_identificationMethod", mdss.ivcc.mrc.csu.mo.IdentificationMethodDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("mdss_entomology_TrueSpecieEntity_specie", mdss.ivcc.mrc.csu.mo.SpecieDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("item", dto);
    req.setAttribute("page_title", "Create MosquitoController");
    render("createComponent.jsp");
  }
  public void update(mdss.ivcc.mrc.csu.entomology.MosquitoDTO dto) throws java.io.IOException, javax.servlet.ServletException
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
  public void failUpdate(mdss.ivcc.mrc.csu.entomology.MosquitoDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    req.setAttribute("mdss_entomology_Mosquito_generation", mdss.ivcc.mrc.csu.mo.GenerationDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("mdss_entomology_Mosquito_sex", mdss.ivcc.mrc.csu.entomology.SexDTO.allItems(super.getClientSession().getRequest()));
    req.setAttribute("mdss_entomology_TrueSpecieEntity_collection", mdss.ivcc.mrc.csu.entomology.AbstractMosquitoCollectionDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("mdss_entomology_TrueSpecieEntity_identificationMethod", mdss.ivcc.mrc.csu.mo.IdentificationMethodDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("mdss_entomology_TrueSpecieEntity_specie", mdss.ivcc.mrc.csu.mo.SpecieDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("item", dto);
    req.setAttribute("page_title", "Update MosquitoController");
    render("updateComponent.jsp");
  }
  public void newInstance() throws java.io.IOException, javax.servlet.ServletException
  {
    com.terraframe.mojo.constants.ClientRequestIF clientRequest = super.getClientRequest();
    mdss.ivcc.mrc.csu.entomology.MosquitoDTO dto = new mdss.ivcc.mrc.csu.entomology.MosquitoDTO(clientRequest);
    req.setAttribute("mdss_entomology_Mosquito_generation", mdss.ivcc.mrc.csu.mo.GenerationDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("mdss_entomology_Mosquito_sex", mdss.ivcc.mrc.csu.entomology.SexDTO.allItems(super.getClientSession().getRequest()));
    req.setAttribute("mdss_entomology_TrueSpecieEntity_collection", mdss.ivcc.mrc.csu.entomology.AbstractMosquitoCollectionDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("mdss_entomology_TrueSpecieEntity_identificationMethod", mdss.ivcc.mrc.csu.mo.IdentificationMethodDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("mdss_entomology_TrueSpecieEntity_specie", mdss.ivcc.mrc.csu.mo.SpecieDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("item", dto);
    req.setAttribute("page_title", "Create MosquitoController");
    render("createComponent.jsp");
  }
  public void failNewInstance() throws java.io.IOException, javax.servlet.ServletException
  {
    this.viewAll();
  }
  public void view(java.lang.String id) throws java.io.IOException, javax.servlet.ServletException
  {
    com.terraframe.mojo.constants.ClientRequestIF clientRequest = super.getClientRequest();
    req.setAttribute("mdss_entomology_Mosquito_generation", mdss.ivcc.mrc.csu.mo.GenerationDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("mdss_entomology_Mosquito_sex", mdss.ivcc.mrc.csu.entomology.SexDTO.allItems(super.getClientSession().getRequest()));
    req.setAttribute("mdss_entomology_TrueSpecieEntity_collection", mdss.ivcc.mrc.csu.entomology.AbstractMosquitoCollectionDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("mdss_entomology_TrueSpecieEntity_identificationMethod", mdss.ivcc.mrc.csu.mo.IdentificationMethodDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("mdss_entomology_TrueSpecieEntity_specie", mdss.ivcc.mrc.csu.mo.SpecieDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("item", mdss.ivcc.mrc.csu.entomology.MosquitoDTO.get(clientRequest, id));
    req.setAttribute("page_title", "View MosquitoController");
    render("viewComponent.jsp");
  }
  public void failView(java.lang.String id) throws java.io.IOException, javax.servlet.ServletException
  {
    this.viewAll();
  }
}
