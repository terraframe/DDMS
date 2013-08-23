package dss.vector.solutions;

import java.io.File;

import com.runwaysdk.configuration.ProfileManager;
import com.runwaysdk.configuration.ProfileReader;
import com.runwaysdk.generation.loader.Reloadable;

public class InstallProperties implements Reloadable
{
  /**
   * The install.properties configuration file
   */
  private ProfileReader props;

  /**
   * Private constructor loads the server.properties configuration
   */
  private InstallProperties()
  {
    this.props = ProfileManager.getBundle("server/install.properties");
  }

  /**
   * A holder class for access to the singleton. Allows for lazy instantiation
   * and thread safety because the class is not loaded until the first access to
   * INSTANCE.
   */
  static class Singleton implements Reloadable
  {
    private static final InstallProperties INSTANCE = new InstallProperties();

    static boolean getBoolean(String propertyName)
    {
      return INSTANCE.props.getBoolean(propertyName);
    }

    static String getString(String propertyName)
    {
      return INSTANCE.props.getString(propertyName);
    }
  }

  public static boolean isMaster()
  {
    return Singleton.getBoolean("master");
  }

  /**
   * Checks if this node is the master node and throws an exception if it is
   * not.
   * 
   * @return
   */
  public static void validateMasterOperation()
  {
    if (!isMaster())
    {
      throw new MasterOperationException();
    }
  }

  public static File getManagerClasses()
  {
    return new File(Singleton.getString("manager.classes"));
  }

  public static File getSynchClasses()
  {
    return new File(Singleton.getString("synch.classes"));
  }

  public static File getGeoClasses()
  {
    return new File(Singleton.getString("geo.classes"));
  }

  public static File getInitializerClasses()
  {
    return new File(Singleton.getString("initializer.classes"));
  }

  public static File getBackupClasses()
  {
    return new File(Singleton.getString("backup.classes"));
  }
}
