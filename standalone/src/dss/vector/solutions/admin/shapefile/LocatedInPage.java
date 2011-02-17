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

import dss.vector.solutions.admin.shapefile.LocatedInBean.Option;

public class LocatedInPage extends WizardPage implements PropertyChangeListener
{
  public static String       PAGE_NAME = "LocatedInPage";

  private LocatedInBean      bean;

  private ComboViewer        option;

  private DataBindingContext bindingContext;

  public LocatedInPage(LocatedInBean bean)
  {
    super(PAGE_NAME);

    setTitle(Localizer.getMessage("SHAPE_FILE_COLUMNS"));
    setDescription(Localizer.getMessage("SHAPE_FILE_ATTRIBUTES_DESC"));

    this.bean = bean;
    this.bean.addPropertyChangeListener("option", this);
    this.bindingContext = new DataBindingContext(SWTObservables.getRealm(Display.getDefault()));
    
    setPageComplete(bean.getOption() != null);    
  }

  @Override
  public void createControl(Composite parent)
  {
    Composite composite = new Composite(parent, SWT.NULL);
    composite.setLayout(new GridLayout(2, false));

    new Label(composite, SWT.NULL).setText(Localizer.getMessage("ALGORITHM") + ": ");
    option = new ComboViewer(composite, SWT.BORDER | SWT.READ_ONLY);
    option.getControl().setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
    option.setContentProvider(new OptionContentProvider());
    option.setLabelProvider(new LabelProvider());
    option.setInput(bean);

    setControl(composite);

    this.bind(option, "option");
  }

  private void bind(ComboViewer viewer, String attribute)
  {
    IObservableValue uiElement = ViewersObservables.observeSingleSelection(viewer);
    IObservableValue modelElement = BeanProperties.value(LocatedInBean.class, attribute).observe(bean);

    UpdateValueStrategy targetToModel = new UpdateValueStrategy()
    {
      @Override
      protected IStatus doSet(IObservableValue observableValue, Object value)
      {
        if (value instanceof LabeledValueBean)
        {
          String _value = ( (LabeledValueBean) value ).getValue();

          if (_value != null)
          {
            Option _option = LocatedInBean.Option.valueOf(_value);

            return super.doSet(observableValue, _option);
          }
          
          return super.doSet(observableValue, null);
        }

        return super.doSet(observableValue, value);
      }
    };

    UpdateValueStrategy modelToTarget = new UpdateValueStrategy()
    {
      @Override
      protected IStatus doSet(IObservableValue observableValue, Object value)
      {
        if (value instanceof LocatedInBean.Option)
        {
          Option _option = (LocatedInBean.Option) value;

          return super.doSet(observableValue, new LabeledValueBean(_option.name()));
        }

        return super.doSet(observableValue, new LabeledValueBean((String) value));
      }
    };

    bindingContext.bindValue(uiElement, modelElement, targetToModel, modelToTarget);
  }

  @Override
  public void propertyChange(PropertyChangeEvent evt)
  {
    setPageComplete(bean.getOption() != null);
  }
}
