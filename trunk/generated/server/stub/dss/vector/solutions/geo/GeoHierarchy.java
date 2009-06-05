package dss.vector.solutions.geo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.terraframe.mojo.business.BusinessFacade;
import com.terraframe.mojo.business.BusinessQuery;
import com.terraframe.mojo.business.generation.EntityQueryAPIGenerator;
import com.terraframe.mojo.constants.ComponentInfo;
import com.terraframe.mojo.dataaccess.MdAttributeDAOIF;
import com.terraframe.mojo.dataaccess.ProgrammingErrorException;
import com.terraframe.mojo.dataaccess.database.Database;
import com.terraframe.mojo.dataaccess.metadata.MdBusinessDAO;
import com.terraframe.mojo.dataaccess.transaction.AbortIfProblem;
import com.terraframe.mojo.dataaccess.transaction.Transaction;
import com.terraframe.mojo.generation.loader.LoaderDecorator;
import com.terraframe.mojo.generation.loader.Reloadable;
import com.terraframe.mojo.gis.dataaccess.MdAttributeGeometryDAOIF;
import com.terraframe.mojo.query.Attribute;
import com.terraframe.mojo.query.GeneratedEntityQuery;
import com.terraframe.mojo.query.GeneratedViewQuery;
import com.terraframe.mojo.query.OIterator;
import com.terraframe.mojo.query.QueryFacade;
import com.terraframe.mojo.query.QueryFactory;
import com.terraframe.mojo.query.ValueQuery;
import com.terraframe.mojo.query.ViewQueryBuilder;
import com.terraframe.mojo.system.gis.metadata.MdAttributeGeometry;
import com.terraframe.mojo.system.gis.metadata.MdAttributeLineString;
import com.terraframe.mojo.system.gis.metadata.MdAttributeMultiLineString;
import com.terraframe.mojo.system.gis.metadata.MdAttributeMultiPoint;
import com.terraframe.mojo.system.gis.metadata.MdAttributeMultiPolygon;
import com.terraframe.mojo.system.gis.metadata.MdAttributePoint;
import com.terraframe.mojo.system.gis.metadata.MdAttributePolygon;
import com.terraframe.mojo.system.metadata.MdBusiness;
import com.terraframe.mojo.system.metadata.MdBusinessQuery;

import dss.vector.solutions.MDSSInfo;
import dss.vector.solutions.geo.generated.Earth;
import dss.vector.solutions.geo.generated.GeoEntity;
import dss.vector.solutions.geo.generated.GeoEntityQuery;
import dss.vector.solutions.query.MapUtil;
import dss.vector.solutions.query.QueryConstants;

