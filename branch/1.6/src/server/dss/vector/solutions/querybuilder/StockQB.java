package dss.vector.solutions.querybuilder;

import java.util.Map;

import org.json.JSONObject;

import com.runwaysdk.dataaccess.MdEntityDAOIF;
import com.runwaysdk.generation.loader.Reloadable;
import com.runwaysdk.query.GeneratedEntityQuery;
import com.runwaysdk.query.QueryException;
import com.runwaysdk.query.QueryFactory;
import com.runwaysdk.query.SelectableSQLInteger;
import com.runwaysdk.query.ValueQuery;

import dss.vector.solutions.general.Disease;
import dss.vector.solutions.query.Layer;
import dss.vector.solutions.stock.EventOption;
import dss.vector.solutions.stock.StockEvent;
import dss.vector.solutions.stock.StockEventQuery;
import dss.vector.solutions.stock.StockItem;
import dss.vector.solutions.stock.StockItemQuery;
import dss.vector.solutions.util.QueryUtil;

public class StockQB extends AbstractQB implements Reloadable
{

  public StockQB(String xml, String config, Layer layer, Integer pageNumber, Integer pageSize, Disease disease)
  {
    super(xml, config, layer, pageNumber, pageNumber, disease);
  }
  
  @Override
  protected String getAuditClassAlias()
  {
    return StockItem.CLASS;
  }

  @Override
  protected ValueQuery construct(QueryFactory queryFactory, ValueQuery valueQuery, Map<String, GeneratedEntityQuery> queryMap, String xml, JSONObject queryConfig)
  {
    StockItemQuery stockItemQuery = (StockItemQuery) queryMap.get(StockItem.CLASS);

    StockEventQuery stockEventQuery = (StockEventQuery) queryMap.get(StockEvent.CLASS);

    dss.vector.solutions.PersonQuery personQuery = (dss.vector.solutions.PersonQuery) queryMap.get(dss.vector.solutions.Person.CLASS);

    if (stockEventQuery != null)
    {
      valueQuery.WHERE(stockEventQuery.getItem().EQ(stockItemQuery.getId()));

      this.addGeoDisplayLabelQuery(stockEventQuery);

      QueryUtil.joinEnumerationDisplayLabels(valueQuery, StockEvent.CLASS, stockEventQuery);

      if (personQuery != null)
      {
        valueQuery.WHERE(stockEventQuery.getStaff().EQ(personQuery.getStockStaffDelegate()));
      }
    }

    try
    {
      SelectableSQLInteger dobSel = (SelectableSQLInteger) valueQuery.getSelectableRef("quanity_instock");

      String eventTable = stockEventQuery.getTableAlias();
      MdEntityDAOIF eventMD = stockEventQuery.getMdClassIF();
      String transactionTypeCol = QueryUtil.getColumnName(eventMD, StockEvent.TRANSACTIONTYPE);
      String quantityCol = QueryUtil.getColumnName(eventMD, StockEvent.QUANTITY);

      String sql = "SUM(" + "CASE " + eventTable + "." + transactionTypeCol + "_c WHEN '" + EventOption.STOCK_IN.getId() + "' THEN " + eventTable + "." + quantityCol + " ELSE " + eventTable + "." + quantityCol + " * -1 END)";

      dobSel.setSQL(sql);
    }
    catch (QueryException e)
    {
    }

    QueryUtil.joinTermAllpaths(valueQuery, StockItem.CLASS, stockItemQuery, this.getTermRestrictions());

    this.setNumericRestrictions(valueQuery, queryConfig);

    QueryUtil.setQueryDates(xml, valueQuery, queryConfig, queryMap, stockEventQuery.getDisease());

    return valueQuery;
  }

}
