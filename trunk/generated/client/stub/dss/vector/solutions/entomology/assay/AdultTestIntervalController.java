package dss.vector.solutions.entomology.assay;

public class AdultTestIntervalController extends AdultTestIntervalControllerBase implements com.terraframe.mojo.generation.loader.Reloadable
{
  public static final String JSP_DIR = "WEB-INF/dss/vector/solutions/entomology/assay/AdultTestInterval/";
  public static final String LAYOUT = "/layout.jsp";
  
  private static final long serialVersionUID = 1237579407292L;
  
  public AdultTestIntervalController(javax.servlet.http.HttpServletRequest req, javax.servlet.http.HttpServletResponse resp, java.lang.Boolean isAsynchronous)
  {
    super(req, resp, isAsynchronous, JSP_DIR, LAYOUT);
  }
  
  public void cancel(dss.vector.solutions.entomology.assay.AdultTestIntervalDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    dto.unlock();
    this.view(dto.getId());
  }
  public void failCancel(dss.vector.solutions.entomology.assay.AdultTestIntervalDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    resp.sendError(500);
  }
  public void edit(java.lang.String id) throws java.io.IOException, javax.servlet.ServletException
  {
    dss.vector.solutions.entomology.assay.AdultTestIntervalDTO dto = dss.vector.solutions.entomology.assay.AdultTestIntervalDTO.lock(super.getClientRequest(), id);
    req.setAttribute("dss_vector_solutions_entomology_assay_AdultTestInterval_assay", dss.vector.solutions.entomology.assay.AdultAssayDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("item", dto);
    
    render("editComponent.jsp");
  }
  public void failEdit(java.lang.String id) throws java.io.IOException, javax.servlet.ServletException
  {
    this.view(id);
  }
  public void newInstance() throws java.io.IOException, javax.servlet.ServletException
  {
    com.terraframe.mojo.constants.ClientRequestIF clientRequest = super.getClientRequest();
    dss.vector.solutions.entomology.assay.AdultTestIntervalDTO dto = new dss.vector.solutions.entomology.assay.AdultTestIntervalDTO(clientRequest);
    req.setAttribute("dss_vector_solutions_entomology_assay_AdultTestInterval_assay", dss.vector.solutions.entomology.assay.AdultAssayDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("item", dto);
    
    render("createComponent.jsp");
  }
  public void failNewInstance() throws java.io.IOException, javax.servlet.ServletException
  {
    this.viewAll();
  }
  public void viewPage(java.lang.String sortAttribute, java.lang.Boolean isAscending, java.lang.Integer pageSize, java.lang.Integer pageNumber) throws java.io.IOException, javax.servlet.ServletException
  {
    com.terraframe.mojo.constants.ClientRequestIF clientRequest = super.getClientRequest();
    dss.vector.solutions.entomology.assay.AdultTestIntervalQueryDTO query = dss.vector.solutions.entomology.assay.AdultTestIntervalDTO.getAllInstances(clientRequest, sortAttribute, isAscending, pageSize, pageNumber);
    req.setAttribute("query", query);
    
    render("viewAllComponent.jsp");
  }
  public void failViewPage(java.lang.String sortAttribute, java.lang.String isAscending, java.lang.String pageSize, java.lang.String pageNumber) throws java.io.IOException, javax.servlet.ServletException
  {
    resp.sendError(500);
  }
  public void viewAll() throws java.io.IOException, javax.servlet.ServletException
  {
    com.terraframe.mojo.constants.ClientRequestIF clientRequest = super.getClientRequest();
    dss.vector.solutions.entomology.assay.AdultTestIntervalQueryDTO query = dss.vector.solutions.entomology.assay.AdultTestIntervalDTO.getAllInstances(clientRequest, null, true, 20, 1);
    req.setAttribute("query", query);
    
    render("viewAllComponent.jsp");
  }
  public void failViewAll() throws java.io.IOException, javax.servlet.ServletException
  {
    resp.sendError(500);
  }
  public void create(dss.vector.solutions.entomology.assay.AdultTestIntervalDTO dto) throws java.io.IOException, javax.servlet.ServletException
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
  public void failCreate(dss.vector.solutions.entomology.assay.AdultTestIntervalDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    req.setAttribute("dss_vector_solutions_entomology_assay_AdultTestInterval_assay", dss.vector.solutions.entomology.assay.AdultAssayDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("item", dto);
    
    render("createComponent.jsp");
  }
  public void delete(dss.vector.solutions.entomology.assay.AdultTestIntervalDTO dto) throws java.io.IOException, javax.servlet.ServletException
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
  public void failDelete(dss.vector.solutions.entomology.assay.AdultTestIntervalDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    req.setAttribute("dss_vector_solutions_entomology_assay_AdultTestInterval_assay", dss.vector.solutions.entomology.assay.AdultAssayDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("item", dto);
    
    render("editComponent.jsp");
  }
  public void view(java.lang.String id) throws java.io.IOException, javax.servlet.ServletException
  {
    com.terraframe.mojo.constants.ClientRequestIF clientRequest = super.getClientRequest();
    req.setAttribute("dss_vector_solutions_entomology_assay_AdultTestInterval_assay", dss.vector.solutions.entomology.assay.AdultAssayDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("item", dss.vector.solutions.entomology.assay.AdultTestIntervalDTO.get(clientRequest, id));
    
    render("viewComponent.jsp");
  }
  public void failView(java.lang.String id) throws java.io.IOException, javax.servlet.ServletException
  {
    this.viewAll();
  }
  public void update(dss.vector.solutions.entomology.assay.AdultTestIntervalDTO dto) throws java.io.IOException, javax.servlet.ServletException
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
  public void failUpdate(dss.vector.solutions.entomology.assay.AdultTestIntervalDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    req.setAttribute("dss_vector_solutions_entomology_assay_AdultTestInterval_assay", dss.vector.solutions.entomology.assay.AdultAssayDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("item", dto);
    
    render("updateComponent.jsp");
  }
}
