package dss.vector.solutions.initializer;

import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.widgets.Shell;

public class LocalizedProgressMonitorDialog extends ProgressMonitorDialog
{

  public LocalizedProgressMonitorDialog(Shell parent)
  {
    super(parent);
  }

  @Override
  protected void configureShell(Shell shell)
  {
    super.configureShell(shell);
    
    shell.setText(Localizer.getMessage("TITLE"));
    shell.setImage(ImageDescriptor.createFromURL(Object.class.getResource("/icons/information.png")).createImage());
  }
  
}
