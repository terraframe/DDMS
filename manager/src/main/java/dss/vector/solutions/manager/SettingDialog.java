package dss.vector.solutions.manager;

import org.eclipse.core.databinding.Binding;
import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.UpdateValueStrategy;
import org.eclipse.core.databinding.beans.BeanProperties;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.core.databinding.validation.IValidator;
import org.eclipse.core.databinding.validation.ValidationStatus;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.jface.databinding.swt.WidgetProperties;
import org.eclipse.jface.databinding.viewers.ViewersObservables;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.internal.databinding.provisional.fieldassist.ControlDecorationSupport;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

public class SettingDialog extends Dialog
{
  private Text              timeout;

  private ComboViewer       logLevel;

  private ServerContextBean bean;

  public SettingDialog(Shell parentShell, ManagerContextBean context)
  {
    super(parentShell);

    this.setShellStyle(getShellStyle() | SWT.RESIZE | SWT.SHELL_TRIM);
    this.bean = new ServerContextBean(context);
  }

  @Override
  protected void configureShell(Shell shell)
  {
    super.configureShell(shell);

    shell.setText(Localizer.getMessage("SETTINGS"));
  }

  @Override
  protected Control createDialogArea(Composite parent)
  {
    Composite composite = (Composite) super.createDialogArea(parent);
    composite.setLayout(new GridLayout(2, true));

    new Label(composite, SWT.NULL).setText(Localizer.getMessage("TIMEOUT"));

    this.timeout = new Text(composite, SWT.BORDER);
    this.timeout.setText("24000");
    this.timeout.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

    new Label(composite, SWT.NULL).setText(Localizer.getMessage("LOG_LEVEL"));

    this.logLevel = new ComboViewer(composite, SWT.BORDER | SWT.READ_ONLY);
    this.logLevel.getControl().setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
    this.logLevel.setContentProvider(new LogContentProvider());
    this.logLevel.setLabelProvider(new LogLabelProvider());
    this.logLevel.setInput(new Object());

    this.bind();

    return composite;
  }

  private void bind()
  {
    DataBindingContext bindingContext = new DataBindingContext(SWTObservables.getRealm(Display.getDefault()));

    IObservableValue uiElement = ViewersObservables.observeSingleSelection(this.logLevel);
    IObservableValue modelElement = BeanProperties.value(ServerContextBean.class, "logLevel").observe(bean);
    bindingContext.bindValue(uiElement, modelElement, null, null);

    uiElement = WidgetProperties.text(SWT.Modify).observe(timeout);
    modelElement = BeanProperties.value(ServerContextBean.class, "timeout").observe(bean);

    IValidator numberValidator = new IValidator()
    {
      @Override
      public IStatus validate(Object value)
      {
        String s = String.valueOf(value);

        try
        {
          Integer.parseInt(s);

          return ValidationStatus.ok();
        }
        catch (Exception e)
        {
          return ValidationStatus.error(Localizer.getMessage("INTEGER_ONLY"), e);
        }
      }
    };

    UpdateValueStrategy targetToModel = new UpdateValueStrategy();
    targetToModel.setBeforeSetValidator(numberValidator);

    Binding bindValue = bindingContext.bindValue(uiElement, modelElement, targetToModel, null);
    ControlDecorationSupport.create(bindValue, SWT.TOP | SWT.LEFT);
  }

  @Override
  protected void createButtonsForButtonBar(Composite parent)
  {
    createButton(parent, IDialogConstants.OK_ID, Localizer.getMessage("SAVE"), true);
    createButton(parent, IDialogConstants.CANCEL_ID, Localizer.getMessage("CANCEL"), true);
  }

  @Override
  protected void buttonPressed(int buttonId)
  {
    if (buttonId == IDialogConstants.OK_ID)
    {
      this.bean.save();
    }

    super.buttonPressed(buttonId);
  }

}
