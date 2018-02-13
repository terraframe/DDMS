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
import java.util.List;

import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.Viewer;

import com.runwaysdk.generation.loader.Reloadable;

import dss.vector.solutions.gis.Localizer;
import dss.vector.solutions.gis.LabeledValueBean;

public class AttributeContentProvider implements IStructuredContentProvider, Reloadable
{
  private boolean required;

  public AttributeContentProvider(boolean required)
  {
    this.required = required;
  }

  @Override
  public Object[] getElements(Object object)
  {
    Collection<LabeledValueBean> collection = new LinkedList<LabeledValueBean>();

    if (object instanceof ShapeFileBean)
    {
      ShapeFileBean data = (ShapeFileBean) object;

      if (required)
      {
        collection.add(new LabeledValueBean(null, Localizer.getMessage("CHOOSE_OPTION")));
      }
      else
      {
        collection.add(new LabeledValueBean(null, ""));
      }

      List<String> attributes = data.getAttributes();

      if (attributes != null)
      {
        for (String attribute : attributes)
        {
          collection.add(new LabeledValueBean(attribute));
        }
      }
    }

    return collection.toArray(new LabeledValueBean[collection.size()]);
  }

  @Override
  public void dispose()
  {
  }

  @Override
  public void inputChanged(Viewer arg0, Object arg1, Object arg2)
  {
  }

}
