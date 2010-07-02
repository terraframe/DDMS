package dss.vector.solutions.permission;

@com.runwaysdk.business.ClassSignature(hash = 144530637)
/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to UniversalPermissionView.java
 *
 * @author Autogenerated by RunwaySDK
 */
public  abstract  class UniversalPermissionViewQueryBase extends com.runwaysdk.query.GeneratedViewQuery
 implements com.runwaysdk.generation.loader.Reloadable
{
private static final long serialVersionUID = 144530637;

  public UniversalPermissionViewQueryBase(com.runwaysdk.query.QueryFactory componentQueryFactory)
  {
    super(componentQueryFactory);
  }

  public UniversalPermissionViewQueryBase(com.runwaysdk.query.QueryFactory componentQueryFactory, com.runwaysdk.query.ViewQueryBuilder viewQueryBuilder)
  {
    super(componentQueryFactory, viewQueryBuilder);
  }
  public String getClassType()
  {
    return dss.vector.solutions.permission.UniversalPermissionView.CLASS;
  }
  public com.runwaysdk.query.SelectableChar getId()
  {
    return getId(null);

  }
 
  public com.runwaysdk.query.SelectableChar getId(String alias)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(dss.vector.solutions.permission.UniversalPermissionView.ID, alias, null);

  }
 
  public com.runwaysdk.query.SelectableChar getId(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(dss.vector.solutions.permission.UniversalPermissionView.ID, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableChar getLabel()
  {
    return getLabel(null);

  }
 
  public com.runwaysdk.query.SelectableChar getLabel(String alias)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(dss.vector.solutions.permission.UniversalPermissionView.LABEL, alias, null);

  }
 
  public com.runwaysdk.query.SelectableChar getLabel(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(dss.vector.solutions.permission.UniversalPermissionView.LABEL, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableBoolean getPermission()
  {
    return getPermission(null);

  }
 
  public com.runwaysdk.query.SelectableBoolean getPermission(String alias)
  {
    return (com.runwaysdk.query.SelectableBoolean)this.getSelectable(dss.vector.solutions.permission.UniversalPermissionView.PERMISSION, alias, null);

  }
 
  public com.runwaysdk.query.SelectableBoolean getPermission(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableBoolean)this.getSelectable(dss.vector.solutions.permission.UniversalPermissionView.PERMISSION, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableChar getUniversalId()
  {
    return getUniversalId(null);

  }
 
  public com.runwaysdk.query.SelectableChar getUniversalId(String alias)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(dss.vector.solutions.permission.UniversalPermissionView.UNIVERSALID, alias, null);

  }
 
  public com.runwaysdk.query.SelectableChar getUniversalId(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(dss.vector.solutions.permission.UniversalPermissionView.UNIVERSALID, alias, displayLabel);

  }
 
  /**  
   * Returns an iterator of Business objects that match the query criteria specified
   * on this query object. 
   * @return iterator of Business objects that match the query criteria specified
   * on this query object.
   */
  @java.lang.SuppressWarnings("unchecked")
  public com.runwaysdk.query.OIterator<? extends UniversalPermissionView> getIterator()
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
    return new com.runwaysdk.query.ViewIterator<UniversalPermissionView>(this.getMdClassIF(), valueIterator);
  }

}
