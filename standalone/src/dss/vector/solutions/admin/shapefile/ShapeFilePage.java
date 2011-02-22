package dss.vector.solutions.admin.shapefile;

import java.io.File;

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

import com.runwaysdk.manager.general.Localizer;

public class ShapeFilePage extends WizardPage
{
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
    fileText.addMouseListener(new MouseAdapter()
    {
      @Override
      public void mouseDown(MouseEvent e)
      {
        openFileDialog();
      }
    });

    FormData fileButtonData = new FormData();
    fileButtonData.left = new FormAttachment(fileText);
    fileButtonData.right = new FormAttachment(100, 0);

    Button fileButton = new Button(fileComposite, SWT.BORDER);
    fileButton.setLayoutData(fileButtonData);
    fileButton.setText("Select File");
    fileButton.addListener(SWT.Selection, new Listener()
    {
      @Override
      public void handleEvent(Event event)
      {
        openFileDialog();
      }
    });

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

    if (data.getShapeFile() == null)
    {
      setPageComplete(false);
    }
    else
    {
      try
      {
        ShapefileImporterFacade facade = new ShapefileImporterFacade();
        String[] attributes = facade.getAttributes(data.getShapeFile());
        data.setAttributes(attributes);

        IWizardPage page = this.getNextPage();

        if (page instanceof IInitPage)
        {
          ( (IInitPage) page ).init();
        }

        setPageComplete(true);
      }
      catch (Exception e)
      {
        this.setErrorMessage(e.getLocalizedMessage());

        setPageComplete(false);
      }
    }
  }

}
