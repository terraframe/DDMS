package dss.vector.solutions.util;

import java.util.Locale;
import java.util.ResourceBundle;

import com.runwaysdk.CommonExceptionProcessor;
import com.runwaysdk.constants.BusinessDTOInfo;
import com.runwaysdk.constants.ExceptionConstants;
import com.runwaysdk.generation.loader.LoaderDecorator;

/**
 * A wrapper for access to the MDSS.properties bundle, this fixes isolated
 * scenarios where the system would be unable to find the bundle
 * 
 * @author Eric
 */
public class MDSSProperties
{
  private static final String NAME = "MDSS";
  private static ClassLoader LOADER = BusinessDTOInfo.class.getClassLoader();

  public static String getString(String key)
  {
    Locale locale = null;
    try
    {
      locale = (Locale) LoaderDecorator.load("com.runwaysdk.session.Session").getMethod("getCurrentLocale").invoke(null);
    }catch(Exception e)
    {
      CommonExceptionProcessor.processException(ExceptionConstants.ConversionException.getExceptionClass(), e.getLocalizedMessage());
    }
    
    return ResourceBundle.getBundle(getBundleName(), locale, LOADER).getString(key);
  }

  public static Object getObject(String key)
  {
    Locale locale = null;
    try
    {
      locale = (Locale) LoaderDecorator.load("com.runwaysdk.session.Session").getMethod("getCurrentLocale").invoke(null);
    }catch(Exception e)
    {
      CommonExceptionProcessor.processException(ExceptionConstants.ConversionException.getExceptionClass(), e.getLocalizedMessage());
    }
    
    return ResourceBundle.getBundle(getBundleName(),locale, LOADER).getObject(key);
  }
  
  public static String getString(String key, Locale locale)
  {
    ResourceBundle other = ResourceBundle.getBundle(getBundleName(), locale, LOADER);
    return other.getString(key);
  }
  
  public static Object getObject(String key, Locale locale)
  {
    ResourceBundle other = ResourceBundle.getBundle(getBundleName(), locale, LOADER);
    return other.getObject(key);
  }
  
  private static String getBundleName()
  {
    Object dimension = null;
    try
    {
      dimension = (Locale) LoaderDecorator.load("com.runwaysdk.session.Session").getMethod("getCurrentDimension").invoke(null);
      if (dimension!=null)
      {
        String name = (String) LoaderDecorator.load("com.runwaysdk.dataaccess.MdDimensionDAOIF").getMethod("getName").invoke(dimension);
        return NAME +  "-" + name;
      }
    }catch(Exception e)
    {
      CommonExceptionProcessor.processException(ExceptionConstants.ConversionException.getExceptionClass(), e.getLocalizedMessage());
    }
    
    return NAME;
  }
}
