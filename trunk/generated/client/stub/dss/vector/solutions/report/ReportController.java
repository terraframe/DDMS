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
package dss.vector.solutions.report;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.Enumeration;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.birt.core.exception.BirtException;
import org.eclipse.birt.report.engine.api.EngineException;
import org.eclipse.birt.report.engine.api.IReportRunnable;
import org.eclipse.birt.report.model.api.activity.SemanticException;

import com.runwaysdk.constants.ClientProperties;
import com.runwaysdk.constants.ClientRequestIF;
import com.runwaysdk.constants.LocalProperties;
import com.runwaysdk.dataaccess.ProgrammingErrorException;
import com.runwaysdk.generation.loader.Reloadable;
import com.runwaysdk.util.IDGenerator;

import dss.vector.solutions.query.QueryBuilderDTO;
import dss.vector.solutions.query.QueryConstants;
import dss.vector.solutions.query.SavedSearchDTO;
import dss.vector.solutions.query.SavedSearchRequiredExceptionDTO;
import dss.vector.solutions.util.ReportGenerator;

public class ReportController extends ReportControllerBase implements Reloadable
{
  public static final long serialVersionUID = 1236706138416L;

  public ReportController(HttpServletRequest req, HttpServletResponse resp, Boolean isAsynchronous)
  {
    super(req, resp, isAsynchronous);
  }

  public void generateReport(String queryXML, String config, String savedSearchId) throws IOException, ServletException
  {
    try
    {
      validateParameters(queryXML, config, savedSearchId);

      buildReport(savedSearchId, this.getCSV(queryXML, config, savedSearchId));
    }
    catch (Throwable t)
    {
      resp.getWriter().write(t.getLocalizedMessage());
    }
  }

  private InputStream getCSV(String queryXML, String config, String savedSearchId)
  {
    ClientRequestIF request = this.getClientRequest();
    SavedSearchDTO search = SavedSearchDTO.get(this.getClientRequest(), savedSearchId);
    String queryType = search.getQueryType();
    String className = QueryConstants.getQueryClass(queryType);

    return QueryBuilderDTO.exportQueryToCSV(request, className, queryXML, config, savedSearchId);
  }

  private void validateParameters(String queryXML, String geoEntityType, String savedSearchId)
  {
    if (savedSearchId == null || savedSearchId.trim().length() == 0)
    {
      throw new SavedSearchRequiredExceptionDTO(this.getClientRequest(), req.getLocale());
    }
  }

  private void buildReport(String savedSearchId, InputStream input) throws ServletException, IOException, SemanticException
  {
    ClientRequestIF request = this.getClientRequest();
    SavedSearchDTO search = SavedSearchDTO.get(request, savedSearchId);

    InputStream template = search.getTemplateStream();

    String directory = ClientProperties.getFileCacheDirectory() + IDGenerator.nextID();

    try
    {
      String fileName = search.getQueryName().replaceAll("\\s", "");
      // Generate the report;
      ReportGenerator generator = new ReportGenerator(LocalProperties.getLogDirectory(), directory, this.getClientRequest(), this.req.getLocale());
      IReportRunnable design = generator.getReportDesign(template, input);

      try
      {
        resp.setHeader("Content-Type", "application/pdf");
        resp.setHeader("Content-Disposition", "attachment;filename=\"" + fileName + ".pdf\"");

        generator.generate(design, resp.getOutputStream());
      }
      finally
      {
        generator.cleanup();
      }
    }
    catch (IOException e)
    {
      /*
       * Exception is coming from within BIRT so we need to swallow it and give
       * them a localized message.
       */

      String msg = "The provided design is not a valid BIRT design";
      throw new TemplateExceptionDTO(this.getClientRequest(), req.getLocale(), msg);
    }
    catch (SemanticException e)
    {
      /*
       * Exception is coming from within BIRT so we need to swallow it and give
       * them a localized message.
       */

      String msg = "The provided design is not a valid BIRT design";
      throw new TemplateExceptionDTO(this.getClientRequest(), req.getLocale(), msg);
    }
    catch (EngineException e)
    {
      /*
       * Exception is coming from within BIRT so we need to swallow it and give
       * them a localized message.
       */

      String msg = "The provided design is not a valid BIRT design";
      throw new TemplateExceptionDTO(this.getClientRequest(), req.getLocale(), msg);
    }
    catch (BirtException e)
    {
      /*
       * Unable to setup the BIRT: This is a developer problem and should never
       * happen. It is indicitive of a configuration or class path problem.
       */

      throw new ProgrammingErrorException(e);
    }
  }

