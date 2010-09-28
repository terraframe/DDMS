package dss.vector.solutions.stock;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import com.runwaysdk.dataaccess.MdEntityDAOIF;
import com.runwaysdk.dataaccess.ProgrammingErrorException;
import com.runwaysdk.query.GeneratedEntityQuery;
import com.runwaysdk.query.QueryException;
import com.runwaysdk.query.QueryFactory;
import com.runwaysdk.query.SelectableSQLInteger;
import com.runwaysdk.query.ValueQuery;

import dss.vector.solutions.ontology.AllPathsQuery;
import dss.vector.solutions.ontology.Term;
import dss.vector.solutions.ontology.TermQuery;
import dss.vector.solutions.query.Layer;
import dss.vector.solutions.util.QueryUtil;

/**
 * @author jsmethie
 * 
 */
public class StockItem extends StockItemBase implements com.runwaysdk.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1257278739419L;

  public StockItem()
  {
    super();
  }
  
  @Override
  public String toString()
  {
    if (this.isNew())
    {
      return "New: " + this.getClassDisplayLabel();
    }
    
    return this.getClassDisplayLabel() + ": " + this.getItemId();
  }  

  @Override
  protected String buildKey()
  {
	  if (this.getItemName() != null && this.getUnit() != null) {
		 return this.getItemName().getKey() + "-" + this.getQuantity() + "-" + this.getUnit().getKey();
	  } else {
		  return super.buildKey();
	  }
  }

  @Override
  public StockItemView getView()
  {
    StockItemView view = new StockItemView();
    view.populateView(this);

    return view;
  }

  @Override
  public StockItemView unlockView()
  {
    this.unlock();

    return this.getView();
  }

  @Override
  public StockItemView lockView()
  {
    this.lock();

    return this.getView();
  }

  @Override
  public void apply()
  {
    validateItemName();

    super.apply();
  }

  @Override
  public void validateItemName()
  {
    Term item = this.getItemName();

    if (item != null && !item.isLeaf())
    {
      String msg = "Term [" + item.getOptionName() + "] may not have any children to be a valid item.";

      ItemLeafProblem p = new ItemLeafProblem(msg);
      p.setNotification(this, ITEMNAME);
      p.setItemName(item.getOptionName());
      p.apply();

      p.throwIt();
    }
  }

  public static StockItemQuery getItems(Term item, QueryFactory factory)
  {
    AllPathsQuery apQ = new AllPathsQuery(factory);
    apQ.WHERE(apQ.getParentTerm().EQ(item));

    TermQuery tQ = new TermQuery(factory);
    tQ.WHERE(tQ.getId().EQ(apQ.getChildTerm().getId()));

    StockItemQuery query = new StockItemQuery(factory);
    query.WHERE(query.getItemName().EQ(tQ));

    return query;
  }
  
  public boolean isLeaf()
  {
    return this.getItemName().isLeaf();
  }

  public static StockItem[] getItems(Term item)
  {
    QueryFactory factory = new QueryFactory();

    StockItemQuery query = getItems(item, factory);

    List<? extends StockItem> list = query.getIterator().getAll();

    return list.toArray(new StockItem[list.size()]);
  }
  
  public static StockItem[] getLeafs(Term item)
  {
    List<StockItem> list = new LinkedList<StockItem>();
    QueryFactory factory = new QueryFactory();

    StockItemQuery query = getItems(item, factory);

    for(StockItem stock : query.getIterator().getAll())
    {
      if(stock.isLeaf())
      {
        list.add(stock);
      }
    }

    return list.toArray(new StockItem[list.size()]);
  }
  
  public String getLabel()
  {
    return this.getItemId() + " - " + this.getItemName().getName() + " " + this.getQuantity() + " " + this.getUnit().getName();
  }
  
  /**
   * Takes in an XML string and returns a ValueQuery representing the structured
   * query in the XML.
   * 
   * @param xml
   * @return
   */
  public static ValueQuery xmlToValueQuery(String xml, String config, Layer layer)
  {
    JSONObject queryConfig;
    try
    {
      queryConfig = new JSONObject(config);
    }
    catch (JSONException e1)
    {
      throw new ProgrammingErrorException(e1);
    }

    QueryFactory queryFactory = new QueryFactory();

    ValueQuery valueQuery = new ValueQuery(queryFactory);

    // IMPORTANT: Required call for all query screens.
    Map<String, GeneratedEntityQuery> queryMap = QueryUtil.joinQueryWithGeoEntities(queryFactory, valueQuery, xml, queryConfig, layer);

    StockItemQuery stockItemQuery = (StockItemQuery) queryMap.get(StockItem.CLASS);
    
    StockEventQuery stockEventQuery = (StockEventQuery) queryMap.get(StockEvent.CLASS);

    dss.vector.solutions.PersonQuery personQuery = (dss.vector.solutions.PersonQuery) queryMap.get(dss.vector.solutions.Person.CLASS);
    
    if(stockEventQuery != null)
    {
      valueQuery.WHERE(stockEventQuery.getItem().EQ(stockItemQuery.getId()));
      
      
      QueryUtil.joinGeoDisplayLabels(valueQuery,StockEvent.CLASS,stockEventQuery);
      
      QueryUtil.joinEnumerationDisplayLabels(valueQuery,StockEvent.CLASS,stockEventQuery);
      
      if(personQuery != null)
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
      
      String sql = "SUM("
      +"CASE "+eventTable+"."+transactionTypeCol+"_c WHEN '"+EventOption.STOCK_IN.getId()+"' THEN "+eventTable+"."+quantityCol
      +" ELSE "+eventTable+"."+quantityCol+" * -1 END)";
      
      dobSel.setSQL(sql);
    }
    catch (QueryException e)
    {
    }
    
    QueryUtil.joinTermAllpaths(valueQuery,StockItem.CLASS,stockItemQuery);  

    QueryUtil.setTermRestrictions(valueQuery, queryMap );    
    
    QueryUtil.setNumericRestrictions(valueQuery, queryConfig);
    
    QueryUtil.setQueryDates(xml, valueQuery, queryConfig, queryMap, stockEventQuery.getDisease());

    return valueQuery;
  }
}
