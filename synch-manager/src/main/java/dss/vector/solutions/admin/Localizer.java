package dss.vector.solutions.admin;

import java.util.Locale;
import java.util.ResourceBundle;

import com.terraframe.utf8.UTF8ResourceBundle;

public class Localizer
{
  public static final String DEFAULT_LOCALE = "defaultLocale";

  private static Localizer   instance;

  private ResourceBundle     bundle;

  private Localizer()
  {
    this(getLocale());
  }

  private Localizer(Locale locale)
  {
    this.bundle = UTF8ResourceBundle.getBundle("admin", locale);
  }

  private static synchronized Localizer instance()
  {
    if (instance == null)
    {
      instance = new Localizer();
    }

    return instance;
  }

  public static synchronized void setInstance(Locale locale)
  {
    instance = new Localizer(locale);
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
    String value = Localizer.instance().get(key);

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
