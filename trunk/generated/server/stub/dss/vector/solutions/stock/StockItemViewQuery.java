/*******************************************************************************
 * Copyright (C) 2018 IVCC
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 ******************************************************************************/
package dss.vector.solutions.stock;



/**
 *
 * @author Autogenerated by TerraFrame
 */
public class StockItemViewQuery extends dss.vector.solutions.stock.StockItemViewQueryBase  implements com.runwaysdk.generation.loader.Reloadable
{
private static final long serialVersionUID = 1257278739999L;

  public StockItemViewQuery(com.runwaysdk.query.QueryFactory queryFactory)
  {
    super(queryFactory);
    this.buildQuery(new DefaultStockItemViewBuilder(queryFactory));
  }

  public StockItemViewQuery(com.runwaysdk.query.QueryFactory queryFactory, com.runwaysdk.query.ViewQueryBuilder viewQueryBuilder)
  {
    super(queryFactory, viewQueryBuilder);
  }

  class DefaultStockItemViewBuilder extends com.runwaysdk.query.ViewQueryBuilder implements com.runwaysdk.generation.loader.Reloadable
  {
    private StockItemQuery stock;
    
    public DefaultStockItemViewBuilder(com.runwaysdk.query.QueryFactory queryFactory)
    {
      super(queryFactory);
      
      this.stock = new StockItemQuery(queryFactory);
    }

    protected StockItemViewQuery getViewQuery()
    {
      return (StockItemViewQuery)super.getViewQuery();
    }

    /**
     * build the select clause
     */
    protected void buildSelectClause()
    {
      StockItemViewQuery vQuery = this.getViewQuery();
      
      vQuery.map(StockItemView.CONCRETEID, stock.getId());
      vQuery.map(StockItemView.ITEMNAME, stock.getItemName());
      vQuery.map(StockItemView.QUANTITY, stock.getQuantity());
      vQuery.map(StockItemView.UNIT, stock.getUnit());      
      vQuery.map(StockItemView.ITEMID, stock.getItemId());      
    }

    /**
     * Implement only if additional join criteria is required.
     */
    protected void buildWhereClause()
    {

    }

  }
}
