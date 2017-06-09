package dss.vector.solutions.geoserver;

import java.io.File;
import java.util.Locale;
import java.util.ResourceBundle;

import com.runwaysdk.constants.BusinessInfo;
import com.runwaysdk.constants.CommonProperties;
import com.runwaysdk.generation.loader.Reloadable;
import com.terraframe.utf8.UTF8ResourceBundle;

public class GeoserverProperties implements Reloadable
{
  private static final GeoserverProperties instance = new GeoserverProperties();

  /**
   * Bundle for reading properties file
   */
  private final ResourceBundle             bundle;

  public GeoserverProperties()
  {
    this.bundle = UTF8ResourceBundle.getBundle("GeoServer", Locale.getDefault(), BusinessInfo.class.getClassLoader());
  }

  public ResourceBundle getBundle()
  {
    return bundle;
  }

  public static String getGeoserverGWCDir()
  {
    return instance.getBundle().getString("geoserver.gwc.dir");
  }

  /**
   * Returns the url to access GeoServer locally.
   * 
   * @return
   */
  public static String getGeoServerLocalURL()
  {
    return instance.getBundle().getString("geoserver.local.path");
  }

  /**
   * Returns the url to access GeoServer remotely.
   * 
   * @return
   */
  public static String getGeoServerRemoteURL()
  {
    return instance.getBundle().getString("geoserver.remote.path");
  }

  public static String getAppName()
  {
    return instance.getBundle().getString("geoserver.appname");
  }

  public static String getLockFile()
  {
    return instance.getBundle().getString("geoserver.lock.file");
  }

  public static String getLockPath()
  {
    return CommonProperties.getDeployRoot() + File.separator + "webapps" + File.separator + getAppName() + File.separator + getLockFile();
  }
}
