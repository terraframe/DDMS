package dss.vector.solutions.manager;


import org.eclipse.core.databinding.Binding;
import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.UpdateValueStrategy;
import org.eclipse.core.databinding.beans.BeanProperties;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.jface.databinding.swt.WidgetProperties;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.internal.databinding.provisional.fieldassist.ControlDecorationSupport;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

public class ServerSettingDialog extends Dialog
{
  private Text                     hostname;

  private Button                   https;

  private ServerSettingContextBean bean;

  public ServerSettingDialog(Shell parentShell, ManagerContextBean context, String[] applications)
  {
    super(parentShell);

    this.setShellStyle(getShellStyle() | SWT.RESIZE | SWT.SHELL_TRIM);
    this.bean = new ServerSettingContextBean(applications);
  }

  @Override
  protected void configureShell(Shell shell)
  {
    super.configureShell(shell);

    shell.setText(Localizer.getMessage("SERVER_SETTINGS"));
  }

  @Override
  protected Control createDialogArea(Composite parent)
  {
    Composite composite = (Composite) super.createDialogArea(parent);
    composite.setLayout(new GridLayout(2, true));

    new Label(composite, SWT.NULL).setText(Localizer.getMessage("HOSTNAME"));

    this.hostname = new Text(composite, SWT.BORDER);
    this.hostname.setText("");
    this.hostname.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
    
    this.https = new Button(composite, SWT.CHECK);
    this.https.setText(Localizer.getMessage("ENABLE_HTTPS"));
    this.https.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

    this.bind();

    return composite;
  }

  private void bind()
  {
    DataBindingContext bindingContext = new DataBindingContext(SWTObservables.getRealm(Display.getDefault()));

    IObservableValue uiElement = WidgetProperties.text(SWT.Modify).observe(hostname);
    IObservableValue modelElement = BeanProperties.value(ServerSettingContextBean.class, "hostname").observe(bean);
    bindingContext.bindValue(uiElement, modelElement, null, null);
    
    uiElement = WidgetProperties.selection().observe(this.https);
    modelElement = BeanProperties.value(ServerSettingContextBean.class, "https").observe(bean);
    bindingContext.bindValue(uiElement, modelElement, null, null);
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
