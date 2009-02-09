package mdss.entomology;

public class TrueSpecieEntityController extends TrueSpecieEntityControllerBase implements com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1234203355616L;
  
  public TrueSpecieEntityController(javax.servlet.http.HttpServletRequest req, javax.servlet.http.HttpServletResponse resp)
  {
    super(req, resp);
  }
  
  public void create(mdss.entomology.TrueSpecieEntityDTO dto) throws java.io.IOException, javax.servlet.ServletException
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
  public void failCreate(mdss.entomology.TrueSpecieEntityDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    req.setAttribute("mdss_entomology_TrueSpecieEntity_collection", mdss.entomology.AbstractMosquitoCollectionDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("mdss_entomology_TrueSpecieEntity_identificationMethod", mdss.entomology.IndentificationMethodDTO.allItems(super.getClientSession().getRequest()));
    req.setAttribute("mdss_entomology_TrueSpecieEntity_specie", mdss.entomology.SpecieDTO.allItems(super.getClientSession().getRequest()));
    req.setAttribute("item", dto);
    req.getRequestDispatcher("WEB-INF/mdss/entomology/TrueSpecieEntity/create.jsp").forward(req, resp);
  }
  public void update(mdss.entomology.TrueSpecieEntityDTO dto) throws java.io.IOException, javax.servlet.ServletException
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
  public void failUpdate(mdss.entomology.TrueSpecieEntityDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    req.setAttribute("mdss_entomology_TrueSpecieEntity_collection", mdss.entomology.AbstractMosquitoCollectionDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("mdss_entomology_TrueSpecieEntity_identificationMethod", mdss.entomology.IndentificationMethodDTO.allItems(super.getClientSession().getRequest()));
    req.setAttribute("mdss_entomology_TrueSpecieEntity_specie", mdss.entomology.SpecieDTO.allItems(super.getClientSession().getRequest()));
    req.setAttribute("item", dto);
    req.getRequestDispatcher("WEB-INF/mdss/entomology/TrueSpecieEntity/edit.jsp").forward(req, resp);
  }
  public void viewAll() throws java.io.IOException, javax.servlet.ServletException
  {
    com.terraframe.mojo.constants.ClientRequestIF clientRequest = super.getClientRequest();
    mdss.entomology.TrueSpecieEntityQueryDTO query = mdss.entomology.TrueSpecieEntityDTO.getAllInstances(clientRequest, null, true, 20, 1);
    req.setAttribute("query", query);
    req.getRequestDispatcher("WEB-INF/mdss/entomology/TrueSpecieEntity/viewAll.jsp").forward(req, resp);
  }
  public void failViewAll() throws java.io.IOException, javax.servlet.ServletException
  {
    resp.sendError(500);
  }
  public void view(java.lang.String id) throws java.io.IOException, javax.servlet.ServletException
  {
    com.terraframe.mojo.constants.ClientRequestIF clientRequest = super.getClientRequest();
    req.setAttribute("mdss_entomology_TrueSpecieEntity_collection", mdss.entomology.AbstractMosquitoCollectionDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("mdss_entomology_TrueSpecieEntity_identificationMethod", mdss.entomology.IndentificationMethodDTO.allItems(super.getClientSession().getRequest()));
    req.setAttribute("mdss_entomology_TrueSpecieEntity_specie", mdss.entomology.SpecieDTO.allItems(super.getClientSession().getRequest()));
    req.setAttribute("item", mdss.entomology.TrueSpecieEntityDTO.get(clientRequest, id));
    req.getRequestDispatcher("WEB-INF/mdss/entomology/TrueSpecieEntity/view.jsp").forward(req, resp);
  }
  public void failView(java.lang.String id) throws java.io.IOException, javax.servlet.ServletException
  {
    this.viewAll();
  }
  public void cancel(mdss.entomology.TrueSpecieEntityDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    dto.unlock();
    this.view(dto.getId());
  }
  public void failCancel(mdss.entomology.TrueSpecieEntityDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    resp.sendError(500);
  }
  public void viewPage(java.lang.String sortAttribute, java.lang.Boolean isAscending, java.lang.Integer pageSize, java.lang.Integer pageNumber) throws java.io.IOException, javax.servlet.ServletException
  {
    com.terraframe.mojo.constants.ClientRequestIF clientRequest = super.getClientRequest();
    mdss.entomology.TrueSpecieEntityQueryDTO query = mdss.entomology.TrueSpecieEntityDTO.getAllInstances(clientRequest, sortAttribute, isAscending, pageSize, pageNumber);
    req.setAttribute("query", query);
    req.getRequestDispatcher("WEB-INF/mdss/entomology/TrueSpecieEntity/viewAll.jsp").forward(req, resp);
  }
  public void failViewPage(java.lang.String sortAttribute, java.lang.String isAscending, java.lang.String pageSize, java.lang.String pageNumber) throws java.io.IOException, javax.servlet.ServletException
  {
    resp.sendError(500);
  }
  public void edit(java.lang.String id) throws java.io.IOException, javax.servlet.ServletException
  {
    mdss.entomology.TrueSpecieEntityDTO dto = mdss.entomology.TrueSpecieEntityDTO.lock(super.getClientRequest(), id);
    req.setAttribute("mdss_entomology_TrueSpecieEntity_collection", mdss.entomology.AbstractMosquitoCollectionDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("mdss_entomology_TrueSpecieEntity_identificationMethod", mdss.entomology.IndentificationMethodDTO.allItems(super.getClientSession().getRequest()));
    req.setAttribute("mdss_entomology_TrueSpecieEntity_specie", mdss.entomology.SpecieDTO.allItems(super.getClientSession().getRequest()));
    req.setAttribute("item", dto);
    req.getRequestDispatcher("WEB-INF/mdss/entomology/TrueSpecieEntity/edit.jsp").forward(req, resp);
  }
  public void failEdit(java.lang.String id) throws java.io.IOException, javax.servlet.ServletException
  {
    this.view(id);
  }
  public void delete(mdss.entomology.TrueSpecieEntityDTO dto) throws java.io.IOException, javax.servlet.ServletException
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
  public void failDelete(mdss.entomology.TrueSpecieEntityDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    req.setAttribute("mdss_entomology_TrueSpecieEntity_collection", mdss.entomology.AbstractMosquitoCollectionDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("mdss_entomology_TrueSpecieEntity_identificationMethod", mdss.entomology.IndentificationMethodDTO.allItems(super.getClientSession().getRequest()));
    req.setAttribute("mdss_entomology_TrueSpecieEntity_specie", mdss.entomology.SpecieDTO.allItems(super.getClientSession().getRequest()));
    req.setAttribute("item", dto);
    req.getRequestDispatcher("WEB-INF/mdss/entomology/TrueSpecieEntity/edit.jsp").forward(req, resp);
  }
}
