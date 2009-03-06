package csu.mrc.ivcc.mdss.entomology.assay.biochemical;

import csu.mrc.ivcc.mdss.entomology.assay.biochemical.AcHETestResultControllerBase;

public class AcHETestResultController extends AcHETestResultControllerBase implements com.terraframe.mojo.generation.loader.Reloadable
{
  public static final String JSP_DIR = "WEB-INF/csu/mrc/ivcc/mdss/entomology/assay/biochemical/AcHETestResult/";
  public static final String LAYOUT = JSP_DIR + "layout.jsp";
  
  private static final long serialVersionUID = 1235751249586L;
  
  public AcHETestResultController(javax.servlet.http.HttpServletRequest req, javax.servlet.http.HttpServletResponse resp, java.lang.Boolean isAsynchronous)
  {
    super(req, resp, isAsynchronous, JSP_DIR, LAYOUT);
  }
  
  public void edit(java.lang.String id) throws java.io.IOException, javax.servlet.ServletException
  {
    csu.mrc.ivcc.mdss.entomology.assay.biochemical.AcHETestResultDTO dto = csu.mrc.ivcc.mdss.entomology.assay.biochemical.AcHETestResultDTO.lock(super.getClientRequest(), id);
    req.setAttribute("mdss_ivcc_mrc_csu_entomology_assay_biochemical_AcHETestResult_testResult", csu.mrc.ivcc.mdss.mo.MolecularAssayResultDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("mdss_ivcc_mrc_csu_entomology_assay_AssayTestResult_mosquito", csu.mrc.ivcc.mdss.entomology.MosquitoDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("item", dto);
    req.setAttribute("page_title", "Edit AcHETestResultController");
    render("editComponent.jsp");
  }
  public void failEdit(java.lang.String id) throws java.io.IOException, javax.servlet.ServletException
  {
    this.view(id);
  }
  public void view(java.lang.String id) throws java.io.IOException, javax.servlet.ServletException
  {
    com.terraframe.mojo.constants.ClientRequestIF clientRequest = super.getClientRequest();
    req.setAttribute("mdss_ivcc_mrc_csu_entomology_assay_biochemical_AcHETestResult_testResult", csu.mrc.ivcc.mdss.mo.MolecularAssayResultDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("mdss_ivcc_mrc_csu_entomology_assay_AssayTestResult_mosquito", csu.mrc.ivcc.mdss.entomology.MosquitoDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("item", csu.mrc.ivcc.mdss.entomology.assay.biochemical.AcHETestResultDTO.get(clientRequest, id));
    req.setAttribute("page_title", "View AcHETestResultController");
    render("viewComponent.jsp");
  }
  public void failView(java.lang.String id) throws java.io.IOException, javax.servlet.ServletException
  {
    this.viewAll();
  }
  public void cancel(csu.mrc.ivcc.mdss.entomology.assay.biochemical.AcHETestResultDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    dto.unlock();
    this.view(dto.getId());
  }
  public void failCancel(csu.mrc.ivcc.mdss.entomology.assay.biochemical.AcHETestResultDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    resp.sendError(500);
  }
  public void newInstance() throws java.io.IOException, javax.servlet.ServletException
  {
    com.terraframe.mojo.constants.ClientRequestIF clientRequest = super.getClientRequest();
    csu.mrc.ivcc.mdss.entomology.assay.biochemical.AcHETestResultDTO dto = new csu.mrc.ivcc.mdss.entomology.assay.biochemical.AcHETestResultDTO(clientRequest);
    req.setAttribute("mdss_ivcc_mrc_csu_entomology_assay_biochemical_AcHETestResult_testResult", csu.mrc.ivcc.mdss.mo.MolecularAssayResultDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("mdss_ivcc_mrc_csu_entomology_assay_AssayTestResult_mosquito", csu.mrc.ivcc.mdss.entomology.MosquitoDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("item", dto);
    req.setAttribute("page_title", "Create AcHETestResultController");
    render("createComponent.jsp");
  }
  public void failNewInstance() throws java.io.IOException, javax.servlet.ServletException
  {
    this.viewAll();
  }
  public void viewAll() throws java.io.IOException, javax.servlet.ServletException
  {
    com.terraframe.mojo.constants.ClientRequestIF clientRequest = super.getClientRequest();
    csu.mrc.ivcc.mdss.entomology.assay.biochemical.AcHETestResultQueryDTO query = csu.mrc.ivcc.mdss.entomology.assay.biochemical.AcHETestResultDTO.getAllInstances(clientRequest, null, true, 20, 1);
    req.setAttribute("query", query);
    req.setAttribute("page_title", "View All AcHETestResultController Objects");
    render("viewAllComponent.jsp");
  }
  public void failViewAll() throws java.io.IOException, javax.servlet.ServletException
  {
    resp.sendError(500);
  }
  public void create(csu.mrc.ivcc.mdss.entomology.assay.biochemical.AcHETestResultDTO dto) throws java.io.IOException, javax.servlet.ServletException
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
  public void failCreate(csu.mrc.ivcc.mdss.entomology.assay.biochemical.AcHETestResultDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    req.setAttribute("mdss_ivcc_mrc_csu_entomology_assay_biochemical_AcHETestResult_testResult", csu.mrc.ivcc.mdss.mo.MolecularAssayResultDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("mdss_ivcc_mrc_csu_entomology_assay_AssayTestResult_mosquito", csu.mrc.ivcc.mdss.entomology.MosquitoDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("item", dto);
    req.setAttribute("page_title", "Create AcHETestResultController");
    render("createComponent.jsp");
  }
  public void update(csu.mrc.ivcc.mdss.entomology.assay.biochemical.AcHETestResultDTO dto) throws java.io.IOException, javax.servlet.ServletException
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
  public void failUpdate(csu.mrc.ivcc.mdss.entomology.assay.biochemical.AcHETestResultDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    req.setAttribute("mdss_ivcc_mrc_csu_entomology_assay_biochemical_AcHETestResult_testResult", csu.mrc.ivcc.mdss.mo.MolecularAssayResultDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("mdss_ivcc_mrc_csu_entomology_assay_AssayTestResult_mosquito", csu.mrc.ivcc.mdss.entomology.MosquitoDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("item", dto);
    req.setAttribute("page_title", "Update AcHETestResultController");
    render("updateComponent.jsp");
  }
  public void viewPage(java.lang.String sortAttribute, java.lang.Boolean isAscending, java.lang.Integer pageSize, java.lang.Integer pageNumber) throws java.io.IOException, javax.servlet.ServletException
  {
    com.terraframe.mojo.constants.ClientRequestIF clientRequest = super.getClientRequest();
    csu.mrc.ivcc.mdss.entomology.assay.biochemical.AcHETestResultQueryDTO query = csu.mrc.ivcc.mdss.entomology.assay.biochemical.AcHETestResultDTO.getAllInstances(clientRequest, sortAttribute, isAscending, pageSize, pageNumber);
    req.setAttribute("query", query);
    req.setAttribute("page_title", "View All AcHETestResultController Objects");
    render("viewAllComponent.jsp");
  }
  public void failViewPage(java.lang.String sortAttribute, java.lang.String isAscending, java.lang.String pageSize, java.lang.String pageNumber) throws java.io.IOException, javax.servlet.ServletException
  {
    resp.sendError(500);
  }
  public void delete(csu.mrc.ivcc.mdss.entomology.assay.biochemical.AcHETestResultDTO dto) throws java.io.IOException, javax.servlet.ServletException
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
  public void failDelete(csu.mrc.ivcc.mdss.entomology.assay.biochemical.AcHETestResultDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    req.setAttribute("mdss_ivcc_mrc_csu_entomology_assay_biochemical_AcHETestResult_testResult", csu.mrc.ivcc.mdss.mo.MolecularAssayResultDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("mdss_ivcc_mrc_csu_entomology_assay_AssayTestResult_mosquito", csu.mrc.ivcc.mdss.entomology.MosquitoDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("item", dto);
    req.setAttribute("page_title", "Edit AcHETestResultController");
    render("editComponent.jsp");
  }
}