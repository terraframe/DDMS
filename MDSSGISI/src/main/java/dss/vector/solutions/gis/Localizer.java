package dss.vector.solutions.gis;

import java.util.Locale;
import java.util.ResourceBundle;

public class Localizer
{
  public static final String DEFAULT_LOCALE = "defaultLocale";

  private static Localizer   instance;

  private ResourceBundle     bundle;

  private Localizer()
  {
    this("localization", Locale.US);
  }

  private Localizer(String name, Locale locale)
  {
    this.bundle = ResourceBundle.getBundle(name, locale);
  }

  private String get(String key)
  {
    try
    {
      String value = bundle.getString(key);
      String unicodeValue = new String(value.getBytes("ISO-8859-1"), "UTF-8");
      return unicodeValue;
    }
    catch (Exception e)
    {
      return null;
    }
  }

  private Locale getBundleLocale()
  {
    return bundle.getLocale();
  }

  private static synchronized Localizer instance()
  {
    if (instance == null)
    {
      instance = new Localizer();
    }

    return instance;
  }

  public static synchronized void setInstance(String name, Locale locale)
  {
    instance = new Localizer(name, locale);
  }

  public static String getMessage(String key)
  {
    String value = Localizer.instance().get(key);

    if (value == null)
    {
      return key;
    }

    return value;
  }

  public static Locale getLocale()
  {
    return instance().getBundleLocale();
  }
}