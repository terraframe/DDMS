package dss.vector.solutions.standalone;

import java.io.File;
import java.util.StringTokenizer;

import javax.swing.SwingWorker;

import com.terraframe.mojo.constants.CommonProperties;
import com.terraframe.mojo.dataaccess.transaction.TransactionExportManager;
import com.terraframe.mojo.session.StartSession;

public class ExportManager extends SwingWorker<Void, Void>
{
  private ExportPanel  component;

  private ExportOption option;

  private Long         lower;

  private Long         upper;

  private File         location;

  public ExportManager(ExportPanel component)
  {
    this.option = ExportOption.ALL;
    this.location = null;
    this.component = component;
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

  @StartSession
  private void export()
  {
    try
    {
      this.validate();

      StringTokenizer toke = new StringTokenizer(location.getName(), ".");

      String path = location.getParent();
      String fileName = toke.nextToken();

      if (option.equals(ExportOption.RANGE))
      {
        if (upper != null)
        {
          TransactionExportManager.export(lower, CommonProperties.getTransactionXMLschemaLocation(), fileName, path, component);
        }
        else
        {
          TransactionExportManager.export(lower, upper, CommonProperties.getTransactionXMLschemaLocation(), fileName, path, component);
        }
      }
      else if (option.equals(ExportOption.ALL))
      {
        TransactionExportManager.export(0L, CommonProperties.getTransactionXMLschemaLocation(), fileName, path, component);
      }
      else if (option.equals(ExportOption.NOT_IMPORTED))
      {
        TransactionExportManager.export(CommonProperties.getTransactionRecordXMLschemaLocation(), fileName, path, component);
      }
    }
    catch (Throwable t)
    {
      component.handleError(t);
    }

  }

  private void validate()
  {
    if (location == null)
    {
      String msg = "Please select a location in which to export the transactions to.";
      throw new ExportLocationException(msg);
    }

    if (option.equals(ExportOption.RANGE))
    {
      if (lower == null)
      {        
        String msg = "Please input a starting sequence";        
        throw new StartingExportSequenceException(msg);
      }
      else if (upper != null && lower > upper)
      {
        String msg = "The end sequence number must be greater than the start sequence number";

        ExportSequenceException e = new ExportSequenceException(msg);
        e.setStartSequence(lower);
        e.setEndSequence(upper);
        e.apply();

        throw e;
      }
    }

  }

  @Override
  protected Void doInBackground() throws Exception
  {
    this.export();

    return null;
  }
}
