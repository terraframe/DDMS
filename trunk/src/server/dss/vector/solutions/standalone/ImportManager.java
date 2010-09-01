package dss.vector.solutions.standalone;

import java.io.File;
import java.util.Locale;

import javax.swing.SwingWorker;

import com.runwaysdk.dataaccess.transaction.TransactionImportManager;
import com.runwaysdk.session.Request;

import dss.vector.solutions.InstallProperties;
import dss.vector.solutions.util.MDSSProperties;

public class ImportManager extends SwingWorker<Void, Void>
{
  private File        file;

  private ImportPanel component;

  private Locale      locale;

  public ImportManager(File file, ImportPanel panel, Locale locale)
  {
    this.file = file;
    this.component = panel;
    this.locale = locale;
  }

  @Override
  protected Void doInBackground() throws Exception
  {
    this.importFile();

    component.complete();

    return null;
  }

  @Request
  private void importFile()
  {
    try
    {

      if (file == null || !file.exists() || file.isDirectory())
      {
        throw new RuntimeException(MDSSProperties.getString("Provide_Import_File", locale));
      }

      TransactionImportManager manager = new TransactionImportManager(file.getAbsolutePath());

      // IMPORTANT: We do not want to import the application files if this
      // is the master server. The master server is the authorative copy
      // of the application file artifacts.
      manager.setImportApplicationFiles(!InstallProperties.isMaster());

      manager.addListener(component);
      manager.importTransactions();

    }
    catch (Exception e)
    {
      component.handleError(e);
    }
  }

}
