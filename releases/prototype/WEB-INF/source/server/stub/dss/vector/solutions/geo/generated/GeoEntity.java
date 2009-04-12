package dss.vector.solutions.geo.generated;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import com.terraframe.mojo.business.Business;
import com.terraframe.mojo.dataaccess.InvalidIdException;
import com.terraframe.mojo.dataaccess.ValueObject;
import com.terraframe.mojo.dataaccess.transaction.Transaction;
import com.terraframe.mojo.generation.loader.Reloadable;
import com.terraframe.mojo.query.GeneratedViewQuery;
import com.terraframe.mojo.query.OIterator;
import com.terraframe.mojo.query.QueryFactory;
import com.terraframe.mojo.query.ValueQuery;
import com.terraframe.mojo.query.ViewQueryBuilder;
import com.terraframe.mojo.session.Session;
import com.terraframe.mojo.system.metadata.MdBusiness;

import dss.vector.solutions.geo.ConfirmDeleteEntityException;
import dss.vector.solutions.geo.ConfirmParentChangeException;
import dss.vector.solutions.geo.DuplicateParentException;
import dss.vector.solutions.geo.GeoEntityView;
import dss.vector.solutions.geo.GeoEntityViewQuery;
import dss.vector.solutions.geo.GeoHierarchy;
import dss.vector.solutions.geo.LocatedIn;
import dss.vector.solutions.geo.LocatedInQuery;
import dss.vector.solutions.geo.LocatedInException;

