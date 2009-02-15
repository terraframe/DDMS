package mdss.entomology.assay;

public class AdultDiscriminatingDoseAssayController extends AdultDiscriminatingDoseAssayControllerBase implements com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1234731978216L;
  
  public AdultDiscriminatingDoseAssayController(javax.servlet.http.HttpServletRequest req, javax.servlet.http.HttpServletResponse resp, java.lang.Boolean isAsynchronous)
  {
    super(req, resp, isAsynchronous);
  }
  
  public void viewPage(java.lang.String sortAttribute, java.lang.Boolean isAscending, java.lang.Integer pageSize, java.lang.Integer pageNumber) throws java.io.IOException, javax.servlet.ServletException
  {
    com.terraframe.mojo.constants.ClientRequestIF clientRequest = super.getClientRequest();
    mdss.entomology.assay.AdultDiscriminatingDoseAssayQueryDTO query = mdss.entomology.assay.AdultDiscriminatingDoseAssayDTO.getAllInstances(clientRequest, sortAttribute, isAscending, pageSize, pageNumber);
    req.setAttribute("query", query);
    if(this.isAsynchronous())
    {
      req.getRequestDispatcher("WEB-INF/mdss/entomology/assay/AdultDiscriminatingDoseAssay/viewAllComponent.jsp").forward(req, resp);
    }
    else
    {
      req.getRequestDispatcher("WEB-INF/mdss/entomology/assay/AdultDiscriminatingDoseAssay/viewAll.jsp").forward(req, resp);
    }
  }
  public void failViewPage(java.lang.String sortAttribute, java.lang.String isAscending, java.lang.String pageSize, java.lang.String pageNumber) throws java.io.IOException, javax.servlet.ServletException
  {
    resp.sendError(500);
  }
  public void viewAll() throws java.io.IOException, javax.servlet.ServletException
  {
    com.terraframe.mojo.constants.ClientRequestIF clientRequest = super.getClientRequest();
    mdss.entomology.assay.AdultDiscriminatingDoseAssayQueryDTO query = mdss.entomology.assay.AdultDiscriminatingDoseAssayDTO.getAllInstances(clientRequest, null, true, 20, 1);
    req.setAttribute("query", query);
    if(this.isAsynchronous())
    {
      req.getRequestDispatcher("WEB-INF/mdss/entomology/assay/AdultDiscriminatingDoseAssay/viewAllComponent.jsp").forward(req, resp);
    }
    else
    {
      req.getRequestDispatcher("WEB-INF/mdss/entomology/assay/AdultDiscriminatingDoseAssay/viewAll.jsp").forward(req, resp);
    }
  }
  public void failViewAll() throws java.io.IOException, javax.servlet.ServletException
  {
    resp.sendError(500);
  }
  public void view(java.lang.String id) throws java.io.IOException, javax.servlet.ServletException
  {
    com.terraframe.mojo.constants.ClientRequestIF clientRequest = super.getClientRequest();
    req.setAttribute("mdss_entomology_assay_AbstractAssay_collection", mdss.entomology.MosquitoCollectionDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("mdss_entomology_assay_AbstractAssay_identificationMethod", mdss.entomology.IdentificationMethodDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("mdss_entomology_assay_AbstractAssay_sex", mdss.entomology.AssaySexDTO.allItems(super.getClientSession().getRequest()));
    req.setAttribute("mdss_entomology_assay_AbstractAssay_specie", mdss.entomology.SpecieDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("mdss_entomology_assay_AbstractAssay_testMethod", mdss.entomology.AssayMethodDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("item", mdss.entomology.assay.AdultDiscriminatingDoseAssayDTO.get(clientRequest, id));
    if(this.isAsynchronous())
    {
      req.getRequestDispatcher("WEB-INF/mdss/entomology/assay/AdultDiscriminatingDoseAssay/viewComponent.jsp").forward(req, resp);
    }
    else
    {
      req.getRequestDispatcher("WEB-INF/mdss/entomology/assay/AdultDiscriminatingDoseAssay/view.jsp").forward(req, resp);
    }
  }
  public void failView(java.lang.String id) throws java.io.IOException, javax.servlet.ServletException
  {
    this.viewAll();
  }
  public void newInstance() throws java.io.IOException, javax.servlet.ServletException
  {
    com.terraframe.mojo.constants.ClientRequestIF clientRequest = super.getClientRequest();
    mdss.entomology.assay.AdultDiscriminatingDoseAssayDTO dto = new mdss.entomology.assay.AdultDiscriminatingDoseAssayDTO(clientRequest);
    req.setAttribute("mdss_entomology_assay_AbstractAssay_collection", mdss.entomology.MosquitoCollectionDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("mdss_entomology_assay_AbstractAssay_identificationMethod", mdss.entomology.IdentificationMethodDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("mdss_entomology_assay_AbstractAssay_sex", mdss.entomology.AssaySexDTO.allItems(super.getClientSession().getRequest()));
    req.setAttribute("mdss_entomology_assay_AbstractAssay_specie", mdss.entomology.SpecieDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("mdss_entomology_assay_AbstractAssay_testMethod", mdss.entomology.AssayMethodDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("item", dto);
    if(this.isAsynchronous())
    {
      req.getRequestDispatcher("WEB-INF/mdss/entomology/assay/AdultDiscriminatingDoseAssay/createComponent.jsp").forward(req, resp);
    }
    else
    {
      req.getRequestDispatcher("WEB-INF/mdss/entomology/assay/AdultDiscriminatingDoseAssay/create.jsp").forward(req, resp);
    }
  }
  public void failNewInstance() throws java.io.IOException, javax.servlet.ServletException
  {
    this.viewAll();
  }
  public void create(mdss.entomology.assay.AdultDiscriminatingDoseAssayDTO dto) throws java.io.IOException, javax.servlet.ServletException
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
  public void failCreate(mdss.entomology.assay.AdultDiscriminatingDoseAssayDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    req.setAttribute("mdss_entomology_assay_AbstractAssay_collection", mdss.entomology.MosquitoCollectionDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("mdss_entomology_assay_AbstractAssay_identificationMethod", mdss.entomology.IdentificationMethodDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("mdss_entomology_assay_AbstractAssay_sex", mdss.entomology.AssaySexDTO.allItems(super.getClientSession().getRequest()));
    req.setAttribute("mdss_entomology_assay_AbstractAssay_specie", mdss.entomology.SpecieDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("mdss_entomology_assay_AbstractAssay_testMethod", mdss.entomology.AssayMethodDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("item", dto);
    if(this.isAsynchronous())
    {
      req.getRequestDispatcher("WEB-INF/mdss/entomology/assay/AdultDiscriminatingDoseAssay/createComponent.jsp").forward(req, resp);
    }
    else
    {
      req.getRequestDispatcher("WEB-INF/mdss/entomology/assay/AdultDiscriminatingDoseAssay/create.jsp").forward(req, resp);
    }
  }
  public void edit(java.lang.String id) throws java.io.IOException, javax.servlet.ServletException
  {
    mdss.entomology.assay.AdultDiscriminatingDoseAssayDTO dto = mdss.entomology.assay.AdultDiscriminatingDoseAssayDTO.lock(super.getClientRequest(), id);
    req.setAttribute("mdss_entomology_assay_AbstractAssay_collection", mdss.entomology.MosquitoCollectionDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("mdss_entomology_assay_AbstractAssay_identificationMethod", mdss.entomology.IdentificationMethodDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("mdss_entomology_assay_AbstractAssay_sex", mdss.entomology.AssaySexDTO.allItems(super.getClientSession().getRequest()));
    req.setAttribute("mdss_entomology_assay_AbstractAssay_specie", mdss.entomology.SpecieDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("mdss_entomology_assay_AbstractAssay_testMethod", mdss.entomology.AssayMethodDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("item", dto);
    if(this.isAsynchronous())
    {
      req.getRequestDispatcher("WEB-INF/mdss/entomology/assay/AdultDiscriminatingDoseAssay/editComponent.jsp").forward(req, resp);
    }
    else
    {
      req.getRequestDispatcher("WEB-INF/mdss/entomology/assay/AdultDiscriminatingDoseAssay/edit.jsp").forward(req, resp);
    }
  }
  public void failEdit(java.lang.String id) throws java.io.IOException, javax.servlet.ServletException
  {
    this.view(id);
  }
  public void update(mdss.entomology.assay.AdultDiscriminatingDoseAssayDTO dto) throws java.io.IOException, javax.servlet.ServletException
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
  public void failUpdate(mdss.entomology.assay.AdultDiscriminatingDoseAssayDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    req.setAttribute("mdss_entomology_assay_AbstractAssay_collection", mdss.entomology.MosquitoCollectionDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("mdss_entomology_assay_AbstractAssay_identificationMethod", mdss.entomology.IdentificationMethodDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("mdss_entomology_assay_AbstractAssay_sex", mdss.entomology.AssaySexDTO.allItems(super.getClientSession().getRequest()));
    req.setAttribute("mdss_entomology_assay_AbstractAssay_specie", mdss.entomology.SpecieDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("mdss_entomology_assay_AbstractAssay_testMethod", mdss.entomology.AssayMethodDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("item", dto);
    if(this.isAsynchronous())
    {
      req.getRequestDispatcher("WEB-INF/mdss/entomology/assay/AdultDiscriminatingDoseAssay/editComponent.jsp").forward(req, resp);
    }
    else
    {
      req.getRequestDispatcher("WEB-INF/mdss/entomology/assay/AdultDiscriminatingDoseAssay/edit.jsp").forward(req, resp);
    }
  }
  public void cancel(mdss.entomology.assay.AdultDiscriminatingDoseAssayDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    dto.unlock();
    this.view(dto.getId());
  }
  public void failCancel(mdss.entomology.assay.AdultDiscriminatingDoseAssayDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    resp.sendError(500);
  }
  public void delete(mdss.entomology.assay.AdultDiscriminatingDoseAssayDTO dto) throws java.io.IOException, javax.servlet.ServletException
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
  public void failDelete(mdss.entomology.assay.AdultDiscriminatingDoseAssayDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    req.setAttribute("mdss_entomology_assay_AbstractAssay_collection", mdss.entomology.MosquitoCollectionDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("mdss_entomology_assay_AbstractAssay_identificationMethod", mdss.entomology.IdentificationMethodDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("mdss_entomology_assay_AbstractAssay_sex", mdss.entomology.AssaySexDTO.allItems(super.getClientSession().getRequest()));
    req.setAttribute("mdss_entomology_assay_AbstractAssay_specie", mdss.entomology.SpecieDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("mdss_entomology_assay_AbstractAssay_testMethod", mdss.entomology.AssayMethodDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("item", dto);
    if(this.isAsynchronous())
    {
      req.getRequestDispatcher("WEB-INF/mdss/entomology/assay/AdultDiscriminatingDoseAssay/editComponent.jsp").forward(req, resp);
    }
    else
    {
      req.getRequestDispatcher("WEB-INF/mdss/entomology/assay/AdultDiscriminatingDoseAssay/edit.jsp").forward(req, resp);
    }
  }
}