  @Override
  public void run(String report) throws IOException, ServletException
  {
    try
    {
      run(report, ReportItemDTO.get(this.getClientRequest(), report));
    }
    catch (Throwable t)
    {
      boolean redirect = dss.vector.solutions.util.ErrorUtility.prepareThrowable(t, req, resp, this.isAsynchronous());

      if (!redirect)
      {
        req.getRequestDispatcher("/index.jsp").forward(req, resp);
      }
    }
  }

  @SuppressWarnings("unchecked")
  public void run(String report, ReportItemDTO item) throws UnsupportedEncodingException, ServletException, IOException
  {
    /*
     * First validate permissions, this must be done before response.getOutputStream()
     * is called otherwise redirecting on the error case will not work
     */
    item.validatePermissions();

    List<ReportParameterDTO> parameters = new LinkedList<ReportParameterDTO>();

    Enumeration<String> parameterNames = req.getParameterNames();

    Integer pageNumber = 1;
    String format = item.getOutputFormatEnumNames().get(0);

    while (parameterNames.hasMoreElements())
    {
      String parameterName = parameterNames.nextElement();
      String[] parameterValues = req.getParameterValues(parameterName);

      ReportParameterDTO parameter = new ReportParameterDTO(this.getClientRequest());
      parameter.setParameterName(parameterName);
      parameter.setParameterValue(parameterValues[0]);

      parameters.add(parameter);

      if (parameter.getParameterName().equals("pageNumber"))
      {
        pageNumber = new Integer(parameter.getParameterValue());
      }

      if (parameter.getParameterName().equals("format"))
      {
        format = parameter.getParameterValue();
      }
    }

    /*
     * Important: Calling resp.getOutputStream() changes the state of the HTTP
     * request and response objects.  However, if an error occurs while rendering
     * the report we need to delegate to the standard error handling mechanism.
     * As such we can't call resp.getOutputStream() until we are sure the report
     * has rendered.  Therefore, first render the report to a temp byte array stream.
     * Once that has rendered, copy the bytes from the byte array to the servlet
     * output stream.  Note, this may cause memory problems if the report being
     * rendered is too big.
     */
    ByteArrayOutputStream rStream = new ByteArrayOutputStream();

    try
    {
      String url = this.req.getRequestURL().toString();
      String baseURL = url.substring(0, url.lastIndexOf('/'));

      Long pageCount = item.render(rStream, parameters.toArray(new ReportParameterDTO[parameters.size()]), baseURL, null);

      if (format.equals(OutputFormatDTO.HTML.name()))
      {
        req.setAttribute("pageTitle", item.getReportLabel().getValue());
        req.setAttribute("report", rStream.toString());
        req.setAttribute("pageNumber", pageNumber);
        req.setAttribute("pageCount", pageCount);
        req.setAttribute("id", report);
        req.setAttribute("cache", item.getCacheDocument());

        req.getRequestDispatcher("/WEB-INF/report.jsp").forward(req, resp);
      }
      else
      {
        String fileName = item.getReportLabel().getValue().replaceAll("\\s", "_");
        resp.setHeader("Content-Type", "application/" + format);
        resp.setHeader("Content-Disposition", "attachment;filename=\"" + fileName + "." + format + "\"");

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

  @Override
  public void generate(String report) throws IOException, ServletException
  {
    try
    {
      ReportItemDTO item = ReportItemDTO.get(this.getClientRequest(), report);

      if (item.getCacheDocument() || !item.hasParameterDefinitions())
      {
        this.run(report, item);
      }
      else
      {
        /*
         * First validate permissions, this must be done before response.getOutputStream()
         * is called otherwise redirecting on the error case will not work
         */
        item.validatePermissions();

        req.setAttribute("pageTitle", item.getReportLabel().getValue());
        req.setAttribute("report", null);
        req.setAttribute("pageNumber", 0);
        req.setAttribute("pageCount", 0);
        req.setAttribute("id", report);
        req.setAttribute("cache", item.getCacheDocument());

        req.getRequestDispatcher("/WEB-INF/report.jsp").forward(req, resp);
      }
    }
    catch (Throwable t)
    {
      boolean redirect = dss.vector.solutions.util.ErrorUtility.prepareThrowable(t, req, resp, this.isAsynchronous());

      if (!redirect)
      {
        req.getRequestDispatcher("/index.jsp").forward(req, resp);
      }
    }
  }
}
