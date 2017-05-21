package dss.vector.solutions.permission;

import java.util.List;

import com.runwaysdk.business.rbac.Authenticate;
import com.runwaysdk.business.rbac.Operation;
import com.runwaysdk.business.rbac.RoleDAO;
import com.runwaysdk.business.rbac.RoleDAOIF;
import com.runwaysdk.dataaccess.MdAttributeConcreteDAOIF;
import com.runwaysdk.dataaccess.MdAttributeDAOIF;
import com.runwaysdk.dataaccess.MdBusinessDAOIF;
import com.runwaysdk.dataaccess.MdClassDAOIF;
import com.runwaysdk.dataaccess.MdDimensionDAOIF;
import com.runwaysdk.dataaccess.MdRelationshipDAOIF;
import com.runwaysdk.dataaccess.metadata.MdBusinessDAO;
import com.runwaysdk.dataaccess.metadata.MdClassDAO;
import com.runwaysdk.dataaccess.metadata.MdRelationshipDAO;
import com.runwaysdk.dataaccess.transaction.AttributeNotificationMap;
import com.runwaysdk.dataaccess.transaction.Transaction;
import com.runwaysdk.query.QueryFactory;
import com.runwaysdk.query.SelectablePrimitive;
import com.runwaysdk.session.Session;
import com.runwaysdk.system.Roles;

import dss.vector.solutions.InstallProperties;
import dss.vector.solutions.MDSSRoleInfo;
import dss.vector.solutions.RequiredAttributeException;
import dss.vector.solutions.general.SystemURL;
import dss.vector.solutions.geo.AllPaths;
import dss.vector.solutions.geo.LocatedIn;
import dss.vector.solutions.geo.generated.GeoEntity;

public class MDSSRoleView extends MDSSRoleViewBase implements com.runwaysdk.generation.loader.Reloadable
{
  private static final long serialVersionUID = -1226626177;

  public MDSSRoleView()
  {
    super();
  }

