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
package dss.vector.solutions.kaleidoscope.report;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Enumeration;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.runwaysdk.constants.ClientRequestIF;
import com.runwaysdk.controller.ErrorUtility;
import com.runwaysdk.controller.MultipartFileParameter;
import com.runwaysdk.generation.loader.Reloadable;
import com.runwaysdk.transport.conversion.json.JSONReturnObject;

import dss.vector.solutions.kaleidoscope.JSONControllerUtil;
import dss.vector.solutions.kaleidoscope.dashboard.DashboardDTO;
import dss.vector.solutions.util.FileDownloadUtil;
import dss.vector.solutions.util.LocalizationFacadeDTO;

public class KaleidoscopeReportController extends KaleidoscopeReportControllerBase implements Reloadable
{
  public static final String JSP_DIR = "/WEB-INF/dss/vector/solutions/kaleidoscope/report/KaleidoscopeReport/";

  public static final String LAYOUT  = "/layout.jsp";

  public KaleidoscopeReportController(HttpServletRequest req, HttpServletResponse resp, Boolean isAsynchronous)
  {
    super(req, resp, isAsynchronous, JSP_DIR, LAYOUT);
  }

  public void cancel(KaleidoscopeReportDTO dto) throws IOException, ServletException
  {
    dto.unlock();
    this.view(dto.getId());
  }

  public void failCancel(KaleidoscopeReportDTO dto) throws IOException, ServletException
  {
    this.edit(dto.getDashboardId());
  }

  @Override
  public void create(KaleidoscopeReportDTO dto, MultipartFileParameter design) throws IOException, ServletException
  {
    try
    {
      if (design != null)
      {
        dto.setReportName(design.getFilename());
        dto.applyWithFile(design.getInputStream());
      }
      else
      {
        dto.applyWithFile(null);
      }

      this.resp.getWriter().print(new JSONReturnObject(dto).toString());
    }
    catch (Throwable t)
    {
      boolean needsRedirect = ErrorUtility.handleFormError(t, req, resp);

      if (needsRedirect)
      {
        this.failCreate(dto, design);
      }
    }
  }

  @Override
  public void failCreate(KaleidoscopeReportDTO dto, MultipartFileParameter design) throws IOException, ServletException
  {
    this.newInstance(dto);
  }

  public void delete(KaleidoscopeReportDTO dto) throws IOException, ServletException
  {
    try
    {
      dto.delete();
      this.viewAll();
    }
    catch (Throwable t)
    {
      boolean redirected = false; // ErrorUtility.prepareThrowable(t, req, resp,
                                  // this.isAsynchronous());

      if (!redirected)
      {
        this.failDelete(dto);
      }
    }
  }

  public void failDelete(KaleidoscopeReportDTO dto) throws IOException, ServletException
  {
    this.edit(dto.getDashboardId());
  }

  public void edit(String dashboardId) throws IOException, ServletException
  {
    try
    {
      this.edit(dashboardId, KaleidoscopeReportDTO.lockOrCreateReport(super.getClientRequest(), dashboardId));
    }
    catch (Throwable t)
    {
      boolean redirect = false;
      if (!redirect)
      {
        this.failEdit(dashboardId);
      }
    }
  }

  private void edit(String dashboardId, KaleidoscopeReportDTO dto) throws IOException, ServletException
  {
    try
    {
      req.setAttribute("item", dto);
      req.setAttribute("dashboardId", dashboardId);

      render("editComponent.jsp");
    }
    catch (Throwable t)
    {
      boolean redirect = false;

      if (!redirect)
      {
        this.failEdit(dto.getId());
      }
    }
  }

  public void failEdit(String id) throws IOException, ServletException
  {
    this.view(id);
  }

  public void newInstance() throws IOException, ServletException
  {
    try
    {
      this.newInstance(new KaleidoscopeReportDTO(this.getClientRequest()));
    }
    catch (Throwable t)
    {
      boolean redirect = false;

      if (!redirect)
      {
        this.failNewInstance();
      }
    }
  }

  private void newInstance(KaleidoscopeReportDTO dto) throws IOException, ServletException
  {
    try
    {
      req.setAttribute("item", dto);
      req.setAttribute("dashboards", DashboardDTO.getSortedDashboards(this.getClientRequest()).getResultSet());

      render("createComponent.jsp");
    }
    catch (Throwable t)
    {
      boolean redirect = false;
      if (!redirect)
      {
        this.failNewInstance();
      }
    }
  }

