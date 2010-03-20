package dss.vector.solutions;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.runwaysdk.generation.loader.Reloadable;
import com.runwaysdk.session.StartSession;

import dss.vector.solutions.query.SavedMap;

public class CleanupContextListener implements ServletContextListener, Reloadable
{

  public void contextDestroyed(ServletContextEvent arg0)
  {
    doCleanup();
  }
  
  @StartSession
  private void doCleanup()
  {
    // Clean up all database map views
    SavedMap.cleanOldViews(System.currentTimeMillis());
  }

  public void contextInitialized(ServletContextEvent arg0)
  {
    
  }

}
