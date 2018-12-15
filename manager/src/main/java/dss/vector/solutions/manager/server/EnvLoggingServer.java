package dss.vector.solutions.manager.server;

import org.apache.log4j.Logger;
import org.json.JSONException;

public class EnvLoggingServer implements Runnable
{
  // https://stackoverflow.com/questions/9652032/how-can-i-create-2-separate-log-files-with-one-log4j-config-file

  private static final long            ONE_MINUTE               = 60000;

  private static final long            DEFAULT_LOGGING_INTERVAL = ONE_MINUTE * 10;

  private static Logger                logger                   = Logger.getLogger("envLogger");
  // private static Logger logger = LoggerFactory.getLogger("dss.vector.solutions.envlogger.EnvLoggingServer");

  private static Boolean               isRun                    = true;

  private static long                  loggingInterval;

  private IServer server;

  public EnvLoggingServer(IServer server)
  {
    this.server = server;
  }

  public void start()
  {
    loggingInterval = DEFAULT_LOGGING_INTERVAL;
    isRun = true;
    
    logger.error("Starting logging server.");
    Thread t = new Thread(this);
    t.setName("DDMS Environment Logging Server");
    t.start();
  }

  public void stop()
  {
    logger.error("Shutting down logging server.");
    isRun = false;
  }

  @Override
  public void run()
  {
    try
    {
      while (isRun)
      {
        logger.error(this.server.getEnvInfo());

        try
        {
          Thread.sleep(loggingInterval);
        }
        catch (InterruptedException e)
        {
        }
      }
    }
    catch (JSONException e1)
    {
      e1.printStackTrace();
    }
  }
}
