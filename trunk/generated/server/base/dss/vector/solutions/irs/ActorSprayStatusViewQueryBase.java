package dss.vector.solutions.irs;

@com.terraframe.mojo.business.ClassSignature(hash = -864375856)
/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to ActorSprayStatusView.java
 *
 * @author Autogenerated by TerraFrame
 */
public  abstract  class ActorSprayStatusViewQueryBase extends dss.vector.solutions.irs.SprayStatusViewQuery
 implements com.terraframe.mojo.generation.loader.Reloadable
{
private static final long serialVersionUID = -864375856;

  public ActorSprayStatusViewQueryBase(com.terraframe.mojo.query.QueryFactory componentQueryFactory)
  {
    super(componentQueryFactory);
  }

  public ActorSprayStatusViewQueryBase(com.terraframe.mojo.query.QueryFactory componentQueryFactory, com.terraframe.mojo.query.ViewQueryBuilder viewQueryBuilder)
  {
    super(componentQueryFactory, viewQueryBuilder);
  }
  public String getClassType()
  {
    return dss.vector.solutions.irs.ActorSprayStatusView.CLASS;
  }
  public com.terraframe.mojo.query.AttributeInteger getReceived()
  {
    return getReceived(null);

  }
 
  public com.terraframe.mojo.query.AttributeInteger getReceived(String alias)
  {
    return (com.terraframe.mojo.query.AttributeInteger)this.getSelectable(dss.vector.solutions.irs.ActorSprayStatusView.RECEIVED, alias, null);

  }
 
  public com.terraframe.mojo.query.AttributeInteger getReceived(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.AttributeInteger)this.getSelectable(dss.vector.solutions.irs.ActorSprayStatusView.RECEIVED, alias, displayLabel);

  }
 
  public com.terraframe.mojo.query.AttributeInteger getRefills()
  {
    return getRefills(null);

  }
 
  public com.terraframe.mojo.query.AttributeInteger getRefills(String alias)
  {
    return (com.terraframe.mojo.query.AttributeInteger)this.getSelectable(dss.vector.solutions.irs.ActorSprayStatusView.REFILLS, alias, null);

  }
 
  public com.terraframe.mojo.query.AttributeInteger getRefills(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.AttributeInteger)this.getSelectable(dss.vector.solutions.irs.ActorSprayStatusView.REFILLS, alias, displayLabel);

  }
 
  public com.terraframe.mojo.query.AttributeInteger getReturned()
  {
    return getReturned(null);

  }
 
  public com.terraframe.mojo.query.AttributeInteger getReturned(String alias)
  {
    return (com.terraframe.mojo.query.AttributeInteger)this.getSelectable(dss.vector.solutions.irs.ActorSprayStatusView.RETURNED, alias, null);

  }
 
  public com.terraframe.mojo.query.AttributeInteger getReturned(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.AttributeInteger)this.getSelectable(dss.vector.solutions.irs.ActorSprayStatusView.RETURNED, alias, displayLabel);

  }
 
  public dss.vector.solutions.irs.SprayDataQuery.SprayDataQueryReferenceIF getSprayData()
  {
    return getSprayData(null);

  }
 
  public dss.vector.solutions.irs.SprayDataQuery.SprayDataQueryReferenceIF getSprayData(String alias)
  {

    return (dss.vector.solutions.irs.SprayDataQuery.SprayDataQueryReferenceIF)this.getSelectable(dss.vector.solutions.irs.ActorSprayStatusView.SPRAYDATA, alias, null);

  }
 
  public dss.vector.solutions.irs.SprayDataQuery.SprayDataQueryReferenceIF getSprayData(String alias, String displayLabel)
  {

    return (dss.vector.solutions.irs.SprayDataQuery.SprayDataQueryReferenceIF)this.getSelectable(dss.vector.solutions.irs.ActorSprayStatusView.SPRAYDATA, alias, displayLabel);

  }
  public com.terraframe.mojo.query.AttributeInteger getUsed()
  {
    return getUsed(null);

  }
 
  public com.terraframe.mojo.query.AttributeInteger getUsed(String alias)
  {
    return (com.terraframe.mojo.query.AttributeInteger)this.getSelectable(dss.vector.solutions.irs.ActorSprayStatusView.USED, alias, null);

  }
 
  public com.terraframe.mojo.query.AttributeInteger getUsed(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.AttributeInteger)this.getSelectable(dss.vector.solutions.irs.ActorSprayStatusView.USED, alias, displayLabel);

  }
 
  /**  
   * Returns an iterator of Business objects that match the query criteria specified
   * on this query object. 
   * @return iterator of Business objects that match the query criteria specified
   * on this query object.
   */
  @java.lang.SuppressWarnings("unchecked")
  public com.terraframe.mojo.query.OIterator<? extends ActorSprayStatusView> getIterator()
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
    return new com.terraframe.mojo.query.ViewIterator<ActorSprayStatusView>(this.getMdClassIF(), valueIterator);
  }

}
