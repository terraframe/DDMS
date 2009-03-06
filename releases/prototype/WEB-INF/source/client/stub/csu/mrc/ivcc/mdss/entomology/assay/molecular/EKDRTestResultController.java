package csu.mrc.ivcc.mdss.entomology.assay.molecular;

import csu.mrc.ivcc.mdss.entomology.assay.molecular.EKDRTestResultControllerBase;

public class EKDRTestResultController extends EKDRTestResultControllerBase implements com.terraframe.mojo.generation.loader.Reloadable
{
  public static final String JSP_DIR = "WEB-INF/csu/mrc/ivcc/mdss/entomology/assay/molecular/EKDRTestResult/";
  public static final String LAYOUT = JSP_DIR + "layout.jsp";
  
  private static final long serialVersionUID = 1235751253858L;
  
  public EKDRTestResultController(javax.servlet.http.HttpServletRequest req, javax.servlet.http.HttpServletResponse resp, java.lang.Boolean isAsynchronous)
  {
    super(req, resp, isAsynchronous, JSP_DIR, LAYOUT);
  }
  
  public void viewAll() throws java.io.IOException, javax.servlet.ServletException
  {
    com.terraframe.mojo.constants.ClientRequestIF clientRequest = super.getClientRequest();
    csu.mrc.ivcc.mdss.entomology.assay.molecular.EKDRTestResultQueryDTO query = csu.mrc.ivcc.mdss.entomology.assay.molecular.EKDRTestResultDTO.getAllInstances(clientRequest, null, true, 20, 1);
    req.setAttribute("query", query);
    req.setAttribute("page_title", "View All EKDRTestResultController Objects");
    render("viewAllComponent.jsp");
  }
  public void failViewAll() throws java.io.IOException, javax.servlet.ServletException
  {
    resp.sendError(500);
  }
  public void cancel(csu.mrc.ivcc.mdss.entomology.assay.molecular.EKDRTestResultDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    dto.unlock();
    this.view(dto.getId());
  }
  public void failCancel(csu.mrc.ivcc.mdss.entomology.assay.molecular.EKDRTestResultDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    resp.sendError(500);
  }
  public void newInstance() throws java.io.IOException, javax.servlet.ServletException
  {
    com.terraframe.mojo.constants.ClientRequestIF clientRequest = super.getClientRequest();
    csu.mrc.ivcc.mdss.entomology.assay.molecular.EKDRTestResultDTO dto = new csu.mrc.ivcc.mdss.entomology.assay.molecular.EKDRTestResultDTO(clientRequest);
    req.setAttribute("mdss_ivcc_mrc_csu_entomology_assay_molecular_EKDRTestResult_testResult", csu.mrc.ivcc.mdss.mo.MolecularAssayResultDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("mdss_ivcc_mrc_csu_entomology_assay_AssayTestResult_mosquito", csu.mrc.ivcc.mdss.entomology.MosquitoDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("item", dto);
    req.setAttribute("page_title", "Create EKDRTestResultController");
    render("createComponent.jsp");
  }
  public void failNewInstance() throws java.io.IOException, javax.servlet.ServletException
  {
    this.viewAll();
  }
  public void viewPage(java.lang.String sortAttribute, java.lang.Boolean isAscending, java.lang.Integer pageSize, java.lang.Integer pageNumber) throws java.io.IOException, javax.servlet.ServletException
  {
    com.terraframe.mojo.constants.ClientRequestIF clientRequest = super.getClientRequest();
    csu.mrc.ivcc.mdss.entomology.assay.molecular.EKDRTestResultQueryDTO query = csu.mrc.ivcc.mdss.entomology.assay.molecular.EKDRTestResultDTO.getAllInstances(clientRequest, sortAttribute, isAscending, pageSize, pageNumber);
    req.setAttribute("query", query);
    req.setAttribute("page_title", "View All EKDRTestResultController Objects");
    render("viewAllComponent.jsp");
  }
  public void failViewPage(java.lang.String sortAttribute, java.lang.String isAscending, java.lang.String pageSize, java.lang.String pageNumber) throws java.io.IOException, javax.servlet.ServletException
  {
    resp.sendError(500);
  }
  public void view(java.lang.String id) throws java.io.IOException, javax.servlet.ServletException
  {
    com.terraframe.mojo.constants.ClientRequestIF clientRequest = super.getClientRequest();
    req.setAttribute("mdss_ivcc_mrc_csu_entomology_assay_molecular_EKDRTestResult_testResult", csu.mrc.ivcc.mdss.mo.MolecularAssayResultDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("mdss_ivcc_mrc_csu_entomology_assay_AssayTestResult_mosquito", csu.mrc.ivcc.mdss.entomology.MosquitoDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("item", csu.mrc.ivcc.mdss.entomology.assay.molecular.EKDRTestResultDTO.get(clientRequest, id));
    req.setAttribute("page_title", "View EKDRTestResultController");
    render("viewComponent.jsp");
  }
  public void failView(java.lang.String id) throws java.io.IOException, javax.servlet.ServletException
  {
    this.viewAll();
  }
  public void update(csu.mrc.ivcc.mdss.entomology.assay.molecular.EKDRTestResultDTO dto) throws java.io.IOException, javax.servlet.ServletException
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
  public void failUpdate(csu.mrc.ivcc.mdss.entomology.assay.molecular.EKDRTestResultDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    req.setAttribute("mdss_ivcc_mrc_csu_entomology_assay_molecular_EKDRTestResult_testResult", csu.mrc.ivcc.mdss.mo.MolecularAssayResultDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("mdss_ivcc_mrc_csu_entomology_assay_AssayTestResult_mosquito", csu.mrc.ivcc.mdss.entomology.MosquitoDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("item", dto);
    req.setAttribute("page_title", "Update EKDRTestResultController");
    render("updateComponent.jsp");
  }
  public void edit(java.lang.String id) throws java.io.IOException, javax.servlet.ServletException
  {
    csu.mrc.ivcc.mdss.entomology.assay.molecular.EKDRTestResultDTO dto = csu.mrc.ivcc.mdss.entomology.assay.molecular.EKDRTestResultDTO.lock(super.getClientRequest(), id);
    req.setAttribute("mdss_ivcc_mrc_csu_entomology_assay_molecular_EKDRTestResult_testResult", csu.mrc.ivcc.mdss.mo.MolecularAssayResultDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("mdss_ivcc_mrc_csu_entomology_assay_AssayTestResult_mosquito", csu.mrc.ivcc.mdss.entomology.MosquitoDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("item", dto);
    req.setAttribute("page_title", "Edit EKDRTestResultController");
    render("editComponent.jsp");
  }
  public void failEdit(java.lang.String id) throws java.io.IOException, javax.servlet.ServletException
  {
    this.view(id);
  }
  public void delete(csu.mrc.ivcc.mdss.entomology.assay.molecular.EKDRTestResultDTO dto) throws java.io.IOException, javax.servlet.ServletException
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
  public void failDelete(csu.mrc.ivcc.mdss.entomology.assay.molecular.EKDRTestResultDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    req.setAttribute("mdss_ivcc_mrc_csu_entomology_assay_molecular_EKDRTestResult_testResult", csu.mrc.ivcc.mdss.mo.MolecularAssayResultDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("mdss_ivcc_mrc_csu_entomology_assay_AssayTestResult_mosquito", csu.mrc.ivcc.mdss.entomology.MosquitoDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("item", dto);
    req.setAttribute("page_title", "Edit EKDRTestResultController");
    render("editComponent.jsp");
  }
  public void create(csu.mrc.ivcc.mdss.entomology.assay.molecular.EKDRTestResultDTO dto) throws java.io.IOException, javax.servlet.ServletException
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
  public void failCreate(csu.mrc.ivcc.mdss.entomology.assay.molecular.EKDRTestResultDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    req.setAttribute("mdss_ivcc_mrc_csu_entomology_assay_molecular_EKDRTestResult_testResult", csu.mrc.ivcc.mdss.mo.MolecularAssayResultDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("mdss_ivcc_mrc_csu_entomology_assay_AssayTestResult_mosquito", csu.mrc.ivcc.mdss.entomology.MosquitoDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("item", dto);
    req.setAttribute("page_title", "Create EKDRTestResultController");
    render("createComponent.jsp");
  }
}