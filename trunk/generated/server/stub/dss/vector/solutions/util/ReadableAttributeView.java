package dss.vector.solutions.util;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.terraframe.mojo.business.rbac.ActorDAO;
import com.terraframe.mojo.business.rbac.ActorDAOIF;
import com.terraframe.mojo.business.rbac.Authenticate;
import com.terraframe.mojo.business.rbac.Operation;
import com.terraframe.mojo.business.rbac.RoleDAO;
import com.terraframe.mojo.business.rbac.UserDAO;
import com.terraframe.mojo.constants.MdAttributeInfo;
import com.terraframe.mojo.dataaccess.MdAttributeDAOIF;
import com.terraframe.mojo.dataaccess.MdClassDAOIF;
import com.terraframe.mojo.dataaccess.cache.DataNotFoundException;
import com.terraframe.mojo.dataaccess.metadata.MdClassDAO;
import com.terraframe.mojo.dataaccess.transaction.Transaction;
import com.terraframe.mojo.generation.loader.Reloadable;
import com.terraframe.mojo.session.Session;
import com.terraframe.mojo.system.metadata.MdAttribute;

public class ReadableAttributeView extends ReadableAttributeViewBase implements com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1239221651119L;

  public ReadableAttributeView()
  {
    super();
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

      ReadableAttributeView view = new ReadableAttributeView();
      view.setAttributeName(mdAttribute.definesAttribute());
      view.setDisplayLabel(mdAttribute.getDisplayLabel(Session.getCurrentLocale()));
      view.setAttributeRequired(mdAttribute.isRequired());
      view.setAttributeDescription(mdAttribute.getDescription(Session.getCurrentLocale()));

      Set<Operation> permissions = actor.getAssignedPermissions(mdAttribute);

      boolean readable = permissions.contains(Operation.READ);

      view.setReadPermission(readable);

      // view.apply();
      list.add(view);
    }

    // Sort alphabetically by attribute name
    Collections.sort(list, new Sorter());

    return list.toArray(new ReadableAttributeView[list.size()]);
  }

  @Transaction
  @Authenticate
  public static void setActorAttributes(String universal, String actorName, ReadableAttributeView[] attributeViews)
  {
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

      MdAttribute mdAttribute = MdAttribute.get(mdAttributeDAO.getId());
      String oldValue = mdAttribute.getDisplayLabel().getValue();
      String newValue = view.getDisplayLabel();
      
      if (!oldValue.equals(newValue))
      {
        mdAttribute.lock();
        mdAttribute.getDisplayLabel().setValue(newValue);
        mdAttribute.apply();
      }

      if (view.getReadPermission() != null && view.getReadPermission())
      {
        actor.grantPermission(Operation.READ, mdAttribute.getId());
      }
      else
      {
        actor.revokePermission(Operation.READ, mdAttribute.getId());
      }
    }
    actor.apply();
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
