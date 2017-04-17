package dss.vector.solutions.stock;

import java.util.LinkedList;
import java.util.List;

import com.runwaysdk.query.QueryFactory;
import com.runwaysdk.query.ValueQuery;

import dss.vector.solutions.general.Disease;
import dss.vector.solutions.ontology.AllPathsQuery;
import dss.vector.solutions.ontology.Term;
import dss.vector.solutions.ontology.TermQuery;
import dss.vector.solutions.query.Layer;
import dss.vector.solutions.querybuilder.StockQB;

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
    if (this.getItemName() != null && this.getUnit() != null)
    {
      return this.getItemName().getKey() + "-" + this.getQuantity() + "-" + this.getUnit().getKey();
    }
    else
    {
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

    for (StockItem stock : query.getIterator().getAll())
    {
      if (stock.isLeaf())
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
   * Takes in an XML string and returns a ValueQuery representing the structured query in the XML.
   * 
   * @param xml
   * @return
   */
  public static ValueQuery xmlToValueQuery(String xml, String config, Layer layer, Integer pageNumber, Integer pageSize, Disease disease)
  {
    return new StockQB(xml, config, layer, pageSize, pageSize, disease).construct();
  }
}
