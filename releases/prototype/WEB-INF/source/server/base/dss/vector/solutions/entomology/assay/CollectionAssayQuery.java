package dss.vector.solutions.entomology.assay;

/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to CollectionAssay.java
 *
 * @author Autogenerated by TerraFrame
 */
public  class CollectionAssayQuery extends dss.vector.solutions.entomology.assay.AbstractAssayQuery
 implements com.terraframe.mojo.generation.loader.Reloadable
{
private static final long serialVersionUID = 1237311439606L;

  public CollectionAssayQuery(com.terraframe.mojo.query.QueryFactory componentQueryFactory)
  {
    super(componentQueryFactory);
    if (this.getComponentQuery() == null)
    {
      com.terraframe.mojo.business.BusinessQuery businessQuery = componentQueryFactory.businessQuery(this.getClassType());

       this.setBusinessQuery(businessQuery);
    }
  }

  public String getClassType()
  {
    return "dss.vector.solutions.entomology.assay.CollectionAssay";
  }
  public com.terraframe.mojo.query.AttributeIntegerIF getAmount()
  {
    return getAmount(null);

  }
 
  public com.terraframe.mojo.query.AttributeIntegerIF getAmount(String alias)
  {
    return (com.terraframe.mojo.query.AttributeIntegerIF)this.getComponentQuery().attributeFactory(dss.vector.solutions.entomology.assay.CollectionAssay.AMOUNT, "com.terraframe.mojo.system.metadata.MdAttributeInteger", alias);

  }
  public dss.vector.solutions.entomology.MosquitoCollectionQuery.MosquitoCollectionQueryReferenceIF getCollection()
  {
    return getCollection(null);

  }
 
  public dss.vector.solutions.entomology.MosquitoCollectionQuery.MosquitoCollectionQueryReferenceIF getCollection(String alias)
  {

    com.terraframe.mojo.dataaccess.MdAttributeDAOIF mdAttributeIF = this.getComponentQuery().getMdAttributeROfromMap("collection");

    return (dss.vector.solutions.entomology.MosquitoCollectionQuery.MosquitoCollectionQueryReferenceIF)this.getComponentQuery().internalAttributeFactory(dss.vector.solutions.entomology.assay.CollectionAssay.COLLECTION, mdAttributeIF, this, alias);

  }
  public com.terraframe.mojo.query.AttributeIntegerIF getExposureTime()
  {
    return getExposureTime(null);

  }
 
  public com.terraframe.mojo.query.AttributeIntegerIF getExposureTime(String alias)
  {
    return (com.terraframe.mojo.query.AttributeIntegerIF)this.getComponentQuery().attributeFactory(dss.vector.solutions.entomology.assay.CollectionAssay.EXPOSURETIME, "com.terraframe.mojo.system.metadata.MdAttributeInteger", alias);

  }
  public dss.vector.solutions.mo.GenerationQuery.GenerationQueryReferenceIF getGeneration()
  {
    return getGeneration(null);

  }
 
  public dss.vector.solutions.mo.GenerationQuery.GenerationQueryReferenceIF getGeneration(String alias)
  {

    com.terraframe.mojo.dataaccess.MdAttributeDAOIF mdAttributeIF = this.getComponentQuery().getMdAttributeROfromMap("generation");

    return (dss.vector.solutions.mo.GenerationQuery.GenerationQueryReferenceIF)this.getComponentQuery().internalAttributeFactory(dss.vector.solutions.entomology.assay.CollectionAssay.GENERATION, mdAttributeIF, this, alias);

  }
  public dss.vector.solutions.mo.IdentificationMethodQuery.IdentificationMethodQueryReferenceIF getIdentificationMethod()
  {
    return getIdentificationMethod(null);

  }
 
  public dss.vector.solutions.mo.IdentificationMethodQuery.IdentificationMethodQueryReferenceIF getIdentificationMethod(String alias)
  {

    com.terraframe.mojo.dataaccess.MdAttributeDAOIF mdAttributeIF = this.getComponentQuery().getMdAttributeROfromMap("identificationMethod");

    return (dss.vector.solutions.mo.IdentificationMethodQuery.IdentificationMethodQueryReferenceIF)this.getComponentQuery().internalAttributeFactory(dss.vector.solutions.entomology.assay.CollectionAssay.IDENTIFICATIONMETHOD, mdAttributeIF, this, alias);

  }
  public com.terraframe.mojo.query.AttributeIntegerIF getIntervalTime()
  {
    return getIntervalTime(null);

  }
 
  public com.terraframe.mojo.query.AttributeIntegerIF getIntervalTime(String alias)
  {
    return (com.terraframe.mojo.query.AttributeIntegerIF)this.getComponentQuery().attributeFactory(dss.vector.solutions.entomology.assay.CollectionAssay.INTERVALTIME, "com.terraframe.mojo.system.metadata.MdAttributeInteger", alias);

  }
  public com.terraframe.mojo.query.AttributeBooleanIF getIsofemale()
  {
    return getIsofemale(null);

  }
 
  public com.terraframe.mojo.query.AttributeBooleanIF getIsofemale(String alias)
  {
    return (com.terraframe.mojo.query.AttributeBooleanIF)this.getComponentQuery().attributeFactory(dss.vector.solutions.entomology.assay.CollectionAssay.ISOFEMALE, "com.terraframe.mojo.system.metadata.MdAttributeBoolean", alias);

  }
  public com.terraframe.mojo.query.AttributeIntegerIF getQuantityTested()
  {
    return getQuantityTested(null);

  }
 
  public com.terraframe.mojo.query.AttributeIntegerIF getQuantityTested(String alias)
  {
    return (com.terraframe.mojo.query.AttributeIntegerIF)this.getComponentQuery().attributeFactory(dss.vector.solutions.entomology.assay.CollectionAssay.QUANTITYTESTED, "com.terraframe.mojo.system.metadata.MdAttributeInteger", alias);

  }
  public dss.vector.solutions.mo.ResistanceMethodologyQuery.ResistanceMethodologyQueryReferenceIF getTestMethod()
  {
    return getTestMethod(null);

  }
 
  public dss.vector.solutions.mo.ResistanceMethodologyQuery.ResistanceMethodologyQueryReferenceIF getTestMethod(String alias)
  {

    com.terraframe.mojo.dataaccess.MdAttributeDAOIF mdAttributeIF = this.getComponentQuery().getMdAttributeROfromMap("testMethod");

    return (dss.vector.solutions.mo.ResistanceMethodologyQuery.ResistanceMethodologyQueryReferenceIF)this.getComponentQuery().internalAttributeFactory(dss.vector.solutions.entomology.assay.CollectionAssay.TESTMETHOD, mdAttributeIF, this, alias);

  }
  public dss.vector.solutions.entomology.assay.UnitMasterQuery.UnitQueryIF getUnits()
  {
    return getUnits(null);

  }
 
  public dss.vector.solutions.entomology.assay.UnitMasterQuery.UnitQueryIF getUnits(String alias)
  {

    com.terraframe.mojo.dataaccess.MdAttributeDAOIF mdAttributeIF = this.getComponentQuery().getMdAttributeROfromMap("units");

    return (dss.vector.solutions.entomology.assay.UnitMasterQuery.UnitQueryIF)this.getComponentQuery().internalAttributeFactory("units", mdAttributeIF, this, alias);

  }
  protected com.terraframe.mojo.query.AttributeReference referenceFactory( com.terraframe.mojo.dataaccess.MdAttributeRefDAOIF mdAttributeIF, String attributeNamespace, String definingTableName, String definingTableAlias,  com.terraframe.mojo.dataaccess.MdBusinessDAOIF referenceMdBusinessIF, String referenceTableAlias, com.terraframe.mojo.query.ComponentQuery rootQuery, java.util.Set<com.terraframe.mojo.query.Join> tableJoinSet, String userDefinedAlias)
  {
    String name = mdAttributeIF.definesAttribute();
    
    if (name.equals("collection")) 
    {
       return new dss.vector.solutions.entomology.MosquitoCollectionQuery.MosquitoCollectionQueryReference((com.terraframe.mojo.dataaccess.MdAttributeRefDAOIF)mdAttributeIF, attributeNamespace, definingTableName, definingTableAlias, referenceMdBusinessIF, referenceTableAlias, rootQuery, tableJoinSet, userDefinedAlias);
    }
    else if (name.equals("generation")) 
    {
       return new dss.vector.solutions.mo.GenerationQuery.GenerationQueryReference((com.terraframe.mojo.dataaccess.MdAttributeRefDAOIF)mdAttributeIF, attributeNamespace, definingTableName, definingTableAlias, referenceMdBusinessIF, referenceTableAlias, rootQuery, tableJoinSet, userDefinedAlias);
    }
    else if (name.equals("identificationMethod")) 
    {
       return new dss.vector.solutions.mo.IdentificationMethodQuery.IdentificationMethodQueryReference((com.terraframe.mojo.dataaccess.MdAttributeRefDAOIF)mdAttributeIF, attributeNamespace, definingTableName, definingTableAlias, referenceMdBusinessIF, referenceTableAlias, rootQuery, tableJoinSet, userDefinedAlias);
    }
    else if (name.equals("testMethod")) 
    {
       return new dss.vector.solutions.mo.ResistanceMethodologyQuery.ResistanceMethodologyQueryReference((com.terraframe.mojo.dataaccess.MdAttributeRefDAOIF)mdAttributeIF, attributeNamespace, definingTableName, definingTableAlias, referenceMdBusinessIF, referenceTableAlias, rootQuery, tableJoinSet, userDefinedAlias);
    }
    else 
    {
      return super.referenceFactory(mdAttributeIF, attributeNamespace, definingTableName, definingTableAlias, referenceMdBusinessIF, referenceTableAlias, rootQuery, tableJoinSet, userDefinedAlias);
    }
  }

  protected com.terraframe.mojo.query.AttributeEnumeration enumerationFactory( com.terraframe.mojo.dataaccess.MdAttributeEnumerationDAOIF mdAttributeIF, String attributeNamespace, String definingTableName, String definingTableAlias,  String mdEnumerationTableName, com.terraframe.mojo.dataaccess.MdBusinessDAOIF masterListMdBusinessIF, String masterListTalbeAlias, com.terraframe.mojo.query.ComponentQuery rootQuery, java.util.Set<com.terraframe.mojo.query.Join> tableJoinSet, String userDefinedAlias)
  {
    String name = mdAttributeIF.definesAttribute();
    
    if (name.equals("units")) 
    {
       return new dss.vector.solutions.entomology.assay.UnitMasterQuery.UnitQuery((com.terraframe.mojo.dataaccess.MdAttributeEnumerationDAOIF)mdAttributeIF,  attributeNamespace, definingTableName, definingTableAlias, mdEnumerationTableName, masterListMdBusinessIF, masterListTalbeAlias, rootQuery, tableJoinSet, userDefinedAlias);
    }
    else 
    {
      return super.enumerationFactory(mdAttributeIF, attributeNamespace, definingTableName, definingTableAlias, mdEnumerationTableName, masterListMdBusinessIF, masterListTalbeAlias, rootQuery, tableJoinSet, userDefinedAlias);
    }
  }

  /**  
   * Returns an iterator of Business objects that match the query criteria specified
   * on this query object. 
   * @return iterator of Business objects that match the query criteria specified
   * on this query object.
   */
  public com.terraframe.mojo.query.OIterator<? extends CollectionAssay> getIterator()
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
    return new com.terraframe.mojo.business.BusinessIterator<CollectionAssay>(this.getComponentQuery().getMdEntityIF(), columnInfoMap, results);
  }


