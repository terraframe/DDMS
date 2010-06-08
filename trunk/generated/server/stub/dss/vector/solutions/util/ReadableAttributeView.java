package dss.vector.solutions.util;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
import com.runwaysdk.dataaccess.MdClassDAOIF;
import com.runwaysdk.dataaccess.MdDimensionDAOIF;
import com.runwaysdk.dataaccess.cache.DataNotFoundException;
import com.runwaysdk.dataaccess.metadata.MdAttributeDAO;
import com.runwaysdk.dataaccess.metadata.MdClassDAO;
import com.runwaysdk.dataaccess.transaction.Transaction;
import com.runwaysdk.generation.loader.Reloadable;
import com.runwaysdk.session.PermissionFacade;
import com.runwaysdk.session.Session;
import com.runwaysdk.system.metadata.MdAttribute;
import com.runwaysdk.system.metadata.MdAttributeDimension;
import com.runwaysdk.system.metadata.MdClass;

import dss.vector.solutions.InstallProperties;
import dss.vector.solutions.ontology.BrowserField;

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
    MdClassDAOIF mdClass = MdClassDAO.getMdClassDAO(universal);

    List<ReadableAttributeView> list = new LinkedList<ReadableAttributeView>();

    for (MdAttributeDAOIF mdAttribute : mdClass.getAllDefinedMdAttributes())
    {
      // Don't show 'em system attributes, Owner, or Domain
      if (ignore(mdAttribute))
      {
        continue;
      }

      Set<Operation> permissions = actor.getAssignedPermissions(mdAttribute);

      boolean readable = permissions.contains(Operation.READ);

      // Check the dimension permissions
      if (!readable)
      {
        MdDimensionDAOIF mdDimension = Session.getCurrentDimension();

        if (mdDimension != null)
        {
          MdAttributeDimensionDAOIF mdAttributeDimension = mdAttribute.getMdAttributeDimension(mdDimension);

          permissions = actor.getAssignedPermissions(mdAttributeDimension);
          readable = permissions.contains(Operation.READ);
        }
      }

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
    MdClassDAOIF mdClass = MdClassDAO.getMdClassDAO(universal);
    Map<String, ? extends MdAttributeDAOIF> attributeMap = mdClass.getAllDefinedMdAttributeMap();

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
      MdAttributeDAOIF _mdAttribute = MdAttributeDAO.get(mdAttribute.getId());
      MdAttributeDimensionDAOIF _mdAttributeDimension = _mdAttribute.getMdAttributeDimension(_mdDimension);

      Boolean permission = view.getReadPermission();

      if (permission != null)
      {
        if (permission)
        {
          actor.grantPermission(Operation.READ, _mdAttributeDimension.getId());
        }
        else
        {
          actor.revokePermission(Operation.READ, _mdAttributeDimension.getId());
        }
      }
      
      ReadableAttributeView.setDimensionAttributes(view.getNotBlank(), _mdAttribute, _mdDimension);
    }

    // actor.apply();
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
}
