package dss.vector.solutions.manager.properties;

import java.util.ResourceBundle;

public class RegistryProperties
{
  /**
   * The common.properties configuration file
   */
  private ResourceBundle props;

  /**
   * Private constructor loads the common.properties configuration
   */
  private RegistryProperties()
  {
    props = ResourceBundle.getBundle("registry");
  }

  /**
   * A holder class for access to the singleton. Allows for lazy instantiation
   * and thread safety because the class is not loaded until the first access to
   * INSTANCE.
   */
  private static class Singleton
  {
    private static final RegistryProperties INSTANCE = new RegistryProperties();
  }

  private static ResourceBundle instance()
  {
    return Singleton.INSTANCE.props;
  }

  public static String getRegistryRoot32()
  {
    return instance().getString("registry.32");
  }

  public static String getRegistryRoot64()
  {
    return instance().getString("registry.64");
  }
  

  public static String getDeleteCommand()
  {
    return instance().getString("delete.cmd");
  }
}
