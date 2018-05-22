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
package dss.vector.solutions.geoserver;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.runwaysdk.constants.CommonProperties;
import com.runwaysdk.constants.DatabaseProperties;
import com.runwaysdk.generation.loader.Reloadable;
import com.runwaysdk.query.OIterator;
import com.runwaysdk.query.QueryFactory;

import dss.vector.solutions.FileNotFoundException;
import dss.vector.solutions.basemap.OfflineBasemapManagement;
import dss.vector.solutions.basemap.OfflineBasemapManagementQuery;
import dss.vector.solutions.geoserver.GeoserverLayer.LayerType;

public class LocalBasemapBuilder implements Reloadable
{

  private static final Log log = LogFactory.getLog(LocalBasemapBuilder.class);

  public static void configureGeoserverForOSM()
  {
	String lineSLDFileName = "style_osm_line.sld";
	String polygonSLDFileName = "style_osm_polygon.sld";
    String defaultStylesPathStr = CommonProperties.getDeployRoot() + File.separator + "webapps" + File.separator + GeoserverProperties.getAppName() + File.separator + "data" + File.separator + "styles" ;
    Path defaultStylesPath = Paths.get(defaultStylesPathStr);
    Path lineSLDFilePath = Paths.get(defaultStylesPath + File.separator + "osm-line-style.sld");
    Path polygonSLDFilePath = Paths.get(defaultStylesPath + File.separator + "osm-polygon-style.sld");
    
    if (GeoserverFacade.workspaceExists(GeoserverProperties.getOSMWorkspace()))
    {
      GeoserverFacade.removeWorkspace(GeoserverProperties.getOSMWorkspace());
      GeoserverFacade.removeStore(GeoserverProperties.getOSMWorkspace(), GeoserverProperties.getOSMDatastore());
    }

    GeoserverFacade.publishWorkspace(GeoserverProperties.getOSMWorkspace());
    GeoserverFacade.publishStore(GeoserverProperties.getOSMDatastore(), GeoserverProperties.getOSMDatabaseName(), GeoserverProperties.getOSMDatabaseSchema());

    // Line styles
//    if (GeoserverFacade.styleExists("osm-line-style"))
//    {
    GeoserverFacade.removeStyle("osm-line-style");
  
    File targetLineFile = new File(lineSLDFilePath.toString());
    if(targetLineFile.exists() && !targetLineFile.isDirectory())
    {
    	targetLineFile.delete();
    }

    URL lineFileURL = LocalBasemapBuilder.class.getClassLoader().getResource(lineSLDFileName);
    File lineFile = null;
    try
    {
      lineFile = new File(lineFileURL.getFile());
    }
    catch(NullPointerException e)
    {
    	FileNotFoundException fileEx = new FileNotFoundException();
    	fileEx.setName(lineSLDFileName);
    	fileEx.setPath(lineFileURL.getPath());
    	throw fileEx;
    }

    GeoserverFacade.publishStyle("osm-line-style", lineFile, true);
    
    
    // Polygon styles
//    if (GeoserverFacade.styleExists("osm-polygon-style"))
//    {
    GeoserverFacade.removeStyle("osm-polygon-style");
      
    File targetPolyFile = new File(polygonSLDFilePath.toString());
	if(targetPolyFile.exists() && !targetPolyFile.isDirectory())
	{
	  targetPolyFile.delete();
	}

    URL polygonFileURL = LocalBasemapBuilder.class.getClassLoader().getResource(polygonSLDFileName);
    File polygonFile = null;
    try
    {
      polygonFile = new File(polygonFileURL.getFile());
    }
    catch(NullPointerException e)
    {
    	FileNotFoundException fileEx = new FileNotFoundException();
    	fileEx.setName(polygonSLDFileName);
    	fileEx.setPath(polygonFileURL.getPath());
    	throw fileEx;
    }

    GeoserverFacade.publishStyle("osm-polygon-style", polygonFile, true);

  }

