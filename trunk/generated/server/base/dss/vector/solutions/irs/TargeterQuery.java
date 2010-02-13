package dss.vector.solutions.irs;

@com.terraframe.mojo.business.ClassSignature(hash = 1665076196)
/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to Targeter.java
 *
 * @author Autogenerated by TerraFrame
 */
public  class TargeterQuery extends com.terraframe.mojo.query.GeneratedBusinessQuery
 implements com.terraframe.mojo.generation.loader.Reloadable
{
private static final long serialVersionUID = 1665076196;

  public TargeterQuery(com.terraframe.mojo.query.QueryFactory componentQueryFactory)
  {
     super();
    if (this.getComponentQuery() == null)
    {
      com.terraframe.mojo.business.BusinessQuery businessQuery = componentQueryFactory.businessQuery(this.getClassType());

       this.setBusinessQuery(businessQuery);
    }
  }

  public TargeterQuery(com.terraframe.mojo.query.ValueQuery valueQuery)
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
    return dss.vector.solutions.irs.Targeter.CLASS;
  }
  public com.terraframe.mojo.query.SelectableSingleMoment getCreateDate()
  {
    return getCreateDate(null);

  }
 
  public com.terraframe.mojo.query.SelectableSingleMoment getCreateDate(String alias)
  {
    return (com.terraframe.mojo.query.SelectableSingleMoment)this.getComponentQuery().get(dss.vector.solutions.irs.Targeter.CREATEDATE, alias, null);

  }
 
  public com.terraframe.mojo.query.SelectableSingleMoment getCreateDate(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.SelectableSingleMoment)this.getComponentQuery().get(dss.vector.solutions.irs.Targeter.CREATEDATE, alias, displayLabel);

  }
  public com.terraframe.mojo.system.SingleActorQuery.SingleActorQueryReferenceIF getCreatedBy()
  {
    return getCreatedBy(null);

  }
 
  public com.terraframe.mojo.system.SingleActorQuery.SingleActorQueryReferenceIF getCreatedBy(String alias)
  {

    com.terraframe.mojo.dataaccess.MdAttributeDAOIF mdAttributeIF = this.getComponentQuery().getMdAttributeROfromMap("createdBy");

    return (com.terraframe.mojo.system.SingleActorQuery.SingleActorQueryReferenceIF)this.getComponentQuery().internalAttributeFactory(dss.vector.solutions.irs.Targeter.CREATEDBY, mdAttributeIF, this, alias, null);

  }
 
  public com.terraframe.mojo.system.SingleActorQuery.SingleActorQueryReferenceIF getCreatedBy(String alias, String displayLabel)
  {

    com.terraframe.mojo.dataaccess.MdAttributeDAOIF mdAttributeIF = this.getComponentQuery().getMdAttributeROfromMap("createdBy");

    return (com.terraframe.mojo.system.SingleActorQuery.SingleActorQueryReferenceIF)this.getComponentQuery().internalAttributeFactory(dss.vector.solutions.irs.Targeter.CREATEDBY, mdAttributeIF, this, alias, displayLabel);

  }
  public com.terraframe.mojo.system.metadata.MdDomainQuery.MdDomainQueryReferenceIF getEntityDomain()
  {
    return getEntityDomain(null);

  }
 
  public com.terraframe.mojo.system.metadata.MdDomainQuery.MdDomainQueryReferenceIF getEntityDomain(String alias)
  {

    com.terraframe.mojo.dataaccess.MdAttributeDAOIF mdAttributeIF = this.getComponentQuery().getMdAttributeROfromMap("entityDomain");

    return (com.terraframe.mojo.system.metadata.MdDomainQuery.MdDomainQueryReferenceIF)this.getComponentQuery().internalAttributeFactory(dss.vector.solutions.irs.Targeter.ENTITYDOMAIN, mdAttributeIF, this, alias, null);

  }
 
  public com.terraframe.mojo.system.metadata.MdDomainQuery.MdDomainQueryReferenceIF getEntityDomain(String alias, String displayLabel)
  {

    com.terraframe.mojo.dataaccess.MdAttributeDAOIF mdAttributeIF = this.getComponentQuery().getMdAttributeROfromMap("entityDomain");

    return (com.terraframe.mojo.system.metadata.MdDomainQuery.MdDomainQueryReferenceIF)this.getComponentQuery().internalAttributeFactory(dss.vector.solutions.irs.Targeter.ENTITYDOMAIN, mdAttributeIF, this, alias, displayLabel);

  }
  public com.terraframe.mojo.query.SelectableSingleChar getId()
  {
    return getId(null);

  }
 
  public com.terraframe.mojo.query.SelectableSingleChar getId(String alias)
  {
    return (com.terraframe.mojo.query.SelectableSingleChar)this.getComponentQuery().get(dss.vector.solutions.irs.Targeter.ID, alias, null);

  }
 
  public com.terraframe.mojo.query.SelectableSingleChar getId(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.SelectableSingleChar)this.getComponentQuery().get(dss.vector.solutions.irs.Targeter.ID, alias, displayLabel);

  }
  public com.terraframe.mojo.query.SelectableSingleChar getKeyName()
  {
    return getKeyName(null);

  }
 
  public com.terraframe.mojo.query.SelectableSingleChar getKeyName(String alias)
  {
    return (com.terraframe.mojo.query.SelectableSingleChar)this.getComponentQuery().get(dss.vector.solutions.irs.Targeter.KEYNAME, alias, null);

  }
 
  public com.terraframe.mojo.query.SelectableSingleChar getKeyName(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.SelectableSingleChar)this.getComponentQuery().get(dss.vector.solutions.irs.Targeter.KEYNAME, alias, displayLabel);

  }
  public com.terraframe.mojo.query.SelectableSingleMoment getLastUpdateDate()
  {
    return getLastUpdateDate(null);

  }
 
  public com.terraframe.mojo.query.SelectableSingleMoment getLastUpdateDate(String alias)
  {
    return (com.terraframe.mojo.query.SelectableSingleMoment)this.getComponentQuery().get(dss.vector.solutions.irs.Targeter.LASTUPDATEDATE, alias, null);

  }
 
  public com.terraframe.mojo.query.SelectableSingleMoment getLastUpdateDate(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.SelectableSingleMoment)this.getComponentQuery().get(dss.vector.solutions.irs.Targeter.LASTUPDATEDATE, alias, displayLabel);

  }
  public com.terraframe.mojo.system.SingleActorQuery.SingleActorQueryReferenceIF getLastUpdatedBy()
  {
    return getLastUpdatedBy(null);

  }
 
  public com.terraframe.mojo.system.SingleActorQuery.SingleActorQueryReferenceIF getLastUpdatedBy(String alias)
  {

    com.terraframe.mojo.dataaccess.MdAttributeDAOIF mdAttributeIF = this.getComponentQuery().getMdAttributeROfromMap("lastUpdatedBy");

    return (com.terraframe.mojo.system.SingleActorQuery.SingleActorQueryReferenceIF)this.getComponentQuery().internalAttributeFactory(dss.vector.solutions.irs.Targeter.LASTUPDATEDBY, mdAttributeIF, this, alias, null);

  }
 
  public com.terraframe.mojo.system.SingleActorQuery.SingleActorQueryReferenceIF getLastUpdatedBy(String alias, String displayLabel)
  {

    com.terraframe.mojo.dataaccess.MdAttributeDAOIF mdAttributeIF = this.getComponentQuery().getMdAttributeROfromMap("lastUpdatedBy");

    return (com.terraframe.mojo.system.SingleActorQuery.SingleActorQueryReferenceIF)this.getComponentQuery().internalAttributeFactory(dss.vector.solutions.irs.Targeter.LASTUPDATEDBY, mdAttributeIF, this, alias, displayLabel);

  }
  public com.terraframe.mojo.system.UsersQuery.UsersQueryReferenceIF getLockedBy()
  {
    return getLockedBy(null);

  }
 
  public com.terraframe.mojo.system.UsersQuery.UsersQueryReferenceIF getLockedBy(String alias)
  {

    com.terraframe.mojo.dataaccess.MdAttributeDAOIF mdAttributeIF = this.getComponentQuery().getMdAttributeROfromMap("lockedBy");

    return (com.terraframe.mojo.system.UsersQuery.UsersQueryReferenceIF)this.getComponentQuery().internalAttributeFactory(dss.vector.solutions.irs.Targeter.LOCKEDBY, mdAttributeIF, this, alias, null);

  }
 
  public com.terraframe.mojo.system.UsersQuery.UsersQueryReferenceIF getLockedBy(String alias, String displayLabel)
  {

    com.terraframe.mojo.dataaccess.MdAttributeDAOIF mdAttributeIF = this.getComponentQuery().getMdAttributeROfromMap("lockedBy");

    return (com.terraframe.mojo.system.UsersQuery.UsersQueryReferenceIF)this.getComponentQuery().internalAttributeFactory(dss.vector.solutions.irs.Targeter.LOCKEDBY, mdAttributeIF, this, alias, displayLabel);

  }
  public com.terraframe.mojo.system.ActorQuery.ActorQueryReferenceIF getOwner()
  {
    return getOwner(null);

  }
 
  public com.terraframe.mojo.system.ActorQuery.ActorQueryReferenceIF getOwner(String alias)
  {

    com.terraframe.mojo.dataaccess.MdAttributeDAOIF mdAttributeIF = this.getComponentQuery().getMdAttributeROfromMap("owner");

    return (com.terraframe.mojo.system.ActorQuery.ActorQueryReferenceIF)this.getComponentQuery().internalAttributeFactory(dss.vector.solutions.irs.Targeter.OWNER, mdAttributeIF, this, alias, null);

  }
 
  public com.terraframe.mojo.system.ActorQuery.ActorQueryReferenceIF getOwner(String alias, String displayLabel)
  {

    com.terraframe.mojo.dataaccess.MdAttributeDAOIF mdAttributeIF = this.getComponentQuery().getMdAttributeROfromMap("owner");

    return (com.terraframe.mojo.system.ActorQuery.ActorQueryReferenceIF)this.getComponentQuery().internalAttributeFactory(dss.vector.solutions.irs.Targeter.OWNER, mdAttributeIF, this, alias, displayLabel);

  }
  public com.terraframe.mojo.query.SelectableSingleLong getSeq()
  {
    return getSeq(null);

  }
 
  public com.terraframe.mojo.query.SelectableSingleLong getSeq(String alias)
  {
    return (com.terraframe.mojo.query.SelectableSingleLong)this.getComponentQuery().get(dss.vector.solutions.irs.Targeter.SEQ, alias, null);

  }
 
  public com.terraframe.mojo.query.SelectableSingleLong getSeq(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.SelectableSingleLong)this.getComponentQuery().get(dss.vector.solutions.irs.Targeter.SEQ, alias, displayLabel);

  }
  public com.terraframe.mojo.query.SelectableSingleChar getSiteMaster()
  {
    return getSiteMaster(null);

  }
 
  public com.terraframe.mojo.query.SelectableSingleChar getSiteMaster(String alias)
  {
    return (com.terraframe.mojo.query.SelectableSingleChar)this.getComponentQuery().get(dss.vector.solutions.irs.Targeter.SITEMASTER, alias, null);

  }
 
  public com.terraframe.mojo.query.SelectableSingleChar getSiteMaster(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.SelectableSingleChar)this.getComponentQuery().get(dss.vector.solutions.irs.Targeter.SITEMASTER, alias, displayLabel);

  }
  public com.terraframe.mojo.query.SelectableSingleChar getType()
  {
    return getType(null);

  }
 
  public com.terraframe.mojo.query.SelectableSingleChar getType(String alias)
  {
    return (com.terraframe.mojo.query.SelectableSingleChar)this.getComponentQuery().get(dss.vector.solutions.irs.Targeter.TYPE, alias, null);

  }
 
  public com.terraframe.mojo.query.SelectableSingleChar getType(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.SelectableSingleChar)this.getComponentQuery().get(dss.vector.solutions.irs.Targeter.TYPE, alias, displayLabel);

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

  /**  
   * Returns an iterator of Business objects that match the query criteria specified
   * on this query object. 
   * @return iterator of Business objects that match the query criteria specified
   * on this query object.
   */
  public com.terraframe.mojo.query.OIterator<? extends Targeter> getIterator()
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
    return new com.terraframe.mojo.business.BusinessIterator<Targeter>(this.getComponentQuery().getMdEntityIF(), columnInfoMap, results);
  }


