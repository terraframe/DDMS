package dss.vector.solutions.manager.action;

import java.io.File;
import java.lang.Thread.UncaughtExceptionHandler;

import org.apache.tools.ant.Project;
import org.apache.tools.ant.taskdefs.Java;
import org.apache.tools.ant.types.Path;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.widgets.Display;

import dss.vector.solutions.manager.Localizer;
import dss.vector.solutions.manager.ManagerContextBean;
import dss.vector.solutions.manager.ManagerProperties;

public class GeoAction extends Action
{
  private ManagerContextBean       context;

  private UncaughtExceptionHandler exceptionHandler;

  public GeoAction(ManagerContextBean context, UncaughtExceptionHandler exceptionHandler)
  {
    super(Localizer.getMessage("GIS"), ImageDescriptor.createFromURL(Object.class.getResource("/icons/globe.png")));
    this.setToolTipText(Localizer.getMessage("GIS"));

    this.context = context;
    this.exceptionHandler = exceptionHandler;
  }

  @Override
  public void run()
  {
    if (context.hasApplication())
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
            classpath.append(ManagerProperties.getGeoLib() + "*");
            classpath.append(File.pathSeparator + ManagerProperties.getGeoClasses());
            classpath.append(File.pathSeparator + context.getApplicationClassesPath());
            classpath.append(File.pathSeparator + context.getApplicationLibPath() + "*");

            Project project = new Project();
            project.setBaseDir(new File(System.getProperty("user.dir")));
            project.init();

            Java java = new Java();
            java.setTaskName("runjava");
            java.setProject(project);
            java.setFork(true);
            java.setFailonerror(true);
            java.setClassname("dss.vector.solutions.gis.GISManagerLauncher");
            java.setClasspath(new Path(project, classpath.toString()));
            java.init();
            
            java.createArg().setValue("-l" + Localizer.getLocale().toString());

            java.createJvmarg().setValue("-Xms" + ManagerProperties.getProcessMemoryMin());
            java.createJvmarg().setValue("-Xmx" + ManagerProperties.getProcessMemoryMax());
            java.createJvmarg().setValue("-XX:PermSize=" + ManagerProperties.getProcessPermSize());

            int result = java.executeJava();

            if (result != 0)
            {
              throw new RuntimeException(Localizer.getMessage("GIS_EXITED_WITH_ERROR"));
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
      thread.setUncaughtExceptionHandler(exceptionHandler);
      thread.setDaemon(true);
      thread.start();

      context.setProcessRunning(true);
    }
  }
}
