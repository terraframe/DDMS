package dss.vector.solutions.permission;

import java.util.List;

import com.runwaysdk.business.rbac.Operation;
import com.runwaysdk.business.rbac.RoleDAO;
import com.runwaysdk.business.rbac.RoleDAOIF;
import com.runwaysdk.dataaccess.MdAttributeConcreteDAOIF;
import com.runwaysdk.dataaccess.MdAttributeDAOIF;
import com.runwaysdk.dataaccess.MdAttributeDimensionDAOIF;
import com.runwaysdk.dataaccess.MdBusinessDAOIF;
import com.runwaysdk.dataaccess.MdClassDimensionDAOIF;
import com.runwaysdk.dataaccess.MdDimensionDAOIF;
import com.runwaysdk.dataaccess.metadata.MdBusinessDAO;
import com.runwaysdk.dataaccess.transaction.AttributeNotificationMap;
import com.runwaysdk.dataaccess.transaction.Transaction;
import com.runwaysdk.query.QueryFactory;
import com.runwaysdk.query.SelectablePrimitive;
import com.runwaysdk.session.Session;
import com.runwaysdk.system.Roles;

import dss.vector.solutions.MDSSRoleInfo;
import dss.vector.solutions.general.SystemURL;

public class MDSSRoleView extends MDSSRoleViewBase implements com.runwaysdk.generation.loader.Reloadable
{
  private static final long serialVersionUID = -1226626177;

  public MDSSRoleView()
  {
    super();
  }

  private void populateConcrete(Roles role)
  {
    String _roleName = this.getRoleName();

    if (!_roleName.contains("."))
    {
      _roleName = MDSSRoleInfo.MDSS_PREFIX + "." + _roleName;
    }

    role.setRoleName(_roleName);
    role.getDisplayLabel().setValue(this.getDisplayLabel());
  }

  public void populateView(MDSSRole concrete)
  {
    Roles role = concrete.getRole();

    this.setConcreteId(concrete.getId());
    this.setRoleName(role.getRoleName());
    this.setDisplayLabel(role.getDisplayLabel().getValue());
  }

  @Override
  @Transaction
  public void apply()
  {
    MDSSRole concrete = new MDSSRole();
    Roles role = new Roles();

    if (this.hasConcrete())
    {
      concrete = MDSSRole.lock(this.getConcreteId());
      role = concrete.getRole();
      role.lock();
    }

    this.populateMapping(role, concrete);
    this.populateConcrete(role);

    role.apply();

    concrete.setRole(role);

    concrete.apply();

    this.populateView(concrete);
  }

  private void populateMapping(Roles role, MDSSRole concrete)
  {
    new AttributeNotificationMap(concrete, MDSSRole.ID, this, MDSSRoleView.CONCRETEID);
    new AttributeNotificationMap(role, Roles.ROLENAME, this, MDSSRoleView.ROLENAME);
    new AttributeNotificationMap(role, Roles.DISPLAYLABEL, this, MDSSRoleView.DISPLAYLABEL);
  }

  private boolean hasConcrete()
  {
    return this.getConcreteId() != null && !this.getConcreteId().equals("");
  }

  @Override
  public void deleteConcrete()
  {
    if (this.hasConcrete())
    {
      MDSSRole.get(this.getConcreteId()).delete();
    }
  }
  
  public RoleDAOIF getRole()
  {
    if(this.hasConcrete())
    {
      Roles role = MDSSRole.get(this.getConcreteId()).getRole();
      
      return RoleDAO.get(role.getId());
    }
    
    return null;
  }
  
  @Override
  public PermissionView[] getPermissions()
  {
    return PermissionView.getPermissions(this);
  }
  
  @Override
  public void setPermissions(PermissionView[] permissions)
  {
    RoleDAO role = this.getRole().getBusinessDAO();

    for(PermissionView view : permissions)
    {
      List<PermissionOption> permission = view.getPermission();
      SystemURL url = SystemURL.get(view.getUrlId());
      RoleDAOIF writeRole = RoleDAO.get(url.getWriteRole().getId());
      RoleDAOIF readRole = RoleDAO.get(url.getReadRole().getId());
      
      if(permission.contains(PermissionOption.WRITE))
      {
        role.addAscendant(writeRole);
        role.addAscendant(readRole);
      }
      else if(permission.contains(PermissionOption.READ))
      {        
        role.removeAscendant(writeRole);
        role.addAscendant(readRole);
      }
      else
      {        
        role.removeAscendant(writeRole);
        role.removeAscendant(readRole);
      }
    }
  }
  
  @Override
  public UniversalPermissionView[] getUniversalPermissions()
  {
    return UniversalPermissionView.getPermissions(this);
  }
  
  @Override
  public void setUniversalPermissions(UniversalPermissionView[] permissions)
  {
    RoleDAO role = this.getRole().getBusinessDAO();

    for(UniversalPermissionView view : permissions)
    {
      MdBusinessDAOIF universal = MdBusinessDAO.get(view.getUniversalId());
      MdDimensionDAOIF mdDimension = Session.getCurrentDimension();
      MdClassDimensionDAOIF mdClassDimension = universal.getMdClassDimension(mdDimension);
      
      if(view.getPermission() != null && view.getPermission())
      {
        role.grantPermission(Operation.CREATE, mdClassDimension.getId());
        role.grantPermission(Operation.WRITE, mdClassDimension.getId());
        role.grantPermission(Operation.DELETE, mdClassDimension.getId());
        
        List<? extends MdAttributeConcreteDAOIF> attributes = universal.definesAttributes();
        
        for(MdAttributeDAOIF mdAttribute : attributes)
        {
          MdAttributeDimensionDAOIF mdAttributeDimension = mdAttribute.getMdAttributeDimension(mdDimension);
          
          role.grantPermission(Operation.WRITE, mdAttributeDimension.getId());
        }
      }
      else
      {
        role.revokePermission(Operation.CREATE, mdClassDimension.getId());
        role.revokePermission(Operation.WRITE, mdClassDimension.getId());
        role.revokePermission(Operation.DELETE, mdClassDimension.getId());
        
        List<? extends MdAttributeConcreteDAOIF> attributes = universal.definesAttributes();
        
        for(MdAttributeDAOIF mdAttribute : attributes)
        {
          MdAttributeDimensionDAOIF mdAttributeDimension = mdAttribute.getMdAttributeDimension(mdDimension);
          
          role.revokePermission(Operation.WRITE, mdAttributeDimension.getId());
        }        
      }
    }
  }

  public static MDSSRoleViewQuery getPage(String sortAttribute, Boolean isAscending, Integer pageSize, Integer pageNumber)
  {
    MDSSRoleViewQuery query = new MDSSRoleViewQuery(new QueryFactory());

    if (sortAttribute == null)
    {
      sortAttribute = ROLENAME;
    }

    SelectablePrimitive selectable = (SelectablePrimitive) query.getComponentQuery().getSelectableRef(sortAttribute);

    if (isAscending)
    {
      query.ORDER_BY_ASC(selectable, sortAttribute);
    }
    else
    {
      query.ORDER_BY_DESC(selectable, sortAttribute);
    }

    if (pageSize != 0 && pageNumber != 0)
    {
      query.restrictRows(pageSize, pageNumber);
    }

    return query;
  }

}
