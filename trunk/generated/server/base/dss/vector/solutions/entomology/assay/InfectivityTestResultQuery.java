package dss.vector.solutions.entomology.assay;

@com.terraframe.mojo.business.ClassSignature(hash = -1494877763)
/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to InfectivityTestResult.java
 *
 * @author Autogenerated by TerraFrame
 */
public  class InfectivityTestResultQuery extends com.terraframe.mojo.query.GeneratedRelationshipQuery implements com.terraframe.mojo.generation.loader.Reloadable
{
private static final long serialVersionUID = -1494877763;

  public InfectivityTestResultQuery(com.terraframe.mojo.query.QueryFactory componentQueryFactory)
  {
     super();
    if (this.getComponentQuery() == null)
    {
      com.terraframe.mojo.business.RelationshipQuery relationshipQuery = componentQueryFactory.relationshipQuery(this.getClassType());

       this.setRelationshipQuery(relationshipQuery);
    }
  }

  public String getClassType()
  {
    return dss.vector.solutions.entomology.assay.InfectivityTestResult.CLASS;
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
   * @param mosquitoQuery
   * @return Condition restricting objects that are parents in this relationship.
   */
   public com.terraframe.mojo.query.Condition hasParent(dss.vector.solutions.entomology.MosquitoQuery mosquitoQuery)
   {
     return this.getRelationshipQuery().hasParent(mosquitoQuery);
   }
  /**
   * Restricts the query to include objects that are parents in this relationship.
   * @param mosquitoQuery
   * @return Condition restricting objects that are parents in this relationship.
   */
   public com.terraframe.mojo.query.Condition doesNotHaveParent(dss.vector.solutions.entomology.MosquitoQuery mosquitoQuery)
   {
     return this.getRelationshipQuery().doesNotHaveParent(mosquitoQuery);
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
    else if (name.equals("testMethod")) 
    {
       return new dss.vector.solutions.ontology.TermQuery.TermQueryReference((com.terraframe.mojo.dataaccess.MdAttributeRefDAOIF)mdAttributeIF, attributeNamespace, definingTableName, definingTableAlias, referenceMdBusinessIF, referenceTableAlias, rootQuery, tableJoinSet, userDefinedAlias, userDefinedDisplayLabel);
    }
    else 
    {
      String error = "Attribute type ["+mdAttributeIF.getType()+"] is invalid.";
      throw new com.terraframe.mojo.query.QueryException(error);
    }
  }

  public com.terraframe.mojo.query.AttributeMoment getCreateDate()
  {
    return getCreateDate(null);

  }
 
  public com.terraframe.mojo.query.AttributeMoment getCreateDate(String alias)
  {
    return (com.terraframe.mojo.query.AttributeMoment)this.getComponentQuery().attributeFactory(dss.vector.solutions.entomology.assay.InfectivityTestResult.CREATEDATE, "com.terraframe.mojo.system.metadata.MdAttributeDateTime", alias, null);

  }
 
  public com.terraframe.mojo.query.AttributeMoment getCreateDate(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.AttributeMoment)this.getComponentQuery().attributeFactory(dss.vector.solutions.entomology.assay.InfectivityTestResult.CREATEDATE, "com.terraframe.mojo.system.metadata.MdAttributeDateTime", alias, displayLabel);

  }
  public com.terraframe.mojo.system.SingleActorQuery.SingleActorQueryReferenceIF getCreatedBy()
  {
    return getCreatedBy(null);

  }
 
  public com.terraframe.mojo.system.SingleActorQuery.SingleActorQueryReferenceIF getCreatedBy(String alias)
  {

    com.terraframe.mojo.dataaccess.MdAttributeDAOIF mdAttributeIF = this.getComponentQuery().getMdAttributeROfromMap("createdBy");

    return (com.terraframe.mojo.system.SingleActorQuery.SingleActorQueryReferenceIF)this.getComponentQuery().internalAttributeFactory(dss.vector.solutions.entomology.assay.InfectivityTestResult.CREATEDBY, mdAttributeIF, this, alias, null);

  }
 
