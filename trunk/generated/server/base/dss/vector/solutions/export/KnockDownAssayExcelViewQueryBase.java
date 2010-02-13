package dss.vector.solutions.export;

@com.terraframe.mojo.business.ClassSignature(hash = -1010414065)
/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to KnockDownAssayExcelView.java
 *
 * @author Autogenerated by TerraFrame
 */
public  abstract  class KnockDownAssayExcelViewQueryBase extends com.terraframe.mojo.query.GeneratedViewQuery
 implements com.terraframe.mojo.generation.loader.Reloadable
{
private static final long serialVersionUID = -1010414065;

  public KnockDownAssayExcelViewQueryBase(com.terraframe.mojo.query.QueryFactory componentQueryFactory)
  {
    super(componentQueryFactory);
  }

  public KnockDownAssayExcelViewQueryBase(com.terraframe.mojo.query.QueryFactory componentQueryFactory, com.terraframe.mojo.query.ViewQueryBuilder viewQueryBuilder)
  {
    super(componentQueryFactory, viewQueryBuilder);
  }
  public String getClassType()
  {
    return dss.vector.solutions.export.KnockDownAssayExcelView.CLASS;
  }
  public dss.vector.solutions.entomology.assay.AdultAgeRangeQuery.AdultAgeRangeQueryStructIF getAgeRange()
  {
    return getAgeRange(null);

  }
 
  public dss.vector.solutions.entomology.assay.AdultAgeRangeQuery.AdultAgeRangeQueryStructIF getAgeRange(String alias)
  {
    return (dss.vector.solutions.entomology.assay.AdultAgeRangeQuery.AdultAgeRangeQueryStructIF)this.getSelectable(dss.vector.solutions.export.KnockDownAssayExcelView.AGERANGE, alias, null);

  }
 
  public dss.vector.solutions.entomology.assay.AdultAgeRangeQuery.AdultAgeRangeQueryStructIF getAgeRange(String alias, String displayLabel)
  {
    return (dss.vector.solutions.entomology.assay.AdultAgeRangeQuery.AdultAgeRangeQueryStructIF)this.getSelectable(dss.vector.solutions.export.KnockDownAssayExcelView.AGERANGE, alias, displayLabel);

  }
 
  public com.terraframe.mojo.query.SelectableSingleChar getCollectionMethod()
  {
    return getCollectionMethod(null);

  }
 
  public com.terraframe.mojo.query.SelectableSingleChar getCollectionMethod(String alias)
  {
    return (com.terraframe.mojo.query.SelectableSingleChar)this.getSelectable(dss.vector.solutions.export.KnockDownAssayExcelView.COLLECTIONMETHOD, alias, null);

  }
 
  public com.terraframe.mojo.query.SelectableSingleChar getCollectionMethod(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.SelectableSingleChar)this.getSelectable(dss.vector.solutions.export.KnockDownAssayExcelView.COLLECTIONMETHOD, alias, displayLabel);

  }
 
  public com.terraframe.mojo.query.SelectableSingleMoment getDateCollected()
  {
    return getDateCollected(null);

  }
 
  public com.terraframe.mojo.query.SelectableSingleMoment getDateCollected(String alias)
  {
    return (com.terraframe.mojo.query.SelectableSingleMoment)this.getSelectable(dss.vector.solutions.export.KnockDownAssayExcelView.DATECOLLECTED, alias, null);

  }
 
  public com.terraframe.mojo.query.SelectableSingleMoment getDateCollected(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.SelectableSingleMoment)this.getSelectable(dss.vector.solutions.export.KnockDownAssayExcelView.DATECOLLECTED, alias, displayLabel);

  }
 
  public com.terraframe.mojo.query.SelectableSingleInteger getExposureTime()
  {
    return getExposureTime(null);

  }
 
  public com.terraframe.mojo.query.SelectableSingleInteger getExposureTime(String alias)
  {
    return (com.terraframe.mojo.query.SelectableSingleInteger)this.getSelectable(dss.vector.solutions.export.KnockDownAssayExcelView.EXPOSURETIME, alias, null);

  }
 
  public com.terraframe.mojo.query.SelectableSingleInteger getExposureTime(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.SelectableSingleInteger)this.getSelectable(dss.vector.solutions.export.KnockDownAssayExcelView.EXPOSURETIME, alias, displayLabel);

  }
 
  public com.terraframe.mojo.query.SelectableSingleInteger getFed()
  {
    return getFed(null);

  }
 
  public com.terraframe.mojo.query.SelectableSingleInteger getFed(String alias)
  {
    return (com.terraframe.mojo.query.SelectableSingleInteger)this.getSelectable(dss.vector.solutions.export.KnockDownAssayExcelView.FED, alias, null);

  }
 
  public com.terraframe.mojo.query.SelectableSingleInteger getFed(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.SelectableSingleInteger)this.getSelectable(dss.vector.solutions.export.KnockDownAssayExcelView.FED, alias, displayLabel);

  }
 
  public com.terraframe.mojo.query.SelectableSingleChar getGeneration()
  {
    return getGeneration(null);

  }
 
  public com.terraframe.mojo.query.SelectableSingleChar getGeneration(String alias)
  {
    return (com.terraframe.mojo.query.SelectableSingleChar)this.getSelectable(dss.vector.solutions.export.KnockDownAssayExcelView.GENERATION, alias, null);

  }
 
  public com.terraframe.mojo.query.SelectableSingleChar getGeneration(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.SelectableSingleChar)this.getSelectable(dss.vector.solutions.export.KnockDownAssayExcelView.GENERATION, alias, displayLabel);

  }
 
  public dss.vector.solutions.geo.generated.GeoEntityQuery.GeoEntityQueryReferenceIF getGeoEntity()
  {
    return getGeoEntity(null);

  }
 
  public dss.vector.solutions.geo.generated.GeoEntityQuery.GeoEntityQueryReferenceIF getGeoEntity(String alias)
  {

    return (dss.vector.solutions.geo.generated.GeoEntityQuery.GeoEntityQueryReferenceIF)this.getSelectable(dss.vector.solutions.export.KnockDownAssayExcelView.GEOENTITY, alias, null);

  }
 
  public dss.vector.solutions.geo.generated.GeoEntityQuery.GeoEntityQueryReferenceIF getGeoEntity(String alias, String displayLabel)
  {

    return (dss.vector.solutions.geo.generated.GeoEntityQuery.GeoEntityQueryReferenceIF)this.getSelectable(dss.vector.solutions.export.KnockDownAssayExcelView.GEOENTITY, alias, displayLabel);

  }
  public com.terraframe.mojo.query.SelectableSingleInteger getGravid()
  {
    return getGravid(null);

  }
 
  public com.terraframe.mojo.query.SelectableSingleInteger getGravid(String alias)
  {
    return (com.terraframe.mojo.query.SelectableSingleInteger)this.getSelectable(dss.vector.solutions.export.KnockDownAssayExcelView.GRAVID, alias, null);

  }
 
  public com.terraframe.mojo.query.SelectableSingleInteger getGravid(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.SelectableSingleInteger)this.getSelectable(dss.vector.solutions.export.KnockDownAssayExcelView.GRAVID, alias, displayLabel);

  }
 
  public com.terraframe.mojo.query.SelectableSingleChar getId()
  {
    return getId(null);

  }
 
  public com.terraframe.mojo.query.SelectableSingleChar getId(String alias)
  {
    return (com.terraframe.mojo.query.SelectableSingleChar)this.getSelectable(dss.vector.solutions.export.KnockDownAssayExcelView.ID, alias, null);

  }
 
  public com.terraframe.mojo.query.SelectableSingleChar getId(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.SelectableSingleChar)this.getSelectable(dss.vector.solutions.export.KnockDownAssayExcelView.ID, alias, displayLabel);

  }
 
  public com.terraframe.mojo.query.SelectableSingleChar getIdentificationMethod()
  {
    return getIdentificationMethod(null);

  }
 
  public com.terraframe.mojo.query.SelectableSingleChar getIdentificationMethod(String alias)
  {
    return (com.terraframe.mojo.query.SelectableSingleChar)this.getSelectable(dss.vector.solutions.export.KnockDownAssayExcelView.IDENTIFICATIONMETHOD, alias, null);

  }
 
  public com.terraframe.mojo.query.SelectableSingleChar getIdentificationMethod(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.SelectableSingleChar)this.getSelectable(dss.vector.solutions.export.KnockDownAssayExcelView.IDENTIFICATIONMETHOD, alias, displayLabel);

  }
 
  public com.terraframe.mojo.query.SelectableSingleChar getInsecticideActiveIngredient()
  {
    return getInsecticideActiveIngredient(null);

  }
 
  public com.terraframe.mojo.query.SelectableSingleChar getInsecticideActiveIngredient(String alias)
  {
    return (com.terraframe.mojo.query.SelectableSingleChar)this.getSelectable(dss.vector.solutions.export.KnockDownAssayExcelView.INSECTICIDEACTIVEINGREDIENT, alias, null);

  }
 
  public com.terraframe.mojo.query.SelectableSingleChar getInsecticideActiveIngredient(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.SelectableSingleChar)this.getSelectable(dss.vector.solutions.export.KnockDownAssayExcelView.INSECTICIDEACTIVEINGREDIENT, alias, displayLabel);

  }
 
  public com.terraframe.mojo.query.SelectableSingleDouble getInsecticideAmount()
  {
    return getInsecticideAmount(null);

  }
 
  public com.terraframe.mojo.query.SelectableSingleDouble getInsecticideAmount(String alias)
  {
    return (com.terraframe.mojo.query.SelectableSingleDouble)this.getSelectable(dss.vector.solutions.export.KnockDownAssayExcelView.INSECTICIDEAMOUNT, alias, null);

  }
 
  public com.terraframe.mojo.query.SelectableSingleDouble getInsecticideAmount(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.SelectableSingleDouble)this.getSelectable(dss.vector.solutions.export.KnockDownAssayExcelView.INSECTICIDEAMOUNT, alias, displayLabel);

  }
 
  public com.terraframe.mojo.query.SelectableSingleChar getInsecticideUnits()
  {
    return getInsecticideUnits(null);

  }
 
  public com.terraframe.mojo.query.SelectableSingleChar getInsecticideUnits(String alias)
  {
    return (com.terraframe.mojo.query.SelectableSingleChar)this.getSelectable(dss.vector.solutions.export.KnockDownAssayExcelView.INSECTICIDEUNITS, alias, null);

  }
 
  public com.terraframe.mojo.query.SelectableSingleChar getInsecticideUnits(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.SelectableSingleChar)this.getSelectable(dss.vector.solutions.export.KnockDownAssayExcelView.INSECTICIDEUNITS, alias, displayLabel);

  }
 
  public com.terraframe.mojo.query.SelectableSingleBoolean getIsofemale()
  {
    return getIsofemale(null);

  }
 
  public com.terraframe.mojo.query.SelectableSingleBoolean getIsofemale(String alias)
  {
    return (com.terraframe.mojo.query.SelectableSingleBoolean)this.getSelectable(dss.vector.solutions.export.KnockDownAssayExcelView.ISOFEMALE, alias, null);

  }
 
  public com.terraframe.mojo.query.SelectableSingleBoolean getIsofemale(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.SelectableSingleBoolean)this.getSelectable(dss.vector.solutions.export.KnockDownAssayExcelView.ISOFEMALE, alias, displayLabel);

  }
 
  public com.terraframe.mojo.query.SelectableSingleInteger getQuantityTested()
  {
    return getQuantityTested(null);

  }
 
  public com.terraframe.mojo.query.SelectableSingleInteger getQuantityTested(String alias)
  {
    return (com.terraframe.mojo.query.SelectableSingleInteger)this.getSelectable(dss.vector.solutions.export.KnockDownAssayExcelView.QUANTITYTESTED, alias, null);

  }
 
  public com.terraframe.mojo.query.SelectableSingleInteger getQuantityTested(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.SelectableSingleInteger)this.getSelectable(dss.vector.solutions.export.KnockDownAssayExcelView.QUANTITYTESTED, alias, displayLabel);

  }
 
  public com.terraframe.mojo.query.SelectableSingleChar getSex()
  {
    return getSex(null);

  }
 
  public com.terraframe.mojo.query.SelectableSingleChar getSex(String alias)
  {
    return (com.terraframe.mojo.query.SelectableSingleChar)this.getSelectable(dss.vector.solutions.export.KnockDownAssayExcelView.SEX, alias, null);

  }
 
  public com.terraframe.mojo.query.SelectableSingleChar getSex(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.SelectableSingleChar)this.getSelectable(dss.vector.solutions.export.KnockDownAssayExcelView.SEX, alias, displayLabel);

  }
 
  public com.terraframe.mojo.query.SelectableSingleChar getSpecie()
  {
    return getSpecie(null);

  }
 
  public com.terraframe.mojo.query.SelectableSingleChar getSpecie(String alias)
  {
    return (com.terraframe.mojo.query.SelectableSingleChar)this.getSelectable(dss.vector.solutions.export.KnockDownAssayExcelView.SPECIE, alias, null);

  }
 
  public com.terraframe.mojo.query.SelectableSingleChar getSpecie(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.SelectableSingleChar)this.getSelectable(dss.vector.solutions.export.KnockDownAssayExcelView.SPECIE, alias, displayLabel);

  }
 
  public com.terraframe.mojo.query.SelectableSingleMoment getTestDate()
  {
    return getTestDate(null);

  }
 
  public com.terraframe.mojo.query.SelectableSingleMoment getTestDate(String alias)
  {
    return (com.terraframe.mojo.query.SelectableSingleMoment)this.getSelectable(dss.vector.solutions.export.KnockDownAssayExcelView.TESTDATE, alias, null);

  }
 
  public com.terraframe.mojo.query.SelectableSingleMoment getTestDate(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.SelectableSingleMoment)this.getSelectable(dss.vector.solutions.export.KnockDownAssayExcelView.TESTDATE, alias, displayLabel);

  }
 
  public com.terraframe.mojo.query.SelectableSingleChar getTestMethod()
  {
    return getTestMethod(null);

  }
 
  public com.terraframe.mojo.query.SelectableSingleChar getTestMethod(String alias)
  {
    return (com.terraframe.mojo.query.SelectableSingleChar)this.getSelectable(dss.vector.solutions.export.KnockDownAssayExcelView.TESTMETHOD, alias, null);

  }
 
  public com.terraframe.mojo.query.SelectableSingleChar getTestMethod(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.SelectableSingleChar)this.getSelectable(dss.vector.solutions.export.KnockDownAssayExcelView.TESTMETHOD, alias, displayLabel);

  }
 
  /**  
   * Returns an iterator of Business objects that match the query criteria specified
   * on this query object. 
   * @return iterator of Business objects that match the query criteria specified
   * on this query object.
   */
  @java.lang.SuppressWarnings("unchecked")
  public com.terraframe.mojo.query.OIterator<? extends KnockDownAssayExcelView> getIterator()
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
    return new com.terraframe.mojo.query.ViewIterator<KnockDownAssayExcelView>(this.getMdClassIF(), valueIterator);
  }

}
