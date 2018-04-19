package com.runwaysdk.manager;

import java.io.File;
import java.io.FileFilter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;

import org.apache.commons.io.FileUtils;

import com.runwaysdk.constants.DatabaseProperties;
import com.runwaysdk.constants.DeployProperties;
import com.runwaysdk.dataaccess.ProgrammingErrorException;
import com.runwaysdk.dataaccess.database.Database;
import com.runwaysdk.dataaccess.database.general.ProcessReader;
import com.runwaysdk.dataaccess.io.BackupAgent;
import com.runwaysdk.dataaccess.io.RestoreAgent;
import com.runwaysdk.system.metadata.BackupReadException;
import com.runwaysdk.util.FileIO;

public class ODKAgent implements BackupAgent, RestoreAgent
{
  private String appName;

  public ODKAgent(String appName)
  {
    this.appName = appName;
  }

  public void preBackup()
  {
    this.backupDatabase();

    this.backupWebapp();
  }

  private void backupDatabase()
  {
    // Make the temp sql directory
    File directory = new File(BackupProperties.getWebappDir() + appName + File.separator + "Backup" + File.separator + "sql" + File.separator);
    directory.mkdirs();

    File file = new File(directory, this.appName + "Mobile" + ".sql");

    String databaseBinDirectory = DatabaseProperties.getDatabaseBinDirectory();

    String dbDumpTool = "" + databaseBinDirectory + File.separator + DatabaseProperties.getDataDumpExecutable() + "";

    ArrayList<String> argList = new ArrayList<String>();
    argList.add(dbDumpTool);
    argList.add("-U");
    argList.add(this.appName.toLowerCase() + "_mobile");
    argList.add("-h");
    argList.add("127.0.0.1");
    argList.add("-p");
    argList.add(Integer.toString(DatabaseProperties.getPort()));

    // -D is for 8.3, it is not needed for 8.4
    // argList.add("-D");
    argList.add("-b");

    // pg dump compressed format
    // argList.add("--format");
    // argList.add("c");

    argList.add("-f");
    argList.add("" + file.getAbsolutePath() + "");

    // thanks to the ddms schema, we no longer need to manually specify every
    // table name
    argList.add("-n");
    argList.add(this.appName.toLowerCase());

    argList.add("odk");

    ProcessBuilder pb = new ProcessBuilder(argList);

    try
    {
      ProcessReader reader = new ProcessReader(pb);
      reader.start();
    }
    catch (Exception e)
    {
      throw new ProgrammingErrorException(e);
    }
  }

  private void backupWebapp()
  {
    File directory = new File(BackupProperties.getWebappDir() + appName + File.separator + "Backup" + File.separator + "webapp" + File.separator);
    directory.mkdirs();

    String webappRootDir = DeployProperties.getDeployRoot() + File.separator + "webapps";
    String app = this.appName + "Mobile";

    File webappRootFile = new File(webappRootDir, app);

    FilenameFilter filenameFilter = new FilenameFilter()
    {
      public boolean accept(File dir, String name)
      {
        if (name.endsWith(".svn") || dir.getName().startsWith("."))
        {
          return false;
        }

        return true;
      }
    };

    FileIO.copyFolder(webappRootFile, directory, filenameFilter);
  }

  public void postRestore()
  {
    this.dropSchema();

    this.restoreDatabase();

    this.restoreWebapp();

    File directory = new File(BackupProperties.getWebappDir() + appName + File.separator + "Backup" + File.separator);

    try
    {
      FileUtils.deleteDirectory(directory);
    }
    catch (IOException e)
    {
    }
  }

  private void dropSchema()
  {
    String databaseBinDirectory = DatabaseProperties.getDatabaseBinDirectory();

    String dbImportTool = "" + databaseBinDirectory + File.separator + "psql" + "";

    ArrayList<String> argList = new ArrayList<String>();
    argList.add(dbImportTool);
    argList.add("-h");
    argList.add("127.0.0.1");
    argList.add("-p");
    argList.add(Integer.toString(DatabaseProperties.getPort()));
    argList.add("-U");
    argList.add(this.appName.toLowerCase() + "_mobile");
    argList.add("-d");
    argList.add("odk");
    argList.add("-c");
    argList.add("\"DROP SCHEMA IF EXISTS " + this.appName.toLowerCase() + ";\"");
    argList.add("--no-password");
    argList.add("--quiet");

    ProcessBuilder pb = new ProcessBuilder(argList);

    try
    {
      ProcessReader reader = new ProcessReader(pb);
      reader.start();
    }
    catch (Exception e)
    {
      throw new ProgrammingErrorException(e);
    }
  }

  private void restoreWebapp()
  {
    File directory = new File(BackupProperties.getWebappDir() + appName + File.separator + "Backup" + File.separator + "webapp" + File.separator);

    if (directory.exists())
    {

      String webappRootDir = DeployProperties.getDeployRoot() + File.separator + "webapps";
      String app = this.appName + "Mobile";

      File webappRootFile = new File(webappRootDir, app);

      FilenameFilter filenameFilter = new FilenameFilter()
      {
        public boolean accept(File dir, String name)
        {
          if (name.endsWith(".svn") || dir.getName().startsWith("."))
          {
            return false;
          }

          return true;
        }
      };

      FileFilter fileFilter = new FileFilter()
      {
        public boolean accept(File pathname)
        {
          return true;
        }
      };

      try
      {
        FileIO.deleteFolderContent(webappRootFile, fileFilter);
      }
      catch (IOException e)
      {
        // Some files might have already been deleted. We will copy anyway, as
        // the
        // files should overwrite.
      }

      boolean success = FileIO.copyFolder(directory, webappRootFile, filenameFilter);
      if (!success)
      {
        // TODO : This success stuff is garbage, I want the actual IOException
        // why
        // swallow it
        BackupReadException bre = new BackupReadException();
        bre.setLocation(webappRootFile.getAbsolutePath());
        throw bre;
      }
    }
  }

  private void restoreDatabase()
  {
    // Make the temp sql directory
    File directory = new File(BackupProperties.getWebappDir() + appName + File.separator + "Backup" + File.separator + "sql" + File.separator);
    File file = new File(directory, this.appName + "Mobile" + ".sql");

    if (file.exists())
    {
      String databaseBinDirectory = DatabaseProperties.getDatabaseBinDirectory();

      String dbImportTool = "" + databaseBinDirectory + File.separator + "psql" + "";

      ArrayList<String> argList = new ArrayList<String>();
      argList.add(dbImportTool);
      argList.add("-h");
      argList.add("127.0.0.1");
      argList.add("-p");
      argList.add(Integer.toString(DatabaseProperties.getPort()));
      argList.add("-U");
      argList.add(this.appName.toLowerCase() + "_mobile");
      argList.add("-d");
      argList.add("odk");
      argList.add("--file");
      argList.add(file.getAbsolutePath());
      argList.add("--no-password");
      argList.add("--quiet");

      ProcessBuilder pb = new ProcessBuilder(argList);

      try
      {
        ProcessReader reader = new ProcessReader(pb);
        reader.start();
      }
      catch (Exception e)
      {
        throw new ProgrammingErrorException(e);
      }
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
