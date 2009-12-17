package dss.vector.solutions.export;

@com.terraframe.mojo.business.ClassSignature(hash = 794585511)
/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to LarvaeDiscriminatingDoseAssayExcelView.java
 *
 * @author Autogenerated by TerraFrame
 */
public  abstract  class LarvaeDiscriminatingDoseAssayExcelViewQueryBase extends com.terraframe.mojo.query.GeneratedViewQuery
 implements com.terraframe.mojo.generation.loader.Reloadable
{
private static final long serialVersionUID = 794585511;

  public LarvaeDiscriminatingDoseAssayExcelViewQueryBase(com.terraframe.mojo.query.QueryFactory componentQueryFactory)
  {
    super(componentQueryFactory);
  }

  public LarvaeDiscriminatingDoseAssayExcelViewQueryBase(com.terraframe.mojo.query.QueryFactory componentQueryFactory, com.terraframe.mojo.query.ViewQueryBuilder viewQueryBuilder)
  {
    super(componentQueryFactory, viewQueryBuilder);
  }
  public String getClassType()
  {
    return dss.vector.solutions.export.LarvaeDiscriminatingDoseAssayExcelView.CLASS;
  }
  public com.terraframe.mojo.query.AttributeChar getCollectionMethod()
  {
    return getCollectionMethod(null);

  }
 
  public com.terraframe.mojo.query.AttributeChar getCollectionMethod(String alias)
  {
    return (com.terraframe.mojo.query.AttributeChar)this.getSelectable(dss.vector.solutions.export.LarvaeDiscriminatingDoseAssayExcelView.COLLECTIONMETHOD, alias, null);

  }
 
  public com.terraframe.mojo.query.AttributeChar getCollectionMethod(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.AttributeChar)this.getSelectable(dss.vector.solutions.export.LarvaeDiscriminatingDoseAssayExcelView.COLLECTIONMETHOD, alias, displayLabel);

  }
 
  public com.terraframe.mojo.query.AttributeFloat getControlTestMortality()
  {
    return getControlTestMortality(null);

  }
 
  public com.terraframe.mojo.query.AttributeFloat getControlTestMortality(String alias)
  {
    return (com.terraframe.mojo.query.AttributeFloat)this.getSelectable(dss.vector.solutions.export.LarvaeDiscriminatingDoseAssayExcelView.CONTROLTESTMORTALITY, alias, null);

  }
 
  public com.terraframe.mojo.query.AttributeFloat getControlTestMortality(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.AttributeFloat)this.getSelectable(dss.vector.solutions.export.LarvaeDiscriminatingDoseAssayExcelView.CONTROLTESTMORTALITY, alias, displayLabel);

  }
 
  public com.terraframe.mojo.query.AttributeMoment getDateCollected()
  {
    return getDateCollected(null);

  }
 
  public com.terraframe.mojo.query.AttributeMoment getDateCollected(String alias)
  {
    return (com.terraframe.mojo.query.AttributeMoment)this.getSelectable(dss.vector.solutions.export.LarvaeDiscriminatingDoseAssayExcelView.DATECOLLECTED, alias, null);

  }
 
  public com.terraframe.mojo.query.AttributeMoment getDateCollected(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.AttributeMoment)this.getSelectable(dss.vector.solutions.export.LarvaeDiscriminatingDoseAssayExcelView.DATECOLLECTED, alias, displayLabel);

  }
 
  public com.terraframe.mojo.query.AttributeChar getEndPoint()
  {
    return getEndPoint(null);

  }
 
  public com.terraframe.mojo.query.AttributeChar getEndPoint(String alias)
  {
    return (com.terraframe.mojo.query.AttributeChar)this.getSelectable(dss.vector.solutions.export.LarvaeDiscriminatingDoseAssayExcelView.ENDPOINT, alias, null);

  }
 
  public com.terraframe.mojo.query.AttributeChar getEndPoint(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.AttributeChar)this.getSelectable(dss.vector.solutions.export.LarvaeDiscriminatingDoseAssayExcelView.ENDPOINT, alias, displayLabel);

  }
 
  public com.terraframe.mojo.query.AttributeInteger getExposureTime()
  {
    return getExposureTime(null);

  }
 
  public com.terraframe.mojo.query.AttributeInteger getExposureTime(String alias)
  {
    return (com.terraframe.mojo.query.AttributeInteger)this.getSelectable(dss.vector.solutions.export.LarvaeDiscriminatingDoseAssayExcelView.EXPOSURETIME, alias, null);

  }
 
  public com.terraframe.mojo.query.AttributeInteger getExposureTime(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.AttributeInteger)this.getSelectable(dss.vector.solutions.export.LarvaeDiscriminatingDoseAssayExcelView.EXPOSURETIME, alias, displayLabel);

  }
 
  public com.terraframe.mojo.query.AttributeChar getGeneration()
  {
    return getGeneration(null);

  }
 
  public com.terraframe.mojo.query.AttributeChar getGeneration(String alias)
  {
    return (com.terraframe.mojo.query.AttributeChar)this.getSelectable(dss.vector.solutions.export.LarvaeDiscriminatingDoseAssayExcelView.GENERATION, alias, null);

  }
 
  public com.terraframe.mojo.query.AttributeChar getGeneration(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.AttributeChar)this.getSelectable(dss.vector.solutions.export.LarvaeDiscriminatingDoseAssayExcelView.GENERATION, alias, displayLabel);

  }
 
  public dss.vector.solutions.geo.generated.GeoEntityQuery.GeoEntityQueryReferenceIF getGeoEntity()
  {
    return getGeoEntity(null);

  }
 
  public dss.vector.solutions.geo.generated.GeoEntityQuery.GeoEntityQueryReferenceIF getGeoEntity(String alias)
  {

    return (dss.vector.solutions.geo.generated.GeoEntityQuery.GeoEntityQueryReferenceIF)this.getSelectable(dss.vector.solutions.export.LarvaeDiscriminatingDoseAssayExcelView.GEOENTITY, alias, null);

  }
 
  public dss.vector.solutions.geo.generated.GeoEntityQuery.GeoEntityQueryReferenceIF getGeoEntity(String alias, String displayLabel)
  {

    return (dss.vector.solutions.geo.generated.GeoEntityQuery.GeoEntityQueryReferenceIF)this.getSelectable(dss.vector.solutions.export.LarvaeDiscriminatingDoseAssayExcelView.GEOENTITY, alias, displayLabel);

  }
  public com.terraframe.mojo.query.AttributeInteger getHoldingTime()
  {
    return getHoldingTime(null);

  }
 
  public com.terraframe.mojo.query.AttributeInteger getHoldingTime(String alias)
  {
    return (com.terraframe.mojo.query.AttributeInteger)this.getSelectable(dss.vector.solutions.export.LarvaeDiscriminatingDoseAssayExcelView.HOLDINGTIME, alias, null);

  }
 
  public com.terraframe.mojo.query.AttributeInteger getHoldingTime(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.AttributeInteger)this.getSelectable(dss.vector.solutions.export.LarvaeDiscriminatingDoseAssayExcelView.HOLDINGTIME, alias, displayLabel);

  }
 
  public com.terraframe.mojo.query.AttributeChar getId()
  {
    return getId(null);

  }
 
  public com.terraframe.mojo.query.AttributeChar getId(String alias)
  {
    return (com.terraframe.mojo.query.AttributeChar)this.getSelectable(dss.vector.solutions.export.LarvaeDiscriminatingDoseAssayExcelView.ID, alias, null);

  }
 
  public com.terraframe.mojo.query.AttributeChar getId(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.AttributeChar)this.getSelectable(dss.vector.solutions.export.LarvaeDiscriminatingDoseAssayExcelView.ID, alias, displayLabel);

  }
 
  public com.terraframe.mojo.query.AttributeChar getIdentificationMethod()
  {
    return getIdentificationMethod(null);

  }
 
  public com.terraframe.mojo.query.AttributeChar getIdentificationMethod(String alias)
  {
    return (com.terraframe.mojo.query.AttributeChar)this.getSelectable(dss.vector.solutions.export.LarvaeDiscriminatingDoseAssayExcelView.IDENTIFICATIONMETHOD, alias, null);

  }
 
  public com.terraframe.mojo.query.AttributeChar getIdentificationMethod(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.AttributeChar)this.getSelectable(dss.vector.solutions.export.LarvaeDiscriminatingDoseAssayExcelView.IDENTIFICATIONMETHOD, alias, displayLabel);

  }
 
  public com.terraframe.mojo.query.AttributeChar getInsecticideActiveIngredient()
  {
    return getInsecticideActiveIngredient(null);

  }
 
  public com.terraframe.mojo.query.AttributeChar getInsecticideActiveIngredient(String alias)
  {
    return (com.terraframe.mojo.query.AttributeChar)this.getSelectable(dss.vector.solutions.export.LarvaeDiscriminatingDoseAssayExcelView.INSECTICIDEACTIVEINGREDIENT, alias, null);

  }
 
  public com.terraframe.mojo.query.AttributeChar getInsecticideActiveIngredient(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.AttributeChar)this.getSelectable(dss.vector.solutions.export.LarvaeDiscriminatingDoseAssayExcelView.INSECTICIDEACTIVEINGREDIENT, alias, displayLabel);

  }
 
  public com.terraframe.mojo.query.AttributeDouble getInsecticideAmount()
  {
    return getInsecticideAmount(null);

  }
 
  public com.terraframe.mojo.query.AttributeDouble getInsecticideAmount(String alias)
  {
    return (com.terraframe.mojo.query.AttributeDouble)this.getSelectable(dss.vector.solutions.export.LarvaeDiscriminatingDoseAssayExcelView.INSECTICIDEAMOUNT, alias, null);

  }
 
  public com.terraframe.mojo.query.AttributeDouble getInsecticideAmount(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.AttributeDouble)this.getSelectable(dss.vector.solutions.export.LarvaeDiscriminatingDoseAssayExcelView.INSECTICIDEAMOUNT, alias, displayLabel);

  }
 
  public com.terraframe.mojo.query.AttributeChar getInsecticideUnits()
  {
    return getInsecticideUnits(null);

  }
 
  public com.terraframe.mojo.query.AttributeChar getInsecticideUnits(String alias)
  {
    return (com.terraframe.mojo.query.AttributeChar)this.getSelectable(dss.vector.solutions.export.LarvaeDiscriminatingDoseAssayExcelView.INSECTICIDEUNITS, alias, null);

  }
 
  public com.terraframe.mojo.query.AttributeChar getInsecticideUnits(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.AttributeChar)this.getSelectable(dss.vector.solutions.export.LarvaeDiscriminatingDoseAssayExcelView.INSECTICIDEUNITS, alias, displayLabel);

  }
 
  public com.terraframe.mojo.query.AttributeBoolean getIsofemale()
  {
    return getIsofemale(null);

  }
 
  public com.terraframe.mojo.query.AttributeBoolean getIsofemale(String alias)
  {
    return (com.terraframe.mojo.query.AttributeBoolean)this.getSelectable(dss.vector.solutions.export.LarvaeDiscriminatingDoseAssayExcelView.ISOFEMALE, alias, null);

  }
 
  public com.terraframe.mojo.query.AttributeBoolean getIsofemale(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.AttributeBoolean)this.getSelectable(dss.vector.solutions.export.LarvaeDiscriminatingDoseAssayExcelView.ISOFEMALE, alias, displayLabel);

  }
 
  public com.terraframe.mojo.query.AttributeInteger getQuantityDead()
  {
    return getQuantityDead(null);

  }
 
  public com.terraframe.mojo.query.AttributeInteger getQuantityDead(String alias)
  {
    return (com.terraframe.mojo.query.AttributeInteger)this.getSelectable(dss.vector.solutions.export.LarvaeDiscriminatingDoseAssayExcelView.QUANTITYDEAD, alias, null);

  }
 
  public com.terraframe.mojo.query.AttributeInteger getQuantityDead(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.AttributeInteger)this.getSelectable(dss.vector.solutions.export.LarvaeDiscriminatingDoseAssayExcelView.QUANTITYDEAD, alias, displayLabel);

  }
 
  public com.terraframe.mojo.query.AttributeInteger getQuantityTested()
  {
    return getQuantityTested(null);

  }
 
  public com.terraframe.mojo.query.AttributeInteger getQuantityTested(String alias)
  {
    return (com.terraframe.mojo.query.AttributeInteger)this.getSelectable(dss.vector.solutions.export.LarvaeDiscriminatingDoseAssayExcelView.QUANTITYTESTED, alias, null);

  }
 
  public com.terraframe.mojo.query.AttributeInteger getQuantityTested(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.AttributeInteger)this.getSelectable(dss.vector.solutions.export.LarvaeDiscriminatingDoseAssayExcelView.QUANTITYTESTED, alias, displayLabel);

  }
 
  public com.terraframe.mojo.query.AttributeChar getSpecie()
  {
    return getSpecie(null);

  }
 
  public com.terraframe.mojo.query.AttributeChar getSpecie(String alias)
  {
    return (com.terraframe.mojo.query.AttributeChar)this.getSelectable(dss.vector.solutions.export.LarvaeDiscriminatingDoseAssayExcelView.SPECIE, alias, null);

  }
 
  public com.terraframe.mojo.query.AttributeChar getSpecie(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.AttributeChar)this.getSelectable(dss.vector.solutions.export.LarvaeDiscriminatingDoseAssayExcelView.SPECIE, alias, displayLabel);

  }
 
  public com.terraframe.mojo.query.AttributeChar getStartPoint()
  {
    return getStartPoint(null);

  }
 
  public com.terraframe.mojo.query.AttributeChar getStartPoint(String alias)
  {
    return (com.terraframe.mojo.query.AttributeChar)this.getSelectable(dss.vector.solutions.export.LarvaeDiscriminatingDoseAssayExcelView.STARTPOINT, alias, null);

  }
 
  public com.terraframe.mojo.query.AttributeChar getStartPoint(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.AttributeChar)this.getSelectable(dss.vector.solutions.export.LarvaeDiscriminatingDoseAssayExcelView.STARTPOINT, alias, displayLabel);

  }
 
  public com.terraframe.mojo.query.AttributeMoment getTestDate()
  {
    return getTestDate(null);

  }
 
  public com.terraframe.mojo.query.AttributeMoment getTestDate(String alias)
  {
    return (com.terraframe.mojo.query.AttributeMoment)this.getSelectable(dss.vector.solutions.export.LarvaeDiscriminatingDoseAssayExcelView.TESTDATE, alias, null);

  }
 
  public com.terraframe.mojo.query.AttributeMoment getTestDate(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.AttributeMoment)this.getSelectable(dss.vector.solutions.export.LarvaeDiscriminatingDoseAssayExcelView.TESTDATE, alias, displayLabel);

  }
 
  public com.terraframe.mojo.query.AttributeChar getTestMethod()
  {
    return getTestMethod(null);

  }
 
  public com.terraframe.mojo.query.AttributeChar getTestMethod(String alias)
  {
    return (com.terraframe.mojo.query.AttributeChar)this.getSelectable(dss.vector.solutions.export.LarvaeDiscriminatingDoseAssayExcelView.TESTMETHOD, alias, null);

  }
 
  public com.terraframe.mojo.query.AttributeChar getTestMethod(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.AttributeChar)this.getSelectable(dss.vector.solutions.export.LarvaeDiscriminatingDoseAssayExcelView.TESTMETHOD, alias, displayLabel);

  }
 
  /**  
   * Returns an iterator of Business objects that match the query criteria specified
   * on this query object. 
   * @return iterator of Business objects that match the query criteria specified
   * on this query object.
   */
  @java.lang.SuppressWarnings("unchecked")
  public com.terraframe.mojo.query.OIterator<? extends LarvaeDiscriminatingDoseAssayExcelView> getIterator()
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
    return new com.terraframe.mojo.query.ViewIterator<LarvaeDiscriminatingDoseAssayExcelView>(this.getMdClassIF(), valueIterator);
  }

}
