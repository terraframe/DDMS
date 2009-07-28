package dss.vector.solutions.geo.generated;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import com.terraframe.mojo.business.Business;
import com.terraframe.mojo.business.BusinessFacade;
import com.terraframe.mojo.constants.ComponentInfo;
import com.terraframe.mojo.constants.RelationshipInfo;
import com.terraframe.mojo.dataaccess.InvalidIdException;
import com.terraframe.mojo.dataaccess.MdAttributeConcreteDAOIF;
import com.terraframe.mojo.dataaccess.MdBusinessDAOIF;
import com.terraframe.mojo.dataaccess.MdClassDAOIF;
import com.terraframe.mojo.dataaccess.ProgrammingErrorException;
import com.terraframe.mojo.dataaccess.ValueObject;
import com.terraframe.mojo.dataaccess.database.DuplicateDataDatabaseException;
import com.terraframe.mojo.dataaccess.metadata.MdBusinessDAO;
import com.terraframe.mojo.dataaccess.metadata.MdClassDAO;
import com.terraframe.mojo.dataaccess.transaction.Transaction;
import com.terraframe.mojo.generation.loader.LoaderDecorator;
import com.terraframe.mojo.generation.loader.Reloadable;
import com.terraframe.mojo.gis.dataaccess.AttributeGeometryIF;
import com.terraframe.mojo.gis.dataaccess.MdAttributeGeometryDAOIF;
import com.terraframe.mojo.query.AND;
import com.terraframe.mojo.query.Condition;
import com.terraframe.mojo.query.F;
import com.terraframe.mojo.query.GeneratedViewQuery;
import com.terraframe.mojo.query.OIterator;
import com.terraframe.mojo.query.OR;
import com.terraframe.mojo.query.QueryFactory;
import com.terraframe.mojo.query.Selectable;
import com.terraframe.mojo.query.ValueQuery;
import com.terraframe.mojo.query.ViewQueryBuilder;
import com.terraframe.mojo.session.Session;
import com.terraframe.mojo.system.metadata.MdBusiness;
import com.terraframe.mojo.system.metadata.MdBusinessQuery;
import com.terraframe.mojo.system.metadata.MdClass;
import com.terraframe.mojo.util.IdParser;

