package dss.vector.solutions.irs;

@com.terraframe.mojo.business.ClassSignature(hash = -25586811)
/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to TimeInterventionPlanningView.java
 *
 * @author Autogenerated by TerraFrame
 */
public  abstract  class TimeInterventionPlanningViewQueryBase extends dss.vector.solutions.irs.InterventionPlanningViewQuery
 implements com.terraframe.mojo.generation.loader.Reloadable
{
private static final long serialVersionUID = -25586811;

  public TimeInterventionPlanningViewQueryBase(com.terraframe.mojo.query.QueryFactory componentQueryFactory)
  {
    super(componentQueryFactory);
  }

  public TimeInterventionPlanningViewQueryBase(com.terraframe.mojo.query.QueryFactory componentQueryFactory, com.terraframe.mojo.query.ViewQueryBuilder viewQueryBuilder)
  {
    super(componentQueryFactory, viewQueryBuilder);
  }
  public String getClassType()
  {
    return dss.vector.solutions.irs.TimeInterventionPlanningView.CLASS;
  }
  public com.terraframe.mojo.query.SelectableSingleInteger getOperators()
  {
    return getOperators(null);

  }
 
  public com.terraframe.mojo.query.SelectableSingleInteger getOperators(String alias)
  {
    return (com.terraframe.mojo.query.SelectableSingleInteger)this.getSelectable(dss.vector.solutions.irs.TimeInterventionPlanningView.OPERATORS, alias, null);

  }
 
  public com.terraframe.mojo.query.SelectableSingleInteger getOperators(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.SelectableSingleInteger)this.getSelectable(dss.vector.solutions.irs.TimeInterventionPlanningView.OPERATORS, alias, displayLabel);

  }
 
  public com.terraframe.mojo.query.SelectableSingleInteger getRequiredDays()
  {
    return getRequiredDays(null);

  }
 
  public com.terraframe.mojo.query.SelectableSingleInteger getRequiredDays(String alias)
  {
    return (com.terraframe.mojo.query.SelectableSingleInteger)this.getSelectable(dss.vector.solutions.irs.TimeInterventionPlanningView.REQUIREDDAYS, alias, null);

  }
 
  public com.terraframe.mojo.query.SelectableSingleInteger getRequiredDays(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.SelectableSingleInteger)this.getSelectable(dss.vector.solutions.irs.TimeInterventionPlanningView.REQUIREDDAYS, alias, displayLabel);

  }
 
  /**  
   * Returns an iterator of Business objects that match the query criteria specified
   * on this query object. 
   * @return iterator of Business objects that match the query criteria specified
   * on this query object.
   */
  @java.lang.SuppressWarnings("unchecked")
  public com.terraframe.mojo.query.OIterator<? extends TimeInterventionPlanningView> getIterator()
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
    return new com.terraframe.mojo.query.ViewIterator<TimeInterventionPlanningView>(this.getMdClassIF(), valueIterator);
  }

}
