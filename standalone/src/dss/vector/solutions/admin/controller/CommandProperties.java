package dss.vector.solutions.admin.controller;

import java.util.ResourceBundle;

public class CommandProperties
{
  /**
   * The common.properties configuration file
   */
  private ResourceBundle props;

  /**
   * Private constructor loads the common.properties configuration
   */
  private CommandProperties()
  {
    props = ResourceBundle.getBundle("command");
  }

  /**
   * A holder class for access to the singleton. Allows for lazy instantiation
   * and thread safety because the class is not loaded until the first access to
   * INSTANCE.
   */
  private static class Singleton
  {
    private static final CommandProperties INSTANCE = new CommandProperties();
  }

  private static ResourceBundle instance()
  {
    return Singleton.INSTANCE.props;
  }

  public static String getTimeoutLocation()
  {
    return instance().getString("timeout.location");
  }

  public static String getStartCommand()
  {
    return instance().getString("command.start");
  }

  public static String getStopCommand()
  {
    return instance().getString("command.stop");
  }

  public static String getServerURL()
  {
    return instance().getString("url.server");
  }  
}
