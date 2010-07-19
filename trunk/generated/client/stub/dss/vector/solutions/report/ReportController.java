package dss.vector.solutions.report;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.birt.report.engine.api.EngineConstants;
import org.eclipse.birt.report.engine.api.IRenderOption;
import org.eclipse.birt.report.engine.api.IReportEngine;
import org.eclipse.birt.report.engine.api.IReportRunnable;
import org.eclipse.birt.report.engine.api.IRunAndRenderTask;
import org.eclipse.birt.report.engine.api.RenderOption;
import org.eclipse.birt.report.model.api.CachedMetaDataHandle;
import org.eclipse.birt.report.model.api.DesignElementHandle;
import org.eclipse.birt.report.model.api.MemberHandle;
import org.eclipse.birt.report.model.api.OdaDataSetHandle;
import org.eclipse.birt.report.model.api.OdaDataSourceHandle;
import org.eclipse.birt.report.model.api.ReportDesignHandle;
import org.eclipse.birt.report.model.api.activity.SemanticException;
import org.eclipse.birt.report.model.api.elements.structures.ResultSetColumn;

import au.com.bytecode.opencsv.CSVReader;

import com.runwaysdk.constants.ClientProperties;
import com.runwaysdk.constants.ClientRequestIF;
import com.runwaysdk.generation.loader.Reloadable;
import com.runwaysdk.util.FileIO;
import com.runwaysdk.util.IDGenerator;

import dss.vector.solutions.query.QueryBuilderDTO;
import dss.vector.solutions.query.QueryConstants;
import dss.vector.solutions.query.SavedSearchDTO;
import dss.vector.solutions.query.SavedSearchRequiredExceptionDTO;
import dss.vector.solutions.util.BirtEngine;

public class ReportController extends ReportControllerBase implements Reloadable
{
  private static final long serialVersionUID    = 1236706138416L;

  private static String     DATA_SET_QUERY      = "queryText";

  private static String     TEMP_FILE_NAME      = "temp.csv";

  private String            FLAT_FILE_EXTENSION = "org.eclipse.datatools.connectivity.oda.flatfile";

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

    /*
    if(type.equals(QueryTypeDTO.AGGREGATED_CASES))
    {
      return AggregatedCaseDTO.exportQueryToCSV(this.getClientRequest(), queryXML, config, savedSearchId);
    }
    else if(type.equals(QueryTypeDTO.ENTOMOLOGY))
    {
//      return MosquitoDTO.exportQueryToCSV(this.getClientRequest(), queryXML, config, savedSearchId);
    }
    else if(type.equals(QueryTypeDTO.RESISTANCE))
    {
      return AdultDiscriminatingDoseAssayDTO.exportQueryToCSV(this.getClientRequest(), queryXML, config, savedSearchId);
    }
    else if(type.equals(QueryTypeDTO.IRS))
    {
      return AbstractSprayDTO.exportQueryToCSV(this.getClientRequest(), queryXML, config, savedSearchId);
    }
    else if(type.equals(QueryTypeDTO.INDICATOR_SURVEY))
    {
      return SurveyPointDTO.exportQueryToCSV(this.getClientRequest(), queryXML, config, savedSearchId);
    }
    */
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
    String tempDir = null;

