package dss.vector.solutions.report;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;

import net.jawr.web.resource.bundle.IOUtils;
import net.lingala.zip4j.core.ZipFile;
import net.lingala.zip4j.exception.ZipException;
import net.lingala.zip4j.model.ZipParameters;
import net.lingala.zip4j.util.Zip4jConstants;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.io.Files;
import com.runwaysdk.dataaccess.io.FileReadException;
import com.runwaysdk.generation.loader.Reloadable;
import com.runwaysdk.query.OIterator;
import com.runwaysdk.query.QueryFactory;
import com.runwaysdk.system.VaultFile;
import com.runwaysdk.system.VaultFileQuery;
import com.runwaysdk.util.FileIO;
import com.runwaysdk.vault.VaultFileDAO;

/**
 * This class is responsible for merging uploaded report resource documents.
 */
public class ReportResourceManager implements Reloadable
{
  private static final String VAULT_RESOURCE_BUNDLE = "__BIRT_GLOBAL_RESOURCE_BUNDLE_";
  
  private VaultFileDAO resources;
  
  private File tempDir;
  
  private Logger logger = LoggerFactory.getLogger(ReportResourceManager.class);
  
  /**
   * We follow the singleton pattern.
   */
  private static ReportResourceManager INSTANCE;
  
  private ReportResourceManager()
  {
    VaultFileQuery vfq = new VaultFileQuery(new QueryFactory());
    vfq.WHERE(vfq.getFileName().EQ(VAULT_RESOURCE_BUNDLE).AND(vfq.getFileExtension().EQ("zip")));
    OIterator<? extends VaultFile> it = vfq.getIterator();
    if (!it.hasNext())
    {
      resources = VaultFileDAO.newInstance();
      resources.setFileName(VAULT_RESOURCE_BUNDLE);
      resources.setExtension("zip");
      resources.setSize(0); // Not sure why its required if you're allowed to just set it to 0 and walk away
      resources.apply();
    }
    else
    {
      resources = (VaultFileDAO) VaultFileDAO.get(it.next().getId());
    }
    it.close();
  }
  
  public static synchronized ReportResourceManager getInstance()
  {
    if (INSTANCE == null)
    {
      INSTANCE = new ReportResourceManager();
    }
    
    return INSTANCE;
  }
  
  /**
   * @return A reference to the global resources directory, containing all uploaded BIRT resources.
   */
  public synchronized File getBIRTResources()
  {
    try
    {
      if (tempDir == null || !tempDir.exists())
      {
        tempDir = Files.createTempDir();
        
        ZipFile zip4j = new ZipFile(resources.getFile());
        zip4j.extractAll(tempDir.getAbsolutePath());
        
        logger.info("New BIRT resources temp directory created at [" + tempDir.getAbsolutePath() + "].");
      }
      
      return tempDir;
    }
    catch (ZipException e)
    {
      throw new ReportResourcesException(e);
    }
  }
  
  /**
   * Imports a resource into the BIRT resource bundle collection so that it may be used by BIRT. If the upload is a zip, it will be unzipped.
   */
  public void uploadResource(InputStream IS, String nameOfResource)
  {
    try
    {
      // Ensure that our temp directory contains the current global resource bundle.
      File resourcesF = getBIRTResources();
      
      // Write what they uploaded to our temp directory
      if (nameOfResource.endsWith("zip"))
      {
        File uploadedF = new File(resourcesF, "uploaded_resources_" + Integer.toString(((int)(new Date().getTime()/100000))));
        FileOutputStream uploadedFOS = new FileOutputStream(uploadedF);
        IOUtils.copy(IS, uploadedFOS);
        uploadedFOS.flush();
        uploadedFOS.close();
        new ZipFile(uploadedF).extractAll(resourcesF.getAbsolutePath());
        FileIO.deleteFile(uploadedF);
      }
      else
      {
        FileOutputStream uploadedFOS = new FileOutputStream(new File(resourcesF, nameOfResource));
        IOUtils.copy(IS, uploadedFOS);
      }
      
      // Zip the temp directory
      File resourcesZipF = new File(resourcesF, "zipped_birt_res_" + Integer.toString(((int)(new Date().getTime()/100000))));
      ZipFile resourcesZip = new ZipFile(resourcesZipF);
      ZipParameters parameters = new ZipParameters();
      parameters.setCompressionMethod(Zip4jConstants.COMP_DEFLATE);
      parameters.setCompressionLevel(Zip4jConstants.DEFLATE_LEVEL_NORMAL);
      parameters.setIncludeRootFolder(false);
      resourcesZip.addFolder(resourcesF, parameters);
      
      // Save the new zip into the vault
      VaultFile.get(resources.getId()).appLock();
      resources.putFile(new FileInputStream(resourcesZipF));
      resources.apply();
      
      FileIO.deleteFile(resourcesZipF);
    }
    catch (ZipException e)
    {
      throw new FileReadException(new File(nameOfResource), e);
    }
    catch (IOException e)
    {
      throw new ReportResourcesException(e);
    }
  }
}
