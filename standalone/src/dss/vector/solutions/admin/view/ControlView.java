package dss.vector.solutions.admin.view;

import java.io.File;
import java.lang.reflect.InvocationTargetException;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.part.ViewPart;

import com.runwaysdk.manager.general.Localizer;
import com.runwaysdk.manager.view.IViewPart;

import dss.vector.solutions.admin.controller.IControllerListener;
import dss.vector.solutions.admin.controller.IModuleController;

public class ControlView extends ViewPart implements IViewPart, IControllerListener
{
  private static final Point  DIMENSION        = new Point(500, 400);

  /**
   * 
   */
  private static final long   serialVersionUID = -6658235304142152484L;

  private static final String START            = "start";

  private static final String STOP             = "stop";

  private static final String BACKUP           = "backup";

  private static final String RESTORE          = "restore";

  private static final String TIMEOUT          = "timeout";

  private Button              startButton;

  private Button              stopButton;

  private Button              backupButton;

  private Button              restoreButton;

  private Button              refreshButton;

  private Text                timeoutField;

  private Composite           composite;

  private IModuleController   controller;

  public ControlView(IModuleController controller)
  {
    this.controller = controller;
    this.controller.addListener(this);
  }

  @Override
  public void createPartControl(Composite parent)
  {
    composite = new Composite(parent, SWT.FILL);
    composite.setSize(DIMENSION);
    composite.setLayout(new FormLayout());

    Composite timeoutComposite = new Composite(composite, SWT.NONE);
    timeoutComposite.setLayout(new FillLayout());
    timeoutComposite.setLayoutData(new FormData(400, 20));

    Label label = new Label(timeoutComposite, SWT.LEFT);
    label.setText(Localizer.getMessage(TIMEOUT) + " ");

    timeoutField = new Text(timeoutComposite, SWT.BORDER);
    timeoutField.setText(this.controller.getTimeout().toString());

    FormData data = new FormData();
    data.top = new FormAttachment(timeoutComposite);

    Composite buttons = new Composite(composite, SWT.FILL);
    buttons.setLayout(new FillLayout());
    buttons.setLayoutData(data);

    startButton = new Button(buttons, SWT.BORDER);
    startButton.setText(Localizer.getMessage(START));
    startButton.addListener(SWT.Selection, new Listener()
    {
      @Override
      public void handleEvent(Event arg0)
      {
        String timeout = timeoutField.getText();

        try
        {
          controller.start(Integer.parseInt(timeout));
        }
        catch (Exception e)
        {
          MessageDialog.openError(composite.getShell(), Localizer.getMessage("ERROR_TITLE"), e.getLocalizedMessage());
        }
      }
    });

    stopButton = new Button(buttons, SWT.BORDER);
    stopButton.setText(Localizer.getMessage(STOP));
    stopButton.addListener(SWT.Selection, new Listener()
    {
      @Override
      public void handleEvent(Event arg0)
      {
        controller.stop();
      }
    });

    backupButton = new Button(buttons, SWT.BORDER);
    backupButton.setText(Localizer.getMessage(BACKUP));
    backupButton.addListener(SWT.Selection, new Listener()
    {
      @Override
      public void handleEvent(Event arg0)
      {
        if (!controller.isServerUp())
        {
          FileDialog dialog = new FileDialog(composite.getShell(), SWT.SAVE);
          String file = dialog.open();

          if (file != null && file.length() > 0)
          {
            controller.backup(new File(file));
          }
        }
        else
        {
          error(Localizer.getMessage("TOMCAT_ERROR"));
        }
      }
    });

    restoreButton = new Button(buttons, SWT.BORDER);
    restoreButton.setText(Localizer.getMessage(RESTORE));
    restoreButton.addListener(SWT.Selection, new Listener()
    {
      @Override
      public void handleEvent(Event arg0)
      {
        if (!controller.isServerUp())
        {
          FileDialog dialog = new FileDialog(composite.getShell());
          String file = dialog.open();

          if (file != null && file.length() > 0)
          {
            controller.restore(new File(file));
          }
        }
        else
        {
          error(Localizer.getMessage("TOMCAT_ERROR"));
        }
      }
    });

    refreshButton = new Button(buttons, SWT.BORDER);
    refreshButton.setText(Localizer.getMessage("REFRESH"));
    refreshButton.addListener(SWT.Selection, new Listener()
    {
      @Override
      public void handleEvent(Event arg0)
      {
        controller.refresh();
      }
    });

    this.disableButtons();
    this.setButtons();
  }

  @Override
  public void setFocus()
  {
    timeoutField.setFocus();
  }

  private void setButtons(boolean started)
  {
    startButton.setEnabled(!started);
    stopButton.setEnabled(started);
    backupButton.setEnabled(!started);
    restoreButton.setEnabled(!started);
  }

  private void disableButtons()
  {
    startButton.setEnabled(false);
    stopButton.setEnabled(false);
    backupButton.setEnabled(false);
    restoreButton.setEnabled(false);
  }

  void setButtons()
  {
    controller.refresh();
  }

  @Override
  public Control getWidget()
  {
    return composite;
  }

  @Override
  public void beforeServerStateChange(boolean state)
  {
    this.disableButtons();
  }

  @Override
  public void error(final String msg)
  {
    Runnable runnable = new Runnable()
    {
      @Override
      public void run()
      {
        MessageDialog.openError(composite.getShell(), Localizer.getMessage("ERROR_TITLE"), msg);
      }
    };

    this.asyncExec(runnable);
  }

  @Override
  public void message(final String msg)
  {
    Runnable runnable = new Runnable()
    {
      @Override
      public void run()
      {
        MessageDialog.openInformation(composite.getShell(), Localizer.getMessage("MESSAGE_TITLE"), msg);
      }
    };

    this.asyncExec(runnable);
  }

  @Override
  public void execute(IRunnableWithProgress runnable) throws InvocationTargetException, InterruptedException
  {
    Shell shell = this.getWidget().getShell();

    ProgressMonitorDialog dialog = new ProgressMonitorDialog(shell);
    dialog.run(true, false, runnable);
  }

  @Override
  public synchronized void serverStateChange(final boolean state)
  {
    Runnable runnable = new Runnable()
    {
      @Override
      public void run()
      {
        setButtons(state);
      }
    };

    this.asyncExec(runnable);
  }

  private void asyncExec(Runnable runnable)
  {
    Display display = composite.getDisplay();
    display.asyncExec(runnable);
  }

}
