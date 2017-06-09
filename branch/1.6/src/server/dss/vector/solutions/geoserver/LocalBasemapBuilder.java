package dss.vector.solutions.geoserver;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.runwaysdk.constants.CommonProperties;
import com.runwaysdk.generation.loader.Reloadable;

import dss.vector.solutions.geoserver.GeoserverLayer.LayerType;

public class LocalBasemapBuilder implements Reloadable
{
  private static String				databaseName = "osm";
  private static String				databaseSchema = "public";
  private static String				databaseUser = "osm"; //TODO: replace with prop file
  private static String				databasePass = "osm"; //TODO: replace with prop file
  private static String				datastoreName = "OSM";
  private static String				workspaceName = "OSM";

  // TODO: is this the right log?
  private static final Log          initLog     = LogFactory.getLog(LocalBasemapBuilder.class);

  
  public static String getDatabaseName()
  {
	  return databaseName;
  }
  
  public static String getDatastoreName()
  {
	  return datastoreName;
  }
  
  public static String getWorkspaceName()
  {
	  return workspaceName;
  }
  
  public static String getDatabaseSchema()
  {
	  return databaseSchema;
  }
  
  public static String getDatabaseUser()
  {
	  return databaseUser;
  }
  
  public static String getDatabasePass()
  {
	  return databasePass;
  }

