package dss.vector.solutions.report;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.logging.Logger;

import org.eclipse.birt.core.archive.IDocArchiveReader;
import org.eclipse.birt.core.archive.compound.ArchiveReader;
import org.eclipse.birt.report.engine.api.HTMLActionHandler;
import org.eclipse.birt.report.engine.api.IAction;
import org.eclipse.birt.report.engine.api.IReportDocument;
import org.eclipse.birt.report.engine.api.IReportEngine;
import org.eclipse.birt.report.engine.api.RenderOption;
import org.eclipse.birt.report.engine.api.script.IReportContext;

import com.runwaysdk.constants.LocalProperties;
import com.runwaysdk.generation.loader.Reloadable;

import dss.vector.solutions.util.BirtEngine;

public abstract class AbstractUrlActionHandler extends HTMLActionHandler implements Reloadable
{
  private static final String RPTDOCUMENT = ".rptdocument";

  private static final String RPTDESIGN   = ".rptdesign";

  /** logger */
  protected Logger            log         = Logger.getLogger(AbstractUrlActionHandler.class.getName());

  private IReportDocument     document;

  private RenderContext       context;

  public AbstractUrlActionHandler(IReportDocument document, RenderContext context)
  {
    this.document = document;
    this.context = context;
  }

  protected abstract String getDefaultFormat();

  /**
   * Get URL of the action.
   * 
   * @param actionDefn
   * @param context
   * @return URL
   */
  public String getURL(IAction action, IReportContext context)
  {
    if (action != null && action.getType() == IAction.ACTION_DRILLTHROUGH)
    {
      return this.buildDrillAction(action, context);
    }
    else if (action != null && action.getType() == IAction.ACTION_BOOKMARK)
    {
      return this.buildBookmarkAction(action, context);
    }

    return super.getURL(action, context);
  }

  public String buildBookmarkAction(IAction action, IReportContext context)
  {
    if (action.getReportName() != null)
    {
      StringBuffer buffer = new StringBuffer(this.buildDrillAction(action, context));
      this.appendBookmark(buffer, action.getBookmark());

      return buffer.toString();
    }
    else
    {
      StringBuffer buffer = this.getReportLink(this.context.getParameters());

      if (this.document != null)
      {
        long pageNumber = this.document.getPageNumber(action.getBookmark());

        this.appendParamter(buffer, RenderContext.PAGE_NUMBER, pageNumber);
      }

      this.appendBookmark(buffer, action.getBookmark());

      return buffer.toString();
    }
  }

  public String getURL(IAction action, Object context)
  {
    if (action != null && action.getType() == IAction.ACTION_DRILLTHROUGH)
    {
      return this.buildDrillAction(action, context);
    }

    return super.getURL(action, context);
  }

  /**
   * builds URL for drillthrough action
   * 
   * @param action
   *          instance of the IAction instance
   * @param context
   *          the context for building the action string
   * @return a URL
   */
  protected String buildDrillAction(IAction action, Object context)
  {
    ReportItem item = this.getReportItem(action);

    StringBuffer buffer = new StringBuffer();
    buffer.append(this.context.getBaseUrl() + "/" + item.getRunURL());

    // Adds the parameters
    Map<String, Object> parameters = this.getParameterMap(action);

    if (parameters != null)
    {
      Set<Entry<String, Object>> entries = parameters.entrySet();

      for (Entry<String, Object> entry : entries)
      {
        String parameterKey = (String) entry.getKey();
        Object parameterValue = entry.getValue();

        if (parameterValue != null && parameterValue instanceof List)
        {
          List<?> list = (List<?>) parameterValue;

          this.appendParamter(buffer, parameterKey, list);
        }
        else
        {
          this.appendParamter(buffer, parameterKey, parameterValue);
        }
      }
    }

    if (action.getBookmark() != null)
    {
      /*
       * Important: Not only do we need to append the bookmark to the link we must
       * also find the page number the bookmark appears on and append the calculated
       * page number to the link. The only way to find the actual page number is to
       * process the design and produce a rptdocument file.
       */
      try
      {
        File archive = item.getReportDocument(new RenderContext(item, this.context.getBaseUrl(), parameters));
        IDocArchiveReader reader = new ArchiveReader(archive.getAbsolutePath());

        IReportEngine engine = BirtEngine.getBirtEngine(LocalProperties.getLogDirectory());
        IReportDocument document = engine.openReportDocument(item.getReportName(), reader, new HashMap<Object, Object>());

        long pageNumber = document.getPageNumber(action.getBookmark());
        this.appendParamter(buffer, RenderContext.PAGE_NUMBER, pageNumber);
        this.appendBookmark(buffer, action.getBookmark());
      }
      catch (Exception e)
      {
        throw new RuntimeException(e);
      }
    }

    return buffer.toString();
  }

