/*******************************************************************************
 * Copyright (C) 2018 IVCC
 * 
 * This program is free software: you can redistribute it and/or modify it under
 * the terms of the GNU General Public License as published by the Free Software
 * Foundation, either version 3 of the License, or (at your option) any later
 * version.
 * 
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 * 
 * You should have received a copy of the GNU General Public License along with
 * this program. If not, see <http://www.gnu.org/licenses/>.
 ******************************************************************************/
package dss.vector.solutions.manager.properties;

import java.io.File;
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
    return instance().getString("webapp.root") + "webapps" + File.separator;
  }

  public static String getODKPath()
  {
    return instance().getString("odk.path");
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

  public static String getDropCommand()
  {
    return instance().getString("drop.cmd");
  }

  public static String getShortcutDirectory()
  {
    return instance().getString("shortcut.dir");
  }

  public static String getKeystoreLocation()
  {
    return instance().getString("keystore.location");
  }

  public static String getKeystorePassword()
  {
    return instance().getString("keystore.password");
  }

  public static String getKeystoreAlias()
  {
    return instance().getString("keystore.alias");
  }

  public static String getServerXml()
  {
    return instance().getString("webapp.root") + "conf" + File.separator + "server.xml";
  }
  
  public static String getWebXml()
  {
    return instance().getString("webapp.root") + "conf" + File.separator + "web.xml";
  }
}
