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
package dss.vector.solutions.report;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;

import javax.xml.bind.DatatypeConverter;

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

import dss.vector.solutions.FileRequiredException;
import net.jawr.web.resource.bundle.IOUtils;
import net.lingala.zip4j.core.ZipFile;
import net.lingala.zip4j.exception.ZipException;
import net.lingala.zip4j.model.ZipParameters;
import net.lingala.zip4j.util.Zip4jConstants;

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
   * This is the hex string for an "empty" zip file. I say empty in quotes because it contains a folder (named __VAULT_RESOURCES_DEFAULT_FOLDER__) because its impossible to create a truly empty zip file. This should suit our needs though.
   */
  private static final String emptyZipHex = "504B03040A0000000000995AD146000000000000000000000000230010005F5F5641554C545F5245534F55524345535F44454641554C545F464F4C4445525F5F2F55580C0072AC815572AC8155F5011400504B010215030A0000000000995AD14600000000000000000000000023000C000000000000000040ED41000000005F5F5641554C545F5245534F55524345535F44454641554C545F464F4C4445525F5F2F5558080072AC815572AC8155504B050600000000010001005D000000510000000000";
  
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
      resources.putFile(DatatypeConverter.parseHexBinary(emptyZipHex));
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
    if (IS == null || nameOfResource == null)
    {
      throw new FileRequiredException();
    }
    
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