/**
 * Interface that masks all type unsafe query methods and defines all type safe methods.
 * This type is used when a join is performed on this class as a reference.
 **/
  public interface CollectionAssayQueryReferenceIF extends com.terraframe.mojo.generation.loader.Reloadable, dss.vector.solutions.entomology.assay.AbstractAssayQuery.AbstractAssayQueryReferenceIF
  {

    public com.terraframe.mojo.query.AttributeIntegerIF getAmount();
    public com.terraframe.mojo.query.AttributeIntegerIF getAmount(String alias);
    public dss.vector.solutions.entomology.MosquitoCollectionQuery.MosquitoCollectionQueryReferenceIF getCollection();
    public dss.vector.solutions.entomology.MosquitoCollectionQuery.MosquitoCollectionQueryReferenceIF getCollection(String alias);
    public com.terraframe.mojo.query.AttributeIntegerIF getExposureTime();
    public com.terraframe.mojo.query.AttributeIntegerIF getExposureTime(String alias);
    public dss.vector.solutions.mo.GenerationQuery.GenerationQueryReferenceIF getGeneration();
    public dss.vector.solutions.mo.GenerationQuery.GenerationQueryReferenceIF getGeneration(String alias);
    public dss.vector.solutions.mo.IdentificationMethodQuery.IdentificationMethodQueryReferenceIF getIdentificationMethod();
    public dss.vector.solutions.mo.IdentificationMethodQuery.IdentificationMethodQueryReferenceIF getIdentificationMethod(String alias);
    public com.terraframe.mojo.query.AttributeIntegerIF getIntervalTime();
    public com.terraframe.mojo.query.AttributeIntegerIF getIntervalTime(String alias);
    public com.terraframe.mojo.query.AttributeBooleanIF getIsofemale();
    public com.terraframe.mojo.query.AttributeBooleanIF getIsofemale(String alias);
    public com.terraframe.mojo.query.AttributeIntegerIF getQuantityTested();
    public com.terraframe.mojo.query.AttributeIntegerIF getQuantityTested(String alias);
    public dss.vector.solutions.mo.ResistanceMethodologyQuery.ResistanceMethodologyQueryReferenceIF getTestMethod();
    public dss.vector.solutions.mo.ResistanceMethodologyQuery.ResistanceMethodologyQueryReferenceIF getTestMethod(String alias);
  public dss.vector.solutions.entomology.assay.UnitMasterQuery.UnitQueryIF getUnits();
  public dss.vector.solutions.entomology.assay.UnitMasterQuery.UnitQueryIF getUnits(String alias);

    public com.terraframe.mojo.query.BasicCondition EQ(dss.vector.solutions.entomology.assay.CollectionAssay collectionAssay);

    public com.terraframe.mojo.query.BasicCondition NE(dss.vector.solutions.entomology.assay.CollectionAssay collectionAssay);

  }

