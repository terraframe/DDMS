package dss.vector.solutions.ontology;

@com.terraframe.mojo.business.ClassSignature(hash = -750104166)
/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to AllPaths.java
 *
 * @author Autogenerated by TerraFrame
 */
public  class AllPathsQuery extends com.terraframe.mojo.query.GeneratedBusinessQuery
 implements com.terraframe.mojo.generation.loader.Reloadable
{
private static final long serialVersionUID = -750104166;

  public AllPathsQuery(com.terraframe.mojo.query.QueryFactory componentQueryFactory)
  {
     super();
    if (this.getComponentQuery() == null)
    {
      com.terraframe.mojo.business.BusinessQuery businessQuery = componentQueryFactory.businessQuery(this.getClassType());

       this.setBusinessQuery(businessQuery);
    }
  }

  public AllPathsQuery(com.terraframe.mojo.query.ValueQuery valueQuery)
  {
     super();
    if (this.getComponentQuery() == null)
    {
      com.terraframe.mojo.business.BusinessQuery businessQuery = new com.terraframe.mojo.business.BusinessQuery(valueQuery, this.getClassType());

       this.setBusinessQuery(businessQuery);
    }
  }

  public String getClassType()
  {
    return dss.vector.solutions.ontology.AllPaths.CLASS;
  }
  public dss.vector.solutions.ontology.TermQuery.TermQueryReferenceIF getChildTerm()
  {
    return getChildTerm(null);

  }
 
  public dss.vector.solutions.ontology.TermQuery.TermQueryReferenceIF getChildTerm(String alias)
  {

    com.terraframe.mojo.dataaccess.MdAttributeDAOIF mdAttributeIF = this.getComponentQuery().getMdAttributeROfromMap("childTerm");

    return (dss.vector.solutions.ontology.TermQuery.TermQueryReferenceIF)this.getComponentQuery().internalAttributeFactory(dss.vector.solutions.ontology.AllPaths.CHILDTERM, mdAttributeIF, this, alias, null);

  }
 
  public dss.vector.solutions.ontology.TermQuery.TermQueryReferenceIF getChildTerm(String alias, String displayLabel)
  {

    com.terraframe.mojo.dataaccess.MdAttributeDAOIF mdAttributeIF = this.getComponentQuery().getMdAttributeROfromMap("childTerm");

    return (dss.vector.solutions.ontology.TermQuery.TermQueryReferenceIF)this.getComponentQuery().internalAttributeFactory(dss.vector.solutions.ontology.AllPaths.CHILDTERM, mdAttributeIF, this, alias, displayLabel);

  }
  public com.terraframe.mojo.query.SelectableMoment getCreateDate()
  {
    return getCreateDate(null);

  }
 
  public com.terraframe.mojo.query.SelectableMoment getCreateDate(String alias)
  {
    return (com.terraframe.mojo.query.SelectableMoment)this.getComponentQuery().get(dss.vector.solutions.ontology.AllPaths.CREATEDATE, alias, null);

  }
 
  public com.terraframe.mojo.query.SelectableMoment getCreateDate(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.SelectableMoment)this.getComponentQuery().get(dss.vector.solutions.ontology.AllPaths.CREATEDATE, alias, displayLabel);

  }
  public com.terraframe.mojo.system.SingleActorQuery.SingleActorQueryReferenceIF getCreatedBy()
  {
    return getCreatedBy(null);

  }
 
  public com.terraframe.mojo.system.SingleActorQuery.SingleActorQueryReferenceIF getCreatedBy(String alias)
  {

    com.terraframe.mojo.dataaccess.MdAttributeDAOIF mdAttributeIF = this.getComponentQuery().getMdAttributeROfromMap("createdBy");

    return (com.terraframe.mojo.system.SingleActorQuery.SingleActorQueryReferenceIF)this.getComponentQuery().internalAttributeFactory(dss.vector.solutions.ontology.AllPaths.CREATEDBY, mdAttributeIF, this, alias, null);

  }
 
  public com.terraframe.mojo.system.SingleActorQuery.SingleActorQueryReferenceIF getCreatedBy(String alias, String displayLabel)
  {

    com.terraframe.mojo.dataaccess.MdAttributeDAOIF mdAttributeIF = this.getComponentQuery().getMdAttributeROfromMap("createdBy");

    return (com.terraframe.mojo.system.SingleActorQuery.SingleActorQueryReferenceIF)this.getComponentQuery().internalAttributeFactory(dss.vector.solutions.ontology.AllPaths.CREATEDBY, mdAttributeIF, this, alias, displayLabel);

  }
  public com.terraframe.mojo.system.metadata.MdDomainQuery.MdDomainQueryReferenceIF getEntityDomain()
  {
    return getEntityDomain(null);

  }
 
  public com.terraframe.mojo.system.metadata.MdDomainQuery.MdDomainQueryReferenceIF getEntityDomain(String alias)
  {

    com.terraframe.mojo.dataaccess.MdAttributeDAOIF mdAttributeIF = this.getComponentQuery().getMdAttributeROfromMap("entityDomain");

    return (com.terraframe.mojo.system.metadata.MdDomainQuery.MdDomainQueryReferenceIF)this.getComponentQuery().internalAttributeFactory(dss.vector.solutions.ontology.AllPaths.ENTITYDOMAIN, mdAttributeIF, this, alias, null);

  }
 
  public com.terraframe.mojo.system.metadata.MdDomainQuery.MdDomainQueryReferenceIF getEntityDomain(String alias, String displayLabel)
  {

    com.terraframe.mojo.dataaccess.MdAttributeDAOIF mdAttributeIF = this.getComponentQuery().getMdAttributeROfromMap("entityDomain");

    return (com.terraframe.mojo.system.metadata.MdDomainQuery.MdDomainQueryReferenceIF)this.getComponentQuery().internalAttributeFactory(dss.vector.solutions.ontology.AllPaths.ENTITYDOMAIN, mdAttributeIF, this, alias, displayLabel);

  }
  public com.terraframe.mojo.query.SelectableChar getId()
  {
    return getId(null);

  }
 
  public com.terraframe.mojo.query.SelectableChar getId(String alias)
  {
    return (com.terraframe.mojo.query.SelectableChar)this.getComponentQuery().get(dss.vector.solutions.ontology.AllPaths.ID, alias, null);

  }
 
  public com.terraframe.mojo.query.SelectableChar getId(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.SelectableChar)this.getComponentQuery().get(dss.vector.solutions.ontology.AllPaths.ID, alias, displayLabel);

  }
  public com.terraframe.mojo.query.SelectableChar getKeyName()
  {
    return getKeyName(null);

  }
 
  public com.terraframe.mojo.query.SelectableChar getKeyName(String alias)
  {
    return (com.terraframe.mojo.query.SelectableChar)this.getComponentQuery().get(dss.vector.solutions.ontology.AllPaths.KEYNAME, alias, null);

  }
 
  public com.terraframe.mojo.query.SelectableChar getKeyName(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.SelectableChar)this.getComponentQuery().get(dss.vector.solutions.ontology.AllPaths.KEYNAME, alias, displayLabel);

  }
  public com.terraframe.mojo.query.SelectableMoment getLastUpdateDate()
  {
    return getLastUpdateDate(null);

  }
 
  public com.terraframe.mojo.query.SelectableMoment getLastUpdateDate(String alias)
  {
    return (com.terraframe.mojo.query.SelectableMoment)this.getComponentQuery().get(dss.vector.solutions.ontology.AllPaths.LASTUPDATEDATE, alias, null);

  }
 
  public com.terraframe.mojo.query.SelectableMoment getLastUpdateDate(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.SelectableMoment)this.getComponentQuery().get(dss.vector.solutions.ontology.AllPaths.LASTUPDATEDATE, alias, displayLabel);

  }
  public com.terraframe.mojo.system.SingleActorQuery.SingleActorQueryReferenceIF getLastUpdatedBy()
  {
    return getLastUpdatedBy(null);

  }
 
  public com.terraframe.mojo.system.SingleActorQuery.SingleActorQueryReferenceIF getLastUpdatedBy(String alias)
  {

    com.terraframe.mojo.dataaccess.MdAttributeDAOIF mdAttributeIF = this.getComponentQuery().getMdAttributeROfromMap("lastUpdatedBy");

    return (com.terraframe.mojo.system.SingleActorQuery.SingleActorQueryReferenceIF)this.getComponentQuery().internalAttributeFactory(dss.vector.solutions.ontology.AllPaths.LASTUPDATEDBY, mdAttributeIF, this, alias, null);

  }
 
  public com.terraframe.mojo.system.SingleActorQuery.SingleActorQueryReferenceIF getLastUpdatedBy(String alias, String displayLabel)
  {

    com.terraframe.mojo.dataaccess.MdAttributeDAOIF mdAttributeIF = this.getComponentQuery().getMdAttributeROfromMap("lastUpdatedBy");

    return (com.terraframe.mojo.system.SingleActorQuery.SingleActorQueryReferenceIF)this.getComponentQuery().internalAttributeFactory(dss.vector.solutions.ontology.AllPaths.LASTUPDATEDBY, mdAttributeIF, this, alias, displayLabel);

  }
  public com.terraframe.mojo.system.UsersQuery.UsersQueryReferenceIF getLockedBy()
  {
    return getLockedBy(null);

  }
 
  public com.terraframe.mojo.system.UsersQuery.UsersQueryReferenceIF getLockedBy(String alias)
  {

    com.terraframe.mojo.dataaccess.MdAttributeDAOIF mdAttributeIF = this.getComponentQuery().getMdAttributeROfromMap("lockedBy");

    return (com.terraframe.mojo.system.UsersQuery.UsersQueryReferenceIF)this.getComponentQuery().internalAttributeFactory(dss.vector.solutions.ontology.AllPaths.LOCKEDBY, mdAttributeIF, this, alias, null);

  }
 
  public com.terraframe.mojo.system.UsersQuery.UsersQueryReferenceIF getLockedBy(String alias, String displayLabel)
  {

    com.terraframe.mojo.dataaccess.MdAttributeDAOIF mdAttributeIF = this.getComponentQuery().getMdAttributeROfromMap("lockedBy");

    return (com.terraframe.mojo.system.UsersQuery.UsersQueryReferenceIF)this.getComponentQuery().internalAttributeFactory(dss.vector.solutions.ontology.AllPaths.LOCKEDBY, mdAttributeIF, this, alias, displayLabel);

  }
  public dss.vector.solutions.ontology.OntologyRelationshipQuery.OntologyRelationshipQueryReferenceIF getOntologyRelationship()
  {
    return getOntologyRelationship(null);

  }
 
  public dss.vector.solutions.ontology.OntologyRelationshipQuery.OntologyRelationshipQueryReferenceIF getOntologyRelationship(String alias)
  {

    com.terraframe.mojo.dataaccess.MdAttributeDAOIF mdAttributeIF = this.getComponentQuery().getMdAttributeROfromMap("ontologyRelationship");

    return (dss.vector.solutions.ontology.OntologyRelationshipQuery.OntologyRelationshipQueryReferenceIF)this.getComponentQuery().internalAttributeFactory(dss.vector.solutions.ontology.AllPaths.ONTOLOGYRELATIONSHIP, mdAttributeIF, this, alias, null);

  }
 
  public dss.vector.solutions.ontology.OntologyRelationshipQuery.OntologyRelationshipQueryReferenceIF getOntologyRelationship(String alias, String displayLabel)
  {

    com.terraframe.mojo.dataaccess.MdAttributeDAOIF mdAttributeIF = this.getComponentQuery().getMdAttributeROfromMap("ontologyRelationship");

    return (dss.vector.solutions.ontology.OntologyRelationshipQuery.OntologyRelationshipQueryReferenceIF)this.getComponentQuery().internalAttributeFactory(dss.vector.solutions.ontology.AllPaths.ONTOLOGYRELATIONSHIP, mdAttributeIF, this, alias, displayLabel);

  }
  public com.terraframe.mojo.system.ActorQuery.ActorQueryReferenceIF getOwner()
  {
    return getOwner(null);

  }
 
  public com.terraframe.mojo.system.ActorQuery.ActorQueryReferenceIF getOwner(String alias)
  {

    com.terraframe.mojo.dataaccess.MdAttributeDAOIF mdAttributeIF = this.getComponentQuery().getMdAttributeROfromMap("owner");

    return (com.terraframe.mojo.system.ActorQuery.ActorQueryReferenceIF)this.getComponentQuery().internalAttributeFactory(dss.vector.solutions.ontology.AllPaths.OWNER, mdAttributeIF, this, alias, null);

  }
 
  public com.terraframe.mojo.system.ActorQuery.ActorQueryReferenceIF getOwner(String alias, String displayLabel)
  {

    com.terraframe.mojo.dataaccess.MdAttributeDAOIF mdAttributeIF = this.getComponentQuery().getMdAttributeROfromMap("owner");

    return (com.terraframe.mojo.system.ActorQuery.ActorQueryReferenceIF)this.getComponentQuery().internalAttributeFactory(dss.vector.solutions.ontology.AllPaths.OWNER, mdAttributeIF, this, alias, displayLabel);

  }
  public dss.vector.solutions.ontology.TermQuery.TermQueryReferenceIF getParentTerm()
  {
    return getParentTerm(null);

  }
 
  public dss.vector.solutions.ontology.TermQuery.TermQueryReferenceIF getParentTerm(String alias)
  {

    com.terraframe.mojo.dataaccess.MdAttributeDAOIF mdAttributeIF = this.getComponentQuery().getMdAttributeROfromMap("parentTerm");

    return (dss.vector.solutions.ontology.TermQuery.TermQueryReferenceIF)this.getComponentQuery().internalAttributeFactory(dss.vector.solutions.ontology.AllPaths.PARENTTERM, mdAttributeIF, this, alias, null);

  }
 
  public dss.vector.solutions.ontology.TermQuery.TermQueryReferenceIF getParentTerm(String alias, String displayLabel)
  {

    com.terraframe.mojo.dataaccess.MdAttributeDAOIF mdAttributeIF = this.getComponentQuery().getMdAttributeROfromMap("parentTerm");

    return (dss.vector.solutions.ontology.TermQuery.TermQueryReferenceIF)this.getComponentQuery().internalAttributeFactory(dss.vector.solutions.ontology.AllPaths.PARENTTERM, mdAttributeIF, this, alias, displayLabel);

  }
  public com.terraframe.mojo.query.SelectableLong getSeq()
  {
    return getSeq(null);

  }
 
  public com.terraframe.mojo.query.SelectableLong getSeq(String alias)
  {
    return (com.terraframe.mojo.query.SelectableLong)this.getComponentQuery().get(dss.vector.solutions.ontology.AllPaths.SEQ, alias, null);

  }
 
  public com.terraframe.mojo.query.SelectableLong getSeq(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.SelectableLong)this.getComponentQuery().get(dss.vector.solutions.ontology.AllPaths.SEQ, alias, displayLabel);

  }
  public com.terraframe.mojo.query.SelectableChar getSiteMaster()
  {
    return getSiteMaster(null);

  }
 
  public com.terraframe.mojo.query.SelectableChar getSiteMaster(String alias)
  {
    return (com.terraframe.mojo.query.SelectableChar)this.getComponentQuery().get(dss.vector.solutions.ontology.AllPaths.SITEMASTER, alias, null);

  }
 
  public com.terraframe.mojo.query.SelectableChar getSiteMaster(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.SelectableChar)this.getComponentQuery().get(dss.vector.solutions.ontology.AllPaths.SITEMASTER, alias, displayLabel);

  }
  public com.terraframe.mojo.query.SelectableChar getType()
  {
    return getType(null);

  }
 
  public com.terraframe.mojo.query.SelectableChar getType(String alias)
  {
    return (com.terraframe.mojo.query.SelectableChar)this.getComponentQuery().get(dss.vector.solutions.ontology.AllPaths.TYPE, alias, null);

  }
 
  public com.terraframe.mojo.query.SelectableChar getType(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.SelectableChar)this.getComponentQuery().get(dss.vector.solutions.ontology.AllPaths.TYPE, alias, displayLabel);

  }
  protected com.terraframe.mojo.query.AttributeReference referenceFactory( com.terraframe.mojo.dataaccess.MdAttributeRefDAOIF mdAttributeIF, String attributeNamespace, String definingTableName, String definingTableAlias,  com.terraframe.mojo.dataaccess.MdBusinessDAOIF referenceMdBusinessIF, String referenceTableAlias, com.terraframe.mojo.query.ComponentQuery rootQuery, java.util.Set<com.terraframe.mojo.query.Join> tableJoinSet, String userDefinedAlias, String userDefinedDisplayLabel)
  {
    String name = mdAttributeIF.definesAttribute();
    
    if (name.equals("childTerm")) 
    {
       return new dss.vector.solutions.ontology.TermQuery.TermQueryReference((com.terraframe.mojo.dataaccess.MdAttributeRefDAOIF)mdAttributeIF, attributeNamespace, definingTableName, definingTableAlias, referenceMdBusinessIF, referenceTableAlias, rootQuery, tableJoinSet, userDefinedAlias, userDefinedDisplayLabel);
    }
    else if (name.equals("createdBy")) 
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
    else if (name.equals("ontologyRelationship")) 
    {
       return new dss.vector.solutions.ontology.OntologyRelationshipQuery.OntologyRelationshipQueryReference((com.terraframe.mojo.dataaccess.MdAttributeRefDAOIF)mdAttributeIF, attributeNamespace, definingTableName, definingTableAlias, referenceMdBusinessIF, referenceTableAlias, rootQuery, tableJoinSet, userDefinedAlias, userDefinedDisplayLabel);
    }
    else if (name.equals("owner")) 
    {
       return new com.terraframe.mojo.system.ActorQuery.ActorQueryReference((com.terraframe.mojo.dataaccess.MdAttributeRefDAOIF)mdAttributeIF, attributeNamespace, definingTableName, definingTableAlias, referenceMdBusinessIF, referenceTableAlias, rootQuery, tableJoinSet, userDefinedAlias, userDefinedDisplayLabel);
    }
    else if (name.equals("parentTerm")) 
    {
       return new dss.vector.solutions.ontology.TermQuery.TermQueryReference((com.terraframe.mojo.dataaccess.MdAttributeRefDAOIF)mdAttributeIF, attributeNamespace, definingTableName, definingTableAlias, referenceMdBusinessIF, referenceTableAlias, rootQuery, tableJoinSet, userDefinedAlias, userDefinedDisplayLabel);
    }
    else 
    {
      String error = "Attribute type ["+mdAttributeIF.getType()+"] is invalid.";
      throw new com.terraframe.mojo.query.QueryException(error);
    }
  }

  /**  
   * Returns an iterator of Business objects that match the query criteria specified
   * on this query object. 
   * @return iterator of Business objects that match the query criteria specified
   * on this query object.
   */
  public com.terraframe.mojo.query.OIterator<? extends AllPaths> getIterator()
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
    return new com.terraframe.mojo.business.BusinessIterator<AllPaths>(this.getComponentQuery().getMdEntityIF(), columnInfoMap, results);
  }


