package dss.vector.solutions;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.runwaysdk.system.scheduler.SchedulerManager;

public class CleanupContextListener implements ServletContextListener
{

  @Override
  public void contextDestroyed(ServletContextEvent arg0)
  {
    SchedulerManager.shutdown();

    ServerContext.instance().contextDestroyed();
  }

  @Override
  public void contextInitialized(ServletContextEvent arg0)
  {
    ServerContext.instance();

    SchedulerManager.start();
  }
}
