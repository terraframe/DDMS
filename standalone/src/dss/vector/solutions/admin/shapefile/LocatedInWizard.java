package dss.vector.solutions.admin.shapefile;

import java.lang.reflect.InvocationTargetException;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.jface.wizard.WizardPage;

import com.runwaysdk.manager.controller.TransactionTaskListener;
import com.runwaysdk.manager.general.Localizer;

public class LocatedInWizard extends Wizard
{
  private LocatedInBean bean;

  private boolean       importing;

  public LocatedInWizard()
  {
    setWindowTitle(Localizer.getMessage("LOCATED_IN_WIZARD"));
    setNeedsProgressMonitor(true);

    this.bean = new LocatedInBean();
    this.importing = false;
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.eclipse.jface.wizard.IWizard#addPages()
   */
  public void addPages()
  {
    addPage(new LocatedInPage(bean));
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.eclipse.jface.wizard.IWizard#performFinish()
   */
  public boolean performFinish()
  {
    try
    {
      // puts the data into a database ...
      getContainer().run(true, true, new IRunnableWithProgress()
      {
        public void run(final IProgressMonitor monitor) throws InvocationTargetException, InterruptedException
        {
          setImporting(true);

          ShapeFileFacade facade = new ShapeFileFacade();

          facade.buildLocatedIn(bean, new TransactionTaskListener(monitor));
        }
      });
    }
    catch (InvocationTargetException e)
    {
      this.getLastPage().setErrorMessage(e.getCause().getLocalizedMessage());
      this.setImporting(false);

      return false;
    }
    catch (InterruptedException e)
    {
      this.getLastPage().setErrorMessage(e.getLocalizedMessage());
      this.setImporting(false);

      return false;

    }

    return true;
  }

  public WizardPage getLastPage()
  {
    return (WizardPage) this.getPage(ShapeFileAttributePage.PAGE_NAME);
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.eclipse.jface.wizard.IWizard#performCancel()
   */
  public boolean performCancel()
  {
    if (!this.isImporting())
    {
      return MessageDialog.openConfirm(getShell(), Localizer.getMessage("CONFIRMATION"), Localizer.getMessage("CANCEL_MESSAGE"));
    }

    return false;
  }

  public LocatedInBean getData()
  {
    return bean;
  }

  private synchronized void setImporting(boolean importing)
  {
    this.importing = importing;
  }

  private synchronized boolean isImporting()
  {
    return importing;
  }
}
