package dss.vector.solutions.surveillance;

@com.terraframe.mojo.business.ClassSignature(hash = 1753669920)
/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to CaseReferral.java
 *
 * @author Autogenerated by TerraFrame
 */
public  class CaseReferralQuery extends com.terraframe.mojo.query.GeneratedRelationshipQuery implements com.terraframe.mojo.generation.loader.Reloadable
{
private static final long serialVersionUID = 1753669920;

  public CaseReferralQuery(com.terraframe.mojo.query.QueryFactory componentQueryFactory)
  {
     super();
    if (this.getComponentQuery() == null)
    {
      com.terraframe.mojo.business.RelationshipQuery relationshipQuery = componentQueryFactory.relationshipQuery(this.getClassType());

       this.setRelationshipQuery(relationshipQuery);
    }
  }

  public CaseReferralQuery(com.terraframe.mojo.query.ValueQuery valueQuery)
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
    return dss.vector.solutions.surveillance.CaseReferral.CLASS;
  }
  /**
   * Restricts the query to include objects that are children in this relationship.
   * @param termQuery
   * @return Condition restricting objects that are children in this relationship.
   */
   public com.terraframe.mojo.query.Condition hasChild(dss.vector.solutions.ontology.TermQuery termQuery)
   {
     return this.getRelationshipQuery().hasChild(termQuery);
   }
  /**
   * Restricts the query to include objects that are children in this relationship.
   * @param termQuery
   * @return Condition restricting objects that are children in this relationship.
   */
   public com.terraframe.mojo.query.Condition doesNotHaveChild(dss.vector.solutions.ontology.TermQuery termQuery)
   {
     return this.getRelationshipQuery().doesNotHaveChild(termQuery);
   }
  /**
   * Restricts the query to include objects that are parents in this relationship.
   * @param aggregatedCaseQuery
   * @return Condition restricting objects that are parents in this relationship.
   */
   public com.terraframe.mojo.query.Condition hasParent(dss.vector.solutions.surveillance.AggregatedCaseQuery aggregatedCaseQuery)
   {
     return this.getRelationshipQuery().hasParent(aggregatedCaseQuery);
   }
  /**
   * Restricts the query to include objects that are parents in this relationship.
   * @param aggregatedCaseQuery
   * @return Condition restricting objects that are parents in this relationship.
   */
   public com.terraframe.mojo.query.Condition doesNotHaveParent(dss.vector.solutions.surveillance.AggregatedCaseQuery aggregatedCaseQuery)
   {
     return this.getRelationshipQuery().doesNotHaveParent(aggregatedCaseQuery);
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

  public com.terraframe.mojo.query.SelectableSingleInteger getAmount()
  {
    return getAmount(null);

  }
 
  public com.terraframe.mojo.query.SelectableSingleInteger getAmount(String alias)
  {
    return (com.terraframe.mojo.query.SelectableSingleInteger)this.getComponentQuery().get(dss.vector.solutions.surveillance.CaseReferral.AMOUNT, alias, null);

  }
 
  public com.terraframe.mojo.query.SelectableSingleInteger getAmount(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.SelectableSingleInteger)this.getComponentQuery().get(dss.vector.solutions.surveillance.CaseReferral.AMOUNT, alias, displayLabel);

  }
  public com.terraframe.mojo.query.SelectableSingleMoment getCreateDate()
  {
    return getCreateDate(null);

  }
 
  public com.terraframe.mojo.query.SelectableSingleMoment getCreateDate(String alias)
  {
    return (com.terraframe.mojo.query.SelectableSingleMoment)this.getComponentQuery().get(dss.vector.solutions.surveillance.CaseReferral.CREATEDATE, alias, null);

  }
 
  public com.terraframe.mojo.query.SelectableSingleMoment getCreateDate(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.SelectableSingleMoment)this.getComponentQuery().get(dss.vector.solutions.surveillance.CaseReferral.CREATEDATE, alias, displayLabel);

  }
  public com.terraframe.mojo.system.SingleActorQuery.SingleActorQueryReferenceIF getCreatedBy()
  {
    return getCreatedBy(null);

  }
 
  public com.terraframe.mojo.system.SingleActorQuery.SingleActorQueryReferenceIF getCreatedBy(String alias)
  {

    com.terraframe.mojo.dataaccess.MdAttributeDAOIF mdAttributeIF = this.getComponentQuery().getMdAttributeROfromMap("createdBy");

    return (com.terraframe.mojo.system.SingleActorQuery.SingleActorQueryReferenceIF)this.getComponentQuery().internalAttributeFactory(dss.vector.solutions.surveillance.CaseReferral.CREATEDBY, mdAttributeIF, this, alias, null);

  }
 
  public com.terraframe.mojo.system.SingleActorQuery.SingleActorQueryReferenceIF getCreatedBy(String alias, String displayLabel)
  {

    com.terraframe.mojo.dataaccess.MdAttributeDAOIF mdAttributeIF = this.getComponentQuery().getMdAttributeROfromMap("createdBy");

    return (com.terraframe.mojo.system.SingleActorQuery.SingleActorQueryReferenceIF)this.getComponentQuery().internalAttributeFactory(dss.vector.solutions.surveillance.CaseReferral.CREATEDBY, mdAttributeIF, this, alias, displayLabel);

  }
  public com.terraframe.mojo.system.metadata.MdDomainQuery.MdDomainQueryReferenceIF getEntityDomain()
  {
    return getEntityDomain(null);

  }
 
  public com.terraframe.mojo.system.metadata.MdDomainQuery.MdDomainQueryReferenceIF getEntityDomain(String alias)
  {

    com.terraframe.mojo.dataaccess.MdAttributeDAOIF mdAttributeIF = this.getComponentQuery().getMdAttributeROfromMap("entityDomain");

    return (com.terraframe.mojo.system.metadata.MdDomainQuery.MdDomainQueryReferenceIF)this.getComponentQuery().internalAttributeFactory(dss.vector.solutions.surveillance.CaseReferral.ENTITYDOMAIN, mdAttributeIF, this, alias, null);

  }
 
  public com.terraframe.mojo.system.metadata.MdDomainQuery.MdDomainQueryReferenceIF getEntityDomain(String alias, String displayLabel)
  {

    com.terraframe.mojo.dataaccess.MdAttributeDAOIF mdAttributeIF = this.getComponentQuery().getMdAttributeROfromMap("entityDomain");

    return (com.terraframe.mojo.system.metadata.MdDomainQuery.MdDomainQueryReferenceIF)this.getComponentQuery().internalAttributeFactory(dss.vector.solutions.surveillance.CaseReferral.ENTITYDOMAIN, mdAttributeIF, this, alias, displayLabel);

  }
  public com.terraframe.mojo.query.SelectableSingleChar getId()
  {
    return getId(null);

  }
 
  public com.terraframe.mojo.query.SelectableSingleChar getId(String alias)
  {
    return (com.terraframe.mojo.query.SelectableSingleChar)this.getComponentQuery().get(dss.vector.solutions.surveillance.CaseReferral.ID, alias, null);

  }
 
  public com.terraframe.mojo.query.SelectableSingleChar getId(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.SelectableSingleChar)this.getComponentQuery().get(dss.vector.solutions.surveillance.CaseReferral.ID, alias, displayLabel);

  }
  public com.terraframe.mojo.query.SelectableSingleChar getKeyName()
  {
    return getKeyName(null);

  }
 
  public com.terraframe.mojo.query.SelectableSingleChar getKeyName(String alias)
  {
    return (com.terraframe.mojo.query.SelectableSingleChar)this.getComponentQuery().get(dss.vector.solutions.surveillance.CaseReferral.KEYNAME, alias, null);

  }
 
  public com.terraframe.mojo.query.SelectableSingleChar getKeyName(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.SelectableSingleChar)this.getComponentQuery().get(dss.vector.solutions.surveillance.CaseReferral.KEYNAME, alias, displayLabel);

  }
  public com.terraframe.mojo.query.SelectableSingleMoment getLastUpdateDate()
  {
    return getLastUpdateDate(null);

  }
 
  public com.terraframe.mojo.query.SelectableSingleMoment getLastUpdateDate(String alias)
  {
    return (com.terraframe.mojo.query.SelectableSingleMoment)this.getComponentQuery().get(dss.vector.solutions.surveillance.CaseReferral.LASTUPDATEDATE, alias, null);

  }
 
  public com.terraframe.mojo.query.SelectableSingleMoment getLastUpdateDate(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.SelectableSingleMoment)this.getComponentQuery().get(dss.vector.solutions.surveillance.CaseReferral.LASTUPDATEDATE, alias, displayLabel);

  }
  public com.terraframe.mojo.system.SingleActorQuery.SingleActorQueryReferenceIF getLastUpdatedBy()
  {
    return getLastUpdatedBy(null);

  }
 
  public com.terraframe.mojo.system.SingleActorQuery.SingleActorQueryReferenceIF getLastUpdatedBy(String alias)
  {

    com.terraframe.mojo.dataaccess.MdAttributeDAOIF mdAttributeIF = this.getComponentQuery().getMdAttributeROfromMap("lastUpdatedBy");

    return (com.terraframe.mojo.system.SingleActorQuery.SingleActorQueryReferenceIF)this.getComponentQuery().internalAttributeFactory(dss.vector.solutions.surveillance.CaseReferral.LASTUPDATEDBY, mdAttributeIF, this, alias, null);

  }
 
  public com.terraframe.mojo.system.SingleActorQuery.SingleActorQueryReferenceIF getLastUpdatedBy(String alias, String displayLabel)
  {

    com.terraframe.mojo.dataaccess.MdAttributeDAOIF mdAttributeIF = this.getComponentQuery().getMdAttributeROfromMap("lastUpdatedBy");

    return (com.terraframe.mojo.system.SingleActorQuery.SingleActorQueryReferenceIF)this.getComponentQuery().internalAttributeFactory(dss.vector.solutions.surveillance.CaseReferral.LASTUPDATEDBY, mdAttributeIF, this, alias, displayLabel);

  }
  public com.terraframe.mojo.system.UsersQuery.UsersQueryReferenceIF getLockedBy()
  {
    return getLockedBy(null);

  }
 
  public com.terraframe.mojo.system.UsersQuery.UsersQueryReferenceIF getLockedBy(String alias)
  {

    com.terraframe.mojo.dataaccess.MdAttributeDAOIF mdAttributeIF = this.getComponentQuery().getMdAttributeROfromMap("lockedBy");

    return (com.terraframe.mojo.system.UsersQuery.UsersQueryReferenceIF)this.getComponentQuery().internalAttributeFactory(dss.vector.solutions.surveillance.CaseReferral.LOCKEDBY, mdAttributeIF, this, alias, null);

  }
 
  public com.terraframe.mojo.system.UsersQuery.UsersQueryReferenceIF getLockedBy(String alias, String displayLabel)
  {

    com.terraframe.mojo.dataaccess.MdAttributeDAOIF mdAttributeIF = this.getComponentQuery().getMdAttributeROfromMap("lockedBy");

    return (com.terraframe.mojo.system.UsersQuery.UsersQueryReferenceIF)this.getComponentQuery().internalAttributeFactory(dss.vector.solutions.surveillance.CaseReferral.LOCKEDBY, mdAttributeIF, this, alias, displayLabel);

  }
  public com.terraframe.mojo.system.ActorQuery.ActorQueryReferenceIF getOwner()
  {
    return getOwner(null);

  }
 
  public com.terraframe.mojo.system.ActorQuery.ActorQueryReferenceIF getOwner(String alias)
  {

    com.terraframe.mojo.dataaccess.MdAttributeDAOIF mdAttributeIF = this.getComponentQuery().getMdAttributeROfromMap("owner");

    return (com.terraframe.mojo.system.ActorQuery.ActorQueryReferenceIF)this.getComponentQuery().internalAttributeFactory(dss.vector.solutions.surveillance.CaseReferral.OWNER, mdAttributeIF, this, alias, null);

  }
 
  public com.terraframe.mojo.system.ActorQuery.ActorQueryReferenceIF getOwner(String alias, String displayLabel)
  {

    com.terraframe.mojo.dataaccess.MdAttributeDAOIF mdAttributeIF = this.getComponentQuery().getMdAttributeROfromMap("owner");

    return (com.terraframe.mojo.system.ActorQuery.ActorQueryReferenceIF)this.getComponentQuery().internalAttributeFactory(dss.vector.solutions.surveillance.CaseReferral.OWNER, mdAttributeIF, this, alias, displayLabel);

  }
  public com.terraframe.mojo.query.SelectableSingleLong getSeq()
  {
    return getSeq(null);

  }
 
  public com.terraframe.mojo.query.SelectableSingleLong getSeq(String alias)
  {
    return (com.terraframe.mojo.query.SelectableSingleLong)this.getComponentQuery().get(dss.vector.solutions.surveillance.CaseReferral.SEQ, alias, null);

  }
 
  public com.terraframe.mojo.query.SelectableSingleLong getSeq(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.SelectableSingleLong)this.getComponentQuery().get(dss.vector.solutions.surveillance.CaseReferral.SEQ, alias, displayLabel);

  }
  public com.terraframe.mojo.query.SelectableSingleChar getSiteMaster()
  {
    return getSiteMaster(null);

  }
 
  public com.terraframe.mojo.query.SelectableSingleChar getSiteMaster(String alias)
  {
    return (com.terraframe.mojo.query.SelectableSingleChar)this.getComponentQuery().get(dss.vector.solutions.surveillance.CaseReferral.SITEMASTER, alias, null);

  }
 
  public com.terraframe.mojo.query.SelectableSingleChar getSiteMaster(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.SelectableSingleChar)this.getComponentQuery().get(dss.vector.solutions.surveillance.CaseReferral.SITEMASTER, alias, displayLabel);

  }
  public com.terraframe.mojo.query.SelectableSingleChar getType()
  {
    return getType(null);

  }
 
  public com.terraframe.mojo.query.SelectableSingleChar getType(String alias)
  {
    return (com.terraframe.mojo.query.SelectableSingleChar)this.getComponentQuery().get(dss.vector.solutions.surveillance.CaseReferral.TYPE, alias, null);

  }
 
  public com.terraframe.mojo.query.SelectableSingleChar getType(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.SelectableSingleChar)this.getComponentQuery().get(dss.vector.solutions.surveillance.CaseReferral.TYPE, alias, displayLabel);

  }
  /**  
   * Returns an iterator of Business objects that match the query criteria specified
   * on this query object. 
   * @return iterator of Business objects that match the query criteria specified
   * on this query object.
   */
  public com.terraframe.mojo.query.OIterator<? extends CaseReferral> getIterator()
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
    return new com.terraframe.mojo.business.RelationshipIterator<CaseReferral>(this.getComponentQuery().getMdEntityIF(), this.getRelationshipQuery(), columnInfoMap, results);
  }

}
