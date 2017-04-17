package dss.vector.solutions.permission;

import java.util.ArrayList;
import java.util.List;

import com.runwaysdk.business.rbac.Operation;
import com.runwaysdk.business.rbac.RoleDAOIF;
import com.runwaysdk.dataaccess.MdBusinessDAOIF;
import com.runwaysdk.dataaccess.metadata.MdBusinessDAO;
import com.runwaysdk.session.PermissionMap;

import dss.vector.solutions.geo.GeoHierarchy;
import dss.vector.solutions.geo.GeoHierarchyView;
import dss.vector.solutions.geo.generated.Earth;
import dss.vector.solutions.geo.generated.GeoEntity;

public class UniversalPermissionView extends UniversalPermissionViewBase implements com.runwaysdk.generation.loader.Reloadable
{
  private static final long serialVersionUID = 291539801;

  public UniversalPermissionView()
  {
    super();
  }

  public static UniversalPermissionView[] getPermissions(MDSSRoleView role)
  {
    List<UniversalPermissionView> list = new ArrayList<UniversalPermissionView>();

    RoleDAOIF roleDAO = role.getRole();
    PermissionMap permissions = roleDAO.getOperations();

    GeoHierarchyView[] universals = GeoHierarchy.getAllViews();

    for (GeoHierarchyView universal : universals)
    {
      if (!universal.getGeneratedType().equals(Earth.CLASS) && !universal.getGeneratedType().equals(GeoEntity.CLASS))
      {
        UniversalPermissionView view = UniversalPermissionView.getView(universal, permissions);
        list.add(view);
      }
    }

    return list.toArray(new UniversalPermissionView[list.size()]);
  }

  private static UniversalPermissionView getView(GeoHierarchyView universal, PermissionMap permissions)
  {
    MdBusinessDAOIF mdBusiness = MdBusinessDAO.get(universal.getReferenceId());

    UniversalPermissionView permission = new UniversalPermissionView();
    permission.setLabel(universal.getDisplayLabel());
    permission.setUniversalId(mdBusiness.getId());
    permission.setPermission(permissions.containsPermission(mdBusiness.getPermissionKey(), Operation.CREATE));

    return permission;
  }

}
