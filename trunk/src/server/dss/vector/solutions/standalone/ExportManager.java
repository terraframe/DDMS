package dss.vector.solutions.standalone;

import java.io.File;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

import javax.swing.SwingWorker;

import com.runwaysdk.constants.CommonProperties;
import com.runwaysdk.dataaccess.transaction.TransactionExportManager;
import com.runwaysdk.session.Request;

import dss.vector.solutions.InstallProperties;

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

  @Request
  private void export()
  {
    try
    {
      this.validate();

      StringTokenizer toke = new StringTokenizer(location.getName(), ".");

      String path = location.getParent();
      String fileName = toke.nextToken();

      List<String> files = this.getApplicationFiles();

      TransactionExportManager manager = new TransactionExportManager(files, CommonProperties.getTransactionRecordXMLschemaLocation(), fileName, path);
      manager.addListener(component);
      manager.setExportStoredApplicationFiles(!InstallProperties.isMaster());

      if (option.equals(ExportOption.ALL))
      {
        manager.export(0L);
      }
      else if (option.equals(ExportOption.RANGE))
      {
        manager.export(lower, upper);
      }
      else if (option.equals(ExportOption.NOT_IMPORTED))
      {
        manager.export();
      }
    }
    catch (Throwable t)
    {
      StandaloneClient.handleError(component, t);
    }

  }

  private List<String> getApplicationFiles()
  {
    List<String> files = new LinkedList<String>();

    if (InstallProperties.isMaster())
    {
      files.add(File.separator + "WEB-INF" + File.separator + "dss");
      files.add(File.separator + "js");
      files.add(File.separator + "css");
      files.add(File.separator + "imgs");
      files.add(File.separator + "WEB-INF" + File.separator + "classes" + File.separator + "com");
      files.add(File.separator + "WEB-INF" + File.separator + "classes" + File.separator + "dss");
      files.add(File.separator + "WEB-INF" + File.separator + "lib");
    }

    return files;
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

  @Override
  protected void done()
  {
    super.done();

    component.complete();
  }
}
