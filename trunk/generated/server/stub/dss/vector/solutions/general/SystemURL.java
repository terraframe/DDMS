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
  private static final long  serialVersionUID  = 1698680258;

  public static final String ODK_DATA_CAPTURE  = "Mobile data capture";

  public static final String ODK_ADMINISTRATOR = "ODK Administrator";

  public SystemURL()
  {
    super();
  }

  @Override
  protected String buildKey()
  {
    return this.getUrlName();
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
  
  public static Boolean hasWritePermissions(String url)
  {
    Map<String, String> roles = Session.getCurrentSession().getUserRoles();
    
    if (roles.containsKey(RoleDAOIF.ADMIN_ROLE))
    {
      return true;
    }
    
    QueryFactory factory = new QueryFactory();
    SystemURLQuery urlQuery = new SystemURLQuery(factory);
    urlQuery.WHERE(urlQuery.getUrl().EQ(url));
    OIterator<? extends SystemURL> it = urlQuery.getIterator();
    
    try
    {
      if (it.hasNext())
      {
        SystemURL systemUrl = it.next();
        
        RoleDAO writeRole = systemUrl.getWriteRoleDAO();
        
        if (writeRole == null)
        {
          return false;
        }
        
        return roles.containsKey(writeRole.getRoleName());
      }
    }
    finally
    {
      it.close();
    }

    return false;
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

      if (menuItem.getTerm() != null)
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

  public boolean hasRole(RoleProperty property)
  {
    URLRoleQuery query = new URLRoleQuery(new QueryFactory());
    query.WHERE(query.parentId().EQ(this.getId()));
    query.AND(query.childId().EQ(property.getId()));

    return ( query.getCount() > 0 );
  }

  public static SystemURL getByURL(String url)
  {
    if (url != null && url.length() > 0)
    {
      QueryFactory factory = new QueryFactory();
      SystemURLQuery query = new SystemURLQuery(factory);

      query.WHERE(query.getUrl().EQ(url));

      OIterator<? extends SystemURL> it = query.getIterator();

      try
      {
        if (it.hasNext())
        {
          return it.next();
        }
      }
      finally
      {
        it.close();
      }
    }

    return null;
  }

  public static SystemURL getByName(String urlName)
  {
    QueryFactory factory = new QueryFactory();
    SystemURLQuery query = new SystemURLQuery(factory);

    query.WHERE(query.getUrlName().EQ(urlName));

    OIterator<? extends SystemURL> it = query.getIterator();

    try
    {
      if (it.hasNext())
      {
        return it.next();
      }

      return null;
    }
    finally
    {
      it.close();
    }
  }

  public static SystemURLQuery getURLs()
  {
    SystemURLQuery query = new SystemURLQuery(new QueryFactory());
    query.ORDER_BY_ASC(query.getDisplayLabel().localize());

    return query;
  }

}
