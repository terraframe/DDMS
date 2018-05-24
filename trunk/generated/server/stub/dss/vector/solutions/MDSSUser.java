/*******************************************************************************
 * Copyright (C) 2018 IVCC
 * 
 * This program is free software: you can redistribute it and/or modify it under
 * the terms of the GNU General Public License as published by the Free Software
 * Foundation, either version 3 of the License, or (at your option) any later
 * version.
 * 
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 * 
 * You should have received a copy of the GNU General Public License along with
 * this program. If not, see <http://www.gnu.org/licenses/>.
 ******************************************************************************/
package dss.vector.solutions;

import java.util.Map;
import java.util.Set;

import com.runwaysdk.business.BusinessFacade;
import com.runwaysdk.business.rbac.Operation;
import com.runwaysdk.business.rbac.RoleDAO;
import com.runwaysdk.business.rbac.RoleDAOIF;
import com.runwaysdk.business.rbac.SingleActorDAOIF;
import com.runwaysdk.business.rbac.UserDAO;
import com.runwaysdk.business.rbac.UserDAOIF;
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
import dss.vector.solutions.general.SystemURL;
import dss.vector.solutions.geo.generated.GeoEntity;
import dss.vector.solutions.odk.ODKPermissionExporter;
import dss.vector.solutions.permission.MDSSRole;
import dss.vector.solutions.permission.PermissionOption;

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
    apply(true);
  }

  public void apply(boolean exportODK)
  {
    boolean isNew = this.isNew();
    boolean isUsernameModified = this.isModified(USERNAME);

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

    if (isUsernameModified && exportODK)
    {
      ODKPermissionExporter.export();
    }
  }

  @Override
  public void delete()
  {
    boolean isODK = this.hasODKRole();

    UserSettings.deleteIfExists(this);

    super.delete();

    if (isODK)
    {
      ODKPermissionExporter.export();
    }
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
    boolean before = this.hasODKRole();

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

    boolean after = this.hasODKRole();

    if (before != after)
    {
      ODKPermissionExporter.export();
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

  public boolean hasODKRole()
  {
    // If the person is an ODK user update the password
    SystemURL captureURL = SystemURL.getByName(SystemURL.ODK_DATA_CAPTURE);
    SystemURL adminURL = SystemURL.getByName(SystemURL.ODK_ADMINISTRATOR);

    RoleDAO read = captureURL.getRole(PermissionOption.READ);
    RoleDAO write = captureURL.getRole(PermissionOption.WRITE);
    RoleDAO admin = adminURL.getRole(PermissionOption.WRITE);

    UserDAOIF userDAO = UserDAO.get(this.getId());
    Set<RoleDAOIF> roles = userDAO.authorizedRoles();

    boolean isRead = roles.contains(read);
    boolean isWrite = roles.contains(write);
    boolean isAdmin = roles.contains(admin);

    return ( isRead || isWrite || isAdmin );
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
    SessionIF session = Session.getCurrentSession();

    if (session != null)
    {
      SingleActorDAOIF user = session.getUser();

      return SingleActor.get(user.getId());
    }

    return null;
  }
}
