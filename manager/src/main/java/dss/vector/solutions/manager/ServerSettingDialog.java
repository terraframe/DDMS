package dss.vector.solutions.manager;

import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.beans.BeanProperties;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.jface.databinding.swt.WidgetProperties;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

public class ServerSettingDialog extends Dialog
{
  private Text                     hostname;

  private Text                     keystorePath;

  private Text                     keystoreAlias;

  private Text                     keystorePass;

  private Button                   https;

  private ServerSettingContextBean bean;

  public ServerSettingDialog(Shell parentShell, ManagerContextBean context, String[] applications)
  {
    super(parentShell);

    this.setShellStyle(getShellStyle() | SWT.SHELL_TRIM);
    this.bean = new ServerSettingContextBean(applications);
  }

  @Override
  protected void configureShell(Shell shell)
  {
    super.configureShell(shell);

    shell.setText(Localizer.getMessage("SERVER_SETTINGS"));
    // shell.setSize(new Point(500, 300));
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

    new Label(composite, SWT.NULL).setText(Localizer.getMessage("ENABLE_HTTPS"));

    this.https = new Button(composite, SWT.CHECK);
    this.https.setText("");
    this.https.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

    new Label(composite, SWT.NULL).setText(Localizer.getMessage("KEYSTORE_PATH"));

    Composite c = new Composite(composite, SWT.NONE);
    c.setLayout(new FillLayout(SWT.HORIZONTAL));

    this.keystorePath = new Text(c, SWT.BORDER);
    this.keystorePath.setText("Select");

    Button b = new Button(c, SWT.NONE);
    b.setText("Select File");
    b.addSelectionListener(new SelectionListener()
    {
      @Override
      public void widgetSelected(SelectionEvent arg0)
      {
        FileDialog fd = new FileDialog(Display.getCurrent().getActiveShell(), SWT.OPEN);
        fd.setFileName(keystorePath.getText());
        fd.setText(Localizer.getMessage("SELECT_KEYSTORE"));
        fd.setFilterExtensions(new String[] { "*.jks", "*.ks" });

        String selected = fd.open();
        if (selected != null)
        {
          keystorePath.setText(selected.replaceAll("\\\\", "/"));
        }
      }

      @Override
      public void widgetDefaultSelected(SelectionEvent arg0)
      {
      }
    });

    new Label(composite, SWT.NULL).setText(Localizer.getMessage("KEYSTORE_ALIAS"));

    this.keystoreAlias = new Text(composite, SWT.BORDER);
    this.keystoreAlias.setText("");
    this.keystoreAlias.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

    new Label(composite, SWT.NULL).setText(Localizer.getMessage("KEYSTORE_PASS"));

    this.keystorePass = new Text(composite, SWT.BORDER);
    this.keystorePass.setText("");
    this.keystorePass.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

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

    uiElement = WidgetProperties.text(SWT.Modify).observe(this.keystorePath);
    modelElement = BeanProperties.value(ServerSettingContextBean.class, "keystorePath").observe(bean);
    bindingContext.bindValue(uiElement, modelElement, null, null);

    uiElement = WidgetProperties.text(SWT.Modify).observe(this.keystoreAlias);
    modelElement = BeanProperties.value(ServerSettingContextBean.class, "keystoreAlias").observe(bean);
    bindingContext.bindValue(uiElement, modelElement, null, null);

    uiElement = WidgetProperties.text(SWT.Modify).observe(this.keystorePass);
    modelElement = BeanProperties.value(ServerSettingContextBean.class, "keystorePass").observe(bean);
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
