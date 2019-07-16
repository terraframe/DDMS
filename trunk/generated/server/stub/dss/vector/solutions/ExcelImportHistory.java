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
package dss.vector.solutions;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.runwaysdk.dataaccess.ValueObject;
import com.runwaysdk.dataaccess.database.Database;
import com.runwaysdk.dataaccess.transaction.Transaction;
import com.runwaysdk.query.OIterator;
import com.runwaysdk.query.OrderBy.SortOrder;
import com.runwaysdk.query.QueryFactory;
import com.runwaysdk.query.Selectable;
import com.runwaysdk.query.ValueQuery;
import com.runwaysdk.system.scheduler.AllJobStatus;
import com.runwaysdk.system.scheduler.ExecutableJob;
import com.runwaysdk.system.scheduler.ExecutableJobQuery;
import com.runwaysdk.vault.VaultFileDAO;
import com.runwaysdk.vault.VaultFileDAOIF;

import dss.vector.solutions.report.SchedulerUtil;
import net.jawr.web.resource.bundle.IOUtils;

public class ExcelImportHistory extends ExcelImportHistoryBase implements com.runwaysdk.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1301140123;
  
  public ExcelImportHistory()
  {
    super();
  }
  
  @Override
  public void downloadErrorSpreadsheet(OutputStream outputStream)
  {
    VaultFileDAOIF file = VaultFileDAO.get(this.getErrorFileId());
    
    try
    {
      IOUtils.copy(file.getFileStream(), outputStream);
    }
    catch (IOException e)
    {
      throw new RuntimeException(e);
    }
  }
  
  /**
   * @MdMethod
   * 
   * Used to clear all NON RUNNING job history. Invoked by the upload manager to clear its history.
   */
  public static synchronized void deleteAllHistory()
  {
    QueryFactory qf = new QueryFactory();
    
    ExecutableJobQuery jobQ = new ExecutableJobQuery(qf);
    ExcelImportHistoryQuery historyQ = new ExcelImportHistoryQuery(qf);
    ValueQuery vq = new ValueQuery(qf);
    
    vq.SELECT(jobQ.getId("jobId"));
    vq.SELECT(historyQ.getId("historyId"));
    vq.SELECT(historyQ.get(ExcelImportHistory.HISTORYCOMMENT));
    vq.SELECT(historyQ.get(ExcelImportHistory.HISTORYINFORMATION));
    vq.SELECT(historyQ.get(ExcelImportHistory.STATUS));
    
    vq.WHERE(historyQ.getStatus().notContainsAll(AllJobStatus.RUNNING));
    vq.AND(historyQ.job(jobQ));
    
    Calendar cal = Calendar.getInstance();
    cal.add(Calendar.MONTH, -1);
    vq.AND(historyQ.getEndTime().LT(cal.getTime()));
    
    Set<String> jobs = new HashSet<String>();
    Set<String> historyIds = new HashSet<String>();
    Set<String> statusIds = new HashSet<String>();
    Set<String> historyCommentIds = new HashSet<String>();
    Set<String> historyInfoIds = new HashSet<String>();
    
    OIterator<? extends ValueObject> vqIt = vq.getIterator();
    
    try
    {
      while (vqIt.hasNext())
      {
        ValueObject obj = vqIt.next();
        ExcelImportHistory.get(obj.getValue("historyId"));
        historyIds.add(obj.getValue("historyId"));
        
        jobs.add(obj.getValue("jobId"));
        
        statusIds.add(obj.getValue(ExcelImportHistory.STATUS));
        historyCommentIds.add(obj.getValue(ExcelImportHistory.HISTORYCOMMENT));
        historyInfoIds.add(obj.getValue(ExcelImportHistory.HISTORYINFORMATION));
      }
    }
    finally
    {
      vqIt.close();
    }
    
    deleteAllHistoryInTrans(historyIds, statusIds, historyCommentIds, historyInfoIds);
    deleteAllJobsInTrans(jobs);
    deleteFaultyData();
  }
  @Transaction
  private static void deleteAllHistoryInTrans(Set<String> historyIds, Set<String> statusIds,
      Set<String> historyCommentIds, Set<String> historyInfoIds)
  {
    if (historyIds.size() > 0)
    {
      ArrayList<String> statements = new ArrayList<String>();
      
      String formattedIds = SchedulerUtil.getFormattedIds(historyIds);
      statements.add("delete from job_history jh where jh.id in (" + formattedIds + ")");
      statements.add("delete from job_history_record jhr where jhr.child_id in (" + formattedIds + ")");
      statements.add("delete from excel_import_history eih where eih.id in (" + formattedIds + ")");
      
      String formattedHistoryCommentIds = SchedulerUtil.getFormattedIds(historyCommentIds);
      statements.add("delete from job_history_history_comment jhc where jhc.id in (" + formattedHistoryCommentIds + ")");
      
      String formattedInfoIds = SchedulerUtil.getFormattedIds(historyInfoIds);
      statements.add("delete from job_history_history_informatio jhi where jhi.id in (" + formattedInfoIds + ")");
      
      String formattedStatusIds = SchedulerUtil.getFormattedIds(statusIds);
      statements.add("delete from alljobstatus stat where stat.set_id in (" + formattedStatusIds + ")");
      
      Database.executeBatch(statements);
    }
  }
  @Transaction
  private static void deleteAllJobsInTrans(Set<String> jobs)
  {
    for (String jobId : jobs)
    {
      ExecutableJob.get(jobId).delete();
    }
  }
  @Transaction
  private static void deleteFaultyData()
  {
    Database.executeStatement("DELETE FROM ddms.excel_import_history hist\n" + 
        "WHERE hist.id\n" + 
        "NOT IN\n" + 
        "(\n" + 
        "  SELECT child_id\n" + 
        "  FROM ddms.job_history_record\n" + 
        ");");
    Database.executeStatement("DELETE FROM ddms.job_history hist\n" + 
        "WHERE hist.id\n" + 
        "NOT IN\n" + 
        "(\n" + 
        "  SELECT child_id\n" + 
        "  FROM ddms.job_history_record\n" + 
        ");");
  }
  
  public static java.lang.Long getTotalHistoryCount()
  {
    ExcelImportHistoryQuery query = new ExcelImportHistoryQuery(new QueryFactory());
    
    return query.getCount();
  }
  
  public static dss.vector.solutions.ExcelImportHistory[] getPaginatedHistory(java.lang.String sortAttribute, java.lang.Boolean isAscending, java.lang.Integer pageSize, java.lang.Integer pageNumber)
  {
    ExcelImportHistoryQuery query = new ExcelImportHistoryQuery(new QueryFactory());
    
    Selectable selOrderBy = query.getStartTime();
    if (sortAttribute != null && sortAttribute.length() > 0)
    {
      selOrderBy = query.get(sortAttribute);
    }
    query.ORDER_BY(selOrderBy, isAscending ? SortOrder.ASC : SortOrder.DESC);
    query.restrictRows(pageSize, pageNumber);
    
    OIterator<? extends ExcelImportHistory> jhs = query.getIterator();
    
    ExcelImportHistory[] histories = new ExcelImportHistory[(int) query.getCount()];
    
    long size = query.getCount();
    if (size > pageSize)
    {
      size = pageSize;
    }
    histories = new ExcelImportHistory[(int) size];
    
    try
    {
      int i = 0;
      while (jhs.hasNext())
      {
        ExcelImportHistory jh = jhs.next();
        histories[i] = jh;
        ++i;
      }
    }
    catch (Throwable e)
    {
      return new ExcelImportHistory[]{};
    }
    finally
    {
      jhs.close();
    }
    
    return histories;
  }
  
  /**
   * Not used anymore. Can be deleted.
   * @deprecated
   */
//  public static dss.vector.solutions.ExcelImportHistory[] getAllHistory()
//  {
//    ExcelImportHistoryQuery query = new ExcelImportHistoryQuery(new QueryFactory());
//    query.ORDER_BY(query.getStartTime(), SortOrder.DESC);
//    OIterator<? extends ExcelImportHistory> jhs = query.getIterator();
//    
//    ExcelImportHistory[] histories = new ExcelImportHistory[(int) query.getCount()];
//    
//    try
//    {
//      int i = 0;
//      while (jhs.hasNext())
//      {
//        ExcelImportHistory jh = jhs.next();
//        histories[i] = jh;
//        ++i;
//      }
//    }
//    catch (Throwable e)
//    {
//      return new ExcelImportHistory[]{};
//    }
//    finally
//    {
//      jhs.close();
//    }
//    
//    return histories;
//  }
}
