package dss.vector.solutions.util;

@com.terraframe.mojo.business.ClassSignature(hash = -525261213)
/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to ReadableAttributeView.java
 *
 * @author Autogenerated by TerraFrame
 */
public  abstract  class ReadableAttributeViewQueryBase extends com.terraframe.mojo.query.GeneratedViewQuery
 implements com.terraframe.mojo.generation.loader.Reloadable
{
private static final long serialVersionUID = -525261213;

  public ReadableAttributeViewQueryBase(com.terraframe.mojo.query.QueryFactory componentQueryFactory)
  {
    super(componentQueryFactory);
  }

  public ReadableAttributeViewQueryBase(com.terraframe.mojo.query.QueryFactory componentQueryFactory, com.terraframe.mojo.query.ViewQueryBuilder viewQueryBuilder)
  {
    super(componentQueryFactory, viewQueryBuilder);
  }
  public String getClassType()
  {
    return dss.vector.solutions.util.ReadableAttributeView.CLASS;
  }
  public com.terraframe.mojo.query.AttributeChar getAttributeDescription()
  {
    return getAttributeDescription(null);

  }
 
  public com.terraframe.mojo.query.AttributeChar getAttributeDescription(String alias)
  {
    return (com.terraframe.mojo.query.AttributeChar)this.getSelectable(dss.vector.solutions.util.ReadableAttributeView.ATTRIBUTEDESCRIPTION, alias, null);

  }
 
  public com.terraframe.mojo.query.AttributeChar getAttributeDescription(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.AttributeChar)this.getSelectable(dss.vector.solutions.util.ReadableAttributeView.ATTRIBUTEDESCRIPTION, alias, displayLabel);

  }
 
  public com.terraframe.mojo.query.AttributeChar getAttributeName()
  {
    return getAttributeName(null);

  }
 
  public com.terraframe.mojo.query.AttributeChar getAttributeName(String alias)
  {
    return (com.terraframe.mojo.query.AttributeChar)this.getSelectable(dss.vector.solutions.util.ReadableAttributeView.ATTRIBUTENAME, alias, null);

  }
 
  public com.terraframe.mojo.query.AttributeChar getAttributeName(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.AttributeChar)this.getSelectable(dss.vector.solutions.util.ReadableAttributeView.ATTRIBUTENAME, alias, displayLabel);

  }
 
  public com.terraframe.mojo.query.AttributeBoolean getAttributeRequired()
  {
    return getAttributeRequired(null);

  }
 
  public com.terraframe.mojo.query.AttributeBoolean getAttributeRequired(String alias)
  {
    return (com.terraframe.mojo.query.AttributeBoolean)this.getSelectable(dss.vector.solutions.util.ReadableAttributeView.ATTRIBUTEREQUIRED, alias, null);

  }
 
  public com.terraframe.mojo.query.AttributeBoolean getAttributeRequired(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.AttributeBoolean)this.getSelectable(dss.vector.solutions.util.ReadableAttributeView.ATTRIBUTEREQUIRED, alias, displayLabel);

  }
 
  public com.terraframe.mojo.query.AttributeChar getDisplayLabel()
  {
    return getDisplayLabel(null);

  }
 
  public com.terraframe.mojo.query.AttributeChar getDisplayLabel(String alias)
  {
    return (com.terraframe.mojo.query.AttributeChar)this.getSelectable(dss.vector.solutions.util.ReadableAttributeView.DISPLAYLABEL, alias, null);

  }
 
  public com.terraframe.mojo.query.AttributeChar getDisplayLabel(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.AttributeChar)this.getSelectable(dss.vector.solutions.util.ReadableAttributeView.DISPLAYLABEL, alias, displayLabel);

  }
 
  public com.terraframe.mojo.query.AttributeChar getId()
  {
    return getId(null);

  }
 
  public com.terraframe.mojo.query.AttributeChar getId(String alias)
  {
    return (com.terraframe.mojo.query.AttributeChar)this.getSelectable(dss.vector.solutions.util.ReadableAttributeView.ID, alias, null);

  }
 
  public com.terraframe.mojo.query.AttributeChar getId(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.AttributeChar)this.getSelectable(dss.vector.solutions.util.ReadableAttributeView.ID, alias, displayLabel);

  }
 
  public com.terraframe.mojo.query.AttributeBoolean getReadPermission()
  {
    return getReadPermission(null);

  }
 
  public com.terraframe.mojo.query.AttributeBoolean getReadPermission(String alias)
  {
    return (com.terraframe.mojo.query.AttributeBoolean)this.getSelectable(dss.vector.solutions.util.ReadableAttributeView.READPERMISSION, alias, null);

  }
 
  public com.terraframe.mojo.query.AttributeBoolean getReadPermission(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.AttributeBoolean)this.getSelectable(dss.vector.solutions.util.ReadableAttributeView.READPERMISSION, alias, displayLabel);

  }
 
  /**  
   * Returns an iterator of Business objects that match the query criteria specified
   * on this query object. 
   * @return iterator of Business objects that match the query criteria specified
   * on this query object.
   */
  @java.lang.SuppressWarnings("unchecked")
  public com.terraframe.mojo.query.OIterator<? extends ReadableAttributeView> getIterator()
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
    return new com.terraframe.mojo.query.ViewIterator<ReadableAttributeView>(this.getMdClassIF(), valueIterator);
  }

}
