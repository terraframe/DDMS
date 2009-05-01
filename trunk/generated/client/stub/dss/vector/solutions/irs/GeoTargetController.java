package dss.vector.solutions.irs;

import java.lang.String;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import dss.vector.solutions.geo.generated.DistrictDTO;
import dss.vector.solutions.geo.generated.GeoEntityDTO;
import dss.vector.solutions.geo.generated.ProvinceDTO;
import dss.vector.solutions.geo.generated.SprayZoneDTO;

public class GeoTargetController extends GeoTargetControllerBase implements com.terraframe.mojo.generation.loader.Reloadable
{
  public static final String JSP_DIR          = "WEB-INF/dss/vector/solutions/irs/GeoTarget/";

  public static final String LAYOUT           = JSP_DIR + "layout.jsp";

  private static final long  serialVersionUID = 1240267414799L;

  public GeoTargetController(javax.servlet.http.HttpServletRequest req, javax.servlet.http.HttpServletResponse resp, java.lang.Boolean isAsynchronous)
  {
    super(req, resp, isAsynchronous, JSP_DIR, LAYOUT);
  }

  public void view(GeoEntityDTO geoEntinty, Integer year, Boolean showChildren) throws java.io.IOException, javax.servlet.ServletException
  {
    com.terraframe.mojo.constants.ClientRequestIF clientRequest = super.getClientRequest();

    GeoTargetDTO item = new GeoTargetDTO(clientRequest);
    String filterType = req.getParameter("filterType");

    // set this as if show_children is false , we will change it if needed
    List<String> geoEntityIds = new ArrayList<String>();

    if (showChildren && filterType != SprayZoneDTO.CLASS)
    {
      if (filterType.equals(ProvinceDTO.CLASS))
      {
        geoEntityIds.addAll(Arrays.asList(geoEntinty.getAllChildIds("dss.vector.solutions.geo.generated.District")));
      }
      if (filterType.equals(DistrictDTO.CLASS))
      {
        geoEntityIds.addAll(Arrays.asList(geoEntinty.getAllChildIds("dss.vector.solutions.geo.generated.SprayZone")));
      }
    }

    geoEntityIds.add(geoEntinty.getId());
    item.setTargetYear(year.intValue());
    item.setGeoEntity(geoEntinty);
    // item.apply();

    GeoTargetViewDTO[] geoTargetViews = GeoTargetViewDTO.getGeoTargets(clientRequest,(String[])geoEntityIds.toArray(new String[geoEntityIds.size()]), year);
    // geoTargetViews = GeoTargetViewDTO.lockAll(clientRequest,geoTargetViews);

    req.setAttribute("item", item);
    req.setAttribute("geoTargetViews", geoTargetViews);
    render("viewComponent.jsp");
  }

  public void failView(GeoEntityDTO geoEntinty, Integer year, Boolean showChildren) throws java.io.IOException, javax.servlet.ServletException
  {
    this.viewAll();
  }

  public void create(dss.vector.solutions.irs.GeoTargetDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    try
    {
      dto.apply();
      // this.view(dto.getId());
    }
    catch (com.terraframe.mojo.ProblemExceptionDTO e)
    {
      this.failCreate(dto);
    }
  }

