package dss.vector.solutions.entomology.assay;

/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to EfficacyAssay.java
 *
 * @author Autogenerated by TerraFrame
 */
public  class EfficacyAssayQuery extends dss.vector.solutions.entomology.assay.AbstractAssayQuery
 implements com.terraframe.mojo.generation.loader.Reloadable
{
private static final long serialVersionUID = 1237311446721L;

  public EfficacyAssayQuery(com.terraframe.mojo.query.QueryFactory componentQueryFactory)
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
    return "dss.vector.solutions.entomology.assay.EfficacyAssay";
  }
  public dss.vector.solutions.entomology.assay.AdultAgeRangeQuery.AdultAgeRangeQueryStructIF getAgeRange()
  {
    return getAgeRange(null);

  }
 
  public dss.vector.solutions.entomology.assay.AdultAgeRangeQuery.AdultAgeRangeQueryStructIF getAgeRange(String alias)
  {

    com.terraframe.mojo.dataaccess.MdAttributeDAOIF mdAttributeIF = this.getComponentQuery().getMdAttributeROfromMap(dss.vector.solutions.entomology.assay.EfficacyAssay.AGERANGE);

    return (dss.vector.solutions.entomology.assay.AdultAgeRangeQuery.AdultAgeRangeQueryStructIF)this.getComponentQuery().internalAttributeFactory(dss.vector.solutions.entomology.assay.EfficacyAssay.AGERANGE, mdAttributeIF, this, alias);

  }
  public com.terraframe.mojo.query.AttributeCharIF getColonyName()
  {
    return getColonyName(null);

  }
 
  public com.terraframe.mojo.query.AttributeCharIF getColonyName(String alias)
  {
    return (com.terraframe.mojo.query.AttributeCharIF)this.getComponentQuery().attributeFactory(dss.vector.solutions.entomology.assay.EfficacyAssay.COLONYNAME, "com.terraframe.mojo.system.metadata.MdAttributeCharacter", alias);

  }
  public com.terraframe.mojo.query.AttributeIntegerIF getExposureTime()
  {
    return getExposureTime(null);

  }
 
  public com.terraframe.mojo.query.AttributeIntegerIF getExposureTime(String alias)
  {
    return (com.terraframe.mojo.query.AttributeIntegerIF)this.getComponentQuery().attributeFactory(dss.vector.solutions.entomology.assay.EfficacyAssay.EXPOSURETIME, "com.terraframe.mojo.system.metadata.MdAttributeInteger", alias);

  }
  public com.terraframe.mojo.query.AttributeIntegerIF getFed()
  {
    return getFed(null);

  }
 
  public com.terraframe.mojo.query.AttributeIntegerIF getFed(String alias)
  {
    return (com.terraframe.mojo.query.AttributeIntegerIF)this.getComponentQuery().attributeFactory(dss.vector.solutions.entomology.assay.EfficacyAssay.FED, "com.terraframe.mojo.system.metadata.MdAttributeInteger", alias);

  }
  public dss.vector.solutions.geo.generated.GeoEntityQuery.GeoEntityQueryReferenceIF getGeoEntity()
  {
    return getGeoEntity(null);

  }
 
  public dss.vector.solutions.geo.generated.GeoEntityQuery.GeoEntityQueryReferenceIF getGeoEntity(String alias)
  {

    com.terraframe.mojo.dataaccess.MdAttributeDAOIF mdAttributeIF = this.getComponentQuery().getMdAttributeROfromMap("geoEntity");

    return (dss.vector.solutions.geo.generated.GeoEntityQuery.GeoEntityQueryReferenceIF)this.getComponentQuery().internalAttributeFactory(dss.vector.solutions.entomology.assay.EfficacyAssay.GEOENTITY, mdAttributeIF, this, alias);

  }
  public com.terraframe.mojo.query.AttributeIntegerIF getGravid()
  {
    return getGravid(null);

  }
 
  public com.terraframe.mojo.query.AttributeIntegerIF getGravid(String alias)
  {
    return (com.terraframe.mojo.query.AttributeIntegerIF)this.getComponentQuery().attributeFactory(dss.vector.solutions.entomology.assay.EfficacyAssay.GRAVID, "com.terraframe.mojo.system.metadata.MdAttributeInteger", alias);

  }
  public com.terraframe.mojo.query.AttributeIntegerIF getHoldingTime()
  {
    return getHoldingTime(null);

  }
 
  public com.terraframe.mojo.query.AttributeIntegerIF getHoldingTime(String alias)
  {
    return (com.terraframe.mojo.query.AttributeIntegerIF)this.getComponentQuery().attributeFactory(dss.vector.solutions.entomology.assay.EfficacyAssay.HOLDINGTIME, "com.terraframe.mojo.system.metadata.MdAttributeInteger", alias);

  }
  public com.terraframe.mojo.query.AttributeFloatIF getMortality()
  {
    return getMortality(null);

  }
 
  public com.terraframe.mojo.query.AttributeFloatIF getMortality(String alias)
  {
    return (com.terraframe.mojo.query.AttributeFloatIF)this.getComponentQuery().attributeFactory(dss.vector.solutions.entomology.assay.EfficacyAssay.MORTALITY, "com.terraframe.mojo.system.metadata.MdAttributeFloat", alias);

  }
  public com.terraframe.mojo.query.AttributeIntegerIF getQuantityDead()
  {
    return getQuantityDead(null);

  }
 
  public com.terraframe.mojo.query.AttributeIntegerIF getQuantityDead(String alias)
  {
    return (com.terraframe.mojo.query.AttributeIntegerIF)this.getComponentQuery().attributeFactory(dss.vector.solutions.entomology.assay.EfficacyAssay.QUANTITYDEAD, "com.terraframe.mojo.system.metadata.MdAttributeInteger", alias);

  }
  public com.terraframe.mojo.query.AttributeIntegerIF getQuantityLive()
  {
    return getQuantityLive(null);

  }
 
  public com.terraframe.mojo.query.AttributeIntegerIF getQuantityLive(String alias)
  {
    return (com.terraframe.mojo.query.AttributeIntegerIF)this.getComponentQuery().attributeFactory(dss.vector.solutions.entomology.assay.EfficacyAssay.QUANTITYLIVE, "com.terraframe.mojo.system.metadata.MdAttributeInteger", alias);

  }
  public com.terraframe.mojo.query.AttributeIntegerIF getQuantityTested()
  {
    return getQuantityTested(null);

  }
 
  public com.terraframe.mojo.query.AttributeIntegerIF getQuantityTested(String alias)
  {
    return (com.terraframe.mojo.query.AttributeIntegerIF)this.getComponentQuery().attributeFactory(dss.vector.solutions.entomology.assay.EfficacyAssay.QUANTITYTESTED, "com.terraframe.mojo.system.metadata.MdAttributeInteger", alias);

  }
  public dss.vector.solutions.entomology.SexMasterQuery.AssaySexQueryIF getSex()
  {
    return getSex(null);

  }
 
  public dss.vector.solutions.entomology.SexMasterQuery.AssaySexQueryIF getSex(String alias)
  {

    com.terraframe.mojo.dataaccess.MdAttributeDAOIF mdAttributeIF = this.getComponentQuery().getMdAttributeROfromMap("sex");

    return (dss.vector.solutions.entomology.SexMasterQuery.AssaySexQueryIF)this.getComponentQuery().internalAttributeFactory("sex", mdAttributeIF, this, alias);

  }
  public dss.vector.solutions.SurfacePositionMasterQuery.SurfacePositionQueryIF getSurfacePostion()
  {
    return getSurfacePostion(null);

  }
 
  public dss.vector.solutions.SurfacePositionMasterQuery.SurfacePositionQueryIF getSurfacePostion(String alias)
  {

    com.terraframe.mojo.dataaccess.MdAttributeDAOIF mdAttributeIF = this.getComponentQuery().getMdAttributeROfromMap("surfacePostion");

    return (dss.vector.solutions.SurfacePositionMasterQuery.SurfacePositionQueryIF)this.getComponentQuery().internalAttributeFactory("surfacePostion", mdAttributeIF, this, alias);

  }
  public dss.vector.solutions.mo.ResistanceMethodologyQuery.ResistanceMethodologyQueryReferenceIF getTestMethod()
  {
    return getTestMethod(null);

  }
 
  public dss.vector.solutions.mo.ResistanceMethodologyQuery.ResistanceMethodologyQueryReferenceIF getTestMethod(String alias)
  {

    com.terraframe.mojo.dataaccess.MdAttributeDAOIF mdAttributeIF = this.getComponentQuery().getMdAttributeROfromMap("testMethod");

    return (dss.vector.solutions.mo.ResistanceMethodologyQuery.ResistanceMethodologyQueryReferenceIF)this.getComponentQuery().internalAttributeFactory(dss.vector.solutions.entomology.assay.EfficacyAssay.TESTMETHOD, mdAttributeIF, this, alias);

  }
  protected com.terraframe.mojo.query.AttributeReference referenceFactory( com.terraframe.mojo.dataaccess.MdAttributeRefDAOIF mdAttributeIF, String attributeNamespace, String definingTableName, String definingTableAlias,  com.terraframe.mojo.dataaccess.MdBusinessDAOIF referenceMdBusinessIF, String referenceTableAlias, com.terraframe.mojo.query.ComponentQuery rootQuery, java.util.Set<com.terraframe.mojo.query.Join> tableJoinSet, String userDefinedAlias)
  {
    String name = mdAttributeIF.definesAttribute();
    
    if (name.equals("geoEntity")) 
    {
       return new dss.vector.solutions.geo.generated.GeoEntityQuery.GeoEntityQueryReference((com.terraframe.mojo.dataaccess.MdAttributeRefDAOIF)mdAttributeIF, attributeNamespace, definingTableName, definingTableAlias, referenceMdBusinessIF, referenceTableAlias, rootQuery, tableJoinSet, userDefinedAlias);
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

  protected com.terraframe.mojo.query.AttributeStruct structFactory( com.terraframe.mojo.dataaccess.MdAttributeStructDAOIF mdAttributeIF, String attributeNamespace, String definingTableName, String definingTableAlias,  com.terraframe.mojo.dataaccess.MdStructDAOIF mdStructIF, String structTableAlias, com.terraframe.mojo.query.ComponentQuery rootQuery, java.util.Set<com.terraframe.mojo.query.Join> tableJoinSet, String userDefinedAlias)
  {
    String name = mdAttributeIF.definesAttribute();
    
    if (name.equals("ageRange")) 
    {
       return new dss.vector.solutions.entomology.assay.AdultAgeRangeQuery.AdultAgeRangeQueryStruct((com.terraframe.mojo.dataaccess.MdAttributeStructDAOIF)mdAttributeIF,  attributeNamespace, definingTableName, definingTableAlias, mdStructIF, structTableAlias, rootQuery, tableJoinSet, userDefinedAlias);
    }
    else 
    {
      return super.structFactory(mdAttributeIF, attributeNamespace, definingTableName, definingTableAlias, mdStructIF, structTableAlias, rootQuery, tableJoinSet, userDefinedAlias);
    }
  }

  protected com.terraframe.mojo.query.AttributeEnumeration enumerationFactory( com.terraframe.mojo.dataaccess.MdAttributeEnumerationDAOIF mdAttributeIF, String attributeNamespace, String definingTableName, String definingTableAlias,  String mdEnumerationTableName, com.terraframe.mojo.dataaccess.MdBusinessDAOIF masterListMdBusinessIF, String masterListTalbeAlias, com.terraframe.mojo.query.ComponentQuery rootQuery, java.util.Set<com.terraframe.mojo.query.Join> tableJoinSet, String userDefinedAlias)
  {
    String name = mdAttributeIF.definesAttribute();
    
    if (name.equals("sex")) 
    {
       return new dss.vector.solutions.entomology.SexMasterQuery.AssaySexQuery((com.terraframe.mojo.dataaccess.MdAttributeEnumerationDAOIF)mdAttributeIF,  attributeNamespace, definingTableName, definingTableAlias, mdEnumerationTableName, masterListMdBusinessIF, masterListTalbeAlias, rootQuery, tableJoinSet, userDefinedAlias);
    }
    else if (name.equals("surfacePostion")) 
    {
       return new dss.vector.solutions.SurfacePositionMasterQuery.SurfacePositionQuery((com.terraframe.mojo.dataaccess.MdAttributeEnumerationDAOIF)mdAttributeIF,  attributeNamespace, definingTableName, definingTableAlias, mdEnumerationTableName, masterListMdBusinessIF, masterListTalbeAlias, rootQuery, tableJoinSet, userDefinedAlias);
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
  public com.terraframe.mojo.query.OIterator<? extends EfficacyAssay> getIterator()
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
    return new com.terraframe.mojo.business.BusinessIterator<EfficacyAssay>(this.getComponentQuery().getMdEntityIF(), columnInfoMap, results);
  }


/**
 * Interface that masks all type unsafe query methods and defines all type safe methods.
 * This type is used when a join is performed on this class as a reference.
 **/
  public interface EfficacyAssayQueryReferenceIF extends com.terraframe.mojo.generation.loader.Reloadable, dss.vector.solutions.entomology.assay.AbstractAssayQuery.AbstractAssayQueryReferenceIF
  {

    public dss.vector.solutions.entomology.assay.AdultAgeRangeQuery.AdultAgeRangeQueryStructIF getAgeRange();
    public dss.vector.solutions.entomology.assay.AdultAgeRangeQuery.AdultAgeRangeQueryStructIF getAgeRange(String alias);
    public com.terraframe.mojo.query.AttributeCharIF getColonyName();
    public com.terraframe.mojo.query.AttributeCharIF getColonyName(String alias);
    public com.terraframe.mojo.query.AttributeIntegerIF getExposureTime();
    public com.terraframe.mojo.query.AttributeIntegerIF getExposureTime(String alias);
    public com.terraframe.mojo.query.AttributeIntegerIF getFed();
    public com.terraframe.mojo.query.AttributeIntegerIF getFed(String alias);
    public dss.vector.solutions.geo.generated.GeoEntityQuery.GeoEntityQueryReferenceIF getGeoEntity();
    public dss.vector.solutions.geo.generated.GeoEntityQuery.GeoEntityQueryReferenceIF getGeoEntity(String alias);
    public com.terraframe.mojo.query.AttributeIntegerIF getGravid();
    public com.terraframe.mojo.query.AttributeIntegerIF getGravid(String alias);
    public com.terraframe.mojo.query.AttributeIntegerIF getHoldingTime();
    public com.terraframe.mojo.query.AttributeIntegerIF getHoldingTime(String alias);
    public com.terraframe.mojo.query.AttributeFloatIF getMortality();
    public com.terraframe.mojo.query.AttributeFloatIF getMortality(String alias);
    public com.terraframe.mojo.query.AttributeIntegerIF getQuantityDead();
    public com.terraframe.mojo.query.AttributeIntegerIF getQuantityDead(String alias);
    public com.terraframe.mojo.query.AttributeIntegerIF getQuantityLive();
    public com.terraframe.mojo.query.AttributeIntegerIF getQuantityLive(String alias);
    public com.terraframe.mojo.query.AttributeIntegerIF getQuantityTested();
    public com.terraframe.mojo.query.AttributeIntegerIF getQuantityTested(String alias);
  public dss.vector.solutions.entomology.SexMasterQuery.AssaySexQueryIF getSex();
  public dss.vector.solutions.entomology.SexMasterQuery.AssaySexQueryIF getSex(String alias);
  public dss.vector.solutions.SurfacePositionMasterQuery.SurfacePositionQueryIF getSurfacePostion();
  public dss.vector.solutions.SurfacePositionMasterQuery.SurfacePositionQueryIF getSurfacePostion(String alias);
    public dss.vector.solutions.mo.ResistanceMethodologyQuery.ResistanceMethodologyQueryReferenceIF getTestMethod();
    public dss.vector.solutions.mo.ResistanceMethodologyQuery.ResistanceMethodologyQueryReferenceIF getTestMethod(String alias);

    public com.terraframe.mojo.query.BasicCondition EQ(dss.vector.solutions.entomology.assay.EfficacyAssay efficacyAssay);

    public com.terraframe.mojo.query.BasicCondition NE(dss.vector.solutions.entomology.assay.EfficacyAssay efficacyAssay);

  }

/**
 * Implements type safe query methods.
 * This type is used when a join is performed on this class as a reference.
 **/
  public static class EfficacyAssayQueryReference extends dss.vector.solutions.entomology.assay.AbstractAssayQuery.AbstractAssayQueryReference
 implements EfficacyAssayQueryReferenceIF
, com.terraframe.mojo.generation.loader.Reloadable
  {
private static final long serialVersionUID = 1237311446828L;

  public EfficacyAssayQueryReference(com.terraframe.mojo.dataaccess.MdAttributeRefDAOIF mdAttributeIF, String attributeNamespace, String definingTableName, String definingTableAlias, com.terraframe.mojo.dataaccess.MdBusinessDAOIF referenceMdBusinessIF, String referenceTableAlias, com.terraframe.mojo.query.ComponentQuery rootQuery, java.util.Set<com.terraframe.mojo.query.Join> tableJoinSet, String alias)
  {
    super(mdAttributeIF, attributeNamespace, definingTableName, definingTableAlias, referenceMdBusinessIF, referenceTableAlias, rootQuery, tableJoinSet, alias);

  }


    public com.terraframe.mojo.query.BasicCondition EQ(dss.vector.solutions.entomology.assay.EfficacyAssay efficacyAssay)
    {
      return this.EQ(efficacyAssay.getId());
    }

    public com.terraframe.mojo.query.BasicCondition NE(dss.vector.solutions.entomology.assay.EfficacyAssay efficacyAssay)
    {
      return this.NE(efficacyAssay.getId());
    }

  public dss.vector.solutions.entomology.assay.AdultAgeRangeQuery.AdultAgeRangeQueryStructIF getAgeRange()
  {
    return getAgeRange(null);

  }
 
  public dss.vector.solutions.entomology.assay.AdultAgeRangeQuery.AdultAgeRangeQueryStructIF getAgeRange(String alias)
  {
    return (dss.vector.solutions.entomology.assay.AdultAgeRangeQuery.AdultAgeRangeQueryStructIF)this.attributeFactory("ageRange", "com.terraframe.mojo.system.metadata.MdAttributeStruct", alias);

  }
  public com.terraframe.mojo.query.AttributeCharIF getColonyName()
  {
    return getColonyName(null);

  }
 
  public com.terraframe.mojo.query.AttributeCharIF getColonyName(String alias)
  {
    return (com.terraframe.mojo.query.AttributeCharIF)this.attributeFactory("colonyName", "com.terraframe.mojo.system.metadata.MdAttributeCharacter", alias);

  }
  public com.terraframe.mojo.query.AttributeIntegerIF getExposureTime()
  {
    return getExposureTime(null);

  }
 
  public com.terraframe.mojo.query.AttributeIntegerIF getExposureTime(String alias)
  {
    return (com.terraframe.mojo.query.AttributeIntegerIF)this.attributeFactory("exposureTime", "com.terraframe.mojo.system.metadata.MdAttributeInteger", alias);

  }
  public com.terraframe.mojo.query.AttributeIntegerIF getFed()
  {
    return getFed(null);

  }
 
  public com.terraframe.mojo.query.AttributeIntegerIF getFed(String alias)
  {
    return (com.terraframe.mojo.query.AttributeIntegerIF)this.attributeFactory("fed", "com.terraframe.mojo.system.metadata.MdAttributeInteger", alias);

  }
  public dss.vector.solutions.geo.generated.GeoEntityQuery.GeoEntityQueryReferenceIF getGeoEntity()
  {
    return getGeoEntity(null);

  }
 
  public dss.vector.solutions.geo.generated.GeoEntityQuery.GeoEntityQueryReferenceIF getGeoEntity(String alias)
  {
    return (dss.vector.solutions.geo.generated.GeoEntityQuery.GeoEntityQueryReferenceIF)this.attributeFactory("geoEntity", "com.terraframe.mojo.system.metadata.MdAttributeReference", alias);

  }
  public com.terraframe.mojo.query.AttributeIntegerIF getGravid()
  {
    return getGravid(null);

  }
 
  public com.terraframe.mojo.query.AttributeIntegerIF getGravid(String alias)
  {
    return (com.terraframe.mojo.query.AttributeIntegerIF)this.attributeFactory("gravid", "com.terraframe.mojo.system.metadata.MdAttributeInteger", alias);

  }
  public com.terraframe.mojo.query.AttributeIntegerIF getHoldingTime()
  {
    return getHoldingTime(null);

  }
 
  public com.terraframe.mojo.query.AttributeIntegerIF getHoldingTime(String alias)
  {
    return (com.terraframe.mojo.query.AttributeIntegerIF)this.attributeFactory("holdingTime", "com.terraframe.mojo.system.metadata.MdAttributeInteger", alias);

  }
  public com.terraframe.mojo.query.AttributeFloatIF getMortality()
  {
    return getMortality(null);

  }
 
  public com.terraframe.mojo.query.AttributeFloatIF getMortality(String alias)
  {
    return (com.terraframe.mojo.query.AttributeFloatIF)this.attributeFactory("mortality", "com.terraframe.mojo.system.metadata.MdAttributeFloat", alias);

  }
  public com.terraframe.mojo.query.AttributeIntegerIF getQuantityDead()
  {
    return getQuantityDead(null);

  }
 
  public com.terraframe.mojo.query.AttributeIntegerIF getQuantityDead(String alias)
  {
    return (com.terraframe.mojo.query.AttributeIntegerIF)this.attributeFactory("quantityDead", "com.terraframe.mojo.system.metadata.MdAttributeInteger", alias);

  }
  public com.terraframe.mojo.query.AttributeIntegerIF getQuantityLive()
  {
    return getQuantityLive(null);

  }
 
  public com.terraframe.mojo.query.AttributeIntegerIF getQuantityLive(String alias)
  {
    return (com.terraframe.mojo.query.AttributeIntegerIF)this.attributeFactory("quantityLive", "com.terraframe.mojo.system.metadata.MdAttributeInteger", alias);

  }
  public com.terraframe.mojo.query.AttributeIntegerIF getQuantityTested()
  {
    return getQuantityTested(null);

  }
 
  public com.terraframe.mojo.query.AttributeIntegerIF getQuantityTested(String alias)
  {
    return (com.terraframe.mojo.query.AttributeIntegerIF)this.attributeFactory("quantityTested", "com.terraframe.mojo.system.metadata.MdAttributeInteger", alias);

  }
  public dss.vector.solutions.entomology.SexMasterQuery.AssaySexQueryIF getSex()
  {
    return getSex(null);

  }
 
  public dss.vector.solutions.entomology.SexMasterQuery.AssaySexQueryIF getSex(String alias)
  {
    return (dss.vector.solutions.entomology.SexMasterQuery.AssaySexQueryIF)this.attributeFactory("sex", "com.terraframe.mojo.system.metadata.MdAttributeEnumeration", alias);

  }
  public dss.vector.solutions.SurfacePositionMasterQuery.SurfacePositionQueryIF getSurfacePostion()
  {
    return getSurfacePostion(null);

  }
 
  public dss.vector.solutions.SurfacePositionMasterQuery.SurfacePositionQueryIF getSurfacePostion(String alias)
  {
    return (dss.vector.solutions.SurfacePositionMasterQuery.SurfacePositionQueryIF)this.attributeFactory("surfacePostion", "com.terraframe.mojo.system.metadata.MdAttributeEnumeration", alias);

  }
  public dss.vector.solutions.mo.ResistanceMethodologyQuery.ResistanceMethodologyQueryReferenceIF getTestMethod()
  {
    return getTestMethod(null);

  }
 
  public dss.vector.solutions.mo.ResistanceMethodologyQuery.ResistanceMethodologyQueryReferenceIF getTestMethod(String alias)
  {
    return (dss.vector.solutions.mo.ResistanceMethodologyQuery.ResistanceMethodologyQueryReferenceIF)this.attributeFactory("testMethod", "com.terraframe.mojo.system.metadata.MdAttributeReference", alias);

  }
  protected com.terraframe.mojo.query.AttributeReference referenceFactory( com.terraframe.mojo.dataaccess.MdAttributeRefDAOIF mdAttributeIF, String attributeNamespace, String definingTableName, String definingTableAlias,  com.terraframe.mojo.dataaccess.MdBusinessDAOIF referenceMdBusinessIF, String referenceTableAlias, com.terraframe.mojo.query.ComponentQuery rootQuery, java.util.Set<com.terraframe.mojo.query.Join> tableJoinSet, String userDefinedAlias)
  {
    String name = mdAttributeIF.definesAttribute();
    
    if (name.equals("geoEntity")) 
    {
       return new dss.vector.solutions.geo.generated.GeoEntityQuery.GeoEntityQueryReference((com.terraframe.mojo.dataaccess.MdAttributeRefDAOIF)mdAttributeIF, attributeNamespace, definingTableName, definingTableAlias, referenceMdBusinessIF, referenceTableAlias, rootQuery, tableJoinSet, userDefinedAlias);
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

  protected com.terraframe.mojo.query.AttributeStruct structFactory( com.terraframe.mojo.dataaccess.MdAttributeStructDAOIF mdAttributeIF, String attributeNamespace, String definingTableName, String definingTableAlias,  com.terraframe.mojo.dataaccess.MdStructDAOIF mdStructIF, String structTableAlias, com.terraframe.mojo.query.ComponentQuery rootQuery, java.util.Set<com.terraframe.mojo.query.Join> tableJoinSet, String userDefinedAlias)
  {
    String name = mdAttributeIF.definesAttribute();
    
    if (name.equals("ageRange")) 
    {
       return new dss.vector.solutions.entomology.assay.AdultAgeRangeQuery.AdultAgeRangeQueryStruct((com.terraframe.mojo.dataaccess.MdAttributeStructDAOIF)mdAttributeIF,  attributeNamespace, definingTableName, definingTableAlias, mdStructIF, structTableAlias, rootQuery, tableJoinSet, userDefinedAlias);
    }
    else 
    {
      return super.structFactory(mdAttributeIF, attributeNamespace, definingTableName, definingTableAlias, mdStructIF, structTableAlias, rootQuery, tableJoinSet, userDefinedAlias);
    }
  }

  protected com.terraframe.mojo.query.AttributeEnumeration enumerationFactory( com.terraframe.mojo.dataaccess.MdAttributeEnumerationDAOIF mdAttributeIF, String attributeNamespace, String definingTableName, String definingTableAlias,  String mdEnumerationTableName, com.terraframe.mojo.dataaccess.MdBusinessDAOIF masterListMdBusinessIF, String masterListTalbeAlias, com.terraframe.mojo.query.ComponentQuery rootQuery, java.util.Set<com.terraframe.mojo.query.Join> tableJoinSet, String userDefinedAlias)
  {
    String name = mdAttributeIF.definesAttribute();
    
    if (name.equals("sex")) 
    {
       return new dss.vector.solutions.entomology.SexMasterQuery.AssaySexQuery((com.terraframe.mojo.dataaccess.MdAttributeEnumerationDAOIF)mdAttributeIF,  attributeNamespace, definingTableName, definingTableAlias, mdEnumerationTableName, masterListMdBusinessIF, masterListTalbeAlias, rootQuery, tableJoinSet, userDefinedAlias);
    }
    else if (name.equals("surfacePostion")) 
    {
       return new dss.vector.solutions.SurfacePositionMasterQuery.SurfacePositionQuery((com.terraframe.mojo.dataaccess.MdAttributeEnumerationDAOIF)mdAttributeIF,  attributeNamespace, definingTableName, definingTableAlias, mdEnumerationTableName, masterListMdBusinessIF, masterListTalbeAlias, rootQuery, tableJoinSet, userDefinedAlias);
    }
    else 
    {
      return super.enumerationFactory(mdAttributeIF, attributeNamespace, definingTableName, definingTableAlias, mdEnumerationTableName, masterListMdBusinessIF, masterListTalbeAlias, rootQuery, tableJoinSet, userDefinedAlias);
    }
  }

  }
}
