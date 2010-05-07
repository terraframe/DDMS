package dss.vector.solutions.entomology;

@com.runwaysdk.business.ClassSignature(hash = -647537391)
/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to PupalCollection.java
 *
 * @author Autogenerated by RunwaySDK
 */
public  class PupalCollectionQuery extends com.runwaysdk.query.GeneratedBusinessQuery
 implements com.runwaysdk.generation.loader.Reloadable
{
private static final long serialVersionUID = -647537391;

  public PupalCollectionQuery(com.runwaysdk.query.QueryFactory componentQueryFactory)
  {
     super();
    if (this.getComponentQuery() == null)
    {
      com.runwaysdk.business.BusinessQuery businessQuery = componentQueryFactory.businessQuery(this.getClassType());

       this.setBusinessQuery(businessQuery);
    }
  }

  public PupalCollectionQuery(com.runwaysdk.query.ValueQuery valueQuery)
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
    return dss.vector.solutions.entomology.PupalCollection.CLASS;
  }
  public com.runwaysdk.query.SelectableChar getCollectionId()
  {
    return getCollectionId(null);

  }
 
  public com.runwaysdk.query.SelectableChar getCollectionId(String alias)
  {
    return (com.runwaysdk.query.SelectableChar)this.getComponentQuery().get(dss.vector.solutions.entomology.PupalCollection.COLLECTIONID, alias, null);

  }
 
  public com.runwaysdk.query.SelectableChar getCollectionId(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableChar)this.getComponentQuery().get(dss.vector.solutions.entomology.PupalCollection.COLLECTIONID, alias, displayLabel);

  }
  public com.runwaysdk.query.SelectableMoment getCreateDate()
  {
    return getCreateDate(null);

  }
 
  public com.runwaysdk.query.SelectableMoment getCreateDate(String alias)
  {
    return (com.runwaysdk.query.SelectableMoment)this.getComponentQuery().get(dss.vector.solutions.entomology.PupalCollection.CREATEDATE, alias, null);

  }
 
  public com.runwaysdk.query.SelectableMoment getCreateDate(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableMoment)this.getComponentQuery().get(dss.vector.solutions.entomology.PupalCollection.CREATEDATE, alias, displayLabel);

  }
  public com.runwaysdk.system.SingleActorQuery.SingleActorQueryReferenceIF getCreatedBy()
  {
    return getCreatedBy(null);

  }
 
  public com.runwaysdk.system.SingleActorQuery.SingleActorQueryReferenceIF getCreatedBy(String alias)
  {

    com.runwaysdk.dataaccess.MdAttributeDAOIF mdAttributeIF = this.getComponentQuery().getMdAttributeROfromMap("createdBy");

    return (com.runwaysdk.system.SingleActorQuery.SingleActorQueryReferenceIF)this.getComponentQuery().internalAttributeFactory(dss.vector.solutions.entomology.PupalCollection.CREATEDBY, mdAttributeIF, this, alias, null);

  }
 
  public com.runwaysdk.system.SingleActorQuery.SingleActorQueryReferenceIF getCreatedBy(String alias, String displayLabel)
  {

    com.runwaysdk.dataaccess.MdAttributeDAOIF mdAttributeIF = this.getComponentQuery().getMdAttributeROfromMap("createdBy");

    return (com.runwaysdk.system.SingleActorQuery.SingleActorQueryReferenceIF)this.getComponentQuery().internalAttributeFactory(dss.vector.solutions.entomology.PupalCollection.CREATEDBY, mdAttributeIF, this, alias, displayLabel);

  }
  public com.runwaysdk.query.SelectableMoment getEndDate()
  {
    return getEndDate(null);

  }
 
  public com.runwaysdk.query.SelectableMoment getEndDate(String alias)
  {
    return (com.runwaysdk.query.SelectableMoment)this.getComponentQuery().get(dss.vector.solutions.entomology.PupalCollection.ENDDATE, alias, null);

  }
 
  public com.runwaysdk.query.SelectableMoment getEndDate(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableMoment)this.getComponentQuery().get(dss.vector.solutions.entomology.PupalCollection.ENDDATE, alias, displayLabel);

  }
  public com.runwaysdk.system.metadata.MdDomainQuery.MdDomainQueryReferenceIF getEntityDomain()
  {
    return getEntityDomain(null);

  }
 
  public com.runwaysdk.system.metadata.MdDomainQuery.MdDomainQueryReferenceIF getEntityDomain(String alias)
  {

    com.runwaysdk.dataaccess.MdAttributeDAOIF mdAttributeIF = this.getComponentQuery().getMdAttributeROfromMap("entityDomain");

    return (com.runwaysdk.system.metadata.MdDomainQuery.MdDomainQueryReferenceIF)this.getComponentQuery().internalAttributeFactory(dss.vector.solutions.entomology.PupalCollection.ENTITYDOMAIN, mdAttributeIF, this, alias, null);

  }
 
  public com.runwaysdk.system.metadata.MdDomainQuery.MdDomainQueryReferenceIF getEntityDomain(String alias, String displayLabel)
  {

    com.runwaysdk.dataaccess.MdAttributeDAOIF mdAttributeIF = this.getComponentQuery().getMdAttributeROfromMap("entityDomain");

    return (com.runwaysdk.system.metadata.MdDomainQuery.MdDomainQueryReferenceIF)this.getComponentQuery().internalAttributeFactory(dss.vector.solutions.entomology.PupalCollection.ENTITYDOMAIN, mdAttributeIF, this, alias, displayLabel);

  }
  public dss.vector.solutions.geo.generated.GeoEntityQuery.GeoEntityQueryReferenceIF getGeoEntity()
  {
    return getGeoEntity(null);

  }
 
  public dss.vector.solutions.geo.generated.GeoEntityQuery.GeoEntityQueryReferenceIF getGeoEntity(String alias)
  {

    com.runwaysdk.dataaccess.MdAttributeDAOIF mdAttributeIF = this.getComponentQuery().getMdAttributeROfromMap("geoEntity");

    return (dss.vector.solutions.geo.generated.GeoEntityQuery.GeoEntityQueryReferenceIF)this.getComponentQuery().internalAttributeFactory(dss.vector.solutions.entomology.PupalCollection.GEOENTITY, mdAttributeIF, this, alias, null);

  }
 
  public dss.vector.solutions.geo.generated.GeoEntityQuery.GeoEntityQueryReferenceIF getGeoEntity(String alias, String displayLabel)
  {

    com.runwaysdk.dataaccess.MdAttributeDAOIF mdAttributeIF = this.getComponentQuery().getMdAttributeROfromMap("geoEntity");

    return (dss.vector.solutions.geo.generated.GeoEntityQuery.GeoEntityQueryReferenceIF)this.getComponentQuery().internalAttributeFactory(dss.vector.solutions.entomology.PupalCollection.GEOENTITY, mdAttributeIF, this, alias, displayLabel);

  }
  public com.runwaysdk.query.SelectableChar getId()
  {
    return getId(null);

  }
 
  public com.runwaysdk.query.SelectableChar getId(String alias)
  {
    return (com.runwaysdk.query.SelectableChar)this.getComponentQuery().get(dss.vector.solutions.entomology.PupalCollection.ID, alias, null);

  }
 
  public com.runwaysdk.query.SelectableChar getId(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableChar)this.getComponentQuery().get(dss.vector.solutions.entomology.PupalCollection.ID, alias, displayLabel);

  }
  public com.runwaysdk.query.SelectableChar getKeyName()
  {
    return getKeyName(null);

  }
 
  public com.runwaysdk.query.SelectableChar getKeyName(String alias)
  {
    return (com.runwaysdk.query.SelectableChar)this.getComponentQuery().get(dss.vector.solutions.entomology.PupalCollection.KEYNAME, alias, null);

  }
 
  public com.runwaysdk.query.SelectableChar getKeyName(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableChar)this.getComponentQuery().get(dss.vector.solutions.entomology.PupalCollection.KEYNAME, alias, displayLabel);

  }
  public com.runwaysdk.query.SelectableMoment getLastUpdateDate()
  {
    return getLastUpdateDate(null);

  }
 
  public com.runwaysdk.query.SelectableMoment getLastUpdateDate(String alias)
  {
    return (com.runwaysdk.query.SelectableMoment)this.getComponentQuery().get(dss.vector.solutions.entomology.PupalCollection.LASTUPDATEDATE, alias, null);

  }
 
  public com.runwaysdk.query.SelectableMoment getLastUpdateDate(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableMoment)this.getComponentQuery().get(dss.vector.solutions.entomology.PupalCollection.LASTUPDATEDATE, alias, displayLabel);

  }
  public com.runwaysdk.system.SingleActorQuery.SingleActorQueryReferenceIF getLastUpdatedBy()
  {
    return getLastUpdatedBy(null);

  }
 
  public com.runwaysdk.system.SingleActorQuery.SingleActorQueryReferenceIF getLastUpdatedBy(String alias)
  {

    com.runwaysdk.dataaccess.MdAttributeDAOIF mdAttributeIF = this.getComponentQuery().getMdAttributeROfromMap("lastUpdatedBy");

    return (com.runwaysdk.system.SingleActorQuery.SingleActorQueryReferenceIF)this.getComponentQuery().internalAttributeFactory(dss.vector.solutions.entomology.PupalCollection.LASTUPDATEDBY, mdAttributeIF, this, alias, null);

  }
 
  public com.runwaysdk.system.SingleActorQuery.SingleActorQueryReferenceIF getLastUpdatedBy(String alias, String displayLabel)
  {

    com.runwaysdk.dataaccess.MdAttributeDAOIF mdAttributeIF = this.getComponentQuery().getMdAttributeROfromMap("lastUpdatedBy");

    return (com.runwaysdk.system.SingleActorQuery.SingleActorQueryReferenceIF)this.getComponentQuery().internalAttributeFactory(dss.vector.solutions.entomology.PupalCollection.LASTUPDATEDBY, mdAttributeIF, this, alias, displayLabel);

  }
  public com.runwaysdk.system.UsersQuery.UsersQueryReferenceIF getLockedBy()
  {
    return getLockedBy(null);

  }
 
  public com.runwaysdk.system.UsersQuery.UsersQueryReferenceIF getLockedBy(String alias)
  {

    com.runwaysdk.dataaccess.MdAttributeDAOIF mdAttributeIF = this.getComponentQuery().getMdAttributeROfromMap("lockedBy");

    return (com.runwaysdk.system.UsersQuery.UsersQueryReferenceIF)this.getComponentQuery().internalAttributeFactory(dss.vector.solutions.entomology.PupalCollection.LOCKEDBY, mdAttributeIF, this, alias, null);

  }
 
  public com.runwaysdk.system.UsersQuery.UsersQueryReferenceIF getLockedBy(String alias, String displayLabel)
  {

    com.runwaysdk.dataaccess.MdAttributeDAOIF mdAttributeIF = this.getComponentQuery().getMdAttributeROfromMap("lockedBy");

    return (com.runwaysdk.system.UsersQuery.UsersQueryReferenceIF)this.getComponentQuery().internalAttributeFactory(dss.vector.solutions.entomology.PupalCollection.LOCKEDBY, mdAttributeIF, this, alias, displayLabel);

  }
  public com.runwaysdk.query.SelectableChar getNotes()
  {
    return getNotes(null);

  }
 
  public com.runwaysdk.query.SelectableChar getNotes(String alias)
  {
    return (com.runwaysdk.query.SelectableChar)this.getComponentQuery().get(dss.vector.solutions.entomology.PupalCollection.NOTES, alias, null);

  }
 
  public com.runwaysdk.query.SelectableChar getNotes(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableChar)this.getComponentQuery().get(dss.vector.solutions.entomology.PupalCollection.NOTES, alias, displayLabel);

  }
  public com.runwaysdk.system.ActorQuery.ActorQueryReferenceIF getOwner()
  {
    return getOwner(null);

  }
 
  public com.runwaysdk.system.ActorQuery.ActorQueryReferenceIF getOwner(String alias)
  {

    com.runwaysdk.dataaccess.MdAttributeDAOIF mdAttributeIF = this.getComponentQuery().getMdAttributeROfromMap("owner");

    return (com.runwaysdk.system.ActorQuery.ActorQueryReferenceIF)this.getComponentQuery().internalAttributeFactory(dss.vector.solutions.entomology.PupalCollection.OWNER, mdAttributeIF, this, alias, null);

  }
 
  public com.runwaysdk.system.ActorQuery.ActorQueryReferenceIF getOwner(String alias, String displayLabel)
  {

    com.runwaysdk.dataaccess.MdAttributeDAOIF mdAttributeIF = this.getComponentQuery().getMdAttributeROfromMap("owner");

    return (com.runwaysdk.system.ActorQuery.ActorQueryReferenceIF)this.getComponentQuery().internalAttributeFactory(dss.vector.solutions.entomology.PupalCollection.OWNER, mdAttributeIF, this, alias, displayLabel);

  }
  public com.runwaysdk.query.SelectableLong getSeq()
  {
    return getSeq(null);

  }
 
  public com.runwaysdk.query.SelectableLong getSeq(String alias)
  {
    return (com.runwaysdk.query.SelectableLong)this.getComponentQuery().get(dss.vector.solutions.entomology.PupalCollection.SEQ, alias, null);

  }
 
  public com.runwaysdk.query.SelectableLong getSeq(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableLong)this.getComponentQuery().get(dss.vector.solutions.entomology.PupalCollection.SEQ, alias, displayLabel);

  }
  public com.runwaysdk.query.SelectableChar getSiteMaster()
  {
    return getSiteMaster(null);

  }
 
  public com.runwaysdk.query.SelectableChar getSiteMaster(String alias)
  {
    return (com.runwaysdk.query.SelectableChar)this.getComponentQuery().get(dss.vector.solutions.entomology.PupalCollection.SITEMASTER, alias, null);

  }
 
  public com.runwaysdk.query.SelectableChar getSiteMaster(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableChar)this.getComponentQuery().get(dss.vector.solutions.entomology.PupalCollection.SITEMASTER, alias, displayLabel);

  }
  public com.runwaysdk.query.SelectableMoment getStartDate()
  {
    return getStartDate(null);

  }
 
  public com.runwaysdk.query.SelectableMoment getStartDate(String alias)
  {
    return (com.runwaysdk.query.SelectableMoment)this.getComponentQuery().get(dss.vector.solutions.entomology.PupalCollection.STARTDATE, alias, null);

  }
 
  public com.runwaysdk.query.SelectableMoment getStartDate(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableMoment)this.getComponentQuery().get(dss.vector.solutions.entomology.PupalCollection.STARTDATE, alias, displayLabel);

  }
  public com.runwaysdk.query.SelectableChar getType()
  {
    return getType(null);

  }
 
  public com.runwaysdk.query.SelectableChar getType(String alias)
  {
    return (com.runwaysdk.query.SelectableChar)this.getComponentQuery().get(dss.vector.solutions.entomology.PupalCollection.TYPE, alias, null);

  }
 
  public com.runwaysdk.query.SelectableChar getType(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableChar)this.getComponentQuery().get(dss.vector.solutions.entomology.PupalCollection.TYPE, alias, displayLabel);

  }
  protected com.runwaysdk.query.AttributeReference referenceFactory( com.runwaysdk.dataaccess.MdAttributeRefDAOIF mdAttributeIF, String attributeNamespace, String definingTableName, String definingTableAlias,  com.runwaysdk.dataaccess.MdBusinessDAOIF referenceMdBusinessIF, String referenceTableAlias, com.runwaysdk.query.ComponentQuery rootQuery, java.util.Set<com.runwaysdk.query.Join> tableJoinSet, String userDefinedAlias, String userDefinedDisplayLabel)
  {
    String name = mdAttributeIF.definesAttribute();
    
    if (name.equals("createdBy")) 
    {
       return new com.runwaysdk.system.SingleActorQuery.SingleActorQueryReference((com.runwaysdk.dataaccess.MdAttributeRefDAOIF)mdAttributeIF, attributeNamespace, definingTableName, definingTableAlias, referenceMdBusinessIF, referenceTableAlias, rootQuery, tableJoinSet, userDefinedAlias, userDefinedDisplayLabel);
    }
    else if (name.equals("entityDomain")) 
    {
       return new com.runwaysdk.system.metadata.MdDomainQuery.MdDomainQueryReference((com.runwaysdk.dataaccess.MdAttributeRefDAOIF)mdAttributeIF, attributeNamespace, definingTableName, definingTableAlias, referenceMdBusinessIF, referenceTableAlias, rootQuery, tableJoinSet, userDefinedAlias, userDefinedDisplayLabel);
    }
    else if (name.equals("geoEntity")) 
    {
       return new dss.vector.solutions.geo.generated.GeoEntityQuery.GeoEntityQueryReference((com.runwaysdk.dataaccess.MdAttributeRefDAOIF)mdAttributeIF, attributeNamespace, definingTableName, definingTableAlias, referenceMdBusinessIF, referenceTableAlias, rootQuery, tableJoinSet, userDefinedAlias, userDefinedDisplayLabel);
    }
    else if (name.equals("lastUpdatedBy")) 
    {
       return new com.runwaysdk.system.SingleActorQuery.SingleActorQueryReference((com.runwaysdk.dataaccess.MdAttributeRefDAOIF)mdAttributeIF, attributeNamespace, definingTableName, definingTableAlias, referenceMdBusinessIF, referenceTableAlias, rootQuery, tableJoinSet, userDefinedAlias, userDefinedDisplayLabel);
    }
    else if (name.equals("lockedBy")) 
    {
       return new com.runwaysdk.system.UsersQuery.UsersQueryReference((com.runwaysdk.dataaccess.MdAttributeRefDAOIF)mdAttributeIF, attributeNamespace, definingTableName, definingTableAlias, referenceMdBusinessIF, referenceTableAlias, rootQuery, tableJoinSet, userDefinedAlias, userDefinedDisplayLabel);
    }
    else if (name.equals("owner")) 
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
  public com.runwaysdk.query.OIterator<? extends PupalCollection> getIterator()
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
    return new com.runwaysdk.business.BusinessIterator<PupalCollection>(this.getComponentQuery().getMdEntityIF(), columnInfoMap, results);
  }


