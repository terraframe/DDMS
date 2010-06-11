package dss.vector.solutions.export;

@com.runwaysdk.business.ClassSignature(hash = 1918539488)
/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to EfficacyAssayExcelView.java
 *
 * @author Autogenerated by RunwaySDK
 */
public  abstract  class EfficacyAssayExcelViewQueryBase extends com.runwaysdk.query.GeneratedViewQuery
 implements com.runwaysdk.generation.loader.Reloadable
{
private static final long serialVersionUID = 1918539488;

  public EfficacyAssayExcelViewQueryBase(com.runwaysdk.query.QueryFactory componentQueryFactory)
  {
    super(componentQueryFactory);
  }

  public EfficacyAssayExcelViewQueryBase(com.runwaysdk.query.QueryFactory componentQueryFactory, com.runwaysdk.query.ViewQueryBuilder viewQueryBuilder)
  {
    super(componentQueryFactory, viewQueryBuilder);
  }
  public String getClassType()
  {
    return dss.vector.solutions.export.EfficacyAssayExcelView.CLASS;
  }
  public dss.vector.solutions.entomology.assay.AdultAgeRangeQuery.AdultAgeRangeQueryStructIF getAgeRange()
  {
    return getAgeRange(null);

  }
 
  public dss.vector.solutions.entomology.assay.AdultAgeRangeQuery.AdultAgeRangeQueryStructIF getAgeRange(String alias)
  {
    return (dss.vector.solutions.entomology.assay.AdultAgeRangeQuery.AdultAgeRangeQueryStructIF)this.getSelectable(dss.vector.solutions.export.EfficacyAssayExcelView.AGERANGE, alias, null);

  }
 
  public dss.vector.solutions.entomology.assay.AdultAgeRangeQuery.AdultAgeRangeQueryStructIF getAgeRange(String alias, String displayLabel)
  {
    return (dss.vector.solutions.entomology.assay.AdultAgeRangeQuery.AdultAgeRangeQueryStructIF)this.getSelectable(dss.vector.solutions.export.EfficacyAssayExcelView.AGERANGE, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableChar getColonyName()
  {
    return getColonyName(null);

  }
 
  public com.runwaysdk.query.SelectableChar getColonyName(String alias)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(dss.vector.solutions.export.EfficacyAssayExcelView.COLONYNAME, alias, null);

  }
 
  public com.runwaysdk.query.SelectableChar getColonyName(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(dss.vector.solutions.export.EfficacyAssayExcelView.COLONYNAME, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableInteger getExposureTime()
  {
    return getExposureTime(null);

  }
 
  public com.runwaysdk.query.SelectableInteger getExposureTime(String alias)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.export.EfficacyAssayExcelView.EXPOSURETIME, alias, null);

  }
 
  public com.runwaysdk.query.SelectableInteger getExposureTime(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.export.EfficacyAssayExcelView.EXPOSURETIME, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableInteger getFed()
  {
    return getFed(null);

  }
 
  public com.runwaysdk.query.SelectableInteger getFed(String alias)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.export.EfficacyAssayExcelView.FED, alias, null);

  }
 
  public com.runwaysdk.query.SelectableInteger getFed(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.export.EfficacyAssayExcelView.FED, alias, displayLabel);

  }
 
  public dss.vector.solutions.geo.generated.GeoEntityQuery.GeoEntityQueryReferenceIF getGeoEntity()
  {
    return getGeoEntity(null);

  }
 
  public dss.vector.solutions.geo.generated.GeoEntityQuery.GeoEntityQueryReferenceIF getGeoEntity(String alias)
  {

    return (dss.vector.solutions.geo.generated.GeoEntityQuery.GeoEntityQueryReferenceIF)this.getSelectable(dss.vector.solutions.export.EfficacyAssayExcelView.GEOENTITY, alias, null);

  }
 
  public dss.vector.solutions.geo.generated.GeoEntityQuery.GeoEntityQueryReferenceIF getGeoEntity(String alias, String displayLabel)
  {

    return (dss.vector.solutions.geo.generated.GeoEntityQuery.GeoEntityQueryReferenceIF)this.getSelectable(dss.vector.solutions.export.EfficacyAssayExcelView.GEOENTITY, alias, displayLabel);

  }
  public com.runwaysdk.query.SelectableInteger getGravid()
  {
    return getGravid(null);

  }
 
  public com.runwaysdk.query.SelectableInteger getGravid(String alias)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.export.EfficacyAssayExcelView.GRAVID, alias, null);

  }
 
  public com.runwaysdk.query.SelectableInteger getGravid(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.export.EfficacyAssayExcelView.GRAVID, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableInteger getHoldingTime()
  {
    return getHoldingTime(null);

  }
 
  public com.runwaysdk.query.SelectableInteger getHoldingTime(String alias)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.export.EfficacyAssayExcelView.HOLDINGTIME, alias, null);

  }
 
  public com.runwaysdk.query.SelectableInteger getHoldingTime(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.export.EfficacyAssayExcelView.HOLDINGTIME, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableChar getId()
  {
    return getId(null);

  }
 
  public com.runwaysdk.query.SelectableChar getId(String alias)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(dss.vector.solutions.export.EfficacyAssayExcelView.ID, alias, null);

  }
 
  public com.runwaysdk.query.SelectableChar getId(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(dss.vector.solutions.export.EfficacyAssayExcelView.ID, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableChar getInsecticideActiveIngredient()
  {
    return getInsecticideActiveIngredient(null);

  }
 
  public com.runwaysdk.query.SelectableChar getInsecticideActiveIngredient(String alias)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(dss.vector.solutions.export.EfficacyAssayExcelView.INSECTICIDEACTIVEINGREDIENT, alias, null);

  }
 
  public com.runwaysdk.query.SelectableChar getInsecticideActiveIngredient(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(dss.vector.solutions.export.EfficacyAssayExcelView.INSECTICIDEACTIVEINGREDIENT, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableDouble getInsecticideAmount()
  {
    return getInsecticideAmount(null);

  }
 
  public com.runwaysdk.query.SelectableDouble getInsecticideAmount(String alias)
  {
    return (com.runwaysdk.query.SelectableDouble)this.getSelectable(dss.vector.solutions.export.EfficacyAssayExcelView.INSECTICIDEAMOUNT, alias, null);

  }
 
  public com.runwaysdk.query.SelectableDouble getInsecticideAmount(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableDouble)this.getSelectable(dss.vector.solutions.export.EfficacyAssayExcelView.INSECTICIDEAMOUNT, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableChar getInsecticideUnits()
  {
    return getInsecticideUnits(null);

  }
 
  public com.runwaysdk.query.SelectableChar getInsecticideUnits(String alias)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(dss.vector.solutions.export.EfficacyAssayExcelView.INSECTICIDEUNITS, alias, null);

  }
 
  public com.runwaysdk.query.SelectableChar getInsecticideUnits(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(dss.vector.solutions.export.EfficacyAssayExcelView.INSECTICIDEUNITS, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableInteger getQuantityDead()
  {
    return getQuantityDead(null);

  }
 
  public com.runwaysdk.query.SelectableInteger getQuantityDead(String alias)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.export.EfficacyAssayExcelView.QUANTITYDEAD, alias, null);

  }
 
  public com.runwaysdk.query.SelectableInteger getQuantityDead(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.export.EfficacyAssayExcelView.QUANTITYDEAD, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableInteger getQuantityTested()
  {
    return getQuantityTested(null);

  }
 
  public com.runwaysdk.query.SelectableInteger getQuantityTested(String alias)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.export.EfficacyAssayExcelView.QUANTITYTESTED, alias, null);

  }
 
  public com.runwaysdk.query.SelectableInteger getQuantityTested(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.export.EfficacyAssayExcelView.QUANTITYTESTED, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableChar getSex()
  {
    return getSex(null);

  }
 
  public com.runwaysdk.query.SelectableChar getSex(String alias)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(dss.vector.solutions.export.EfficacyAssayExcelView.SEX, alias, null);

  }
 
  public com.runwaysdk.query.SelectableChar getSex(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(dss.vector.solutions.export.EfficacyAssayExcelView.SEX, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableChar getSpecie()
  {
    return getSpecie(null);

  }
 
  public com.runwaysdk.query.SelectableChar getSpecie(String alias)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(dss.vector.solutions.export.EfficacyAssayExcelView.SPECIE, alias, null);

  }
 
  public com.runwaysdk.query.SelectableChar getSpecie(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(dss.vector.solutions.export.EfficacyAssayExcelView.SPECIE, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableChar getSurfacePosition()
  {
    return getSurfacePosition(null);

  }
 
  public com.runwaysdk.query.SelectableChar getSurfacePosition(String alias)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(dss.vector.solutions.export.EfficacyAssayExcelView.SURFACEPOSITION, alias, null);

  }
 
  public com.runwaysdk.query.SelectableChar getSurfacePosition(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(dss.vector.solutions.export.EfficacyAssayExcelView.SURFACEPOSITION, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableChar getSurfaceType()
  {
    return getSurfaceType(null);

  }
 
  public com.runwaysdk.query.SelectableChar getSurfaceType(String alias)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(dss.vector.solutions.export.EfficacyAssayExcelView.SURFACETYPE, alias, null);

  }
 
  public com.runwaysdk.query.SelectableChar getSurfaceType(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(dss.vector.solutions.export.EfficacyAssayExcelView.SURFACETYPE, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableMoment getTestDate()
  {
    return getTestDate(null);

  }
 
  public com.runwaysdk.query.SelectableMoment getTestDate(String alias)
  {
    return (com.runwaysdk.query.SelectableMoment)this.getSelectable(dss.vector.solutions.export.EfficacyAssayExcelView.TESTDATE, alias, null);

  }
 
  public com.runwaysdk.query.SelectableMoment getTestDate(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableMoment)this.getSelectable(dss.vector.solutions.export.EfficacyAssayExcelView.TESTDATE, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableChar getTestMethod()
  {
    return getTestMethod(null);

  }
 
  public com.runwaysdk.query.SelectableChar getTestMethod(String alias)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(dss.vector.solutions.export.EfficacyAssayExcelView.TESTMETHOD, alias, null);

  }
 
  public com.runwaysdk.query.SelectableChar getTestMethod(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(dss.vector.solutions.export.EfficacyAssayExcelView.TESTMETHOD, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableInteger getTimeOnSurface()
  {
    return getTimeOnSurface(null);

  }
 
  public com.runwaysdk.query.SelectableInteger getTimeOnSurface(String alias)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.export.EfficacyAssayExcelView.TIMEONSURFACE, alias, null);

  }
 
  public com.runwaysdk.query.SelectableInteger getTimeOnSurface(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.export.EfficacyAssayExcelView.TIMEONSURFACE, alias, displayLabel);

  }
 
  /**  
   * Returns an iterator of Business objects that match the query criteria specified
   * on this query object. 
   * @return iterator of Business objects that match the query criteria specified
   * on this query object.
   */
  @java.lang.SuppressWarnings("unchecked")
  public com.runwaysdk.query.OIterator<? extends EfficacyAssayExcelView> getIterator()
  {
    com.runwaysdk.query.ValueIterator valueIterator;
    if (_pageSize != null && _pageNumber != null)
    {
      valueIterator = (com.runwaysdk.query.ValueIterator<com.runwaysdk.dataaccess.ValueObject>)this.getComponentQuery().getIterator(_pageSize, _pageNumber);
    }
    else
    {
      valueIterator = (com.runwaysdk.query.ValueIterator<com.runwaysdk.dataaccess.ValueObject>)this.getComponentQuery().getIterator();
    }
    return new com.runwaysdk.query.ViewIterator<EfficacyAssayExcelView>(this.getMdClassIF(), valueIterator);
  }

}
