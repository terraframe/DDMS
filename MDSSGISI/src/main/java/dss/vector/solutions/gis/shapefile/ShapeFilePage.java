package dss.vector.solutions.gis.shapefile;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Text;

import com.runwaysdk.generation.loader.Reloadable;

import dss.vector.solutions.gis.Localizer;
import dss.vector.solutions.gis.GISException;
import dss.vector.solutions.gis.IInitPage;

public class ShapeFilePage extends WizardPage implements Reloadable
{
  class FileSelection extends MouseAdapter implements Reloadable
  {
    @Override
    public void mouseDown(MouseEvent e)
    {
      openFileDialog();
    }
  }

  class FileListener implements Listener, Reloadable
  {
    @Override
    public void handleEvent(Event arg0)
    {
      openFileDialog();
    }
  }

  public static final String  PAGE_NAME = "ShapeFilePage";

  private Text                fileText;

  private final ShapeFileBean data;

  public ShapeFilePage(ShapeFileBean data)
  {
    super(PAGE_NAME);
    setTitle(Localizer.getMessage("SHAPE_FILE_PAGE"));
    setDescription(Localizer.getMessage("SHAPE_FILE_DESC"));
    setPageComplete(false);

    this.data = data;
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * org.eclipse.jface.dialogs.IDialogPage#createControl(org.eclipse.swt.widgets
   * .Composite)
   */
  public void createControl(Composite parent)
  {
    Composite composite = new Composite(parent, SWT.NULL);
    composite.setLayout(new FormLayout());

    Label label = new Label(composite, SWT.NULL);
    label.setText(Localizer.getMessage("SHAPE_FILE") + ": ");
    label.setLayoutData(new FormData());

    FormData compositeData = new FormData();
    compositeData.left = new FormAttachment(label, 5);
    compositeData.right = new FormAttachment(100, -5);

    Composite fileComposite = new Composite(composite, SWT.BORDER);
    fileComposite.setLayout(new FormLayout());
    fileComposite.setLayoutData(compositeData);

    FormData fileTextData = new FormData(SWT.DEFAULT, 20);
    fileTextData.left = new FormAttachment(0, 0);
    fileTextData.right = new FormAttachment(70, 0);

    fileText = new Text(fileComposite, SWT.BORDER);
    fileText.setLayoutData(fileTextData);
    fileText.addMouseListener(new FileSelection());

    FormData fileButtonData = new FormData();
    fileButtonData.left = new FormAttachment(fileText);
    fileButtonData.right = new FormAttachment(100, 0);

    Button fileButton = new Button(fileComposite, SWT.BORDER);
    fileButton.setLayoutData(fileButtonData);
    fileButton.setText(Localizer.getMessage("SELECT_FILE"));
    fileButton.addListener(SWT.Selection, new FileListener());

    setControl(composite);
  }

  private void openFileDialog()
  {
    FileDialog dialog = new FileDialog(getShell(), SWT.OPEN);
    dialog.setFilterNames(new String[] { Localizer.getMessage("SHAPE_FILES"), Localizer.getMessage("ALL_FILES") });
    dialog.setFilterExtensions(new String[] { "*.shp", "*.*" });

    String location = dialog.open();

    if (location != null && location.length() > 0)
    {
      this.setShapefile(location);
    }
  }

  private void setShapefile(String location)
  {
    fileText.setText(location);

    data.setShapeFile(new File(location));

    if (data.getShapeFile() != null)
    {
      processShapefile();
    }

    readyNextPage();
  }

  private void processShapefile()
  {
    class ProcessShapefileRunner implements IRunnableWithProgress, Reloadable
    {
      public void run(final IProgressMonitor monitor) throws InvocationTargetException, InterruptedException
      {
        monitor.beginTask(Localizer.getMessage("READ_SHAPE_FILE"), -1);

        try
        {
          ShapefileImporter facade = new ShapefileImporter(data.getShapeFile());
          List<String> attributes = facade.getAttributes();
          data.setAttributes(attributes);
        }
        catch (Throwable t)
        {
          throw new InvocationTargetException(t);
        }

        monitor.done();
      }
    }

    try
    {
      ShapeFilePage.this.setErrorMessage(null);

      getContainer().run(true, false, new ProcessShapefileRunner());
    }
    catch (Exception e)
    {
      String msg = null;
      if(e instanceof InvocationTargetException)
      {
        InvocationTargetException iEx = (InvocationTargetException) e;
        Throwable target = iEx.getCause();
        if(target instanceof GISException)
        {
          msg = target.getMessage();
        }
      }
      
      if(msg == null)
      {
        msg = Localizer.getMessage("UNABLE_TO_READ_SHAPEFILE");
      }
//      e.printStackTrace();

      ShapeFilePage.this.setErrorMessage(msg);

      setPageComplete(false);
    }
  }

  private void readyNextPage()
  {
    if (data.getAttributes() != null)
    {
      IWizardPage page = ShapeFilePage.this.getNextPage();

      if (page instanceof IInitPage)
      {
        ( (IInitPage) page ).init();
      }

      setPageComplete(true);
    }
    else
    {
      setPageComplete(false);
    }
  }
}
