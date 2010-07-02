package dss.vector.solutions.permission;

import com.runwaysdk.business.rbac.RoleDAO;

import dss.vector.solutions.general.Disease;
import dss.vector.solutions.general.SystemURL;

public class PermissionFactory
{
  private String key;
  
  public PermissionFactory(String key)
  {
    this.key = key;
  }
  
  public PermissionAction getAction(SystemURL url, Disease disease)
  {
    if (key.equalsIgnoreCase("WRITE"))
    {
      return new WriteAction(url, disease);
    }

    return new ReadAction(url, disease);
  }

  public PermissionAction getAction(RoleDAO role, Disease disease)
  {
    if (key.equalsIgnoreCase("WRITE"))
    {
      return new WriteAction(role, disease);
    }
    
    return new ReadAction(role, disease);
  }
}
