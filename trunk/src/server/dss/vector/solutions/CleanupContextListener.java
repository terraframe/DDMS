package dss.vector.solutions;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class CleanupContextListener implements ServletContextListener
{

  @Override
  public void contextDestroyed(ServletContextEvent arg0)
  {
    ServerContext.instance().contextDestroyed();
  }

  @Override
  public void contextInitialized(ServletContextEvent arg0)
  {
    // Do nothing
  }
}
