package dss.vector.solutions.geo.generated;

import java.lang.reflect.Constructor;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Savepoint;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import com.terraframe.mojo.business.Business;
import com.terraframe.mojo.business.BusinessFacade;
import com.terraframe.mojo.constants.DatabaseInfo;
import com.terraframe.mojo.constants.MdBusinessInfo;
import com.terraframe.mojo.constants.RelationshipInfo;
import com.terraframe.mojo.constants.ServerConstants;
import com.terraframe.mojo.constants.ServerProperties;
import com.terraframe.mojo.dataaccess.InvalidIdException;
import com.terraframe.mojo.dataaccess.MdAttributeConcreteDAOIF;
import com.terraframe.mojo.dataaccess.MdAttributeReferenceDAOIF;
import com.terraframe.mojo.dataaccess.MdBusinessDAOIF;
import com.terraframe.mojo.dataaccess.MdClassDAOIF;
import com.terraframe.mojo.dataaccess.ProgrammingErrorException;
import com.terraframe.mojo.dataaccess.ValueObject;
import com.terraframe.mojo.dataaccess.cache.DataNotFoundException;
import com.terraframe.mojo.dataaccess.database.Database;
import com.terraframe.mojo.dataaccess.database.DuplicateDataDatabaseException;
import com.terraframe.mojo.dataaccess.database.general.PostgreSQL;
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
import com.terraframe.mojo.query.SelectablePrimitive;
import com.terraframe.mojo.query.ValueQuery;
import com.terraframe.mojo.query.ViewQueryBuilder;
import com.terraframe.mojo.session.Session;
import com.terraframe.mojo.session.SessionIF;
import com.terraframe.mojo.system.metadata.MdBusiness;
import com.terraframe.mojo.system.metadata.MdBusinessQuery;
import com.terraframe.mojo.system.metadata.MdClass;
import com.terraframe.mojo.system.metadata.MdRelationship;
import com.terraframe.mojo.util.IdParser;
import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.io.ParseException;
import com.vividsolutions.jts.io.WKTReader;

import dss.vector.solutions.MDSSInfo;
import dss.vector.solutions.Property;
import dss.vector.solutions.PropertyInfo;
import dss.vector.solutions.geo.AllPaths;
import dss.vector.solutions.geo.AllPathsQuery;
import dss.vector.solutions.geo.ConfirmDeleteEntityException;
import dss.vector.solutions.geo.ConfirmParentChangeException;
import dss.vector.solutions.geo.DuplicateParentException;
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
import dss.vector.solutions.util.GeoEntityImporter;
import dss.vector.solutions.util.GeometryHelper;
import dss.vector.solutions.util.MDSSProperties;

