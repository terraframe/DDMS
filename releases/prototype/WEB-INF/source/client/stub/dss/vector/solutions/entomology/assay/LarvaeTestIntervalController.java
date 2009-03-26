package dss.vector.solutions.entomology.assay;

public class LarvaeTestIntervalController extends LarvaeTestIntervalControllerBase implements com.terraframe.mojo.generation.loader.Reloadable
{
  public static final String JSP_DIR = "WEB-INF/dss/vector/solutions/entomology/assay/LarvaeTestInterval/";
  public static final String LAYOUT = JSP_DIR + "layout.jsp";
  
  private static final long serialVersionUID = 1237579423735L;
  
  public LarvaeTestIntervalController(javax.servlet.http.HttpServletRequest req, javax.servlet.http.HttpServletResponse resp, java.lang.Boolean isAsynchronous)
  {
    super(req, resp, isAsynchronous, JSP_DIR, LAYOUT);
  }
  
  public void delete(dss.vector.solutions.entomology.assay.LarvaeTestIntervalDTO dto) throws java.io.IOException, javax.servlet.ServletException
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
  public void failDelete(dss.vector.solutions.entomology.assay.LarvaeTestIntervalDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    req.setAttribute("dss_vector_solutions_entomology_assay_LarvaeTestInterval_assay", dss.vector.solutions.entomology.assay.LarvaeAssayDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("item", dto);
    req.setAttribute("page_title", "Edit LarvaeTestIntervalController");
    render("editComponent.jsp");
  }
  public void newInstance() throws java.io.IOException, javax.servlet.ServletException
  {
    com.terraframe.mojo.constants.ClientRequestIF clientRequest = super.getClientRequest();
    dss.vector.solutions.entomology.assay.LarvaeTestIntervalDTO dto = new dss.vector.solutions.entomology.assay.LarvaeTestIntervalDTO(clientRequest);
    req.setAttribute("dss_vector_solutions_entomology_assay_LarvaeTestInterval_assay", dss.vector.solutions.entomology.assay.LarvaeAssayDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("item", dto);
    req.setAttribute("page_title", "Create LarvaeTestIntervalController");
    render("createComponent.jsp");
  }
  public void failNewInstance() throws java.io.IOException, javax.servlet.ServletException
  {
    this.viewAll();
  }
  public void cancel(dss.vector.solutions.entomology.assay.LarvaeTestIntervalDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    dto.unlock();
    this.view(dto.getId());
  }
  public void failCancel(dss.vector.solutions.entomology.assay.LarvaeTestIntervalDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    resp.sendError(500);
  }
  public void create(dss.vector.solutions.entomology.assay.LarvaeTestIntervalDTO dto) throws java.io.IOException, javax.servlet.ServletException
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
  public void failCreate(dss.vector.solutions.entomology.assay.LarvaeTestIntervalDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    req.setAttribute("dss_vector_solutions_entomology_assay_LarvaeTestInterval_assay", dss.vector.solutions.entomology.assay.LarvaeAssayDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("item", dto);
    req.setAttribute("page_title", "Create LarvaeTestIntervalController");
    render("createComponent.jsp");
  }
  public void viewPage(java.lang.String sortAttribute, java.lang.Boolean isAscending, java.lang.Integer pageSize, java.lang.Integer pageNumber) throws java.io.IOException, javax.servlet.ServletException
  {
    com.terraframe.mojo.constants.ClientRequestIF clientRequest = super.getClientRequest();
    dss.vector.solutions.entomology.assay.LarvaeTestIntervalQueryDTO query = dss.vector.solutions.entomology.assay.LarvaeTestIntervalDTO.getAllInstances(clientRequest, sortAttribute, isAscending, pageSize, pageNumber);
    req.setAttribute("query", query);
    req.setAttribute("page_title", "View All LarvaeTestIntervalController Objects");
    render("viewAllComponent.jsp");
  }
  public void failViewPage(java.lang.String sortAttribute, java.lang.String isAscending, java.lang.String pageSize, java.lang.String pageNumber) throws java.io.IOException, javax.servlet.ServletException
  {
    resp.sendError(500);
  }
  public void update(dss.vector.solutions.entomology.assay.LarvaeTestIntervalDTO dto) throws java.io.IOException, javax.servlet.ServletException
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
  public void failUpdate(dss.vector.solutions.entomology.assay.LarvaeTestIntervalDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    req.setAttribute("dss_vector_solutions_entomology_assay_LarvaeTestInterval_assay", dss.vector.solutions.entomology.assay.LarvaeAssayDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("item", dto);
    req.setAttribute("page_title", "Update LarvaeTestIntervalController");
    render("updateComponent.jsp");
  }
  public void viewAll() throws java.io.IOException, javax.servlet.ServletException
  {
    com.terraframe.mojo.constants.ClientRequestIF clientRequest = super.getClientRequest();
    dss.vector.solutions.entomology.assay.LarvaeTestIntervalQueryDTO query = dss.vector.solutions.entomology.assay.LarvaeTestIntervalDTO.getAllInstances(clientRequest, null, true, 20, 1);
    req.setAttribute("query", query);
    req.setAttribute("page_title", "View All LarvaeTestIntervalController Objects");
    render("viewAllComponent.jsp");
  }
  public void failViewAll() throws java.io.IOException, javax.servlet.ServletException
  {
    resp.sendError(500);
  }
  public void view(java.lang.String id) throws java.io.IOException, javax.servlet.ServletException
  {
    com.terraframe.mojo.constants.ClientRequestIF clientRequest = super.getClientRequest();
    req.setAttribute("dss_vector_solutions_entomology_assay_LarvaeTestInterval_assay", dss.vector.solutions.entomology.assay.LarvaeAssayDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("item", dss.vector.solutions.entomology.assay.LarvaeTestIntervalDTO.get(clientRequest, id));
    req.setAttribute("page_title", "View LarvaeTestIntervalController");
    render("viewComponent.jsp");
  }
  public void failView(java.lang.String id) throws java.io.IOException, javax.servlet.ServletException
  {
    this.viewAll();
  }
  public void edit(java.lang.String id) throws java.io.IOException, javax.servlet.ServletException
  {
    dss.vector.solutions.entomology.assay.LarvaeTestIntervalDTO dto = dss.vector.solutions.entomology.assay.LarvaeTestIntervalDTO.lock(super.getClientRequest(), id);
    req.setAttribute("dss_vector_solutions_entomology_assay_LarvaeTestInterval_assay", dss.vector.solutions.entomology.assay.LarvaeAssayDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("item", dto);
    req.setAttribute("page_title", "Edit LarvaeTestIntervalController");
    render("editComponent.jsp");
  }
  public void failEdit(java.lang.String id) throws java.io.IOException, javax.servlet.ServletException
  {
    this.view(id);
  }
}