/**
 * Interface that masks all type unsafe query methods and defines all type safe methods.
 * This type is used when a join is performed on this class as a reference.
 **/
  public interface PupalCollectionQueryReferenceIF extends com.runwaysdk.generation.loader.Reloadable, com.runwaysdk.query.SelectableReference
  {

    public com.runwaysdk.query.SelectableChar getCollectionId();
    public com.runwaysdk.query.SelectableChar getCollectionId(String alias);
    public com.runwaysdk.query.SelectableChar getCollectionId(String alias, String displayLabel);
    public com.runwaysdk.query.SelectableMoment getCreateDate();
    public com.runwaysdk.query.SelectableMoment getCreateDate(String alias);
    public com.runwaysdk.query.SelectableMoment getCreateDate(String alias, String displayLabel);
    public com.runwaysdk.system.SingleActorQuery.SingleActorQueryReferenceIF getCreatedBy();
    public com.runwaysdk.system.SingleActorQuery.SingleActorQueryReferenceIF getCreatedBy(String alias);
    public com.runwaysdk.system.SingleActorQuery.SingleActorQueryReferenceIF getCreatedBy(String alias, String displayLabel);
    public com.runwaysdk.query.SelectableMoment getEndDate();
    public com.runwaysdk.query.SelectableMoment getEndDate(String alias);
    public com.runwaysdk.query.SelectableMoment getEndDate(String alias, String displayLabel);
    public com.runwaysdk.system.metadata.MdDomainQuery.MdDomainQueryReferenceIF getEntityDomain();
    public com.runwaysdk.system.metadata.MdDomainQuery.MdDomainQueryReferenceIF getEntityDomain(String alias);
    public com.runwaysdk.system.metadata.MdDomainQuery.MdDomainQueryReferenceIF getEntityDomain(String alias, String displayLabel);
    public dss.vector.solutions.geo.generated.GeoEntityQuery.GeoEntityQueryReferenceIF getGeoEntity();
    public dss.vector.solutions.geo.generated.GeoEntityQuery.GeoEntityQueryReferenceIF getGeoEntity(String alias);
    public dss.vector.solutions.geo.generated.GeoEntityQuery.GeoEntityQueryReferenceIF getGeoEntity(String alias, String displayLabel);
    public com.runwaysdk.query.SelectableChar getId();
    public com.runwaysdk.query.SelectableChar getId(String alias);
    public com.runwaysdk.query.SelectableChar getId(String alias, String displayLabel);
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
    public com.runwaysdk.query.SelectableChar getNotes();
    public com.runwaysdk.query.SelectableChar getNotes(String alias);
    public com.runwaysdk.query.SelectableChar getNotes(String alias, String displayLabel);
    public com.runwaysdk.system.ActorQuery.ActorQueryReferenceIF getOwner();
    public com.runwaysdk.system.ActorQuery.ActorQueryReferenceIF getOwner(String alias);
    public com.runwaysdk.system.ActorQuery.ActorQueryReferenceIF getOwner(String alias, String displayLabel);
    public com.runwaysdk.query.SelectableLong getSeq();
    public com.runwaysdk.query.SelectableLong getSeq(String alias);
    public com.runwaysdk.query.SelectableLong getSeq(String alias, String displayLabel);
    public com.runwaysdk.query.SelectableChar getSiteMaster();
    public com.runwaysdk.query.SelectableChar getSiteMaster(String alias);
    public com.runwaysdk.query.SelectableChar getSiteMaster(String alias, String displayLabel);
    public com.runwaysdk.query.SelectableMoment getStartDate();
    public com.runwaysdk.query.SelectableMoment getStartDate(String alias);
    public com.runwaysdk.query.SelectableMoment getStartDate(String alias, String displayLabel);
    public com.runwaysdk.query.SelectableChar getType();
    public com.runwaysdk.query.SelectableChar getType(String alias);
    public com.runwaysdk.query.SelectableChar getType(String alias, String displayLabel);

    public com.runwaysdk.query.BasicCondition EQ(dss.vector.solutions.entomology.PupalCollection pupalCollection);

    public com.runwaysdk.query.BasicCondition NE(dss.vector.solutions.entomology.PupalCollection pupalCollection);

  }