    try
    {
      ClientRequestIF request = this.getClientRequest();
      SavedSearchDTO search = SavedSearchDTO.get(request, savedSearchId);

      // Get report name and launch the engine
      ServletContext sc = req.getSession().getServletContext();
      IReportEngine engine = BirtEngine.getBirtEngine(sc, request);

      tempDir = this.generateTempCSVFile(input, TEMP_FILE_NAME);

      // Open report design
      IReportRunnable design = engine.openReportDesign(search.getTemplateStream());

      this.configureDataSet(tempDir, design);

      // set output options
      String queryName = search.getQueryName().replaceAll(" ", "");

      resp.setHeader("Content-Disposition", "attachment;filename=" + queryName + ".pdf");
      IRenderOption options = new RenderOption();
      options.setOutputFormat(RenderOption.OUTPUT_FORMAT_PDF);
      options.setOutputStream(resp.getOutputStream());

      HashMap<String, Object> contextMap = new HashMap<String, Object>();
      contextMap.put(EngineConstants.APPCONTEXT_CLASSLOADER_KEY, this.getClass().getClassLoader());

      // create task to run and render report
      IRunAndRenderTask task = engine.createRunAndRenderTask(design);
      task.setAppContext(contextMap);
      task.setRenderOption(options);

      // run report
      task.run();
      task.close();
    }
    catch (Throwable e)
    {
      String msg = "The provided design is not a valid BIRT design";
      throw new TemplateExceptionDTO(this.getClientRequest(), req.getLocale(), msg);
    }
    finally
    {
      // Delete the temp file
      if (tempDir != null)
      {
        this.deleteTempDirectory(tempDir);
      }
    }
  }

  @SuppressWarnings("unchecked")
  private void configureDataSet(String tempDir, IReportRunnable design) throws SemanticException,
      ServletException
  {
    // Change the data source to the temporary csv directory
    ReportDesignHandle handle = (ReportDesignHandle) design.getDesignHandle();

    validateReportData(handle, tempDir);

    for (Iterator i = handle.getDataSources().iterator(); i.hasNext();)
    {
      ( (DesignElementHandle) i.next() ).setProperty("HOME", tempDir);
    }

    // Update the data set to use the temporary csv file
    for (Iterator i = handle.getDataSets().iterator(); i.hasNext();)
    {
      DesignElementHandle dataset = (DesignElementHandle) i.next();

      Pattern pattern = Pattern.compile("^(select\\s+.*?\\s+from\\s+)(.*?)(\\s+:.*)$");
      Matcher matcher = pattern.matcher(dataset.getStringProperty(DATA_SET_QUERY));

      matcher.find();

      StringBuffer result = new StringBuffer(matcher.group(1));
      result.append(TEMP_FILE_NAME);
      result.append(matcher.group(3));

      dataset.setProperty(DATA_SET_QUERY, result.toString());
    }
  }

  @SuppressWarnings("unchecked")
  private void validateReportData(ReportDesignHandle handle, String dir) throws ServletException
  {
    if (handle.getAllDataSets().size() > 1)
    {
      throw new DataSetLimitExceptionDTO(this.getClientRequest());
    }

    if (handle.getAllDataSources().size() > 1)
    {
      throw new DataSourceLimitExceptionDTO(this.getClientRequest());
    }

    // Validate the csv structure against the report structure
    try
    {
      List<String> headers = new LinkedList<String>();
      CSVReader in = new CSVReader(new FileReader(new File(dir + "/" + TEMP_FILE_NAME)), ',', '\"');

      for (String token : in.readNext())
      {
        headers.add(token.replaceAll("\"", "").trim());
      }

      in.close();

      for (Iterator i = handle.getDataSources().iterator(); i.hasNext();)
      {
        String extensionID = ( (OdaDataSourceHandle) i.next() ).getExtensionID();

        if (!extensionID.equals(FLAT_FILE_EXTENSION))
        {
          String msg = "The only system only supports flat file data sources";
          throw new UnsupportedDataSourceExceptionDTO(this.getClientRequest(), req.getLocale(), msg);
        }
      }

      for (Iterator i = handle.getDataSets().iterator(); i.hasNext();)
      {
        OdaDataSetHandle dataset = (OdaDataSetHandle) i.next();

        CachedMetaDataHandle metadata = dataset.getCachedMetaDataHandle();

        MemberHandle members = metadata.getResultSet();

        ArrayList list = members.getListValue();

        for (Object choice : list)
        {
          ResultSetColumn column = (ResultSetColumn) choice;
          String columnName = column.getColumnName();

          if (!headers.contains(columnName))
          {
            String msg = "Invalid query structure";
            throw new QueryConfigurationExceptionDTO(this.getClientRequest(), req.getLocale(), msg);
          }
        }
      }
    }
    catch (IOException e)
    {
      throw new ServletException(e);
    }
  }

  private String generateTempCSVFile(InputStream in, String fileName)
  {
    try
    {
      String path = ClientProperties.getFileCacheDirectory() + IDGenerator.nextID() + "/";

      // Make the file structure
      new File(path).mkdirs();

      File file = new File(path + fileName);
      file.deleteOnExit();

      FileIO.write(new BufferedOutputStream(new FileOutputStream(file)), in);

      return path;
    }
    catch (Exception e)
    {
      throw new UnableToGenerateCSVExceptionDTO(this.getClientRequest(), req.getLocale());
    }
  }

  private void deleteTempDirectory(String file)
  {
    try
    {
      File directory = new File(file);

      if (directory.exists())
      {
        FileIO.deleteDirectory(directory);
      }
    }
    catch (IOException e)
    {
      throw new UnableToGenerateCSVExceptionDTO(this.getClientRequest(), req.getLocale());
    }
  }
}
