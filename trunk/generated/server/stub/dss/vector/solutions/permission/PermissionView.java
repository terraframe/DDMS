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
package dss.vector.solutions.permission;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.runwaysdk.business.rbac.RoleDAOIF;
import com.runwaysdk.query.OIterator;
import com.runwaysdk.query.QueryFactory;

import dss.vector.solutions.general.SystemURL;
import dss.vector.solutions.general.SystemURLQuery;

public class PermissionView extends PermissionViewBase implements com.runwaysdk.generation.loader.Reloadable
{
  private static final long serialVersionUID = 436192251;

  public PermissionView()
  {
    super();
  }

  public static PermissionView[] getPermissions(MDSSRoleView role)
  {
    List<PermissionView> list = new ArrayList<PermissionView>();

    SystemURLQuery query = new SystemURLQuery(new QueryFactory());
    query.ORDER_BY_ASC(query.getDisplayLabel().localize(), SystemURL.URL);

    OIterator<? extends SystemURL> it = query.getIterator();

    try
    {
      while (it.hasNext())
      {
        SystemURL url = it.next();
        RoleDAOIF writeRole = url.getWriteRoleDAO();
        RoleDAOIF readRole = url.getReadRoleDAO();

        if (writeRole != null && readRole != null)
        {
          PermissionView view = PermissionView.getView(role, url, writeRole, readRole);
          list.add(view);
        }
      }
    }
    finally
    {
      it.close();
    }

    return list.toArray(new PermissionView[list.size()]);
  }

  private static PermissionView getView(MDSSRoleView view, SystemURL url, RoleDAOIF writeRole, RoleDAOIF readRole)
  {
    PermissionView permission = new PermissionView();
    permission.setUrlId(url.getId());
    permission.setLabel(url.getDisplayLabel().getValue());

    RoleDAOIF role = view.getRole();
    Set<RoleDAOIF> superRoles = role.getSuperRoles();

    if (superRoles.contains(writeRole))
    {
      permission.addPermission(PermissionOption.WRITE);
    }
    else if (superRoles.contains(readRole))
    {
      permission.addPermission(PermissionOption.READ);
    }
    else
    {
      permission.addPermission(PermissionOption.NONE);
    }

    return permission;
  }

}
