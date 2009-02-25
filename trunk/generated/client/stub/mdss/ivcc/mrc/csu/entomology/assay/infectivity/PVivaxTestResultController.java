package mdss.ivcc.mrc.csu.entomology.assay.infectivity;

public class PVivaxTestResultController extends PVivaxTestResultControllerBase implements com.terraframe.mojo.generation.loader.Reloadable
{
  public static final String JSP_DIR = "WEB-INF/mdss/ivcc/mrc/csu/entomology/assay/infectivity/PVivaxTestResult/";
  public static final String LAYOUT = JSP_DIR + "layout.jsp";
  
  private static final long serialVersionUID = 1235599941421L;
  
  public PVivaxTestResultController(javax.servlet.http.HttpServletRequest req, javax.servlet.http.HttpServletResponse resp, java.lang.Boolean isAsynchronous)
  {
    super(req, resp, isAsynchronous, JSP_DIR, LAYOUT);
  }
  
  public void cancel(mdss.ivcc.mrc.csu.entomology.assay.infectivity.PVivaxTestResultDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    dto.unlock();
    this.view(dto.getId());
  }
  public void failCancel(mdss.ivcc.mrc.csu.entomology.assay.infectivity.PVivaxTestResultDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    resp.sendError(500);
  }
  public void delete(mdss.ivcc.mrc.csu.entomology.assay.infectivity.PVivaxTestResultDTO dto) throws java.io.IOException, javax.servlet.ServletException
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
  public void failDelete(mdss.ivcc.mrc.csu.entomology.assay.infectivity.PVivaxTestResultDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    req.setAttribute("mdss_ivcc_mrc_csu_entomology_assay_infectivity_PVivaxTestResult_mosquito", mdss.ivcc.mrc.csu.entomology.MosquitoDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("item", dto);
    req.setAttribute("page_title", "Edit PVivaxTestResultController");
    render("editComponent.jsp");
  }
  public void create(mdss.ivcc.mrc.csu.entomology.assay.infectivity.PVivaxTestResultDTO dto) throws java.io.IOException, javax.servlet.ServletException
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
  public void failCreate(mdss.ivcc.mrc.csu.entomology.assay.infectivity.PVivaxTestResultDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    req.setAttribute("mdss_ivcc_mrc_csu_entomology_assay_infectivity_PVivaxTestResult_mosquito", mdss.ivcc.mrc.csu.entomology.MosquitoDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("item", dto);
    req.setAttribute("page_title", "Create PVivaxTestResultController");
    render("createComponent.jsp");
  }
  public void update(mdss.ivcc.mrc.csu.entomology.assay.infectivity.PVivaxTestResultDTO dto) throws java.io.IOException, javax.servlet.ServletException
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
  public void failUpdate(mdss.ivcc.mrc.csu.entomology.assay.infectivity.PVivaxTestResultDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    req.setAttribute("mdss_ivcc_mrc_csu_entomology_assay_infectivity_PVivaxTestResult_mosquito", mdss.ivcc.mrc.csu.entomology.MosquitoDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("item", dto);
    req.setAttribute("page_title", "Update PVivaxTestResultController");
    render("updateComponent.jsp");
  }
  public void viewPage(java.lang.String sortAttribute, java.lang.Boolean isAscending, java.lang.Integer pageSize, java.lang.Integer pageNumber) throws java.io.IOException, javax.servlet.ServletException
  {
    com.terraframe.mojo.constants.ClientRequestIF clientRequest = super.getClientRequest();
    mdss.ivcc.mrc.csu.entomology.assay.infectivity.PVivaxTestResultQueryDTO query = mdss.ivcc.mrc.csu.entomology.assay.infectivity.PVivaxTestResultDTO.getAllInstances(clientRequest, sortAttribute, isAscending, pageSize, pageNumber);
    req.setAttribute("query", query);
    req.setAttribute("page_title", "View All PVivaxTestResultController Objects");
    render("viewAllComponent.jsp");
  }
  public void failViewPage(java.lang.String sortAttribute, java.lang.String isAscending, java.lang.String pageSize, java.lang.String pageNumber) throws java.io.IOException, javax.servlet.ServletException
  {
    resp.sendError(500);
  }
  public void newInstance() throws java.io.IOException, javax.servlet.ServletException
  {
    com.terraframe.mojo.constants.ClientRequestIF clientRequest = super.getClientRequest();
    mdss.ivcc.mrc.csu.entomology.assay.infectivity.PVivaxTestResultDTO dto = new mdss.ivcc.mrc.csu.entomology.assay.infectivity.PVivaxTestResultDTO(clientRequest);
    req.setAttribute("mdss_ivcc_mrc_csu_entomology_assay_infectivity_PVivaxTestResult_mosquito", mdss.ivcc.mrc.csu.entomology.MosquitoDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("item", dto);
    req.setAttribute("page_title", "Create PVivaxTestResultController");
    render("createComponent.jsp");
  }
  public void failNewInstance() throws java.io.IOException, javax.servlet.ServletException
  {
    this.viewAll();
  }
  public void viewAll() throws java.io.IOException, javax.servlet.ServletException
  {
    com.terraframe.mojo.constants.ClientRequestIF clientRequest = super.getClientRequest();
    mdss.ivcc.mrc.csu.entomology.assay.infectivity.PVivaxTestResultQueryDTO query = mdss.ivcc.mrc.csu.entomology.assay.infectivity.PVivaxTestResultDTO.getAllInstances(clientRequest, null, true, 20, 1);
    req.setAttribute("query", query);
    req.setAttribute("page_title", "View All PVivaxTestResultController Objects");
    render("viewAllComponent.jsp");
  }
  public void failViewAll() throws java.io.IOException, javax.servlet.ServletException
  {
    resp.sendError(500);
  }
  public void view(java.lang.String id) throws java.io.IOException, javax.servlet.ServletException
  {
    com.terraframe.mojo.constants.ClientRequestIF clientRequest = super.getClientRequest();
    req.setAttribute("mdss_ivcc_mrc_csu_entomology_assay_infectivity_PVivaxTestResult_mosquito", mdss.ivcc.mrc.csu.entomology.MosquitoDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("item", mdss.ivcc.mrc.csu.entomology.assay.infectivity.PVivaxTestResultDTO.get(clientRequest, id));
    req.setAttribute("page_title", "View PVivaxTestResultController");
    render("viewComponent.jsp");
  }
  public void failView(java.lang.String id) throws java.io.IOException, javax.servlet.ServletException
  {
    this.viewAll();
  }
  public void edit(java.lang.String id) throws java.io.IOException, javax.servlet.ServletException
  {
    mdss.ivcc.mrc.csu.entomology.assay.infectivity.PVivaxTestResultDTO dto = mdss.ivcc.mrc.csu.entomology.assay.infectivity.PVivaxTestResultDTO.lock(super.getClientRequest(), id);
    req.setAttribute("mdss_ivcc_mrc_csu_entomology_assay_infectivity_PVivaxTestResult_mosquito", mdss.ivcc.mrc.csu.entomology.MosquitoDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("item", dto);
    req.setAttribute("page_title", "Edit PVivaxTestResultController");
    render("editComponent.jsp");
  }
  public void failEdit(java.lang.String id) throws java.io.IOException, javax.servlet.ServletException
  {
    this.view(id);
  }
}
