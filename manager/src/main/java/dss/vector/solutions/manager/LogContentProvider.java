package dss.vector.solutions.manager;

import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.Viewer;

public class LogContentProvider implements IStructuredContentProvider
{

  @Override
  public Object[] getElements(Object input)
  {
    return LogLevel.values();
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
