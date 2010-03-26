package dss.vector.solutions.util;

import java.util.Locale;
import java.util.ResourceBundle;

import com.runwaysdk.business.Business;

/**
 * A wrapper for access to the MDSS.properties bundle, this fixes isolated
 * scenarios where the system would be unable to find the bundle
 * 
 * @author Eric
 */
public class MDSSProperties
{
  private static final String NAME = "MDSS";
  private static ResourceBundle bundle = ResourceBundle.getBundle(NAME, Locale.getDefault(), Business.class.getClassLoader());

  public static String getString(String key)
  {
    return bundle.getString(key);
  }

  public static Object getObject(String key)
  {
    return bundle.getObject(key);
  }
  
  public static String getString(String key, Locale locale)
  {
    ResourceBundle other = ResourceBundle.getBundle(NAME, locale, Business.class.getClassLoader());
    return other.getString(key);
  }
  
  public static Object getObject(String key, Locale locale)
  {
    ResourceBundle other = ResourceBundle.getBundle(NAME, locale, Business.class.getClassLoader());
    return other.getObject(key);
  }
}
