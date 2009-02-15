package mdss.entomology;

public class IdentificationMethodController extends IdentificationMethodControllerBase implements com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1234725413597L;
  
  public IdentificationMethodController(javax.servlet.http.HttpServletRequest req, javax.servlet.http.HttpServletResponse resp, java.lang.Boolean isAsynchronous)
  {
    super(req, resp, isAsynchronous);
  }
  
  public void delete(mdss.entomology.IdentificationMethodDTO dto) throws java.io.IOException, javax.servlet.ServletException
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
  public void failDelete(mdss.entomology.IdentificationMethodDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    req.setAttribute("item", dto);
    if(this.isAsynchronous())
    {
      req.getRequestDispatcher("WEB-INF/mdss/entomology/IdentificationMethod/editComponent.jsp").forward(req, resp);
    }
    else
    {
      req.getRequestDispatcher("WEB-INF/mdss/entomology/IdentificationMethod/edit.jsp").forward(req, resp);
    }
  }
  public void edit(java.lang.String id) throws java.io.IOException, javax.servlet.ServletException
  {
    mdss.entomology.IdentificationMethodDTO dto = mdss.entomology.IdentificationMethodDTO.lock(super.getClientRequest(), id);
    req.setAttribute("item", dto);
    if(this.isAsynchronous())
    {
      req.getRequestDispatcher("WEB-INF/mdss/entomology/IdentificationMethod/editComponent.jsp").forward(req, resp);
    }
    else
    {
      req.getRequestDispatcher("WEB-INF/mdss/entomology/IdentificationMethod/edit.jsp").forward(req, resp);
    }
  }
  public void failEdit(java.lang.String id) throws java.io.IOException, javax.servlet.ServletException
  {
    this.view(id);
  }
  public void newInstance() throws java.io.IOException, javax.servlet.ServletException
  {
    com.terraframe.mojo.constants.ClientRequestIF clientRequest = super.getClientRequest();
    mdss.entomology.IdentificationMethodDTO dto = new mdss.entomology.IdentificationMethodDTO(clientRequest);
    req.setAttribute("item", dto);
    if(this.isAsynchronous())
    {
      req.getRequestDispatcher("WEB-INF/mdss/entomology/IdentificationMethod/createComponent.jsp").forward(req, resp);
    }
    else
    {
      req.getRequestDispatcher("WEB-INF/mdss/entomology/IdentificationMethod/create.jsp").forward(req, resp);
    }
  }
  public void failNewInstance() throws java.io.IOException, javax.servlet.ServletException
  {
    this.viewAll();
  }
  public void update(mdss.entomology.IdentificationMethodDTO dto) throws java.io.IOException, javax.servlet.ServletException
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
  public void failUpdate(mdss.entomology.IdentificationMethodDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    req.setAttribute("item", dto);
    if(this.isAsynchronous())
    {
      req.getRequestDispatcher("WEB-INF/mdss/entomology/IdentificationMethod/editComponent.jsp").forward(req, resp);
    }
    else
    {
      req.getRequestDispatcher("WEB-INF/mdss/entomology/IdentificationMethod/edit.jsp").forward(req, resp);
    }
  }
  public void view(java.lang.String id) throws java.io.IOException, javax.servlet.ServletException
  {
    com.terraframe.mojo.constants.ClientRequestIF clientRequest = super.getClientRequest();
    req.setAttribute("item", mdss.entomology.IdentificationMethodDTO.get(clientRequest, id));
    if(this.isAsynchronous())
    {
      req.getRequestDispatcher("WEB-INF/mdss/entomology/IdentificationMethod/viewComponent.jsp").forward(req, resp);
    }
    else
    {
      req.getRequestDispatcher("WEB-INF/mdss/entomology/IdentificationMethod/view.jsp").forward(req, resp);
    }
  }
  public void failView(java.lang.String id) throws java.io.IOException, javax.servlet.ServletException
  {
    this.viewAll();
  }
  public void cancel(mdss.entomology.IdentificationMethodDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    dto.unlock();
    this.view(dto.getId());
  }
  public void failCancel(mdss.entomology.IdentificationMethodDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    resp.sendError(500);
  }
  public void create(mdss.entomology.IdentificationMethodDTO dto) throws java.io.IOException, javax.servlet.ServletException
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
  public void failCreate(mdss.entomology.IdentificationMethodDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    req.setAttribute("item", dto);
    if(this.isAsynchronous())
    {
      req.getRequestDispatcher("WEB-INF/mdss/entomology/IdentificationMethod/createComponent.jsp").forward(req, resp);
    }
    else
    {
      req.getRequestDispatcher("WEB-INF/mdss/entomology/IdentificationMethod/create.jsp").forward(req, resp);
    }
  }
  public void viewPage(java.lang.String sortAttribute, java.lang.Boolean isAscending, java.lang.Integer pageSize, java.lang.Integer pageNumber) throws java.io.IOException, javax.servlet.ServletException
  {
    com.terraframe.mojo.constants.ClientRequestIF clientRequest = super.getClientRequest();
    mdss.entomology.IdentificationMethodQueryDTO query = mdss.entomology.IdentificationMethodDTO.getAllInstances(clientRequest, sortAttribute, isAscending, pageSize, pageNumber);
    req.setAttribute("query", query);
    if(this.isAsynchronous())
    {
      req.getRequestDispatcher("WEB-INF/mdss/entomology/IdentificationMethod/viewAllComponent.jsp").forward(req, resp);
    }
    else
    {
      req.getRequestDispatcher("WEB-INF/mdss/entomology/IdentificationMethod/viewAll.jsp").forward(req, resp);
    }
  }
  public void failViewPage(java.lang.String sortAttribute, java.lang.String isAscending, java.lang.String pageSize, java.lang.String pageNumber) throws java.io.IOException, javax.servlet.ServletException
  {
    resp.sendError(500);
  }
  public void viewAll() throws java.io.IOException, javax.servlet.ServletException
  {
    com.terraframe.mojo.constants.ClientRequestIF clientRequest = super.getClientRequest();
    mdss.entomology.IdentificationMethodQueryDTO query = mdss.entomology.IdentificationMethodDTO.getAllInstances(clientRequest, null, true, 20, 1);
    req.setAttribute("query", query);
    if(this.isAsynchronous())
    {
      req.getRequestDispatcher("WEB-INF/mdss/entomology/IdentificationMethod/viewAllComponent.jsp").forward(req, resp);
    }
    else
    {
      req.getRequestDispatcher("WEB-INF/mdss/entomology/IdentificationMethod/viewAll.jsp").forward(req, resp);
    }
  }
  public void failViewAll() throws java.io.IOException, javax.servlet.ServletException
  {
    resp.sendError(500);
  }
}
