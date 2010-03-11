package dss.vector.solutions.standalone;

import java.io.File;
import java.util.StringTokenizer;

import com.terraframe.mojo.constants.CommonProperties;
import com.terraframe.mojo.dataaccess.transaction.TransactionExportManager;

public class ExportManager
{
  private ExportOption option;

  private Long         lower;

  private Long         upper;

  private File         location;

  public ExportManager()
  {
    this.option = ExportOption.ALL;
    this.location = null;
  }

  public ExportOption getOption()
  {
    return option;
  }

  public void setOption(ExportOption option)
  {
    this.option = option;
  }

  public long getLower()
  {
    return lower;
  }

  public void setLower(String lower)
  {
    if (lower != null && lower.length() > 0)
    {
      this.lower = Long.parseLong(lower);
    }
  }

  public long getUpper()
  {
    return upper;
  }

  public void setUpper(String upper)
  {
    if (upper != null && upper.length() > 0)
    {
      this.upper = Long.parseLong(upper);
    }
  }

  public File getLocation()
  {
    return location;
  }

  public void setLocation(File location)
  {
    this.location = location;
  }

  public void excute()
  {
    this.validate();
    
    StringTokenizer toke = new StringTokenizer(location.getName(), ".");

    String path = location.getParent();
    String fileName = toke.nextToken();

    if (option.equals(ExportOption.RANGE))
    {
      if (upper != null)
      {
        TransactionExportManager.export(lower, CommonProperties.getTransactionXMLschemaLocation(), fileName, path);
      }
      else
      {
        TransactionExportManager.export(lower, upper, CommonProperties.getTransactionXMLschemaLocation(), fileName, path);
      }
    }
    else if (option.equals(ExportOption.ALL))
    {
      TransactionExportManager.export(0L, CommonProperties.getTransactionXMLschemaLocation(), fileName, path);
    }
  }

  private void validate()
  {
    if (location == null)
    {
      throw new RuntimeException("Please select a location in which to export the transactions to.");
    }
    else if (location.isDirectory())
    {
      throw new RuntimeException("Please select a file not a directory");      
    }

    if (option.equals(ExportOption.RANGE))
    {
      if (lower == null)
      {
        throw new RuntimeException("Please input a starting sequence");
      }
      else if (upper != null && lower > upper)
      {
        throw new RuntimeException("Please input a the end sequence number must be higher then the start sequence number");
      }
    }

  }
}
