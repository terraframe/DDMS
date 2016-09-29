package dss.vector.solutions.util;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.PrintStream;
import java.util.List;
import java.util.Properties;
import java.util.TreeSet;
import java.util.zip.ZipException;
import java.util.zip.ZipFile;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.io.Files;
import com.runwaysdk.business.Business;
import com.runwaysdk.business.BusinessQuery;
import com.runwaysdk.constants.DatabaseProperties;
import com.runwaysdk.constants.DeployProperties;
import com.runwaysdk.constants.LocalProperties;
import com.runwaysdk.constants.ServerProperties;
import com.runwaysdk.constants.VaultInfo;
import com.runwaysdk.constants.VaultProperties;
import com.runwaysdk.dataaccess.cache.globalcache.ehcache.CacheShutdown;
import com.runwaysdk.dataaccess.database.Database;
import com.runwaysdk.dataaccess.io.Backup;
import com.runwaysdk.dataaccess.io.Versioning;
import com.runwaysdk.query.OIterator;
import com.runwaysdk.query.QueryFactory;
import com.runwaysdk.session.Request;
import com.runwaysdk.system.Users;
import com.runwaysdk.system.UsersQuery;
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
 * @param args[0] fBackup The path to the backup zip file you want to import
 * @param args[1] fPatches TODO: Not implemented yet. The path to the runway metadata patches zip file. The zip exists in the runway-patcher project.
 *      If you do not specify this path, this program will skip patching after restoring the backup.
 * 
 * Steps from ground 0:
 * 1) Add your password to a ~/.pgpass file with host 127.0.0.1 (NOT LOCALHOST) that the psql import process will use when importing the sql files.
 * 2) Create user mdssdeploy
 * 3) Create a new database of name mdssdeploy with owner: mdssdeploy
 * 4) Modify your database search_path to ddms,public: ALTER DATABASE mdssdeploy SET search_path=ddms,public;
 *    alternatively: ALTER ROLE mdssdeploy SET search_path = ddms,public;
 * 5) Install the postgis extension: CREATE EXTENSION postgis;
 * 6) Build the project
 * 7) Create and run a java main program launch for this class with the necessary parameters.
 * 8) Compile and deploy the new source we just loaded from the backup to your tomcat server
 * 
 * 
 * Troubleshooting Problems:
 * Q: The import happened but gave no real error!?:
 * A: The importer can be picky about certain filenames. If your filename contains a bunch of strange characters like ()!@#$%
 *    try renaming it to something simple like ghana.zip.
 * A: This can happen if your user does not have permissions to access the database.
 *   1) find your data directory (show data_directory)
 *   2) find your log file (show log_filename, show log_directory)
 *   3) Check the postgres logs (mine is at /usr/local/var/postgres/9.3/server.log)
 * 
 * Q: The query builder pages don't render and some pages have some content missing.
 * A: After running this, you will see a new source directory: src/backup. This is the generated java files from the backup, which
 *    is required for the app to run properly. Run a deploy (step 7) and make sure your backup source matches the database.
 * 
 * @author rrowlands
 */
public class BackupDevImporter
{
  private Logger logger;
  
  private File fRestoreUnzip;
  
  private File fDestSrcRoot;
  
  private File fBackup;
  
  private File fPatches;
  
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
  
  private String databaseName;
  
