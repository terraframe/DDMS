package dss.vector.solutions.util;

import java.util.logging.Level;

import javax.servlet.ServletContext;

import org.eclipse.birt.core.exception.BirtException;
import org.eclipse.birt.core.framework.IPlatformContext;
import org.eclipse.birt.core.framework.Platform;
import org.eclipse.birt.core.framework.PlatformServletContext;
import org.eclipse.birt.report.engine.api.EngineConfig;
import org.eclipse.birt.report.engine.api.IReportEngine;
import org.eclipse.birt.report.engine.api.IReportEngineFactory;

import com.runwaysdk.constants.ClientRequestIF;
import com.runwaysdk.constants.LocalProperties;
import com.runwaysdk.generation.loader.Reloadable;

import dss.vector.solutions.report.BirtConfigurationExceptionDTO;

public class BirtEngine implements Reloadable
{
  private enum LogLevel implements Reloadable {
    SEVERE(Level.SEVERE), WARNING(Level.WARNING), INFO(Level.INFO), CONFIG(Level.CONFIG), FINE(Level.FINE), FINER(Level.FINER), FINEST(Level.FINEST), OFF(Level.OFF);

    private Level level;

    private LogLevel(Level level)
    {
      this.level = level;
    }

    public Level getLevel()
    {
      return level;
    }
  }

  private static IReportEngine engine = null;

  public static synchronized void initBirtConfig()
  {
  }

  public static synchronized IReportEngine getBirtEngine(ServletContext sc, ClientRequestIF request)
  {
    if (engine == null)
    {
      LogLevel level = LogLevel.FINE;
      IPlatformContext context = new PlatformServletContext(sc);

      EngineConfig config = new EngineConfig();
      config.setEngineHome(""); // Use the default /WEB-INF/platform as the
                                // location BIRT home
      config.setLogConfig(LocalProperties.getLogDirectory(), level.getLevel());
      config.setPlatformContext(context);

      try
      {
        Platform.startup(config);
      }
      catch (BirtException e)
      {
        String msg = "Unable to startup the BIRT report engine please ensure that all BIRT directories are configured properly";

        throw new BirtConfigurationExceptionDTO(request, msg, e);
      }

      IReportEngineFactory factory = (IReportEngineFactory) Platform.createFactoryObject(IReportEngineFactory.EXTENSION_REPORT_ENGINE_FACTORY);
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