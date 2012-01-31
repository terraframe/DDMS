package dss.vector.solutions.geo;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.runwaysdk.business.BusinessFacade;
import com.runwaysdk.business.Entity;
import com.runwaysdk.business.generation.CompilerException;
import com.runwaysdk.business.generation.EntityQueryAPIGenerator;
import com.runwaysdk.business.rbac.Authenticate;
import com.runwaysdk.constants.MdTypeInfo;
import com.runwaysdk.constants.RelationshipInfo;
import com.runwaysdk.dataaccess.MdAttributeDAOIF;
import com.runwaysdk.dataaccess.MdBusinessDAOIF;
import com.runwaysdk.dataaccess.MdEntityDAOIF;
import com.runwaysdk.dataaccess.ProgrammingErrorException;
import com.runwaysdk.dataaccess.ValueObject;
import com.runwaysdk.dataaccess.metadata.MdBusinessDAO;
import com.runwaysdk.dataaccess.metadata.MdEntityDAO;
import com.runwaysdk.dataaccess.metadata.ReservedWords;
import com.runwaysdk.dataaccess.transaction.AbortIfProblem;
import com.runwaysdk.dataaccess.transaction.Transaction;
import com.runwaysdk.generation.loader.LoaderDecorator;
import com.runwaysdk.generation.loader.Reloadable;
import com.runwaysdk.gis.dataaccess.MdAttributeGeometryDAOIF;
import com.runwaysdk.query.Attribute;
import com.runwaysdk.query.GeneratedEntityQuery;
import com.runwaysdk.query.GeneratedViewQuery;
import com.runwaysdk.query.OIterator;
import com.runwaysdk.query.QueryFacade;
import com.runwaysdk.query.QueryFactory;
import com.runwaysdk.query.Selectable;
import com.runwaysdk.query.SelectableSQLCharacter;
import com.runwaysdk.query.SelectableSQLInteger;
import com.runwaysdk.query.ValueQuery;
import com.runwaysdk.query.ViewQueryBuilder;
import com.runwaysdk.session.Request;
import com.runwaysdk.system.gis.metadata.MdAttributeGeometry;
import com.runwaysdk.system.metadata.MdBusiness;
import com.runwaysdk.system.metadata.MdBusinessQuery;
import com.runwaysdk.system.metadata.MdType;

import dss.vector.solutions.InstallProperties;
import dss.vector.solutions.MDSSInfo;
import dss.vector.solutions.general.ThresholdAlertCalculationType;
import dss.vector.solutions.geo.generated.Earth;
import dss.vector.solutions.geo.generated.GeoEntity;
import dss.vector.solutions.geo.generated.GeoEntityQuery;
import dss.vector.solutions.query.AllRenderTypes;
import dss.vector.solutions.query.Layer;
import dss.vector.solutions.query.QueryConstants;
import dss.vector.solutions.query.SavedSearch;
import dss.vector.solutions.query.SavedSearchQuery;
import dss.vector.solutions.util.QueryUtil;
import dss.vector.solutions.util.UniversalSearchHelper;

public class GeoHierarchy extends GeoHierarchyBase implements com.runwaysdk.generation.loader.Reloadable
{
  private static final long   serialVersionUID    = 1236133816255L;

  public static final Integer SRID                = 4326;

  private static String       allowedInTree       = null;

  private static final String THEMATIC_SUFFIX     = "_thematic";

  public static final String  ALLPATHS_VIEW       = "geohierarchy_allpaths";
  
  public static final String GEOHIERARCHY_FLAGS = "geohierarchy_flags"; // FIXME find literal string refernces

  public static final String ALLPATHS_ROLLUP = "recursive_rollup"; // FIXME find literal string refernces

  public static final String  ALLPATHS_CHILD_TYPE = "child_type";

  public static final String  ALLPATHS_ROOT_TYPE  = "root_type";

  public static final String  ALLPATHS_DEPTH      = "depth";

  // private static Object lockObj = new Object();

  public GeoHierarchy()
  {
    super();
  }
  
  public static String getAllPathsSQL()
  {
    MdEntityDAOIF geoHierarchyMd = MdEntityDAO.getMdEntityDAO(GeoHierarchy.CLASS);
    String geoHierarchyTable = geoHierarchyMd.getTableName();
    String geoEntityClassCol = QueryUtil.getColumnName(GeoHierarchy.getGeoEntityClassMd());
    String politicalCol = QueryUtil.getColumnName(GeoHierarchy.getPoliticalMd());
    String sprayTargetAllowedCol = QueryUtil.getColumnName(GeoHierarchy.getSprayTargetAllowedMd());
    String populationAllowedCol = QueryUtil.getColumnName(GeoHierarchy.getPopulationAllowedMd());

    MdEntityDAOIF mdTypeMd = MdEntityDAO.getMdEntityDAO(MdTypeInfo.CLASS);
    String mdTypeTable = mdTypeMd.getTableName();
    String pckNameCol = QueryUtil.getColumnName(MdType.getPackageNameMd());
    String nameCol = QueryUtil.getColumnName(MdType.getTypeNameMd());

    String allowedInTable = MdEntityDAO.getMdEntityDAO(AllowedIn.CLASS).getTableName();

    String sql = "";

    sql += "WITH RECURSIVE geohierarchy_flags AS( \n";
    sql += "SELECT  (t1." + pckNameCol + " || '.' || t1." + nameCol + ") AS parent_type, \n";
    sql += "  g1." + geoEntityClassCol + " as parent_class, \n";
    sql += "  g1." + politicalCol + " AS parent_political, \n";
    sql += "  g1." + sprayTargetAllowedCol + " AS parent_spraytargetallowed,  \n";
    sql += "  g1." + populationAllowedCol + " AS parent_populationallowed, \n";
    sql += "  (t2." + pckNameCol + " || '.' || t2." + nameCol + ") AS child_type, \n";
    sql += "  g2." + geoEntityClassCol + " As child_class, \n";
    sql += "  g2." + politicalCol + " AS child_political, \n";
    sql += "  g2." + sprayTargetAllowedCol + " AS child_spraytargetallowed,  \n";
    sql += "  g2." + populationAllowedCol + " AS child_populationallowed \n";
    sql += "FROM " + allowedInTable + " , \n";
    sql += "  " + geoHierarchyTable + " g1,  \n";
    sql += "  " + geoHierarchyTable + " g2, \n";
    sql += "  " + mdTypeTable + " t1 , \n";
    sql += "  " + mdTypeTable + " t2  \n";
    sql += "WHERE  " + allowedInTable + "." + RelationshipInfo.PARENT_ID + " = g1.id \n";
    sql += "AND " + allowedInTable + "." + RelationshipInfo.CHILD_ID + " = g2.id \n";
    sql += "AND t1.id = g1." + geoEntityClassCol + "  \n";
    sql += "AND t2.id = g2." + geoEntityClassCol + " \n";
    sql += ") \n";
    sql += ", recursive_rollup AS ( \n";
    sql += " SELECT child_type, parent_type, parent_type as root_type, child_political, child_spraytargetallowed, child_populationallowed, parent_political, parent_spraytargetallowed, parent_populationallowed, parent_class as root_class ,0 as depth,  child_class \n";
    sql += "   \n";
    sql += "  FROM geohierarchy_flags \n";
    sql += " UNION \n";
    sql += " SELECT b.child_type, b.parent_type, a.root_type, b.child_political, b.child_spraytargetallowed, b.child_populationallowed, a.parent_political, a.parent_spraytargetallowed, a.parent_populationallowed, a.root_class , a.depth+1 , b.child_class \n";
    sql += " FROM recursive_rollup a,  geohierarchy_flags b \n";
    sql += " WHERE a.child_type = b.parent_type \n";
    sql += ") \n";

    return sql;
  }

