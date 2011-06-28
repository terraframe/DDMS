package dss.vector.solutions.gis;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Monitor;
import org.eclipse.swt.widgets.ProgressBar;
import org.eclipse.swt.widgets.Shell;

import com.runwaysdk.dataaccess.cache.ObjectCache;
import com.runwaysdk.dataaccess.transaction.ITaskListener;

public class Splash implements ITaskListener
{
  private Shell       shell;

  private ProgressBar progressBar;

  private Label       label;

  private int         current;

  public Splash(Monitor monitor)
  {
    FormData labelData = new FormData();
    labelData.left = new FormAttachment(0, 5);
    labelData.bottom = new FormAttachment(100, -80);

    FormData progressData = new FormData();
    progressData.left = new FormAttachment(0, 5);
    progressData.right = new FormAttachment(100, -5);
    progressData.bottom = new FormAttachment(100, -5);

    shell = new Shell(SWT.ON_TOP);
    shell.setSize(300, 100);
    shell.setLayout(new FormLayout());

    label = new Label(shell, SWT.CENTER);
    label.setText(Localizer.getMessage("INIT"));
    label.setLayoutData(labelData);

    progressBar = new ProgressBar(shell, SWT.HORIZONTAL);
    progressBar.setLayoutData(progressData);
    progressBar.setMinimum(0);
    progressBar.setMaximum(100);
    progressBar.setSelection(0);

    Rectangle splashRect = shell.getBounds();
    Rectangle displayRect = monitor.getBounds();

    int x = ( displayRect.width - splashRect.width ) / 2;
    int y = ( displayRect.height - splashRect.height ) / 2;

    shell.setLocation(x, y);
  }

  public void open()
  {
    ObjectCache.addListener(this);

    this.shell.open();
  }

  public void close()
  {
    ObjectCache.removeListener(this);

    this.shell.close();
  }

  @Override
  public void done(boolean status)
  {
    // Do nothing
  }

  @Override
  public void start()
  {
    // Do nothing
  }

  @Override
  public void taskProgress(int amount)
  {
    this.progressBar.setSelection(current += amount);
  }

  @Override
  public void taskStart(String name, int totalAmount)
  {
    this.progressBar.setMinimum(0);
    this.progressBar.setMaximum(totalAmount);
    this.progressBar.setSelection(0);
  }
  
  public Shell getShell()
  {
    return shell;
  }
  
  public boolean isDisposed()
  {
    return this.shell.isDisposed();
  }

}
