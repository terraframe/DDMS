package dss.vector.solutions.geo.generated;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Savepoint;
import java.sql.Timestamp;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;
import java.util.TreeSet;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.json.JSONArray;
import org.json.JSONException;

import com.runwaysdk.business.Business;
import com.runwaysdk.business.BusinessFacade;
import com.runwaysdk.constants.CommonProperties;
import com.runwaysdk.constants.DatabaseInfo;
import com.runwaysdk.constants.DatabaseProperties;
import com.runwaysdk.constants.MdAttributeCharacterInfo;
import com.runwaysdk.constants.MdAttributeIntegerInfo;
import com.runwaysdk.constants.MdBusinessInfo;
import com.runwaysdk.constants.RelationshipInfo;
import com.runwaysdk.constants.ServerConstants;
import com.runwaysdk.dataaccess.InvalidIdException;
import com.runwaysdk.dataaccess.MdAttributeConcreteDAOIF;
import com.runwaysdk.dataaccess.MdAttributeReferenceDAOIF;
import com.runwaysdk.dataaccess.MdBusinessDAOIF;
import com.runwaysdk.dataaccess.MdClassDAOIF;
import com.runwaysdk.dataaccess.ProgrammingErrorException;
import com.runwaysdk.dataaccess.RelationshipDAOIF;
import com.runwaysdk.dataaccess.ValueObject;
import com.runwaysdk.dataaccess.cache.DataNotFoundException;
import com.runwaysdk.dataaccess.database.Database;
import com.runwaysdk.dataaccess.database.DuplicateDataDatabaseException;
import com.runwaysdk.dataaccess.database.general.PostgreSQL;
import com.runwaysdk.dataaccess.metadata.MdBusinessDAO;
import com.runwaysdk.dataaccess.metadata.MdClassDAO;
import com.runwaysdk.dataaccess.transaction.Transaction;
import com.runwaysdk.generation.loader.LoaderDecorator;
import com.runwaysdk.generation.loader.Reloadable;
import com.runwaysdk.gis.dataaccess.AttributeGeometryIF;
import com.runwaysdk.gis.dataaccess.MdAttributeGeometryDAOIF;
import com.runwaysdk.query.AND;
import com.runwaysdk.query.Condition;
import com.runwaysdk.query.F;
import com.runwaysdk.query.GeneratedViewQuery;
import com.runwaysdk.query.LeftJoinEq;
import com.runwaysdk.query.OIterator;
import com.runwaysdk.query.OR;
import com.runwaysdk.query.QueryFactory;
import com.runwaysdk.query.SelectableChar;
import com.runwaysdk.query.SelectablePrimitive;
import com.runwaysdk.query.ValueQuery;
import com.runwaysdk.query.ViewQueryBuilder;
import com.runwaysdk.session.Session;
import com.runwaysdk.session.SessionIF;
import com.runwaysdk.system.metadata.MdBusiness;
import com.runwaysdk.system.metadata.MdBusinessQuery;
import com.runwaysdk.system.metadata.MdClass;
import com.runwaysdk.system.metadata.MdRelationship;
import com.runwaysdk.transport.conversion.json.JSONUtil;
import com.runwaysdk.util.IdParser;
import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.io.ParseException;

import dss.vector.solutions.DefaultGeoEntity;
import dss.vector.solutions.LocalProperty;
import dss.vector.solutions.MDSSInfo;
import dss.vector.solutions.WKTParsingProblem;
import dss.vector.solutions.geo.AllPaths;
import dss.vector.solutions.geo.AllPathsQuery;
import dss.vector.solutions.geo.ChildEntityOverflowInformation;
import dss.vector.solutions.geo.ConfirmDeleteEntityException;
import dss.vector.solutions.geo.ConfirmParentChangeException;
import dss.vector.solutions.geo.DuplicateParentException;
import dss.vector.solutions.geo.EntityNotChildOfMainWarning;
import dss.vector.solutions.geo.GeoEntitySelectionProblem;
import dss.vector.solutions.geo.GeoEntityView;
import dss.vector.solutions.geo.GeoEntityViewQuery;
import dss.vector.solutions.geo.GeoHierarchy;
import dss.vector.solutions.geo.GeoHierarchyView;
import dss.vector.solutions.geo.GeoSynonym;
import dss.vector.solutions.geo.LocatedIn;
import dss.vector.solutions.geo.LocatedInException;
import dss.vector.solutions.geo.LocatedInQuery;
import dss.vector.solutions.geo.NoCompatibleTypesException;
import dss.vector.solutions.geo.SearchParameter;
import dss.vector.solutions.ontology.Term;
import dss.vector.solutions.ontology.TermQuery;
import dss.vector.solutions.query.QueryBuilder;
import dss.vector.solutions.query.SavedSearch;
import dss.vector.solutions.util.GeoEntityImporter;
import dss.vector.solutions.util.GeometryHelper;
import dss.vector.solutions.util.QueryUtil;

public abstract class GeoEntity extends GeoEntityBase implements com.runwaysdk.generation.loader.Reloadable
{
  private static final long serialVersionUID   = 1234288139462L;

  /**
   * The maximum number of children allowed for UI display under one parent node.
   */
  public static final int   OVERFLOW_THRESHOLD = 40;

  public GeoEntity()
  {
    super();
  }

  protected String buildKey()
  {
    return this.getGeoId();
  }

  @Override
  public void apply()
  {
    applyInternal();
  }

  public void directApply()
  {
    super.apply();
  }

  /**
   * Applies this GeoEntity and recursively sets the activated status of all children if the status
   * has changed on the parent.
   * 
   * @return
   * @throws ParseException
   */
  @Transaction
  private Set<String> applyInternal()
  {
    if ( ( this.getGeoId() == null ) || ( this.getGeoId().length() == 0 ))
    {
      boolean unique = false;
      String generatedGeoId = null;

      while (!unique)
      {
        generatedGeoId = LocalProperty.getNextId();

        try
        {
          searchByGeoId(generatedGeoId);
        }
        catch (InvalidIdException e)
        {
          unique = true;
        }
      }

      this.setGeoId(generatedGeoId);
    }

    GeometryHelper geometryHelper = new GeometryHelper();
    String geoData = this.getGeoData();
    if (geoData != null && geoData.length() > 0)
    {
      try
      {
        Geometry geo = geometryHelper.parseGeometry(geoData);
        this.setGeoPoint(geometryHelper.getGeoPoint(geo));
        this.setGeoMultiPolygon(geometryHelper.getGeoMultiPolygon(geo));

        // reset the geoData to the filtered WKT as parsed by JTS
        geoData = geo.toText();
        this.setGeoData(geoData);
      }
      catch (Exception e)
      {
        String msg = "Error parsing WKT";

        WKTParsingProblem p = new WKTParsingProblem(msg);
        p.setNotification(this, GEODATA);
        p.setReason(e.getLocalizedMessage());
        p.apply();
        p.throwIt();
      }

    }
    // allow new instances to set geoPoint and geoMultiPolygon directly because
    // the
    // values may have been set via the geo entity importer. Otherwise, null the
    // values
    // out if geoData is empty.
    else if (!this.isNew())
    {
      this.setGeoPoint(null);
      this.setGeoMultiPolygon(null);
    }

    Set<String> ids = new HashSet<String>();

    if (this.isModified(GeoEntity.ACTIVATED))
    {
      ids.addAll(setChildEntityActivated(this.getActivated(), this));

      ids.add(this.getId());
    }

    String entityLabel = this.getEntityLabel().getValue();
    if (entityLabel == null || entityLabel.length() == 0)
    {
      this.getEntityLabel().setValue(this.getGeoId());
    }

    super.apply();

    SavedSearch.updateSavedSearchIds(this);

    return ids;
  }

  /**
   * Updates this GeoEntity and its children if its activated attribute has been modified.
   * 
   * @return
   */
  @Override
  public String[] updateFromTree()
  {
    Set<String> ids = applyInternal();
    return ids.toArray(new String[ids.size()]);
  }

  public static GeoEntity searchByGeoId(String geoId)
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
  public static ValueQuery searchByEntityNameOrGeoId(String type, String name, Boolean enforceRoot)
  {
    QueryFactory f = new QueryFactory();

    ValueQuery valueQuery = new ValueQuery(f);
    MdBusinessQuery mdQ = new MdBusinessQuery(valueQuery);
    GeoEntityQuery q = new GeoEntityQuery(valueQuery);
    TermQuery tq = new TermQuery(valueQuery);

    SelectableChar orderBy = q.getEntityLabel().localize(GeoEntity.ENTITYLABEL);
    SelectablePrimitive[] selectables = new SelectablePrimitive[] { q.getId(GeoEntity.ID), orderBy, q.getGeoId(GeoEntity.GEOID), q.getType(GeoEntity.TYPE), mdQ.getDisplayLabel().localize(MdBusinessInfo.DISPLAY_LABEL), tq.getTermDisplayLabel().localize(GeoEntityView.MOSUBTYPE) };

    Condition[] conditions = new Condition[] { q.getType(GeoEntity.TYPE).EQ(type), F.CONCAT(mdQ.getPackageName(), F.CONCAT(".", mdQ.getTypeName())).EQ(q.getType()), q.getActivated().EQ(true) };

    if (enforceRoot)
    {
      DefaultGeoEntity defaultGeoEntity = DefaultGeoEntity.getDefaultGeoEntity();
      AllPathsQuery allQ = new AllPathsQuery(valueQuery);

      conditions = (Condition[]) ArrayUtils.addAll(conditions, new Condition[] { allQ.getChildGeoEntity().EQ(q), allQ.getParentGeoEntity().EQ(defaultGeoEntity.getGeoEntity()) });
    }

    LeftJoinEq[] joins = new LeftJoinEq[] { q.getTerm("geoTermId").LEFT_JOIN_EQ(tq.getId("termId")) };

    if (name != null && !name.equals(""))
    {
      String[] tokens = name.split(" ");
      SelectablePrimitive[] searchables = new SelectablePrimitive[] { orderBy, q.getGeoId(GeoEntity.GEOID) };

      QueryBuilder.textLookup(valueQuery, f, tokens, searchables, selectables, conditions, joins);
    }
    else
    {
      QueryBuilder.orderedLookup(valueQuery, f, orderBy, selectables, conditions, joins);
    }

    valueQuery.restrictRows(20, 1);

    return valueQuery;
  }

