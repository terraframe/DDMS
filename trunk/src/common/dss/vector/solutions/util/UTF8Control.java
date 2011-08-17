package dss.vector.solutions.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.Locale;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;
import java.util.ResourceBundle.Control;

public class UTF8Control extends Control
{
  public ResourceBundle newBundle(String baseName, Locale locale, String format, ClassLoader loader, boolean reload) throws IllegalAccessException, InstantiationException, IOException
  {
    String bundleName = this.toBundleName(baseName, locale);
    String resourceName = this.toResourceName(bundleName, "properties");

    InputStream stream = getStream(loader, reload, resourceName);

    if (stream != null)
    {
      try
      {
        return new PropertyResourceBundle(new InputStreamReader(stream, "UTF-8"));

      }
      finally
      {
        stream.close();
      }
    }

    return null;
  }

  private InputStream getStream(ClassLoader loader, boolean reload, String resourceName) throws IOException
  {
    InputStream stream = null;

    if (reload)
    {
      URL url = loader.getResource(resourceName);
      if (url != null)
      {
        URLConnection connection = url.openConnection();
        if (connection != null)
        {
          connection.setUseCaches(false);
          stream = connection.getInputStream();
        }
      }
    }
    else
    {
      stream = loader.getResourceAsStream(resourceName);
    }
    return stream;
  }
}
