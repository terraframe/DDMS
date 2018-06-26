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

import com.runwaysdk.business.rbac.Operation;
import com.runwaysdk.business.rbac.SingleActorDAOIF;
import com.runwaysdk.dataaccess.MdTypeDAOIF;
import com.runwaysdk.dataaccess.metadata.MdTypeDAO;
import com.runwaysdk.query.Condition;
import com.runwaysdk.query.LeftJoinEq;
import com.runwaysdk.query.OrderBy.SortOrder;
import com.runwaysdk.query.QueryFactory;
import com.runwaysdk.query.SelectablePrimitive;
import com.runwaysdk.query.ValueQuery;
import com.runwaysdk.session.ReadTypePermissionException;
import com.runwaysdk.session.Session;
import com.runwaysdk.session.SessionFacade;
import com.runwaysdk.system.scheduler.ExecutableJob;
import com.runwaysdk.system.scheduler.ExecutableJobQuery;
import com.runwaysdk.system.scheduler.JobHistoryViewQuery;

import dss.vector.solutions.ExcelImportJob;
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
  public static com.runwaysdk.system.scheduler.JobHistoryViewQuery getJobHistories(java.lang.String sortAttribute, java.lang.Boolean isAscending, java.lang.Integer pageSize, java.lang.Integer pageNumber)
  {
    QueryFactory f = new QueryFactory();
    
    String[] typeExcludes = new String[]{ExcelImportJob.CLASS};
    
    JobHistoryViewQuery query = new JobHistoryViewQuery(f, sortAttribute, isAscending, pageSize, pageNumber, typeExcludes);
    
    return query;
  }
  
  /**
   * MdMethod
   * 
   * @param value
   * @return
   */
  public static com.runwaysdk.system.scheduler.ExecutableJobQuery instanceQuery(java.lang.String[] filterTypes, String sortAttr, Boolean isDescending, Integer pageSize, Integer pageNumber)
  {
    String sessionId = Session.getCurrentSession().getId();
    
    boolean access = SessionFacade.checkTypeAccess(sessionId, Operation.READ, ExecutableJob.CLASS);
    
    if (!access)
    {
      SingleActorDAOIF userIF = Session.getCurrentSession().getUser();

      MdTypeDAOIF mdTypeIF = MdTypeDAO.getMdTypeDAO(ExecutableJob.CLASS);

      String errorMsg = "User [" + userIF.getSingleActorName() + "] does not have the read permission for type [" + ExecutableJob.CLASS + "]";
      throw new ReadTypePermissionException(errorMsg, mdTypeIF, userIF);
    }
    
    QueryFactory qf = new QueryFactory();
    ExecutableJobQuery query = new ExecutableJobQuery(qf);
    
    if (isDescending)
    {
      query.ORDER_BY(query.get(sortAttr), SortOrder.DESC);
    }
    else
    {
      query.ORDER_BY(query.get(sortAttr), SortOrder.ASC);
    }
    
    query.restrictRows(pageSize, pageNumber);
    
    for (String type : filterTypes)
    {
      query.WHERE(query.getType().NE(type));
    }
    
    return query;
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
