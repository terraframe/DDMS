package dss.vector.solutions.gis;

import com.runwaysdk.generation.loader.LoaderDecorator;

public class WindowLauncher
{
  public static void main(String[] args)
  {
    Class<?> clazz = LoaderDecorator.load("dss.vector.solutions.gis.MainWindow");
    try
    {
      clazz.getMethod("main", String[].class).invoke(null, (Object)args);
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
  }
}
