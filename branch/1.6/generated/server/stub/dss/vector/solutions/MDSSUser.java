package dss.vector.solutions;

import java.util.Map;

import com.runwaysdk.business.BusinessFacade;
import com.runwaysdk.business.rbac.Operation;
import com.runwaysdk.business.rbac.RoleDAO;
import com.runwaysdk.business.rbac.RoleDAOIF;
import com.runwaysdk.business.rbac.SingleActorDAOIF;
import com.runwaysdk.business.rbac.UserDAO;
import com.runwaysdk.dataaccess.transaction.Transaction;
import com.runwaysdk.session.CreatePermissionException;
import com.runwaysdk.session.DeletePermissionException;
import com.runwaysdk.session.Request;
import com.runwaysdk.session.Session;
import com.runwaysdk.session.SessionIF;
import com.runwaysdk.system.Assignments;
import com.runwaysdk.system.Roles;
import com.runwaysdk.system.SingleActor;

import dss.vector.solutions.general.Disease;
import dss.vector.solutions.geo.generated.GeoEntity;
import dss.vector.solutions.permission.MDSSRole;

public class MDSSUser extends MDSSUserBase implements com.runwaysdk.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1240853345874L;

  public MDSSUser()
  {
    super();
  }

  @Override
  public void apply()
  {
    boolean isNew = this.isNew();
    // Change for ticket #664
    if (isNew)
    {
      this.setSessionLimit(5);

    }

    super.apply();

    if (isNew)
    {
      UserSettings.createIfNotExists(this);
    }
  }

  @Override
  public void delete()
  {
    UserSettings.deleteIfExists(this);

    super.delete();
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

    // First clear all existing roles
    Roles[] roles = MDSSRole.getAssignableRoles();

    for (Roles role : roles)
    {
      RoleDAOIF roleDAOIF = RoleDAO.get(role.getId());

      if (session != null && !session.checkTypeAccess(Operation.DELETE, Assignments.CLASS))
      {
        String msg = "The user does not have permissions to assign members to roles";
        Assignments entity = new Assignments(this, Roles.get(roleDAOIF.getId()));

        throw new DeletePermissionException(msg, entity, session.getUser());
      }

      roleDAOIF.getBusinessDAO().deassignMember(userDAO);
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

  public static void changeRootGeoEntity(String geoEntityId)
  {
    GeoEntity geoEntity = GeoEntity.get(geoEntityId);

    SingleActorDAOIF user = Session.getCurrentSession().getUser();
    MDSSUser mdssUser = MDSSUser.get(user.getId());

    mdssUser.appLock();
    mdssUser.setRootGeoEntity(geoEntity);
    mdssUser.apply();
  }

  @Override
  @Transaction
  public void changeDisease(String diseaseName)
  {
    UserSettings settings = UserSettings.createIfNotExists(this);
    settings.lock();

    Disease disease = Disease.getByKey(diseaseName);
    settings.setDisease(disease);
    settings.apply();
  }

  @Override
  public String getDiseaseName()
  {
    UserSettings settings = UserSettings.createIfNotExists(this);
    Disease disease = settings.getDisease();
    if (disease != null)
    {
      return disease.getKeyName();
    }
    return Disease.MALARIA;
  }

  @Request
  public static Boolean canDeleteAll()
  {
    SessionIF session = Session.getCurrentSession();
    Map<String, String> roles = session.getUserRoles();

    boolean canDelete = ( roles.containsKey(MDSSRoleInfo.SYSTEM) || roles.containsKey(MDSSRoleInfo.RUNWAY_ADMIN) );

    return canDelete;
  }

  public static SingleActor getCurrentUser()
  {
    SingleActorDAOIF user = Session.getCurrentSession().getUser();

    return SingleActor.get(user.getId());
  }
}
