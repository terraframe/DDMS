package dss.vector.solutions.entomology.assay;

/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to AdultTestInterval.java
 *
 * @author Autogenerated by TerraFrame
 */
public  class AdultTestIntervalQuery extends com.terraframe.mojo.query.GeneratedBusinessQuery
 implements com.terraframe.mojo.generation.loader.Reloadable
{
private static final long serialVersionUID = 1238027435923L;

  public AdultTestIntervalQuery(com.terraframe.mojo.query.QueryFactory componentQueryFactory)
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
    return "dss.vector.solutions.entomology.assay.AdultTestInterval";
  }
  public dss.vector.solutions.entomology.assay.AdultAssayQuery.AdultAssayQueryReferenceIF getAssay()
  {
    return getAssay(null);

  }
 
  public dss.vector.solutions.entomology.assay.AdultAssayQuery.AdultAssayQueryReferenceIF getAssay(String alias)
  {

    com.terraframe.mojo.dataaccess.MdAttributeDAOIF mdAttributeIF = this.getComponentQuery().getMdAttributeROfromMap("assay");

    return (dss.vector.solutions.entomology.assay.AdultAssayQuery.AdultAssayQueryReferenceIF)this.getComponentQuery().internalAttributeFactory(dss.vector.solutions.entomology.assay.AdultTestInterval.ASSAY, mdAttributeIF, this, alias);

  }
  public com.terraframe.mojo.query.AttributeMomentIF getCreateDate()
  {
    return getCreateDate(null);

  }
 
  public com.terraframe.mojo.query.AttributeMomentIF getCreateDate(String alias)
  {
    return (com.terraframe.mojo.query.AttributeMomentIF)this.getComponentQuery().attributeFactory(dss.vector.solutions.entomology.assay.AdultTestInterval.CREATEDATE, "com.terraframe.mojo.system.metadata.MdAttributeDateTime", alias);

  }
  public com.terraframe.mojo.system.SingleActorQuery.SingleActorQueryReferenceIF getCreatedBy()
  {
    return getCreatedBy(null);

  }
 
  public com.terraframe.mojo.system.SingleActorQuery.SingleActorQueryReferenceIF getCreatedBy(String alias)
  {

    com.terraframe.mojo.dataaccess.MdAttributeDAOIF mdAttributeIF = this.getComponentQuery().getMdAttributeROfromMap("createdBy");

    return (com.terraframe.mojo.system.SingleActorQuery.SingleActorQueryReferenceIF)this.getComponentQuery().internalAttributeFactory(dss.vector.solutions.entomology.assay.AdultTestInterval.CREATEDBY, mdAttributeIF, this, alias);

  }
  public com.terraframe.mojo.system.metadata.MdDomainQuery.MdDomainQueryReferenceIF getEntityDomain()
  {
    return getEntityDomain(null);

  }
 
  public com.terraframe.mojo.system.metadata.MdDomainQuery.MdDomainQueryReferenceIF getEntityDomain(String alias)
  {

    com.terraframe.mojo.dataaccess.MdAttributeDAOIF mdAttributeIF = this.getComponentQuery().getMdAttributeROfromMap("entityDomain");

    return (com.terraframe.mojo.system.metadata.MdDomainQuery.MdDomainQueryReferenceIF)this.getComponentQuery().internalAttributeFactory(dss.vector.solutions.entomology.assay.AdultTestInterval.ENTITYDOMAIN, mdAttributeIF, this, alias);

  }
  public com.terraframe.mojo.query.AttributeCharIF getId()
  {
    return getId(null);

  }
 
  public com.terraframe.mojo.query.AttributeCharIF getId(String alias)
  {
    return (com.terraframe.mojo.query.AttributeCharIF)this.getComponentQuery().attributeFactory(dss.vector.solutions.entomology.assay.AdultTestInterval.ID, "com.terraframe.mojo.system.metadata.MdAttributeCharacter", alias);

  }
  public com.terraframe.mojo.query.AttributeCharIF getKeyName()
  {
    return getKeyName(null);

  }
 
  public com.terraframe.mojo.query.AttributeCharIF getKeyName(String alias)
  {
    return (com.terraframe.mojo.query.AttributeCharIF)this.getComponentQuery().attributeFactory(dss.vector.solutions.entomology.assay.AdultTestInterval.KEYNAME, "com.terraframe.mojo.system.metadata.MdAttributeCharacter", alias);

  }
  public com.terraframe.mojo.query.AttributeIntegerIF getKnockedDown()
  {
    return getKnockedDown(null);

  }
 
  public com.terraframe.mojo.query.AttributeIntegerIF getKnockedDown(String alias)
  {
    return (com.terraframe.mojo.query.AttributeIntegerIF)this.getComponentQuery().attributeFactory(dss.vector.solutions.entomology.assay.AdultTestInterval.KNOCKEDDOWN, "com.terraframe.mojo.system.metadata.MdAttributeInteger", alias);

  }
  public com.terraframe.mojo.query.AttributeMomentIF getLastUpdateDate()
  {
    return getLastUpdateDate(null);

  }
 
  public com.terraframe.mojo.query.AttributeMomentIF getLastUpdateDate(String alias)
  {
    return (com.terraframe.mojo.query.AttributeMomentIF)this.getComponentQuery().attributeFactory(dss.vector.solutions.entomology.assay.AdultTestInterval.LASTUPDATEDATE, "com.terraframe.mojo.system.metadata.MdAttributeDateTime", alias);

  }
  public com.terraframe.mojo.system.SingleActorQuery.SingleActorQueryReferenceIF getLastUpdatedBy()
  {
    return getLastUpdatedBy(null);

  }
 
  public com.terraframe.mojo.system.SingleActorQuery.SingleActorQueryReferenceIF getLastUpdatedBy(String alias)
  {

    com.terraframe.mojo.dataaccess.MdAttributeDAOIF mdAttributeIF = this.getComponentQuery().getMdAttributeROfromMap("lastUpdatedBy");

    return (com.terraframe.mojo.system.SingleActorQuery.SingleActorQueryReferenceIF)this.getComponentQuery().internalAttributeFactory(dss.vector.solutions.entomology.assay.AdultTestInterval.LASTUPDATEDBY, mdAttributeIF, this, alias);

  }
  public com.terraframe.mojo.system.UsersQuery.UsersQueryReferenceIF getLockedBy()
  {
    return getLockedBy(null);

  }
 
  public com.terraframe.mojo.system.UsersQuery.UsersQueryReferenceIF getLockedBy(String alias)
  {

    com.terraframe.mojo.dataaccess.MdAttributeDAOIF mdAttributeIF = this.getComponentQuery().getMdAttributeROfromMap("lockedBy");

    return (com.terraframe.mojo.system.UsersQuery.UsersQueryReferenceIF)this.getComponentQuery().internalAttributeFactory(dss.vector.solutions.entomology.assay.AdultTestInterval.LOCKEDBY, mdAttributeIF, this, alias);

  }
  public com.terraframe.mojo.system.ActorQuery.ActorQueryReferenceIF getOwner()
  {
    return getOwner(null);

  }
 
  public com.terraframe.mojo.system.ActorQuery.ActorQueryReferenceIF getOwner(String alias)
  {

    com.terraframe.mojo.dataaccess.MdAttributeDAOIF mdAttributeIF = this.getComponentQuery().getMdAttributeROfromMap("owner");

    return (com.terraframe.mojo.system.ActorQuery.ActorQueryReferenceIF)this.getComponentQuery().internalAttributeFactory(dss.vector.solutions.entomology.assay.AdultTestInterval.OWNER, mdAttributeIF, this, alias);

  }
  public com.terraframe.mojo.query.AttributeIntegerIF getPeriod()
  {
    return getPeriod(null);

  }
 
  public com.terraframe.mojo.query.AttributeIntegerIF getPeriod(String alias)
  {
    return (com.terraframe.mojo.query.AttributeIntegerIF)this.getComponentQuery().attributeFactory(dss.vector.solutions.entomology.assay.AdultTestInterval.PERIOD, "com.terraframe.mojo.system.metadata.MdAttributeInteger", alias);

  }
  public com.terraframe.mojo.query.AttributeLongIF getSeq()
  {
    return getSeq(null);

  }
 
  public com.terraframe.mojo.query.AttributeLongIF getSeq(String alias)
  {
    return (com.terraframe.mojo.query.AttributeLongIF)this.getComponentQuery().attributeFactory(dss.vector.solutions.entomology.assay.AdultTestInterval.SEQ, "com.terraframe.mojo.system.metadata.MdAttributeLong", alias);

  }
  public com.terraframe.mojo.query.AttributeCharIF getSiteMaster()
  {
    return getSiteMaster(null);

  }
 
  public com.terraframe.mojo.query.AttributeCharIF getSiteMaster(String alias)
  {
    return (com.terraframe.mojo.query.AttributeCharIF)this.getComponentQuery().attributeFactory(dss.vector.solutions.entomology.assay.AdultTestInterval.SITEMASTER, "com.terraframe.mojo.system.metadata.MdAttributeCharacter", alias);

  }
  public com.terraframe.mojo.query.AttributeCharIF getType()
  {
    return getType(null);

  }
 
  public com.terraframe.mojo.query.AttributeCharIF getType(String alias)
  {
    return (com.terraframe.mojo.query.AttributeCharIF)this.getComponentQuery().attributeFactory(dss.vector.solutions.entomology.assay.AdultTestInterval.TYPE, "com.terraframe.mojo.system.metadata.MdAttributeCharacter", alias);

  }
  protected com.terraframe.mojo.query.AttributeReference referenceFactory( com.terraframe.mojo.dataaccess.MdAttributeRefDAOIF mdAttributeIF, String attributeNamespace, String definingTableName, String definingTableAlias,  com.terraframe.mojo.dataaccess.MdBusinessDAOIF referenceMdBusinessIF, String referenceTableAlias, com.terraframe.mojo.query.ComponentQuery rootQuery, java.util.Set<com.terraframe.mojo.query.Join> tableJoinSet, String userDefinedAlias)
  {
    String name = mdAttributeIF.definesAttribute();
    
    if (name.equals("assay")) 
    {
       return new dss.vector.solutions.entomology.assay.AdultAssayQuery.AdultAssayQueryReference((com.terraframe.mojo.dataaccess.MdAttributeRefDAOIF)mdAttributeIF, attributeNamespace, definingTableName, definingTableAlias, referenceMdBusinessIF, referenceTableAlias, rootQuery, tableJoinSet, userDefinedAlias);
    }
    else if (name.equals("createdBy")) 
    {
       return new com.terraframe.mojo.system.SingleActorQuery.SingleActorQueryReference((com.terraframe.mojo.dataaccess.MdAttributeRefDAOIF)mdAttributeIF, attributeNamespace, definingTableName, definingTableAlias, referenceMdBusinessIF, referenceTableAlias, rootQuery, tableJoinSet, userDefinedAlias);
    }
    else if (name.equals("entityDomain")) 
    {
       return new com.terraframe.mojo.system.metadata.MdDomainQuery.MdDomainQueryReference((com.terraframe.mojo.dataaccess.MdAttributeRefDAOIF)mdAttributeIF, attributeNamespace, definingTableName, definingTableAlias, referenceMdBusinessIF, referenceTableAlias, rootQuery, tableJoinSet, userDefinedAlias);
    }
    else if (name.equals("lastUpdatedBy")) 
    {
       return new com.terraframe.mojo.system.SingleActorQuery.SingleActorQueryReference((com.terraframe.mojo.dataaccess.MdAttributeRefDAOIF)mdAttributeIF, attributeNamespace, definingTableName, definingTableAlias, referenceMdBusinessIF, referenceTableAlias, rootQuery, tableJoinSet, userDefinedAlias);
    }
    else if (name.equals("lockedBy")) 
    {
       return new com.terraframe.mojo.system.UsersQuery.UsersQueryReference((com.terraframe.mojo.dataaccess.MdAttributeRefDAOIF)mdAttributeIF, attributeNamespace, definingTableName, definingTableAlias, referenceMdBusinessIF, referenceTableAlias, rootQuery, tableJoinSet, userDefinedAlias);
    }
    else if (name.equals("owner")) 
    {
       return new com.terraframe.mojo.system.ActorQuery.ActorQueryReference((com.terraframe.mojo.dataaccess.MdAttributeRefDAOIF)mdAttributeIF, attributeNamespace, definingTableName, definingTableAlias, referenceMdBusinessIF, referenceTableAlias, rootQuery, tableJoinSet, userDefinedAlias);
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
  public com.terraframe.mojo.query.OIterator<? extends AdultTestInterval> getIterator()
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
    return new com.terraframe.mojo.business.BusinessIterator<AdultTestInterval>(this.getComponentQuery().getMdEntityIF(), columnInfoMap, results);
  }


/**
 * Interface that masks all type unsafe query methods and defines all type safe methods.
 * This type is used when a join is performed on this class as a reference.
 **/
  public interface AdultTestIntervalQueryReferenceIF extends com.terraframe.mojo.generation.loader.Reloadable, com.terraframe.mojo.query.AttributeReferenceIF
  {

    public dss.vector.solutions.entomology.assay.AdultAssayQuery.AdultAssayQueryReferenceIF getAssay();
    public dss.vector.solutions.entomology.assay.AdultAssayQuery.AdultAssayQueryReferenceIF getAssay(String alias);
    public com.terraframe.mojo.query.AttributeMomentIF getCreateDate();
    public com.terraframe.mojo.query.AttributeMomentIF getCreateDate(String alias);
    public com.terraframe.mojo.system.SingleActorQuery.SingleActorQueryReferenceIF getCreatedBy();
    public com.terraframe.mojo.system.SingleActorQuery.SingleActorQueryReferenceIF getCreatedBy(String alias);
    public com.terraframe.mojo.system.metadata.MdDomainQuery.MdDomainQueryReferenceIF getEntityDomain();
    public com.terraframe.mojo.system.metadata.MdDomainQuery.MdDomainQueryReferenceIF getEntityDomain(String alias);
    public com.terraframe.mojo.query.AttributeCharIF getId();
    public com.terraframe.mojo.query.AttributeCharIF getId(String alias);
    public com.terraframe.mojo.query.AttributeCharIF getKeyName();
    public com.terraframe.mojo.query.AttributeCharIF getKeyName(String alias);
    public com.terraframe.mojo.query.AttributeIntegerIF getKnockedDown();
    public com.terraframe.mojo.query.AttributeIntegerIF getKnockedDown(String alias);
    public com.terraframe.mojo.query.AttributeMomentIF getLastUpdateDate();
    public com.terraframe.mojo.query.AttributeMomentIF getLastUpdateDate(String alias);
    public com.terraframe.mojo.system.SingleActorQuery.SingleActorQueryReferenceIF getLastUpdatedBy();
    public com.terraframe.mojo.system.SingleActorQuery.SingleActorQueryReferenceIF getLastUpdatedBy(String alias);
    public com.terraframe.mojo.system.UsersQuery.UsersQueryReferenceIF getLockedBy();
    public com.terraframe.mojo.system.UsersQuery.UsersQueryReferenceIF getLockedBy(String alias);
    public com.terraframe.mojo.system.ActorQuery.ActorQueryReferenceIF getOwner();
    public com.terraframe.mojo.system.ActorQuery.ActorQueryReferenceIF getOwner(String alias);
    public com.terraframe.mojo.query.AttributeIntegerIF getPeriod();
    public com.terraframe.mojo.query.AttributeIntegerIF getPeriod(String alias);
    public com.terraframe.mojo.query.AttributeLongIF getSeq();
    public com.terraframe.mojo.query.AttributeLongIF getSeq(String alias);
    public com.terraframe.mojo.query.AttributeCharIF getSiteMaster();
    public com.terraframe.mojo.query.AttributeCharIF getSiteMaster(String alias);
    public com.terraframe.mojo.query.AttributeCharIF getType();
    public com.terraframe.mojo.query.AttributeCharIF getType(String alias);

    public com.terraframe.mojo.query.BasicCondition EQ(dss.vector.solutions.entomology.assay.AdultTestInterval adultTestInterval);

    public com.terraframe.mojo.query.BasicCondition NE(dss.vector.solutions.entomology.assay.AdultTestInterval adultTestInterval);

  }

/**
 * Implements type safe query methods.
 * This type is used when a join is performed on this class as a reference.
 **/
  public static class AdultTestIntervalQueryReference extends com.terraframe.mojo.query.AttributeReference
 implements AdultTestIntervalQueryReferenceIF
, com.terraframe.mojo.generation.loader.Reloadable
  {
private static final long serialVersionUID = 1238027436078L;

  public AdultTestIntervalQueryReference(com.terraframe.mojo.dataaccess.MdAttributeRefDAOIF mdAttributeIF, String attributeNamespace, String definingTableName, String definingTableAlias, com.terraframe.mojo.dataaccess.MdBusinessDAOIF referenceMdBusinessIF, String referenceTableAlias, com.terraframe.mojo.query.ComponentQuery rootQuery, java.util.Set<com.terraframe.mojo.query.Join> tableJoinSet, String alias)
  {
    super(mdAttributeIF, attributeNamespace, definingTableName, definingTableAlias, referenceMdBusinessIF, referenceTableAlias, rootQuery, tableJoinSet, alias);

  }


    public com.terraframe.mojo.query.BasicCondition EQ(dss.vector.solutions.entomology.assay.AdultTestInterval adultTestInterval)
    {
      return this.EQ(adultTestInterval.getId());
    }

    public com.terraframe.mojo.query.BasicCondition NE(dss.vector.solutions.entomology.assay.AdultTestInterval adultTestInterval)
    {
      return this.NE(adultTestInterval.getId());
    }

  public dss.vector.solutions.entomology.assay.AdultAssayQuery.AdultAssayQueryReferenceIF getAssay()
  {
    return getAssay(null);

  }
 
  public dss.vector.solutions.entomology.assay.AdultAssayQuery.AdultAssayQueryReferenceIF getAssay(String alias)
  {
    return (dss.vector.solutions.entomology.assay.AdultAssayQuery.AdultAssayQueryReferenceIF)this.attributeFactory("assay", "com.terraframe.mojo.system.metadata.MdAttributeReference", alias);

  }
  public com.terraframe.mojo.query.AttributeMomentIF getCreateDate()
  {
    return getCreateDate(null);

  }
 
  public com.terraframe.mojo.query.AttributeMomentIF getCreateDate(String alias)
  {
    return (com.terraframe.mojo.query.AttributeMomentIF)this.attributeFactory("createDate", "com.terraframe.mojo.system.metadata.MdAttributeDateTime", alias);

  }
  public com.terraframe.mojo.system.SingleActorQuery.SingleActorQueryReferenceIF getCreatedBy()
  {
    return getCreatedBy(null);

  }
 
  public com.terraframe.mojo.system.SingleActorQuery.SingleActorQueryReferenceIF getCreatedBy(String alias)
  {
    return (com.terraframe.mojo.system.SingleActorQuery.SingleActorQueryReferenceIF)this.attributeFactory("createdBy", "com.terraframe.mojo.system.metadata.MdAttributeReference", alias);

  }
  public com.terraframe.mojo.system.metadata.MdDomainQuery.MdDomainQueryReferenceIF getEntityDomain()
  {
    return getEntityDomain(null);

  }
 
  public com.terraframe.mojo.system.metadata.MdDomainQuery.MdDomainQueryReferenceIF getEntityDomain(String alias)
  {
    return (com.terraframe.mojo.system.metadata.MdDomainQuery.MdDomainQueryReferenceIF)this.attributeFactory("entityDomain", "com.terraframe.mojo.system.metadata.MdAttributeReference", alias);

  }
  public com.terraframe.mojo.query.AttributeCharIF getId()
  {
    return getId(null);

  }
 
  public com.terraframe.mojo.query.AttributeCharIF getId(String alias)
  {
    return (com.terraframe.mojo.query.AttributeCharIF)this.attributeFactory("id", "com.terraframe.mojo.system.metadata.MdAttributeCharacter", alias);

  }
  public com.terraframe.mojo.query.AttributeCharIF getKeyName()
  {
    return getKeyName(null);

  }
 
  public com.terraframe.mojo.query.AttributeCharIF getKeyName(String alias)
  {
    return (com.terraframe.mojo.query.AttributeCharIF)this.attributeFactory("keyName", "com.terraframe.mojo.system.metadata.MdAttributeCharacter", alias);

  }
  public com.terraframe.mojo.query.AttributeIntegerIF getKnockedDown()
  {
    return getKnockedDown(null);

  }
 
  public com.terraframe.mojo.query.AttributeIntegerIF getKnockedDown(String alias)
  {
    return (com.terraframe.mojo.query.AttributeIntegerIF)this.attributeFactory("knockedDown", "com.terraframe.mojo.system.metadata.MdAttributeInteger", alias);

  }
  public com.terraframe.mojo.query.AttributeMomentIF getLastUpdateDate()
  {
    return getLastUpdateDate(null);

  }
 
  public com.terraframe.mojo.query.AttributeMomentIF getLastUpdateDate(String alias)
  {
    return (com.terraframe.mojo.query.AttributeMomentIF)this.attributeFactory("lastUpdateDate", "com.terraframe.mojo.system.metadata.MdAttributeDateTime", alias);

  }
  public com.terraframe.mojo.system.SingleActorQuery.SingleActorQueryReferenceIF getLastUpdatedBy()
  {
    return getLastUpdatedBy(null);

  }
 
  public com.terraframe.mojo.system.SingleActorQuery.SingleActorQueryReferenceIF getLastUpdatedBy(String alias)
  {
    return (com.terraframe.mojo.system.SingleActorQuery.SingleActorQueryReferenceIF)this.attributeFactory("lastUpdatedBy", "com.terraframe.mojo.system.metadata.MdAttributeReference", alias);

  }
  public com.terraframe.mojo.system.UsersQuery.UsersQueryReferenceIF getLockedBy()
  {
    return getLockedBy(null);

  }
 
  public com.terraframe.mojo.system.UsersQuery.UsersQueryReferenceIF getLockedBy(String alias)
  {
    return (com.terraframe.mojo.system.UsersQuery.UsersQueryReferenceIF)this.attributeFactory("lockedBy", "com.terraframe.mojo.system.metadata.MdAttributeReference", alias);

  }
  public com.terraframe.mojo.system.ActorQuery.ActorQueryReferenceIF getOwner()
  {
    return getOwner(null);

  }
 
  public com.terraframe.mojo.system.ActorQuery.ActorQueryReferenceIF getOwner(String alias)
  {
    return (com.terraframe.mojo.system.ActorQuery.ActorQueryReferenceIF)this.attributeFactory("owner", "com.terraframe.mojo.system.metadata.MdAttributeReference", alias);

  }
  public com.terraframe.mojo.query.AttributeIntegerIF getPeriod()
  {
    return getPeriod(null);

  }
 
  public com.terraframe.mojo.query.AttributeIntegerIF getPeriod(String alias)
  {
    return (com.terraframe.mojo.query.AttributeIntegerIF)this.attributeFactory("period", "com.terraframe.mojo.system.metadata.MdAttributeInteger", alias);

  }
  public com.terraframe.mojo.query.AttributeLongIF getSeq()
  {
    return getSeq(null);

  }
 
  public com.terraframe.mojo.query.AttributeLongIF getSeq(String alias)
  {
    return (com.terraframe.mojo.query.AttributeLongIF)this.attributeFactory("seq", "com.terraframe.mojo.system.metadata.MdAttributeLong", alias);

  }
  public com.terraframe.mojo.query.AttributeCharIF getSiteMaster()
  {
    return getSiteMaster(null);

  }
 
  public com.terraframe.mojo.query.AttributeCharIF getSiteMaster(String alias)
  {
    return (com.terraframe.mojo.query.AttributeCharIF)this.attributeFactory("siteMaster", "com.terraframe.mojo.system.metadata.MdAttributeCharacter", alias);

  }
  public com.terraframe.mojo.query.AttributeCharIF getType()
  {
    return getType(null);

  }
 
  public com.terraframe.mojo.query.AttributeCharIF getType(String alias)
  {
    return (com.terraframe.mojo.query.AttributeCharIF)this.attributeFactory("type", "com.terraframe.mojo.system.metadata.MdAttributeCharacter", alias);

  }
  protected com.terraframe.mojo.query.AttributeReference referenceFactory( com.terraframe.mojo.dataaccess.MdAttributeRefDAOIF mdAttributeIF, String attributeNamespace, String definingTableName, String definingTableAlias,  com.terraframe.mojo.dataaccess.MdBusinessDAOIF referenceMdBusinessIF, String referenceTableAlias, com.terraframe.mojo.query.ComponentQuery rootQuery, java.util.Set<com.terraframe.mojo.query.Join> tableJoinSet, String userDefinedAlias)
  {
    String name = mdAttributeIF.definesAttribute();
    
    if (name.equals("assay")) 
    {
       return new dss.vector.solutions.entomology.assay.AdultAssayQuery.AdultAssayQueryReference((com.terraframe.mojo.dataaccess.MdAttributeRefDAOIF)mdAttributeIF, attributeNamespace, definingTableName, definingTableAlias, referenceMdBusinessIF, referenceTableAlias, rootQuery, tableJoinSet, userDefinedAlias);
    }
    else if (name.equals("createdBy")) 
    {
       return new com.terraframe.mojo.system.SingleActorQuery.SingleActorQueryReference((com.terraframe.mojo.dataaccess.MdAttributeRefDAOIF)mdAttributeIF, attributeNamespace, definingTableName, definingTableAlias, referenceMdBusinessIF, referenceTableAlias, rootQuery, tableJoinSet, userDefinedAlias);
    }
    else if (name.equals("entityDomain")) 
    {
       return new com.terraframe.mojo.system.metadata.MdDomainQuery.MdDomainQueryReference((com.terraframe.mojo.dataaccess.MdAttributeRefDAOIF)mdAttributeIF, attributeNamespace, definingTableName, definingTableAlias, referenceMdBusinessIF, referenceTableAlias, rootQuery, tableJoinSet, userDefinedAlias);
    }
    else if (name.equals("lastUpdatedBy")) 
    {
       return new com.terraframe.mojo.system.SingleActorQuery.SingleActorQueryReference((com.terraframe.mojo.dataaccess.MdAttributeRefDAOIF)mdAttributeIF, attributeNamespace, definingTableName, definingTableAlias, referenceMdBusinessIF, referenceTableAlias, rootQuery, tableJoinSet, userDefinedAlias);
    }
    else if (name.equals("lockedBy")) 
    {
       return new com.terraframe.mojo.system.UsersQuery.UsersQueryReference((com.terraframe.mojo.dataaccess.MdAttributeRefDAOIF)mdAttributeIF, attributeNamespace, definingTableName, definingTableAlias, referenceMdBusinessIF, referenceTableAlias, rootQuery, tableJoinSet, userDefinedAlias);
    }
    else if (name.equals("owner")) 
    {
       return new com.terraframe.mojo.system.ActorQuery.ActorQueryReference((com.terraframe.mojo.dataaccess.MdAttributeRefDAOIF)mdAttributeIF, attributeNamespace, definingTableName, definingTableAlias, referenceMdBusinessIF, referenceTableAlias, rootQuery, tableJoinSet, userDefinedAlias);
    }
    else 
    {
      String error = "Attribute type ["+mdAttributeIF.getType()+"] is invalid.";
      throw new com.terraframe.mojo.query.QueryException(error);
    }
  }

  }
}
