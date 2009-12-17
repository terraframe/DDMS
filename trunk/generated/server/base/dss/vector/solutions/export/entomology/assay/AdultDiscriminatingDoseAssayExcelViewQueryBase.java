package dss.vector.solutions.export.entomology.assay;

@com.terraframe.mojo.business.ClassSignature(hash = -596191603)
/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to AdultDiscriminatingDoseAssayExcelView.java
 *
 * @author Autogenerated by TerraFrame
 */
public  abstract  class AdultDiscriminatingDoseAssayExcelViewQueryBase extends com.terraframe.mojo.query.GeneratedViewQuery
 implements com.terraframe.mojo.generation.loader.Reloadable
{
private static final long serialVersionUID = -596191603;

  public AdultDiscriminatingDoseAssayExcelViewQueryBase(com.terraframe.mojo.query.QueryFactory componentQueryFactory)
  {
    super(componentQueryFactory);
  }

  public AdultDiscriminatingDoseAssayExcelViewQueryBase(com.terraframe.mojo.query.QueryFactory componentQueryFactory, com.terraframe.mojo.query.ViewQueryBuilder viewQueryBuilder)
  {
    super(componentQueryFactory, viewQueryBuilder);
  }
  public String getClassType()
  {
    return dss.vector.solutions.export.entomology.assay.AdultDiscriminatingDoseAssayExcelView.CLASS;
  }
  public dss.vector.solutions.entomology.assay.AdultAgeRangeQuery.AdultAgeRangeQueryStructIF getAgeRange()
  {
    return getAgeRange(null);

  }
 
  public dss.vector.solutions.entomology.assay.AdultAgeRangeQuery.AdultAgeRangeQueryStructIF getAgeRange(String alias)
  {
    return (dss.vector.solutions.entomology.assay.AdultAgeRangeQuery.AdultAgeRangeQueryStructIF)this.getSelectable(dss.vector.solutions.export.entomology.assay.AdultDiscriminatingDoseAssayExcelView.AGERANGE, alias, null);

  }
 
  public dss.vector.solutions.entomology.assay.AdultAgeRangeQuery.AdultAgeRangeQueryStructIF getAgeRange(String alias, String displayLabel)
  {
    return (dss.vector.solutions.entomology.assay.AdultAgeRangeQuery.AdultAgeRangeQueryStructIF)this.getSelectable(dss.vector.solutions.export.entomology.assay.AdultDiscriminatingDoseAssayExcelView.AGERANGE, alias, displayLabel);

  }
 
  public com.terraframe.mojo.query.AttributeChar getCollectionMethod()
  {
    return getCollectionMethod(null);

  }
 
  public com.terraframe.mojo.query.AttributeChar getCollectionMethod(String alias)
  {
    return (com.terraframe.mojo.query.AttributeChar)this.getSelectable(dss.vector.solutions.export.entomology.assay.AdultDiscriminatingDoseAssayExcelView.COLLECTIONMETHOD, alias, null);

  }
 
  public com.terraframe.mojo.query.AttributeChar getCollectionMethod(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.AttributeChar)this.getSelectable(dss.vector.solutions.export.entomology.assay.AdultDiscriminatingDoseAssayExcelView.COLLECTIONMETHOD, alias, displayLabel);

  }
 
  public com.terraframe.mojo.query.AttributeFloat getControlTestMortality()
  {
    return getControlTestMortality(null);

  }
 
  public com.terraframe.mojo.query.AttributeFloat getControlTestMortality(String alias)
  {
    return (com.terraframe.mojo.query.AttributeFloat)this.getSelectable(dss.vector.solutions.export.entomology.assay.AdultDiscriminatingDoseAssayExcelView.CONTROLTESTMORTALITY, alias, null);

  }
 
  public com.terraframe.mojo.query.AttributeFloat getControlTestMortality(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.AttributeFloat)this.getSelectable(dss.vector.solutions.export.entomology.assay.AdultDiscriminatingDoseAssayExcelView.CONTROLTESTMORTALITY, alias, displayLabel);

  }
 
  public com.terraframe.mojo.query.AttributeMoment getDateCollected()
  {
    return getDateCollected(null);

  }
 
  public com.terraframe.mojo.query.AttributeMoment getDateCollected(String alias)
  {
    return (com.terraframe.mojo.query.AttributeMoment)this.getSelectable(dss.vector.solutions.export.entomology.assay.AdultDiscriminatingDoseAssayExcelView.DATECOLLECTED, alias, null);

  }
 
  public com.terraframe.mojo.query.AttributeMoment getDateCollected(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.AttributeMoment)this.getSelectable(dss.vector.solutions.export.entomology.assay.AdultDiscriminatingDoseAssayExcelView.DATECOLLECTED, alias, displayLabel);

  }
 
  public com.terraframe.mojo.query.AttributeInteger getExposureTime()
  {
    return getExposureTime(null);

  }
 
  public com.terraframe.mojo.query.AttributeInteger getExposureTime(String alias)
  {
    return (com.terraframe.mojo.query.AttributeInteger)this.getSelectable(dss.vector.solutions.export.entomology.assay.AdultDiscriminatingDoseAssayExcelView.EXPOSURETIME, alias, null);

  }
 
  public com.terraframe.mojo.query.AttributeInteger getExposureTime(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.AttributeInteger)this.getSelectable(dss.vector.solutions.export.entomology.assay.AdultDiscriminatingDoseAssayExcelView.EXPOSURETIME, alias, displayLabel);

  }
 
  public com.terraframe.mojo.query.AttributeInteger getFed()
  {
    return getFed(null);

  }
 
  public com.terraframe.mojo.query.AttributeInteger getFed(String alias)
  {
    return (com.terraframe.mojo.query.AttributeInteger)this.getSelectable(dss.vector.solutions.export.entomology.assay.AdultDiscriminatingDoseAssayExcelView.FED, alias, null);

  }
 
  public com.terraframe.mojo.query.AttributeInteger getFed(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.AttributeInteger)this.getSelectable(dss.vector.solutions.export.entomology.assay.AdultDiscriminatingDoseAssayExcelView.FED, alias, displayLabel);

  }
 
  public com.terraframe.mojo.query.AttributeChar getGeneration()
  {
    return getGeneration(null);

  }
 
  public com.terraframe.mojo.query.AttributeChar getGeneration(String alias)
  {
    return (com.terraframe.mojo.query.AttributeChar)this.getSelectable(dss.vector.solutions.export.entomology.assay.AdultDiscriminatingDoseAssayExcelView.GENERATION, alias, null);

  }
 
  public com.terraframe.mojo.query.AttributeChar getGeneration(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.AttributeChar)this.getSelectable(dss.vector.solutions.export.entomology.assay.AdultDiscriminatingDoseAssayExcelView.GENERATION, alias, displayLabel);

  }
 
  public dss.vector.solutions.geo.generated.GeoEntityQuery.GeoEntityQueryReferenceIF getGeoEntity()
  {
    return getGeoEntity(null);

  }
 
  public dss.vector.solutions.geo.generated.GeoEntityQuery.GeoEntityQueryReferenceIF getGeoEntity(String alias)
  {

    return (dss.vector.solutions.geo.generated.GeoEntityQuery.GeoEntityQueryReferenceIF)this.getSelectable(dss.vector.solutions.export.entomology.assay.AdultDiscriminatingDoseAssayExcelView.GEOENTITY, alias, null);

  }
 
  public dss.vector.solutions.geo.generated.GeoEntityQuery.GeoEntityQueryReferenceIF getGeoEntity(String alias, String displayLabel)
  {

    return (dss.vector.solutions.geo.generated.GeoEntityQuery.GeoEntityQueryReferenceIF)this.getSelectable(dss.vector.solutions.export.entomology.assay.AdultDiscriminatingDoseAssayExcelView.GEOENTITY, alias, displayLabel);

  }
  public com.terraframe.mojo.query.AttributeInteger getGravid()
  {
    return getGravid(null);

  }
 
  public com.terraframe.mojo.query.AttributeInteger getGravid(String alias)
  {
    return (com.terraframe.mojo.query.AttributeInteger)this.getSelectable(dss.vector.solutions.export.entomology.assay.AdultDiscriminatingDoseAssayExcelView.GRAVID, alias, null);

  }
 
  public com.terraframe.mojo.query.AttributeInteger getGravid(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.AttributeInteger)this.getSelectable(dss.vector.solutions.export.entomology.assay.AdultDiscriminatingDoseAssayExcelView.GRAVID, alias, displayLabel);

  }
 
  public com.terraframe.mojo.query.AttributeInteger getHoldingTime()
  {
    return getHoldingTime(null);

  }
 
  public com.terraframe.mojo.query.AttributeInteger getHoldingTime(String alias)
  {
    return (com.terraframe.mojo.query.AttributeInteger)this.getSelectable(dss.vector.solutions.export.entomology.assay.AdultDiscriminatingDoseAssayExcelView.HOLDINGTIME, alias, null);

  }
 
  public com.terraframe.mojo.query.AttributeInteger getHoldingTime(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.AttributeInteger)this.getSelectable(dss.vector.solutions.export.entomology.assay.AdultDiscriminatingDoseAssayExcelView.HOLDINGTIME, alias, displayLabel);

  }
 
  public com.terraframe.mojo.query.AttributeChar getId()
  {
    return getId(null);

  }
 
  public com.terraframe.mojo.query.AttributeChar getId(String alias)
  {
    return (com.terraframe.mojo.query.AttributeChar)this.getSelectable(dss.vector.solutions.export.entomology.assay.AdultDiscriminatingDoseAssayExcelView.ID, alias, null);

  }
 
  public com.terraframe.mojo.query.AttributeChar getId(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.AttributeChar)this.getSelectable(dss.vector.solutions.export.entomology.assay.AdultDiscriminatingDoseAssayExcelView.ID, alias, displayLabel);

  }
 
  public com.terraframe.mojo.query.AttributeChar getIdentificationMethod()
  {
    return getIdentificationMethod(null);

  }
 
  public com.terraframe.mojo.query.AttributeChar getIdentificationMethod(String alias)
  {
    return (com.terraframe.mojo.query.AttributeChar)this.getSelectable(dss.vector.solutions.export.entomology.assay.AdultDiscriminatingDoseAssayExcelView.IDENTIFICATIONMETHOD, alias, null);

  }
 
  public com.terraframe.mojo.query.AttributeChar getIdentificationMethod(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.AttributeChar)this.getSelectable(dss.vector.solutions.export.entomology.assay.AdultDiscriminatingDoseAssayExcelView.IDENTIFICATIONMETHOD, alias, displayLabel);

  }
 
  public com.terraframe.mojo.query.AttributeChar getInsecticideActiveIngredient()
  {
    return getInsecticideActiveIngredient(null);

  }
 
  public com.terraframe.mojo.query.AttributeChar getInsecticideActiveIngredient(String alias)
  {
    return (com.terraframe.mojo.query.AttributeChar)this.getSelectable(dss.vector.solutions.export.entomology.assay.AdultDiscriminatingDoseAssayExcelView.INSECTICIDEACTIVEINGREDIENT, alias, null);

  }
 
  public com.terraframe.mojo.query.AttributeChar getInsecticideActiveIngredient(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.AttributeChar)this.getSelectable(dss.vector.solutions.export.entomology.assay.AdultDiscriminatingDoseAssayExcelView.INSECTICIDEACTIVEINGREDIENT, alias, displayLabel);

  }
 
  public com.terraframe.mojo.query.AttributeDouble getInsecticideAmount()
  {
    return getInsecticideAmount(null);

  }
 
  public com.terraframe.mojo.query.AttributeDouble getInsecticideAmount(String alias)
  {
    return (com.terraframe.mojo.query.AttributeDouble)this.getSelectable(dss.vector.solutions.export.entomology.assay.AdultDiscriminatingDoseAssayExcelView.INSECTICIDEAMOUNT, alias, null);

  }
 
  public com.terraframe.mojo.query.AttributeDouble getInsecticideAmount(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.AttributeDouble)this.getSelectable(dss.vector.solutions.export.entomology.assay.AdultDiscriminatingDoseAssayExcelView.INSECTICIDEAMOUNT, alias, displayLabel);

  }
 
  public com.terraframe.mojo.query.AttributeChar getInsecticideUnits()
  {
    return getInsecticideUnits(null);

  }
 
  public com.terraframe.mojo.query.AttributeChar getInsecticideUnits(String alias)
  {
    return (com.terraframe.mojo.query.AttributeChar)this.getSelectable(dss.vector.solutions.export.entomology.assay.AdultDiscriminatingDoseAssayExcelView.INSECTICIDEUNITS, alias, null);

  }
 
  public com.terraframe.mojo.query.AttributeChar getInsecticideUnits(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.AttributeChar)this.getSelectable(dss.vector.solutions.export.entomology.assay.AdultDiscriminatingDoseAssayExcelView.INSECTICIDEUNITS, alias, displayLabel);

  }
 
  public com.terraframe.mojo.query.AttributeBoolean getIsofemale()
  {
    return getIsofemale(null);

  }
 
  public com.terraframe.mojo.query.AttributeBoolean getIsofemale(String alias)
  {
    return (com.terraframe.mojo.query.AttributeBoolean)this.getSelectable(dss.vector.solutions.export.entomology.assay.AdultDiscriminatingDoseAssayExcelView.ISOFEMALE, alias, null);

  }
 
  public com.terraframe.mojo.query.AttributeBoolean getIsofemale(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.AttributeBoolean)this.getSelectable(dss.vector.solutions.export.entomology.assay.AdultDiscriminatingDoseAssayExcelView.ISOFEMALE, alias, displayLabel);

  }
 
  public com.terraframe.mojo.query.AttributeInteger getQuantityDead()
  {
    return getQuantityDead(null);

  }
 
  public com.terraframe.mojo.query.AttributeInteger getQuantityDead(String alias)
  {
    return (com.terraframe.mojo.query.AttributeInteger)this.getSelectable(dss.vector.solutions.export.entomology.assay.AdultDiscriminatingDoseAssayExcelView.QUANTITYDEAD, alias, null);

  }
 
  public com.terraframe.mojo.query.AttributeInteger getQuantityDead(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.AttributeInteger)this.getSelectable(dss.vector.solutions.export.entomology.assay.AdultDiscriminatingDoseAssayExcelView.QUANTITYDEAD, alias, displayLabel);

  }
 
  public com.terraframe.mojo.query.AttributeInteger getQuantityTested()
  {
    return getQuantityTested(null);

  }
 
  public com.terraframe.mojo.query.AttributeInteger getQuantityTested(String alias)
  {
    return (com.terraframe.mojo.query.AttributeInteger)this.getSelectable(dss.vector.solutions.export.entomology.assay.AdultDiscriminatingDoseAssayExcelView.QUANTITYTESTED, alias, null);

  }
 
  public com.terraframe.mojo.query.AttributeInteger getQuantityTested(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.AttributeInteger)this.getSelectable(dss.vector.solutions.export.entomology.assay.AdultDiscriminatingDoseAssayExcelView.QUANTITYTESTED, alias, displayLabel);

  }
 
  public com.terraframe.mojo.query.AttributeChar getSex()
  {
    return getSex(null);

  }
 
  public com.terraframe.mojo.query.AttributeChar getSex(String alias)
  {
    return (com.terraframe.mojo.query.AttributeChar)this.getSelectable(dss.vector.solutions.export.entomology.assay.AdultDiscriminatingDoseAssayExcelView.SEX, alias, null);

  }
 
  public com.terraframe.mojo.query.AttributeChar getSex(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.AttributeChar)this.getSelectable(dss.vector.solutions.export.entomology.assay.AdultDiscriminatingDoseAssayExcelView.SEX, alias, displayLabel);

  }
 
  public com.terraframe.mojo.query.AttributeChar getSpecie()
  {
    return getSpecie(null);

  }
 
  public com.terraframe.mojo.query.AttributeChar getSpecie(String alias)
  {
    return (com.terraframe.mojo.query.AttributeChar)this.getSelectable(dss.vector.solutions.export.entomology.assay.AdultDiscriminatingDoseAssayExcelView.SPECIE, alias, null);

  }
 
  public com.terraframe.mojo.query.AttributeChar getSpecie(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.AttributeChar)this.getSelectable(dss.vector.solutions.export.entomology.assay.AdultDiscriminatingDoseAssayExcelView.SPECIE, alias, displayLabel);

  }
 
  public com.terraframe.mojo.query.AttributeMoment getTestDate()
  {
    return getTestDate(null);

  }
 
  public com.terraframe.mojo.query.AttributeMoment getTestDate(String alias)
  {
    return (com.terraframe.mojo.query.AttributeMoment)this.getSelectable(dss.vector.solutions.export.entomology.assay.AdultDiscriminatingDoseAssayExcelView.TESTDATE, alias, null);

  }
 
  public com.terraframe.mojo.query.AttributeMoment getTestDate(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.AttributeMoment)this.getSelectable(dss.vector.solutions.export.entomology.assay.AdultDiscriminatingDoseAssayExcelView.TESTDATE, alias, displayLabel);

  }
 
  public com.terraframe.mojo.query.AttributeChar getTestMethod()
  {
    return getTestMethod(null);

  }
 
  public com.terraframe.mojo.query.AttributeChar getTestMethod(String alias)
  {
    return (com.terraframe.mojo.query.AttributeChar)this.getSelectable(dss.vector.solutions.export.entomology.assay.AdultDiscriminatingDoseAssayExcelView.TESTMETHOD, alias, null);

  }
 
  public com.terraframe.mojo.query.AttributeChar getTestMethod(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.AttributeChar)this.getSelectable(dss.vector.solutions.export.entomology.assay.AdultDiscriminatingDoseAssayExcelView.TESTMETHOD, alias, displayLabel);

  }
 
  /**  
   * Returns an iterator of Business objects that match the query criteria specified
   * on this query object. 
   * @return iterator of Business objects that match the query criteria specified
   * on this query object.
   */
  @java.lang.SuppressWarnings("unchecked")
  public com.terraframe.mojo.query.OIterator<? extends AdultDiscriminatingDoseAssayExcelView> getIterator()
  {
    com.terraframe.mojo.query.ValueIterator valueIterator;
    if (_pageSize != null && _pageNumber != null)
    {
      valueIterator = (com.terraframe.mojo.query.ValueIterator<com.terraframe.mojo.dataaccess.ValueObject>)this.getComponentQuery().getIterator(_pageSize, _pageNumber);
    }
    else
    {
      valueIterator = (com.terraframe.mojo.query.ValueIterator<com.terraframe.mojo.dataaccess.ValueObject>)this.getComponentQuery().getIterator();
    }
    return new com.terraframe.mojo.query.ViewIterator<AdultDiscriminatingDoseAssayExcelView>(this.getMdClassIF(), valueIterator);
  }

}
