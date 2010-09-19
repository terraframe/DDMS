package dss.vector.solutions.admin.controller;

import java.io.File;
import java.lang.reflect.InvocationTargetException;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.operation.IRunnableWithProgress;

import com.runwaysdk.controller.IConfiguration;
import com.runwaysdk.controller.TransactionTaskListener;
import com.runwaysdk.dataaccess.transaction.TransactionImportManager;
import com.runwaysdk.session.Request;

public class ImportWorker implements IRunnableWithProgress
{
  private File            location;

  private IConfiguration  configuration;

  public ImportWorker(File location, IConfiguration configuration)
  {
    this.location = location;
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
    TransactionImportManager manager = new TransactionImportManager(location.getAbsolutePath());
    manager.addListener(new TransactionTaskListener(monitor));
    manager.setImportApplicationFiles(configuration.getImportApplicationFiles());
    manager.importTransactions();
    
    monitor.done();
  }
}