package dss.vector.solutions.permission;

import java.util.List;

import com.runwaysdk.business.rbac.Operation;
import com.runwaysdk.business.rbac.RoleDAO;
import com.runwaysdk.dataaccess.MdAttributeDAOIF;
import com.runwaysdk.dataaccess.MdAttributeDimensionDAOIF;
import com.runwaysdk.dataaccess.MdClassDAOIF;
import com.runwaysdk.dataaccess.MdClassDimensionDAOIF;
import com.runwaysdk.dataaccess.MdDimensionDAOIF;
import com.runwaysdk.dataaccess.MdMethodDAOIF;
import com.runwaysdk.dataaccess.MdRelationshipDAOIF;
import com.runwaysdk.dataaccess.MetadataDAOIF;
import com.runwaysdk.generation.loader.Reloadable;
import com.runwaysdk.system.Roles;

import dss.vector.solutions.general.SystemURL;

public class WriteAction extends PermissionAction implements Reloadable
{
  public WriteAction(SystemURL url, List<MdDimensionDAOIF> mdDimensions)
  {
    super(url, url.getWriteRoleDAO(), mdDimensions);
  }
  
  public WriteAction(SystemURL url, RoleDAO role, List<MdDimensionDAOIF> mdDimensions)
  {
    super(url, role, mdDimensions);
  }

  @Override
  protected void doIt(MdDimensionDAOIF mdDimension, MetadataDAOIF metadata)
  {
    if (metadata instanceof MdClassDAOIF)
    {
      MdClassDAOIF mdClass = (MdClassDAOIF) metadata;
      MdClassDimensionDAOIF mdClassDimension = mdClass.getMdClassDimension(mdDimension);

      role.grantPermission(Operation.CREATE, mdClassDimension.getId());
      role.grantPermission(Operation.DELETE, mdClassDimension.getId());
      role.grantPermission(Operation.WRITE, mdClassDimension.getId());
      
      if (metadata instanceof MdRelationshipDAOIF)
      {
        role.grantPermission(Operation.ADD_CHILD, mdClass.getId());        
        role.grantPermission(Operation.ADD_PARENT, mdClass.getId());        
      }

      List<? extends MdAttributeDAOIF> attributes = mdClass.definesAttributes();

      for (MdAttributeDAOIF mdAttribute : attributes)
      {
        MdAttributeDimensionDAOIF mdAttributeDimension = mdAttribute.getMdAttributeDimension(mdDimension);

        role.grantPermission(Operation.WRITE, mdAttributeDimension.getId());
      }
    }
    else if (metadata instanceof MdMethodDAOIF)
    {
      role.grantPermission(Operation.EXECUTE, metadata.getId());
    }
  }

  @Override
  public void updateURL()
  {
    Roles role = this.getRole();

    SystemURL url = this.getUrl();
    url.setWriteRole(role);
    url.apply();
  }
}
