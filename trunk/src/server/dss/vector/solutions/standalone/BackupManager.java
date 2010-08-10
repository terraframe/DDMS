package dss.vector.solutions.standalone;

import java.io.File;
import java.io.PrintStream;

import javax.swing.JTextArea;
import javax.swing.SwingWorker;

import com.runwaysdk.dataaccess.io.Backup;
import com.runwaysdk.session.Request;

public class BackupManager extends SwingWorker<Void, Void>
{
  private ControlPanel component;

  private File        file;

  public BackupManager(ControlPanel component, File file)
  {
    this.component = component;
    this.file = file;
  }

  @Request
  protected Void doInBackground() throws Exception
  {
    try
    {
      JTextArea textArea = component.getTextArea();
      textArea.setText(null);
      TextAreaOutputStream out = new TextAreaOutputStream(textArea);
      PrintStream print = new PrintStream(out, true);

      Backup backup = new Backup(print, file.getName(), file.getParent(), true, true);
      backup.backup();

      print.close();

      component.unlockContainer();
    }
    catch (Exception e)
    {
      StandaloneClient.handleError(component, e);
    }

    return null;
  }

}
