package csu.mrc.ivcc.mdss.entomology.assay;

public class LarvaeAgeRangeController extends LarvaeAgeRangeControllerBase implements com.terraframe.mojo.generation.loader.Reloadable
{
  public static final String JSP_DIR = "WEB-INF/csu/mrc/ivcc/mdss/entomology/assay/LarvaeAgeRange/";
  public static final String LAYOUT = JSP_DIR + "layout.jsp";
  
  private static final long serialVersionUID = 1236960163674L;
  
  public LarvaeAgeRangeController(javax.servlet.http.HttpServletRequest req, javax.servlet.http.HttpServletResponse resp, java.lang.Boolean isAsynchronous)
  {
    super(req, resp, isAsynchronous, JSP_DIR, LAYOUT);
  }
  
  public void cancel(csu.mrc.ivcc.mdss.entomology.assay.LarvaeAgeRangeDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    dto.unlock();
    this.view(dto.getId());
  }
  public void failCancel(csu.mrc.ivcc.mdss.entomology.assay.LarvaeAgeRangeDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    resp.sendError(500);
  }
  public void viewAll() throws java.io.IOException, javax.servlet.ServletException
  {
    com.terraframe.mojo.constants.ClientRequestIF clientRequest = super.getClientRequest();
    csu.mrc.ivcc.mdss.entomology.assay.LarvaeAgeRangeQueryDTO query = csu.mrc.ivcc.mdss.entomology.assay.LarvaeAgeRangeDTO.getAllInstances(clientRequest, null, true, 20, 1);
    req.setAttribute("query", query);
    req.setAttribute("page_title", "View All LarvaeAgeRangeController Objects");
    render("viewAllComponent.jsp");
  }
  public void failViewAll() throws java.io.IOException, javax.servlet.ServletException
  {
    resp.sendError(500);
  }
  public void update(csu.mrc.ivcc.mdss.entomology.assay.LarvaeAgeRangeDTO dto) throws java.io.IOException, javax.servlet.ServletException
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
  public void failUpdate(csu.mrc.ivcc.mdss.entomology.assay.LarvaeAgeRangeDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    req.setAttribute("csu_mrc_ivcc_mdss_entomology_assay_LarvaeAgeRange_endPoint", csu.mrc.ivcc.mdss.mo.LarvaeAgeDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("csu_mrc_ivcc_mdss_entomology_assay_LarvaeAgeRange_startPoint", csu.mrc.ivcc.mdss.mo.LarvaeAgeDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("item", dto);
    req.setAttribute("page_title", "Update LarvaeAgeRangeController");
    render("updateComponent.jsp");
  }
  public void view(java.lang.String id) throws java.io.IOException, javax.servlet.ServletException
  {
    com.terraframe.mojo.constants.ClientRequestIF clientRequest = super.getClientRequest();
    req.setAttribute("csu_mrc_ivcc_mdss_entomology_assay_LarvaeAgeRange_endPoint", csu.mrc.ivcc.mdss.mo.LarvaeAgeDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("csu_mrc_ivcc_mdss_entomology_assay_LarvaeAgeRange_startPoint", csu.mrc.ivcc.mdss.mo.LarvaeAgeDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("item", csu.mrc.ivcc.mdss.entomology.assay.LarvaeAgeRangeDTO.get(clientRequest, id));
    req.setAttribute("page_title", "View LarvaeAgeRangeController");
    render("viewComponent.jsp");
  }
  public void failView(java.lang.String id) throws java.io.IOException, javax.servlet.ServletException
  {
    this.viewAll();
  }
  public void create(csu.mrc.ivcc.mdss.entomology.assay.LarvaeAgeRangeDTO dto) throws java.io.IOException, javax.servlet.ServletException
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
  public void failCreate(csu.mrc.ivcc.mdss.entomology.assay.LarvaeAgeRangeDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    req.setAttribute("csu_mrc_ivcc_mdss_entomology_assay_LarvaeAgeRange_endPoint", csu.mrc.ivcc.mdss.mo.LarvaeAgeDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("csu_mrc_ivcc_mdss_entomology_assay_LarvaeAgeRange_startPoint", csu.mrc.ivcc.mdss.mo.LarvaeAgeDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("item", dto);
    req.setAttribute("page_title", "Create LarvaeAgeRangeController");
    render("createComponent.jsp");
  }
  public void delete(csu.mrc.ivcc.mdss.entomology.assay.LarvaeAgeRangeDTO dto) throws java.io.IOException, javax.servlet.ServletException
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
  public void failDelete(csu.mrc.ivcc.mdss.entomology.assay.LarvaeAgeRangeDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    req.setAttribute("csu_mrc_ivcc_mdss_entomology_assay_LarvaeAgeRange_endPoint", csu.mrc.ivcc.mdss.mo.LarvaeAgeDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("csu_mrc_ivcc_mdss_entomology_assay_LarvaeAgeRange_startPoint", csu.mrc.ivcc.mdss.mo.LarvaeAgeDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("item", dto);
    req.setAttribute("page_title", "Edit LarvaeAgeRangeController");
    render("editComponent.jsp");
  }
  public void edit(java.lang.String id) throws java.io.IOException, javax.servlet.ServletException
  {
    csu.mrc.ivcc.mdss.entomology.assay.LarvaeAgeRangeDTO dto = csu.mrc.ivcc.mdss.entomology.assay.LarvaeAgeRangeDTO.lock(super.getClientRequest(), id);
    req.setAttribute("csu_mrc_ivcc_mdss_entomology_assay_LarvaeAgeRange_endPoint", csu.mrc.ivcc.mdss.mo.LarvaeAgeDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("csu_mrc_ivcc_mdss_entomology_assay_LarvaeAgeRange_startPoint", csu.mrc.ivcc.mdss.mo.LarvaeAgeDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("item", dto);
    req.setAttribute("page_title", "Edit LarvaeAgeRangeController");
    render("editComponent.jsp");
  }
  public void failEdit(java.lang.String id) throws java.io.IOException, javax.servlet.ServletException
  {
    this.view(id);
  }
  public void newInstance() throws java.io.IOException, javax.servlet.ServletException
  {
    com.terraframe.mojo.constants.ClientRequestIF clientRequest = super.getClientRequest();
    csu.mrc.ivcc.mdss.entomology.assay.LarvaeAgeRangeDTO dto = new csu.mrc.ivcc.mdss.entomology.assay.LarvaeAgeRangeDTO(clientRequest);
    req.setAttribute("csu_mrc_ivcc_mdss_entomology_assay_LarvaeAgeRange_endPoint", csu.mrc.ivcc.mdss.mo.LarvaeAgeDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("csu_mrc_ivcc_mdss_entomology_assay_LarvaeAgeRange_startPoint", csu.mrc.ivcc.mdss.mo.LarvaeAgeDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("item", dto);
    req.setAttribute("page_title", "Create LarvaeAgeRangeController");
    render("createComponent.jsp");
  }
  public void failNewInstance() throws java.io.IOException, javax.servlet.ServletException
  {
    this.viewAll();
  }
  public void viewPage(java.lang.String sortAttribute, java.lang.Boolean isAscending, java.lang.Integer pageSize, java.lang.Integer pageNumber) throws java.io.IOException, javax.servlet.ServletException
  {
    com.terraframe.mojo.constants.ClientRequestIF clientRequest = super.getClientRequest();
    csu.mrc.ivcc.mdss.entomology.assay.LarvaeAgeRangeQueryDTO query = csu.mrc.ivcc.mdss.entomology.assay.LarvaeAgeRangeDTO.getAllInstances(clientRequest, sortAttribute, isAscending, pageSize, pageNumber);
    req.setAttribute("query", query);
    req.setAttribute("page_title", "View All LarvaeAgeRangeController Objects");
    render("viewAllComponent.jsp");
  }
  public void failViewPage(java.lang.String sortAttribute, java.lang.String isAscending, java.lang.String pageSize, java.lang.String pageNumber) throws java.io.IOException, javax.servlet.ServletException
  {
    resp.sendError(500);
  }
}
