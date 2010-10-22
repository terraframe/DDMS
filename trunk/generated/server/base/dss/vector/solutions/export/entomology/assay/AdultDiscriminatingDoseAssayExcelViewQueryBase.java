package dss.vector.solutions.export.entomology.assay;

@com.runwaysdk.business.ClassSignature(hash = -554488408)
/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to AdultDiscriminatingDoseAssayExcelView.java
 *
 * @author Autogenerated by RunwaySDK
 */
public  abstract  class AdultDiscriminatingDoseAssayExcelViewQueryBase extends com.runwaysdk.query.GeneratedViewQuery
 implements com.runwaysdk.generation.loader.Reloadable
{
private static final long serialVersionUID = -554488408;

  public AdultDiscriminatingDoseAssayExcelViewQueryBase(com.runwaysdk.query.QueryFactory componentQueryFactory)
  {
    super(componentQueryFactory);
  }

  public AdultDiscriminatingDoseAssayExcelViewQueryBase(com.runwaysdk.query.QueryFactory componentQueryFactory, com.runwaysdk.query.ViewQueryBuilder viewQueryBuilder)
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
 
  public com.runwaysdk.query.SelectableChar getCollectionId()
  {
    return getCollectionId(null);

  }
 
  public com.runwaysdk.query.SelectableChar getCollectionId(String alias)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(dss.vector.solutions.export.entomology.assay.AdultDiscriminatingDoseAssayExcelView.COLLECTIONID, alias, null);

  }
 
  public com.runwaysdk.query.SelectableChar getCollectionId(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(dss.vector.solutions.export.entomology.assay.AdultDiscriminatingDoseAssayExcelView.COLLECTIONID, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableFloat getControlTestMortality()
  {
    return getControlTestMortality(null);

  }
 
  public com.runwaysdk.query.SelectableFloat getControlTestMortality(String alias)
  {
    return (com.runwaysdk.query.SelectableFloat)this.getSelectable(dss.vector.solutions.export.entomology.assay.AdultDiscriminatingDoseAssayExcelView.CONTROLTESTMORTALITY, alias, null);

  }
 
  public com.runwaysdk.query.SelectableFloat getControlTestMortality(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableFloat)this.getSelectable(dss.vector.solutions.export.entomology.assay.AdultDiscriminatingDoseAssayExcelView.CONTROLTESTMORTALITY, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableInteger getExposureTime()
  {
    return getExposureTime(null);

  }
 
  public com.runwaysdk.query.SelectableInteger getExposureTime(String alias)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.export.entomology.assay.AdultDiscriminatingDoseAssayExcelView.EXPOSURETIME, alias, null);

  }
 
  public com.runwaysdk.query.SelectableInteger getExposureTime(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.export.entomology.assay.AdultDiscriminatingDoseAssayExcelView.EXPOSURETIME, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableInteger getFed()
  {
    return getFed(null);

  }
 
  public com.runwaysdk.query.SelectableInteger getFed(String alias)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.export.entomology.assay.AdultDiscriminatingDoseAssayExcelView.FED, alias, null);

  }
 
  public com.runwaysdk.query.SelectableInteger getFed(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.export.entomology.assay.AdultDiscriminatingDoseAssayExcelView.FED, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableChar getGeneration()
  {
    return getGeneration(null);

  }
 
  public com.runwaysdk.query.SelectableChar getGeneration(String alias)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(dss.vector.solutions.export.entomology.assay.AdultDiscriminatingDoseAssayExcelView.GENERATION, alias, null);

  }
 
  public com.runwaysdk.query.SelectableChar getGeneration(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(dss.vector.solutions.export.entomology.assay.AdultDiscriminatingDoseAssayExcelView.GENERATION, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableInteger getGravid()
  {
    return getGravid(null);

  }
 
  public com.runwaysdk.query.SelectableInteger getGravid(String alias)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.export.entomology.assay.AdultDiscriminatingDoseAssayExcelView.GRAVID, alias, null);

  }
 
  public com.runwaysdk.query.SelectableInteger getGravid(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.export.entomology.assay.AdultDiscriminatingDoseAssayExcelView.GRAVID, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableInteger getHoldingTime()
  {
    return getHoldingTime(null);

  }
 
  public com.runwaysdk.query.SelectableInteger getHoldingTime(String alias)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.export.entomology.assay.AdultDiscriminatingDoseAssayExcelView.HOLDINGTIME, alias, null);

  }
 
  public com.runwaysdk.query.SelectableInteger getHoldingTime(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.export.entomology.assay.AdultDiscriminatingDoseAssayExcelView.HOLDINGTIME, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableChar getId()
  {
    return getId(null);

  }
 
  public com.runwaysdk.query.SelectableChar getId(String alias)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(dss.vector.solutions.export.entomology.assay.AdultDiscriminatingDoseAssayExcelView.ID, alias, null);

  }
 
  public com.runwaysdk.query.SelectableChar getId(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(dss.vector.solutions.export.entomology.assay.AdultDiscriminatingDoseAssayExcelView.ID, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableChar getIdentificationMethod()
  {
    return getIdentificationMethod(null);

  }
 
  public com.runwaysdk.query.SelectableChar getIdentificationMethod(String alias)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(dss.vector.solutions.export.entomology.assay.AdultDiscriminatingDoseAssayExcelView.IDENTIFICATIONMETHOD, alias, null);

  }
 
  public com.runwaysdk.query.SelectableChar getIdentificationMethod(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(dss.vector.solutions.export.entomology.assay.AdultDiscriminatingDoseAssayExcelView.IDENTIFICATIONMETHOD, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableChar getInsecticideActiveIngredient()
  {
    return getInsecticideActiveIngredient(null);

  }
 
  public com.runwaysdk.query.SelectableChar getInsecticideActiveIngredient(String alias)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(dss.vector.solutions.export.entomology.assay.AdultDiscriminatingDoseAssayExcelView.INSECTICIDEACTIVEINGREDIENT, alias, null);

  }
 
  public com.runwaysdk.query.SelectableChar getInsecticideActiveIngredient(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(dss.vector.solutions.export.entomology.assay.AdultDiscriminatingDoseAssayExcelView.INSECTICIDEACTIVEINGREDIENT, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableDouble getInsecticideAmount()
  {
    return getInsecticideAmount(null);

  }
 
  public com.runwaysdk.query.SelectableDouble getInsecticideAmount(String alias)
  {
    return (com.runwaysdk.query.SelectableDouble)this.getSelectable(dss.vector.solutions.export.entomology.assay.AdultDiscriminatingDoseAssayExcelView.INSECTICIDEAMOUNT, alias, null);

  }
 
  public com.runwaysdk.query.SelectableDouble getInsecticideAmount(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableDouble)this.getSelectable(dss.vector.solutions.export.entomology.assay.AdultDiscriminatingDoseAssayExcelView.INSECTICIDEAMOUNT, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableChar getInsecticideUnits()
  {
    return getInsecticideUnits(null);

  }
 
  public com.runwaysdk.query.SelectableChar getInsecticideUnits(String alias)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(dss.vector.solutions.export.entomology.assay.AdultDiscriminatingDoseAssayExcelView.INSECTICIDEUNITS, alias, null);

  }
 
  public com.runwaysdk.query.SelectableChar getInsecticideUnits(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(dss.vector.solutions.export.entomology.assay.AdultDiscriminatingDoseAssayExcelView.INSECTICIDEUNITS, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableBoolean getIsofemale()
  {
    return getIsofemale(null);

  }
 
  public com.runwaysdk.query.SelectableBoolean getIsofemale(String alias)
  {
    return (com.runwaysdk.query.SelectableBoolean)this.getSelectable(dss.vector.solutions.export.entomology.assay.AdultDiscriminatingDoseAssayExcelView.ISOFEMALE, alias, null);

  }
 
  public com.runwaysdk.query.SelectableBoolean getIsofemale(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableBoolean)this.getSelectable(dss.vector.solutions.export.entomology.assay.AdultDiscriminatingDoseAssayExcelView.ISOFEMALE, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableDouble getKd50()
  {
    return getKd50(null);

  }
 
  public com.runwaysdk.query.SelectableDouble getKd50(String alias)
  {
    return (com.runwaysdk.query.SelectableDouble)this.getSelectable(dss.vector.solutions.export.entomology.assay.AdultDiscriminatingDoseAssayExcelView.KD50, alias, null);

  }
 
  public com.runwaysdk.query.SelectableDouble getKd50(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableDouble)this.getSelectable(dss.vector.solutions.export.entomology.assay.AdultDiscriminatingDoseAssayExcelView.KD50, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableDouble getKd95()
  {
    return getKd95(null);

  }
 
  public com.runwaysdk.query.SelectableDouble getKd95(String alias)
  {
    return (com.runwaysdk.query.SelectableDouble)this.getSelectable(dss.vector.solutions.export.entomology.assay.AdultDiscriminatingDoseAssayExcelView.KD95, alias, null);

  }
 
  public com.runwaysdk.query.SelectableDouble getKd95(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableDouble)this.getSelectable(dss.vector.solutions.export.entomology.assay.AdultDiscriminatingDoseAssayExcelView.KD95, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableInteger getQuantityDead()
  {
    return getQuantityDead(null);

  }
 
  public com.runwaysdk.query.SelectableInteger getQuantityDead(String alias)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.export.entomology.assay.AdultDiscriminatingDoseAssayExcelView.QUANTITYDEAD, alias, null);

  }
 
  public com.runwaysdk.query.SelectableInteger getQuantityDead(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.export.entomology.assay.AdultDiscriminatingDoseAssayExcelView.QUANTITYDEAD, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableInteger getQuantityTested()
  {
    return getQuantityTested(null);

  }
 
  public com.runwaysdk.query.SelectableInteger getQuantityTested(String alias)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.export.entomology.assay.AdultDiscriminatingDoseAssayExcelView.QUANTITYTESTED, alias, null);

  }
 
  public com.runwaysdk.query.SelectableInteger getQuantityTested(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.export.entomology.assay.AdultDiscriminatingDoseAssayExcelView.QUANTITYTESTED, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableChar getSex()
  {
    return getSex(null);

  }
 
  public com.runwaysdk.query.SelectableChar getSex(String alias)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(dss.vector.solutions.export.entomology.assay.AdultDiscriminatingDoseAssayExcelView.SEX, alias, null);

  }
 
  public com.runwaysdk.query.SelectableChar getSex(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(dss.vector.solutions.export.entomology.assay.AdultDiscriminatingDoseAssayExcelView.SEX, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableChar getSpecie()
  {
    return getSpecie(null);

  }
 
  public com.runwaysdk.query.SelectableChar getSpecie(String alias)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(dss.vector.solutions.export.entomology.assay.AdultDiscriminatingDoseAssayExcelView.SPECIE, alias, null);

  }
 
  public com.runwaysdk.query.SelectableChar getSpecie(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(dss.vector.solutions.export.entomology.assay.AdultDiscriminatingDoseAssayExcelView.SPECIE, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableMoment getTestDate()
  {
    return getTestDate(null);

  }
 
  public com.runwaysdk.query.SelectableMoment getTestDate(String alias)
  {
    return (com.runwaysdk.query.SelectableMoment)this.getSelectable(dss.vector.solutions.export.entomology.assay.AdultDiscriminatingDoseAssayExcelView.TESTDATE, alias, null);

  }
 
  public com.runwaysdk.query.SelectableMoment getTestDate(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableMoment)this.getSelectable(dss.vector.solutions.export.entomology.assay.AdultDiscriminatingDoseAssayExcelView.TESTDATE, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableChar getTestMethod()
  {
    return getTestMethod(null);

  }
 
  public com.runwaysdk.query.SelectableChar getTestMethod(String alias)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(dss.vector.solutions.export.entomology.assay.AdultDiscriminatingDoseAssayExcelView.TESTMETHOD, alias, null);

  }
 
  public com.runwaysdk.query.SelectableChar getTestMethod(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(dss.vector.solutions.export.entomology.assay.AdultDiscriminatingDoseAssayExcelView.TESTMETHOD, alias, displayLabel);

  }
 
  /**  
   * Returns an iterator of Business objects that match the query criteria specified
   * on this query object. 
   * @return iterator of Business objects that match the query criteria specified
   * on this query object.
   */
  @java.lang.SuppressWarnings("unchecked")
  public com.runwaysdk.query.OIterator<? extends AdultDiscriminatingDoseAssayExcelView> getIterator()
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
    return new com.runwaysdk.query.ViewIterator<AdultDiscriminatingDoseAssayExcelView>(this.getMdClassIF(), valueIterator);
  }

}
