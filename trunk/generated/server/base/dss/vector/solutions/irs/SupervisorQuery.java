package dss.vector.solutions.irs;

@com.terraframe.mojo.business.ClassSignature(hash = -311704263)
/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to Supervisor.java
 *
 * @author Autogenerated by TerraFrame
 */
public  class SupervisorQuery extends com.terraframe.mojo.query.GeneratedBusinessQuery
 implements com.terraframe.mojo.generation.loader.Reloadable
{
private static final long serialVersionUID = -311704263;

  public SupervisorQuery(com.terraframe.mojo.query.QueryFactory componentQueryFactory)
  {
     super();
    if (this.getComponentQuery() == null)
    {
      com.terraframe.mojo.business.BusinessQuery businessQuery = componentQueryFactory.businessQuery(this.getClassType());

       this.setBusinessQuery(businessQuery);
    }
  }

  public String getClassType()
  {
    return dss.vector.solutions.irs.Supervisor.CLASS;
  }
  public com.terraframe.mojo.query.AttributeMoment getCreateDate()
  {
    return getCreateDate(null);

  }
 
  public com.terraframe.mojo.query.AttributeMoment getCreateDate(String alias)
  {
    return (com.terraframe.mojo.query.AttributeMoment)this.getComponentQuery().attributeFactory(dss.vector.solutions.irs.Supervisor.CREATEDATE, "com.terraframe.mojo.system.metadata.MdAttributeDateTime", alias, null);

  }
 
  public com.terraframe.mojo.query.AttributeMoment getCreateDate(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.AttributeMoment)this.getComponentQuery().attributeFactory(dss.vector.solutions.irs.Supervisor.CREATEDATE, "com.terraframe.mojo.system.metadata.MdAttributeDateTime", alias, displayLabel);

  }
  public com.terraframe.mojo.system.SingleActorQuery.SingleActorQueryReferenceIF getCreatedBy()
  {
    return getCreatedBy(null);

  }
 
  public com.terraframe.mojo.system.SingleActorQuery.SingleActorQueryReferenceIF getCreatedBy(String alias)
  {

    com.terraframe.mojo.dataaccess.MdAttributeDAOIF mdAttributeIF = this.getComponentQuery().getMdAttributeROfromMap("createdBy");

    return (com.terraframe.mojo.system.SingleActorQuery.SingleActorQueryReferenceIF)this.getComponentQuery().internalAttributeFactory(dss.vector.solutions.irs.Supervisor.CREATEDBY, mdAttributeIF, this, alias, null);

  }
 
  public com.terraframe.mojo.system.SingleActorQuery.SingleActorQueryReferenceIF getCreatedBy(String alias, String displayLabel)
  {

    com.terraframe.mojo.dataaccess.MdAttributeDAOIF mdAttributeIF = this.getComponentQuery().getMdAttributeROfromMap("createdBy");

    return (com.terraframe.mojo.system.SingleActorQuery.SingleActorQueryReferenceIF)this.getComponentQuery().internalAttributeFactory(dss.vector.solutions.irs.Supervisor.CREATEDBY, mdAttributeIF, this, alias, displayLabel);

  }
  public com.terraframe.mojo.system.metadata.MdDomainQuery.MdDomainQueryReferenceIF getEntityDomain()
  {
    return getEntityDomain(null);

  }
 
  public com.terraframe.mojo.system.metadata.MdDomainQuery.MdDomainQueryReferenceIF getEntityDomain(String alias)
  {

    com.terraframe.mojo.dataaccess.MdAttributeDAOIF mdAttributeIF = this.getComponentQuery().getMdAttributeROfromMap("entityDomain");

    return (com.terraframe.mojo.system.metadata.MdDomainQuery.MdDomainQueryReferenceIF)this.getComponentQuery().internalAttributeFactory(dss.vector.solutions.irs.Supervisor.ENTITYDOMAIN, mdAttributeIF, this, alias, null);

  }
 
  public com.terraframe.mojo.system.metadata.MdDomainQuery.MdDomainQueryReferenceIF getEntityDomain(String alias, String displayLabel)
  {

    com.terraframe.mojo.dataaccess.MdAttributeDAOIF mdAttributeIF = this.getComponentQuery().getMdAttributeROfromMap("entityDomain");

    return (com.terraframe.mojo.system.metadata.MdDomainQuery.MdDomainQueryReferenceIF)this.getComponentQuery().internalAttributeFactory(dss.vector.solutions.irs.Supervisor.ENTITYDOMAIN, mdAttributeIF, this, alias, displayLabel);

  }
  public com.terraframe.mojo.query.AttributeChar getId()
  {
    return getId(null);

  }
 
  public com.terraframe.mojo.query.AttributeChar getId(String alias)
  {
    return (com.terraframe.mojo.query.AttributeChar)this.getComponentQuery().attributeFactory(dss.vector.solutions.irs.Supervisor.ID, "com.terraframe.mojo.system.metadata.MdAttributeCharacter", alias, null);

  }
 
  public com.terraframe.mojo.query.AttributeChar getId(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.AttributeChar)this.getComponentQuery().attributeFactory(dss.vector.solutions.irs.Supervisor.ID, "com.terraframe.mojo.system.metadata.MdAttributeCharacter", alias, displayLabel);

  }
  public com.terraframe.mojo.query.AttributeChar getKeyName()
  {
    return getKeyName(null);

  }
 
  public com.terraframe.mojo.query.AttributeChar getKeyName(String alias)
  {
    return (com.terraframe.mojo.query.AttributeChar)this.getComponentQuery().attributeFactory(dss.vector.solutions.irs.Supervisor.KEYNAME, "com.terraframe.mojo.system.metadata.MdAttributeCharacter", alias, null);

  }
 
  public com.terraframe.mojo.query.AttributeChar getKeyName(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.AttributeChar)this.getComponentQuery().attributeFactory(dss.vector.solutions.irs.Supervisor.KEYNAME, "com.terraframe.mojo.system.metadata.MdAttributeCharacter", alias, displayLabel);

  }
  public com.terraframe.mojo.query.AttributeMoment getLastUpdateDate()
  {
    return getLastUpdateDate(null);

  }
 
  public com.terraframe.mojo.query.AttributeMoment getLastUpdateDate(String alias)
  {
    return (com.terraframe.mojo.query.AttributeMoment)this.getComponentQuery().attributeFactory(dss.vector.solutions.irs.Supervisor.LASTUPDATEDATE, "com.terraframe.mojo.system.metadata.MdAttributeDateTime", alias, null);

  }
 
  public com.terraframe.mojo.query.AttributeMoment getLastUpdateDate(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.AttributeMoment)this.getComponentQuery().attributeFactory(dss.vector.solutions.irs.Supervisor.LASTUPDATEDATE, "com.terraframe.mojo.system.metadata.MdAttributeDateTime", alias, displayLabel);

  }
  public com.terraframe.mojo.system.SingleActorQuery.SingleActorQueryReferenceIF getLastUpdatedBy()
  {
    return getLastUpdatedBy(null);

  }
 
  public com.terraframe.mojo.system.SingleActorQuery.SingleActorQueryReferenceIF getLastUpdatedBy(String alias)
  {

    com.terraframe.mojo.dataaccess.MdAttributeDAOIF mdAttributeIF = this.getComponentQuery().getMdAttributeROfromMap("lastUpdatedBy");

    return (com.terraframe.mojo.system.SingleActorQuery.SingleActorQueryReferenceIF)this.getComponentQuery().internalAttributeFactory(dss.vector.solutions.irs.Supervisor.LASTUPDATEDBY, mdAttributeIF, this, alias, null);

  }
 
  public com.terraframe.mojo.system.SingleActorQuery.SingleActorQueryReferenceIF getLastUpdatedBy(String alias, String displayLabel)
  {

    com.terraframe.mojo.dataaccess.MdAttributeDAOIF mdAttributeIF = this.getComponentQuery().getMdAttributeROfromMap("lastUpdatedBy");

    return (com.terraframe.mojo.system.SingleActorQuery.SingleActorQueryReferenceIF)this.getComponentQuery().internalAttributeFactory(dss.vector.solutions.irs.Supervisor.LASTUPDATEDBY, mdAttributeIF, this, alias, displayLabel);

  }
  public com.terraframe.mojo.system.UsersQuery.UsersQueryReferenceIF getLockedBy()
  {
    return getLockedBy(null);

  }
 
  public com.terraframe.mojo.system.UsersQuery.UsersQueryReferenceIF getLockedBy(String alias)
  {

    com.terraframe.mojo.dataaccess.MdAttributeDAOIF mdAttributeIF = this.getComponentQuery().getMdAttributeROfromMap("lockedBy");

    return (com.terraframe.mojo.system.UsersQuery.UsersQueryReferenceIF)this.getComponentQuery().internalAttributeFactory(dss.vector.solutions.irs.Supervisor.LOCKEDBY, mdAttributeIF, this, alias, null);

  }
 
  public com.terraframe.mojo.system.UsersQuery.UsersQueryReferenceIF getLockedBy(String alias, String displayLabel)
  {

    com.terraframe.mojo.dataaccess.MdAttributeDAOIF mdAttributeIF = this.getComponentQuery().getMdAttributeROfromMap("lockedBy");

    return (com.terraframe.mojo.system.UsersQuery.UsersQueryReferenceIF)this.getComponentQuery().internalAttributeFactory(dss.vector.solutions.irs.Supervisor.LOCKEDBY, mdAttributeIF, this, alias, displayLabel);

  }
  public com.terraframe.mojo.system.ActorQuery.ActorQueryReferenceIF getOwner()
  {
    return getOwner(null);

  }
 
  public com.terraframe.mojo.system.ActorQuery.ActorQueryReferenceIF getOwner(String alias)
  {

    com.terraframe.mojo.dataaccess.MdAttributeDAOIF mdAttributeIF = this.getComponentQuery().getMdAttributeROfromMap("owner");

    return (com.terraframe.mojo.system.ActorQuery.ActorQueryReferenceIF)this.getComponentQuery().internalAttributeFactory(dss.vector.solutions.irs.Supervisor.OWNER, mdAttributeIF, this, alias, null);

  }
 
  public com.terraframe.mojo.system.ActorQuery.ActorQueryReferenceIF getOwner(String alias, String displayLabel)
  {

    com.terraframe.mojo.dataaccess.MdAttributeDAOIF mdAttributeIF = this.getComponentQuery().getMdAttributeROfromMap("owner");

    return (com.terraframe.mojo.system.ActorQuery.ActorQueryReferenceIF)this.getComponentQuery().internalAttributeFactory(dss.vector.solutions.irs.Supervisor.OWNER, mdAttributeIF, this, alias, displayLabel);

  }
  public dss.vector.solutions.PersonQuery.PersonQueryReferenceIF getPerson()
  {
    return getPerson(null);

  }
 
  public dss.vector.solutions.PersonQuery.PersonQueryReferenceIF getPerson(String alias)
  {

    com.terraframe.mojo.dataaccess.MdAttributeDAOIF mdAttributeIF = this.getComponentQuery().getMdAttributeROfromMap("person");

    return (dss.vector.solutions.PersonQuery.PersonQueryReferenceIF)this.getComponentQuery().internalAttributeFactory(dss.vector.solutions.irs.Supervisor.PERSON, mdAttributeIF, this, alias, null);

  }
 
  public dss.vector.solutions.PersonQuery.PersonQueryReferenceIF getPerson(String alias, String displayLabel)
  {

    com.terraframe.mojo.dataaccess.MdAttributeDAOIF mdAttributeIF = this.getComponentQuery().getMdAttributeROfromMap("person");

    return (dss.vector.solutions.PersonQuery.PersonQueryReferenceIF)this.getComponentQuery().internalAttributeFactory(dss.vector.solutions.irs.Supervisor.PERSON, mdAttributeIF, this, alias, displayLabel);

  }
  public com.terraframe.mojo.query.AttributeLong getSeq()
  {
    return getSeq(null);

  }
 
  public com.terraframe.mojo.query.AttributeLong getSeq(String alias)
  {
    return (com.terraframe.mojo.query.AttributeLong)this.getComponentQuery().attributeFactory(dss.vector.solutions.irs.Supervisor.SEQ, "com.terraframe.mojo.system.metadata.MdAttributeLong", alias, null);

  }
 
  public com.terraframe.mojo.query.AttributeLong getSeq(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.AttributeLong)this.getComponentQuery().attributeFactory(dss.vector.solutions.irs.Supervisor.SEQ, "com.terraframe.mojo.system.metadata.MdAttributeLong", alias, displayLabel);

  }
  public com.terraframe.mojo.query.AttributeChar getSiteMaster()
  {
    return getSiteMaster(null);

  }
 
  public com.terraframe.mojo.query.AttributeChar getSiteMaster(String alias)
  {
    return (com.terraframe.mojo.query.AttributeChar)this.getComponentQuery().attributeFactory(dss.vector.solutions.irs.Supervisor.SITEMASTER, "com.terraframe.mojo.system.metadata.MdAttributeCharacter", alias, null);

  }
 
  public com.terraframe.mojo.query.AttributeChar getSiteMaster(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.AttributeChar)this.getComponentQuery().attributeFactory(dss.vector.solutions.irs.Supervisor.SITEMASTER, "com.terraframe.mojo.system.metadata.MdAttributeCharacter", alias, displayLabel);

  }
  public com.terraframe.mojo.query.AttributeChar getType()
  {
    return getType(null);

  }
 
  public com.terraframe.mojo.query.AttributeChar getType(String alias)
  {
    return (com.terraframe.mojo.query.AttributeChar)this.getComponentQuery().attributeFactory(dss.vector.solutions.irs.Supervisor.TYPE, "com.terraframe.mojo.system.metadata.MdAttributeCharacter", alias, null);

  }
 
  public com.terraframe.mojo.query.AttributeChar getType(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.AttributeChar)this.getComponentQuery().attributeFactory(dss.vector.solutions.irs.Supervisor.TYPE, "com.terraframe.mojo.system.metadata.MdAttributeCharacter", alias, displayLabel);

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
    else if (name.equals("person")) 
    {
       return new dss.vector.solutions.PersonQuery.PersonQueryReference((com.terraframe.mojo.dataaccess.MdAttributeRefDAOIF)mdAttributeIF, attributeNamespace, definingTableName, definingTableAlias, referenceMdBusinessIF, referenceTableAlias, rootQuery, tableJoinSet, userDefinedAlias, userDefinedDisplayLabel);
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
  public com.terraframe.mojo.query.OIterator<? extends Supervisor> getIterator()
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
    return new com.terraframe.mojo.business.BusinessIterator<Supervisor>(this.getComponentQuery().getMdEntityIF(), columnInfoMap, results);
  }


