package dss.vector.solutions.manager;

import java.util.Locale;
import java.util.ResourceBundle;

public class Localizer
{
  public static final String DEFAULT_LOCALE = "defaultLocale";

  private static Localizer   instance;

  private ResourceBundle     bundle;

  private Localizer()
  {
    this("manager", getLocale());
  }

  private Localizer(String name, Locale locale)
  {
    this.bundle = ResourceBundle.getBundle(name, locale, new UTF8Control());
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

  public static String getMessage(String key, String... parameters)
  {
    String value = Localizer.instance().get(key);

    if (value == null)
    {
      return key;
    }

    // Replace values
    for (int i = 0; i < parameters.length; i++)
    {
      value = value.replaceAll("\\{" + i + "\\}", parameters[i]);
    }

    return value;
  }

  public static Locale getLocale()
  {
    return Locale.US;
  }

}
