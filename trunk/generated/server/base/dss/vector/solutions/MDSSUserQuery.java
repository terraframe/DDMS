package dss.vector.solutions;

@com.terraframe.mojo.business.ClassSignature(hash = 805238809)
/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to MDSSUser.java
 *
 * @author Autogenerated by TerraFrame
 */
public  class MDSSUserQuery extends com.terraframe.mojo.system.UsersQuery
 implements com.terraframe.mojo.generation.loader.Reloadable
{
private static final long serialVersionUID = 805238809;

  public MDSSUserQuery(com.terraframe.mojo.query.QueryFactory componentQueryFactory)
  {
    super(componentQueryFactory);
    if (this.getComponentQuery() == null)
    {
      com.terraframe.mojo.business.BusinessQuery businessQuery = componentQueryFactory.businessQuery(this.getClassType());

       this.setBusinessQuery(businessQuery);
    }
  }

  public MDSSUserQuery(com.terraframe.mojo.query.ValueQuery valueQuery)
  {
    super(valueQuery);
    if (this.getComponentQuery() == null)
    {
      com.terraframe.mojo.business.BusinessQuery businessQuery = new com.terraframe.mojo.business.BusinessQuery(valueQuery, this.getClassType());

       this.setBusinessQuery(businessQuery);
    }
  }

  public String getClassType()
  {
    return dss.vector.solutions.MDSSUser.CLASS;
  }
  public dss.vector.solutions.query.DefaultSavedMapQuery.DefaultSavedMapQueryReferenceIF getDefaultMap()
  {
    return getDefaultMap(null);

  }
 
  public dss.vector.solutions.query.DefaultSavedMapQuery.DefaultSavedMapQueryReferenceIF getDefaultMap(String alias)
  {

    com.terraframe.mojo.dataaccess.MdAttributeDAOIF mdAttributeIF = this.getComponentQuery().getMdAttributeROfromMap("defaultMap");

    return (dss.vector.solutions.query.DefaultSavedMapQuery.DefaultSavedMapQueryReferenceIF)this.getComponentQuery().internalAttributeFactory(dss.vector.solutions.MDSSUser.DEFAULTMAP, mdAttributeIF, this, alias, null);

  }
 
  public dss.vector.solutions.query.DefaultSavedMapQuery.DefaultSavedMapQueryReferenceIF getDefaultMap(String alias, String displayLabel)
  {

    com.terraframe.mojo.dataaccess.MdAttributeDAOIF mdAttributeIF = this.getComponentQuery().getMdAttributeROfromMap("defaultMap");

    return (dss.vector.solutions.query.DefaultSavedMapQuery.DefaultSavedMapQueryReferenceIF)this.getComponentQuery().internalAttributeFactory(dss.vector.solutions.MDSSUser.DEFAULTMAP, mdAttributeIF, this, alias, displayLabel);

  }
  public dss.vector.solutions.query.SavedSearchQuery.SavedSearchQueryReferenceIF getDefaultSearch()
  {
    return getDefaultSearch(null);

  }
 
  public dss.vector.solutions.query.SavedSearchQuery.SavedSearchQueryReferenceIF getDefaultSearch(String alias)
  {

    com.terraframe.mojo.dataaccess.MdAttributeDAOIF mdAttributeIF = this.getComponentQuery().getMdAttributeROfromMap("defaultSearch");

    return (dss.vector.solutions.query.SavedSearchQuery.SavedSearchQueryReferenceIF)this.getComponentQuery().internalAttributeFactory(dss.vector.solutions.MDSSUser.DEFAULTSEARCH, mdAttributeIF, this, alias, null);

  }
 
  public dss.vector.solutions.query.SavedSearchQuery.SavedSearchQueryReferenceIF getDefaultSearch(String alias, String displayLabel)
  {

    com.terraframe.mojo.dataaccess.MdAttributeDAOIF mdAttributeIF = this.getComponentQuery().getMdAttributeROfromMap("defaultSearch");

    return (dss.vector.solutions.query.SavedSearchQuery.SavedSearchQueryReferenceIF)this.getComponentQuery().internalAttributeFactory(dss.vector.solutions.MDSSUser.DEFAULTSEARCH, mdAttributeIF, this, alias, displayLabel);

  }
  public com.terraframe.mojo.query.SelectableSingleChar getGeoRoot()
  {
    return getGeoRoot(null);

  }
 
  public com.terraframe.mojo.query.SelectableSingleChar getGeoRoot(String alias)
  {
    return (com.terraframe.mojo.query.SelectableSingleChar)this.getComponentQuery().get(dss.vector.solutions.MDSSUser.GEOROOT, alias, null);

  }
 
  public com.terraframe.mojo.query.SelectableSingleChar getGeoRoot(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.SelectableSingleChar)this.getComponentQuery().get(dss.vector.solutions.MDSSUser.GEOROOT, alias, displayLabel);

  }
  public dss.vector.solutions.PersonQuery.PersonQueryReferenceIF getPerson()
  {
    return getPerson(null);

  }
 
  public dss.vector.solutions.PersonQuery.PersonQueryReferenceIF getPerson(String alias)
  {

    com.terraframe.mojo.dataaccess.MdAttributeDAOIF mdAttributeIF = this.getComponentQuery().getMdAttributeROfromMap("person");

    return (dss.vector.solutions.PersonQuery.PersonQueryReferenceIF)this.getComponentQuery().internalAttributeFactory(dss.vector.solutions.MDSSUser.PERSON, mdAttributeIF, this, alias, null);

  }
 
  public dss.vector.solutions.PersonQuery.PersonQueryReferenceIF getPerson(String alias, String displayLabel)
  {

    com.terraframe.mojo.dataaccess.MdAttributeDAOIF mdAttributeIF = this.getComponentQuery().getMdAttributeROfromMap("person");

    return (dss.vector.solutions.PersonQuery.PersonQueryReferenceIF)this.getComponentQuery().internalAttributeFactory(dss.vector.solutions.MDSSUser.PERSON, mdAttributeIF, this, alias, displayLabel);

  }
  public dss.vector.solutions.geo.generated.GeoEntityQuery.GeoEntityQueryReferenceIF getRootGeoEntity()
  {
    return getRootGeoEntity(null);

  }
 
  public dss.vector.solutions.geo.generated.GeoEntityQuery.GeoEntityQueryReferenceIF getRootGeoEntity(String alias)
  {

    com.terraframe.mojo.dataaccess.MdAttributeDAOIF mdAttributeIF = this.getComponentQuery().getMdAttributeROfromMap("rootGeoEntity");

    return (dss.vector.solutions.geo.generated.GeoEntityQuery.GeoEntityQueryReferenceIF)this.getComponentQuery().internalAttributeFactory(dss.vector.solutions.MDSSUser.ROOTGEOENTITY, mdAttributeIF, this, alias, null);

  }
 
  public dss.vector.solutions.geo.generated.GeoEntityQuery.GeoEntityQueryReferenceIF getRootGeoEntity(String alias, String displayLabel)
  {

    com.terraframe.mojo.dataaccess.MdAttributeDAOIF mdAttributeIF = this.getComponentQuery().getMdAttributeROfromMap("rootGeoEntity");

    return (dss.vector.solutions.geo.generated.GeoEntityQuery.GeoEntityQueryReferenceIF)this.getComponentQuery().internalAttributeFactory(dss.vector.solutions.MDSSUser.ROOTGEOENTITY, mdAttributeIF, this, alias, displayLabel);

  }
  protected com.terraframe.mojo.query.AttributeReference referenceFactory( com.terraframe.mojo.dataaccess.MdAttributeRefDAOIF mdAttributeIF, String attributeNamespace, String definingTableName, String definingTableAlias,  com.terraframe.mojo.dataaccess.MdBusinessDAOIF referenceMdBusinessIF, String referenceTableAlias, com.terraframe.mojo.query.ComponentQuery rootQuery, java.util.Set<com.terraframe.mojo.query.Join> tableJoinSet, String userDefinedAlias, String userDefinedDisplayLabel)
  {
    String name = mdAttributeIF.definesAttribute();
    
    if (name.equals("defaultMap")) 
    {
       return new dss.vector.solutions.query.DefaultSavedMapQuery.DefaultSavedMapQueryReference((com.terraframe.mojo.dataaccess.MdAttributeRefDAOIF)mdAttributeIF, attributeNamespace, definingTableName, definingTableAlias, referenceMdBusinessIF, referenceTableAlias, rootQuery, tableJoinSet, userDefinedAlias, userDefinedDisplayLabel);
    }
    else if (name.equals("defaultSearch")) 
    {
       return new dss.vector.solutions.query.SavedSearchQuery.SavedSearchQueryReference((com.terraframe.mojo.dataaccess.MdAttributeRefDAOIF)mdAttributeIF, attributeNamespace, definingTableName, definingTableAlias, referenceMdBusinessIF, referenceTableAlias, rootQuery, tableJoinSet, userDefinedAlias, userDefinedDisplayLabel);
    }
    else if (name.equals("person")) 
    {
       return new dss.vector.solutions.PersonQuery.PersonQueryReference((com.terraframe.mojo.dataaccess.MdAttributeRefDAOIF)mdAttributeIF, attributeNamespace, definingTableName, definingTableAlias, referenceMdBusinessIF, referenceTableAlias, rootQuery, tableJoinSet, userDefinedAlias, userDefinedDisplayLabel);
    }
    else if (name.equals("rootGeoEntity")) 
    {
       return new dss.vector.solutions.geo.generated.GeoEntityQuery.GeoEntityQueryReference((com.terraframe.mojo.dataaccess.MdAttributeRefDAOIF)mdAttributeIF, attributeNamespace, definingTableName, definingTableAlias, referenceMdBusinessIF, referenceTableAlias, rootQuery, tableJoinSet, userDefinedAlias, userDefinedDisplayLabel);
    }
    else 
    {
      return super.referenceFactory(mdAttributeIF, attributeNamespace, definingTableName, definingTableAlias, referenceMdBusinessIF, referenceTableAlias, rootQuery, tableJoinSet, userDefinedAlias, userDefinedDisplayLabel);
    }
  }

  /**  
   * Returns an iterator of Business objects that match the query criteria specified
   * on this query object. 
   * @return iterator of Business objects that match the query criteria specified
   * on this query object.
   */
  public com.terraframe.mojo.query.OIterator<? extends MDSSUser> getIterator()
  {
    this.checkNotUsedInValueQuery();
    String sqlStmt;
    if (_limit != null && _skip != null)
    {
      sqlStmt = this.getComponentQuery().getSQL(_limit, _skip);
    }
    else
    {
      sqlStmt = this.getComponentQuery().getSQL();
    }
    java.util.Map<String, com.terraframe.mojo.query.ColumnInfo> columnInfoMap = this.getComponentQuery().getColumnInfoMap();

    java.sql.ResultSet results = com.terraframe.mojo.dataaccess.database.Database.query(sqlStmt);
    return new com.terraframe.mojo.business.BusinessIterator<MDSSUser>(this.getComponentQuery().getMdEntityIF(), columnInfoMap, results);
  }


  public com.terraframe.mojo.query.Condition persistedQueries()
  {
    com.terraframe.mojo.query.QueryFactory queryFactory = this.getQueryFactory();
    com.terraframe.mojo.business.RelationshipQuery relationshipQuery = queryFactory.relationshipQuery(dss.vector.solutions.query.PersistsSearch.CLASS);

    return this.getBusinessQuery().isParentIn(relationshipQuery);
  }


  public com.terraframe.mojo.query.Condition SUBSELECT_persistedQueries()
  {
    com.terraframe.mojo.query.QueryFactory queryFactory = this.getQueryFactory();
    com.terraframe.mojo.business.RelationshipQuery relationshipQuery = queryFactory.relationshipQuery(dss.vector.solutions.query.PersistsSearch.CLASS);

    return this.getBusinessQuery().isParentIn_SUBSELECT(relationshipQuery);
  }

  public com.terraframe.mojo.query.Condition persistedQueries(dss.vector.solutions.query.PersistsSearchQuery persistsSearchQuery)
  {
    return this.getBusinessQuery().isParentIn(persistsSearchQuery);
  }

  public com.terraframe.mojo.query.Condition SUBSELECT_persistedQueries(dss.vector.solutions.query.PersistsSearchQuery persistsSearchQuery)
  {
    return this.getBusinessQuery().isParentIn_SUBSELECT(persistsSearchQuery);
  }

  public com.terraframe.mojo.query.Condition persistedQueries(dss.vector.solutions.query.SavedSearchQuery savedSearchQuery)
  {
    com.terraframe.mojo.query.QueryFactory queryFactory = this.getQueryFactory();
    com.terraframe.mojo.business.RelationshipQuery relationshipQuery = queryFactory.relationshipQuery(dss.vector.solutions.query.PersistsSearch.CLASS);
    relationshipQuery.AND(relationshipQuery.hasChild(savedSearchQuery));

    return this.getBusinessQuery().isParentIn(relationshipQuery);
  }

  public com.terraframe.mojo.query.Condition SUBSELECT_persistedQueries(dss.vector.solutions.query.SavedSearchQuery savedSearchQuery)
  {
    com.terraframe.mojo.query.QueryFactory queryFactory = this.getQueryFactory();
    com.terraframe.mojo.business.RelationshipQuery relationshipQuery = queryFactory.relationshipQuery(dss.vector.solutions.query.PersistsSearch.CLASS);
    relationshipQuery.AND(relationshipQuery.hasChild(savedSearchQuery));

    return this.getBusinessQuery().isParentIn_SUBSELECT(relationshipQuery);
  }

  public com.terraframe.mojo.query.Condition persistedQueries(dss.vector.solutions.query.SavedSearchQuery savedSearchQuery, dss.vector.solutions.query.PersistsSearchQuery persistsSearchQuery)
  {
    persistsSearchQuery.AND(persistsSearchQuery.hasChild(savedSearchQuery));
    return this.getBusinessQuery().isParentIn(persistsSearchQuery);
  }

  public com.terraframe.mojo.query.Condition SUBSELECT_persistedQueries(dss.vector.solutions.query.SavedSearchQuery savedSearchQuery, dss.vector.solutions.query.PersistsSearchQuery persistsSearchQuery)
  {
    persistsSearchQuery.AND(persistsSearchQuery.hasChild(savedSearchQuery));
    return this.getBusinessQuery().isParentIn_SUBSELECT(persistsSearchQuery);
  }


  public com.terraframe.mojo.query.Condition NOT_IN_persistedQueries()
  {
    com.terraframe.mojo.query.QueryFactory queryFactory = this.getQueryFactory();
    com.terraframe.mojo.business.RelationshipQuery relationshipQuery = queryFactory.relationshipQuery(dss.vector.solutions.query.PersistsSearch.CLASS);

    return this.getBusinessQuery().isNotParentIn(relationshipQuery);
  }


  public com.terraframe.mojo.query.Condition SUBSELECT_NOT_IN_persistedQueries()
  {
    com.terraframe.mojo.query.QueryFactory queryFactory = this.getQueryFactory();
    com.terraframe.mojo.business.RelationshipQuery relationshipQuery = queryFactory.relationshipQuery(dss.vector.solutions.query.PersistsSearch.CLASS);

    return this.getBusinessQuery().isNotParentIn_SUBSELECT(relationshipQuery);
  }

  public com.terraframe.mojo.query.Condition NOT_IN_persistedQueries(dss.vector.solutions.query.PersistsSearchQuery persistsSearchQuery)
  {
    return this.getBusinessQuery().isNotParentIn(persistsSearchQuery);
  }

  public com.terraframe.mojo.query.Condition SUBSELECT_NOT_IN_persistedQueries(dss.vector.solutions.query.PersistsSearchQuery persistsSearchQuery)
  {
    return this.getBusinessQuery().isNotParentIn_SUBSELECT(persistsSearchQuery);
  }

  public com.terraframe.mojo.query.Condition NOT_IN_persistedQueries(dss.vector.solutions.query.SavedSearchQuery savedSearchQuery)
  {
    com.terraframe.mojo.query.QueryFactory queryFactory = this.getQueryFactory();
    com.terraframe.mojo.business.RelationshipQuery relationshipQuery = queryFactory.relationshipQuery(dss.vector.solutions.query.PersistsSearch.CLASS);
    relationshipQuery.AND(relationshipQuery.hasChild(savedSearchQuery));

    return this.getBusinessQuery().isNotParentIn(relationshipQuery);
  }

  public com.terraframe.mojo.query.Condition SUBSELECT_NOT_IN_persistedQueries(dss.vector.solutions.query.SavedSearchQuery savedSearchQuery)
  {
    com.terraframe.mojo.query.QueryFactory queryFactory = this.getQueryFactory();
    com.terraframe.mojo.business.RelationshipQuery relationshipQuery = queryFactory.relationshipQuery(dss.vector.solutions.query.PersistsSearch.CLASS);
    relationshipQuery.AND(relationshipQuery.hasChild(savedSearchQuery));

    return this.getBusinessQuery().isNotParentIn_SUBSELECT(relationshipQuery);
  }

  public com.terraframe.mojo.query.Condition NOT_IN_persistedQueries(dss.vector.solutions.query.SavedSearchQuery savedSearchQuery, dss.vector.solutions.query.PersistsSearchQuery persistsSearchQuery)
  {
    persistsSearchQuery.AND(persistsSearchQuery.hasChild(savedSearchQuery));
    return this.getBusinessQuery().isNotParentIn(persistsSearchQuery);
  }

  public com.terraframe.mojo.query.Condition SUBSELECT_NOT_IN_persistedQueries(dss.vector.solutions.query.SavedSearchQuery savedSearchQuery, dss.vector.solutions.query.PersistsSearchQuery persistsSearchQuery)
  {
    persistsSearchQuery.AND(persistsSearchQuery.hasChild(savedSearchQuery));
    return this.getBusinessQuery().isNotParentIn_SUBSELECT(persistsSearchQuery);
  }


