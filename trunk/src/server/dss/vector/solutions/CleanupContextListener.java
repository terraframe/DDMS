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
