package dss.vector.solutions.irs;

public class InTeamController extends InTeamControllerBase implements com.terraframe.mojo.generation.loader.Reloadable
{
  public static final String JSP_DIR = "WEB-INF/dss/vector/solutions/irs/InTeam/";
  public static final String LAYOUT = JSP_DIR + "layout.jsp";
  
  private static final long serialVersionUID = 1240342486241L;
  
  public InTeamController(javax.servlet.http.HttpServletRequest req, javax.servlet.http.HttpServletResponse resp, java.lang.Boolean isAsynchronous)
  {
    super(req, resp, isAsynchronous, JSP_DIR, LAYOUT);
  }
  
  public void viewPage(java.lang.String sortAttribute, java.lang.Boolean isAscending, java.lang.Integer pageSize, java.lang.Integer pageNumber) throws java.io.IOException, javax.servlet.ServletException
  {
    com.terraframe.mojo.constants.ClientRequestIF clientRequest = super.getClientRequest();
    dss.vector.solutions.irs.InTeamQueryDTO query = dss.vector.solutions.irs.InTeamDTO.getAllInstances(clientRequest, sortAttribute, isAscending, pageSize, pageNumber);
    req.setAttribute("query", query);
    req.setAttribute("page_title", "View All InTeamController Objects");
    render("viewAllComponent.jsp");
  }
  public void failViewPage(java.lang.String sortAttribute, java.lang.String isAscending, java.lang.String pageSize, java.lang.String pageNumber) throws java.io.IOException, javax.servlet.ServletException
  {
    resp.sendError(500);
  }
  public void update(dss.vector.solutions.irs.InTeamDTO dto) throws java.io.IOException, javax.servlet.ServletException
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
  public void failUpdate(dss.vector.solutions.irs.InTeamDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    req.setAttribute("item", dto);
    req.setAttribute("page_title", "Update InTeamController");
    render("editComponent.jsp");
  }
  public void newInstance(java.lang.String parentId, java.lang.String childId) throws java.io.IOException, javax.servlet.ServletException
  {
    com.terraframe.mojo.constants.ClientRequestIF clientRequest = super.getClientRequest();
    dss.vector.solutions.irs.InTeamDTO dto = new dss.vector.solutions.irs.InTeamDTO(clientRequest, parentId, childId);
    req.setAttribute("item", dto);
    req.setAttribute("page_title", "Create InTeamController");
    render("createComponent.jsp");
  }
  public void failNewInstance(java.lang.String parentId, java.lang.String childId) throws java.io.IOException, javax.servlet.ServletException
  {
    this.viewAll();
  }
  public void edit(java.lang.String id) throws java.io.IOException, javax.servlet.ServletException
  {
    dss.vector.solutions.irs.InTeamDTO dto = dss.vector.solutions.irs.InTeamDTO.lock(super.getClientRequest(), id);
    req.setAttribute("item", dto);
    req.setAttribute("page_title", "Edit InTeamController");
    render("editComponent.jsp");
  }
  public void failEdit(java.lang.String id) throws java.io.IOException, javax.servlet.ServletException
  {
    this.view(id);
  }
  public void view(java.lang.String id) throws java.io.IOException, javax.servlet.ServletException
  {
    com.terraframe.mojo.constants.ClientRequestIF clientRequest = super.getClientRequest();
    req.setAttribute("item", dss.vector.solutions.irs.InTeamDTO.get(clientRequest, id));
    req.setAttribute("page_title", "View InTeamController");
    render("viewComponent.jsp");
  }
  public void failView(java.lang.String id) throws java.io.IOException, javax.servlet.ServletException
  {
    this.viewAll();
  }
  public void cancel(dss.vector.solutions.irs.InTeamDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    dto.unlock();
    this.view(dto.getId());
  }
  public void failCancel(dss.vector.solutions.irs.InTeamDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    resp.sendError(500);
  }
  public void viewAll() throws java.io.IOException, javax.servlet.ServletException
  {
    com.terraframe.mojo.constants.ClientRequestIF clientRequest = super.getClientRequest();
    dss.vector.solutions.irs.InTeamQueryDTO query = dss.vector.solutions.irs.InTeamDTO.getAllInstances(clientRequest, null, true, 20, 1);
    req.setAttribute("query", query);
    req.setAttribute("page_title", "View All InTeamController Objects");
    render("viewAllComponent.jsp");
  }
  public void failViewAll() throws java.io.IOException, javax.servlet.ServletException
  {
    resp.sendError(500);
  }
  public void parentQuery(java.lang.String parentId) throws java.io.IOException, javax.servlet.ServletException
  {
    com.terraframe.mojo.constants.ClientRequestIF clientRequest = super.getClientRequest();
    dss.vector.solutions.irs.InTeamQueryDTO query = dss.vector.solutions.irs.InTeamDTO.parentQuery(clientRequest, parentId);
    req.setAttribute("query", query);
    req.setAttribute("page_title", "View All InTeamController Objects");
    render("viewAllComponent.jsp");
  }
  public void failParentQuery(java.lang.String parentId) throws java.io.IOException, javax.servlet.ServletException
  {
    resp.sendError(500);
  }
  public void create(dss.vector.solutions.irs.InTeamDTO dto) throws java.io.IOException, javax.servlet.ServletException
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
  public void failCreate(dss.vector.solutions.irs.InTeamDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    req.setAttribute("item", dto);
    req.setAttribute("page_title", "Create InTeamController");
    render("createComponent.jsp");
  }
  public void delete(dss.vector.solutions.irs.InTeamDTO dto) throws java.io.IOException, javax.servlet.ServletException
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
  public void failDelete(dss.vector.solutions.irs.InTeamDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    req.setAttribute("item", dto);
    req.setAttribute("page_title", "Edit InTeamController");
    render("editComponent.jsp");
  }
  public void childQuery(java.lang.String childId) throws java.io.IOException, javax.servlet.ServletException
  {
    com.terraframe.mojo.constants.ClientRequestIF clientRequest = super.getClientRequest();
    dss.vector.solutions.irs.InTeamQueryDTO query = dss.vector.solutions.irs.InTeamDTO.childQuery(clientRequest, childId);
    req.setAttribute("query", query);
    req.setAttribute("page_title", "View All InTeamController Objects");
    render("viewAllComponent.jsp");
  }
  public void failChildQuery(java.lang.String childId) throws java.io.IOException, javax.servlet.ServletException
  {
    resp.sendError(500);
  }
  public void newRelationship() throws java.io.IOException, javax.servlet.ServletException
  {
    req.setAttribute("parentList", dss.vector.solutions.irs.SprayTeamDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("childList", dss.vector.solutions.MDSSUserDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("page_title", "Parent/Child Selection");
    render("newRelationshipComponent.jsp");
  }
  public void failNewRelationship() throws java.io.IOException, javax.servlet.ServletException
  {
    resp.sendError(500);
  }
}