/**
 * Interface that masks all type unsafe query methods and defines all type safe methods.
 * This type is used when a join is performed on this class as a reference.
 **/
  public interface AllPathsQueryReferenceIF extends com.terraframe.mojo.generation.loader.Reloadable, com.terraframe.mojo.query.SelectableReference
  {

    public dss.vector.solutions.ontology.TermQuery.TermQueryReferenceIF getChildTerm();
    public dss.vector.solutions.ontology.TermQuery.TermQueryReferenceIF getChildTerm(String alias);
    public dss.vector.solutions.ontology.TermQuery.TermQueryReferenceIF getChildTerm(String alias, String displayLabel);
    public com.terraframe.mojo.query.SelectableMoment getCreateDate();
    public com.terraframe.mojo.query.SelectableMoment getCreateDate(String alias);
    public com.terraframe.mojo.query.SelectableMoment getCreateDate(String alias, String displayLabel);
    public com.terraframe.mojo.system.SingleActorQuery.SingleActorQueryReferenceIF getCreatedBy();
    public com.terraframe.mojo.system.SingleActorQuery.SingleActorQueryReferenceIF getCreatedBy(String alias);
    public com.terraframe.mojo.system.SingleActorQuery.SingleActorQueryReferenceIF getCreatedBy(String alias, String displayLabel);
    public com.terraframe.mojo.system.metadata.MdDomainQuery.MdDomainQueryReferenceIF getEntityDomain();
    public com.terraframe.mojo.system.metadata.MdDomainQuery.MdDomainQueryReferenceIF getEntityDomain(String alias);
    public com.terraframe.mojo.system.metadata.MdDomainQuery.MdDomainQueryReferenceIF getEntityDomain(String alias, String displayLabel);
    public com.terraframe.mojo.query.SelectableChar getId();
    public com.terraframe.mojo.query.SelectableChar getId(String alias);
    public com.terraframe.mojo.query.SelectableChar getId(String alias, String displayLabel);
    public com.terraframe.mojo.query.SelectableChar getKeyName();
    public com.terraframe.mojo.query.SelectableChar getKeyName(String alias);
    public com.terraframe.mojo.query.SelectableChar getKeyName(String alias, String displayLabel);
    public com.terraframe.mojo.query.SelectableMoment getLastUpdateDate();
    public com.terraframe.mojo.query.SelectableMoment getLastUpdateDate(String alias);
    public com.terraframe.mojo.query.SelectableMoment getLastUpdateDate(String alias, String displayLabel);
    public com.terraframe.mojo.system.SingleActorQuery.SingleActorQueryReferenceIF getLastUpdatedBy();
    public com.terraframe.mojo.system.SingleActorQuery.SingleActorQueryReferenceIF getLastUpdatedBy(String alias);
    public com.terraframe.mojo.system.SingleActorQuery.SingleActorQueryReferenceIF getLastUpdatedBy(String alias, String displayLabel);
    public com.terraframe.mojo.system.UsersQuery.UsersQueryReferenceIF getLockedBy();
    public com.terraframe.mojo.system.UsersQuery.UsersQueryReferenceIF getLockedBy(String alias);
    public com.terraframe.mojo.system.UsersQuery.UsersQueryReferenceIF getLockedBy(String alias, String displayLabel);
    public dss.vector.solutions.ontology.OntologyRelationshipQuery.OntologyRelationshipQueryReferenceIF getOntologyRelationship();
    public dss.vector.solutions.ontology.OntologyRelationshipQuery.OntologyRelationshipQueryReferenceIF getOntologyRelationship(String alias);
    public dss.vector.solutions.ontology.OntologyRelationshipQuery.OntologyRelationshipQueryReferenceIF getOntologyRelationship(String alias, String displayLabel);
    public com.terraframe.mojo.system.ActorQuery.ActorQueryReferenceIF getOwner();
    public com.terraframe.mojo.system.ActorQuery.ActorQueryReferenceIF getOwner(String alias);
    public com.terraframe.mojo.system.ActorQuery.ActorQueryReferenceIF getOwner(String alias, String displayLabel);
    public dss.vector.solutions.ontology.TermQuery.TermQueryReferenceIF getParentTerm();
    public dss.vector.solutions.ontology.TermQuery.TermQueryReferenceIF getParentTerm(String alias);
    public dss.vector.solutions.ontology.TermQuery.TermQueryReferenceIF getParentTerm(String alias, String displayLabel);
    public com.terraframe.mojo.query.SelectableLong getSeq();
    public com.terraframe.mojo.query.SelectableLong getSeq(String alias);
    public com.terraframe.mojo.query.SelectableLong getSeq(String alias, String displayLabel);
    public com.terraframe.mojo.query.SelectableChar getSiteMaster();
    public com.terraframe.mojo.query.SelectableChar getSiteMaster(String alias);
    public com.terraframe.mojo.query.SelectableChar getSiteMaster(String alias, String displayLabel);
    public com.terraframe.mojo.query.SelectableChar getType();
    public com.terraframe.mojo.query.SelectableChar getType(String alias);
    public com.terraframe.mojo.query.SelectableChar getType(String alias, String displayLabel);

    public com.terraframe.mojo.query.BasicCondition EQ(dss.vector.solutions.ontology.AllPaths allPaths);

    public com.terraframe.mojo.query.BasicCondition NE(dss.vector.solutions.ontology.AllPaths allPaths);

  }

