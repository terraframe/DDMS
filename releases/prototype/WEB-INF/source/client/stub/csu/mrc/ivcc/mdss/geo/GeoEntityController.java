package csu.mrc.ivcc.mdss.geo;

import csu.mrc.ivcc.mdss.geo.GeoEntityControllerBase;

public class GeoEntityController extends GeoEntityControllerBase implements com.terraframe.mojo.generation.loader.Reloadable
{
  public static final String JSP_DIR = "WEB-INF/csu/mrc/ivcc/mdss/geo/GeoEntity/";
  public static final String LAYOUT = JSP_DIR + "layout.jsp";
  
  private static final long serialVersionUID = 1235073595864L;
  
  public GeoEntityController(javax.servlet.http.HttpServletRequest req, javax.servlet.http.HttpServletResponse resp, java.lang.Boolean isAsynchronous)
  {
    super(req, resp, isAsynchronous, JSP_DIR, LAYOUT);
  }
  
  public void edit(java.lang.String id) throws java.io.IOException, javax.servlet.ServletException
  {
    csu.mrc.ivcc.mdss.geo.GeoEntityDTO dto = csu.mrc.ivcc.mdss.geo.GeoEntityDTO.lock(super.getClientRequest(), id);
    req.setAttribute("mdss_ivcc_mrc_csu_geo_GeoEntity_terrain", csu.mrc.ivcc.mdss.geo.TerrainDTO.allItems(super.getClientSession().getRequest()));
    req.setAttribute("item", dto);
    req.setAttribute("page_title", "Edit Geo Entity");
    render("editComponent.jsp");
  }
  public void failEdit(java.lang.String id) throws java.io.IOException, javax.servlet.ServletException
  {
    this.view(id);
  }
  public void viewPage(java.lang.String sortAttribute, java.lang.Boolean isAscending, java.lang.Integer pageSize, java.lang.Integer pageNumber) throws java.io.IOException, javax.servlet.ServletException
  {
    com.terraframe.mojo.constants.ClientRequestIF clientRequest = super.getClientRequest();
    csu.mrc.ivcc.mdss.geo.GeoEntityQueryDTO query = csu.mrc.ivcc.mdss.geo.GeoEntityDTO.getAllInstances(clientRequest, sortAttribute, isAscending, pageSize, pageNumber);
    req.setAttribute("query", query);
    req.setAttribute("page_title", "View All Geo Entities");
    render("viewAllComponent.jsp");
  }
  public void failViewPage(java.lang.String sortAttribute, java.lang.String isAscending, java.lang.String pageSize, java.lang.String pageNumber) throws java.io.IOException, javax.servlet.ServletException
  {
    resp.sendError(500);
  }
  public void update(csu.mrc.ivcc.mdss.geo.GeoEntityDTO dto) throws java.io.IOException, javax.servlet.ServletException
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
  public void failUpdate(csu.mrc.ivcc.mdss.geo.GeoEntityDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    req.setAttribute("mdss_ivcc_mrc_csu_geo_GeoEntity_terrain", csu.mrc.ivcc.mdss.geo.TerrainDTO.allItems(super.getClientSession().getRequest()));
    req.setAttribute("item", dto);
    req.setAttribute("page_title", "Update Geo Entity");
    render("updateComponent.jsp");
  }
  public void view(java.lang.String id) throws java.io.IOException, javax.servlet.ServletException
  {
    com.terraframe.mojo.constants.ClientRequestIF clientRequest = super.getClientRequest();
    req.setAttribute("mdss_ivcc_mrc_csu_geo_GeoEntity_terrain", csu.mrc.ivcc.mdss.geo.TerrainDTO.allItems(super.getClientSession().getRequest()));
    req.setAttribute("item", csu.mrc.ivcc.mdss.geo.GeoEntityDTO.get(clientRequest, id));
    req.setAttribute("page_title", "View Geo Entity");
    render("viewComponent.jsp");
  }
  public void failView(java.lang.String id) throws java.io.IOException, javax.servlet.ServletException
  {
    this.viewAll();
  }
  public void create(csu.mrc.ivcc.mdss.geo.GeoEntityDTO dto) throws java.io.IOException, javax.servlet.ServletException
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
  public void failCreate(csu.mrc.ivcc.mdss.geo.GeoEntityDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    req.setAttribute("mdss_ivcc_mrc_csu_geo_GeoEntity_terrain", csu.mrc.ivcc.mdss.geo.TerrainDTO.allItems(super.getClientSession().getRequest()));
    req.setAttribute("item", dto);
    req.setAttribute("page_title", "Create Geo Entity");
    render("createComponent.jsp");
  }
  public void newInstance() throws java.io.IOException, javax.servlet.ServletException
  {
    com.terraframe.mojo.constants.ClientRequestIF clientRequest = super.getClientRequest();
    csu.mrc.ivcc.mdss.geo.GeoEntityDTO dto = new csu.mrc.ivcc.mdss.geo.GeoEntityDTO(clientRequest);
    req.setAttribute("mdss_ivcc_mrc_csu_geo_GeoEntity_terrain", csu.mrc.ivcc.mdss.geo.TerrainDTO.allItems(super.getClientSession().getRequest()));
    req.setAttribute("item", dto);
    req.setAttribute("page_title", "Create Geo Entity");
    render("createComponent.jsp");
  }
  public void failNewInstance() throws java.io.IOException, javax.servlet.ServletException
  {
    this.viewAll();
  }
  public void viewAll() throws java.io.IOException, javax.servlet.ServletException
  {
    com.terraframe.mojo.constants.ClientRequestIF clientRequest = super.getClientRequest();
    csu.mrc.ivcc.mdss.geo.GeoEntityQueryDTO query = csu.mrc.ivcc.mdss.geo.GeoEntityDTO.getAllInstances(clientRequest, null, true, 20, 1);
    req.setAttribute("query", query);
    req.setAttribute("page_title", "View All Geo Entities");
    render("viewAllComponent.jsp");
  }
  public void failViewAll() throws java.io.IOException, javax.servlet.ServletException
  {
    resp.sendError(500);
  }
  public void cancel(csu.mrc.ivcc.mdss.geo.GeoEntityDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    dto.unlock();
    this.view(dto.getId());
  }
  public void failCancel(csu.mrc.ivcc.mdss.geo.GeoEntityDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    resp.sendError(500);
  }
  public void delete(csu.mrc.ivcc.mdss.geo.GeoEntityDTO dto) throws java.io.IOException, javax.servlet.ServletException
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
  public void failDelete(csu.mrc.ivcc.mdss.geo.GeoEntityDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    req.setAttribute("mdss_ivcc_mrc_csu_geo_GeoEntity_terrain", csu.mrc.ivcc.mdss.geo.TerrainDTO.allItems(super.getClientSession().getRequest()));
    req.setAttribute("item", dto);
    req.setAttribute("page_title", "Edit Geo Entity");
    render("editComponent.jsp");
  }
}