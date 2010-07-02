package dss.vector.solutions.permission;

import java.util.List;

import com.runwaysdk.business.rbac.RoleDAO;
import com.runwaysdk.dataaccess.MdDimensionDAOIF;
import com.runwaysdk.dataaccess.MetadataDAOIF;
import com.runwaysdk.generation.loader.Reloadable;
import com.runwaysdk.system.Roles;

import dss.vector.solutions.general.SystemURL;

public abstract class PermissionAction implements Reloadable
{
  private SystemURL              url;

  protected RoleDAO              role;

  private List<MdDimensionDAOIF> mdDimensions;

  public PermissionAction(SystemURL url, RoleDAO role, List<MdDimensionDAOIF> mdDimensions)
  {
    this.url = url;
    this.role = role;
    this.mdDimensions = mdDimensions;
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
    for (MdDimensionDAOIF mdDimension : mdDimensions)
    {
      this.doIt(mdDimension, metadata);
    }
  }

  public abstract void updateURL();

  protected abstract void doIt(MdDimensionDAOIF mdDimension, MetadataDAOIF metadata);
  
  public static PermissionAction getAction(String name, SystemURL url, List<MdDimensionDAOIF> mdDimensions)
  {
    if(name.equalsIgnoreCase("WRITE"))
    {
      return new WriteAction(url, mdDimensions);
    }
    
    return new ReadAction(url, mdDimensions);
  }
  
  public static PermissionAction getAction(String name, SystemURL url, RoleDAO role, List<MdDimensionDAOIF> mdDimensions)
  {
    if(name.equalsIgnoreCase("WRITE"))
    {
      return new WriteAction(url, role, mdDimensions);
    }
    
    return new ReadAction(url, role, mdDimensions);
  }
}
