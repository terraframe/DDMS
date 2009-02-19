package mdss.test;

public class GeoEntityController extends GeoEntityControllerBase implements com.terraframe.mojo.generation.loader.Reloadable
{
  public static final String JSP_DIR = "WEB-INF/mdss/test/GeoEntity/";
  public static final String LAYOUT = JSP_DIR + "layout.jsp";
  
  private static final long serialVersionUID = 1235073595864L;
  
  public GeoEntityController(javax.servlet.http.HttpServletRequest req, javax.servlet.http.HttpServletResponse resp, java.lang.Boolean isAsynchronous)
  {
    super(req, resp, isAsynchronous, JSP_DIR, LAYOUT);
  }
  
  public void edit(java.lang.String id) throws java.io.IOException, javax.servlet.ServletException
  {
    mdss.test.GeoEntityDTO dto = mdss.test.GeoEntityDTO.lock(super.getClientRequest(), id);
    req.setAttribute("mdss_test_GeoEntity_terrain", mdss.test.TerrainDTO.allItems(super.getClientSession().getRequest()));
    req.setAttribute("item", dto);
    req.setAttribute("page_title", "Edit GeoEntityController");
    render("editComponent.jsp");
  }
  public void failEdit(java.lang.String id) throws java.io.IOException, javax.servlet.ServletException
  {
    this.view(id);
  }
  public void viewPage(java.lang.String sortAttribute, java.lang.Boolean isAscending, java.lang.Integer pageSize, java.lang.Integer pageNumber) throws java.io.IOException, javax.servlet.ServletException
  {
    com.terraframe.mojo.constants.ClientRequestIF clientRequest = super.getClientRequest();
    mdss.test.GeoEntityQueryDTO query = mdss.test.GeoEntityDTO.getAllInstances(clientRequest, sortAttribute, isAscending, pageSize, pageNumber);
    req.setAttribute("query", query);
    req.setAttribute("page_title", "View All GeoEntityController Objects");
    render("viewAllComponent.jsp");
  }
  public void failViewPage(java.lang.String sortAttribute, java.lang.String isAscending, java.lang.String pageSize, java.lang.String pageNumber) throws java.io.IOException, javax.servlet.ServletException
  {
    resp.sendError(500);
  }
  public void update(mdss.test.GeoEntityDTO dto) throws java.io.IOException, javax.servlet.ServletException
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
  public void failUpdate(mdss.test.GeoEntityDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    req.setAttribute("mdss_test_GeoEntity_terrain", mdss.test.TerrainDTO.allItems(super.getClientSession().getRequest()));
    req.setAttribute("item", dto);
    req.setAttribute("page_title", "Update GeoEntityController");
    render("updateComponent.jsp");
  }
  public void view(java.lang.String id) throws java.io.IOException, javax.servlet.ServletException
  {
    com.terraframe.mojo.constants.ClientRequestIF clientRequest = super.getClientRequest();
    req.setAttribute("mdss_test_GeoEntity_terrain", mdss.test.TerrainDTO.allItems(super.getClientSession().getRequest()));
    req.setAttribute("item", mdss.test.GeoEntityDTO.get(clientRequest, id));
    req.setAttribute("page_title", "View GeoEntityController");
    render("viewComponent.jsp");
  }
  public void failView(java.lang.String id) throws java.io.IOException, javax.servlet.ServletException
  {
    this.viewAll();
  }
  public void create(mdss.test.GeoEntityDTO dto) throws java.io.IOException, javax.servlet.ServletException
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
  public void failCreate(mdss.test.GeoEntityDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    req.setAttribute("mdss_test_GeoEntity_terrain", mdss.test.TerrainDTO.allItems(super.getClientSession().getRequest()));
    req.setAttribute("item", dto);
    req.setAttribute("page_title", "Create GeoEntityController");
    render("createComponent.jsp");
  }
  public void newInstance() throws java.io.IOException, javax.servlet.ServletException
  {
    com.terraframe.mojo.constants.ClientRequestIF clientRequest = super.getClientRequest();
    mdss.test.GeoEntityDTO dto = new mdss.test.GeoEntityDTO(clientRequest);
    req.setAttribute("mdss_test_GeoEntity_terrain", mdss.test.TerrainDTO.allItems(super.getClientSession().getRequest()));
    req.setAttribute("item", dto);
    req.setAttribute("page_title", "Create GeoEntityController");
    render("createComponent.jsp");
  }
  public void failNewInstance() throws java.io.IOException, javax.servlet.ServletException
  {
    this.viewAll();
  }
  public void viewAll() throws java.io.IOException, javax.servlet.ServletException
  {
    com.terraframe.mojo.constants.ClientRequestIF clientRequest = super.getClientRequest();
    mdss.test.GeoEntityQueryDTO query = mdss.test.GeoEntityDTO.getAllInstances(clientRequest, null, true, 20, 1);
    req.setAttribute("query", query);
    req.setAttribute("page_title", "View All GeoEntityController Objects");
    render("viewAllComponent.jsp");
  }
  public void failViewAll() throws java.io.IOException, javax.servlet.ServletException
  {
    resp.sendError(500);
  }
  public void cancel(mdss.test.GeoEntityDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    dto.unlock();
    this.view(dto.getId());
  }
  public void failCancel(mdss.test.GeoEntityDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    resp.sendError(500);
  }
  public void delete(mdss.test.GeoEntityDTO dto) throws java.io.IOException, javax.servlet.ServletException
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
  public void failDelete(mdss.test.GeoEntityDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    req.setAttribute("mdss_test_GeoEntity_terrain", mdss.test.TerrainDTO.allItems(super.getClientSession().getRequest()));
    req.setAttribute("item", dto);
    req.setAttribute("page_title", "Edit GeoEntityController");
    render("editComponent.jsp");
  }
}
