package dss.vector.solutions.report;

import org.quartz.DisallowConcurrentExecution;

import com.runwaysdk.business.rbac.Authenticate;
import com.runwaysdk.query.OIterator;
import com.runwaysdk.query.QueryFactory;
import com.runwaysdk.system.scheduler.AllJobStatus;
import com.runwaysdk.system.scheduler.ExecutionContext;
import com.runwaysdk.system.scheduler.JobHistory;
import com.runwaysdk.system.scheduler.JobHistoryQuery;
import com.runwaysdk.system.scheduler.JobHistoryRecordQuery;

import dss.vector.solutions.query.CycleJobQuery;
import dss.vector.solutions.query.MapInUseException;

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
    // Make sure there's not another instance of this job already running.
    QueryFactory qf = new QueryFactory();
    ReportJobQuery rjq = new ReportJobQuery(qf);
    JobHistoryQuery jhq = new JobHistoryQuery(qf);
    JobHistoryRecordQuery jhrq = new JobHistoryRecordQuery(qf);
    
    jhq.WHERE(jhq.getStatus().containsExactly(AllJobStatus.RUNNING));
    jhrq.WHERE(jhrq.hasChild(jhq));
    rjq.AND(rjq.getId().EQ(jhrq.parentId()));
    rjq.AND(rjq.getId().EQ(this.getId()));
    jhq.AND(jhq.getId().NE(executionContext.getJobHistory().getId()));
    
    if (jhq.getCount() > 0)
    {
      // TODO : We should be localizing our exceptions.
      throw new RuntimeException("Only one instance of a job may be running at a time.");
    }
    
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
