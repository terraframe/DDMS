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

import com.runwaysdk.query.AND;
import com.runwaysdk.query.QueryFactory;

import dss.vector.solutions.general.Disease;
import dss.vector.solutions.geo.generated.GeoEntity;
import dss.vector.solutions.geo.generated.StockDepot;

public class StockEvent extends StockEventBase implements com.runwaysdk.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1257354693081L;

  public StockEvent()
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
    
    return this.getClassDisplayLabel();
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
  
  @Override
  public void apply()
  {
    validateStockDepot();
    
    if (this.isNew() && this.getDisease() == null) {
    	this.setDisease(Disease.getCurrent());
    }
    
    super.apply();
  }
  
  @Override
  public void validateStockDepot()
  {
    GeoEntity entity = this.getStockDepot();

    if(entity != null && !(entity instanceof StockDepot)) 
    {
      StockDepotProblem p = new StockDepotProblem();
      p.setGeoId(entity.getGeoId());
      p.apply();
      
      p.throwIt();
    }
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

  public static int getQuantity(GeoEntity entity, StockItem stockItem, Date date)
  {
    int quantity = 0;

    StockEventQuery query = new StockEventQuery(new QueryFactory());
    query.WHERE(AND.get(query.getStockDepot().EQ(entity), query.getItem().EQ(stockItem), query.getEventDate().LE(date)));

    for (StockEvent e : query.getIterator().getAll())
    {
      if (e.getQuantity() != null)
      {
        if(e.getTransactionType().contains(EventOption.STOCK_IN))
        {
          quantity += e.getQuantity();
        }
        else 
        {
          quantity -= e.getQuantity();          
        }
      }
    }
    return quantity;
  }
}
