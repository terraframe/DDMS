package dss.vector.solutions.irs;

@com.terraframe.mojo.business.ClassSignature(hash = -1846867763)
/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to InsecticideInterventionPlanningView.java
 *
 * @author Autogenerated by TerraFrame
 */
public  abstract  class InsecticideInterventionPlanningViewQueryBase extends dss.vector.solutions.irs.InterventionPlanningViewQuery
 implements com.terraframe.mojo.generation.loader.Reloadable
{
private static final long serialVersionUID = -1846867763;

  public InsecticideInterventionPlanningViewQueryBase(com.terraframe.mojo.query.QueryFactory componentQueryFactory)
  {
    super(componentQueryFactory);
  }

  public InsecticideInterventionPlanningViewQueryBase(com.terraframe.mojo.query.QueryFactory componentQueryFactory, com.terraframe.mojo.query.ViewQueryBuilder viewQueryBuilder)
  {
    super(componentQueryFactory, viewQueryBuilder);
  }
  public String getClassType()
  {
    return dss.vector.solutions.irs.InsecticideInterventionPlanningView.CLASS;
  }
  public com.terraframe.mojo.query.SelectableDouble getRequiredInsecticide()
  {
    return getRequiredInsecticide(null);

  }
 
  public com.terraframe.mojo.query.SelectableDouble getRequiredInsecticide(String alias)
  {
    return (com.terraframe.mojo.query.SelectableDouble)this.getSelectable(dss.vector.solutions.irs.InsecticideInterventionPlanningView.REQUIREDINSECTICIDE, alias, null);

  }
 
  public com.terraframe.mojo.query.SelectableDouble getRequiredInsecticide(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.SelectableDouble)this.getSelectable(dss.vector.solutions.irs.InsecticideInterventionPlanningView.REQUIREDINSECTICIDE, alias, displayLabel);

  }
 
  /**  
   * Returns an iterator of Business objects that match the query criteria specified
   * on this query object. 
   * @return iterator of Business objects that match the query criteria specified
   * on this query object.
   */
  @java.lang.SuppressWarnings("unchecked")
  public com.terraframe.mojo.query.OIterator<? extends InsecticideInterventionPlanningView> getIterator()
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
    return new com.terraframe.mojo.query.ViewIterator<InsecticideInterventionPlanningView>(this.getMdClassIF(), valueIterator);
  }

}
