package dss.vector.solutions.util;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.StringReader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.eclipse.birt.core.exception.BirtException;
import org.eclipse.birt.report.engine.api.EngineConstants;
import org.eclipse.birt.report.engine.api.EngineException;
import org.eclipse.birt.report.engine.api.IRenderOption;
import org.eclipse.birt.report.engine.api.IReportEngine;
import org.eclipse.birt.report.engine.api.IReportRunnable;
import org.eclipse.birt.report.engine.api.IRunAndRenderTask;
import org.eclipse.birt.report.engine.api.RenderOption;
import org.eclipse.birt.report.model.api.DesignElementHandle;
import org.eclipse.birt.report.model.api.OdaDataSourceHandle;
import org.eclipse.birt.report.model.api.ReportDesignHandle;
import org.eclipse.birt.report.model.api.activity.SemanticException;

import com.runwaysdk.constants.ClientRequestIF;
import com.runwaysdk.dataaccess.io.FileWriteExceptionDTO;
import com.runwaysdk.generation.loader.Reloadable;
import com.runwaysdk.util.FileIO;

import au.com.bytecode.opencsv.CSVReader;
import dss.vector.solutions.report.DataSetLimitExceptionDTO;
import dss.vector.solutions.report.DataSourceLimitExceptionDTO;
import dss.vector.solutions.report.QueryConfigurationExceptionDTO;
import dss.vector.solutions.report.UnsupportedDataSourceExceptionDTO;

public class ReportGenerator implements Reloadable
{
  private static final String SELECTABLE_REGEX    = "^select\\s+(.*?)\\s+from\\s+(.*?)(\\s+:.*)$";

  private static final String QUERY_REGEX         = "^(select\\s+.*?\\s+from\\s+)(.*?)(\\s+:.*)$";

  private static final String DATA_SET_QUERY      = "queryText";

  private static final String TEMP_FILE_NAME      = "temp.csv";

  private static final String FLAT_FILE_EXTENSION = "org.eclipse.datatools.connectivity.oda.flatfile";

  private String              tempDirectory;

  private String              logDirectory;

  private ClientRequestIF     clientRequest;

  private Locale              locale;

  private IReportEngine       engine;

  public ReportGenerator(String logDirectory, String tempDirectory, ClientRequestIF clientRequest, Locale locale) throws BirtException
  {
    this.logDirectory = logDirectory;
    this.tempDirectory = tempDirectory;
    this.clientRequest = clientRequest;
    this.locale = locale;
    this.engine = BirtEngine.getBirtEngine(this.logDirectory);
  }

  public IReportRunnable getReportDesign(InputStream template, InputStream csv) throws FileNotFoundException, IOException, EngineException, SemanticException
  {
    this.generateTempCSVFile(csv, TEMP_FILE_NAME);

    // Open report design
    IReportRunnable design = engine.openReportDesign(template);

    this.configureDataSet(design);

    return design;
  }

  public void generate(IReportRunnable design, OutputStream oStream) throws IOException, BirtException
  {
    // set output options
    IRenderOption options = new RenderOption();
    options.setOutputFormat(RenderOption.OUTPUT_FORMAT_PDF);
    options.setOutputStream(oStream);

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

  public void cleanup()
  {
    // Delete the temp file
    if (this.tempDirectory != null)
    {
      this.deleteTempDirectory(this.tempDirectory);
    }
  }

  @SuppressWarnings("unchecked")
  private void configureDataSet(IReportRunnable design) throws SemanticException, IOException
  {
    // Change the data source to the temporary csv directory
    ReportDesignHandle handle = (ReportDesignHandle) design.getDesignHandle();

    validateReportData(handle);

    for (Iterator i = handle.getDataSources().iterator(); i.hasNext();)
    {
      ( (DesignElementHandle) i.next() ).setProperty("HOME", this.tempDirectory);
    }

    // Update the data set to use the temporary csv file
    for (Iterator i = handle.getDataSets().iterator(); i.hasNext();)
    {
      DesignElementHandle dataset = (DesignElementHandle) i.next();
      String query = dataset.getStringProperty(DATA_SET_QUERY);

      Pattern pattern = Pattern.compile(QUERY_REGEX);
      Matcher matcher = pattern.matcher(query);

      matcher.find();

      StringBuffer result = new StringBuffer(matcher.group(1));
      result.append(TEMP_FILE_NAME);
      result.append(matcher.group(3));

      dataset.setProperty(DATA_SET_QUERY, result.toString());
    }
  }

  @SuppressWarnings("unchecked")
  private void validateReportData(ReportDesignHandle handle) throws IOException
  {
    if (handle.getAllDataSets().size() > 1)
    {
      throw new DataSetLimitExceptionDTO(this.clientRequest, this.locale);
    }

    if (handle.getAllDataSources().size() > 1)
    {
      throw new DataSourceLimitExceptionDTO(this.clientRequest, this.locale);
    }

    // Validate the csv structure against the report structure
    List<String> headers = new LinkedList<String>();
    CSVReader in = new CSVReader(new FileReader(new File(this.tempDirectory + File.separator + TEMP_FILE_NAME)), ',', '\"');

    try
    {
      for (String token : in.readNext())
      {
        headers.add(token.replaceAll("\"", "").trim());
      }
    }
    finally
    {
      in.close();
    }

    for (Iterator i = handle.getDataSources().iterator(); i.hasNext();)
    {
      String extensionID = ( (OdaDataSourceHandle) i.next() ).getExtensionID();

      if (!extensionID.equals(FLAT_FILE_EXTENSION))
      {
        throw new UnsupportedDataSourceExceptionDTO(this.clientRequest, this.locale);
      }
    }

    // Update the data set to use the temporary csv file
    for (Iterator i = handle.getDataSets().iterator(); i.hasNext();)
    {
      DesignElementHandle dataset = (DesignElementHandle) i.next();
      String query = dataset.getStringProperty(DATA_SET_QUERY);

      Pattern pattern = Pattern.compile(SELECTABLE_REGEX);
      Matcher matcher = pattern.matcher(query);

      matcher.find();

      String group = matcher.group(1);
      String[] selectables = new CSVReader(new StringReader(group)).readNext();

      for (String selectable : selectables)
      {
        selectable = selectable.replaceAll("\"", "");
        if (!headers.contains(selectable.trim()))
        {
          throw new QueryConfigurationExceptionDTO(this.clientRequest, this.locale);
        }
      }
    }
  }

  private void generateTempCSVFile(InputStream in, String fileName) throws FileNotFoundException, IOException
  {
    // Make the file structure
    new File(this.tempDirectory).mkdirs();

    File file = new File(this.tempDirectory + File.separator + fileName);
    file.deleteOnExit();

    FileIO.write(new BufferedOutputStream(new FileOutputStream(file)), in);
  }

  private void deleteTempDirectory(String file)
  {
    File directory = new File(file);

    try
    {
      FileIO.deleteDirectory(directory);
    }
    catch (IOException e)
    {
      throw new FileWriteExceptionDTO(directory, e);
    }
  }
}
