package dss.vector.solutions.admin.shapefile;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.UpdateValueStrategy;
import org.eclipse.core.databinding.beans.BeanProperties;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.jface.databinding.viewers.ViewersObservables;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;

import com.runwaysdk.manager.general.Localizer;

public class ShapeFileAttributePage extends WizardPage implements IInitPage, PropertyChangeListener
{
  public static final String  PAGE_NAME = "AttributesPage";

  private ComboViewer         universal;

  private ComboViewer         name;

  private ComboViewer         id;

  private ComboViewer         locatedIn;

  private ComboViewer         locatedInType;

  private DataBindingContext  bindingContext;

  private final ShapeFileBean data;

  public ShapeFileAttributePage(ShapeFileBean data)
  {
    super(PAGE_NAME);

    setTitle(Localizer.getMessage("SHAPE_FILE_COLUMNS"));
    setDescription(Localizer.getMessage("SHAPE_FILE_ATTRIBUTES_DESC"));
    setPageComplete(false);

    this.data = data;
    this.data.addPropertyChangeListener("universal", this);
    this.data.addPropertyChangeListener("name", this);
    this.bindingContext = new DataBindingContext(SWTObservables.getRealm(Display.getDefault()));
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
    composite.setLayout(new GridLayout(2, false));

    new Label(composite, SWT.NULL).setText(Localizer.getMessage("UNIVERSAL") + ": ");
    universal = new ComboViewer(composite, SWT.BORDER | SWT.READ_ONLY);
    universal.getControl().setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
    universal.setContentProvider(new UniversalContentProvider());
    universal.setLabelProvider(new LabelProvider());
    universal.setInput(data);

    new Label(composite, SWT.NULL).setText(Localizer.getMessage("GEO_ENTITY") + ": ");
    name = new ComboViewer(composite, SWT.BORDER | SWT.READ_ONLY);
    name.getControl().setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
    name.setContentProvider(new AttributeContentProvider(true));
    name.setLabelProvider(new LabelProvider());

    new Label(composite, SWT.NULL).setText(Localizer.getMessage("GEO_ID") + ": ");
    id = new ComboViewer(composite, SWT.BORDER | SWT.READ_ONLY);
    id.getControl().setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
    id.setContentProvider(new AttributeContentProvider(false));
    id.setLabelProvider(new LabelProvider());

    new Label(composite, SWT.NULL).setText(Localizer.getMessage("LOCATED_IN") + ": ");
    locatedIn = new ComboViewer(composite, SWT.BORDER | SWT.READ_ONLY);
    locatedIn.getControl().setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
    locatedIn.setContentProvider(new AttributeContentProvider(false));
    locatedIn.setLabelProvider(new LabelProvider());

    new Label(composite, SWT.NULL).setText(Localizer.getMessage("LOCATED_IN_SUBTYPE") + ": ");
    locatedInType = new ComboViewer(composite, SWT.BORDER | SWT.READ_ONLY);
    locatedInType.getControl().setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
    locatedInType.setContentProvider(new AttributeContentProvider(false));
    locatedInType.setLabelProvider(new LabelProvider());

    setControl(composite);

    this.bind(universal, "universal");
    this.bind(name, "name");
    this.bind(id, "id");
    this.bind(locatedIn, "locatedIn");
    this.bind(locatedInType, "locatedInType");
  }

  @Override
  public void init()
  {
    name.setInput(data);
    id.setInput(data);
    locatedIn.setInput(data);
    locatedInType.setInput(data);

    bindingContext.updateTargets();
  }

  private void bind(ComboViewer viewer, String attribute)
  {
    IObservableValue uiElement = ViewersObservables.observeSingleSelection(viewer);
    IObservableValue modelElement = BeanProperties.value(ShapeFileBean.class, attribute).observe(data);

    UpdateValueStrategy targetToModel = new UpdateValueStrategy()
    {
      @Override
      protected IStatus doSet(IObservableValue observableValue, Object value)
      {
        if (value instanceof LabeledValueBean)
        {
          return super.doSet(observableValue, ( (LabeledValueBean) value ).getValue());
        }
        else
        {
          return super.doSet(observableValue, value);
        }
      }
    };

    UpdateValueStrategy modelToTarget = new UpdateValueStrategy()
    {
      @Override
      protected IStatus doSet(IObservableValue observableValue, Object value)
      {
        return super.doSet(observableValue, new LabeledValueBean((String) value));
      }
    };

    bindingContext.bindValue(uiElement, modelElement, targetToModel, modelToTarget);
  }

  @Override
  public void propertyChange(PropertyChangeEvent evt)
  {
    setPageComplete(data.hasRequiredAttributes());
  }

}
