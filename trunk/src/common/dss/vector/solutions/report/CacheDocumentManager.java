package dss.vector.solutions.report;

import java.io.File;
import java.io.IOException;
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
  public static final String                    BIRT_SUFFIX   = "imgs" + File.separator + "birt";

  /**
   * rptdocument cache directory
   */
  public static final String                    CACHE_DIR     = DeployProperties.getJspDir() + File.separator + "reportcache";

  /**
   * birt temp imgs directory
   */
  public static final String                    IMGS_DIR      = DeployProperties.getDeployPath() + File.separator + BIRT_SUFFIX;

  /**
   * Executer responsible for running the cleanup thread
   */
  private static final ScheduledExecutorService executor      = Executors.newSingleThreadScheduledExecutor();

  /**
   * Interval time in milliseconds
   */
  public static final long                      INTERVAL_TIME = 5000;

  @Override
  public void run()
  {
    String[] directories = new String[] { CACHE_DIR, IMGS_DIR };

    for (String dir : directories)
    {
      File directory = new File(dir);

      File[] files = directory.listFiles();

      for (File file : files)
      {
        String sessionId = new String(Base64.decode(file.getName()));

        if (!SessionFacade.containsSession(sessionId))
        {
          try
          {
            FileIO.deleteDirectory(file);
          }
          catch (IOException e)
          {
            // This is run inside of a thread so there isn't much to do except
            // log the error
            MdssLog.error("Error deleting cached report documents for session [" + sessionId + "]", e);
          }
        }
      }
    }
  }

  public static void start()
  {
    executor.scheduleWithFixedDelay(new CacheDocumentManager(), 0, INTERVAL_TIME, TimeUnit.MILLISECONDS);
  }

  public static void stop()
  {
    executor.shutdown();
  }
}
