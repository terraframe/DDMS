package dss.vector.solutions;

import java.util.HashSet;
import java.util.Set;

import com.runwaysdk.dataaccess.ValueObject;
import com.runwaysdk.dataaccess.transaction.Transaction;
import com.runwaysdk.query.OIterator;
import com.runwaysdk.query.QueryFactory;
import com.runwaysdk.query.ValueQuery;
import com.runwaysdk.system.scheduler.AllJobStatus;
import com.runwaysdk.system.scheduler.ExecutableJob;
import com.runwaysdk.system.scheduler.ExecutableJobQuery;

public class ExcelImportHistory extends ExcelImportHistoryBase implements com.runwaysdk.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1301140123;
  
  public ExcelImportHistory()
  {
    super();
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
