package mdss.entomology;

public class UninterestingSpecieGroupController extends UninterestingSpecieGroupControllerBase implements com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1234288140468L;
  
  public UninterestingSpecieGroupController(javax.servlet.http.HttpServletRequest req, javax.servlet.http.HttpServletResponse resp, java.lang.Boolean isAsynchronous)
  {
    super(req, resp, isAsynchronous);
  }
  
  public void edit(java.lang.String id) throws java.io.IOException, javax.servlet.ServletException
  {
    mdss.entomology.UninterestingSpecieGroupDTO dto = mdss.entomology.UninterestingSpecieGroupDTO.lock(super.getClientRequest(), id);
    req.setAttribute("mdss_entomology_TrueSpecieEntity_collection", mdss.entomology.AbstractMosquitoCollectionDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("mdss_entomology_TrueSpecieEntity_identificationMethod", mdss.entomology.IdentificationMethodDTO.allItems(super.getClientSession().getRequest()));
    req.setAttribute("mdss_entomology_TrueSpecieEntity_specie", mdss.entomology.SpecieDTO.allItems(super.getClientSession().getRequest()));
    req.setAttribute("item", dto);
    if(this.isAsynchronous())
    {
      req.getRequestDispatcher("WEB-INF/mdss/entomology/UninterestingSpecieGroup/editComponent.jsp").forward(req, resp);
    }
    else
    {
      req.getRequestDispatcher("WEB-INF/mdss/entomology/UninterestingSpecieGroup/edit.jsp").forward(req, resp);
    }
  }
  public void failEdit(java.lang.String id) throws java.io.IOException, javax.servlet.ServletException
  {
    this.view(id);
  }
  public void view(java.lang.String id) throws java.io.IOException, javax.servlet.ServletException
  {
    com.terraframe.mojo.constants.ClientRequestIF clientRequest = super.getClientRequest();
    req.setAttribute("mdss_entomology_TrueSpecieEntity_collection", mdss.entomology.AbstractMosquitoCollectionDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("mdss_entomology_TrueSpecieEntity_identificationMethod", mdss.entomology.IdentificationMethodDTO.allItems(super.getClientSession().getRequest()));
    req.setAttribute("mdss_entomology_TrueSpecieEntity_specie", mdss.entomology.SpecieDTO.allItems(super.getClientSession().getRequest()));
    req.setAttribute("item", mdss.entomology.UninterestingSpecieGroupDTO.get(clientRequest, id));
    if(this.isAsynchronous())
    {
      req.getRequestDispatcher("WEB-INF/mdss/entomology/UninterestingSpecieGroup/viewComponent.jsp").forward(req, resp);
    }
    else
    {
      req.getRequestDispatcher("WEB-INF/mdss/entomology/UninterestingSpecieGroup/view.jsp").forward(req, resp);
    }
  }
  public void failView(java.lang.String id) throws java.io.IOException, javax.servlet.ServletException
  {
    this.viewAll();
  }
  public void newInstance() throws java.io.IOException, javax.servlet.ServletException
  {
    com.terraframe.mojo.constants.ClientRequestIF clientRequest = super.getClientRequest();
    mdss.entomology.UninterestingSpecieGroupDTO dto = new mdss.entomology.UninterestingSpecieGroupDTO(clientRequest);
    req.setAttribute("mdss_entomology_TrueSpecieEntity_collection", mdss.entomology.AbstractMosquitoCollectionDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("mdss_entomology_TrueSpecieEntity_identificationMethod", mdss.entomology.IdentificationMethodDTO.allItems(super.getClientSession().getRequest()));
    req.setAttribute("mdss_entomology_TrueSpecieEntity_specie", mdss.entomology.SpecieDTO.allItems(super.getClientSession().getRequest()));
    req.setAttribute("item", dto);
    if(this.isAsynchronous())
    {
      req.getRequestDispatcher("WEB-INF/mdss/entomology/UninterestingSpecieGroup/createComponent.jsp").forward(req, resp);
    }
    else
    {
      req.getRequestDispatcher("WEB-INF/mdss/entomology/UninterestingSpecieGroup/create.jsp").forward(req, resp);
    }
  }
  public void failNewInstance() throws java.io.IOException, javax.servlet.ServletException
  {
    this.viewAll();
  }
  public void update(mdss.entomology.UninterestingSpecieGroupDTO dto) throws java.io.IOException, javax.servlet.ServletException
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
  public void failUpdate(mdss.entomology.UninterestingSpecieGroupDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    req.setAttribute("mdss_entomology_TrueSpecieEntity_collection", mdss.entomology.AbstractMosquitoCollectionDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("mdss_entomology_TrueSpecieEntity_identificationMethod", mdss.entomology.IdentificationMethodDTO.allItems(super.getClientSession().getRequest()));
    req.setAttribute("mdss_entomology_TrueSpecieEntity_specie", mdss.entomology.SpecieDTO.allItems(super.getClientSession().getRequest()));
    req.setAttribute("item", dto);
    if(this.isAsynchronous())
    {
      req.getRequestDispatcher("WEB-INF/mdss/entomology/UninterestingSpecieGroup/editComponent.jsp").forward(req, resp);
    }
    else
    {
      req.getRequestDispatcher("WEB-INF/mdss/entomology/UninterestingSpecieGroup/edit.jsp").forward(req, resp);
    }
  }
  public void viewPage(java.lang.String sortAttribute, java.lang.Boolean isAscending, java.lang.Integer pageSize, java.lang.Integer pageNumber) throws java.io.IOException, javax.servlet.ServletException
  {
    com.terraframe.mojo.constants.ClientRequestIF clientRequest = super.getClientRequest();
    mdss.entomology.UninterestingSpecieGroupQueryDTO query = mdss.entomology.UninterestingSpecieGroupDTO.getAllInstances(clientRequest, sortAttribute, isAscending, pageSize, pageNumber);
    req.setAttribute("query", query);
    if(this.isAsynchronous())
    {
      req.getRequestDispatcher("WEB-INF/mdss/entomology/UninterestingSpecieGroup/viewAllComponent.jsp").forward(req, resp);
    }
    else
    {
      req.getRequestDispatcher("WEB-INF/mdss/entomology/UninterestingSpecieGroup/viewAll.jsp").forward(req, resp);
    }
  }
  public void failViewPage(java.lang.String sortAttribute, java.lang.String isAscending, java.lang.String pageSize, java.lang.String pageNumber) throws java.io.IOException, javax.servlet.ServletException
  {
    resp.sendError(500);
  }
  public void viewAll() throws java.io.IOException, javax.servlet.ServletException
  {
    com.terraframe.mojo.constants.ClientRequestIF clientRequest = super.getClientRequest();
    mdss.entomology.UninterestingSpecieGroupQueryDTO query = mdss.entomology.UninterestingSpecieGroupDTO.getAllInstances(clientRequest, null, true, 20, 1);
    req.setAttribute("query", query);
    if(this.isAsynchronous())
    {
      req.getRequestDispatcher("WEB-INF/mdss/entomology/UninterestingSpecieGroup/viewAllComponent.jsp").forward(req, resp);
    }
    else
    {
      req.getRequestDispatcher("WEB-INF/mdss/entomology/UninterestingSpecieGroup/viewAll.jsp").forward(req, resp);
    }
  }
  public void failViewAll() throws java.io.IOException, javax.servlet.ServletException
  {
    resp.sendError(500);
  }
  public void cancel(mdss.entomology.UninterestingSpecieGroupDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    dto.unlock();
    this.view(dto.getId());
  }
  public void failCancel(mdss.entomology.UninterestingSpecieGroupDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    resp.sendError(500);
  }
  public void create(mdss.entomology.UninterestingSpecieGroupDTO dto) throws java.io.IOException, javax.servlet.ServletException
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
  public void failCreate(mdss.entomology.UninterestingSpecieGroupDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    req.setAttribute("mdss_entomology_TrueSpecieEntity_collection", mdss.entomology.AbstractMosquitoCollectionDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("mdss_entomology_TrueSpecieEntity_identificationMethod", mdss.entomology.IdentificationMethodDTO.allItems(super.getClientSession().getRequest()));
    req.setAttribute("mdss_entomology_TrueSpecieEntity_specie", mdss.entomology.SpecieDTO.allItems(super.getClientSession().getRequest()));
    req.setAttribute("item", dto);
    if(this.isAsynchronous())
    {
      req.getRequestDispatcher("WEB-INF/mdss/entomology/UninterestingSpecieGroup/createComponent.jsp").forward(req, resp);
    }
    else
    {
      req.getRequestDispatcher("WEB-INF/mdss/entomology/UninterestingSpecieGroup/create.jsp").forward(req, resp);
    }
  }
  public void delete(mdss.entomology.UninterestingSpecieGroupDTO dto) throws java.io.IOException, javax.servlet.ServletException
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
  public void failDelete(mdss.entomology.UninterestingSpecieGroupDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    req.setAttribute("mdss_entomology_TrueSpecieEntity_collection", mdss.entomology.AbstractMosquitoCollectionDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("mdss_entomology_TrueSpecieEntity_identificationMethod", mdss.entomology.IdentificationMethodDTO.allItems(super.getClientSession().getRequest()));
    req.setAttribute("mdss_entomology_TrueSpecieEntity_specie", mdss.entomology.SpecieDTO.allItems(super.getClientSession().getRequest()));
    req.setAttribute("item", dto);
    if(this.isAsynchronous())
    {
      req.getRequestDispatcher("WEB-INF/mdss/entomology/UninterestingSpecieGroup/editComponent.jsp").forward(req, resp);
    }
    else
    {
      req.getRequestDispatcher("WEB-INF/mdss/entomology/UninterestingSpecieGroup/edit.jsp").forward(req, resp);
    }
  }
}
