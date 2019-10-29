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

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import com.runwaysdk.dataaccess.transaction.AttributeNotificationMap;
import com.runwaysdk.dataaccess.transaction.Transaction;
import com.runwaysdk.query.AND;
import com.runwaysdk.query.AttributeEnumeration;
import com.runwaysdk.query.AttributeLocal;
import com.runwaysdk.query.AttributeReference;
import com.runwaysdk.query.Condition;
import com.runwaysdk.query.OIterator;
import com.runwaysdk.query.OR;
import com.runwaysdk.query.QueryFactory;
import com.runwaysdk.query.Selectable;
import com.runwaysdk.query.SelectablePrimitive;

import dss.vector.solutions.MDSSUser;
import dss.vector.solutions.generator.DeleteAllAccessException;
import dss.vector.solutions.geo.generated.GeoEntity;
import dss.vector.solutions.geo.generated.StockDepot;
import dss.vector.solutions.ontology.Term;

public class StockEventView extends StockEventViewBase implements com.runwaysdk.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1257790601945L;

  public StockEventView()
  {
    super();
  }

  public void populateView(StockEvent concrete)
  {
    StockItem item = concrete.getItem();

    Integer stock = StockEvent.getQuantity(concrete.getStockDepot(), item, concrete.getEventDate());

    this.setConcreteId(concrete.getId());
    this.setStockDepot(concrete.getStockDepot());
    this.setItem(item);
    this.setItemLabel(item.getLabel());
    this.setCost(concrete.getCost());
    this.setEventDate(concrete.getEventDate());
    this.setQuantity(concrete.getQuantity());
    this.setStaff(concrete.getStaff());
    this.setAvailableStock(stock);
    this.setStaffLabel(concrete.getStaff().getPerson().toString());
    this.setOtherParty(concrete.getOtherParty());

    this.clearTransactionType();

    for (EventOption option : concrete.getTransactionType())
    {
      this.addTransactionType(option);
    }
  }

  private void populateConcrete(StockEvent concrete)
  {
    concrete.setStockDepot(this.getStockDepot());
    concrete.setItem(this.getItem());
    concrete.setCost(this.getCost());
    concrete.setEventDate(this.getEventDate());
    concrete.setQuantity(this.getQuantity());
    concrete.setStaff(this.getStaff());
    concrete.setOtherParty(this.getOtherParty());

    concrete.clearTransactionType();

    for (EventOption option : this.getTransactionType())
    {
      concrete.addTransactionType(option);
    }
  }

  private void buildAttributeMap(StockEvent concrete)
  {
    new AttributeNotificationMap(concrete, StockEvent.STOCKDEPOT, this, StockEventView.STOCKDEPOT);
    new AttributeNotificationMap(concrete, StockEvent.ITEM, this, StockEventView.ITEM);
    new AttributeNotificationMap(concrete, StockEvent.COST, this, StockEventView.COST);
    new AttributeNotificationMap(concrete, StockEvent.EVENTDATE, this, StockEventView.EVENTDATE);
    new AttributeNotificationMap(concrete, StockEvent.QUANTITY, this, StockEventView.QUANTITY);
    new AttributeNotificationMap(concrete, StockEvent.STAFF, this, StockEventView.STAFF);
    new AttributeNotificationMap(concrete, StockEvent.TRANSACTIONTYPE, this, StockEventView.TRANSACTIONTYPE);
    new AttributeNotificationMap(concrete, StockEvent.OTHERPARTY, this, StockEventView.OTHERPARTY);
  }

  @Override
  public void apply()
  {
    if (this.getQuantity() != null)
    {
      StockEvent concrete = new StockEvent();

      if (this.hasConcrete())
      {
        concrete = StockEvent.get(this.getConcreteId());
      }

      // Build the attribute map between StockEvent and
      // StockEventView for error handling
      this.buildAttributeMap(concrete);

      this.populateConcrete(concrete);

      concrete.apply();

      this.populateView(concrete);
    }
  }

  @Override
  public void deleteConcrete()
  {
    if (this.hasConcrete())
    {
      StockEvent.get(this.getConcreteId()).delete();
    }
  }

  private boolean hasConcrete()
  {
    return this.getConcreteId() != null && !this.getConcreteId().equals("");
  }

  @Transaction
  public static StockEventView[] getViews(String geoId, Term item, Date date, EventOption transactionType)
  {
    GeoEntity entity = GeoEntity.searchByGeoId(geoId);

    if (entity != null && ! ( entity instanceof StockDepot ))
    {
      StockDepotProblem p = new StockDepotProblem();
      p.setGeoId(entity.getGeoId());
      p.apply();

      p.throwIt();
    }

    StockItem[] items = StockEventView.getItems(item, transactionType);

    List<StockEventView> list = new LinkedList<StockEventView>();

    for (StockItem stockItem : items)
    {
      Integer stock = StockEvent.getQuantity(entity, stockItem, date);

      if (stockItem.isLeaf() || stock > 0)
      {
        StockEventView view = new StockEventView();
        view.setStockDepot(entity);
        view.setItem(stockItem);
        view.setItemLabel(stockItem.getLabel());
        view.setAvailableStock(stock);
        view.addTransactionType(transactionType);
        view.setEventDate(date);

        list.add(view);
      }
    }

    return list.toArray(new StockEventView[list.size()]);
  }

  private static StockItem[] getItems(Term item, EventOption transactionType)
  {
    if (transactionType.equals(EventOption.STOCK_IN))
    {
      return StockItem.getLeafs(item);
    }

    return StockItem.getItems(item);
  }

  @Transaction
  public static StockEventView[] applyAll(StockEventView[] views)
  {
    for (StockEventView view : views)
    {
      view.apply();

      view.setQuantity(null);
      view.setCost(null);
      view.setConcreteId(null);
    }

    return views;
  }

  @Transaction
  public static StockEventViewQuery getPage(String sortAttribute, Boolean isAscending, Integer pageSize, Integer pageNumber, String geoId, String itemId, Date startDate, Date endDate)
  {
    GeoEntity entity = GeoEntity.searchByGeoId(geoId);

    if (entity != null && ! ( entity instanceof StockDepot ))
    {
      StockDepotProblem p = new StockDepotProblem();
      p.setGeoId(entity.getGeoId());
      p.apply();

      p.throwIt();
    }

    StockEventViewQuery query = StockEventView.getQuery(geoId, itemId, startDate, endDate);

    if (sortAttribute == null)
    {
      sortAttribute = StockEventView.EVENTDATE;
    }

    Selectable attribute = query.getComponentQuery().getSelectableRef(sortAttribute);

    if (attribute instanceof AttributeEnumeration)
    {
      attribute = query.getTransactionType().getEnumName();
    }
    else if (attribute instanceof AttributeReference)
    {
      attribute = query.getItem().getItemName().getName();
    }
    else if (attribute instanceof AttributeLocal)
    {
      attribute = ( (AttributeLocal) attribute ).localize();
    }

    if (isAscending)
    {
      query.ORDER_BY_ASC((SelectablePrimitive) attribute, sortAttribute);
    }
    else
    {
      query.ORDER_BY_DESC((SelectablePrimitive) attribute, sortAttribute);
    }

    if (pageSize != 0 && pageNumber != 0)
    {
      query.restrictRows(pageSize, pageNumber);
    }

    return query;
  }

  @Transaction
  public static void deleteAll(String geoId, String itemId, Date startDate, Date endDate)
  {
    if (!MDSSUser.canDeleteAll())
    {
      throw new DeleteAllAccessException();
    }

    StockEventViewQuery query = StockEventView.getQuery(geoId, itemId, startDate, endDate);

    OIterator<? extends StockEventView> iterator = null;

    try
    {
      iterator = query.getIterator();

      while (iterator.hasNext())
      {
        StockEventView item = iterator.next();
        item.deleteConcrete();
      }
    }
    finally
    {
      iterator.close();
    }
  }

  private static StockEventViewQuery getQuery(String geoId, String itemId, Date startDate, Date endDate)
  {
    StockItem[] items = StockItem.getItems(Term.get(itemId));

    StockEventViewQuery query = new StockEventViewQuery(new QueryFactory());

    Condition itemCondition = query.getItem().getItemName().EQ(itemId);

    for (StockItem item : items)
    {
      itemCondition = OR.get(itemCondition, query.getItem().EQ(item));
    }

    Condition whereCondition = AND.get(query.getStockDepot().getGeoId().EQ(geoId), itemCondition);
    whereCondition = AND.get(whereCondition, query.getEventDate().GE(startDate), query.getEventDate().LE(endDate));

    query.WHERE(whereCondition);

    return query;
  }
}
