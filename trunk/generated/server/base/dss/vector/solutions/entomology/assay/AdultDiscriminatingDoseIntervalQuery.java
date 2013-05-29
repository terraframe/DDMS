package dss.vector.solutions.entomology.assay;

@com.runwaysdk.business.ClassSignature(hash = -821953576)
/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to AdultDiscriminatingDoseInterval.java
 *
 * @author Autogenerated by RunwaySDK
 */
public  class AdultDiscriminatingDoseIntervalQuery extends com.runwaysdk.query.GeneratedBusinessQuery
 implements com.runwaysdk.generation.loader.Reloadable
{

  public AdultDiscriminatingDoseIntervalQuery(com.runwaysdk.query.QueryFactory componentQueryFactory)
  {
     super();
    if (this.getComponentQuery() == null)
    {
      com.runwaysdk.business.BusinessQuery businessQuery = componentQueryFactory.businessQuery(this.getClassType());

       this.setBusinessQuery(businessQuery);
    }
  }

  public AdultDiscriminatingDoseIntervalQuery(com.runwaysdk.query.ValueQuery valueQuery)
  {
     super();
    if (this.getComponentQuery() == null)
    {
      com.runwaysdk.business.BusinessQuery businessQuery = new com.runwaysdk.business.BusinessQuery(valueQuery, this.getClassType());

       this.setBusinessQuery(businessQuery);
    }
  }

  public String getClassType()
  {
    return dss.vector.solutions.entomology.assay.AdultDiscriminatingDoseInterval.CLASS;
  }
  public com.runwaysdk.query.SelectableInteger getAmount()
  {
    return getAmount(null);

  }
 
  public com.runwaysdk.query.SelectableInteger getAmount(String alias)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getComponentQuery().get(dss.vector.solutions.entomology.assay.AdultDiscriminatingDoseInterval.AMOUNT, alias, null);

  }
 
  public com.runwaysdk.query.SelectableInteger getAmount(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getComponentQuery().get(dss.vector.solutions.entomology.assay.AdultDiscriminatingDoseInterval.AMOUNT, alias, displayLabel);

  }
  public dss.vector.solutions.entomology.assay.AdultDiscriminatingDoseAssayQuery.AdultDiscriminatingDoseAssayQueryReferenceIF getAssay()
  {
    return getAssay(null);

  }
 
  public dss.vector.solutions.entomology.assay.AdultDiscriminatingDoseAssayQuery.AdultDiscriminatingDoseAssayQueryReferenceIF getAssay(String alias)
  {

    com.runwaysdk.dataaccess.MdAttributeDAOIF mdAttributeIF = this.getComponentQuery().getMdAttributeROfromMap(dss.vector.solutions.entomology.assay.AdultDiscriminatingDoseInterval.ASSAY);

    return (dss.vector.solutions.entomology.assay.AdultDiscriminatingDoseAssayQuery.AdultDiscriminatingDoseAssayQueryReferenceIF)this.getComponentQuery().internalAttributeFactory(dss.vector.solutions.entomology.assay.AdultDiscriminatingDoseInterval.ASSAY, mdAttributeIF, this, alias, null);

  }
 
  public dss.vector.solutions.entomology.assay.AdultDiscriminatingDoseAssayQuery.AdultDiscriminatingDoseAssayQueryReferenceIF getAssay(String alias, String displayLabel)
  {

    com.runwaysdk.dataaccess.MdAttributeDAOIF mdAttributeIF = this.getComponentQuery().getMdAttributeROfromMap(dss.vector.solutions.entomology.assay.AdultDiscriminatingDoseInterval.ASSAY);

    return (dss.vector.solutions.entomology.assay.AdultDiscriminatingDoseAssayQuery.AdultDiscriminatingDoseAssayQueryReferenceIF)this.getComponentQuery().internalAttributeFactory(dss.vector.solutions.entomology.assay.AdultDiscriminatingDoseInterval.ASSAY, mdAttributeIF, this, alias, displayLabel);

  }
  public com.runwaysdk.query.SelectableMoment getCreateDate()
  {
    return getCreateDate(null);

  }
 
  public com.runwaysdk.query.SelectableMoment getCreateDate(String alias)
  {
    return (com.runwaysdk.query.SelectableMoment)this.getComponentQuery().get(dss.vector.solutions.entomology.assay.AdultDiscriminatingDoseInterval.CREATEDATE, alias, null);

  }
 
  public com.runwaysdk.query.SelectableMoment getCreateDate(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableMoment)this.getComponentQuery().get(dss.vector.solutions.entomology.assay.AdultDiscriminatingDoseInterval.CREATEDATE, alias, displayLabel);

  }
  public com.runwaysdk.system.SingleActorQuery.SingleActorQueryReferenceIF getCreatedBy()
  {
    return getCreatedBy(null);

  }
 
  public com.runwaysdk.system.SingleActorQuery.SingleActorQueryReferenceIF getCreatedBy(String alias)
  {

    com.runwaysdk.dataaccess.MdAttributeDAOIF mdAttributeIF = this.getComponentQuery().getMdAttributeROfromMap(dss.vector.solutions.entomology.assay.AdultDiscriminatingDoseInterval.CREATEDBY);

    return (com.runwaysdk.system.SingleActorQuery.SingleActorQueryReferenceIF)this.getComponentQuery().internalAttributeFactory(dss.vector.solutions.entomology.assay.AdultDiscriminatingDoseInterval.CREATEDBY, mdAttributeIF, this, alias, null);

  }
 
  public com.runwaysdk.system.SingleActorQuery.SingleActorQueryReferenceIF getCreatedBy(String alias, String displayLabel)
  {

    com.runwaysdk.dataaccess.MdAttributeDAOIF mdAttributeIF = this.getComponentQuery().getMdAttributeROfromMap(dss.vector.solutions.entomology.assay.AdultDiscriminatingDoseInterval.CREATEDBY);

    return (com.runwaysdk.system.SingleActorQuery.SingleActorQueryReferenceIF)this.getComponentQuery().internalAttributeFactory(dss.vector.solutions.entomology.assay.AdultDiscriminatingDoseInterval.CREATEDBY, mdAttributeIF, this, alias, displayLabel);

  }
  public com.runwaysdk.system.metadata.MdDomainQuery.MdDomainQueryReferenceIF getEntityDomain()
  {
    return getEntityDomain(null);

  }
 
  public com.runwaysdk.system.metadata.MdDomainQuery.MdDomainQueryReferenceIF getEntityDomain(String alias)
  {

    com.runwaysdk.dataaccess.MdAttributeDAOIF mdAttributeIF = this.getComponentQuery().getMdAttributeROfromMap(dss.vector.solutions.entomology.assay.AdultDiscriminatingDoseInterval.ENTITYDOMAIN);

    return (com.runwaysdk.system.metadata.MdDomainQuery.MdDomainQueryReferenceIF)this.getComponentQuery().internalAttributeFactory(dss.vector.solutions.entomology.assay.AdultDiscriminatingDoseInterval.ENTITYDOMAIN, mdAttributeIF, this, alias, null);

  }
 
  public com.runwaysdk.system.metadata.MdDomainQuery.MdDomainQueryReferenceIF getEntityDomain(String alias, String displayLabel)
  {

    com.runwaysdk.dataaccess.MdAttributeDAOIF mdAttributeIF = this.getComponentQuery().getMdAttributeROfromMap(dss.vector.solutions.entomology.assay.AdultDiscriminatingDoseInterval.ENTITYDOMAIN);

    return (com.runwaysdk.system.metadata.MdDomainQuery.MdDomainQueryReferenceIF)this.getComponentQuery().internalAttributeFactory(dss.vector.solutions.entomology.assay.AdultDiscriminatingDoseInterval.ENTITYDOMAIN, mdAttributeIF, this, alias, displayLabel);

  }
  public com.runwaysdk.query.SelectableChar getId()
  {
    return getId(null);

  }
 
  public com.runwaysdk.query.SelectableChar getId(String alias)
  {
    return (com.runwaysdk.query.SelectableChar)this.getComponentQuery().get(dss.vector.solutions.entomology.assay.AdultDiscriminatingDoseInterval.ID, alias, null);

  }
 
  public com.runwaysdk.query.SelectableChar getId(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableChar)this.getComponentQuery().get(dss.vector.solutions.entomology.assay.AdultDiscriminatingDoseInterval.ID, alias, displayLabel);

  }
  public com.runwaysdk.query.SelectableInteger getIntervalTime()
  {
    return getIntervalTime(null);

  }
 
  public com.runwaysdk.query.SelectableInteger getIntervalTime(String alias)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getComponentQuery().get(dss.vector.solutions.entomology.assay.AdultDiscriminatingDoseInterval.INTERVALTIME, alias, null);

  }
 
  public com.runwaysdk.query.SelectableInteger getIntervalTime(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getComponentQuery().get(dss.vector.solutions.entomology.assay.AdultDiscriminatingDoseInterval.INTERVALTIME, alias, displayLabel);

  }
  public com.runwaysdk.query.SelectableChar getKeyName()
  {
    return getKeyName(null);

  }
 
  public com.runwaysdk.query.SelectableChar getKeyName(String alias)
  {
    return (com.runwaysdk.query.SelectableChar)this.getComponentQuery().get(dss.vector.solutions.entomology.assay.AdultDiscriminatingDoseInterval.KEYNAME, alias, null);

  }
 
  public com.runwaysdk.query.SelectableChar getKeyName(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableChar)this.getComponentQuery().get(dss.vector.solutions.entomology.assay.AdultDiscriminatingDoseInterval.KEYNAME, alias, displayLabel);

  }
  public com.runwaysdk.query.SelectableMoment getLastUpdateDate()
  {
    return getLastUpdateDate(null);

  }
 
  public com.runwaysdk.query.SelectableMoment getLastUpdateDate(String alias)
  {
    return (com.runwaysdk.query.SelectableMoment)this.getComponentQuery().get(dss.vector.solutions.entomology.assay.AdultDiscriminatingDoseInterval.LASTUPDATEDATE, alias, null);

  }
 
  public com.runwaysdk.query.SelectableMoment getLastUpdateDate(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableMoment)this.getComponentQuery().get(dss.vector.solutions.entomology.assay.AdultDiscriminatingDoseInterval.LASTUPDATEDATE, alias, displayLabel);

  }
  public com.runwaysdk.system.SingleActorQuery.SingleActorQueryReferenceIF getLastUpdatedBy()
  {
    return getLastUpdatedBy(null);

  }
 
  public com.runwaysdk.system.SingleActorQuery.SingleActorQueryReferenceIF getLastUpdatedBy(String alias)
  {

    com.runwaysdk.dataaccess.MdAttributeDAOIF mdAttributeIF = this.getComponentQuery().getMdAttributeROfromMap(dss.vector.solutions.entomology.assay.AdultDiscriminatingDoseInterval.LASTUPDATEDBY);

    return (com.runwaysdk.system.SingleActorQuery.SingleActorQueryReferenceIF)this.getComponentQuery().internalAttributeFactory(dss.vector.solutions.entomology.assay.AdultDiscriminatingDoseInterval.LASTUPDATEDBY, mdAttributeIF, this, alias, null);

  }
 
  public com.runwaysdk.system.SingleActorQuery.SingleActorQueryReferenceIF getLastUpdatedBy(String alias, String displayLabel)
  {

    com.runwaysdk.dataaccess.MdAttributeDAOIF mdAttributeIF = this.getComponentQuery().getMdAttributeROfromMap(dss.vector.solutions.entomology.assay.AdultDiscriminatingDoseInterval.LASTUPDATEDBY);

    return (com.runwaysdk.system.SingleActorQuery.SingleActorQueryReferenceIF)this.getComponentQuery().internalAttributeFactory(dss.vector.solutions.entomology.assay.AdultDiscriminatingDoseInterval.LASTUPDATEDBY, mdAttributeIF, this, alias, displayLabel);

  }
  public com.runwaysdk.system.UsersQuery.UsersQueryReferenceIF getLockedBy()
  {
    return getLockedBy(null);

  }
 
  public com.runwaysdk.system.UsersQuery.UsersQueryReferenceIF getLockedBy(String alias)
  {

    com.runwaysdk.dataaccess.MdAttributeDAOIF mdAttributeIF = this.getComponentQuery().getMdAttributeROfromMap(dss.vector.solutions.entomology.assay.AdultDiscriminatingDoseInterval.LOCKEDBY);

    return (com.runwaysdk.system.UsersQuery.UsersQueryReferenceIF)this.getComponentQuery().internalAttributeFactory(dss.vector.solutions.entomology.assay.AdultDiscriminatingDoseInterval.LOCKEDBY, mdAttributeIF, this, alias, null);

  }
 
  public com.runwaysdk.system.UsersQuery.UsersQueryReferenceIF getLockedBy(String alias, String displayLabel)
  {

    com.runwaysdk.dataaccess.MdAttributeDAOIF mdAttributeIF = this.getComponentQuery().getMdAttributeROfromMap(dss.vector.solutions.entomology.assay.AdultDiscriminatingDoseInterval.LOCKEDBY);

    return (com.runwaysdk.system.UsersQuery.UsersQueryReferenceIF)this.getComponentQuery().internalAttributeFactory(dss.vector.solutions.entomology.assay.AdultDiscriminatingDoseInterval.LOCKEDBY, mdAttributeIF, this, alias, displayLabel);

  }
  public com.runwaysdk.system.ActorQuery.ActorQueryReferenceIF getOwner()
  {
    return getOwner(null);

  }
 
  public com.runwaysdk.system.ActorQuery.ActorQueryReferenceIF getOwner(String alias)
  {

    com.runwaysdk.dataaccess.MdAttributeDAOIF mdAttributeIF = this.getComponentQuery().getMdAttributeROfromMap(dss.vector.solutions.entomology.assay.AdultDiscriminatingDoseInterval.OWNER);

    return (com.runwaysdk.system.ActorQuery.ActorQueryReferenceIF)this.getComponentQuery().internalAttributeFactory(dss.vector.solutions.entomology.assay.AdultDiscriminatingDoseInterval.OWNER, mdAttributeIF, this, alias, null);

  }
 
  public com.runwaysdk.system.ActorQuery.ActorQueryReferenceIF getOwner(String alias, String displayLabel)
  {

    com.runwaysdk.dataaccess.MdAttributeDAOIF mdAttributeIF = this.getComponentQuery().getMdAttributeROfromMap(dss.vector.solutions.entomology.assay.AdultDiscriminatingDoseInterval.OWNER);

    return (com.runwaysdk.system.ActorQuery.ActorQueryReferenceIF)this.getComponentQuery().internalAttributeFactory(dss.vector.solutions.entomology.assay.AdultDiscriminatingDoseInterval.OWNER, mdAttributeIF, this, alias, displayLabel);

  }
  public com.runwaysdk.query.SelectableLong getSeq()
  {
    return getSeq(null);

  }
 
  public com.runwaysdk.query.SelectableLong getSeq(String alias)
  {
    return (com.runwaysdk.query.SelectableLong)this.getComponentQuery().get(dss.vector.solutions.entomology.assay.AdultDiscriminatingDoseInterval.SEQ, alias, null);

  }
 
  public com.runwaysdk.query.SelectableLong getSeq(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableLong)this.getComponentQuery().get(dss.vector.solutions.entomology.assay.AdultDiscriminatingDoseInterval.SEQ, alias, displayLabel);

  }
  public com.runwaysdk.query.SelectableChar getSiteMaster()
  {
    return getSiteMaster(null);

  }
 
  public com.runwaysdk.query.SelectableChar getSiteMaster(String alias)
  {
    return (com.runwaysdk.query.SelectableChar)this.getComponentQuery().get(dss.vector.solutions.entomology.assay.AdultDiscriminatingDoseInterval.SITEMASTER, alias, null);

  }
 
  public com.runwaysdk.query.SelectableChar getSiteMaster(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableChar)this.getComponentQuery().get(dss.vector.solutions.entomology.assay.AdultDiscriminatingDoseInterval.SITEMASTER, alias, displayLabel);

  }
  public com.runwaysdk.query.SelectableChar getType()
  {
    return getType(null);

  }
 
  public com.runwaysdk.query.SelectableChar getType(String alias)
  {
    return (com.runwaysdk.query.SelectableChar)this.getComponentQuery().get(dss.vector.solutions.entomology.assay.AdultDiscriminatingDoseInterval.TYPE, alias, null);

  }
 
  public com.runwaysdk.query.SelectableChar getType(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableChar)this.getComponentQuery().get(dss.vector.solutions.entomology.assay.AdultDiscriminatingDoseInterval.TYPE, alias, displayLabel);

  }
  protected com.runwaysdk.query.AttributeReference referenceFactory( com.runwaysdk.dataaccess.MdAttributeRefDAOIF mdAttributeIF, String attributeNamespace, String definingTableName, String definingTableAlias,  com.runwaysdk.dataaccess.MdBusinessDAOIF referenceMdBusinessIF, String referenceTableAlias, com.runwaysdk.query.ComponentQuery rootQuery, java.util.Set<com.runwaysdk.query.Join> tableJoinSet, String userDefinedAlias, String userDefinedDisplayLabel)
  {
    String name = mdAttributeIF.definesAttribute();
    
    if (name.equals(dss.vector.solutions.entomology.assay.AdultDiscriminatingDoseInterval.ASSAY)) 
    {
       return new dss.vector.solutions.entomology.assay.AdultDiscriminatingDoseAssayQuery.AdultDiscriminatingDoseAssayQueryReference((com.runwaysdk.dataaccess.MdAttributeRefDAOIF)mdAttributeIF, attributeNamespace, definingTableName, definingTableAlias, referenceMdBusinessIF, referenceTableAlias, rootQuery, tableJoinSet, userDefinedAlias, userDefinedDisplayLabel);
    }
    else if (name.equals(dss.vector.solutions.entomology.assay.AdultDiscriminatingDoseInterval.CREATEDBY)) 
    {
       return new com.runwaysdk.system.SingleActorQuery.SingleActorQueryReference((com.runwaysdk.dataaccess.MdAttributeRefDAOIF)mdAttributeIF, attributeNamespace, definingTableName, definingTableAlias, referenceMdBusinessIF, referenceTableAlias, rootQuery, tableJoinSet, userDefinedAlias, userDefinedDisplayLabel);
    }
    else if (name.equals(dss.vector.solutions.entomology.assay.AdultDiscriminatingDoseInterval.ENTITYDOMAIN)) 
    {
       return new com.runwaysdk.system.metadata.MdDomainQuery.MdDomainQueryReference((com.runwaysdk.dataaccess.MdAttributeRefDAOIF)mdAttributeIF, attributeNamespace, definingTableName, definingTableAlias, referenceMdBusinessIF, referenceTableAlias, rootQuery, tableJoinSet, userDefinedAlias, userDefinedDisplayLabel);
    }
    else if (name.equals(dss.vector.solutions.entomology.assay.AdultDiscriminatingDoseInterval.LASTUPDATEDBY)) 
    {
       return new com.runwaysdk.system.SingleActorQuery.SingleActorQueryReference((com.runwaysdk.dataaccess.MdAttributeRefDAOIF)mdAttributeIF, attributeNamespace, definingTableName, definingTableAlias, referenceMdBusinessIF, referenceTableAlias, rootQuery, tableJoinSet, userDefinedAlias, userDefinedDisplayLabel);
    }
    else if (name.equals(dss.vector.solutions.entomology.assay.AdultDiscriminatingDoseInterval.LOCKEDBY)) 
    {
       return new com.runwaysdk.system.UsersQuery.UsersQueryReference((com.runwaysdk.dataaccess.MdAttributeRefDAOIF)mdAttributeIF, attributeNamespace, definingTableName, definingTableAlias, referenceMdBusinessIF, referenceTableAlias, rootQuery, tableJoinSet, userDefinedAlias, userDefinedDisplayLabel);
    }
    else if (name.equals(dss.vector.solutions.entomology.assay.AdultDiscriminatingDoseInterval.OWNER)) 
    {
       return new com.runwaysdk.system.ActorQuery.ActorQueryReference((com.runwaysdk.dataaccess.MdAttributeRefDAOIF)mdAttributeIF, attributeNamespace, definingTableName, definingTableAlias, referenceMdBusinessIF, referenceTableAlias, rootQuery, tableJoinSet, userDefinedAlias, userDefinedDisplayLabel);
    }
    else 
    {
      String error = "Attribute type ["+mdAttributeIF.getType()+"] is invalid.";
      throw new com.runwaysdk.query.QueryException(error);
    }
  }

  /**  
   * Returns an iterator of Business objects that match the query criteria specified
   * on this query object. 
   * @return iterator of Business objects that match the query criteria specified
   * on this query object.
   */
  public com.runwaysdk.query.OIterator<? extends AdultDiscriminatingDoseInterval> getIterator()
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
    return new com.runwaysdk.business.BusinessIterator<AdultDiscriminatingDoseInterval>(this.getComponentQuery().getMdEntityIF(), columnInfoMap, results);
  }


