package dss.vector.solutions.general;

public class EmailConfigurationController extends EmailConfigurationControllerBase implements com.terraframe.mojo.generation.loader.Reloadable
{
  public static final String JSP_DIR = "WEB-INF/dss/vector/solutions/general/EmailConfiguration/";
  public static final String LAYOUT = "/layout.jsp";
  
  private static final long serialVersionUID = -1538736812;
  
  public EmailConfigurationController(javax.servlet.http.HttpServletRequest req, javax.servlet.http.HttpServletResponse resp, java.lang.Boolean isAsynchronous)
  {
    super(req, resp, isAsynchronous, JSP_DIR, LAYOUT);
  }
  
  public void cancel(dss.vector.solutions.general.EmailConfigurationDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    dto.unlock();
    this.view(dto.getId());
  }
  public void failCancel(dss.vector.solutions.general.EmailConfigurationDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    this.edit(dto.getId());
  }
  public void create(dss.vector.solutions.general.EmailConfigurationDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    try
    {
      dto.apply();
      this.view(dto.getId());
    }
    catch(com.terraframe.mojo.ProblemExceptionDTO e)
    {
      dss.vector.solutions.util.ErrorUtility.prepareProblems(e, req);
      this.failCreate(dto);
    }
    catch(java.lang.Throwable t)
    {
      dss.vector.solutions.util.ErrorUtility.prepareThrowable(t, req);
      this.failCreate(dto);
    }
  }
  public void failCreate(dss.vector.solutions.general.EmailConfigurationDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    req.setAttribute("protocol", dss.vector.solutions.general.EmailProtocolDTO.allItems(super.getClientSession().getRequest()));
    req.setAttribute("item", dto);
    render("createComponent.jsp");
  }
  public void delete(dss.vector.solutions.general.EmailConfigurationDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    try
    {
      dto.delete();
      this.viewAll();
    }
    catch(com.terraframe.mojo.ProblemExceptionDTO e)
    {
      dss.vector.solutions.util.ErrorUtility.prepareProblems(e, req);
      this.failDelete(dto);
    }
    catch(java.lang.Throwable t)
    {
      dss.vector.solutions.util.ErrorUtility.prepareThrowable(t, req);
      this.failDelete(dto);
    }
  }
  public void failDelete(dss.vector.solutions.general.EmailConfigurationDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    req.setAttribute("protocol", dss.vector.solutions.general.EmailProtocolDTO.allItems(super.getClientSession().getRequest()));
    req.setAttribute("item", dto);
    render("editComponent.jsp");
  }
  public void edit(java.lang.String id) throws java.io.IOException, javax.servlet.ServletException
  {
    dss.vector.solutions.general.EmailConfigurationDTO dto = dss.vector.solutions.general.EmailConfigurationDTO.lock(super.getClientRequest(), id);
    req.setAttribute("protocol", dss.vector.solutions.general.EmailProtocolDTO.allItems(super.getClientSession().getRequest()));
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
    dss.vector.solutions.general.EmailConfigurationDTO dto = new dss.vector.solutions.general.EmailConfigurationDTO(clientRequest);
    req.setAttribute("protocol", dss.vector.solutions.general.EmailProtocolDTO.allItems(super.getClientSession().getRequest()));
    req.setAttribute("item", dto);
    render("createComponent.jsp");
  }
  public void failNewInstance() throws java.io.IOException, javax.servlet.ServletException
  {
    this.viewAll();
  }
  public void update(dss.vector.solutions.general.EmailConfigurationDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    try
    {
      dto.apply();
      this.view(dto.getId());
    }
    catch(com.terraframe.mojo.ProblemExceptionDTO e)
    {
      dss.vector.solutions.util.ErrorUtility.prepareProblems(e, req);
      this.failUpdate(dto);
    }
    catch(java.lang.Throwable t)
    {
      dss.vector.solutions.util.ErrorUtility.prepareThrowable(t, req);
      this.failUpdate(dto);
    }
  }
  public void failUpdate(dss.vector.solutions.general.EmailConfigurationDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    req.setAttribute("protocol", dss.vector.solutions.general.EmailProtocolDTO.allItems(super.getClientSession().getRequest()));
    req.setAttribute("item", dto);
    render("editComponent.jsp");
  }
  public void view(java.lang.String id) throws java.io.IOException, javax.servlet.ServletException
  {
    dss.vector.solutions.util.RedirectUtility utility = new dss.vector.solutions.util.RedirectUtility(req, resp);
    utility.put("id", id);
    utility.checkURL(this.getClass().getSimpleName(), "view");
    com.terraframe.mojo.constants.ClientRequestIF clientRequest = super.getClientRequest();
    dss.vector.solutions.general.EmailConfigurationDTO dto = dss.vector.solutions.general.EmailConfigurationDTO.get(clientRequest, id);
    req.setAttribute("protocol", dss.vector.solutions.general.EmailProtocolDTO.allItems(super.getClientSession().getRequest()));
    req.setAttribute("item", dto);
    render("viewComponent.jsp");
  }
  public void failView(java.lang.String id) throws java.io.IOException, javax.servlet.ServletException
  {
    this.viewAll();
  }
  public void viewAll() throws java.io.IOException, javax.servlet.ServletException
  {
    com.terraframe.mojo.constants.ClientRequestIF clientRequest = super.getClientRequest();
    dss.vector.solutions.general.EmailConfigurationQueryDTO query = dss.vector.solutions.general.EmailConfigurationDTO.getAllInstances(clientRequest, null, true, 20, 1);
    req.setAttribute("query", query);
    render("viewAllComponent.jsp");
  }
  public void failViewAll() throws java.io.IOException, javax.servlet.ServletException
  {
    resp.sendError(500);
  }
  public void viewPage(java.lang.String sortAttribute, java.lang.Boolean isAscending, java.lang.Integer pageSize, java.lang.Integer pageNumber) throws java.io.IOException, javax.servlet.ServletException
  {
    com.terraframe.mojo.constants.ClientRequestIF clientRequest = super.getClientRequest();
    dss.vector.solutions.general.EmailConfigurationQueryDTO query = dss.vector.solutions.general.EmailConfigurationDTO.getAllInstances(clientRequest, sortAttribute, isAscending, pageSize, pageNumber);
    req.setAttribute("query", query);
    render("viewAllComponent.jsp");
  }
  public void failViewPage(java.lang.String sortAttribute, java.lang.String isAscending, java.lang.String pageSize, java.lang.String pageNumber) throws java.io.IOException, javax.servlet.ServletException
  {
    resp.sendError(500);
  }
}
