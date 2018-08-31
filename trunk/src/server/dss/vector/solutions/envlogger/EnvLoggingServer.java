package dss.vector.solutions.envlogger;

import java.io.File;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;
import org.apache.log4j.spi.LoggerFactory;
import org.json.JSONException;
import org.json.JSONObject;

import com.runwaysdk.configuration.ConfigurationManager;
import com.runwaysdk.configuration.ConfigurationManager.ConfigGroup;
import com.runwaysdk.configuration.ConfigurationReaderIF;
import com.runwaysdk.dataaccess.database.Database;
import com.runwaysdk.generation.loader.Reloadable;
import com.runwaysdk.session.Request;

public class EnvLoggingServer implements Runnable, Reloadable
{
  // https://stackoverflow.com/questions/9652032/how-can-i-create-2-separate-log-files-with-one-log4j-config-file
  
  private static final long ONE_MINUTE = 60000;
  
  private static final long DEFAULT_LOGGING_INTERVAL = ONE_MINUTE * 10;
  
  private static ConfigurationReaderIF props;
  
  private static Logger logger = Logger.getLogger("envLogger");
//  private static Logger logger = LoggerFactory.getLogger("dss.vector.solutions.envlogger.EnvLoggingServer");
  
  private static Boolean isRun = true;
  
  private static long loggingInterval;
  
  public static void start() throws Exception
  {
    props = ConfigurationManager.getReader(ConfigGroup.COMMON, "common.properties");
    Boolean prop = props.getBoolean("logEnvironment", false);
    loggingInterval = props.getLong("logEnvironmentInterval", DEFAULT_LOGGING_INTERVAL);
    
    if (prop)
    {
      logger.error("Starting logging server.");
      Thread t = new Thread(new EnvLoggingServer());
      t.setName("DDMS Environment Logging Server");
      t.start();
    }
    else
    {
      logger.error("Environment logging server is disabled. You can enable by setting common.properties [logEnvironment=true].");
    }
  }
  
  public static void stop()
  {
    logger.error("Shutting down logging server.");
    isRun = false;
  }
  
  @Override
  @Request
  public void run()
  {
    try
    {
      while (isRun)
      {
        JSONObject json = new JSONObject();
        
        printConnections(json);
        
        printMemory(json);
        
        printDisk(json);
        
        logger.error(json.toString());
        
        try {
          Thread.sleep(loggingInterval);
        } catch (InterruptedException e) {
        }
      }
    }
    catch (JSONException e1)
    {
      e1.printStackTrace();
    }
  }
  
  private void printDisk(JSONObject json) throws JSONException
  {
    JSONObject diskJ = new JSONObject();
    
    File root = new File("/");
    
    long total = root.getTotalSpace();
    long free = root.getFreeSpace();
    long usable = root.getUsableSpace();
    
    diskJ.put("total", total);
    diskJ.put("free", free);
    diskJ.put("usable", usable);
    
    json.put("disk", diskJ);
  }
  
  private void printMemory(JSONObject json) throws JSONException
  {
    JSONObject memJ = new JSONObject();
    
    long total = Runtime.getRuntime().totalMemory();
    long free = Runtime.getRuntime().freeMemory();
    
    memJ.put("total", total);
    memJ.put("free", free);
    
    json.put("memory", memJ);
  }
  
  private void printConnections(JSONObject json) throws JSONException
  {
    JSONObject idleCons = new JSONObject();
    
    ResultSet resultSet = Database.query("select * from pg_stat_activity where state='idle in transaction';");
    
    try
    {
      while (resultSet.next())
      {
        JSONObject idleCon = new JSONObject();        
        
        String pid = resultSet.getString("pid");
        String query = resultSet.getString("query");
        
        idleCon.put("pid", pid);
        idleCon.put("query", query);
        
        idleCons.put("connection", idleCon);
      }
    }
    catch (SQLException sqlEx1)
    {
      Database.throwDatabaseException(sqlEx1);
    }
    finally
    {
      try
      {
        java.sql.Statement statement = resultSet.getStatement();
        resultSet.close();
        statement.close();
      }
      catch (SQLException sqlEx2)
      {
        Database.throwDatabaseException(sqlEx2);
      }
    }
    
    json.put("idleConnections", idleCons);
  }
}
