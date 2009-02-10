package mdss.entomology;

public class AbstractMosquitoCollectionController extends AbstractMosquitoCollectionControllerBase implements com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1234288140012L;
  
  public AbstractMosquitoCollectionController(javax.servlet.http.HttpServletRequest req, javax.servlet.http.HttpServletResponse resp, java.lang.Boolean isAsynchronous)
  {
    super(req, resp, isAsynchronous);
  }
  
  public void create(mdss.entomology.AbstractMosquitoCollectionDTO dto) throws java.io.IOException, javax.servlet.ServletException
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
  public void failCreate(mdss.entomology.AbstractMosquitoCollectionDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    req.setAttribute("mdss_entomology_AbstractMosquitoCollection_geoEntity", mdss.test.GeoEntityDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("item", dto);
    if(this.isAsynchronous())
    {
      req.getRequestDispatcher("WEB-INF/mdss/entomology/AbstractMosquitoCollection/createComponent.jsp").forward(req, resp);
    }
    else
    {
      req.getRequestDispatcher("WEB-INF/mdss/entomology/AbstractMosquitoCollection/create.jsp").forward(req, resp);
    }
  }
  public void viewAll() throws java.io.IOException, javax.servlet.ServletException
  {
    com.terraframe.mojo.constants.ClientRequestIF clientRequest = super.getClientRequest();
    mdss.entomology.AbstractMosquitoCollectionQueryDTO query = mdss.entomology.AbstractMosquitoCollectionDTO.getAllInstances(clientRequest, null, true, 20, 1);
    req.setAttribute("query", query);
    if(this.isAsynchronous())
    {
      req.getRequestDispatcher("WEB-INF/mdss/entomology/AbstractMosquitoCollection/viewAllComponent.jsp").forward(req, resp);
    }
    else
    {
      req.getRequestDispatcher("WEB-INF/mdss/entomology/AbstractMosquitoCollection/viewAll.jsp").forward(req, resp);
    }
  }
  public void failViewAll() throws java.io.IOException, javax.servlet.ServletException
  {
    resp.sendError(500);
  }
  public void update(mdss.entomology.AbstractMosquitoCollectionDTO dto) throws java.io.IOException, javax.servlet.ServletException
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
  public void failUpdate(mdss.entomology.AbstractMosquitoCollectionDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    req.setAttribute("mdss_entomology_AbstractMosquitoCollection_geoEntity", mdss.test.GeoEntityDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("item", dto);
    if(this.isAsynchronous())
    {
      req.getRequestDispatcher("WEB-INF/mdss/entomology/AbstractMosquitoCollection/editComponent.jsp").forward(req, resp);
    }
    else
    {
      req.getRequestDispatcher("WEB-INF/mdss/entomology/AbstractMosquitoCollection/edit.jsp").forward(req, resp);
    }
  }
  public void view(java.lang.String id) throws java.io.IOException, javax.servlet.ServletException
  {
    com.terraframe.mojo.constants.ClientRequestIF clientRequest = super.getClientRequest();
    req.setAttribute("mdss_entomology_AbstractMosquitoCollection_geoEntity", mdss.test.GeoEntityDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("item", mdss.entomology.AbstractMosquitoCollectionDTO.get(clientRequest, id));
    if(this.isAsynchronous())
    {
      req.getRequestDispatcher("WEB-INF/mdss/entomology/AbstractMosquitoCollection/viewComponent.jsp").forward(req, resp);
    }
    else
    {
      req.getRequestDispatcher("WEB-INF/mdss/entomology/AbstractMosquitoCollection/view.jsp").forward(req, resp);
    }
  }
  public void failView(java.lang.String id) throws java.io.IOException, javax.servlet.ServletException
  {
    this.viewAll();
  }
  public void edit(java.lang.String id) throws java.io.IOException, javax.servlet.ServletException
  {
    mdss.entomology.AbstractMosquitoCollectionDTO dto = mdss.entomology.AbstractMosquitoCollectionDTO.lock(super.getClientRequest(), id);
    req.setAttribute("mdss_entomology_AbstractMosquitoCollection_geoEntity", mdss.test.GeoEntityDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("item", dto);
    if(this.isAsynchronous())
    {
      req.getRequestDispatcher("WEB-INF/mdss/entomology/AbstractMosquitoCollection/editComponent.jsp").forward(req, resp);
    }
    else
    {
      req.getRequestDispatcher("WEB-INF/mdss/entomology/AbstractMosquitoCollection/edit.jsp").forward(req, resp);
    }
  }
  public void failEdit(java.lang.String id) throws java.io.IOException, javax.servlet.ServletException
  {
    this.view(id);
  }
  public void cancel(mdss.entomology.AbstractMosquitoCollectionDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    dto.unlock();
    this.view(dto.getId());
  }
  public void failCancel(mdss.entomology.AbstractMosquitoCollectionDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    resp.sendError(500);
  }
  public void viewPage(java.lang.String sortAttribute, java.lang.Boolean isAscending, java.lang.Integer pageSize, java.lang.Integer pageNumber) throws java.io.IOException, javax.servlet.ServletException
  {
    com.terraframe.mojo.constants.ClientRequestIF clientRequest = super.getClientRequest();
    mdss.entomology.AbstractMosquitoCollectionQueryDTO query = mdss.entomology.AbstractMosquitoCollectionDTO.getAllInstances(clientRequest, sortAttribute, isAscending, pageSize, pageNumber);
    req.setAttribute("query", query);
    if(this.isAsynchronous())
    {
      req.getRequestDispatcher("WEB-INF/mdss/entomology/AbstractMosquitoCollection/viewAllComponent.jsp").forward(req, resp);
    }
    else
    {
      req.getRequestDispatcher("WEB-INF/mdss/entomology/AbstractMosquitoCollection/viewAll.jsp").forward(req, resp);
    }
  }
  public void failViewPage(java.lang.String sortAttribute, java.lang.String isAscending, java.lang.String pageSize, java.lang.String pageNumber) throws java.io.IOException, javax.servlet.ServletException
  {
    resp.sendError(500);
  }
  public void delete(mdss.entomology.AbstractMosquitoCollectionDTO dto) throws java.io.IOException, javax.servlet.ServletException
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
  public void failDelete(mdss.entomology.AbstractMosquitoCollectionDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    req.setAttribute("mdss_entomology_AbstractMosquitoCollection_geoEntity", mdss.test.GeoEntityDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("item", dto);
    if(this.isAsynchronous())
    {
      req.getRequestDispatcher("WEB-INF/mdss/entomology/AbstractMosquitoCollection/editComponent.jsp").forward(req, resp);
    }
    else
    {
      req.getRequestDispatcher("WEB-INF/mdss/entomology/AbstractMosquitoCollection/edit.jsp").forward(req, resp);
    }
  }
}
