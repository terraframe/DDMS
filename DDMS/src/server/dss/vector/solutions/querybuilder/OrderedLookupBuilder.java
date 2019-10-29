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
package dss.vector.solutions.querybuilder;

import com.runwaysdk.generation.loader.Reloadable;
import com.runwaysdk.query.AND;
import com.runwaysdk.query.Condition;
import com.runwaysdk.query.Join;
import com.runwaysdk.query.SelectablePrimitive;
import com.runwaysdk.query.ValueQuery;

public class OrderedLookupBuilder implements Reloadable
{

  private ValueQuery            query;

  private SelectablePrimitive   orderBy;

  private SelectablePrimitive[] selectables;

  private Condition[]           conditions;

  private Join[]                joins;

  public OrderedLookupBuilder(ValueQuery query, SelectablePrimitive orderBy, SelectablePrimitive[] selectables, Condition[] conditions, Join[] joins)
  {
    this.query = query;
    this.orderBy = orderBy;
    this.selectables = selectables;
    this.conditions = conditions;
    this.joins = joins;
  }

  public OrderedLookupBuilder(ValueQuery query, SelectablePrimitive orderBy, SelectablePrimitive[] selectables, Condition[] conditions)
  {
    this(query, orderBy, selectables, conditions, new Join[] {});
  }

  protected ValueQuery getQuery()
  {
    return query;
  }

  protected SelectablePrimitive[] getSelectables()
  {
    return selectables;
  }

  protected Condition[] getConditions()
  {
    return conditions;
  }

  protected Join[] getJoins()
  {
    return joins;
  }

  public void buildQuery()
  {
    Condition condition = null;

    for (Condition cond : conditions)
    {
      condition = ( condition == null ) ? cond : AND.get(condition, cond);
    }

    this.select();
    this.where(condition);

    this.join();

    this.orderBy();
  }

  protected void orderBy()
  {
    query.ORDER_BY_ASC(orderBy);
  }

  protected void join()
  {
    for (Join join : joins)
    {
      query.AND(join);
    }
  }

  protected void where(Condition condition)
  {
    query.WHERE(condition);
  }

  protected void select()
  {
    query.SELECT(selectables);
  }
}
