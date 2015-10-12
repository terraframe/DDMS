package dss.vector.solutions.util;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileFilter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.PrintStream;
import java.util.List;
import java.util.Properties;
import java.util.zip.ZipException;
import java.util.zip.ZipFile;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.io.Files;
import com.runwaysdk.business.Business;
import com.runwaysdk.business.BusinessQuery;
import com.runwaysdk.constants.DatabaseProperties;
import com.runwaysdk.constants.LocalProperties;
import com.runwaysdk.constants.ServerProperties;
import com.runwaysdk.constants.VaultInfo;
import com.runwaysdk.constants.VaultProperties;
import com.runwaysdk.dataaccess.database.Database;
import com.runwaysdk.dataaccess.io.Backup;
import com.runwaysdk.query.OIterator;
import com.runwaysdk.query.QueryFactory;
import com.runwaysdk.session.Request;
import com.runwaysdk.system.metadata.BackupReadException;
import com.runwaysdk.system.metadata.CorruptBackupException;
import com.runwaysdk.util.FileIO;

import dss.vector.solutions.form.business.FormBedNet;
import dss.vector.solutions.form.business.FormHousehold;
import dss.vector.solutions.form.business.FormPerson;
import dss.vector.solutions.form.business.FormSurvey;
import dss.vector.solutions.form.business.HumanBloodIndex;
import dss.vector.solutions.geo.generated.CollectionSite;
import dss.vector.solutions.geo.generated.Country;
import dss.vector.solutions.geo.generated.Earth;
import dss.vector.solutions.geo.generated.GeoEntity;
import dss.vector.solutions.geo.generated.GeoEntityEntityLabel;
import dss.vector.solutions.geo.generated.HealthFacility;
import dss.vector.solutions.geo.generated.SentinelSite;
import dss.vector.solutions.geo.generated.SprayZone;
import dss.vector.solutions.geo.generated.StockDepot;
import dss.vector.solutions.geo.generated.Surface;
import dss.vector.solutions.report.CacheDocumentManager;

/**
 * This importer will import production backup datasets into your dev environment.
 * 
 * Steps from ground 0:
 * 1) Create a new database
 * 2) Install the postgis extension
 * 3) Modify the database permissions to make sure our user is gonna be able to access it (otherwise the import just skips with no errors)
 * 4) Create a new launch for this java class with extra memory with a first argument that is the path to your zipped backup
 * 5) Build the project and then run that launch!
 * 
 * 
 * Troubleshooting steps (aka did the import happen but give no real error!?):
 * 1) find your data directory (show data_directory)
 * 2) find your log file (show log_filename, show log_directory)
 * 3) CHECK THE POSTGRES LOGS (mine is at /usr/local/var/postgres/9.3/server.log)
 * 
 * @author rrowlands
 */
public class BackupDevImporter
{
  private Logger logger = LoggerFactory.getLogger(BackupDevImporter.class);
  
  private File fRestoreUnzip;
  
  private File fDestSrcRoot = new File(LocalProperties.getSrcRoot() + File.separator + "backup");
  
  /**
   * When we copy the generated source over, because we're copying the entire package, its going to copy some source that we already have predefined in fresh installs.
   * The point of this array is to define what source we're going to delete after copying the package. Any name specified here will delete ALL the source (base, stub, controller, query etc)
   */
  private String[] duplicateSource = new String[]{
      // Predefined GeoEntities:
      CollectionSite.CLASS, Earth.CLASS, GeoEntity.CLASS, GeoEntityEntityLabel.CLASS, HealthFacility.CLASS, SentinelSite.CLASS,
      SprayZone.CLASS, StockDepot.CLASS, Surface.CLASS, Country.CLASS,
      
      // Predefined Forms:
      FormSurvey.CLASS, FormBedNet.CLASS, FormPerson.CLASS, FormHousehold.CLASS, HumanBloodIndex.CLASS
  };
  
  /**
   * @param args
   *   path The path to the backup file.
   * 
   * 1) Copy the generated source of universals and forms to some custom build directory
   * 2) Drop application tables
   * 3) Import database
   * 4) Create / manage db users
   * 5) Restore vault: copy the vault files to that same custom build directory
   */
  public static void main(String[] args)
  {
    String backupPath = args[0];
    
    File fBackup = new File(backupPath);
    if (!fBackup.isFile())
    {
      throw new RuntimeException("The supplied path [" + backupPath + "] is not a valid backup file.");
    }
    
    BackupDevImporter importer = new BackupDevImporter();
    importer.start(fBackup);
  }
  
