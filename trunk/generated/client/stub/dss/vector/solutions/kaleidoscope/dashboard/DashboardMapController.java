/*******************************************************************************
 * Copyright (C) 2018 IVCC
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 ******************************************************************************/
package dss.vector.solutions.kaleidoscope.dashboard;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;

import com.runwaysdk.constants.ClientRequestIF;
import com.runwaysdk.constants.CommonProperties;
import com.runwaysdk.controller.ErrorUtility;

import dss.vector.solutions.geoserver.GeoserverProperties;
import dss.vector.solutions.kaleidoscope.JavascriptUtil;
import dss.vector.solutions.kaleidoscope.dashboard.layer.DashboardLayerDTO;
import dss.vector.solutions.util.FileDownloadUtil;

public class DashboardMapController extends DashboardMapControllerBase implements com.runwaysdk.generation.loader.Reloadable
{
  public static final String JSP_DIR = "/WEB-INF/dss/vector/solutions/kaleidoscope/dashboard/DashboardMap/";

  public static final String LAYOUT  = "WEB-INF/templates/layout.jsp";

  public DashboardMapController(javax.servlet.http.HttpServletRequest req, javax.servlet.http.HttpServletResponse resp, java.lang.Boolean isAsynchronous)
  {
    super(req, resp, isAsynchronous, JSP_DIR, LAYOUT);
  }
  
  public void refresh(java.lang.String state, String id) throws java.io.IOException, javax.servlet.ServletException
  {
    String ret = DashboardMapDTO.refresh(this.getClientRequest(), id, state, this.getRequest().getLocale().toLanguageTag());
    
    ServletOutputStream sos = this.getResponse().getOutputStream();
    sos.write(ret.getBytes(Charset.forName("UTF-8")));
    sos.close();
  }

  public void cancel(dss.vector.solutions.kaleidoscope.dashboard.DashboardMapDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    dto.unlock();
    this.view(dto.getId());
  }

  public void failCancel(dss.vector.solutions.kaleidoscope.dashboard.DashboardMapDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    this.edit(dto.getId());
  }

  public void create(dss.vector.solutions.kaleidoscope.dashboard.DashboardMapDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    try
    {
      dto.apply();
      this.view(dto.getId());
    }
    catch (com.runwaysdk.ProblemExceptionDTO e)
    {
      this.failCreate(dto);
    }
  }

  public void failCreate(dss.vector.solutions.kaleidoscope.dashboard.DashboardMapDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    req.setAttribute("item", dto);
    render("createComponent.jsp");
  }

  public void delete(dss.vector.solutions.kaleidoscope.dashboard.DashboardMapDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    try
    {
      dto.delete();
      this.viewAll();
    }
    catch (com.runwaysdk.ProblemExceptionDTO e)
    {
      this.failDelete(dto);
    }
  }

  public void failDelete(dss.vector.solutions.kaleidoscope.dashboard.DashboardMapDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    req.setAttribute("item", dto);
    render("editComponent.jsp");
  }

  public void edit(java.lang.String id) throws java.io.IOException, javax.servlet.ServletException
  {
    dss.vector.solutions.kaleidoscope.dashboard.DashboardMapDTO dto = dss.vector.solutions.kaleidoscope.dashboard.DashboardMapDTO.lock(super.getClientRequest(), id);
    req.setAttribute("item", dto);
    render("editComponent.jsp");
  }

  public void failEdit(java.lang.String id) throws java.io.IOException, javax.servlet.ServletException
  {
    this.view(id);
  }

  public void newInstance() throws java.io.IOException, javax.servlet.ServletException
  {
    com.runwaysdk.constants.ClientRequestIF clientRequest = super.getClientRequest();
    dss.vector.solutions.kaleidoscope.dashboard.DashboardMapDTO dto = new dss.vector.solutions.kaleidoscope.dashboard.DashboardMapDTO(clientRequest);
    req.setAttribute("item", dto);
    render("createComponent.jsp");
  }

  public void failNewInstance() throws java.io.IOException, javax.servlet.ServletException
  {
    this.viewAll();
  }

  public void update(dss.vector.solutions.kaleidoscope.dashboard.DashboardMapDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    try
    {
      dto.apply();
      this.view(dto.getId());
    }
    catch (com.runwaysdk.ProblemExceptionDTO e)
    {
      this.failUpdate(dto);
    }
  }

  public void failUpdate(dss.vector.solutions.kaleidoscope.dashboard.DashboardMapDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    req.setAttribute("item", dto);
    render("editComponent.jsp");
  }

