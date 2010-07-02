package dss.vector.solutions.permission;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.runwaysdk.business.rbac.RoleDAO;
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
    query.ORDER_BY_DESC(query.getUrl(), SystemURL.URL);

    OIterator<? extends SystemURL> it = query.getIterator();

    try
    {
      while (it.hasNext())
      {
        SystemURL url = it.next();

        if (url.getReadRole() != null && url.getWriteRole() != null)
        {
          PermissionView view = PermissionView.getView(role, url);
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

  private static PermissionView getView(MDSSRoleView view, SystemURL url)
  {
    PermissionView permission = new PermissionView();
    permission.setUrlId(url.getId());
    permission.setLabel(url.getDisplayLabel().getValue());

    RoleDAOIF role = view.getRole();
    Set<RoleDAOIF> superRoles = role.getSuperRoles();

    RoleDAOIF writeRole = RoleDAO.get(url.getWriteRole().getId());
    RoleDAOIF readRole = RoleDAO.get(url.getReadRole().getId());

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