import dss.vector.solutions.MDSSInfo;
import dss.vector.solutions.geo.AllPaths;
import dss.vector.solutions.geo.AllPathsQuery;
import dss.vector.solutions.geo.ConfirmParentChangeException;
import dss.vector.solutions.geo.DuplicateParentException;
import dss.vector.solutions.geo.GeoEntityView;
import dss.vector.solutions.geo.GeoEntityViewQuery;
import dss.vector.solutions.geo.GeoHierarchy;
import dss.vector.solutions.geo.GeoHierarchyView;
import dss.vector.solutions.geo.LocatedIn;
import dss.vector.solutions.geo.LocatedInException;
import dss.vector.solutions.geo.LocatedInQuery;
import dss.vector.solutions.geo.NoCompatibleTypesException;
import dss.vector.solutions.query.ActionNotAllowedException;
import dss.vector.solutions.util.GeoEntityImporter;

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
  }

  /**
   * Searches for a GeoEntity based on the entity name and type.
   * 
   * @param type
   * @param name
   * @return
   */
  public static ValueQuery searchByEntityName(String type, String name)
  {
    QueryFactory f = new QueryFactory();

    GeoEntityQuery q;
    if (type == null || type.trim().length() == 0)
    {
      q = new GeoEntityQuery(f);
    }
    else
    {
      Class<?> klass = LoaderDecorator.load(type + "Query");
      try
      {
        Constructor<?> constructor = klass.getConstructor(QueryFactory.class);
        q = (GeoEntityQuery) constructor.newInstance(f);
      }
      catch (Throwable t)
      {
        throw new ProgrammingErrorException(t);
      }
    }

    ValueQuery valueQuery = new ValueQuery(f);

    Selectable[] selectables = new Selectable[] { q.getId(GeoEntity.ID),
        q.getEntityName(GeoEntity.ENTITYNAME) };
    valueQuery.SELECT(selectables);

    String searchable = name + "%";
    valueQuery.WHERE(q.getEntityName(GeoEntity.ENTITYNAME).LIKEi(searchable));

    valueQuery.restrictRows(20, 1);

    return valueQuery;
  }

  /**
   * Searches for a GeoEntity based on the entity name and type.
   * 
   * @param type
   * @param name
   * @return
   */
  public static ValueQuery searchByEntityNameOrGeoId(String type, String name)
  {
    QueryFactory f = new QueryFactory();
    GeoEntityQuery q;

    if (type == null || type.trim().length() == 0)
    {
      q = new GeoEntityQuery(f);
    }
    else
    {
      Class<?> klass = LoaderDecorator.load(type + "Query");
      try
      {
        Constructor<?> constructor = klass.getConstructor(QueryFactory.class);
        q = (GeoEntityQuery) constructor.newInstance(f);
      }
      catch (Throwable t)
      {
        throw new ProgrammingErrorException(t);
      }
    }

    ValueQuery valueQuery = new ValueQuery(f);

    Selectable[] selectables = new Selectable[] { q.getId(GeoEntity.ID),
        q.getEntityName(GeoEntity.ENTITYNAME), q.getGeoId(GeoEntity.GEOID), q.getType(GeoEntity.TYPE) };
    valueQuery.SELECT(selectables);

    String searchable = name + "%";

    Condition or = OR.get(q.getEntityName(GeoEntity.ENTITYNAME).LIKEi(searchable), q.getGeoId().LIKEi(
        searchable));

    valueQuery.WHERE(or);

    valueQuery.restrictRows(20, 1);

    return valueQuery;
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
   * with more than on parent. If the entity only has one parent, then entity is
   * deleted as normal.
   */
  @Override
  public void confirmDeleteEntity(String parentId)
  {
    // V1 Restriction
    throw new ActionNotAllowedException();
    
    /*
    List<GeoEntity> parents = this.getImmediateParents();
    if (parents.size() > 1)
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
    */
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

    /*
     * // DO NO CLEANUP FOR V1 AND LET A REQUIRED REFERENCE ERROR BE THROWN //
     * clean up the paths table. This will invalidate the paths table
     * QueryFactory f = new QueryFactory(); AllPathsQuery pathsQuery = new
     * AllPathsQuery(f);
     * 
     * pathsQuery.WHERE(OR.get(pathsQuery.getChildGeoEntity().EQ(this),
     * pathsQuery.getParentGeoEntity().EQ(this)));
     * 
     * OIterator<? extends AllPaths> iter = pathsQuery.getIterator(); try {
     * while(iter.hasNext()) { iter.next().delete(); } } finally { iter.close();
     * }
     */

    super.delete();
  }

  /**
   * This GeoEntity is equal to the given object if the object is the same
   * object reference or if the ids match.
   */
  @Override
  public boolean equals(Object obj)
  {
    boolean equals = super.equals(obj);

    if (!equals && obj instanceof GeoEntity)
    {
      equals = this.getId().equals( ( (GeoEntity) obj ).getId());
    }

    return equals;
  }

  /**
   * Searches for the GeoEntity with the given geoId and returns itself and its
   * children and parents.
   * 
   * @param geoId
   * @param filter
   * @return
   */
  public static GeoEntityView[] searchAndCollectByGeoId(String geoId, String filter)
  {
    GeoEntity geoEntity = GeoEntity.searchByGeoId(geoId);

    return geoEntity.collectAllLocatedIn(true, filter);
  }

  /**
   * Collects all children and parents (optional) for the located in
   * relationship.
   */
  @Override
  public GeoEntityView[] collectAllLocatedIn(Boolean includeParents, String filter)
  {
    Set<GeoEntity> collected = new HashSet<GeoEntity>();

    collected.add(this);

    if (includeParents)
    {
      collected.addAll(this.getAllParents());
    }

    // include this GeoEntity and its children
    collected.addAll(this.getImmediateChildren());

    // translate the GeoEntities into their views
    // and filter out all unallowed types.
    List<GeoEntityView> finalList = new LinkedList<GeoEntityView>();

    Set<String> filteredTypes = new HashSet<String>();
    if (filter != null && filter.trim().length() > 0)
    {
      String types[] = filteredTypes(filter);
      filteredTypes.addAll(Arrays.asList(types));
    }

    for (GeoEntity geoEntity : collected)
    {
      boolean match = filteredTypes.size() == 0 ? true : false;
      for (String filterType : filteredTypes)
      {
        // allow is_a children
        if (LoaderDecorator.load(filterType).isAssignableFrom(geoEntity.getClass()))
        {
          match = true;
        }
      }

      if (match)
      {
        finalList.add(geoEntity.getViewFromGeoEntity());
      }
    }

    Collections.sort(finalList, new GeoEntityViewSorter());

    return finalList.toArray(new GeoEntityView[finalList.size()]);
  }

  /**
   * Converts a GeoEntity id into a view representation.
   * 
   * @return
   */

  public static GeoEntityView getView(String id)
  {
    return GeoEntity.get(id).getViewFromGeoEntity();
  }

  /**
   * Converts a GeoEntity id into a view representation.
   * 
   * @return
   */

  public static GeoEntityView getViewByGeoId(String id)
  {
    return GeoEntity.searchByGeoId(id).getViewFromGeoEntity();
  }

  /**
   * Converts this GeoEntity into a view representation.
   * 
   * @return
   */
  private GeoEntityView getViewFromGeoEntity()
  {
    GeoEntityView view = new GeoEntityView();

    view.setActivated(this.getActivated());
    view.setEntityName(this.getEntityName());
    view.setEntityType(this.getType());
    view.setGeoEntityId(this.getId());
    view.setGeoId(this.getGeoId());

    MdClass mdClass = MdClass.getMdClass(this.getType());
    view.setTypeDisplayLabel(mdClass.getDisplayLabel().getValue());

    return view;
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
   * Recursively collects all children with the {@link LocatedIn} relationship.
   * 
   * @return
   */
  public List<GeoEntity> getAllChildren()
  {
    List<GeoEntity> children = new LinkedList<GeoEntity>();
    getAllChildren(children, this);
    return children;
  }

  /**
   * Recursively collects all child ids with the {@link LocatedIn} relationship.
   * 
   * @param filter
   *          Return only children with this class
   * @return Array of child Ids sorted by name
   */
  public String[] getAllChildIds(String filter)
  {
    List<String> geoEntityIdList = new ArrayList<String>();
    List<GeoEntity> children = new LinkedList<GeoEntity>();
    getAllChildren(children, this);
    Collections.sort(children, new GeoEntitySorter());
    for (GeoEntity child : children)
    {
      // if the filter is null we will just add all children
      if (filter == null || child.getClass().getName().equals(filter))
      {
        geoEntityIdList.add(child.getId());
      }
    }

    return geoEntityIdList.toArray(new String[geoEntityIdList.size()]);
  }

  /**
   * Recursive function to collect {@link LocatedIn} children.
   * 
   * @param children
   * @param parent
   */
  private static void getAllChildren(List<GeoEntity> allChildren, GeoEntity parent)
  {
    List<GeoEntity> children = parent.getImmediateChildren();
    for (GeoEntity child : children)
    {
      allChildren.add(child);
      getAllChildren(allChildren, child);
    }
  }

  /**
   * Recursively collects all parents of the LocatedIn relationship.
   * 
   * @return
   */
  public List<GeoEntity> getAllParents()
  {
    List<GeoEntity> allParents = new LinkedList<GeoEntity>();

    getAllParents(allParents, this);

    return allParents;
  }

  /**
   * Recursive method that collects all parents for the given parent.
   * 
   * @param allChildren
   * @param parent
   * @return
   */
  private static void getAllParents(List<GeoEntity> allParents, GeoEntity child)
  {
    List<GeoEntity> parents = child.getImmediateParents();
    for (GeoEntity parent : parents)
    {
      allParents.add(parent);
      getAllParents(allParents, parent);
    }
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
   * Returns the first level of children which belong to the spray hierarchy.
   * 
   * @return
   */
  public GeoEntity[] getImmediateSprayChildren()
  {
    List<String> list = new LinkedList<String>();
    List<GeoEntity> children = new LinkedList<GeoEntity>();

    for (GeoHierarchyView view : GeoHierarchy.getSprayHierarchies(this))
    {
      list.add(view.getGeneratedType());
    }

    for (GeoEntity geoEntity : this.getImmediateChildren())
    {
      if (list.contains(geoEntity.getType()))
      {
        children.add(geoEntity);
      }
    }

    return children.toArray(new GeoEntity[children.size()]);
  }

  /**
   * Gets all children of a GeoEntity, but stops its breadth-first decent when
   * it finds a child which belongs to the given fully qualified types.
   */
  public List<GeoEntity> getPrunedChildren(GeoHierarchyView... types)
  {
    List<String> list = new LinkedList<String>();

    for (GeoHierarchyView view : types)
    {
      list.add(view.getGeneratedType());
    }

    return this.getPrunedChildren(list);
  }

  /**
   * Gets all children of a GeoEntity, but stops its breadth-first decent when
   * it finds a child which belongs to the given fully qualified types.
   */
  public List<GeoEntity> getPrunedChildren(String... types)
  {
    return this.getPrunedChildren(Arrays.asList(types));
  }

  /**
   * Gets all children of a GeoEntity, but stops its breadth-first decent when
   * it finds a child which belongs to the given fully qualified types.
   */
  public List<GeoEntity> getPrunedChildren(List<String> types)
  {
    List<Condition> conditions = new LinkedList<Condition>();

    QueryFactory factory = new QueryFactory();
    GeoEntityQuery geoEntityQuery = new GeoEntityQuery(factory);
    AllPathsQuery query = new AllPathsQuery(factory);

    for (String type : types)
    {
      conditions.add(query.getChildUniversal().EQ(MdClass.getMdClass(type)));
    }

    Condition[] array = conditions.toArray(new Condition[conditions.size()]);
    Condition and = AND.get(query.getParentGeoEntity().EQ(this), OR.get(array));

    query.WHERE(and);

    geoEntityQuery.WHERE(geoEntityQuery.getId().EQ(query.getChildGeoEntity().getId()));

    List<GeoEntity> list = new LinkedList<GeoEntity>(geoEntityQuery.getIterator().getAll());

    return list;
  }

  public List<GeoEntity> getPrunedParents(String... types)
  {
    return this.getPrunedParents(Arrays.asList(types));
  }

  public List<GeoEntity> getPrunedParents(List<String> types)
  {
    List<Condition> conditions = new LinkedList<Condition>();

    QueryFactory factory = new QueryFactory();
    GeoEntityQuery geoEntityQuery = new GeoEntityQuery(factory);
    AllPathsQuery query = new AllPathsQuery(factory);

    for (String type : types)
    {
      conditions.add(query.getParentUniversal().EQ(MdClass.getMdClass(type)));
    }

    Condition[] array = conditions.toArray(new Condition[conditions.size()]);
    Condition and = AND.get(query.getChildGeoEntity().EQ(this), OR.get(array));

    query.WHERE(and);

    geoEntityQuery.WHERE(geoEntityQuery.getId().EQ(query.getParentGeoEntity().getId()));

    List<GeoEntity> list = new LinkedList<GeoEntity>(geoEntityQuery.getIterator().getAll());

    return list;
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
   *          The active status of the parent.
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

    boolean isNew = this.isNew();
    if (isNew)
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
      // V1 Restriction
      if(!isNew)
      {
        throw new ActionNotAllowedException();
      }
      /* 
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
      */
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
      return new String[] {};
    }
  }

  @Override
  public LocatedIn addContainsGeoEntity(GeoEntity geoEntity)
  {
    validateHierarchy(geoEntity.getType(), this.getType());
    LocatedIn locatedIn = super.addContainsGeoEntity(geoEntity);

    return locatedIn;
  }

  @Override
  public LocatedIn addLocatedInGeoEntity(GeoEntity geoEntity)
  {
    validateHierarchy(this.getType(), geoEntity.getType());
    LocatedIn locatedIn = super.addLocatedInGeoEntity(geoEntity);

    return locatedIn;
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

  @Override
  public String[] getCompatibleTypes()
  {
    String type = this.getType();
    Set<String> types = GeoHierarchy.getIsAHierarchy(type);

    types.remove(type);

    if (types.isEmpty())
    {
      NoCompatibleTypesException ex = new NoCompatibleTypesException();
      // ex.setEntityName(this.getEntityName());
      // ex.setGeoId(this.getGeoId());
      throw ex;
    }

    return types.toArray(new String[types.size()]);
  }

  @Override
  @Transaction
  public GeoEntity changeUniversalType(String newType)
  {
    Class<?> newClass = LoaderDecorator.load(newType);
    try
    {
      GeoEntity copy = (GeoEntity) newClass.newInstance();

      // copy all attributes from the old object to the new
      MdBusinessDAOIF oldMd = MdBusinessDAO.getMdBusinessDAO(newType);
      for (MdAttributeConcreteDAOIF mdAttr : oldMd.getAllDefinedMdAttributes())
      {
        if (!mdAttr.isSystem())
        {
          String attrName = mdAttr.definesAttribute();

          if (mdAttr instanceof MdAttributeGeometryDAOIF)
          {
            AttributeGeometryIF geoAttr = (AttributeGeometryIF) BusinessFacade.getAttribute(copy,
                attrName);
            copy.setValue(attrName, geoAttr.getGeometry());
          }
          else
          {
            copy.setValue(attrName, this.getValue(attrName));
          }
        }
      }

      // copy located_in relationships
      List<GeoEntity> parents = this.getImmediateParents();
      List<GeoEntity> children = this.getImmediateChildren();

      this.delete();
      copy.apply();

      for (GeoEntity parent : parents)
      {
        copy.addLocatedInGeoEntity(parent).apply();
      }

      for (GeoEntity child : children)
      {
        copy.addContainsGeoEntity(child).apply();
      }

      return copy;
    }
    catch (Throwable e)
    {
      throw new ProgrammingErrorException(e);
    }
  }

  /**
   * Returns a list of all LocatedIn children for which this GeoEntity is a
   * parent. The list is ordered by the entity name.
   */
  @Override
  public GeoEntityViewQuery getOrderedChildren(String filter)
  {
    QueryFactory f = new QueryFactory();
    OrderedGeoEntityQueryBuilder builder = new OrderedGeoEntityQueryBuilder(f, this, filter);
    GeoEntityViewQuery query = new GeoEntityViewQuery(f, builder);

    return query;
  }

  private class OrderedGeoEntityQueryBuilder extends ViewQueryBuilder implements Reloadable
  {

    private GeoEntity       geoEntity;

    private GeoEntityQuery  geoEntityQuery;

    private LocatedInQuery  locatedInQuery;

    private MdBusinessQuery mdBusinessQuery;

    private String          filter;

    protected OrderedGeoEntityQueryBuilder(QueryFactory queryFactory, GeoEntity geoEntity, String filter)
    {
      super(queryFactory);

      this.filter = filter;
      this.geoEntity = geoEntity;
      this.geoEntityQuery = new GeoEntityQuery(queryFactory);
      this.locatedInQuery = new LocatedInQuery(queryFactory);
      this.mdBusinessQuery = new MdBusinessQuery(queryFactory);
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
      vQuery.map(GeoEntityView.TYPEDISPLAYLABEL, mdBusinessQuery.getDisplayLabel().currentLocale());
    }

    @Override
    protected void buildWhereClause()
    {
      GeneratedViewQuery vQuery = this.getViewQuery();

      vQuery.WHERE(this.locatedInQuery.parentId().EQ(geoEntity.getId()));
      vQuery.WHERE(this.geoEntityQuery.locatedInGeoEntity(this.locatedInQuery));

      vQuery.WHERE(F.CONCAT(mdBusinessQuery.getPackageName(),
          F.CONCAT(".", mdBusinessQuery.getTypeName())).EQ(geoEntityQuery.getType()));

      // filter by type if possible (and all of type's child subclasses)
      if (filter != null && filter.trim().length() > 0)
      {
        String types[] = filteredTypes(filter);
        vQuery.WHERE(geoEntityQuery.getType().IN(types));
      }

   // Restricted types to avoid returning large data sets
      String[] baseTypes = {
        //MDSSInfo.GENERATED_GEO_PACKAGE+".WaterBody",
        MDSSInfo.GENERATED_GEO_PACKAGE+".River",
        MDSSInfo.GENERATED_GEO_PACKAGE+".Road",
        MDSSInfo.GENERATED_GEO_PACKAGE+".Railway"
      };
      
      // Grab all is_a children of the restricted types to add to
      // the restricted list.
      Set<String> notInSet = new HashSet<String>(Arrays.asList(baseTypes));
      for(String baseType : baseTypes)
      {
        MdBusiness baseMd = MdBusiness.getMdBusiness(baseType);
        MdBusinessDAOIF baseDAOIF = (MdBusinessDAOIF) BusinessFacade.getEntityDAO(baseMd);
        
        for(MdBusinessDAOIF subclass : baseDAOIF.getAllConcreteSubClasses())
        {
          notInSet.add(subclass.definesType());
        }
      }
      
      vQuery.WHERE(geoEntityQuery.getType().NI(notInSet.toArray(new String[notInSet.size()])));
      
      vQuery.ORDER_BY_ASC(this.geoEntityQuery.getEntityName());
    }
  }

  public static GeoEntityViewQuery getAsViews(String[] entities)
  {
    QueryFactory f = new QueryFactory();
    GeoEntityViewQuery q = new GeoEntityViewQuery(f, entities);
    return q;
  }

  /**
   * Given a filter (a GeoEntity class), this method returns all parents and
   * children and the filter type itself that's allowed in the hierarchy.
   * 
   * @param type
   * @return
   */
  private static String[] filteredTypes(String filter)
  {
    // get allowed types by filter, which includes all parents and children
    // of the filter type and the filter type itself.
    List<GeoHierarchy> allowedTypes = new LinkedList<GeoHierarchy>();
    GeoHierarchy filterType = GeoHierarchy.getGeoHierarchyFromType(filter);

    allowedTypes.addAll(filterType.getAllChildren());
    allowedTypes.add(filterType);
    allowedTypes.addAll(filterType.getAllParents());

    String[] types = new String[allowedTypes.size()];
    for (int i = 0; i < allowedTypes.size(); i++)
    {
      GeoHierarchy allowedType = allowedTypes.get(i);
      types[i] = MDSSInfo.GENERATED_GEO_PACKAGE + "." + allowedType.getGeoEntityClass().getTypeName();
    }

    return types;
  }

  private class GeoEntityViewSorter implements Comparator<GeoEntityView>, Reloadable
  {
    public int compare(GeoEntityView geo1, GeoEntityView geo2)
    {
      return geo1.getEntityName().compareTo(geo2.getEntityName());
    }
  }

  public class GeoEntitySorter implements Comparator<GeoEntity>, Reloadable
  {
    public int compare(GeoEntity geo1, GeoEntity geo2)
    {
      return geo1.getEntityName().compareTo(geo2.getEntityName());
    }
  }

  @Transaction
  public static void updateAllPaths()
  {
    QueryFactory qf = new QueryFactory();

    GeoEntityQuery geoEntityQ = new GeoEntityQuery(qf);

    ValueQuery q = new ValueQuery(qf);
    q.SELECT(geoEntityQ.getId(ComponentInfo.ID));

    OIterator<ValueObject> i = q.getIterator();

    try
    {
      int applyCount = 0;

      for (ValueObject valueObject : i)
      {
        String childId = valueObject.getValue(ComponentInfo.ID);

        applyCount = updateAllPathForGeoEntity(childId, true, applyCount);
      }
    }
    finally
    {
      i.close();
    }
  }

  public static void updateAllPathForGeoEntity(String childId)
  {
    updateAllPathForGeoEntity(childId, false, 0);
  }

  @Transaction
  public static int updateAllPathForGeoEntity(String childId, boolean showTicker, int applyCount)
  {
    MdClassDAOIF childMdClassIF = MdClassDAO.getMdClassByRootId(IdParser
        .parseMdTypeRootIdFromId(childId));

    createPath(childId, childMdClassIF.getId(), childId, childMdClassIF.getId());

    if (showTicker)
    {
      applyCount = updateAllPathsTicker(applyCount);
    }

    List<String> parentIdList = getParentIds(childId);

    for (String parentId : parentIdList)
    {
      MdClassDAOIF parentMdClassIF = MdClassDAO.getMdClassByRootId(IdParser
          .parseMdTypeRootIdFromId(parentId));
      createPath(parentId, parentMdClassIF.getId(), childId, childMdClassIF.getId());
      if (showTicker)
      {
        applyCount = updateAllPathsTicker(applyCount);
      }
    }

    return applyCount;
  }

  private static List<String> getParentIds(String childId)
  {
    QueryFactory queryFactory = new QueryFactory();

    LocatedInQuery locatedInQuery = new LocatedInQuery(queryFactory);

    ValueQuery valueQuery = new ValueQuery(queryFactory);

    valueQuery.SELECT(locatedInQuery.parentId(RelationshipInfo.PARENT_ID));
    valueQuery.WHERE(locatedInQuery.childId().EQ(childId));

    List<ValueObject> valueObjectList = valueQuery.getIterator().getAll();

    List<String> parentIdList = new LinkedList<String>();

    for (ValueObject valueObject : valueObjectList)
    {
      String parentId = valueObject.getValue(RelationshipInfo.PARENT_ID);
      parentIdList.add(parentId);
      parentIdList.addAll(getParentIds(parentId));
    }

    return parentIdList;
  }

  private static void createPath(String parentId, String parentMdBusiness, String childId,
      String childMdBusiness)
  {
    // create save point
//    Savepoint savepoint = Database.setSavepoint();
    
//    String saveId = "a"+IDGenerator.nextID();
//    Database.parseAndExecute("SAVEPOINT "+saveId+"");
    
    try
    {

      // Check if the entry already exists. If so, don't create it.
      // WARNING: this is not thread safe. Make SAVEPOINTS work instead.
      QueryFactory f = new QueryFactory();
      AllPathsQuery q = new AllPathsQuery(f);
      q.WHERE(q.getChildGeoEntity().EQ(childId));
      q.WHERE(q.getParentGeoEntity().EQ(parentId));
      

      if(q.getCount() == 0)
      {
        AllPaths allPaths = new AllPaths();
        allPaths.setValue(AllPaths.PARENTGEOENTITY, parentId);
        allPaths.setValue(AllPaths.PARENTUNIVERSAL, parentMdBusiness);
        allPaths.setValue(AllPaths.CHILDGEOENTITY, childId);
        allPaths.setValue(AllPaths.CHILDUNIVERSAL, childMdBusiness);
        allPaths.apply();
      }
      
      //Database.parseAndExecute("RELEASE "+saveId+"");
//      Database.parseAndExecute("RELEASE "+saveId+"");
    }
    catch (DuplicateDataDatabaseException ex)
    {
      // This might happen. Relationship already exists.
//      Database.rollbackSavepoint(savepoint);
//      Database.parseAndExecute("ROLLBACK TO SAVEPOINT "+saveId+"");
    }
    finally
    {
//      Database.releaseSavepoint(savepoint);
    }
  }

  private static int updateAllPathsTicker(int applyCount)
  {
    System.out.print(".");
    applyCount++;

    if (applyCount % GeoEntityImporter.feedbackMod == 0)
    {
      System.out.println();
    }
    return applyCount;
  }
}