package dss.vector.solutions.standalone;

import java.io.File;
import java.io.PrintStream;

import javax.swing.JTextArea;
import javax.swing.SwingWorker;

import com.runwaysdk.dataaccess.io.Restore;
import com.runwaysdk.session.Request;

public class RestoreManager extends SwingWorker<Void, Void>
{
  private ControlPanel component;

  private File        file;

  public RestoreManager(ControlPanel component, File file)
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

      Restore restore = new Restore(print, file.getAbsolutePath());
      restore.restore();

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
