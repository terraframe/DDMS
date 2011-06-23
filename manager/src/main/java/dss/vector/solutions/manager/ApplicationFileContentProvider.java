package dss.vector.solutions.manager;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.net.URL;
import java.util.Collection;
import java.util.LinkedList;

import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.Viewer;

public class ApplicationFileContentProvider implements IStructuredContentProvider
{
  @Override
  public Object[] getElements(Object input)
  {
    if (input instanceof String)
    {
      String resourceName = (String) input;
      Collection<LabeledBean> collection = new LinkedList<LabeledBean>();

      try
      {
        URL resource = ApplicationFileContentProvider.class.getResource(resourceName);
        File file = new File(resource.toURI());

        BufferedReader reader = new BufferedReader(new FileReader(file));

        try
        {
          while (reader.ready())
          {
            String line = reader.readLine();
            String[] split = line.split(",");

            collection.add(new LabeledBean(split[0], split[1]));
          }
        }
        finally
        {
          reader.close();
        }
      }
      catch (RuntimeException e)
      {
        throw e;
      }
      catch (Exception e)
      {
        throw new RuntimeException(e);
      }

      return collection.toArray(new LabeledBean[collection.size()]);
    }

    return new Object[] {};
  }

  @Override
  public void dispose()
  {
  }

  @Override
  public void inputChanged(Viewer arg0, Object arg1, Object arg2)
  {
    // Do nothing
  }

}
