/*******************************************************************************
 * Copyright (C) 2018 IVCC
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 ******************************************************************************/
package com.runwaysdk.manager;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

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
        file32 = new File(regPath32);
        ioExFile = file32;
        
        execWait(command32);
        
        if (fileExistsCheck(file32))
        {
          String content = org.apache.commons.io.FileUtils.readFileToString(file32, "UTF-16LE");
          String regx = "(?i)" + BackupProperties.getRegistry32().replace("\\", "\\\\");
          String replacement = BackupProperties.getRegistry64().replace("\\", "\\\\");
          content = content.replaceAll(regx, replacement);
          
          ioExFile = file64;
          IOUtils.write(content, new FileOutputStream(file64), "UTF-16LE");
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
        file64 = new File(regPath64);
        ioExFile = file64;
        execWait(command64);
        
        if (fileExistsCheck(file64))
        {
          String content = org.apache.commons.io.FileUtils.readFileToString(file64, "UTF-16LE");
          String regx = "(?i)" + BackupProperties.getRegistry64().replace("\\", "\\\\");
          String replacement = BackupProperties.getRegistry32().replace("\\", "\\\\");
          content = content.replaceAll(regx, replacement);
          
          ioExFile = file32;
          IOUtils.write(content, new FileOutputStream(file32), "UTF-16LE");
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
  
  private boolean fileExistsCheck(File f)
  {
    final int numWaits = 5;
    
    for (int i = 0; i < numWaits; ++i)
    {
      if (f.exists())
      {
        return true;
      }
      else
      {
        try {
          String msg = "Waiting 1 second for file [" + f.getAbsolutePath() + " to exist.";
          Logger.error(msg);
          System.out.println(msg);
          
          Thread.sleep(1000);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
    }
    
    return false;
  }

  private void execWait(String command) throws IOException
  {
    try
    {
      String msg = "Executing command [" + command + "].";
      System.out.println(msg);
      Logger.info(msg);
      
      Process exec = Runtime.getRuntime().exec(command);

      exec.waitFor();
      
      String err = IOUtils.toString(new BufferedInputStream(exec.getErrorStream()));
      String out = IOUtils.toString(new BufferedInputStream(exec.getInputStream()));
      
      String msg2 = "Standard output: [" + out + "]. Error output: [" + err + "].";
      
      if (err.length() > 0)
      {
        Logger.error(msg2);
        System.out.println(msg2);
      }
      else
      {
        Logger.info(msg2);
        System.out.println(msg2);
      }
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
