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
package dss.vector.solutions.irs;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import com.runwaysdk.dataaccess.transaction.Transaction;
import com.runwaysdk.query.OIterator;
import com.runwaysdk.query.QueryFactory;

import dss.vector.solutions.general.Disease;

public class InsecticideNozzle extends InsecticideNozzleBase implements com.runwaysdk.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1240597942330L;

  public InsecticideNozzle(String parentId, String childId)
  {
    super(parentId, childId);
  }

  public InsecticideNozzle(InsecticideBrand parent, Nozzle child)
  {
    this(parent.getId(), child.getId());
  }
  
  @Override
  public String toString()
  {
    if (this.isNew())
    {
      return "New: " + this.getClassDisplayLabel();
    }
    else if(this.getParent() != null && this.getChild() != null)
    {
      return this.getParent().toString() + " - " + this.getChild().toString();
    }

    return super.toString();
  }

  
  @Override
  protected String buildKey()
  {
    return this.getId();
  }
  
  @Override
  public void apply()
  {
    if(this.getConfigurationDate() == null)
    {
      this.setConfigurationDate(new Date());
    }

    if (this.isNew() && this.getDisease() == null) {
    	this.setDisease(Disease.getCurrent());
    }
  	    
    super.apply();
  }

  public static InsecticideNozzle[] getAll()
  {
    List<InsecticideNozzle> list = new LinkedList<InsecticideNozzle>();
    InsecticideNozzleQuery query = new InsecticideNozzleQuery(new QueryFactory());
    query.WHERE(query.getEnabled().EQ(true));
    query.ORDER_BY_ASC(query.getCreateDate());

    OIterator<? extends InsecticideNozzle> it = query.getIterator();

    try
    {
      while (it.hasNext())
      {
        InsecticideNozzle next = it.next();

        if(next.getParent().getEnabled() && next.getChild().getEnabled())
        {
          list.add(next);
        }
      }
      return list.toArray(new InsecticideNozzle[list.size()]);
    }
    finally
    {
      it.close();
    }
  }

  @Transaction
  public static InsecticideNozzle[] applyAll(InsecticideNozzle[] insecticideNozzles)
  {
    for(InsecticideNozzle concrete : insecticideNozzles)
    {
      concrete.apply();
    }

    return insecticideNozzles;
  }

  public InsecticideNozzleView getView()
  {
    InsecticideNozzleView view = new InsecticideNozzleView();

    view.populateView(this);

    return view;
  }

  public InsecticideNozzleView lockView()
  {
    this.lock();

    return this.getView();
  }

  public InsecticideNozzleView unlockView()
  {
    this.unlock();

    return this.getView();
  }

  public static InsecticideNozzleView getView(String id)
  {
    return InsecticideNozzle.get(id).getView();
  }
}
