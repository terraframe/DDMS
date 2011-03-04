package dss.vector.solutions.admin.shapefile;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.runwaysdk.dataaccess.transaction.ITaskListener;
import com.runwaysdk.generation.loader.LoaderDecorator;

public class ShapefileImporterFacade
{
  public static String CONCRETE_CLASS = "dss.vector.solutions.geo.ShapefileImporter";

  private Class<?>     clazz;

  private Object       instance;

  public ShapefileImporterFacade(File file) throws MalformedURLException
  {
    this(file.toURI().toURL());
  }

  public ShapefileImporterFacade(URL url)
  {
    this.clazz = LoaderDecorator.load(CONCRETE_CLASS);

    try
    {
      this.instance = clazz.getConstructor(URL.class).newInstance(url);
    }
    catch (Exception e)
    {
      throw new RuntimeException(e);
    }
  }

  public String getName()
  {
    return new BeanWrapper<String>(instance).get("name");
  }

  public void setName(String name)
  {
    new BeanWrapper<String>(instance).set("name", name);
  }

  public String getId()
  {
    return new BeanWrapper<String>(instance).get("id");
  }

  public void setId(String id)
  {
    new BeanWrapper<String>(instance).set("id", id);
  }

  public String getParent()
  {
    return new BeanWrapper<String>(instance).get("parent");
  }

  public void setParent(String parent)
  {
    new BeanWrapper<String>(instance).set("parent", parent);
  }

  public String getParentType()
  {
    return new BeanWrapper<String>(instance).get("parentType");
  }

  public void setParentType(String parentType)
  {
    new BeanWrapper<String>(instance).set("parentType", parentType);
  }

  /**
   * @param type
   *          Fully qualified type of the entities being imported.
   */
  public void setType(String type)
  {
    new BeanWrapper<String>(instance).set("type", type);
  }

  public void addListener(ITaskListener listener)
  {
    Class<? extends Object> clazz = instance.getClass();

    try
    {
      clazz.getMethod("addListener", ITaskListener.class).invoke(instance, listener);
    }
    catch (Exception e)
    {
      throw new RuntimeException(e);
    }
  }

  public void removeListener(ITaskListener listener)
  {
    Class<? extends Object> clazz = instance.getClass();

    try
    {
      clazz.getMethod("removeListener", ITaskListener.class).invoke(instance, listener);
    }
    catch (Exception e)
    {
      throw new RuntimeException(e);
    }
  }

  public void run()
  {
    Class<? extends Object> clazz = instance.getClass();

    try
    {
      clazz.getMethod("run").invoke(instance);
    }
    catch (Exception e)
    {
      throw new RuntimeException(e);
    }
  }

  public Map<String, String> getEntityIdMap()
  {
    return new BeanWrapper<Map<String, String>>(instance).get("entityIdMap");
  }

  /**
   * @return A list of attributes defined in the shapefile
   */
  public List<String> getAttributes()
  {
    return new BeanWrapper<List<String>>(instance).get("attributes");
  }

  public void setValues(ShapeFileBean data)
  {
    this.setName(data.getName());
    this.setId(data.getId());
    this.setParent(data.getParent());
    this.setParentType(data.getParentType());
  }

  @SuppressWarnings("unchecked")
  public static Collection<LabeledValueBean> getUniversals()
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
}
