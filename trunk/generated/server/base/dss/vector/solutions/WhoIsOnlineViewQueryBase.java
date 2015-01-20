package dss.vector.solutions;

@com.runwaysdk.business.ClassSignature(hash = 7973945)
/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to WhoIsOnlineView.java
 *
 * @author Autogenerated by RunwaySDK
 */
public  abstract  class WhoIsOnlineViewQueryBase extends com.runwaysdk.query.GeneratedViewQuery
 implements com.runwaysdk.generation.loader.Reloadable
{

  public WhoIsOnlineViewQueryBase(com.runwaysdk.query.QueryFactory componentQueryFactory)
  {
    super(componentQueryFactory);
  }

  public WhoIsOnlineViewQueryBase(com.runwaysdk.query.QueryFactory componentQueryFactory, com.runwaysdk.query.ViewQueryBuilder viewQueryBuilder)
  {
    super(componentQueryFactory, viewQueryBuilder);
  }
  public String getClassType()
  {
    return dss.vector.solutions.WhoIsOnlineView.CLASS;
  }
  public com.runwaysdk.query.SelectableChar getId()
  {
    return getId(null);

  }
 
  public com.runwaysdk.query.SelectableChar getId(String alias)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(dss.vector.solutions.WhoIsOnlineView.ID, alias, null);

  }
 
  public com.runwaysdk.query.SelectableChar getId(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(dss.vector.solutions.WhoIsOnlineView.ID, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableChar getLocale()
  {
    return getLocale(null);

  }
 
  public com.runwaysdk.query.SelectableChar getLocale(String alias)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(dss.vector.solutions.WhoIsOnlineView.LOCALE, alias, null);

  }
 
  public com.runwaysdk.query.SelectableChar getLocale(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(dss.vector.solutions.WhoIsOnlineView.LOCALE, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableChar getUsername()
  {
    return getUsername(null);

  }
 
  public com.runwaysdk.query.SelectableChar getUsername(String alias)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(dss.vector.solutions.WhoIsOnlineView.USERNAME, alias, null);

  }
 
  public com.runwaysdk.query.SelectableChar getUsername(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(dss.vector.solutions.WhoIsOnlineView.USERNAME, alias, displayLabel);

  }
 
  /**  
   * Returns an iterator of Business objects that match the query criteria specified
   * on this query object. 
   * @return iterator of Business objects that match the query criteria specified
   * on this query object.
   */
  @java.lang.SuppressWarnings("unchecked")
  public com.runwaysdk.query.OIterator<? extends WhoIsOnlineView> getIterator()
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
    return new com.runwaysdk.query.ViewIterator<WhoIsOnlineView>(this.getMdClassIF(), valueIterator);
  }

}