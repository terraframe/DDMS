package mdss.general;

public class SexMasterController extends SexMasterControllerBase implements com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1234288148322L;
  
  public SexMasterController(javax.servlet.http.HttpServletRequest req, javax.servlet.http.HttpServletResponse resp, java.lang.Boolean isAsynchronous)
  {
    super(req, resp, isAsynchronous);
  }
  
  public void cancel(mdss.general.SexMasterDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    dto.unlock();
    this.view(dto.getId());
  }
  public void failCancel(mdss.general.SexMasterDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    resp.sendError(500);
  }
  public void delete(mdss.general.SexMasterDTO dto) throws java.io.IOException, javax.servlet.ServletException
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
  public void failDelete(mdss.general.SexMasterDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    req.setAttribute("item", dto);
    if(this.isAsynchronous())
    {
      req.getRequestDispatcher("WEB-INF/mdss/general/SexMaster/editComponent.jsp").forward(req, resp);
    }
    else
    {
      req.getRequestDispatcher("WEB-INF/mdss/general/SexMaster/edit.jsp").forward(req, resp);
    }
  }
  public void viewAll() throws java.io.IOException, javax.servlet.ServletException
  {
    com.terraframe.mojo.constants.ClientRequestIF clientRequest = super.getClientRequest();
    mdss.general.SexMasterQueryDTO query = mdss.general.SexMasterDTO.getAllInstances(clientRequest, null, true, 20, 1);
    req.setAttribute("query", query);
    if(this.isAsynchronous())
    {
      req.getRequestDispatcher("WEB-INF/mdss/general/SexMaster/viewAllComponent.jsp").forward(req, resp);
    }
    else
    {
      req.getRequestDispatcher("WEB-INF/mdss/general/SexMaster/viewAll.jsp").forward(req, resp);
    }
  }
  public void failViewAll() throws java.io.IOException, javax.servlet.ServletException
  {
    resp.sendError(500);
  }
  public void create(mdss.general.SexMasterDTO dto) throws java.io.IOException, javax.servlet.ServletException
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
  public void failCreate(mdss.general.SexMasterDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    req.setAttribute("item", dto);
    if(this.isAsynchronous())
    {
      req.getRequestDispatcher("WEB-INF/mdss/general/SexMaster/createComponent.jsp").forward(req, resp);
    }
    else
    {
      req.getRequestDispatcher("WEB-INF/mdss/general/SexMaster/create.jsp").forward(req, resp);
    }
  }
  public void viewPage(java.lang.String sortAttribute, java.lang.Boolean isAscending, java.lang.Integer pageSize, java.lang.Integer pageNumber) throws java.io.IOException, javax.servlet.ServletException
  {
    com.terraframe.mojo.constants.ClientRequestIF clientRequest = super.getClientRequest();
    mdss.general.SexMasterQueryDTO query = mdss.general.SexMasterDTO.getAllInstances(clientRequest, sortAttribute, isAscending, pageSize, pageNumber);
    req.setAttribute("query", query);
    if(this.isAsynchronous())
    {
      req.getRequestDispatcher("WEB-INF/mdss/general/SexMaster/viewAllComponent.jsp").forward(req, resp);
    }
    else
    {
      req.getRequestDispatcher("WEB-INF/mdss/general/SexMaster/viewAll.jsp").forward(req, resp);
    }
  }
  public void failViewPage(java.lang.String sortAttribute, java.lang.String isAscending, java.lang.String pageSize, java.lang.String pageNumber) throws java.io.IOException, javax.servlet.ServletException
  {
    resp.sendError(500);
  }
  public void edit(java.lang.String id) throws java.io.IOException, javax.servlet.ServletException
  {
    mdss.general.SexMasterDTO dto = mdss.general.SexMasterDTO.lock(super.getClientRequest(), id);
    req.setAttribute("item", dto);
    if(this.isAsynchronous())
    {
      req.getRequestDispatcher("WEB-INF/mdss/general/SexMaster/editComponent.jsp").forward(req, resp);
    }
    else
    {
      req.getRequestDispatcher("WEB-INF/mdss/general/SexMaster/edit.jsp").forward(req, resp);
    }
  }
  public void failEdit(java.lang.String id) throws java.io.IOException, javax.servlet.ServletException
  {
    this.view(id);
  }
  public void view(java.lang.String id) throws java.io.IOException, javax.servlet.ServletException
  {
    com.terraframe.mojo.constants.ClientRequestIF clientRequest = super.getClientRequest();
    req.setAttribute("item", mdss.general.SexMasterDTO.get(clientRequest, id));
    if(this.isAsynchronous())
    {
      req.getRequestDispatcher("WEB-INF/mdss/general/SexMaster/viewComponent.jsp").forward(req, resp);
    }
    else
    {
      req.getRequestDispatcher("WEB-INF/mdss/general/SexMaster/view.jsp").forward(req, resp);
    }
  }
  public void failView(java.lang.String id) throws java.io.IOException, javax.servlet.ServletException
  {
    this.viewAll();
  }
  public void newInstance() throws java.io.IOException, javax.servlet.ServletException
  {
    com.terraframe.mojo.constants.ClientRequestIF clientRequest = super.getClientRequest();
    mdss.general.SexMasterDTO dto = new mdss.general.SexMasterDTO(clientRequest);
    req.setAttribute("item", dto);
    if(this.isAsynchronous())
    {
      req.getRequestDispatcher("WEB-INF/mdss/general/SexMaster/createComponent.jsp").forward(req, resp);
    }
    else
    {
      req.getRequestDispatcher("WEB-INF/mdss/general/SexMaster/create.jsp").forward(req, resp);
    }
  }
  public void failNewInstance() throws java.io.IOException, javax.servlet.ServletException
  {
    this.viewAll();
  }
  public void update(mdss.general.SexMasterDTO dto) throws java.io.IOException, javax.servlet.ServletException
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
  public void failUpdate(mdss.general.SexMasterDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    req.setAttribute("item", dto);
    if(this.isAsynchronous())
    {
      req.getRequestDispatcher("WEB-INF/mdss/general/SexMaster/editComponent.jsp").forward(req, resp);
    }
    else
    {
      req.getRequestDispatcher("WEB-INF/mdss/general/SexMaster/edit.jsp").forward(req, resp);
    }
  }
}
