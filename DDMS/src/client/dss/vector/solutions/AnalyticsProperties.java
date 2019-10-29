/**
 * Copyright (c) 2015 TerraFrame, Inc. All rights reserved.
 *
 * This file is part of Runway SDK(tm).
 *
 * Runway SDK(tm) is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * Runway SDK(tm) is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with Runway SDK(tm).  If not, see <http://www.gnu.org/licenses/>.
 */
package dss.vector.solutions;
import com.runwaysdk.configuration.ConfigurationManager;
import com.runwaysdk.configuration.ConfigurationManager.ConfigGroup;
import com.runwaysdk.configuration.ConfigurationReaderIF;
/**
 * Convenience class that allows easy access to the client.properties file.
 * 
 * @author Eric
 */
public class AnalyticsProperties
{
  /**
   * The client.properties configuration file
   */
  private ConfigurationReaderIF props;
  /**
   * A holder class for access to the singleton. Allows for lazy instantiation
   * and thread safety because the class is not loaded until the first access to
   * INSTANCE.
   */
  private static class Singleton
  {
    private static final AnalyticsProperties INSTANCE = new AnalyticsProperties();
  }
  /**
   * Private constructor loads the client.properties configuration
   */
  private AnalyticsProperties()
  {
    props = ConfigurationManager.getReader(ConfigGroup.CLIENT, "analytics.properties");
  }
  
  public static String getGoogleTagManagerTrackingId()
  {
    return Singleton.INSTANCE.props.getString("googletagmanagertrackingcode");
  }
  
  public static String getGoogleAnalyticsTrackingId()
  {
    return Singleton.INSTANCE.props.getString("googleanalyticstrackingcode");
  }
}