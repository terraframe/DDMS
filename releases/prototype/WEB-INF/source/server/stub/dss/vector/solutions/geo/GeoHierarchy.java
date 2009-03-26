package dss.vector.solutions.geo;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.terraframe.mojo.dataaccess.ProgrammingErrorException;
import com.terraframe.mojo.dataaccess.transaction.AbortIfProblem;
import com.terraframe.mojo.dataaccess.transaction.Transaction;
import com.terraframe.mojo.generation.loader.Reloadable;
import com.terraframe.mojo.query.GeneratedViewQuery;
import com.terraframe.mojo.query.OIterator;
import com.terraframe.mojo.query.QueryFactory;
import com.terraframe.mojo.query.ViewQueryBuilder;
import com.terraframe.mojo.system.metadata.MdBusiness;
import com.terraframe.mojo.system.metadata.MdBusinessQuery;

import dss.vector.solutions.MDSSInfo;
import dss.vector.solutions.geo.generated.Earth;
import dss.vector.solutions.geo.generated.GeoEntity;
import dss.vector.solutions.geo.generated.GeoEntityQuery;

public class GeoHierarchy extends GeoHierarchyBase implements
    com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1236133816255L;

  public GeoHierarchy()
  {
    super();
  }

  /**
   * Returns a JSON object representing the allowed GeoEntity types within the
   * GeoEntity of the given id.
   * 
   * @param geoEntityId
   * @return
   */
  public static String defineAllowedTree(String geoEntityId)
  {
    try
    {
      GeoEntity geoEntity = GeoEntity.get(geoEntityId);
      MdBusiness rootMd = MdBusiness.getMdBusiness(geoEntity.getType());

      GeoHierarchy geo = getGeoHierarchyFromType(rootMd);

      JSONObject map = new JSONObject();
      JSONObject types = new JSONObject();
      HashSet<String> imports = new HashSet<String>();

      treeRecurse(types, imports, geo);

      map.put("types", types);
      map.put("imports", new JSONArray(imports));

      return map.toString(4);
    }
    catch (JSONException e)
    {
      throw new ProgrammingErrorException(e);
    }
  }

  /**
   * Returns the GeoHiearachy representative of the given type (a GeoEntity
   * subtype).
   * 
   * @param mdType
   * @return
   */
  public static GeoHierarchy getGeoHierarchyFromType(String mdType)
  {
    MdBusiness md = MdBusiness.getMdBusiness(mdType);
    return getGeoHierarchyFromType(md);
  }
  
  /**
   * Recursively collects all parents of the AllowedIn relationship.
   * 
   * @return
   */
  public List<GeoHierarchy> getAllParents()
  {
    List<GeoHierarchy> allParents = new LinkedList<GeoHierarchy>();
    
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
  private static void getAllParents(List<GeoHierarchy> allParents, GeoHierarchy child)
  {
    List<GeoHierarchy> parents = child.getImmediateParents();
    for(GeoHierarchy parent : parents)
    {
      allParents.add(parent);
      getAllParents(allParents, parent);
    }
  }

  /**
   * Returns all parents of the first level of the AllowedIn relationship.
   * 
   * @return
   */
  public List<GeoHierarchy> getImmediateParents()
  {
    List<GeoHierarchy> parents = new LinkedList<GeoHierarchy>();
    OIterator<? extends GeoHierarchy> iter = this.getAllAllowedInGeoEntity();

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
   * Recursively collects all children of the AllowedIn relationship.
   * 
   * @return
   */
  public List<GeoHierarchy> getAllChildren()
  {
    List<GeoHierarchy> allChildren = new LinkedList<GeoHierarchy>();
    
    getAllChildren(allChildren, this);
    
    return allChildren;
  }
  
  /**
   * Recursive method that collects all children for the given parent.
   * 
   * @param allChildren
   * @param parent
   * @return
   */
  private static void getAllChildren(List<GeoHierarchy> allChildren, GeoHierarchy parent)
  {
    List<GeoHierarchy> children = parent.getImmediateChildren();
    for(GeoHierarchy child : children)
    {
      allChildren.add(child);
      getAllChildren(allChildren, child);
    }
  }
  
  /**
   * Returns all children of the first level of the AllowedIn relationship.
   * 
   * @return
   */
  public List<GeoHierarchy> getImmediateChildren()
  {
    List<GeoHierarchy> children = new LinkedList<GeoHierarchy>();
    OIterator<? extends GeoHierarchy> iter = this.getAllAcceptsGeoEntity();

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
   * Returns the GeoHierarchy representative of the given MdBusiness (a
   * GeoEntity subtype).
   * 
   * @param md
   * @return
   */
  public static GeoHierarchy getGeoHierarchyFromType(MdBusiness md)
  {
    QueryFactory f = new QueryFactory();
    GeoHierarchyQuery geoQuery = new GeoHierarchyQuery(f);
    geoQuery.WHERE(geoQuery.getGeoEntityClass().EQ(md));

    OIterator<? extends GeoHierarchy> iter = geoQuery.getIterator();
    try
    {
      return iter.next();
    }
    finally
    {
      iter.close();
    }
  }

  /**
   * Recursive function to build a tree structure denoting what GeoHierarchy
   * types are allowed in one another.
   * 
   * @param types
   * @param imports
   * @param geo
   * @throws JSONException
   */
  private static void treeRecurse(JSONObject types, HashSet<String> imports, GeoHierarchy geo)
      throws JSONException
  {
    List<GeoHierarchy> geos = geo.getImmediateChildren();

    JSONArray allowed = new JSONArray();
    for (int i = 0; i < geos.size(); i++)
    {
      GeoHierarchy g = geos.get(i);

      MdBusiness md = g.getGeoEntityClass();
      String type = md.getPackageName() + "." + md.getTypeName();

      JSONObject typeAndLabel = new JSONObject();
      typeAndLabel.put("type", type);
      typeAndLabel.put("label", md.getDisplayLabel());
      allowed.put(typeAndLabel);

      treeRecurse(types, imports, g);
    }

    MdBusiness md = geo.getGeoEntityClass();

    String type = md.getPackageName() + "." + md.getTypeName();
    types.put(type, allowed);

    imports.add(type);
    imports.add(type + "Controller");
  }

  /**
   * Static accessor to delete the given GeoHierarchy.
   * 
   * @param geoHierarchyId
   */
  public static void deleteGeoHierarchy(String geoHierarchyId)
  {
    GeoHierarchy geoHierarchy = GeoHierarchy.get(geoHierarchyId);
    geoHierarchy.delete();
  }

  /**
   * Deletes this GeoHierarchy and it's associated MdBusiness that defines a
   * GeoEntity subtype. All children are deleted recursively.
   */
  @Override
  @Transaction
  public void delete()
  {
    List<GeoHierarchy> children = this.getImmediateChildren();
    for (GeoHierarchy child : children)
    {
      // FIXME Don't delete children with more than one parent.
      child.delete();
    }

    MdBusiness geoEntityClass = this.getGeoEntityClass();

    super.delete();

    geoEntityClass.delete();
  }

  /**
   * Defines a new GeoEntity type based on the value of this view.
   */
  @Transaction
  public static String defineGeoEntity(GeoEntityDefinition definition)
  {
    // validate attributes
    definition.applyNoPersist();

    return defineGeoEntityInternal(definition);
  }

  /**
   * Defines a new GeoEntity type. This method will error out if there are any
   * problems.
   * 
   * @param definition
   * @param allowedInIds
   * @return
   */
  @AbortIfProblem
  private static String defineGeoEntityInternal(GeoEntityDefinition definition)
  {
    // define the new MdBusiness
    String typeName = definition.getTypeName();
    String label = definition.getDisplayLabel();
    String description = definition.getDescription();

    validateModifyGeoHierarchy(label);

    MdBusiness mdGeoEntity = new MdBusiness();
    mdGeoEntity.setPackageName(MDSSInfo.GENERATED_GEO_PACKAGE);
    mdGeoEntity.setTypeName(typeName);
    mdGeoEntity.setDisplayLabel(label);
    mdGeoEntity.setDescription(description);
    mdGeoEntity.setIsAbstract(false); // User defined types must be concrete
    mdGeoEntity.setExtendable(true);
    mdGeoEntity.setPublish(true);

    String parentTypeId = definition.getParentTypeId();
    MdBusiness parent;

    if (parentTypeId != null && parentTypeId.trim().length() > 0)
    {
      parent = MdBusiness.get(parentTypeId);
    }
    else
    {
      parent = MdBusiness.getMdBusiness(GeoEntity.CLASS);
    }
    mdGeoEntity.setSuperMdBusiness(parent);
    mdGeoEntity.apply();

    // add the spatial attribute to the MdBusiness
    // SpatialTypes spatialTypes = definition.getSpatialType().get(0);
    // FIXME add spatial attribute when nathan is done

    // create the GeoHeirachy and relationship
    GeoHierarchy geoHierarchy = new GeoHierarchy();
    geoHierarchy.setPolitical(definition.getPolitical());
    geoHierarchy.setGeoEntityClass(mdGeoEntity);
    geoHierarchy.apply();

    GeoHierarchy allowedIn = GeoHierarchy.get(definition.getParentGeoHierarchyId());
    geoHierarchy.addAllowedInGeoEntity(allowedIn).apply();

    return geoHierarchy.getId();
  }

  /**
   * Locks this object and the MdBusiness which represents a GeoEntity subtype.
   */
  @Override
  @Transaction
  public void lock()
  {
    super.lock();

    this.getGeoEntityClass().lock();
  }

  /**
   * Unlocks this object and the MdBusiness which represents a GeoEntity
   * subtype.
   */
  @Override
  @Transaction
  public void unlock()
  {
    super.unlock();

    this.getGeoEntityClass().unlock();
  }

  /**
   * Updates a GeoHierarchy and its enclosed MdBusiness
   * 
   * @param view
   */
  @Transaction
  public static void updateFromView(GeoHierarchyView view)
  {
    // GeoHierarchy should already be locked
    GeoHierarchy geoHierarchy = GeoHierarchy.get(view.getGeoHierarchyId());
    geoHierarchy.setPolitical(view.getPolitical());
    geoHierarchy.apply();

    MdBusiness geoEntityClass = geoHierarchy.getGeoEntityClass();
    geoEntityClass.setDisplayLabel(view.getDisplayLabel());
    geoEntityClass.setDescription(view.getDescription());
    geoEntityClass.apply();
  }

  /**
   * Adds the given childGeoHierarchyId as a child of the given
   * parentGeoHierarchyId in the {@link AllowedIn} relationship.
   * 
   * @param childId
   * @param parentId
   * @param cloneOperation
   */
  @Transaction
  public static void applyExistingWithParent(String childGeoHierarchyId, String parentGeoHierarchyId,
      Boolean cloneOperation)
  {
    GeoHierarchy childGeoHierarchy = GeoHierarchy.get(childGeoHierarchyId);
    GeoHierarchy parentGeoHierarchy = GeoHierarchy.get(parentGeoHierarchyId);

    validateModifyGeoHierarchy(childGeoHierarchy.getGeoEntityClass().getDisplayLabel());

    if (!cloneOperation)
    {
      // remove the old parent from the child
      OIterator<? extends AllowedIn> iter = childGeoHierarchy.getAllAllowedInGeoEntityRel();
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

    childGeoHierarchy.addAllowedInGeoEntity(parentGeoHierarchy).apply();
  }

  /**
   * Checks that the GeoHierarchy can be modified, which is only possible if no
   * GeoEntity instances have been created for any types except the single Earth
   * instance.
   * 
   * @throws ModifyHierarchyWithInstancesException
   */
  private static void validateModifyGeoHierarchy(String displayLabel)
  {
    Earth earth = Earth.getEarthInstance();

    QueryFactory f = new QueryFactory();
    GeoEntityQuery q = new GeoEntityQuery(f);

    q.WHERE(q.getId().NE(earth.getId()));

    if (q.getCount() > 0)
    {
      String error = "Cannot modify the Hierarchy when Geo Entity data exists.";
      ModifyHierarchyWithInstancesException ex = new ModifyHierarchyWithInstancesException(error);
      ex.setDisplayLabel(displayLabel);
      throw ex;
    }
  }

  /**
   * Returns all GeoHierarchy views that fit the following critiera. Note that a
   * GeoHierarchy that maps to an abstract GeoEntity subtype will not be
   * included, for those are used internally only.
   * 
   * @param sortAttribute
   * @param ascending
   * @param pageSize
   * @param pageNumber
   * @return
   */
  public static GeoHierarchyViewQuery getGeoEntityHierarchyViews(String sortAttribute,
      Boolean ascending, Integer pageSize, Integer pageNumber)
  {
    QueryFactory f = new QueryFactory();
    GeoHierarchyViewQuery viewQuery = new GeoHierarchyViewQuery(f, sortAttribute, ascending, pageSize,
        pageNumber);

    return viewQuery;
  }

  /**
   * Returns a {@link GeoHierarchyView} representative of this GeoHierarchy.
   */
  @Override
  public GeoHierarchyView getViewForGeoHierarchy()
  {
    MdBusiness md = this.getGeoEntityClass();

    GeoHierarchyView view = new GeoHierarchyView();
    view.setPolitical(this.getPolitical());
    view.setDescription(md.getDescription());
    view.setTypeName(md.getTypeName());
    view.setDisplayLabel(md.getDisplayLabel());
    view.setReferenceId(md.getId());
    view.setGeoHierarchyId(this.getId());
    return view;
  }

  /**
   * Returns all immediate children of the AllowedIn relationship for this
   * GeoHieararchy. The children are respresented as GeoHierarchyViews.
   */
  @Override
  public GeoHierarchyViewQuery getOrderedChildren()
  {
    QueryFactory f = new QueryFactory();
    GeoHierarchyViewQuery query = new GeoHierarchyViewQuery(f, new OrderedGeoHiearchyQueryBuilder(f,
        this));

    return query;
  }

  /**
   * Returns all political GeoHierarchy views starting with the GeoHierarchy
   * that represents the given GeoEntity.
   * 
   * @param geoEntityId
   * @return
   */
  public static GeoHierarchyView[] getPoliticalGeoHierarchies(String geoEntityId)
  {
    GeoEntity geoEntity = GeoEntity.get(geoEntityId);

    return getPoliticalGeoHierarchiesByType(geoEntity.getType());
  }

  /**
   * Returns all political GeoHierarchies under and including the given type.
   * 
   * @param type
   * @return
   */
  public static GeoHierarchyView[] getPoliticalGeoHierarchiesByType(String type)
  {
    GeoHierarchy earthH = GeoHierarchy.getGeoHierarchyFromType(type);

    List<GeoHierarchyView> hierarchy = new LinkedList<GeoHierarchyView>();
    treeRecurse(hierarchy, earthH);

    return hierarchy.toArray(new GeoHierarchyView[hierarchy.size()]);
  }

  private static void treeRecurse(List<GeoHierarchyView> hierarchy, GeoHierarchy parent)
  {
    if (parent.getPolitical())
    {
      hierarchy.add(parent.getViewForGeoHierarchy());
    }

    List<GeoHierarchy> children = parent.getImmediateChildren();
    for (GeoHierarchy childH : children)
    {
      treeRecurse(hierarchy, childH);
    }

  }

  /**
   * Gets GeoHierarchy views that are immediate children of the given
   * GeoHierarchy.
   */
  private class OrderedGeoHiearchyQueryBuilder extends ViewQueryBuilder implements Reloadable
  {
    private GeoHierarchy      geoHierarchy;

    private GeoHierarchyQuery geoHierarchyQuery;

    private AllowedInQuery    allowedInQuery;

    private MdBusinessQuery   mdBusinessQuery;

    protected OrderedGeoHiearchyQueryBuilder(QueryFactory queryFactory, GeoHierarchy geoHierarchy)
    {
      super(queryFactory);

      geoHierarchyQuery = new GeoHierarchyQuery(queryFactory);
      allowedInQuery = new AllowedInQuery(queryFactory);
      mdBusinessQuery = new MdBusinessQuery(queryFactory);

      this.geoHierarchy = geoHierarchy;
    }

    @Override
    protected void buildSelectClause()
    {
      GeneratedViewQuery vQuery = this.getViewQuery();

      vQuery.map(GeoHierarchyView.GEOHIERARCHYID, geoHierarchyQuery.getId());
      vQuery.map(GeoHierarchyView.POLITICAL, geoHierarchyQuery.getPolitical());

      vQuery.map(GeoHierarchyView.REFERENCEID, mdBusinessQuery.getId());
      vQuery.map(GeoHierarchyView.TYPENAME, mdBusinessQuery.getTypeName());
      vQuery.map(GeoHierarchyView.DISPLAYLABEL, mdBusinessQuery.getDisplayLabel());
      vQuery.map(GeoHierarchyView.DESCRIPTION, mdBusinessQuery.getDescription());
    }

    @Override
    protected void buildWhereClause()
    {
      GeneratedViewQuery vQuery = this.getViewQuery();

      vQuery.WHERE(allowedInQuery.parentId().EQ(geoHierarchy.getId()));
      vQuery.WHERE(geoHierarchyQuery.allowedInGeoEntity(allowedInQuery));

      vQuery.WHERE(mdBusinessQuery.getIsAbstract().EQ(false));
      vQuery.WHERE(geoHierarchyQuery.getGeoEntityClass().EQ(mdBusinessQuery));

      vQuery.ORDER_BY_ASC(mdBusinessQuery.getDisplayLabel());
    }
  }

}