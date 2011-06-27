package dss.vector.solutions.manager;

import java.io.File;
import java.lang.Thread.UncaughtExceptionHandler;

import org.apache.tools.ant.Project;
import org.apache.tools.ant.taskdefs.Java;
import org.apache.tools.ant.types.Path;
import org.apache.tools.ant.types.Commandline.Argument;
import org.eclipse.swt.widgets.Display;

public class Initializer implements Runnable
{
  private ManagerContextBean       context;

  private String                   application;

  private UncaughtExceptionHandler handler;

  public Initializer(String application, ManagerContextBean context, UncaughtExceptionHandler handler)
  {
    this.application = application;
    this.handler = handler;
    this.context = context;
  }

  public void run()
  {
    final Display display = Display.getCurrent();

    Thread thread = new Thread(new Runnable()
    {
      @Override
      public void run()
      {
        try
        {
          StringBuffer classpath = new StringBuffer();
          classpath.append(ManagerProperties.getInitializerLib() + "*");
          classpath.append(File.pathSeparator + ManagerProperties.getInitializerClasses());
          classpath.append(File.pathSeparator + ManagerContextBean.getApplicationClassesPath(application));
          classpath.append(File.pathSeparator + ManagerContextBean.getApplicationLibPath(application) + "*");

          Project project = new Project();
          project.setBaseDir(new File(System.getProperty("user.dir")));
          project.init();

          Java java = new Java();
          java.setTaskName("runjava");
          java.setProject(project);
          java.setFork(true);
          java.setFailonerror(true);
          java.setClassname("dss.vector.solutions.initializer.Builder");
          java.setClasspath(new Path(project, classpath.toString()));
          java.init();

          Argument argument = java.createArg();
          argument.setValue(context.getApplication() + "!@!@!@");

          java.executeJava();
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
