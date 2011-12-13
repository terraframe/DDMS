package dss.vector.solutions.util;

import java.util.Locale;
import java.util.ResourceBundle;

import com.runwaysdk.business.Business;
import com.runwaysdk.dataaccess.MdDimensionDAOIF;
import com.runwaysdk.session.Session;

import dss.vector.solutions.localization.MultiBundle;

/**
 * A wrapper for access to the MDSS.properties bundle, this fixes isolated
 * scenarios where the system would be unable to find the bundle
 * 
 * @author Eric
 */
public class MDSSProperties
{
  private static final String NAME   = "MDSS";

  private static ClassLoader  LOADER = Business.class.getClassLoader();

  public static String getString(String key)
  {
    return MultiBundle.get(key);
  }

  public static Object getObject(String key)
  {
    return ResourceBundle.getBundle(getBundleName(), Session.getCurrentLocale(), LOADER).getObject(key);
  }

  public static String getString(String key, Locale locale)
  {
    return MultiBundle.get(key, locale);
  }

  public static Object getObject(String key, Locale locale)
  {
    ResourceBundle other = ResourceBundle.getBundle(getBundleName(), locale, LOADER);
    return other.getObject(key);
  }

  private static String getBundleName()
  {
    MdDimensionDAOIF currentDimension = Session.getCurrentDimension();
    if (currentDimension != null)
    {
      return NAME + "-" + currentDimension.getName();
    }
    else
    {
      return NAME;
    }
  }
}
