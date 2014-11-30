package dss.vector.solutions.report;

import org.quartz.DisallowConcurrentExecution;

import com.runwaysdk.business.rbac.Authenticate;
import com.runwaysdk.query.OIterator;
import com.runwaysdk.query.QueryFactory;
import com.runwaysdk.system.scheduler.ExecutionContext;
import com.runwaysdk.system.scheduler.JobHistory;

@DisallowConcurrentExecution
public class ReportJob extends ReportJobBase implements com.runwaysdk.generation.loader.Reloadable
{
  private static final long serialVersionUID = -1264275518;

  public ReportJob()
  {
    super();
  }

  @Override
  public void execute(ExecutionContext executionContext)
  {
    ReportItem item = this.getReportItem();
    item.lock();

    try
    {
      item.generateAndSaveDocument(new ReportParameter[] {});
    }
    finally
    {
      item.unlock();
    }
  }

  public static ReportJob get(ReportItem item)
  {
    ReportJobQuery query = new ReportJobQuery(new QueryFactory());
    query.WHERE(query.getReportItem().EQ(item));

    OIterator<? extends ReportJob> it = query.getIterator();

    try
    {
      if (it.hasNext())
      {
        return it.next();
      }

      return null;
    }
    finally
    {
      it.close();
    }
  }

  @Authenticate
  public synchronized JobHistory start()
  {
    return super.start();
  }

  @Authenticate
  public void stop()
  {
    super.stop();
  }

  @Authenticate
  public void pause()
  {
    super.pause();
  }

  @Authenticate
  public void cancel()
  {
    super.cancel();
  }

  @Authenticate
  public void resume()
  {
    super.resume();
  }
}
