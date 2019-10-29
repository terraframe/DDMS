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
package dss.vector.solutions.kaleidoscope;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;

import com.runwaysdk.constants.ClientRequestIF;
import com.runwaysdk.util.FileIO;

import dss.vector.solutions.general.SystemURLDTO;
import dss.vector.solutions.kaleidoscope.dashboard.DashboardDTO;
import dss.vector.solutions.kaleidoscope.dashboard.DashboardQueryDTO;

public class UserMenuController extends UserMenuControllerBase implements com.runwaysdk.generation.loader.Reloadable
{
  public static final String JSP_DIR    = "/WEB-INF/dss/vector/solutions/kaleidoscope/userMenu/";

  public static final String LAYOUT     = "/layout.jsp";

  public static final String DASHBOARDS = "userDashboards.jsp";

  public UserMenuController(javax.servlet.http.HttpServletRequest req, javax.servlet.http.HttpServletResponse resp, java.lang.Boolean isAsynchronous)
  {
    super(req, resp, isAsynchronous, JSP_DIR, LAYOUT);
  }

  @Override
  public void kaleidoscopes() throws IOException, ServletException
  {

    ClientRequestIF clientRequest = super.getClientRequest();

    DashboardQueryDTO dashboardsQ = DashboardDTO.getSortedDashboards(clientRequest);
    List<? extends DashboardDTO> dashboards = dashboardsQ.getResultSet();

    JavascriptUtil.loadUserBundle(this.getClientRequest(), this.req);

    this.req.setAttribute("dashboards", dashboards);
    this.req.setAttribute("hasWritePermission", SystemURLDTO.hasWritePermissions(clientRequest, "dss.vector.solutions.kaleidoscope.UserMenuController.kaleidoscopes.mojo"));

    render(DASHBOARDS);
  }

  /**
   * Gets the dashboard thumbnail for display in the app.
   * 
   * @dashboardId
   */
  @Override
  public void getDashboardMapThumbnail(String dashboardId)
  {
    ClientRequestIF clientRequest = super.getClientRequest();

    DashboardDTO db = DashboardDTO.get(clientRequest, dashboardId);

    try
    {
      InputStream istream = db.getThumbnailStream();

      try
      {
        resp.setContentType("image/png");

        ServletOutputStream ostream = resp.getOutputStream();

        try
        {
          FileIO.write(ostream, istream);

          ostream.flush();
        }
        finally
        {
          ostream.close();
        }
      }
      finally
      {
        istream.close();
      }
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
  }

  @Override
  public void dhis2Management() throws IOException, ServletException
  {
    URL url = new URL(this.req.getScheme(), this.req.getServerName(), this.req.getServerPort(), this.req.getContextPath());
    String path = url.toString();

    this.req.setAttribute("path", path);

    JavascriptUtil.loadDatasets(this.getClientRequest(), req);

    this.render("dhis2-management.jsp");
  }
}
