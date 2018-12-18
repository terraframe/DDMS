package dss.vector.solutions.manager.action;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.eclipse.jface.action.Action;

import dss.vector.solutions.manager.Localizer;
import dss.vector.solutions.manager.Logger;
import dss.vector.solutions.manager.server.IServer;

public class LogDebugAction extends Action
{
  private ZipOutputStream out;
  
  private File desktop;
  
  private File zip;
  
  private File mdss;
  
  private IServer server;

  public LogDebugAction(IServer server)
  {
	super(Localizer.getMessage("LOGDEBUG"));
	
	this.server = server;
  }

  public void run()
  {
    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH-mm-ss");  
    LocalDateTime now = LocalDateTime.now();  
    String filename = "ddms-logdebug-" + dtf.format(now) + ".zip";
    
    desktop = javax.swing.filechooser.FileSystemView.getFileSystemView().getHomeDirectory();
    zip = new File(desktop, filename);
    mdss = new File("C:\\MDSS");
    
    try
    {
      try
      {
        out = new ZipOutputStream(new FileOutputStream(zip));
        
        addDirectory(new File(mdss, "logs"));
        addDirectory(new File(mdss, "tomcat\\logs"));
        addPostgresLogs();
        addThreadDump();
        addConnectionQuery();
        addEnvInfo();
      }
      finally
      {
        if (out != null)
        {
          out.close();
        }
      }
    }
    catch (Throwable t)
    {
      Logger.error("Error happened while trying to create zip [" + zip.getAbsolutePath() + "].", t);
    }
  }
  
  private void addEnvInfo()
  {
    try
    {
      if (this.server != null)
      {
        String envInfo = this.server.getEnvInfo();
        
        if (envInfo != null)
        {
          byte[] envInfoBytes = envInfo.getBytes();
          
          addBytes("server-env-info.json", envInfoBytes);
        }
      }
    }
    catch (Throwable e)
    {
      handleException("server-env-info.json", "Error happened while trying to write the server's env info to zip.", e);
    }
  }
  
  private void addConnectionQuery()
  {
    try
    {
      Runtime rt = Runtime.getRuntime();
      String[] commands = {
          getPostgresDir() + "\\bin\\psql.exe",
          "-p", "5444", "-h", "127.0.0.1",
          "-U", "postgres", "-d", "postgres",
          "-c", "select * from pg_stat_activity"
      };
      String resp = executeProcess(rt, commands);
      
      addBytes("db-connections.txt", resp.getBytes());
    }
    catch(Throwable t)
    {
      handleException("db-connections.txt", "Error happened while trying to write the connection query to zip.", t);
    }
  }

  private String executeProcess(Runtime rt, String[] commands)
  {
    StringBuilder sb = new StringBuilder();
    
    Thread t = new Thread() {
      public void run() {
        try
        {
          Process proc = rt.exec(commands);
          
          BufferedReader stdInput = new BufferedReader(new InputStreamReader(proc.getInputStream()));
          
          BufferedReader stdError = new BufferedReader(new InputStreamReader(proc.getErrorStream()));
          
          // read the output from the command
          sb.append("Standard Out:\n");
          String s = null;
          while ((s = stdInput.readLine()) != null)
          {
            sb.append(s + "\n");
          }
          
          // read any errors from the attempted command
          sb.append("Standard Error:\n");
          while ((s = stdError.readLine()) != null)
          {
            sb.append(s + "\n");
          }
        }
        catch (Throwable t)
        {
          Logger.error("Error occured while querying for db connections", t);
        }
      }
    };
    t.start();
    
    try
    {
      t.join(10000);
    }
    catch (InterruptedException e)
    {
      Logger.error("Interrupted when waiting for db connection query", e);
    }
    
    return sb.toString();
  }
  
