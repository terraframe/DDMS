package dss.vector.solutions.irs;

@com.runwaysdk.business.ClassSignature(hash = 1951960069)
/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to TimeInterventionPlanningView.java
 *
 * @author Autogenerated by RunwaySDK
 */
public  abstract  class TimeInterventionPlanningViewQueryBase extends dss.vector.solutions.irs.InterventionPlanningViewQuery
 implements com.runwaysdk.generation.loader.Reloadable
{

  public TimeInterventionPlanningViewQueryBase(com.runwaysdk.query.QueryFactory componentQueryFactory)
  {
    super(componentQueryFactory);
  }

  public TimeInterventionPlanningViewQueryBase(com.runwaysdk.query.QueryFactory componentQueryFactory, com.runwaysdk.query.ViewQueryBuilder viewQueryBuilder)
  {
    super(componentQueryFactory, viewQueryBuilder);
  }
  public String getClassType()
  {
    return dss.vector.solutions.irs.TimeInterventionPlanningView.CLASS;
  }
  public com.runwaysdk.query.SelectableInteger getOperators()
  {
    return getOperators(null);

  }
 
  public com.runwaysdk.query.SelectableInteger getOperators(String alias)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.irs.TimeInterventionPlanningView.OPERATORS, alias, null);

  }
 
  public com.runwaysdk.query.SelectableInteger getOperators(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.irs.TimeInterventionPlanningView.OPERATORS, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableInteger getRequiredDays()
  {
    return getRequiredDays(null);

  }
 
  public com.runwaysdk.query.SelectableInteger getRequiredDays(String alias)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.irs.TimeInterventionPlanningView.REQUIREDDAYS, alias, null);

  }
 
  public com.runwaysdk.query.SelectableInteger getRequiredDays(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.irs.TimeInterventionPlanningView.REQUIREDDAYS, alias, displayLabel);

  }
 
  /**  
   * Returns an iterator of Business objects that match the query criteria specified
   * on this query object. 
   * @return iterator of Business objects that match the query criteria specified
   * on this query object.
   */
  @java.lang.SuppressWarnings("unchecked")
  public com.runwaysdk.query.OIterator<? extends TimeInterventionPlanningView> getIterator()
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
    return new com.runwaysdk.query.ViewIterator<TimeInterventionPlanningView>(this.getMdClassIF(), valueIterator);
  }

}
