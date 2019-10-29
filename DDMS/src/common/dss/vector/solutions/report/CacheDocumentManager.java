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
package dss.vector.solutions.report;

import java.io.File;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.apache.axis.encoding.Base64;

import com.runwaysdk.constants.DeployProperties;
import com.runwaysdk.session.SessionFacade;
import com.runwaysdk.util.FileIO;

import dss.vector.solutions.MdssLog;

public class CacheDocumentManager implements Runnable
{
  public static final String                    BIRT_SUFFIX   = "imgs/birt";

  /**
   * rptdocument cache directory
   */
  public static final String                    CACHE_DIR     = DeployProperties.getJspDir() + File.separator + "reportcache";

  /**
   * birt temp imgs directory
   */
  public static final String                    IMGS_DIR      = DeployProperties.getDeployPath() + File.separator + "imgs" + File.separator + "birt";

  /**
   * Executer responsible for running the cleanup thread
   */
  private static final ScheduledExecutorService executor      = Executors.newSingleThreadScheduledExecutor();

  /**
   * Interval time in minutes
   */
  public static final long                      INTERVAL_TIME = 5;

  @Override
  public void run()
  {
    try
    {
      String[] directories = new String[] { CACHE_DIR, IMGS_DIR };

      for (String dir : directories)
      {
        File directory = new File(dir);

        File[] files = directory.listFiles();

        if (files != null)
        {
          for (File file : files)
          {
            String sessionId = new String(Base64.decode(file.getName()));

            if (!SessionFacade.containsSession(sessionId))
            {
              FileIO.deleteDirectory(file);
            }
          }
        }
      }
    }
    catch (Exception e)
    {
      // This is run inside of a thread so there isn't much to do except
      // log the error
      MdssLog.error("Error in reporting cleanup thread", e);
    }
  }

  public static void start()
  {
    executor.scheduleWithFixedDelay(new CacheDocumentManager(), 0, INTERVAL_TIME, TimeUnit.MINUTES);
  }

  public static void stop()
  {
    executor.shutdown();
  }
}
