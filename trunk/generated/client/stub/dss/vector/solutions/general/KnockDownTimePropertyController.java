package dss.vector.solutions.general;

public class KnockDownTimePropertyController extends KnockDownTimePropertyControllerBase implements com.terraframe.mojo.generation.loader.Reloadable
{
  public static final String JSP_DIR = "WEB-INF/dss/vector/solutions/general/KnockDownTimeProperty/";
  public static final String LAYOUT = JSP_DIR + "layout.jsp";
  
  private static final long serialVersionUID = 1237411066733L;
  
  public KnockDownTimePropertyController(javax.servlet.http.HttpServletRequest req, javax.servlet.http.HttpServletResponse resp, java.lang.Boolean isAsynchronous)
  {
    super(req, resp, isAsynchronous, JSP_DIR, LAYOUT);
  }
  
  public void delete(dss.vector.solutions.general.KnockDownTimePropertyDTO dto) throws java.io.IOException, javax.servlet.ServletException
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
  public void failDelete(dss.vector.solutions.general.KnockDownTimePropertyDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    req.setAttribute("insecticide", dss.vector.solutions.general.InsecticideDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("item", dto);
    req.setAttribute("page_title", "Edit Knock Down Time Property");
    render("editComponent.jsp");
  }
  public void update(dss.vector.solutions.general.KnockDownTimePropertyDTO dto) throws java.io.IOException, javax.servlet.ServletException
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
  public void failUpdate(dss.vector.solutions.general.KnockDownTimePropertyDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    req.setAttribute("insecticide", dss.vector.solutions.general.InsecticideDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("item", dto);
    req.setAttribute("page_title", "Update Knock Down Time Property");
    render("updateComponent.jsp");
  }
  public void edit(java.lang.String id) throws java.io.IOException, javax.servlet.ServletException
  {
    dss.vector.solutions.general.KnockDownTimePropertyDTO dto = dss.vector.solutions.general.KnockDownTimePropertyDTO.lock(super.getClientRequest(), id);
    req.setAttribute("insecticide", dss.vector.solutions.general.InsecticideDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("item", dto);
    req.setAttribute("page_title", "Edit Knock Down Time Property");
    render("editComponent.jsp");
  }
  public void failEdit(java.lang.String id) throws java.io.IOException, javax.servlet.ServletException
  {
    this.view(id);
  }
  public void newInstance() throws java.io.IOException, javax.servlet.ServletException
  {
    com.terraframe.mojo.constants.ClientRequestIF clientRequest = super.getClientRequest();
    dss.vector.solutions.general.KnockDownTimePropertyDTO dto = new dss.vector.solutions.general.KnockDownTimePropertyDTO(clientRequest);
    req.setAttribute("insecticide", dss.vector.solutions.general.InsecticideDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("item", dto);
    req.setAttribute("page_title", "Create Knock Down Time Property");
    render("createComponent.jsp");
  }
  public void failNewInstance() throws java.io.IOException, javax.servlet.ServletException
  {
    this.viewAll();
  }
  public void create(dss.vector.solutions.general.KnockDownTimePropertyDTO dto) throws java.io.IOException, javax.servlet.ServletException
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
  public void failCreate(dss.vector.solutions.general.KnockDownTimePropertyDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    req.setAttribute("insecticide", dss.vector.solutions.general.InsecticideDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("item", dto);
    req.setAttribute("page_title", "Create Knock Down Time Property");
    render("createComponent.jsp");
  }
  public void cancel(dss.vector.solutions.general.KnockDownTimePropertyDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    dto.unlock();
    this.view(dto.getId());
  }
  public void failCancel(dss.vector.solutions.general.KnockDownTimePropertyDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    resp.sendError(500);
  }
  public void view(java.lang.String id) throws java.io.IOException, javax.servlet.ServletException
  {
    com.terraframe.mojo.constants.ClientRequestIF clientRequest = super.getClientRequest();
    req.setAttribute("insecticide", dss.vector.solutions.general.InsecticideDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("item", dss.vector.solutions.general.KnockDownTimePropertyDTO.get(clientRequest, id));
    req.setAttribute("page_title", "View Knock Down Time Property");
    render("viewComponent.jsp");
  }
  public void failView(java.lang.String id) throws java.io.IOException, javax.servlet.ServletException
  {
    this.viewAll();
  }
  public void viewAll() throws java.io.IOException, javax.servlet.ServletException
  {
    req.setAttribute("page_title", "View All Knock Down Time Property");
    render("viewAllComponent.jsp");
  }
  public void failViewAll() throws java.io.IOException, javax.servlet.ServletException
  {
    resp.sendError(500);
  }
  public void viewPage(java.lang.String sortAttribute, java.lang.Boolean isAscending, java.lang.Integer pageSize, java.lang.Integer pageNumber) throws java.io.IOException, javax.servlet.ServletException
  {
    com.terraframe.mojo.constants.ClientRequestIF clientRequest = super.getClientRequest();
    dss.vector.solutions.general.KnockDownTimePropertyQueryDTO query = dss.vector.solutions.general.KnockDownTimePropertyDTO.getAllInstances(clientRequest, sortAttribute, isAscending, pageSize, pageNumber);
    req.setAttribute("query", query);
    req.setAttribute("page_title", "View All Knock Down Time Property");
    render("viewAllComponent.jsp");
  }
  public void failViewPage(java.lang.String sortAttribute, java.lang.String isAscending, java.lang.String pageSize, java.lang.String pageNumber) throws java.io.IOException, javax.servlet.ServletException
  {
    resp.sendError(500);
  }
}