  @Transaction
  public static void validateByParameters(String geoId, String[] filter)
  {
    GeoEntity entity = GeoEntity.searchByGeoId(geoId);
    Set<String> universals = new TreeSet<String>();

    boolean political = Boolean.parseBoolean(filter[0]);
    boolean populated = Boolean.parseBoolean(filter[1]);
    boolean sprayTarget = Boolean.parseBoolean(filter[2]);
    boolean urban = Boolean.parseBoolean(filter[3]);

    SearchParameter parameter = new SearchParameter(political, sprayTarget, populated, urban, false, false);
    GeoHierarchyView[] views = GeoHierarchy.getHierarchies(parameter);

    for (GeoHierarchyView view : views)
    {
      universals.add(view.getGeneratedType());
    }

    for (int i = 3; i < filter.length; i++)
    {
      universals.add(filter[i].replace("*", ""));
    }

    String entityType = entity.getType();

    if (!universals.contains(entityType))
    {
      String msg = "The selected geo entity [" + entity.getLabel() + "] is not part of the given hierarchy";
      GeoEntitySelectionProblem p = new GeoEntitySelectionProblem(msg);
      p.setEntityLabel(entity.getLabel());
      p.apply();

      p.throwIt();
    }
  }

  @Transaction
  public static void validateByType(String geoId, String type)
  {
    GeoEntity entity = GeoEntity.searchByGeoId(geoId);

    if (!type.equalsIgnoreCase(entity.getType()))
    {
      String msg = "The selected geo entity [" + entity.getLabel() + "] must be of type [" + type + "]";
      GeoEntitySelectionProblem p = new GeoEntitySelectionProblem(msg);
      p.setEntityLabel(entity.getLabel());
      p.apply();

      p.throwIt();
    }
  }

  /**
   * 
   * @param value
   *          *
   * @param filter
   *          [0] = political
   * @param filter
   *          [1] = populated
   * @param filter
   *          [2] = spray Target
   * @param filter
   *          [3] = urban
   * @param filter
   *          [..] = extra universals
   * @return
   */
  /**
   * @param value
   * @param filter
   * @return
   */
  public static ValueQuery searchByParameters(String value, String[] filter, Boolean enforceRoot)
  {
    QueryFactory factory = new QueryFactory();

    ValueQuery valueQuery = new ValueQuery(factory);
    MdBusinessQuery mdQ = new MdBusinessQuery(valueQuery);
    GeoEntityQuery q = new GeoEntityQuery(valueQuery);
    TermQuery tq = new TermQuery(valueQuery);

    boolean political = Boolean.parseBoolean(filter[0]);
    boolean populated = Boolean.parseBoolean(filter[1]);
    boolean sprayTarget = Boolean.parseBoolean(filter[2]);
    boolean urban = Boolean.parseBoolean(filter[3]);

    SearchParameter parameter = new SearchParameter(political, sprayTarget, populated, urban, false, false);
    GeoHierarchyView[] views = GeoHierarchy.getHierarchies(parameter);

    SelectableChar orderBy = q.getEntityLabel().localize(GeoEntity.ENTITYLABEL);
    SelectablePrimitive[] selectables = new SelectablePrimitive[] { q.getId(GeoEntity.ID), orderBy, q.getGeoId(GeoEntity.GEOID), q.getType(GeoEntity.TYPE), mdQ.getDisplayLabel().localize(MdBusinessInfo.DISPLAY_LABEL), tq.getTermDisplayLabel().localize(GeoEntityView.MOSUBTYPE) };

    Condition condition = null;

    for (GeoHierarchyView view : views)
    {
      String type = view.getGeneratedType();

      if (condition == null)
      {
        condition = q.getType().EQ(type);
      }
      else
      {
        condition = OR.get(condition, q.getType().EQ(type));
      }
    }

    for (int i = 3; i < filter.length; i++)
    {
      String universal = filter[i].replace("*", "");

      if (condition == null)
      {
        condition = q.getType().EQ(universal);
      }
      else
      {
        condition = OR.get(condition, q.getType().EQ(universal));
      }
    }

    Condition[] conditions = new Condition[] { condition, F.CONCAT(mdQ.getPackageName(), F.CONCAT(".", mdQ.getTypeName())).EQ(q.getType()), q.getActivated().EQ(true) };

    if (enforceRoot)
    {
      DefaultGeoEntity defaultGeoEntity = DefaultGeoEntity.getDefaultGeoEntity();
      AllPathsQuery allQ = new AllPathsQuery(valueQuery);

      conditions = (Condition[]) ArrayUtils.addAll(conditions, new Condition[] { allQ.getChildGeoEntity().EQ(q), allQ.getParentGeoEntity().EQ(defaultGeoEntity.getGeoEntity()) });
    }

    LeftJoinEq[] joins = new LeftJoinEq[] { q.getTerm("geoTermId").LEFT_JOIN_EQ(tq.getId("termId")) };

    if (value != null && !value.equals(""))
    {
      String[] tokens = value.split(" ");
      SelectablePrimitive[] searchables = new SelectablePrimitive[] { orderBy, q.getGeoId(GeoEntity.GEOID) };

      QueryBuilder.textLookup(valueQuery, factory, tokens, searchables, selectables, conditions, joins);
    }
    else
    {
      QueryBuilder.orderedLookup(valueQuery, factory, orderBy, selectables, conditions, joins);
    }

    valueQuery.restrictRows(20, 1);

    return valueQuery;
  }

  /**
   * Throws an exception to alert the user before they change an entity's parent.
   */
  @Override
  public void confirmChangeParent(String parentId)
  {
    GeoEntity parent = GeoEntity.get(parentId);

    ConfirmParentChangeException ex = new ConfirmParentChangeException();
    ex.setEntityName(parent.getEntityLabel().getValue());

    throw ex;
  }

  /**
   * Throws an exception to alert the user before they try to delete an entity with more than one
   * parent. If the entity only has one parent, then entity is deleted as normal.
   */
  @Override
  public void confirmDeleteEntity(String parentId)
  {
    QueryFactory f = new QueryFactory();
    LocatedInQuery q = new LocatedInQuery(f);

    q.WHERE(q.childId().EQ(this.getId()));
    q.AND(q.parentId().NE(parentId));

    if (q.getCount() > 0)
    {
      GeoEntity parent = GeoEntity.get(parentId);
      ConfirmDeleteEntityException ex = new ConfirmDeleteEntityException();
      ex.setEntityName(parent.getEntityLabel().getValue());
      throw ex;
    }
    else
    {
      this.deleteEntity();
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
      while (iter.hasNext())
      {
        iter.next().delete();
      }
    }
    finally
    {
      iter.close();

      deleteEntityAndChildrenFromAllPaths(this.getId());
      updateAllPathForGeoEntity(this.getId(), null);
    }
  }
  
  private static final String TEMP_TABLE = "RUNWAY_ALLPATHS_GEO_MULTIPARENT_TEMP";
  private static final String TEMP_GEO_ID_COL = "objId";
  private static final String TEMP_PARENT_ID_COL = "parentId";
  private static final String TEMP_DEPTH_COL = "depth";
  private static final String INDEX_NAME = "RUNWAY_ALLPATHS_GEO_MULTIPARENT_TEMP_INDEX";
  private static final List<String> TEMP_TABLE_COLUMNS = Arrays.asList(
      TEMP_GEO_ID_COL + " " + Database.formatCharacterField(DatabaseProperties.getDatabaseType(MdAttributeCharacterInfo.CLASS), "64"),
      TEMP_PARENT_ID_COL + " " + Database.formatCharacterField(DatabaseProperties.getDatabaseType(MdAttributeCharacterInfo.CLASS), "64"),
      TEMP_DEPTH_COL + " " + DatabaseProperties.getDatabaseType(MdAttributeIntegerInfo.CLASS)
  );
  private static final List<String> TEMP_TABLE_ATTRS = Arrays.asList(
      MdAttributeCharacterInfo.CLASS, MdAttributeCharacterInfo.CLASS, MdAttributeIntegerInfo.CLASS
  );
  
