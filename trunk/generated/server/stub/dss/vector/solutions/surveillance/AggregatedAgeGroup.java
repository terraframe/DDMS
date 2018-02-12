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
package dss.vector.solutions.surveillance;

import java.util.LinkedList;
import java.util.List;

import com.runwaysdk.query.QueryFactory;
import com.runwaysdk.query.SelectablePrimitive;

import dss.vector.solutions.general.Disease;

public class AggregatedAgeGroup extends AggregatedAgeGroupBase implements com.runwaysdk.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1238693152481L;

  public AggregatedAgeGroup()
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
    else if (this.getDisplayLabel() != null)
    {
      return this.getDisplayLabel();
    }

    return super.toString();
  }

  protected String buildKey()
  {
    return this.getDisease().getKey() + ": " + this.getStartAge() + " - " + this.getEndAge();
  }

  @Override
  public void apply()
  {
    if (this.isNew() && this.getDisease() == null)
    {
      this.setDisease(Disease.getCurrent());
    }

    super.apply();
  }

  public static AggregatedAgeGroup[] getAll()
  {
    List<AggregatedAgeGroup> list = new LinkedList<AggregatedAgeGroup>();
    AggregatedAgeGroupQuery query = new AggregatedAgeGroupQuery(new QueryFactory());
    query.WHERE(query.getDisease().EQ(Disease.getCurrent()));
    query.AND(query.getActive().EQ(true));
    query.ORDER_BY_ASC(query.getEndAge());

    for (AggregatedAgeGroup d : query.getIterator())
    {
      list.add(d);
    }

    return list.toArray(new AggregatedAgeGroup[list.size()]);
  }

  public static AggregatedAgeGroupQuery getPage(String sortAttribute, Boolean isAscending, Integer pageSize, Integer pageNumber)
  {
    AggregatedAgeGroupQuery query = new AggregatedAgeGroupQuery(new QueryFactory());
    query.WHERE(query.getDisease().EQ(Disease.getCurrent()));

    SelectablePrimitive selectablePrimitive = (SelectablePrimitive) query.get(AggregatedAgeGroup.STARTAGE);

    if (sortAttribute != null)
    {
      selectablePrimitive = (SelectablePrimitive) query.get(sortAttribute);

    }

    if (isAscending)
    {
      query.ORDER_BY_ASC(selectablePrimitive, sortAttribute);
    }
    else
    {
      query.ORDER_BY_DESC(selectablePrimitive, sortAttribute);
    }

    return query;
  }
}
