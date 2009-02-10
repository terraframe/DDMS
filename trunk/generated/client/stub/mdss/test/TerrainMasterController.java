package mdss.test;

public class TerrainMasterController extends TerrainMasterControllerBase implements com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1234288149611L;
  
  public TerrainMasterController(javax.servlet.http.HttpServletRequest req, javax.servlet.http.HttpServletResponse resp, java.lang.Boolean isAsynchronous)
  {
    super(req, resp, isAsynchronous);
  }
  
  public void create(mdss.test.TerrainMasterDTO dto) throws java.io.IOException, javax.servlet.ServletException
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
  public void failCreate(mdss.test.TerrainMasterDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    req.setAttribute("item", dto);
    if(this.isAsynchronous())
    {
      req.getRequestDispatcher("WEB-INF/mdss/test/TerrainMaster/createComponent.jsp").forward(req, resp);
    }
    else
    {
      req.getRequestDispatcher("WEB-INF/mdss/test/TerrainMaster/create.jsp").forward(req, resp);
    }
  }
  public void newInstance() throws java.io.IOException, javax.servlet.ServletException
  {
    com.terraframe.mojo.constants.ClientRequestIF clientRequest = super.getClientRequest();
    mdss.test.TerrainMasterDTO dto = new mdss.test.TerrainMasterDTO(clientRequest);
    req.setAttribute("item", dto);
    if(this.isAsynchronous())
    {
      req.getRequestDispatcher("WEB-INF/mdss/test/TerrainMaster/createComponent.jsp").forward(req, resp);
    }
    else
    {
      req.getRequestDispatcher("WEB-INF/mdss/test/TerrainMaster/create.jsp").forward(req, resp);
    }
  }
  public void failNewInstance() throws java.io.IOException, javax.servlet.ServletException
  {
    this.viewAll();
  }
  public void update(mdss.test.TerrainMasterDTO dto) throws java.io.IOException, javax.servlet.ServletException
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
  public void failUpdate(mdss.test.TerrainMasterDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    req.setAttribute("item", dto);
    if(this.isAsynchronous())
    {
      req.getRequestDispatcher("WEB-INF/mdss/test/TerrainMaster/editComponent.jsp").forward(req, resp);
    }
    else
    {
      req.getRequestDispatcher("WEB-INF/mdss/test/TerrainMaster/edit.jsp").forward(req, resp);
    }
  }
  public void delete(mdss.test.TerrainMasterDTO dto) throws java.io.IOException, javax.servlet.ServletException
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
  public void failDelete(mdss.test.TerrainMasterDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    req.setAttribute("item", dto);
    if(this.isAsynchronous())
    {
      req.getRequestDispatcher("WEB-INF/mdss/test/TerrainMaster/editComponent.jsp").forward(req, resp);
    }
    else
    {
      req.getRequestDispatcher("WEB-INF/mdss/test/TerrainMaster/edit.jsp").forward(req, resp);
    }
  }
  public void edit(java.lang.String id) throws java.io.IOException, javax.servlet.ServletException
  {
    mdss.test.TerrainMasterDTO dto = mdss.test.TerrainMasterDTO.lock(super.getClientRequest(), id);
    req.setAttribute("item", dto);
    if(this.isAsynchronous())
    {
      req.getRequestDispatcher("WEB-INF/mdss/test/TerrainMaster/editComponent.jsp").forward(req, resp);
    }
    else
    {
      req.getRequestDispatcher("WEB-INF/mdss/test/TerrainMaster/edit.jsp").forward(req, resp);
    }
  }
  public void failEdit(java.lang.String id) throws java.io.IOException, javax.servlet.ServletException
  {
    this.view(id);
  }
  public void view(java.lang.String id) throws java.io.IOException, javax.servlet.ServletException
  {
    com.terraframe.mojo.constants.ClientRequestIF clientRequest = super.getClientRequest();
    req.setAttribute("item", mdss.test.TerrainMasterDTO.get(clientRequest, id));
    if(this.isAsynchronous())
    {
      req.getRequestDispatcher("WEB-INF/mdss/test/TerrainMaster/viewComponent.jsp").forward(req, resp);
    }
    else
    {
      req.getRequestDispatcher("WEB-INF/mdss/test/TerrainMaster/view.jsp").forward(req, resp);
    }
  }
  public void failView(java.lang.String id) throws java.io.IOException, javax.servlet.ServletException
  {
    this.viewAll();
  }
  public void viewAll() throws java.io.IOException, javax.servlet.ServletException
  {
    com.terraframe.mojo.constants.ClientRequestIF clientRequest = super.getClientRequest();
    mdss.test.TerrainMasterQueryDTO query = mdss.test.TerrainMasterDTO.getAllInstances(clientRequest, null, true, 20, 1);
    req.setAttribute("query", query);
    if(this.isAsynchronous())
    {
      req.getRequestDispatcher("WEB-INF/mdss/test/TerrainMaster/viewAllComponent.jsp").forward(req, resp);
    }
    else
    {
      req.getRequestDispatcher("WEB-INF/mdss/test/TerrainMaster/viewAll.jsp").forward(req, resp);
    }
  }
  public void failViewAll() throws java.io.IOException, javax.servlet.ServletException
  {
    resp.sendError(500);
  }
  public void cancel(mdss.test.TerrainMasterDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    dto.unlock();
    this.view(dto.getId());
  }
  public void failCancel(mdss.test.TerrainMasterDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    resp.sendError(500);
  }
  public void viewPage(java.lang.String sortAttribute, java.lang.Boolean isAscending, java.lang.Integer pageSize, java.lang.Integer pageNumber) throws java.io.IOException, javax.servlet.ServletException
  {
    com.terraframe.mojo.constants.ClientRequestIF clientRequest = super.getClientRequest();
    mdss.test.TerrainMasterQueryDTO query = mdss.test.TerrainMasterDTO.getAllInstances(clientRequest, sortAttribute, isAscending, pageSize, pageNumber);
    req.setAttribute("query", query);
    if(this.isAsynchronous())
    {
      req.getRequestDispatcher("WEB-INF/mdss/test/TerrainMaster/viewAllComponent.jsp").forward(req, resp);
    }
    else
    {
      req.getRequestDispatcher("WEB-INF/mdss/test/TerrainMaster/viewAll.jsp").forward(req, resp);
    }
  }
  public void failViewPage(java.lang.String sortAttribute, java.lang.String isAscending, java.lang.String pageSize, java.lang.String pageNumber) throws java.io.IOException, javax.servlet.ServletException
  {
    resp.sendError(500);
  }
}