  public com.terraframe.mojo.system.SingleActorQuery.SingleActorQueryReferenceIF getCreatedBy(String alias, String displayLabel)
  {

    com.terraframe.mojo.dataaccess.MdAttributeDAOIF mdAttributeIF = this.getComponentQuery().getMdAttributeROfromMap("createdBy");

    return (com.terraframe.mojo.system.SingleActorQuery.SingleActorQueryReferenceIF)this.getComponentQuery().internalAttributeFactory(dss.vector.solutions.entomology.assay.InfectivityTestResult.CREATEDBY, mdAttributeIF, this, alias, displayLabel);

  }
  public com.terraframe.mojo.system.metadata.MdDomainQuery.MdDomainQueryReferenceIF getEntityDomain()
  {
    return getEntityDomain(null);

  }
 
  public com.terraframe.mojo.system.metadata.MdDomainQuery.MdDomainQueryReferenceIF getEntityDomain(String alias)
  {

    com.terraframe.mojo.dataaccess.MdAttributeDAOIF mdAttributeIF = this.getComponentQuery().getMdAttributeROfromMap("entityDomain");

    return (com.terraframe.mojo.system.metadata.MdDomainQuery.MdDomainQueryReferenceIF)this.getComponentQuery().internalAttributeFactory(dss.vector.solutions.entomology.assay.InfectivityTestResult.ENTITYDOMAIN, mdAttributeIF, this, alias, null);

  }
 
  public com.terraframe.mojo.system.metadata.MdDomainQuery.MdDomainQueryReferenceIF getEntityDomain(String alias, String displayLabel)
  {

    com.terraframe.mojo.dataaccess.MdAttributeDAOIF mdAttributeIF = this.getComponentQuery().getMdAttributeROfromMap("entityDomain");

    return (com.terraframe.mojo.system.metadata.MdDomainQuery.MdDomainQueryReferenceIF)this.getComponentQuery().internalAttributeFactory(dss.vector.solutions.entomology.assay.InfectivityTestResult.ENTITYDOMAIN, mdAttributeIF, this, alias, displayLabel);

  }
  public com.terraframe.mojo.query.AttributeChar getId()
  {
    return getId(null);

  }
 
  public com.terraframe.mojo.query.AttributeChar getId(String alias)
  {
    return (com.terraframe.mojo.query.AttributeChar)this.getComponentQuery().attributeFactory(dss.vector.solutions.entomology.assay.InfectivityTestResult.ID, "com.terraframe.mojo.system.metadata.MdAttributeCharacter", alias, null);

  }
 
