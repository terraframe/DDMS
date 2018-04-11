package dss.vector.solutions.manager;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;

import dss.vector.solutions.manager.properties.ManagerProperties;
import dss.vector.solutions.manager.properties.PropertyReader;
import dss.vector.solutions.manager.properties.PropertyWriter;

public class ServerSettingContextBean
{
  public static final String    ODK_HOSTNAME_PROPERTY = "security.server.hostname";

  public static final String    HOSTNAME_PROPERTY     = "server.hostname";

  public static final String    HTTPS_PROPERTY        = "https.enable";

  private static final String   MOBILE                = "Mobile";

  /**
   * PropertyChangeSupport
   */
  private PropertyChangeSupport propertyChangeSupport;

  private String                hostname;

  private Boolean               https;

  private String[]              applications;

  public ServerSettingContextBean(String[] applications)
  {
    this.propertyChangeSupport = new PropertyChangeSupport(this);
    this.hostname = ServerSettingContextBean.getHostnameProperty();
    this.https = ServerSettingContextBean.getHttpsEnableProperty();
    this.applications = applications;
  }

  public void addPropertyChangeListener(String propertyName, PropertyChangeListener listener)
  {
    propertyChangeSupport.addPropertyChangeListener(propertyName, listener);
  }

  public void removePropertyChangeListener(PropertyChangeListener listener)
  {
    propertyChangeSupport.removePropertyChangeListener(listener);
  }

  public String getHostname()
  {
    return hostname;
  }

  public void setHostname(String hostname)
  {
    this.propertyChangeSupport.firePropertyChange("hostname", this.hostname, this.hostname = hostname);
  }

  public Boolean getHttps()
  {
    return https;
  }

  public void setHttps(Boolean https)
  {
    this.propertyChangeSupport.firePropertyChange("https", this.https, this.https = https);
  }

  public void save()
  {
    try
    {
      URL resource = this.getClass().getResource("/manager.properties");
      File file = new File(resource.toURI());

      PropertyWriter writer = new PropertyWriter(file.getAbsolutePath());
      writer.write(HOSTNAME_PROPERTY, this.hostname);
      writer.write(HTTPS_PROPERTY, this.https.toString());

      /*
       * Write all of the ODK webapp settings
       */
      for (String application : applications)
      {
        String path = ManagerProperties.getWebappPath() + application + MOBILE + File.separator + "WEB-INF/classes/security.properties";

        File mobileFile = new File(path);

        if (mobileFile.exists())
        {
          new PropertyWriter(mobileFile.getAbsolutePath()).write(ODK_HOSTNAME_PROPERTY, this.hostname);
        }
      }
    }
    catch (URISyntaxException e)
    {
      throw new RuntimeException(e);
    }
  }

  public static String getHostnameProperty()
  {
    try
    {
      URL resource = ServerSettingContextBean.class.getResource("/manager.properties");
      File file = new File(resource.toURI());

      PropertyReader reader = new PropertyReader(file.getAbsolutePath());

      String hostname = reader.getValue(HOSTNAME_PROPERTY);

      return hostname;
    }
    catch (URISyntaxException e)
    {
      throw new RuntimeException(e);
    }

  }

  public static Boolean getHttpsEnableProperty()
  {
    try
    {
      URL resource = ServerSettingContextBean.class.getResource("/manager.properties");
      File file = new File(resource.toURI());

      PropertyReader reader = new PropertyReader(file.getAbsolutePath());

      String https = reader.getValue(HTTPS_PROPERTY);

      return new Boolean(https);
    }
    catch (URISyntaxException e)
    {
      throw new RuntimeException(e);
    }

  }

}
