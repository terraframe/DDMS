package dss.vector.solutions.util;

import java.util.Locale;
import java.util.ResourceBundle;

import com.terraframe.mojo.business.Business;

public class MDSSProperties
{
  private static ResourceBundle bundle = ResourceBundle.getBundle("MDSS", Locale.getDefault(), Business.class.getClassLoader());
  
  public static String getString(String key)
  {
    return bundle.getString(key);
  }
  
  public static Object getObject(String key)
  {
    return bundle.getObject(key);
  }
}
