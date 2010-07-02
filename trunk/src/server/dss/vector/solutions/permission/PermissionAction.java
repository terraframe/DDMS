package dss.vector.solutions.permission;

import com.runwaysdk.business.rbac.RoleDAO;
import com.runwaysdk.dataaccess.MdDimensionDAOIF;
import com.runwaysdk.dataaccess.MetadataDAOIF;
import com.runwaysdk.dataaccess.cache.DataNotFoundException;
import com.runwaysdk.dataaccess.metadata.MdDimensionDAO;
import com.runwaysdk.generation.loader.Reloadable;
import com.runwaysdk.system.Roles;
import com.runwaysdk.system.metadata.MdDimension;

import dss.vector.solutions.MDSSRoleInfo;
import dss.vector.solutions.general.Disease;
import dss.vector.solutions.general.SystemURL;
import dss.vector.solutions.permissions.RoleProperty;

public abstract class PermissionAction implements Reloadable
{
  private SystemURL url;
  
  protected RoleDAO role;

  private Disease   disease;

  protected PermissionAction(SystemURL url, Disease disease)
  {
    this.url = url;
    this.disease = disease;

    this.initializeRole();
  }

  protected PermissionAction(RoleDAO role, Disease disease)
  {
    this.disease = disease;
    this.role = role;
  }
  
  public SystemURL getUrl()
  {
    return url;
  }

  public Roles getRole()
  {
    return Roles.get(role.getId());
  }

  public void assign(MetadataDAOIF metadata)
  {
    MdDimension dimension = disease.getDimension();
    MdDimensionDAOIF mdDimension = MdDimensionDAO.get(dimension.getId());

    this.doIt(mdDimension, metadata);
  }

  private final void initializeRole()
  {
    String name = url.getKey().replace(" ", "");
    String _roleName = MDSSRoleInfo.MDSS_PREFIX + "." + disease.getKey() + "." + name + "." + this.getActionName();

    try
    {
      role = RoleDAO.findRole(_roleName).getBusinessDAO();
    }
    catch (DataNotFoundException e)
    {
      PermissionOption roleType = this.getRoleType();
      
      role = RoleDAO.createRole(_roleName, _roleName);
      role.apply();

      RoleProperty property = new RoleProperty();
      property.setRole(Roles.get(role.getId()));
      property.setDisease(disease);
      property.addRoleType(roleType);
      property.apply();
      
      url.addRoles(property).apply();
    }
  }

  protected abstract void doIt(MdDimensionDAOIF mdDimension, MetadataDAOIF metadata);
  
  protected abstract String getActionName();
  
  protected abstract PermissionOption getRoleType();
}