/**
 * Interface that masks all type unsafe query methods and defines all type safe methods.
 * This type is used when a join is performed on this class as a reference.
 **/
  public interface AdultDiscriminatingDoseIntervalQueryReferenceIF extends com.runwaysdk.generation.loader.Reloadable, com.runwaysdk.query.SelectableReference
  {

    public com.runwaysdk.query.SelectableInteger getAmount();
    public com.runwaysdk.query.SelectableInteger getAmount(String alias);
    public com.runwaysdk.query.SelectableInteger getAmount(String alias, String displayLabel);
    public dss.vector.solutions.entomology.assay.AdultDiscriminatingDoseAssayQuery.AdultDiscriminatingDoseAssayQueryReferenceIF getAssay();
    public dss.vector.solutions.entomology.assay.AdultDiscriminatingDoseAssayQuery.AdultDiscriminatingDoseAssayQueryReferenceIF getAssay(String alias);
    public dss.vector.solutions.entomology.assay.AdultDiscriminatingDoseAssayQuery.AdultDiscriminatingDoseAssayQueryReferenceIF getAssay(String alias, String displayLabel);
    public com.runwaysdk.query.SelectableMoment getCreateDate();
    public com.runwaysdk.query.SelectableMoment getCreateDate(String alias);
    public com.runwaysdk.query.SelectableMoment getCreateDate(String alias, String displayLabel);
    public com.runwaysdk.system.SingleActorQuery.SingleActorQueryReferenceIF getCreatedBy();
    public com.runwaysdk.system.SingleActorQuery.SingleActorQueryReferenceIF getCreatedBy(String alias);
    public com.runwaysdk.system.SingleActorQuery.SingleActorQueryReferenceIF getCreatedBy(String alias, String displayLabel);
    public com.runwaysdk.system.metadata.MdDomainQuery.MdDomainQueryReferenceIF getEntityDomain();
    public com.runwaysdk.system.metadata.MdDomainQuery.MdDomainQueryReferenceIF getEntityDomain(String alias);
    public com.runwaysdk.system.metadata.MdDomainQuery.MdDomainQueryReferenceIF getEntityDomain(String alias, String displayLabel);
    public com.runwaysdk.query.SelectableChar getId();
    public com.runwaysdk.query.SelectableChar getId(String alias);
    public com.runwaysdk.query.SelectableChar getId(String alias, String displayLabel);
    public com.runwaysdk.query.SelectableInteger getIntervalTime();
    public com.runwaysdk.query.SelectableInteger getIntervalTime(String alias);
    public com.runwaysdk.query.SelectableInteger getIntervalTime(String alias, String displayLabel);
    public com.runwaysdk.query.SelectableChar getKeyName();
    public com.runwaysdk.query.SelectableChar getKeyName(String alias);
    public com.runwaysdk.query.SelectableChar getKeyName(String alias, String displayLabel);
    public com.runwaysdk.query.SelectableMoment getLastUpdateDate();
    public com.runwaysdk.query.SelectableMoment getLastUpdateDate(String alias);
    public com.runwaysdk.query.SelectableMoment getLastUpdateDate(String alias, String displayLabel);
    public com.runwaysdk.system.SingleActorQuery.SingleActorQueryReferenceIF getLastUpdatedBy();
    public com.runwaysdk.system.SingleActorQuery.SingleActorQueryReferenceIF getLastUpdatedBy(String alias);
    public com.runwaysdk.system.SingleActorQuery.SingleActorQueryReferenceIF getLastUpdatedBy(String alias, String displayLabel);
    public com.runwaysdk.system.UsersQuery.UsersQueryReferenceIF getLockedBy();
    public com.runwaysdk.system.UsersQuery.UsersQueryReferenceIF getLockedBy(String alias);
    public com.runwaysdk.system.UsersQuery.UsersQueryReferenceIF getLockedBy(String alias, String displayLabel);
    public com.runwaysdk.system.ActorQuery.ActorQueryReferenceIF getOwner();
    public com.runwaysdk.system.ActorQuery.ActorQueryReferenceIF getOwner(String alias);
    public com.runwaysdk.system.ActorQuery.ActorQueryReferenceIF getOwner(String alias, String displayLabel);
    public com.runwaysdk.query.SelectableLong getSeq();
    public com.runwaysdk.query.SelectableLong getSeq(String alias);
    public com.runwaysdk.query.SelectableLong getSeq(String alias, String displayLabel);
    public com.runwaysdk.query.SelectableChar getSiteMaster();
    public com.runwaysdk.query.SelectableChar getSiteMaster(String alias);
    public com.runwaysdk.query.SelectableChar getSiteMaster(String alias, String displayLabel);
    public com.runwaysdk.query.SelectableChar getType();
    public com.runwaysdk.query.SelectableChar getType(String alias);
    public com.runwaysdk.query.SelectableChar getType(String alias, String displayLabel);

    public com.runwaysdk.query.BasicCondition EQ(dss.vector.solutions.entomology.assay.AdultDiscriminatingDoseInterval adultDiscriminatingDoseInterval);

    public com.runwaysdk.query.BasicCondition NE(dss.vector.solutions.entomology.assay.AdultDiscriminatingDoseInterval adultDiscriminatingDoseInterval);

  }

