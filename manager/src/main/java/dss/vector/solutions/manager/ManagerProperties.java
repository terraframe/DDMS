package dss.vector.solutions.manager;

import java.util.ResourceBundle;

public class ManagerProperties
{
  /**
   * The common.properties configuration file
   */
  private ResourceBundle props;

  /**
   * Private constructor loads the common.properties configuration
   */
  private ManagerProperties()
  {
    props = ResourceBundle.getBundle("manager");
  }

  /**
   * A holder class for access to the singleton. Allows for lazy instantiation
   * and thread safety because the class is not loaded until the first access to
   * INSTANCE.
   */
  private static class Singleton
  {
    private static final ManagerProperties INSTANCE = new ManagerProperties();
  }

  private static ResourceBundle instance()
  {
    return Singleton.INSTANCE.props;
  }

  public static String getStartCommand()
  {
    return instance().getString("command.start");
  }

  public static String getStopCommand()
  {
    return instance().getString("command.stop");
  }

  public static String getWebappPath()
  {
    return instance().getString("webapp.path");
  }

  public static String getSynchLib()
  {
    return instance().getString("synch.lib");
  }

  public static String getSynchClasses()
  {
    return instance().getString("synch.classes");
  }

  public static int getListenerPort()
  {
    return Integer.parseInt(instance().getString("listener.port"));
  }

  public static String getBackupLib()
  {
    return instance().getString("backup.lib");
  }

  public static String getBackupProfiles()
  {
    return instance().getString("backup.profiles");
  }

  public static String getBackupClasses()
  {
    return instance().getString("backup.classes");
  }

  public static String getGeoLib()
  {
    return instance().getString("geo.lib");
  }

  public static String getGeoClasses()
  {
    return instance().getString("geo.classes");
  }

  public static String getInitializerLib()
  {
    return instance().getString("initializer.lib");
  }

  public static String getInitializerClasses()
  {
    return instance().getString("initializer.classes");
  }

  public static String getProcessMemoryMin()
  {
    return instance().getString("process.memory.min");
  }
  
  public static String getProcessMemoryMax()
  {
    return instance().getString("process.memory.max");
  }
  
  public static String getProcessPermSize()
  {
    return instance().getString("process.perm.size");
  }
}
