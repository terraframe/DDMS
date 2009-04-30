package dss.vector.solutions.query;

import com.terraframe.mojo.ProblemExceptionDTO;
import com.terraframe.mojo.web.json.JSONMojoExceptionDTO;
import com.terraframe.mojo.web.json.JSONProblemExceptionDTO;

import dss.vector.solutions.sld.SLDWriter;

public class LayerController extends LayerControllerBase implements
    com.terraframe.mojo.generation.loader.Reloadable
{
  public static final String JSP_DIR          = "WEB-INF/dss/vector/solutions/query/Layer/";

  public static final String LAYOUT           = JSP_DIR + "layout.jsp";

  public static final String EDIT_SUMMARY     = JSP_DIR + "editSummary.jsp";

  private static final long  serialVersionUID = 1240900964253L;

  public LayerController(javax.servlet.http.HttpServletRequest req,
      javax.servlet.http.HttpServletResponse resp, java.lang.Boolean isAsynchronous)
  {
    super(req, resp, isAsynchronous, JSP_DIR, LAYOUT);
  }

  public void delete(dss.vector.solutions.query.LayerDTO dto) throws java.io.IOException,
      javax.servlet.ServletException
  {
    try
    {
      dto.delete();
      this.viewAll();
    }
    catch (com.terraframe.mojo.ProblemExceptionDTO e)
    {
      this.failDelete(dto);
    }
  }

  public void failDelete(dss.vector.solutions.query.LayerDTO dto) throws java.io.IOException,
      javax.servlet.ServletException
  {
    req.setAttribute("dss_vector_solutions_query_Layer_geoHierarchy",
        dss.vector.solutions.geo.GeoHierarchyDTO.getAllInstances(super.getClientSession().getRequest(),
            "keyName", true, 0, 0).getResultSet());
    req.setAttribute("dss_vector_solutions_query_Layer_geometryStyle",
        dss.vector.solutions.query.GeometryStyleDTO.getAllInstances(
            super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("dss_vector_solutions_query_Layer_textStyle",
        dss.vector.solutions.query.TextStyleDTO.getAllInstances(super.getClientSession().getRequest(),
            "keyName", true, 0, 0).getResultSet());
    req.setAttribute("item", dto);
    req.setAttribute("page_title", "Edit LayerController");
    render("editComponent.jsp");
  }

  public void viewPage(java.lang.String sortAttribute, java.lang.Boolean isAscending,
      java.lang.Integer pageSize, java.lang.Integer pageNumber) throws java.io.IOException,
      javax.servlet.ServletException
  {
    com.terraframe.mojo.constants.ClientRequestIF clientRequest = super.getClientRequest();
    dss.vector.solutions.query.LayerQueryDTO query = dss.vector.solutions.query.LayerDTO
        .getAllInstances(clientRequest, sortAttribute, isAscending, pageSize, pageNumber);
    req.setAttribute("query", query);
    req.setAttribute("page_title", "View All LayerController Objects");
    render("viewAllComponent.jsp");
  }

  public void failViewPage(java.lang.String sortAttribute, java.lang.String isAscending,
      java.lang.String pageSize, java.lang.String pageNumber) throws java.io.IOException,
      javax.servlet.ServletException
  {
    resp.sendError(500);
  }

  public void newInstance() throws java.io.IOException, javax.servlet.ServletException
  {
    com.terraframe.mojo.constants.ClientRequestIF clientRequest = super.getClientRequest();
    dss.vector.solutions.query.LayerDTO dto = new dss.vector.solutions.query.LayerDTO(clientRequest);
    req.setAttribute("dss_vector_solutions_query_Layer_geoHierarchy",
        dss.vector.solutions.geo.GeoHierarchyDTO.getAllInstances(super.getClientSession().getRequest(),
            "keyName", true, 0, 0).getResultSet());
    req.setAttribute("dss_vector_solutions_query_Layer_geometryStyle",
        dss.vector.solutions.query.GeometryStyleDTO.getAllInstances(
            super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("dss_vector_solutions_query_Layer_textStyle",
        dss.vector.solutions.query.TextStyleDTO.getAllInstances(super.getClientSession().getRequest(),
            "keyName", true, 0, 0).getResultSet());
    req.setAttribute("item", dto);
    req.setAttribute("page_title", "Create LayerController");
    render("createComponent.jsp");
  }

  public void failNewInstance() throws java.io.IOException, javax.servlet.ServletException
  {
    this.viewAll();
  }

  /**
   * Edits the layer summary by locking all of its style components and
   * providing a jsp where their values can be edited.
   */
  public void editSummary(java.lang.String layerId) throws java.io.IOException,
      javax.servlet.ServletException
  {
    try
    {
      LayerDTO layer = LayerDTO.lock(this.getClientRequest(), layerId);

      req.setAttribute("layerId", layerId);
      req.setAttribute("geoStyle", layer.getGeometryStyle());
      req.setAttribute("textStyle", layer.getTextStyle());

      req.getRequestDispatcher(EDIT_SUMMARY).forward(req, resp);
    }
    catch (Throwable t)
    {
      JSONMojoExceptionDTO jsonE = new JSONMojoExceptionDTO(t);
      resp.setStatus(500);
      resp.getWriter().print(jsonE.getJSON());
    }
  }

  public void failEditSummary(java.lang.String layerId) throws java.io.IOException,
      javax.servlet.ServletException
  {
    resp.sendError(500);
  }

  public void edit(java.lang.String id) throws java.io.IOException, javax.servlet.ServletException
  {
    dss.vector.solutions.query.LayerDTO dto = dss.vector.solutions.query.LayerDTO.lock(super
        .getClientRequest(), id);
    req.setAttribute("dss_vector_solutions_query_Layer_geoHierarchy",
        dss.vector.solutions.geo.GeoHierarchyDTO.getAllInstances(super.getClientSession().getRequest(),
            "keyName", true, 0, 0).getResultSet());
    req.setAttribute("dss_vector_solutions_query_Layer_geometryStyle",
        dss.vector.solutions.query.GeometryStyleDTO.getAllInstances(
            super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("dss_vector_solutions_query_Layer_textStyle",
        dss.vector.solutions.query.TextStyleDTO.getAllInstances(super.getClientSession().getRequest(),
            "keyName", true, 0, 0).getResultSet());
    req.setAttribute("item", dto);
    req.setAttribute("page_title", "Edit LayerController");
    render("editComponent.jsp");
  }

  public void failEdit(java.lang.String id) throws java.io.IOException, javax.servlet.ServletException
  {
    this.view(id);
  }

  public void viewAll() throws java.io.IOException, javax.servlet.ServletException
  {
    com.terraframe.mojo.constants.ClientRequestIF clientRequest = super.getClientRequest();
    dss.vector.solutions.query.LayerQueryDTO query = dss.vector.solutions.query.LayerDTO
        .getAllInstances(clientRequest, null, true, 20, 1);
    req.setAttribute("query", query);
    req.setAttribute("page_title", "View All LayerController Objects");
    render("viewAllComponent.jsp");
  }

  public void failViewAll() throws java.io.IOException, javax.servlet.ServletException
  {
    resp.sendError(500);
  }

  public void view(java.lang.String id) throws java.io.IOException, javax.servlet.ServletException
  {
    com.terraframe.mojo.constants.ClientRequestIF clientRequest = super.getClientRequest();
    req.setAttribute("dss_vector_solutions_query_Layer_geoHierarchy",
        dss.vector.solutions.geo.GeoHierarchyDTO.getAllInstances(super.getClientSession().getRequest(),
            "keyName", true, 0, 0).getResultSet());
    req.setAttribute("dss_vector_solutions_query_Layer_geometryStyle",
        dss.vector.solutions.query.GeometryStyleDTO.getAllInstances(
            super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("dss_vector_solutions_query_Layer_textStyle",
        dss.vector.solutions.query.TextStyleDTO.getAllInstances(super.getClientSession().getRequest(),
            "keyName", true, 0, 0).getResultSet());
    req.setAttribute("item", dss.vector.solutions.query.LayerDTO.get(clientRequest, id));
    req.setAttribute("page_title", "View LayerController");
    render("viewComponent.jsp");
  }

  public void failView(java.lang.String id) throws java.io.IOException, javax.servlet.ServletException
  {
    this.viewAll();
  }

  public void cancel(dss.vector.solutions.query.LayerDTO dto) throws java.io.IOException,
      javax.servlet.ServletException
  {
    dto.unlock();
    this.view(dto.getId());
  }

  public void failCancel(dss.vector.solutions.query.LayerDTO dto) throws java.io.IOException,
      javax.servlet.ServletException
  {
    resp.sendError(500);
  }

  public void viewSummary(java.lang.String layerId) throws java.io.IOException,
      javax.servlet.ServletException
  {
  }

  public void failViewSummary(java.lang.String layerId) throws java.io.IOException,
      javax.servlet.ServletException
  {
    resp.sendError(500);
  }

  /**
   * Updates the layer summary layer (the style components).
   */
  public void updateSummary(dss.vector.solutions.query.GeometryStyleDTO geometryStyle,
      dss.vector.solutions.query.TextStyleDTO textStyle, String layerId) throws java.io.IOException,
      javax.servlet.ServletException
  {
    try
    {
      LayerDTO layer = LayerDTO.updateLayer(this.getClientRequest(), geometryStyle, textStyle, layerId);

      SLDWriter sldWriter = new SLDWriter(layer);
      sldWriter.write();

      resp.getWriter().print(layer.getId());
    }
    catch (ProblemExceptionDTO e)
    {
      JSONProblemExceptionDTO jsonE = new JSONProblemExceptionDTO(e);
      resp.setStatus(500);
      resp.getWriter().print(jsonE.getJSON());
    }
    catch(Throwable t)
    {
      JSONMojoExceptionDTO jsonE = new JSONMojoExceptionDTO(t);
      resp.setStatus(500);
      resp.getWriter().print(jsonE.getJSON());
    }
  }

  public void failUpdateSummary(dss.vector.solutions.query.GeometryStyleDTO geometryStyle,
      dss.vector.solutions.query.TextStyleDTO textStyle) throws java.io.IOException,
      javax.servlet.ServletException
  {
    resp.sendError(500);
  }

  public void update(dss.vector.solutions.query.LayerDTO dto) throws java.io.IOException,
      javax.servlet.ServletException
  {
    try
    {
      dto.apply();
      this.view(dto.getId());
    }
    catch (com.terraframe.mojo.ProblemExceptionDTO e)
    {
      this.failUpdate(dto);
    }
  }

  public void failUpdate(dss.vector.solutions.query.LayerDTO dto) throws java.io.IOException,
      javax.servlet.ServletException
  {
    req.setAttribute("dss_vector_solutions_query_Layer_geoHierarchy",
        dss.vector.solutions.geo.GeoHierarchyDTO.getAllInstances(super.getClientSession().getRequest(),
            "keyName", true, 0, 0).getResultSet());
    req.setAttribute("dss_vector_solutions_query_Layer_geometryStyle",
        dss.vector.solutions.query.GeometryStyleDTO.getAllInstances(
            super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("dss_vector_solutions_query_Layer_textStyle",
        dss.vector.solutions.query.TextStyleDTO.getAllInstances(super.getClientSession().getRequest(),
            "keyName", true, 0, 0).getResultSet());
    req.setAttribute("item", dto);
    req.setAttribute("page_title", "Update LayerController");
    render("editComponent.jsp");
  }

  /**
   * Creates a Layer with default styles.
   */
  public void createSummary(java.lang.String savedSearchId, java.lang.String layerClass)
      throws java.io.IOException, javax.servlet.ServletException
  {
    try
    {
      LayerDTO layer = LayerDTO.createLayer(this.getClientRequest(), savedSearchId, layerClass);

      SLDWriter sldWriter = new SLDWriter(layer);
      sldWriter.write();

      resp.getWriter().print(layer.getId());
    }
    catch (Throwable t)
    {
      // FIXME roll back file changes and delete Layer

      JSONMojoExceptionDTO jsonE = new JSONMojoExceptionDTO(t);
      resp.setStatus(500);
      resp.getWriter().print(jsonE.getJSON());
    }
  }

  public void failCreateSummary(java.lang.String savedSearchId, java.lang.String layerClass)
      throws java.io.IOException, javax.servlet.ServletException
  {
    resp.sendError(500);
  }

  public void create(dss.vector.solutions.query.LayerDTO dto) throws java.io.IOException,
      javax.servlet.ServletException
  {
    try
    {
      dto.apply();
      this.view(dto.getId());
    }
    catch (com.terraframe.mojo.ProblemExceptionDTO e)
    {
      this.failCreate(dto);
    }
  }

  public void failCreate(dss.vector.solutions.query.LayerDTO dto) throws java.io.IOException,
      javax.servlet.ServletException
  {
    req.setAttribute("dss_vector_solutions_query_Layer_geoHierarchy",
        dss.vector.solutions.geo.GeoHierarchyDTO.getAllInstances(super.getClientSession().getRequest(),
            "keyName", true, 0, 0).getResultSet());
    req.setAttribute("dss_vector_solutions_query_Layer_geometryStyle",
        dss.vector.solutions.query.GeometryStyleDTO.getAllInstances(
            super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("dss_vector_solutions_query_Layer_textStyle",
        dss.vector.solutions.query.TextStyleDTO.getAllInstances(super.getClientSession().getRequest(),
            "keyName", true, 0, 0).getResultSet());
    req.setAttribute("item", dto);
    req.setAttribute("page_title", "Create LayerController");
    render("createComponent.jsp");
  }
}
