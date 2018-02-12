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

import com.runwaysdk.defaults.RMIContextListener;
import com.runwaysdk.generation.loader.Reloadable;
import com.runwaysdk.util.ClientInitializer;

import dss.vector.solutions.general.EmailContextListener;

public class ContextListenerManagerHelper implements Reloadable
{
  private RMIContextListener rMIContextListener;
  private CleanupContextListener cleanupContextListener;
  private EmailContextListener emailContextListener;
  
  public ContextListenerManagerHelper()
  {
    rMIContextListener = new RMIContextListener();
    cleanupContextListener = new CleanupContextListener();
    emailContextListener = new EmailContextListener();
  }
  
  public void contextInitialized(ServletContextEvent arg0)
  {
    rMIContextListener.contextInitialized(arg0);
    ClientInitializer.init();
    cleanupContextListener.contextInitialized(arg0);
    emailContextListener.contextInitialized(arg0);
  }

  public void contextDestroyed(ServletContextEvent arg0)
  {
    rMIContextListener.contextDestroyed(arg0);
    cleanupContextListener.contextDestroyed(arg0);
    emailContextListener.contextDestroyed(arg0);
  }
}
