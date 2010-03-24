package dss.vector.solutions.standalone;

import java.io.File;

import javax.swing.SwingWorker;

import com.runwaysdk.dataaccess.transaction.TransactionImportManager;
import com.runwaysdk.session.StartSession;

import dss.vector.solutions.InstallProperties;
import dss.vector.solutions.util.MDSSProperties;

public class ImportManager extends SwingWorker<Void, Void>
{
  private File        file;

  private ImportPanel component;

  public ImportManager(File file, ImportPanel panel)
  {
    this.file = file;
    this.component = panel;
  }

  @Override
  protected Void doInBackground() throws Exception
  {
    this.importFile();

    return null;
  }

  @StartSession
  private void importFile()
  {
    try
    {

      if (file == null || !file.exists() || file.isDirectory())
      {
        throw new RuntimeException(MDSSProperties.getString("Provide_Import_File"));
      }

      TransactionImportManager manager = new TransactionImportManager(file.getAbsolutePath());
      
      // IMPORTANT: We do not want to import the application files if this
      // is the master server.  The master server is the authorative copy
      // of the application file artifacts.
      manager.setImportApplicationFiles(!InstallProperties.isMaster());

      manager.addPropertyChangeListener(component);
      manager.importTransactions();

    }
    catch (Exception e)
    {
      component.handleError(e);
    }
  }

}
