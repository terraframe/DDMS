package dss.vector.solutions.odk;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Locale;
import java.util.Properties;
import java.util.ResourceBundle;

import com.runwaysdk.constants.BusinessInfo;
import com.runwaysdk.dataaccess.ProgrammingErrorException;
import com.runwaysdk.generation.loader.Reloadable;
import com.terraframe.utf8.UTF8ResourceBundle;

public class ODKProperties implements Reloadable
{
  private static final ODKProperties instance = new ODKProperties();

  /**
   * Bundle for reading properties file
   */
  private final ResourceBundle       bundle;

  public ODKProperties()
  {
    this.bundle = UTF8ResourceBundle.getBundle("odk", Locale.getDefault(), BusinessInfo.class.getClassLoader());
  }

  public ResourceBundle getBundle()
  {
    return bundle;
  }

  public static Boolean getInitialize()
  {
    return new Boolean(instance.getBundle().getString("odk.initialize"));
  }

  public static void writeInitialize(boolean initialize)
  {
    URL resource = ODKProperties.class.getResource("odk.properties");

    try
    {
      File file = new File(resource.toURI());

      Properties props = new Properties();

      try (FileInputStream istream = new FileInputStream(file))
      {
        props.load(istream);
      }

      props.setProperty("odk.initialize", new Boolean(initialize).toString());

      try (OutputStream out = new FileOutputStream(file))
      {
        props.store(out, "Updated on first start to no longer initialize");
      }
    }
    catch (URISyntaxException | IOException e)
    {
      throw new ProgrammingErrorException(e);
    }
  }
}
