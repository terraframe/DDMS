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

import java.util.Collection;
import java.util.LinkedList;

import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.Viewer;

import com.runwaysdk.generation.loader.Reloadable;

import dss.vector.solutions.geo.GeoHierarchyView;
import dss.vector.solutions.gis.Localizer;
import dss.vector.solutions.gis.LabeledValueBean;

public class UniversalContentProvider implements IStructuredContentProvider, Reloadable
{
  private Collection<LabeledValueBean> universals;

  public UniversalContentProvider(GeoHierarchyView[] views)
  {
    this.universals = new LinkedList<LabeledValueBean>();
    this.universals.add(new LabeledValueBean(null, Localizer.getMessage("CHOOSE_OPTION")));

    for (GeoHierarchyView view : views)
    {
      this.universals.add(new LabeledValueBean(view.getGeneratedType(), view.getDisplayLabel()));
    }
  }

  @Override
  public Object[] getElements(Object arg0)
  {
    return universals.toArray(new LabeledValueBean[universals.size()]);
  }

  @Override
  public void dispose()
  {
    // Do nothing
  }

  @Override
  public void inputChanged(Viewer arg0, Object arg1, Object arg2)
  {
    // Do nothing
  }

}