  public void failNewInstance() throws IOException, ServletException
  {
    this.viewAll();
  }

  @Override
  public void update(KaleidoscopeReportDTO dto, MultipartFileParameter design) throws IOException, ServletException
  {
    try
    {
      if (design != null)
      {
        dto.setReportName(design.getFilename());
        dto.applyWithFile(design.getInputStream());
      }
      else
      {
        dto.applyWithFile(null);
      }

      this.resp.getWriter().print(new JSONReturnObject(dto).toString());
    }
    catch (Throwable t)
    {
      boolean needsRedirect = ErrorUtility.handleFormError(t, req, resp);

      if (needsRedirect)
      {
        this.failUpdate(dto, design);
      }
    }
  }

  @Override
  public void failUpdate(KaleidoscopeReportDTO dto, MultipartFileParameter design) throws IOException, ServletException
  {
    this.edit(dto.getDashboardId(), dto);
  }

  public void view(String id) throws IOException, ServletException
  {
    try
    {
      this.view(KaleidoscopeReportDTO.get(super.getClientRequest(), id));
    }
    catch (Throwable t)
    {
      boolean redirect = false;

      if (!redirect)
      {
        this.failView(id);
      }
    }
  }

  private void view(KaleidoscopeReportDTO dto) throws IOException, ServletException
  {
    try
    {
      req.setAttribute("item", dto);
      render("viewComponent.jsp");
    }
    catch (Throwable t)
    {
      boolean redirect = false;

      if (!redirect)
      {
        this.failView(dto.getId());
      }
    }
  }

  public void failView(String id) throws IOException, ServletException
  {
    this.viewAll();
  }

  public void viewAll() throws IOException, ServletException
  {
    this.req.getRequestDispatcher("/index.jsp").forward(this.req, this.resp);
  }

  public void failViewAll() throws IOException, ServletException
  {
    this.req.getRequestDispatcher("/index.jsp").forward(this.req, this.resp);
  }

  public void viewPage(String sortAttribute, Boolean isAscending, Integer pageSize, Integer pageNumber) throws IOException, ServletException
  {
    try
    {
      ClientRequestIF clientRequest = super.getClientRequest();
      KaleidoscopeReportQueryDTO query = KaleidoscopeReportDTO.getAllInstances(clientRequest, sortAttribute, isAscending, pageSize, pageNumber);
      req.setAttribute("query", query);
      render("viewAllComponent.jsp");
    }
    catch (Exception e)
    {
      this.failViewAll();
    }
  }

  @Override
  public void run(String report, String configuration) throws IOException, ServletException
  {
    try
    {
      run(KaleidoscopeReportDTO.getReportItemForDashboard(this.getClientRequest(), report), configuration);
    }
    catch (Throwable t)
    {
      boolean redirect = ErrorUtility.prepareThrowable(t, req, resp, this.isAsynchronous());

      if (!redirect)
      {
        req.getRequestDispatcher("/index.jsp").forward(req, resp);
      }
    }
  }

