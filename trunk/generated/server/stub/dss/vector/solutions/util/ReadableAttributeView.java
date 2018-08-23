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
package dss.vector.solutions.util;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.runwaysdk.business.BusinessFacade;
import com.runwaysdk.business.rbac.ActorDAO;
import com.runwaysdk.business.rbac.ActorDAOIF;
import com.runwaysdk.business.rbac.Authenticate;
import com.runwaysdk.business.rbac.Operation;
import com.runwaysdk.business.rbac.RoleDAO;
import com.runwaysdk.business.rbac.UserDAO;
import com.runwaysdk.constants.MdAttributeInfo;
import com.runwaysdk.dataaccess.MdAttributeConcreteDAOIF;
import com.runwaysdk.dataaccess.MdAttributeDAOIF;
import com.runwaysdk.dataaccess.MdAttributeDimensionDAOIF;
import com.runwaysdk.dataaccess.MdAttributeVirtualDAOIF;
import com.runwaysdk.dataaccess.MdClassDAOIF;
import com.runwaysdk.dataaccess.MdDimensionDAOIF;
import com.runwaysdk.dataaccess.cache.DataNotFoundException;
import com.runwaysdk.dataaccess.metadata.MdClassDAO;
import com.runwaysdk.dataaccess.transaction.Transaction;
import com.runwaysdk.generation.loader.Reloadable;
import com.runwaysdk.session.PermissionFacade;
import com.runwaysdk.session.PermissionMap;
import com.runwaysdk.session.Session;
import com.runwaysdk.system.Roles;
import com.runwaysdk.system.metadata.MdAttribute;
import com.runwaysdk.system.metadata.MdAttributeDimension;
import com.runwaysdk.system.metadata.MdClass;

import dss.vector.solutions.InstallProperties;
import dss.vector.solutions.MDSSRoleInfo;
import dss.vector.solutions.ontology.BrowserField;
import dss.vector.solutions.permission.MDSSRole;
import dss.vector.solutions.permission.PermissionChange;

