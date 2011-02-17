package dss.vector.solutions.admin.shapefile;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.LinkedList;

import com.runwaysdk.dataaccess.transaction.ITaskListener;
import com.runwaysdk.generation.loader.LoaderDecorator;

public class ShapeFileFacade
{
  public void importShapeFile(ShapeFileBean data, ITaskListener... listeners)
  {
    for (ITaskListener listener : listeners)
    {
      listener.taskStart("Importing shape file", 100);

      for (int i = 0; i < 100; i++)
      {
        listener.taskProgress(i);

        try
        {
          Thread.sleep(100);
        }
        catch (InterruptedException e)
        {
          e.printStackTrace();
        }
      }

      listener.done(true);
    }
  }

  public String[] getAttributes(File file)
  {
    if (file == null)
    {
      return new String[] {};
    }

    return new String[] { "province_name", "population", "density", "province_id" };
  }

  @SuppressWarnings("unchecked")
  public Collection<LabeledValueBean> getUniversals()
  {
    Collection<LabeledValueBean> collection = new LinkedList<LabeledValueBean>();
    String className = "dss.vector.solutions.geo.GeoHierarchyView";
    String methodName = "getUniversalIdAndLabel";

    try
    {
      Class<?> clazz = LoaderDecorator.load(className);
      Method method = clazz.getMethod(methodName);

      LinkedHashMap<String, String> map = (LinkedHashMap<String, String>) method.invoke(null);

      for (String label : map.keySet())
      {
        String id = map.get(label);
        collection.add(new LabeledValueBean(id, label));
      }

      return collection;
    }
    catch (InvocationTargetException e)
    {
      throw new RuntimeException(e.getCause().getLocalizedMessage());
    }
    catch (Exception e)
    {
      throw new RuntimeException(e.getLocalizedMessage());
    }
  }

  public void buildLocatedIn(LocatedInBean bean, ITaskListener... listeners)
  {
    for (ITaskListener listener : listeners)
    {
      listener.taskStart("Building located in", 100);

      for (int i = 0; i < 100; i++)
      {
        listener.taskProgress(i);

        try
        {
          Thread.sleep(100);
        }
        catch (InterruptedException e)
        {
          e.printStackTrace();
        }
      }

      listener.done(true);
    }
  }
}