/**
 * Interface that masks all type unsafe query methods and defines all type safe methods.
 * This type is used when a join is performed on this class as a reference.
 **/
  public interface MDSSUserQueryReferenceIF extends com.terraframe.mojo.generation.loader.Reloadable, com.terraframe.mojo.system.UsersQuery.UsersQueryReferenceIF
  {

    public dss.vector.solutions.query.DefaultSavedMapQuery.DefaultSavedMapQueryReferenceIF getDefaultMap();
    public dss.vector.solutions.query.DefaultSavedMapQuery.DefaultSavedMapQueryReferenceIF getDefaultMap(String alias);
    public dss.vector.solutions.query.DefaultSavedMapQuery.DefaultSavedMapQueryReferenceIF getDefaultMap(String alias, String displayLabel);
    public dss.vector.solutions.query.SavedSearchQuery.SavedSearchQueryReferenceIF getDefaultSearch();
    public dss.vector.solutions.query.SavedSearchQuery.SavedSearchQueryReferenceIF getDefaultSearch(String alias);
    public dss.vector.solutions.query.SavedSearchQuery.SavedSearchQueryReferenceIF getDefaultSearch(String alias, String displayLabel);
    public com.terraframe.mojo.query.SelectableSingleChar getGeoRoot();
    public com.terraframe.mojo.query.SelectableSingleChar getGeoRoot(String alias);
    public com.terraframe.mojo.query.SelectableSingleChar getGeoRoot(String alias, String displayLabel);
    public dss.vector.solutions.PersonQuery.PersonQueryReferenceIF getPerson();
    public dss.vector.solutions.PersonQuery.PersonQueryReferenceIF getPerson(String alias);
    public dss.vector.solutions.PersonQuery.PersonQueryReferenceIF getPerson(String alias, String displayLabel);
    public dss.vector.solutions.geo.generated.GeoEntityQuery.GeoEntityQueryReferenceIF getRootGeoEntity();
    public dss.vector.solutions.geo.generated.GeoEntityQuery.GeoEntityQueryReferenceIF getRootGeoEntity(String alias);
    public dss.vector.solutions.geo.generated.GeoEntityQuery.GeoEntityQueryReferenceIF getRootGeoEntity(String alias, String displayLabel);

    public com.terraframe.mojo.query.BasicCondition EQ(dss.vector.solutions.MDSSUser mDSSUser);

    public com.terraframe.mojo.query.BasicCondition NE(dss.vector.solutions.MDSSUser mDSSUser);


  public com.terraframe.mojo.query.Condition persistedQueries();

  public com.terraframe.mojo.query.Condition persistedQueries(dss.vector.solutions.query.SavedSearchQuery savedSearchQuery);

  public com.terraframe.mojo.query.Condition persistedQueries(dss.vector.solutions.query.SavedSearchQuery savedSearchQuery, dss.vector.solutions.query.PersistsSearchQuery persistsSearchQuery);


  public com.terraframe.mojo.query.Condition SUBSELECT_persistedQueries();

  public com.terraframe.mojo.query.Condition SUBSELECT_persistedQueries(dss.vector.solutions.query.SavedSearchQuery savedSearchQuery);

  public com.terraframe.mojo.query.Condition SUBSELECT_persistedQueries(dss.vector.solutions.query.SavedSearchQuery savedSearchQuery, dss.vector.solutions.query.PersistsSearchQuery persistsSearchQuery);


  public com.terraframe.mojo.query.Condition NOT_IN_persistedQueries();

  public com.terraframe.mojo.query.Condition NOT_IN_persistedQueries(dss.vector.solutions.query.SavedSearchQuery savedSearchQuery);

  public com.terraframe.mojo.query.Condition NOT_IN_persistedQueries(dss.vector.solutions.query.SavedSearchQuery savedSearchQuery, dss.vector.solutions.query.PersistsSearchQuery persistsSearchQuery);


  public com.terraframe.mojo.query.Condition SUBSELECT_NOT_IN_persistedQueries();

  public com.terraframe.mojo.query.Condition SUBSELECT_NOT_IN_persistedQueries(dss.vector.solutions.query.SavedSearchQuery savedSearchQuery);

  public com.terraframe.mojo.query.Condition SUBSELECT_NOT_IN_persistedQueries(dss.vector.solutions.query.SavedSearchQuery savedSearchQuery, dss.vector.solutions.query.PersistsSearchQuery persistsSearchQuery);

  }