  public BackupDevImporter(File fBackup, File fPatches)
  {
    logger = LoggerFactory.getLogger(BackupDevImporter.class);
    this.fBackup = fBackup;
    this.fPatches = fPatches;
  }
  
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
    try
    {
      File fBackup = new File(args[0]);
      
      File fPatches = null;
      if (args.length > 1)
      {
        fPatches = new File(args[1]);
      }
      
      BackupDevImporter importer = new BackupDevImporter(fBackup, fPatches);
      importer.start();
    }
    finally
    {
      CacheShutdown.shutdown();
    }
  }
  
  @Request
  public void start()
  {
    this.validate();
    
    logger.info("Restore will import [" + fBackup.getAbsolutePath() + "] into database with name [" + databaseName + "].");
    
    fRestoreUnzip = Files.createTempDir();
    
    try
    {
      this.unzipFile(this.fBackup);
      
      this.deleteCaches();
      
      this.dropApplicationTables();
      
      this.importSQL();
      
      this.restoreWebapp();
      
      this.restoreVault();
      
      this.patch();
    }
    finally
    {
      fRestoreUnzip.delete();
    }
    
    System.out.println("Restore is completed.");
  }
  
  private void validate()
  {
    if (LocalProperties.getSrcRoot() == null || !(new File(LocalProperties.getSrcRoot()).exists()))
    {
      throw new RuntimeException("Your local.properties [local.src.root] is misconfigured.");
    }
    else
    {
      fDestSrcRoot = new File(LocalProperties.getSrcRoot() + File.separator + "backup");
    }
    
    if (fBackup == null || !fBackup.isFile())
    {
      throw new RuntimeException("The first argument [" + fBackup + "] is not a valid path to a backup file.");
    }
    
    String dbbin = DatabaseProperties.getDatabaseBinDirectory();
    if (dbbin.equals("") || !new File(dbbin).exists())
    {
      throw new RuntimeException("Check the value of your databaseBinDirectory in database.properties");
    }
    
    databaseName = DatabaseProperties.getDatabaseName();
    if (databaseName == null || databaseName.equals(""))
    {
      throw new RuntimeException("Check the value of your database name in database.properties");
    }
    
//    if (fPatches == null || !fPatches.isFile())
//    {
//      String msg = "The second argument [" + backupPath + "] is not a valid path to a runway metadata patches zip. Your backup will not be patched.";
//      logger.warn(msg);
//    }
//    else
//    {
//      this.fPatches = fPatches;
//    }
  }
  
  private void unzipFile(File fBackup)
  {
    try
    {
      logger.info("Unzipping from [" + fBackup.getAbsolutePath() + "] to [" + this.fRestoreUnzip.getAbsolutePath() + "]");
      FileIO.write(new ZipFile(fBackup), this.fRestoreUnzip.getAbsolutePath());
    }
    catch (ZipException e)
    {
      throw new RuntimeException(e);
    }
    catch (IOException e)
    {
      throw new RuntimeException(e);
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
    else
    {
      logger.info("Skipping delete of permissions cache at [" + fPermissionCache.getAbsolutePath() + "].");
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
    else
    {
      logger.info("Skipping delete of transaction cache at [" + fTransactionCache.getAbsolutePath() + "].");
    }
    
    // Session cache (TODO : Untested)
    String sSessionCache = LocalProperties.getSessionCacheDirectory();
    File fSessionCache = new File(sSessionCache);
    if (fSessionCache.exists())
    {
      logger.info("Deleting session cache at [" + fSessionCache.getAbsolutePath() + "].");
      fSessionCache.delete();
    }
    else
    {
      logger.info("Skipping delete of session cache at [" + fSessionCache.getAbsolutePath() + "].");
    }
    
    // Report cache
    File fReportCache = new File(CacheDocumentManager.CACHE_DIR);
    if (fReportCache.exists())
    {
      logger.info("Deleting report cache at [" + fReportCache.getAbsolutePath() + "].");
      fReportCache.delete();
    }
    else
    {
      logger.info("Skipping delete of report cache at [" + fReportCache.getAbsolutePath() + "].");
    }
  }
  
  private void dropApplicationTables()
  {
    new TreeSet<String>();
    
    if (!Database.tableExists("md_class"))
    {
      logger.info("Skipping table drop.");
      return;
    }
    
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
    FilenameFilter filter = new FilenameFilter()
    {
      @Override
      public boolean accept(File dir, String name)
      {
        return name.endsWith(".sql");
      }
    };

    File sqlDir = new File(this.fRestoreUnzip.getAbsoluteFile() + File.separator + Backup.SQL + File.separator);

    File[] sqlFiles = sqlDir.listFiles(filter);

    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    PrintStream ps = new PrintStream(baos);
    
    ByteArrayOutputStream errBaos = new ByteArrayOutputStream();
    PrintStream errPs = new PrintStream(errBaos);
    try
    {
      if (sqlFiles != null)
      {
        for (File sqlFile : sqlFiles)
        {
          this.logger.info("Importing SQL file [" + sqlFile.getAbsolutePath() + "]");
          Database.importFromSQL(sqlFile.getAbsolutePath(), ps, errPs);
        }
      }
      else
      {
        throw new RuntimeException("Unable to find SQL files at path [" + sqlDir.getAbsolutePath() + "]. Does this backup have SQL files in it?");
      }
      String normalOut = baos.toString();
      if (normalOut.length() > 0)
      {
        this.logger.info(normalOut);
      }
      String errOut = errBaos.toString();
      if (errOut.length() > 0)
      {
        String msg = "psql produced this error output: \n" + errOut;
        if (errOut.contains("no password supplied"))
        {
          throw new RuntimeException("No database password supplied, check your pgpass file. " + msg);
        }
        this.logger.info(msg);
      }
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
    UsersQuery uq = new UsersQuery(new QueryFactory());
    uq.WHERE(uq.getUsername().EQ("ddms"));
    OIterator<? extends Users> it = uq.getIterator();
    try
    {
      if (it.hasNext())
      {
        logger.info("Setting admin user [ddms] password to 'ddms'");
        Database.parseAndExecute("update ddms.users set password = 'yqp7HqQ0QbosXdkb+fxsXg+Sb3c=' where username = 'ddms'");
      }
      else
      {
        logger.info("Skipping reset of admin user [ddms] password.");
      }
    }
    finally
    {
      it.close();
    }
    UsersQuery uq2 = new UsersQuery(new QueryFactory());
    uq2.WHERE(uq2.getUsername().EQ("ddms admin"));
    OIterator<? extends Users> it2 = uq2.getIterator();
    try
    {
      if (it.hasNext())
      {
        logger.info("Setting admin user [ddms admin] password to 'ddms'");
        Database.parseAndExecute("update ddms.users set password = 'yqp7HqQ0QbosXdkb+fxsXg+Sb3c=' where username = 'ddms admin'");
      }
      else
      {
        logger.info("Skipping reset of admin user [ddms admin] password.");
      }
    }
    finally
    {
      it2.close();
    }
    
    if (this.fPatches != null)
    {
      // Patch Runway
      
      
      // Import the metadata
      File metadata = new File(LocalProperties.getSrcRoot() + "/../doc/individual");
  
      if (metadata.exists() && metadata.isDirectory())
      {
        logger.info("Importing metadata schema files from [" + metadata.getAbsolutePath() + "].");
        Versioning.main(new String[] { metadata.getAbsolutePath() });
      }
      else
      {
        logger.error("Metadata schema files were not found at [" + metadata.getAbsolutePath() + "]. Unable to import schemas, your database is not patched.");
      }
    }
  }
  
  private void restoreWebapp()
  {
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
    
    // Delete any source that may exist already
    try
    {
      FileIO.deleteFolderContent(fDestSrcRoot, null);
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
    
    modifyProperties();
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
    logger.warn("TODO: Write this code that changes properties to match that of the restore. One such important example: domain.");
//    propagateProperty("domain", "terraframe.properties");
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