/**
 * Implements type safe query methods.
 * This type is used when a join is performed on this class as a reference.
 **/
  public static class AllPathsQueryReference extends com.terraframe.mojo.query.AttributeReference
 implements AllPathsQueryReferenceIF
, com.terraframe.mojo.generation.loader.Reloadable
  {
private static final long serialVersionUID = -1323851588;

  public AllPathsQueryReference(com.terraframe.mojo.dataaccess.MdAttributeRefDAOIF mdAttributeIF, String attributeNamespace, String definingTableName, String definingTableAlias, com.terraframe.mojo.dataaccess.MdBusinessDAOIF referenceMdBusinessIF, String referenceTableAlias, com.terraframe.mojo.query.ComponentQuery rootQuery, java.util.Set<com.terraframe.mojo.query.Join> tableJoinSet, String alias, String displayLabel)
  {
    super(mdAttributeIF, attributeNamespace, definingTableName, definingTableAlias, referenceMdBusinessIF, referenceTableAlias, rootQuery, tableJoinSet, alias, displayLabel);

  }


    public com.terraframe.mojo.query.BasicCondition EQ(dss.vector.solutions.ontology.AllPaths allPaths)
    {
      return this.EQ(allPaths.getId());
    }

    public com.terraframe.mojo.query.BasicCondition NE(dss.vector.solutions.ontology.AllPaths allPaths)
    {
      return this.NE(allPaths.getId());
    }

  public dss.vector.solutions.ontology.TermQuery.TermQueryReferenceIF getChildTerm()
  {
    return getChildTerm(null);

  }
 
  public dss.vector.solutions.ontology.TermQuery.TermQueryReferenceIF getChildTerm(String alias)
  {
    return (dss.vector.solutions.ontology.TermQuery.TermQueryReferenceIF)this.get(dss.vector.solutions.ontology.AllPaths.CHILDTERM, alias, null);

  }
 
  public dss.vector.solutions.ontology.TermQuery.TermQueryReferenceIF getChildTerm(String alias, String displayLabel)
  {
    return (dss.vector.solutions.ontology.TermQuery.TermQueryReferenceIF)this.get(dss.vector.solutions.ontology.AllPaths.CHILDTERM,  alias, displayLabel);

  }
  public com.terraframe.mojo.query.SelectableMoment getCreateDate()
  {
    return getCreateDate(null);

  }
 
  public com.terraframe.mojo.query.SelectableMoment getCreateDate(String alias)
  {
    return (com.terraframe.mojo.query.SelectableMoment)this.get(dss.vector.solutions.ontology.AllPaths.CREATEDATE, alias, null);

  }
 
  public com.terraframe.mojo.query.SelectableMoment getCreateDate(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.SelectableMoment)this.get(dss.vector.solutions.ontology.AllPaths.CREATEDATE, alias, displayLabel);

  }
  public com.terraframe.mojo.system.SingleActorQuery.SingleActorQueryReferenceIF getCreatedBy()
  {
    return getCreatedBy(null);

  }
 
  public com.terraframe.mojo.system.SingleActorQuery.SingleActorQueryReferenceIF getCreatedBy(String alias)
  {
    return (com.terraframe.mojo.system.SingleActorQuery.SingleActorQueryReferenceIF)this.get(dss.vector.solutions.ontology.AllPaths.CREATEDBY, alias, null);

  }
 
  public com.terraframe.mojo.system.SingleActorQuery.SingleActorQueryReferenceIF getCreatedBy(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.system.SingleActorQuery.SingleActorQueryReferenceIF)this.get(dss.vector.solutions.ontology.AllPaths.CREATEDBY,  alias, displayLabel);

  }
  public com.terraframe.mojo.system.metadata.MdDomainQuery.MdDomainQueryReferenceIF getEntityDomain()
  {
    return getEntityDomain(null);

  }
 
  public com.terraframe.mojo.system.metadata.MdDomainQuery.MdDomainQueryReferenceIF getEntityDomain(String alias)
  {
    return (com.terraframe.mojo.system.metadata.MdDomainQuery.MdDomainQueryReferenceIF)this.get(dss.vector.solutions.ontology.AllPaths.ENTITYDOMAIN, alias, null);

  }
 
  public com.terraframe.mojo.system.metadata.MdDomainQuery.MdDomainQueryReferenceIF getEntityDomain(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.system.metadata.MdDomainQuery.MdDomainQueryReferenceIF)this.get(dss.vector.solutions.ontology.AllPaths.ENTITYDOMAIN,  alias, displayLabel);

  }
  public com.terraframe.mojo.query.SelectableChar getId()
  {
    return getId(null);

  }
 
  public com.terraframe.mojo.query.SelectableChar getId(String alias)
  {
    return (com.terraframe.mojo.query.SelectableChar)this.get(dss.vector.solutions.ontology.AllPaths.ID, alias, null);

  }
 
  public com.terraframe.mojo.query.SelectableChar getId(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.SelectableChar)this.get(dss.vector.solutions.ontology.AllPaths.ID, alias, displayLabel);

  }
  public com.terraframe.mojo.query.SelectableChar getKeyName()
  {
    return getKeyName(null);

  }
 
  public com.terraframe.mojo.query.SelectableChar getKeyName(String alias)
  {
    return (com.terraframe.mojo.query.SelectableChar)this.get(dss.vector.solutions.ontology.AllPaths.KEYNAME, alias, null);

  }
 
  public com.terraframe.mojo.query.SelectableChar getKeyName(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.SelectableChar)this.get(dss.vector.solutions.ontology.AllPaths.KEYNAME, alias, displayLabel);

  }
  public com.terraframe.mojo.query.SelectableMoment getLastUpdateDate()
  {
    return getLastUpdateDate(null);

  }
 
  public com.terraframe.mojo.query.SelectableMoment getLastUpdateDate(String alias)
  {
    return (com.terraframe.mojo.query.SelectableMoment)this.get(dss.vector.solutions.ontology.AllPaths.LASTUPDATEDATE, alias, null);

  }
 
  public com.terraframe.mojo.query.SelectableMoment getLastUpdateDate(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.SelectableMoment)this.get(dss.vector.solutions.ontology.AllPaths.LASTUPDATEDATE, alias, displayLabel);

  }
  public com.terraframe.mojo.system.SingleActorQuery.SingleActorQueryReferenceIF getLastUpdatedBy()
  {
    return getLastUpdatedBy(null);

  }
 
  public com.terraframe.mojo.system.SingleActorQuery.SingleActorQueryReferenceIF getLastUpdatedBy(String alias)
  {
    return (com.terraframe.mojo.system.SingleActorQuery.SingleActorQueryReferenceIF)this.get(dss.vector.solutions.ontology.AllPaths.LASTUPDATEDBY, alias, null);

  }
 
  public com.terraframe.mojo.system.SingleActorQuery.SingleActorQueryReferenceIF getLastUpdatedBy(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.system.SingleActorQuery.SingleActorQueryReferenceIF)this.get(dss.vector.solutions.ontology.AllPaths.LASTUPDATEDBY,  alias, displayLabel);

  }
  public com.terraframe.mojo.system.UsersQuery.UsersQueryReferenceIF getLockedBy()
  {
    return getLockedBy(null);

  }
 
  public com.terraframe.mojo.system.UsersQuery.UsersQueryReferenceIF getLockedBy(String alias)
  {
    return (com.terraframe.mojo.system.UsersQuery.UsersQueryReferenceIF)this.get(dss.vector.solutions.ontology.AllPaths.LOCKEDBY, alias, null);

  }
 
  public com.terraframe.mojo.system.UsersQuery.UsersQueryReferenceIF getLockedBy(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.system.UsersQuery.UsersQueryReferenceIF)this.get(dss.vector.solutions.ontology.AllPaths.LOCKEDBY,  alias, displayLabel);

  }
  public dss.vector.solutions.ontology.OntologyRelationshipQuery.OntologyRelationshipQueryReferenceIF getOntologyRelationship()
  {
    return getOntologyRelationship(null);

  }
 
  public dss.vector.solutions.ontology.OntologyRelationshipQuery.OntologyRelationshipQueryReferenceIF getOntologyRelationship(String alias)
  {
    return (dss.vector.solutions.ontology.OntologyRelationshipQuery.OntologyRelationshipQueryReferenceIF)this.get(dss.vector.solutions.ontology.AllPaths.ONTOLOGYRELATIONSHIP, alias, null);

  }
 
  public dss.vector.solutions.ontology.OntologyRelationshipQuery.OntologyRelationshipQueryReferenceIF getOntologyRelationship(String alias, String displayLabel)
  {
    return (dss.vector.solutions.ontology.OntologyRelationshipQuery.OntologyRelationshipQueryReferenceIF)this.get(dss.vector.solutions.ontology.AllPaths.ONTOLOGYRELATIONSHIP,  alias, displayLabel);

  }
  public com.terraframe.mojo.system.ActorQuery.ActorQueryReferenceIF getOwner()
  {
    return getOwner(null);

  }
 
  public com.terraframe.mojo.system.ActorQuery.ActorQueryReferenceIF getOwner(String alias)
  {
    return (com.terraframe.mojo.system.ActorQuery.ActorQueryReferenceIF)this.get(dss.vector.solutions.ontology.AllPaths.OWNER, alias, null);

  }
 
  public com.terraframe.mojo.system.ActorQuery.ActorQueryReferenceIF getOwner(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.system.ActorQuery.ActorQueryReferenceIF)this.get(dss.vector.solutions.ontology.AllPaths.OWNER,  alias, displayLabel);

  }
  public dss.vector.solutions.ontology.TermQuery.TermQueryReferenceIF getParentTerm()
  {
    return getParentTerm(null);

  }
 
  public dss.vector.solutions.ontology.TermQuery.TermQueryReferenceIF getParentTerm(String alias)
  {
    return (dss.vector.solutions.ontology.TermQuery.TermQueryReferenceIF)this.get(dss.vector.solutions.ontology.AllPaths.PARENTTERM, alias, null);

  }
 
  public dss.vector.solutions.ontology.TermQuery.TermQueryReferenceIF getParentTerm(String alias, String displayLabel)
  {
    return (dss.vector.solutions.ontology.TermQuery.TermQueryReferenceIF)this.get(dss.vector.solutions.ontology.AllPaths.PARENTTERM,  alias, displayLabel);

  }
  public com.terraframe.mojo.query.SelectableLong getSeq()
  {
    return getSeq(null);

  }
 
  public com.terraframe.mojo.query.SelectableLong getSeq(String alias)
  {
    return (com.terraframe.mojo.query.SelectableLong)this.get(dss.vector.solutions.ontology.AllPaths.SEQ, alias, null);

  }
 
  public com.terraframe.mojo.query.SelectableLong getSeq(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.SelectableLong)this.get(dss.vector.solutions.ontology.AllPaths.SEQ, alias, displayLabel);

  }
  public com.terraframe.mojo.query.SelectableChar getSiteMaster()
  {
    return getSiteMaster(null);

  }
 
  public com.terraframe.mojo.query.SelectableChar getSiteMaster(String alias)
  {
    return (com.terraframe.mojo.query.SelectableChar)this.get(dss.vector.solutions.ontology.AllPaths.SITEMASTER, alias, null);

  }
 
  public com.terraframe.mojo.query.SelectableChar getSiteMaster(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.SelectableChar)this.get(dss.vector.solutions.ontology.AllPaths.SITEMASTER, alias, displayLabel);

  }
  public com.terraframe.mojo.query.SelectableChar getType()
  {
    return getType(null);

  }
 
  public com.terraframe.mojo.query.SelectableChar getType(String alias)
  {
    return (com.terraframe.mojo.query.SelectableChar)this.get(dss.vector.solutions.ontology.AllPaths.TYPE, alias, null);

  }
 
  public com.terraframe.mojo.query.SelectableChar getType(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.SelectableChar)this.get(dss.vector.solutions.ontology.AllPaths.TYPE, alias, displayLabel);

  }
  protected com.terraframe.mojo.query.AttributeReference referenceFactory( com.terraframe.mojo.dataaccess.MdAttributeRefDAOIF mdAttributeIF, String attributeNamespace, String definingTableName, String definingTableAlias,  com.terraframe.mojo.dataaccess.MdBusinessDAOIF referenceMdBusinessIF, String referenceTableAlias, com.terraframe.mojo.query.ComponentQuery rootQuery, java.util.Set<com.terraframe.mojo.query.Join> tableJoinSet, String userDefinedAlias, String userDefinedDisplayLabel)
  {
    String name = mdAttributeIF.definesAttribute();
    
    if (name.equals("childTerm")) 
    {
       return new dss.vector.solutions.ontology.TermQuery.TermQueryReference((com.terraframe.mojo.dataaccess.MdAttributeRefDAOIF)mdAttributeIF, attributeNamespace, definingTableName, definingTableAlias, referenceMdBusinessIF, referenceTableAlias, rootQuery, tableJoinSet, userDefinedAlias, userDefinedDisplayLabel);
    }
    else if (name.equals("createdBy")) 
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
    else if (name.equals("ontologyRelationship")) 
    {
       return new dss.vector.solutions.ontology.OntologyRelationshipQuery.OntologyRelationshipQueryReference((com.terraframe.mojo.dataaccess.MdAttributeRefDAOIF)mdAttributeIF, attributeNamespace, definingTableName, definingTableAlias, referenceMdBusinessIF, referenceTableAlias, rootQuery, tableJoinSet, userDefinedAlias, userDefinedDisplayLabel);
    }
    else if (name.equals("owner")) 
    {
       return new com.terraframe.mojo.system.ActorQuery.ActorQueryReference((com.terraframe.mojo.dataaccess.MdAttributeRefDAOIF)mdAttributeIF, attributeNamespace, definingTableName, definingTableAlias, referenceMdBusinessIF, referenceTableAlias, rootQuery, tableJoinSet, userDefinedAlias, userDefinedDisplayLabel);
    }
    else if (name.equals("parentTerm")) 
    {
       return new dss.vector.solutions.ontology.TermQuery.TermQueryReference((com.terraframe.mojo.dataaccess.MdAttributeRefDAOIF)mdAttributeIF, attributeNamespace, definingTableName, definingTableAlias, referenceMdBusinessIF, referenceTableAlias, rootQuery, tableJoinSet, userDefinedAlias, userDefinedDisplayLabel);
    }
    else 
    {
      String error = "Attribute type ["+mdAttributeIF.getType()+"] is invalid.";
      throw new com.terraframe.mojo.query.QueryException(error);
    }
  }

  }
}
