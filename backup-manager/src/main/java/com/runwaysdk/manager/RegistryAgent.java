package com.runwaysdk.manager;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.commons.io.IOUtils;

import com.runwaysdk.dataaccess.ProgrammingErrorException;
import com.runwaysdk.dataaccess.io.BackupAgent;
import com.runwaysdk.dataaccess.io.FileReadException;
import com.runwaysdk.dataaccess.io.FileWriteException;
import com.runwaysdk.dataaccess.io.RestoreAgent;
import com.runwaysdk.system.metadata.CorruptBackupException;
import com.runwaysdk.system.metadata.CreateBackupException;
import com.runwaysdk.util.FileIO;

public class RegistryAgent implements BackupAgent, RestoreAgent
{
  private static final String REG_PATH = "WEB-INF\\backup";

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

  public String getRegPath(String bitness)
  {
    return BackupProperties.getWebappDir() + appName + "\\" + REG_PATH + "_" + bitness + ".reg";
  }

  public void preBackup()
  {
    File ioExFile = null;
    String jvmBitness = System.getProperty("sun.arch.data.model");
    
    String regPath32 = this.getRegPath("32");
    String regPath64 = this.getRegPath("64");

    String command32 = BackupProperties.getExportCommand() + " " + this.getHKLMPath() + " " + regPath32;
    String command64 = BackupProperties.getExportCommand() + " " + this.getHKLM64Path() + " " + regPath64;

    try
    {
      File file32 = new File(regPath32);
      File file64 = new File(regPath64);
      
      ioExFile = file32;
      FileIO.deleteFile(file32);
      ioExFile = file64;
      FileIO.deleteFile(file64);
      
      // First we want to export either the 32 or the 64 bit registry keys.
      // Second we copy those to the other bitness file, swapping the bitness as we do it
      
      if (jvmBitness.equals("32"))
      {
        ioExFile = file32;
        execWait(command32);
        
        if (file32.exists())
        {
          String content = IOUtils.toString(new FileInputStream(file32));
          content = content.toLowerCase().replaceAll(BackupProperties.getRegistry32().toLowerCase(), BackupProperties.getRegistry64());
          ioExFile = file64;
          IOUtils.write(content, new FileOutputStream(file64));
        }
        else
        {
          String msg = "We ran registry command [" + command32 + "] and a registry file was not created at [" + file32.getAbsolutePath() + "]. This backup is corrupt.";
          Logger.error(msg);
          CreateBackupException ex = new CreateBackupException(msg);
          ex.setLocation(file32.getAbsolutePath());
          throw ex;
        }
      }
      else
      {
        ioExFile = file64;
        execWait(command64);
        
        if (file64.exists())
        {
          String content = IOUtils.toString(new FileInputStream(file64));
          String regx = BackupProperties.getRegistry64().toLowerCase().replace("\\", "\\\\");
          String replacement = BackupProperties.getRegistry32();
          content = content.toLowerCase().replaceAll(regx, replacement);
          ioExFile = file32;
          IOUtils.write(content, new FileOutputStream(file32));
        }
        else
        {
          String msg = "We ran registry command [" + command64 + "] and a registry file was not created at [" + file64.getAbsolutePath() + "]. This backup is corrupt.";
          Logger.error(msg);
          CreateBackupException ex = new CreateBackupException(msg);
          ex.setLocation(file64.getAbsolutePath());
          throw ex;
        }
      }
    }
    catch (IOException e)
    {
      throw new FileWriteException("Couldn't export registry backup file.", ioExFile, e);
    }
  }

  public void postRestore()
  {
    String jvmBitness = System.getProperty("sun.arch.data.model");
    
    String regPath = this.getRegPath(jvmBitness);
    String command = this.getImportCommand(regPath);
    File file = new File(regPath);
    
    if (!file.exists())
    {
      String oldRegPath = BackupProperties.getWebappDir() + appName + "\\" + REG_PATH + ".reg";
      File backwardsCompatibility = new File(oldRegPath);
      
      if (backwardsCompatibility.exists())
      {
        command = this.getImportCommand(oldRegPath);
      }
      else
      {
        String msg = "The registry file [" + file.getAbsolutePath() + "] does not exist, so we can't import it. The restore has failed.";
        Logger.error(msg);
        CorruptBackupException ex = new CorruptBackupException(msg);
        ex.setBackupName(this.appName);
        throw ex;
      }
    }
    
    try
    {
      if (jvmBitness.equals("32"))
      {
        String cleanReg32 = BackupProperties.getDeleteCommand() + " " + this.getHKLMPath() + " /f";
        
        execWait(cleanReg32);
        execWait(command);
      }
      else
      {
        String cleanReg64 = BackupProperties.getDeleteCommand() + " " + this.getHKLM64Path() + " /f";

        execWait(cleanReg64);
        execWait(command);
      }
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
