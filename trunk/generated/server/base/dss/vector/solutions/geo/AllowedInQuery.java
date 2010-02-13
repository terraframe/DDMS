package dss.vector.solutions.geo;

@com.terraframe.mojo.business.ClassSignature(hash = 893286486)
/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to AllowedIn.java
 *
 * @author Autogenerated by TerraFrame
 */
public  class AllowedInQuery extends com.terraframe.mojo.query.GeneratedRelationshipQuery implements com.terraframe.mojo.generation.loader.Reloadable
{
private static final long serialVersionUID = 893286486;

  public AllowedInQuery(com.terraframe.mojo.query.QueryFactory componentQueryFactory)
  {
     super();
    if (this.getComponentQuery() == null)
    {
      com.terraframe.mojo.business.RelationshipQuery relationshipQuery = componentQueryFactory.relationshipQuery(this.getClassType());

       this.setRelationshipQuery(relationshipQuery);
    }
  }

  public AllowedInQuery(com.terraframe.mojo.query.ValueQuery valueQuery)
  {
     super();
    if (this.getComponentQuery() == null)
    {
      com.terraframe.mojo.business.RelationshipQuery relationshipQuery = new com.terraframe.mojo.business.RelationshipQuery(valueQuery, this.getClassType());

       this.setRelationshipQuery(relationshipQuery);
    }
  }

  public String getClassType()
  {
    return dss.vector.solutions.geo.AllowedIn.CLASS;
  }
  /**
   * Restricts the query to include objects that are children in this relationship.
   * @param geoHierarchyQuery
   * @return Condition restricting objects that are children in this relationship.
   */
   public com.terraframe.mojo.query.Condition hasChild(dss.vector.solutions.geo.GeoHierarchyQuery geoHierarchyQuery)
   {
     return this.getRelationshipQuery().hasChild(geoHierarchyQuery);
   }
  /**
   * Restricts the query to include objects that are children in this relationship.
   * @param geoHierarchyQuery
   * @return Condition restricting objects that are children in this relationship.
   */
   public com.terraframe.mojo.query.Condition doesNotHaveChild(dss.vector.solutions.geo.GeoHierarchyQuery geoHierarchyQuery)
   {
     return this.getRelationshipQuery().doesNotHaveChild(geoHierarchyQuery);
   }
  /**
   * Restricts the query to include objects that are parents in this relationship.
   * @param geoHierarchyQuery
   * @return Condition restricting objects that are parents in this relationship.
   */
   public com.terraframe.mojo.query.Condition hasParent(dss.vector.solutions.geo.GeoHierarchyQuery geoHierarchyQuery)
   {
     return this.getRelationshipQuery().hasParent(geoHierarchyQuery);
   }
  /**
   * Restricts the query to include objects that are parents in this relationship.
   * @param geoHierarchyQuery
   * @return Condition restricting objects that are parents in this relationship.
   */
   public com.terraframe.mojo.query.Condition doesNotHaveParent(dss.vector.solutions.geo.GeoHierarchyQuery geoHierarchyQuery)
   {
     return this.getRelationshipQuery().doesNotHaveParent(geoHierarchyQuery);
   }
  protected com.terraframe.mojo.query.AttributeReference referenceFactory( com.terraframe.mojo.dataaccess.MdAttributeRefDAOIF mdAttributeIF, String attributeNamespace, String definingTableName, String definingTableAlias,  com.terraframe.mojo.dataaccess.MdBusinessDAOIF referenceMdBusinessIF, String referenceTableAlias, com.terraframe.mojo.query.ComponentQuery rootQuery, java.util.Set<com.terraframe.mojo.query.Join> tableJoinSet, String userDefinedAlias, String userDefinedDisplayLabel)
  {
    String name = mdAttributeIF.definesAttribute();
    
    if (name.equals("createdBy")) 
    {
       return new com.terraframe.mojo.system.SingleActorQuery.SingleActorQueryReference((com.terraframe.mojo.dataaccess.MdAttributeRefDAOIF)mdAttributeIF, attributeNamespace, definingTableName, definingTableAlias, referenceMdBusinessIF, referenceTableAlias, rootQuery, tableJoinSet, userDefinedAlias, userDefinedDisplayLabel);
    }
    else if (name.equals("entityDomain")) 
    {
       return new com.terraframe.mojo.system.metadata.MdDomainQuery.MdDomainQueryReference((com.terraframe.mojo.dataaccess.MdAttributeRefDAOIF)mdAttributeIF, attributeNamespace, definingTableName, definingTableAlias, referenceMdBusinessIF, referenceTableAlias, rootQuery, tableJoinSet, userDefinedAlias, userDefinedDisplayLabel);
    }
    else if (name.equals("lastUpdatedBy")) 
    {
       return new com.terraframe.mojo.system.SingleActorQuery.SingleActorQueryReference((com.terraframe.mojo.dataaccess.MdAttributeRefDAOIF)mdAttributeIF, attributeNamespace, definingTableName, definingTableAlias, referenceMdBusinessIF, referenceTableAlias, rootQuery, tableJoinSet, userDefinedAlias, userDefinedDisplayLabel);
    }
    else if (name.equals("lockedBy")) 
    {
       return new com.terraframe.mojo.system.UsersQuery.UsersQueryReference((com.terraframe.mojo.dataaccess.MdAttributeRefDAOIF)mdAttributeIF, attributeNamespace, definingTableName, definingTableAlias, referenceMdBusinessIF, referenceTableAlias, rootQuery, tableJoinSet, userDefinedAlias, userDefinedDisplayLabel);
    }
    else if (name.equals("owner")) 
    {
       return new com.terraframe.mojo.system.ActorQuery.ActorQueryReference((com.terraframe.mojo.dataaccess.MdAttributeRefDAOIF)mdAttributeIF, attributeNamespace, definingTableName, definingTableAlias, referenceMdBusinessIF, referenceTableAlias, rootQuery, tableJoinSet, userDefinedAlias, userDefinedDisplayLabel);
    }
    else 
    {
      String error = "Attribute type ["+mdAttributeIF.getType()+"] is invalid.";
      throw new com.terraframe.mojo.query.QueryException(error);
    }
  }