/**
 * Interface that masks all type unsafe query methods and defines all type safe methods.
 * This type is used when a join is performed on this class as a reference.
 **/
  public interface SupervisorQueryReferenceIF extends com.terraframe.mojo.generation.loader.Reloadable, com.terraframe.mojo.query.SelectableReference
  {

    public com.terraframe.mojo.query.AttributeMoment getCreateDate();
    public com.terraframe.mojo.query.AttributeMoment getCreateDate(String alias);
    public com.terraframe.mojo.query.AttributeMoment getCreateDate(String alias, String displayLabel);
    public com.terraframe.mojo.system.SingleActorQuery.SingleActorQueryReferenceIF getCreatedBy();
    public com.terraframe.mojo.system.SingleActorQuery.SingleActorQueryReferenceIF getCreatedBy(String alias);
    public com.terraframe.mojo.system.SingleActorQuery.SingleActorQueryReferenceIF getCreatedBy(String alias, String displayLabel);
    public com.terraframe.mojo.system.metadata.MdDomainQuery.MdDomainQueryReferenceIF getEntityDomain();
    public com.terraframe.mojo.system.metadata.MdDomainQuery.MdDomainQueryReferenceIF getEntityDomain(String alias);
    public com.terraframe.mojo.system.metadata.MdDomainQuery.MdDomainQueryReferenceIF getEntityDomain(String alias, String displayLabel);
    public com.terraframe.mojo.query.AttributeChar getId();
    public com.terraframe.mojo.query.AttributeChar getId(String alias);
    public com.terraframe.mojo.query.AttributeChar getId(String alias, String displayLabel);
    public com.terraframe.mojo.query.AttributeChar getKeyName();
    public com.terraframe.mojo.query.AttributeChar getKeyName(String alias);
    public com.terraframe.mojo.query.AttributeChar getKeyName(String alias, String displayLabel);
    public com.terraframe.mojo.query.AttributeMoment getLastUpdateDate();
    public com.terraframe.mojo.query.AttributeMoment getLastUpdateDate(String alias);
    public com.terraframe.mojo.query.AttributeMoment getLastUpdateDate(String alias, String displayLabel);
    public com.terraframe.mojo.system.SingleActorQuery.SingleActorQueryReferenceIF getLastUpdatedBy();
    public com.terraframe.mojo.system.SingleActorQuery.SingleActorQueryReferenceIF getLastUpdatedBy(String alias);
    public com.terraframe.mojo.system.SingleActorQuery.SingleActorQueryReferenceIF getLastUpdatedBy(String alias, String displayLabel);
    public com.terraframe.mojo.system.UsersQuery.UsersQueryReferenceIF getLockedBy();
    public com.terraframe.mojo.system.UsersQuery.UsersQueryReferenceIF getLockedBy(String alias);
    public com.terraframe.mojo.system.UsersQuery.UsersQueryReferenceIF getLockedBy(String alias, String displayLabel);
    public com.terraframe.mojo.system.ActorQuery.ActorQueryReferenceIF getOwner();
    public com.terraframe.mojo.system.ActorQuery.ActorQueryReferenceIF getOwner(String alias);
    public com.terraframe.mojo.system.ActorQuery.ActorQueryReferenceIF getOwner(String alias, String displayLabel);
    public dss.vector.solutions.PersonQuery.PersonQueryReferenceIF getPerson();
    public dss.vector.solutions.PersonQuery.PersonQueryReferenceIF getPerson(String alias);
    public dss.vector.solutions.PersonQuery.PersonQueryReferenceIF getPerson(String alias, String displayLabel);
    public com.terraframe.mojo.query.AttributeLong getSeq();
    public com.terraframe.mojo.query.AttributeLong getSeq(String alias);
    public com.terraframe.mojo.query.AttributeLong getSeq(String alias, String displayLabel);
    public com.terraframe.mojo.query.AttributeChar getSiteMaster();
    public com.terraframe.mojo.query.AttributeChar getSiteMaster(String alias);
    public com.terraframe.mojo.query.AttributeChar getSiteMaster(String alias, String displayLabel);
    public com.terraframe.mojo.query.AttributeChar getType();
    public com.terraframe.mojo.query.AttributeChar getType(String alias);
    public com.terraframe.mojo.query.AttributeChar getType(String alias, String displayLabel);

    public com.terraframe.mojo.query.BasicCondition EQ(dss.vector.solutions.irs.Supervisor supervisor);

    public com.terraframe.mojo.query.BasicCondition NE(dss.vector.solutions.irs.Supervisor supervisor);

  }