  /**
   * Deletes the term and maintains allpaths integrity. May be a potentially expensive operation.
   * 
   * TODO: Multi-threading
   * TODO: At what point is it faster to rebuild the Allpaths table?
   * TODO: Add better support in Query API for managing tables so this temp table logic can be more cross DB
   */
  @Override
  @Transaction
  public void deleteEntity()
  {
    MdBusinessDAOIF allpathsMdBiz = MdBusinessDAO.getMdBusinessDAO(AllPaths.CLASS);
    String child_geoentity = allpathsMdBiz.definesAttribute(AllPaths.CHILDGEOENTITY).getColumnName();
    String allpaths_ontology = allpathsMdBiz.getTableName(); 
    
    // Count how many ancestors this geoentity has. This will be used for later calculations
    AllPathsQuery apq = new AllPathsQuery(new QueryFactory());
    apq.WHERE(apq.getChildGeoEntity().EQ(this));
    long delRootACount = apq.getCount() - 1;
    
    // Create us a temp table for storing multiple parents that need to be rebuilt on the post step.
    Database.createTempTable(TEMP_TABLE, TEMP_TABLE_COLUMNS, "DROP");
    Database.addNonUniqueIndex(TEMP_TABLE, TEMP_GEO_ID_COL, INDEX_NAME);
    
    // Depth first search because we're using a stack.
    Stack<GeoEntity> s = new Stack<GeoEntity>();
    s.push(this);
    
    stackLoop:
    while (!s.empty())
    {
      GeoEntity current = s.pop();
      
      // Push the first child
      OIterator<? extends Business> children = current.getChildren(LocatedIn.CLASS);
      try
      {
        // We're going to save on memory here by only pushing the first (unprocessed) child. When we loop back up to this node hopefully it will be deleted.
        childLoop:
        while (children.hasNext())
        {
          GeoEntity child = (GeoEntity) children.next();
          
          // If this child is in our temp table, then it has already been processed (and not deleted). We have to do this query here to prevent infinite loops.
          String allpathsAncestorsSql = Database.selectClause(Arrays.asList("count(*)"), Arrays.asList(allpaths_ontology), Arrays.asList(child_geoentity + " = '" + child.getId() + "'"));
          ResultSet resultSet = Database.selectFromWhere("count(*)", TEMP_TABLE, TEMP_GEO_ID_COL + " = '" + child.getId() + "' AND (" + allpathsAncestorsSql + ") > " + (2 + s.size() + delRootACount));
          try
          {
            if (resultSet.next())
            {
              int count = resultSet.getInt("count");
              
              if (count > 0)
              {
                continue childLoop;
              }
            }
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
          
          s.push(current);
          s.push(child);
          continue stackLoop;
        }
      }
      finally
      {
        children.close();
      }
      
      // Does this node have multiple parents?
      List<GeoEntity> parents = new ArrayList<GeoEntity>();
      QueryFactory f = new QueryFactory();
      LocatedInQuery q = new LocatedInQuery(f);
      q.WHERE(q.childId().EQ(current.getId()));
      OIterator<? extends LocatedIn> pRelIt = q.getIterator();
      try {
        while (pRelIt.hasNext())
        {
          parents.add(pRelIt.next().getParent());
        }
      }
      finally
      {
        pRelIt.close();
      }
      
      if (parents.size() == 1)
      {
        GeoEntity parent = parents.get(0);
        
        // Count how many ancestors this geoentity has.
        AllPathsQuery apq2 = new AllPathsQuery(new QueryFactory());
        apq2.WHERE(apq2.getChildGeoEntity().EQ(current));
        long ancestorCount = apq2.getCount() - 1;
        
        // If one of our ancestors has multiple parents 
        if (s.size() + delRootACount < ancestorCount)
        {
          insertIntoTemp(current.getId(), Arrays.asList(parent.getId()), s.size());
        }
        else
        {
          Database.deleteWhere(TEMP_TABLE, TEMP_GEO_ID_COL + " = '" + current.getId() + "' OR " + TEMP_PARENT_ID_COL + " = '" + current.getId() + "'");
          
          deleteLeafFromAllPaths(current.getId());
          current.delete(false);
        }
      }
      else // If I have multiple parents, add to list for post step
      {
        if (s.size() == 0)
        {
          deleteLeafFromAllPaths(current.getId());
          current.delete(false);
          break;
        }
        
        List<String> parentIds = new ArrayList<String>();
        for (GeoEntity parent : parents)
        {
          parentIds.add(parent.getId());
        }
        
        insertIntoTemp(current.getId(), parentIds, s.size());
      }
    }
    
    // Post step: since we destroyed terms with multiple parents those multiple parents (that aren't our children) must now be rebuilt.
    //   We have to do 2 loops here because we need two separate phases for deleting any still existing allpaths data and then rebuilding it.
    String selectSql = Database.selectClause(Arrays.asList(TEMP_GEO_ID_COL, TEMP_PARENT_ID_COL, TEMP_DEPTH_COL), Arrays.asList(TEMP_TABLE),  new ArrayList<String>());
    ResultSet resultSet = Database.query(selectSql + " ORDER BY " + TEMP_DEPTH_COL + " DESC");
    
    try
    {
      while (resultSet.next())
      {
        String termId = resultSet.getString(TEMP_GEO_ID_COL);
        
        deleteEntityFromAllPaths(termId);
      }
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
    
    // Post Step loop #2: Rebuild the terms with multiple parents.
    ResultSet resultSet2 = Database.query(selectSql + " ORDER BY " + TEMP_DEPTH_COL + " DESC");
    
    try
    {
      while (resultSet2.next())
      {
        String geoentityId = resultSet2.getString(TEMP_GEO_ID_COL);
        String parentId = resultSet2.getString(TEMP_PARENT_ID_COL);
        
        updateAllPathForGeoEntity(geoentityId, parentId);
      }
    }
    catch (SQLException sqlEx1)
    {
      Database.throwDatabaseException(sqlEx1);
    }
    finally
    {
      try
      {
        java.sql.Statement statement = resultSet2.getStatement();
        resultSet2.close();
        statement.close();
      }
      catch (SQLException sqlEx2)
      {
        Database.throwDatabaseException(sqlEx2);
      }
    }
    
    // We don't need to care about deleting the temp table because it drops on transaction and the transaction ends here.
  }
  
  private void insertIntoTemp(String geoentityId, List<String> parentIds, Integer depth)
  {
    List<PreparedStatement> statements = new ArrayList<PreparedStatement>();
    
    for (String parentId : parentIds)
    {
      List<String> bindVals = Arrays.asList("?","?","?");
      List<Object> vals = Arrays.asList(geoentityId, parentId, String.valueOf(depth));
      
      PreparedStatement preparedStmt = Database.buildPreparedSQLInsertStatement(TEMP_TABLE, Arrays.asList(TEMP_GEO_ID_COL, TEMP_PARENT_ID_COL, TEMP_DEPTH_COL), bindVals, vals, TEMP_TABLE_ATTRS);
      statements.add(preparedStmt);
    }
    
    Database.executeStatementBatch(statements);
  }

  /**
   * Deletes this GeoEntity.
   * 
   * WARNING: Does not maintain allpaths table!!
   */
   @Override
   public void delete()
   {
     delete(true);
   }
   
  @Transaction
  public void delete(boolean deleteChildren)
  {
    if (deleteChildren)
    {
      List<GeoEntity> children = this.getImmediateChildren();
  
      for (GeoEntity child : children)
      {
        if (child.hasSingleParent())
        {
          child.delete();
        }
      }
    }

    super.delete();
  }

  private boolean hasSingleParent()
  {
    QueryFactory f = new QueryFactory();
    LocatedInQuery q = new LocatedInQuery(f);

    q.WHERE(q.childId().EQ(this.getId()));

    return q.getCount() == 1;
  }

  /**
   * This GeoEntity is equal to the given object if the object is the same object reference or if
   * the ids match.
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
   * Searches for the GeoEntity with the given geoId and returns itself and its children and
   * parents.
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

  public static GeoEntityView[] collectAllLocatedInByGeoId(String geoId, Boolean includeParents, String filter)
  {
    try
    {
      GeoEntity entity = GeoEntity.searchByGeoId(geoId);

      return entity.collectAllLocatedIn(includeParents, filter);
    }
    catch (InvalidIdException e)
    {
      // DO NOTHING
    }

    return new GeoEntityView[0];
  }

  /**
   * Collects all children and parents (optional) for the located in relationship.
   */
  @Override
  public GeoEntityView[] collectAllLocatedIn(Boolean includeParents, String filter)
  {
    List<GeoEntityView> list = new LinkedList<GeoEntityView>();

    list.addAll(GeoEntityViewQuery.getImmediateChildren(this, filter));
    list.addAll(GeoEntityViewQuery.getAllParents(this, filter));

    Collections.sort(list, new GeoEntityViewSorter());

    return list.toArray(new GeoEntityView[list.size()]);
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
    view.setEntityLabel(this.getEntityLabel().getValue());
    view.setEntityType(this.getType());
    view.setGeoEntityId(this.getId());
    view.setGeoId(this.getGeoId());
    view.setTypeDisplayLabel(this.getTypeDisplayLabel());

    Term term = this.getTerm();
    if (term != null)
    {
      view.setMoSubType(term.getTermDisplayLabel().getValue());
    }

    return view;
  }

  @Override
  public String getTypeDisplayLabel()
  {
    String display;

    MdClass mdClass = MdClass.getMdClass(this.getType());
    display = mdClass.getDisplayLabel().getValue();

    String termId = this.getValue(GeoEntity.TERM);
    if (termId != null && termId.trim().length() > 0)
    {
      display += " : " + this.getTerm().getTermDisplayLabel().getValue();
    }

    return display;
  }

  public String getLabel()
  {
    String universal = this.getTypeDisplayLabel();
    String geoEntityName = this.getEntityLabel().getValue();

    return geoEntityName + " (" + universal + ") - " + this.getGeoId();
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

    QueryFactory factory = new QueryFactory();
    AllPathsQuery pathsQuery = new AllPathsQuery(factory);
    GeoEntityQuery entityQuery = new GeoEntityQuery(factory);

    pathsQuery.WHERE(pathsQuery.getChildGeoEntity().EQ(this));

    entityQuery.WHERE(entityQuery.getId().EQ(pathsQuery.getParentGeoEntity().getId()));

    OIterator<? extends GeoEntity> iterator = entityQuery.getIterator();

    try
    {
      while (iterator.hasNext())
      {
        allParents.add(iterator.next());
      }
    }
    finally
    {
      iterator.close();
    }

    return allParents;
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
   * @return All of this GeoEntity descendants which are of first GeoHierarchy which allows
   *         political areas
   */
  public GeoEntity[] getPoliticalChildren()
  {
    return this.getFamily(new SearchParameter(true, false, false, false, false, true));
  }

  /**
   * @return All of this Geo Entity ancestors which are political
   */
  public GeoEntity[] getPoliticalAncestors()
  {
    return this.getFamily(new SearchParameter(true, false, false, false, true, false));
  }

  /**
   * @return All of this Geo Entity decendants which are political
   */
  public GeoEntityQuery getPoliticalDecendants(QueryFactory factory)
  {
    return this.getFamily(factory, new SearchParameter(true, false, false, false, false, false));
  }

  /**
   * @return All of this Geo Entity decendants
   */
  public GeoEntityQuery getAllDecendants(QueryFactory factory)
  {
    return this.getFamily(factory, new SearchParameter(false, false, false, false, false, false));
  }

  /**
   * @return All of this Geo Entity decendants which can have population
   */
  public GeoEntityQuery getPopulationDecendants(QueryFactory factory)
  {
    return this.getFamily(factory, new SearchParameter(true, false, true, false, false, false));
  }

  public GeoEntity[] getImmediateSprayChildren()
  {
    return this.getSprayChildren();
  }

  /**
   * @return All of this GeoEntity descendants which are of first GeoHierarchy which allows spray
   *         areas
   */
  public GeoEntity[] getSprayChildren()
  {
    return this.getFamily(new SearchParameter(false, true, false, false, false, true));
  }

  /**
   * @return All of this Geo Entity ancestors which allow spray targets
   */
  public GeoEntity[] getSprayAncestors()
  {
    return this.getFamily(new SearchParameter(false, true, false, false, true, false));
  }

  /**
   * @return All of this GeoEntity descendants which are of first GeoHierarchy which allows both
   *         political and populated areas
   */
  public GeoEntity[] getPopulationChildren()
  {
    return this.getFamily(new SearchParameter(true, false, true, false, false, true));
  }

  /**
   * @return All of this Geo Entity ancestors which are both political and allow populated areas
   */
  public GeoEntity[] getPopulationAncestors()
  {
    return this.getFamily(new SearchParameter(true, false, true, false, true, false));
  }

  /**
   * @param parameter
   *          The search criteria
   * 
   * @return All of this Geo Entity ancestors or decendants which meet the search criteria
   */
  public GeoEntity[] getFamily(SearchParameter parameter)
  {
    GeoEntityQuery query = this.getFamily(new QueryFactory(), parameter);

    OIterator<? extends GeoEntity> it = query.getIterator();

    try
    {
      List<? extends GeoEntity> entities = it.getAll();

      return entities.toArray(new GeoEntity[entities.size()]);
    }
    finally
    {
      it.close();
    }
  }

  /**
   * @param parameter
   *          The search criteria
   * 
   * @return Query for all of this Geo Entity ancestors or decendants which meet the search criteria
   */
  public GeoEntityQuery getFamily(QueryFactory factory, SearchParameter parameter)
  {
    return parameter.getGeoEntityQuery(factory, this);
  }

  public List<GeoEntity> getFacilityChildren()
  {
    QueryFactory factory = new QueryFactory();

    AllPathsQuery pathsQuery = new AllPathsQuery(factory);
    GeoEntityQuery entityQuery = new GeoEntityQuery(factory);

    MdClass mdClass = MdClass.getMdClass(HealthFacility.CLASS);

    Condition condition = pathsQuery.getChildUniversal().EQ(mdClass);
    condition = AND.get(condition, pathsQuery.getParentGeoEntity().EQ(this));

    pathsQuery.WHERE(condition);

    entityQuery.WHERE(entityQuery.getId().EQ(pathsQuery.getChildGeoEntity().getId()));

    OIterator<? extends GeoEntity> it = entityQuery.getIterator();

    try
    {
      return new LinkedList<GeoEntity>(it.getAll());
    }
    finally
    {
      it.close();
    }
  }

  /**
   * Gets all children of a GeoEntity, but stops its breadth-first decent when it finds a child
   * which belongs to the given fully qualified types.
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
   * Gets all children of a GeoEntity, but stops its breadth-first decent when it finds a child
   * which belongs to the given fully qualified types.
   */
  public List<GeoEntity> getPrunedChildren(String... types)
  {
    return this.getPrunedChildren(Arrays.asList(types));
  }

  public GeoEntityQuery getPrunedChildren(QueryFactory factory, String... types)
  {
    return this.getPrunedChildren(Arrays.asList(types), factory);
  }

  /**
   * Gets all children of a GeoEntity, but stops its breadth-first decent when it finds a child
   * which belongs to the given fully qualified types.
   */
  public List<GeoEntity> getPrunedChildren(List<String> types)
  {
    QueryFactory factory = new QueryFactory();

    GeoEntityQuery geoEntityQuery = getPrunedChildren(types, factory);

    List<GeoEntity> list = new LinkedList<GeoEntity>(geoEntityQuery.getIterator().getAll());

    return list;
  }

  public GeoEntityQuery getPrunedChildren(List<String> types, QueryFactory factory)
  {
    List<Condition> conditions = new LinkedList<Condition>();
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
    return geoEntityQuery;
  }

  public List<GeoEntity> getPrunedParents(String... types)
  {
    return this.getPrunedParents(Arrays.asList(types));
  }

  public List<GeoEntity> getPrunedParents(GeoHierarchyView[] views)
  {
    String[] types = new String[views.length];

    for (int i = 0; i < views.length; i++)
    {
      types[i] = views[i].getGeneratedType();
    }

    return this.getPrunedParents(types);
  }

  public GeoEntityQuery getPrunedParents(QueryFactory factory, String... types)
  {
    return this.getPrunedParents(Arrays.asList(types), factory);
  }

  public List<GeoEntity> getPrunedParents(List<String> types)
  {
    QueryFactory factory = new QueryFactory();

    GeoEntityQuery geoEntityQuery = getPrunedParents(types, factory);

    List<GeoEntity> list = new LinkedList<GeoEntity>(geoEntityQuery.getIterator().getAll());

    return list;
  }

  private GeoEntityQuery getPrunedParents(List<String> types, QueryFactory factory)
  {
    List<Condition> conditions = new LinkedList<Condition>();
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
    return geoEntityQuery;
  }

  /**
   * Sets all children of the parent GeoEntity to the given activated status. If a child has more
   * than one parent then nothing is changed for that child.
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
   * Checks if this GoeEntity is eligible to have its active status changed. The general rule is as
   * follows: A child with more than one parent set to active cannot be deactivated. All other cases
   * are allowed.
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
   * Adds this GeoEntity as a child of the given parent for the {@link LocatedIn} relationship. If
   * this is not for a clone operation then all prior parent relationships will be removed.
   * 
   */
  @Override
  @Transaction
  public String[] applyWithParent(String parentGeoEntityId, Boolean cloneOperation, String oldParentId)
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
      String error = "The child [" + this.getEntityLabel().getValue() + "] cannot be its own parent.";

      LocatedInException e = new LocatedInException(error);
      e.setEntityName(this.getEntityLabel().getValue());
      e.setParentDisplayLabel(this.getEntityLabel().getValue());
      throw e;
    }

    if (!cloneOperation)
    {
      // Remove the old relationship on this GeoEntity and parent.
      QueryFactory f = new QueryFactory();
      LocatedInQuery q = new LocatedInQuery(f);

      q.WHERE(q.parentId().EQ(oldParentId));
      q.AND(q.childId().EQ(this.getId()));

      OIterator<? extends LocatedIn> iter = q.getIterator();

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
        String childDL = this.getEntityLabel().getValue();
        String parentDL = parent.getEntityLabel().getValue();

        String error = "The child [" + childDL + "] is already located in the parent [" + parentDL + "].";
        DuplicateParentException e = new DuplicateParentException(error);
        e.setChildEntityName(childDL);
        e.setParentEntityName(parentDL);

        throw e;
      }
    }

    this.addLocatedInGeoEntity(parent).applyWithoutCreatingAllPaths();
    
    // Update the allpaths table
    // There are 3 different contexts this method can be invoked in:
    if (cloneOperation) // 1) We're creating a new relationship with a new parent
    {
      copyTermFast(parentGeoEntityId, this.getId());
    }
    else if (!isNew) // 2) We're moving this node from one parent to another (delete and create relationship)
    {
      deleteEntityAndChildrenFromAllPaths(this.getId());
      updateAllPathForGeoEntity(this.getId(), null);
    }
    else if (!cloneOperation && isNew) // 3) This is a new GeoEntity so we're giving it it's first parent.
    {
      updateAllPathForGeoEntity(this.getId(), parent.getId());
    }

    // update this GeoEntity and all its
    // children with the parent's active status.
    boolean parentActivated = parent.getActivated();
    boolean childActivated = this.getActivated();

    if (!isNew && parentActivated != childActivated && this.eligibleForActiveChange(parentActivated))
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
  
  /**
   * Removes the term and all its children from the allpaths table.
   * 
   * @param termId
   */
  public static void deleteEntityAndChildrenFromAllPaths(String rootId)
  {
    // Queue results in a breadth first traverse
    Queue<String> qNext = new ArrayDeque<String>();
    qNext.offer(rootId);
    
    while (qNext.size() > 0)
    {
      String sCurrent = qNext.poll();
      GeoEntity tCurrent = GeoEntity.get(sCurrent);
      
      deleteEntityFromAllPaths(sCurrent);
      
      List<String> children = GeoEntity.getChildIds(tCurrent.getId());
      for (String child: children)
      {
        qNext.offer(child);
      }
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
      e.setEntityName(this.getEntityLabel().getValue());
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
  public GeoEntityView changeUniversalType(String newType)
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
            AttributeGeometryIF geoAttr = (AttributeGeometryIF) BusinessFacade.getAttribute(copy, attrName);
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

      return getView(copy.getId());
    }
    catch (Throwable e)
    {
      throw new ProgrammingErrorException(e);
    }
  }

  @Override
  public GeoEntityViewQuery getOrderedChildrenPage(String filter, Integer pageNumber)
  {

    QueryFactory f = new QueryFactory();
    OrderedGeoEntityQueryBuilder builder = new OrderedGeoEntityQueryBuilder(f, this, filter);
    GeoEntityViewQuery query = new GeoEntityViewQuery(f, builder);

    long count = query.getCount();
    query.restrictRows(OVERFLOW_THRESHOLD, pageNumber);

    if (pageNumber == 1 && count > OVERFLOW_THRESHOLD)
    {
      int pages = (int) Math.ceil(count / (double) OVERFLOW_THRESHOLD);
      int currentPage = 2;
      while (currentPage <= pages)
      {
        int start = ( ( currentPage - 1 ) * OVERFLOW_THRESHOLD ) + 1;
        int number = (int) ( currentPage < pages ? OVERFLOW_THRESHOLD - 1 : count - start );
        int end = ( start + number );

        ChildEntityOverflowInformation info = new ChildEntityOverflowInformation();
        info.setOverflowPage(currentPage);
        info.setOverflowStart(start);
        info.setOverflowEnd(end);
        info.setOverflowNumber(number);
        info.setOverflowThreshold(OVERFLOW_THRESHOLD);
        info.throwIt();

        currentPage++;
      }
    }

    return query;
  }

  /**
   * Returns a list of all LocatedIn children for which this GeoEntity is a parent. The list is
   * ordered by the entity name.
   */
  @Override
  public GeoEntityViewQuery getOrderedChildren(String filter)
  {
    return this.getOrderedChildrenPage(filter, 1);
  }

  private class OrderedGeoEntityQueryBuilder extends ViewQueryBuilder implements Reloadable
  {

    private GeoEntity       geoEntity;

    private GeoEntityQuery  geoEntityQuery;

    private LocatedInQuery  locatedInQuery;

    private MdBusinessQuery mdBusinessQuery;

    private TermQuery       termQuery;

    private String          filter;

    protected OrderedGeoEntityQueryBuilder(QueryFactory queryFactory, GeoEntity geoEntity, String filter)
    {
      super(queryFactory);

      this.filter = filter;
      this.geoEntity = geoEntity;
      this.geoEntityQuery = new GeoEntityQuery(queryFactory);
      this.locatedInQuery = new LocatedInQuery(queryFactory);
      this.mdBusinessQuery = new MdBusinessQuery(queryFactory);
      this.termQuery = new TermQuery(queryFactory);
    }

    @Override
    protected void buildSelectClause()
    {
      GeneratedViewQuery vQuery = this.getViewQuery();

      vQuery.map(GeoEntityView.GEOENTITYID, geoEntityQuery.getId());
      vQuery.map(GeoEntityView.GEOID, geoEntityQuery.getGeoId());
      vQuery.map(GeoEntityView.ACTIVATED, geoEntityQuery.getActivated());
      vQuery.map(GeoEntityView.ENTITYLABEL, geoEntityQuery.getEntityLabel().localize());
      vQuery.map(GeoEntityView.ENTITYTYPE, geoEntityQuery.getType());
      vQuery.map(GeoEntityView.TYPEDISPLAYLABEL, mdBusinessQuery.getDisplayLabel().localize());
      vQuery.map(GeoEntityView.MOSUBTYPE, termQuery.getTermDisplayLabel().localize());
    }

    @Override
    protected void buildWhereClause()
    {
      GeneratedViewQuery vQuery = this.getViewQuery();

      vQuery.WHERE(this.locatedInQuery.parentId().EQ(geoEntity.getId()));
      vQuery.AND(this.geoEntityQuery.locatedInGeoEntity(this.locatedInQuery));

      vQuery.AND(F.CONCAT(mdBusinessQuery.getPackageName(), F.CONCAT(".", mdBusinessQuery.getTypeName())).EQ(geoEntityQuery.getType()));

      // filter by type if possible (and all of type's child subclasses)
      if (filter != null && filter.trim().length() > 0)
      {
        String types[] = filteredTypes(filter);

        if (types.length > 0)
        {
          vQuery.AND(geoEntityQuery.getType().IN(types));
        }
      }

      // Restricted types to avoid returning large data sets
      String[] baseTypes = { MDSSInfo.GENERATED_GEO_PACKAGE + ".WaterBody", MDSSInfo.GENERATED_GEO_PACKAGE + ".Reserve", MDSSInfo.GENERATED_GEO_PACKAGE + ".River", MDSSInfo.GENERATED_GEO_PACKAGE + ".Road", MDSSInfo.GENERATED_GEO_PACKAGE + ".Railway" };

      // Grab all is_a children of the restricted types to add to
      // the restricted list.
      Set<String> notInSet = new HashSet<String>(Arrays.asList(baseTypes));
      for (String baseType : baseTypes)
      {
        try
        {
          MdBusiness baseMd = MdBusiness.getMdBusiness(baseType);
          MdBusinessDAOIF baseDAOIF = (MdBusinessDAOIF) BusinessFacade.getEntityDAO(baseMd);
          for (MdBusinessDAOIF subclass : baseDAOIF.getAllConcreteSubClasses())
          {
            notInSet.add(subclass.definesType());
          }
        }
        catch (DataNotFoundException e)
        {
          // The type doesn't exist for this country. Just ignore it
        }
      }

      vQuery.AND(geoEntityQuery.getType().NI(notInSet.toArray(new String[notInSet.size()])));

      vQuery.AND(geoEntityQuery.getTerm("geoTermId").LEFT_JOIN_EQ(termQuery.getId("termId")));

      vQuery.ORDER_BY_ASC(this.geoEntityQuery.getEntityLabel().localize());
    }
  }

  public static GeoEntityViewQuery getAsViews(String[] entities)
  {
    QueryFactory f = new QueryFactory();
    GeoEntityViewQuery q = new GeoEntityViewQuery(f, entities);
    return q;
  }

  /**
   * Given a filter (a GeoEntity class), this method returns all parents and children and the filter
   * type itself that's allowed in the hierarchy.
   * 
   * @param type
   * @return
   */
  private static String[] filteredTypes(String filter)
  {
    // get allowed types by filter, which includes all parents and children
    // of the filter type and the filter type itself.
    List<GeoHierarchy> allowedTypes = new LinkedList<GeoHierarchy>();

    if (JSONUtil.isArray(filter))
    {
      try
      {
        JSONArray typesArr = new JSONArray(filter);

        for (int i = 0; i < typesArr.length(); i++)
        {
          GeoHierarchy filterType = GeoHierarchy.getGeoHierarchyFromType(typesArr.getString(i));

          allowedTypes.addAll(filterType.getAllChildren());
          allowedTypes.add(filterType);
          allowedTypes.addAll(filterType.getAllParents());
        }
      }
      catch (JSONException e)
      {
        throw new ProgrammingErrorException(e);
      }
    }
    else
    {
      GeoHierarchy filterType = GeoHierarchy.getGeoHierarchyFromType(filter);

      allowedTypes.addAll(filterType.getAllChildren());
      allowedTypes.add(filterType);
      allowedTypes.addAll(filterType.getAllParents());
    }

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
      return geo1.getEntityLabel().compareTo(geo2.getEntityLabel());
    }
  }

