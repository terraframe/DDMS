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