  public com.terraframe.mojo.query.SelectableSingleMoment getCreateDate()
  {
    return getCreateDate(null);

  }
 
  public com.terraframe.mojo.query.SelectableSingleMoment getCreateDate(String alias)
  {
    return (com.terraframe.mojo.query.SelectableSingleMoment)this.getComponentQuery().get(dss.vector.solutions.geo.AllowedIn.CREATEDATE, alias, null);

  }
 
  public com.terraframe.mojo.query.SelectableSingleMoment getCreateDate(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.SelectableSingleMoment)this.getComponentQuery().get(dss.vector.solutions.geo.AllowedIn.CREATEDATE, alias, displayLabel);

  }
  public com.terraframe.mojo.system.SingleActorQuery.SingleActorQueryReferenceIF getCreatedBy()
  {
    return getCreatedBy(null);

  }
 
  public com.terraframe.mojo.system.SingleActorQuery.SingleActorQueryReferenceIF getCreatedBy(String alias)
  {

    com.terraframe.mojo.dataaccess.MdAttributeDAOIF mdAttributeIF = this.getComponentQuery().getMdAttributeROfromMap("createdBy");

    return (com.terraframe.mojo.system.SingleActorQuery.SingleActorQueryReferenceIF)this.getComponentQuery().internalAttributeFactory(dss.vector.solutions.geo.AllowedIn.CREATEDBY, mdAttributeIF, this, alias, null);

  }
 
  public com.terraframe.mojo.system.SingleActorQuery.SingleActorQueryReferenceIF getCreatedBy(String alias, String displayLabel)
  {

    com.terraframe.mojo.dataaccess.MdAttributeDAOIF mdAttributeIF = this.getComponentQuery().getMdAttributeROfromMap("createdBy");

    return (com.terraframe.mojo.system.SingleActorQuery.SingleActorQueryReferenceIF)this.getComponentQuery().internalAttributeFactory(dss.vector.solutions.geo.AllowedIn.CREATEDBY, mdAttributeIF, this, alias, displayLabel);

  }
  public com.terraframe.mojo.system.metadata.MdDomainQuery.MdDomainQueryReferenceIF getEntityDomain()
  {
    return getEntityDomain(null);

  }
 
  public com.terraframe.mojo.system.metadata.MdDomainQuery.MdDomainQueryReferenceIF getEntityDomain(String alias)
  {

    com.terraframe.mojo.dataaccess.MdAttributeDAOIF mdAttributeIF = this.getComponentQuery().getMdAttributeROfromMap("entityDomain");

    return (com.terraframe.mojo.system.metadata.MdDomainQuery.MdDomainQueryReferenceIF)this.getComponentQuery().internalAttributeFactory(dss.vector.solutions.geo.AllowedIn.ENTITYDOMAIN, mdAttributeIF, this, alias, null);

  }
 
  public com.terraframe.mojo.system.metadata.MdDomainQuery.MdDomainQueryReferenceIF getEntityDomain(String alias, String displayLabel)
  {

    com.terraframe.mojo.dataaccess.MdAttributeDAOIF mdAttributeIF = this.getComponentQuery().getMdAttributeROfromMap("entityDomain");

    return (com.terraframe.mojo.system.metadata.MdDomainQuery.MdDomainQueryReferenceIF)this.getComponentQuery().internalAttributeFactory(dss.vector.solutions.geo.AllowedIn.ENTITYDOMAIN, mdAttributeIF, this, alias, displayLabel);

  }
  public com.terraframe.mojo.query.SelectableSingleChar getId()
  {
    return getId(null);

  }
 
  public com.terraframe.mojo.query.SelectableSingleChar getId(String alias)
  {
    return (com.terraframe.mojo.query.SelectableSingleChar)this.getComponentQuery().get(dss.vector.solutions.geo.AllowedIn.ID, alias, null);

  }
 
  public com.terraframe.mojo.query.SelectableSingleChar getId(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.SelectableSingleChar)this.getComponentQuery().get(dss.vector.solutions.geo.AllowedIn.ID, alias, displayLabel);

  }
  public com.terraframe.mojo.query.SelectableSingleChar getKeyName()
  {
    return getKeyName(null);

  }
 
  public com.terraframe.mojo.query.SelectableSingleChar getKeyName(String alias)
  {
    return (com.terraframe.mojo.query.SelectableSingleChar)this.getComponentQuery().get(dss.vector.solutions.geo.AllowedIn.KEYNAME, alias, null);

  }
 
  public com.terraframe.mojo.query.SelectableSingleChar getKeyName(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.SelectableSingleChar)this.getComponentQuery().get(dss.vector.solutions.geo.AllowedIn.KEYNAME, alias, displayLabel);

  }
  public com.terraframe.mojo.query.SelectableSingleMoment getLastUpdateDate()
  {
    return getLastUpdateDate(null);

  }
 
  public com.terraframe.mojo.query.SelectableSingleMoment getLastUpdateDate(String alias)
  {
    return (com.terraframe.mojo.query.SelectableSingleMoment)this.getComponentQuery().get(dss.vector.solutions.geo.AllowedIn.LASTUPDATEDATE, alias, null);

  }
 
  public com.terraframe.mojo.query.SelectableSingleMoment getLastUpdateDate(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.SelectableSingleMoment)this.getComponentQuery().get(dss.vector.solutions.geo.AllowedIn.LASTUPDATEDATE, alias, displayLabel);

  }
  public com.terraframe.mojo.system.SingleActorQuery.SingleActorQueryReferenceIF getLastUpdatedBy()
  {
    return getLastUpdatedBy(null);

  }
 
  public com.terraframe.mojo.system.SingleActorQuery.SingleActorQueryReferenceIF getLastUpdatedBy(String alias)
  {

    com.terraframe.mojo.dataaccess.MdAttributeDAOIF mdAttributeIF = this.getComponentQuery().getMdAttributeROfromMap("lastUpdatedBy");

    return (com.terraframe.mojo.system.SingleActorQuery.SingleActorQueryReferenceIF)this.getComponentQuery().internalAttributeFactory(dss.vector.solutions.geo.AllowedIn.LASTUPDATEDBY, mdAttributeIF, this, alias, null);

  }
 
  public com.terraframe.mojo.system.SingleActorQuery.SingleActorQueryReferenceIF getLastUpdatedBy(String alias, String displayLabel)
  {

    com.terraframe.mojo.dataaccess.MdAttributeDAOIF mdAttributeIF = this.getComponentQuery().getMdAttributeROfromMap("lastUpdatedBy");

    return (com.terraframe.mojo.system.SingleActorQuery.SingleActorQueryReferenceIF)this.getComponentQuery().internalAttributeFactory(dss.vector.solutions.geo.AllowedIn.LASTUPDATEDBY, mdAttributeIF, this, alias, displayLabel);

  }
  public com.terraframe.mojo.system.UsersQuery.UsersQueryReferenceIF getLockedBy()
  {
    return getLockedBy(null);

  }
 
  public com.terraframe.mojo.system.UsersQuery.UsersQueryReferenceIF getLockedBy(String alias)
  {

    com.terraframe.mojo.dataaccess.MdAttributeDAOIF mdAttributeIF = this.getComponentQuery().getMdAttributeROfromMap("lockedBy");

    return (com.terraframe.mojo.system.UsersQuery.UsersQueryReferenceIF)this.getComponentQuery().internalAttributeFactory(dss.vector.solutions.geo.AllowedIn.LOCKEDBY, mdAttributeIF, this, alias, null);

  }
 
  public com.terraframe.mojo.system.UsersQuery.UsersQueryReferenceIF getLockedBy(String alias, String displayLabel)
  {

    com.terraframe.mojo.dataaccess.MdAttributeDAOIF mdAttributeIF = this.getComponentQuery().getMdAttributeROfromMap("lockedBy");

    return (com.terraframe.mojo.system.UsersQuery.UsersQueryReferenceIF)this.getComponentQuery().internalAttributeFactory(dss.vector.solutions.geo.AllowedIn.LOCKEDBY, mdAttributeIF, this, alias, displayLabel);

  }
  public com.terraframe.mojo.system.ActorQuery.ActorQueryReferenceIF getOwner()
  {
    return getOwner(null);

  }
 
  public com.terraframe.mojo.system.ActorQuery.ActorQueryReferenceIF getOwner(String alias)
  {

    com.terraframe.mojo.dataaccess.MdAttributeDAOIF mdAttributeIF = this.getComponentQuery().getMdAttributeROfromMap("owner");

    return (com.terraframe.mojo.system.ActorQuery.ActorQueryReferenceIF)this.getComponentQuery().internalAttributeFactory(dss.vector.solutions.geo.AllowedIn.OWNER, mdAttributeIF, this, alias, null);

  }
 
  public com.terraframe.mojo.system.ActorQuery.ActorQueryReferenceIF getOwner(String alias, String displayLabel)
  {

    com.terraframe.mojo.dataaccess.MdAttributeDAOIF mdAttributeIF = this.getComponentQuery().getMdAttributeROfromMap("owner");

    return (com.terraframe.mojo.system.ActorQuery.ActorQueryReferenceIF)this.getComponentQuery().internalAttributeFactory(dss.vector.solutions.geo.AllowedIn.OWNER, mdAttributeIF, this, alias, displayLabel);

  }
  public com.terraframe.mojo.query.SelectableSingleLong getSeq()
  {
    return getSeq(null);

  }
 
  public com.terraframe.mojo.query.SelectableSingleLong getSeq(String alias)
  {
    return (com.terraframe.mojo.query.SelectableSingleLong)this.getComponentQuery().get(dss.vector.solutions.geo.AllowedIn.SEQ, alias, null);

  }
 
  public com.terraframe.mojo.query.SelectableSingleLong getSeq(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.SelectableSingleLong)this.getComponentQuery().get(dss.vector.solutions.geo.AllowedIn.SEQ, alias, displayLabel);

  }
  public com.terraframe.mojo.query.SelectableSingleChar getSiteMaster()
  {
    return getSiteMaster(null);

  }
 
  public com.terraframe.mojo.query.SelectableSingleChar getSiteMaster(String alias)
  {
    return (com.terraframe.mojo.query.SelectableSingleChar)this.getComponentQuery().get(dss.vector.solutions.geo.AllowedIn.SITEMASTER, alias, null);

  }
 
  public com.terraframe.mojo.query.SelectableSingleChar getSiteMaster(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.SelectableSingleChar)this.getComponentQuery().get(dss.vector.solutions.geo.AllowedIn.SITEMASTER, alias, displayLabel);

  }
  public com.terraframe.mojo.query.SelectableSingleChar getType()
  {
    return getType(null);

  }
 
  public com.terraframe.mojo.query.SelectableSingleChar getType(String alias)
  {
    return (com.terraframe.mojo.query.SelectableSingleChar)this.getComponentQuery().get(dss.vector.solutions.geo.AllowedIn.TYPE, alias, null);

  }
 
  public com.terraframe.mojo.query.SelectableSingleChar getType(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.SelectableSingleChar)this.getComponentQuery().get(dss.vector.solutions.geo.AllowedIn.TYPE, alias, displayLabel);

  }
  /**  
   * Returns an iterator of Business objects that match the query criteria specified
   * on this query object. 
   * @return iterator of Business objects that match the query criteria specified
   * on this query object.
   */
  public com.terraframe.mojo.query.OIterator<? extends AllowedIn> getIterator()
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
    return new com.terraframe.mojo.business.RelationshipIterator<AllowedIn>(this.getComponentQuery().getMdEntityIF(), this.getRelationshipQuery(), columnInfoMap, results);
  }

}