  public class GeoEntitySorter implements Comparator<GeoEntity>, Reloadable
  {
    public int compare(GeoEntity geo1, GeoEntity geo2)
    {
      return geo1.getEntityLabel().getValue().compareTo(geo2.getEntityLabel().getValue());
    }
  }

  public static void updateAllPathForGeoEntity(String childId, String parentId)
  {
    updateAllPathForGeoEntity(childId, parentId, true, false, 0);
  }

  public static int updateAllPathForGeoEntityWithParent(String childId, String parentId, boolean copyChildren, boolean showTicker, int applyCount)
  {
    return updateAllPathForGeoEntity(childId, parentId, copyChildren, showTicker, applyCount);
  }

  public static int updateAllPathForGeoEntity(String childId, boolean copyChildren, boolean showTicker, int applyCount)
  {
    return updateAllPathForGeoEntity(childId, null, copyChildren, showTicker, applyCount);
  }

  @Transaction
  private static int updateAllPathForGeoEntity(String childId, String parentId, boolean copyChildren, boolean showTicker, int applyCount)
  {
    MdClassDAOIF childMdClassIF = MdClassDAO.getMdClassByRootId(IdParser.parseMdTypeRootIdFromId(childId));

    createPath(childId, childMdClassIF.getId(), childId, childMdClassIF.getId());

    if (showTicker)
    {
      applyCount = updateAllPathsTicker(applyCount);
    }

    // If an id of a parent is given, only build paths between this node, the
    // given parent
    // and that parent's parents. This is ideal for copies, so we don't have to
    // traverse
    // the paths of existing parents.
    List<String> parentIdList;
    if (parentId != null)
    {
      parentIdList = getRecursiveParentIds(parentId);
      parentIdList.add(0, parentId);
    }
    else
    {
      parentIdList = getRecursiveParentIds(childId);
    }

    for (String someParentId : parentIdList)
    {
      MdClassDAOIF parentMdClassIF = MdClassDAO.getMdClassByRootId(IdParser.parseMdTypeRootIdFromId(someParentId));
      createPath(someParentId, parentMdClassIF.getId(), childId, childMdClassIF.getId());
      if (showTicker)
      {
        applyCount = updateAllPathsTicker(applyCount);
      }
    }

    if (copyChildren)
    {
      // Update paths of the children.
      List<String> childOfChildIdList = getChildIds(childId);
      for (String childOfChild : childOfChildIdList)
      {
        if (parentId != null)
        {
          applyCount = updateAllPathForGeoEntity(childOfChild, childId, copyChildren, showTicker, applyCount);
        }
        else
        {
          applyCount = updateAllPathForGeoEntity(childOfChild, null, copyChildren, showTicker, applyCount);
        }
      }
    }

    return applyCount;
  }

