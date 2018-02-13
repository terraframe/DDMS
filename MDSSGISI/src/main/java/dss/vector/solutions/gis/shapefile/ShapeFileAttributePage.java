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
package dss.vector.solutions.gis.shapefile;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

import com.runwaysdk.generation.loader.Reloadable;

import dss.vector.solutions.gis.Localizer;
import dss.vector.solutions.gis.IInitPage;
import dss.vector.solutions.gis.LabelProvider;

public class ShapeFileAttributePage extends ShapefileBeanPage implements IInitPage, PropertyChangeListener, Reloadable
{
  public static final String PAGE_NAME = "AttributesPage";

  private ComboViewer        name;

  private ComboViewer        id;

  private ComboViewer        locatedIn;

  private ComboViewer        locatedInType;

  public ShapeFileAttributePage(ShapeFileBean data)
  {
    super(PAGE_NAME, data);

    setTitle(Localizer.getMessage("SHAPE_FILE_COLUMNS"));
    setDescription(Localizer.getMessage("SHAPE_FILE_ATTRIBUTES_DESC"));
    setPageComplete(false);

    this.data.addPropertyChangeListener("name", this);
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

    this.bind(name, "name");
    this.bind(id, "id");
    this.bind(locatedIn, "parent");
    this.bind(locatedInType, "parentType");
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

  @Override
  public void propertyChange(PropertyChangeEvent evt)
  {
    setPageComplete(data.hasRequiredAttributes());
  }

}
