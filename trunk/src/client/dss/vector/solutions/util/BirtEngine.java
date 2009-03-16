package dss.vector.solutions.util;

import java.util.Properties;
import java.util.logging.Level;

import javax.servlet.ServletContext;

import org.eclipse.birt.core.exception.BirtException;
import org.eclipse.birt.core.framework.IPlatformContext;
import org.eclipse.birt.core.framework.Platform;
import org.eclipse.birt.core.framework.PlatformServletContext;
import org.eclipse.birt.report.engine.api.EngineConfig;
import org.eclipse.birt.report.engine.api.IReportEngine;
import org.eclipse.birt.report.engine.api.IReportEngineFactory;

public class BirtEngine
{
  private static IReportEngine engine  = null;

  private static Properties    properties = new Properties();

  public static synchronized void initBirtConfig()
  {
    properties.setProperty("logDirectory", "home/jsmethie/temp");
    properties.setProperty("logLevel", "FINEST");
  }

  public static synchronized IReportEngine getBirtEngine(ServletContext sc)
  {
    if (engine == null)
    {
      EngineConfig config = new EngineConfig();
      
      if (properties != null)
      {
        String logLevel = properties.getProperty("logLevel");
        Level level = Level.OFF;
        
        if ("SEVERE".equalsIgnoreCase(logLevel))
        {
          level = Level.SEVERE;
        }
        else if ("WARNING".equalsIgnoreCase(logLevel))
        {
          level = Level.WARNING;
        }
        else if ("INFO".equalsIgnoreCase(logLevel))
        {
          level = Level.INFO;
        }
        else if ("CONFIG".equalsIgnoreCase(logLevel))
        {
          level = Level.CONFIG;
        }
        else if ("FINE".equalsIgnoreCase(logLevel))
        {
          level = Level.FINE;
        }
        else if ("FINER".equalsIgnoreCase(logLevel))
        {
          level = Level.FINER;
        }
        else if ("FINEST".equalsIgnoreCase(logLevel))
        {
          level = Level.FINEST;
        }
        else if ("OFF".equalsIgnoreCase(logLevel))
        {
          level = Level.OFF;
        }

        config.setLogConfig(properties.getProperty("logDirectory"), level);
      }

      config.setEngineHome("");
      IPlatformContext context = new PlatformServletContext(sc);
      config.setPlatformContext(context);

      try
      {
        Platform.startup(config);
      }
      catch (BirtException e)
      {
        e.printStackTrace();
      }

      IReportEngineFactory factory = (IReportEngineFactory) Platform
          .createFactoryObject(IReportEngineFactory.EXTENSION_REPORT_ENGINE_FACTORY);
      engine = factory.createReportEngine(config);

    }
    return engine;
  }

  public static synchronized void destroyBirtEngine()
  {
    if (engine == null)
    {
      return;
    }
    
    engine.destroy();
    Platform.shutdown();

    engine = null;
  }

  public Object clone() throws CloneNotSupportedException
  {
    throw new CloneNotSupportedException();
  }
}