/**
 * Implements type safe query methods.
 * This type is used when a join is performed on this class as a reference.
 **/
  public static class CollectionAssayQueryReference extends dss.vector.solutions.entomology.assay.AbstractAssayQuery.AbstractAssayQueryReference
 implements CollectionAssayQueryReferenceIF
, com.terraframe.mojo.generation.loader.Reloadable
  {
private static final long serialVersionUID = 1237311439713L;

  public CollectionAssayQueryReference(com.terraframe.mojo.dataaccess.MdAttributeRefDAOIF mdAttributeIF, String attributeNamespace, String definingTableName, String definingTableAlias, com.terraframe.mojo.dataaccess.MdBusinessDAOIF referenceMdBusinessIF, String referenceTableAlias, com.terraframe.mojo.query.ComponentQuery rootQuery, java.util.Set<com.terraframe.mojo.query.Join> tableJoinSet, String alias)
  {
    super(mdAttributeIF, attributeNamespace, definingTableName, definingTableAlias, referenceMdBusinessIF, referenceTableAlias, rootQuery, tableJoinSet, alias);

  }


    public com.terraframe.mojo.query.BasicCondition EQ(dss.vector.solutions.entomology.assay.CollectionAssay collectionAssay)
    {
      return this.EQ(collectionAssay.getId());
    }

    public com.terraframe.mojo.query.BasicCondition NE(dss.vector.solutions.entomology.assay.CollectionAssay collectionAssay)
    {
      return this.NE(collectionAssay.getId());
    }

  public com.terraframe.mojo.query.AttributeIntegerIF getAmount()
  {
    return getAmount(null);

  }
 
  public com.terraframe.mojo.query.AttributeIntegerIF getAmount(String alias)
  {
    return (com.terraframe.mojo.query.AttributeIntegerIF)this.attributeFactory("amount", "com.terraframe.mojo.system.metadata.MdAttributeInteger", alias);

  }
  public dss.vector.solutions.entomology.MosquitoCollectionQuery.MosquitoCollectionQueryReferenceIF getCollection()
  {
    return getCollection(null);

  }
 
  public dss.vector.solutions.entomology.MosquitoCollectionQuery.MosquitoCollectionQueryReferenceIF getCollection(String alias)
  {
    return (dss.vector.solutions.entomology.MosquitoCollectionQuery.MosquitoCollectionQueryReferenceIF)this.attributeFactory("collection", "com.terraframe.mojo.system.metadata.MdAttributeReference", alias);

  }
  public com.terraframe.mojo.query.AttributeIntegerIF getExposureTime()
  {
    return getExposureTime(null);

  }
 
  public com.terraframe.mojo.query.AttributeIntegerIF getExposureTime(String alias)
  {
    return (com.terraframe.mojo.query.AttributeIntegerIF)this.attributeFactory("exposureTime", "com.terraframe.mojo.system.metadata.MdAttributeInteger", alias);

  }
  public dss.vector.solutions.mo.GenerationQuery.GenerationQueryReferenceIF getGeneration()
  {
    return getGeneration(null);

  }
 
  public dss.vector.solutions.mo.GenerationQuery.GenerationQueryReferenceIF getGeneration(String alias)
  {
    return (dss.vector.solutions.mo.GenerationQuery.GenerationQueryReferenceIF)this.attributeFactory("generation", "com.terraframe.mojo.system.metadata.MdAttributeReference", alias);

  }
  public dss.vector.solutions.mo.IdentificationMethodQuery.IdentificationMethodQueryReferenceIF getIdentificationMethod()
  {
    return getIdentificationMethod(null);

  }
 
  public dss.vector.solutions.mo.IdentificationMethodQuery.IdentificationMethodQueryReferenceIF getIdentificationMethod(String alias)
  {
    return (dss.vector.solutions.mo.IdentificationMethodQuery.IdentificationMethodQueryReferenceIF)this.attributeFactory("identificationMethod", "com.terraframe.mojo.system.metadata.MdAttributeReference", alias);

  }
  public com.terraframe.mojo.query.AttributeIntegerIF getIntervalTime()
  {
    return getIntervalTime(null);

  }
 
  public com.terraframe.mojo.query.AttributeIntegerIF getIntervalTime(String alias)
  {
    return (com.terraframe.mojo.query.AttributeIntegerIF)this.attributeFactory("intervalTime", "com.terraframe.mojo.system.metadata.MdAttributeInteger", alias);

  }
  public com.terraframe.mojo.query.AttributeBooleanIF getIsofemale()
  {
    return getIsofemale(null);

  }
 
  public com.terraframe.mojo.query.AttributeBooleanIF getIsofemale(String alias)
  {
    return (com.terraframe.mojo.query.AttributeBooleanIF)this.attributeFactory("isofemale", "com.terraframe.mojo.system.metadata.MdAttributeBoolean", alias);

  }
  public com.terraframe.mojo.query.AttributeIntegerIF getQuantityTested()
  {
    return getQuantityTested(null);

  }
 
  public com.terraframe.mojo.query.AttributeIntegerIF getQuantityTested(String alias)
  {
    return (com.terraframe.mojo.query.AttributeIntegerIF)this.attributeFactory("quantityTested", "com.terraframe.mojo.system.metadata.MdAttributeInteger", alias);

  }
  public dss.vector.solutions.mo.ResistanceMethodologyQuery.ResistanceMethodologyQueryReferenceIF getTestMethod()
  {
    return getTestMethod(null);

  }
 
  public dss.vector.solutions.mo.ResistanceMethodologyQuery.ResistanceMethodologyQueryReferenceIF getTestMethod(String alias)
  {
    return (dss.vector.solutions.mo.ResistanceMethodologyQuery.ResistanceMethodologyQueryReferenceIF)this.attributeFactory("testMethod", "com.terraframe.mojo.system.metadata.MdAttributeReference", alias);

  }
  public dss.vector.solutions.entomology.assay.UnitMasterQuery.UnitQueryIF getUnits()
  {
    return getUnits(null);

  }
 
  public dss.vector.solutions.entomology.assay.UnitMasterQuery.UnitQueryIF getUnits(String alias)
  {
    return (dss.vector.solutions.entomology.assay.UnitMasterQuery.UnitQueryIF)this.attributeFactory("units", "com.terraframe.mojo.system.metadata.MdAttributeEnumeration", alias);

  }
  protected com.terraframe.mojo.query.AttributeReference referenceFactory( com.terraframe.mojo.dataaccess.MdAttributeRefDAOIF mdAttributeIF, String attributeNamespace, String definingTableName, String definingTableAlias,  com.terraframe.mojo.dataaccess.MdBusinessDAOIF referenceMdBusinessIF, String referenceTableAlias, com.terraframe.mojo.query.ComponentQuery rootQuery, java.util.Set<com.terraframe.mojo.query.Join> tableJoinSet, String userDefinedAlias)
  {
    String name = mdAttributeIF.definesAttribute();
    
    if (name.equals("collection")) 
    {
       return new dss.vector.solutions.entomology.MosquitoCollectionQuery.MosquitoCollectionQueryReference((com.terraframe.mojo.dataaccess.MdAttributeRefDAOIF)mdAttributeIF, attributeNamespace, definingTableName, definingTableAlias, referenceMdBusinessIF, referenceTableAlias, rootQuery, tableJoinSet, userDefinedAlias);
    }
    else if (name.equals("generation")) 
    {
       return new dss.vector.solutions.mo.GenerationQuery.GenerationQueryReference((com.terraframe.mojo.dataaccess.MdAttributeRefDAOIF)mdAttributeIF, attributeNamespace, definingTableName, definingTableAlias, referenceMdBusinessIF, referenceTableAlias, rootQuery, tableJoinSet, userDefinedAlias);
    }
    else if (name.equals("identificationMethod")) 
    {
       return new dss.vector.solutions.mo.IdentificationMethodQuery.IdentificationMethodQueryReference((com.terraframe.mojo.dataaccess.MdAttributeRefDAOIF)mdAttributeIF, attributeNamespace, definingTableName, definingTableAlias, referenceMdBusinessIF, referenceTableAlias, rootQuery, tableJoinSet, userDefinedAlias);
    }
    else if (name.equals("testMethod")) 
    {
       return new dss.vector.solutions.mo.ResistanceMethodologyQuery.ResistanceMethodologyQueryReference((com.terraframe.mojo.dataaccess.MdAttributeRefDAOIF)mdAttributeIF, attributeNamespace, definingTableName, definingTableAlias, referenceMdBusinessIF, referenceTableAlias, rootQuery, tableJoinSet, userDefinedAlias);
    }
    else 
    {
      return super.referenceFactory(mdAttributeIF, attributeNamespace, definingTableName, definingTableAlias, referenceMdBusinessIF, referenceTableAlias, rootQuery, tableJoinSet, userDefinedAlias);
    }
  }

  protected com.terraframe.mojo.query.AttributeEnumeration enumerationFactory( com.terraframe.mojo.dataaccess.MdAttributeEnumerationDAOIF mdAttributeIF, String attributeNamespace, String definingTableName, String definingTableAlias,  String mdEnumerationTableName, com.terraframe.mojo.dataaccess.MdBusinessDAOIF masterListMdBusinessIF, String masterListTalbeAlias, com.terraframe.mojo.query.ComponentQuery rootQuery, java.util.Set<com.terraframe.mojo.query.Join> tableJoinSet, String userDefinedAlias)
  {
    String name = mdAttributeIF.definesAttribute();
    
    if (name.equals("units")) 
    {
       return new dss.vector.solutions.entomology.assay.UnitMasterQuery.UnitQuery((com.terraframe.mojo.dataaccess.MdAttributeEnumerationDAOIF)mdAttributeIF,  attributeNamespace, definingTableName, definingTableAlias, mdEnumerationTableName, masterListMdBusinessIF, masterListTalbeAlias, rootQuery, tableJoinSet, userDefinedAlias);
    }
    else 
    {
      return super.enumerationFactory(mdAttributeIF, attributeNamespace, definingTableName, definingTableAlias, mdEnumerationTableName, masterListMdBusinessIF, masterListTalbeAlias, rootQuery, tableJoinSet, userDefinedAlias);
    }
  }

  }
}
