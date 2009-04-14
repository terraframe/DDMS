package dss.vector.solutions.surveillance;

/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to AggregatedAgeGroup.java
 *
 * @author Autogenerated by TerraFrame
 */
public  class AggregatedAgeGroupQuery extends com.terraframe.mojo.query.GeneratedBusinessQuery
 implements com.terraframe.mojo.generation.loader.Reloadable
{
private static final long serialVersionUID = 1239670247994L;

  public AggregatedAgeGroupQuery(com.terraframe.mojo.query.QueryFactory componentQueryFactory)
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
    return "dss.vector.solutions.surveillance.AggregatedAgeGroup";
  }
  public com.terraframe.mojo.query.AttributeBooleanIF getActive()
  {
    return getActive(null);

  }
 
  public com.terraframe.mojo.query.AttributeBooleanIF getActive(String alias)
  {
    return (com.terraframe.mojo.query.AttributeBooleanIF)this.getComponentQuery().attributeFactory(dss.vector.solutions.surveillance.AggregatedAgeGroup.ACTIVE, "com.terraframe.mojo.system.metadata.MdAttributeBoolean", alias);

  }
  public com.terraframe.mojo.query.AttributeMomentIF getCreateDate()
  {
    return getCreateDate(null);

  }
 
  public com.terraframe.mojo.query.AttributeMomentIF getCreateDate(String alias)
  {
    return (com.terraframe.mojo.query.AttributeMomentIF)this.getComponentQuery().attributeFactory(dss.vector.solutions.surveillance.AggregatedAgeGroup.CREATEDATE, "com.terraframe.mojo.system.metadata.MdAttributeDateTime", alias);

  }
  public com.terraframe.mojo.system.SingleActorQuery.SingleActorQueryReferenceIF getCreatedBy()
  {
    return getCreatedBy(null);

  }
 
  public com.terraframe.mojo.system.SingleActorQuery.SingleActorQueryReferenceIF getCreatedBy(String alias)
  {

    com.terraframe.mojo.dataaccess.MdAttributeDAOIF mdAttributeIF = this.getComponentQuery().getMdAttributeROfromMap("createdBy");

    return (com.terraframe.mojo.system.SingleActorQuery.SingleActorQueryReferenceIF)this.getComponentQuery().internalAttributeFactory(dss.vector.solutions.surveillance.AggregatedAgeGroup.CREATEDBY, mdAttributeIF, this, alias);

  }
  public com.terraframe.mojo.query.AttributeCharIF getDisplayLabel()
  {
    return getDisplayLabel(null);

  }
 
  public com.terraframe.mojo.query.AttributeCharIF getDisplayLabel(String alias)
  {
    return (com.terraframe.mojo.query.AttributeCharIF)this.getComponentQuery().attributeFactory(dss.vector.solutions.surveillance.AggregatedAgeGroup.DISPLAYLABEL, "com.terraframe.mojo.system.metadata.MdAttributeCharacter", alias);

  }
  public com.terraframe.mojo.query.AttributeIntegerIF getEndAge()
  {
    return getEndAge(null);

  }
 
  public com.terraframe.mojo.query.AttributeIntegerIF getEndAge(String alias)
  {
    return (com.terraframe.mojo.query.AttributeIntegerIF)this.getComponentQuery().attributeFactory(dss.vector.solutions.surveillance.AggregatedAgeGroup.ENDAGE, "com.terraframe.mojo.system.metadata.MdAttributeInteger", alias);

  }
  public com.terraframe.mojo.system.metadata.MdDomainQuery.MdDomainQueryReferenceIF getEntityDomain()
  {
    return getEntityDomain(null);

  }
 
  public com.terraframe.mojo.system.metadata.MdDomainQuery.MdDomainQueryReferenceIF getEntityDomain(String alias)
  {

    com.terraframe.mojo.dataaccess.MdAttributeDAOIF mdAttributeIF = this.getComponentQuery().getMdAttributeROfromMap("entityDomain");

    return (com.terraframe.mojo.system.metadata.MdDomainQuery.MdDomainQueryReferenceIF)this.getComponentQuery().internalAttributeFactory(dss.vector.solutions.surveillance.AggregatedAgeGroup.ENTITYDOMAIN, mdAttributeIF, this, alias);

  }
  public com.terraframe.mojo.query.AttributeCharIF getId()
  {
    return getId(null);

  }
 
  public com.terraframe.mojo.query.AttributeCharIF getId(String alias)
  {
    return (com.terraframe.mojo.query.AttributeCharIF)this.getComponentQuery().attributeFactory(dss.vector.solutions.surveillance.AggregatedAgeGroup.ID, "com.terraframe.mojo.system.metadata.MdAttributeCharacter", alias);

  }
  public com.terraframe.mojo.query.AttributeCharIF getKeyName()
  {
    return getKeyName(null);

  }
 
  public com.terraframe.mojo.query.AttributeCharIF getKeyName(String alias)
  {
    return (com.terraframe.mojo.query.AttributeCharIF)this.getComponentQuery().attributeFactory(dss.vector.solutions.surveillance.AggregatedAgeGroup.KEYNAME, "com.terraframe.mojo.system.metadata.MdAttributeCharacter", alias);

  }
  public com.terraframe.mojo.query.AttributeMomentIF getLastUpdateDate()
  {
    return getLastUpdateDate(null);

  }
 
  public com.terraframe.mojo.query.AttributeMomentIF getLastUpdateDate(String alias)
  {
    return (com.terraframe.mojo.query.AttributeMomentIF)this.getComponentQuery().attributeFactory(dss.vector.solutions.surveillance.AggregatedAgeGroup.LASTUPDATEDATE, "com.terraframe.mojo.system.metadata.MdAttributeDateTime", alias);

  }
  public com.terraframe.mojo.system.SingleActorQuery.SingleActorQueryReferenceIF getLastUpdatedBy()
  {
    return getLastUpdatedBy(null);

  }
 
  public com.terraframe.mojo.system.SingleActorQuery.SingleActorQueryReferenceIF getLastUpdatedBy(String alias)
  {

    com.terraframe.mojo.dataaccess.MdAttributeDAOIF mdAttributeIF = this.getComponentQuery().getMdAttributeROfromMap("lastUpdatedBy");

    return (com.terraframe.mojo.system.SingleActorQuery.SingleActorQueryReferenceIF)this.getComponentQuery().internalAttributeFactory(dss.vector.solutions.surveillance.AggregatedAgeGroup.LASTUPDATEDBY, mdAttributeIF, this, alias);

  }
  public com.terraframe.mojo.system.UsersQuery.UsersQueryReferenceIF getLockedBy()
  {
    return getLockedBy(null);

  }
 
  public com.terraframe.mojo.system.UsersQuery.UsersQueryReferenceIF getLockedBy(String alias)
  {

    com.terraframe.mojo.dataaccess.MdAttributeDAOIF mdAttributeIF = this.getComponentQuery().getMdAttributeROfromMap("lockedBy");

    return (com.terraframe.mojo.system.UsersQuery.UsersQueryReferenceIF)this.getComponentQuery().internalAttributeFactory(dss.vector.solutions.surveillance.AggregatedAgeGroup.LOCKEDBY, mdAttributeIF, this, alias);

  }
  public com.terraframe.mojo.system.metadata.MdViewQuery.MdViewQueryReferenceIF getMdView()
  {
    return getMdView(null);

  }
 
  public com.terraframe.mojo.system.metadata.MdViewQuery.MdViewQueryReferenceIF getMdView(String alias)
  {

    com.terraframe.mojo.dataaccess.MdAttributeDAOIF mdAttributeIF = this.getComponentQuery().getMdAttributeROfromMap("mdView");

    return (com.terraframe.mojo.system.metadata.MdViewQuery.MdViewQueryReferenceIF)this.getComponentQuery().internalAttributeFactory(dss.vector.solutions.surveillance.AggregatedAgeGroup.MDVIEW, mdAttributeIF, this, alias);

  }
  public com.terraframe.mojo.system.ActorQuery.ActorQueryReferenceIF getOwner()
  {
    return getOwner(null);

  }
 
  public com.terraframe.mojo.system.ActorQuery.ActorQueryReferenceIF getOwner(String alias)
  {

    com.terraframe.mojo.dataaccess.MdAttributeDAOIF mdAttributeIF = this.getComponentQuery().getMdAttributeROfromMap("owner");

    return (com.terraframe.mojo.system.ActorQuery.ActorQueryReferenceIF)this.getComponentQuery().internalAttributeFactory(dss.vector.solutions.surveillance.AggregatedAgeGroup.OWNER, mdAttributeIF, this, alias);

  }
  public com.terraframe.mojo.query.AttributeLongIF getSeq()
  {
    return getSeq(null);

  }
 
  public com.terraframe.mojo.query.AttributeLongIF getSeq(String alias)
  {
    return (com.terraframe.mojo.query.AttributeLongIF)this.getComponentQuery().attributeFactory(dss.vector.solutions.surveillance.AggregatedAgeGroup.SEQ, "com.terraframe.mojo.system.metadata.MdAttributeLong", alias);

  }
  public com.terraframe.mojo.query.AttributeCharIF getSiteMaster()
  {
    return getSiteMaster(null);

  }
 
  public com.terraframe.mojo.query.AttributeCharIF getSiteMaster(String alias)
  {
    return (com.terraframe.mojo.query.AttributeCharIF)this.getComponentQuery().attributeFactory(dss.vector.solutions.surveillance.AggregatedAgeGroup.SITEMASTER, "com.terraframe.mojo.system.metadata.MdAttributeCharacter", alias);

  }
  public com.terraframe.mojo.query.AttributeIntegerIF getStartAge()
  {
    return getStartAge(null);

  }
 
  public com.terraframe.mojo.query.AttributeIntegerIF getStartAge(String alias)
  {
    return (com.terraframe.mojo.query.AttributeIntegerIF)this.getComponentQuery().attributeFactory(dss.vector.solutions.surveillance.AggregatedAgeGroup.STARTAGE, "com.terraframe.mojo.system.metadata.MdAttributeInteger", alias);

  }
  public com.terraframe.mojo.query.AttributeCharIF getType()
  {
    return getType(null);

  }
 
  public com.terraframe.mojo.query.AttributeCharIF getType(String alias)
  {
    return (com.terraframe.mojo.query.AttributeCharIF)this.getComponentQuery().attributeFactory(dss.vector.solutions.surveillance.AggregatedAgeGroup.TYPE, "com.terraframe.mojo.system.metadata.MdAttributeCharacter", alias);

  }
  protected com.terraframe.mojo.query.AttributeReference referenceFactory( com.terraframe.mojo.dataaccess.MdAttributeRefDAOIF mdAttributeIF, String attributeNamespace, String definingTableName, String definingTableAlias,  com.terraframe.mojo.dataaccess.MdBusinessDAOIF referenceMdBusinessIF, String referenceTableAlias, com.terraframe.mojo.query.ComponentQuery rootQuery, java.util.Set<com.terraframe.mojo.query.Join> tableJoinSet, String userDefinedAlias)
  {
    String name = mdAttributeIF.definesAttribute();
    
    if (name.equals("createdBy")) 
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
    else if (name.equals("mdView")) 
    {
       return new com.terraframe.mojo.system.metadata.MdViewQuery.MdViewQueryReference((com.terraframe.mojo.dataaccess.MdAttributeRefDAOIF)mdAttributeIF, attributeNamespace, definingTableName, definingTableAlias, referenceMdBusinessIF, referenceTableAlias, rootQuery, tableJoinSet, userDefinedAlias);
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
  public com.terraframe.mojo.query.OIterator<? extends AggregatedAgeGroup> getIterator()
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
    return new com.terraframe.mojo.business.BusinessIterator<AggregatedAgeGroup>(this.getComponentQuery().getMdEntityIF(), columnInfoMap, results);
  }


/**
 * Interface that masks all type unsafe query methods and defines all type safe methods.
 * This type is used when a join is performed on this class as a reference.
 **/
  public interface AggregatedAgeGroupQueryReferenceIF extends com.terraframe.mojo.generation.loader.Reloadable, com.terraframe.mojo.query.AttributeReferenceIF
  {

    public com.terraframe.mojo.query.AttributeBooleanIF getActive();
    public com.terraframe.mojo.query.AttributeBooleanIF getActive(String alias);
    public com.terraframe.mojo.query.AttributeMomentIF getCreateDate();
    public com.terraframe.mojo.query.AttributeMomentIF getCreateDate(String alias);
    public com.terraframe.mojo.system.SingleActorQuery.SingleActorQueryReferenceIF getCreatedBy();
    public com.terraframe.mojo.system.SingleActorQuery.SingleActorQueryReferenceIF getCreatedBy(String alias);
    public com.terraframe.mojo.query.AttributeCharIF getDisplayLabel();
    public com.terraframe.mojo.query.AttributeCharIF getDisplayLabel(String alias);
    public com.terraframe.mojo.query.AttributeIntegerIF getEndAge();
    public com.terraframe.mojo.query.AttributeIntegerIF getEndAge(String alias);
    public com.terraframe.mojo.system.metadata.MdDomainQuery.MdDomainQueryReferenceIF getEntityDomain();
    public com.terraframe.mojo.system.metadata.MdDomainQuery.MdDomainQueryReferenceIF getEntityDomain(String alias);
    public com.terraframe.mojo.query.AttributeCharIF getId();
    public com.terraframe.mojo.query.AttributeCharIF getId(String alias);
    public com.terraframe.mojo.query.AttributeCharIF getKeyName();
    public com.terraframe.mojo.query.AttributeCharIF getKeyName(String alias);
    public com.terraframe.mojo.query.AttributeMomentIF getLastUpdateDate();
    public com.terraframe.mojo.query.AttributeMomentIF getLastUpdateDate(String alias);
    public com.terraframe.mojo.system.SingleActorQuery.SingleActorQueryReferenceIF getLastUpdatedBy();
    public com.terraframe.mojo.system.SingleActorQuery.SingleActorQueryReferenceIF getLastUpdatedBy(String alias);
    public com.terraframe.mojo.system.UsersQuery.UsersQueryReferenceIF getLockedBy();
    public com.terraframe.mojo.system.UsersQuery.UsersQueryReferenceIF getLockedBy(String alias);
    public com.terraframe.mojo.system.metadata.MdViewQuery.MdViewQueryReferenceIF getMdView();
    public com.terraframe.mojo.system.metadata.MdViewQuery.MdViewQueryReferenceIF getMdView(String alias);
    public com.terraframe.mojo.system.ActorQuery.ActorQueryReferenceIF getOwner();
    public com.terraframe.mojo.system.ActorQuery.ActorQueryReferenceIF getOwner(String alias);
    public com.terraframe.mojo.query.AttributeLongIF getSeq();
    public com.terraframe.mojo.query.AttributeLongIF getSeq(String alias);
    public com.terraframe.mojo.query.AttributeCharIF getSiteMaster();
    public com.terraframe.mojo.query.AttributeCharIF getSiteMaster(String alias);
    public com.terraframe.mojo.query.AttributeIntegerIF getStartAge();
    public com.terraframe.mojo.query.AttributeIntegerIF getStartAge(String alias);
    public com.terraframe.mojo.query.AttributeCharIF getType();
    public com.terraframe.mojo.query.AttributeCharIF getType(String alias);

    public com.terraframe.mojo.query.BasicCondition EQ(dss.vector.solutions.surveillance.AggregatedAgeGroup aggregatedAgeGroup);

    public com.terraframe.mojo.query.BasicCondition NE(dss.vector.solutions.surveillance.AggregatedAgeGroup aggregatedAgeGroup);

  }

/**
 * Implements type safe query methods.
 * This type is used when a join is performed on this class as a reference.
 **/
  public static class AggregatedAgeGroupQueryReference extends com.terraframe.mojo.query.AttributeReference
 implements AggregatedAgeGroupQueryReferenceIF
, com.terraframe.mojo.generation.loader.Reloadable
  {
private static final long serialVersionUID = 1239670248297L;

  public AggregatedAgeGroupQueryReference(com.terraframe.mojo.dataaccess.MdAttributeRefDAOIF mdAttributeIF, String attributeNamespace, String definingTableName, String definingTableAlias, com.terraframe.mojo.dataaccess.MdBusinessDAOIF referenceMdBusinessIF, String referenceTableAlias, com.terraframe.mojo.query.ComponentQuery rootQuery, java.util.Set<com.terraframe.mojo.query.Join> tableJoinSet, String alias)
  {
    super(mdAttributeIF, attributeNamespace, definingTableName, definingTableAlias, referenceMdBusinessIF, referenceTableAlias, rootQuery, tableJoinSet, alias);

  }


    public com.terraframe.mojo.query.BasicCondition EQ(dss.vector.solutions.surveillance.AggregatedAgeGroup aggregatedAgeGroup)
    {
      return this.EQ(aggregatedAgeGroup.getId());
    }

    public com.terraframe.mojo.query.BasicCondition NE(dss.vector.solutions.surveillance.AggregatedAgeGroup aggregatedAgeGroup)
    {
      return this.NE(aggregatedAgeGroup.getId());
    }

  public com.terraframe.mojo.query.AttributeBooleanIF getActive()
  {
    return getActive(null);

  }
 
  public com.terraframe.mojo.query.AttributeBooleanIF getActive(String alias)
  {
    return (com.terraframe.mojo.query.AttributeBooleanIF)this.attributeFactory("active", "com.terraframe.mojo.system.metadata.MdAttributeBoolean", alias);

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
  public com.terraframe.mojo.query.AttributeCharIF getDisplayLabel()
  {
    return getDisplayLabel(null);

  }
 
  public com.terraframe.mojo.query.AttributeCharIF getDisplayLabel(String alias)
  {
    return (com.terraframe.mojo.query.AttributeCharIF)this.attributeFactory("displayLabel", "com.terraframe.mojo.system.metadata.MdAttributeCharacter", alias);

  }
  public com.terraframe.mojo.query.AttributeIntegerIF getEndAge()
  {
    return getEndAge(null);

  }
 
  public com.terraframe.mojo.query.AttributeIntegerIF getEndAge(String alias)
  {
    return (com.terraframe.mojo.query.AttributeIntegerIF)this.attributeFactory("endAge", "com.terraframe.mojo.system.metadata.MdAttributeInteger", alias);

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
  public com.terraframe.mojo.system.metadata.MdViewQuery.MdViewQueryReferenceIF getMdView()
  {
    return getMdView(null);

  }
 
  public com.terraframe.mojo.system.metadata.MdViewQuery.MdViewQueryReferenceIF getMdView(String alias)
  {
    return (com.terraframe.mojo.system.metadata.MdViewQuery.MdViewQueryReferenceIF)this.attributeFactory("mdView", "com.terraframe.mojo.system.metadata.MdAttributeReference", alias);

  }
  public com.terraframe.mojo.system.ActorQuery.ActorQueryReferenceIF getOwner()
  {
    return getOwner(null);

  }
 
  public com.terraframe.mojo.system.ActorQuery.ActorQueryReferenceIF getOwner(String alias)
  {
    return (com.terraframe.mojo.system.ActorQuery.ActorQueryReferenceIF)this.attributeFactory("owner", "com.terraframe.mojo.system.metadata.MdAttributeReference", alias);

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
  public com.terraframe.mojo.query.AttributeIntegerIF getStartAge()
  {
    return getStartAge(null);

  }
 
  public com.terraframe.mojo.query.AttributeIntegerIF getStartAge(String alias)
  {
    return (com.terraframe.mojo.query.AttributeIntegerIF)this.attributeFactory("startAge", "com.terraframe.mojo.system.metadata.MdAttributeInteger", alias);

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
    
    if (name.equals("createdBy")) 
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
    else if (name.equals("mdView")) 
    {
       return new com.terraframe.mojo.system.metadata.MdViewQuery.MdViewQueryReference((com.terraframe.mojo.dataaccess.MdAttributeRefDAOIF)mdAttributeIF, attributeNamespace, definingTableName, definingTableAlias, referenceMdBusinessIF, referenceTableAlias, rootQuery, tableJoinSet, userDefinedAlias);
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
