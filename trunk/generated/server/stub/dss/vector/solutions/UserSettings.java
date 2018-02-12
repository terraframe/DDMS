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
package dss.vector.solutions;

import com.runwaysdk.query.OIterator;
import com.runwaysdk.query.QueryFactory;

/**
 * Class to represent settings specific to a user that should not be synchronized (local to one machine).
 */
public class UserSettings extends UserSettingsBase implements com.runwaysdk.generation.loader.Reloadable
{
  private static final long serialVersionUID = -2105152695;
  
  public UserSettings()
  {
    super();
  }
  
  /**
   * Creates and returns a UserSettings object for the given user. If UserSettings
   * already exist for the given user then that object is returned.
   * 
   * @param user
   * @return
   */
  public static synchronized UserSettings createIfNotExists(MDSSUser user)
  {
    UserSettings settings = getForUser(user);
    if(settings == null)
    {
      settings = new UserSettings();
      settings.setUserRef(user);
      settings.apply();
    }
    
    return settings;
  }
  
  public static synchronized void deleteIfExists(MDSSUser user)
  {
    UserSettings settings = getForUser(user);
    if(settings != null)
    {
      settings.delete();
    }
  }
  
  /**
   * Returns the UserSettings for the given MDSSUser if it exists; otherwise,
   * returns null.
   * 
   * @param user
   * @return
   */
  public static UserSettings getForUser(MDSSUser user)
  {
    QueryFactory f = new QueryFactory();
    UserSettingsQuery q = new UserSettingsQuery(f);
    
    q.WHERE(q.getUserRef().EQ(user));
    
    OIterator<? extends UserSettings> iter = q.getIterator();
    try
    {
      if(iter.hasNext())
      {
        return iter.next();
      }
      else
      {
        return null;
      }
    }
    finally
    {
      iter.close();
    }
  }
}
