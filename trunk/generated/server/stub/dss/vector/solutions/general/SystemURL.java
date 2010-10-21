package dss.vector.solutions.general;

import java.util.Map;

import com.runwaysdk.business.rbac.RoleDAO;
import com.runwaysdk.business.rbac.RoleDAOIF;
import com.runwaysdk.query.OIterator;
import com.runwaysdk.query.QueryFactory;
import com.runwaysdk.session.Session;
import com.runwaysdk.system.Roles;

import dss.vector.solutions.permission.PermissionOption;
import dss.vector.solutions.permissions.RoleProperty;
import dss.vector.solutions.permissions.RolePropertyQuery;
import dss.vector.solutions.permissions.URLRoleQuery;

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

  public RoleDAO getRole(PermissionOption type)
  {
    return this.getRole(Disease.getCurrent(), type);
  }

  public RoleDAO getRole(Disease disease, PermissionOption type)
  {
    QueryFactory factory = new QueryFactory();
    URLRoleQuery urlQuery = new URLRoleQuery(factory);
    RolePropertyQuery query = new RolePropertyQuery(factory);

    urlQuery.WHERE(urlQuery.parentId().EQ(this.getId()));

    query.WHERE(query.getId().EQ(urlQuery.childId()));
    query.AND(query.getRoleType().containsAny(type));
    query.AND(query.getDisease().EQ(disease));

    OIterator<? extends RoleProperty> it = query.getIterator();

    try
    {
      if (it.hasNext())
      {
        RoleProperty property = it.next();
        Roles role = property.getRole();

        return SystemURL.getRoleDAO(role);
      }

      return null;
    }
    finally
    {
      it.close();
    }
  }

  public RoleDAO getReadRoleDAO()
  {
    return this.getRole(PermissionOption.READ);
  }

  public RoleDAO getWriteRoleDAO()
  {
    return this.getRole(PermissionOption.WRITE);
  }

  public static RoleDAO getRoleDAO(Roles role)
  {
    return RoleDAO.get(role.getId()).getBusinessDAO();
  }

  public static Boolean hasReadPermissions(String url)
  {
    QueryFactory factory = new QueryFactory();

    SystemURLQuery urlQuery = new SystemURLQuery(factory);
    urlQuery.WHERE(urlQuery.getUrl().EQ(url));

    MenuItemQuery menuQuery = new MenuItemQuery(factory);
    menuQuery.WHERE(menuQuery.getDisease().EQ(Disease.getCurrent()));
    menuQuery.AND(menuQuery.getUrl().EQ(urlQuery));

    OIterator<? extends MenuItem> iterator = menuQuery.getIterator();

    while (iterator.hasNext())
    {
      Map<String, String> roles = Session.getCurrentSession().getUserRoles();

      // Get the read role of the current diesease
      MenuItem menuItem = iterator.next();
      
      if(menuItem.getTerm() != null)
      {
        SystemURL systemUrl = menuItem.getUrl();

        RoleDAO readRole = systemUrl.getReadRoleDAO();

        if (readRole == null)
        {
          return false;
        }
        else
        {
          if (roles.containsKey(RoleDAOIF.ADMIN_ROLE))
          {
            return true;
          }
        }
        
        return roles.containsKey(readRole.getRoleName());
      }
    }

    return false;
  }
}