  private void populateConcrete(Roles role)
  {
    if (!this.hasConcrete())
    {
      String _roleName = this.getDisplayLabel().replace(" ", "").trim();

      if (!_roleName.contains("."))
      {
        _roleName = MDSSRoleInfo.MDSS_PREFIX + "." + _roleName;
      }
      role.setRoleName(_roleName);
    }

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
    if (this.getDisplayLabel() == null || this.getDisplayLabel().equals(""))
    {
      MdClassDAOIF mdClass = MdClassDAO.getMdClassDAO(MDSSRoleView.CLASS);
      MdAttributeDAOIF mdAttribute = mdClass.definesAttribute(MDSSRoleView.DISPLAYLABEL);

      RequiredAttributeException exception = new RequiredAttributeException();
      exception.setAttributeLabel(mdAttribute.getDisplayLabel(Session.getCurrentLocale()));

      throw exception;
    }
    
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

  public boolean hasConcrete()
  {
    return this.getConcreteId() != null && !this.getConcreteId().equals("");
  }

  public MDSSRole getConcrete()
  {
    if (this.hasConcrete())
    {
      return MDSSRole.get(this.getConcreteId());
    }

    return null;
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
    if (this.hasConcrete())
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
  @Authenticate
  @Transaction
  public void setPermissions(PermissionView[] permissions)
  {
    InstallProperties.validateMasterOperation();

    RoleDAO role = this.getRole().getBusinessDAO();

    for (PermissionView view : permissions)
    {
      List<PermissionOption> permission = view.getPermission();
      SystemURL url = SystemURL.get(view.getUrlId());
      RoleDAOIF writeRole = url.getWriteRoleDAO();
      RoleDAOIF readRole = url.getReadRoleDAO();

      if (permission.contains(PermissionOption.WRITE))
      {
        role.addAscendant(writeRole);
        role.addAscendant(readRole);
      }
      else if (permission.contains(PermissionOption.READ))
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
  @Authenticate
  @Transaction
  public void setUniversalPermissions(UniversalPermissionView[] permissions)
  {
    InstallProperties.validateMasterOperation();

    RoleDAO role = this.getRole().getBusinessDAO();
    boolean hasUniversalPermissions = false;
    MdDimensionDAOIF dimension = Session.getCurrentDimension();

    for (UniversalPermissionView view : permissions)
    {
      MdBusinessDAOIF universal = MdBusinessDAO.get(view.getUniversalId());

      if (view.getPermission() != null && view.getPermission())
      {
        role.revokePermission(Operation.DENY_CREATE, universal.getId());
        role.revokePermission(Operation.DENY_WRITE, universal.getId());
        role.revokePermission(Operation.DENY_DELETE, universal.getId());
        role.grantPermission(Operation.CREATE, universal.getId());
        role.grantPermission(Operation.WRITE, universal.getId());
        role.grantPermission(Operation.DELETE, universal.getId());
        
        // Don't forget about the dimension...
        String mdClassDim = universal.getMdClassDimension(dimension).getId();
        role.revokePermission(Operation.DENY_CREATE, mdClassDim);
        role.revokePermission(Operation.DENY_WRITE, mdClassDim);
        role.revokePermission(Operation.DENY_DELETE, mdClassDim);
        role.grantPermission(Operation.CREATE, mdClassDim);
        role.grantPermission(Operation.DELETE, mdClassDim);
        role.grantPermission(Operation.WRITE, mdClassDim);

        List<? extends MdAttributeConcreteDAOIF> attributes = universal.definesAttributes();

        for (MdAttributeDAOIF mdAttribute : attributes)
        {
          role.grantPermission(Operation.WRITE, mdAttribute.getId());
        }

        hasUniversalPermissions = true;
      }
      else
      {
        role.revokePermission(Operation.CREATE, universal.getId());
        role.revokePermission(Operation.WRITE, universal.getId());
        role.revokePermission(Operation.DELETE, universal.getId());
        role.grantPermission(Operation.DENY_CREATE, universal.getId());
        role.grantPermission(Operation.DENY_DELETE, universal.getId());
        role.grantPermission(Operation.DENY_WRITE, universal.getId());
        
        // Don't forget about the dimension...
        String mdClassDim = universal.getMdClassDimension(dimension).getId();
        role.revokePermission(Operation.CREATE, mdClassDim);
        role.revokePermission(Operation.WRITE, mdClassDim);
        role.revokePermission(Operation.DELETE, mdClassDim);
        role.grantPermission(Operation.DENY_CREATE, mdClassDim);
        role.grantPermission(Operation.DENY_DELETE, mdClassDim);
        role.grantPermission(Operation.DENY_WRITE, mdClassDim);

        List<? extends MdAttributeConcreteDAOIF> attributes = universal.definesAttributes();

        for (MdAttributeDAOIF mdAttribute : attributes)
        {
          role.revokePermission(Operation.WRITE, mdAttribute.getId());
        }
      }
    }

    MdBusinessDAOIF universal = MdBusinessDAO.getMdBusinessDAO(GeoEntity.CLASS);

    List<? extends MdAttributeConcreteDAOIF> attributes = universal.definesAttributes();

    for (MdAttributeDAOIF mdAttribute : attributes)
    {
      if (hasUniversalPermissions)
      {
        role.grantPermission(Operation.WRITE, mdAttribute.getId());
      }
      else
      {
        role.revokePermission(Operation.WRITE, mdAttribute.getId());
      }
    }

    MdRelationshipDAOIF locatedIn = MdRelationshipDAO.getMdRelationshipDAO(LocatedIn.CLASS);
    MdBusinessDAOIF allPaths = MdBusinessDAO.getMdBusinessDAO(AllPaths.CLASS);

    if (hasUniversalPermissions)
    {
      role.grantPermission(Operation.WRITE, locatedIn.getId());
      role.grantPermission(Operation.CREATE, locatedIn.getId());
      role.grantPermission(Operation.DELETE, locatedIn.getId());

      role.grantPermission(Operation.WRITE, allPaths.getId());
      role.grantPermission(Operation.CREATE, allPaths.getId());
      role.grantPermission(Operation.DELETE, allPaths.getId());
    }
    else
    {
      role.revokePermission(Operation.WRITE, locatedIn.getId());
      role.revokePermission(Operation.CREATE, locatedIn.getId());
      role.revokePermission(Operation.DELETE, locatedIn.getId());

      role.revokePermission(Operation.WRITE, allPaths.getId());
      role.revokePermission(Operation.CREATE, allPaths.getId());
      role.revokePermission(Operation.DELETE, allPaths.getId());
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
