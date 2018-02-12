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
import java.util.HashSet;
import java.util.Set;

import com.runwaysdk.dataaccess.ValueObject;
import com.runwaysdk.dataaccess.transaction.Transaction;
import com.runwaysdk.query.OIterator;
import com.runwaysdk.query.OrderBy.SortOrder;
import com.runwaysdk.query.QueryFactory;
import com.runwaysdk.query.ValueQuery;
import com.runwaysdk.system.scheduler.AllJobStatus;
import com.runwaysdk.system.scheduler.ExecutableJob;
import com.runwaysdk.system.scheduler.ExecutableJobQuery;
import com.runwaysdk.vault.VaultFileDAO;
import com.runwaysdk.vault.VaultFileDAOIF;

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
   * Used to clear all NON RUNNING job history.
   */
  @Transaction
  public static void deleteAllHistory()
  {
    QueryFactory qf = new QueryFactory();
    
    ExecutableJobQuery jobQ = new ExecutableJobQuery(qf);
    ExcelImportHistoryQuery historyQ = new ExcelImportHistoryQuery(qf);
    ValueQuery vq = new ValueQuery(qf);
    
    vq.SELECT(jobQ.getId("jobId"));
    vq.SELECT(historyQ.getId("historyId"));
    
    vq.WHERE(historyQ.getStatus().notContainsAll(AllJobStatus.RUNNING));
    vq.AND(historyQ.job(jobQ));
    
    Set<String> jobs = new HashSet<String>();
    
    OIterator<? extends ValueObject> vqIt = vq.getIterator();
    
    try
    {
      while (vqIt.hasNext())
      {
        ValueObject obj = vqIt.next();
        ExcelImportHistory.get(obj.getValue("historyId")).delete();
        
        jobs.add(obj.getValue("jobId"));
      }
    }
    finally
    {
      vqIt.close();
    }
    
    for (String jobId : jobs)
    {
      ExecutableJob.get(jobId).delete();
    }
  }
  
  public static dss.vector.solutions.ExcelImportHistory[] getAllHistory()
  {
    ExcelImportHistoryQuery query = new ExcelImportHistoryQuery(new QueryFactory());
    query.ORDER_BY(query.getStartTime(), SortOrder.DESC);
    OIterator<? extends ExcelImportHistory> jhs = query.getIterator();
    
    ExcelImportHistory[] histories = new ExcelImportHistory[(int) query.getCount()];
    
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
}
