package dss.vector.solutions.standalone;

import java.io.File;

import javax.swing.SwingWorker;

import com.terraframe.mojo.constants.CommonProperties;
import com.terraframe.mojo.dataaccess.transaction.TransactionImportManager;
import com.terraframe.mojo.session.StartSession;

public class ImportManager extends SwingWorker<Void, Void>
{
  private File file;
  
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
        throw new RuntimeException("Provide a file to import.");
      }
      
      TransactionImportManager.importTransactions(CommonProperties.getTransactionXMLschemaLocation(), file.getAbsolutePath(), component);
    }
    catch (Exception e)
    {
      component.handleError(e);
    }
  }

}
