package dss.vector.solutions.basemap;

import com.runwaysdk.query.QueryFactory;
import com.runwaysdk.system.scheduler.AllJobStatus;
import com.runwaysdk.system.scheduler.ExecutionContext;
import com.runwaysdk.system.scheduler.JobHistoryQuery;
import com.runwaysdk.system.scheduler.JobHistoryRecordQuery;

import dss.vector.solutions.geoserver.LocalBasemapBuilder;

public class OfflineBasemapJob extends OfflineBasemapJobBase implements com.runwaysdk.generation.loader.Reloadable
{
  private static final long serialVersionUID = -858447874;
  
  public static String[] files;
  
  public OfflineBasemapJob()
  {
    super();
  }
  
  public static Boolean isBasemapJobRunning()
  {
    QueryFactory qf = new QueryFactory();
    OfflineBasemapJobQuery basemapJobQ = new OfflineBasemapJobQuery(qf);
    JobHistoryQuery jhq = new JobHistoryQuery(qf);
    JobHistoryRecordQuery jhrq = new JobHistoryRecordQuery(qf);
    
    jhq.WHERE(jhq.getStatus().containsExactly(AllJobStatus.RUNNING));
    jhrq.WHERE(jhrq.hasChild(jhq));
    basemapJobQ.AND(basemapJobQ.getId().EQ(jhrq.parentId()));
    basemapJobQ.AND(basemapJobQ.getKeyName().EQ("DEFAULT_OFFLINE_BASEMAP_JOB"));
    
    if (jhq.getCount() > 0)
    {
      return true;
    }
    
    return false;
  }

  @Override
  public void execute(ExecutionContext executionContext)
  {
    LocalBasemapBuilder.importBasemapFiles(files);
  }
  
}