  public com.terraframe.mojo.query.AttributeChar getId(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.AttributeChar)this.getComponentQuery().attributeFactory(dss.vector.solutions.entomology.assay.InfectivityTestResult.ID, "com.terraframe.mojo.system.metadata.MdAttributeCharacter", alias, displayLabel);

  }
  public com.terraframe.mojo.query.AttributeChar getKeyName()
  {
    return getKeyName(null);

  }
 
  public com.terraframe.mojo.query.AttributeChar getKeyName(String alias)
  {
    return (com.terraframe.mojo.query.AttributeChar)this.getComponentQuery().attributeFactory(dss.vector.solutions.entomology.assay.InfectivityTestResult.KEYNAME, "com.terraframe.mojo.system.metadata.MdAttributeCharacter", alias, null);

  }
 
  public com.terraframe.mojo.query.AttributeChar getKeyName(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.AttributeChar)this.getComponentQuery().attributeFactory(dss.vector.solutions.entomology.assay.InfectivityTestResult.KEYNAME, "com.terraframe.mojo.system.metadata.MdAttributeCharacter", alias, displayLabel);

  }
  public com.terraframe.mojo.query.AttributeMoment getLastUpdateDate()
  {
    return getLastUpdateDate(null);

  }
 
  public com.terraframe.mojo.query.AttributeMoment getLastUpdateDate(String alias)
  {
    return (com.terraframe.mojo.query.AttributeMoment)this.getComponentQuery().attributeFactory(dss.vector.solutions.entomology.assay.InfectivityTestResult.LASTUPDATEDATE, "com.terraframe.mojo.system.metadata.MdAttributeDateTime", alias, null);

  }
 
  public com.terraframe.mojo.query.AttributeMoment getLastUpdateDate(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.AttributeMoment)this.getComponentQuery().attributeFactory(dss.vector.solutions.entomology.assay.InfectivityTestResult.LASTUPDATEDATE, "com.terraframe.mojo.system.metadata.MdAttributeDateTime", alias, displayLabel);

  }
  public com.terraframe.mojo.system.SingleActorQuery.SingleActorQueryReferenceIF getLastUpdatedBy()
  {
    return getLastUpdatedBy(null);

  }
 
  public com.terraframe.mojo.system.SingleActorQuery.SingleActorQueryReferenceIF getLastUpdatedBy(String alias)
  {

    com.terraframe.mojo.dataaccess.MdAttributeDAOIF mdAttributeIF = this.getComponentQuery().getMdAttributeROfromMap("lastUpdatedBy");

    return (com.terraframe.mojo.system.SingleActorQuery.SingleActorQueryReferenceIF)this.getComponentQuery().internalAttributeFactory(dss.vector.solutions.entomology.assay.InfectivityTestResult.LASTUPDATEDBY, mdAttributeIF, this, alias, null);

  }
 
  public com.terraframe.mojo.system.SingleActorQuery.SingleActorQueryReferenceIF getLastUpdatedBy(String alias, String displayLabel)
  {

    com.terraframe.mojo.dataaccess.MdAttributeDAOIF mdAttributeIF = this.getComponentQuery().getMdAttributeROfromMap("lastUpdatedBy");

    return (com.terraframe.mojo.system.SingleActorQuery.SingleActorQueryReferenceIF)this.getComponentQuery().internalAttributeFactory(dss.vector.solutions.entomology.assay.InfectivityTestResult.LASTUPDATEDBY, mdAttributeIF, this, alias, displayLabel);

  }
  public com.terraframe.mojo.system.UsersQuery.UsersQueryReferenceIF getLockedBy()
  {
    return getLockedBy(null);

  }
 
  public com.terraframe.mojo.system.UsersQuery.UsersQueryReferenceIF getLockedBy(String alias)
  {

    com.terraframe.mojo.dataaccess.MdAttributeDAOIF mdAttributeIF = this.getComponentQuery().getMdAttributeROfromMap("lockedBy");

    return (com.terraframe.mojo.system.UsersQuery.UsersQueryReferenceIF)this.getComponentQuery().internalAttributeFactory(dss.vector.solutions.entomology.assay.InfectivityTestResult.LOCKEDBY, mdAttributeIF, this, alias, null);

  }
 
  public com.terraframe.mojo.system.UsersQuery.UsersQueryReferenceIF getLockedBy(String alias, String displayLabel)
  {

    com.terraframe.mojo.dataaccess.MdAttributeDAOIF mdAttributeIF = this.getComponentQuery().getMdAttributeROfromMap("lockedBy");

    return (com.terraframe.mojo.system.UsersQuery.UsersQueryReferenceIF)this.getComponentQuery().internalAttributeFactory(dss.vector.solutions.entomology.assay.InfectivityTestResult.LOCKEDBY, mdAttributeIF, this, alias, displayLabel);

  }
  public com.terraframe.mojo.system.ActorQuery.ActorQueryReferenceIF getOwner()
  {
    return getOwner(null);

  }
 
  public com.terraframe.mojo.system.ActorQuery.ActorQueryReferenceIF getOwner(String alias)
  {

    com.terraframe.mojo.dataaccess.MdAttributeDAOIF mdAttributeIF = this.getComponentQuery().getMdAttributeROfromMap("owner");

    return (com.terraframe.mojo.system.ActorQuery.ActorQueryReferenceIF)this.getComponentQuery().internalAttributeFactory(dss.vector.solutions.entomology.assay.InfectivityTestResult.OWNER, mdAttributeIF, this, alias, null);

  }
 
  public com.terraframe.mojo.system.ActorQuery.ActorQueryReferenceIF getOwner(String alias, String displayLabel)
  {

    com.terraframe.mojo.dataaccess.MdAttributeDAOIF mdAttributeIF = this.getComponentQuery().getMdAttributeROfromMap("owner");

    return (com.terraframe.mojo.system.ActorQuery.ActorQueryReferenceIF)this.getComponentQuery().internalAttributeFactory(dss.vector.solutions.entomology.assay.InfectivityTestResult.OWNER, mdAttributeIF, this, alias, displayLabel);

  }
  public com.terraframe.mojo.query.AttributeLong getSeq()
  {
    return getSeq(null);

  }
 
  public com.terraframe.mojo.query.AttributeLong getSeq(String alias)
  {
    return (com.terraframe.mojo.query.AttributeLong)this.getComponentQuery().attributeFactory(dss.vector.solutions.entomology.assay.InfectivityTestResult.SEQ, "com.terraframe.mojo.system.metadata.MdAttributeLong", alias, null);

  }
 
  public com.terraframe.mojo.query.AttributeLong getSeq(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.AttributeLong)this.getComponentQuery().attributeFactory(dss.vector.solutions.entomology.assay.InfectivityTestResult.SEQ, "com.terraframe.mojo.system.metadata.MdAttributeLong", alias, displayLabel);

  }
  public com.terraframe.mojo.query.AttributeChar getSiteMaster()
  {
    return getSiteMaster(null);

  }
 
  public com.terraframe.mojo.query.AttributeChar getSiteMaster(String alias)
  {
    return (com.terraframe.mojo.query.AttributeChar)this.getComponentQuery().attributeFactory(dss.vector.solutions.entomology.assay.InfectivityTestResult.SITEMASTER, "com.terraframe.mojo.system.metadata.MdAttributeCharacter", alias, null);

  }
 
  public com.terraframe.mojo.query.AttributeChar getSiteMaster(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.AttributeChar)this.getComponentQuery().attributeFactory(dss.vector.solutions.entomology.assay.InfectivityTestResult.SITEMASTER, "com.terraframe.mojo.system.metadata.MdAttributeCharacter", alias, displayLabel);

  }
  public dss.vector.solutions.ontology.TermQuery.TermQueryReferenceIF getTestMethod()
  {
    return getTestMethod(null);

  }
 
  public dss.vector.solutions.ontology.TermQuery.TermQueryReferenceIF getTestMethod(String alias)
  {

    com.terraframe.mojo.dataaccess.MdAttributeDAOIF mdAttributeIF = this.getComponentQuery().getMdAttributeROfromMap("testMethod");

    return (dss.vector.solutions.ontology.TermQuery.TermQueryReferenceIF)this.getComponentQuery().internalAttributeFactory(dss.vector.solutions.entomology.assay.InfectivityTestResult.TESTMETHOD, mdAttributeIF, this, alias, null);

  }
 
  public dss.vector.solutions.ontology.TermQuery.TermQueryReferenceIF getTestMethod(String alias, String displayLabel)
  {

    com.terraframe.mojo.dataaccess.MdAttributeDAOIF mdAttributeIF = this.getComponentQuery().getMdAttributeROfromMap("testMethod");

    return (dss.vector.solutions.ontology.TermQuery.TermQueryReferenceIF)this.getComponentQuery().internalAttributeFactory(dss.vector.solutions.entomology.assay.InfectivityTestResult.TESTMETHOD, mdAttributeIF, this, alias, displayLabel);

  }
  public com.terraframe.mojo.query.AttributeBoolean getTestResult()
  {
    return getTestResult(null);

  }
 
  public com.terraframe.mojo.query.AttributeBoolean getTestResult(String alias)
  {
    return (com.terraframe.mojo.query.AttributeBoolean)this.getComponentQuery().attributeFactory(dss.vector.solutions.entomology.assay.InfectivityTestResult.TESTRESULT, "com.terraframe.mojo.system.metadata.MdAttributeBoolean", alias, null);

  }
 
  public com.terraframe.mojo.query.AttributeBoolean getTestResult(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.AttributeBoolean)this.getComponentQuery().attributeFactory(dss.vector.solutions.entomology.assay.InfectivityTestResult.TESTRESULT, "com.terraframe.mojo.system.metadata.MdAttributeBoolean", alias, displayLabel);

  }
  public com.terraframe.mojo.query.AttributeChar getType()
  {
    return getType(null);

  }
 
  public com.terraframe.mojo.query.AttributeChar getType(String alias)
  {
    return (com.terraframe.mojo.query.AttributeChar)this.getComponentQuery().attributeFactory(dss.vector.solutions.entomology.assay.InfectivityTestResult.TYPE, "com.terraframe.mojo.system.metadata.MdAttributeCharacter", alias, null);

  }
 
  public com.terraframe.mojo.query.AttributeChar getType(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.AttributeChar)this.getComponentQuery().attributeFactory(dss.vector.solutions.entomology.assay.InfectivityTestResult.TYPE, "com.terraframe.mojo.system.metadata.MdAttributeCharacter", alias, displayLabel);

  }
  /**  
   * Returns an iterator of Business objects that match the query criteria specified
   * on this query object. 
   * @return iterator of Business objects that match the query criteria specified
   * on this query object.
   */
  public com.terraframe.mojo.query.OIterator<? extends InfectivityTestResult> getIterator()
  {
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
    return new com.terraframe.mojo.business.RelationshipIterator<InfectivityTestResult>(this.getComponentQuery().getMdEntityIF(), this.getRelationshipQuery(), columnInfoMap, results);
  }

}