/**
 * Interface that masks all type unsafe query methods and defines all type safe methods.
 * This type is used when a join is performed on this class as a reference.
 **/
  public interface TargeterQueryReferenceIF extends com.terraframe.mojo.generation.loader.Reloadable, com.terraframe.mojo.query.SelectableReference
  {

    public com.terraframe.mojo.query.SelectableSingleMoment getCreateDate();
    public com.terraframe.mojo.query.SelectableSingleMoment getCreateDate(String alias);
    public com.terraframe.mojo.query.SelectableSingleMoment getCreateDate(String alias, String displayLabel);
    public com.terraframe.mojo.system.SingleActorQuery.SingleActorQueryReferenceIF getCreatedBy();
    public com.terraframe.mojo.system.SingleActorQuery.SingleActorQueryReferenceIF getCreatedBy(String alias);
    public com.terraframe.mojo.system.SingleActorQuery.SingleActorQueryReferenceIF getCreatedBy(String alias, String displayLabel);
    public com.terraframe.mojo.system.metadata.MdDomainQuery.MdDomainQueryReferenceIF getEntityDomain();
    public com.terraframe.mojo.system.metadata.MdDomainQuery.MdDomainQueryReferenceIF getEntityDomain(String alias);
    public com.terraframe.mojo.system.metadata.MdDomainQuery.MdDomainQueryReferenceIF getEntityDomain(String alias, String displayLabel);
    public com.terraframe.mojo.query.SelectableSingleChar getId();
    public com.terraframe.mojo.query.SelectableSingleChar getId(String alias);
    public com.terraframe.mojo.query.SelectableSingleChar getId(String alias, String displayLabel);
    public com.terraframe.mojo.query.SelectableSingleChar getKeyName();
    public com.terraframe.mojo.query.SelectableSingleChar getKeyName(String alias);
    public com.terraframe.mojo.query.SelectableSingleChar getKeyName(String alias, String displayLabel);
    public com.terraframe.mojo.query.SelectableSingleMoment getLastUpdateDate();
    public com.terraframe.mojo.query.SelectableSingleMoment getLastUpdateDate(String alias);
    public com.terraframe.mojo.query.SelectableSingleMoment getLastUpdateDate(String alias, String displayLabel);
    public com.terraframe.mojo.system.SingleActorQuery.SingleActorQueryReferenceIF getLastUpdatedBy();
    public com.terraframe.mojo.system.SingleActorQuery.SingleActorQueryReferenceIF getLastUpdatedBy(String alias);
    public com.terraframe.mojo.system.SingleActorQuery.SingleActorQueryReferenceIF getLastUpdatedBy(String alias, String displayLabel);
    public com.terraframe.mojo.system.UsersQuery.UsersQueryReferenceIF getLockedBy();
    public com.terraframe.mojo.system.UsersQuery.UsersQueryReferenceIF getLockedBy(String alias);
    public com.terraframe.mojo.system.UsersQuery.UsersQueryReferenceIF getLockedBy(String alias, String displayLabel);
    public com.terraframe.mojo.system.ActorQuery.ActorQueryReferenceIF getOwner();
    public com.terraframe.mojo.system.ActorQuery.ActorQueryReferenceIF getOwner(String alias);
    public com.terraframe.mojo.system.ActorQuery.ActorQueryReferenceIF getOwner(String alias, String displayLabel);
    public com.terraframe.mojo.query.SelectableSingleLong getSeq();
    public com.terraframe.mojo.query.SelectableSingleLong getSeq(String alias);
    public com.terraframe.mojo.query.SelectableSingleLong getSeq(String alias, String displayLabel);
    public com.terraframe.mojo.query.SelectableSingleChar getSiteMaster();
    public com.terraframe.mojo.query.SelectableSingleChar getSiteMaster(String alias);
    public com.terraframe.mojo.query.SelectableSingleChar getSiteMaster(String alias, String displayLabel);
    public com.terraframe.mojo.query.SelectableSingleChar getType();
    public com.terraframe.mojo.query.SelectableSingleChar getType(String alias);
    public com.terraframe.mojo.query.SelectableSingleChar getType(String alias, String displayLabel);

    public com.terraframe.mojo.query.BasicCondition EQ(dss.vector.solutions.irs.Targeter targeter);

    public com.terraframe.mojo.query.BasicCondition NE(dss.vector.solutions.irs.Targeter targeter);

  }