/**
 * Implements type safe query methods.
 * This type is used when a join is performed on this class as a reference.
 **/
  public static class AdultDiscriminatingDoseIntervalQueryReference extends com.runwaysdk.query.AttributeReference
 implements AdultDiscriminatingDoseIntervalQueryReferenceIF
, com.runwaysdk.generation.loader.Reloadable
  {

  public AdultDiscriminatingDoseIntervalQueryReference(com.runwaysdk.dataaccess.MdAttributeRefDAOIF mdAttributeIF, String attributeNamespace, String definingTableName, String definingTableAlias, com.runwaysdk.dataaccess.MdBusinessDAOIF referenceMdBusinessIF, String referenceTableAlias, com.runwaysdk.query.ComponentQuery rootQuery, java.util.Set<com.runwaysdk.query.Join> tableJoinSet, String alias, String displayLabel)
  {
    super(mdAttributeIF, attributeNamespace, definingTableName, definingTableAlias, referenceMdBusinessIF, referenceTableAlias, rootQuery, tableJoinSet, alias, displayLabel);

  }


    public com.runwaysdk.query.BasicCondition EQ(dss.vector.solutions.entomology.assay.AdultDiscriminatingDoseInterval adultDiscriminatingDoseInterval)
    {
      if(adultDiscriminatingDoseInterval == null) return this.EQ((java.lang.String)null);
      return this.EQ(adultDiscriminatingDoseInterval.getId());
    }

    public com.runwaysdk.query.BasicCondition NE(dss.vector.solutions.entomology.assay.AdultDiscriminatingDoseInterval adultDiscriminatingDoseInterval)
    {
      if(adultDiscriminatingDoseInterval == null) return this.NE((java.lang.String)null);
      return this.NE(adultDiscriminatingDoseInterval.getId());
    }

  public com.runwaysdk.query.SelectableInteger getAmount()
  {
    return getAmount(null);

  }
 
  public com.runwaysdk.query.SelectableInteger getAmount(String alias)
  {
    return (com.runwaysdk.query.SelectableInteger)this.get(dss.vector.solutions.entomology.assay.AdultDiscriminatingDoseInterval.AMOUNT, alias, null);

  }
 
  public com.runwaysdk.query.SelectableInteger getAmount(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableInteger)this.get(dss.vector.solutions.entomology.assay.AdultDiscriminatingDoseInterval.AMOUNT, alias, displayLabel);

  }
  public dss.vector.solutions.entomology.assay.AdultDiscriminatingDoseAssayQuery.AdultDiscriminatingDoseAssayQueryReferenceIF getAssay()
  {
    return getAssay(null);

  }
 
  public dss.vector.solutions.entomology.assay.AdultDiscriminatingDoseAssayQuery.AdultDiscriminatingDoseAssayQueryReferenceIF getAssay(String alias)
  {
    return (dss.vector.solutions.entomology.assay.AdultDiscriminatingDoseAssayQuery.AdultDiscriminatingDoseAssayQueryReferenceIF)this.get(dss.vector.solutions.entomology.assay.AdultDiscriminatingDoseInterval.ASSAY, alias, null);

  }
 
  public dss.vector.solutions.entomology.assay.AdultDiscriminatingDoseAssayQuery.AdultDiscriminatingDoseAssayQueryReferenceIF getAssay(String alias, String displayLabel)
  {
    return (dss.vector.solutions.entomology.assay.AdultDiscriminatingDoseAssayQuery.AdultDiscriminatingDoseAssayQueryReferenceIF)this.get(dss.vector.solutions.entomology.assay.AdultDiscriminatingDoseInterval.ASSAY,  alias, displayLabel);

  }
  public com.runwaysdk.query.SelectableMoment getCreateDate()
  {
    return getCreateDate(null);

  }
 
  public com.runwaysdk.query.SelectableMoment getCreateDate(String alias)
  {
    return (com.runwaysdk.query.SelectableMoment)this.get(dss.vector.solutions.entomology.assay.AdultDiscriminatingDoseInterval.CREATEDATE, alias, null);

  }
 
  public com.runwaysdk.query.SelectableMoment getCreateDate(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableMoment)this.get(dss.vector.solutions.entomology.assay.AdultDiscriminatingDoseInterval.CREATEDATE, alias, displayLabel);

  }
  public com.runwaysdk.system.SingleActorQuery.SingleActorQueryReferenceIF getCreatedBy()
  {
    return getCreatedBy(null);

  }
 
  public com.runwaysdk.system.SingleActorQuery.SingleActorQueryReferenceIF getCreatedBy(String alias)
  {
    return (com.runwaysdk.system.SingleActorQuery.SingleActorQueryReferenceIF)this.get(dss.vector.solutions.entomology.assay.AdultDiscriminatingDoseInterval.CREATEDBY, alias, null);

  }
 
  public com.runwaysdk.system.SingleActorQuery.SingleActorQueryReferenceIF getCreatedBy(String alias, String displayLabel)
  {
    return (com.runwaysdk.system.SingleActorQuery.SingleActorQueryReferenceIF)this.get(dss.vector.solutions.entomology.assay.AdultDiscriminatingDoseInterval.CREATEDBY,  alias, displayLabel);

  }
  public com.runwaysdk.system.metadata.MdDomainQuery.MdDomainQueryReferenceIF getEntityDomain()
  {
    return getEntityDomain(null);

  }
 
  public com.runwaysdk.system.metadata.MdDomainQuery.MdDomainQueryReferenceIF getEntityDomain(String alias)
  {
    return (com.runwaysdk.system.metadata.MdDomainQuery.MdDomainQueryReferenceIF)this.get(dss.vector.solutions.entomology.assay.AdultDiscriminatingDoseInterval.ENTITYDOMAIN, alias, null);

  }
 
  public com.runwaysdk.system.metadata.MdDomainQuery.MdDomainQueryReferenceIF getEntityDomain(String alias, String displayLabel)
  {
    return (com.runwaysdk.system.metadata.MdDomainQuery.MdDomainQueryReferenceIF)this.get(dss.vector.solutions.entomology.assay.AdultDiscriminatingDoseInterval.ENTITYDOMAIN,  alias, displayLabel);

  }
  public com.runwaysdk.query.SelectableChar getId()
  {
    return getId(null);

  }
 
  public com.runwaysdk.query.SelectableChar getId(String alias)
  {
    return (com.runwaysdk.query.SelectableChar)this.get(dss.vector.solutions.entomology.assay.AdultDiscriminatingDoseInterval.ID, alias, null);

  }
 
  public com.runwaysdk.query.SelectableChar getId(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableChar)this.get(dss.vector.solutions.entomology.assay.AdultDiscriminatingDoseInterval.ID, alias, displayLabel);

  }
  public com.runwaysdk.query.SelectableInteger getIntervalTime()
  {
    return getIntervalTime(null);

  }
 
  public com.runwaysdk.query.SelectableInteger getIntervalTime(String alias)
  {
    return (com.runwaysdk.query.SelectableInteger)this.get(dss.vector.solutions.entomology.assay.AdultDiscriminatingDoseInterval.INTERVALTIME, alias, null);

  }
 
  public com.runwaysdk.query.SelectableInteger getIntervalTime(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableInteger)this.get(dss.vector.solutions.entomology.assay.AdultDiscriminatingDoseInterval.INTERVALTIME, alias, displayLabel);

  }
  public com.runwaysdk.query.SelectableChar getKeyName()
  {
    return getKeyName(null);

  }
 
  public com.runwaysdk.query.SelectableChar getKeyName(String alias)
  {
    return (com.runwaysdk.query.SelectableChar)this.get(dss.vector.solutions.entomology.assay.AdultDiscriminatingDoseInterval.KEYNAME, alias, null);

  }
 
  public com.runwaysdk.query.SelectableChar getKeyName(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableChar)this.get(dss.vector.solutions.entomology.assay.AdultDiscriminatingDoseInterval.KEYNAME, alias, displayLabel);

  }
  public com.runwaysdk.query.SelectableMoment getLastUpdateDate()
  {
    return getLastUpdateDate(null);

  }
 
  public com.runwaysdk.query.SelectableMoment getLastUpdateDate(String alias)
  {
    return (com.runwaysdk.query.SelectableMoment)this.get(dss.vector.solutions.entomology.assay.AdultDiscriminatingDoseInterval.LASTUPDATEDATE, alias, null);

  }
 
  public com.runwaysdk.query.SelectableMoment getLastUpdateDate(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableMoment)this.get(dss.vector.solutions.entomology.assay.AdultDiscriminatingDoseInterval.LASTUPDATEDATE, alias, displayLabel);

  }
  public com.runwaysdk.system.SingleActorQuery.SingleActorQueryReferenceIF getLastUpdatedBy()
  {
    return getLastUpdatedBy(null);

  }
 
  public com.runwaysdk.system.SingleActorQuery.SingleActorQueryReferenceIF getLastUpdatedBy(String alias)
  {
    return (com.runwaysdk.system.SingleActorQuery.SingleActorQueryReferenceIF)this.get(dss.vector.solutions.entomology.assay.AdultDiscriminatingDoseInterval.LASTUPDATEDBY, alias, null);

  }
 
  public com.runwaysdk.system.SingleActorQuery.SingleActorQueryReferenceIF getLastUpdatedBy(String alias, String displayLabel)
  {
    return (com.runwaysdk.system.SingleActorQuery.SingleActorQueryReferenceIF)this.get(dss.vector.solutions.entomology.assay.AdultDiscriminatingDoseInterval.LASTUPDATEDBY,  alias, displayLabel);

  }
  public com.runwaysdk.system.UsersQuery.UsersQueryReferenceIF getLockedBy()
  {
    return getLockedBy(null);

  }
 
  public com.runwaysdk.system.UsersQuery.UsersQueryReferenceIF getLockedBy(String alias)
  {
    return (com.runwaysdk.system.UsersQuery.UsersQueryReferenceIF)this.get(dss.vector.solutions.entomology.assay.AdultDiscriminatingDoseInterval.LOCKEDBY, alias, null);

  }
 
  public com.runwaysdk.system.UsersQuery.UsersQueryReferenceIF getLockedBy(String alias, String displayLabel)
  {
    return (com.runwaysdk.system.UsersQuery.UsersQueryReferenceIF)this.get(dss.vector.solutions.entomology.assay.AdultDiscriminatingDoseInterval.LOCKEDBY,  alias, displayLabel);

  }
  public com.runwaysdk.system.ActorQuery.ActorQueryReferenceIF getOwner()
  {
    return getOwner(null);

  }
 
  public com.runwaysdk.system.ActorQuery.ActorQueryReferenceIF getOwner(String alias)
  {
    return (com.runwaysdk.system.ActorQuery.ActorQueryReferenceIF)this.get(dss.vector.solutions.entomology.assay.AdultDiscriminatingDoseInterval.OWNER, alias, null);

  }
 
  public com.runwaysdk.system.ActorQuery.ActorQueryReferenceIF getOwner(String alias, String displayLabel)
  {
    return (com.runwaysdk.system.ActorQuery.ActorQueryReferenceIF)this.get(dss.vector.solutions.entomology.assay.AdultDiscriminatingDoseInterval.OWNER,  alias, displayLabel);

  }
  public com.runwaysdk.query.SelectableLong getSeq()
  {
    return getSeq(null);

  }
 
  public com.runwaysdk.query.SelectableLong getSeq(String alias)
  {
    return (com.runwaysdk.query.SelectableLong)this.get(dss.vector.solutions.entomology.assay.AdultDiscriminatingDoseInterval.SEQ, alias, null);

  }
 
  public com.runwaysdk.query.SelectableLong getSeq(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableLong)this.get(dss.vector.solutions.entomology.assay.AdultDiscriminatingDoseInterval.SEQ, alias, displayLabel);

  }
  public com.runwaysdk.query.SelectableChar getSiteMaster()
  {
    return getSiteMaster(null);

  }
 
  public com.runwaysdk.query.SelectableChar getSiteMaster(String alias)
  {
    return (com.runwaysdk.query.SelectableChar)this.get(dss.vector.solutions.entomology.assay.AdultDiscriminatingDoseInterval.SITEMASTER, alias, null);

  }
 
  public com.runwaysdk.query.SelectableChar getSiteMaster(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableChar)this.get(dss.vector.solutions.entomology.assay.AdultDiscriminatingDoseInterval.SITEMASTER, alias, displayLabel);

  }
  public com.runwaysdk.query.SelectableChar getType()
  {
    return getType(null);

  }
 
  public com.runwaysdk.query.SelectableChar getType(String alias)
  {
    return (com.runwaysdk.query.SelectableChar)this.get(dss.vector.solutions.entomology.assay.AdultDiscriminatingDoseInterval.TYPE, alias, null);

  }
 
  public com.runwaysdk.query.SelectableChar getType(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableChar)this.get(dss.vector.solutions.entomology.assay.AdultDiscriminatingDoseInterval.TYPE, alias, displayLabel);

  }
  protected com.runwaysdk.query.AttributeReference referenceFactory( com.runwaysdk.dataaccess.MdAttributeRefDAOIF mdAttributeIF, String attributeNamespace, String definingTableName, String definingTableAlias,  com.runwaysdk.dataaccess.MdBusinessDAOIF referenceMdBusinessIF, String referenceTableAlias, com.runwaysdk.query.ComponentQuery rootQuery, java.util.Set<com.runwaysdk.query.Join> tableJoinSet, String userDefinedAlias, String userDefinedDisplayLabel)
  {
    String name = mdAttributeIF.definesAttribute();
    
    if (name.equals(dss.vector.solutions.entomology.assay.AdultDiscriminatingDoseInterval.ASSAY)) 
    {
       return new dss.vector.solutions.entomology.assay.AdultDiscriminatingDoseAssayQuery.AdultDiscriminatingDoseAssayQueryReference((com.runwaysdk.dataaccess.MdAttributeRefDAOIF)mdAttributeIF, attributeNamespace, definingTableName, definingTableAlias, referenceMdBusinessIF, referenceTableAlias, rootQuery, tableJoinSet, userDefinedAlias, userDefinedDisplayLabel);
    }
    else if (name.equals(dss.vector.solutions.entomology.assay.AdultDiscriminatingDoseInterval.CREATEDBY)) 
    {
       return new com.runwaysdk.system.SingleActorQuery.SingleActorQueryReference((com.runwaysdk.dataaccess.MdAttributeRefDAOIF)mdAttributeIF, attributeNamespace, definingTableName, definingTableAlias, referenceMdBusinessIF, referenceTableAlias, rootQuery, tableJoinSet, userDefinedAlias, userDefinedDisplayLabel);
    }
    else if (name.equals(dss.vector.solutions.entomology.assay.AdultDiscriminatingDoseInterval.ENTITYDOMAIN)) 
    {
       return new com.runwaysdk.system.metadata.MdDomainQuery.MdDomainQueryReference((com.runwaysdk.dataaccess.MdAttributeRefDAOIF)mdAttributeIF, attributeNamespace, definingTableName, definingTableAlias, referenceMdBusinessIF, referenceTableAlias, rootQuery, tableJoinSet, userDefinedAlias, userDefinedDisplayLabel);
    }
    else if (name.equals(dss.vector.solutions.entomology.assay.AdultDiscriminatingDoseInterval.LASTUPDATEDBY)) 
    {
       return new com.runwaysdk.system.SingleActorQuery.SingleActorQueryReference((com.runwaysdk.dataaccess.MdAttributeRefDAOIF)mdAttributeIF, attributeNamespace, definingTableName, definingTableAlias, referenceMdBusinessIF, referenceTableAlias, rootQuery, tableJoinSet, userDefinedAlias, userDefinedDisplayLabel);
    }
    else if (name.equals(dss.vector.solutions.entomology.assay.AdultDiscriminatingDoseInterval.LOCKEDBY)) 
    {
       return new com.runwaysdk.system.UsersQuery.UsersQueryReference((com.runwaysdk.dataaccess.MdAttributeRefDAOIF)mdAttributeIF, attributeNamespace, definingTableName, definingTableAlias, referenceMdBusinessIF, referenceTableAlias, rootQuery, tableJoinSet, userDefinedAlias, userDefinedDisplayLabel);
    }
    else if (name.equals(dss.vector.solutions.entomology.assay.AdultDiscriminatingDoseInterval.OWNER)) 
    {
       return new com.runwaysdk.system.ActorQuery.ActorQueryReference((com.runwaysdk.dataaccess.MdAttributeRefDAOIF)mdAttributeIF, attributeNamespace, definingTableName, definingTableAlias, referenceMdBusinessIF, referenceTableAlias, rootQuery, tableJoinSet, userDefinedAlias, userDefinedDisplayLabel);
    }
    else 
    {
      String error = "Attribute type ["+mdAttributeIF.getType()+"] is invalid.";
      throw new com.runwaysdk.query.QueryException(error);
    }
  }

  }
}
