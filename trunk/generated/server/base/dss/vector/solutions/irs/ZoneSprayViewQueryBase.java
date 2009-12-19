package dss.vector.solutions.irs;

@com.terraframe.mojo.business.ClassSignature(hash = 2036233408)
/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to ZoneSprayView.java
 *
 * @author Autogenerated by TerraFrame
 */
public  abstract  class ZoneSprayViewQueryBase extends dss.vector.solutions.irs.AbstractSprayViewQuery
 implements com.terraframe.mojo.generation.loader.Reloadable
{
private static final long serialVersionUID = 2036233408;

  public ZoneSprayViewQueryBase(com.terraframe.mojo.query.QueryFactory componentQueryFactory)
  {
    super(componentQueryFactory);
  }

  public ZoneSprayViewQueryBase(com.terraframe.mojo.query.QueryFactory componentQueryFactory, com.terraframe.mojo.query.ViewQueryBuilder viewQueryBuilder)
  {
    super(componentQueryFactory, viewQueryBuilder);
  }
  public String getClassType()
  {
    return dss.vector.solutions.irs.ZoneSprayView.CLASS;
  }
  public com.terraframe.mojo.query.AttributeInteger getSprayWeek()
  {
    return getSprayWeek(null);

  }
 
  public com.terraframe.mojo.query.AttributeInteger getSprayWeek(String alias)
  {
    return (com.terraframe.mojo.query.AttributeInteger)this.getSelectable(dss.vector.solutions.irs.ZoneSprayView.SPRAYWEEK, alias, null);

  }
 
  public com.terraframe.mojo.query.AttributeInteger getSprayWeek(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.AttributeInteger)this.getSelectable(dss.vector.solutions.irs.ZoneSprayView.SPRAYWEEK, alias, displayLabel);

  }
 
  public com.terraframe.mojo.query.AttributeChar getSupervisorName()
  {
    return getSupervisorName(null);

  }
 
  public com.terraframe.mojo.query.AttributeChar getSupervisorName(String alias)
  {
    return (com.terraframe.mojo.query.AttributeChar)this.getSelectable(dss.vector.solutions.irs.ZoneSprayView.SUPERVISORNAME, alias, null);

  }
 
  public com.terraframe.mojo.query.AttributeChar getSupervisorName(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.AttributeChar)this.getSelectable(dss.vector.solutions.irs.ZoneSprayView.SUPERVISORNAME, alias, displayLabel);

  }
 
  public com.terraframe.mojo.query.AttributeChar getSupervisorSurname()
  {
    return getSupervisorSurname(null);

  }
 
  public com.terraframe.mojo.query.AttributeChar getSupervisorSurname(String alias)
  {
    return (com.terraframe.mojo.query.AttributeChar)this.getSelectable(dss.vector.solutions.irs.ZoneSprayView.SUPERVISORSURNAME, alias, null);

  }
 
  public com.terraframe.mojo.query.AttributeChar getSupervisorSurname(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.AttributeChar)this.getSelectable(dss.vector.solutions.irs.ZoneSprayView.SUPERVISORSURNAME, alias, displayLabel);

  }
 
  public com.terraframe.mojo.query.AttributeInteger getTarget()
  {
    return getTarget(null);

  }
 
  public com.terraframe.mojo.query.AttributeInteger getTarget(String alias)
  {
    return (com.terraframe.mojo.query.AttributeInteger)this.getSelectable(dss.vector.solutions.irs.ZoneSprayView.TARGET, alias, null);

  }
 
  public com.terraframe.mojo.query.AttributeInteger getTarget(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.AttributeInteger)this.getSelectable(dss.vector.solutions.irs.ZoneSprayView.TARGET, alias, displayLabel);

  }
 
  /**  
   * Returns an iterator of Business objects that match the query criteria specified
   * on this query object. 
   * @return iterator of Business objects that match the query criteria specified
   * on this query object.
   */
  @java.lang.SuppressWarnings("unchecked")
  public com.terraframe.mojo.query.OIterator<? extends ZoneSprayView> getIterator()
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
    return new com.terraframe.mojo.query.ViewIterator<ZoneSprayView>(this.getMdClassIF(), valueIterator);
  }

}
