package dss.vector.solutions.util;

@com.runwaysdk.business.ClassSignature(hash = -1736233509)
/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to ReadableAttributeView.java
 *
 * @author Autogenerated by RunwaySDK
 */
public  abstract  class ReadableAttributeViewQueryBase extends com.runwaysdk.query.GeneratedViewQuery
 implements com.runwaysdk.generation.loader.Reloadable
{
private static final long serialVersionUID = -1736233509;

  public ReadableAttributeViewQueryBase(com.runwaysdk.query.QueryFactory componentQueryFactory)
  {
    super(componentQueryFactory);
  }

  public ReadableAttributeViewQueryBase(com.runwaysdk.query.QueryFactory componentQueryFactory, com.runwaysdk.query.ViewQueryBuilder viewQueryBuilder)
  {
    super(componentQueryFactory, viewQueryBuilder);
  }
  public String getClassType()
  {
    return dss.vector.solutions.util.ReadableAttributeView.CLASS;
  }
  public com.runwaysdk.query.SelectableChar getAttributeDescription()
  {
    return getAttributeDescription(null);

  }
 
  public com.runwaysdk.query.SelectableChar getAttributeDescription(String alias)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(dss.vector.solutions.util.ReadableAttributeView.ATTRIBUTEDESCRIPTION, alias, null);

  }
 
  public com.runwaysdk.query.SelectableChar getAttributeDescription(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(dss.vector.solutions.util.ReadableAttributeView.ATTRIBUTEDESCRIPTION, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableChar getAttributeName()
  {
    return getAttributeName(null);

  }
 
  public com.runwaysdk.query.SelectableChar getAttributeName(String alias)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(dss.vector.solutions.util.ReadableAttributeView.ATTRIBUTENAME, alias, null);

  }
 
  public com.runwaysdk.query.SelectableChar getAttributeName(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(dss.vector.solutions.util.ReadableAttributeView.ATTRIBUTENAME, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableBoolean getAttributeRequired()
  {
    return getAttributeRequired(null);

  }
 
  public com.runwaysdk.query.SelectableBoolean getAttributeRequired(String alias)
  {
    return (com.runwaysdk.query.SelectableBoolean)this.getSelectable(dss.vector.solutions.util.ReadableAttributeView.ATTRIBUTEREQUIRED, alias, null);

  }
 
  public com.runwaysdk.query.SelectableBoolean getAttributeRequired(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableBoolean)this.getSelectable(dss.vector.solutions.util.ReadableAttributeView.ATTRIBUTEREQUIRED, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableChar getDisplayLabel()
  {
    return getDisplayLabel(null);

  }
 
  public com.runwaysdk.query.SelectableChar getDisplayLabel(String alias)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(dss.vector.solutions.util.ReadableAttributeView.DISPLAYLABEL, alias, null);

  }
 
  public com.runwaysdk.query.SelectableChar getDisplayLabel(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(dss.vector.solutions.util.ReadableAttributeView.DISPLAYLABEL, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableChar getId()
  {
    return getId(null);

  }
 
  public com.runwaysdk.query.SelectableChar getId(String alias)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(dss.vector.solutions.util.ReadableAttributeView.ID, alias, null);

  }
 
  public com.runwaysdk.query.SelectableChar getId(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(dss.vector.solutions.util.ReadableAttributeView.ID, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableBoolean getReadPermission()
  {
    return getReadPermission(null);

  }
 
  public com.runwaysdk.query.SelectableBoolean getReadPermission(String alias)
  {
    return (com.runwaysdk.query.SelectableBoolean)this.getSelectable(dss.vector.solutions.util.ReadableAttributeView.READPERMISSION, alias, null);

  }
 
  public com.runwaysdk.query.SelectableBoolean getReadPermission(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableBoolean)this.getSelectable(dss.vector.solutions.util.ReadableAttributeView.READPERMISSION, alias, displayLabel);

  }
 
  /**  
   * Returns an iterator of Business objects that match the query criteria specified
   * on this query object. 
   * @return iterator of Business objects that match the query criteria specified
   * on this query object.
   */
  @java.lang.SuppressWarnings("unchecked")
  public com.runwaysdk.query.OIterator<? extends ReadableAttributeView> getIterator()
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
    return new com.runwaysdk.query.ViewIterator<ReadableAttributeView>(this.getMdClassIF(), valueIterator);
  }

}