  @Request
  public void start(File fBackup)
  {
    fRestoreUnzip = Files.createTempDir();
    
    try
    {
      this.unzipFile(fBackup);
      
      this.deleteCaches();
      
//      this.dropApplicationTabels();
      
      this.importSQL();
      
      this.patch();
      
      this.restoreWebapp();
      
      this.restoreVault();
    }
    finally
    {
      fRestoreUnzip.delete();
    }
  }
  
  private void unzipFile(File fBackup)
  {
    try
    {
      FileIO.write(new ZipFile(fBackup), this.fRestoreUnzip.getAbsolutePath());
      logger.info("Unzipped from [" + fBackup.getAbsolutePath() + "] to [" + this.fRestoreUnzip.getAbsolutePath() + "]");
    }
    catch (ZipException e)
    {
      CorruptBackupException cbe = new CorruptBackupException(e);
      cbe.setBackupName(fBackup.getName());
      throw cbe;
    }
    catch (IOException e)
    {
      BackupReadException bre = new BackupReadException(e);
      bre.setLocation(fBackup.getName());
      throw bre;
    }
  }
  
  private void deleteCaches()
  {
    // Permissions cache (very important!)
    String sPermissionCache = LocalProperties.getPermissionCacheDirectory();
    File fPermissionCache = new File(sPermissionCache);
    if (fPermissionCache.exists())
    {
      logger.info("Deleting permissions cache at [" + fPermissionCache.getAbsolutePath() + "].");
      fPermissionCache.delete();
    }
     
    // Object cache
//    String sGlobalCacheLoc = ServerProperties.getGlobalCacheFileLocation();
//    String sGlobalCacheName = ServerProperties.getGlobalCacheName();
//    File fGlobalCache = new File(sGlobalCacheLoc + File.separator + sGlobalCacheName);
//    if (fGlobalCache.exists())
//    {
//      logger.info("Deleting global cache at [" + fGlobalCache.getAbsolutePath() + "].");
//      fGlobalCache.delete();
//    }
    
    // Transaction cache (TODO : I don't think this code works)
    String sTransactionCacheLoc = ServerProperties.getTransactionCacheFileLocation();
    String sTransactionCacheName = ServerProperties.getTransactionCacheName();
    File fTransactionCache = new File(sTransactionCacheLoc + File.separator + sTransactionCacheName);
    if (fTransactionCache.exists())
    {
      logger.info("Deleting transaction cache at [" + fTransactionCache.getAbsolutePath() + "].");
      fTransactionCache.delete();
    }
    
    // Session cache (TODO : Untested)
    String sSessionCache = LocalProperties.getSessionCacheDirectory();
    File fSessionCache = new File(sSessionCache);
    if (fSessionCache.exists())
    {
      logger.info("Deleting permissions cache at [" + fSessionCache.getAbsolutePath() + "].");
      fSessionCache.delete();
    }
    
    // Report cache
    File fReportCache = new File(CacheDocumentManager.CACHE_DIR);
    if (fReportCache.exists())
    {
      logger.info("Deleting report cache at [" + fReportCache.getAbsolutePath() + "].");
      fReportCache.delete();
    }
  }
  
  private void dropApplicationTabels()
  {
    logger.info("Dropping tables");
    
    List<String> tableNames = null;
    try
    {
      tableNames = Database.getAllApplicationTables();
    }
    catch (Throwable e)
    {
      logger.warn("Exception while dropping tables. This can happen if the database has already been dropped, we're going to keep running as if nothing happened.", e);
    }

    if (tableNames != null)
    {
      Database.cascadeDropTables(tableNames);
    }
  }
  