  public static HashMap<String, Boolean> getBasemapFilesUploadStatus()
  {
    HashMap<String, Boolean> filesWithStatus = new HashMap<String, Boolean>();

    File[] filesOnDisk = getBasemapFiles();

    ArrayList<String> persistedFilesConfig = new ArrayList<String>();
    OIterator<? extends OfflineBasemapManagement> it = new OfflineBasemapManagementQuery(new QueryFactory()).getIterator();
    while (it.hasNext())
    {
      persistedFilesConfig.add(it.next().getFileName());
    }

    for (File fd : filesOnDisk)
    {
      boolean previouslyUploaded = persistedFilesConfig.contains(fd.getName());
      filesWithStatus.put(fd.getName(), previouslyUploaded);
    }

    // for(String pf : persistedFilesConfig)
    // {
    // boolean fileOnDisk = false;
    // java.util.Iterator it = filesWithStatus.entrySet().iterator();
    // while(it.hasNext())
    // {
    // Map.Entry pair = (Map.Entry)it.next();
    // File file = (File) pair.getKey();
    //
    // if(file.getName().equals(pf))
    // {
    // fileOnDisk = true;
    // }
    // it.remove();
    // }
    //
    // if(!fileOnDisk)
    // {
    // // file uploaded but not on disk anymore... someone might have deleted it.
    // }
    // }

    return filesWithStatus;
  }

  public static File[] getBasemapFiles()
  {
	ArrayList<File> filteredFiles = new ArrayList<File>();
	File[] filteredFilesArr = null;

    File folder = new File(GeoserverProperties.getBasemapDirectory());
    
    if(folder != null && folder.exists())
    {
	    File[] listOfFiles = folder.listFiles();
	
	    if(listOfFiles != null)
	    {
		    for (int i = 0; i < listOfFiles.length; i++)
		    {
		      if (listOfFiles[i].isFile() && FilenameUtils.getExtension(listOfFiles[i].getName()).equals("pbf"))
		      {
		        filteredFiles.add(listOfFiles[i]);
		      }
		      else if (listOfFiles[i].isDirectory())
		      {
		        // skip any sub-directories
		      }
		    }
		
		    filteredFilesArr = new File[filteredFiles.size()];
		    for (int i = 0; i < filteredFiles.size(); i++)
		    {
		      filteredFilesArr[i] = filteredFiles.get(i);
		    }
	    }
    }

    return filteredFilesArr;
  }
  
  public static File[] getConvertedBasemapFiles()
  {
	ArrayList<File> filteredFiles = new ArrayList<File>();
	File[] filteredFilesArr = null;

    File folder = new File(GeoserverProperties.getBasemapDirectory()+ File.separator +"processing");
    
    if(folder != null && folder.exists())
    {
	    File[] listOfFiles = folder.listFiles();
	
	    if(listOfFiles != null)
	    {
		    for (int i = 0; i < listOfFiles.length; i++)
		    {
		      if (listOfFiles[i].isFile() && FilenameUtils.getExtension(listOfFiles[i].getName()).equals("o5m"))
		      {
		        filteredFiles.add(listOfFiles[i]);
		      }
		      else if (listOfFiles[i].isDirectory())
		      {
		        // skip any sub-directories
		      }
		    }
		
		    filteredFilesArr = new File[filteredFiles.size()];
		    for (int i = 0; i < filteredFiles.size(); i++)
		    {
		      filteredFilesArr[i] = filteredFiles.get(i);
		    }
	    }
    }

    return filteredFilesArr;
  }

  public static boolean importBasemapFiles(String[] fileNames)
  {
    boolean successfullImport = false;
    
    if(fileNames.length > 1)
    {
	    List<File> files = new ArrayList<File>();
	    for (String fileName : fileNames)
	    {
	      File file = new File(GeoserverProperties.getBasemapDirectory() + File.separator + fileName);
	
	      if (file.exists())
	      {
	    	  files.add(file);
	      }
	    }
	    
	    successfullImport = combineBasemapFilesAndImport( files.toArray( new File[files.size()] ) );
    }
    else if(fileNames.length == 1)
    {
      File file = new File(GeoserverProperties.getBasemapDirectory() + File.separator + fileNames[0]);
      successfullImport = importBasemapFile(file, true);
    }
    

    // ONLY run after all data is uploaded and if there are actually updates
    if (successfullImport == true && fileNames.length > 0)
    {
      buildOSMGeoserverServices();
    }

    return successfullImport;
  }
  
  public static List<File> convertPbfToO5m(File[] files, File processingDirectory)
  {
	 List<File> convertedFiles = new ArrayList<File>();
	 for(File file : files)
	 {
	  	File convertedFile = convertPbfToO5m(file, processingDirectory);
	  	convertedFiles.add(convertedFile);
	 }
	 
	 return convertedFiles;
  }
  
