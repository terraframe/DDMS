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

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.apache.commons.lang.StringUtils;

import com.runwaysdk.business.rbac.Operation;
import com.runwaysdk.business.rbac.SingleActorDAOIF;
import com.runwaysdk.dataaccess.MdTypeDAOIF;
import com.runwaysdk.dataaccess.database.Database;
import com.runwaysdk.dataaccess.metadata.MdTypeDAO;
import com.runwaysdk.dataaccess.transaction.Transaction;
import com.runwaysdk.query.Condition;
import com.runwaysdk.query.LeftJoinEq;
import com.runwaysdk.query.OrderBy.SortOrder;
import com.runwaysdk.query.QueryFactory;
import com.runwaysdk.query.SelectablePrimitive;
import com.runwaysdk.query.ValueQuery;
import com.runwaysdk.session.ReadTypePermissionException;
import com.runwaysdk.session.Session;
import com.runwaysdk.session.SessionFacade;
import com.runwaysdk.system.scheduler.AllJobStatus;
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
   * Invoked by the DDMS report scheduler to clear its history.
   *
   * @param value
   * @return
   */
  public static synchronized void clearHistory()
  {
    Set<String> ids = new HashSet<String>();
    Set<String> statusIds = new HashSet<String>();
    Set<String> historyCommentIds = new HashSet<String>();
    Set<String> historyInfoIds = new HashSet<String>();
   
    ResultSet resultSet2 = Database.query("select jh.id as id, jh.history_comment as history_comment, jh.history_information as history_information, jh.status as status from\n" + 
        "  job_history_record jhr\n" + 
        "  inner join job_history jh on jh.id = jhr.child_id\n" + 
        "  inner join abstract_job job on job.id = jhr.parent_id\n" + 
        "  inner join alljobstatus ajs on jh.status = ajs.set_id\n" + 
        "where\n" + 
        "  job.type != '" + ExcelImportJob.CLASS + "'\n" + 
        "  AND ajs.item_id != '" + AllJobStatus.RUNNING.getId() + "'");

    try
    {
      while (resultSet2.next())
      {
        String id = resultSet2.getString("id");

        ids.add(id);
        
        statusIds.add(resultSet2.getString("status"));
        historyCommentIds.add(resultSet2.getString("history_comment"));
        historyInfoIds.add(resultSet2.getString("history_information"));
      }
    }
    catch (SQLException sqlEx1)
    {
      Database.throwDatabaseException(sqlEx1);
    }
    finally
    {
      try
      {
        java.sql.Statement statement = resultSet2.getStatement();
        resultSet2.close();
        statement.close();
      }
      catch (SQLException sqlEx2)
      {
        Database.throwDatabaseException(sqlEx2);
      }
    }
    
    // TODO : Runway's querybuilder not working
//    QueryFactory qf = new QueryFactory();
//    
//    ExecutableJobQuery ejq = new ExecutableJobQuery(qf);
//    JobHistoryQuery jhq = new ExcelImportHistoryQuery(qf);
//    ValueQuery vq = new ValueQuery(qf);
//    
//    vq.SELECT(jhq.getId("id"));
//    
//    vq.WHERE(ejq.getType().NE(ExcelImportJob.CLASS));
//    vq.WHERE(jhq.getStatus().notContainsAll(AllJobStatus.RUNNING));
//    vq.AND(jhq.job(ejq));
//    
//    OIterator<? extends ValueObject> it = vq.getIterator();
//    
//    try
//    {
//      for (ValueObject vo : it)
//      {
//        String id = vo.getValue("id");
//        ids.add(id);
//      }
//    }
//    finally
//    {
//      it.close();
//    }
    
    deleteHistoryInTrans(ids, statusIds, historyCommentIds, historyInfoIds);
  }
  @Transaction
  private static void deleteHistoryInTrans(Set<String> ids, Set<String> statusIds,
      Set<String> historyCommentIds, Set<String> historyInfoIds)
  {
    if (ids.size() > 0)
    {
      ArrayList<String> statements = new ArrayList<String>();
      
      String formattedIds = getFormattedIds(ids);
      
      statements.add("delete from job_history jh where jh.id in (" + formattedIds + ")");
      statements.add("delete from job_history_record jhr where jhr.child_id in (" + formattedIds + ")");
      
      String formattedHistoryCommentIds = SchedulerUtil.getFormattedIds(historyCommentIds);
      statements.add("delete from job_history_history_comment jhc where jhc.id in (" + formattedHistoryCommentIds + ")");
      
      String formattedInfoIds = SchedulerUtil.getFormattedIds(historyInfoIds);
      statements.add("delete from job_history_history_informatio jhi where jhi.id in (" + formattedInfoIds + ")");
      
      String formattedStatusIds = SchedulerUtil.getFormattedIds(statusIds);
      statements.add("delete from alljobstatus stat where stat.set_id in (" + formattedStatusIds + ")");
      
      Database.executeBatch(statements);
    }
  }
  
  public static String getFormattedIds(Collection<String> ids)
  {
    ArrayList<String> quotedIds = new ArrayList<String>();
    for (String id : ids)
    {
      String quotedId = "'" + id + "'";
      
      quotedIds.add(quotedId);
    }
    String formattedIds = StringUtils.join(quotedIds, ",");
    
    return formattedIds;
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
