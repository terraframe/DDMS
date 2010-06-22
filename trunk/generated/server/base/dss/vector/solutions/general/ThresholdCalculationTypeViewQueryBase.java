package dss.vector.solutions.general;

@com.runwaysdk.business.ClassSignature(hash = 437300755)
/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to ThresholdCalculationTypeView.java
 *
 * @author Autogenerated by RunwaySDK
 */
public  abstract  class ThresholdCalculationTypeViewQueryBase extends com.runwaysdk.query.GeneratedViewQuery
 implements com.runwaysdk.generation.loader.Reloadable
{
private static final long serialVersionUID = 437300755;

  public ThresholdCalculationTypeViewQueryBase(com.runwaysdk.query.QueryFactory componentQueryFactory)
  {
    super(componentQueryFactory);
  }

  public ThresholdCalculationTypeViewQueryBase(com.runwaysdk.query.QueryFactory componentQueryFactory, com.runwaysdk.query.ViewQueryBuilder viewQueryBuilder)
  {
    super(componentQueryFactory, viewQueryBuilder);
  }
  public String getClassType()
  {
    return dss.vector.solutions.general.ThresholdCalculationTypeView.CLASS;
  }
  public com.runwaysdk.query.SelectableBoolean getCalculationInterval()
  {
    return getCalculationInterval(null);

  }
 
  public com.runwaysdk.query.SelectableBoolean getCalculationInterval(String alias)
  {
    return (com.runwaysdk.query.SelectableBoolean)this.getSelectable(dss.vector.solutions.general.ThresholdCalculationTypeView.CALCULATIONINTERVAL, alias, null);

  }
 
  public com.runwaysdk.query.SelectableBoolean getCalculationInterval(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableBoolean)this.getSelectable(dss.vector.solutions.general.ThresholdCalculationTypeView.CALCULATIONINTERVAL, alias, displayLabel);

  }
 
  public dss.vector.solutions.general.ThresholdCalculationCaseTypesMasterQuery.ThresholdCalculationCaseTypesQueryIF getCaseTypes()
  {
    return getCaseTypes(null);

  }
 
  public dss.vector.solutions.general.ThresholdCalculationCaseTypesMasterQuery.ThresholdCalculationCaseTypesQueryIF getCaseTypes(String alias)
  {
    return (dss.vector.solutions.general.ThresholdCalculationCaseTypesMasterQuery.ThresholdCalculationCaseTypesQueryIF)this.getSelectable(dss.vector.solutions.general.ThresholdCalculationTypeView.CASETYPES, alias, null);

  }
 
  public dss.vector.solutions.general.ThresholdCalculationCaseTypesMasterQuery.ThresholdCalculationCaseTypesQueryIF getCaseTypes(String alias, String displayLabel)
  {
    return (dss.vector.solutions.general.ThresholdCalculationCaseTypesMasterQuery.ThresholdCalculationCaseTypesQueryIF)this.getSelectable(dss.vector.solutions.general.ThresholdCalculationTypeView.CASETYPES, alias, displayLabel);

  }
  public com.runwaysdk.query.SelectableInteger getClinicalPositivePercentage()
  {
    return getClinicalPositivePercentage(null);

  }
 
  public com.runwaysdk.query.SelectableInteger getClinicalPositivePercentage(String alias)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.general.ThresholdCalculationTypeView.CLINICALPOSITIVEPERCENTAGE, alias, null);

  }
 
  public com.runwaysdk.query.SelectableInteger getClinicalPositivePercentage(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.general.ThresholdCalculationTypeView.CLINICALPOSITIVEPERCENTAGE, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableChar getConcreteId()
  {
    return getConcreteId(null);

  }
 
  public com.runwaysdk.query.SelectableChar getConcreteId(String alias)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(dss.vector.solutions.general.ThresholdCalculationTypeView.CONCRETEID, alias, null);

  }
 
  public com.runwaysdk.query.SelectableChar getConcreteId(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(dss.vector.solutions.general.ThresholdCalculationTypeView.CONCRETEID, alias, displayLabel);

  }
 
  public dss.vector.solutions.general.OutbreakCalculationMasterQuery.OutbreakCalculationQueryIF getCountingMethod()
  {
    return getCountingMethod(null);

  }
 
  public dss.vector.solutions.general.OutbreakCalculationMasterQuery.OutbreakCalculationQueryIF getCountingMethod(String alias)
  {
    return (dss.vector.solutions.general.OutbreakCalculationMasterQuery.OutbreakCalculationQueryIF)this.getSelectable(dss.vector.solutions.general.ThresholdCalculationTypeView.COUNTINGMETHOD, alias, null);

  }
 
  public dss.vector.solutions.general.OutbreakCalculationMasterQuery.OutbreakCalculationQueryIF getCountingMethod(String alias, String displayLabel)
  {
    return (dss.vector.solutions.general.OutbreakCalculationMasterQuery.OutbreakCalculationQueryIF)this.getSelectable(dss.vector.solutions.general.ThresholdCalculationTypeView.COUNTINGMETHOD, alias, displayLabel);

  }
  public dss.vector.solutions.geo.GeoHierarchyQuery.GeoHierarchyQueryReferenceIF getEpidemicUniversal()
  {
    return getEpidemicUniversal(null);

  }
 
  public dss.vector.solutions.geo.GeoHierarchyQuery.GeoHierarchyQueryReferenceIF getEpidemicUniversal(String alias)
  {

    return (dss.vector.solutions.geo.GeoHierarchyQuery.GeoHierarchyQueryReferenceIF)this.getSelectable(dss.vector.solutions.general.ThresholdCalculationTypeView.EPIDEMICUNIVERSAL, alias, null);

  }
 
  public dss.vector.solutions.geo.GeoHierarchyQuery.GeoHierarchyQueryReferenceIF getEpidemicUniversal(String alias, String displayLabel)
  {

    return (dss.vector.solutions.geo.GeoHierarchyQuery.GeoHierarchyQueryReferenceIF)this.getSelectable(dss.vector.solutions.general.ThresholdCalculationTypeView.EPIDEMICUNIVERSAL, alias, displayLabel);

  }
  public com.runwaysdk.query.SelectableChar getId()
  {
    return getId(null);

  }
 
  public com.runwaysdk.query.SelectableChar getId(String alias)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(dss.vector.solutions.general.ThresholdCalculationTypeView.ID, alias, null);

  }
 
  public com.runwaysdk.query.SelectableChar getId(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(dss.vector.solutions.general.ThresholdCalculationTypeView.ID, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableInteger getPriorYears()
  {
    return getPriorYears(null);

  }
 
  public com.runwaysdk.query.SelectableInteger getPriorYears(String alias)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.general.ThresholdCalculationTypeView.PRIORYEARS, alias, null);

  }
 
  public com.runwaysdk.query.SelectableInteger getPriorYears(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.general.ThresholdCalculationTypeView.PRIORYEARS, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableDouble getSourceIdentificationMinimum()
  {
    return getSourceIdentificationMinimum(null);

  }
 
  public com.runwaysdk.query.SelectableDouble getSourceIdentificationMinimum(String alias)
  {
    return (com.runwaysdk.query.SelectableDouble)this.getSelectable(dss.vector.solutions.general.ThresholdCalculationTypeView.SOURCEIDENTIFICATIONMINIMUM, alias, null);

  }
 
  public com.runwaysdk.query.SelectableDouble getSourceIdentificationMinimum(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableDouble)this.getSelectable(dss.vector.solutions.general.ThresholdCalculationTypeView.SOURCEIDENTIFICATIONMINIMUM, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableDouble getSourceNotificationMinimum()
  {
    return getSourceNotificationMinimum(null);

  }
 
  public com.runwaysdk.query.SelectableDouble getSourceNotificationMinimum(String alias)
  {
    return (com.runwaysdk.query.SelectableDouble)this.getSelectable(dss.vector.solutions.general.ThresholdCalculationTypeView.SOURCENOTIFICATIONMINIMUM, alias, null);

  }
 
  public com.runwaysdk.query.SelectableDouble getSourceNotificationMinimum(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableDouble)this.getSelectable(dss.vector.solutions.general.ThresholdCalculationTypeView.SOURCENOTIFICATIONMINIMUM, alias, displayLabel);

  }
 
  public dss.vector.solutions.general.ThresholdCalculationMethodMasterQuery.ThresholdCalculationMethodQueryIF getT1Method()
  {
    return getT1Method(null);

  }
 
  public dss.vector.solutions.general.ThresholdCalculationMethodMasterQuery.ThresholdCalculationMethodQueryIF getT1Method(String alias)
  {
    return (dss.vector.solutions.general.ThresholdCalculationMethodMasterQuery.ThresholdCalculationMethodQueryIF)this.getSelectable(dss.vector.solutions.general.ThresholdCalculationTypeView.T1METHOD, alias, null);

  }
 
  public dss.vector.solutions.general.ThresholdCalculationMethodMasterQuery.ThresholdCalculationMethodQueryIF getT1Method(String alias, String displayLabel)
  {
    return (dss.vector.solutions.general.ThresholdCalculationMethodMasterQuery.ThresholdCalculationMethodQueryIF)this.getSelectable(dss.vector.solutions.general.ThresholdCalculationTypeView.T1METHOD, alias, displayLabel);

  }
  public dss.vector.solutions.general.ThresholdCalculationMethodMasterQuery.ThresholdCalculationMethodQueryIF getT2Method()
  {
    return getT2Method(null);

  }
 
  public dss.vector.solutions.general.ThresholdCalculationMethodMasterQuery.ThresholdCalculationMethodQueryIF getT2Method(String alias)
  {
    return (dss.vector.solutions.general.ThresholdCalculationMethodMasterQuery.ThresholdCalculationMethodQueryIF)this.getSelectable(dss.vector.solutions.general.ThresholdCalculationTypeView.T2METHOD, alias, null);

  }
 
  public dss.vector.solutions.general.ThresholdCalculationMethodMasterQuery.ThresholdCalculationMethodQueryIF getT2Method(String alias, String displayLabel)
  {
    return (dss.vector.solutions.general.ThresholdCalculationMethodMasterQuery.ThresholdCalculationMethodQueryIF)this.getSelectable(dss.vector.solutions.general.ThresholdCalculationTypeView.T2METHOD, alias, displayLabel);

  }
  public com.runwaysdk.query.SelectableInteger getWeeksAfter()
  {
    return getWeeksAfter(null);

  }
 
  public com.runwaysdk.query.SelectableInteger getWeeksAfter(String alias)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.general.ThresholdCalculationTypeView.WEEKSAFTER, alias, null);

  }
 
  public com.runwaysdk.query.SelectableInteger getWeeksAfter(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.general.ThresholdCalculationTypeView.WEEKSAFTER, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableInteger getWeeksBefore()
  {
    return getWeeksBefore(null);

  }
 
  public com.runwaysdk.query.SelectableInteger getWeeksBefore(String alias)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.general.ThresholdCalculationTypeView.WEEKSBEFORE, alias, null);

  }
 
  public com.runwaysdk.query.SelectableInteger getWeeksBefore(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.general.ThresholdCalculationTypeView.WEEKSBEFORE, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableDouble getWeight0()
  {
    return getWeight0(null);

  }
 
  public com.runwaysdk.query.SelectableDouble getWeight0(String alias)
  {
    return (com.runwaysdk.query.SelectableDouble)this.getSelectable(dss.vector.solutions.general.ThresholdCalculationTypeView.WEIGHT0, alias, null);

  }
 
  public com.runwaysdk.query.SelectableDouble getWeight0(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableDouble)this.getSelectable(dss.vector.solutions.general.ThresholdCalculationTypeView.WEIGHT0, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableDouble getWeight1()
  {
    return getWeight1(null);

  }
 
  public com.runwaysdk.query.SelectableDouble getWeight1(String alias)
  {
    return (com.runwaysdk.query.SelectableDouble)this.getSelectable(dss.vector.solutions.general.ThresholdCalculationTypeView.WEIGHT1, alias, null);

  }
 
  public com.runwaysdk.query.SelectableDouble getWeight1(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableDouble)this.getSelectable(dss.vector.solutions.general.ThresholdCalculationTypeView.WEIGHT1, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableDouble getWeight2()
  {
    return getWeight2(null);

  }
 
  public com.runwaysdk.query.SelectableDouble getWeight2(String alias)
  {
    return (com.runwaysdk.query.SelectableDouble)this.getSelectable(dss.vector.solutions.general.ThresholdCalculationTypeView.WEIGHT2, alias, null);

  }
 
  public com.runwaysdk.query.SelectableDouble getWeight2(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableDouble)this.getSelectable(dss.vector.solutions.general.ThresholdCalculationTypeView.WEIGHT2, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableDouble getWeight3()
  {
    return getWeight3(null);

  }
 
  public com.runwaysdk.query.SelectableDouble getWeight3(String alias)
  {
    return (com.runwaysdk.query.SelectableDouble)this.getSelectable(dss.vector.solutions.general.ThresholdCalculationTypeView.WEIGHT3, alias, null);

  }
 
  public com.runwaysdk.query.SelectableDouble getWeight3(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableDouble)this.getSelectable(dss.vector.solutions.general.ThresholdCalculationTypeView.WEIGHT3, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableDouble getWeight4()
  {
    return getWeight4(null);

  }
 
  public com.runwaysdk.query.SelectableDouble getWeight4(String alias)
  {
    return (com.runwaysdk.query.SelectableDouble)this.getSelectable(dss.vector.solutions.general.ThresholdCalculationTypeView.WEIGHT4, alias, null);

  }
 
  public com.runwaysdk.query.SelectableDouble getWeight4(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableDouble)this.getSelectable(dss.vector.solutions.general.ThresholdCalculationTypeView.WEIGHT4, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableDouble getWeight5()
  {
    return getWeight5(null);

  }
 
  public com.runwaysdk.query.SelectableDouble getWeight5(String alias)
  {
    return (com.runwaysdk.query.SelectableDouble)this.getSelectable(dss.vector.solutions.general.ThresholdCalculationTypeView.WEIGHT5, alias, null);

  }
 
  public com.runwaysdk.query.SelectableDouble getWeight5(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableDouble)this.getSelectable(dss.vector.solutions.general.ThresholdCalculationTypeView.WEIGHT5, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableDouble getWeight6()
  {
    return getWeight6(null);

  }
 
  public com.runwaysdk.query.SelectableDouble getWeight6(String alias)
  {
    return (com.runwaysdk.query.SelectableDouble)this.getSelectable(dss.vector.solutions.general.ThresholdCalculationTypeView.WEIGHT6, alias, null);

  }
 
  public com.runwaysdk.query.SelectableDouble getWeight6(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableDouble)this.getSelectable(dss.vector.solutions.general.ThresholdCalculationTypeView.WEIGHT6, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableDouble getWeight7()
  {
    return getWeight7(null);

  }
 
  public com.runwaysdk.query.SelectableDouble getWeight7(String alias)
  {
    return (com.runwaysdk.query.SelectableDouble)this.getSelectable(dss.vector.solutions.general.ThresholdCalculationTypeView.WEIGHT7, alias, null);

  }
 
  public com.runwaysdk.query.SelectableDouble getWeight7(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableDouble)this.getSelectable(dss.vector.solutions.general.ThresholdCalculationTypeView.WEIGHT7, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableDouble getWeight8()
  {
    return getWeight8(null);

  }
 
  public com.runwaysdk.query.SelectableDouble getWeight8(String alias)
  {
    return (com.runwaysdk.query.SelectableDouble)this.getSelectable(dss.vector.solutions.general.ThresholdCalculationTypeView.WEIGHT8, alias, null);

  }
 
  public com.runwaysdk.query.SelectableDouble getWeight8(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableDouble)this.getSelectable(dss.vector.solutions.general.ThresholdCalculationTypeView.WEIGHT8, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableDouble getWeight9()
  {
    return getWeight9(null);

  }
 
  public com.runwaysdk.query.SelectableDouble getWeight9(String alias)
  {
    return (com.runwaysdk.query.SelectableDouble)this.getSelectable(dss.vector.solutions.general.ThresholdCalculationTypeView.WEIGHT9, alias, null);

  }
 
  public com.runwaysdk.query.SelectableDouble getWeight9(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableDouble)this.getSelectable(dss.vector.solutions.general.ThresholdCalculationTypeView.WEIGHT9, alias, displayLabel);

  }
 
  /**  
   * Returns an iterator of Business objects that match the query criteria specified
   * on this query object. 
   * @return iterator of Business objects that match the query criteria specified
   * on this query object.
   */
  @java.lang.SuppressWarnings("unchecked")
  public com.runwaysdk.query.OIterator<? extends ThresholdCalculationTypeView> getIterator()
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
    return new com.runwaysdk.query.ViewIterator<ThresholdCalculationTypeView>(this.getMdClassIF(), valueIterator);
  }

}
