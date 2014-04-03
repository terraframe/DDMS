package dss.vector.solutions;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.runwaysdk.system.scheduler.SchedulerManager;

import dss.vector.solutions.report.CacheDocumentManager;

public class CleanupContextListener implements ServletContextListener
{

  @Override
  public void contextInitialized(ServletContextEvent arg0)
  {
    ServerContext.instance();

    SchedulerManager.start();

    CacheDocumentManager.start();
  }

  @Override
  public void contextDestroyed(ServletContextEvent arg0)
  {
    SchedulerManager.shutdown();

    ServerContext.instance().contextDestroyed();

    CacheDocumentManager.stop();
  }
}
