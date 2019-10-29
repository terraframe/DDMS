package dss.vector.solutions.odk;

import com.runwaysdk.query.OIterator;
import com.runwaysdk.query.QueryFactory;

public class ODKUser extends ODKUserBase implements com.runwaysdk.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1846714333;
  
  public ODKUser()
  {
    super();
  }
  
  public static dss.vector.solutions.odk.ODKUser getUser()
  {
    OIterator<? extends ODKUser> it = new ODKUserQuery(new QueryFactory()).getIterator();
    
    try
    {
      if (it.hasNext())
      {
        return it.next();
      }
      else
      {
        return null;
      }
    }
    finally
    {
      it.close();
    }
  }
  
  @Override
  protected void exportOdkUsers(boolean exportODK, boolean isUsernameModified)
  {
    // The ODK root username cannot be changed and the password is exported in PersonView.
  }
  
  @Override
  public void setPassword(String value)
  {
    super.setPassword(value);
    this.setOdkPassword(value);
  }
  
  @Override
  public void setUsername(String value)
  {
    super.setUsername(value);
    
    // ODK username cannot be changed.
//    this.setOdkUsername(value);
  }
  
  @Override
  public void delete()
  {
    throw new UnsupportedOperationException();
  }
}