  public void view(java.lang.String id) throws java.io.IOException, javax.servlet.ServletException
  {
    com.runwaysdk.constants.ClientRequestIF clientRequest = super.getClientRequest();
    req.setAttribute("item", dss.vector.solutions.kaleidoscope.dashboard.DashboardMapDTO.get(clientRequest, id));
    render("viewComponent.jsp");
  }

  public void failView(java.lang.String id) throws java.io.IOException, javax.servlet.ServletException
  {
    this.viewAll();
  }

  public void viewAll() throws java.io.IOException, javax.servlet.ServletException
  {
    com.runwaysdk.constants.ClientRequestIF clientRequest = super.getClientRequest();
    dss.vector.solutions.kaleidoscope.dashboard.DashboardMapQueryDTO query = dss.vector.solutions.kaleidoscope.dashboard.DashboardMapDTO.getAllInstances(clientRequest, null, true, 20, 1);
    req.setAttribute("query", query);
    render("viewAllComponent.jsp");
  }

  public void failViewAll() throws java.io.IOException, javax.servlet.ServletException
  {
    resp.sendError(500);
  }

  public void viewPage(java.lang.String sortAttribute, java.lang.Boolean isAscending, java.lang.Integer pageSize, java.lang.Integer pageNumber) throws java.io.IOException, javax.servlet.ServletException
  {
    com.runwaysdk.constants.ClientRequestIF clientRequest = super.getClientRequest();
    dss.vector.solutions.kaleidoscope.dashboard.DashboardMapQueryDTO query = dss.vector.solutions.kaleidoscope.dashboard.DashboardMapDTO.getAllInstances(clientRequest, sortAttribute, isAscending, pageSize, pageNumber);
    req.setAttribute("query", query);
    render("viewAllComponent.jsp");
  }

  public void failViewPage(java.lang.String sortAttribute, java.lang.String isAscending, java.lang.String pageSize, java.lang.String pageNumber) throws java.io.IOException, javax.servlet.ServletException
  {
    resp.sendError(500);
  }

  @Override
  public void createMapForSession() throws IOException, ServletException
  {
    ClientRequestIF clientRequest = this.getClientRequest();

    // Get all dashboards
    DashboardQueryDTO dashboardQ = DashboardDTO.getSortedDashboards(clientRequest);
    List<? extends DashboardDTO> dashboards = dashboardQ.getResultSet();

    if (dashboards.size() == 0)
    {
      this.failCreateMapForSession();

      return;
    }

    req.setAttribute("geoserver", GeoserverProperties.getAppName());
    req.setAttribute("workspace", CommonProperties.getDeployAppName());
    req.setAttribute("editDashboard", true);
    req.setAttribute("editData", false);

    JavascriptUtil.loadDynamicMapBundle(this.getClientRequest(), req);

    req.getRequestDispatcher("/WEB-INF/dss/vector/solutions/kaleidoscope/dashboard/DashboardMap/dashboardViewer.jsp").forward(req, resp);
  }

  // @Override
  public void failCreateMapForSession() throws IOException, ServletException
  {
    render("nodashboard.jsp");
  }

  @Override
  public void exportMap(String mapId, String outFileName, String outFileFormat, String mapBounds, String mapSize, String activeBaseMap, String dashboardStateJson) throws IOException, ServletException
  {
    ClientRequestIF request = this.getClientRequest();
    DashboardMapDTO map = DashboardMapDTO.get(request, mapId);

    if (outFileName == null || outFileName.length() == 0)
    {
      outFileName = "default";
    }
    
    try
    {
      InputStream mapImageInStream = map.generateMapImageExport(outFileFormat, mapBounds, mapSize, activeBaseMap, dashboardStateJson);
      FileDownloadUtil.writeFile(resp, outFileName, outFileFormat, mapImageInStream, "application/" + outFileFormat);
    }
    catch (Exception e)
    {
      ErrorUtility.prepareThrowable(e, req, resp, false);
      this.failExportMap(mapId, outFileName, outFileFormat, mapBounds, mapSize, activeBaseMap, dashboardStateJson);
    }
  }

  @Override
  public void exportLayerData(String mapId, String state, String layerId) throws IOException, ServletException
  {
    ClientRequestIF request = this.getClientRequest();

    try
    {
      DashboardMapDTO map = DashboardMapDTO.get(request, mapId);
      DashboardLayerDTO layer = DashboardLayerDTO.get(request, layerId);
      String layerName = layer.getNameLabel().getValue();

      InputStream istream = map.exportLayerData(state, layerId);

      try
      {
        FileDownloadUtil.writeFile(resp, layerName, "xlsx", istream, "application/xlsx");
      }
      finally
      {
        istream.close();
      }
    }
    catch (Exception e)
    {
      ErrorUtility.prepareThrowable(e, req, resp, false);
    }
  }
}
