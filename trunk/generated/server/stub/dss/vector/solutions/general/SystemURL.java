package dss.vector.solutions.general;

import com.runwaysdk.business.rbac.RoleDAO;
import com.runwaysdk.system.Roles;

public class SystemURL extends SystemURLBase implements com.runwaysdk.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1698680258;

  public SystemURL()
  {
    super();
  }

  @Override
  protected String buildKey()
  {
    return this.getDisplayLabel().getValue("defaultLocale");
  }
  
  public RoleDAO getReadRoleDAO()
  {
    return SystemURL.getRoleDAO(this.getReadRole());
  }

  public RoleDAO getWriteRoleDAO()
  {
    return SystemURL.getRoleDAO(this.getWriteRole());
  }
  
  public static RoleDAO getRoleDAO(Roles role)
  {
    return RoleDAO.get(role.getId()).getBusinessDAO();
  }
}
