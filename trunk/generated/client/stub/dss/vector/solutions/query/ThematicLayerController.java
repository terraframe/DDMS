package dss.vector.solutions.query;

public class ThematicLayerController extends ThematicLayerControllerBase implements com.terraframe.mojo.generation.loader.Reloadable
{
  public static final String JSP_DIR = "WEB-INF/dss/vector/solutions/query/ThematicLayer/";
  public static final String LAYOUT = "/layout.jsp";
  
  private static final long serialVersionUID = 1241158165413L;
  
  public ThematicLayerController(javax.servlet.http.HttpServletRequest req, javax.servlet.http.HttpServletResponse resp, java.lang.Boolean isAsynchronous)
  {
    super(req, resp, isAsynchronous, JSP_DIR, LAYOUT);
  }
  
  public void create(dss.vector.solutions.query.ThematicLayerDTO dto) throws java.io.IOException, javax.servlet.ServletException
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
  public void failCreate(dss.vector.solutions.query.ThematicLayerDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    req.setAttribute("dss_vector_solutions_query_Layer_geoHierarchy", dss.vector.solutions.geo.GeoHierarchyDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("dss_vector_solutions_query_Layer_geometryStyle", dss.vector.solutions.query.GeometryStyleDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("dss_vector_solutions_query_Layer_textStyle", dss.vector.solutions.query.TextStyleDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("item", dto);
    req.setAttribute("page_title", "Create ThematicLayerController");
    render("createComponent.jsp");
  }
  public void delete(dss.vector.solutions.query.ThematicLayerDTO dto) throws java.io.IOException, javax.servlet.ServletException
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
  public void failDelete(dss.vector.solutions.query.ThematicLayerDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    req.setAttribute("dss_vector_solutions_query_Layer_geoHierarchy", dss.vector.solutions.geo.GeoHierarchyDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("dss_vector_solutions_query_Layer_geometryStyle", dss.vector.solutions.query.GeometryStyleDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("dss_vector_solutions_query_Layer_textStyle", dss.vector.solutions.query.TextStyleDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("item", dto);
    req.setAttribute("page_title", "Edit ThematicLayerController");
    render("editComponent.jsp");
  }
  public void viewPage(java.lang.String sortAttribute, java.lang.Boolean isAscending, java.lang.Integer pageSize, java.lang.Integer pageNumber) throws java.io.IOException, javax.servlet.ServletException
  {
    com.terraframe.mojo.constants.ClientRequestIF clientRequest = super.getClientRequest();
    dss.vector.solutions.query.ThematicLayerQueryDTO query = dss.vector.solutions.query.ThematicLayerDTO.getAllInstances(clientRequest, sortAttribute, isAscending, pageSize, pageNumber);
    req.setAttribute("query", query);
    req.setAttribute("page_title", "View All ThematicLayerController Objects");
    render("viewAllComponent.jsp");
  }
  public void failViewPage(java.lang.String sortAttribute, java.lang.String isAscending, java.lang.String pageSize, java.lang.String pageNumber) throws java.io.IOException, javax.servlet.ServletException
  {
    resp.sendError(500);
  }
  public void edit(java.lang.String id) throws java.io.IOException, javax.servlet.ServletException
  {
    dss.vector.solutions.query.ThematicLayerDTO dto = dss.vector.solutions.query.ThematicLayerDTO.lock(super.getClientRequest(), id);
    req.setAttribute("dss_vector_solutions_query_Layer_geoHierarchy", dss.vector.solutions.geo.GeoHierarchyDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("dss_vector_solutions_query_Layer_geometryStyle", dss.vector.solutions.query.GeometryStyleDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("dss_vector_solutions_query_Layer_textStyle", dss.vector.solutions.query.TextStyleDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("item", dto);
    req.setAttribute("page_title", "Edit ThematicLayerController");
    render("editComponent.jsp");
  }
  public void failEdit(java.lang.String id) throws java.io.IOException, javax.servlet.ServletException
  {
    this.view(id);
  }
  public void update(dss.vector.solutions.query.ThematicLayerDTO dto) throws java.io.IOException, javax.servlet.ServletException
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
  public void failUpdate(dss.vector.solutions.query.ThematicLayerDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    req.setAttribute("dss_vector_solutions_query_Layer_geoHierarchy", dss.vector.solutions.geo.GeoHierarchyDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("dss_vector_solutions_query_Layer_geometryStyle", dss.vector.solutions.query.GeometryStyleDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("dss_vector_solutions_query_Layer_textStyle", dss.vector.solutions.query.TextStyleDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("item", dto);
    req.setAttribute("page_title", "Update ThematicLayerController");
    render("editComponent.jsp");
  }
  public void cancel(dss.vector.solutions.query.ThematicLayerDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    dto.unlock();
    this.view(dto.getId());
  }
  public void failCancel(dss.vector.solutions.query.ThematicLayerDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    resp.sendError(500);
  }
  public void viewAll() throws java.io.IOException, javax.servlet.ServletException
  {
    com.terraframe.mojo.constants.ClientRequestIF clientRequest = super.getClientRequest();
    dss.vector.solutions.query.ThematicLayerQueryDTO query = dss.vector.solutions.query.ThematicLayerDTO.getAllInstances(clientRequest, null, true, 20, 1);
    req.setAttribute("query", query);
    req.setAttribute("page_title", "View All ThematicLayerController Objects");
    render("viewAllComponent.jsp");
  }
  public void failViewAll() throws java.io.IOException, javax.servlet.ServletException
  {
    resp.sendError(500);
  }
  public void newInstance() throws java.io.IOException, javax.servlet.ServletException
  {
    com.terraframe.mojo.constants.ClientRequestIF clientRequest = super.getClientRequest();
    dss.vector.solutions.query.ThematicLayerDTO dto = new dss.vector.solutions.query.ThematicLayerDTO(clientRequest);
    req.setAttribute("dss_vector_solutions_query_Layer_geoHierarchy", dss.vector.solutions.geo.GeoHierarchyDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("dss_vector_solutions_query_Layer_geometryStyle", dss.vector.solutions.query.GeometryStyleDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("dss_vector_solutions_query_Layer_textStyle", dss.vector.solutions.query.TextStyleDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("item", dto);
    req.setAttribute("page_title", "Create ThematicLayerController");
    render("createComponent.jsp");
  }
  public void failNewInstance() throws java.io.IOException, javax.servlet.ServletException
  {
    this.viewAll();
  }
  public void view(java.lang.String id) throws java.io.IOException, javax.servlet.ServletException
  {
    com.terraframe.mojo.constants.ClientRequestIF clientRequest = super.getClientRequest();
    req.setAttribute("dss_vector_solutions_query_Layer_geoHierarchy", dss.vector.solutions.geo.GeoHierarchyDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("dss_vector_solutions_query_Layer_geometryStyle", dss.vector.solutions.query.GeometryStyleDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("dss_vector_solutions_query_Layer_textStyle", dss.vector.solutions.query.TextStyleDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("item", dss.vector.solutions.query.ThematicLayerDTO.get(clientRequest, id));
    req.setAttribute("page_title", "View ThematicLayerController");
    render("viewComponent.jsp");
  }
  public void failView(java.lang.String id) throws java.io.IOException, javax.servlet.ServletException
  {
    this.viewAll();
  }
}
