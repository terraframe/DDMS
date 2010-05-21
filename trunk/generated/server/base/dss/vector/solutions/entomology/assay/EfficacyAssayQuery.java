package dss.vector.solutions.entomology.assay;

@com.runwaysdk.business.ClassSignature(hash = -1591314716)
/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to EfficacyAssay.java
 *
 * @author Autogenerated by RunwaySDK
 */
public  class EfficacyAssayQuery extends dss.vector.solutions.entomology.assay.AbstractAssayQuery
 implements com.runwaysdk.generation.loader.Reloadable
{
private static final long serialVersionUID = -1591314716;

  public EfficacyAssayQuery(com.runwaysdk.query.QueryFactory componentQueryFactory)
  {
    super(componentQueryFactory);
    if (this.getComponentQuery() == null)
    {
      com.runwaysdk.business.BusinessQuery businessQuery = componentQueryFactory.businessQuery(this.getClassType());

       this.setBusinessQuery(businessQuery);
    }
  }

  public EfficacyAssayQuery(com.runwaysdk.query.ValueQuery valueQuery)
  {
    super(valueQuery);
    if (this.getComponentQuery() == null)
    {
      com.runwaysdk.business.BusinessQuery businessQuery = new com.runwaysdk.business.BusinessQuery(valueQuery, this.getClassType());

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

    com.runwaysdk.dataaccess.MdAttributeDAOIF mdAttributeIF = this.getComponentQuery().getMdAttributeROfromMap(dss.vector.solutions.entomology.assay.EfficacyAssay.AGERANGE);

    return (dss.vector.solutions.entomology.assay.AdultAgeRangeQuery.AdultAgeRangeQueryStructIF)this.getComponentQuery().internalAttributeFactory(dss.vector.solutions.entomology.assay.EfficacyAssay.AGERANGE, mdAttributeIF, this, alias, null);

  }
 
  public dss.vector.solutions.entomology.assay.AdultAgeRangeQuery.AdultAgeRangeQueryStructIF getAgeRange(String alias, String displayLabel)
  {

    com.runwaysdk.dataaccess.MdAttributeDAOIF mdAttributeIF = this.getComponentQuery().getMdAttributeROfromMap(dss.vector.solutions.entomology.assay.EfficacyAssay.AGERANGE);

    return (dss.vector.solutions.entomology.assay.AdultAgeRangeQuery.AdultAgeRangeQueryStructIF)this.getComponentQuery().internalAttributeFactory(dss.vector.solutions.entomology.assay.EfficacyAssay.AGERANGE, mdAttributeIF, this, alias, displayLabel);

  }
  public com.runwaysdk.query.SelectableChar getColonyName()
  {
    return getColonyName(null);

  }
 
  public com.runwaysdk.query.SelectableChar getColonyName(String alias)
  {
    return (com.runwaysdk.query.SelectableChar)this.getComponentQuery().get(dss.vector.solutions.entomology.assay.EfficacyAssay.COLONYNAME, alias, null);

  }
 
  public com.runwaysdk.query.SelectableChar getColonyName(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableChar)this.getComponentQuery().get(dss.vector.solutions.entomology.assay.EfficacyAssay.COLONYNAME, alias, displayLabel);

  }
  public com.runwaysdk.query.SelectableInteger getExposureTime()
  {
    return getExposureTime(null);

  }
 
  public com.runwaysdk.query.SelectableInteger getExposureTime(String alias)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getComponentQuery().get(dss.vector.solutions.entomology.assay.EfficacyAssay.EXPOSURETIME, alias, null);

  }
 
  public com.runwaysdk.query.SelectableInteger getExposureTime(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getComponentQuery().get(dss.vector.solutions.entomology.assay.EfficacyAssay.EXPOSURETIME, alias, displayLabel);

  }
  public com.runwaysdk.query.SelectableInteger getFed()
  {
    return getFed(null);

  }
 
  public com.runwaysdk.query.SelectableInteger getFed(String alias)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getComponentQuery().get(dss.vector.solutions.entomology.assay.EfficacyAssay.FED, alias, null);

  }
 
  public com.runwaysdk.query.SelectableInteger getFed(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getComponentQuery().get(dss.vector.solutions.entomology.assay.EfficacyAssay.FED, alias, displayLabel);

  }
  public dss.vector.solutions.geo.generated.SurfaceQuery.SurfaceQueryReferenceIF getGeoEntity()
  {
    return getGeoEntity(null);

  }
 
  public dss.vector.solutions.geo.generated.SurfaceQuery.SurfaceQueryReferenceIF getGeoEntity(String alias)
  {

    com.runwaysdk.dataaccess.MdAttributeDAOIF mdAttributeIF = this.getComponentQuery().getMdAttributeROfromMap(dss.vector.solutions.entomology.assay.EfficacyAssay.GEOENTITY);

    return (dss.vector.solutions.geo.generated.SurfaceQuery.SurfaceQueryReferenceIF)this.getComponentQuery().internalAttributeFactory(dss.vector.solutions.entomology.assay.EfficacyAssay.GEOENTITY, mdAttributeIF, this, alias, null);

  }
 
  public dss.vector.solutions.geo.generated.SurfaceQuery.SurfaceQueryReferenceIF getGeoEntity(String alias, String displayLabel)
  {

    com.runwaysdk.dataaccess.MdAttributeDAOIF mdAttributeIF = this.getComponentQuery().getMdAttributeROfromMap(dss.vector.solutions.entomology.assay.EfficacyAssay.GEOENTITY);

    return (dss.vector.solutions.geo.generated.SurfaceQuery.SurfaceQueryReferenceIF)this.getComponentQuery().internalAttributeFactory(dss.vector.solutions.entomology.assay.EfficacyAssay.GEOENTITY, mdAttributeIF, this, alias, displayLabel);

  }
  public com.runwaysdk.query.SelectableInteger getGravid()
  {
    return getGravid(null);

  }
 
  public com.runwaysdk.query.SelectableInteger getGravid(String alias)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getComponentQuery().get(dss.vector.solutions.entomology.assay.EfficacyAssay.GRAVID, alias, null);

  }
 
  public com.runwaysdk.query.SelectableInteger getGravid(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getComponentQuery().get(dss.vector.solutions.entomology.assay.EfficacyAssay.GRAVID, alias, displayLabel);

  }
  public com.runwaysdk.query.SelectableInteger getHoldingTime()
  {
    return getHoldingTime(null);

  }
 
  public com.runwaysdk.query.SelectableInteger getHoldingTime(String alias)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getComponentQuery().get(dss.vector.solutions.entomology.assay.EfficacyAssay.HOLDINGTIME, alias, null);

  }
 
  public com.runwaysdk.query.SelectableInteger getHoldingTime(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getComponentQuery().get(dss.vector.solutions.entomology.assay.EfficacyAssay.HOLDINGTIME, alias, displayLabel);

  }
  public com.runwaysdk.query.SelectableFloat getMortality()
  {
    return getMortality(null);

  }
 
  public com.runwaysdk.query.SelectableFloat getMortality(String alias)
  {
    return (com.runwaysdk.query.SelectableFloat)this.getComponentQuery().get(dss.vector.solutions.entomology.assay.EfficacyAssay.MORTALITY, alias, null);

  }
 
  public com.runwaysdk.query.SelectableFloat getMortality(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableFloat)this.getComponentQuery().get(dss.vector.solutions.entomology.assay.EfficacyAssay.MORTALITY, alias, displayLabel);

  }
  public com.runwaysdk.query.SelectableInteger getQuantityDead()
  {
    return getQuantityDead(null);

  }
 
  public com.runwaysdk.query.SelectableInteger getQuantityDead(String alias)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getComponentQuery().get(dss.vector.solutions.entomology.assay.EfficacyAssay.QUANTITYDEAD, alias, null);

  }
 
  public com.runwaysdk.query.SelectableInteger getQuantityDead(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getComponentQuery().get(dss.vector.solutions.entomology.assay.EfficacyAssay.QUANTITYDEAD, alias, displayLabel);

  }
  public com.runwaysdk.query.SelectableInteger getQuantityLive()
  {
    return getQuantityLive(null);

  }
 
  public com.runwaysdk.query.SelectableInteger getQuantityLive(String alias)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getComponentQuery().get(dss.vector.solutions.entomology.assay.EfficacyAssay.QUANTITYLIVE, alias, null);

  }
 
  public com.runwaysdk.query.SelectableInteger getQuantityLive(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getComponentQuery().get(dss.vector.solutions.entomology.assay.EfficacyAssay.QUANTITYLIVE, alias, displayLabel);

  }
  public com.runwaysdk.query.SelectableInteger getQuantityTested()
  {
    return getQuantityTested(null);

  }
 
  public com.runwaysdk.query.SelectableInteger getQuantityTested(String alias)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getComponentQuery().get(dss.vector.solutions.entomology.assay.EfficacyAssay.QUANTITYTESTED, alias, null);

  }
 
  public com.runwaysdk.query.SelectableInteger getQuantityTested(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getComponentQuery().get(dss.vector.solutions.entomology.assay.EfficacyAssay.QUANTITYTESTED, alias, displayLabel);

  }
  public dss.vector.solutions.ontology.TermQuery.TermQueryReferenceIF getSex()
  {
    return getSex(null);

  }
 
  public dss.vector.solutions.ontology.TermQuery.TermQueryReferenceIF getSex(String alias)
  {

    com.runwaysdk.dataaccess.MdAttributeDAOIF mdAttributeIF = this.getComponentQuery().getMdAttributeROfromMap(dss.vector.solutions.entomology.assay.EfficacyAssay.SEX);

    return (dss.vector.solutions.ontology.TermQuery.TermQueryReferenceIF)this.getComponentQuery().internalAttributeFactory(dss.vector.solutions.entomology.assay.EfficacyAssay.SEX, mdAttributeIF, this, alias, null);

  }
 
  public dss.vector.solutions.ontology.TermQuery.TermQueryReferenceIF getSex(String alias, String displayLabel)
  {

    com.runwaysdk.dataaccess.MdAttributeDAOIF mdAttributeIF = this.getComponentQuery().getMdAttributeROfromMap(dss.vector.solutions.entomology.assay.EfficacyAssay.SEX);

    return (dss.vector.solutions.ontology.TermQuery.TermQueryReferenceIF)this.getComponentQuery().internalAttributeFactory(dss.vector.solutions.entomology.assay.EfficacyAssay.SEX, mdAttributeIF, this, alias, displayLabel);

  }
  public dss.vector.solutions.ontology.TermQuery.TermQueryReferenceIF getSurfacePostion()
  {
    return getSurfacePostion(null);

  }
 
  public dss.vector.solutions.ontology.TermQuery.TermQueryReferenceIF getSurfacePostion(String alias)
  {

    com.runwaysdk.dataaccess.MdAttributeDAOIF mdAttributeIF = this.getComponentQuery().getMdAttributeROfromMap(dss.vector.solutions.entomology.assay.EfficacyAssay.SURFACEPOSTION);

    return (dss.vector.solutions.ontology.TermQuery.TermQueryReferenceIF)this.getComponentQuery().internalAttributeFactory(dss.vector.solutions.entomology.assay.EfficacyAssay.SURFACEPOSTION, mdAttributeIF, this, alias, null);

  }
 
  public dss.vector.solutions.ontology.TermQuery.TermQueryReferenceIF getSurfacePostion(String alias, String displayLabel)
  {

    com.runwaysdk.dataaccess.MdAttributeDAOIF mdAttributeIF = this.getComponentQuery().getMdAttributeROfromMap(dss.vector.solutions.entomology.assay.EfficacyAssay.SURFACEPOSTION);

    return (dss.vector.solutions.ontology.TermQuery.TermQueryReferenceIF)this.getComponentQuery().internalAttributeFactory(dss.vector.solutions.entomology.assay.EfficacyAssay.SURFACEPOSTION, mdAttributeIF, this, alias, displayLabel);

  }
  public dss.vector.solutions.ontology.TermQuery.TermQueryReferenceIF getTestMethod()
  {
    return getTestMethod(null);

  }
 
  public dss.vector.solutions.ontology.TermQuery.TermQueryReferenceIF getTestMethod(String alias)
  {

    com.runwaysdk.dataaccess.MdAttributeDAOIF mdAttributeIF = this.getComponentQuery().getMdAttributeROfromMap(dss.vector.solutions.entomology.assay.EfficacyAssay.TESTMETHOD);

    return (dss.vector.solutions.ontology.TermQuery.TermQueryReferenceIF)this.getComponentQuery().internalAttributeFactory(dss.vector.solutions.entomology.assay.EfficacyAssay.TESTMETHOD, mdAttributeIF, this, alias, null);

  }
 
  public dss.vector.solutions.ontology.TermQuery.TermQueryReferenceIF getTestMethod(String alias, String displayLabel)
  {

    com.runwaysdk.dataaccess.MdAttributeDAOIF mdAttributeIF = this.getComponentQuery().getMdAttributeROfromMap(dss.vector.solutions.entomology.assay.EfficacyAssay.TESTMETHOD);

    return (dss.vector.solutions.ontology.TermQuery.TermQueryReferenceIF)this.getComponentQuery().internalAttributeFactory(dss.vector.solutions.entomology.assay.EfficacyAssay.TESTMETHOD, mdAttributeIF, this, alias, displayLabel);

  }
  public com.runwaysdk.query.SelectableInteger getTimeOnSurface()
  {
    return getTimeOnSurface(null);

  }
 
  public com.runwaysdk.query.SelectableInteger getTimeOnSurface(String alias)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getComponentQuery().get(dss.vector.solutions.entomology.assay.EfficacyAssay.TIMEONSURFACE, alias, null);

  }
 
  public com.runwaysdk.query.SelectableInteger getTimeOnSurface(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getComponentQuery().get(dss.vector.solutions.entomology.assay.EfficacyAssay.TIMEONSURFACE, alias, displayLabel);

  }
  protected com.runwaysdk.query.AttributeReference referenceFactory( com.runwaysdk.dataaccess.MdAttributeRefDAOIF mdAttributeIF, String attributeNamespace, String definingTableName, String definingTableAlias,  com.runwaysdk.dataaccess.MdBusinessDAOIF referenceMdBusinessIF, String referenceTableAlias, com.runwaysdk.query.ComponentQuery rootQuery, java.util.Set<com.runwaysdk.query.Join> tableJoinSet, String userDefinedAlias, String userDefinedDisplayLabel)
  {
    String name = mdAttributeIF.definesAttribute();
    
    if (name.equals(dss.vector.solutions.entomology.assay.EfficacyAssay.GEOENTITY)) 
    {
       return new dss.vector.solutions.geo.generated.SurfaceQuery.SurfaceQueryReference((com.runwaysdk.dataaccess.MdAttributeRefDAOIF)mdAttributeIF, attributeNamespace, definingTableName, definingTableAlias, referenceMdBusinessIF, referenceTableAlias, rootQuery, tableJoinSet, userDefinedAlias, userDefinedDisplayLabel);
    }
    else if (name.equals(dss.vector.solutions.entomology.assay.EfficacyAssay.SEX)) 
    {
       return new dss.vector.solutions.ontology.TermQuery.TermQueryReference((com.runwaysdk.dataaccess.MdAttributeRefDAOIF)mdAttributeIF, attributeNamespace, definingTableName, definingTableAlias, referenceMdBusinessIF, referenceTableAlias, rootQuery, tableJoinSet, userDefinedAlias, userDefinedDisplayLabel);
    }
    else if (name.equals(dss.vector.solutions.entomology.assay.EfficacyAssay.SURFACEPOSTION)) 
    {
       return new dss.vector.solutions.ontology.TermQuery.TermQueryReference((com.runwaysdk.dataaccess.MdAttributeRefDAOIF)mdAttributeIF, attributeNamespace, definingTableName, definingTableAlias, referenceMdBusinessIF, referenceTableAlias, rootQuery, tableJoinSet, userDefinedAlias, userDefinedDisplayLabel);
    }
    else if (name.equals(dss.vector.solutions.entomology.assay.EfficacyAssay.TESTMETHOD)) 
    {
       return new dss.vector.solutions.ontology.TermQuery.TermQueryReference((com.runwaysdk.dataaccess.MdAttributeRefDAOIF)mdAttributeIF, attributeNamespace, definingTableName, definingTableAlias, referenceMdBusinessIF, referenceTableAlias, rootQuery, tableJoinSet, userDefinedAlias, userDefinedDisplayLabel);
    }
    else 
    {
      return super.referenceFactory(mdAttributeIF, attributeNamespace, definingTableName, definingTableAlias, referenceMdBusinessIF, referenceTableAlias, rootQuery, tableJoinSet, userDefinedAlias, userDefinedDisplayLabel);
    }
  }

  protected com.runwaysdk.query.AttributeStruct structFactory( com.runwaysdk.dataaccess.MdAttributeStructDAOIF mdAttributeIF, String attributeNamespace, String definingTableName, String definingTableAlias,  com.runwaysdk.dataaccess.MdStructDAOIF mdStructIF, String structTableAlias, com.runwaysdk.query.ComponentQuery rootQuery, java.util.Set<com.runwaysdk.query.Join> tableJoinSet, String userDefinedAlias, String userDefinedDisplayLabel)
  {
    String name = mdAttributeIF.definesAttribute();
    
    if (name.equals(dss.vector.solutions.entomology.assay.EfficacyAssay.AGERANGE)) 
    {
       return new dss.vector.solutions.entomology.assay.AdultAgeRangeQuery.AdultAgeRangeQueryStruct((com.runwaysdk.dataaccess.MdAttributeStructDAOIF)mdAttributeIF,  attributeNamespace, definingTableName, definingTableAlias, mdStructIF, structTableAlias, rootQuery, tableJoinSet, userDefinedAlias, userDefinedDisplayLabel);
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
  public com.runwaysdk.query.OIterator<? extends EfficacyAssay> getIterator()
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
    return new com.runwaysdk.business.BusinessIterator<EfficacyAssay>(this.getComponentQuery().getMdEntityIF(), columnInfoMap, results);
  }


/**
 * Interface that masks all type unsafe query methods and defines all type safe methods.
 * This type is used when a join is performed on this class as a reference.
 **/
  public interface EfficacyAssayQueryReferenceIF extends com.runwaysdk.generation.loader.Reloadable, dss.vector.solutions.entomology.assay.AbstractAssayQuery.AbstractAssayQueryReferenceIF
  {

    public dss.vector.solutions.entomology.assay.AdultAgeRangeQuery.AdultAgeRangeQueryStructIF getAgeRange();
    public dss.vector.solutions.entomology.assay.AdultAgeRangeQuery.AdultAgeRangeQueryStructIF getAgeRange(String alias);
    public dss.vector.solutions.entomology.assay.AdultAgeRangeQuery.AdultAgeRangeQueryStructIF getAgeRange(String alias, String displayLabel);
    public com.runwaysdk.query.SelectableChar getColonyName();
    public com.runwaysdk.query.SelectableChar getColonyName(String alias);
    public com.runwaysdk.query.SelectableChar getColonyName(String alias, String displayLabel);
    public com.runwaysdk.query.SelectableInteger getExposureTime();
    public com.runwaysdk.query.SelectableInteger getExposureTime(String alias);
    public com.runwaysdk.query.SelectableInteger getExposureTime(String alias, String displayLabel);
    public com.runwaysdk.query.SelectableInteger getFed();
    public com.runwaysdk.query.SelectableInteger getFed(String alias);
    public com.runwaysdk.query.SelectableInteger getFed(String alias, String displayLabel);
    public dss.vector.solutions.geo.generated.SurfaceQuery.SurfaceQueryReferenceIF getGeoEntity();
    public dss.vector.solutions.geo.generated.SurfaceQuery.SurfaceQueryReferenceIF getGeoEntity(String alias);
    public dss.vector.solutions.geo.generated.SurfaceQuery.SurfaceQueryReferenceIF getGeoEntity(String alias, String displayLabel);
    public com.runwaysdk.query.SelectableInteger getGravid();
    public com.runwaysdk.query.SelectableInteger getGravid(String alias);
    public com.runwaysdk.query.SelectableInteger getGravid(String alias, String displayLabel);
    public com.runwaysdk.query.SelectableInteger getHoldingTime();
    public com.runwaysdk.query.SelectableInteger getHoldingTime(String alias);
    public com.runwaysdk.query.SelectableInteger getHoldingTime(String alias, String displayLabel);
    public com.runwaysdk.query.SelectableFloat getMortality();
    public com.runwaysdk.query.SelectableFloat getMortality(String alias);
    public com.runwaysdk.query.SelectableFloat getMortality(String alias, String displayLabel);
    public com.runwaysdk.query.SelectableInteger getQuantityDead();
    public com.runwaysdk.query.SelectableInteger getQuantityDead(String alias);
    public com.runwaysdk.query.SelectableInteger getQuantityDead(String alias, String displayLabel);
    public com.runwaysdk.query.SelectableInteger getQuantityLive();
    public com.runwaysdk.query.SelectableInteger getQuantityLive(String alias);
    public com.runwaysdk.query.SelectableInteger getQuantityLive(String alias, String displayLabel);
    public com.runwaysdk.query.SelectableInteger getQuantityTested();
    public com.runwaysdk.query.SelectableInteger getQuantityTested(String alias);
    public com.runwaysdk.query.SelectableInteger getQuantityTested(String alias, String displayLabel);
    public dss.vector.solutions.ontology.TermQuery.TermQueryReferenceIF getSex();
    public dss.vector.solutions.ontology.TermQuery.TermQueryReferenceIF getSex(String alias);
    public dss.vector.solutions.ontology.TermQuery.TermQueryReferenceIF getSex(String alias, String displayLabel);
    public dss.vector.solutions.ontology.TermQuery.TermQueryReferenceIF getSurfacePostion();
    public dss.vector.solutions.ontology.TermQuery.TermQueryReferenceIF getSurfacePostion(String alias);
    public dss.vector.solutions.ontology.TermQuery.TermQueryReferenceIF getSurfacePostion(String alias, String displayLabel);
    public dss.vector.solutions.ontology.TermQuery.TermQueryReferenceIF getTestMethod();
    public dss.vector.solutions.ontology.TermQuery.TermQueryReferenceIF getTestMethod(String alias);
    public dss.vector.solutions.ontology.TermQuery.TermQueryReferenceIF getTestMethod(String alias, String displayLabel);
    public com.runwaysdk.query.SelectableInteger getTimeOnSurface();
    public com.runwaysdk.query.SelectableInteger getTimeOnSurface(String alias);
    public com.runwaysdk.query.SelectableInteger getTimeOnSurface(String alias, String displayLabel);

    public com.runwaysdk.query.BasicCondition EQ(dss.vector.solutions.entomology.assay.EfficacyAssay efficacyAssay);

    public com.runwaysdk.query.BasicCondition NE(dss.vector.solutions.entomology.assay.EfficacyAssay efficacyAssay);

  }

/**
 * Implements type safe query methods.
 * This type is used when a join is performed on this class as a reference.
 **/
  public static class EfficacyAssayQueryReference extends dss.vector.solutions.entomology.assay.AbstractAssayQuery.AbstractAssayQueryReference
 implements EfficacyAssayQueryReferenceIF
, com.runwaysdk.generation.loader.Reloadable
  {
private static final long serialVersionUID = -859403134;

  public EfficacyAssayQueryReference(com.runwaysdk.dataaccess.MdAttributeRefDAOIF mdAttributeIF, String attributeNamespace, String definingTableName, String definingTableAlias, com.runwaysdk.dataaccess.MdBusinessDAOIF referenceMdBusinessIF, String referenceTableAlias, com.runwaysdk.query.ComponentQuery rootQuery, java.util.Set<com.runwaysdk.query.Join> tableJoinSet, String alias, String displayLabel)
  {
    super(mdAttributeIF, attributeNamespace, definingTableName, definingTableAlias, referenceMdBusinessIF, referenceTableAlias, rootQuery, tableJoinSet, alias, displayLabel);

  }


    public com.runwaysdk.query.BasicCondition EQ(dss.vector.solutions.entomology.assay.EfficacyAssay efficacyAssay)
    {
      return this.EQ(efficacyAssay.getId());
    }

    public com.runwaysdk.query.BasicCondition NE(dss.vector.solutions.entomology.assay.EfficacyAssay efficacyAssay)
    {
      return this.NE(efficacyAssay.getId());
    }

  public dss.vector.solutions.entomology.assay.AdultAgeRangeQuery.AdultAgeRangeQueryStructIF getAgeRange()
  {
    return getAgeRange(null);

  }
 
  public dss.vector.solutions.entomology.assay.AdultAgeRangeQuery.AdultAgeRangeQueryStructIF getAgeRange(String alias)
  {
    return (dss.vector.solutions.entomology.assay.AdultAgeRangeQuery.AdultAgeRangeQueryStructIF)this.attributeFactory(dss.vector.solutions.entomology.assay.EfficacyAssay.AGERANGE, com.runwaysdk.system.metadata.MdAttributeStruct.CLASS, alias, null);

  }
 
  public dss.vector.solutions.entomology.assay.AdultAgeRangeQuery.AdultAgeRangeQueryStructIF getAgeRange(String alias, String displayLabel)
  {
    return (dss.vector.solutions.entomology.assay.AdultAgeRangeQuery.AdultAgeRangeQueryStructIF)this.attributeFactory(dss.vector.solutions.entomology.assay.EfficacyAssay.AGERANGE, com.runwaysdk.system.metadata.MdAttributeStruct.CLASS, alias, displayLabel);

  }
  public com.runwaysdk.query.SelectableChar getColonyName()
  {
    return getColonyName(null);

  }
 
  public com.runwaysdk.query.SelectableChar getColonyName(String alias)
  {
    return (com.runwaysdk.query.SelectableChar)this.get(dss.vector.solutions.entomology.assay.EfficacyAssay.COLONYNAME, alias, null);

  }
 
  public com.runwaysdk.query.SelectableChar getColonyName(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableChar)this.get(dss.vector.solutions.entomology.assay.EfficacyAssay.COLONYNAME, alias, displayLabel);

  }
  public com.runwaysdk.query.SelectableInteger getExposureTime()
  {
    return getExposureTime(null);

  }
 
  public com.runwaysdk.query.SelectableInteger getExposureTime(String alias)
  {
    return (com.runwaysdk.query.SelectableInteger)this.get(dss.vector.solutions.entomology.assay.EfficacyAssay.EXPOSURETIME, alias, null);

  }
 
  public com.runwaysdk.query.SelectableInteger getExposureTime(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableInteger)this.get(dss.vector.solutions.entomology.assay.EfficacyAssay.EXPOSURETIME, alias, displayLabel);

  }
  public com.runwaysdk.query.SelectableInteger getFed()
  {
    return getFed(null);

  }
 
  public com.runwaysdk.query.SelectableInteger getFed(String alias)
  {
    return (com.runwaysdk.query.SelectableInteger)this.get(dss.vector.solutions.entomology.assay.EfficacyAssay.FED, alias, null);

  }
 
  public com.runwaysdk.query.SelectableInteger getFed(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableInteger)this.get(dss.vector.solutions.entomology.assay.EfficacyAssay.FED, alias, displayLabel);

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
  public com.runwaysdk.query.SelectableInteger getGravid()
  {
    return getGravid(null);

  }
 
  public com.runwaysdk.query.SelectableInteger getGravid(String alias)
  {
    return (com.runwaysdk.query.SelectableInteger)this.get(dss.vector.solutions.entomology.assay.EfficacyAssay.GRAVID, alias, null);

  }
 
  public com.runwaysdk.query.SelectableInteger getGravid(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableInteger)this.get(dss.vector.solutions.entomology.assay.EfficacyAssay.GRAVID, alias, displayLabel);

  }
  public com.runwaysdk.query.SelectableInteger getHoldingTime()
  {
    return getHoldingTime(null);

  }
 
  public com.runwaysdk.query.SelectableInteger getHoldingTime(String alias)
  {
    return (com.runwaysdk.query.SelectableInteger)this.get(dss.vector.solutions.entomology.assay.EfficacyAssay.HOLDINGTIME, alias, null);

  }
 
  public com.runwaysdk.query.SelectableInteger getHoldingTime(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableInteger)this.get(dss.vector.solutions.entomology.assay.EfficacyAssay.HOLDINGTIME, alias, displayLabel);

  }
  public com.runwaysdk.query.SelectableFloat getMortality()
  {
    return getMortality(null);

  }
 
  public com.runwaysdk.query.SelectableFloat getMortality(String alias)
  {
    return (com.runwaysdk.query.SelectableFloat)this.get(dss.vector.solutions.entomology.assay.EfficacyAssay.MORTALITY, alias, null);

  }
 
  public com.runwaysdk.query.SelectableFloat getMortality(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableFloat)this.get(dss.vector.solutions.entomology.assay.EfficacyAssay.MORTALITY, alias, displayLabel);

  }
  public com.runwaysdk.query.SelectableInteger getQuantityDead()
  {
    return getQuantityDead(null);

  }
 
  public com.runwaysdk.query.SelectableInteger getQuantityDead(String alias)
  {
    return (com.runwaysdk.query.SelectableInteger)this.get(dss.vector.solutions.entomology.assay.EfficacyAssay.QUANTITYDEAD, alias, null);

  }
 
  public com.runwaysdk.query.SelectableInteger getQuantityDead(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableInteger)this.get(dss.vector.solutions.entomology.assay.EfficacyAssay.QUANTITYDEAD, alias, displayLabel);

  }
  public com.runwaysdk.query.SelectableInteger getQuantityLive()
  {
    return getQuantityLive(null);

  }
 
  public com.runwaysdk.query.SelectableInteger getQuantityLive(String alias)
  {
    return (com.runwaysdk.query.SelectableInteger)this.get(dss.vector.solutions.entomology.assay.EfficacyAssay.QUANTITYLIVE, alias, null);

  }
 
  public com.runwaysdk.query.SelectableInteger getQuantityLive(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableInteger)this.get(dss.vector.solutions.entomology.assay.EfficacyAssay.QUANTITYLIVE, alias, displayLabel);

  }
  public com.runwaysdk.query.SelectableInteger getQuantityTested()
  {
    return getQuantityTested(null);

  }
 
  public com.runwaysdk.query.SelectableInteger getQuantityTested(String alias)
  {
    return (com.runwaysdk.query.SelectableInteger)this.get(dss.vector.solutions.entomology.assay.EfficacyAssay.QUANTITYTESTED, alias, null);

  }
 
  public com.runwaysdk.query.SelectableInteger getQuantityTested(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableInteger)this.get(dss.vector.solutions.entomology.assay.EfficacyAssay.QUANTITYTESTED, alias, displayLabel);

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
  public com.runwaysdk.query.SelectableInteger getTimeOnSurface()
  {
    return getTimeOnSurface(null);

  }
 
  public com.runwaysdk.query.SelectableInteger getTimeOnSurface(String alias)
  {
    return (com.runwaysdk.query.SelectableInteger)this.get(dss.vector.solutions.entomology.assay.EfficacyAssay.TIMEONSURFACE, alias, null);

  }
 
  public com.runwaysdk.query.SelectableInteger getTimeOnSurface(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableInteger)this.get(dss.vector.solutions.entomology.assay.EfficacyAssay.TIMEONSURFACE, alias, displayLabel);

  }
  protected com.runwaysdk.query.AttributeReference referenceFactory( com.runwaysdk.dataaccess.MdAttributeRefDAOIF mdAttributeIF, String attributeNamespace, String definingTableName, String definingTableAlias,  com.runwaysdk.dataaccess.MdBusinessDAOIF referenceMdBusinessIF, String referenceTableAlias, com.runwaysdk.query.ComponentQuery rootQuery, java.util.Set<com.runwaysdk.query.Join> tableJoinSet, String userDefinedAlias, String userDefinedDisplayLabel)
  {
    String name = mdAttributeIF.definesAttribute();
    
    if (name.equals(dss.vector.solutions.entomology.assay.EfficacyAssay.GEOENTITY)) 
    {
       return new dss.vector.solutions.geo.generated.SurfaceQuery.SurfaceQueryReference((com.runwaysdk.dataaccess.MdAttributeRefDAOIF)mdAttributeIF, attributeNamespace, definingTableName, definingTableAlias, referenceMdBusinessIF, referenceTableAlias, rootQuery, tableJoinSet, userDefinedAlias, userDefinedDisplayLabel);
    }
    else if (name.equals(dss.vector.solutions.entomology.assay.EfficacyAssay.SEX)) 
    {
       return new dss.vector.solutions.ontology.TermQuery.TermQueryReference((com.runwaysdk.dataaccess.MdAttributeRefDAOIF)mdAttributeIF, attributeNamespace, definingTableName, definingTableAlias, referenceMdBusinessIF, referenceTableAlias, rootQuery, tableJoinSet, userDefinedAlias, userDefinedDisplayLabel);
    }
    else if (name.equals(dss.vector.solutions.entomology.assay.EfficacyAssay.SURFACEPOSTION)) 
    {
       return new dss.vector.solutions.ontology.TermQuery.TermQueryReference((com.runwaysdk.dataaccess.MdAttributeRefDAOIF)mdAttributeIF, attributeNamespace, definingTableName, definingTableAlias, referenceMdBusinessIF, referenceTableAlias, rootQuery, tableJoinSet, userDefinedAlias, userDefinedDisplayLabel);
    }
    else if (name.equals(dss.vector.solutions.entomology.assay.EfficacyAssay.TESTMETHOD)) 
    {
       return new dss.vector.solutions.ontology.TermQuery.TermQueryReference((com.runwaysdk.dataaccess.MdAttributeRefDAOIF)mdAttributeIF, attributeNamespace, definingTableName, definingTableAlias, referenceMdBusinessIF, referenceTableAlias, rootQuery, tableJoinSet, userDefinedAlias, userDefinedDisplayLabel);
    }
    else 
    {
      return super.referenceFactory(mdAttributeIF, attributeNamespace, definingTableName, definingTableAlias, referenceMdBusinessIF, referenceTableAlias, rootQuery, tableJoinSet, userDefinedAlias, userDefinedDisplayLabel);
    }
  }

  protected com.runwaysdk.query.AttributeStruct structFactory( com.runwaysdk.dataaccess.MdAttributeStructDAOIF mdAttributeIF, String attributeNamespace, String definingTableName, String definingTableAlias,  com.runwaysdk.dataaccess.MdStructDAOIF mdStructIF, String structTableAlias, com.runwaysdk.query.ComponentQuery rootQuery, java.util.Set<com.runwaysdk.query.Join> tableJoinSet, String userDefinedAlias, String userDefinedDisplayLabel)
  {
    String name = mdAttributeIF.definesAttribute();
    
    if (name.equals(dss.vector.solutions.entomology.assay.EfficacyAssay.AGERANGE)) 
    {
       return new dss.vector.solutions.entomology.assay.AdultAgeRangeQuery.AdultAgeRangeQueryStruct((com.runwaysdk.dataaccess.MdAttributeStructDAOIF)mdAttributeIF,  attributeNamespace, definingTableName, definingTableAlias, mdStructIF, structTableAlias, rootQuery, tableJoinSet, userDefinedAlias, userDefinedDisplayLabel);
    }
    else 
    {
      return super.structFactory(mdAttributeIF, attributeNamespace, definingTableName, definingTableAlias, mdStructIF, structTableAlias, rootQuery, tableJoinSet, userDefinedAlias, userDefinedDisplayLabel);
    }
  }

  }
}
