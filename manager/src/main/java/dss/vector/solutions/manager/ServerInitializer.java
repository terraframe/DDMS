package dss.vector.solutions.manager;

import java.io.File;
import java.lang.Thread.UncaughtExceptionHandler;

import org.apache.tools.ant.Project;
import org.apache.tools.ant.taskdefs.Java;
import org.apache.tools.ant.types.Path;
import org.eclipse.swt.widgets.Display;

import dss.vector.solutions.manager.server.IServer;

public class ServerInitializer implements Runnable
{
  /**
   * Manager context bean
   */
  private ManagerContextBean       context;

  /**
   * List of applications to initialize
   */
  private String[]                 applications;

  /**
   * Server object which handles starting the server
   */
  private IServer                  server;

  /**
   * ExceptionHandler which handles any uncaught exceptions
   */
  private UncaughtExceptionHandler handler;

  public ServerInitializer(String[] applications, ManagerContextBean context, IServer server, UncaughtExceptionHandler handler)
  {
    this.applications = applications;
    this.handler = handler;
    this.context = context;
    this.server = server;
  }

  public void run()
  {
    final Display display = Display.getCurrent();

    Thread thread = new Thread(new Runnable()
    {
      @Override
      public void run()
      {
        boolean isInitilized = true;

        try
        {
          for (String application : applications)
          {
            String baseDir = System.getProperty("user.dir");
            
            StringBuffer classpath = new StringBuffer();
            classpath.append(ManagerProperties.getInitializerLib() + "*");
            classpath.append(File.pathSeparator + ManagerProperties.getInitializerClasses());
            classpath.append(File.pathSeparator + ManagerContextBean.getApplicationClassesPath(application));
            classpath.append(File.pathSeparator + ManagerContextBean.getApplicationLibPath(application) + "*");

            Project project = new Project();
            project.setBaseDir(new File(baseDir));
            project.init();

            Java java = new Java();
            java.setTaskName("initialize");
            java.setProject(project);
            java.setFork(true);
            java.setFailonerror(true);
            java.setClassname("dss.vector.solutions.initializer.BuilderLauncher");
            java.setClasspath(new Path(project, classpath.toString()));
            java.init();

            java.createArg().setValue("-a" + context.getApplication());
            java.createArg().setValue("-l" + Localizer.getLocale().toString());

            java.createJvmarg().setValue("-Xms" + ManagerProperties.getProcessMemoryMin());
            java.createJvmarg().setValue("-Xmx" + ManagerProperties.getProcessMemoryMax());
            java.createJvmarg().setValue("-XX:PermSize=" + ManagerProperties.getProcessPermSize());

            int results = java.executeJava();

            isInitilized = isInitilized && ( results >= 0 );
          }

          if (isInitilized)
          {
            server.startServer();
          }
          else
          {
            server.refresh();
          }
        }
        finally
        {
          display.asyncExec(new Runnable()
          {
            @Override
            public void run()
            {
              context.setProcessRunning(false);
            }
          });
        }
      }
    });
    thread.setUncaughtExceptionHandler(handler);
    thread.setDaemon(true);
    thread.start();

    context.setProcessRunning(true);
  }
}
