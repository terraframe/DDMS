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
 
  public com.terraframe.mojo.query.SelectableChar getCollectionMethod()
  {
    return getCollectionMethod(null);

  }
 
  public com.terraframe.mojo.query.SelectableChar getCollectionMethod(String alias)
  {
    return (com.terraframe.mojo.query.SelectableChar)this.getSelectable(dss.vector.solutions.export.KnockDownAssayExcelView.COLLECTIONMETHOD, alias, null);

  }
 
  public com.terraframe.mojo.query.SelectableChar getCollectionMethod(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.SelectableChar)this.getSelectable(dss.vector.solutions.export.KnockDownAssayExcelView.COLLECTIONMETHOD, alias, displayLabel);

  }
 
  public com.terraframe.mojo.query.SelectableMoment getDateCollected()
  {
    return getDateCollected(null);

  }
 
  public com.terraframe.mojo.query.SelectableMoment getDateCollected(String alias)
  {
    return (com.terraframe.mojo.query.SelectableMoment)this.getSelectable(dss.vector.solutions.export.KnockDownAssayExcelView.DATECOLLECTED, alias, null);

  }
 
  public com.terraframe.mojo.query.SelectableMoment getDateCollected(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.SelectableMoment)this.getSelectable(dss.vector.solutions.export.KnockDownAssayExcelView.DATECOLLECTED, alias, displayLabel);

  }
 
  public com.terraframe.mojo.query.SelectableInteger getExposureTime()
  {
    return getExposureTime(null);

  }
 
  public com.terraframe.mojo.query.SelectableInteger getExposureTime(String alias)
  {
    return (com.terraframe.mojo.query.SelectableInteger)this.getSelectable(dss.vector.solutions.export.KnockDownAssayExcelView.EXPOSURETIME, alias, null);

  }
 
  public com.terraframe.mojo.query.SelectableInteger getExposureTime(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.SelectableInteger)this.getSelectable(dss.vector.solutions.export.KnockDownAssayExcelView.EXPOSURETIME, alias, displayLabel);

  }
 
  public com.terraframe.mojo.query.SelectableInteger getFed()
  {
    return getFed(null);

  }
 
  public com.terraframe.mojo.query.SelectableInteger getFed(String alias)
  {
    return (com.terraframe.mojo.query.SelectableInteger)this.getSelectable(dss.vector.solutions.export.KnockDownAssayExcelView.FED, alias, null);

  }
 
  public com.terraframe.mojo.query.SelectableInteger getFed(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.SelectableInteger)this.getSelectable(dss.vector.solutions.export.KnockDownAssayExcelView.FED, alias, displayLabel);

  }
 
  public com.terraframe.mojo.query.SelectableChar getGeneration()
  {
    return getGeneration(null);

  }
 
  public com.terraframe.mojo.query.SelectableChar getGeneration(String alias)
  {
    return (com.terraframe.mojo.query.SelectableChar)this.getSelectable(dss.vector.solutions.export.KnockDownAssayExcelView.GENERATION, alias, null);

  }
 
  public com.terraframe.mojo.query.SelectableChar getGeneration(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.SelectableChar)this.getSelectable(dss.vector.solutions.export.KnockDownAssayExcelView.GENERATION, alias, displayLabel);

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
  public com.terraframe.mojo.query.SelectableInteger getGravid()
  {
    return getGravid(null);

  }
 
  public com.terraframe.mojo.query.SelectableInteger getGravid(String alias)
  {
    return (com.terraframe.mojo.query.SelectableInteger)this.getSelectable(dss.vector.solutions.export.KnockDownAssayExcelView.GRAVID, alias, null);

  }
 
  public com.terraframe.mojo.query.SelectableInteger getGravid(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.SelectableInteger)this.getSelectable(dss.vector.solutions.export.KnockDownAssayExcelView.GRAVID, alias, displayLabel);

  }
 
  public com.terraframe.mojo.query.SelectableChar getId()
  {
    return getId(null);

  }
 
  public com.terraframe.mojo.query.SelectableChar getId(String alias)
  {
    return (com.terraframe.mojo.query.SelectableChar)this.getSelectable(dss.vector.solutions.export.KnockDownAssayExcelView.ID, alias, null);

  }
 
  public com.terraframe.mojo.query.SelectableChar getId(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.SelectableChar)this.getSelectable(dss.vector.solutions.export.KnockDownAssayExcelView.ID, alias, displayLabel);

  }
 
  public com.terraframe.mojo.query.SelectableChar getIdentificationMethod()
  {
    return getIdentificationMethod(null);

  }
 
  public com.terraframe.mojo.query.SelectableChar getIdentificationMethod(String alias)
  {
    return (com.terraframe.mojo.query.SelectableChar)this.getSelectable(dss.vector.solutions.export.KnockDownAssayExcelView.IDENTIFICATIONMETHOD, alias, null);

  }
 
  public com.terraframe.mojo.query.SelectableChar getIdentificationMethod(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.SelectableChar)this.getSelectable(dss.vector.solutions.export.KnockDownAssayExcelView.IDENTIFICATIONMETHOD, alias, displayLabel);

  }
 
  public com.terraframe.mojo.query.SelectableChar getInsecticideActiveIngredient()
  {
    return getInsecticideActiveIngredient(null);

  }
 
  public com.terraframe.mojo.query.SelectableChar getInsecticideActiveIngredient(String alias)
  {
    return (com.terraframe.mojo.query.SelectableChar)this.getSelectable(dss.vector.solutions.export.KnockDownAssayExcelView.INSECTICIDEACTIVEINGREDIENT, alias, null);

  }
 
  public com.terraframe.mojo.query.SelectableChar getInsecticideActiveIngredient(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.SelectableChar)this.getSelectable(dss.vector.solutions.export.KnockDownAssayExcelView.INSECTICIDEACTIVEINGREDIENT, alias, displayLabel);

  }
 
  public com.terraframe.mojo.query.SelectableDouble getInsecticideAmount()
  {
    return getInsecticideAmount(null);

  }
 
  public com.terraframe.mojo.query.SelectableDouble getInsecticideAmount(String alias)
  {
    return (com.terraframe.mojo.query.SelectableDouble)this.getSelectable(dss.vector.solutions.export.KnockDownAssayExcelView.INSECTICIDEAMOUNT, alias, null);

  }
 
  public com.terraframe.mojo.query.SelectableDouble getInsecticideAmount(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.SelectableDouble)this.getSelectable(dss.vector.solutions.export.KnockDownAssayExcelView.INSECTICIDEAMOUNT, alias, displayLabel);

  }
 
  public com.terraframe.mojo.query.SelectableChar getInsecticideUnits()
  {
    return getInsecticideUnits(null);

  }
 
  public com.terraframe.mojo.query.SelectableChar getInsecticideUnits(String alias)
  {
    return (com.terraframe.mojo.query.SelectableChar)this.getSelectable(dss.vector.solutions.export.KnockDownAssayExcelView.INSECTICIDEUNITS, alias, null);

  }
 
  public com.terraframe.mojo.query.SelectableChar getInsecticideUnits(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.SelectableChar)this.getSelectable(dss.vector.solutions.export.KnockDownAssayExcelView.INSECTICIDEUNITS, alias, displayLabel);

  }
 
  public com.terraframe.mojo.query.SelectableBoolean getIsofemale()
  {
    return getIsofemale(null);

  }
 
  public com.terraframe.mojo.query.SelectableBoolean getIsofemale(String alias)
  {
    return (com.terraframe.mojo.query.SelectableBoolean)this.getSelectable(dss.vector.solutions.export.KnockDownAssayExcelView.ISOFEMALE, alias, null);

  }
 
  public com.terraframe.mojo.query.SelectableBoolean getIsofemale(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.SelectableBoolean)this.getSelectable(dss.vector.solutions.export.KnockDownAssayExcelView.ISOFEMALE, alias, displayLabel);

  }
 
  public com.terraframe.mojo.query.SelectableInteger getQuantityTested()
  {
    return getQuantityTested(null);

  }
 
  public com.terraframe.mojo.query.SelectableInteger getQuantityTested(String alias)
  {
    return (com.terraframe.mojo.query.SelectableInteger)this.getSelectable(dss.vector.solutions.export.KnockDownAssayExcelView.QUANTITYTESTED, alias, null);

  }
 
  public com.terraframe.mojo.query.SelectableInteger getQuantityTested(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.SelectableInteger)this.getSelectable(dss.vector.solutions.export.KnockDownAssayExcelView.QUANTITYTESTED, alias, displayLabel);

  }
 
  public com.terraframe.mojo.query.SelectableChar getSex()
  {
    return getSex(null);

  }
 
  public com.terraframe.mojo.query.SelectableChar getSex(String alias)
  {
    return (com.terraframe.mojo.query.SelectableChar)this.getSelectable(dss.vector.solutions.export.KnockDownAssayExcelView.SEX, alias, null);

  }
 
  public com.terraframe.mojo.query.SelectableChar getSex(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.SelectableChar)this.getSelectable(dss.vector.solutions.export.KnockDownAssayExcelView.SEX, alias, displayLabel);

  }
 
  public com.terraframe.mojo.query.SelectableChar getSpecie()
  {
    return getSpecie(null);

  }
 
  public com.terraframe.mojo.query.SelectableChar getSpecie(String alias)
  {
    return (com.terraframe.mojo.query.SelectableChar)this.getSelectable(dss.vector.solutions.export.KnockDownAssayExcelView.SPECIE, alias, null);

  }
 
  public com.terraframe.mojo.query.SelectableChar getSpecie(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.SelectableChar)this.getSelectable(dss.vector.solutions.export.KnockDownAssayExcelView.SPECIE, alias, displayLabel);

  }
 
  public com.terraframe.mojo.query.SelectableMoment getTestDate()
  {
    return getTestDate(null);

  }
 
  public com.terraframe.mojo.query.SelectableMoment getTestDate(String alias)
  {
    return (com.terraframe.mojo.query.SelectableMoment)this.getSelectable(dss.vector.solutions.export.KnockDownAssayExcelView.TESTDATE, alias, null);

  }
 
  public com.terraframe.mojo.query.SelectableMoment getTestDate(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.SelectableMoment)this.getSelectable(dss.vector.solutions.export.KnockDownAssayExcelView.TESTDATE, alias, displayLabel);

  }
 
  public com.terraframe.mojo.query.SelectableChar getTestMethod()
  {
    return getTestMethod(null);

  }
 
  public com.terraframe.mojo.query.SelectableChar getTestMethod(String alias)
  {
    return (com.terraframe.mojo.query.SelectableChar)this.getSelectable(dss.vector.solutions.export.KnockDownAssayExcelView.TESTMETHOD, alias, null);

  }
 
  public com.terraframe.mojo.query.SelectableChar getTestMethod(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.SelectableChar)this.getSelectable(dss.vector.solutions.export.KnockDownAssayExcelView.TESTMETHOD, alias, displayLabel);

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