  @SuppressWarnings("unchecked")
  private Map<String, Object> getParameterMap(IAction action)
  {
    Map<String, Object> parameters = (Map<String, Object>) action.getParameterBindings();

    if (parameters != null)
    {
      Set<Entry<String, Object>> entries = parameters.entrySet();

      for (Entry<String, Object> entry : entries)
      {
        Object parameterValue = entry.getValue();

        if (parameterValue != null && parameterValue instanceof List)
        {
          List<?> list = (List<?>) parameterValue;

          if (list.size() == 1)
          {
            entry.setValue(list.get(0));
          }
        }
      }

      return parameters;
    }

    return null;
  }

  protected ReportItem getReportItem(IAction action)
  {
    String reportPath = this.getReportPath(action);

    if (reportPath != null && reportPath.length() > 0)
    {
      String reportName = new File(reportPath).getName();

      if (! ( reportName.endsWith(RPTDESIGN) || reportName.endsWith(RPTDOCUMENT) ))
      {
        InvalidDrillThroughFileException exception = new InvalidDrillThroughFileException("Drill through report must end in .rptdesign or .rptdocument");
        exception.apply();

        throw exception;
      }

      if (reportName.endsWith(RPTDOCUMENT))
      {
        reportName = reportName.replace(RPTDOCUMENT, RPTDESIGN);
      }

      /*
       * Get the report item from the name of the report and the output format
       */
      String format = this.getFormat(action);

      ReportItem item = ReportItem.find(reportName, this.getOutputFormat(format));

      if (item != null)
      {
        return item;
      }
      else
      {
        String message = "Unable to find a report in the system with the report name [" + reportName + "] and output format [" + format + "]";

        UnknownReportException e = new UnknownReportException(message);
        e.setReportName(reportName);
        e.setFormat(format);

        throw e;
      }
    }
    else
    {
      String message = "Invalid drill through report definition.  No sub report has been defined.";

      InvalidReportDefinitionException e = new InvalidReportDefinitionException(message);
      e.apply();

      throw e;
    }
  }

  public OutputFormat getOutputFormat(String format)
  {
    if (format.equals(RenderOption.OUTPUT_FORMAT_PDF))
    {
      return OutputFormat.PDF;
    }
    else if (format.equals(RenderOption.OUTPUT_FORMAT_HTML))
    {
      return OutputFormat.HTML;
    }

    UnsupportedOutputFormatException e = new UnsupportedOutputFormatException("Unknown output format type");
    e.apply();

    throw e;
  }

  private String getFormat(IAction action)
  {
    String format = action.getFormat();

    if (format == null || format.length() == 0)
    {
      return this.getDefaultFormat();
    }
    else if (! ( format.equals(RenderOption.OUTPUT_FORMAT_HTML) || format.equals(RenderOption.OUTPUT_FORMAT_PDF) ))
    {
      String message = "Unsupported drill through report format [" + format + "]";

      UnsupportedDrillThroughFormatException e = new UnsupportedDrillThroughFormatException(message);
      e.setOutputFormat(format);
      e.apply();

      throw e;
    }

    return format;
  }

  public StringBuffer getReportLink(Map<String, Object> parameters)
  {
    StringBuffer link = new StringBuffer(this.context.getBaseUrl() + "/" + ReportItem.ROOT_RUN_URL);
    Set<Entry<String, Object>> entries = parameters.entrySet();

    for (Entry<String, Object> entry : entries)
    {
      String parameterKey = entry.getKey();
      Object parameterValue = entry.getValue();

      if (!parameterKey.equals(RenderContext.PAGE_NUMBER))
      {
        if (parameterValue != null && parameterValue instanceof List)
        {
          List<?> list = (List<?>) parameterValue;

          if (list.size() == 1)
          {
            this.appendParamter(link, parameterKey, list.get(0));
          }
          else
          {
            this.appendParamter(link, parameterKey, list);
          }
        }
        else
        {
          this.appendParamter(link, parameterKey, parameterValue);
        }
      }
    }

    return link;
  }

  /**
   * Get report name.
   * 
   * @param action
   * @return
   */
  @SuppressWarnings("deprecation")
  protected String getReportPath(IAction action)
  {
    String systemId = action.getSystemId();
    String reportName = action.getReportName();

    if (systemId == null)
    {
      return reportName;
    }

    if (reportName == null)
    {
      return null;
    }

    // if the reportName is an URL, use it directly
    try
    {
      URL url = new URL(reportName);

      if ("file".equals(url.getProtocol()))
      {
        return url.getFile();
      }

      return url.toExternalForm();
    }
    catch (MalformedURLException ex)
    {
      // DO NOTHING
    }

    // if the system id is the URL, merge the report name with it
    try
    {
      URL root = new URL(systemId);
      URL url = new URL(root, reportName);

      if ("file".equals(url.getProtocol()))
      {
        return url.getFile();
      }

      return url.toExternalForm();
    }
    catch (MalformedURLException ex)
    {
      // DO NOTHING
    }

    // now the root should be a file and the report name is a file also
    File file = new File(reportName);

    if (file.isAbsolute())
    {
      return reportName;
    }

    try
    {
      URL root = new File(systemId).toURL();
      URL url = new URL(root, reportName);

      return url.getFile();
    }
    catch (MalformedURLException ex)
    {
      // DO NOTHING
    }
    return reportName;
  }
}
