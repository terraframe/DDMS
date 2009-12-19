package dss.vector.solutions.stock;

@com.terraframe.mojo.business.ClassSignature(hash = 361836340)
/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to StockItemView.java
 *
 * @author Autogenerated by TerraFrame
 */
public  abstract  class StockItemViewQueryBase extends com.terraframe.mojo.query.GeneratedViewQuery
 implements com.terraframe.mojo.generation.loader.Reloadable
{
private static final long serialVersionUID = 361836340;

  public StockItemViewQueryBase(com.terraframe.mojo.query.QueryFactory componentQueryFactory)
  {
    super(componentQueryFactory);
  }

  public StockItemViewQueryBase(com.terraframe.mojo.query.QueryFactory componentQueryFactory, com.terraframe.mojo.query.ViewQueryBuilder viewQueryBuilder)
  {
    super(componentQueryFactory, viewQueryBuilder);
  }
  public String getClassType()
  {
    return dss.vector.solutions.stock.StockItemView.CLASS;
  }
  public com.terraframe.mojo.query.AttributeChar getConcreteId()
  {
    return getConcreteId(null);

  }
 
  public com.terraframe.mojo.query.AttributeChar getConcreteId(String alias)
  {
    return (com.terraframe.mojo.query.AttributeChar)this.getSelectable(dss.vector.solutions.stock.StockItemView.CONCRETEID, alias, null);

  }
 
  public com.terraframe.mojo.query.AttributeChar getConcreteId(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.AttributeChar)this.getSelectable(dss.vector.solutions.stock.StockItemView.CONCRETEID, alias, displayLabel);

  }
 
  public com.terraframe.mojo.query.AttributeChar getId()
  {
    return getId(null);

  }
 
  public com.terraframe.mojo.query.AttributeChar getId(String alias)
  {
    return (com.terraframe.mojo.query.AttributeChar)this.getSelectable(dss.vector.solutions.stock.StockItemView.ID, alias, null);

  }
 
  public com.terraframe.mojo.query.AttributeChar getId(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.AttributeChar)this.getSelectable(dss.vector.solutions.stock.StockItemView.ID, alias, displayLabel);

  }
 
  public com.terraframe.mojo.query.AttributeChar getItemId()
  {
    return getItemId(null);

  }
 
  public com.terraframe.mojo.query.AttributeChar getItemId(String alias)
  {
    return (com.terraframe.mojo.query.AttributeChar)this.getSelectable(dss.vector.solutions.stock.StockItemView.ITEMID, alias, null);

  }
 
  public com.terraframe.mojo.query.AttributeChar getItemId(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.AttributeChar)this.getSelectable(dss.vector.solutions.stock.StockItemView.ITEMID, alias, displayLabel);

  }
 
  public dss.vector.solutions.ontology.TermQuery.TermQueryReferenceIF getItemName()
  {
    return getItemName(null);

  }
 
  public dss.vector.solutions.ontology.TermQuery.TermQueryReferenceIF getItemName(String alias)
  {

    return (dss.vector.solutions.ontology.TermQuery.TermQueryReferenceIF)this.getSelectable(dss.vector.solutions.stock.StockItemView.ITEMNAME, alias, null);

  }
 
  public dss.vector.solutions.ontology.TermQuery.TermQueryReferenceIF getItemName(String alias, String displayLabel)
  {

    return (dss.vector.solutions.ontology.TermQuery.TermQueryReferenceIF)this.getSelectable(dss.vector.solutions.stock.StockItemView.ITEMNAME, alias, displayLabel);

  }
  public com.terraframe.mojo.query.AttributeFloat getQuantity()
  {
    return getQuantity(null);

  }
 
  public com.terraframe.mojo.query.AttributeFloat getQuantity(String alias)
  {
    return (com.terraframe.mojo.query.AttributeFloat)this.getSelectable(dss.vector.solutions.stock.StockItemView.QUANTITY, alias, null);

  }
 
  public com.terraframe.mojo.query.AttributeFloat getQuantity(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.AttributeFloat)this.getSelectable(dss.vector.solutions.stock.StockItemView.QUANTITY, alias, displayLabel);

  }
 
  public dss.vector.solutions.ontology.TermQuery.TermQueryReferenceIF getUnit()
  {
    return getUnit(null);

  }
 
  public dss.vector.solutions.ontology.TermQuery.TermQueryReferenceIF getUnit(String alias)
  {

    return (dss.vector.solutions.ontology.TermQuery.TermQueryReferenceIF)this.getSelectable(dss.vector.solutions.stock.StockItemView.UNIT, alias, null);

  }
 
  public dss.vector.solutions.ontology.TermQuery.TermQueryReferenceIF getUnit(String alias, String displayLabel)
  {

    return (dss.vector.solutions.ontology.TermQuery.TermQueryReferenceIF)this.getSelectable(dss.vector.solutions.stock.StockItemView.UNIT, alias, displayLabel);

  }
  /**  
   * Returns an iterator of Business objects that match the query criteria specified
   * on this query object. 
   * @return iterator of Business objects that match the query criteria specified
   * on this query object.
   */
  @java.lang.SuppressWarnings("unchecked")
  public com.terraframe.mojo.query.OIterator<? extends StockItemView> getIterator()
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
    return new com.terraframe.mojo.query.ViewIterator<StockItemView>(this.getMdClassIF(), valueIterator);
  }

}