public class GeoHierarchy extends GeoHierarchyBase implements
    com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long    serialVersionUID = 1236133816255L;

  private static final Integer SRID             = 4326;

  public GeoHierarchy()
  {
    super();
  }

  /**
   * Returns the {@link GeoHierarchyView} that represents Earth.
   *
   * @return
   */
  public static GeoHierarchyView getEarthGeoHierarchy()
  {
    return getGeoHierarchyFromType(Earth.CLASS).getViewForGeoHierarchy();
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

      treeRecurse(types, imports, geo, null);

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
    for (GeoHierarchy parent : parents)
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
    for (GeoHierarchy child : children)
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
      while (iter.hasNext())
      {
        return iter.next();
      }

      return null;
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
   * @param parent
   * @throws JSONException
   */
  private static void treeRecurse(JSONObject types, HashSet<String> imports, GeoHierarchy parent,
      GeoHierarchy grandParent) throws JSONException
  {
    List<GeoHierarchy> children = parent.getImmediateChildren();

    JSONArray allowed = new JSONArray();
    for (int i = 0; i < children.size(); i++)
    {
      GeoHierarchy child = children.get(i);

      String type = child.getQualifiedType();

      allowed.put(type);

      treeRecurse(types, imports, child, parent);
    }

    MdBusiness md = parent.getGeoEntityClass();

    String type = parent.getQualifiedType(md);

    Object grandParentType;
    if (grandParent != null)
    {
      grandParentType = grandParent.getQualifiedType();
    }
    else
    {
      grandParentType = JSONObject.NULL;
    }

    JSONObject labelAndChildren = new JSONObject();
    labelAndChildren.put("label", md.getDisplayLabel());
    labelAndChildren.put("parent", grandParentType);
    labelAndChildren.put("children", allowed);
    types.put(type, labelAndChildren);

    imports.add(type);
    imports.add(type + "Controller");
  }

  /**
   * Returns the concatenated package and type name of the underlying MdBusiness
   * this GeoHierarchy represents.
   *
   * @return
   */
  public String getQualifiedType()
  {
    MdBusiness md = this.getGeoEntityClass();
    return getQualifiedType(md);
  }

  /**
   * Returns the concatenated package and type name of the underlying MdBusiness
   * this GeoHierarchy represents.
   *
   * @return
   */
  public String getQualifiedType(MdBusiness md)
  {
    return md.getPackageName() + "." + md.getTypeName();
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

  @Transaction
  protected String buildKey()
  {
    return this.getMdClass().getTypeName() + "_" + this.getGeoEntityClass().getTypeName();
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
   * Checks the given MdBusiness and its parents for an MdAttributeGeometry and
   * returns it. If no MdAttributeGeometry is defined this method returns null.
   *
   * @param mdBusiness
   * @return
   */
  public static MdAttributeGeometry getGeometry(String type)
  {
    MdBusiness md = MdBusiness.getMdBusiness(type);
    return getGeometry(md);
  }

  public MdAttributeGeometry getGeometry()
  {
    return getGeometry(this.getGeoEntityClass());
  }

  /**
   * Checks the given MdBusiness and its parents for an MdAttributeGeometry and
   * returns it. If no MdAttributeGeometry is defined this method returns null.
   *
   * @param mdBusiness
   * @return
   */
  public static MdAttributeGeometry getGeometry(MdBusiness mdBusiness)
  {
    MdBusinessDAO mdBusinessDAO = (MdBusinessDAO) BusinessFacade.getEntityDAO(mdBusiness);
    List<? extends MdAttributeDAOIF> attributeDAOs = mdBusinessDAO.getAllDefinedMdAttributes();

    for (MdAttributeDAOIF attributeDAO : attributeDAOs)
    {
      if (attributeDAO instanceof MdAttributeGeometryDAOIF)
      {
        return MdAttributeGeometry.get(attributeDAO.getId());
      }
    }

    return null;
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
    boolean definesGeometry = false;
    List<SpatialTypes> spatialTypes = definition.getSpatialType();
    if (spatialTypes != null && spatialTypes.size() > 0)
    {
      definesGeometry = true;
    }

    // define the new MdBusiness
    String typeName = definition.getTypeName();
    String label = definition.getDisplayLabel();
    String description = definition.getDescription();

    MdBusiness mdGeoEntity = new MdBusiness();
    mdGeoEntity.setPackageName(MDSSInfo.GENERATED_GEO_PACKAGE);
    mdGeoEntity.setTypeName(typeName);
    mdGeoEntity.getDisplayLabel().setValue(label);
    // if (!label.trim().equals(""))
    // {
    mdGeoEntity.getDisplayLabel().setDefaultValue(label);
    // }

    mdGeoEntity.getDescription().setValue(description);
    // if (!description.trim().equals(""))
    // {
    mdGeoEntity.getDescription().setDefaultValue(description);
    // }

    mdGeoEntity.setIsAbstract(false); // User defined types must be concrete
    mdGeoEntity.setExtendable(true);
    mdGeoEntity.setPublish(true);

    GeoHierarchy geoEntityH = GeoHierarchy.getGeoHierarchyFromType(GeoEntity.CLASS);

    String parentTypeGeoHierarchyId = definition.getParentTypeGeoHierarchyId();
    MdBusiness parent;
    if (parentTypeGeoHierarchyId != null && parentTypeGeoHierarchyId.trim().length() > 0
        && !parentTypeGeoHierarchyId.equals(geoEntityH.getId()))
    {
      GeoHierarchy parentTypeH = GeoHierarchy.get(parentTypeGeoHierarchyId);
      parent = parentTypeH.getGeoEntityClass();

      // make sure the user isn't trying to override the parent geometry.
      if (definesGeometry)
      {
        // A parent in this case, which is not GeoEntity, MUST define an
        // MdAttributeGeometry
        // either itself or via one of its parents. If not, we have some
        // problems ...
        MdAttributeGeometry geoAttrMd = getGeometry(parent);

        String geoLabel = geoAttrMd.getDisplayLabel().getValue();

        String error = "Cannot define a geometry type because the parent ["
            + parent.getDisplayLabel().getValue() + "] already " + "defines the geometry [" + geoLabel
            + "].";
        SpatialTypeDefinedException ex = new SpatialTypeDefinedException(error);
        ex.setIsAParentLabel(parent.getDisplayLabel().getValue());
        ex.setSpatialLabel(geoLabel);
        throw ex;
      }
    }
    else
    {
      if (!definesGeometry)
      {
        String error = "The universal [" + definition.getDisplayLabel()
            + "] must define a geometry attribute";
        SpatialTypeRequiredException ex = new SpatialTypeRequiredException(error);
        throw ex;
      }

      parent = geoEntityH.getGeoEntityClass();
    }

    mdGeoEntity.setSuperMdBusiness(parent);
    mdGeoEntity.apply();

    // add the spatial attribute to the MdBusiness
    if (definesGeometry)
    {
      addGeometryAttribute(mdGeoEntity, spatialTypes.get(0));
    }

    // create the GeoHeirachy and relationship
    GeoHierarchy geoHierarchy = new GeoHierarchy();
    geoHierarchy.setPolitical(definition.getPolitical());
    geoHierarchy.setSprayTargetAllowed(definition.getPolitical());
    geoHierarchy.setGeoEntityClass(mdGeoEntity);
    geoHierarchy.apply();

    GeoHierarchy allowedIn = GeoHierarchy.get(definition.getParentGeoHierarchyId());
    geoHierarchy.addAllowedInGeoEntity(allowedIn).apply();

    return geoHierarchy.getId();
  }

  @Override
  public void confirmChangeParent(String parentId)
  {
    GeoHierarchy view = GeoHierarchy.get(parentId);
    String label = view.getGeoEntityClass().getDisplayLabel().getValue();

    String error = "Delete the old relationship with [" + label + "] or the Hierarchy?";
    ConfirmHierarchyParentChangeException ex = new ConfirmHierarchyParentChangeException(error);
    ex.setParentDisplayLabel(label);
    throw ex;
  }

  @Override
  public void confirmDeleteHierarchy(String parentId)
  {
    List<GeoHierarchy> parents = this.getImmediateParents();
    if (parents.size() > 1)
    {
      GeoHierarchy parent = GeoHierarchy.get(parentId);
      String label = parent.getGeoEntityClass().getDisplayLabel().getValue();

      String error = "Delete the old relationship with [" + label + "] or the Hierarchy?";
      ConfirmDeleteHierarchyException ex = new ConfirmDeleteHierarchyException(error);
      ex.setParentDisplayLabel(label);
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
    AllowedInQuery query = new AllowedInQuery(f);
    query.WHERE(query.childId().EQ(this.getId()));
    query.WHERE(query.parentId().EQ(parentId));

    OIterator<? extends AllowedIn> iter = query.getIterator();
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
   * Adds a Geometric attribute to the given MdBusiness.
   *
   * @param mdGeoEntity
   * @param spatialType
   */
  private static void addGeometryAttribute(MdBusiness mdGeoEntity, SpatialTypes spatialType)
  {

    MdAttributeGeometry attr;
    if (spatialType == SpatialTypes.POINT)
    {
      attr = new MdAttributePoint();
      attr.setAttributeName("point");
    }
    else if (spatialType == SpatialTypes.LINE)
    {
      attr = new MdAttributeLineString();
      attr.setAttributeName("lineString");
    }
    else if (spatialType == SpatialTypes.POLYGON)
    {
      attr = new MdAttributePolygon();
      attr.setAttributeName("polygon");
    }
    else if (spatialType == SpatialTypes.MULTI_POINT)
    {
      attr = new MdAttributeMultiPoint();
      attr.setAttributeName("multiPoint");
    }
    else if (spatialType == SpatialTypes.MULTI_LINE)
    {
      attr = new MdAttributeMultiLineString();
      attr.setAttributeName("multiLineString");
    }
    else if (spatialType == SpatialTypes.MULTI_POLYGON)
    {
      attr = new MdAttributeMultiPolygon();
      attr.setAttributeName("multiPolygon");
    }
    else
    {
      String error = "The geometry type [" + spatialType.getDisplayLabel() + "] is not supported.";
      throw new ProgrammingErrorException(error);
    }

    String attrDisplayLabel = spatialType.getDisplayLabel();

    // if (!attrDisplayLabel.trim().equals(""))
    // {
    attr.getDisplayLabel().setDefaultValue(attrDisplayLabel);
    // }

    attr.setDefiningMdClass(mdGeoEntity);
    attr.setSrid(SRID);
    attr.apply();
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
    geoHierarchy.setSprayTargetAllowed(view.getSprayTargetAllowed());
    geoHierarchy.apply();

    MdBusiness geoEntityClass = geoHierarchy.getGeoEntityClass();
    geoEntityClass.getDisplayLabel().setValue(view.getDisplayLabel());
    // if (!view.getDisplayLabel().trim().equals(""))
    // {
    geoEntityClass.getDisplayLabel().setDefaultValue(view.getDisplayLabel());
    // }

    geoEntityClass.getDescription().setValue(view.getDescription());

    // if (!view.getDescription().trim().equals(""))
    // {
    geoEntityClass.getDescription().setDefaultValue(view.getDescription());
    // }

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

    // make sure a child cannot be applied to itself
    if (childGeoHierarchy.getId().equals(parentGeoHierarchy.getId()))
    {
      String childLabel = childGeoHierarchy.getGeoEntityClass().getDisplayLabel().getValue();

      String error = "The child [" + childLabel + "] cannot be its own parent.";

      AllowedInSelfException e = new AllowedInSelfException(error);
      e.setDisplayLabel(childLabel);
      throw e;
    }

    validateModifyGeoHierarchy(childGeoHierarchy);

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
    else
    {
      // confirm this entity can't be applied to the same
      // parent more than once.
      QueryFactory f = new QueryFactory();
      AllowedInQuery q = new AllowedInQuery(f);
      q.WHERE(q.childId().EQ(childGeoHierarchyId));
      q.WHERE(q.parentId().EQ(parentGeoHierarchyId));

      if (q.getCount() > 0)
      {
        String childDL = childGeoHierarchy.getGeoEntityClass().getDisplayLabel().getValue();
        String parentDL = parentGeoHierarchy.getGeoEntityClass().getDisplayLabel().getValue();

        String error = "The child [" + childDL + "] is already located in the parent [" + parentDL
            + "].";
        DuplicateHierarchyParentException e = new DuplicateHierarchyParentException(error);
        e.setChildDisplayLabel(childDL);
        e.setChildDisplayLabel(parentDL);

        throw e;
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
  private static void validateModifyGeoHierarchy(GeoHierarchy toValidate)
  {
    QueryFactory f = new QueryFactory();
    GeoEntityQuery q = new GeoEntityQuery(f);

    // the GeoHierarchy to modify cannot have any children (recursively)
    List<GeoHierarchy> included = toValidate.getAllChildren();
    included.add(toValidate);

    String[] typeNames = new String[included.size()];

    int count = 0;
    for (GeoHierarchy geo : included)
    {
      MdBusiness md = geo.getGeoEntityClass();
      typeNames[count] = md.getPackageName() + "." + md.getTypeName();
      count++;
    }

    q.WHERE(q.getType().IN(typeNames));

    if (q.getCount() > 0)
    {
      String error = "Cannot modify the Hierarchy when Geo Entity data exists.";
      ModifyHierarchyWithInstancesException ex = new ModifyHierarchyWithInstancesException(error);
      ex.setDisplayLabel(toValidate.getGeoEntityClass().getDisplayLabel().getValue());
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
    view.setSprayTargetAllowed(this.getSprayTargetAllowed());
    view.setDescription(md.getDescription().getValue());
    view.setTypeName(md.getTypeName());
    view.setDisplayLabel(md.getDisplayLabel().getValue());
    view.setReferenceId(md.getId());
    view.setGeoHierarchyId(this.getId());

    MdBusiness superMd = this.getGeoEntityClass().getSuperMdBusiness();
    String isADisplayLabel = ( superMd != null ) ? superMd.getDisplayLabel().getValue() : "";
    view.setIsADisplayLabel(isADisplayLabel);
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
   * Collects and returns al GeoHierarchy objects (parents and children) that
   * are of the type of GeoEntity of the given id. The collection is restricted
   * by the political and sprayZoneAllowed flag on each GeoHierarchy.
   *
   *
   * @param rootGeoEntityId
   * @param political
   * @param sprayZoneAllowed
   * @param extraUniversals Any extra univerals to append to the final list.
   * @return
   */
  public static GeoHierarchyView[] collectHierarchies(String rootGeoEntityId, Boolean political,
      Boolean sprayZoneAllowed, String[] extraUniversals)
  {
    GeoEntity geoEntity = GeoEntity.get(rootGeoEntityId);
    String type = geoEntity.getType();

    GeoHierarchy parent = getGeoHierarchyFromType(type);

    LinkedHashSet<GeoHierarchyView> hierarchies = new LinkedHashSet<GeoHierarchyView>();
    collect(hierarchies, parent, political, sprayZoneAllowed);

    // add the extra universals
    for(String universal : extraUniversals)
    {
      GeoHierarchy extra = getGeoHierarchyFromType(universal);
      MdBusiness md = extra.getGeoEntityClass();

      // if abstract, add all of its non-abstract children
      if(md.getIsAbstract())
      {
        OIterator<? extends MdBusiness> children =  md.getAllSubClass();
        for(MdBusiness child : children)
        {
          if(!child.getIsAbstract())
          {
            GeoHierarchy childH = GeoHierarchy.getGeoHierarchyFromType(child);
            hierarchies.add(childH.getViewForGeoHierarchy());
          }
        }
      }
      else
      {
        hierarchies.add(extra.getViewForGeoHierarchy());
      }
    }

    return hierarchies.toArray(new GeoHierarchyView[hierarchies.size()]);
  }

  public static void collect(LinkedHashSet<GeoHierarchyView> hierarchies, GeoHierarchy parent, Boolean political, Boolean sprayZoneAllowed)
  {
    Boolean isPolitical = parent.getPolitical();
    Boolean isSprayZoneAllowed = parent.getSprayTargetAllowed();

    if(!political.booleanValue() && !sprayZoneAllowed.booleanValue())
    {
      // special way to mean *any* universal as political=false and sprayZoneAllowed=false would
      // normally not make sense.
      hierarchies.add(parent.getViewForGeoHierarchy());
    }
    else if (political.equals(isPolitical) || sprayZoneAllowed.equals(isSprayZoneAllowed))
    {
      hierarchies.add(parent.getViewForGeoHierarchy());
    }

    List<GeoHierarchy> children = parent.getImmediateChildren();
    for (GeoHierarchy childH : children)
    {
      collect(hierarchies, childH, political, sprayZoneAllowed);
    }
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
   * This GeoHierarchy is equal to the given object if the object is the same
   * object reference or if the ids match.
   */
  @Override
  public boolean equals(Object obj)
  {
    boolean equals = super.equals(obj);

    if (!equals && obj instanceof GeoHierarchy)
    {
      equals = this.getId().equals( ( (GeoHierarchy) obj ).getId());
    }

    return equals;
  }

  /**
   * Creates a database view that represents this GeoHierarchy (i.e., the
   * underlying GeoEntity MdBusiness and its spatial attribute). If the view
   * already exists this method does nothing but return the view name.
   *
   * @return The name of the database view
   */
  public boolean createViewTable(String sessionId)
  {
    Boolean viewCreated = this.getViewCreated();
    MdBusiness md = this.getGeoEntityClass();
    String viewName = md.getTypeName().toLowerCase() + QueryConstants.VIEW_NAME_SUFFIX;
    if (!viewCreated.booleanValue())
    {
      MdAttributeGeometry mdAttrGeo = getGeometry(md);
      MdBusiness definingMd = (MdBusiness) mdAttrGeo.getDefiningMdClass();

      String attrName = mdAttrGeo.getAttributeName();

      // create the ValueQuery whose SELECT will become a database view
      QueryFactory f = new QueryFactory();
      ValueQuery vQuery = new ValueQuery(f);

      GeoEntityQuery geoQuery = new GeoEntityQuery(f);
      Attribute entityNameAttr = (Attribute) geoQuery.getEntityName();
      entityNameAttr.setColumnAlias(QueryConstants.ENTITY_NAME_COLUMN);

      // if the MdBusiness that defines the geometry is the MdBusiness this
      // GeoHierarchy wraps, then just join with GeoEntity to get the entityName
      if (md.getId().equals(definingMd.getId()))
      {
        BusinessQuery q = f.businessQuery(md.definesType());
        vQuery.SELECT(q.aAttribute(attrName), entityNameAttr);
        vQuery.WHERE(q.aCharacter(ComponentInfo.ID).EQ(geoQuery.getId()));
      }
      else
      {
        // perform a join between *this* GeoEntity table and the one
        // that defines the geometry attribute.
        BusinessQuery q1 = f.businessQuery(md.definesType());
        BusinessQuery q2 = f.businessQuery(definingMd.definesType());
        vQuery.SELECT(q2.aAttribute(attrName), entityNameAttr);
        vQuery.WHERE(q1.aCharacter(ComponentInfo.ID).EQ(q2.aCharacter(ComponentInfo.ID)));
        vQuery.WHERE(q2.aCharacter(ComponentInfo.ID).EQ(geoQuery.getId()));
      }

      String sql = vQuery.getSQL();

      Database.createView(viewName, sql);

      MapUtil.reload(sessionId, viewName, mdAttrGeo);

      this.appLock();
      this.setViewCreated(true);
      this.apply();
    }

    // To avoid a bug in GeoServer, only include the layer if count > 0
    String countSQL = "SELECT COUNT(*) " + Database.formatColumnAlias("ct") + " FROM " + viewName;
    ResultSet resultSet = Database.query(countSQL);

    Long count = 0L;

    try
    {
      resultSet.next();
      String result = resultSet.getString("ct");
      count = new Long(result.toString());
    }
    catch (SQLException sqlEx1)
    {
      Database.throwDatabaseException(sqlEx1);
    }
    finally
    {
      try
      {
        java.sql.Statement statement = resultSet.getStatement();
        resultSet.close();
        statement.close();
      }
      catch (SQLException sqlEx2)
      {
        Database.throwDatabaseException(sqlEx2);
      }
    }

    return count > 0;
  }

  @SuppressWarnings("unchecked")
  public static void addGeoHierarchyJoinConditions(ValueQuery valueQuery,
      Map<String, GeneratedEntityQuery> queryParserMap)
  {
    QueryFactory queryFactory = valueQuery.getQueryFactory();

    Map<String, GeneratedEntityQuery> geoEntityQueryMap = new HashMap<String, GeneratedEntityQuery>();
    HashMap<String, GeoHierarchy> geoHierarchyMap = new HashMap<String, GeoHierarchy>();

    // Identify the entities in the query that are GeoEntities
    for (GeneratedEntityQuery entityQuery : queryParserMap.values())
    {
      if (GeoEntityQuery.class.isAssignableFrom(entityQuery.getClass()))
      {
        String type = entityQuery.getClassType();

        geoEntityQueryMap.put(type, entityQuery);

        geoHierarchyMap.put(type, getGeoHierarchyFromType(type));
      }
    }

    // Move along folks, there's nothing to see here (or join).
    if(geoHierarchyMap.size() == 0)
    {
      return;
    }

    // Get all children of Earth
    GeoHierarchy earthGeoHierarchy = getGeoHierarchyFromType(Earth.CLASS);
    List<GeoHierarchy> allEarthChildren = new LinkedList<GeoHierarchy>();
    getAllChildren(allEarthChildren, earthGeoHierarchy);

    // Find the first and last GeoHierarchy
    Collection<GeoHierarchy> requestedList = geoHierarchyMap.values();
    List<Integer> indexes = new LinkedList<Integer>();
    for(GeoHierarchy requested : requestedList)
    {
      // find the entry relative to the master list of GeoHierarchies
      Integer index = allEarthChildren.indexOf(requested);
      indexes.add(index);
    }

    Collections.sort(indexes);

    GeoHierarchy first = allEarthChildren.get(indexes.get(0));
    GeoHierarchy last = allEarthChildren.get(indexes.get(indexes.size()-1));

    // Fill in the gaps from the first to last GeoHierarchy
    List<GeoHierarchy> pathFromParentToChild = new LinkedList<GeoHierarchy>();
    List<GeoHierarchy> absoluteParents = last.getAllParents();

    for(GeoHierarchy parent : absoluteParents)
    {
      if(pathFromParentToChild.contains(first))
      {
        break;
      }

      pathFromParentToChild.add(parent);
    }

    pathFromParentToChild.add(0, last);
    Collections.reverse(pathFromParentToChild);

    // create entity queries for types in the path from parent to child that are
    // not yet in the parser map
    for (GeoHierarchy geoHierarchy : pathFromParentToChild)
    {
      String type = geoHierarchy.getGeoEntityClass().definesType();

      // No criteria was specified for this entity universal in the path from
      // parent to child. We need
      // to add it to the map.
      if (!queryParserMap.containsKey(type))
      {
        String queryType = EntityQueryAPIGenerator.getQueryClass(type);

        Class<?> queryClass = LoaderDecorator.load(queryType);

        GeneratedEntityQuery generatedEntityQuery = null;

        try
        {
          generatedEntityQuery = (GeneratedEntityQuery) queryClass.getConstructor(QueryFactory.class)
              .newInstance(queryFactory);
          queryParserMap.put(type, generatedEntityQuery);
        }
        catch (Throwable e)
        {
          throw new ProgrammingErrorException(e);
        }

        QueryFacade.setUsedInSelectClause(valueQuery, generatedEntityQuery);
      }
    }

    // Now that we have <code>GeneratedEntityQuery</code> objects defined for
    // every type in the path from parent to
    // child, we need to build the join criteria.
    for (int i = pathFromParentToChild.size() - 1; i >= 0; i--)
    {
      // Stop joining when we have reached the parent.
      if (i - 1 >= 0)
      {
        GeoHierarchy childGeoHierarchy = pathFromParentToChild.get(i);
        GeoHierarchy parentGeoHierarchy = pathFromParentToChild.get(i - 1);

        String childType = childGeoHierarchy.getGeoEntityClass().definesType();
        String parentType = parentGeoHierarchy.getGeoEntityClass().definesType();

        GeoEntityQuery childQuery = (GeoEntityQuery) queryParserMap.get(childType);
        GeoEntityQuery parentQuery = (GeoEntityQuery) queryParserMap.get(parentType);

        valueQuery.AND(childQuery.locatedInGeoEntity(parentQuery));
      }
      else
      {
        break;
      }
    }

  }

//  public String toString()
//  {
//    return this.getGeoEntityClass().getDisplayLabel().getValue();
//  }

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

    private MdBusinessQuery   parentMdBusinessQuery;

    protected OrderedGeoHiearchyQueryBuilder(QueryFactory queryFactory, GeoHierarchy geoHierarchy)
    {
      super(queryFactory);

      geoHierarchyQuery = new GeoHierarchyQuery(queryFactory);
      allowedInQuery = new AllowedInQuery(queryFactory);
      mdBusinessQuery = new MdBusinessQuery(queryFactory);
      parentMdBusinessQuery = new MdBusinessQuery(queryFactory);

      this.geoHierarchy = geoHierarchy;
    }

    @Override
    protected void buildSelectClause()
    {
      GeneratedViewQuery vQuery = this.getViewQuery();

      vQuery.map(GeoHierarchyView.GEOHIERARCHYID, geoHierarchyQuery.getId());
      vQuery.map(GeoHierarchyView.POLITICAL, geoHierarchyQuery.getPolitical());
      vQuery.map(GeoHierarchy.SPRAYTARGETALLOWED, geoHierarchyQuery.getSprayTargetAllowed());

      vQuery.map(GeoHierarchyView.REFERENCEID, mdBusinessQuery.getId());
      vQuery.map(GeoHierarchyView.TYPENAME, mdBusinessQuery.getTypeName());
      vQuery.map(GeoHierarchyView.DISPLAYLABEL, mdBusinessQuery.getDisplayLabel().currentLocale());
      vQuery.map(GeoHierarchyView.DESCRIPTION, mdBusinessQuery.getDescription().currentLocale());
      vQuery.map(GeoHierarchyView.ISADISPLAYLABEL, parentMdBusinessQuery.getDisplayLabel()
          .currentLocale());
    }

    @Override
    protected void buildWhereClause()
    {
      GeneratedViewQuery vQuery = this.getViewQuery();

      vQuery.WHERE(allowedInQuery.parentId().EQ(geoHierarchy.getId()));
      vQuery.WHERE(geoHierarchyQuery.allowedInGeoEntity(allowedInQuery));

      vQuery.WHERE(mdBusinessQuery.getIsAbstract().EQ(false));
      vQuery.WHERE(geoHierarchyQuery.getGeoEntityClass().EQ(mdBusinessQuery));

      vQuery.WHERE(mdBusinessQuery.getSuperMdBusiness().EQ(parentMdBusinessQuery));

      vQuery.ORDER_BY_ASC(mdBusinessQuery.getDisplayLabel().currentLocale());
    }
  }

}
