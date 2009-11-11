package dss.vector.solutions.stock;

import java.util.Date;

import com.terraframe.mojo.query.AND;
import com.terraframe.mojo.query.QueryFactory;

import dss.vector.solutions.geo.generated.GeoEntity;

public class StockEvent extends StockEventBase implements com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1257354693081L;

  public StockEvent()
  {
    super();
  }

  @Override
  protected String buildKey()
  {
    return this.getId();
  }
  
  @Override
  public StockEventView getView()
  {
    StockEventView view = new StockEventView();
    view.populateView(this);

    return view;
  }

  @Override
  public StockEventView unlockView()
  {
    this.unlock();

    return this.getView();
  }

  @Override
  public StockEventView lockView()
  {
    this.lock();

    return this.getView();
  }

  public static int getQuantity(GeoEntity entity, StockItem stockItem, Date date, EventOption event)
  {
    int quantity = 0;

    StockEventQuery query = new StockEventQuery(new QueryFactory());
    query.WHERE(AND.get(query.getStockDepot().EQ(entity), query.getItem().EQ(stockItem), query.getTransactionType().containsExactly(event), query.getEventDate().LE(date)));

    for (StockEvent e : query.getIterator().getAll())
    {
      if (e.getQuantity() != null)
      {
        quantity += e.getQuantity();
      }
    }

    return quantity;
  }

  public static Integer getAvailableStock(GeoEntity entity, StockItem stockItem, Date date)
  {
    int inStock = StockEvent.getQuantity(entity, stockItem, date, EventOption.STOCK_IN);
    int outStock = StockEvent.getQuantity(entity, stockItem, date, EventOption.STOCK_OUT);

    return inStock - outStock;
  }

  public static boolean hasQuantity(GeoEntity entity, StockItem stockItem, Date date)
  {
    int quantity = 0;

    StockEventQuery query = new StockEventQuery(new QueryFactory());
    query.WHERE(AND.get(query.getStockDepot().EQ(entity), query.getItem().EQ(stockItem), query.getEventDate().LE(date)));

    for (StockEvent e : query.getIterator().getAll())
    {
      if (e.getQuantity() != null)
      {
        quantity += e.getQuantity();
      }
    }

    return quantity > 0;
  }
}
