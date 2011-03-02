package dss.vector.solutions.admin.shapefile;

import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.jface.wizard.WizardPage;

import com.runwaysdk.manager.controller.TransactionTaskListener;
import com.runwaysdk.manager.general.Localizer;

import dss.vector.solutions.TransactionExecuter;

public class ShapeFileWizard extends Wizard
{
  private ShapeFileBean data;

  private boolean       importing;

  public ShapeFileWizard()
  {
    this.data = new ShapeFileBean();
    this.importing = false;

    setWindowTitle(Localizer.getMessage("SHAPE_FILE_WIZARD"));
    setNeedsProgressMonitor(true);
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.eclipse.jface.wizard.IWizard#addPages()
   */
  public void addPages()
  {
    addPage(new ShapeFilePage(data));
    addPage(new ShapeFileAttributePage(data));
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
          try
          {
            new TransactionExecuter()
            {

              @Override
              protected void executeMethod() throws Exception
              {
                ShapefileImporterFacade facade = new ShapefileImporterFacade(data.getShapeFile());
                facade.addListener(new TransactionTaskListener(monitor));
                facade.setValues(data);
                facade.run();
              }
            }.execute();
          }
          catch (RuntimeException e)
          {
            throw e;
          }
          catch (Exception e)
          {
            throw new RuntimeException(e);
          }
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

  public ShapeFileBean getData()
  {
    return data;
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