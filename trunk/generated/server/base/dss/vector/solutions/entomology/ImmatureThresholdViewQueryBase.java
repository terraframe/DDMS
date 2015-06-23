package dss.vector.solutions.entomology;

@com.runwaysdk.business.ClassSignature(hash = 1802166118)
/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to ImmatureThresholdView.java
 *
 * @author Autogenerated by RunwaySDK
 */
public  abstract  class ImmatureThresholdViewQueryBase extends com.runwaysdk.query.GeneratedViewQuery
 implements com.runwaysdk.generation.loader.Reloadable
{

  public ImmatureThresholdViewQueryBase(com.runwaysdk.query.QueryFactory componentQueryFactory)
  {
    super(componentQueryFactory);
  }

  public ImmatureThresholdViewQueryBase(com.runwaysdk.query.QueryFactory componentQueryFactory, com.runwaysdk.query.ViewQueryBuilder viewQueryBuilder)
  {
    super(componentQueryFactory, viewQueryBuilder);
  }
  public String getClassType()
  {
    return dss.vector.solutions.entomology.ImmatureThresholdView.CLASS;
  }
  public com.runwaysdk.query.SelectableChar getConcreteId()
  {
    return getConcreteId(null);

  }
 
  public com.runwaysdk.query.SelectableChar getConcreteId(String alias)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(dss.vector.solutions.entomology.ImmatureThresholdView.CONCRETEID, alias, null);

  }
 
  public com.runwaysdk.query.SelectableChar getConcreteId(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(dss.vector.solutions.entomology.ImmatureThresholdView.CONCRETEID, alias, displayLabel);

  }
 
  public dss.vector.solutions.entomology.ImmatureThresholdDisplayLabelQuery.ImmatureThresholdDisplayLabelQueryStructIF getDisplayLabel()
  {
    return getDisplayLabel(null);

  }
 
  public dss.vector.solutions.entomology.ImmatureThresholdDisplayLabelQuery.ImmatureThresholdDisplayLabelQueryStructIF getDisplayLabel(String alias)
  {
    return (dss.vector.solutions.entomology.ImmatureThresholdDisplayLabelQuery.ImmatureThresholdDisplayLabelQueryStructIF)this.getSelectable(dss.vector.solutions.entomology.ImmatureThresholdView.DISPLAYLABEL, alias, null);

  }
 
  public dss.vector.solutions.entomology.ImmatureThresholdDisplayLabelQuery.ImmatureThresholdDisplayLabelQueryStructIF getDisplayLabel(String alias, String displayLabel)
  {
    return (dss.vector.solutions.entomology.ImmatureThresholdDisplayLabelQuery.ImmatureThresholdDisplayLabelQueryStructIF)this.getSelectable(dss.vector.solutions.entomology.ImmatureThresholdView.DISPLAYLABEL, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableChar getId()
  {
    return getId(null);

  }
 
  public com.runwaysdk.query.SelectableChar getId(String alias)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(dss.vector.solutions.entomology.ImmatureThresholdView.ID, alias, null);

  }
 
  public com.runwaysdk.query.SelectableChar getId(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(dss.vector.solutions.entomology.ImmatureThresholdView.ID, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableChar getThresholdIndex()
  {
    return getThresholdIndex(null);

  }
 
  public com.runwaysdk.query.SelectableChar getThresholdIndex(String alias)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(dss.vector.solutions.entomology.ImmatureThresholdView.THRESHOLDINDEX, alias, null);

  }
 
  public com.runwaysdk.query.SelectableChar getThresholdIndex(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(dss.vector.solutions.entomology.ImmatureThresholdView.THRESHOLDINDEX, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableDecimal getThresholdValue()
  {
    return getThresholdValue(null);

  }
 
  public com.runwaysdk.query.SelectableDecimal getThresholdValue(String alias)
  {
    return (com.runwaysdk.query.SelectableDecimal)this.getSelectable(dss.vector.solutions.entomology.ImmatureThresholdView.THRESHOLDVALUE, alias, null);

  }
 
  public com.runwaysdk.query.SelectableDecimal getThresholdValue(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableDecimal)this.getSelectable(dss.vector.solutions.entomology.ImmatureThresholdView.THRESHOLDVALUE, alias, displayLabel);

  }
 
  /**  
   * Returns an iterator of Business objects that match the query criteria specified
   * on this query object. 
   * @return iterator of Business objects that match the query criteria specified
   * on this query object.
   */
  @java.lang.SuppressWarnings("unchecked")
  public com.runwaysdk.query.OIterator<? extends ImmatureThresholdView> getIterator()
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
    return new com.runwaysdk.query.ViewIterator<ImmatureThresholdView>(this.getMdClassIF(), valueIterator);
  }

}
