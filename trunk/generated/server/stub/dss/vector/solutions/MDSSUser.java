package dss.vector.solutions;

import com.terraframe.mojo.business.BusinessFacade;
import com.terraframe.mojo.business.rbac.Operation;
import com.terraframe.mojo.business.rbac.RoleDAO;
import com.terraframe.mojo.business.rbac.RoleDAOIF;
import com.terraframe.mojo.business.rbac.UserDAO;
import com.terraframe.mojo.business.rbac.UserDAOIF;
import com.terraframe.mojo.dataaccess.transaction.Transaction;
import com.terraframe.mojo.session.CreatePermissionException;
import com.terraframe.mojo.session.DeletePermissionException;
import com.terraframe.mojo.session.Session;
import com.terraframe.mojo.session.SessionIF;
import com.terraframe.mojo.system.Assignments;
import com.terraframe.mojo.system.Roles;

import dss.vector.solutions.geo.generated.GeoEntity;

public class MDSSUser extends MDSSUserBase implements com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1240853345874L;

  public MDSSUser()
  {
    super();
  }

  @Override
  public void apply()
  {
    // Change for ticket #664
    this.setSessionLimit(5);

    super.apply();

    // Assign this user to the GUIVisibility role
    UserDAO userDAO = (UserDAO) BusinessFacade.getEntityDAO(this).getEntityDAO();

    RoleDAO.findRole(MDSSRoleInfo.GUI_VISIBILITY).assignMember(userDAO);
  }
  
  @Transaction
  public void directApply()
  {
    super.apply();
  }

  @Override
  protected String buildKey()
  {
    return this.getUsername();
  }

  @Override
  @Transaction
  public void updateRoles(String[] assign, String[] revoke)
  {
    // Since permissions are not checked when modifying DAOs
    // You need to do an explict CRUD check on the Assignments
    // class to ensure that the user has permission to
    // assign permissions
    SessionIF session = Session.getCurrentSession();

    UserDAO userDAO = (UserDAO) BusinessFacade.getEntityDAO(this).getEntityDAO();

    // First clear all existing roles except GUI visibility
    String[] roles = MDSSUser.getAssignableRoles();

    for (String roleName : roles)
    {
      RoleDAOIF roleDAO = RoleDAO.findRole(roleName);

      if (session != null && !session.checkTypeAccess(Operation.DELETE, Assignments.CLASS))
      {
        String msg = "The user does not have permissions to assign members to roles";
        Assignments entity = new Assignments(this, Roles.get(roleDAO.getId()));

        throw new DeletePermissionException(msg, entity, session.getUser());
      }

      RoleDAO.findRole(roleName).getBusinessDAO().deassignMember(userDAO);
    }

    for (String id : assign)
    {
      RoleDAOIF role = RoleDAO.get(id);

      if (session != null && !session.checkTypeAccess(Operation.CREATE, Assignments.CLASS))
      {
        String msg = "The user does not have permissions to assign members to roles";
        Assignments entity = new Assignments(this, Roles.get(role.getId()));

        throw new CreatePermissionException(msg, entity, session.getUser());
      }

      role.assignMember(userDAO);
    }
  }

  private static String[] getAssignableRoles()
  {
    return new String[] { MDSSRoleInfo.DATACAPTURER, MDSSRoleInfo.ENTOMOLOGIST, MDSSRoleInfo.MANAGER, MDSSRoleInfo.MDSS, MDSSRoleInfo.MDSS_CORRDINATOR, MDSSRoleInfo.OPERATIONAL_MANAGER, MDSSRoleInfo.STOCK_STAFF, };
  }

  public static void changeRootGeoEntity(String geoEntityId)
  {
    GeoEntity geoEntity = GeoEntity.get(geoEntityId);

    UserDAOIF user = Session.getCurrentSession().getUser();
    MDSSUser mdssUser = MDSSUser.get(user.getId());

    mdssUser.appLock();
    mdssUser.setRootGeoEntity(geoEntity);
    mdssUser.apply();
  }
}
