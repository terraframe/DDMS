package com.runwaysdk.manager;

import java.util.ResourceBundle;

import com.terraframe.utf8.UTF8ResourceBundle;

public class BackupProperties
{
  /**
   * The common.properties configuration file
   */
  private ResourceBundle props;

  /**
   * Private constructor loads the common.properties configuration
   */
  private BackupProperties()
  {
    props = UTF8ResourceBundle.getBundle("backup");
  }

  /**
   * A holder class for access to the singleton. Allows for lazy instantiation
   * and thread safety because the class is not loaded until the first access to
   * INSTANCE.
   */
  private static class Singleton
  {
    private static final BackupProperties INSTANCE = new BackupProperties();
  }

  private static ResourceBundle instance()
  {
    return Singleton.INSTANCE.props;
  }

  public static String getExportCommand()
  {
    return instance().getString("export.cmd");
  }

  public static String getImportCommand()
  {
    return instance().getString("import.cmd");
  }

  public static String getRegistry32()
  {
    return instance().getString("registry.32");
  }

  public static String getRegistry64()
  {
    return instance().getString("registry.64");
  }

  public static String getWebappDir()
  {
    return instance().getString("webapp.dir");
  }

  public static String getElevateImportCommand()
  {
    return instance().getString("elevate.import.cmd");
  }
}
