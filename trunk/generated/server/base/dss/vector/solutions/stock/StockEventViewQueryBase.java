package dss.vector.solutions.stock;

@com.terraframe.mojo.business.ClassSignature(hash = -1862635317)
/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to StockEventView.java
 *
 * @author Autogenerated by TerraFrame
 */
public  abstract  class StockEventViewQueryBase extends com.terraframe.mojo.query.GeneratedViewQuery
 implements com.terraframe.mojo.generation.loader.Reloadable
{
private static final long serialVersionUID = -1862635317;

  public StockEventViewQueryBase(com.terraframe.mojo.query.QueryFactory componentQueryFactory)
  {
    super(componentQueryFactory);
  }

  public StockEventViewQueryBase(com.terraframe.mojo.query.QueryFactory componentQueryFactory, com.terraframe.mojo.query.ViewQueryBuilder viewQueryBuilder)
  {
    super(componentQueryFactory, viewQueryBuilder);
  }
  public String getClassType()
  {
    return dss.vector.solutions.stock.StockEventView.CLASS;
  }
  public com.terraframe.mojo.query.SelectableSingleInteger getAvailableStock()
  {
    return getAvailableStock(null);

  }
 
  public com.terraframe.mojo.query.SelectableSingleInteger getAvailableStock(String alias)
  {
    return (com.terraframe.mojo.query.SelectableSingleInteger)this.getSelectable(dss.vector.solutions.stock.StockEventView.AVAILABLESTOCK, alias, null);

  }
 
  public com.terraframe.mojo.query.SelectableSingleInteger getAvailableStock(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.SelectableSingleInteger)this.getSelectable(dss.vector.solutions.stock.StockEventView.AVAILABLESTOCK, alias, displayLabel);

  }
 
  public com.terraframe.mojo.query.SelectableSingleChar getConcreteId()
  {
    return getConcreteId(null);

  }
 
  public com.terraframe.mojo.query.SelectableSingleChar getConcreteId(String alias)
  {
    return (com.terraframe.mojo.query.SelectableSingleChar)this.getSelectable(dss.vector.solutions.stock.StockEventView.CONCRETEID, alias, null);

  }
 
  public com.terraframe.mojo.query.SelectableSingleChar getConcreteId(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.SelectableSingleChar)this.getSelectable(dss.vector.solutions.stock.StockEventView.CONCRETEID, alias, displayLabel);

  }
 
  public com.terraframe.mojo.query.SelectableSingleDecimal getCost()
  {
    return getCost(null);

  }
 
  public com.terraframe.mojo.query.SelectableSingleDecimal getCost(String alias)
  {
    return (com.terraframe.mojo.query.SelectableSingleDecimal)this.getSelectable(dss.vector.solutions.stock.StockEventView.COST, alias, null);

  }
 
  public com.terraframe.mojo.query.SelectableSingleDecimal getCost(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.SelectableSingleDecimal)this.getSelectable(dss.vector.solutions.stock.StockEventView.COST, alias, displayLabel);

  }
 
  public com.terraframe.mojo.query.SelectableSingleMoment getEventDate()
  {
    return getEventDate(null);

  }
 
  public com.terraframe.mojo.query.SelectableSingleMoment getEventDate(String alias)
  {
    return (com.terraframe.mojo.query.SelectableSingleMoment)this.getSelectable(dss.vector.solutions.stock.StockEventView.EVENTDATE, alias, null);

  }
 
  public com.terraframe.mojo.query.SelectableSingleMoment getEventDate(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.SelectableSingleMoment)this.getSelectable(dss.vector.solutions.stock.StockEventView.EVENTDATE, alias, displayLabel);

  }
 
  public com.terraframe.mojo.query.SelectableSingleChar getId()
  {
    return getId(null);

  }
 
  public com.terraframe.mojo.query.SelectableSingleChar getId(String alias)
  {
    return (com.terraframe.mojo.query.SelectableSingleChar)this.getSelectable(dss.vector.solutions.stock.StockEventView.ID, alias, null);

  }
 
  public com.terraframe.mojo.query.SelectableSingleChar getId(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.SelectableSingleChar)this.getSelectable(dss.vector.solutions.stock.StockEventView.ID, alias, displayLabel);

  }
 
  public dss.vector.solutions.stock.StockItemQuery.StockItemQueryReferenceIF getItem()
  {
    return getItem(null);

  }
 
  public dss.vector.solutions.stock.StockItemQuery.StockItemQueryReferenceIF getItem(String alias)
  {

    return (dss.vector.solutions.stock.StockItemQuery.StockItemQueryReferenceIF)this.getSelectable(dss.vector.solutions.stock.StockEventView.ITEM, alias, null);

  }
 
  public dss.vector.solutions.stock.StockItemQuery.StockItemQueryReferenceIF getItem(String alias, String displayLabel)
  {

    return (dss.vector.solutions.stock.StockItemQuery.StockItemQueryReferenceIF)this.getSelectable(dss.vector.solutions.stock.StockEventView.ITEM, alias, displayLabel);

  }
  public com.terraframe.mojo.query.SelectableSingleChar getItemLabel()
  {
    return getItemLabel(null);

  }
 
  public com.terraframe.mojo.query.SelectableSingleChar getItemLabel(String alias)
  {
    return (com.terraframe.mojo.query.SelectableSingleChar)this.getSelectable(dss.vector.solutions.stock.StockEventView.ITEMLABEL, alias, null);

  }
 
  public com.terraframe.mojo.query.SelectableSingleChar getItemLabel(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.SelectableSingleChar)this.getSelectable(dss.vector.solutions.stock.StockEventView.ITEMLABEL, alias, displayLabel);

  }
 
  public com.terraframe.mojo.query.SelectableSingleChar getOtherParty()
  {
    return getOtherParty(null);

  }
 
  public com.terraframe.mojo.query.SelectableSingleChar getOtherParty(String alias)
  {
    return (com.terraframe.mojo.query.SelectableSingleChar)this.getSelectable(dss.vector.solutions.stock.StockEventView.OTHERPARTY, alias, null);

  }
 
  public com.terraframe.mojo.query.SelectableSingleChar getOtherParty(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.SelectableSingleChar)this.getSelectable(dss.vector.solutions.stock.StockEventView.OTHERPARTY, alias, displayLabel);

  }
 
  public com.terraframe.mojo.query.SelectableSingleInteger getQuantity()
  {
    return getQuantity(null);

  }
 
  public com.terraframe.mojo.query.SelectableSingleInteger getQuantity(String alias)
  {
    return (com.terraframe.mojo.query.SelectableSingleInteger)this.getSelectable(dss.vector.solutions.stock.StockEventView.QUANTITY, alias, null);

  }
 
  public com.terraframe.mojo.query.SelectableSingleInteger getQuantity(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.SelectableSingleInteger)this.getSelectable(dss.vector.solutions.stock.StockEventView.QUANTITY, alias, displayLabel);

  }
 
  public dss.vector.solutions.stock.StockStaffQuery.StockStaffQueryReferenceIF getStaff()
  {
    return getStaff(null);

  }
 
  public dss.vector.solutions.stock.StockStaffQuery.StockStaffQueryReferenceIF getStaff(String alias)
  {

    return (dss.vector.solutions.stock.StockStaffQuery.StockStaffQueryReferenceIF)this.getSelectable(dss.vector.solutions.stock.StockEventView.STAFF, alias, null);

  }
 
  public dss.vector.solutions.stock.StockStaffQuery.StockStaffQueryReferenceIF getStaff(String alias, String displayLabel)
  {

    return (dss.vector.solutions.stock.StockStaffQuery.StockStaffQueryReferenceIF)this.getSelectable(dss.vector.solutions.stock.StockEventView.STAFF, alias, displayLabel);

  }
  public com.terraframe.mojo.query.SelectableSingleChar getStaffLabel()
  {
    return getStaffLabel(null);

  }
 
  public com.terraframe.mojo.query.SelectableSingleChar getStaffLabel(String alias)
  {
    return (com.terraframe.mojo.query.SelectableSingleChar)this.getSelectable(dss.vector.solutions.stock.StockEventView.STAFFLABEL, alias, null);

  }
 
  public com.terraframe.mojo.query.SelectableSingleChar getStaffLabel(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.SelectableSingleChar)this.getSelectable(dss.vector.solutions.stock.StockEventView.STAFFLABEL, alias, displayLabel);

  }
 
  public dss.vector.solutions.geo.generated.GeoEntityQuery.GeoEntityQueryReferenceIF getStockDepot()
  {
    return getStockDepot(null);

  }
 
  public dss.vector.solutions.geo.generated.GeoEntityQuery.GeoEntityQueryReferenceIF getStockDepot(String alias)
  {

    return (dss.vector.solutions.geo.generated.GeoEntityQuery.GeoEntityQueryReferenceIF)this.getSelectable(dss.vector.solutions.stock.StockEventView.STOCKDEPOT, alias, null);

  }
 
  public dss.vector.solutions.geo.generated.GeoEntityQuery.GeoEntityQueryReferenceIF getStockDepot(String alias, String displayLabel)
  {

    return (dss.vector.solutions.geo.generated.GeoEntityQuery.GeoEntityQueryReferenceIF)this.getSelectable(dss.vector.solutions.stock.StockEventView.STOCKDEPOT, alias, displayLabel);

  }
  public dss.vector.solutions.stock.EventOptionMasterQuery.EventOptionQueryIF getTransactionType()
  {
    return getTransactionType(null);

  }
 
  public dss.vector.solutions.stock.EventOptionMasterQuery.EventOptionQueryIF getTransactionType(String alias)
  {
    return (dss.vector.solutions.stock.EventOptionMasterQuery.EventOptionQueryIF)this.getSelectable(dss.vector.solutions.stock.StockEventView.TRANSACTIONTYPE, alias, null);

  }
 
  public dss.vector.solutions.stock.EventOptionMasterQuery.EventOptionQueryIF getTransactionType(String alias, String displayLabel)
  {
    return (dss.vector.solutions.stock.EventOptionMasterQuery.EventOptionQueryIF)this.getSelectable(dss.vector.solutions.stock.StockEventView.TRANSACTIONTYPE, alias, displayLabel);

  }
  /**  
   * Returns an iterator of Business objects that match the query criteria specified
   * on this query object. 
   * @return iterator of Business objects that match the query criteria specified
   * on this query object.
   */
  @java.lang.SuppressWarnings("unchecked")
  public com.terraframe.mojo.query.OIterator<? extends StockEventView> getIterator()
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
    return new com.terraframe.mojo.query.ViewIterator<StockEventView>(this.getMdClassIF(), valueIterator);
  }

}
