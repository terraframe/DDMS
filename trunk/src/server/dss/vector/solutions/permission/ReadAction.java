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
import com.runwaysdk.dataaccess.MdViewDAOIF;
import com.runwaysdk.dataaccess.MetadataDAOIF;
import com.runwaysdk.generation.loader.Reloadable;

import dss.vector.solutions.general.Disease;
import dss.vector.solutions.general.SystemURL;

public class ReadAction extends PermissionAction implements Reloadable
{
  public ReadAction(SystemURL url, Disease disease)
  {
    super(url, disease);
  }
  
  public ReadAction(RoleDAO role, Disease disease)
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

      role.grantPermission(Operation.READ, mdClassDimension.getId());
      role.grantPermission(Operation.READ_ALL, mdClassDimension.getId());

      if (mdClass instanceof MdViewDAOIF)
      {
        role.grantPermission(Operation.CREATE, mdClassDimension.getId());
        role.grantPermission(Operation.DELETE, mdClassDimension.getId());
        role.grantPermission(Operation.WRITE, mdClassDimension.getId());
        role.grantPermission(Operation.WRITE_ALL, mdClassDimension.getId());
      }
      else if (metadata instanceof MdRelationshipDAOIF)
      {
        role.grantPermission(Operation.READ_CHILD, mdClass.getId());        
        role.grantPermission(Operation.READ_PARENT, mdClass.getId());                
      }

//
//
//      List<? extends MdAttributeDAOIF> attributes = mdClass.definesAttributes();
//
//      for (MdAttributeDAOIF mdAttribute : attributes)
//      {
//        MdAttributeDimensionDAOIF mdAttributeDimension = mdAttribute.getMdAttributeDimension(mdDimension);
//
//        role.grantPermission(Operation.READ, mdAttributeDimension.getId());
//
//        if (mdClass instanceof MdViewDAOIF)
//        {
//          role.grantPermission(Operation.WRITE, mdAttributeDimension.getId());
//        }
//      }
    }
    else if (metadata instanceof MdMethodDAOIF)
    {
      role.grantPermission(Operation.EXECUTE, metadata.getId());
    }
  }

  @Override
  protected String getActionName()
  {
    return "Read";
  }

  @Override
  protected PermissionOption getRoleType()
  {
    return PermissionOption.READ;
  }
}
