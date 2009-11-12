package dss.vector.solutions.general;

@com.terraframe.mojo.business.ClassSignature(hash = -791308227)
/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to ThresholdCalculationTypeView.java
 *
 * @author Autogenerated by TerraFrame
 */
public  abstract  class ThresholdCalculationTypeViewQueryBase extends com.terraframe.mojo.query.GeneratedViewQuery
 implements com.terraframe.mojo.generation.loader.Reloadable
{
private static final long serialVersionUID = -791308227;

  public ThresholdCalculationTypeViewQueryBase(com.terraframe.mojo.query.QueryFactory componentQueryFactory)
  {
    super(componentQueryFactory);
  }

  public ThresholdCalculationTypeViewQueryBase(com.terraframe.mojo.query.QueryFactory componentQueryFactory, com.terraframe.mojo.query.ViewQueryBuilder viewQueryBuilder)
  {
    super(componentQueryFactory, viewQueryBuilder);
  }
  public String getClassType()
  {
    return dss.vector.solutions.general.ThresholdCalculationTypeView.CLASS;
  }
  public com.terraframe.mojo.query.AttributeChar getConcreteId()
  {
    return getConcreteId(null);

  }
 
  public com.terraframe.mojo.query.AttributeChar getConcreteId(String alias)
  {
    return (com.terraframe.mojo.query.AttributeChar)this.getSelectable(dss.vector.solutions.general.ThresholdCalculationTypeView.CONCRETEID, alias, null);

  }
 
  public com.terraframe.mojo.query.AttributeChar getConcreteId(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.AttributeChar)this.getSelectable(dss.vector.solutions.general.ThresholdCalculationTypeView.CONCRETEID, alias, displayLabel);

  }
 
  public com.terraframe.mojo.query.AttributeChar getId()
  {
    return getId(null);

  }
 
  public com.terraframe.mojo.query.AttributeChar getId(String alias)
  {
    return (com.terraframe.mojo.query.AttributeChar)this.getSelectable(dss.vector.solutions.general.ThresholdCalculationTypeView.ID, alias, null);

  }
 
  public com.terraframe.mojo.query.AttributeChar getId(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.AttributeChar)this.getSelectable(dss.vector.solutions.general.ThresholdCalculationTypeView.ID, alias, displayLabel);

  }
 
  public com.terraframe.mojo.query.AttributeInteger getPriorYears()
  {
    return getPriorYears(null);

  }
 
  public com.terraframe.mojo.query.AttributeInteger getPriorYears(String alias)
  {
    return (com.terraframe.mojo.query.AttributeInteger)this.getSelectable(dss.vector.solutions.general.ThresholdCalculationTypeView.PRIORYEARS, alias, null);

  }
 
  public com.terraframe.mojo.query.AttributeInteger getPriorYears(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.AttributeInteger)this.getSelectable(dss.vector.solutions.general.ThresholdCalculationTypeView.PRIORYEARS, alias, displayLabel);

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
  public com.terraframe.mojo.query.AttributeInteger getWeeksAfter()
  {
    return getWeeksAfter(null);

  }
 
  public com.terraframe.mojo.query.AttributeInteger getWeeksAfter(String alias)
  {
    return (com.terraframe.mojo.query.AttributeInteger)this.getSelectable(dss.vector.solutions.general.ThresholdCalculationTypeView.WEEKSAFTER, alias, null);

  }
 
  public com.terraframe.mojo.query.AttributeInteger getWeeksAfter(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.AttributeInteger)this.getSelectable(dss.vector.solutions.general.ThresholdCalculationTypeView.WEEKSAFTER, alias, displayLabel);

  }
 
  public com.terraframe.mojo.query.AttributeInteger getWeeksBefore()
  {
    return getWeeksBefore(null);

  }
 
  public com.terraframe.mojo.query.AttributeInteger getWeeksBefore(String alias)
  {
    return (com.terraframe.mojo.query.AttributeInteger)this.getSelectable(dss.vector.solutions.general.ThresholdCalculationTypeView.WEEKSBEFORE, alias, null);

  }
 
  public com.terraframe.mojo.query.AttributeInteger getWeeksBefore(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.AttributeInteger)this.getSelectable(dss.vector.solutions.general.ThresholdCalculationTypeView.WEEKSBEFORE, alias, displayLabel);

  }
 
  public com.terraframe.mojo.query.AttributeDouble getWeight0()
  {
    return getWeight0(null);

  }
 
  public com.terraframe.mojo.query.AttributeDouble getWeight0(String alias)
  {
    return (com.terraframe.mojo.query.AttributeDouble)this.getSelectable(dss.vector.solutions.general.ThresholdCalculationTypeView.WEIGHT0, alias, null);

  }
 
  public com.terraframe.mojo.query.AttributeDouble getWeight0(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.AttributeDouble)this.getSelectable(dss.vector.solutions.general.ThresholdCalculationTypeView.WEIGHT0, alias, displayLabel);

  }
 
  public com.terraframe.mojo.query.AttributeDouble getWeight1()
  {
    return getWeight1(null);

  }
 
  public com.terraframe.mojo.query.AttributeDouble getWeight1(String alias)
  {
    return (com.terraframe.mojo.query.AttributeDouble)this.getSelectable(dss.vector.solutions.general.ThresholdCalculationTypeView.WEIGHT1, alias, null);

  }
 
  public com.terraframe.mojo.query.AttributeDouble getWeight1(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.AttributeDouble)this.getSelectable(dss.vector.solutions.general.ThresholdCalculationTypeView.WEIGHT1, alias, displayLabel);

  }
 
  public com.terraframe.mojo.query.AttributeDouble getWeight2()
  {
    return getWeight2(null);

  }
 
  public com.terraframe.mojo.query.AttributeDouble getWeight2(String alias)
  {
    return (com.terraframe.mojo.query.AttributeDouble)this.getSelectable(dss.vector.solutions.general.ThresholdCalculationTypeView.WEIGHT2, alias, null);

  }
 
  public com.terraframe.mojo.query.AttributeDouble getWeight2(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.AttributeDouble)this.getSelectable(dss.vector.solutions.general.ThresholdCalculationTypeView.WEIGHT2, alias, displayLabel);

  }
 
  public com.terraframe.mojo.query.AttributeDouble getWeight3()
  {
    return getWeight3(null);

  }
 
  public com.terraframe.mojo.query.AttributeDouble getWeight3(String alias)
  {
    return (com.terraframe.mojo.query.AttributeDouble)this.getSelectable(dss.vector.solutions.general.ThresholdCalculationTypeView.WEIGHT3, alias, null);

  }
 
  public com.terraframe.mojo.query.AttributeDouble getWeight3(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.AttributeDouble)this.getSelectable(dss.vector.solutions.general.ThresholdCalculationTypeView.WEIGHT3, alias, displayLabel);

  }
 
  public com.terraframe.mojo.query.AttributeDouble getWeight4()
  {
    return getWeight4(null);

  }
 
  public com.terraframe.mojo.query.AttributeDouble getWeight4(String alias)
  {
    return (com.terraframe.mojo.query.AttributeDouble)this.getSelectable(dss.vector.solutions.general.ThresholdCalculationTypeView.WEIGHT4, alias, null);

  }
 
  public com.terraframe.mojo.query.AttributeDouble getWeight4(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.AttributeDouble)this.getSelectable(dss.vector.solutions.general.ThresholdCalculationTypeView.WEIGHT4, alias, displayLabel);

  }
 
  public com.terraframe.mojo.query.AttributeDouble getWeight5()
  {
    return getWeight5(null);

  }
 
  public com.terraframe.mojo.query.AttributeDouble getWeight5(String alias)
  {
    return (com.terraframe.mojo.query.AttributeDouble)this.getSelectable(dss.vector.solutions.general.ThresholdCalculationTypeView.WEIGHT5, alias, null);

  }
 
  public com.terraframe.mojo.query.AttributeDouble getWeight5(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.AttributeDouble)this.getSelectable(dss.vector.solutions.general.ThresholdCalculationTypeView.WEIGHT5, alias, displayLabel);

  }
 
  public com.terraframe.mojo.query.AttributeDouble getWeight6()
  {
    return getWeight6(null);

  }
 
  public com.terraframe.mojo.query.AttributeDouble getWeight6(String alias)
  {
    return (com.terraframe.mojo.query.AttributeDouble)this.getSelectable(dss.vector.solutions.general.ThresholdCalculationTypeView.WEIGHT6, alias, null);

  }
 
  public com.terraframe.mojo.query.AttributeDouble getWeight6(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.AttributeDouble)this.getSelectable(dss.vector.solutions.general.ThresholdCalculationTypeView.WEIGHT6, alias, displayLabel);

  }
 
  public com.terraframe.mojo.query.AttributeDouble getWeight7()
  {
    return getWeight7(null);

  }
 
  public com.terraframe.mojo.query.AttributeDouble getWeight7(String alias)
  {
    return (com.terraframe.mojo.query.AttributeDouble)this.getSelectable(dss.vector.solutions.general.ThresholdCalculationTypeView.WEIGHT7, alias, null);

  }
 
  public com.terraframe.mojo.query.AttributeDouble getWeight7(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.AttributeDouble)this.getSelectable(dss.vector.solutions.general.ThresholdCalculationTypeView.WEIGHT7, alias, displayLabel);

  }
 
  public com.terraframe.mojo.query.AttributeDouble getWeight8()
  {
    return getWeight8(null);

  }
 
  public com.terraframe.mojo.query.AttributeDouble getWeight8(String alias)
  {
    return (com.terraframe.mojo.query.AttributeDouble)this.getSelectable(dss.vector.solutions.general.ThresholdCalculationTypeView.WEIGHT8, alias, null);

  }
 
  public com.terraframe.mojo.query.AttributeDouble getWeight8(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.AttributeDouble)this.getSelectable(dss.vector.solutions.general.ThresholdCalculationTypeView.WEIGHT8, alias, displayLabel);

  }
 
  public com.terraframe.mojo.query.AttributeDouble getWeight9()
  {
    return getWeight9(null);

  }
 
  public com.terraframe.mojo.query.AttributeDouble getWeight9(String alias)
  {
    return (com.terraframe.mojo.query.AttributeDouble)this.getSelectable(dss.vector.solutions.general.ThresholdCalculationTypeView.WEIGHT9, alias, null);

  }
 
  public com.terraframe.mojo.query.AttributeDouble getWeight9(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.AttributeDouble)this.getSelectable(dss.vector.solutions.general.ThresholdCalculationTypeView.WEIGHT9, alias, displayLabel);

  }
 
  /**  
   * Returns an iterator of Business objects that match the query criteria specified
   * on this query object. 
   * @return iterator of Business objects that match the query criteria specified
   * on this query object.
   */
  @java.lang.SuppressWarnings("unchecked")
  public com.terraframe.mojo.query.OIterator<? extends ThresholdCalculationTypeView> getIterator()
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
    return new com.terraframe.mojo.query.ViewIterator<ThresholdCalculationTypeView>(this.getMdClassIF(), valueIterator);
  }

}