  private static List<String> getRecursiveParentIds(String childId)
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
      parentIdList.addAll(getRecursiveParentIds(parentId));
    }

    return parentIdList;
  }

  @Transaction
  public static void buildAllPathsFast()
  {
    buildAllPathsFastInner();
  }

  public static void buildAllPathsFastInner()
  {
    MdBusiness allPathsMdBusiness = MdBusiness.getMdBusiness(AllPaths.CLASS);

    allPathsMdBusiness.deleteAllTableRecords();

    String original_child = "original_child";

    String geoEntityTable = MdBusiness.getMdBusiness(GeoEntity.CLASS).getTableName();
    String locatedInTable = MdRelationship.getMdElement(LocatedIn.CLASS).getTableName();
    String allPathsTable = allPathsMdBusiness.getTableName();
    String allPathsRootTypeId = IdParser.parseRootFromId(MdBusiness.getMdBusiness(AllPaths.CLASS).getId());
    String sitemaster = CommonProperties.getDomain();
    Date transactionDate = new Date();
    String createdById;
    SessionIF sessionIF = Session.getCurrentSession();
    if (sessionIF != null)
    {
      createdById = sessionIF.getUser().getId();
    }
    else
    {
      createdById = ServerConstants.SYSTEM_USER_ID;
    }

    String sql = "INSERT INTO " + allPathsTable + " (\n" + "  " + AllPaths.getIdMd().getMdAttributeConcrete().getColumnName() + ",\n" + "  " + AllPaths.getSiteMasterMd().getMdAttributeConcrete().getColumnName() + ",\n" + "  " + AllPaths.getKeyNameMd().getMdAttributeConcrete().getColumnName() + ",\n" + "  " + AllPaths.getTypeMd().getMdAttributeConcrete().getColumnName() + ",\n" + "  " + AllPaths.getEntityDomainMd().getMdAttributeConcrete().getColumnName() + ",\n" + "  "
        + AllPaths.getLastUpdateDateMd().getMdAttributeConcrete().getColumnName() + ",\n" + "  " + AllPaths.getSeqMd().getMdAttributeConcrete().getColumnName() + ",\n" + "  " + AllPaths.getCreatedByMd().getMdAttributeConcrete().getColumnName() + ",\n" + "  " + AllPaths.getLockedByMd().getMdAttributeConcrete().getColumnName() + ",\n" + "  " + AllPaths.getCreateDateMd().getMdAttributeConcrete().getColumnName() + ",\n" + "  " + AllPaths.getOwnerMd().getMdAttributeConcrete().getColumnName() + ",\n"
        + "  " + AllPaths.getLastUpdatedByMd().getMdAttributeConcrete().getColumnName() + ",\n" + "  " + AllPaths.getParentGeoEntityMd().getMdAttributeConcrete().getColumnName() + ",\n" + "  " + AllPaths.getParentUniversalMd().getMdAttributeConcrete().getColumnName() + ",\n" + "  " + AllPaths.getChildGeoEntityMd().getMdAttributeConcrete().getColumnName() + ",\n" + "  " + AllPaths.getChildUniversalMd().getMdAttributeConcrete().getColumnName() + "\n" + ") \n"

        + "WITH RECURSIVE quick_paths (" + original_child + ") AS (\n" + "    SELECT " + RelationshipDAOIF.CHILD_ID_COLUMN + " as " + original_child + ", " + RelationshipDAOIF.PARENT_ID_COLUMN + " FROM " + locatedInTable + "\n" + "    UNION\n" + "    SELECT " + original_child + ", l." + RelationshipDAOIF.PARENT_ID_COLUMN + "\n" + "    FROM " + locatedInTable + " l\n" + "      INNER JOIN quick_paths\n" + "      ON (l." + RelationshipDAOIF.CHILD_ID_COLUMN + " = quick_paths."
        + RelationshipDAOIF.PARENT_ID_COLUMN + ")\n" + "    )\n"

        + "SELECT  \n" + "    MD5(geo1." + GeoEntity.getIdMd().getMdAttributeConcrete().getColumnName() + " || geo2." + GeoEntity.getIdMd().getMdAttributeConcrete().getColumnName() + " ) || '" + allPathsRootTypeId + "' AS " + GeoEntity.getIdMd().getMdAttributeConcrete().getColumnName() + ",\n" + "    '" + sitemaster + "'  AS " + AllPaths.getSiteMasterMd().getMdAttributeConcrete().getColumnName() + ",\n" + "    MD5(geo1." + GeoEntity.getIdMd().getMdAttributeConcrete().getColumnName()
        + " || geo2." + GeoEntity.getIdMd().getMdAttributeConcrete().getColumnName() + " ) || '" + allPathsRootTypeId + "' AS " + GeoEntity.getKeyNameMd().getMdAttributeConcrete().getColumnName() + ",\n" + "    '" + AllPaths.CLASS + "' AS \"" + AllPaths.getTypeMd().getMdAttributeConcrete().getColumnName() + "\",\n" + "    '' AS " + AllPaths.getEntityDomainMd().getMdAttributeConcrete().getColumnName() + ",\n" + "    ? AS " + AllPaths.getLastUpdateDateMd().getMdAttributeConcrete().getColumnName()
        + ",\n" + "  NEXTVAL('" + PostgreSQL.OBJECT_UPDATE_SEQUENCE + "') AS " + AllPaths.getSeqMd().getMdAttributeConcrete().getColumnName() + ",\n" + "    '" + createdById + "'  AS " + AllPaths.getCreatedByMd().getMdAttributeConcrete().getColumnName() + ",\n" + "    NULL AS " + AllPaths.getLockedByMd().getMdAttributeConcrete().getColumnName() + ",\n" + "    ? AS " + AllPaths.getCreateDateMd().getMdAttributeConcrete().getColumnName() + ",\n" + "    '" + createdById + "' AS \""
        + AllPaths.getOwnerMd().getMdAttributeConcrete().getColumnName() + "\",\n" + "    '" + createdById + "' AS " + AllPaths.getLastUpdatedByMd().getMdAttributeConcrete().getColumnName() + ",\n" + "    paths." + RelationshipInfo.PARENT_ID + " AS " + AllPaths.getParentGeoEntityMd().getMdAttributeConcrete().getColumnName() + ", \n" + "    SUBSTRING(paths." + RelationshipInfo.PARENT_ID + "," + DatabaseInfo.ROOT_ID_SIZE + "+1," + DatabaseInfo.ROOT_ID_SIZE + ") || '"
        + MdBusinessInfo.ID_VALUE.substring(0, Integer.parseInt(DatabaseInfo.ROOT_ID_SIZE)) + "',\n" + "    paths." + original_child + " as " + AllPaths.getChildGeoEntityMd().getMdAttributeConcrete().getColumnName() + ", \n" + "    SUBSTRING(paths." + original_child + "," + DatabaseInfo.ROOT_ID_SIZE + "+1," + DatabaseInfo.ROOT_ID_SIZE + ")   || '" + MdBusinessInfo.ID_VALUE.substring(0, Integer.parseInt(DatabaseInfo.ROOT_ID_SIZE)) + "'\n" + "FROM " + geoEntityTable + " as geo1, "
        + geoEntityTable + " as geo2,\n" + "(SELECT " + original_child + ", parent_id FROM quick_paths UNION SELECT " + QueryUtil.getIdColumn() + "," + QueryUtil.getIdColumn() + " FROM " + geoEntityTable + " ) as paths\n" + "WHERE geo1." + GeoEntity.getIdMd().getMdAttributeConcrete().getColumnName() + " = paths." + RelationshipInfo.PARENT_ID + " AND geo2." + GeoEntity.getIdMd().getMdAttributeConcrete().getColumnName() + " = paths." + original_child + "\n";

    Connection conn = Database.getConnection();

    PreparedStatement prepared = null;

    try
    {
      prepared = conn.prepareStatement(sql);
      prepared.setTimestamp(1, new Timestamp(transactionDate.getTime()));
      prepared.setTimestamp(2, new Timestamp(transactionDate.getTime()));
      prepared.executeUpdate();
    }
    catch (SQLException e)
    {
      throw new ProgrammingErrorException(e);
    }
    finally
    {
      if (prepared != null)
      {
        try
        {
          prepared.close();
        }
        catch (SQLException e)
        {
          throw new ProgrammingErrorException(e);
        }
      }
    }
  }

  @Transaction
  public static void copyTermFast(String newParentGeoEntityId, String childGeoEntityId)
  {
    MdBusiness allPathsMdBusiness = MdBusiness.getMdBusiness(AllPaths.CLASS);

    String allPathsTable = allPathsMdBusiness.getTableName();
    String allPathsRootTypeId = IdParser.parseRootFromId(MdBusiness.getMdBusiness(AllPaths.CLASS).getId());
    String sitemaster = CommonProperties.getDomain();
    Date transactionDate = new Date();
    String createdById;
    SessionIF sessionIF = Session.getCurrentSession();
    if (sessionIF != null)
    {
      createdById = sessionIF.getUser().getId();
    }
    else
    {
      createdById = ServerConstants.SYSTEM_USER_ID;
    }

    String sql = "INSERT INTO " + allPathsTable + " (\n" + "  " + AllPaths.getIdMd().getMdAttributeConcrete().getColumnName() + ",\n" + "  " + AllPaths.getSiteMasterMd().getMdAttributeConcrete().getColumnName() + ",\n" + "  " + AllPaths.getKeyNameMd().getMdAttributeConcrete().getColumnName() + ",\n" + "  " + AllPaths.getTypeMd().getMdAttributeConcrete().getColumnName() + ",\n" + "  " + AllPaths.getEntityDomainMd().getMdAttributeConcrete().getColumnName() + ",\n" + "  "
        + AllPaths.getLastUpdateDateMd().getMdAttributeConcrete().getColumnName() + ",\n" + "  " + AllPaths.getSeqMd().getMdAttributeConcrete().getColumnName() + ",\n" + "  " + AllPaths.getCreatedByMd().getMdAttributeConcrete().getColumnName() + ",\n" + "  " + AllPaths.getLockedByMd().getMdAttributeConcrete().getColumnName() + ",\n" + "  " + AllPaths.getCreateDateMd().getMdAttributeConcrete().getColumnName() + ",\n" + "  " + AllPaths.getOwnerMd().getMdAttributeConcrete().getColumnName() + ",\n"
        + "  " + AllPaths.getLastUpdatedByMd().getMdAttributeConcrete().getColumnName() + ",\n" + "  " + AllPaths.getParentGeoEntityMd().getMdAttributeConcrete().getColumnName() + ",\n" + "  " + AllPaths.getParentUniversalMd().getMdAttributeConcrete().getColumnName() + ",\n" + "  " + AllPaths.getChildGeoEntityMd().getMdAttributeConcrete().getColumnName() + ",\n" + "  " + AllPaths.getChildUniversalMd().getMdAttributeConcrete().getColumnName() + "\n" + ") \n" + " SELECT \n"
        + "   MD5(allpaths_parent." + AllPaths.getParentGeoEntityMd().getMdAttributeConcrete().getColumnName() + " || allpaths_child." + AllPaths.getChildGeoEntityMd().getMdAttributeConcrete().getColumnName() + " ) || '" + allPathsRootTypeId + "' AS newId,\n" + "    '" + sitemaster + "'                                       AS " + AllPaths.getSiteMasterMd().getMdAttributeConcrete().getColumnName() + ",\n" + "   MD5(allpaths_parent."
        + AllPaths.getParentGeoEntityMd().getMdAttributeConcrete().getColumnName() + " || allpaths_child." + AllPaths.getChildGeoEntityMd().getMdAttributeConcrete().getColumnName() + " ) || '" + allPathsRootTypeId + "' AS newKey,\n" + "    '" + AllPaths.CLASS + "'                                   AS \"" + AllPaths.getTypeMd().getMdAttributeConcrete().getColumnName() + "\",\n" + "    ''                                                     AS "
        + AllPaths.getEntityDomainMd().getMdAttributeConcrete().getColumnName() + ",\n" + "    ?                                                      AS " + AllPaths.getLastUpdateDateMd().getMdAttributeConcrete().getColumnName() + ",\n" + "    NEXTVAL('" + PostgreSQL.OBJECT_UPDATE_SEQUENCE + "')    AS " + AllPaths.getSeqMd().getMdAttributeConcrete().getColumnName() + ",\n" + "    '" + createdById + "'                                      AS "
        + AllPaths.getCreatedByMd().getMdAttributeConcrete().getColumnName() + ",\n" + "    NULL                                                   AS " + AllPaths.getLockedByMd().getMdAttributeConcrete().getColumnName() + ",\n" + "    ?                                                      AS " + AllPaths.getCreatedByMd().getMdAttributeConcrete().getColumnName() + ",\n" + "    '" + createdById + "'                                      AS \""
        + AllPaths.getOwnerMd().getMdAttributeConcrete().getColumnName() + "\",\n" + "    '" + createdById + "'                                      AS " + AllPaths.getLastUpdatedByMd().getMdAttributeConcrete().getColumnName() + ",\n" + "    allpaths_parent." + AllPaths.getParentGeoEntityMd().getMdAttributeConcrete().getColumnName() + " AS " + AllPaths.getParentGeoEntityMd().getMdAttributeConcrete().getColumnName() + ", \n" + "    allpaths_parent."
        + AllPaths.getParentUniversalMd().getMdAttributeConcrete().getColumnName() + " AS " + AllPaths.getParentUniversalMd().getMdAttributeConcrete().getColumnName() + ", \n" + "    allpaths_child." + AllPaths.getChildGeoEntityMd().getMdAttributeConcrete().getColumnName() + "   AS " + AllPaths.getChildGeoEntityMd().getMdAttributeConcrete().getColumnName() + ", \n" + "    allpaths_child." + AllPaths.getChildUniversalMd().getMdAttributeConcrete().getColumnName() + "   AS "
        + AllPaths.getChildUniversalMd().getMdAttributeConcrete().getColumnName() + " \n"
        +

        " FROM \n"
        +
        // Fech all of the recursive children of the given child term, including
        // the child term itself.
        "  (SELECT " + AllPaths.getChildGeoEntityMd().getMdAttributeConcrete().getColumnName() + "," + AllPaths.getChildUniversalMd().getMdAttributeConcrete().getColumnName() + " \n" + "    FROM " + allPathsTable + " \n" + "     WHERE " + AllPaths.getParentGeoEntityMd().getMdAttributeConcrete().getColumnName() + " = '" + childGeoEntityId
        + "' ) AS allpaths_child, \n"
        +
        // Fech all of the recursive parents of the given new parent term,
        // including the new parent term itself.
        "  (SELECT " + AllPaths.getParentGeoEntityMd().getMdAttributeConcrete().getColumnName() + ", " + AllPaths.getParentUniversalMd().getMdAttributeConcrete().getColumnName() + " \n" + "     FROM " + allPathsTable + " \n" + "    WHERE " + AllPaths.getChildGeoEntityMd().getMdAttributeConcrete().getColumnName() + " = '" + newParentGeoEntityId + "' \n"
        + "    ) AS allpaths_parent \n"
        +
        // Since a term can have multiple parents, a path to one of the new
        // parent's parents may already exist
        " WHERE allpaths_parent." + AllPaths.getParentGeoEntityMd().getMdAttributeConcrete().getColumnName() + " NOT IN \n" + "   (SELECT " + AllPaths.getParentGeoEntityMd().getMdAttributeConcrete().getColumnName() + " \n" + "      FROM " + allPathsTable + " \n" + "     WHERE " + AllPaths.getParentGeoEntityMd().getMdAttributeConcrete().getColumnName() + " = allpaths_parent." + AllPaths.getParentGeoEntityMd().getMdAttributeConcrete().getColumnName() + " \n" + "      AND "
        + AllPaths.getChildGeoEntityMd().getMdAttributeConcrete().getColumnName() + " = allpaths_child." + AllPaths.getChildGeoEntityMd().getMdAttributeConcrete().getColumnName() + ") \n";

    Connection conn = Database.getConnection();

    PreparedStatement prepared = null;

    try
    {
      prepared = conn.prepareStatement(sql);
      prepared.setTimestamp(1, new Timestamp(transactionDate.getTime()));
      prepared.setTimestamp(2, new Timestamp(transactionDate.getTime()));
      prepared.executeUpdate();
    }
    catch (SQLException e)
    {
      throw new ProgrammingErrorException(e);
    }
    finally
    {
      if (prepared != null)
      {
        try
        {
          prepared.close();
        }
        catch (SQLException e)
        {
          throw new ProgrammingErrorException(e);
        }
      }
    }

  }

  private static List<String> getChildIds(String parentId)
  {
    QueryFactory queryFactory = new QueryFactory();

    LocatedInQuery locatedInQuery = new LocatedInQuery(queryFactory);

    ValueQuery valueQuery = new ValueQuery(queryFactory);

    valueQuery.SELECT(locatedInQuery.childId(RelationshipInfo.CHILD_ID));
    valueQuery.WHERE(locatedInQuery.parentId().EQ(parentId));

    List<ValueObject> valueObjectList = valueQuery.getIterator().getAll();

    List<String> childOfChildIdList = new LinkedList<String>();

    for (ValueObject valueObject : valueObjectList)
    {
      String childId = valueObject.getValue(RelationshipInfo.CHILD_ID);
      childOfChildIdList.add(childId);
    }

    return childOfChildIdList;
  }

  
  
  public static void createPath(String parentId, String parentMdBusiness, String childId, String childMdBusiness)
  {
    // create save point
    Savepoint savepoint = Database.setSavepoint();

    try
    {
      AllPaths allPaths = new AllPaths();
      allPaths.setValue(AllPaths.PARENTGEOENTITY, parentId);
      allPaths.setValue(AllPaths.PARENTUNIVERSAL, parentMdBusiness);
      allPaths.setValue(AllPaths.CHILDGEOENTITY, childId);
      allPaths.setValue(AllPaths.CHILDUNIVERSAL, childMdBusiness);
      allPaths.apply();
      
      // Performing this via insert statement and bypassing the validation in the object API is 15% faster
//      MdBusinessDAO mdBiz = MdBusinessDAO.getMdBusinessDAO(AllPaths.CLASS).getBusinessDAO();
//      String allPathsTbl = mdBiz.getTableName();
//      List<String> tableAttrs = Arrays.asList( MdAttributeCharacterInfo.CLASS, MdAttributeCharacterInfo.CLASS, MdAttributeCharacterInfo.CLASS, MdAttributeCharacterInfo.CLASS, MdAttributeCharacterInfo.CLASS );
//      List<String> columns = Arrays.asList(mdBiz.definesAttribute(AllPaths.ID).getColumnName(), mdBiz.definesAttribute(AllPaths.PARENTGEOENTITY).getColumnName(), mdBiz.definesAttribute(AllPaths.PARENTUNIVERSAL).getColumnName(), mdBiz.definesAttribute(AllPaths.CHILDGEOENTITY).getColumnName(), mdBiz.definesAttribute(AllPaths.CHILDUNIVERSAL).getColumnName());
//      
//      List<PreparedStatement> statements = new ArrayList<PreparedStatement>();
//      
//      List<String> bindVals = Arrays.asList("?","?","?", "?", "?");
//      List<Object> vals = Arrays.asList(parentId, parentMdBusiness, childId, childMdBusiness);
//      
//      PreparedStatement preparedStmt = Database.buildPreparedSQLInsertStatement(allPathsTbl, columns, bindVals, vals, tableAttrs);
//      statements.add(preparedStmt);
//      
//      Database.executeStatementBatch(statements);
    }
    catch (DuplicateDataDatabaseException ex)
    {
      // This might happen. Relationship already exists.
      Database.rollbackSavepoint(savepoint);
    }
    catch (RuntimeException ex)
    {
      Database.rollbackSavepoint(savepoint);
      throw ex;
    }
    finally
    {
      Database.releaseSavepoint(savepoint);
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

  @Transaction
  public void addSynonym(String synonymEntityName)
  {
    GeoSynonym geoSynonym = new GeoSynonym();
    geoSynonym.setEntityName(synonymEntityName);
    geoSynonym.apply();

    this.addSynonyms(geoSynonym).apply();
  }

  @Override
  public String toString()
  {
    return getLabel();
  }

  /**
   * Returns all attributes that reference the GeoEntity class.
   * 
   * @param className
   * @return
   */
  public static String[] getGeoAttributes(String className)
  {
    MdBusinessDAOIF md = MdBusinessDAO.getMdBusinessDAO(className);

    List<String> list = new LinkedList<String>();

    for (MdAttributeConcreteDAOIF mdAttr : md.definesAttributes())
    {
      if (mdAttr instanceof MdAttributeReferenceDAOIF)
      {
        MdAttributeReferenceDAOIF mdAttributeReference = (MdAttributeReferenceDAOIF) mdAttr;

        String reference = mdAttributeReference.javaType(false);

        try
        {
          if (GeoEntity.class.isAssignableFrom(Class.forName(reference)))
          {
            list.add(mdAttributeReference.definesAttribute());
          }
        }
        catch (ClassNotFoundException e)
        {
        }
      }
    }

    return list.toArray(new String[list.size()]);
  }

  /**
   * A single leaf node has no children and has one or fewer parents.
   * 
   * @return true if a single leaf node, false otherwise.
   */
  public boolean isSingleLeafNode()
  {
    // It is a leaf node if the term has no children and only has one parent.
    if (this.getAllContainsGeoEntity().getAll().size() == 0 && this.getAllLocatedInGeoEntity().getAll().size() <= 1)
    {
      return true;
    }
    else
    {
      return false;
    }
  }

  /**
   * Precondition: Assumes the given term is a leaf node!!!
   */
  public static void deleteLeafFromAllPaths(String leafTermId)
  {
    MdBusiness mdBusinessAllPaths = MdBusiness.getMdBusiness(AllPaths.CLASS);

    String tableName = mdBusinessAllPaths.getTableName();

    String childGeoEntityColumn = QueryUtil.getColumnName(AllPaths.getChildGeoEntityMd());

    String procCallString = "DELETE FROM " + tableName + " WHERE " + childGeoEntityColumn + " = ?";

    Connection conn = Database.getConnection();
    CallableStatement procCall = null;

    try
    {
      procCall = conn.prepareCall(procCallString);
      procCall.setString(1, leafTermId);
      procCall.execute();
    }
    catch (SQLException e)
    {
      throw new ProgrammingErrorException(e);
    }
    finally
    {
      if (procCall != null)
      {
        try
        {
          procCall.close();
        }
        catch (SQLException e2)
        {
          throw new ProgrammingErrorException(e2);
        }
      }
    }
  }

  /**
   * Removes all AllPaths entries where the given term is a parent or child.
   * 
   * @param termId
   */
  public static void deleteEntityFromAllPaths(String entityId)
  {
    MdBusiness mdBusinessAllPaths = MdBusiness.getMdBusiness(AllPaths.CLASS);

    String tableName = mdBusinessAllPaths.getTableName();

    String childColumn = QueryUtil.getColumnName(AllPaths.getChildGeoEntityMd());
    String parentColumn = QueryUtil.getColumnName(AllPaths.getParentGeoEntityMd());

    String procCallString = "DELETE FROM " + tableName + " WHERE " + childColumn + " = ? " + " OR " + parentColumn + " = ?";

    Connection conn = Database.getConnection();
    CallableStatement procCall = null;

    try
    {
      procCall = conn.prepareCall(procCallString);
      procCall.setString(1, entityId);
      procCall.setString(2, entityId);
      procCall.execute();
    }
    catch (SQLException e)
    {
      throw new ProgrammingErrorException(e);
    }
    finally
    {
      if (procCall != null)
      {
        try
        {
          procCall.close();
        }
        catch (SQLException e2)
        {
          throw new ProgrammingErrorException(e2);
        }
      }
    }
  }

  /**
   * Checks if the list of children are located_in the parents. This method returns a list of ids
   * that are valid children and sets an MdWarning on the call for all entities that were not
   * children.
   * 
   * @param childIds
   * @param parentIds
   * @return
   */
  public static String[] isChildOfParents(String childIds[], String[] parentIds)
  {
    QueryFactory f = new QueryFactory();
    ValueQuery v = new ValueQuery(f);

    AllPathsQuery ap = new AllPathsQuery(f);

    v.SELECT(ap.getChildGeoEntity("childId"));

    v.WHERE(ap.getChildGeoEntity().IN(childIds));
    v.AND(ap.getParentGeoEntity().IN(parentIds));

    OIterator<? extends ValueObject> iter = v.getIterator();
    Set<String> isChild = new HashSet<String>();

    try
    {
      while (iter.hasNext())
      {
        isChild.add(iter.next().getValue("childId"));
      }
    }
    finally
    {
      iter.close();
    }

    // Find which entities were not children of the parents
    Set<String> originalIds = new HashSet<String>(Arrays.asList(childIds));
    originalIds.removeAll(isChild);

    if (originalIds.size() > 0)
    {
      GeoEntityViewQuery query = GeoEntity.getAsViews(originalIds.toArray(new String[originalIds.size()]));
      OIterator<? extends GeoEntityView> iter2 = query.getIterator();
      List<String> displays = new LinkedList<String>();
      try
      {
        while (iter2.hasNext())
        {
          displays.add(iter2.next().getEntityLabel()); // TODO include
          // full
          // display (or build on
          // client)
        }
      }
      finally
      {
        iter2.close();
      }

      EntityNotChildOfMainWarning warning = new EntityNotChildOfMainWarning();
      warning.setEntities(StringUtils.join(displays, ","));
      warning.throwIt();
    }

    return isChild.toArray(new String[isChild.size()]);
  }
}