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
import dss.vector.solutions.manager.properties.ManagerProperties;

public class SyncAction extends Action implements UncaughtExceptionHandler
{
  private ManagerContextBean context;

  public SyncAction(ManagerContextBean context)
  {
    super(Localizer.getMessage("SYNCH"), ImageDescriptor.createFromURL(Object.class.getResource("/icons/Transfer.png")));
    this.setToolTipText(Localizer.getMessage("SYNCH"));

    this.context = context;
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
            classpath.append(ManagerProperties.getSynchLib() + "*");
            classpath.append(File.pathSeparator + ManagerProperties.getSynchClasses());
            classpath.append(File.pathSeparator + context.getApplicationClassesPath());
            classpath.append(File.pathSeparator + ManagerProperties.getBackupLib() + "*");

            Project project = new Project();
            project.setBaseDir(new File(System.getProperty("user.dir")));
            project.init();

            Java java = new Java();
            java.setTaskName("runjava");
            java.setProject(project);
            java.setFork(true);
            java.setFailonerror(true);
            java.setClassname("dss.vector.solutions.admin.SynchronziationManagerLauncher");
            java.setClasspath(new Path(project, classpath.toString()));
            java.init();

            java.createArg().setValue("-i" + context.getInstallProperties());
            java.createArg().setValue("-l" + Localizer.getLocale().toString());
            java.createArg().setValue("-a" + context.getApplication());

            java.createJvmarg().setValue("-Xms" + ManagerProperties.getProcessMemoryMin());
            java.createJvmarg().setValue("-Xmx" + ManagerProperties.getProcessMemoryMax());
            java.createJvmarg().setValue("-XX:PermSize=" + ManagerProperties.getProcessPermSize());

            int result = java.executeJava();

            if (result != 0)
            {
              throw new RuntimeException(Localizer.getMessage("SYNC_EXITED_WITH_ERROR"));
            }
          }
          catch (Exception e)
          {
            e.printStackTrace();
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
      thread.setUncaughtExceptionHandler(this);
      thread.setDaemon(true);
      thread.start();

      context.setProcessRunning(true);
    }
  }

  @Override
  public void uncaughtException(Thread thread, Throwable t)
  {
    t.printStackTrace();
  }
}