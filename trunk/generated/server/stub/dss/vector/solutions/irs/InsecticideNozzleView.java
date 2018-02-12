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

public class InsecticideNozzleView extends InsecticideNozzleViewBase implements com.runwaysdk.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1241039480281L;

  public InsecticideNozzleView()
  {
    super();
  }

  public void populateView(InsecticideNozzle concrete)
  {
    InsecticideBrand brand = concrete.getParent();
    Nozzle nozzle = concrete.getChild();

    this.setBrand(brand);
    this.setNozzle(nozzle);
    this.setEnabled(concrete.getEnabled());
    this.setInsecticideNozzleId(concrete.getId());
    this.setBrandLabel(brand.getProductName().getTermDisplayLabel().getValue());
    this.setNozzleLabel(nozzle.getDisplayLabel());
    this.setConfigurationDate(concrete.getConfigurationDate());
  }

  public void populateConcrete(InsecticideNozzle concrete)
  {
    Date date = this.getConfigurationDate();
    
    concrete.setEnabled(this.getEnabled());
    concrete.setConfigurationDate(date);
  }

  private boolean hasConcrete()
  {
    return this.getInsecticideNozzleId() != null && !this.getInsecticideNozzleId().equals("");
  }

  @Transaction
  public void apply()
  {
    InsecticideNozzle concrete = null;

    if (this.hasConcrete())
    {
      concrete = InsecticideNozzle.lock(this.getInsecticideNozzleId());
    }
    else
    {
      concrete = new InsecticideNozzle(this.getBrand(), this.getNozzle());
    }

    this.populateConcrete(concrete);

    concrete.apply();

    this.populateView(concrete);
  }

  @Transaction
  public void deleteConcrete()
  {
    if (this.hasConcrete())
    {
      InsecticideNozzle.get(this.getInsecticideNozzleId()).delete();
    }
  }
  
  @Transaction
  public static InsecticideNozzleView[] getAll()
  {
    InsecticideNozzleQuery query = new InsecticideNozzleQuery(new QueryFactory());
    query.ORDER_BY_ASC(query.getCreateDate());
    
    return InsecticideNozzleView.getViews(query);
  }

  @Transaction
  public static InsecticideNozzleView[] getAllActive()
  {
    InsecticideNozzleQuery query = new InsecticideNozzleQuery(new QueryFactory());
    query.WHERE(query.getEnabled().EQ(true));
    query.ORDER_BY_ASC(query.getCreateDate());

    return InsecticideNozzleView.getViews(query);
  }

  private static InsecticideNozzleView[] getViews(InsecticideNozzleQuery query)
  {
    List<InsecticideNozzleView> list = new LinkedList<InsecticideNozzleView>();
    OIterator<? extends InsecticideNozzle> it = query.getIterator();
    
    try
    {
      while (it.hasNext())
      {
        list.add(it.next().getView());
      }
      return list.toArray(new InsecticideNozzleView[list.size()]);
    }
    finally
    {
      it.close();
    }
  }

  @Transaction
  public static InsecticideNozzleView[] applyAll(InsecticideNozzleView[] insecticides)
  {
    for(InsecticideNozzleView view : insecticides)
    {
      view.apply();
    }

    return insecticides;
  }

}
