package dss.vector.solutions.query;

@com.runwaysdk.business.ClassSignature(hash = -1949610480)
/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to LayerView.java
 *
 * @author Autogenerated by RunwaySDK
 */
public  abstract  class LayerViewQueryBase extends com.runwaysdk.query.GeneratedViewQuery
 implements com.runwaysdk.generation.loader.Reloadable
{

  public LayerViewQueryBase(com.runwaysdk.query.QueryFactory componentQueryFactory)
  {
    super(componentQueryFactory);
  }

  public LayerViewQueryBase(com.runwaysdk.query.QueryFactory componentQueryFactory, com.runwaysdk.query.ViewQueryBuilder viewQueryBuilder)
  {
    super(componentQueryFactory, viewQueryBuilder);
  }
  public String getClassType()
  {
    return dss.vector.solutions.query.LayerView.CLASS;
  }
  public com.runwaysdk.query.SelectableChar getId()
  {
    return getId(null);

  }
 
  public com.runwaysdk.query.SelectableChar getId(String alias)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(dss.vector.solutions.query.LayerView.ID, alias, null);

  }
 
  public com.runwaysdk.query.SelectableChar getId(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(dss.vector.solutions.query.LayerView.ID, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableChar getLayerId()
  {
    return getLayerId(null);

  }
 
  public com.runwaysdk.query.SelectableChar getLayerId(String alias)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(dss.vector.solutions.query.LayerView.LAYERID, alias, null);

  }
 
  public com.runwaysdk.query.SelectableChar getLayerId(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(dss.vector.solutions.query.LayerView.LAYERID, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableChar getLayerName()
  {
    return getLayerName(null);

  }
 
  public com.runwaysdk.query.SelectableChar getLayerName(String alias)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(dss.vector.solutions.query.LayerView.LAYERNAME, alias, null);

  }
 
  public com.runwaysdk.query.SelectableChar getLayerName(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(dss.vector.solutions.query.LayerView.LAYERNAME, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableInteger getLayerPosition()
  {
    return getLayerPosition(null);

  }
 
  public com.runwaysdk.query.SelectableInteger getLayerPosition(String alias)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.query.LayerView.LAYERPOSITION, alias, null);

  }
 
  public com.runwaysdk.query.SelectableInteger getLayerPosition(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.query.LayerView.LAYERPOSITION, alias, displayLabel);

  }
 
  /**  
   * Returns an iterator of Business objects that match the query criteria specified
   * on this query object. 
   * @return iterator of Business objects that match the query criteria specified
   * on this query object.
   */
  @java.lang.SuppressWarnings("unchecked")
  public com.runwaysdk.query.OIterator<? extends LayerView> getIterator()
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
    return new com.runwaysdk.query.ViewIterator<LayerView>(this.getMdClassIF(), valueIterator);
  }

}