  public static ValueQuery xmlToValueQuery(String xml, String config, Layer layer)
  {
    JSONObject queryConfig;
    try
    {
      queryConfig = new JSONObject(config);
      JSONObject selectedUniMap = queryConfig.getJSONObject(QueryConstants.SELECTED_UNIVERSALS);
      Iterator<?> keys = selectedUniMap.keys();
      String universalType = (String) keys.next();

      // Load the type-specific universal query class
      QueryFactory f = new QueryFactory();
      GeoEntityQuery geoQuery = new GeoEntityQuery(f);

      // Add the GeoEntity selectables to the ValueQuery
      List<Selectable> selectables = new LinkedList<Selectable>();

      Selectable localizedLabel = geoQuery.getEntityName(GeoEntity.ENTITYNAME);
      Selectable geoId = geoQuery.getGeoId(GeoEntity.GEOID);
      Selectable type = geoQuery.getType(GeoEntity.TYPE);
      
      selectables.add(localizedLabel);
      selectables.add(geoId);
      selectables.add(type);
      
      // If we're mapping then include the geometry column
      if (layer != null)
      {
        Attribute attr;
        if (layer.getRenderAs().get(0).equals(AllRenderTypes.POINT))
        {
          attr = geoQuery.get(GeoEntity.GEOPOINT);
        }
        else
        {
          attr = geoQuery.get(GeoEntity.GEOMULTIPOLYGON);
        }

        attr.setUserDefinedAlias(QueryConstants.GEOMETRY_NAME_COLUMN);
        selectables.add(attr);
      }

      ValueQuery vq = new ValueQuery(f);

      vq.SELECT(selectables.toArray(new Selectable[selectables.size()]));

      localizedLabel.setColumnAlias(QueryConstants.ENTITY_NAME_COLUMN);
      geoId.setColumnAlias(QueryConstants.GEO_ID_COLUMN);

      if (layer != null)
      {
        Selectable geometry = vq.getSelectableRef(QueryConstants.GEOMETRY_NAME_COLUMN);
        geometry.setColumnAlias(QueryConstants.GEOMETRY_NAME_COLUMN);
        vq.WHERE(geometry.NE(null));

        if (layer.hasThematicVariable())
        {
          Selectable thematic = vq.getSelectableRef(layer.getThematicUserAlias());

          // Only lock and apply the layer if it's not new to avoid erroring out
          // on a new instance used for calculations.
          if (!layer.isNew())
          {
            layer.appLock();
          }

          layer.setThematicColumnAlias(thematic.getColumnAlias());

          if (!layer.isNew())
          {
            layer.apply();
          }
        }
      }

      vq.WHERE(geoQuery.getType().EQ(universalType));

      return vq;
    }
    catch (JSONException e1)
    {
      throw new ProgrammingErrorException(e1);
    }
  }

  public static String getQueryType()
  {
    return QueryConstants.namespaceQuery(GeoHierarchy.CLASS, QueryConstants.QueryType.QUERY_UNIVERSAL);
  }

  @Override
  public void apply()
  {
    if (!this.isNew() || this.isAppliedToDB())
    {
      // Ensure that if this Universal is the Epidemic Universal than it must be
      // both political and allow populations
      List<GeoHierarchy> universals = ThresholdAlertCalculationType.getEpidemicUniversals();

      if (universals.contains(this) && ! ( this.getPolitical() && this.getPopulationAllowed() ))
      {
        EpidemicUniversalException e = new EpidemicUniversalException();
        e.setUniversal(this.getDisplayLabel());
        e.apply();

        throw e;
      }
    }

    boolean isNew = this.isNew();
    super.apply();
    if (isNew)
    {
      UniversalSearchHelper helper = new UniversalSearchHelper();
      helper.createSearch(this);
    }
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
    synchronized (Object.class)
    {
      return defineAllowedTree2(geoEntityId);
    }
  }

