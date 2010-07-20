package dss.vector.solutions.permission;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import com.runwaysdk.business.rbac.Operation;
import com.runwaysdk.business.rbac.OperationManager;
import com.runwaysdk.business.rbac.RoleDAO;
import com.runwaysdk.business.rbac.RoleDAOIF;
import com.runwaysdk.dataaccess.MetadataDAOIF;
import com.runwaysdk.dataaccess.RelationshipDAOIF;
import com.runwaysdk.dataaccess.transaction.Transaction;
import com.runwaysdk.query.OIterator;
import com.runwaysdk.query.QueryFactory;
import com.runwaysdk.query.OrderBy.SortOrder;
import com.runwaysdk.system.Roles;

import dss.vector.solutions.MDSSRoleInfo;

public class MDSSRole extends MDSSRoleBase implements com.runwaysdk.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1185298795;

  public MDSSRole()
  {
    super();
  }

  @Override
  @Transaction
  public void apply()
  {
    boolean isNew = !this.isAppliedToDB() && this.isNew();

    super.apply();

    // When new roles are defined we must copy over all of the existing
    // permissions for GUIVisibility.
    
    if (isNew)
    {
      RoleDAO role = RoleDAO.get(this.getRole().getId()).getBusinessDAO();
      RoleDAOIF guiVisibility = RoleDAO.findRole(MDSSRoleInfo.GUI_VISIBILITY);
      
      Set<RelationshipDAOIF> permissions = guiVisibility.getPermissions();
      
      for (RelationshipDAOIF permission : permissions)
      {
        MetadataDAOIF metadata = (MetadataDAOIF) permission.getChild();
        
        Set<Operation> operations = OperationManager.getOperations(permission);
        List<Operation> _operations = new LinkedList<Operation>(operations);
        
        role.grantPermission(_operations, metadata.getId());
      }
    }
  }

  @Override
  @Transaction
  public void delete()
  {
    super.delete();
    
    this.getRole().delete();
  }

  @Override
  public MDSSRoleView lockView()
  {
    this.lock();

    return this.getView();
  }

  public MDSSRoleView unlockView()
  {
    this.unlock();

    return this.getView();
  }

  public MDSSRoleView getView()
  {
    MDSSRoleView view = new MDSSRoleView();

    view.populateView(this);

    return view;
  }

  public static Roles[] getRoles()
  {
    List<Roles> list = new ArrayList<Roles>();
    MDSSRoleQuery query = new MDSSRoleQuery(new QueryFactory());
    query.ORDER_BY(query.getRole().getDisplayLabel().localize(), SortOrder.ASC);

    OIterator<? extends MDSSRole> it = query.getIterator();

    try
    {
      while (it.hasNext())
      {
        MDSSRole role = it.next();
        list.add(role.getRole());
      }
    }
    finally
    {
      it.close();
    }

    return list.toArray(new Roles[list.size()]);
  }

  
  public static Roles[] getAssignableRoles()
  {
    List<Roles> list = new ArrayList<Roles>();    
    list.add(Roles.findRoleByName(MDSSRoleInfo.SYSTEM));

    MDSSRoleQuery query = new MDSSRoleQuery(new QueryFactory());
    query.ORDER_BY(query.getRole().getDisplayLabel().localize(), SortOrder.ASC);
    
    OIterator<? extends MDSSRole> it = query.getIterator();
    
    try
    {
      while (it.hasNext())
      {
        MDSSRole role = it.next();
        list.add(role.getRole());
      }
    }
    finally
    {
      it.close();
    }
        
    return list.toArray(new Roles[list.size()]);
  }
  
  public static MDSSRoleView getViewByRoleName(String roleName)
  {
    String prefixedName = MDSSRoleInfo.MDSS_PREFIX + "." + roleName.replace(" ", "").trim();
    
    MDSSRoleQuery query = new MDSSRoleQuery(new QueryFactory());
    query.WHERE(query.getRole().getRoleName().EQ(prefixedName));
    
    OIterator<? extends MDSSRole> it = query.getIterator();
    
    try
    {
      if(it.hasNext())
      {
        return it.next().getView();
      }
      
      MDSSRoleView view = new MDSSRoleView();
      view.setRoleName(prefixedName);
      view.setDisplayLabel(roleName);
      
      return view;
    }
    finally
    {
      it.close();
    }
  }
}
