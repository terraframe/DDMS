package dss.vector.solutions.permission;

import java.util.ArrayList;
import java.util.List;

import com.runwaysdk.business.rbac.Operation;
import com.runwaysdk.business.rbac.RoleDAOIF;
import com.runwaysdk.dataaccess.MdBusinessDAOIF;
import com.runwaysdk.dataaccess.MdClassDimensionDAOIF;
import com.runwaysdk.dataaccess.MdDimensionDAOIF;
import com.runwaysdk.dataaccess.metadata.MdBusinessDAO;
import com.runwaysdk.session.PermissionMap;
import com.runwaysdk.session.Session;

import dss.vector.solutions.geo.GeoHierarchy;
import dss.vector.solutions.geo.GeoHierarchyView;

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
      UniversalPermissionView view = UniversalPermissionView.getView(universal, permissions);
      list.add(view);
    }

    return list.toArray(new UniversalPermissionView[list.size()]);
  }

  private static UniversalPermissionView getView(GeoHierarchyView universal, PermissionMap permissions)
  {
    MdBusinessDAOIF mdBusiness = MdBusinessDAO.get(universal.getReferenceId());
    MdDimensionDAOIF mdDimension = Session.getCurrentDimension();
    MdClassDimensionDAOIF mdClassDimension = mdBusiness.getMdClassDimension(mdDimension);
    String key = mdClassDimension.getPermissionKey();
        
    UniversalPermissionView permission = new UniversalPermissionView();
    permission.setLabel(universal.getDisplayLabel());
    permission.setUniversalId(mdBusiness.getId());
    permission.setPermission(permissions.containsPermission(key, Operation.CREATE));

    return permission;
  }

}
