package dss.vector.solutions.geoserver;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.runwaysdk.constants.CommonProperties;
import com.runwaysdk.constants.DatabaseProperties;
import com.runwaysdk.generation.loader.Reloadable;
import com.runwaysdk.query.OIterator;
import com.runwaysdk.query.QueryFactory;

import dss.vector.solutions.basemap.OfflineBasemapManagement;
import dss.vector.solutions.basemap.OfflineBasemapManagementQuery;
import dss.vector.solutions.geoserver.GeoserverLayer.LayerType;

public class LocalBasemapBuilder implements Reloadable
{

  private static final Log log = LogFactory.getLog(LocalBasemapBuilder.class);

  public static void configureGeoserverForOSM()
  {

    if (GeoserverFacade.workspaceExists(GeoserverProperties.getOSMWorkspace()))
    {
      GeoserverFacade.removeWorkspace(GeoserverProperties.getOSMWorkspace());
      GeoserverFacade.removeStore(GeoserverProperties.getOSMWorkspace(), GeoserverProperties.getOSMDatastore());
    }

    GeoserverFacade.publishWorkspace(GeoserverProperties.getOSMWorkspace());
    GeoserverFacade.publishStore(GeoserverProperties.getOSMDatastore(), GeoserverProperties.getOSMDatabaseName(), GeoserverProperties.getOSMDatabaseSchema());

    if (GeoserverFacade.styleExists("osm-line-style"))
    {
      GeoserverFacade.removeStyle("osm-line-style");
    }

    System.out.println("finding line styles...");
    URL lineFileURL = LocalBasemapBuilder.class.getClassLoader().getResource("style_osm_line.sld");
    File lineFile = new File(lineFileURL.getFile());

    GeoserverFacade.publishStyle("osm-line-style", lineFile, true);

    if (GeoserverFacade.styleExists("osm-polygon-style"))
    {
      GeoserverFacade.removeStyle("osm-polygon-style");
    }

    URL polygonFileURL = LocalBasemapBuilder.class.getClassLoader().getResource("style_osm_polygon.sld");
    File polygonFile = new File(polygonFileURL.getFile());

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

    // OfflineBasemapManagement basemapManager = OfflineBasemapManagement.getByKey("Singleton");
    // String persistedFiles = basemapManager.getConfig();
    // List<String> persistedFilesConfig = new ArrayList<String>(Arrays.asList(persistedFiles.split(",")));

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
    File folder = new File(CommonProperties.getDeployRoot() + File.separator + "webapps" + File.separator + CommonProperties.getDeployAppName() + File.separator + "basemaps");
    File[] listOfFiles = folder.listFiles();
    ArrayList<File> filteredFiles = new ArrayList<File>();

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

    File[] filteredFilesArr = new File[filteredFiles.size()];
    for (int i = 0; i < filteredFiles.size(); i++)
    {
      filteredFilesArr[i] = filteredFiles.get(i);
    }

    return filteredFilesArr;
  }

  public static boolean importBasemapFiles(String[] fileNames)
  {
    boolean cleanDB = true;
    boolean allSuccessful = true;
    ArrayList<String> uploadedFiles = new ArrayList<String>();
    for (String fileName : fileNames)
    {
      File file = new File(CommonProperties.getDeployRoot() + File.separator + "webapps" + File.separator + CommonProperties.getDeployAppName() + File.separator + "basemaps" + File.separator + fileName);

      if (file.exists())
      {
        // OfflineBasemapManagement persistedFile = null;
        // try
        // {
        // persistedFile = new OfflineBasemapManagement();
        // persistedFile.setFileName(file.getName());
        // persistedFile.setUploadSuccessful(false);
        // persistedFile.setQuedForUpload(true);
        // persistedFile.apply();
        //
        // } catch(Exception e)
        // {
        // throw new ProgrammingErrorException(e);
        // }
        boolean successfulImport = importBasemapFile(file, cleanDB);
        // boolean successfulImport = true;
        if (successfulImport)
        {
          uploadedFiles.add(file.getName());

          // persistedFile.lock();
          // persistedFile.setUploadSuccessful(true);
          // persistedFile.setQuedForUpload(false);
          // persistedFile.apply();

        }
        else
        {
          allSuccessful = false;
        }

        cleanDB = false;
      }
    }

    // ONLY run after all data is uploaded
    buildOSMGeoserverServices();

    return allSuccessful;
  }

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
    command.add("4"); // 4 processes
    command.add("--slim");
    command.add("--latlong");
    command.add("-d");
    command.add(GeoserverProperties.getOSMDatabaseName()); // osm database
    command.add("-P");
    command.add(Integer.toString(DatabaseProperties.getPort())); // port
    command.add("--username");
    command.add(GeoserverProperties.getOSMUserName()); // user name
    command.add("--password");
    command.add(file.getAbsolutePath());

    // ProcessBuilder proBuilder = new ProcessBuilder( command );
    // proBuilder.redirectErrorStream(true);

    try
    {
      ProcessBuilderWrapper proc = new ProcessBuilderWrapper(new File("/tmp"), command);

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
    catch (Exception e2)
    {
      // TODO Auto-generated catch block
      e2.printStackTrace();
    }

    // Process process = null;
    // try {
    // process = proBuilder.inheritIO().start();
    // } catch(IOException e1) {
    // throw new ProgrammingErrorException(e1);
    // }

    // BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(process.getOutputStream()));
    // BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
    // BufferedReader error = new BufferedReader(new InputStreamReader(process.getErrorStream()));

    // String line;
    // String errorLine;
    // StringBuilder builder = new StringBuilder();
    // log.debug("Output of running %s is:\n".concat(command.toString()));
    // try {
    //
    // String dbPass = GeoserverProperties.getOSMPassword().concat("\n");
    // writer.write( dbPass );
    //
    // while((line = reader.readLine()) != null)
    // {
    // builder.append(line);
    // builder.append(System.getProperty("line.separator"));
    //
    // //System.out.println(line);
    // }
    //
    // //String result = builder.toString();
    // //System.out.println(result);
    //
    // while((errorLine = error.readLine()) != null)
    // {
    // System.out.println(errorLine);
    // }
    //
    // int exitValue = process.waitFor();
    // if(exitValue == 0)
    // {
    // success = true;
    //
    // log.debug("\n\nBasemap data import successful!");
    // }
    // else
    // {
    // log.debug("\n\nProblem with basemap data import!");
    // }
    //
    // } catch(IOException e1) {
    // throw new ProgrammingErrorException(e1);
    // } catch(InterruptedException e) {
    // throw new ProgrammingErrorException(e);
    // } finally {
    // try {
    // writer.close();
    // reader.close();
    // error.close();
    // } catch (IOException e) {
    // throw new ProgrammingErrorException(e);
    // }
    // }

    return success;
  }

  public static void importAllBasemapData()
  {
    File[] files = getBasemapFiles();

    boolean cleanDB = true;
    for (File file : files)
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

    // GeoserverFacade.removeCache("osm_basic");
    // GeoserverFacade.rePublishCache("osm_basic", GeoserverProperties.getOSMWorkspace());
  }

}