package dss.vector.solutions.gis;

import java.util.Locale;
import java.util.ResourceBundle;

public class GISAdminLocalizer
{
  public static final String       BUNDLE_NAME    = "gisadmin";

  public static final String       DEFAULT_LOCALE = "defaultLocale";

  private static GISAdminLocalizer instance;

  private ResourceBundle           bundle;

  private GISAdminLocalizer()
  {
    this(getLocale());
  }

  private GISAdminLocalizer(Locale locale)
  {
    this.bundle = ResourceBundle.getBundle(BUNDLE_NAME, locale);
  }

  private static synchronized GISAdminLocalizer instance()
  {
    if (instance == null)
    {
      instance = new GISAdminLocalizer();
    }

    return instance;
  }

  public static synchronized void setInstance(Locale locale)
  {
    instance = new GISAdminLocalizer(locale);
  }

  private String get(String key)
  {
    try
    {
      return bundle.getString(key);
    }
    catch (Exception e)
    {
      return null;
    }
  }

  public static String getMessage(String key)
  {
    String value = GISAdminLocalizer.instance().get(key);

    if (value == null)
    {
      return key;
    }

    return value;
  }

  public static Locale getLocale()
  {
    return Locale.US;
  }
}