  private void run(KaleidoscopeReportDTO item, String configuration) throws UnsupportedEncodingException, ServletException, IOException
  {
    if (item != null)
    {
      try
      {
        /*
         * First validate permissions, this must be done before response.getOutputStream() is called otherwise redirecting on the error case will not
         * work
         */
        item.validatePermissions();

        String reportUrl = this.getReportURL();
        String format = null;

        JSONObject json = new JSONObject(configuration);

        List<ReportParameterDTO> parameters = new LinkedList<ReportParameterDTO>();

        JSONArray jsonArray = json.getJSONArray("parameters");

        for (int i = 0; i < jsonArray.length(); i++)
        {
          JSONObject object = jsonArray.getJSONObject(i);

          String name = object.getString("name");
          String value = object.getString("value");

          parameters.add(this.createReportParameter(name, value));

          if (name.equals("format"))
          {
            format = value;
          }
        }

        /*
         * Important: Calling resp.getOutputStream() changes the state of the HTTP request and response objects. However, if an error occurs while
         * rendering the report we need to delegate to the standard error handling mechanism. As such we can't call resp.getOutputStream() until we
         * are sure the report has rendered. Therefore, first render the report to a temp byte array stream. Once that has rendered, copy the bytes
         * from the byte array to the servlet output stream. Note, this may cause memory problems if the report being rendered is too big.
         */
        ByteArrayOutputStream rStream = new ByteArrayOutputStream();

        try
        {
          String url = this.req.getRequestURL().toString();
          String baseURL = url.substring(0, url.lastIndexOf("/"));

          item.render(rStream, parameters.toArray(new ReportParameterDTO[parameters.size()]), baseURL, reportUrl);

          if (format == null || format.equalsIgnoreCase("html"))
          {
            req.setAttribute("report", rStream.toString());

            req.getRequestDispatcher("/WEB-INF/dss/vector/solutions/kaleidoscope/report/KaleidoscopeReport/report.jsp").forward(req, resp);
          }
          else
          {
            String fileName = item.getReportLabel().getValue().replaceAll("\\s", "_");
            resp.setHeader("Content-Type", "application/" + format);
            resp.setHeader("Content-Disposition", "attachment;filename=" + fileName + "." + format);

            ServletOutputStream oStream = resp.getOutputStream();

            try
            {
              oStream.write(rStream.toByteArray());
            }
            finally
            {
              oStream.flush();
              oStream.close();
            }
          }
        }
        finally
        {
          rStream.close();
        }
      }
      catch (JSONException e)
      {
        boolean redirect = ErrorUtility.prepareThrowable(e, req, resp, this.isAsynchronous());

        if (!redirect)
        {
          req.getRequestDispatcher("/index.jsp").forward(req, resp);
        }
      }
    }
    else
    {
      req.setAttribute("report", LocalizationFacadeDTO.getFromBundles(this.getClientRequest(), "report.empty"));
    }
  }

  private ReportParameterDTO createReportParameter(String parameterName, String parameterValue)
  {
    ReportParameterDTO parameter = new ReportParameterDTO(this.getClientRequest());
    parameter.setParameterName(parameterName);
    parameter.setParameterValue(parameterValue);

    return parameter;
  }

  public String getReportURL() throws UnsupportedEncodingException
  {
    String str = "dss.vector.solutions.kaleidoscope.report.KaleidoscopeReportController.generate.mojo?";
    boolean isFirst = true;

    Enumeration<String> paramNames = req.getParameterNames();
    while (paramNames.hasMoreElements())
    {
      String paramName = paramNames.nextElement();

      if (!paramName.equals("pageNumber"))
      {
        if (!isFirst)
        {
          str = str + "&";
        }

        String[] paramValues = req.getParameterValues(paramName);

        for (int i = 0; i < paramValues.length; i++)
        {
          String paramValue = paramValues[i];
          str = str + URLEncoder.encode(paramName, "UTF-8") + "=" + URLEncoder.encode(paramValue, "UTF-8");
        }

        isFirst = false;
      }
    }
    return str;
  }

  // @Override
  public void remove(String dashboardId) throws IOException, ServletException
  {
    ClientRequestIF request = this.getClientRequest();

    try
    {
      KaleidoscopeReportDTO item = KaleidoscopeReportDTO.getReportItemForDashboard(request, dashboardId);
      item.delete();

      JSONControllerUtil.writeReponse(this.resp);
    }
    catch (Throwable t)
    {
      JSONControllerUtil.handleException(this.resp, t, this.getClientRequest());
    }
  }

  // @Override
  public void download(String dashboardId) throws IOException, ServletException
  {
    ClientRequestIF request = this.getClientRequest();
    InputStream rStream = null;

    KaleidoscopeReportDTO report = KaleidoscopeReportDTO.getReportItemForDashboard(request, dashboardId);

    if (report != null)
    {
      String reportId = report.getId();
      String reportName = report.getReportLabel().getValue().replaceAll("\\s", "_");

      try
      {
        rStream = KaleidoscopeReportDTO.getDesignAsStream(request, reportId);

        // Browser dev tools may throw warnings about mime type. This is valid for a custom type.
        // Verify the standard implementation if you are concerned.
        FileDownloadUtil.writeFile(resp, reportName, "rptdesign", rStream, "application/" + "octet-stream");
      }
      catch (Exception e)
      {
        ErrorUtility.prepareThrowable(e, req, resp, false);
        this.failDownload(dashboardId);
      }
      finally
      {
        rStream.close();
      }
    }
  }
}
