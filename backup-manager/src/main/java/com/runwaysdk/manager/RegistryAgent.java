package com.runwaysdk.manager;

import java.io.File;
import java.io.IOException;

import com.runwaysdk.dataaccess.ProgrammingErrorException;
import com.runwaysdk.dataaccess.io.BackupAgent;
import com.runwaysdk.dataaccess.io.FileReadException;
import com.runwaysdk.dataaccess.io.FileWriteException;
import com.runwaysdk.dataaccess.io.RestoreAgent;
import com.runwaysdk.util.FileIO;

public class RegistryAgent implements BackupAgent, RestoreAgent
{
  private static final String REG_PATH = "WEB-INF\\backup.reg";

  private String              appName;

  public RegistryAgent(String appName)
  {
    this.appName = appName;
  }

  public String getHKLMPath()
  {
    return BackupProperties.getRegistry32() + this.appName;
  }

  public String getHKLM64Path()
  {
    return BackupProperties.getRegistry64() + this.appName;
  }

  public String getRegPath()
  {
    return BackupProperties.getWebappDir() + appName + "\\" + REG_PATH;
  }

  public void preBackup()
  {
    String regPath = this.getRegPath();

    String command = BackupProperties.getExportCommand() + " " + this.getHKLMPath() + " " + regPath;
    String command64 = BackupProperties.getExportCommand() + " " + this.getHKLM64Path() + " " + regPath;
    File file = new File(regPath);

    try
    {
      FileIO.deleteFile(file);

      execWait(command64);

      // If the file exported, then it worked.
      if (file.exists())
      {
        return;
      }

      execWait(command);
    }
    catch (IOException e)
    {
      throw new FileWriteException("Couldn't export registry backup file " + regPath, file, e);
    }
  }

  public void postRestore()
  {
    String regPath = this.getRegPath();
    
    String cleanReg32 = BackupProperties.getDeleteCommand() + " " + this.getHKLMPath() + " /f";
    String cleanReg64 = BackupProperties.getDeleteCommand() + " " + this.getHKLM64Path() + " /f";
    
    String command = this.getImportCommand(regPath); 
    
    File file = new File(regPath);
    
    try
    {
      if (!file.exists())
      {
        // Can't import a file that's not here...
        Logger.error("The registry file [" + file.getAbsolutePath() + "] does not exist, so we can't import it. Skipping all registry tasks.");
        return;
      }
      
      execWait(cleanReg64);
      execWait(cleanReg32);
      
      execWait(command);
    }
    catch (IOException e)
    {
      throw new FileReadException("Couldn't import registry backup file " + regPath, file, e);
    }
  }
  
  private String getImportCommand(String regPath)
  {
    double version = Double.parseDouble(System.getProperty("os.version"));

    if (version >= 6.0)
    {
      return BackupProperties.getElevateImportCommand() + " " + regPath;
    }

    return BackupProperties.getImportCommand() + " " + regPath;
  }

  private void execWait(String command) throws IOException
  {
    Logger.info("Exec " + command);
    try
    {
      Process exec = Runtime.getRuntime().exec(command);
      // InputStream is = exec.getInputStream();
      // InputStream es = exec.getErrorStream();
      // StringWriter sw = new StringWriter();
      //      
      // int c;
      // while ( ( c = is.read() ) != -1)
      // sw.write(c);
      // System.out.println("out:" + sw.toString());
      //      
      // sw = new StringWriter();
      // while ( ( c = es.read() ) != -1)
      // sw.write(c);
      // System.out.println("err:" + sw.toString());

      exec.waitFor();
    }
    catch (InterruptedException e)
    {
      throw new ProgrammingErrorException("Command [" + command + "] interrupted.", e);
    }
  }

  public void preRestore()
  {
    // noop
  }

  public void postBackup()
  {
    // noop
  }
}