public abstract class GeoEntity extends GeoEntityBase implements com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1234288139462L;

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

  /**
   * Applies this GeoEntity and recursively sets the activated status of all
   * children if the status has changed on the parent.
   *
   * @return
   * @throws ParseException
   */
  @Transaction
  private Set<String> applyInternal()
  {
    GeometryHelper geometryHelper = new GeometryHelper();
    WKTReader r = new WKTReader();
    String geoData = this.getGeoData();
    if(geoData != null && geoData.length() > 0)
    {
      Geometry geo;
      try
      {
        geo = r.read(geoData);
      }
      catch (ParseException e)
      {
        throw new ProgrammingErrorException(e);
      }

      this.setGeoPoint(geometryHelper.getGeoPoint(geo));
      this.setGeoMultiPolygon(geometryHelper.getGeoMultiPolygon(geo));
    }
    // allow new instances to set geoPoint and geoMultiPolygon directly because the
    // values may have been set via the geo entity importer. Otherwise, null the values
    // out if geoData is empty.
    else if(!this.isNew())
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
  public static ValueQuery searchByEntityName(String type, String name)
  {
    QueryFactory f = new QueryFactory();

    MdBusinessQuery mdQ = new MdBusinessQuery(f);
    GeoEntityQuery q;
    TermQuery tq = new TermQuery(f);
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

    Selectable[] selectables = new Selectable[] { q.getId(GeoEntity.ID), q.getEntityName(GeoEntity.ENTITYNAME), q.getGeoId(GeoEntity.GEOID),
          mdQ.getDisplayLabel().currentLocale(MdBusinessInfo.DISPLAY_LABEL), tq.getName(GeoEntityView.MOSUBTYPE)};
    valueQuery.SELECT(selectables);

    String searchable = name + "%";
    valueQuery.WHERE(q.getEntityName(GeoEntity.ENTITYNAME).LIKEi(searchable));
    valueQuery.AND(F.CONCAT(mdQ.getPackageName(), F.CONCAT(".", mdQ.getTypeName())).EQ(q.getType()));
    valueQuery.AND(q.getTerm("geoTermId").LEFT_JOIN_EQ(tq.getId("termId")));

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

    MdBusinessQuery mdQ = new MdBusinessQuery(f);
    GeoEntityQuery q;
    TermQuery tq = new TermQuery(f);
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

    Selectable[] selectables = new Selectable[] { q.getId(GeoEntity.ID), q.getEntityName(GeoEntity.ENTITYNAME),
        q.getGeoId(GeoEntity.GEOID), q.getType(GeoEntity.TYPE),
        mdQ.getDisplayLabel().currentLocale(MdBusinessInfo.DISPLAY_LABEL), tq.getName(GeoEntityView.MOSUBTYPE) };

    valueQuery.SELECT(selectables);

    String searchable = name.replace(" ", "% ") + "%";

    Condition or = OR.get(q.getEntityName(GeoEntity.ENTITYNAME).LIKEi(searchable), q.getGeoId().LIKEi(searchable));

    valueQuery.WHERE(or);
    valueQuery.AND(F.CONCAT(mdQ.getPackageName(), F.CONCAT(".", mdQ.getTypeName())).EQ(q.getType()));
    valueQuery.AND(q.getTerm("geoTermId").LEFT_JOIN_EQ(tq.getId("termId")));
    valueQuery.ORDER_BY_ASC((SelectablePrimitive) valueQuery.getSelectable(GeoEntity.ENTITYNAME));

    valueQuery.restrictRows(20, 1);

//    String sql = valueQuery.getSQL();
//    System.out.println(sql);

    return valueQuery;
  }
  
  /**
   * 
   * @param value   * 
   * @param filter[0] = political
   * @param filter[1] = populated
   * @param filter[2] = spray Target
   * @param filter[..] = extra universals
   * @return
   */
  /**
   * @param value
   * @param filter
   * @return
   */
  public static ValueQuery searchByParameters(String value, String[] filter)
  {
    QueryFactory factory = new QueryFactory();
    
    ValueQuery valueQuery = new ValueQuery(factory);    
    MdBusinessQuery mdQ = new MdBusinessQuery(valueQuery);
    GeoEntityQuery q = new GeoEntityQuery(valueQuery);
    TermQuery tq = new TermQuery(valueQuery);
    
    boolean political = Boolean.parseBoolean(filter[0]);
    boolean populated = Boolean.parseBoolean(filter[1]);
    boolean sprayTarget = Boolean.parseBoolean(filter[2]);
    
    SearchParameter parameter = new SearchParameter(political, sprayTarget, populated, false, false);    
    GeoHierarchyView[] views = GeoHierarchy.getHierarchies(parameter);
    
    SelectablePrimitive[] selectables = new SelectablePrimitive[] { 
        q.getId(GeoEntity.ID),
        q.getEntityName(GeoEntity.ENTITYNAME),
        q.getGeoId(GeoEntity.GEOID),
        q.getType(GeoEntity.TYPE),
        mdQ.getDisplayLabel().currentLocale(MdBusinessInfo.DISPLAY_LABEL),
        tq.getName(GeoEntityView.MOSUBTYPE)
    };
    
    Condition condition = null;
    
    for(GeoHierarchyView view : views)
    {
      String type = view.getGeneratedType();
      
      if(condition == null)
      {
        condition = q.getType().EQ(type);        
      }
      else
      {
        condition = OR.get(condition, q.getType().EQ(type));        
      }
    }
    
    for(int i = 3; i < filter.length; i++)
    {      
      String universal = filter[i].replace("*", "");
      
      if(condition == null)
      {
        condition = q.getType().EQ(universal);        
      }
      else
      {
        condition = OR.get(condition, q.getType().EQ(universal));        
      }      
    }

//    String[] searchable = value.split(" ");
//    
//    Condition[] conditions = new Condition[] {condition, F.CONCAT(mdQ.getPackageName(), F.CONCAT(".", mdQ.getTypeName())).EQ(q.getType())};
//    LeftJoinEq[] joins = new LeftJoinEq[] {q.getTerm("geoTermId").LEFT_JOIN_EQ(tq.getId("termId"))};   
//    
//    QueryBuilder.textLookup(valueQuery, factory, searchable, selectables, conditions, joins);
    
    valueQuery.SELECT(selectables);
    String searchable = value.replace(" ", "% ") + "%";
    Condition or = OR.get(condition, q.getEntityName(GeoEntity.ENTITYNAME).LIKEi(searchable), q.getGeoId().LIKEi(searchable));
    valueQuery.WHERE(or);
    valueQuery.AND(F.CONCAT(mdQ.getPackageName(), F.CONCAT(".", mdQ.getTypeName())).EQ(q.getType()));
    valueQuery.AND(q.getTerm("geoTermId").LEFT_JOIN_EQ(tq.getId("termId")));
    valueQuery.ORDER_BY_ASC((SelectablePrimitive) valueQuery.getSelectable(GeoEntity.ENTITYNAME));
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
   * with more than one parent. If the entity only has one parent, then entity
   * is deleted as normal.
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
       ex.setEntityName(parent.getEntityName());
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
      
      buildAllPathsFast();
    }
  }
  
  @Override
  @Transaction
  public void deleteEntity()
  {
    if(this.isSingleLeafNode())
    {
      deleteLeafFromAllPaths(this.getId());
      
      this.delete();
    }
    else
    {
      MdBusiness mdBusiness = MdBusiness.getMdBusiness(AllPaths.CLASS);
      mdBusiness.deleteAllTableRecords();
      
      this.delete();
      
      buildAllPathsFast();
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
      if(child.hasSingleParent())
      {
        child.delete();
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
    view.setTypeDisplayLabel(this.getTypeDisplayLabel());

    Term term = this.getTerm();
    if(term != null)
    {
      view.setMoSubType(term.getDisplay());
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
    if(termId != null && termId.trim().length() > 0)
    {
      display += " : "+this.getTerm().getDisplay();
    }

    return display;
  }

  public String getLabel()
  {
    String universal = this.getTypeDisplayLabel();
    String geoEntityName = this.getEntityName();

    return geoEntityName + " (" + universal + ")";
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
   * @return All of this GeoEntity descendants which are of first GeoHierarchy
   *         which allows political areas
   */
  public GeoEntity[] getPoliticalChildren()
  {
    return this.getFamily(new SearchParameter(true, false, false, false, true));
  }

  /**
   * @return All of this Geo Entity ancestors which are political
   */
  public GeoEntity[] getPoliticalAncestors()
  {
    return this.getFamily(new SearchParameter(true, false, false, true, false));
  }

  /**
   * @return All of this Geo Entity decendants which are political
   */
  public GeoEntityQuery getPoliticalDecendants(QueryFactory factory)
  {
    return this.getFamily(factory, new SearchParameter(true, false, false, false, false));
  }


  /**
   * @return All of this Geo Entity decendants which can have population
   */
  public GeoEntityQuery getPopulationDecendants(QueryFactory factory)
  {
    return this.getFamily(factory, new SearchParameter(true, false, true, false, false));
  }

  public GeoEntity[] getImmediateSprayChildren()
  {
    return this.getSprayChildren();
  }

  /**
   * @return All of this GeoEntity descendants which are of first GeoHierarchy
   *         which allows spray areas
   */
  public GeoEntity[] getSprayChildren()
  {
    return this.getFamily(new SearchParameter(false, true, false, false, true));
  }

  /**
   * @return All of this Geo Entity ancestors which allow spray targets
   */
  public GeoEntity[] getSprayAncestors()
  {
    return this.getFamily(new SearchParameter(false, true, false, true, false));
  }

  /**
   * @return All of this GeoEntity descendants which are of first GeoHierarchy
   *         which allows both political and populated areas
   */
  public GeoEntity[] getPopulationChildren()
  {
    return this.getFamily(new SearchParameter(true, false, true, false, true));
  }

  /**
   * @return All of this Geo Entity ancestors which are both political and allow
   *         populated areas
   */
  public GeoEntity[] getPopulationAncestors()
  {
    return this.getFamily(new SearchParameter(true, false, true, true, false));
  }

  /**
   * @param parameter
   *          The search criteria
   *
   * @return All of this Geo Entity ancestors or decendants which meet the
   *         search criteria
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
   * @return Query for all of this Geo Entity ancestors or decendants which meet
   *         the search criteria
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

  public GeoEntityQuery getPrunedChildren(QueryFactory factory, String... types)
  {
    return this.getPrunedChildren(Arrays.asList(types), factory);
  }

  /**
   * Gets all children of a GeoEntity, but stops its breadth-first decent when
   * it finds a child which belongs to the given fully qualified types.
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
      String error = "The child [" + this.getEntityName() + "] cannot be its own parent.";

      LocatedInException e = new LocatedInException(error);
      e.setEntityName(this.getEntityName());
      e.setParentDisplayLabel(this.getEntityName());
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

        String error = "The child [" + childDL + "] is already located in the parent [" + parentDL + "].";
        DuplicateParentException e = new DuplicateParentException(error);
        e.setChildEntityName(childDL);
        e.setParentEntityName(parentDL);

        throw e;
      }
    }

    this.addLocatedInGeoEntity(parent).apply();

    if(cloneOperation)
    {
      copyTermFast(parentGeoEntityId, this.getId());
    }
    else if(!isNew)
    {
      buildAllPathsFast();
    }
    
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

    private TermQuery termQuery;

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
      vQuery.map(GeoEntityView.ENTITYNAME, geoEntityQuery.getEntityName());
      vQuery.map(GeoEntityView.ENTITYTYPE, geoEntityQuery.getType());
      vQuery.map(GeoEntityView.TYPEDISPLAYLABEL, mdBusinessQuery.getDisplayLabel().currentLocale());
//      vQuery.map(GeoEntityView.MOSUBTYPE, termQuery.getName());
      vQuery.map(GeoEntityView.MOSUBTYPE, termQuery.getDisplay());
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
        vQuery.AND(geoEntityQuery.getType().IN(types));
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
        catch(DataNotFoundException e)
        {
          // The type doesn't exist for this country. Just ignore it
        }
      }

      vQuery.AND(geoEntityQuery.getType().NI(notInSet.toArray(new String[notInSet.size()])));

      vQuery.AND(geoEntityQuery.getTerm("geoTermId").LEFT_JOIN_EQ(termQuery.getId("termId")));

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
        applyCount = updateAllPathForGeoEntity(childOfChild, childId, copyChildren, showTicker, applyCount);
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
    MdBusiness allPathsMdBusiness = MdBusiness.getMdBusiness(AllPaths.CLASS);

    allPathsMdBusiness.deleteAllTableRecords();

    String geoEntityTable = MdBusiness.getMdBusiness(GeoEntity.CLASS).getTableName();
    String locatedInTable = MdRelationship.getMdElement(LocatedIn.CLASS).getTableName();
    String allPathsTable = allPathsMdBusiness.getTableName();
    String allPathsRootTypeId = IdParser.parseRootFromId(MdBusiness.getMdBusiness(AllPaths.CLASS).getId());
    String sitemaster = ServerProperties.getDomain();
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

    String sql = "INSERT INTO "+allPathsTable+" (\n" +
            "  "+AllPaths.getIdMd().definesAttribute().toLowerCase()+",\n" +
            "  "+AllPaths.getSiteMasterMd().definesAttribute().toLowerCase()+",\n" +
            "  "+AllPaths.getKeyNameMd().definesAttribute().toLowerCase()+",\n" +
            "  "+AllPaths.getTypeMd().definesAttribute().toLowerCase()+",\n" +
            "  "+AllPaths.getEntityDomainMd().definesAttribute().toLowerCase()+",\n" +
            "  "+AllPaths.getLastUpdateDateMd().definesAttribute().toLowerCase()+",\n" +
            "  "+AllPaths.getSeqMd().definesAttribute().toLowerCase()+",\n" +
            "  "+AllPaths.getCreatedByMd().definesAttribute().toLowerCase()+",\n" +
            "  "+AllPaths.getLockedByMd().definesAttribute().toLowerCase()+",\n" +
            "  "+AllPaths.getCreateDateMd().definesAttribute().toLowerCase()+",\n" +
            "  "+AllPaths.getOwnerMd().definesAttribute().toLowerCase()+",\n" +
            "  "+AllPaths.getLastUpdatedByMd().definesAttribute().toLowerCase()+",\n" +
            "  "+AllPaths.getParentGeoEntityMd().definesAttribute().toLowerCase()+",\n" +
            "  "+AllPaths.getParentUniversalMd().definesAttribute().toLowerCase()+",\n" +
            "  "+AllPaths.getChildGeoEntityMd().definesAttribute().toLowerCase()+",\n" +
            "  "+AllPaths.getChildUniversalMd().definesAttribute().toLowerCase()+"\n" +
            ") \n" +

            "WITH RECURSIVE quick_paths(root_id, "+RelationshipInfo.CHILD_ID+", "+RelationshipInfo.PARENT_ID+", "+AllPaths.getSeqMd().definesAttribute().toLowerCase()+") AS ( \n" +
            "    SELECT "+RelationshipInfo.CHILD_ID+" , "+RelationshipInfo.CHILD_ID+", "+RelationshipInfo.PARENT_ID+", NEXTVAL('"+PostgreSQL.UNIQUE_OBJECT_ID_SEQUENCE+"')  FROM "+locatedInTable+"  locatedin \n" +
            "    UNION\n" +
            "    SELECT a.root_id, b."+RelationshipInfo.CHILD_ID+", b."+RelationshipInfo.PARENT_ID+", NEXTVAL('"+PostgreSQL.UNIQUE_OBJECT_ID_SEQUENCE+"')  FROM quick_paths a, "+locatedInTable+" b WHERE b."+RelationshipInfo.CHILD_ID+" = a."+RelationshipInfo.PARENT_ID+"\n" +
            "    )\n" +

            "SELECT  \n" +
            "    MD5(geo1."+GeoEntity.getIdMd().definesAttribute().toLowerCase()+" || geo2."+GeoEntity.getIdMd().definesAttribute().toLowerCase()+" ) || '"+allPathsRootTypeId+"' AS "+GeoEntity.getIdMd().definesAttribute().toLowerCase()+",\n" +
            "    '"+sitemaster+"'  AS "+AllPaths.getSiteMasterMd().definesAttribute().toLowerCase()+",\n" +
            "    MD5(geo1."+GeoEntity.getIdMd().definesAttribute().toLowerCase()+" || geo2."+GeoEntity.getIdMd().definesAttribute().toLowerCase()+" ) || '"+allPathsRootTypeId+"' AS "+GeoEntity.getKeyNameMd().definesAttribute().toLowerCase()+",\n" +
            "    '"+AllPaths.CLASS+"' AS \""+AllPaths.getTypeMd().definesAttribute().toLowerCase()+"\",\n" +
            "    '' AS "+AllPaths.getEntityDomainMd().definesAttribute().toLowerCase()+",\n" +
            "    ? AS "+AllPaths.getLastUpdateDateMd().definesAttribute().toLowerCase()+",\n" +
            "    paths."+AllPaths.getSeqMd().definesAttribute().toLowerCase()+"  AS "+AllPaths.getSeqMd().definesAttribute().toLowerCase()+",\n" +
            "    '"+createdById+"'  AS "+AllPaths.getCreatedByMd().definesAttribute().toLowerCase()+",\n" +
            "    NULL AS "+AllPaths.getLockedByMd().definesAttribute().toLowerCase()+",\n" +
            "    ? AS "+AllPaths.getCreateDateMd().definesAttribute().toLowerCase()+",\n" +
            "    '"+createdById+"' AS \""+AllPaths.getOwnerMd().definesAttribute().toLowerCase()+"\",\n" +
            "    '"+createdById+"' AS "+AllPaths.getLastUpdatedByMd().definesAttribute().toLowerCase()+",\n" +
            "    paths."+RelationshipInfo.PARENT_ID+" AS "+AllPaths.getParentGeoEntityMd().definesAttribute().toLowerCase()+", \n" +
            "    SUBSTRING(paths."+RelationshipInfo.PARENT_ID+","+DatabaseInfo.ROOT_ID_SIZE+"+1,"+DatabaseInfo.ROOT_ID_SIZE+") || '"+MdBusinessInfo.ID_VALUE.substring(0, Integer.parseInt(DatabaseInfo.ROOT_ID_SIZE))+"',\n" +
            "    paths.root_id as "+AllPaths.getChildGeoEntityMd().definesAttribute().toLowerCase()+", \n" +
            "    SUBSTRING(paths.root_id,"+DatabaseInfo.ROOT_ID_SIZE+"+1,"+DatabaseInfo.ROOT_ID_SIZE+")   || '"+MdBusinessInfo.ID_VALUE.substring(0, Integer.parseInt(DatabaseInfo.ROOT_ID_SIZE))+"'\n" +
            "FROM "+geoEntityTable+" as geo1, "+geoEntityTable+" as geo2,\n" +
            "(SELECT * FROM quick_paths UNION SELECT "+GeoEntity.getIdMd().definesAttribute().toLowerCase()+","+GeoEntity.getIdMd().definesAttribute().toLowerCase()+","+GeoEntity.getIdMd().definesAttribute().toLowerCase()+",NEXTVAL('"+PostgreSQL.UNIQUE_OBJECT_ID_SEQUENCE+"') FROM "+geoEntityTable+" ) as paths\n"+
            "WHERE geo1."+GeoEntity.getIdMd().definesAttribute().toLowerCase()+" = paths."+RelationshipInfo.PARENT_ID+" AND geo2."+GeoEntity.getIdMd().definesAttribute().toLowerCase()+" = paths.root_id\n";

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
    String sitemaster = ServerProperties.getDomain();
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

    String sql = "INSERT INTO "+allPathsTable+" (\n" +
    "  "+AllPaths.getIdMd().definesAttribute().toLowerCase()+",\n" +
    "  "+AllPaths.getSiteMasterMd().definesAttribute().toLowerCase()+",\n" +
    "  "+AllPaths.getKeyNameMd().definesAttribute().toLowerCase()+",\n" +
    "  "+AllPaths.getTypeMd().definesAttribute().toLowerCase()+",\n" +
    "  "+AllPaths.getEntityDomainMd().definesAttribute().toLowerCase()+",\n" +
    "  "+AllPaths.getLastUpdateDateMd().definesAttribute().toLowerCase()+",\n" +
    "  "+AllPaths.getSeqMd().definesAttribute().toLowerCase()+",\n" +
    "  "+AllPaths.getCreatedByMd().definesAttribute().toLowerCase()+",\n" +
    "  "+AllPaths.getLockedByMd().definesAttribute().toLowerCase()+",\n" +
    "  "+AllPaths.getCreateDateMd().definesAttribute().toLowerCase()+",\n" +
    "  "+AllPaths.getOwnerMd().definesAttribute().toLowerCase()+",\n" +
    "  "+AllPaths.getLastUpdatedByMd().definesAttribute().toLowerCase()+",\n" +
    "  "+AllPaths.getParentGeoEntityMd().definesAttribute().toLowerCase()+",\n" +
    "  "+AllPaths.getParentUniversalMd().definesAttribute().toLowerCase()+",\n" +
    "  "+AllPaths.getChildGeoEntityMd().definesAttribute().toLowerCase()+",\n" +
    "  "+AllPaths.getChildUniversalMd().definesAttribute().toLowerCase()+"\n" +
    ") \n" +
    " SELECT \n"+
    "   MD5(allpaths_parent."+AllPaths.getParentGeoEntityMd().definesAttribute().toLowerCase()+" || allpaths_child."+AllPaths.getChildGeoEntityMd().definesAttribute().toLowerCase()+" ) || '"+allPathsRootTypeId+"' AS newId,\n" +
    "    '"+sitemaster+"'                                       AS "+AllPaths.getSiteMasterMd().definesAttribute().toLowerCase()+",\n" +
    "   MD5(allpaths_parent."+AllPaths.getParentGeoEntityMd().definesAttribute().toLowerCase()+" || allpaths_child."+AllPaths.getChildGeoEntityMd().definesAttribute().toLowerCase()+" ) || '"+allPathsRootTypeId+"' AS newKey,\n" +
    "    '"+AllPaths.CLASS+"'                                   AS \""+AllPaths.getTypeMd().definesAttribute().toLowerCase()+"\",\n" +
    "    ''                                                     AS "+AllPaths.getEntityDomainMd().definesAttribute().toLowerCase()+",\n" +
    "    ?                                                      AS "+AllPaths.getLastUpdateDateMd().definesAttribute().toLowerCase()+",\n" +
    "    NEXTVAL('"+PostgreSQL.UNIQUE_OBJECT_ID_SEQUENCE+"')    AS "+AllPaths.getSeqMd().definesAttribute().toLowerCase()+",\n" +
    "    '"+createdById+"'                                      AS "+AllPaths.getCreatedByMd().definesAttribute().toLowerCase()+",\n" +
    "    NULL                                                   AS "+AllPaths.getLockedByMd().definesAttribute().toLowerCase()+",\n" +
    "    ?                                                      AS "+AllPaths.getCreatedByMd().definesAttribute().toLowerCase()+",\n" +
    "    '"+createdById+"'                                      AS \""+AllPaths.getOwnerMd().definesAttribute().toLowerCase()+"\",\n" +
    "    '"+createdById+"'                                      AS "+AllPaths.getLastUpdatedByMd().definesAttribute().toLowerCase()+",\n" +
    "    allpaths_parent."+AllPaths.getParentGeoEntityMd().definesAttribute().toLowerCase()+" AS "+AllPaths.getParentGeoEntityMd().definesAttribute().toLowerCase()+", \n" +
    "    allpaths_parent."+AllPaths.getParentUniversalMd().definesAttribute().toLowerCase()+" AS "+AllPaths.getParentUniversalMd().definesAttribute().toLowerCase()+", \n" +
    "    allpaths_child."+AllPaths.getChildGeoEntityMd().definesAttribute().toLowerCase()+"   AS "+AllPaths.getChildGeoEntityMd().definesAttribute().toLowerCase()+", \n" +
    "    allpaths_child."+AllPaths.getChildUniversalMd().definesAttribute().toLowerCase()+"   AS "+AllPaths.getChildUniversalMd().definesAttribute().toLowerCase()+" \n" +

    " FROM \n"+
    // Fech all of the recursive children of the given child term, including the child term itself.
    "  (SELECT "+AllPaths.getChildGeoEntityMd().definesAttribute().toLowerCase()+","+AllPaths.getChildUniversalMd().definesAttribute().toLowerCase()+" \n"+
    "    FROM "+allPathsTable+" \n"+
    "     WHERE "+AllPaths.getParentGeoEntityMd().definesAttribute().toLowerCase()+" = '"+childGeoEntityId+"' ) AS allpaths_child, \n"+
    // Fech all of the recursive parents of the given new parent term, including the new parent term itself.
    "  (SELECT "+AllPaths.getParentGeoEntityMd().definesAttribute().toLowerCase()+", "+AllPaths.getParentUniversalMd().definesAttribute().toLowerCase()+" \n"+
    "     FROM "+allPathsTable+" \n"+
    "    WHERE "+AllPaths.getChildGeoEntityMd().definesAttribute().toLowerCase()+" = '"+newParentGeoEntityId+"' \n"+
    "    ) AS allpaths_parent \n"+
    // Since a term can have multiple parents, a path to one of the new parent's parents may already exist
    " WHERE allpaths_parent."+AllPaths.getParentGeoEntityMd().definesAttribute().toLowerCase()+" NOT IN \n"+
    "   (SELECT "+AllPaths.getParentGeoEntityMd().definesAttribute().toLowerCase()+" \n"+
    "      FROM "+allPathsTable+" \n"+
    "     WHERE "+AllPaths.getParentGeoEntityMd().definesAttribute().toLowerCase()+" = allpaths_parent."+AllPaths.getParentGeoEntityMd().definesAttribute().toLowerCase()+" \n"+
    "      AND "+AllPaths.getChildGeoEntityMd().definesAttribute().toLowerCase()+" = allpaths_child."+AllPaths.getChildGeoEntityMd().definesAttribute().toLowerCase()+") \n";

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

      // Check if the entry already exists. If so, don't create it.
      // WARNING: this is not thread safe. Make SAVEPOINTS work instead.
      // QueryFactory f = new QueryFactory(); AllPathsQuery q = new
      // AllPathsQuery(f); q.WHERE(q.getChildGeoEntity().EQ(childId));
      // q.WHERE(q.getParentGeoEntity().EQ(parentId));
      //
      // if(q.getCount() == 0)
      // {
      AllPaths allPaths = new AllPaths();
      allPaths.setValue(AllPaths.PARENTGEOENTITY, parentId);
      allPaths.setValue(AllPaths.PARENTUNIVERSAL, parentMdBusiness);
      allPaths.setValue(AllPaths.CHILDGEOENTITY, childId);
      allPaths.setValue(AllPaths.CHILDUNIVERSAL, childMdBusiness);
      allPaths.apply();
      // }

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
    String toString = this.getEntityName();
    toString += "(" + this.getTypeDisplayLabel() + ")";
    return toString;
  }

  public String getOutbreakAlert()
  {
    Property property = Property.getByPackageAndName(PropertyInfo.GENERAL_PACKAGE, PropertyInfo.EPIDEMIC_UNIVERSAL);

    if (property != null)
    {
      String universal = property.getPropertyValue();

      if (!universal.equals(""))
      {
        GeoHierarchy hierarchy = GeoHierarchy.get(universal);

        if (hierarchy != null)
        {
          String universalType = hierarchy.getGeoEntityClass().definesType();

          if (GeoHierarchy.isAncestor(this.getType(), universalType))
          {
            return MDSSProperties.getString("Epidemic");
          }
        }
      }
    }

    return MDSSProperties.getString("Outbreak");
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

    for(MdAttributeConcreteDAOIF mdAttr : md.definesAttributes())
    {
      if(mdAttr instanceof MdAttributeReferenceDAOIF)
      {
        MdAttributeReferenceDAOIF mdAttributeReference = (MdAttributeReferenceDAOIF) mdAttr;
        
        String reference = mdAttributeReference.javaType(false);
        
        try
        {
          if (GeoEntity.class.isAssignableFrom(Class.forName(reference)))
          {
            list.add( mdAttributeReference.definesAttribute());
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
    if (this.getAllContainsGeoEntity().getAll().size() == 0 &&
        this.getAllLocatedInGeoEntity().getAll().size() <= 1)
    {
      return true;
    }
    else
    {
      return false;
    }
  }

  /**
   * Precondition:  Assumes the given term is a leaf node!!!
   */
  public static void deleteLeafFromAllPaths(String leafTermId)
  {
    MdBusiness mdBusinessAllPaths = MdBusiness.getMdBusiness(AllPaths.CLASS);

    String tableName = mdBusinessAllPaths.getTableName();

    String childGeoEntityColumn = AllPaths.getChildGeoEntityMd().definesAttribute();

    String procCallString = "DELETE FROM "+tableName+" WHERE "+childGeoEntityColumn+" = ?";

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

    String childColumn = AllPaths.getChildGeoEntityMd().definesAttribute();
    String parentColumn = AllPaths.getParentGeoEntityMd().definesAttribute();

    String procCallString = "DELETE FROM "+tableName+" WHERE "+childColumn+" = ? "
      + " OR "+parentColumn+" = ?";

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
}