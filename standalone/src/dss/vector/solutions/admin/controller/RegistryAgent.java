package dss.vector.solutions.admin.controller;

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
  private static final String HKLM = "HKEY_LOCAL_MACHINE\\SOFTWARE\\DDMS\\Components";
  private static final String HKLM_64 = "HKEY_LOCAL_MACHINE\\SOFTWARE\\Wow6432Node\\DDMS\\Components";
  private static final String REG_PATH = "C:\\MDSS\\tomcat6\\webapps\\DDMS\\WEB-INF\\backup.reg";

  public RegistryAgent()
  {
  }

  public void preBackup()
  {
    String command = "reg export " + HKLM + " " + REG_PATH;
    String command64 = "reg export " + HKLM_64 + " " + REG_PATH;
    File file = new File(REG_PATH);
    
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
      throw new FileWriteException("Couldn't export registry backup file " + REG_PATH, file, e);
    }
  }

  public void postRestore()
  {
    String command = "reg import " + REG_PATH;
    File file = new File(REG_PATH);
    
    try
    {
      if (!file.exists())
      {
        // Can't import a file that's not here...
        return;
      }
      
      execWait(command);
      
      FileIO.deleteFile(file);
    }
    catch (IOException e)
    {
      throw new FileReadException("Couldn't import registry backup file " + REG_PATH, file, e);
    }
  }
  
  private void execWait(String command) throws IOException
  {
    System.out.println("Exec " + command);
    try
    {
      Process exec = Runtime.getRuntime().exec(command);
//      InputStream is = exec.getInputStream();
//      InputStream es = exec.getErrorStream();
//      StringWriter sw = new StringWriter();
//      
//      int c;
//      while ( ( c = is.read() ) != -1)
//        sw.write(c);
//      System.out.println("out:" + sw.toString());
//      
//      sw = new StringWriter();
//      while ( ( c = es.read() ) != -1)
//        sw.write(c);
//      System.out.println("err:" + sw.toString());
      
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
