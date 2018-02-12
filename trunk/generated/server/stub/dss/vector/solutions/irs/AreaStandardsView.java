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

import com.runwaysdk.dataaccess.transaction.Transaction;
import com.runwaysdk.query.AttributeEnumeration;
import com.runwaysdk.query.OIterator;
import com.runwaysdk.query.QueryFactory;
import com.runwaysdk.query.Selectable;
import com.runwaysdk.query.SelectablePrimitive;

public class AreaStandardsView extends AreaStandardsViewBase implements com.runwaysdk.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1240597920978L;

  public AreaStandardsView()
  {
    super();
  }

  private void populateConcrete(AreaStandards concrete)
  {
    concrete.setRoom(this.getRoom());
    concrete.setStructureArea(this.getStructureArea());
    concrete.setHousehold(this.getHousehold());
    concrete.setUnitNozzleAreaCoverage(this.getUnitNozzleAreaCoverage());
    concrete.setStartDate(this.getStartDate());
    concrete.setEndDate(this.getEndDate());
    concrete.clearTargetUnit();

    for (TargetUnit unit : this.getTargetUnit())
    {
      concrete.addTargetUnit(unit);
    }
  }

  private boolean hasConcrete()
  {
    return this.getAreaStandardsId() != null && !this.getAreaStandardsId().equals("");
  }

  @Override
  public void apply()
  {
    if (this.getStartDate() == null)
    {
      this.setStartDate(new Date());
    }

    AreaStandards concrete = new AreaStandards();

    if (this.hasConcrete())
    {
      concrete = AreaStandards.lock(this.getAreaStandardsId());
    }

    this.apply(concrete);
  }

  @Override
  @Transaction
  public void deleteConcrete()
  {
    if (this.hasConcrete())
    {
      AreaStandards.get(this.getAreaStandardsId()).delete();
    }
  }

  @Override
  public void applyClone()
  {
    this.setStartDate(new Date());

    AreaStandards concrete = AreaStandards.get(this.getStartDate());

    if (concrete == null)
    {
      concrete = new AreaStandards();
    }
    else
    {
      concrete.lock();
    }

    this.apply(concrete);
  }

  private void apply(AreaStandards concrete)
  {
    this.populateConcrete(concrete);

    concrete.apply();

    concrete.populateView(this);
  }

  @Transaction
  public static AreaStandardsView getMostRecent()
  {
    AreaStandardsQuery query = new AreaStandardsQuery(new QueryFactory());
    query.ORDER_BY_DESC(query.getStartDate());

    OIterator<? extends AreaStandards> it = query.getIterator();

    try
    {
      while (it.hasNext())
      {
        AreaStandards concrete = it.next();

        if (concrete.getStartDate() != null || !it.hasNext())
        {
          return concrete.getView();
        }
      }

      return null;
    }
    finally
    {
      it.close();
    }
  }

  public Float getTargetArea()
  {
    for (TargetUnit unit : this.getTargetUnit())
    {
      if (unit.equals(TargetUnit.HOUSEHOLD))
      {
        return this.getHousehold();
      }
      else if (unit.equals(TargetUnit.ROOM))
      {
        return this.getRoom();
      }
      if (unit.equals(TargetUnit.STRUCTURE))
      {
        return this.getStructureArea();
      }
    }

    return this.getHousehold();
  }

  public static AreaStandardsViewQuery getPage(String sortAttribute, Boolean isAscending, Integer pageSize, Integer pageNumber)
  {
    AreaStandardsViewQuery query = new AreaStandardsViewQuery(new QueryFactory());

    if (sortAttribute == null)
    {
      sortAttribute = AreaStandardsView.STARTDATE;
    }

    Selectable selectable = query.getComponentQuery().getSelectableRef(sortAttribute);

    if (sortAttribute.equalsIgnoreCase(AreaStandardsView.TARGETUNIT))
    {
      selectable = ( (AttributeEnumeration) selectable.getAttribute() ).get(TargetUnitMaster.ENUMNAME);
    }

    if (isAscending)
    {
      query.ORDER_BY_ASC((SelectablePrimitive) selectable, sortAttribute);
    }
    else
    {
      query.ORDER_BY_DESC((SelectablePrimitive) selectable, sortAttribute);
    }

    if (pageSize != 0 && pageNumber != 0)
    {
      query.restrictRows(pageSize, pageNumber);
    }

    return query;
  }

}
