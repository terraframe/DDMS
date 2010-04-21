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