  @Transaction
  private static String defineAllowedTree2(String geoEntityId)
  {
    try
    {
      if (allowedInTree == null)
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

        allowedInTree = map.toString();
      }

      return allowedInTree;
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

  public static GeoHierarchy getGeoHierarchyFromLabel(String label)
  {
    String systemName = GeoHierarchy.getSystemName(label);

    GeoHierarchyQuery query = new GeoHierarchyQuery(new QueryFactory());
    query.WHERE(query.getGeoEntityClass().getTypeName().EQ(systemName));

    OIterator<? extends GeoHierarchy> i = query.getIterator();

    if (i.hasNext())
    {
      try
      {
        return i.next();
      }
      finally
      {
        i.close();
      }
    }
    else
    {
      // String errMsg = "Unable to find a universal with the name \"" + label +
      // "\" (or \"" + systemName + "\")";
      // System.err.println(errMsg);

      return null;
    }
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
  private static void treeRecurse(JSONObject types, HashSet<String> imports, GeoHierarchy parent, GeoHierarchy grandParent) throws JSONException
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
   * Returns a Set of type names included in the hierarchy for which the given
   * type is a member. Abstract types will be recursed through but the type
   * itself will not be added to the list. Although a Set is returned, the order
   * of insertion is preserved to as closely mimic a flattened tree as possible.
   * 
   * @param type
   * @return
   */
  public static Set<String> getIsAHierarchy(String type)
  {
    Set<String> types = new LinkedHashSet<String>();

    MdBusiness current = MdBusiness.getMdBusiness(type);

    MdBusiness parent = current.getSuperMdBusiness();
    MdBusiness start;
    if (parent != null && !parent.definesType().equals(GeoEntity.CLASS))
    {
      start = parent;
    }
    else
    {
      start = current;
    }

    while (parent != null)
    {
      if (!parent.getIsAbstract())
      {
        types.add(parent.definesType());
      }

      parent = parent.getSuperMdBusiness();
    }

    getIsAChildren(types, start);

    return types;
  }

  private static void getIsAChildren(Set<String> types, MdBusiness parent)
  {
    for (MdBusiness child : parent.getAllSubClass().getAll())
    {
      if (!child.getIsAbstract())
      {
        types.add(child.definesType());
      }

      getIsAChildren(types, child);
    }
  }

  public String getTypeName()
  {
    MdBusiness md = this.getGeoEntityClass();
    return md.getTypeName();
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
    return md.definesType();
  }

  /**
   * Static accessor to delete the given GeoHierarchy.
   * 
   * @param geoHierarchyId
   * @return An array of ids for all GeoHierarchies that were deleted.
   */
  @Authenticate
  public static String[] deleteGeoHierarchy(String geoHierarchyId)
  {
    Set<String> ids;

    synchronized (Object.class)
    {
      try
      {
        ids = deleteGeoHierarchy2(geoHierarchyId);
      }
      catch (CompilerException e)
      {
        CannotDeleteUniversalException cdue = new CannotDeleteUniversalException();
        cdue.setUniversalLabel(GeoHierarchy.get(geoHierarchyId).toString());
        throw cdue;
      }
      allowedInTree = null;
    }

    return ids.toArray(new String[ids.size()]);
  }

  @Transaction
  private static Set<String> deleteGeoHierarchy2(String geoHierarchyId)
  {
    InstallProperties.validateMasterOperation();

    Set<String> ids = new HashSet<String>();

    GeoHierarchy geoHierarchy = GeoHierarchy.get(geoHierarchyId);

    // Don't allow the user to delete Earth for very obvious reasons.
    MdBusiness geoEntityClass = geoHierarchy.getGeoEntityClass();
    if (geoEntityClass.definesType().equals(Earth.CLASS))
    {
      String error = "Cannot delete the Earth Universal.";
      DeleteEarthException ex = new DeleteEarthException(error);
      ex.setEarthName(geoEntityClass.getDisplayLabel().getValue());
      throw ex;
    }

    geoHierarchy.deleteInternal(ids);

    // Move all orphaned children under Earth
    GeoHierarchy geoEntityGH = GeoHierarchy.getGeoHierarchyFromType(GeoEntity.CLASS);
    GeoHierarchy earthGH = GeoHierarchy.getGeoHierarchyFromType(Earth.CLASS);
    
    QueryFactory f = new QueryFactory();
    GeoHierarchyQuery children = new GeoHierarchyQuery(f);
    AllowedInQuery rel = new AllowedInQuery(f);

    children.WHERE(children.SUBSELECT_NOT_IN_allowedInGeoEntity(rel));
    children.AND(children.getId().NI(earthGH.getId(), geoEntityGH.getId()));

    for(GeoHierarchy child : children.getIterator().getAll())
    {
      earthGH.addAcceptsGeoEntity(child).apply();
    }
    
    return ids;
  }

  protected String buildKey()
  {
    return this.getGeoEntityClass().definesType();
  }

  /**
   * Deletes this GeoHierarchy and its associated MdBusiness that defines a
   * GeoEntity subtype. All children are deleted recursively.
   */
  @Transaction
  private void deleteInternal(Set<String> ids)
  {
    ids.add(this.getId());

    MdBusiness geoEntityClass = this.getGeoEntityClass();

    // Regenerate the all paths table if any entities exist for this universal
    QueryFactory f = new QueryFactory();
    GeoEntityQuery q = new GeoEntityQuery(f);

    q.WHERE(q.getType().EQ(geoEntityClass.definesType()));

    boolean hasEntries = q.getCount() > 0;
    if (hasEntries)
    {
      MdBusiness mdBusiness = MdBusiness.getMdBusiness(AllPaths.CLASS);
      mdBusiness.deleteAllTableRecords();
    }

    // Remove all references to this universal in the saved queries
    String type = geoEntityClass.definesType();
    QueryFactory f2 = new QueryFactory();
    SavedSearchQuery ssq = new SavedSearchQuery(f2);

    for (SavedSearch search : ssq.getIterator().getAll())
    {
      try
      {
        JSONObject config = new JSONObject(search.getConfig());
        JSONObject selected = config.getJSONObject(QueryConstants.SELECTED_UNIVERSALS);

        Iterator iter = selected.keys();
        boolean modified = false;
        while (iter.hasNext())
        {
          String key = (String) iter.next();
          JSONArray filtered = new JSONArray();
          JSONArray universals = selected.getJSONArray(key);
          for (int i = 0; i < universals.length(); i++)
          {
            String universal = universals.getString(i);
            if (!universal.equals(type))
            {
              filtered.put(universal);
            }
          }

          if (filtered.length() != universals.length())
          {
            modified = true;
            selected.put(key, filtered);
          }
        }

        if (modified)
        {
          search.appLock();
          search.setConfig(config.toString());
          search.apply();
        }
      }
      catch (JSONException e)
      {
        throw new ProgrammingErrorException(e);
      }
    }

    super.delete();

    UniversalSearchHelper helper = new UniversalSearchHelper();
    helper.deleteSearch(this);

    geoEntityClass.delete();

    if (hasEntries)
    {
      GeoEntity.buildAllPathsFast();
    }
  }

  /**
   * Defines a new GeoEntity type based on the value of this view.
   */
  @Authenticate
  public static String defineGeoEntity(GeoEntityDefinition definition)
  {

    synchronized (Object.class)
    {
      String id = defineGeoEntity2(definition);

      allowedInTree = null;

      return id;
    }
  }

  @Transaction
  private static String defineGeoEntity2(GeoEntityDefinition definition)
  {
    InstallProperties.validateMasterOperation();

    // validate attributes
    definition.applyNoPersist();

    String id = defineGeoEntityInternal(definition);

    return id;
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

  public static MdAttributeGeometry getGeometry(MdBusinessDAOIF mdBusinessDAO)
  {
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
   * Checks the given MdBusiness and its parents for an MdAttributeGeometry and
   * returns it. If no MdAttributeGeometry is defined this method returns null.
   * 
   * @param mdBusiness
   * @return
   */
  public static MdAttributeGeometry getGeometry(MdBusiness mdBusiness)
  {
    MdBusinessDAO mdBusinessDAO = (MdBusinessDAO) BusinessFacade.getEntityDAO(mdBusiness);
    return getGeometry(mdBusinessDAO);
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
    String typeName = getSystemName(definition.getDisplayLabel());
    String label = definition.getDisplayLabel();
    String description = definition.getDescription();

    MdBusiness mdGeoEntity = new MdBusiness();
    mdGeoEntity.setPackageName(MDSSInfo.GENERATED_GEO_PACKAGE);
    mdGeoEntity.setTypeName(typeName);
    mdGeoEntity.getDisplayLabel().setValue(label);
    // mdGeoEntity.getDisplayLabel().setDefaultValue(label);
    mdGeoEntity.getDescription().setValue(description);
    // mdGeoEntity.getDescription().setDefaultValue(description);

    mdGeoEntity.setIsAbstract(false); // User defined types must be concrete
    mdGeoEntity.setExtendable(true);
    mdGeoEntity.setPublish(true);

    MdBusiness parent = MdBusiness.getMdBusiness(GeoEntity.CLASS);
    mdGeoEntity.setSuperMdBusiness(parent);
    mdGeoEntity.apply();

    // create the GeoHeirachy and relationship
    GeoHierarchy geoHierarchy = new GeoHierarchy();
    geoHierarchy.setPolitical(definition.getPolitical());
    geoHierarchy.setSprayTargetAllowed(definition.getSprayTargetAllowed());
    geoHierarchy.setGeoEntityClass(mdGeoEntity);
    geoHierarchy.setTerm(definition.getTerm());
    geoHierarchy.setPopulationAllowed(definition.getPopulationAllowed());
    geoHierarchy.setUrban(definition.getUrban());
    geoHierarchy.apply();

    // IMPORTANT: geoHierarchy.apply() causes a reload which causes a class cast
    // exception when
    // GeoHierarchy.get(geoHierarchy.getId()) is invoked on the out dated class.
    // Therefore we need to use reflection to get the current GeoHierarchy class
    // definition.
    try
    {
      Class<?> ghClass = LoaderDecorator.load(GeoHierarchy.CLASS);
      Method getMethod = ghClass.getMethod("get", String.class);
      Object childUniversal = getMethod.invoke(null, geoHierarchy.getId());
      Object parentUniversal = getMethod.invoke(null, definition.getParentGeoHierarchyId());
      Entity allowedIn = (Entity) ghClass.getMethod("addAllowedInGeoEntity", parentUniversal.getClass()).invoke(childUniversal, parentUniversal);
      allowedIn.apply();

      ghClass.getMethod("validateConsistentHierarchy").invoke(childUniversal);
      return ( (Entity) childUniversal ).getId();
    }
    catch (InvocationTargetException e)
    {
      Throwable cause = e.getCause();

      if (cause != null && ( cause instanceof RuntimeException ))
      {
        throw (RuntimeException) cause;
      }

      // if (targetException instanceof SmartException)
      // {
      // throw (SmartException) targetException;
      // }
      throw new ProgrammingErrorException(e);
    }
    catch (Exception e)
    {
      throw new ProgrammingErrorException(e);
    }
    //
    // GeoHierarchy allowedIn =
    // GeoHierarchy.get(definition.getParentGeoHierarchyId());
    // geoHierarchy.addAllowedInGeoEntity(allowedIn).apply();
    //
    // geoHierarchy.validateConsistentHierarchy();
    //
    // return geoHierarchy.getId();
  }

  @Override
  public void confirmChangeParent(String parentId)
  {
    InstallProperties.validateMasterOperation();

    GeoHierarchy view = GeoHierarchy.get(parentId);
    String label = view.getGeoEntityClass().getDisplayLabel().getValue();

    String error = "Delete the old relationship with [" + label + "] or the Hierarchy?";
    ConfirmHierarchyParentChangeException ex = new ConfirmHierarchyParentChangeException(error);
    ex.setParentDisplayLabel(label);
    throw ex;
  }

  @Override
  public String[] confirmDeleteHierarchy(String parentId)
  {
    InstallProperties.validateMasterOperation();

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
      return deleteGeoHierarchy(this.getId());
    }
  }

  @Override
  public void deleteRelationship(String parentId)
  {
    synchronized (Object.class)
    {
      deleteRelationship2(parentId);

      allowedInTree = null;
    }
  }

  @Transaction
  private void deleteRelationship2(String parentId)
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

  public GeoEntityDefinition getGeoEntityDefinition()
  {
    GeoEntityDefinition definition = new GeoEntityDefinition();
    MdBusiness mdBusiness = this.getGeoEntityClass();

    definition.setTypeName(mdBusiness.getTypeName());
    definition.setDisplayLabel(mdBusiness.getDisplayLabel().getValue());
    definition.setDescription(mdBusiness.getDescription().getValue());
    definition.setTerm(this.getTerm());
    definition.setPolitical(this.getPolitical());
    definition.setSprayTargetAllowed(this.getSprayTargetAllowed());
    definition.setPopulationAllowed(this.getPopulationAllowed());

    return definition;
  }

  /**
   * Locks this object and the MdBusiness which represents a GeoEntity subtype.
   */
  @Override
  @Transaction
  public void lock()
  {

    InstallProperties.validateMasterOperation();

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
  @Authenticate
  public static void updateFromView(GeoHierarchyView view)
  {
    synchronized (Object.class)
    {
      updateFromView2(view);

      allowedInTree = null;
    }
  }

  /**
   * Ensures that the political and spray hierarchy don't have gaps and that
   * those hierarchies do not branch.
   * 
   * @throws HierarchyGapException
   *           if there is a gap in the political or spray hierarchy.
   * @throws HierarchyBranchException
   *           if the political or spray hierarchy tries to branch.
   */
  public void validateConsistentHierarchy()
  {
    List<GeoHierarchy> parents = this.getImmediateParents();

    boolean isPolitical = this.getPolitical();
    boolean isSpray = this.getSprayTargetAllowed();
    boolean isUrban = this.getUrban();

    if (parents.size() > 0)
    {
      boolean politicalParent = false;
      boolean sprayParent = false;
      boolean urbanParent = false;

      boolean politicalChild = false;
      boolean sprayChild = false;
      boolean urbanChild = false;

      // To avoid gaps, we compare this GeoHierarchies political/spray
      // value to that of its parent. One match must exist.
      for (GeoHierarchy parent : parents)
      {
        if (parent.getPolitical())
        {
          politicalParent = true;
        }

        if (parent.getSprayTargetAllowed())
        {
          sprayParent = true;
        }

        if (parent.getUrban() != null && parent.getUrban())
        {
          urbanParent = true;
        }
      }
      
      // To avoid branching, we must check the immediate children of each
      // parent and see if the political/spray hierarchy continues.
      for(GeoHierarchy parent : parents)
      {
        for (GeoHierarchy child : parent.getImmediateChildren())
        {
          if (child.equals(this) || parents.contains(child))
          {
            continue;
          }

          if (child.getPolitical())
          {
            politicalChild = true;
          }

          if (child.getSprayTargetAllowed())
          {
            sprayChild = true;
          }

          if (child.getUrban() != null && child.getUrban())
          {
            urbanChild = true;
          }
        }
      }

      // check political hierarchy
      if (isPolitical && !politicalParent)
      {
        String msg = "The universal [" + this.getQualifiedType() + "] attempted to create a" + " gap in the political hierarchy.";
        HierarchyGapException ex = new HierarchyGapException(msg);
        throw ex;
      }
      else if (isPolitical && politicalChild)
      {
        String msg = "The universal [" + this.getQualifiedType() + "] attempted to branch the political hierarchy.";
        HierarchyBranchException ex = new HierarchyBranchException(msg);
        throw ex;
      }

      // check spray hierarchy
      if (isSpray && !sprayParent)
      {
        String msg = "The universal [" + this.getQualifiedType() + "] attempted to create a" + " gap in the spray hierarchy.";
        HierarchyGapException ex = new HierarchyGapException(msg);
        throw ex;
      }
      else if (isSpray && sprayChild)
      {
        String msg = "The universal [" + this.getQualifiedType() + "] attempted to branch the spray hierarchy.";
        HierarchyBranchException ex = new HierarchyBranchException(msg);
        throw ex;
      }

      // check urban hierarchy
      if (isUrban && !urbanParent)
      {
        String msg = "The universal [" + this.getQualifiedType() + "] attempted to create a" + " gap in the urban hierarchy.";
        HierarchyGapException ex = new HierarchyGapException(msg);
        throw ex;
      }
      else if (isUrban && urbanChild)
      {
        String msg = "The universal [" + this.getQualifiedType() + "] attempted to branch the urban hierarchy.";
        HierarchyBranchException ex = new HierarchyBranchException(msg);
        throw ex;
      }
    }

    if (!isPolitical)
    {
      // Political has been set to false, so set all of its children political
      // flags to false. Since no branching is allowed we can safely modify all
      // children.
      for (GeoHierarchy child : this.getAllChildren())
      {
        child.appLock();
        child.setPolitical(false);
        child.apply();
      }
    }

    if (!isSpray)
    {
      // Spray has been set to false, so set all of its children spray
      // flags to false. Since no branching is allowed we can safely modify all
      // children.
      for (GeoHierarchy child : this.getAllChildren())
      {
        child.appLock();
        child.setSprayTargetAllowed(false);
        child.apply();
      }
    }

    if (!isUrban)
    {
      // Urban has been set to false, so set all of its children spray
      // flags to false. Since no branching is allowed we can safely modify all
      // children.
      for (GeoHierarchy child : this.getAllChildren())
      {
        child.appLock();
        child.setUrban(false);
        child.apply();
      }
    }
  }

  @Transaction
  private static void updateFromView2(GeoHierarchyView view)
  {
    InstallProperties.validateMasterOperation();

    // GeoHierarchy should already be locked
    GeoHierarchy geoHierarchy = GeoHierarchy.get(view.getGeoHierarchyId());
    geoHierarchy.setPolitical(view.getPolitical());
    geoHierarchy.setSprayTargetAllowed(view.getSprayTargetAllowed());
    geoHierarchy.setTerm(view.getTerm());
    geoHierarchy.setPopulationAllowed(view.getPopulationAllowed());
    geoHierarchy.setUrban(view.getUrban());

    geoHierarchy.validateConsistentHierarchy();

    geoHierarchy.apply();

    MdBusiness geoEntityClass = geoHierarchy.getGeoEntityClass();
    geoEntityClass.getDisplayLabel().setValue(view.getDisplayLabel());
    geoEntityClass.getDescription().setValue(view.getDescription());

    // Do these ever need to be called?
    // geoEntityClass.getDisplayLabel().setDefaultValue(view.getDisplayLabel());
    // geoEntityClass.getDescription().setDefaultValue(view.getDescription());

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
  @Authenticate
  public static void applyExistingWithParent(String childGeoHierarchyId, String parentGeoHierarchyId, Boolean cloneOperation)
  {

    synchronized (Object.class)
    {

      applyExistingWithParent2(childGeoHierarchyId, parentGeoHierarchyId, cloneOperation);

      allowedInTree = null;

    }
  }

  @Transaction
  private static void applyExistingWithParent2(String childGeoHierarchyId, String parentGeoHierarchyId, Boolean cloneOperation)
  {
    InstallProperties.validateMasterOperation();

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

    if (!cloneOperation)
    {
      validateModifyGeoHierarchy(childGeoHierarchy);
    }

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

        String error = "The child [" + childDL + "] is already located in the parent [" + parentDL + "].";
        DuplicateHierarchyParentException e = new DuplicateHierarchyParentException(error);
        e.setChildDisplayLabel(childDL);
        e.setChildDisplayLabel(parentDL);

        throw e;
      }
    }

    childGeoHierarchy.addAllowedInGeoEntity(parentGeoHierarchy).apply();

    childGeoHierarchy.validateConsistentHierarchy();
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
  public static GeoHierarchyViewQuery getGeoEntityHierarchyViews(String sortAttribute, Boolean ascending, Integer pageSize, Integer pageNumber)
  {
    InstallProperties.validateMasterOperation();

    QueryFactory f = new QueryFactory();
    GeoHierarchyViewQuery viewQuery = new GeoHierarchyViewQuery(f, sortAttribute, ascending, pageSize, pageNumber);

    return viewQuery;
  }

  public static GeoHierarchyViewQuery getAllGeoHierarchyViews()
  {
    QueryFactory f = new QueryFactory();
    GeoHierarchyViewQuery viewQuery = new GeoHierarchyViewQuery(f, new GetAllGeoHierarchyQueryBuilder(f));

    return viewQuery;
  }

  @Request
  public static GeoHierarchyView[] getAllViews()
  {
    List<GeoHierarchyView> list = new LinkedList<GeoHierarchyView>();

    GeoHierarchyQuery query = new GeoHierarchyQuery(new QueryFactory());
    query.ORDER_BY_ASC(query.getGeoEntityClass().getTypeName());

    OIterator<? extends GeoHierarchy> iterator = query.getIterator();

    try
    {
      while (iterator.hasNext())
      {
        list.add(iterator.next().getViewForGeoHierarchy());
      }

      return list.toArray(new GeoHierarchyView[list.size()]);
    }
    finally
    {
      iterator.close();
    }
  }

  /**
   * @return An array of all Geo Hierarchies where SprayTargetAllowed==true
   */
  public static GeoHierarchy[] getAllSprayTargets()
  {
    GeoHierarchyQuery query = new GeoHierarchyQuery(new QueryFactory());
    query.WHERE(query.getSprayTargetAllowed().EQ(true));
    List<? extends GeoHierarchy> list = query.getIterator().getAll();
    return list.toArray(new GeoHierarchy[list.size()]);
  }

  /**
   * @return An array of all Geo Hierarchies where Political==true
   */
  public static GeoHierarchy[] getAllPoliticals()
  {
    GeoHierarchyQuery query = new GeoHierarchyQuery(new QueryFactory());
    query.WHERE(query.getPolitical().EQ(true));
    List<? extends GeoHierarchy> list = query.getIterator().getAll();
    return list.toArray(new GeoHierarchy[list.size()]);
  }

  /**
   * @return An array of all Geo Hierarchies where Urban==true
   */
  public static GeoHierarchy[] getAllUrban()
  {
    GeoHierarchyQuery query = new GeoHierarchyQuery(new QueryFactory());
    query.WHERE(query.getUrban().EQ(true));
    List<? extends GeoHierarchy> list = query.getIterator().getAll();
    return list.toArray(new GeoHierarchy[list.size()]);
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
    view.setUrban(this.getUrban());
    view.setDescription(md.getDescription().getValue());
    view.setTypeName(md.getTypeName());
    view.setDisplayLabel(md.getDisplayLabel().getValue());
    view.setReferenceId(md.getId());
    view.setGeoHierarchyId(this.getId());
    view.setTerm(this.getTerm());
    view.setPopulationAllowed(this.getPopulationAllowed());

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
    GeoHierarchyViewQuery query = new GeoHierarchyViewQuery(f, new OrderedGeoHiearchyQueryBuilder(f, this));

    return query;
  }

  /**
   * Collects and returns all GeoHierarchy objects (parents and children) that
   * are of the type of GeoEntity of the given id. The collection is restricted
   * by the political and sprayZoneAllowed flag on each GeoHierarchy.
   * 
   * 
   * @param rootGeoEntityId
   * @param political
   * @param sprayZoneAllowed
   * @param extraUniversals
   *          Any extra univerals to append to the final list.
   * @return
   */
  public static GeoHierarchyView[] collectHierarchies(String rootGeoEntityId, Boolean[] flags, String[] extraUniversals)
  {
    boolean[] _flags = new boolean[flags.length];

    for (int i = 0; i < flags.length; i++)
    {
      _flags[i] = flags[i].booleanValue();
    }

    GeoEntity geoEntity = GeoEntity.get(rootGeoEntityId);
    String type = geoEntity.getType();

    GeoHierarchy parent = getGeoHierarchyFromType(type);

    LinkedHashSet<GeoHierarchyView> hierarchies = new LinkedHashSet<GeoHierarchyView>();
    collect(hierarchies, parent, _flags);

    // add the extra universals
    for (String universal : extraUniversals)
    {
      boolean collectChildren = false;

      if (universal.endsWith("*"))
      {
        universal = universal.substring(0, universal.length() - 1);
        collectChildren = true;
      }

      GeoHierarchy extra = getGeoHierarchyFromType(universal);
      hierarchies.add(extra.getViewForGeoHierarchy());

      if (collectChildren)
      {
        Set<String> children = getIsAHierarchy(universal);
        for (String child : children)
        {
          GeoHierarchy childH = GeoHierarchy.getGeoHierarchyFromType(child);
          hierarchies.add(childH.getViewForGeoHierarchy());
        }
      }
    }

    return hierarchies.toArray(new GeoHierarchyView[hierarchies.size()]);
  }

  public static void collect(LinkedHashSet<GeoHierarchyView> hierarchies, GeoHierarchy parent, boolean[] flags)
  {
    if (GeoHierarchy.isValid(parent, flags))
    {
      hierarchies.add(parent.getViewForGeoHierarchy());

      // IMPORTANT: We only need to search the child of valid entities because
      // there cannot be any gaps in the hierarchy
      List<GeoHierarchy> children = parent.getImmediateChildren();

      for (GeoHierarchy childH : children)
      {
        collect(hierarchies, childH, flags);
      }
    }
  }

  private static boolean isValid(GeoHierarchy entity, boolean[] flags)
  {
    boolean isPolitical = entity.getPolitical().booleanValue();
    boolean isSprayZoneAllowed = entity.getSprayTargetAllowed().booleanValue();
    boolean isUrban = entity.getUrban() != null ? entity.getUrban().booleanValue() : false;

    boolean political = flags[0];
    boolean sprayZoneAllowed = flags[1];
    boolean urban = flags[2];

    if (!political && !sprayZoneAllowed && !urban)
    {
      // Special criteria: Accept all entities

      return true;
    }
    else if (political && isPolitical)
    {
      return true;
    }
    else if (sprayZoneAllowed && isSprayZoneAllowed)
    {
      return true;
    }
    else if (urban && isUrban)
    {
      return true;
    }

    return false;
  }

  /**
   * Returns all GeoHierarchy views starting with the GeoHierarchy that
   * represents the given GeoEntity.
   * 
   * @param parameter
   *          TODO
   * @param geoEntityId
   * 
   * @return
   */
  public static GeoHierarchyView[] getHierarchies(GeoEntity geoEntity, SearchParameter parameter)
  {
    return getGeoHierarchiesByType(geoEntity.getType(), parameter);
  }

  public static GeoHierarchyView[] getHierarchies(SearchParameter parameter)
  {
    return getGeoHierarchiesByType(Earth.CLASS, parameter);
  }

  /**
   * Returns all GeoHierarchies under and including the given type.
   * 
   * @param type
   * @param parameter
   *          TODO
   * @return
   */
  public static GeoHierarchyView[] getGeoHierarchiesByType(String type, SearchParameter parameter)
  {
    GeoHierarchy hierarchy = GeoHierarchy.getGeoHierarchyFromType(type);

    LinkedHashSet<GeoHierarchyView> views = new LinkedHashSet<GeoHierarchyView>();

    if (parameter.isFirst())
    {
      List<GeoHierarchy> hierachies = parameter.getHierarchies(hierarchy);

      for (GeoHierarchy childH : hierachies)
      {
        treeRecurse(views, childH, parameter);
      }
    }
    else
    {
      treeRecurse(views, hierarchy, parameter);
    }

    return views.toArray(new GeoHierarchyView[views.size()]);
  }

  private static void treeRecurse(LinkedHashSet<GeoHierarchyView> views, GeoHierarchy hierarchy, SearchParameter parameter)
  {
    boolean valid = parameter.validateFlagsAndModifiers(hierarchy);

    if (valid)
    {
      views.add(hierarchy.getViewForGeoHierarchy());

      if (!parameter.isFirst())
      {
        List<GeoHierarchy> hierachies = parameter.getHierarchies(hierarchy);

        for (GeoHierarchy childH : hierachies)
        {
          treeRecurse(views, childH, parameter);
        }
      }
    }
    else if (parameter.validateFlags(hierarchy))
    {
      List<GeoHierarchy> hierachies = parameter.getHierarchies(hierarchy);

      for (GeoHierarchy childH : hierachies)
      {
        treeRecurse(views, childH, parameter);
      }
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

  // private String getViewSQL(MdBusiness md, MdAttributeGeometry mdAttrGeo)
  // {
  //
  // MdBusiness definingMd = (MdBusiness) mdAttrGeo.getDefiningMdClass();
  //
  // String attrName = mdAttrGeo.getAttributeName();
  //
  // // create the ValueQuery whose SELECT will become a database view
  // QueryFactory f = new QueryFactory();
  // ValueQuery vQuery = new ValueQuery(f);
  //
  // GeoEntityQuery geoQuery = new GeoEntityQuery(f);
  // Attribute entityNameAttr = (Attribute) geoQuery.getEntityName();
  // entityNameAttr.setColumnAlias(QueryConstants.ENTITY_NAME_COLUMN);
  //
  // // if the MdBusiness that defines the geometry is the MdBusiness this
  // // GeoHierarchy wraps, then just join with GeoEntity to get the entityName
  // if (md.getId().equals(definingMd.getId()))
  // {
  // BusinessQuery q = f.businessQuery(md.definesType());
  // vQuery.SELECT(q.get(attrName), entityNameAttr);
  // vQuery.WHERE(q.aCharacter(ComponentInfo.ID).EQ(geoQuery.getId()));
  // }
  // else
  // {
  // // perform a join between *this* GeoEntity table and the one
  // // that defines the geometry attribute.
  // BusinessQuery q1 = f.businessQuery(md.definesType());
  // BusinessQuery q2 = f.businessQuery(definingMd.definesType());
  // vQuery.SELECT(q2.get(attrName), entityNameAttr);
  // vQuery.WHERE(q1.aCharacter(ComponentInfo.ID).EQ(q2.aCharacter(ComponentInfo.ID)));
  // vQuery.WHERE(q2.aCharacter(ComponentInfo.ID).EQ(geoQuery.getId()));
  // }
  //
  // // exclude any entity without spatial data
  // Selectable geometrySelectable = vQuery.getSelectableRef(attrName);
  // vQuery.AND(geometrySelectable.NE(null));
  //
  // String sql = vQuery.getSQL();
  // return sql;
  // }

  public static boolean addGeoHierarchyJoinConditions(ValueQuery valueQuery, Map<String, GeneratedEntityQuery> queryParserMap)
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
    if (geoHierarchyMap.size() == 0)
    {
      return false;
    }

    // Get all children of Earth
    GeoHierarchy earthGeoHierarchy = getGeoHierarchyFromType(Earth.CLASS);
    List<GeoHierarchy> allEarthChildren = new LinkedList<GeoHierarchy>();
    getAllChildren(allEarthChildren, earthGeoHierarchy);

    // Find the first and last GeoHierarchy
    Collection<GeoHierarchy> requestedList = geoHierarchyMap.values();
    List<Integer> indexes = new LinkedList<Integer>();
    for (GeoHierarchy requested : requestedList)
    {
      // find the entry relative to the master list of GeoHierarchies
      Integer index = allEarthChildren.indexOf(requested);
      indexes.add(index);
    }

    Collections.sort(indexes);

    GeoHierarchy first = allEarthChildren.get(indexes.get(0));
    GeoHierarchy last = allEarthChildren.get(indexes.get(indexes.size() - 1));

    // Fill in the gaps from the first to last GeoHierarchy
    List<GeoHierarchy> pathFromParentToChild = new LinkedList<GeoHierarchy>();
    List<GeoHierarchy> absoluteParents = last.getAllParents();

    for (GeoHierarchy parent : absoluteParents)
    {
      if (pathFromParentToChild.contains(first))
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
          generatedEntityQuery = (GeneratedEntityQuery) queryClass.getConstructor(QueryFactory.class).newInstance(queryFactory);
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

    return true;
  }

  /**
   * Returns the display label of this GeoHierarchy, which is actually the
   * display label of the underlying MdBusiness that this instance references.
   * NOTE: this should only be called after this GeoHierarchy has been
   * successfully applied because then the required MdBusiness reference will be
   * available.
   * 
   * @return
   */
  public String getDisplayLabel()
  {
    return this.getGeoEntityClass().getDisplayLabel().getValue();
  }

  public String toString()
  {
    MdBusiness md = this.getGeoEntityClass();
    return md != null ? md.getDisplayLabel().getValue() : super.toString();
  }

  private static class GetAllGeoHierarchyQueryBuilder extends ViewQueryBuilder implements Reloadable
  {
    private GeoHierarchyQuery geoHierarchyQuery;

    private MdBusinessQuery   mdBusinessQuery;

    private MdBusinessQuery   parentMdBusinessQuery;

    protected GetAllGeoHierarchyQueryBuilder(QueryFactory queryFactory)
    {
      super(queryFactory);

      geoHierarchyQuery = new GeoHierarchyQuery(queryFactory);
      mdBusinessQuery = new MdBusinessQuery(queryFactory);
      parentMdBusinessQuery = new MdBusinessQuery(queryFactory);
    }

    /**
     * build the select clause
     */
    protected void buildSelectClause()
    {
      GeneratedViewQuery vQuery = this.getViewQuery();

      vQuery.map(GeoHierarchyView.GEOHIERARCHYID, geoHierarchyQuery.getId());
      vQuery.map(GeoHierarchyView.POLITICAL, geoHierarchyQuery.getPolitical());
      vQuery.map(GeoHierarchy.SPRAYTARGETALLOWED, geoHierarchyQuery.getSprayTargetAllowed());

      vQuery.map(GeoHierarchyView.REFERENCEID, mdBusinessQuery.getId());
      vQuery.map(GeoHierarchyView.TYPENAME, mdBusinessQuery.getTypeName());
      vQuery.map(GeoHierarchyView.DISPLAYLABEL, mdBusinessQuery.getDisplayLabel().localize());
      vQuery.map(GeoHierarchyView.DESCRIPTION, mdBusinessQuery.getDescription().localize());
      vQuery.map(GeoHierarchyView.ISADISPLAYLABEL, parentMdBusinessQuery.getDisplayLabel().localize());
    }

    /**
     * Implement only if additional join criteria is required.
     */
    protected void buildWhereClause()
    {
      GeneratedViewQuery vQuery = this.getViewQuery();

      vQuery.WHERE(geoHierarchyQuery.getGeoEntityClass().EQ(mdBusinessQuery));
      vQuery.WHERE(mdBusinessQuery.getSuperMdBusiness().EQ(parentMdBusinessQuery));

      vQuery.ORDER_BY_ASC(mdBusinessQuery.getDisplayLabel().localize());
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
      vQuery.map(GeoHierarchyView.DISPLAYLABEL, mdBusinessQuery.getDisplayLabel().localize());
      vQuery.map(GeoHierarchyView.DESCRIPTION, mdBusinessQuery.getDescription().localize());
      vQuery.map(GeoHierarchyView.ISADISPLAYLABEL, parentMdBusinessQuery.getDisplayLabel().localize());
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

      vQuery.ORDER_BY_ASC(mdBusinessQuery.getDisplayLabel().localize());
    }
  }

  public static boolean isAncestor(String type, String universal)
  {
    GeoHierarchyView[] decendants = GeoHierarchy.getGeoHierarchiesByType(type, new SearchParameter());

    for (GeoHierarchyView decendant : decendants)
    {
      String generatedType = decendant.getGeneratedType();

      if (generatedType.equals(universal))
      {
        return true;
      }
    }

    return false;
  }

  public static String getMostChildishUniversialType(String[] types)
  {

    QueryFactory queryFactory = new QueryFactory();

    ValueQuery lowestUniversial = new ValueQuery(queryFactory);

    SelectableSQLCharacter childType = lowestUniversial.aSQLCharacter(ALLPATHS_CHILD_TYPE, ALLPATHS_CHILD_TYPE);

    SelectableSQLInteger depth = lowestUniversial.aSQLAggregateInteger(ALLPATHS_DEPTH, "MAX(" + ALLPATHS_DEPTH + ")");

    lowestUniversial.SELECT(new Selectable[] { childType, depth });
    lowestUniversial.FROM(ALLPATHS_VIEW, ALLPATHS_VIEW);
    lowestUniversial.WHERE(childType.IN(types));
    lowestUniversial.ORDER_BY_DESC(depth);

    OIterator<ValueObject> iter = lowestUniversial.getIterator();
    try
    {
      if (iter.hasNext())
      {
        ValueObject o = iter.next();
        int maxDepth = Integer.parseInt(o.getValue(ALLPATHS_DEPTH));
        String universal = o.getValue(ALLPATHS_CHILD_TYPE);

        // Make sure we have the "smallest" universal. If the
        // next result has the same depth then we must throw
        // an error because they are either siblings or equally "small"
        if (iter.hasNext())
        {
          ValueObject o2 = iter.next();
          int nextDepth = Integer.parseInt(o2.getValue(ALLPATHS_DEPTH));
          if (maxDepth == nextDepth)
          {
            String msg = "There is no universal with the largest depth among [" + types + "]";
            throw new NoSmallestLocatedInException(msg);
          }
        }

        return universal;
      }
    }
    finally
    {
      iter.close();
    }
    return null;
  }

  public static Boolean hasBrowserRoot(String className)
  {
    GeoHierarchy hierarchy = GeoHierarchy.getGeoHierarchyFromType(className);

    if (hierarchy != null)
    {
      return ( hierarchy.getTerm() != null );
    }

    return false;
  }

  public static String getSystemName(String description)
  {
    return getSystemName(description, "Universal", true);
  }

  public static String getSystemName(String description, String suffix, boolean isClassName)
  {
    String systemName = description;
    String name = description.replace("/", " Or ").replace("&", " And ");
    // String[] parts = name.split("[^a-zA-Z0-9]");
    String[] parts = name.split("[^\\p{L}0-9]");
    StringBuffer sb = new StringBuffer();
    if (parts.length == 1 && description.equals(description.toUpperCase()))
    {
      // It's an acronym, so use it as is.
      systemName = description;
    }
    else
    {
      // Create a camelcase representation of the description
      for (int i = 0; i < parts.length; i++)
      {
        String part = parts[i];
        if (part.length() > 0)
        {
          if (i == parts.length - 1)
          {
            // Last part
            String arabicPart = convertRomanToArabic(part);
            if (arabicPart.equals(part))
            {
              // Not a roman numeral
              sb.append(part.substring(0, 1).toUpperCase());
              sb.append(part.substring(1).toLowerCase());
            }
            else
            {
              // Roman numeral converted to arabic
              sb.append(arabicPart);
            }
          }
          else
          {
            sb.append(part.substring(0, 1).toUpperCase());
            sb.append(part.substring(1).toLowerCase());
          }
        }
      }
      systemName = sb.toString();

      if (ReservedWords.javaContains(systemName) || ReservedWords.sqlContains(systemName))
      {
        systemName += suffix;
      }

      if (!isClassName)
        systemName = systemName.substring(0, 1).toLowerCase() + systemName.substring(1);
    }
    return systemName;
  }

  private static String convertRomanToArabic(String part)
  {
    if ("IV".equals(part.toUpperCase()))
    {
      return part.substring(0, part.length() - 2) + "4";
    }
    if ("V".equals(part.toUpperCase()))
    {
      return part.substring(0, part.length() - 1) + "5";
    }
    if ("III".equals(part.toUpperCase()))
    {
      return part.substring(0, part.length() - 3) + "3";
    }
    if ("II".equals(part.toUpperCase()))
    {
      return part.substring(0, part.length() - 2) + "2";
    }
    if ("I".equals(part.toUpperCase()))
    {
      return part.substring(0, part.length() - 1) + "1";
    }
    return part;
  }
}