  private void importSQL()
  {
    String dbbin = DatabaseProperties.getDatabaseBinDirectory();
    if (dbbin.equals("") || !new File(dbbin).exists())
    {
      throw new RuntimeException("Check the value of your databaseBinDirectory in database.properties");
    }
    
    FilenameFilter filter = new FilenameFilter()
    {
      @Override
      public boolean accept(File dir, String name)
      {
        return name.endsWith(".sql");
      }
    };

    File sqlDir = new File(this.fRestoreUnzip.getAbsoluteFile() + File.separator + Backup.SQL + File.separator);

    this.logger.info("Importing SQL from directory [" + sqlDir + "]");

    File[] sqlFiles = sqlDir.listFiles(filter);

    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    PrintStream ps = new PrintStream(baos);
    try
    {
      if (sqlFiles != null)
      {
        for (File sqlFile : sqlFiles)
        {
          Database.importFromSQL(sqlFile.getAbsolutePath(), ps);
        }
      }
      this.logger.info(baos.toString());
    }
    finally
    {
      ps.close();
      try
      {
        baos.close();
      }
      catch (IOException e)
      {
        throw new RuntimeException(e);
      }
    }
  }
  
  /**
   * Patch the database to the development version by importing all schemas.
   */
  private void patch()
  {
    // Run some SQL to reset the admin password back to ddms
//    Database.parseAndExecute("update ddms.users set password = 'yqp7HqQ0QbosXdkb+fxsXg+Sb3c=' where username = 'ddms'");
//    Database.parseAndExecute("update ddms.users set password = 'yqp7HqQ0QbosXdkb+fxsXg+Sb3c=' where username = 'ddms admin'");
  }
  
  private void restoreWebapp()
  {
    // Delete any source that may exist already
    try
    {
      FileIO.deleteFolderContent(this.fDestSrcRoot, null);
    }
    catch (IOException e1)
    {
      throw new RuntimeException(e1);
    }
    
    // The path to the webapps directory inside of the unzipped backup.
    File fBackupWebapp = new File(this.fRestoreUnzip, Backup.WEBAPP_DIR_NAME);
    
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
      FileIO.deleteFolderContent(fDestSrcRoot, fileFilter);
    }
    catch (IOException e)
    {
      this.logger.warn("This error happened while deleting the existing webapp content", e);
    }
    
    // Copy source of universals
    String geoGen = "/dss/vector/solutions/geo/generated";
    File geoGenDest = new File(fDestSrcRoot, geoGen);
    copyFolderIfExist(new File(fBackupWebapp, "WEB-INF/source/server/stub" + geoGen), geoGenDest, filenameFilter);
    copyFolderIfExist(new File(fBackupWebapp, "WEB-INF/source/server/base" + geoGen), geoGenDest, filenameFilter);
    copyFolderIfExist(new File(fBackupWebapp, "WEB-INF/source/client/base" + geoGen), geoGenDest, filenameFilter);
    copyFolderIfExist(new File(fBackupWebapp, "WEB-INF/source/client/stub" + geoGen), geoGenDest, filenameFilter);
    
    // and generated forms
    String formGen = "/dss/vector/solutions/form/business";
    File formGenDest = new File(fDestSrcRoot, formGen);
    copyFolderIfExist(new File(fBackupWebapp, "WEB-INF/source/server/stub" + formGen), formGenDest, filenameFilter);
    copyFolderIfExist(new File(fBackupWebapp, "WEB-INF/source/server/base" + formGen), formGenDest, filenameFilter);
    copyFolderIfExist(new File(fBackupWebapp, "WEB-INF/source/client/base" + formGen), formGenDest, filenameFilter);
    copyFolderIfExist(new File(fBackupWebapp, "WEB-INF/source/client/stub" + formGen), formGenDest, filenameFilter);
    
    // and generated form's relationships
    String formGenRel = "/dss/vector/solutions/form/tree";
    File formGenRelDest = new File(fDestSrcRoot, formGenRel);
    copyFolderIfExist(new File(fBackupWebapp, "WEB-INF/source/server/stub" + formGenRel), formGenRelDest, filenameFilter);
    copyFolderIfExist(new File(fBackupWebapp, "WEB-INF/source/server/base" + formGenRel), formGenRelDest, filenameFilter);
    copyFolderIfExist(new File(fBackupWebapp, "WEB-INF/source/client/base" + formGenRel), formGenRelDest, filenameFilter);
    copyFolderIfExist(new File(fBackupWebapp, "WEB-INF/source/client/stub" + formGenRel), formGenRelDest, filenameFilter);
    
