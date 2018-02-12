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

import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.swt.graphics.Image;

public class StringLabelProvider implements ILabelProvider
{

  @Override
  public Image getImage(Object arg0)
  {

    return null;
  }

  @Override
  public String getText(Object element)
  {
    if(element instanceof String)
    {
      return (String)element;
    }
    
    return null;
  }

  @Override
  public void addListener(ILabelProviderListener arg0)
  {

  }

  @Override
  public void dispose()
  {

  }

  @Override
  public boolean isLabelProperty(Object arg0, String arg1)
  {

    return false;
  }

  @Override
  public void removeListener(ILabelProviderListener arg0)
  {

  }

}
