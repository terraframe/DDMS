package mdss.entomology;

public class IndentificationMethodMasterController extends IndentificationMethodMasterControllerBase implements com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1234203358165L;
  
  public IndentificationMethodMasterController(javax.servlet.http.HttpServletRequest req, javax.servlet.http.HttpServletResponse resp)
  {
    super(req, resp);
  }
  
  public void create(mdss.entomology.IndentificationMethodMasterDTO dto) throws java.io.IOException, javax.servlet.ServletException
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
  public void failCreate(mdss.entomology.IndentificationMethodMasterDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    req.setAttribute("item", dto);
    req.getRequestDispatcher("WEB-INF/mdss/entomology/IndentificationMethodMaster/create.jsp").forward(req, resp);
  }
  public void update(mdss.entomology.IndentificationMethodMasterDTO dto) throws java.io.IOException, javax.servlet.ServletException
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
  public void failUpdate(mdss.entomology.IndentificationMethodMasterDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    req.setAttribute("item", dto);
    req.getRequestDispatcher("WEB-INF/mdss/entomology/IndentificationMethodMaster/edit.jsp").forward(req, resp);
  }
  public void viewPage(java.lang.String sortAttribute, java.lang.Boolean isAscending, java.lang.Integer pageSize, java.lang.Integer pageNumber) throws java.io.IOException, javax.servlet.ServletException
  {
    com.terraframe.mojo.constants.ClientRequestIF clientRequest = super.getClientRequest();
    mdss.entomology.IndentificationMethodMasterQueryDTO query = mdss.entomology.IndentificationMethodMasterDTO.getAllInstances(clientRequest, sortAttribute, isAscending, pageSize, pageNumber);
    req.setAttribute("query", query);
    req.getRequestDispatcher("WEB-INF/mdss/entomology/IndentificationMethodMaster/viewAll.jsp").forward(req, resp);
  }
  public void failViewPage(java.lang.String sortAttribute, java.lang.String isAscending, java.lang.String pageSize, java.lang.String pageNumber) throws java.io.IOException, javax.servlet.ServletException
  {
    resp.sendError(500);
  }
  public void viewAll() throws java.io.IOException, javax.servlet.ServletException
  {
    com.terraframe.mojo.constants.ClientRequestIF clientRequest = super.getClientRequest();
    mdss.entomology.IndentificationMethodMasterQueryDTO query = mdss.entomology.IndentificationMethodMasterDTO.getAllInstances(clientRequest, null, true, 20, 1);
    req.setAttribute("query", query);
    req.getRequestDispatcher("WEB-INF/mdss/entomology/IndentificationMethodMaster/viewAll.jsp").forward(req, resp);
  }
  public void failViewAll() throws java.io.IOException, javax.servlet.ServletException
  {
    resp.sendError(500);
  }
  public void newInstance() throws java.io.IOException, javax.servlet.ServletException
  {
    com.terraframe.mojo.constants.ClientRequestIF clientRequest = super.getClientRequest();
    mdss.entomology.IndentificationMethodMasterDTO dto = new mdss.entomology.IndentificationMethodMasterDTO(clientRequest);
    req.setAttribute("item", dto);
    req.getRequestDispatcher("WEB-INF/mdss/entomology/IndentificationMethodMaster/create.jsp").forward(req, resp);
  }
  public void failNewInstance() throws java.io.IOException, javax.servlet.ServletException
  {
    this.viewAll();
  }
  public void cancel(mdss.entomology.IndentificationMethodMasterDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    dto.unlock();
    this.view(dto.getId());
  }
  public void failCancel(mdss.entomology.IndentificationMethodMasterDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    resp.sendError(500);
  }
  public void delete(mdss.entomology.IndentificationMethodMasterDTO dto) throws java.io.IOException, javax.servlet.ServletException
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
  public void failDelete(mdss.entomology.IndentificationMethodMasterDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    req.setAttribute("item", dto);
    req.getRequestDispatcher("WEB-INF/mdss/entomology/IndentificationMethodMaster/edit.jsp").forward(req, resp);
  }
  public void view(java.lang.String id) throws java.io.IOException, javax.servlet.ServletException
  {
    com.terraframe.mojo.constants.ClientRequestIF clientRequest = super.getClientRequest();
    req.setAttribute("item", mdss.entomology.IndentificationMethodMasterDTO.get(clientRequest, id));
    req.getRequestDispatcher("WEB-INF/mdss/entomology/IndentificationMethodMaster/view.jsp").forward(req, resp);
  }
  public void failView(java.lang.String id) throws java.io.IOException, javax.servlet.ServletException
  {
    this.viewAll();
  }
  public void edit(java.lang.String id) throws java.io.IOException, javax.servlet.ServletException
  {
    mdss.entomology.IndentificationMethodMasterDTO dto = mdss.entomology.IndentificationMethodMasterDTO.lock(super.getClientRequest(), id);
    req.setAttribute("item", dto);
    req.getRequestDispatcher("WEB-INF/mdss/entomology/IndentificationMethodMaster/edit.jsp").forward(req, resp);
  }
  public void failEdit(java.lang.String id) throws java.io.IOException, javax.servlet.ServletException
  {
    this.view(id);
  }
}
