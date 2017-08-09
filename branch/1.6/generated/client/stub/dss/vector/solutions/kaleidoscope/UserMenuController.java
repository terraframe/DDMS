package dss.vector.solutions.kaleidoscope;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;

import com.runwaysdk.constants.ClientRequestIF;
import com.runwaysdk.util.FileIO;

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
