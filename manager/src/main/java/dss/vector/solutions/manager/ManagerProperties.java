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

  public static String getSynchPath()
  {
    return instance().getString("synch.path");
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
}