/**
 * Implements type safe query methods.
 * This type is used when a join is performed on this class as a reference.
 **/
  public static class SupervisorQueryReference extends com.terraframe.mojo.query.AttributeReference
 implements SupervisorQueryReferenceIF
, com.terraframe.mojo.generation.loader.Reloadable
  {
private static final long serialVersionUID = 1557931739;

  public SupervisorQueryReference(com.terraframe.mojo.dataaccess.MdAttributeRefDAOIF mdAttributeIF, String attributeNamespace, String definingTableName, String definingTableAlias, com.terraframe.mojo.dataaccess.MdBusinessDAOIF referenceMdBusinessIF, String referenceTableAlias, com.terraframe.mojo.query.ComponentQuery rootQuery, java.util.Set<com.terraframe.mojo.query.Join> tableJoinSet, String alias, String displayLabel)
  {
    super(mdAttributeIF, attributeNamespace, definingTableName, definingTableAlias, referenceMdBusinessIF, referenceTableAlias, rootQuery, tableJoinSet, alias, displayLabel);

  }


    public com.terraframe.mojo.query.BasicCondition EQ(dss.vector.solutions.irs.Supervisor supervisor)
    {
      return this.EQ(supervisor.getId());
    }

    public com.terraframe.mojo.query.BasicCondition NE(dss.vector.solutions.irs.Supervisor supervisor)
    {
      return this.NE(supervisor.getId());
    }

  public com.terraframe.mojo.query.AttributeMoment getCreateDate()
  {
    return getCreateDate(null);

  }
 
  public com.terraframe.mojo.query.AttributeMoment getCreateDate(String alias)
  {
    return (com.terraframe.mojo.query.AttributeMoment)this.attributeFactory("createDate", "com.terraframe.mojo.system.metadata.MdAttributeDateTime", alias, null);

  }
 
  public com.terraframe.mojo.query.AttributeMoment getCreateDate(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.AttributeMoment)this.attributeFactory("createDate", "com.terraframe.mojo.system.metadata.MdAttributeDateTime", alias, displayLabel);

  }
  public com.terraframe.mojo.system.SingleActorQuery.SingleActorQueryReferenceIF getCreatedBy()
  {
    return getCreatedBy(null);

  }
 
  public com.terraframe.mojo.system.SingleActorQuery.SingleActorQueryReferenceIF getCreatedBy(String alias)
  {
    return (com.terraframe.mojo.system.SingleActorQuery.SingleActorQueryReferenceIF)this.attributeFactory("createdBy", "com.terraframe.mojo.system.metadata.MdAttributeReference", alias, null);

  }
 
  public com.terraframe.mojo.system.SingleActorQuery.SingleActorQueryReferenceIF getCreatedBy(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.system.SingleActorQuery.SingleActorQueryReferenceIF)this.attributeFactory("createdBy", "com.terraframe.mojo.system.metadata.MdAttributeReference", alias, displayLabel);

  }
  public com.terraframe.mojo.system.metadata.MdDomainQuery.MdDomainQueryReferenceIF getEntityDomain()
  {
    return getEntityDomain(null);

  }
 
  public com.terraframe.mojo.system.metadata.MdDomainQuery.MdDomainQueryReferenceIF getEntityDomain(String alias)
  {
    return (com.terraframe.mojo.system.metadata.MdDomainQuery.MdDomainQueryReferenceIF)this.attributeFactory("entityDomain", "com.terraframe.mojo.system.metadata.MdAttributeReference", alias, null);

  }
 
  public com.terraframe.mojo.system.metadata.MdDomainQuery.MdDomainQueryReferenceIF getEntityDomain(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.system.metadata.MdDomainQuery.MdDomainQueryReferenceIF)this.attributeFactory("entityDomain", "com.terraframe.mojo.system.metadata.MdAttributeReference", alias, displayLabel);

  }
  public com.terraframe.mojo.query.AttributeChar getId()
  {
    return getId(null);

  }
 
  public com.terraframe.mojo.query.AttributeChar getId(String alias)
  {
    return (com.terraframe.mojo.query.AttributeChar)this.attributeFactory("id", "com.terraframe.mojo.system.metadata.MdAttributeCharacter", alias, null);

  }
 
  public com.terraframe.mojo.query.AttributeChar getId(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.AttributeChar)this.attributeFactory("id", "com.terraframe.mojo.system.metadata.MdAttributeCharacter", alias, displayLabel);

  }
  public com.terraframe.mojo.query.AttributeChar getKeyName()
  {
    return getKeyName(null);

  }
 
  public com.terraframe.mojo.query.AttributeChar getKeyName(String alias)
  {
    return (com.terraframe.mojo.query.AttributeChar)this.attributeFactory("keyName", "com.terraframe.mojo.system.metadata.MdAttributeCharacter", alias, null);

  }
 
  public com.terraframe.mojo.query.AttributeChar getKeyName(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.AttributeChar)this.attributeFactory("keyName", "com.terraframe.mojo.system.metadata.MdAttributeCharacter", alias, displayLabel);

  }
  public com.terraframe.mojo.query.AttributeMoment getLastUpdateDate()
  {
    return getLastUpdateDate(null);

  }
 
  public com.terraframe.mojo.query.AttributeMoment getLastUpdateDate(String alias)
  {
    return (com.terraframe.mojo.query.AttributeMoment)this.attributeFactory("lastUpdateDate", "com.terraframe.mojo.system.metadata.MdAttributeDateTime", alias, null);

  }
 
  public com.terraframe.mojo.query.AttributeMoment getLastUpdateDate(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.AttributeMoment)this.attributeFactory("lastUpdateDate", "com.terraframe.mojo.system.metadata.MdAttributeDateTime", alias, displayLabel);

  }
  public com.terraframe.mojo.system.SingleActorQuery.SingleActorQueryReferenceIF getLastUpdatedBy()
  {
    return getLastUpdatedBy(null);

  }
 
  public com.terraframe.mojo.system.SingleActorQuery.SingleActorQueryReferenceIF getLastUpdatedBy(String alias)
  {
    return (com.terraframe.mojo.system.SingleActorQuery.SingleActorQueryReferenceIF)this.attributeFactory("lastUpdatedBy", "com.terraframe.mojo.system.metadata.MdAttributeReference", alias, null);

  }
 
  public com.terraframe.mojo.system.SingleActorQuery.SingleActorQueryReferenceIF getLastUpdatedBy(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.system.SingleActorQuery.SingleActorQueryReferenceIF)this.attributeFactory("lastUpdatedBy", "com.terraframe.mojo.system.metadata.MdAttributeReference", alias, displayLabel);

  }
  public com.terraframe.mojo.system.UsersQuery.UsersQueryReferenceIF getLockedBy()
  {
    return getLockedBy(null);

  }
 
  public com.terraframe.mojo.system.UsersQuery.UsersQueryReferenceIF getLockedBy(String alias)
  {
    return (com.terraframe.mojo.system.UsersQuery.UsersQueryReferenceIF)this.attributeFactory("lockedBy", "com.terraframe.mojo.system.metadata.MdAttributeReference", alias, null);

  }
 
  public com.terraframe.mojo.system.UsersQuery.UsersQueryReferenceIF getLockedBy(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.system.UsersQuery.UsersQueryReferenceIF)this.attributeFactory("lockedBy", "com.terraframe.mojo.system.metadata.MdAttributeReference", alias, displayLabel);

  }
  public com.terraframe.mojo.system.ActorQuery.ActorQueryReferenceIF getOwner()
  {
    return getOwner(null);

  }
 
  public com.terraframe.mojo.system.ActorQuery.ActorQueryReferenceIF getOwner(String alias)
  {
    return (com.terraframe.mojo.system.ActorQuery.ActorQueryReferenceIF)this.attributeFactory("owner", "com.terraframe.mojo.system.metadata.MdAttributeReference", alias, null);

  }
 
  public com.terraframe.mojo.system.ActorQuery.ActorQueryReferenceIF getOwner(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.system.ActorQuery.ActorQueryReferenceIF)this.attributeFactory("owner", "com.terraframe.mojo.system.metadata.MdAttributeReference", alias, displayLabel);

  }
  public dss.vector.solutions.PersonQuery.PersonQueryReferenceIF getPerson()
  {
    return getPerson(null);

  }
 
  public dss.vector.solutions.PersonQuery.PersonQueryReferenceIF getPerson(String alias)
  {
    return (dss.vector.solutions.PersonQuery.PersonQueryReferenceIF)this.attributeFactory("person", "com.terraframe.mojo.system.metadata.MdAttributeReference", alias, null);

  }
 
  public dss.vector.solutions.PersonQuery.PersonQueryReferenceIF getPerson(String alias, String displayLabel)
  {
    return (dss.vector.solutions.PersonQuery.PersonQueryReferenceIF)this.attributeFactory("person", "com.terraframe.mojo.system.metadata.MdAttributeReference", alias, displayLabel);

  }
  public com.terraframe.mojo.query.AttributeLong getSeq()
  {
    return getSeq(null);

  }
 
  public com.terraframe.mojo.query.AttributeLong getSeq(String alias)
  {
    return (com.terraframe.mojo.query.AttributeLong)this.attributeFactory("seq", "com.terraframe.mojo.system.metadata.MdAttributeLong", alias, null);

  }
 
  public com.terraframe.mojo.query.AttributeLong getSeq(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.AttributeLong)this.attributeFactory("seq", "com.terraframe.mojo.system.metadata.MdAttributeLong", alias, displayLabel);

  }
  public com.terraframe.mojo.query.AttributeChar getSiteMaster()
  {
    return getSiteMaster(null);

  }
 
  public com.terraframe.mojo.query.AttributeChar getSiteMaster(String alias)
  {
    return (com.terraframe.mojo.query.AttributeChar)this.attributeFactory("siteMaster", "com.terraframe.mojo.system.metadata.MdAttributeCharacter", alias, null);

  }
 
  public com.terraframe.mojo.query.AttributeChar getSiteMaster(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.AttributeChar)this.attributeFactory("siteMaster", "com.terraframe.mojo.system.metadata.MdAttributeCharacter", alias, displayLabel);

  }
  public com.terraframe.mojo.query.AttributeChar getType()
  {
    return getType(null);

  }
 
  public com.terraframe.mojo.query.AttributeChar getType(String alias)
  {
    return (com.terraframe.mojo.query.AttributeChar)this.attributeFactory("type", "com.terraframe.mojo.system.metadata.MdAttributeCharacter", alias, null);

  }
 
  public com.terraframe.mojo.query.AttributeChar getType(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.AttributeChar)this.attributeFactory("type", "com.terraframe.mojo.system.metadata.MdAttributeCharacter", alias, displayLabel);

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
    else if (name.equals("person")) 
    {
       return new dss.vector.solutions.PersonQuery.PersonQueryReference((com.terraframe.mojo.dataaccess.MdAttributeRefDAOIF)mdAttributeIF, attributeNamespace, definingTableName, definingTableAlias, referenceMdBusinessIF, referenceTableAlias, rootQuery, tableJoinSet, userDefinedAlias, userDefinedDisplayLabel);
    }
    else 
    {
      String error = "Attribute type ["+mdAttributeIF.getType()+"] is invalid.";
      throw new com.terraframe.mojo.query.QueryException(error);
    }
  }

  }
}
