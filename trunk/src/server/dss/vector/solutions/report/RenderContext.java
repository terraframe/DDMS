package dss.vector.solutions.report;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import com.runwaysdk.generation.loader.Reloadable;

import dss.vector.solutions.MdssLog;

public class RenderContext implements Reloadable
{
  public static final String  PAGE_NUMBER = "pageNumber";

  public static final String  FORMAT      = "format";

  private String              format;

  private String              baseUrl;

  private Map<String, Object> parameters;

  public RenderContext(ReportItem item, String baseUrl, Map<String, Object> parameters)
  {
    this.baseUrl = baseUrl;
    this.parameters = parameters;
    this.format = this.getFormat(item, parameters);
  }

  public String getFormat()
  {
    return this.format;
  }

  public String getBaseUrl()
  {
    return this.baseUrl;
  }

  public Map<String, Object> getParameters()
  {
    return this.parameters;
  }

  private String getFormat(ReportItem item, Map<String, Object> parameters)
  {
    if (parameters.containsKey(FORMAT))
    {
      return (String) parameters.get(FORMAT);
    }

    return item.getOutputFormat().get(0).name();
  }

  public long getPageNumber()
  {
    if (parameters.containsKey(PAGE_NUMBER))
    {
      String value = (String) this.parameters.get(PAGE_NUMBER);

      try
      {
        return Long.parseLong(value);
      }
      catch (Exception e)
      {
        /*
         * Unable to parse the pageNumber: log the error and just return the first page
         */
        MdssLog.error("Unable to parse the value [" + value + "] as a long.", e);
      }
    }
    return 1;
  }

  public boolean hasPageNumber()
  {
    return this.parameters.containsKey(PAGE_NUMBER);
  }

  public int getReportHash(String reportName)
  {
    StringBuffer key = new StringBuffer();
    key.append(reportName);

    Iterator<Entry<String, Object>> iterator = parameters.entrySet().iterator();

    while (iterator.hasNext())
    {
      Entry<String, Object> entry = iterator.next();

      if (this.isValidParameter(entry))
      {
        key.append(entry.getKey() + "-" + entry.getValue());
      }
    }

    return key.toString().hashCode();
  }

  private boolean isValidParameter(Entry<String, Object> parameter)
  {
    if (parameter.getKey().equals(PAGE_NUMBER))
    {
      return false;
    }

    return true;
  }

}