  public static void configureGeoserverForOSM()
  {
	
	initLog.debug("Attempting to update GeoServer system configuration...");
	
    URL geoserverWMSConfigFileURL = LocalBasemapBuilder.class.getClassLoader().getResource("wms/wms.xml");
  	File geoserverWMSConfigFile = new File(geoserverWMSConfigFileURL.getFile());
  	  
  	String existingGeoserverWMSConfigFilePath = CommonProperties.getDeployRoot()
	  .concat(File.separator)
	  .concat("webapps")
	  .concat(File.separator)
	  .concat("geoserver")
	  .concat(File.separator)
	  .concat("data")
	  .concat(File.separator)
	  .concat("wms.xml");
  	  
  	File existingGeoserverWMSConfigFile = new File(existingGeoserverWMSConfigFilePath);
  	  
  	FileOutputStream wmsSettingsWriter = null;
	try {
		wmsSettingsWriter = new FileOutputStream(existingGeoserverWMSConfigFile, false);
		wmsSettingsWriter.write(Files.readAllBytes(Paths.get(geoserverWMSConfigFile.getPath())));
	} catch (FileNotFoundException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	} catch (IOException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	} finally {
		try {
			wmsSettingsWriter.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
  	// reload geoserver to register system config changes
    GeoserverFacade.refresh();
      
	if (GeoserverFacade.workspaceExists(getWorkspaceName()))
    {
      GeoserverFacade.removeWorkspace(getWorkspaceName());
      GeoserverFacade.removeStore(getWorkspaceName(), getDatastoreName());
    }
      
    GeoserverFacade.publishWorkspace(getWorkspaceName());
    GeoserverFacade.publishStore(getDatastoreName(), getDatabaseName(), getDatabaseSchema());
      
    if(!GeoserverFacade.styleExists("osm-line-style"))
    {
    	System.out.println("finding line styles...");
    	URL lineFileURL = LocalBasemapBuilder.class.getClassLoader().getResource("style_osm_line.sld");
    	File lineFile = new File(lineFileURL.getFile());
    	
    	GeoserverFacade.publishStyle("osm-line-style", lineFile, true);
    }
      
    if(!GeoserverFacade.styleExists("osm-polygon-style"))
    {
        URL polygonFileURL = LocalBasemapBuilder.class.getClassLoader().getResource("style_osm_polygon.sld");
        File polygonFile = new File(polygonFileURL.getFile());
        
        GeoserverFacade.publishStyle("osm-polygon-style", polygonFile, true);
    }
      
    URL symbolsSourceURL = LocalBasemapBuilder.class.getClassLoader().getResource("mapsymbols");
    File symbolsSourceDir = new File(symbolsSourceURL.getFile());
      
    String symbolsDest = CommonProperties.getDeployRoot()
	  .concat(File.separator)
	  .concat("webapps")
	  .concat(File.separator)
	  .concat("geoserver")
	  .concat(File.separator)
	  .concat("data")
	  .concat(File.separator)
	  .concat("styles");
      
    File dest = new File(symbolsDest);
    try {
      FileUtils.copyDirectory(symbolsSourceDir, dest);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public static File[] getBasemapFiles()
  {
    File folder = new File("webapps" + File.separator + CommonProperties.getDeployAppName() + File.separator + "basemaps");
	File[] listOfFiles = folder.listFiles();

    for (int i = 0; i < listOfFiles.length; i++) {
      if (listOfFiles[i].isFile() && FilenameUtils.getExtension(listOfFiles[i].getName()) == "pbf") {
        System.out.println("File " + listOfFiles[i].getName());
      } else if (listOfFiles[i].isDirectory()) {
        System.out.println("Directory " + listOfFiles[i].getName());
      }
    }
    
	return listOfFiles;
  }
  
  public static void importBasemapFiles(String[] fileNames)
  {
	boolean cleanDB = true;
	for(String fileName : fileNames)
    {
	  File file = new File("webapps" + File.separator + CommonProperties.getDeployAppName() + File.separator + "basemaps" + File.separator + fileName);
	    
	  importBasemapFile(file, cleanDB);  
	  cleanDB = false;
    }
  }
  
  public static void importBasemapFile(File file, boolean cleanFirst)
  {
	String uploadMethod = "append";
	{
	  if(cleanFirst)
	  {
		uploadMethod = "create";
	  }
	}
	
	
    List<String> command = new ArrayList<String>();
    command.add("osm2pgsql");
    command.add("--".concat(uploadMethod));
    command.add("--number-processes");
    command.add("4"); // 4 processes
    command.add("--slim");
    command.add("--latlong");
    command.add( "-d");
    command.add( "osm"); // osm database 
    command.add("--username");
    command.add(getDatabaseUser()); // user name
    command.add("--password");
    command.add(file.getAbsolutePath());
    
    ProcessBuilder proBuilder = new ProcessBuilder( command );
    proBuilder.redirectErrorStream(true);
    
    Process process = null;
	try {
		process = proBuilder.inheritIO().start();
	} catch(IOException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
    
    BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(process.getOutputStream()));
    BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
    BufferedReader error = new BufferedReader(new InputStreamReader(process.getErrorStream()));

    String line;
    String errorLine;
    StringBuilder builder = new StringBuilder();
    initLog.debug("Output of running %s is:\n".concat(command.toString()));
    try {
    	
    	writer.write( getDatabasePass().concat("\n") );
        //writer.flush();
        
		while((line = reader.readLine()) != null) 
		{
			builder.append(line);
			builder.append(System.getProperty("line.separator"));
			   
		    //System.out.println(line);
		}
		
		//String result = builder.toString();
		//System.out.println(result);
		
		while((errorLine = error.readLine()) != null) 
		{
		    System.out.println(errorLine);
		}
		
		int exitValue = process.waitFor();
		if(exitValue == 0)
		{
			initLog.debug("\n\nBasemap data import successful!");
		}
		else
		{
			initLog.debug("\n\nProblem with basemap data import!");
		}
        
	} catch(IOException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	} catch(InterruptedException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
    } finally {
    	try {
			writer.close();
	    	reader.close();
	    	error.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
  }
  
  public static void importAllBasemapData()
  {
      File[] files = getBasemapFiles();
      
      boolean cleanDB = true;
      for(File file : files)
      {
    	  importBasemapFile(file, cleanDB);
    	  cleanDB = false;
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
      
//      GeoserverLayer pointLayer = new GeoserverLayer();
//      pointLayer.setLayerName("planet_osm_point");
//      pointLayer.setLayerType(LayerType.POINT);
//      pointLayer.setStyleName("osm-polygon-style");
      
      
      GeoserverFacade.publishOSMLayer(lineLayer);
      GeoserverFacade.publishOSMLayer(roadLayer);
      GeoserverFacade.publishOSMLayer(polyLayer);
//      GeoserverFacade.publishOSMLayer(pointLayer);
      
      String[] layers = new String[3];
      layers[0] = polyLayer.getLayerName();
      layers[1] = lineLayer.getLayerName();
      layers[2] = roadLayer.getLayerName();
//      layers[3] = pointLayer.getLayerName();
      GeoserverFacade.publishLayerGroup(layers, getWorkspaceName(), "osm_basic");
  }

}