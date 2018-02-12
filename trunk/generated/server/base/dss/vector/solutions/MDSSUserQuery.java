/*******************************************************************************
 * Copyright (C) 2018 IVCC
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 ******************************************************************************/
package dss.vector.solutions;

@com.runwaysdk.business.ClassSignature(hash = -1261268934)
/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to MDSSUser.java
 *
 * @author Autogenerated by RunwaySDK
 */
public  class MDSSUserQuery extends com.runwaysdk.system.UsersQuery
 implements com.runwaysdk.generation.loader.Reloadable
{

  public MDSSUserQuery(com.runwaysdk.query.QueryFactory componentQueryFactory)
  {
    super(componentQueryFactory);
    if (this.getComponentQuery() == null)
    {
      com.runwaysdk.business.BusinessQuery businessQuery = componentQueryFactory.businessQuery(this.getClassType());

       this.setBusinessQuery(businessQuery);
    }
  }

  public MDSSUserQuery(com.runwaysdk.query.ValueQuery valueQuery)
  {
    super(valueQuery);
    if (this.getComponentQuery() == null)
    {
      com.runwaysdk.business.BusinessQuery businessQuery = new com.runwaysdk.business.BusinessQuery(valueQuery, this.getClassType());

       this.setBusinessQuery(businessQuery);
    }
  }

  public String getClassType()
  {
    return dss.vector.solutions.MDSSUser.CLASS;
  }
  public com.runwaysdk.query.SelectableChar getGeoRoot()
  {
    return getGeoRoot(null);

  }
 
  public com.runwaysdk.query.SelectableChar getGeoRoot(String alias)
  {
    return (com.runwaysdk.query.SelectableChar)this.getComponentQuery().get(dss.vector.solutions.MDSSUser.GEOROOT, alias, null);

  }
 
  public com.runwaysdk.query.SelectableChar getGeoRoot(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableChar)this.getComponentQuery().get(dss.vector.solutions.MDSSUser.GEOROOT, alias, displayLabel);

  }
  public dss.vector.solutions.PersonQuery.PersonQueryReferenceIF getPerson()
  {
    return getPerson(null);

  }
 
  public dss.vector.solutions.PersonQuery.PersonQueryReferenceIF getPerson(String alias)
  {

    com.runwaysdk.dataaccess.MdAttributeDAOIF mdAttributeIF = this.getComponentQuery().getMdAttributeROfromMap(dss.vector.solutions.MDSSUser.PERSON);

    return (dss.vector.solutions.PersonQuery.PersonQueryReferenceIF)this.getComponentQuery().internalAttributeFactory(dss.vector.solutions.MDSSUser.PERSON, mdAttributeIF, this, alias, null);

  }
 
  public dss.vector.solutions.PersonQuery.PersonQueryReferenceIF getPerson(String alias, String displayLabel)
  {

    com.runwaysdk.dataaccess.MdAttributeDAOIF mdAttributeIF = this.getComponentQuery().getMdAttributeROfromMap(dss.vector.solutions.MDSSUser.PERSON);

    return (dss.vector.solutions.PersonQuery.PersonQueryReferenceIF)this.getComponentQuery().internalAttributeFactory(dss.vector.solutions.MDSSUser.PERSON, mdAttributeIF, this, alias, displayLabel);

  }
  public dss.vector.solutions.geo.generated.GeoEntityQuery.GeoEntityQueryReferenceIF getRootGeoEntity()
  {
    return getRootGeoEntity(null);

  }
 
  public dss.vector.solutions.geo.generated.GeoEntityQuery.GeoEntityQueryReferenceIF getRootGeoEntity(String alias)
  {

    com.runwaysdk.dataaccess.MdAttributeDAOIF mdAttributeIF = this.getComponentQuery().getMdAttributeROfromMap(dss.vector.solutions.MDSSUser.ROOTGEOENTITY);

    return (dss.vector.solutions.geo.generated.GeoEntityQuery.GeoEntityQueryReferenceIF)this.getComponentQuery().internalAttributeFactory(dss.vector.solutions.MDSSUser.ROOTGEOENTITY, mdAttributeIF, this, alias, null);

  }
 
  public dss.vector.solutions.geo.generated.GeoEntityQuery.GeoEntityQueryReferenceIF getRootGeoEntity(String alias, String displayLabel)
  {

    com.runwaysdk.dataaccess.MdAttributeDAOIF mdAttributeIF = this.getComponentQuery().getMdAttributeROfromMap(dss.vector.solutions.MDSSUser.ROOTGEOENTITY);

    return (dss.vector.solutions.geo.generated.GeoEntityQuery.GeoEntityQueryReferenceIF)this.getComponentQuery().internalAttributeFactory(dss.vector.solutions.MDSSUser.ROOTGEOENTITY, mdAttributeIF, this, alias, displayLabel);

  }
  protected com.runwaysdk.query.AttributeReference referenceFactory( com.runwaysdk.dataaccess.MdAttributeRefDAOIF mdAttributeIF, String attributeNamespace, String definingTableName, String definingTableAlias,  com.runwaysdk.dataaccess.MdBusinessDAOIF referenceMdBusinessIF, String referenceTableAlias, com.runwaysdk.query.ComponentQuery rootQuery, java.util.Set<com.runwaysdk.query.Join> tableJoinSet, String userDefinedAlias, String userDefinedDisplayLabel)
  {
    String name = mdAttributeIF.definesAttribute();
    
    if (name.equals(dss.vector.solutions.MDSSUser.PERSON)) 
    {
       return new dss.vector.solutions.PersonQuery.PersonQueryReference((com.runwaysdk.dataaccess.MdAttributeRefDAOIF)mdAttributeIF, attributeNamespace, definingTableName, definingTableAlias, referenceMdBusinessIF, referenceTableAlias, rootQuery, tableJoinSet, userDefinedAlias, userDefinedDisplayLabel);
    }
    else if (name.equals(dss.vector.solutions.MDSSUser.ROOTGEOENTITY)) 
    {
       return new dss.vector.solutions.geo.generated.GeoEntityQuery.GeoEntityQueryReference((com.runwaysdk.dataaccess.MdAttributeRefDAOIF)mdAttributeIF, attributeNamespace, definingTableName, definingTableAlias, referenceMdBusinessIF, referenceTableAlias, rootQuery, tableJoinSet, userDefinedAlias, userDefinedDisplayLabel);
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
  public com.runwaysdk.query.OIterator<? extends MDSSUser> getIterator()
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
    java.util.Map<String, com.runwaysdk.query.ColumnInfo> columnInfoMap = this.getComponentQuery().getColumnInfoMap();

    java.sql.ResultSet results = com.runwaysdk.dataaccess.database.Database.query(sqlStmt);
    return new com.runwaysdk.business.BusinessIterator<MDSSUser>(this.getComponentQuery().getMdEntityIF(), columnInfoMap, results);
  }


  public com.runwaysdk.query.Condition persistedQueries()
  {
    com.runwaysdk.query.QueryFactory queryFactory = this.getQueryFactory();
    com.runwaysdk.business.RelationshipQuery relationshipQuery = queryFactory.relationshipQuery(dss.vector.solutions.query.PersistsSearch.CLASS);

    return this.getBusinessQuery().isParentIn(relationshipQuery);
  }


  public com.runwaysdk.query.Condition SUBSELECT_persistedQueries()
  {
    com.runwaysdk.query.QueryFactory queryFactory = this.getQueryFactory();
    com.runwaysdk.business.RelationshipQuery relationshipQuery = queryFactory.relationshipQuery(dss.vector.solutions.query.PersistsSearch.CLASS);

    return this.getBusinessQuery().isParentIn_SUBSELECT(relationshipQuery);
  }

  public com.runwaysdk.query.Condition persistedQueries(dss.vector.solutions.query.PersistsSearchQuery persistsSearchQuery)
  {
    return this.getBusinessQuery().isParentIn(persistsSearchQuery);
  }

  public com.runwaysdk.query.Condition SUBSELECT_persistedQueries(dss.vector.solutions.query.PersistsSearchQuery persistsSearchQuery)
  {
    return this.getBusinessQuery().isParentIn_SUBSELECT(persistsSearchQuery);
  }

  public com.runwaysdk.query.Condition persistedQueries(dss.vector.solutions.query.SavedSearchQuery savedSearchQuery)
  {
    com.runwaysdk.query.QueryFactory queryFactory = this.getQueryFactory();
    com.runwaysdk.business.RelationshipQuery relationshipQuery = queryFactory.relationshipQuery(dss.vector.solutions.query.PersistsSearch.CLASS);
    relationshipQuery.AND(relationshipQuery.hasChild(savedSearchQuery));

    return this.getBusinessQuery().isParentIn(relationshipQuery);
  }

  public com.runwaysdk.query.Condition SUBSELECT_persistedQueries(dss.vector.solutions.query.SavedSearchQuery savedSearchQuery)
  {
    com.runwaysdk.query.QueryFactory queryFactory = this.getQueryFactory();
    com.runwaysdk.business.RelationshipQuery relationshipQuery = queryFactory.relationshipQuery(dss.vector.solutions.query.PersistsSearch.CLASS);
    relationshipQuery.AND(relationshipQuery.hasChild(savedSearchQuery));

    return this.getBusinessQuery().isParentIn_SUBSELECT(relationshipQuery);
  }

  public com.runwaysdk.query.Condition persistedQueries(dss.vector.solutions.query.SavedSearchQuery savedSearchQuery, dss.vector.solutions.query.PersistsSearchQuery persistsSearchQuery)
  {
    persistsSearchQuery.AND(persistsSearchQuery.hasChild(savedSearchQuery));
    return this.getBusinessQuery().isParentIn(persistsSearchQuery);
  }

  public com.runwaysdk.query.Condition SUBSELECT_persistedQueries(dss.vector.solutions.query.SavedSearchQuery savedSearchQuery, dss.vector.solutions.query.PersistsSearchQuery persistsSearchQuery)
  {
    persistsSearchQuery.AND(persistsSearchQuery.hasChild(savedSearchQuery));
    return this.getBusinessQuery().isParentIn_SUBSELECT(persistsSearchQuery);
  }


  public com.runwaysdk.query.Condition NOT_IN_persistedQueries()
  {
    com.runwaysdk.query.QueryFactory queryFactory = this.getQueryFactory();
    com.runwaysdk.business.RelationshipQuery relationshipQuery = queryFactory.relationshipQuery(dss.vector.solutions.query.PersistsSearch.CLASS);

    return this.getBusinessQuery().isNotParentIn(relationshipQuery);
  }


  public com.runwaysdk.query.Condition SUBSELECT_NOT_IN_persistedQueries()
  {
    com.runwaysdk.query.QueryFactory queryFactory = this.getQueryFactory();
    com.runwaysdk.business.RelationshipQuery relationshipQuery = queryFactory.relationshipQuery(dss.vector.solutions.query.PersistsSearch.CLASS);

    return this.getBusinessQuery().isNotParentIn_SUBSELECT(relationshipQuery);
  }

  public com.runwaysdk.query.Condition NOT_IN_persistedQueries(dss.vector.solutions.query.PersistsSearchQuery persistsSearchQuery)
  {
    return this.getBusinessQuery().isNotParentIn(persistsSearchQuery);
  }

  public com.runwaysdk.query.Condition SUBSELECT_NOT_IN_persistedQueries(dss.vector.solutions.query.PersistsSearchQuery persistsSearchQuery)
  {
    return this.getBusinessQuery().isNotParentIn_SUBSELECT(persistsSearchQuery);
  }

  public com.runwaysdk.query.Condition NOT_IN_persistedQueries(dss.vector.solutions.query.SavedSearchQuery savedSearchQuery)
  {
    com.runwaysdk.query.QueryFactory queryFactory = this.getQueryFactory();
    com.runwaysdk.business.RelationshipQuery relationshipQuery = queryFactory.relationshipQuery(dss.vector.solutions.query.PersistsSearch.CLASS);
    relationshipQuery.AND(relationshipQuery.hasChild(savedSearchQuery));

    return this.getBusinessQuery().isNotParentIn(relationshipQuery);
  }

  public com.runwaysdk.query.Condition SUBSELECT_NOT_IN_persistedQueries(dss.vector.solutions.query.SavedSearchQuery savedSearchQuery)
  {
    com.runwaysdk.query.QueryFactory queryFactory = this.getQueryFactory();
    com.runwaysdk.business.RelationshipQuery relationshipQuery = queryFactory.relationshipQuery(dss.vector.solutions.query.PersistsSearch.CLASS);
    relationshipQuery.AND(relationshipQuery.hasChild(savedSearchQuery));

    return this.getBusinessQuery().isNotParentIn_SUBSELECT(relationshipQuery);
  }

  public com.runwaysdk.query.Condition NOT_IN_persistedQueries(dss.vector.solutions.query.SavedSearchQuery savedSearchQuery, dss.vector.solutions.query.PersistsSearchQuery persistsSearchQuery)
  {
    persistsSearchQuery.AND(persistsSearchQuery.hasChild(savedSearchQuery));
    return this.getBusinessQuery().isNotParentIn(persistsSearchQuery);
  }

  public com.runwaysdk.query.Condition SUBSELECT_NOT_IN_persistedQueries(dss.vector.solutions.query.SavedSearchQuery savedSearchQuery, dss.vector.solutions.query.PersistsSearchQuery persistsSearchQuery)
  {
    persistsSearchQuery.AND(persistsSearchQuery.hasChild(savedSearchQuery));
    return this.getBusinessQuery().isNotParentIn_SUBSELECT(persistsSearchQuery);
  }


/**
 * Interface that masks all type unsafe query methods and defines all type safe methods.
 * This type is used when a join is performed on this class as a reference.
 **/
  public interface MDSSUserQueryReferenceIF extends com.runwaysdk.generation.loader.Reloadable, com.runwaysdk.system.UsersQuery.UsersQueryReferenceIF
  {

    public com.runwaysdk.query.SelectableChar getGeoRoot();
    public com.runwaysdk.query.SelectableChar getGeoRoot(String alias);
    public com.runwaysdk.query.SelectableChar getGeoRoot(String alias, String displayLabel);
    public dss.vector.solutions.PersonQuery.PersonQueryReferenceIF getPerson();
    public dss.vector.solutions.PersonQuery.PersonQueryReferenceIF getPerson(String alias);
    public dss.vector.solutions.PersonQuery.PersonQueryReferenceIF getPerson(String alias, String displayLabel);
    public dss.vector.solutions.geo.generated.GeoEntityQuery.GeoEntityQueryReferenceIF getRootGeoEntity();
    public dss.vector.solutions.geo.generated.GeoEntityQuery.GeoEntityQueryReferenceIF getRootGeoEntity(String alias);
    public dss.vector.solutions.geo.generated.GeoEntityQuery.GeoEntityQueryReferenceIF getRootGeoEntity(String alias, String displayLabel);

    public com.runwaysdk.query.BasicCondition EQ(dss.vector.solutions.MDSSUser mDSSUser);

    public com.runwaysdk.query.BasicCondition NE(dss.vector.solutions.MDSSUser mDSSUser);


  public com.runwaysdk.query.Condition persistedQueries();

  public com.runwaysdk.query.Condition persistedQueries(dss.vector.solutions.query.SavedSearchQuery savedSearchQuery);

  public com.runwaysdk.query.Condition persistedQueries(dss.vector.solutions.query.SavedSearchQuery savedSearchQuery, dss.vector.solutions.query.PersistsSearchQuery persistsSearchQuery);


  public com.runwaysdk.query.Condition SUBSELECT_persistedQueries();

  public com.runwaysdk.query.Condition SUBSELECT_persistedQueries(dss.vector.solutions.query.SavedSearchQuery savedSearchQuery);

  public com.runwaysdk.query.Condition SUBSELECT_persistedQueries(dss.vector.solutions.query.SavedSearchQuery savedSearchQuery, dss.vector.solutions.query.PersistsSearchQuery persistsSearchQuery);


  public com.runwaysdk.query.Condition NOT_IN_persistedQueries();

  public com.runwaysdk.query.Condition NOT_IN_persistedQueries(dss.vector.solutions.query.SavedSearchQuery savedSearchQuery);

  public com.runwaysdk.query.Condition NOT_IN_persistedQueries(dss.vector.solutions.query.SavedSearchQuery savedSearchQuery, dss.vector.solutions.query.PersistsSearchQuery persistsSearchQuery);


  public com.runwaysdk.query.Condition SUBSELECT_NOT_IN_persistedQueries();

  public com.runwaysdk.query.Condition SUBSELECT_NOT_IN_persistedQueries(dss.vector.solutions.query.SavedSearchQuery savedSearchQuery);

  public com.runwaysdk.query.Condition SUBSELECT_NOT_IN_persistedQueries(dss.vector.solutions.query.SavedSearchQuery savedSearchQuery, dss.vector.solutions.query.PersistsSearchQuery persistsSearchQuery);

  }

/**
 * Implements type safe query methods.
 * This type is used when a join is performed on this class as a reference.
 **/
  public static class MDSSUserQueryReference extends com.runwaysdk.system.UsersQuery.UsersQueryReference
 implements MDSSUserQueryReferenceIF
, com.runwaysdk.generation.loader.Reloadable
  {

  public MDSSUserQueryReference(com.runwaysdk.dataaccess.MdAttributeRefDAOIF mdAttributeIF, String attributeNamespace, String definingTableName, String definingTableAlias, com.runwaysdk.dataaccess.MdBusinessDAOIF referenceMdBusinessIF, String referenceTableAlias, com.runwaysdk.query.ComponentQuery rootQuery, java.util.Set<com.runwaysdk.query.Join> tableJoinSet, String alias, String displayLabel)
  {
    super(mdAttributeIF, attributeNamespace, definingTableName, definingTableAlias, referenceMdBusinessIF, referenceTableAlias, rootQuery, tableJoinSet, alias, displayLabel);

  }


    public com.runwaysdk.query.BasicCondition EQ(dss.vector.solutions.MDSSUser mDSSUser)
    {
      if(mDSSUser == null) return this.EQ((java.lang.String)null);
      return this.EQ(mDSSUser.getId());
    }

    public com.runwaysdk.query.BasicCondition NE(dss.vector.solutions.MDSSUser mDSSUser)
    {
      if(mDSSUser == null) return this.NE((java.lang.String)null);
      return this.NE(mDSSUser.getId());
    }

  public com.runwaysdk.query.SelectableChar getGeoRoot()
  {
    return getGeoRoot(null);

  }
 
  public com.runwaysdk.query.SelectableChar getGeoRoot(String alias)
  {
    return (com.runwaysdk.query.SelectableChar)this.get(dss.vector.solutions.MDSSUser.GEOROOT, alias, null);

  }
 
  public com.runwaysdk.query.SelectableChar getGeoRoot(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableChar)this.get(dss.vector.solutions.MDSSUser.GEOROOT, alias, displayLabel);

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

  public com.runwaysdk.query.Condition persistedQueries()
  {
    com.runwaysdk.query.QueryFactory queryFactory = this.getQueryFactory();
    com.runwaysdk.business.RelationshipQuery relationshipQuery = queryFactory.relationshipQuery(dss.vector.solutions.query.PersistsSearch.CLASS);

    return this.isParentIn(relationshipQuery);
  }


  public com.runwaysdk.query.Condition SUBSELECT_persistedQueries()
  {
    com.runwaysdk.query.QueryFactory queryFactory = this.getQueryFactory();
    com.runwaysdk.business.RelationshipQuery relationshipQuery = queryFactory.relationshipQuery(dss.vector.solutions.query.PersistsSearch.CLASS);

    return this.isParentIn_SUBSELECT(relationshipQuery);
  }

  public com.runwaysdk.query.Condition persistedQueries(dss.vector.solutions.query.PersistsSearchQuery persistsSearchQuery)
  {
    return this.isParentIn(persistsSearchQuery);
  }

  public com.runwaysdk.query.Condition SUBSELECT_persistedQueries(dss.vector.solutions.query.PersistsSearchQuery persistsSearchQuery)
  {
    return this.isParentIn_SUBSELECT(persistsSearchQuery);
  }

  public com.runwaysdk.query.Condition persistedQueries(dss.vector.solutions.query.SavedSearchQuery savedSearchQuery)
  {
    com.runwaysdk.query.QueryFactory queryFactory = this.getQueryFactory();
    com.runwaysdk.business.RelationshipQuery relationshipQuery = queryFactory.relationshipQuery(dss.vector.solutions.query.PersistsSearch.CLASS);
    relationshipQuery.AND(relationshipQuery.hasChild(savedSearchQuery));

    return this.isParentIn(relationshipQuery);
  }

  public com.runwaysdk.query.Condition SUBSELECT_persistedQueries(dss.vector.solutions.query.SavedSearchQuery savedSearchQuery)
  {
    com.runwaysdk.query.QueryFactory queryFactory = this.getQueryFactory();
    com.runwaysdk.business.RelationshipQuery relationshipQuery = queryFactory.relationshipQuery(dss.vector.solutions.query.PersistsSearch.CLASS);
    relationshipQuery.AND(relationshipQuery.hasChild(savedSearchQuery));

    return this.isParentIn_SUBSELECT(relationshipQuery);
  }

  public com.runwaysdk.query.Condition persistedQueries(dss.vector.solutions.query.SavedSearchQuery savedSearchQuery, dss.vector.solutions.query.PersistsSearchQuery persistsSearchQuery)
  {
    persistsSearchQuery.AND(persistsSearchQuery.hasChild(savedSearchQuery));
    return this.isParentIn(persistsSearchQuery);
  }

  public com.runwaysdk.query.Condition SUBSELECT_persistedQueries(dss.vector.solutions.query.SavedSearchQuery savedSearchQuery, dss.vector.solutions.query.PersistsSearchQuery persistsSearchQuery)
  {
    persistsSearchQuery.AND(persistsSearchQuery.hasChild(savedSearchQuery));
    return this.isParentIn_SUBSELECT(persistsSearchQuery);
  }


  public com.runwaysdk.query.Condition NOT_IN_persistedQueries()
  {
    com.runwaysdk.query.QueryFactory queryFactory = this.getQueryFactory();
    com.runwaysdk.business.RelationshipQuery relationshipQuery = queryFactory.relationshipQuery(dss.vector.solutions.query.PersistsSearch.CLASS);

    return this.isNotParentIn(relationshipQuery);
  }


  public com.runwaysdk.query.Condition SUBSELECT_NOT_IN_persistedQueries()
  {
    com.runwaysdk.query.QueryFactory queryFactory = this.getQueryFactory();
    com.runwaysdk.business.RelationshipQuery relationshipQuery = queryFactory.relationshipQuery(dss.vector.solutions.query.PersistsSearch.CLASS);

    return this.isNotParentIn_SUBSELECT(relationshipQuery);
  }

  public com.runwaysdk.query.Condition NOT_IN_persistedQueries(dss.vector.solutions.query.PersistsSearchQuery persistsSearchQuery)
  {
    return this.isNotParentIn(persistsSearchQuery);
  }

  public com.runwaysdk.query.Condition SUBSELECT_NOT_IN_persistedQueries(dss.vector.solutions.query.PersistsSearchQuery persistsSearchQuery)
  {
    return this.isNotParentIn_SUBSELECT(persistsSearchQuery);
  }

  public com.runwaysdk.query.Condition NOT_IN_persistedQueries(dss.vector.solutions.query.SavedSearchQuery savedSearchQuery)
  {
    com.runwaysdk.query.QueryFactory queryFactory = this.getQueryFactory();
    com.runwaysdk.business.RelationshipQuery relationshipQuery = queryFactory.relationshipQuery(dss.vector.solutions.query.PersistsSearch.CLASS);
    relationshipQuery.AND(relationshipQuery.hasChild(savedSearchQuery));

    return this.isNotParentIn(relationshipQuery);
  }

  public com.runwaysdk.query.Condition SUBSELECT_NOT_IN_persistedQueries(dss.vector.solutions.query.SavedSearchQuery savedSearchQuery)
  {
    com.runwaysdk.query.QueryFactory queryFactory = this.getQueryFactory();
    com.runwaysdk.business.RelationshipQuery relationshipQuery = queryFactory.relationshipQuery(dss.vector.solutions.query.PersistsSearch.CLASS);
    relationshipQuery.AND(relationshipQuery.hasChild(savedSearchQuery));

    return this.isNotParentIn_SUBSELECT(relationshipQuery);
  }

  public com.runwaysdk.query.Condition NOT_IN_persistedQueries(dss.vector.solutions.query.SavedSearchQuery savedSearchQuery, dss.vector.solutions.query.PersistsSearchQuery persistsSearchQuery)
  {
    persistsSearchQuery.AND(persistsSearchQuery.hasChild(savedSearchQuery));
    return this.isNotParentIn(persistsSearchQuery);
  }

  public com.runwaysdk.query.Condition SUBSELECT_NOT_IN_persistedQueries(dss.vector.solutions.query.SavedSearchQuery savedSearchQuery, dss.vector.solutions.query.PersistsSearchQuery persistsSearchQuery)
  {
    persistsSearchQuery.AND(persistsSearchQuery.hasChild(savedSearchQuery));
    return this.isNotParentIn_SUBSELECT(persistsSearchQuery);
  }

  protected com.runwaysdk.query.AttributeReference referenceFactory( com.runwaysdk.dataaccess.MdAttributeRefDAOIF mdAttributeIF, String attributeNamespace, String definingTableName, String definingTableAlias,  com.runwaysdk.dataaccess.MdBusinessDAOIF referenceMdBusinessIF, String referenceTableAlias, com.runwaysdk.query.ComponentQuery rootQuery, java.util.Set<com.runwaysdk.query.Join> tableJoinSet, String userDefinedAlias, String userDefinedDisplayLabel)
  {
    String name = mdAttributeIF.definesAttribute();
    
    if (name.equals(dss.vector.solutions.MDSSUser.PERSON)) 
    {
       return new dss.vector.solutions.PersonQuery.PersonQueryReference((com.runwaysdk.dataaccess.MdAttributeRefDAOIF)mdAttributeIF, attributeNamespace, definingTableName, definingTableAlias, referenceMdBusinessIF, referenceTableAlias, rootQuery, tableJoinSet, userDefinedAlias, userDefinedDisplayLabel);
    }
    else if (name.equals(dss.vector.solutions.MDSSUser.ROOTGEOENTITY)) 
    {
       return new dss.vector.solutions.geo.generated.GeoEntityQuery.GeoEntityQueryReference((com.runwaysdk.dataaccess.MdAttributeRefDAOIF)mdAttributeIF, attributeNamespace, definingTableName, definingTableAlias, referenceMdBusinessIF, referenceTableAlias, rootQuery, tableJoinSet, userDefinedAlias, userDefinedDisplayLabel);
    }
    else 
    {
      return super.referenceFactory(mdAttributeIF, attributeNamespace, definingTableName, definingTableAlias, referenceMdBusinessIF, referenceTableAlias, rootQuery, tableJoinSet, userDefinedAlias, userDefinedDisplayLabel);
    }
  }

  }

/**
 * Interface that masks all type unsafe query methods and defines all type safe methods.
 * This type is used when a join is performed on this class as a reference.
 **/
  public interface MDSSUserQueryMultiReferenceIF extends com.runwaysdk.generation.loader.Reloadable, com.runwaysdk.system.UsersQuery.UsersQueryMultiReferenceIF
  {

    public com.runwaysdk.query.SelectableChar getGeoRoot();
    public com.runwaysdk.query.SelectableChar getGeoRoot(String alias);
    public com.runwaysdk.query.SelectableChar getGeoRoot(String alias, String displayLabel);
    public dss.vector.solutions.PersonQuery.PersonQueryReferenceIF getPerson();
    public dss.vector.solutions.PersonQuery.PersonQueryReferenceIF getPerson(String alias);
    public dss.vector.solutions.PersonQuery.PersonQueryReferenceIF getPerson(String alias, String displayLabel);
    public dss.vector.solutions.geo.generated.GeoEntityQuery.GeoEntityQueryReferenceIF getRootGeoEntity();
    public dss.vector.solutions.geo.generated.GeoEntityQuery.GeoEntityQueryReferenceIF getRootGeoEntity(String alias);
    public dss.vector.solutions.geo.generated.GeoEntityQuery.GeoEntityQueryReferenceIF getRootGeoEntity(String alias, String displayLabel);

    public com.runwaysdk.query.Condition containsAny(dss.vector.solutions.MDSSUser ... mDSSUser);
    public com.runwaysdk.query.Condition notContainsAny(dss.vector.solutions.MDSSUser ... mDSSUser);
    public com.runwaysdk.query.Condition containsAll(dss.vector.solutions.MDSSUser ... mDSSUser);
    public com.runwaysdk.query.Condition notContainsAll(dss.vector.solutions.MDSSUser ... mDSSUser);
    public com.runwaysdk.query.Condition containsExactly(dss.vector.solutions.MDSSUser ... mDSSUser);
  }

/**
 * Implements type safe query methods.
 * This type is used when a join is performed on this class as a reference.
 **/
  public static class MDSSUserQueryMultiReference extends com.runwaysdk.system.UsersQuery.UsersQueryMultiReference
 implements MDSSUserQueryMultiReferenceIF
, com.runwaysdk.generation.loader.Reloadable
  {

  public MDSSUserQueryMultiReference(com.runwaysdk.dataaccess.MdAttributeMultiReferenceDAOIF mdAttributeIF, String attributeNamespace, String definingTableName, String definingTableAlias, String mdMultiReferenceTableName, com.runwaysdk.dataaccess.MdBusinessDAOIF referenceMdBusinessIF, String referenceTableAlias, com.runwaysdk.query.ComponentQuery rootQuery, java.util.Set<com.runwaysdk.query.Join> tableJoinSet, String alias, String displayLabel)
  {
    super(mdAttributeIF, attributeNamespace, definingTableName, definingTableAlias, mdMultiReferenceTableName, referenceMdBusinessIF, referenceTableAlias, rootQuery, tableJoinSet, alias, displayLabel);

  }



    public com.runwaysdk.query.Condition containsAny(dss.vector.solutions.MDSSUser ... mDSSUser)  {

      String[] itemIdArray = new String[mDSSUser.length]; 

      for (int i=0; i<mDSSUser.length; i++)
      {
        itemIdArray[i] = mDSSUser[i].getId();
      }

      return this.containsAny(itemIdArray);
  }

    public com.runwaysdk.query.Condition notContainsAny(dss.vector.solutions.MDSSUser ... mDSSUser)  {

      String[] itemIdArray = new String[mDSSUser.length]; 

      for (int i=0; i<mDSSUser.length; i++)
      {
        itemIdArray[i] = mDSSUser[i].getId();
      }

      return this.notContainsAny(itemIdArray);
  }

    public com.runwaysdk.query.Condition containsAll(dss.vector.solutions.MDSSUser ... mDSSUser)  {

      String[] itemIdArray = new String[mDSSUser.length]; 

      for (int i=0; i<mDSSUser.length; i++)
      {
        itemIdArray[i] = mDSSUser[i].getId();
      }

      return this.containsAll(itemIdArray);
  }

    public com.runwaysdk.query.Condition notContainsAll(dss.vector.solutions.MDSSUser ... mDSSUser)  {

      String[] itemIdArray = new String[mDSSUser.length]; 

      for (int i=0; i<mDSSUser.length; i++)
      {
        itemIdArray[i] = mDSSUser[i].getId();
      }

      return this.notContainsAll(itemIdArray);
  }

    public com.runwaysdk.query.Condition containsExactly(dss.vector.solutions.MDSSUser ... mDSSUser)  {

      String[] itemIdArray = new String[mDSSUser.length]; 

      for (int i=0; i<mDSSUser.length; i++)
      {
        itemIdArray[i] = mDSSUser[i].getId();
      }

      return this.containsExactly(itemIdArray);
  }
  public com.runwaysdk.query.SelectableChar getGeoRoot()
  {
    return getGeoRoot(null);

  }
 
  public com.runwaysdk.query.SelectableChar getGeoRoot(String alias)
  {
    return (com.runwaysdk.query.SelectableChar)this.get(dss.vector.solutions.MDSSUser.GEOROOT, alias, null);

  }
 
  public com.runwaysdk.query.SelectableChar getGeoRoot(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableChar)this.get(dss.vector.solutions.MDSSUser.GEOROOT, alias, displayLabel);

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
  protected com.runwaysdk.query.AttributeReference referenceFactory( com.runwaysdk.dataaccess.MdAttributeRefDAOIF mdAttributeIF, String attributeNamespace, String definingTableName, String definingTableAlias,  com.runwaysdk.dataaccess.MdBusinessDAOIF referenceMdBusinessIF, String referenceTableAlias, com.runwaysdk.query.ComponentQuery rootQuery, java.util.Set<com.runwaysdk.query.Join> tableJoinSet, String userDefinedAlias, String userDefinedDisplayLabel)
  {
    String name = mdAttributeIF.definesAttribute();
    
    if (name.equals(dss.vector.solutions.MDSSUser.PERSON)) 
    {
       return new dss.vector.solutions.PersonQuery.PersonQueryReference((com.runwaysdk.dataaccess.MdAttributeRefDAOIF)mdAttributeIF, attributeNamespace, definingTableName, definingTableAlias, referenceMdBusinessIF, referenceTableAlias, rootQuery, tableJoinSet, userDefinedAlias, userDefinedDisplayLabel);
    }
    else if (name.equals(dss.vector.solutions.MDSSUser.ROOTGEOENTITY)) 
    {
       return new dss.vector.solutions.geo.generated.GeoEntityQuery.GeoEntityQueryReference((com.runwaysdk.dataaccess.MdAttributeRefDAOIF)mdAttributeIF, attributeNamespace, definingTableName, definingTableAlias, referenceMdBusinessIF, referenceTableAlias, rootQuery, tableJoinSet, userDefinedAlias, userDefinedDisplayLabel);
    }
    else 
    {
      return super.referenceFactory(mdAttributeIF, attributeNamespace, definingTableName, definingTableAlias, referenceMdBusinessIF, referenceTableAlias, rootQuery, tableJoinSet, userDefinedAlias, userDefinedDisplayLabel);
    }
  }

  }
}
