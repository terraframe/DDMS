package dss.vector.solutions.entomology.assay;

@com.terraframe.mojo.business.ClassSignature(hash = -1999684354)
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
private static final long serialVersionUID = -1999684354;

  public EfficacyAssayQuery(com.terraframe.mojo.query.QueryFactory componentQueryFactory)
  {
    super(componentQueryFactory);
    if (this.getComponentQuery() == null)
    {
      com.terraframe.mojo.business.BusinessQuery businessQuery = componentQueryFactory.businessQuery(this.getClassType());

       this.setBusinessQuery(businessQuery);
    }
  }

  public EfficacyAssayQuery(com.terraframe.mojo.query.ValueQuery valueQuery)
  {
    super(valueQuery);
    if (this.getComponentQuery() == null)
    {
      com.terraframe.mojo.business.BusinessQuery businessQuery = new com.terraframe.mojo.business.BusinessQuery(valueQuery, this.getClassType());

       this.setBusinessQuery(businessQuery);
    }
  }

  public String getClassType()
  {
    return dss.vector.solutions.entomology.assay.EfficacyAssay.CLASS;
  }
  public dss.vector.solutions.entomology.assay.AdultAgeRangeQuery.AdultAgeRangeQueryStructIF getAgeRange()
  {
    return getAgeRange(null);

  }
 
  public dss.vector.solutions.entomology.assay.AdultAgeRangeQuery.AdultAgeRangeQueryStructIF getAgeRange(String alias)
  {

    com.terraframe.mojo.dataaccess.MdAttributeDAOIF mdAttributeIF = this.getComponentQuery().getMdAttributeROfromMap(dss.vector.solutions.entomology.assay.EfficacyAssay.AGERANGE);

    return (dss.vector.solutions.entomology.assay.AdultAgeRangeQuery.AdultAgeRangeQueryStructIF)this.getComponentQuery().internalAttributeFactory(dss.vector.solutions.entomology.assay.EfficacyAssay.AGERANGE, mdAttributeIF, this, alias, null);

  }
 
  public dss.vector.solutions.entomology.assay.AdultAgeRangeQuery.AdultAgeRangeQueryStructIF getAgeRange(String alias, String displayLabel)
  {

    com.terraframe.mojo.dataaccess.MdAttributeDAOIF mdAttributeIF = this.getComponentQuery().getMdAttributeROfromMap(dss.vector.solutions.entomology.assay.EfficacyAssay.AGERANGE);

    return (dss.vector.solutions.entomology.assay.AdultAgeRangeQuery.AdultAgeRangeQueryStructIF)this.getComponentQuery().internalAttributeFactory(dss.vector.solutions.entomology.assay.EfficacyAssay.AGERANGE, mdAttributeIF, this, alias, displayLabel);

  }
  public com.terraframe.mojo.query.AttributeChar getColonyName()
  {
    return getColonyName(null);

  }
 
  public com.terraframe.mojo.query.AttributeChar getColonyName(String alias)
  {
    return (com.terraframe.mojo.query.AttributeChar)this.getComponentQuery().get(dss.vector.solutions.entomology.assay.EfficacyAssay.COLONYNAME, alias, null);

  }
 
  public com.terraframe.mojo.query.AttributeChar getColonyName(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.AttributeChar)this.getComponentQuery().get(dss.vector.solutions.entomology.assay.EfficacyAssay.COLONYNAME, alias, displayLabel);

  }
  public com.terraframe.mojo.query.AttributeInteger getExposureTime()
  {
    return getExposureTime(null);

  }
 
  public com.terraframe.mojo.query.AttributeInteger getExposureTime(String alias)
  {
    return (com.terraframe.mojo.query.AttributeInteger)this.getComponentQuery().get(dss.vector.solutions.entomology.assay.EfficacyAssay.EXPOSURETIME, alias, null);

  }
 
  public com.terraframe.mojo.query.AttributeInteger getExposureTime(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.AttributeInteger)this.getComponentQuery().get(dss.vector.solutions.entomology.assay.EfficacyAssay.EXPOSURETIME, alias, displayLabel);

  }
  public com.terraframe.mojo.query.AttributeInteger getFed()
  {
    return getFed(null);

  }
 
  public com.terraframe.mojo.query.AttributeInteger getFed(String alias)
  {
    return (com.terraframe.mojo.query.AttributeInteger)this.getComponentQuery().get(dss.vector.solutions.entomology.assay.EfficacyAssay.FED, alias, null);

  }
 
  public com.terraframe.mojo.query.AttributeInteger getFed(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.AttributeInteger)this.getComponentQuery().get(dss.vector.solutions.entomology.assay.EfficacyAssay.FED, alias, displayLabel);

  }
  public dss.vector.solutions.geo.generated.SurfaceQuery.SurfaceQueryReferenceIF getGeoEntity()
  {
    return getGeoEntity(null);

  }
 
  public dss.vector.solutions.geo.generated.SurfaceQuery.SurfaceQueryReferenceIF getGeoEntity(String alias)
  {

    com.terraframe.mojo.dataaccess.MdAttributeDAOIF mdAttributeIF = this.getComponentQuery().getMdAttributeROfromMap("geoEntity");

    return (dss.vector.solutions.geo.generated.SurfaceQuery.SurfaceQueryReferenceIF)this.getComponentQuery().internalAttributeFactory(dss.vector.solutions.entomology.assay.EfficacyAssay.GEOENTITY, mdAttributeIF, this, alias, null);

  }
 
  public dss.vector.solutions.geo.generated.SurfaceQuery.SurfaceQueryReferenceIF getGeoEntity(String alias, String displayLabel)
  {

    com.terraframe.mojo.dataaccess.MdAttributeDAOIF mdAttributeIF = this.getComponentQuery().getMdAttributeROfromMap("geoEntity");

    return (dss.vector.solutions.geo.generated.SurfaceQuery.SurfaceQueryReferenceIF)this.getComponentQuery().internalAttributeFactory(dss.vector.solutions.entomology.assay.EfficacyAssay.GEOENTITY, mdAttributeIF, this, alias, displayLabel);

  }
  public com.terraframe.mojo.query.AttributeInteger getGravid()
  {
    return getGravid(null);

  }
 
  public com.terraframe.mojo.query.AttributeInteger getGravid(String alias)
  {
    return (com.terraframe.mojo.query.AttributeInteger)this.getComponentQuery().get(dss.vector.solutions.entomology.assay.EfficacyAssay.GRAVID, alias, null);

  }
 
  public com.terraframe.mojo.query.AttributeInteger getGravid(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.AttributeInteger)this.getComponentQuery().get(dss.vector.solutions.entomology.assay.EfficacyAssay.GRAVID, alias, displayLabel);

  }
  public com.terraframe.mojo.query.AttributeInteger getHoldingTime()
  {
    return getHoldingTime(null);

  }
 
  public com.terraframe.mojo.query.AttributeInteger getHoldingTime(String alias)
  {
    return (com.terraframe.mojo.query.AttributeInteger)this.getComponentQuery().get(dss.vector.solutions.entomology.assay.EfficacyAssay.HOLDINGTIME, alias, null);

  }
 
  public com.terraframe.mojo.query.AttributeInteger getHoldingTime(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.AttributeInteger)this.getComponentQuery().get(dss.vector.solutions.entomology.assay.EfficacyAssay.HOLDINGTIME, alias, displayLabel);

  }
  public com.terraframe.mojo.query.AttributeFloat getMortality()
  {
    return getMortality(null);

  }
 
  public com.terraframe.mojo.query.AttributeFloat getMortality(String alias)
  {
    return (com.terraframe.mojo.query.AttributeFloat)this.getComponentQuery().get(dss.vector.solutions.entomology.assay.EfficacyAssay.MORTALITY, alias, null);

  }
 
  public com.terraframe.mojo.query.AttributeFloat getMortality(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.AttributeFloat)this.getComponentQuery().get(dss.vector.solutions.entomology.assay.EfficacyAssay.MORTALITY, alias, displayLabel);

  }
  public com.terraframe.mojo.query.AttributeInteger getQuantityDead()
  {
    return getQuantityDead(null);

  }
 
  public com.terraframe.mojo.query.AttributeInteger getQuantityDead(String alias)
  {
    return (com.terraframe.mojo.query.AttributeInteger)this.getComponentQuery().get(dss.vector.solutions.entomology.assay.EfficacyAssay.QUANTITYDEAD, alias, null);

  }
 
  public com.terraframe.mojo.query.AttributeInteger getQuantityDead(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.AttributeInteger)this.getComponentQuery().get(dss.vector.solutions.entomology.assay.EfficacyAssay.QUANTITYDEAD, alias, displayLabel);

  }
  public com.terraframe.mojo.query.AttributeInteger getQuantityLive()
  {
    return getQuantityLive(null);

  }
 
  public com.terraframe.mojo.query.AttributeInteger getQuantityLive(String alias)
  {
    return (com.terraframe.mojo.query.AttributeInteger)this.getComponentQuery().get(dss.vector.solutions.entomology.assay.EfficacyAssay.QUANTITYLIVE, alias, null);

  }
 
  public com.terraframe.mojo.query.AttributeInteger getQuantityLive(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.AttributeInteger)this.getComponentQuery().get(dss.vector.solutions.entomology.assay.EfficacyAssay.QUANTITYLIVE, alias, displayLabel);

  }
  public com.terraframe.mojo.query.AttributeInteger getQuantityTested()
  {
    return getQuantityTested(null);

  }
 
  public com.terraframe.mojo.query.AttributeInteger getQuantityTested(String alias)
  {
    return (com.terraframe.mojo.query.AttributeInteger)this.getComponentQuery().get(dss.vector.solutions.entomology.assay.EfficacyAssay.QUANTITYTESTED, alias, null);

  }
 
  public com.terraframe.mojo.query.AttributeInteger getQuantityTested(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.AttributeInteger)this.getComponentQuery().get(dss.vector.solutions.entomology.assay.EfficacyAssay.QUANTITYTESTED, alias, displayLabel);

  }
  public dss.vector.solutions.ontology.TermQuery.TermQueryReferenceIF getSex()
  {
    return getSex(null);

  }
 
  public dss.vector.solutions.ontology.TermQuery.TermQueryReferenceIF getSex(String alias)
  {

    com.terraframe.mojo.dataaccess.MdAttributeDAOIF mdAttributeIF = this.getComponentQuery().getMdAttributeROfromMap("sex");

    return (dss.vector.solutions.ontology.TermQuery.TermQueryReferenceIF)this.getComponentQuery().internalAttributeFactory(dss.vector.solutions.entomology.assay.EfficacyAssay.SEX, mdAttributeIF, this, alias, null);

  }
 
  public dss.vector.solutions.ontology.TermQuery.TermQueryReferenceIF getSex(String alias, String displayLabel)
  {

    com.terraframe.mojo.dataaccess.MdAttributeDAOIF mdAttributeIF = this.getComponentQuery().getMdAttributeROfromMap("sex");

    return (dss.vector.solutions.ontology.TermQuery.TermQueryReferenceIF)this.getComponentQuery().internalAttributeFactory(dss.vector.solutions.entomology.assay.EfficacyAssay.SEX, mdAttributeIF, this, alias, displayLabel);

  }
  public dss.vector.solutions.ontology.TermQuery.TermQueryReferenceIF getSurfacePostion()
  {
    return getSurfacePostion(null);

  }
 
  public dss.vector.solutions.ontology.TermQuery.TermQueryReferenceIF getSurfacePostion(String alias)
  {

    com.terraframe.mojo.dataaccess.MdAttributeDAOIF mdAttributeIF = this.getComponentQuery().getMdAttributeROfromMap("surfacePostion");

    return (dss.vector.solutions.ontology.TermQuery.TermQueryReferenceIF)this.getComponentQuery().internalAttributeFactory(dss.vector.solutions.entomology.assay.EfficacyAssay.SURFACEPOSTION, mdAttributeIF, this, alias, null);

  }
 
  public dss.vector.solutions.ontology.TermQuery.TermQueryReferenceIF getSurfacePostion(String alias, String displayLabel)
  {

    com.terraframe.mojo.dataaccess.MdAttributeDAOIF mdAttributeIF = this.getComponentQuery().getMdAttributeROfromMap("surfacePostion");

    return (dss.vector.solutions.ontology.TermQuery.TermQueryReferenceIF)this.getComponentQuery().internalAttributeFactory(dss.vector.solutions.entomology.assay.EfficacyAssay.SURFACEPOSTION, mdAttributeIF, this, alias, displayLabel);

  }
  public dss.vector.solutions.ontology.TermQuery.TermQueryReferenceIF getTestMethod()
  {
    return getTestMethod(null);

  }
 
  public dss.vector.solutions.ontology.TermQuery.TermQueryReferenceIF getTestMethod(String alias)
  {

    com.terraframe.mojo.dataaccess.MdAttributeDAOIF mdAttributeIF = this.getComponentQuery().getMdAttributeROfromMap("testMethod");

    return (dss.vector.solutions.ontology.TermQuery.TermQueryReferenceIF)this.getComponentQuery().internalAttributeFactory(dss.vector.solutions.entomology.assay.EfficacyAssay.TESTMETHOD, mdAttributeIF, this, alias, null);

  }
 
  public dss.vector.solutions.ontology.TermQuery.TermQueryReferenceIF getTestMethod(String alias, String displayLabel)
  {

    com.terraframe.mojo.dataaccess.MdAttributeDAOIF mdAttributeIF = this.getComponentQuery().getMdAttributeROfromMap("testMethod");

    return (dss.vector.solutions.ontology.TermQuery.TermQueryReferenceIF)this.getComponentQuery().internalAttributeFactory(dss.vector.solutions.entomology.assay.EfficacyAssay.TESTMETHOD, mdAttributeIF, this, alias, displayLabel);

  }
  public com.terraframe.mojo.query.AttributeInteger getTimeOnSurface()
  {
    return getTimeOnSurface(null);

  }
 
  public com.terraframe.mojo.query.AttributeInteger getTimeOnSurface(String alias)
  {
    return (com.terraframe.mojo.query.AttributeInteger)this.getComponentQuery().get(dss.vector.solutions.entomology.assay.EfficacyAssay.TIMEONSURFACE, alias, null);

  }
 
  public com.terraframe.mojo.query.AttributeInteger getTimeOnSurface(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.AttributeInteger)this.getComponentQuery().get(dss.vector.solutions.entomology.assay.EfficacyAssay.TIMEONSURFACE, alias, displayLabel);

  }
  protected com.terraframe.mojo.query.AttributeReference referenceFactory( com.terraframe.mojo.dataaccess.MdAttributeRefDAOIF mdAttributeIF, String attributeNamespace, String definingTableName, String definingTableAlias,  com.terraframe.mojo.dataaccess.MdBusinessDAOIF referenceMdBusinessIF, String referenceTableAlias, com.terraframe.mojo.query.ComponentQuery rootQuery, java.util.Set<com.terraframe.mojo.query.Join> tableJoinSet, String userDefinedAlias, String userDefinedDisplayLabel)
  {
    String name = mdAttributeIF.definesAttribute();
    
    if (name.equals("geoEntity")) 
    {
       return new dss.vector.solutions.geo.generated.SurfaceQuery.SurfaceQueryReference((com.terraframe.mojo.dataaccess.MdAttributeRefDAOIF)mdAttributeIF, attributeNamespace, definingTableName, definingTableAlias, referenceMdBusinessIF, referenceTableAlias, rootQuery, tableJoinSet, userDefinedAlias, userDefinedDisplayLabel);
    }
    else if (name.equals("sex")) 
    {
       return new dss.vector.solutions.ontology.TermQuery.TermQueryReference((com.terraframe.mojo.dataaccess.MdAttributeRefDAOIF)mdAttributeIF, attributeNamespace, definingTableName, definingTableAlias, referenceMdBusinessIF, referenceTableAlias, rootQuery, tableJoinSet, userDefinedAlias, userDefinedDisplayLabel);
    }
    else if (name.equals("surfacePostion")) 
    {
       return new dss.vector.solutions.ontology.TermQuery.TermQueryReference((com.terraframe.mojo.dataaccess.MdAttributeRefDAOIF)mdAttributeIF, attributeNamespace, definingTableName, definingTableAlias, referenceMdBusinessIF, referenceTableAlias, rootQuery, tableJoinSet, userDefinedAlias, userDefinedDisplayLabel);
    }
    else if (name.equals("testMethod")) 
    {
       return new dss.vector.solutions.ontology.TermQuery.TermQueryReference((com.terraframe.mojo.dataaccess.MdAttributeRefDAOIF)mdAttributeIF, attributeNamespace, definingTableName, definingTableAlias, referenceMdBusinessIF, referenceTableAlias, rootQuery, tableJoinSet, userDefinedAlias, userDefinedDisplayLabel);
    }
    else 
    {
      return super.referenceFactory(mdAttributeIF, attributeNamespace, definingTableName, definingTableAlias, referenceMdBusinessIF, referenceTableAlias, rootQuery, tableJoinSet, userDefinedAlias, userDefinedDisplayLabel);
    }
  }

  protected com.terraframe.mojo.query.AttributeStruct structFactory( com.terraframe.mojo.dataaccess.MdAttributeStructDAOIF mdAttributeIF, String attributeNamespace, String definingTableName, String definingTableAlias,  com.terraframe.mojo.dataaccess.MdStructDAOIF mdStructIF, String structTableAlias, com.terraframe.mojo.query.ComponentQuery rootQuery, java.util.Set<com.terraframe.mojo.query.Join> tableJoinSet, String userDefinedAlias, String userDefinedDisplayLabel)
  {
    String name = mdAttributeIF.definesAttribute();
    
    if (name.equals(dss.vector.solutions.entomology.assay.EfficacyAssay.AGERANGE)) 
    {
       return new dss.vector.solutions.entomology.assay.AdultAgeRangeQuery.AdultAgeRangeQueryStruct((com.terraframe.mojo.dataaccess.MdAttributeStructDAOIF)mdAttributeIF,  attributeNamespace, definingTableName, definingTableAlias, mdStructIF, structTableAlias, rootQuery, tableJoinSet, userDefinedAlias, userDefinedDisplayLabel);
    }
    else 
    {
      return super.structFactory(mdAttributeIF, attributeNamespace, definingTableName, definingTableAlias, mdStructIF, structTableAlias, rootQuery, tableJoinSet, userDefinedAlias, userDefinedDisplayLabel);
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
    public dss.vector.solutions.entomology.assay.AdultAgeRangeQuery.AdultAgeRangeQueryStructIF getAgeRange(String alias, String displayLabel);
    public com.terraframe.mojo.query.AttributeChar getColonyName();
    public com.terraframe.mojo.query.AttributeChar getColonyName(String alias);
    public com.terraframe.mojo.query.AttributeChar getColonyName(String alias, String displayLabel);
    public com.terraframe.mojo.query.AttributeInteger getExposureTime();
    public com.terraframe.mojo.query.AttributeInteger getExposureTime(String alias);
    public com.terraframe.mojo.query.AttributeInteger getExposureTime(String alias, String displayLabel);
    public com.terraframe.mojo.query.AttributeInteger getFed();
    public com.terraframe.mojo.query.AttributeInteger getFed(String alias);
    public com.terraframe.mojo.query.AttributeInteger getFed(String alias, String displayLabel);
    public dss.vector.solutions.geo.generated.SurfaceQuery.SurfaceQueryReferenceIF getGeoEntity();
    public dss.vector.solutions.geo.generated.SurfaceQuery.SurfaceQueryReferenceIF getGeoEntity(String alias);
    public dss.vector.solutions.geo.generated.SurfaceQuery.SurfaceQueryReferenceIF getGeoEntity(String alias, String displayLabel);
    public com.terraframe.mojo.query.AttributeInteger getGravid();
    public com.terraframe.mojo.query.AttributeInteger getGravid(String alias);
    public com.terraframe.mojo.query.AttributeInteger getGravid(String alias, String displayLabel);
    public com.terraframe.mojo.query.AttributeInteger getHoldingTime();
    public com.terraframe.mojo.query.AttributeInteger getHoldingTime(String alias);
    public com.terraframe.mojo.query.AttributeInteger getHoldingTime(String alias, String displayLabel);
    public com.terraframe.mojo.query.AttributeFloat getMortality();
    public com.terraframe.mojo.query.AttributeFloat getMortality(String alias);
    public com.terraframe.mojo.query.AttributeFloat getMortality(String alias, String displayLabel);
    public com.terraframe.mojo.query.AttributeInteger getQuantityDead();
    public com.terraframe.mojo.query.AttributeInteger getQuantityDead(String alias);
    public com.terraframe.mojo.query.AttributeInteger getQuantityDead(String alias, String displayLabel);
    public com.terraframe.mojo.query.AttributeInteger getQuantityLive();
    public com.terraframe.mojo.query.AttributeInteger getQuantityLive(String alias);
    public com.terraframe.mojo.query.AttributeInteger getQuantityLive(String alias, String displayLabel);
    public com.terraframe.mojo.query.AttributeInteger getQuantityTested();
    public com.terraframe.mojo.query.AttributeInteger getQuantityTested(String alias);
    public com.terraframe.mojo.query.AttributeInteger getQuantityTested(String alias, String displayLabel);
    public dss.vector.solutions.ontology.TermQuery.TermQueryReferenceIF getSex();
    public dss.vector.solutions.ontology.TermQuery.TermQueryReferenceIF getSex(String alias);
    public dss.vector.solutions.ontology.TermQuery.TermQueryReferenceIF getSex(String alias, String displayLabel);
    public dss.vector.solutions.ontology.TermQuery.TermQueryReferenceIF getSurfacePostion();
    public dss.vector.solutions.ontology.TermQuery.TermQueryReferenceIF getSurfacePostion(String alias);
    public dss.vector.solutions.ontology.TermQuery.TermQueryReferenceIF getSurfacePostion(String alias, String displayLabel);
    public dss.vector.solutions.ontology.TermQuery.TermQueryReferenceIF getTestMethod();
    public dss.vector.solutions.ontology.TermQuery.TermQueryReferenceIF getTestMethod(String alias);
    public dss.vector.solutions.ontology.TermQuery.TermQueryReferenceIF getTestMethod(String alias, String displayLabel);
    public com.terraframe.mojo.query.AttributeInteger getTimeOnSurface();
    public com.terraframe.mojo.query.AttributeInteger getTimeOnSurface(String alias);
    public com.terraframe.mojo.query.AttributeInteger getTimeOnSurface(String alias, String displayLabel);

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
private static final long serialVersionUID = 937916700;

  public EfficacyAssayQueryReference(com.terraframe.mojo.dataaccess.MdAttributeRefDAOIF mdAttributeIF, String attributeNamespace, String definingTableName, String definingTableAlias, com.terraframe.mojo.dataaccess.MdBusinessDAOIF referenceMdBusinessIF, String referenceTableAlias, com.terraframe.mojo.query.ComponentQuery rootQuery, java.util.Set<com.terraframe.mojo.query.Join> tableJoinSet, String alias, String displayLabel)
  {
    super(mdAttributeIF, attributeNamespace, definingTableName, definingTableAlias, referenceMdBusinessIF, referenceTableAlias, rootQuery, tableJoinSet, alias, displayLabel);

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
    return (dss.vector.solutions.entomology.assay.AdultAgeRangeQuery.AdultAgeRangeQueryStructIF)this.attributeFactory(dss.vector.solutions.entomology.assay.EfficacyAssay.AGERANGE, com.terraframe.mojo.system.metadata.MdAttributeStruct.CLASS, alias, null);

  }
 
  public dss.vector.solutions.entomology.assay.AdultAgeRangeQuery.AdultAgeRangeQueryStructIF getAgeRange(String alias, String displayLabel)
  {
    return (dss.vector.solutions.entomology.assay.AdultAgeRangeQuery.AdultAgeRangeQueryStructIF)this.attributeFactory(dss.vector.solutions.entomology.assay.EfficacyAssay.AGERANGE, com.terraframe.mojo.system.metadata.MdAttributeStruct.CLASS, alias, displayLabel);

  }
  public com.terraframe.mojo.query.AttributeChar getColonyName()
  {
    return getColonyName(null);

  }
 
  public com.terraframe.mojo.query.AttributeChar getColonyName(String alias)
  {
    return (com.terraframe.mojo.query.AttributeChar)this.get(dss.vector.solutions.entomology.assay.EfficacyAssay.COLONYNAME, alias, null);

  }
 
  public com.terraframe.mojo.query.AttributeChar getColonyName(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.AttributeChar)this.get(dss.vector.solutions.entomology.assay.EfficacyAssay.COLONYNAME, alias, displayLabel);

  }
  public com.terraframe.mojo.query.AttributeInteger getExposureTime()
  {
    return getExposureTime(null);

  }
 
  public com.terraframe.mojo.query.AttributeInteger getExposureTime(String alias)
  {
    return (com.terraframe.mojo.query.AttributeInteger)this.get(dss.vector.solutions.entomology.assay.EfficacyAssay.EXPOSURETIME, alias, null);

  }
 
  public com.terraframe.mojo.query.AttributeInteger getExposureTime(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.AttributeInteger)this.get(dss.vector.solutions.entomology.assay.EfficacyAssay.EXPOSURETIME, alias, displayLabel);

  }
  public com.terraframe.mojo.query.AttributeInteger getFed()
  {
    return getFed(null);

  }
 
  public com.terraframe.mojo.query.AttributeInteger getFed(String alias)
  {
    return (com.terraframe.mojo.query.AttributeInteger)this.get(dss.vector.solutions.entomology.assay.EfficacyAssay.FED, alias, null);

  }
 
  public com.terraframe.mojo.query.AttributeInteger getFed(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.AttributeInteger)this.get(dss.vector.solutions.entomology.assay.EfficacyAssay.FED, alias, displayLabel);

  }
  public dss.vector.solutions.geo.generated.SurfaceQuery.SurfaceQueryReferenceIF getGeoEntity()
  {
    return getGeoEntity(null);

  }
 
  public dss.vector.solutions.geo.generated.SurfaceQuery.SurfaceQueryReferenceIF getGeoEntity(String alias)
  {
    return (dss.vector.solutions.geo.generated.SurfaceQuery.SurfaceQueryReferenceIF)this.get(dss.vector.solutions.entomology.assay.EfficacyAssay.GEOENTITY, alias, null);

  }
 
  public dss.vector.solutions.geo.generated.SurfaceQuery.SurfaceQueryReferenceIF getGeoEntity(String alias, String displayLabel)
  {
    return (dss.vector.solutions.geo.generated.SurfaceQuery.SurfaceQueryReferenceIF)this.get(dss.vector.solutions.entomology.assay.EfficacyAssay.GEOENTITY,  alias, displayLabel);

  }
  public com.terraframe.mojo.query.AttributeInteger getGravid()
  {
    return getGravid(null);

  }
 
  public com.terraframe.mojo.query.AttributeInteger getGravid(String alias)
  {
    return (com.terraframe.mojo.query.AttributeInteger)this.get(dss.vector.solutions.entomology.assay.EfficacyAssay.GRAVID, alias, null);

  }
 
  public com.terraframe.mojo.query.AttributeInteger getGravid(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.AttributeInteger)this.get(dss.vector.solutions.entomology.assay.EfficacyAssay.GRAVID, alias, displayLabel);

  }
  public com.terraframe.mojo.query.AttributeInteger getHoldingTime()
  {
    return getHoldingTime(null);

  }
 
  public com.terraframe.mojo.query.AttributeInteger getHoldingTime(String alias)
  {
    return (com.terraframe.mojo.query.AttributeInteger)this.get(dss.vector.solutions.entomology.assay.EfficacyAssay.HOLDINGTIME, alias, null);

  }
 
  public com.terraframe.mojo.query.AttributeInteger getHoldingTime(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.AttributeInteger)this.get(dss.vector.solutions.entomology.assay.EfficacyAssay.HOLDINGTIME, alias, displayLabel);

  }
  public com.terraframe.mojo.query.AttributeFloat getMortality()
  {
    return getMortality(null);

  }
 
  public com.terraframe.mojo.query.AttributeFloat getMortality(String alias)
  {
    return (com.terraframe.mojo.query.AttributeFloat)this.get(dss.vector.solutions.entomology.assay.EfficacyAssay.MORTALITY, alias, null);

  }
 
  public com.terraframe.mojo.query.AttributeFloat getMortality(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.AttributeFloat)this.get(dss.vector.solutions.entomology.assay.EfficacyAssay.MORTALITY, alias, displayLabel);

  }
  public com.terraframe.mojo.query.AttributeInteger getQuantityDead()
  {
    return getQuantityDead(null);

  }
 
  public com.terraframe.mojo.query.AttributeInteger getQuantityDead(String alias)
  {
    return (com.terraframe.mojo.query.AttributeInteger)this.get(dss.vector.solutions.entomology.assay.EfficacyAssay.QUANTITYDEAD, alias, null);

  }
 
  public com.terraframe.mojo.query.AttributeInteger getQuantityDead(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.AttributeInteger)this.get(dss.vector.solutions.entomology.assay.EfficacyAssay.QUANTITYDEAD, alias, displayLabel);

  }
  public com.terraframe.mojo.query.AttributeInteger getQuantityLive()
  {
    return getQuantityLive(null);

  }
 
  public com.terraframe.mojo.query.AttributeInteger getQuantityLive(String alias)
  {
    return (com.terraframe.mojo.query.AttributeInteger)this.get(dss.vector.solutions.entomology.assay.EfficacyAssay.QUANTITYLIVE, alias, null);

  }
 
  public com.terraframe.mojo.query.AttributeInteger getQuantityLive(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.AttributeInteger)this.get(dss.vector.solutions.entomology.assay.EfficacyAssay.QUANTITYLIVE, alias, displayLabel);

  }
  public com.terraframe.mojo.query.AttributeInteger getQuantityTested()
  {
    return getQuantityTested(null);

  }
 
  public com.terraframe.mojo.query.AttributeInteger getQuantityTested(String alias)
  {
    return (com.terraframe.mojo.query.AttributeInteger)this.get(dss.vector.solutions.entomology.assay.EfficacyAssay.QUANTITYTESTED, alias, null);

  }
 
  public com.terraframe.mojo.query.AttributeInteger getQuantityTested(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.AttributeInteger)this.get(dss.vector.solutions.entomology.assay.EfficacyAssay.QUANTITYTESTED, alias, displayLabel);

  }
  public dss.vector.solutions.ontology.TermQuery.TermQueryReferenceIF getSex()
  {
    return getSex(null);

  }
 
  public dss.vector.solutions.ontology.TermQuery.TermQueryReferenceIF getSex(String alias)
  {
    return (dss.vector.solutions.ontology.TermQuery.TermQueryReferenceIF)this.get(dss.vector.solutions.entomology.assay.EfficacyAssay.SEX, alias, null);

  }
 
  public dss.vector.solutions.ontology.TermQuery.TermQueryReferenceIF getSex(String alias, String displayLabel)
  {
    return (dss.vector.solutions.ontology.TermQuery.TermQueryReferenceIF)this.get(dss.vector.solutions.entomology.assay.EfficacyAssay.SEX,  alias, displayLabel);

  }
  public dss.vector.solutions.ontology.TermQuery.TermQueryReferenceIF getSurfacePostion()
  {
    return getSurfacePostion(null);

  }
 
  public dss.vector.solutions.ontology.TermQuery.TermQueryReferenceIF getSurfacePostion(String alias)
  {
    return (dss.vector.solutions.ontology.TermQuery.TermQueryReferenceIF)this.get(dss.vector.solutions.entomology.assay.EfficacyAssay.SURFACEPOSTION, alias, null);

  }
 
  public dss.vector.solutions.ontology.TermQuery.TermQueryReferenceIF getSurfacePostion(String alias, String displayLabel)
  {
    return (dss.vector.solutions.ontology.TermQuery.TermQueryReferenceIF)this.get(dss.vector.solutions.entomology.assay.EfficacyAssay.SURFACEPOSTION,  alias, displayLabel);

  }
  public dss.vector.solutions.ontology.TermQuery.TermQueryReferenceIF getTestMethod()
  {
    return getTestMethod(null);

  }
 
  public dss.vector.solutions.ontology.TermQuery.TermQueryReferenceIF getTestMethod(String alias)
  {
    return (dss.vector.solutions.ontology.TermQuery.TermQueryReferenceIF)this.get(dss.vector.solutions.entomology.assay.EfficacyAssay.TESTMETHOD, alias, null);

  }
 
  public dss.vector.solutions.ontology.TermQuery.TermQueryReferenceIF getTestMethod(String alias, String displayLabel)
  {
    return (dss.vector.solutions.ontology.TermQuery.TermQueryReferenceIF)this.get(dss.vector.solutions.entomology.assay.EfficacyAssay.TESTMETHOD,  alias, displayLabel);

  }
  public com.terraframe.mojo.query.AttributeInteger getTimeOnSurface()
  {
    return getTimeOnSurface(null);

  }
 
  public com.terraframe.mojo.query.AttributeInteger getTimeOnSurface(String alias)
  {
    return (com.terraframe.mojo.query.AttributeInteger)this.get(dss.vector.solutions.entomology.assay.EfficacyAssay.TIMEONSURFACE, alias, null);

  }
 
  public com.terraframe.mojo.query.AttributeInteger getTimeOnSurface(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.AttributeInteger)this.get(dss.vector.solutions.entomology.assay.EfficacyAssay.TIMEONSURFACE, alias, displayLabel);

  }
  protected com.terraframe.mojo.query.AttributeReference referenceFactory( com.terraframe.mojo.dataaccess.MdAttributeRefDAOIF mdAttributeIF, String attributeNamespace, String definingTableName, String definingTableAlias,  com.terraframe.mojo.dataaccess.MdBusinessDAOIF referenceMdBusinessIF, String referenceTableAlias, com.terraframe.mojo.query.ComponentQuery rootQuery, java.util.Set<com.terraframe.mojo.query.Join> tableJoinSet, String userDefinedAlias, String userDefinedDisplayLabel)
  {
    String name = mdAttributeIF.definesAttribute();
    
    if (name.equals("geoEntity")) 
    {
       return new dss.vector.solutions.geo.generated.SurfaceQuery.SurfaceQueryReference((com.terraframe.mojo.dataaccess.MdAttributeRefDAOIF)mdAttributeIF, attributeNamespace, definingTableName, definingTableAlias, referenceMdBusinessIF, referenceTableAlias, rootQuery, tableJoinSet, userDefinedAlias, userDefinedDisplayLabel);
    }
    else if (name.equals("sex")) 
    {
       return new dss.vector.solutions.ontology.TermQuery.TermQueryReference((com.terraframe.mojo.dataaccess.MdAttributeRefDAOIF)mdAttributeIF, attributeNamespace, definingTableName, definingTableAlias, referenceMdBusinessIF, referenceTableAlias, rootQuery, tableJoinSet, userDefinedAlias, userDefinedDisplayLabel);
    }
    else if (name.equals("surfacePostion")) 
    {
       return new dss.vector.solutions.ontology.TermQuery.TermQueryReference((com.terraframe.mojo.dataaccess.MdAttributeRefDAOIF)mdAttributeIF, attributeNamespace, definingTableName, definingTableAlias, referenceMdBusinessIF, referenceTableAlias, rootQuery, tableJoinSet, userDefinedAlias, userDefinedDisplayLabel);
    }
    else if (name.equals("testMethod")) 
    {
       return new dss.vector.solutions.ontology.TermQuery.TermQueryReference((com.terraframe.mojo.dataaccess.MdAttributeRefDAOIF)mdAttributeIF, attributeNamespace, definingTableName, definingTableAlias, referenceMdBusinessIF, referenceTableAlias, rootQuery, tableJoinSet, userDefinedAlias, userDefinedDisplayLabel);
    }
    else 
    {
      return super.referenceFactory(mdAttributeIF, attributeNamespace, definingTableName, definingTableAlias, referenceMdBusinessIF, referenceTableAlias, rootQuery, tableJoinSet, userDefinedAlias, userDefinedDisplayLabel);
    }
  }

  protected com.terraframe.mojo.query.AttributeStruct structFactory( com.terraframe.mojo.dataaccess.MdAttributeStructDAOIF mdAttributeIF, String attributeNamespace, String definingTableName, String definingTableAlias,  com.terraframe.mojo.dataaccess.MdStructDAOIF mdStructIF, String structTableAlias, com.terraframe.mojo.query.ComponentQuery rootQuery, java.util.Set<com.terraframe.mojo.query.Join> tableJoinSet, String userDefinedAlias, String userDefinedDisplayLabel)
  {
    String name = mdAttributeIF.definesAttribute();
    
    if (name.equals(dss.vector.solutions.entomology.assay.EfficacyAssay.AGERANGE)) 
    {
       return new dss.vector.solutions.entomology.assay.AdultAgeRangeQuery.AdultAgeRangeQueryStruct((com.terraframe.mojo.dataaccess.MdAttributeStructDAOIF)mdAttributeIF,  attributeNamespace, definingTableName, definingTableAlias, mdStructIF, structTableAlias, rootQuery, tableJoinSet, userDefinedAlias, userDefinedDisplayLabel);
    }
    else 
    {
      return super.structFactory(mdAttributeIF, attributeNamespace, definingTableName, definingTableAlias, mdStructIF, structTableAlias, rootQuery, tableJoinSet, userDefinedAlias, userDefinedDisplayLabel);
    }
  }

  }
}