  public static File convertPbfToO5m(File file, File processingDirectory)
  {
	  List<String> command = new ArrayList<String>();
	  
      boolean tempDirCreated = new File(processingDirectory + File.separator + "processing").mkdir();
      File tempDir = new File(processingDirectory + File.separator + "processing");
	  
	  command.add(getOsmConvertRoot());
	  command.add(file.getName());
	  command.add("-o=processing/"+file.getName().replace(".pbf", ".o5m"));
	  
	  try
      {
        ProcessBuilderWrapper proc = new ProcessBuilderWrapper(processingDirectory, command);

        if (proc.getStatus() == 0)
        {
          File convertedFile = new File(tempDir + File.separator + file.getName().replace(".pbf", ".o5m"));
      	  
      	  if(convertedFile.exists() && convertedFile.length() > 0)
      	  {
      		  log.debug("\n\nBasemap merge successful!");
      		  return convertedFile;
      	  }
      	  else
      	  {
      		  return null;
      	  }
        }
        else
        {
          log.debug("\n\nProblem merging basemap!");
          log.debug(proc.getErrors());
          return null;
        }
      } 
	  catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	  }
	  
	  
	  return null;
  }
  
  public static String getOsmConvertRoot()
  {
     String osmConvertRoot = "osmconvert"; // if installed as a service. Typically a dev environment.
     if (System.getProperty("os.name").toLowerCase().contains("windows"))
     {
//	      String arch = System.getenv("PROCESSOR_ARCHITECTURE");
//	      String wow64Arch = System.getenv("PROCESSOR_ARCHITEW6432");
//	      String realArch = arch != null && arch.endsWith("64") || wow64Arch != null && wow64Arch.endsWith("64") ? "64" : "32";
	      
	      osmConvertRoot = GeoserverProperties.getOSMConvertRoot();
	      
//	      if(realArch == "64" && osmConvertRoot.contains("osmconvert64"))
//	      {
//	    	  // 64 bit OS referencing 64 bit osmconvert. 64 bit osmconvert files are named osmconvert64-x.x.x.exe by default.
//	      }
//	      else if(realArch == "32" && osmConvertRoot.contains("osmconvert") && !osmConvertRoot.contains("osmconvert64"))
//	      {
//	    	  // 32 bit OS referencing 32 bit osmconvert. 32 bit osmconvert files are named osmconvert.exe by default.
//	      }
//	      else if(osmConvertRoot.isEmpty())
//	      {
//	    	  // Property not set.
//	      }
     }
     
     return osmConvertRoot;
  }
  
  /**
   * Combine multiplbe OSM .pbf files into one. 
   * 
   * Potential performance enhancement: https://wiki.openstreetmap.org/wiki/Osmconvert#Parallel_Processing
   * 
   * @param files
   * @return
   */
  public static Boolean combineBasemapFilesAndImport(File[] files)
  {
    boolean success = false;
    String mergedFileName = "merged.pbf";
    File basemapDir = new File(GeoserverProperties.getBasemapDirectory());
    //osmconvert <(osmconvert a.pbf --out-o5m) <(osmconvert b.pbf --out-o5m) -o merged.pbf
    List<String> command = new ArrayList<String>();
    
    // convert all .pbf to .o5m format which is required for merge
    List<File> convertedBasemapFiles = convertPbfToO5m(files, basemapDir);
    
    File tempDir = new File(basemapDir + File.separator + "processing");
    
    command.add(getOsmConvertRoot());
    
    for(File file : convertedBasemapFiles)
    {
      command.add("processing"+ File.separator + file.getName());
    }
    command.add("-o=" + "processing" + File.separator + mergedFileName);
    
    try
    {
        ProcessBuilderWrapper proc = new ProcessBuilderWrapper(basemapDir, command);

        System.out.println("Command has terminated with status: " + proc.getStatus());
        System.out.println("Output:\n" + proc.getInfos());
        System.out.println("Error: " + proc.getErrors());

        if (proc.getStatus() == 0)
        {
          success = true;
          File mergedFile = new File(tempDir + File.separator + mergedFileName);
      	  
      	  if(mergedFile.exists() && mergedFile.length() > 0)
      	  {
      	    Boolean importComplete = importBasemapFile(mergedFile, true);
        	
      	    if(importComplete)
          	{
              log.debug("\n\nBasemap import successful!");
          	}
      	    else
      	    {
      	      log.debug("\n\nBasemap import failure!");
      	    }
      	  }
      	  else
      	  {
      		  // throw exception
      	  }
      	  
      	  log.debug("\n\nBasemap merge successful!");
        }
        else
        {
          log.debug("\n\nProblem merging basemap!");
          log.debug(proc.getErrors());
        }
    } catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
    finally
    {
    	try {
    		FileUtils.deleteDirectory(tempDir);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    return success;
  }

  /**
   * 
   * @param file
   * @param cleanFirst
   * @return
   */
  public static boolean importBasemapFile(File file, boolean cleanFirst)
  {
    boolean success = false;

    String uploadMethod = "append";
    {
      if (cleanFirst)
      {
        uploadMethod = "create";
      }
    }

    String psqlRoot = "osm2pgsql"; // if installed as a service. Typically a dev environment.
    if (System.getProperty("os.name").toLowerCase().contains("windows"))
    {
      psqlRoot = GeoserverProperties.getOSM2PgsqlRoot();
    }

    List<String> command = new ArrayList<String>();
    command.add(psqlRoot);
    command.add("--".concat(uploadMethod));
    command.add("--number-processes");
    command.add(GeoserverProperties.getNumberOfProcessesForUploads());
    command.add("--slim");
    command.add("--latlong");
    command.add("-d");
    command.add(GeoserverProperties.getOSMDatabaseName()); // osm database
    command.add("-P");
    command.add(Integer.toString(DatabaseProperties.getPort())); // port
    command.add("--username");
    command.add(GeoserverProperties.getOSMUserName()); // user name
    //    command.add("--password");
    command.add(file.getAbsolutePath());

    try
    {
      Path path = Files.createTempDirectory("pb");
      File dir = path.toFile();

      try
      {
        ProcessBuilderWrapper proc = new ProcessBuilderWrapper(dir, command);

        System.out.println("Command has terminated with status: " + proc.getStatus());
        System.out.println("Output:\n" + proc.getInfos());
        System.out.println("Error: " + proc.getErrors());

        if (proc.getStatus() == 0)
        {
          success = true;

          log.debug("\n\nBasemap data import successful!");
        }
        else
        {
          log.debug("\n\nProblem with basemap data import!");
        }
      }
      finally
      {
        Files.deleteIfExists(path);
      }
    }
    catch (Exception e2)
    {
      // TODO Auto-generated catch block
      e2.printStackTrace();
      //throw new ProgrammingErrorException(e2);
    }

    return success;
  }

  public static void importAllBasemapData()
  {
    File[] files = getBasemapFiles();

    if(files.length > 1)
    {
      // Combine the files to improve upload performance with osm2pgsql
  	  combineBasemapFilesAndImport(files);
    }
    else
    {
      importBasemapFile(files[0], true);
    }
  }

  public static void buildOSMGeoserverServices()
  {
    GeoserverLayer lineLayer = new GeoserverLayer();
    lineLayer.setLayerName("planet_osm_line");
    lineLayer.setLayerType(LayerType.LINE);
    lineLayer.setStyleName("osm-line-style");

    GeoserverLayer roadLayer = new GeoserverLayer();
    roadLayer.setLayerName("planet_osm_roads");
    roadLayer.setLayerType(LayerType.LINE);
    roadLayer.setStyleName("osm-line-style");

    GeoserverLayer polyLayer = new GeoserverLayer();
    polyLayer.setLayerName("planet_osm_polygon");
    polyLayer.setLayerType(LayerType.POLYGON);
    polyLayer.setStyleName("osm-polygon-style");

    // GeoserverLayer pointLayer = new GeoserverLayer();
    // pointLayer.setLayerName("planet_osm_point");
    // pointLayer.setLayerType(LayerType.POINT);
    // pointLayer.setStyleName("osm-polygon-style");

    if (GeoserverFacade.layerExists(lineLayer.getLayerName()))
    {
      GeoserverFacade.removeLayer(lineLayer.getLayerName());
    }
    GeoserverFacade.publishOSMLayer(lineLayer);

    if (GeoserverFacade.layerExists(roadLayer.getLayerName()))
    {
      GeoserverFacade.removeLayer(roadLayer.getLayerName());
    }
    GeoserverFacade.publishOSMLayer(roadLayer);

    if (GeoserverFacade.layerExists(polyLayer.getLayerName()))
    {
      GeoserverFacade.removeLayer(polyLayer.getLayerName());
    }
    GeoserverFacade.publishOSMLayer(polyLayer);
    // GeoserverFacade.publishOSMLayer(pointLayer);

    String[] layers = new String[3];
    layers[0] = polyLayer.getLayerName();
    layers[1] = lineLayer.getLayerName();
    layers[2] = roadLayer.getLayerName();
    // layers[3] = pointLayer.getLayerName();

    if (GeoserverFacade.layerGroupExists("osm_basic"))
    {
      GeoserverFacade.removeLayer(lineLayer.getLayerName());
    }
    GeoserverFacade.publishLayerGroup(layers, GeoserverProperties.getOSMWorkspace(), "osm_basic");

    GeoserverFacade.removeCache("osm_basic", GeoserverProperties.getOSMWorkspace());
  }

}