public class ReadableAttributeView extends ReadableAttributeViewBase implements com.runwaysdk.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1239221651119L;

  public ReadableAttributeView()
  {
    super();
  }

  public static ReadableAttributeView[] getReadableAttributes(String className)
  {
    MdClass mdClass = MdClass.getMdClass(className);
    MdClassDAO mdClassDAO = (MdClassDAO) BusinessFacade.getEntityDAO(mdClass);

    String sessionId = Session.getCurrentSession().getId();

    List<ReadableAttributeView> list = new LinkedList<ReadableAttributeView>();

    for (MdAttributeDAOIF mdAttribute : mdClassDAO.getAllDefinedMdAttributes())
    {
      boolean readable = PermissionFacade.checkAttributeReadAccess(sessionId, mdClass, mdAttribute);

      if (mdAttribute.isSystem() || !readable)
      {
        continue;
      }

      ReadableAttributeView view = createReadableAttributeView(mdAttribute, readable);

      list.add(view);
    }

    return list.toArray(new ReadableAttributeView[list.size()]);
  }

  public static ReadableAttributeView[] getActorAttributes(String universal, String actorName)
  {
    ActorDAOIF actor = getActor(actorName);
    PermissionMap map = actor.getOperations();

    MdClassDAOIF mdClass = MdClassDAO.getMdClassDAO(universal);
    MdDimensionDAOIF mdDimension = Session.getCurrentDimension();

    List<ReadableAttributeView> list = new LinkedList<ReadableAttributeView>();

    for (MdAttributeDAOIF mdAttribute : mdClass.getAllDefinedMdAttributes())
    {
      // Don't show 'em system attributes, Owner, or Domain
      if (ignore(mdAttribute))
      {
        continue;
      }

      MdAttributeDimensionDAOIF mdAttributeDimension = mdAttribute.getMdAttributeDimension(mdDimension);
      boolean readable = !map.containsPermission(mdAttributeDimension.getPermissionKey(), Operation.DENY_READ);

      ReadableAttributeView view = createReadableAttributeView(mdAttribute, readable);

      list.add(view);
    }

    // Sort alphabetically by attribute name
    Collections.sort(list, new Sorter());

    return list.toArray(new ReadableAttributeView[list.size()]);
  }

  /**
   * Creates and populates a ReadableAttributeView based on the given
   * MdAttributeDAOIF and readable flag.
   * 
   * @param mdAttribute
   * @param readable
   * @return
   */
  private static ReadableAttributeView createReadableAttributeView(MdAttributeDAOIF mdAttribute, boolean readable)
  {
    String attrName = mdAttribute.definesAttribute();
    String display = mdAttribute.getDisplayLabel(Session.getCurrentLocale());
    String desc = mdAttribute.getDescription(Session.getCurrentLocale());
    BrowserField field = BrowserField.getBrowserField(mdAttribute);
    MdDimensionDAOIF mdDimension = Session.getCurrentDimension();
    MdAttributeDimensionDAOIF mdAttributeDimension = mdAttribute.getMdAttributeConcrete().getMdAttributeDimension(mdDimension);

    ReadableAttributeView view = new ReadableAttributeView();
    view.setAttributeName(attrName);
    view.setDisplayLabel(display);
    view.setAttributeRequired(mdAttribute.isRequired());
    view.setAttributeDescription(desc);
    view.setReadPermission(readable);
    view.setNotBlank(mdAttributeDimension.isRequired());
    view.setBarcode(AttributeMetadata.isBarcode(mdAttribute));
    view.setBasic(AttributeMetadata.isBasic(mdAttribute));

    if (field != null)
    {
      view.setFieldId(field.getId());
    }

    return view;
  }

  @Transaction
  @Authenticate
  public static void setActorAttributes(String universal, String actorName, ReadableAttributeView[] attributeViews)
  {
    InstallProperties.validateMasterOperation();

    ActorDAO actor = (ActorDAO) getActor(actorName).getBusinessDAO();
    PermissionMap existingPermissions = actor.getOperations();

    MdClassDAOIF mdClass = MdClassDAO.getMdClassDAO(universal);
    Map<String, ? extends MdAttributeDAOIF> attributeMap = mdClass.getAllDefinedMdAttributeMap();

    List<PermissionChange> newPermissions = new LinkedList<PermissionChange>();

    for (ReadableAttributeView view : attributeViews)
    {
      MdAttributeDAOIF mdAttributeDAO = attributeMap.get(view.getAttributeName().toLowerCase());

      if (ignore(mdAttributeDAO))
      {
        continue;
      }

      MdAttribute mdAttribute = MdAttribute.get(mdAttributeDAO.getMdAttributeConcrete().getId());
      String oldValue = mdAttribute.getDisplayLabel().getValue();
      String newLabel = view.getDisplayLabel();

      if (!oldValue.equals(newLabel))
      {
        mdAttribute.lock();
        mdAttribute.getDisplayLabel().setValue(newLabel);
        mdAttribute.apply();
      }

      MdDimensionDAOIF _mdDimension = Session.getCurrentDimension();

      MdAttributeDimensionDAOIF _mdAttributeDimension = mdAttributeDAO.getMdAttributeDimension(_mdDimension);

      Boolean permission = view.getReadPermission();
      if (MDSSRoleInfo.GUI_VISIBILITY.equals(actorName) && view.getNotBlank() != null && view.getNotBlank())
      {
        // GUI Visibility cannot hide notBlank fields!
        permission = new Boolean(true);
      }
      Boolean existing = !existingPermissions.containsPermission(_mdAttributeDimension.getPermissionKey(), Operation.DENY_READ);

      if (permission != null && permission != existing)
      {
        newPermissions.add(new PermissionChange(!permission, _mdAttributeDimension.getId()));

        if (mdAttributeDAO instanceof MdAttributeVirtualDAOIF)
        {
          MdAttributeConcreteDAOIF mdAttributeConcrete = mdAttributeDAO.getMdAttributeConcrete();
          MdAttributeDimensionDAOIF mdAttributeConcreteDimension = mdAttributeConcrete.getMdAttributeDimension(_mdDimension);

          newPermissions.add(new PermissionChange(!permission, mdAttributeConcreteDimension.getId()));
        }

      }

      ReadableAttributeView.setDimensionAttributes(view.getNotBlank(), mdAttributeDAO, _mdDimension);

      /*
       * Update the barcode status
       */
      AttributeMetadata metadata = AttributeMetadata.getMetadata(mdAttributeDAO);

      if (view.getBarcode() && metadata == null)
      {
        metadata = new AttributeMetadata();
        metadata.setReferencedMdAttributeId(mdAttributeDAO.getId());
        metadata.setBarcode(view.getBarcode());
        metadata.apply();
      }
      else if (metadata != null)
      {
        metadata.appLock();
        metadata.setBarcode(view.getBarcode());
        metadata.apply();
      }
    }

    ReadableAttributeView.assignPermissions(actor, newPermissions);

  }

  private static void assignPermissions(ActorDAO actor, List<PermissionChange> permissions)
  {
    List<ActorDAO> actors = new LinkedList<ActorDAO>();
    actors.add(actor);

    if (actor instanceof RoleDAO)
    {
      RoleDAO role = (RoleDAO) actor;

      if (role.getRoleName().equalsIgnoreCase(MDSSRoleInfo.GUI_VISIBILITY))
      {
        Roles[] roles = MDSSRole.getRoles();

        for (Roles _role : roles)
        {
          actors.add(RoleDAO.get(_role.getId()).getBusinessDAO());
        }
      }
    }

    for (ActorDAO _actor : actors)
    {
      for (PermissionChange permission : permissions)
      {
        if (permission.isDeny())
        {
          _actor.grantPermission(Operation.DENY_READ, permission.getMetadataId());
        }
        else
        {
          _actor.revokePermission(Operation.DENY_READ, permission.getMetadataId());
        }
      }
    }
  }

  /**
   * Sets the required field on the coresponding MdAttributeConcrete
   * 
   * @param notBlank
   * @param mdAttribute
   * @param mdDimension
   */
  private static void setDimensionAttributes(Boolean notBlank, MdAttributeDAOIF mdAttribute, MdDimensionDAOIF mdDimension)
  {
    MdAttributeConcreteDAOIF mdAttributeConcrete = mdAttribute.getMdAttributeConcrete();
    MdAttributeDimensionDAOIF _mdAttributeDimension = mdAttributeConcrete.getMdAttributeDimension(mdDimension);

    MdAttributeDimension mdAttributeDimension = MdAttributeDimension.lock(_mdAttributeDimension.getId());
    mdAttributeDimension.setRequired(notBlank != null && notBlank);
    mdAttributeDimension.apply();
  }

  private static ActorDAOIF getActor(String actorName)
  {
    ActorDAOIF actor;
    try
    {
      actor = RoleDAO.findRole(actorName);
    }
    catch (DataNotFoundException e)
    {
      actor = UserDAO.findUser(actorName);
    }
    return actor;
  }

  private static class Sorter implements Comparator<ReadableAttributeView>, Reloadable
  {
    public int compare(ReadableAttributeView o1, ReadableAttributeView o2)
    {
      return o1.getAttributeName().compareTo(o2.getAttributeName());
    }
  }

  /**
   * We ignore certain special attributes that don't make sense for this view.
   * This checks to see if the given attribute should be ignored.
   * 
   * @param mdAttribute
   * @return
   */
  private static boolean ignore(MdAttributeDAOIF mdAttribute)
  {
    if (mdAttribute == null)
      return true;

    return mdAttribute.isSystem() || mdAttribute.definesAttribute().equals(MdAttributeInfo.OWNER) || mdAttribute.definesAttribute().equals(MdAttributeInfo.DOMAIN);
  }

  @Override
  public String toString()
  {
    return "Attribute=" + this.getAttributeName() + " Readable=" + this.getReadPermission();
  }

  public static boolean isVisible(MdAttributeDAOIF mdAttributeDAO)
  {
    if (Session.getCurrentSession() != null)
    {
      MdAttributeConcreteDAOIF mdAttributeConcrete = mdAttributeDAO.getMdAttributeConcrete();
      ActorDAO actor = (ActorDAO) getActor(MDSSRoleInfo.GUI_VISIBILITY).getBusinessDAO();
      PermissionMap existingPermissions = actor.getOperations();

      MdDimensionDAOIF _mdDimension = Session.getCurrentDimension();
      MdAttributeDimensionDAOIF _mdAttributeDimension = mdAttributeDAO.getMdAttributeDimension(_mdDimension);
      MdAttributeDimensionDAOIF _mdAttributeConcreteDimension = mdAttributeConcrete.getMdAttributeDimension(_mdDimension);

      boolean deny = existingPermissions.containsPermission(_mdAttributeDimension.getPermissionKey(), Operation.DENY_READ);
      boolean denyConcreate = existingPermissions.containsPermission(_mdAttributeConcreteDimension.getPermissionKey(), Operation.DENY_READ);

      return ! ( deny || denyConcreate );
    }

    return true;
  }
}