  public void failCreate(dss.vector.solutions.irs.GeoTargetDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    req.setAttribute("dss_vector_solutions_irs_GeoTarget_geoEntity", dss.vector.solutions.geo.generated.GeoEntityDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("item", dto);
    req.setAttribute("page_title", "Create GeoTargetController");
    render("createComponent.jsp");
  }

  public void delete(dss.vector.solutions.irs.GeoTargetDTO dto) throws java.io.IOException, javax.servlet.ServletException
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

  public void failDelete(dss.vector.solutions.irs.GeoTargetDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    req.setAttribute("dss_vector_solutions_irs_GeoTarget_geoEntity", dss.vector.solutions.geo.generated.GeoEntityDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("item", dto);
    req.setAttribute("page_title", "Edit GeoTargetController");
    render("editComponent.jsp");
  }

  public void cancel(dss.vector.solutions.irs.GeoTargetDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    dto.unlock();
    // this.view(dto.getId());
  }

  public void failCancel(dss.vector.solutions.irs.GeoTargetDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    resp.sendError(500);
  }

  public void edit(java.lang.String id) throws java.io.IOException, javax.servlet.ServletException
  {
    dss.vector.solutions.irs.GeoTargetDTO dto = dss.vector.solutions.irs.GeoTargetDTO.lock(super.getClientRequest(), id);
    req.setAttribute("dss_vector_solutions_irs_GeoTarget_geoEntity", dss.vector.solutions.geo.generated.GeoEntityDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("item", dto);
    req.setAttribute("page_title", "Edit GeoTargetController");
    render("editComponent.jsp");
  }

  public void failEdit(java.lang.String id) throws java.io.IOException, javax.servlet.ServletException
  {
    // this.view(id);
  }

  public void newInstance() throws java.io.IOException, javax.servlet.ServletException
  {
    com.terraframe.mojo.constants.ClientRequestIF clientRequest = super.getClientRequest();
    dss.vector.solutions.irs.GeoTargetDTO dto = new dss.vector.solutions.irs.GeoTargetDTO(clientRequest);
    req.setAttribute("dss_vector_solutions_irs_GeoTarget_geoEntity", dss.vector.solutions.geo.generated.GeoEntityDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("item", dto);
    req.setAttribute("page_title", "Create GeoTargetController");
    render("createComponent.jsp");
  }

  public void failNewInstance() throws java.io.IOException, javax.servlet.ServletException
  {
    this.viewAll();
  }

  public void viewAll() throws java.io.IOException, javax.servlet.ServletException
  {
    com.terraframe.mojo.constants.ClientRequestIF clientRequest = super.getClientRequest();
    dss.vector.solutions.irs.GeoTargetQueryDTO query = dss.vector.solutions.irs.GeoTargetDTO.getAllInstances(clientRequest, null, true, 20, 1);
    req.setAttribute("query", query);
    req.setAttribute("page_title", "View All GeoTargetController Objects");
    render("viewAllComponent.jsp");
  }

  public void failViewAll() throws java.io.IOException, javax.servlet.ServletException
  {
    resp.sendError(500);
  }

  public void viewPage(java.lang.String sortAttribute, java.lang.Boolean isAscending, java.lang.Integer pageSize, java.lang.Integer pageNumber) throws java.io.IOException, javax.servlet.ServletException
  {
    com.terraframe.mojo.constants.ClientRequestIF clientRequest = super.getClientRequest();
    dss.vector.solutions.irs.GeoTargetQueryDTO query = dss.vector.solutions.irs.GeoTargetDTO.getAllInstances(clientRequest, sortAttribute, isAscending, pageSize, pageNumber);
    req.setAttribute("query", query);
    req.setAttribute("page_title", "View All GeoTargetController Objects");
    render("viewAllComponent.jsp");
  }

  public void failViewPage(java.lang.String sortAttribute, java.lang.String isAscending, java.lang.String pageSize, java.lang.String pageNumber) throws java.io.IOException, javax.servlet.ServletException
  {
    resp.sendError(500);
  }

  public void update(dss.vector.solutions.irs.GeoTargetDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    try
    {
      dto.apply();
      // this.view(dto.getId());
    }
    catch (com.terraframe.mojo.ProblemExceptionDTO e)
    {
      this.failUpdate(dto);
    }
  }

  public void failUpdate(dss.vector.solutions.irs.GeoTargetDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    req.setAttribute("dss_vector_solutions_irs_GeoTarget_geoEntity", dss.vector.solutions.geo.generated.GeoEntityDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("item", dto);
    req.setAttribute("page_title", "Update GeoTargetController");
    render("editComponent.jsp");
  }
}