/**
 * Implements type safe query methods.
 * This type is used when a join is performed on this class as a reference.
 **/
  public static class TargeterQueryReference extends com.terraframe.mojo.query.AttributeReference
 implements TargeterQueryReferenceIF
, com.terraframe.mojo.generation.loader.Reloadable
  {
private static final long serialVersionUID = 2096303622;

  public TargeterQueryReference(com.terraframe.mojo.dataaccess.MdAttributeRefDAOIF mdAttributeIF, String attributeNamespace, String definingTableName, String definingTableAlias, com.terraframe.mojo.dataaccess.MdBusinessDAOIF referenceMdBusinessIF, String referenceTableAlias, com.terraframe.mojo.query.ComponentQuery rootQuery, java.util.Set<com.terraframe.mojo.query.Join> tableJoinSet, String alias, String displayLabel)
  {
    super(mdAttributeIF, attributeNamespace, definingTableName, definingTableAlias, referenceMdBusinessIF, referenceTableAlias, rootQuery, tableJoinSet, alias, displayLabel);

  }


    public com.terraframe.mojo.query.BasicCondition EQ(dss.vector.solutions.irs.Targeter targeter)
    {
      return this.EQ(targeter.getId());
    }

    public com.terraframe.mojo.query.BasicCondition NE(dss.vector.solutions.irs.Targeter targeter)
    {
      return this.NE(targeter.getId());
    }

  public com.terraframe.mojo.query.SelectableSingleMoment getCreateDate()
  {
    return getCreateDate(null);

  }
 
  public com.terraframe.mojo.query.SelectableSingleMoment getCreateDate(String alias)
  {
    return (com.terraframe.mojo.query.SelectableSingleMoment)this.get(dss.vector.solutions.irs.Targeter.CREATEDATE, alias, null);

  }
 
  public com.terraframe.mojo.query.SelectableSingleMoment getCreateDate(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.SelectableSingleMoment)this.get(dss.vector.solutions.irs.Targeter.CREATEDATE, alias, displayLabel);

  }
  public com.terraframe.mojo.system.SingleActorQuery.SingleActorQueryReferenceIF getCreatedBy()
  {
    return getCreatedBy(null);

  }
 
  public com.terraframe.mojo.system.SingleActorQuery.SingleActorQueryReferenceIF getCreatedBy(String alias)
  {
    return (com.terraframe.mojo.system.SingleActorQuery.SingleActorQueryReferenceIF)this.get(dss.vector.solutions.irs.Targeter.CREATEDBY, alias, null);

  }
 
  public com.terraframe.mojo.system.SingleActorQuery.SingleActorQueryReferenceIF getCreatedBy(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.system.SingleActorQuery.SingleActorQueryReferenceIF)this.get(dss.vector.solutions.irs.Targeter.CREATEDBY,  alias, displayLabel);

  }
  public com.terraframe.mojo.system.metadata.MdDomainQuery.MdDomainQueryReferenceIF getEntityDomain()
  {
    return getEntityDomain(null);

  }
 
  public com.terraframe.mojo.system.metadata.MdDomainQuery.MdDomainQueryReferenceIF getEntityDomain(String alias)
  {
    return (com.terraframe.mojo.system.metadata.MdDomainQuery.MdDomainQueryReferenceIF)this.get(dss.vector.solutions.irs.Targeter.ENTITYDOMAIN, alias, null);

  }
 
  public com.terraframe.mojo.system.metadata.MdDomainQuery.MdDomainQueryReferenceIF getEntityDomain(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.system.metadata.MdDomainQuery.MdDomainQueryReferenceIF)this.get(dss.vector.solutions.irs.Targeter.ENTITYDOMAIN,  alias, displayLabel);

  }
  public com.terraframe.mojo.query.SelectableSingleChar getId()
  {
    return getId(null);

  }
 
  public com.terraframe.mojo.query.SelectableSingleChar getId(String alias)
  {
    return (com.terraframe.mojo.query.SelectableSingleChar)this.get(dss.vector.solutions.irs.Targeter.ID, alias, null);

  }
 
  public com.terraframe.mojo.query.SelectableSingleChar getId(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.SelectableSingleChar)this.get(dss.vector.solutions.irs.Targeter.ID, alias, displayLabel);

  }
  public com.terraframe.mojo.query.SelectableSingleChar getKeyName()
  {
    return getKeyName(null);

  }
 
  public com.terraframe.mojo.query.SelectableSingleChar getKeyName(String alias)
  {
    return (com.terraframe.mojo.query.SelectableSingleChar)this.get(dss.vector.solutions.irs.Targeter.KEYNAME, alias, null);

  }
 
  public com.terraframe.mojo.query.SelectableSingleChar getKeyName(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.SelectableSingleChar)this.get(dss.vector.solutions.irs.Targeter.KEYNAME, alias, displayLabel);

  }
  public com.terraframe.mojo.query.SelectableSingleMoment getLastUpdateDate()
  {
    return getLastUpdateDate(null);

  }
 
  public com.terraframe.mojo.query.SelectableSingleMoment getLastUpdateDate(String alias)
  {
    return (com.terraframe.mojo.query.SelectableSingleMoment)this.get(dss.vector.solutions.irs.Targeter.LASTUPDATEDATE, alias, null);

  }
 
  public com.terraframe.mojo.query.SelectableSingleMoment getLastUpdateDate(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.SelectableSingleMoment)this.get(dss.vector.solutions.irs.Targeter.LASTUPDATEDATE, alias, displayLabel);

  }
  public com.terraframe.mojo.system.SingleActorQuery.SingleActorQueryReferenceIF getLastUpdatedBy()
  {
    return getLastUpdatedBy(null);

  }
 
  public com.terraframe.mojo.system.SingleActorQuery.SingleActorQueryReferenceIF getLastUpdatedBy(String alias)
  {
    return (com.terraframe.mojo.system.SingleActorQuery.SingleActorQueryReferenceIF)this.get(dss.vector.solutions.irs.Targeter.LASTUPDATEDBY, alias, null);

  }
 
  public com.terraframe.mojo.system.SingleActorQuery.SingleActorQueryReferenceIF getLastUpdatedBy(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.system.SingleActorQuery.SingleActorQueryReferenceIF)this.get(dss.vector.solutions.irs.Targeter.LASTUPDATEDBY,  alias, displayLabel);

  }
  public com.terraframe.mojo.system.UsersQuery.UsersQueryReferenceIF getLockedBy()
  {
    return getLockedBy(null);

  }
 
  public com.terraframe.mojo.system.UsersQuery.UsersQueryReferenceIF getLockedBy(String alias)
  {
    return (com.terraframe.mojo.system.UsersQuery.UsersQueryReferenceIF)this.get(dss.vector.solutions.irs.Targeter.LOCKEDBY, alias, null);

  }
 
  public com.terraframe.mojo.system.UsersQuery.UsersQueryReferenceIF getLockedBy(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.system.UsersQuery.UsersQueryReferenceIF)this.get(dss.vector.solutions.irs.Targeter.LOCKEDBY,  alias, displayLabel);

  }
  public com.terraframe.mojo.system.ActorQuery.ActorQueryReferenceIF getOwner()
  {
    return getOwner(null);

  }
 
  public com.terraframe.mojo.system.ActorQuery.ActorQueryReferenceIF getOwner(String alias)
  {
    return (com.terraframe.mojo.system.ActorQuery.ActorQueryReferenceIF)this.get(dss.vector.solutions.irs.Targeter.OWNER, alias, null);

  }
 
  public com.terraframe.mojo.system.ActorQuery.ActorQueryReferenceIF getOwner(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.system.ActorQuery.ActorQueryReferenceIF)this.get(dss.vector.solutions.irs.Targeter.OWNER,  alias, displayLabel);

  }
  public com.terraframe.mojo.query.SelectableSingleLong getSeq()
  {
    return getSeq(null);

  }
 
  public com.terraframe.mojo.query.SelectableSingleLong getSeq(String alias)
  {
    return (com.terraframe.mojo.query.SelectableSingleLong)this.get(dss.vector.solutions.irs.Targeter.SEQ, alias, null);

  }
 
  public com.terraframe.mojo.query.SelectableSingleLong getSeq(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.SelectableSingleLong)this.get(dss.vector.solutions.irs.Targeter.SEQ, alias, displayLabel);

  }
  public com.terraframe.mojo.query.SelectableSingleChar getSiteMaster()
  {
    return getSiteMaster(null);

  }
 
  public com.terraframe.mojo.query.SelectableSingleChar getSiteMaster(String alias)
  {
    return (com.terraframe.mojo.query.SelectableSingleChar)this.get(dss.vector.solutions.irs.Targeter.SITEMASTER, alias, null);

  }
 
  public com.terraframe.mojo.query.SelectableSingleChar getSiteMaster(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.SelectableSingleChar)this.get(dss.vector.solutions.irs.Targeter.SITEMASTER, alias, displayLabel);

  }
  public com.terraframe.mojo.query.SelectableSingleChar getType()
  {
    return getType(null);

  }
 
  public com.terraframe.mojo.query.SelectableSingleChar getType(String alias)
  {
    return (com.terraframe.mojo.query.SelectableSingleChar)this.get(dss.vector.solutions.irs.Targeter.TYPE, alias, null);

  }
 
  public com.terraframe.mojo.query.SelectableSingleChar getType(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.SelectableSingleChar)this.get(dss.vector.solutions.irs.Targeter.TYPE, alias, displayLabel);

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

  }
}