  private void addThreadDump()
  {
    try
    {
      final StringBuilder managerDump = new StringBuilder();
      final ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();
      final ThreadInfo[] threadInfos = threadMXBean.getThreadInfo(threadMXBean.getAllThreadIds(), 100);
      for (ThreadInfo threadInfo : threadInfos) {
          managerDump.append('"');
          managerDump.append(threadInfo.getThreadName());
          managerDump.append("\" ");
          final Thread.State state = threadInfo.getThreadState();
          managerDump.append("\n   java.lang.Thread.State: ");
          managerDump.append(state);
          final StackTraceElement[] stackTraceElements = threadInfo.getStackTrace();
          for (final StackTraceElement stackTraceElement : stackTraceElements) {
              managerDump.append("\n        at ");
              managerDump.append(stackTraceElement);
          }
          managerDump.append("\n\n");
      }
      
      byte[] managerData = managerDump.toString().getBytes();
    
      addBytes("manager-stack-dump.txt", managerData);
    }
    catch (Throwable e)
    {
      handleException("manager-stack-dump.txt", "Error happened while trying to write the manager's stack dump to zip.", e);
    }
    
    try
    {
      if (this.server != null)
      {
        String serverDump = this.server.getStackDump();
        
        if (serverDump != null)
        {
          byte[] serverData = serverDump.toString().getBytes();
          
          addBytes("server-stack-dump.txt", serverData);
        }
      }
    }
    catch (Throwable e)
    {
      handleException("server-stack-dump.txt", "Error happened while trying to write the server's stack dump to zip.", e);
    }
  }
  
  private void addPostgresLogs()
  {
    try
    {
      addDirectory(new File(getPostgresDir(), "data\\pg_log"));
    }
    catch (Throwable t)
    {
      handleException("postgres-logs.txt", "Error happened while trying to add postgres logs to zip.", t);
    }
  }
  
  private File getPostgresDir()
  {
    File postgres = new File(mdss, "PostgreSql");
    
    File[] versions = postgres.listFiles();
    
    String max = null;
    for (File version : versions)
    {
      if (max == null || Integer.valueOf(version.getName()) > Integer.valueOf(max))
      {
        max = version.getName();
      }
    }
    
    return new File(postgres, max);
  }
  
  private void addDirectory(File dir)
  {
    try
    {
      Path pp = dir.toPath();
      Files.walk(pp)
        .filter(path -> !Files.isDirectory(path))
        .forEach(path -> {
          addFile(path.toFile());
        });
    }
    catch (Throwable e)
    {
      handleException("add-directory-error.txt", "Error happened while trying to add directory [" + dir.getAbsolutePath() + "] to zip.", e);
    }
  }
  
  private void addFile(File add)
  {
    try
    {
      addBytes(mdss.toPath().relativize(add.toPath()).toString(), Files.readAllBytes(add.toPath()));
    }
    catch (IOException e)
    {
      String name = "add-file-error.txt";
      if (add != null && add.getName() != null)
      {
        name = "add-file-" + add.getName() + "-error.txt";
      }
      
      handleException(name, "Error happened while trying to add file [" + add.getAbsolutePath() + "] to zip.", e);
    }
  }
  
  private void addBytes(String path, byte[] bytes)
  {
    try
    {
      ZipEntry e = new ZipEntry(path);
      out.putNextEntry(e);
      
      out.write(bytes, 0, bytes.length);
      out.closeEntry();
    }
    catch (IOException e)
    {
      handleException("add-bytes-error.txt", "Error happened while trying to add bytes [" + path + "] to zip.", e);
    }
  }
  
  private void handleException(String path, String msg, Throwable t)
  {
    try
    {
      StringWriter sw = new StringWriter();
      PrintWriter pw = new PrintWriter(sw);
      t.printStackTrace(pw);
      String sStackTrace = sw.toString(); // stack trace as a string
      
      addBytes(path, sStackTrace.getBytes());
    }
    catch (Throwable t2)
    {
      Logger.error(msg, t);
    }
  }
}