    deleteDuplicateWebappSource();
  }
  
  private void copyFolderIfExist(File source, File destination, FilenameFilter filter)
  {
    if (source.exists())
    {
      FileIO.copyFolder(source, destination, filter);
    }
  }
  
  /**
   * Change any dev properties to work with the new restore
   */
  private void modifyProperties()
  {
    propagateProperty("domain", "terraframe.properties");
  }
  
  private void propagateProperty(String key, String bundle)
  {
    // The path to the webapps directory inside of the unzipped backup.
    File fBackupClasses = new File(this.fRestoreUnzip, Backup.WEBAPP_DIR_NAME + "/WEB-INF/classes");
    File fDevBundle = new File(fBackupClasses, bundle);
    
    // Read the property from the existing file
    Properties prop = new Properties();
    String newValue = null;
    
    
    // Write that property into the dev file
    try
    {
      String data = new String();
      for (String line : FileIO.readLines(fDevBundle))
      {
        if (!line.startsWith(key + '='))
        {
          data += line;
        }
        else
        {
          data += key + '=' + newValue;
        }
        data += "\n";
      }
      FileIO.write(fDevBundle, data);
    }
    catch (IOException e)
    {
      throw new RuntimeException(e);
    }
  }
  
  /**
   * When we copy the generated source over, because we're copying the entire package, its going to copy some source that we already have predefined in fresh installs.
   * The point of this method is to delete any source thats going to be duplicate.
   */
  private void deleteDuplicateWebappSource()
  {
    String sDevSrcBackup = fDestSrcRoot.getAbsolutePath();
    
    for (String sDup : duplicateSource)
    {
      String sName = sDup.replace(".", File.separator);
      String sDelPath = sDevSrcBackup + File.separator + sName;
      
      File fStub = new File(sDelPath + ".java");
      if (fStub.exists()) { fStub.delete(); }
      
      File fDTO = new File(sDelPath + "DTO.java");
      if (fDTO.exists()) { fDTO.delete(); }
      
      File fDTOBase = new File(sDelPath + "DTOBase.java");
      if (fDTOBase.exists()) { fDTOBase.delete(); }
      
      File fBase = new File(sDelPath + "Base.java");
      if (fBase.exists()) { fBase.delete(); }
      
      File fQuery = new File(sDelPath + "Query.java");
      if (fQuery.exists()) { fQuery.delete(); }
      
      File fQueryDTO = new File(sDelPath + "QueryDTO.java");
      if (fQueryDTO.exists()) { fQueryDTO.delete(); }
      
      File fController = new File(sDelPath + "Controller.java");
      if (fController.exists()) { fController.delete(); }
      
      File fControllerBase = new File(sDelPath + "ControllerBase.java");
      if (fControllerBase.exists()) { fControllerBase.delete(); }
    }
  }
  
  private void restoreVault()
  {
    QueryFactory qf = new QueryFactory();
    BusinessQuery vaultQ = qf.businessQuery(VaultInfo.CLASS);
    
    File backupVaultFileLocation = new File(this.fRestoreUnzip, Backup.VAULT_DIR_NAME);
    
    OIterator<Business> i = vaultQ.getIterator();
    try
    {
      for (Business vault : i)
      {
        String vaultName = vault.getValue(VaultInfo.VAULT_NAME);
        File fVPath = new File(VaultProperties.getPath(vaultName));
        File fVaultInsideBackup = new File(backupVaultFileLocation, vault.getId());
        
        if (!fVPath.exists())
        {
          fVPath.mkdirs();
        }
        
        if (fVPath.exists())
        {
          logger.debug("Restoring vault [" + vaultName + "] from [" + fVaultInsideBackup.getAbsolutePath() + "] to [" + fVPath.getAbsolutePath() + "].");
          
          FileIO.copyFolder(fVaultInsideBackup, fVPath);
        }
        else
        {
          logger.warn("Skipped restore of vault [" + vaultName + "] from backup [" + fVaultInsideBackup.getAbsolutePath() + "] to [" + fVPath.getAbsolutePath() + "] because the file in the backup does not exist.");
        }
      }
    }
    finally
    {
      i.close();
    }
  }
  
}
