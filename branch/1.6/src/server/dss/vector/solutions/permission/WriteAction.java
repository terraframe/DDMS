package dss.vector.solutions.permission;

import com.runwaysdk.business.rbac.Operation;
import com.runwaysdk.business.rbac.RoleDAO;
import com.runwaysdk.dataaccess.MdClassDAOIF;
import com.runwaysdk.dataaccess.MdClassDimensionDAOIF;
import com.runwaysdk.dataaccess.MdDimensionDAOIF;
import com.runwaysdk.dataaccess.MdMethodDAOIF;
import com.runwaysdk.dataaccess.MdRelationshipDAOIF;
import com.runwaysdk.dataaccess.MetadataDAOIF;
import com.runwaysdk.generation.loader.Reloadable;

import dss.vector.solutions.general.Disease;
import dss.vector.solutions.general.SystemURL;

public class WriteAction extends PermissionAction implements Reloadable
{  
  public WriteAction(SystemURL url, Disease disease)
  {
    super(url, disease);
  }
  
  public WriteAction(RoleDAO role, Disease disease)
  {
    super(role, disease);
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
      role.grantPermission(Operation.WRITE_ALL, mdClassDimension.getId());
      
      if (metadata instanceof MdRelationshipDAOIF)
      {
        role.grantPermission(Operation.ADD_CHILD, mdClass.getId());        
        role.grantPermission(Operation.ADD_PARENT, mdClass.getId());        
      }
    }
    else if (metadata instanceof MdMethodDAOIF)
    {
      role.grantPermission(Operation.EXECUTE, metadata.getId());
    }
  }

  @Override
  protected String getActionName()
  {
    return "Write";
  }

  @Override
  protected PermissionOption getRoleType()
  {
    return PermissionOption.WRITE;
  }
}
