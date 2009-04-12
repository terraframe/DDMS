package dss.vector.solutions.entomology;

/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to MorphologicalSpecieGroup.java
 *
 * @author Autogenerated by TerraFrame
 */
public  class MorphologicalSpecieGroupQuery extends com.terraframe.mojo.query.GeneratedBusinessQuery
 implements com.terraframe.mojo.generation.loader.Reloadable
{
private static final long serialVersionUID = 1239572496439L;

  public MorphologicalSpecieGroupQuery(com.terraframe.mojo.query.QueryFactory componentQueryFactory)
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
    return "dss.vector.solutions.entomology.MorphologicalSpecieGroup";
  }
  public dss.vector.solutions.entomology.ConcreteMosquitoCollectionQuery.ConcreteMosquitoCollectionQueryReferenceIF getCollection()
  {
    return getCollection(null);

  }
 
  public dss.vector.solutions.entomology.ConcreteMosquitoCollectionQuery.ConcreteMosquitoCollectionQueryReferenceIF getCollection(String alias)
  {

    com.terraframe.mojo.dataaccess.MdAttributeDAOIF mdAttributeIF = this.getComponentQuery().getMdAttributeROfromMap("collection");

    return (dss.vector.solutions.entomology.ConcreteMosquitoCollectionQuery.ConcreteMosquitoCollectionQueryReferenceIF)this.getComponentQuery().internalAttributeFactory(dss.vector.solutions.entomology.MorphologicalSpecieGroup.COLLECTION, mdAttributeIF, this, alias);

  }
  public com.terraframe.mojo.query.AttributeMomentIF getCreateDate()
  {
    return getCreateDate(null);

  }
 
  public com.terraframe.mojo.query.AttributeMomentIF getCreateDate(String alias)
  {
    return (com.terraframe.mojo.query.AttributeMomentIF)this.getComponentQuery().attributeFactory(dss.vector.solutions.entomology.MorphologicalSpecieGroup.CREATEDATE, "com.terraframe.mojo.system.metadata.MdAttributeDateTime", alias);

  }
  public com.terraframe.mojo.system.SingleActorQuery.SingleActorQueryReferenceIF getCreatedBy()
  {
    return getCreatedBy(null);

  }
 
  public com.terraframe.mojo.system.SingleActorQuery.SingleActorQueryReferenceIF getCreatedBy(String alias)
  {

    com.terraframe.mojo.dataaccess.MdAttributeDAOIF mdAttributeIF = this.getComponentQuery().getMdAttributeROfromMap("createdBy");

    return (com.terraframe.mojo.system.SingleActorQuery.SingleActorQueryReferenceIF)this.getComponentQuery().internalAttributeFactory(dss.vector.solutions.entomology.MorphologicalSpecieGroup.CREATEDBY, mdAttributeIF, this, alias);

  }
  public com.terraframe.mojo.system.metadata.MdDomainQuery.MdDomainQueryReferenceIF getEntityDomain()
  {
    return getEntityDomain(null);

  }
 
  public com.terraframe.mojo.system.metadata.MdDomainQuery.MdDomainQueryReferenceIF getEntityDomain(String alias)
  {

    com.terraframe.mojo.dataaccess.MdAttributeDAOIF mdAttributeIF = this.getComponentQuery().getMdAttributeROfromMap("entityDomain");

    return (com.terraframe.mojo.system.metadata.MdDomainQuery.MdDomainQueryReferenceIF)this.getComponentQuery().internalAttributeFactory(dss.vector.solutions.entomology.MorphologicalSpecieGroup.ENTITYDOMAIN, mdAttributeIF, this, alias);

  }
  public com.terraframe.mojo.query.AttributeCharIF getId()
  {
    return getId(null);

  }
 
  public com.terraframe.mojo.query.AttributeCharIF getId(String alias)
  {
    return (com.terraframe.mojo.query.AttributeCharIF)this.getComponentQuery().attributeFactory(dss.vector.solutions.entomology.MorphologicalSpecieGroup.ID, "com.terraframe.mojo.system.metadata.MdAttributeCharacter", alias);

  }
  public dss.vector.solutions.mo.IdentificationMethodQuery.IdentificationMethodQueryReferenceIF getIdentificationMethod()
  {
    return getIdentificationMethod(null);

  }
 
  public dss.vector.solutions.mo.IdentificationMethodQuery.IdentificationMethodQueryReferenceIF getIdentificationMethod(String alias)
  {

    com.terraframe.mojo.dataaccess.MdAttributeDAOIF mdAttributeIF = this.getComponentQuery().getMdAttributeROfromMap("identificationMethod");

    return (dss.vector.solutions.mo.IdentificationMethodQuery.IdentificationMethodQueryReferenceIF)this.getComponentQuery().internalAttributeFactory(dss.vector.solutions.entomology.MorphologicalSpecieGroup.IDENTIFICATIONMETHOD, mdAttributeIF, this, alias);

  }
  public com.terraframe.mojo.query.AttributeCharIF getKeyName()
  {
    return getKeyName(null);

  }
 
  public com.terraframe.mojo.query.AttributeCharIF getKeyName(String alias)
  {
    return (com.terraframe.mojo.query.AttributeCharIF)this.getComponentQuery().attributeFactory(dss.vector.solutions.entomology.MorphologicalSpecieGroup.KEYNAME, "com.terraframe.mojo.system.metadata.MdAttributeCharacter", alias);

  }
  public com.terraframe.mojo.query.AttributeMomentIF getLastUpdateDate()
  {
    return getLastUpdateDate(null);

  }
 
  public com.terraframe.mojo.query.AttributeMomentIF getLastUpdateDate(String alias)
  {
    return (com.terraframe.mojo.query.AttributeMomentIF)this.getComponentQuery().attributeFactory(dss.vector.solutions.entomology.MorphologicalSpecieGroup.LASTUPDATEDATE, "com.terraframe.mojo.system.metadata.MdAttributeDateTime", alias);

  }
  public com.terraframe.mojo.system.SingleActorQuery.SingleActorQueryReferenceIF getLastUpdatedBy()
  {
    return getLastUpdatedBy(null);

  }
 
  public com.terraframe.mojo.system.SingleActorQuery.SingleActorQueryReferenceIF getLastUpdatedBy(String alias)
  {

    com.terraframe.mojo.dataaccess.MdAttributeDAOIF mdAttributeIF = this.getComponentQuery().getMdAttributeROfromMap("lastUpdatedBy");

    return (com.terraframe.mojo.system.SingleActorQuery.SingleActorQueryReferenceIF)this.getComponentQuery().internalAttributeFactory(dss.vector.solutions.entomology.MorphologicalSpecieGroup.LASTUPDATEDBY, mdAttributeIF, this, alias);

  }
  public com.terraframe.mojo.system.UsersQuery.UsersQueryReferenceIF getLockedBy()
  {
    return getLockedBy(null);

  }
 
  public com.terraframe.mojo.system.UsersQuery.UsersQueryReferenceIF getLockedBy(String alias)
  {

    com.terraframe.mojo.dataaccess.MdAttributeDAOIF mdAttributeIF = this.getComponentQuery().getMdAttributeROfromMap("lockedBy");

    return (com.terraframe.mojo.system.UsersQuery.UsersQueryReferenceIF)this.getComponentQuery().internalAttributeFactory(dss.vector.solutions.entomology.MorphologicalSpecieGroup.LOCKEDBY, mdAttributeIF, this, alias);

  }
  public com.terraframe.mojo.system.ActorQuery.ActorQueryReferenceIF getOwner()
  {
    return getOwner(null);

  }
 
  public com.terraframe.mojo.system.ActorQuery.ActorQueryReferenceIF getOwner(String alias)
  {

    com.terraframe.mojo.dataaccess.MdAttributeDAOIF mdAttributeIF = this.getComponentQuery().getMdAttributeROfromMap("owner");

    return (com.terraframe.mojo.system.ActorQuery.ActorQueryReferenceIF)this.getComponentQuery().internalAttributeFactory(dss.vector.solutions.entomology.MorphologicalSpecieGroup.OWNER, mdAttributeIF, this, alias);

  }
  public com.terraframe.mojo.query.AttributeIntegerIF getQuantity()
  {
    return getQuantity(null);

  }
 
  public com.terraframe.mojo.query.AttributeIntegerIF getQuantity(String alias)
  {
    return (com.terraframe.mojo.query.AttributeIntegerIF)this.getComponentQuery().attributeFactory(dss.vector.solutions.entomology.MorphologicalSpecieGroup.QUANTITY, "com.terraframe.mojo.system.metadata.MdAttributeInteger", alias);

  }
  public com.terraframe.mojo.query.AttributeIntegerIF getQuantityFemale()
  {
    return getQuantityFemale(null);

  }
 
  public com.terraframe.mojo.query.AttributeIntegerIF getQuantityFemale(String alias)
  {
    return (com.terraframe.mojo.query.AttributeIntegerIF)this.getComponentQuery().attributeFactory(dss.vector.solutions.entomology.MorphologicalSpecieGroup.QUANTITYFEMALE, "com.terraframe.mojo.system.metadata.MdAttributeInteger", alias);

  }
  public com.terraframe.mojo.query.AttributeIntegerIF getQuantityMale()
  {
    return getQuantityMale(null);

  }
 
  public com.terraframe.mojo.query.AttributeIntegerIF getQuantityMale(String alias)
  {
    return (com.terraframe.mojo.query.AttributeIntegerIF)this.getComponentQuery().attributeFactory(dss.vector.solutions.entomology.MorphologicalSpecieGroup.QUANTITYMALE, "com.terraframe.mojo.system.metadata.MdAttributeInteger", alias);

  }
  public com.terraframe.mojo.query.AttributeLongIF getSeq()
  {
    return getSeq(null);

  }
 
  public com.terraframe.mojo.query.AttributeLongIF getSeq(String alias)
  {
    return (com.terraframe.mojo.query.AttributeLongIF)this.getComponentQuery().attributeFactory(dss.vector.solutions.entomology.MorphologicalSpecieGroup.SEQ, "com.terraframe.mojo.system.metadata.MdAttributeLong", alias);

  }
  public com.terraframe.mojo.query.AttributeCharIF getSiteMaster()
  {
    return getSiteMaster(null);

  }
 
  public com.terraframe.mojo.query.AttributeCharIF getSiteMaster(String alias)
  {
    return (com.terraframe.mojo.query.AttributeCharIF)this.getComponentQuery().attributeFactory(dss.vector.solutions.entomology.MorphologicalSpecieGroup.SITEMASTER, "com.terraframe.mojo.system.metadata.MdAttributeCharacter", alias);

  }
  public dss.vector.solutions.mo.SpecieQuery.SpecieQueryReferenceIF getSpecie()
  {
    return getSpecie(null);

  }
 
  public dss.vector.solutions.mo.SpecieQuery.SpecieQueryReferenceIF getSpecie(String alias)
  {

    com.terraframe.mojo.dataaccess.MdAttributeDAOIF mdAttributeIF = this.getComponentQuery().getMdAttributeROfromMap("specie");

    return (dss.vector.solutions.mo.SpecieQuery.SpecieQueryReferenceIF)this.getComponentQuery().internalAttributeFactory(dss.vector.solutions.entomology.MorphologicalSpecieGroup.SPECIE, mdAttributeIF, this, alias);

  }
  public com.terraframe.mojo.query.AttributeCharIF getType()
  {
    return getType(null);

  }
 
  public com.terraframe.mojo.query.AttributeCharIF getType(String alias)
  {
    return (com.terraframe.mojo.query.AttributeCharIF)this.getComponentQuery().attributeFactory(dss.vector.solutions.entomology.MorphologicalSpecieGroup.TYPE, "com.terraframe.mojo.system.metadata.MdAttributeCharacter", alias);

  }
  protected com.terraframe.mojo.query.AttributeReference referenceFactory( com.terraframe.mojo.dataaccess.MdAttributeRefDAOIF mdAttributeIF, String attributeNamespace, String definingTableName, String definingTableAlias,  com.terraframe.mojo.dataaccess.MdBusinessDAOIF referenceMdBusinessIF, String referenceTableAlias, com.terraframe.mojo.query.ComponentQuery rootQuery, java.util.Set<com.terraframe.mojo.query.Join> tableJoinSet, String userDefinedAlias)
  {
    String name = mdAttributeIF.definesAttribute();
    
    if (name.equals("collection")) 
    {
       return new dss.vector.solutions.entomology.ConcreteMosquitoCollectionQuery.ConcreteMosquitoCollectionQueryReference((com.terraframe.mojo.dataaccess.MdAttributeRefDAOIF)mdAttributeIF, attributeNamespace, definingTableName, definingTableAlias, referenceMdBusinessIF, referenceTableAlias, rootQuery, tableJoinSet, userDefinedAlias);
    }
    else if (name.equals("createdBy")) 
    {
       return new com.terraframe.mojo.system.SingleActorQuery.SingleActorQueryReference((com.terraframe.mojo.dataaccess.MdAttributeRefDAOIF)mdAttributeIF, attributeNamespace, definingTableName, definingTableAlias, referenceMdBusinessIF, referenceTableAlias, rootQuery, tableJoinSet, userDefinedAlias);
    }
    else if (name.equals("entityDomain")) 
    {
       return new com.terraframe.mojo.system.metadata.MdDomainQuery.MdDomainQueryReference((com.terraframe.mojo.dataaccess.MdAttributeRefDAOIF)mdAttributeIF, attributeNamespace, definingTableName, definingTableAlias, referenceMdBusinessIF, referenceTableAlias, rootQuery, tableJoinSet, userDefinedAlias);
    }
    else if (name.equals("identificationMethod")) 
    {
       return new dss.vector.solutions.mo.IdentificationMethodQuery.IdentificationMethodQueryReference((com.terraframe.mojo.dataaccess.MdAttributeRefDAOIF)mdAttributeIF, attributeNamespace, definingTableName, definingTableAlias, referenceMdBusinessIF, referenceTableAlias, rootQuery, tableJoinSet, userDefinedAlias);
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
    else if (name.equals("specie")) 
    {
       return new dss.vector.solutions.mo.SpecieQuery.SpecieQueryReference((com.terraframe.mojo.dataaccess.MdAttributeRefDAOIF)mdAttributeIF, attributeNamespace, definingTableName, definingTableAlias, referenceMdBusinessIF, referenceTableAlias, rootQuery, tableJoinSet, userDefinedAlias);
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
  public com.terraframe.mojo.query.OIterator<? extends MorphologicalSpecieGroup> getIterator()
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
    return new com.terraframe.mojo.business.BusinessIterator<MorphologicalSpecieGroup>(this.getComponentQuery().getMdEntityIF(), columnInfoMap, results);
  }


/**
 * Interface that masks all type unsafe query methods and defines all type safe methods.
 * This type is used when a join is performed on this class as a reference.
 **/
  public interface MorphologicalSpecieGroupQueryReferenceIF extends com.terraframe.mojo.generation.loader.Reloadable, com.terraframe.mojo.query.AttributeReferenceIF
  {

    public dss.vector.solutions.entomology.ConcreteMosquitoCollectionQuery.ConcreteMosquitoCollectionQueryReferenceIF getCollection();
    public dss.vector.solutions.entomology.ConcreteMosquitoCollectionQuery.ConcreteMosquitoCollectionQueryReferenceIF getCollection(String alias);
    public com.terraframe.mojo.query.AttributeMomentIF getCreateDate();
    public com.terraframe.mojo.query.AttributeMomentIF getCreateDate(String alias);
    public com.terraframe.mojo.system.SingleActorQuery.SingleActorQueryReferenceIF getCreatedBy();
    public com.terraframe.mojo.system.SingleActorQuery.SingleActorQueryReferenceIF getCreatedBy(String alias);
    public com.terraframe.mojo.system.metadata.MdDomainQuery.MdDomainQueryReferenceIF getEntityDomain();
    public com.terraframe.mojo.system.metadata.MdDomainQuery.MdDomainQueryReferenceIF getEntityDomain(String alias);
    public com.terraframe.mojo.query.AttributeCharIF getId();
    public com.terraframe.mojo.query.AttributeCharIF getId(String alias);
    public dss.vector.solutions.mo.IdentificationMethodQuery.IdentificationMethodQueryReferenceIF getIdentificationMethod();
    public dss.vector.solutions.mo.IdentificationMethodQuery.IdentificationMethodQueryReferenceIF getIdentificationMethod(String alias);
    public com.terraframe.mojo.query.AttributeCharIF getKeyName();
    public com.terraframe.mojo.query.AttributeCharIF getKeyName(String alias);
    public com.terraframe.mojo.query.AttributeMomentIF getLastUpdateDate();
    public com.terraframe.mojo.query.AttributeMomentIF getLastUpdateDate(String alias);
    public com.terraframe.mojo.system.SingleActorQuery.SingleActorQueryReferenceIF getLastUpdatedBy();
    public com.terraframe.mojo.system.SingleActorQuery.SingleActorQueryReferenceIF getLastUpdatedBy(String alias);
    public com.terraframe.mojo.system.UsersQuery.UsersQueryReferenceIF getLockedBy();
    public com.terraframe.mojo.system.UsersQuery.UsersQueryReferenceIF getLockedBy(String alias);
    public com.terraframe.mojo.system.ActorQuery.ActorQueryReferenceIF getOwner();
    public com.terraframe.mojo.system.ActorQuery.ActorQueryReferenceIF getOwner(String alias);
    public com.terraframe.mojo.query.AttributeIntegerIF getQuantity();
    public com.terraframe.mojo.query.AttributeIntegerIF getQuantity(String alias);
    public com.terraframe.mojo.query.AttributeIntegerIF getQuantityFemale();
    public com.terraframe.mojo.query.AttributeIntegerIF getQuantityFemale(String alias);
    public com.terraframe.mojo.query.AttributeIntegerIF getQuantityMale();
    public com.terraframe.mojo.query.AttributeIntegerIF getQuantityMale(String alias);
    public com.terraframe.mojo.query.AttributeLongIF getSeq();
    public com.terraframe.mojo.query.AttributeLongIF getSeq(String alias);
    public com.terraframe.mojo.query.AttributeCharIF getSiteMaster();
    public com.terraframe.mojo.query.AttributeCharIF getSiteMaster(String alias);
    public dss.vector.solutions.mo.SpecieQuery.SpecieQueryReferenceIF getSpecie();
    public dss.vector.solutions.mo.SpecieQuery.SpecieQueryReferenceIF getSpecie(String alias);
    public com.terraframe.mojo.query.AttributeCharIF getType();
    public com.terraframe.mojo.query.AttributeCharIF getType(String alias);

    public com.terraframe.mojo.query.BasicCondition EQ(dss.vector.solutions.entomology.MorphologicalSpecieGroup morphologicalSpecieGroup);

    public com.terraframe.mojo.query.BasicCondition NE(dss.vector.solutions.entomology.MorphologicalSpecieGroup morphologicalSpecieGroup);

  }

/**
 * Implements type safe query methods.
 * This type is used when a join is performed on this class as a reference.
 **/
  public static class MorphologicalSpecieGroupQueryReference extends com.terraframe.mojo.query.AttributeReference
 implements MorphologicalSpecieGroupQueryReferenceIF
, com.terraframe.mojo.generation.loader.Reloadable
  {
private static final long serialVersionUID = 1239572496741L;

  public MorphologicalSpecieGroupQueryReference(com.terraframe.mojo.dataaccess.MdAttributeRefDAOIF mdAttributeIF, String attributeNamespace, String definingTableName, String definingTableAlias, com.terraframe.mojo.dataaccess.MdBusinessDAOIF referenceMdBusinessIF, String referenceTableAlias, com.terraframe.mojo.query.ComponentQuery rootQuery, java.util.Set<com.terraframe.mojo.query.Join> tableJoinSet, String alias)
  {
    super(mdAttributeIF, attributeNamespace, definingTableName, definingTableAlias, referenceMdBusinessIF, referenceTableAlias, rootQuery, tableJoinSet, alias);

  }


    public com.terraframe.mojo.query.BasicCondition EQ(dss.vector.solutions.entomology.MorphologicalSpecieGroup morphologicalSpecieGroup)
    {
      return this.EQ(morphologicalSpecieGroup.getId());
    }

    public com.terraframe.mojo.query.BasicCondition NE(dss.vector.solutions.entomology.MorphologicalSpecieGroup morphologicalSpecieGroup)
    {
      return this.NE(morphologicalSpecieGroup.getId());
    }

  public dss.vector.solutions.entomology.ConcreteMosquitoCollectionQuery.ConcreteMosquitoCollectionQueryReferenceIF getCollection()
  {
    return getCollection(null);

  }
 
  public dss.vector.solutions.entomology.ConcreteMosquitoCollectionQuery.ConcreteMosquitoCollectionQueryReferenceIF getCollection(String alias)
  {
    return (dss.vector.solutions.entomology.ConcreteMosquitoCollectionQuery.ConcreteMosquitoCollectionQueryReferenceIF)this.attributeFactory("collection", "com.terraframe.mojo.system.metadata.MdAttributeReference", alias);

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
  public dss.vector.solutions.mo.IdentificationMethodQuery.IdentificationMethodQueryReferenceIF getIdentificationMethod()
  {
    return getIdentificationMethod(null);

  }
 
  public dss.vector.solutions.mo.IdentificationMethodQuery.IdentificationMethodQueryReferenceIF getIdentificationMethod(String alias)
  {
    return (dss.vector.solutions.mo.IdentificationMethodQuery.IdentificationMethodQueryReferenceIF)this.attributeFactory("identificationMethod", "com.terraframe.mojo.system.metadata.MdAttributeReference", alias);

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
  public com.terraframe.mojo.system.ActorQuery.ActorQueryReferenceIF getOwner()
  {
    return getOwner(null);

  }
 
  public com.terraframe.mojo.system.ActorQuery.ActorQueryReferenceIF getOwner(String alias)
  {
    return (com.terraframe.mojo.system.ActorQuery.ActorQueryReferenceIF)this.attributeFactory("owner", "com.terraframe.mojo.system.metadata.MdAttributeReference", alias);

  }
  public com.terraframe.mojo.query.AttributeIntegerIF getQuantity()
  {
    return getQuantity(null);

  }
 
  public com.terraframe.mojo.query.AttributeIntegerIF getQuantity(String alias)
  {
    return (com.terraframe.mojo.query.AttributeIntegerIF)this.attributeFactory("quantity", "com.terraframe.mojo.system.metadata.MdAttributeInteger", alias);

  }
  public com.terraframe.mojo.query.AttributeIntegerIF getQuantityFemale()
  {
    return getQuantityFemale(null);

  }
 
  public com.terraframe.mojo.query.AttributeIntegerIF getQuantityFemale(String alias)
  {
    return (com.terraframe.mojo.query.AttributeIntegerIF)this.attributeFactory("quantityFemale", "com.terraframe.mojo.system.metadata.MdAttributeInteger", alias);

  }
  public com.terraframe.mojo.query.AttributeIntegerIF getQuantityMale()
  {
    return getQuantityMale(null);

  }
 
  public com.terraframe.mojo.query.AttributeIntegerIF getQuantityMale(String alias)
  {
    return (com.terraframe.mojo.query.AttributeIntegerIF)this.attributeFactory("quantityMale", "com.terraframe.mojo.system.metadata.MdAttributeInteger", alias);

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
  public dss.vector.solutions.mo.SpecieQuery.SpecieQueryReferenceIF getSpecie()
  {
    return getSpecie(null);

  }
 
  public dss.vector.solutions.mo.SpecieQuery.SpecieQueryReferenceIF getSpecie(String alias)
  {
    return (dss.vector.solutions.mo.SpecieQuery.SpecieQueryReferenceIF)this.attributeFactory("specie", "com.terraframe.mojo.system.metadata.MdAttributeReference", alias);

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
    
    if (name.equals("collection")) 
    {
       return new dss.vector.solutions.entomology.ConcreteMosquitoCollectionQuery.ConcreteMosquitoCollectionQueryReference((com.terraframe.mojo.dataaccess.MdAttributeRefDAOIF)mdAttributeIF, attributeNamespace, definingTableName, definingTableAlias, referenceMdBusinessIF, referenceTableAlias, rootQuery, tableJoinSet, userDefinedAlias);
    }
    else if (name.equals("createdBy")) 
    {
       return new com.terraframe.mojo.system.SingleActorQuery.SingleActorQueryReference((com.terraframe.mojo.dataaccess.MdAttributeRefDAOIF)mdAttributeIF, attributeNamespace, definingTableName, definingTableAlias, referenceMdBusinessIF, referenceTableAlias, rootQuery, tableJoinSet, userDefinedAlias);
    }
    else if (name.equals("entityDomain")) 
    {
       return new com.terraframe.mojo.system.metadata.MdDomainQuery.MdDomainQueryReference((com.terraframe.mojo.dataaccess.MdAttributeRefDAOIF)mdAttributeIF, attributeNamespace, definingTableName, definingTableAlias, referenceMdBusinessIF, referenceTableAlias, rootQuery, tableJoinSet, userDefinedAlias);
    }
    else if (name.equals("identificationMethod")) 
    {
       return new dss.vector.solutions.mo.IdentificationMethodQuery.IdentificationMethodQueryReference((com.terraframe.mojo.dataaccess.MdAttributeRefDAOIF)mdAttributeIF, attributeNamespace, definingTableName, definingTableAlias, referenceMdBusinessIF, referenceTableAlias, rootQuery, tableJoinSet, userDefinedAlias);
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
    else if (name.equals("specie")) 
    {
       return new dss.vector.solutions.mo.SpecieQuery.SpecieQueryReference((com.terraframe.mojo.dataaccess.MdAttributeRefDAOIF)mdAttributeIF, attributeNamespace, definingTableName, definingTableAlias, referenceMdBusinessIF, referenceTableAlias, rootQuery, tableJoinSet, userDefinedAlias);
    }
    else 
    {
      String error = "Attribute type ["+mdAttributeIF.getType()+"] is invalid.";
      throw new com.terraframe.mojo.query.QueryException(error);
    }
  }

  }
}
