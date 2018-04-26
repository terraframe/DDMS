/*******************************************************************************
 * Copyright (C) 2018 IVCC
 * 
 * This program is free software: you can redistribute it and/or modify it under
 * the terms of the GNU General Public License as published by the Free Software
 * Foundation, either version 3 of the License, or (at your option) any later
 * version.
 * 
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 * 
 * You should have received a copy of the GNU General Public License along with
 * this program. If not, see <http://www.gnu.org/licenses/>.
 ******************************************************************************/
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

  private Text              managerCode;

  private Text              analyticsCode;

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

    new Label(composite, SWT.NULL).setText(Localizer.getMessage("MANAGER_CODE"));

    this.managerCode = new Text(composite, SWT.BORDER);
    this.managerCode.setText("");
    this.managerCode.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

    new Label(composite, SWT.NULL).setText(Localizer.getMessage("ANALYTICS_CODE"));

    this.analyticsCode = new Text(composite, SWT.BORDER);
    this.analyticsCode.setText("");
    this.analyticsCode.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

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
    
    uiElement = WidgetProperties.text(SWT.Modify).observe(managerCode);
    modelElement = BeanProperties.value(ServerContextBean.class, "managerCode").observe(bean);
    bindingContext.bindValue(uiElement, modelElement, null, null);
    
    uiElement = WidgetProperties.text(SWT.Modify).observe(analyticsCode);
    modelElement = BeanProperties.value(ServerContextBean.class, "analyticsCode").observe(bean);
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
