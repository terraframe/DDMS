package dss.vector.solutions.permission;

import com.runwaysdk.business.rbac.RoleDAO;
import com.runwaysdk.dataaccess.MdDimensionDAOIF;
import com.runwaysdk.dataaccess.MetadataDAOIF;
import com.runwaysdk.dataaccess.cache.DataNotFoundException;
import com.runwaysdk.dataaccess.metadata.MdDimensionDAO;
import com.runwaysdk.generation.loader.Reloadable;
import com.runwaysdk.query.OIterator;
import com.runwaysdk.query.QueryFactory;
import com.runwaysdk.system.Roles;
import com.runwaysdk.system.metadata.MdDimension;

import dss.vector.solutions.MDSSRoleInfo;
import dss.vector.solutions.general.Disease;
import dss.vector.solutions.general.SystemURL;
import dss.vector.solutions.permissions.RoleProperty;
import dss.vector.solutions.permissions.RolePropertyQuery;

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
      role = RoleDAO.createRole(_roleName, _roleName);
      role.apply();
    }

    RoleProperty property = this.getRoleProperty(role);

    if (!url.hasRole(property))
    {
      property.removeExistingURLs();

      url.addRoles(property).apply();
    }
  }

  private RoleProperty getRoleProperty(RoleDAO role)
  {
    PermissionOption roleType = this.getRoleType();

    RolePropertyQuery query = new RolePropertyQuery(new QueryFactory());
    query.WHERE(query.getRole().EQ(role.getId()));
    query.AND(query.getDisease().EQ(disease));
    query.AND(query.getRoleType().containsExactly(roleType));

    OIterator<? extends RoleProperty> it = query.getIterator();

    try
    {
      if (it.hasNext())
      {
        return it.next();
      }

      RoleProperty property = new RoleProperty();
      property.setRole(Roles.get(role.getId()));
      property.setDisease(disease);
      property.addRoleType(roleType);
      property.apply();

      return property;
    }
    finally
    {
      it.close();
    }
  }

  protected abstract void doIt(MdDimensionDAOIF mdDimension, MetadataDAOIF metadata);

  protected abstract String getActionName();

  protected abstract PermissionOption getRoleType();
}
