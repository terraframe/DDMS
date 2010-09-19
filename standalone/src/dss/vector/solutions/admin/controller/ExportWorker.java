package dss.vector.solutions.admin.controller;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.StringTokenizer;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.operation.IRunnableWithProgress;

import com.runwaysdk.constants.CommonProperties;
import com.runwaysdk.controller.IConfiguration;
import com.runwaysdk.controller.IExportStrategy;
import com.runwaysdk.controller.TransactionTaskListener;
import com.runwaysdk.dataaccess.transaction.TransactionExportManager;
import com.runwaysdk.dataaccess.transaction.TransactionImportManager;
import com.runwaysdk.session.Request;

public class ExportWorker implements IRunnableWithProgress
{
  private File            location;

  private IConfiguration  configuration;
  
  private IExportStrategy strategy;

  public ExportWorker(File location, IExportStrategy strategy, IConfiguration configuration)
  {
    this.location = location;
    this.strategy = strategy;
    this.configuration = configuration;
  }

  public File getLocation()
  {
    return location;
  }

  public void setLocation(File location)
  {
    this.location = location;
  }

  @Override
  @Request
  public void run(final IProgressMonitor monitor) throws InvocationTargetException, InterruptedException
  {
    StringTokenizer toke = new StringTokenizer(location.getName(), ".");

    String path = location.getParent();
    String fileName = toke.nextToken();

    List<String> files = configuration.getExportApplicationFiles();

    TransactionExportManager manager = new TransactionExportManager(files, CommonProperties.getTransactionRecordXMLschemaLocation(), fileName, path);    
    manager.addListener(new TransactionTaskListener(monitor));
    manager.setExportStoredApplicationFiles(configuration.getExportStoredApplicationFiles());
    strategy.execute(manager);
    
    monitor.done();
  }
}