/**
 * Implements type safe query methods.
 * This type is used when a join is performed on this class as a reference.
 **/
  public static class MDSSUserQueryReference extends com.terraframe.mojo.system.UsersQuery.UsersQueryReference
 implements MDSSUserQueryReferenceIF
, com.terraframe.mojo.generation.loader.Reloadable
  {
private static final long serialVersionUID = -841333833;

  public MDSSUserQueryReference(com.terraframe.mojo.dataaccess.MdAttributeRefDAOIF mdAttributeIF, String attributeNamespace, String definingTableName, String definingTableAlias, com.terraframe.mojo.dataaccess.MdBusinessDAOIF referenceMdBusinessIF, String referenceTableAlias, com.terraframe.mojo.query.ComponentQuery rootQuery, java.util.Set<com.terraframe.mojo.query.Join> tableJoinSet, String alias, String displayLabel)
  {
    super(mdAttributeIF, attributeNamespace, definingTableName, definingTableAlias, referenceMdBusinessIF, referenceTableAlias, rootQuery, tableJoinSet, alias, displayLabel);

  }


    public com.terraframe.mojo.query.BasicCondition EQ(dss.vector.solutions.MDSSUser mDSSUser)
    {
      return this.EQ(mDSSUser.getId());
    }

    public com.terraframe.mojo.query.BasicCondition NE(dss.vector.solutions.MDSSUser mDSSUser)
    {
      return this.NE(mDSSUser.getId());
    }

  public dss.vector.solutions.query.DefaultSavedMapQuery.DefaultSavedMapQueryReferenceIF getDefaultMap()
  {
    return getDefaultMap(null);

  }
 
  public dss.vector.solutions.query.DefaultSavedMapQuery.DefaultSavedMapQueryReferenceIF getDefaultMap(String alias)
  {
    return (dss.vector.solutions.query.DefaultSavedMapQuery.DefaultSavedMapQueryReferenceIF)this.get(dss.vector.solutions.MDSSUser.DEFAULTMAP, alias, null);

  }
 
  public dss.vector.solutions.query.DefaultSavedMapQuery.DefaultSavedMapQueryReferenceIF getDefaultMap(String alias, String displayLabel)
  {
    return (dss.vector.solutions.query.DefaultSavedMapQuery.DefaultSavedMapQueryReferenceIF)this.get(dss.vector.solutions.MDSSUser.DEFAULTMAP,  alias, displayLabel);

  }
  public dss.vector.solutions.query.SavedSearchQuery.SavedSearchQueryReferenceIF getDefaultSearch()
  {
    return getDefaultSearch(null);

  }
 
  public dss.vector.solutions.query.SavedSearchQuery.SavedSearchQueryReferenceIF getDefaultSearch(String alias)
  {
    return (dss.vector.solutions.query.SavedSearchQuery.SavedSearchQueryReferenceIF)this.get(dss.vector.solutions.MDSSUser.DEFAULTSEARCH, alias, null);

  }
 
  public dss.vector.solutions.query.SavedSearchQuery.SavedSearchQueryReferenceIF getDefaultSearch(String alias, String displayLabel)
  {
    return (dss.vector.solutions.query.SavedSearchQuery.SavedSearchQueryReferenceIF)this.get(dss.vector.solutions.MDSSUser.DEFAULTSEARCH,  alias, displayLabel);

  }
  public com.terraframe.mojo.query.SelectableSingleChar getGeoRoot()
  {
    return getGeoRoot(null);

  }
 
  public com.terraframe.mojo.query.SelectableSingleChar getGeoRoot(String alias)
  {
    return (com.terraframe.mojo.query.SelectableSingleChar)this.get(dss.vector.solutions.MDSSUser.GEOROOT, alias, null);

  }
 
  public com.terraframe.mojo.query.SelectableSingleChar getGeoRoot(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.SelectableSingleChar)this.get(dss.vector.solutions.MDSSUser.GEOROOT, alias, displayLabel);

  }
  public dss.vector.solutions.PersonQuery.PersonQueryReferenceIF getPerson()
  {
    return getPerson(null);

  }
 
  public dss.vector.solutions.PersonQuery.PersonQueryReferenceIF getPerson(String alias)
  {
    return (dss.vector.solutions.PersonQuery.PersonQueryReferenceIF)this.get(dss.vector.solutions.MDSSUser.PERSON, alias, null);

  }
 
  public dss.vector.solutions.PersonQuery.PersonQueryReferenceIF getPerson(String alias, String displayLabel)
  {
    return (dss.vector.solutions.PersonQuery.PersonQueryReferenceIF)this.get(dss.vector.solutions.MDSSUser.PERSON,  alias, displayLabel);

  }
  public dss.vector.solutions.geo.generated.GeoEntityQuery.GeoEntityQueryReferenceIF getRootGeoEntity()
  {
    return getRootGeoEntity(null);

  }
 
  public dss.vector.solutions.geo.generated.GeoEntityQuery.GeoEntityQueryReferenceIF getRootGeoEntity(String alias)
  {
    return (dss.vector.solutions.geo.generated.GeoEntityQuery.GeoEntityQueryReferenceIF)this.get(dss.vector.solutions.MDSSUser.ROOTGEOENTITY, alias, null);

  }
 
  public dss.vector.solutions.geo.generated.GeoEntityQuery.GeoEntityQueryReferenceIF getRootGeoEntity(String alias, String displayLabel)
  {
    return (dss.vector.solutions.geo.generated.GeoEntityQuery.GeoEntityQueryReferenceIF)this.get(dss.vector.solutions.MDSSUser.ROOTGEOENTITY,  alias, displayLabel);

  }

  public com.terraframe.mojo.query.Condition persistedQueries()
  {
    com.terraframe.mojo.query.QueryFactory queryFactory = this.getQueryFactory();
    com.terraframe.mojo.business.RelationshipQuery relationshipQuery = queryFactory.relationshipQuery(dss.vector.solutions.query.PersistsSearch.CLASS);

    return this.isParentIn(relationshipQuery);
  }


  public com.terraframe.mojo.query.Condition SUBSELECT_persistedQueries()
  {
    com.terraframe.mojo.query.QueryFactory queryFactory = this.getQueryFactory();
    com.terraframe.mojo.business.RelationshipQuery relationshipQuery = queryFactory.relationshipQuery(dss.vector.solutions.query.PersistsSearch.CLASS);

    return this.isParentIn_SUBSELECT(relationshipQuery);
  }

  public com.terraframe.mojo.query.Condition persistedQueries(dss.vector.solutions.query.PersistsSearchQuery persistsSearchQuery)
  {
    return this.isParentIn(persistsSearchQuery);
  }

  public com.terraframe.mojo.query.Condition SUBSELECT_persistedQueries(dss.vector.solutions.query.PersistsSearchQuery persistsSearchQuery)
  {
    return this.isParentIn_SUBSELECT(persistsSearchQuery);
  }

  public com.terraframe.mojo.query.Condition persistedQueries(dss.vector.solutions.query.SavedSearchQuery savedSearchQuery)
  {
    com.terraframe.mojo.query.QueryFactory queryFactory = this.getQueryFactory();
    com.terraframe.mojo.business.RelationshipQuery relationshipQuery = queryFactory.relationshipQuery(dss.vector.solutions.query.PersistsSearch.CLASS);
    relationshipQuery.AND(relationshipQuery.hasChild(savedSearchQuery));

    return this.isParentIn(relationshipQuery);
  }

  public com.terraframe.mojo.query.Condition SUBSELECT_persistedQueries(dss.vector.solutions.query.SavedSearchQuery savedSearchQuery)
  {
    com.terraframe.mojo.query.QueryFactory queryFactory = this.getQueryFactory();
    com.terraframe.mojo.business.RelationshipQuery relationshipQuery = queryFactory.relationshipQuery(dss.vector.solutions.query.PersistsSearch.CLASS);
    relationshipQuery.AND(relationshipQuery.hasChild(savedSearchQuery));

    return this.isParentIn_SUBSELECT(relationshipQuery);
  }

  public com.terraframe.mojo.query.Condition persistedQueries(dss.vector.solutions.query.SavedSearchQuery savedSearchQuery, dss.vector.solutions.query.PersistsSearchQuery persistsSearchQuery)
  {
    persistsSearchQuery.AND(persistsSearchQuery.hasChild(savedSearchQuery));
    return this.isParentIn(persistsSearchQuery);
  }

  public com.terraframe.mojo.query.Condition SUBSELECT_persistedQueries(dss.vector.solutions.query.SavedSearchQuery savedSearchQuery, dss.vector.solutions.query.PersistsSearchQuery persistsSearchQuery)
  {
    persistsSearchQuery.AND(persistsSearchQuery.hasChild(savedSearchQuery));
    return this.isParentIn_SUBSELECT(persistsSearchQuery);
  }


  public com.terraframe.mojo.query.Condition NOT_IN_persistedQueries()
  {
    com.terraframe.mojo.query.QueryFactory queryFactory = this.getQueryFactory();
    com.terraframe.mojo.business.RelationshipQuery relationshipQuery = queryFactory.relationshipQuery(dss.vector.solutions.query.PersistsSearch.CLASS);

    return this.isNotParentIn(relationshipQuery);
  }


  public com.terraframe.mojo.query.Condition SUBSELECT_NOT_IN_persistedQueries()
  {
    com.terraframe.mojo.query.QueryFactory queryFactory = this.getQueryFactory();
    com.terraframe.mojo.business.RelationshipQuery relationshipQuery = queryFactory.relationshipQuery(dss.vector.solutions.query.PersistsSearch.CLASS);

    return this.isNotParentIn_SUBSELECT(relationshipQuery);
  }

  public com.terraframe.mojo.query.Condition NOT_IN_persistedQueries(dss.vector.solutions.query.PersistsSearchQuery persistsSearchQuery)
  {
    return this.isNotParentIn(persistsSearchQuery);
  }

  public com.terraframe.mojo.query.Condition SUBSELECT_NOT_IN_persistedQueries(dss.vector.solutions.query.PersistsSearchQuery persistsSearchQuery)
  {
    return this.isNotParentIn_SUBSELECT(persistsSearchQuery);
  }

  public com.terraframe.mojo.query.Condition NOT_IN_persistedQueries(dss.vector.solutions.query.SavedSearchQuery savedSearchQuery)
  {
    com.terraframe.mojo.query.QueryFactory queryFactory = this.getQueryFactory();
    com.terraframe.mojo.business.RelationshipQuery relationshipQuery = queryFactory.relationshipQuery(dss.vector.solutions.query.PersistsSearch.CLASS);
    relationshipQuery.AND(relationshipQuery.hasChild(savedSearchQuery));

    return this.isNotParentIn(relationshipQuery);
  }

  public com.terraframe.mojo.query.Condition SUBSELECT_NOT_IN_persistedQueries(dss.vector.solutions.query.SavedSearchQuery savedSearchQuery)
  {
    com.terraframe.mojo.query.QueryFactory queryFactory = this.getQueryFactory();
    com.terraframe.mojo.business.RelationshipQuery relationshipQuery = queryFactory.relationshipQuery(dss.vector.solutions.query.PersistsSearch.CLASS);
    relationshipQuery.AND(relationshipQuery.hasChild(savedSearchQuery));

    return this.isNotParentIn_SUBSELECT(relationshipQuery);
  }

  public com.terraframe.mojo.query.Condition NOT_IN_persistedQueries(dss.vector.solutions.query.SavedSearchQuery savedSearchQuery, dss.vector.solutions.query.PersistsSearchQuery persistsSearchQuery)
  {
    persistsSearchQuery.AND(persistsSearchQuery.hasChild(savedSearchQuery));
    return this.isNotParentIn(persistsSearchQuery);
  }

  public com.terraframe.mojo.query.Condition SUBSELECT_NOT_IN_persistedQueries(dss.vector.solutions.query.SavedSearchQuery savedSearchQuery, dss.vector.solutions.query.PersistsSearchQuery persistsSearchQuery)
  {
    persistsSearchQuery.AND(persistsSearchQuery.hasChild(savedSearchQuery));
    return this.isNotParentIn_SUBSELECT(persistsSearchQuery);
  }

  protected com.terraframe.mojo.query.AttributeReference referenceFactory( com.terraframe.mojo.dataaccess.MdAttributeRefDAOIF mdAttributeIF, String attributeNamespace, String definingTableName, String definingTableAlias,  com.terraframe.mojo.dataaccess.MdBusinessDAOIF referenceMdBusinessIF, String referenceTableAlias, com.terraframe.mojo.query.ComponentQuery rootQuery, java.util.Set<com.terraframe.mojo.query.Join> tableJoinSet, String userDefinedAlias, String userDefinedDisplayLabel)
  {
    String name = mdAttributeIF.definesAttribute();
    
    if (name.equals("defaultMap")) 
    {
       return new dss.vector.solutions.query.DefaultSavedMapQuery.DefaultSavedMapQueryReference((com.terraframe.mojo.dataaccess.MdAttributeRefDAOIF)mdAttributeIF, attributeNamespace, definingTableName, definingTableAlias, referenceMdBusinessIF, referenceTableAlias, rootQuery, tableJoinSet, userDefinedAlias, userDefinedDisplayLabel);
    }
    else if (name.equals("defaultSearch")) 
    {
       return new dss.vector.solutions.query.SavedSearchQuery.SavedSearchQueryReference((com.terraframe.mojo.dataaccess.MdAttributeRefDAOIF)mdAttributeIF, attributeNamespace, definingTableName, definingTableAlias, referenceMdBusinessIF, referenceTableAlias, rootQuery, tableJoinSet, userDefinedAlias, userDefinedDisplayLabel);
    }
    else if (name.equals("person")) 
    {
       return new dss.vector.solutions.PersonQuery.PersonQueryReference((com.terraframe.mojo.dataaccess.MdAttributeRefDAOIF)mdAttributeIF, attributeNamespace, definingTableName, definingTableAlias, referenceMdBusinessIF, referenceTableAlias, rootQuery, tableJoinSet, userDefinedAlias, userDefinedDisplayLabel);
    }
    else if (name.equals("rootGeoEntity")) 
    {
       return new dss.vector.solutions.geo.generated.GeoEntityQuery.GeoEntityQueryReference((com.terraframe.mojo.dataaccess.MdAttributeRefDAOIF)mdAttributeIF, attributeNamespace, definingTableName, definingTableAlias, referenceMdBusinessIF, referenceTableAlias, rootQuery, tableJoinSet, userDefinedAlias, userDefinedDisplayLabel);
    }
    else 
    {
      return super.referenceFactory(mdAttributeIF, attributeNamespace, definingTableName, definingTableAlias, referenceMdBusinessIF, referenceTableAlias, rootQuery, tableJoinSet, userDefinedAlias, userDefinedDisplayLabel);
    }
  }

  }
}