public abstract class GeoEntity extends GeoEntityBase implements
    com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1234288139462L;

  public GeoEntity()
  {
    super();
  }

  @Override
  public void apply()
  {
    applyInternal();
  }

  /**
   * Applies this GeoEntity and recursively sets the activated status of all
   * children if the status has changed on the parent.
   * 
   * @return
   */
  @Transaction
  private Set<String> applyInternal()
  {
    Set<String> ids = new HashSet<String>();

    if (this.isModified(GeoEntity.ACTIVATED))
    {
      ids.addAll(setChildEntityActivated(this.getActivated(), this));

      ids.add(this.getId());
    }

    super.apply();

    return ids;
  }

  /**
   * Updates this GeoEntity and its children if its activated attribute has been
   * modified.
   * 
   * @return
   */
  @Override
  public String[] updateFromTree()
  {
    Set<String> ids = applyInternal();
    return ids.toArray(new String[ids.size()]);
  }

  public static GeoEntity searchByGeoId(java.lang.String geoId)
  {
    QueryFactory queryFactory = new QueryFactory();

    ValueQuery valueQuery = new ValueQuery(queryFactory);

    GeoEntityQuery geoEntityQ = new GeoEntityQuery(queryFactory);

    valueQuery.SELECT(geoEntityQ.getId(GeoEntity.ID));
    valueQuery.WHERE(geoEntityQ.getGeoId().EQ(geoId));

    OIterator<? extends ValueObject> iterator = valueQuery.getIterator();
    try
    {
      if (iterator.hasNext())
      {
        ValueObject valueObject = iterator.next();

        String id = valueObject.getValue(GeoEntity.ID);

        return (GeoEntity) Business.get(id);
      }
      else
      {
        String msg = "A GeoEntity with the geoId [" + geoId + "] does not exist";
        throw new InvalidIdException(msg, geoId);
      }
    }
    finally
    {
      iterator.close();
    }

    // Heads up: clean up
    // GeoEntityQuery query = new GeoEntityQuery(new QueryFactory());
    //
    // query.WHERE(query.getGeoId().EQ(geoId));
    //
    // OIterator<? extends GeoEntity> iterator = query.getIterator();
    // try
    // {
    // if (iterator.hasNext())
    // {
    // return iterator.next();
    // }
    // else
    // {
    // String msg = "A GeoEntity with the geoId [" + geoId + "] does not exist";
    // throw new InvalidIdException(msg, geoId);
    // }
    // }
    // finally
    // {
    // iterator.close();
    // }
  }

  /**
   * Throws an exception to alert the user before they change an entity's
   * parent.
   */
  @Override
  public void confirmChangeParent(String parentId)
  {
    GeoEntity parent = GeoEntity.get(parentId);

    ConfirmParentChangeException ex = new ConfirmParentChangeException();
    ex.setEntityName(parent.getEntityName());

    throw ex;
  }
  
  /**
   * Throws an exception to alert the user before they try to delete an entity
   * with more than on parent. If the entity only has one parent, then entity
   * is deleted as normal.
   */
  @Override
  public void confirmDeleteEntity(String parentId)
  {

    List<GeoEntity> parents = this.getImmediateParents();
    if(parents.size() > 1)
    {
      GeoEntity parent = GeoEntity.get(parentId);
      ConfirmDeleteEntityException ex = new ConfirmDeleteEntityException();
      ex.setEntityName(parent.getEntityName());

      throw ex;
    }
    else
    {
      this.delete();
    }
  }
  
  @Override
  @Transaction
  public void deleteRelationship(String parentId)
  {
    QueryFactory f = new QueryFactory();
    LocatedInQuery query = new LocatedInQuery(f);
    query.WHERE(query.childId().EQ(this.getId()));
    query.WHERE(query.parentId().EQ(parentId));
    
    OIterator<? extends LocatedIn> iter = query.getIterator();
    try
    {
      while(iter.hasNext())
      {
        iter.next().delete();
      }
    }
    finally
    {
      iter.close();
    }
  }

  /**
   * Deletes this GeoEntity and all its children in the LocatedIn relationship.
   */
  @Override
  @Transaction
  public void delete()
  {
    List<GeoEntity> children = this.getImmediateChildren();

    for (GeoEntity child : children)
    {
      child.delete();
    }

    super.delete();
  }

  /**
   * Returns all parents of this GeoEntity up to one level.
   * 
   * @return
   */
  public List<GeoEntity> getImmediateParents()
  {
    List<GeoEntity> parents = new LinkedList<GeoEntity>();
    OIterator<? extends GeoEntity> iter = this.getAllLocatedInGeoEntity();
    try
    {
      while (iter.hasNext())
      {
        parents.add(iter.next());
      }
    }
    finally
    {
      iter.close();
    }

    return parents;
  }

  /**
   * Returns all the children of this GeoEntity up to one level.
   * 
   * @return
   */
  public List<GeoEntity> getImmediateChildren()
  {
    List<GeoEntity> children = new LinkedList<GeoEntity>();
    OIterator<? extends GeoEntity> iter = this.getAllContainsGeoEntity();
    try
    {
      while (iter.hasNext())
      {
        children.add(iter.next());
      }
    }
    finally
    {
      iter.close();
    }

    return children;
  }

  /**
   * Sets all children of the parent GeoEntity to the given activated status. If
   * a child has more than one parent then nothing is changed for that child.
   * 
   * @param activated
   * @param parent
   * @return A list of ids for each updated child.
   */
  private static List<String> setChildEntityActivated(boolean activated, GeoEntity parent)
  {
    List<String> ids = new LinkedList<String>();
    List<GeoEntity> children = parent.getImmediateChildren();

    for (GeoEntity child : children)
    {

      // CHECK: a child with more than one parent set to active
      // cannot be deactivated.
      if (child.eligibleForActiveChange(activated))
      {
        child.appLock();
        child.setActivated(activated);
        ids.addAll(child.applyInternal());

        ids.add(child.getId());
      }
    }

    return ids;
  }

  /**
   * Checks if this GoeEntity is eligible to have its active status changed. The
   * general rule is as follows: A child with more than one parent set to active
   * cannot be deactivated. All other cases are allowed.
   * 
   * @param activated
   *            The active status of the parent.
   * @return
   */
  private boolean eligibleForActiveChange(boolean activated)
  {
    List<GeoEntity> parents = this.getImmediateParents();

    if (!activated && parents.size() > 1)
    {
      for (GeoEntity nextParent : parents)
      {
        if (nextParent.getActivated())
        {
          return false;
        }
      }

      return true;
    }
    else
    {
      return true;
    }
  }

  /**
   * Adds this GeoEntity as a child of the given parent for the
   * {@link LocatedIn} relationship. If this is not for a clone operation then
   * all prior parent relationships will be removed.
   * 
   */
  @Override
  @Transaction
  public String[] applyWithParent(String parentGeoEntityId, Boolean cloneOperation)
  {
    GeoEntity parent = GeoEntity.get(parentGeoEntityId);

    // set the active status of the child to that of the parent
    // unless the child has more than one parent.

    if (this.isNew())
    {
      this.apply(); // has no children
    }

    // make sure a child cannot be applied to itself
    if (this.getId().equals(parentGeoEntityId))
    {
      String error = "The child [" + this.getEntityName() + "] cannot be its own parent.";

      LocatedInException e = new LocatedInException(error);
      e.setEntityName(this.getEntityName());
      e.setParentDisplayLabel(this.getEntityName());
      throw e;
    }

    if (!cloneOperation)
    {
      OIterator<? extends LocatedIn> iter = this.getAllLocatedInGeoEntityRel();
      try
      {
        while (iter.hasNext())
        {
          iter.next().delete();
        }
      }
      finally
      {
        iter.close();
      }
    }
    else
    {
      // confirm this entity can't be applied to the same
      // parent more than once.
      QueryFactory f = new QueryFactory();
      LocatedInQuery q = new LocatedInQuery(f);
      q.WHERE(q.childId().EQ(this.getId()));
      q.WHERE(q.parentId().EQ(parentGeoEntityId));

      if (q.getCount() > 0)
      {
        String childDL = this.getEntityName();
        String parentDL = parent.getEntityName();

        String error = "The child [" + childDL + "] is already located in the parent [" + parentDL
            + "].";
        DuplicateParentException e = new DuplicateParentException(error);
        e.setChildEntityName(childDL);
        e.setParentEntityName(parentDL);

        throw e;
      }
    }

    this.addLocatedInGeoEntity(parent).apply();

    // update this GeoEntity and all its 
    // children with the parent's active status.
    boolean parentActivated = parent.getActivated();
    boolean childActivated = this.getActivated();
    
    if (parentActivated != childActivated && this.eligibleForActiveChange(parentActivated))
    {
      this.appLock();
      this.setActivated(parentActivated);
      Set<String> ids = this.applyInternal();
      return ids.toArray(new String[ids.size()]);
    }
    else
    {
      return new String[]{};
    }
  }

  @Override
  public LocatedIn addContainsGeoEntity(GeoEntity geoEntity)
  {
    validateHierarchy(geoEntity.getType(), this.getType());
    return super.addContainsGeoEntity(geoEntity);
  }

  @Override
  public LocatedIn addLocatedInGeoEntity(GeoEntity geoEntity)
  {
    validateHierarchy(this.getType(), geoEntity.getType());
    return super.addLocatedInGeoEntity(geoEntity);
  }

  /**
   * Validates that this GeoEntity is allowed in the given parent GeoEntity.
   * 
   * @param parentGeoEntityId
   * @return
   */
  private void validateHierarchy(String childType, String parentType)
  {
    MdBusiness childMd = MdBusiness.getMdBusiness(childType);
    MdBusiness parentMd = MdBusiness.getMdBusiness(parentType);

    GeoHierarchy parentGH = GeoHierarchy.getGeoHierarchyFromType(parentMd);
    GeoHierarchy childGH = GeoHierarchy.getGeoHierarchyFromType(childMd);

    OIterator<? extends GeoHierarchy> iter = childGH.getAllAllowedInGeoEntity();
    boolean match = false;
    try
    {
      while (iter.hasNext())
      {
        GeoHierarchy gh = iter.next();
        match = checkHierarchy(parentGH, gh);

        if (match)
        {
          break;
        }
      }
    }
    finally
    {
      iter.close();
    }

    if (!match)
    {
      String error = "The universal type [" + childType + "] cannot be located in [" + parentType + "].";
      LocatedInException e = new LocatedInException(error);
      e.setEntityName(this.getEntityName());
      e.setParentDisplayLabel(parentMd.getDisplayLabel().getValue(Session.getCurrentLocale()));
      throw e;
    }
  }

  /**
   * Recursively checks if the child GeoHierarcy is allowed in the parent.
   * 
   * @param parent
   * @param child
   * @return
   */
  private boolean checkHierarchy(GeoHierarchy parent, GeoHierarchy child)
  {
    if (child.getId().equals(parent.getId()))
    {
      return true;
    }

    // check the parent's parents
    OIterator<? extends GeoHierarchy> iter = child.getAllAllowedInGeoEntity();
    try
    {
      while (iter.hasNext())
      {
        GeoHierarchy next = iter.next();
        if (checkHierarchy(parent, next))
        {
          return true;
        }
      }
    }
    finally
    {
      iter.close();
    }

    return false;
  }

  /**
   * Returns a list of all LocatedIn children for which this GeoEntity is a
   * parent. The list is ordered by the entity name.
   */
  @Override
  public GeoEntityViewQuery getOrderedChildren(String filter)
  {
    QueryFactory f = new QueryFactory();
    OrderedGeoEntityQueryBuilder builder = new OrderedGeoEntityQueryBuilder(f, this);
    GeoEntityViewQuery query = new GeoEntityViewQuery(f, builder);
    
    return query;
    
//    QueryFactory f = new QueryFactory();
//    GeoEntityQuery geoQuery = new GeoEntityQuery(f);
//    LocatedInQuery locQuery = new LocatedInQuery(f);
//
//    locQuery.WHERE(locQuery.parentId().EQ(this.getId()));
//    geoQuery.WHERE(geoQuery.locatedInGeoEntity(locQuery));
//
//    // filter by type if possible (and all of type's child subclasses)
//    if (filter != null && filter.trim().length() > 0)
//    {
//      // get allowed types by filter, which includes all parents and children
//      // of the filter type and the filter type itself.
//      List<GeoHierarchy> allowedTypes = new LinkedList<GeoHierarchy>();
//      GeoHierarchy filterType = GeoHierarchy.getGeoHierarchyFromType(filter);
//
//      allowedTypes.addAll(filterType.getAllChildren());
//      allowedTypes.add(filterType);
//      allowedTypes.addAll(filterType.getAllParents());
//
//      String[] types = new String[allowedTypes.size()];
//      for (int i = 0; i < allowedTypes.size(); i++)
//      {
//        GeoHierarchy allowedType = allowedTypes.get(i);
//        types[i] = MDSSInfo.GENERATED_GEO_PACKAGE + "." + allowedType.getGeoEntityClass().getTypeName();
//      }
//
//      geoQuery.WHERE(geoQuery.getType().IN(types));
//    }
//
//    geoQuery.ORDER_BY_ASC(geoQuery.getEntityName());
//
//    return geoQuery;
  }
  
  private class OrderedGeoEntityQueryBuilder extends ViewQueryBuilder implements Reloadable
  {

    private GeoEntity geoEntity;
    private GeoEntityQuery geoEntityQuery;
    private LocatedInQuery locatedInQuery;
    
    protected OrderedGeoEntityQueryBuilder(QueryFactory queryFactory, GeoEntity geoEntity)
    {
      super(queryFactory);

      this.geoEntity = geoEntity;
      this.geoEntityQuery = new GeoEntityQuery(queryFactory);
      this.locatedInQuery = new LocatedInQuery(queryFactory);
    }

    @Override
    protected void buildSelectClause()
    {
      GeneratedViewQuery vQuery = this.getViewQuery();
      
      vQuery.map(GeoEntityView.GEOENTITYID, geoEntityQuery.getId());
      vQuery.map(GeoEntityView.GEOID, geoEntityQuery.getGeoId());
      vQuery.map(GeoEntityView.ACTIVATED, geoEntityQuery.getActivated());
      vQuery.map(GeoEntityView.ENTITYNAME, geoEntityQuery.getEntityName());
      vQuery.map(GeoEntityView.ENTITYTYPE, geoEntityQuery.getType());
    }

    @Override
    protected void buildWhereClause()
    {
      GeneratedViewQuery vQuery = this.getViewQuery();
      
      vQuery.WHERE(this.locatedInQuery.parentId().EQ(geoEntity.getId()));
      vQuery.WHERE(this.geoEntityQuery.locatedInGeoEntity(this.locatedInQuery));
      
      // FIXME restrict by filter
      
      vQuery.ORDER_BY_ASC(this.geoEntityQuery.getEntityName());
    }
    
  }
}