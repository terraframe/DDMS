package dss.vector.solutions.irs;

@com.runwaysdk.business.ClassSignature(hash = 1744027102)
/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to SupervisorView.java
 *
 * @author Autogenerated by RunwaySDK
 */
public  abstract  class SupervisorViewQueryBase extends dss.vector.solutions.PersonViewQuery
 implements com.runwaysdk.generation.loader.Reloadable
{

  public SupervisorViewQueryBase(com.runwaysdk.query.QueryFactory componentQueryFactory)
  {
    super(componentQueryFactory);
  }

  public SupervisorViewQueryBase(com.runwaysdk.query.QueryFactory componentQueryFactory, com.runwaysdk.query.ViewQueryBuilder viewQueryBuilder)
  {
    super(componentQueryFactory, viewQueryBuilder);
  }
  public String getClassType()
  {
    return dss.vector.solutions.irs.SupervisorView.CLASS;
  }
  public com.runwaysdk.query.SelectableChar getSupervisorId()
  {
    return getSupervisorId(null);

  }
 
  public com.runwaysdk.query.SelectableChar getSupervisorId(String alias)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(dss.vector.solutions.irs.SupervisorView.SUPERVISORID, alias, null);

  }
 
  public com.runwaysdk.query.SelectableChar getSupervisorId(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(dss.vector.solutions.irs.SupervisorView.SUPERVISORID, alias, displayLabel);

  }
 
  /**  
   * Returns an iterator of Business objects that match the query criteria specified
   * on this query object. 
   * @return iterator of Business objects that match the query criteria specified
   * on this query object.
   */
  @java.lang.SuppressWarnings("unchecked")
  public com.runwaysdk.query.OIterator<? extends SupervisorView> getIterator()
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
    return new com.runwaysdk.query.ViewIterator<SupervisorView>(this.getMdClassIF(), valueIterator);
  }

}
