package dss.vector.solutions.geo.generated;

public class HospitalController extends HospitalControllerBase implements com.terraframe.mojo.generation.loader.Reloadable
{
  public static final String JSP_DIR = "WEB-INF/dss/vector/solutions/geo/generated/Hospital/";
  public static final String LAYOUT = "/layout.jsp";
  
  private static final long serialVersionUID = 1244424912145L;
  
  public HospitalController(javax.servlet.http.HttpServletRequest req, javax.servlet.http.HttpServletResponse resp, java.lang.Boolean isAsynchronous)
  {
    super(req, resp, isAsynchronous, JSP_DIR, LAYOUT);
  }
  
  public void newInstance() throws java.io.IOException, javax.servlet.ServletException
  {
    com.terraframe.mojo.constants.ClientRequestIF clientRequest = super.getClientRequest();
    dss.vector.solutions.geo.generated.HospitalDTO dto = new dss.vector.solutions.geo.generated.HospitalDTO(clientRequest);
    req.setAttribute("item", dto);
    req.setAttribute("page_title", "Create Hospital");
    render("createComponent.jsp");
  }
  public void failNewInstance() throws java.io.IOException, javax.servlet.ServletException
  {
    this.viewAll();
  }
  public void viewAll() throws java.io.IOException, javax.servlet.ServletException
  {
    com.terraframe.mojo.constants.ClientRequestIF clientRequest = super.getClientRequest();
    dss.vector.solutions.geo.generated.HospitalQueryDTO query = dss.vector.solutions.geo.generated.HospitalDTO.getAllInstances(clientRequest, null, true, 20, 1);
    req.setAttribute("query", query);
    req.setAttribute("page_title", "View All Hospital Objects");
    render("viewAllComponent.jsp");
  }
  public void failViewAll() throws java.io.IOException, javax.servlet.ServletException
  {
    resp.sendError(500);
  }
  public void create(dss.vector.solutions.geo.generated.HospitalDTO dto) throws java.io.IOException, javax.servlet.ServletException
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
  public void failCreate(dss.vector.solutions.geo.generated.HospitalDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    req.setAttribute("item", dto);
    req.setAttribute("page_title", "Create Hospital");
    render("createComponent.jsp");
  }
  public void edit(java.lang.String id) throws java.io.IOException, javax.servlet.ServletException
  {
    dss.vector.solutions.geo.generated.HospitalDTO dto = dss.vector.solutions.geo.generated.HospitalDTO.lock(super.getClientRequest(), id);
    req.setAttribute("item", dto);
    req.setAttribute("page_title", "Edit Hospital");
    render("editComponent.jsp");
  }
  public void failEdit(java.lang.String id) throws java.io.IOException, javax.servlet.ServletException
  {
    this.view(id);
  }
  public void view(java.lang.String id) throws java.io.IOException, javax.servlet.ServletException
  {
    com.terraframe.mojo.constants.ClientRequestIF clientRequest = super.getClientRequest();
    req.setAttribute("item", dss.vector.solutions.geo.generated.HospitalDTO.get(clientRequest, id));
    req.setAttribute("page_title", "View Hospital");
    render("viewComponent.jsp");
  }
  public void failView(java.lang.String id) throws java.io.IOException, javax.servlet.ServletException
  {
    this.viewAll();
  }
  public void delete(dss.vector.solutions.geo.generated.HospitalDTO dto) throws java.io.IOException, javax.servlet.ServletException
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
  public void failDelete(dss.vector.solutions.geo.generated.HospitalDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    req.setAttribute("item", dto);
    req.setAttribute("page_title", "Edit Hospital");
    render("editComponent.jsp");
  }
  public void viewPage(java.lang.String sortAttribute, java.lang.Boolean isAscending, java.lang.Integer pageSize, java.lang.Integer pageNumber) throws java.io.IOException, javax.servlet.ServletException
  {
    com.terraframe.mojo.constants.ClientRequestIF clientRequest = super.getClientRequest();
    dss.vector.solutions.geo.generated.HospitalQueryDTO query = dss.vector.solutions.geo.generated.HospitalDTO.getAllInstances(clientRequest, sortAttribute, isAscending, pageSize, pageNumber);
    req.setAttribute("query", query);
    req.setAttribute("page_title", "View All Hospital Objects");
    render("viewAllComponent.jsp");
  }
  public void failViewPage(java.lang.String sortAttribute, java.lang.String isAscending, java.lang.String pageSize, java.lang.String pageNumber) throws java.io.IOException, javax.servlet.ServletException
  {
    resp.sendError(500);
  }
  public void update(dss.vector.solutions.geo.generated.HospitalDTO dto) throws java.io.IOException, javax.servlet.ServletException
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
  public void failUpdate(dss.vector.solutions.geo.generated.HospitalDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    req.setAttribute("item", dto);
    req.setAttribute("page_title", "Update Hospital");
    render("editComponent.jsp");
  }
  public void cancel(dss.vector.solutions.geo.generated.HospitalDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    dto.unlock();
    this.view(dto.getId());
  }
  public void failCancel(dss.vector.solutions.geo.generated.HospitalDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    this.edit(dto.getId());
  }
}
