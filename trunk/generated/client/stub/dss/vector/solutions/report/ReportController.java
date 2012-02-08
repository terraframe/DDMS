package dss.vector.solutions.report;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.birt.core.exception.BirtException;
import org.eclipse.birt.report.model.api.activity.SemanticException;

import com.runwaysdk.constants.ClientProperties;
import com.runwaysdk.constants.ClientRequestIF;
import com.runwaysdk.constants.LocalProperties;
import com.runwaysdk.generation.loader.Reloadable;
import com.runwaysdk.util.IDGenerator;

import dss.vector.solutions.query.QueryBuilderDTO;
import dss.vector.solutions.query.QueryConstants;
import dss.vector.solutions.query.SavedSearchDTO;
import dss.vector.solutions.query.SavedSearchRequiredExceptionDTO;
import dss.vector.solutions.util.ReportGenerator;

public class ReportController extends ReportControllerBase implements Reloadable
{
  private static final long serialVersionUID = 1236706138416L;

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
    String fileName = search.getQueryName().replaceAll("\\s", "");

    String directory = ClientProperties.getFileCacheDirectory() + IDGenerator.nextID();

    resp.setHeader("Content-Disposition", "attachment;filename=" + fileName + ".pdf");

    try
    {
      // Generate the report;
      ReportGenerator generator = new ReportGenerator(LocalProperties.getLogDirectory(), directory, this.getClientRequest(), this.req.getLocale());
      generator.generate(template, input, resp.getOutputStream());
    }
    catch (BirtException e)
    {
      throw new ServletException(e);
    }
  }
}
