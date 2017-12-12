package dss.vector.solutions;

import com.runwaysdk.dataaccess.transaction.Transaction;
import com.runwaysdk.query.OIterator;
import com.runwaysdk.query.QueryFactory;
import com.runwaysdk.system.scheduler.AllJobStatus;

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
    
    ExcelImportHistoryQuery query = new ExcelImportHistoryQuery(qf);
    OIterator<? extends ExcelImportHistory> jhs = query.getIterator();
    
    while (jhs.hasNext())
    {
      ExcelImportHistory jh = jhs.next();
      if (!jh.getStatus().contains(AllJobStatus.RUNNING))
      {
        jh.delete();
      }
    }
  }
  
  public static dss.vector.solutions.ExcelImportHistory[] getAllHistory()
  {
    ExcelImportHistoryQuery query = new ExcelImportHistoryQuery(new QueryFactory());
    OIterator<? extends ExcelImportHistory> jhs = query.getIterator();
    
    ExcelImportHistory[] histories = new ExcelImportHistory[(int) query.getCount()];
    
    int i = 0;
    while (jhs.hasNext())
    {
      ExcelImportHistory jh = jhs.next();
      histories[i] = jh;
      ++i;
    }
    
    return histories;
  }
}