/**
 * Implements type safe query methods.
 * This type is used when a join is performed on this class as a reference.
 **/
  public static class PupalCollectionQueryReference extends com.runwaysdk.query.AttributeReference
 implements PupalCollectionQueryReferenceIF
, com.runwaysdk.generation.loader.Reloadable
  {
private static final long serialVersionUID = -1123154001;

  public PupalCollectionQueryReference(com.runwaysdk.dataaccess.MdAttributeRefDAOIF mdAttributeIF, String attributeNamespace, String definingTableName, String definingTableAlias, com.runwaysdk.dataaccess.MdBusinessDAOIF referenceMdBusinessIF, String referenceTableAlias, com.runwaysdk.query.ComponentQuery rootQuery, java.util.Set<com.runwaysdk.query.Join> tableJoinSet, String alias, String displayLabel)
  {
    super(mdAttributeIF, attributeNamespace, definingTableName, definingTableAlias, referenceMdBusinessIF, referenceTableAlias, rootQuery, tableJoinSet, alias, displayLabel);

  }


    public com.runwaysdk.query.BasicCondition EQ(dss.vector.solutions.entomology.PupalCollection pupalCollection)
    {
      return this.EQ(pupalCollection.getId());
    }

    public com.runwaysdk.query.BasicCondition NE(dss.vector.solutions.entomology.PupalCollection pupalCollection)
    {
      return this.NE(pupalCollection.getId());
    }

  public com.runwaysdk.query.SelectableChar getCollectionId()
  {
    return getCollectionId(null);

  }
 
  public com.runwaysdk.query.SelectableChar getCollectionId(String alias)
  {
    return (com.runwaysdk.query.SelectableChar)this.get(dss.vector.solutions.entomology.PupalCollection.COLLECTIONID, alias, null);

  }
 
  public com.runwaysdk.query.SelectableChar getCollectionId(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableChar)this.get(dss.vector.solutions.entomology.PupalCollection.COLLECTIONID, alias, displayLabel);

  }
  public com.runwaysdk.query.SelectableMoment getCreateDate()
  {
    return getCreateDate(null);

  }
 
  public com.runwaysdk.query.SelectableMoment getCreateDate(String alias)
  {
    return (com.runwaysdk.query.SelectableMoment)this.get(dss.vector.solutions.entomology.PupalCollection.CREATEDATE, alias, null);

  }
 
  public com.runwaysdk.query.SelectableMoment getCreateDate(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableMoment)this.get(dss.vector.solutions.entomology.PupalCollection.CREATEDATE, alias, displayLabel);

  }
  public com.runwaysdk.system.SingleActorQuery.SingleActorQueryReferenceIF getCreatedBy()
  {
    return getCreatedBy(null);

  }
 
  public com.runwaysdk.system.SingleActorQuery.SingleActorQueryReferenceIF getCreatedBy(String alias)
  {
    return (com.runwaysdk.system.SingleActorQuery.SingleActorQueryReferenceIF)this.get(dss.vector.solutions.entomology.PupalCollection.CREATEDBY, alias, null);

  }
 
  public com.runwaysdk.system.SingleActorQuery.SingleActorQueryReferenceIF getCreatedBy(String alias, String displayLabel)
  {
    return (com.runwaysdk.system.SingleActorQuery.SingleActorQueryReferenceIF)this.get(dss.vector.solutions.entomology.PupalCollection.CREATEDBY,  alias, displayLabel);

  }
  public com.runwaysdk.query.SelectableMoment getEndDate()
  {
    return getEndDate(null);

  }
 
  public com.runwaysdk.query.SelectableMoment getEndDate(String alias)
  {
    return (com.runwaysdk.query.SelectableMoment)this.get(dss.vector.solutions.entomology.PupalCollection.ENDDATE, alias, null);

  }
 
  public com.runwaysdk.query.SelectableMoment getEndDate(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableMoment)this.get(dss.vector.solutions.entomology.PupalCollection.ENDDATE, alias, displayLabel);

  }
  public com.runwaysdk.system.metadata.MdDomainQuery.MdDomainQueryReferenceIF getEntityDomain()
  {
    return getEntityDomain(null);

  }
 
  public com.runwaysdk.system.metadata.MdDomainQuery.MdDomainQueryReferenceIF getEntityDomain(String alias)
  {
    return (com.runwaysdk.system.metadata.MdDomainQuery.MdDomainQueryReferenceIF)this.get(dss.vector.solutions.entomology.PupalCollection.ENTITYDOMAIN, alias, null);

  }
 
  public com.runwaysdk.system.metadata.MdDomainQuery.MdDomainQueryReferenceIF getEntityDomain(String alias, String displayLabel)
  {
    return (com.runwaysdk.system.metadata.MdDomainQuery.MdDomainQueryReferenceIF)this.get(dss.vector.solutions.entomology.PupalCollection.ENTITYDOMAIN,  alias, displayLabel);

  }
  public dss.vector.solutions.geo.generated.GeoEntityQuery.GeoEntityQueryReferenceIF getGeoEntity()
  {
    return getGeoEntity(null);

  }
 
  public dss.vector.solutions.geo.generated.GeoEntityQuery.GeoEntityQueryReferenceIF getGeoEntity(String alias)
  {
    return (dss.vector.solutions.geo.generated.GeoEntityQuery.GeoEntityQueryReferenceIF)this.get(dss.vector.solutions.entomology.PupalCollection.GEOENTITY, alias, null);

  }
 
  public dss.vector.solutions.geo.generated.GeoEntityQuery.GeoEntityQueryReferenceIF getGeoEntity(String alias, String displayLabel)
  {
    return (dss.vector.solutions.geo.generated.GeoEntityQuery.GeoEntityQueryReferenceIF)this.get(dss.vector.solutions.entomology.PupalCollection.GEOENTITY,  alias, displayLabel);

  }
  public com.runwaysdk.query.SelectableChar getId()
  {
    return getId(null);

  }
 
  public com.runwaysdk.query.SelectableChar getId(String alias)
  {
    return (com.runwaysdk.query.SelectableChar)this.get(dss.vector.solutions.entomology.PupalCollection.ID, alias, null);

  }
 
  public com.runwaysdk.query.SelectableChar getId(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableChar)this.get(dss.vector.solutions.entomology.PupalCollection.ID, alias, displayLabel);

  }
  public com.runwaysdk.query.SelectableChar getKeyName()
  {
    return getKeyName(null);

  }
 
  public com.runwaysdk.query.SelectableChar getKeyName(String alias)
  {
    return (com.runwaysdk.query.SelectableChar)this.get(dss.vector.solutions.entomology.PupalCollection.KEYNAME, alias, null);

  }
 
  public com.runwaysdk.query.SelectableChar getKeyName(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableChar)this.get(dss.vector.solutions.entomology.PupalCollection.KEYNAME, alias, displayLabel);

  }
  public com.runwaysdk.query.SelectableMoment getLastUpdateDate()
  {
    return getLastUpdateDate(null);

  }
 
  public com.runwaysdk.query.SelectableMoment getLastUpdateDate(String alias)
  {
    return (com.runwaysdk.query.SelectableMoment)this.get(dss.vector.solutions.entomology.PupalCollection.LASTUPDATEDATE, alias, null);

  }
 
  public com.runwaysdk.query.SelectableMoment getLastUpdateDate(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableMoment)this.get(dss.vector.solutions.entomology.PupalCollection.LASTUPDATEDATE, alias, displayLabel);

  }
  public com.runwaysdk.system.SingleActorQuery.SingleActorQueryReferenceIF getLastUpdatedBy()
  {
    return getLastUpdatedBy(null);

  }
 
  public com.runwaysdk.system.SingleActorQuery.SingleActorQueryReferenceIF getLastUpdatedBy(String alias)
  {
    return (com.runwaysdk.system.SingleActorQuery.SingleActorQueryReferenceIF)this.get(dss.vector.solutions.entomology.PupalCollection.LASTUPDATEDBY, alias, null);

  }
 
  public com.runwaysdk.system.SingleActorQuery.SingleActorQueryReferenceIF getLastUpdatedBy(String alias, String displayLabel)
  {
    return (com.runwaysdk.system.SingleActorQuery.SingleActorQueryReferenceIF)this.get(dss.vector.solutions.entomology.PupalCollection.LASTUPDATEDBY,  alias, displayLabel);

  }
  public com.runwaysdk.system.UsersQuery.UsersQueryReferenceIF getLockedBy()
  {
    return getLockedBy(null);

  }
 
  public com.runwaysdk.system.UsersQuery.UsersQueryReferenceIF getLockedBy(String alias)
  {
    return (com.runwaysdk.system.UsersQuery.UsersQueryReferenceIF)this.get(dss.vector.solutions.entomology.PupalCollection.LOCKEDBY, alias, null);

  }
 
  public com.runwaysdk.system.UsersQuery.UsersQueryReferenceIF getLockedBy(String alias, String displayLabel)
  {
    return (com.runwaysdk.system.UsersQuery.UsersQueryReferenceIF)this.get(dss.vector.solutions.entomology.PupalCollection.LOCKEDBY,  alias, displayLabel);

  }
  public com.runwaysdk.query.SelectableChar getNotes()
  {
    return getNotes(null);

  }
 
  public com.runwaysdk.query.SelectableChar getNotes(String alias)
  {
    return (com.runwaysdk.query.SelectableChar)this.get(dss.vector.solutions.entomology.PupalCollection.NOTES, alias, null);

  }
 
  public com.runwaysdk.query.SelectableChar getNotes(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableChar)this.get(dss.vector.solutions.entomology.PupalCollection.NOTES, alias, displayLabel);

  }
  public com.runwaysdk.system.ActorQuery.ActorQueryReferenceIF getOwner()
  {
    return getOwner(null);

  }
 
  public com.runwaysdk.system.ActorQuery.ActorQueryReferenceIF getOwner(String alias)
  {
    return (com.runwaysdk.system.ActorQuery.ActorQueryReferenceIF)this.get(dss.vector.solutions.entomology.PupalCollection.OWNER, alias, null);

  }
 
  public com.runwaysdk.system.ActorQuery.ActorQueryReferenceIF getOwner(String alias, String displayLabel)
  {
    return (com.runwaysdk.system.ActorQuery.ActorQueryReferenceIF)this.get(dss.vector.solutions.entomology.PupalCollection.OWNER,  alias, displayLabel);

  }
  public com.runwaysdk.query.SelectableLong getSeq()
  {
    return getSeq(null);

  }
 
  public com.runwaysdk.query.SelectableLong getSeq(String alias)
  {
    return (com.runwaysdk.query.SelectableLong)this.get(dss.vector.solutions.entomology.PupalCollection.SEQ, alias, null);

  }
 
  public com.runwaysdk.query.SelectableLong getSeq(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableLong)this.get(dss.vector.solutions.entomology.PupalCollection.SEQ, alias, displayLabel);

  }
  public com.runwaysdk.query.SelectableChar getSiteMaster()
  {
    return getSiteMaster(null);

  }
 
  public com.runwaysdk.query.SelectableChar getSiteMaster(String alias)
  {
    return (com.runwaysdk.query.SelectableChar)this.get(dss.vector.solutions.entomology.PupalCollection.SITEMASTER, alias, null);

  }
 
  public com.runwaysdk.query.SelectableChar getSiteMaster(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableChar)this.get(dss.vector.solutions.entomology.PupalCollection.SITEMASTER, alias, displayLabel);

  }
  public com.runwaysdk.query.SelectableMoment getStartDate()
  {
    return getStartDate(null);

  }
 
  public com.runwaysdk.query.SelectableMoment getStartDate(String alias)
  {
    return (com.runwaysdk.query.SelectableMoment)this.get(dss.vector.solutions.entomology.PupalCollection.STARTDATE, alias, null);

  }
 
  public com.runwaysdk.query.SelectableMoment getStartDate(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableMoment)this.get(dss.vector.solutions.entomology.PupalCollection.STARTDATE, alias, displayLabel);

  }
  public com.runwaysdk.query.SelectableChar getType()
  {
    return getType(null);

  }
 
  public com.runwaysdk.query.SelectableChar getType(String alias)
  {
    return (com.runwaysdk.query.SelectableChar)this.get(dss.vector.solutions.entomology.PupalCollection.TYPE, alias, null);

  }
 
  public com.runwaysdk.query.SelectableChar getType(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableChar)this.get(dss.vector.solutions.entomology.PupalCollection.TYPE, alias, displayLabel);

  }
  protected com.runwaysdk.query.AttributeReference referenceFactory( com.runwaysdk.dataaccess.MdAttributeRefDAOIF mdAttributeIF, String attributeNamespace, String definingTableName, String definingTableAlias,  com.runwaysdk.dataaccess.MdBusinessDAOIF referenceMdBusinessIF, String referenceTableAlias, com.runwaysdk.query.ComponentQuery rootQuery, java.util.Set<com.runwaysdk.query.Join> tableJoinSet, String userDefinedAlias, String userDefinedDisplayLabel)
  {
    String name = mdAttributeIF.definesAttribute();
    
    if (name.equals("createdBy")) 
    {
       return new com.runwaysdk.system.SingleActorQuery.SingleActorQueryReference((com.runwaysdk.dataaccess.MdAttributeRefDAOIF)mdAttributeIF, attributeNamespace, definingTableName, definingTableAlias, referenceMdBusinessIF, referenceTableAlias, rootQuery, tableJoinSet, userDefinedAlias, userDefinedDisplayLabel);
    }
    else if (name.equals("entityDomain")) 
    {
       return new com.runwaysdk.system.metadata.MdDomainQuery.MdDomainQueryReference((com.runwaysdk.dataaccess.MdAttributeRefDAOIF)mdAttributeIF, attributeNamespace, definingTableName, definingTableAlias, referenceMdBusinessIF, referenceTableAlias, rootQuery, tableJoinSet, userDefinedAlias, userDefinedDisplayLabel);
    }
    else if (name.equals("geoEntity")) 
    {
       return new dss.vector.solutions.geo.generated.GeoEntityQuery.GeoEntityQueryReference((com.runwaysdk.dataaccess.MdAttributeRefDAOIF)mdAttributeIF, attributeNamespace, definingTableName, definingTableAlias, referenceMdBusinessIF, referenceTableAlias, rootQuery, tableJoinSet, userDefinedAlias, userDefinedDisplayLabel);
    }
    else if (name.equals("lastUpdatedBy")) 
    {
       return new com.runwaysdk.system.SingleActorQuery.SingleActorQueryReference((com.runwaysdk.dataaccess.MdAttributeRefDAOIF)mdAttributeIF, attributeNamespace, definingTableName, definingTableAlias, referenceMdBusinessIF, referenceTableAlias, rootQuery, tableJoinSet, userDefinedAlias, userDefinedDisplayLabel);
    }
    else if (name.equals("lockedBy")) 
    {
       return new com.runwaysdk.system.UsersQuery.UsersQueryReference((com.runwaysdk.dataaccess.MdAttributeRefDAOIF)mdAttributeIF, attributeNamespace, definingTableName, definingTableAlias, referenceMdBusinessIF, referenceTableAlias, rootQuery, tableJoinSet, userDefinedAlias, userDefinedDisplayLabel);
    }
    else if (name.equals("owner")) 
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
