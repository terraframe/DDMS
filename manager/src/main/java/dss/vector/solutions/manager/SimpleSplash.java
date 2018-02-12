/*******************************************************************************
 * Copyright (C) 2018 IVCC
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 ******************************************************************************/
package dss.vector.solutions.manager;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Monitor;
import org.eclipse.swt.widgets.Shell;

public class SimpleSplash
{
  private Shell shell;

  private Label label;

  public SimpleSplash(Monitor monitor)
  {
    FormData labelData = new FormData();
    labelData.left = new FormAttachment(0, 5);
    labelData.bottom = new FormAttachment(100, -80);

    FormData progressData = new FormData();
    progressData.left = new FormAttachment(0, 5);
    progressData.right = new FormAttachment(100, -5);
    progressData.bottom = new FormAttachment(100, -5);

    shell = new Shell(SWT.ON_TOP);
    shell.setSize(300, 100);
    shell.setLayout(new FormLayout());

    label = new Label(shell, SWT.CENTER);
    label.setText(Localizer.getMessage("INIT"));
    label.setLayoutData(labelData);

    Rectangle splashRect = shell.getBounds();
    Rectangle displayRect = monitor.getBounds();

    int x = ( displayRect.width - splashRect.width ) / 2;
    int y = ( displayRect.height - splashRect.height ) / 2;

    shell.setLocation(x, y);
  }

  public Shell getShell()
  {
    return shell;
  }

  public boolean isDisposed()
  {
    return this.shell.isDisposed();
  }

  public void open()
  {
    this.shell.open();
  }

  public void close()
  {
    this.shell.close();
  }

}
