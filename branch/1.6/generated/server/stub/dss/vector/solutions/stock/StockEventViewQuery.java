package dss.vector.solutions.stock;

import com.runwaysdk.query.CONCAT;
import com.runwaysdk.query.F;

/**
 *
 * @author Autogenerated by TerraFrame
 */
public class StockEventViewQuery extends dss.vector.solutions.stock.StockEventViewQueryBase  implements com.runwaysdk.generation.loader.Reloadable
{
private static final long serialVersionUID = 1257790601973L;

  public StockEventViewQuery(com.runwaysdk.query.QueryFactory queryFactory)
  {
    super(queryFactory);
    this.buildQuery(new DefaultStockEventViewBuilder(queryFactory));
  }

  public StockEventViewQuery(com.runwaysdk.query.QueryFactory queryFactory, com.runwaysdk.query.ViewQueryBuilder viewQueryBuilder)
  {
    super(queryFactory, viewQueryBuilder);
  }

  class DefaultStockEventViewBuilder extends com.runwaysdk.query.ViewQueryBuilder implements com.runwaysdk.generation.loader.Reloadable
  {
    private StockEventQuery query;
    
    public DefaultStockEventViewBuilder(com.runwaysdk.query.QueryFactory queryFactory)
    {
      super(queryFactory);
      
      query = new StockEventQuery(queryFactory);
    }

    protected StockEventViewQuery getViewQuery()
    {
      return (StockEventViewQuery)super.getViewQuery();
    }

    /**
     * build the select clause
     */
    protected void buildSelectClause()
    {
      StockEventViewQuery vQuery = this.getViewQuery();

      CONCAT itemLabel = F.CONCAT(query.getItem().getItemId(), " - ");
      itemLabel = F.CONCAT(itemLabel, query.getItem().getItemName().getName());
      itemLabel = F.CONCAT(itemLabel, " ");
      itemLabel = F.CONCAT(itemLabel, query.getItem().getQuantity());
      itemLabel = F.CONCAT(itemLabel, " ");
      itemLabel = F.CONCAT(itemLabel, query.getItem().getUnit().getName());
      
      CONCAT staffLabel = F.CONCAT(query.getStaff().getPerson().getFirstName(), " ");
      staffLabel = F.CONCAT(staffLabel, query.getStaff().getPerson().getLastName());
      
      vQuery.map(StockEventView.CONCRETEID, query.getId());
      vQuery.map(StockEventView.COST, query.getCost());
      vQuery.map(StockEventView.EVENTDATE, query.getEventDate());
      vQuery.map(StockEventView.ITEM, query.getItem());
      vQuery.map(StockEventView.ITEMLABEL, itemLabel);
      vQuery.map(StockEventView.QUANTITY, query.getQuantity());
      vQuery.map(StockEventView.STAFF, query.getStaff());
      vQuery.map(StockEventView.STAFFLABEL, staffLabel);
      vQuery.map(StockEventView.STOCKDEPOT, query.getStockDepot());
      vQuery.map(StockEventView.TRANSACTIONTYPE, query.getTransactionType());
      vQuery.map(StockEventView.OTHERPARTY, query.getOtherParty());
    }

    /**
     * Implement only if additional join criteria is required.
     */
    protected void buildWhereClause()
    {

    }

  }
}
