/*******************************************************************************
 * Copyright (C) 2018 IVCC
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 ******************************************************************************/
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
  
  public static String getDeleteCommand()
  {
    return instance().getString("delete.cmd");
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

  public static String getServerPath()
  {
    return instance().getString("server.path");
  }
}
