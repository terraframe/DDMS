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
package dss.vector.solutions.report;

import com.runwaysdk.query.Condition;
import com.runwaysdk.query.LeftJoinEq;
import com.runwaysdk.query.QueryFactory;
import com.runwaysdk.query.SelectablePrimitive;
import com.runwaysdk.query.ValueQuery;
import com.runwaysdk.system.scheduler.ExecutableJob;
import com.runwaysdk.system.scheduler.ExecutableJobQuery;

import dss.vector.solutions.query.QueryBuilder;

public class SchedulerUtil extends SchedulerUtilBase implements com.runwaysdk.generation.loader.Reloadable
{
  private static final long serialVersionUID = -2050291932;
  
  public SchedulerUtil()
  {
    super();
  }
  
  /**
   * MdMethod
   * 
   * @param value
   * @return
   */
  public static com.runwaysdk.query.ValueQuery searchByValueQuery(java.lang.String value)
  {
    QueryFactory factory = new QueryFactory();
    
    ValueQuery valueQuery = new ValueQuery(factory);
    ExecutableJobQuery jQ = new ExecutableJobQuery(valueQuery);
    
    SelectablePrimitive[] selectables = new SelectablePrimitive[] { jQ.getId(ExecutableJob.ID), jQ.getJobId(ExecutableJob.JOBID) };

    Condition[] conditions = new Condition[] {};

    LeftJoinEq[] joins = new LeftJoinEq[] {};

    if (value != null && !value.equals(""))
    {
      String[] tokens = value.split(" ");
      SelectablePrimitive[] searchables = new SelectablePrimitive[] { jQ.getJobId(ExecutableJob.JOBID) };

      QueryBuilder.textLookup(valueQuery, factory, tokens, searchables, selectables, conditions, joins);
    }
    else
    {
      QueryBuilder.orderedLookup(valueQuery, factory, jQ.getJobId(ExecutableJob.JOBID), selectables, conditions, joins);
    }

    valueQuery.restrictRows(20, 1);

    return valueQuery;
  }
  
}
