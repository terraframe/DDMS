package dss.vector.solutions.query;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethodBase;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.terraframe.mojo.business.Business;
import com.terraframe.mojo.constants.LocalProperties;
import com.terraframe.mojo.dataaccess.ProgrammingErrorException;
import com.terraframe.mojo.dataaccess.database.Database;
import com.terraframe.mojo.dataaccess.database.DatabaseException;
import com.terraframe.mojo.query.ValueQuery;
import com.terraframe.mojo.session.Session;
import com.terraframe.mojo.system.WebFile;
import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.Envelope;

import dss.vector.solutions.geo.DuplicateMapDataException;
import dss.vector.solutions.geo.GeoServerReloadException;
import dss.vector.solutions.global.CredentialsSingleton;


public class MapUtil extends MapUtilBase implements com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1242080109170L;

  public MapUtil()
  {
    super();
  }
  
  /**
   * Creates database views for each layer provided and returns a JSON string
   * with the correct mapping information for use with OpenLayers/GeoServer.
   * 
   * @param layers
   * @return
   */
  public static String createDBViews(List<? extends Layer> layers)
  {
    if(layers.size() == 0)
    {
      String error = "A user tried to create a map without layers.";
      throw new NoThematicLayerException(error);
    }
    
    JSONObject mapData;
    JSONArray layersJSON;
    
    try
    {
      String geoserverPath = getGeoServerRemoteURL();
      
      mapData = new JSONObject();
      layersJSON = new JSONArray();
      
      mapData.put("geoserverURL", geoserverPath);
      mapData.put("layers", layersJSON);
    }
    catch(JSONException e)
    {
      String error = "Could not create the mapping data.";
      throw new ProgrammingErrorException(error, e);
    }
    
    
    String sessionId = Session.getCurrentSession().getId();
    for(int i=0; i<layers.size(); i++)
    {
      Layer layer = layers.get(i);
      String viewName = layer.getViewName();
      
      SavedSearch search = layer.getSavedSearch(); 
      String xml = search.getQueryXml();
      String config = search.getConfig();
      String queryType = search.getQueryType();
      
      // QueryBuilder.getValueQuery() takes in the query class for use with reflection.
      // TODO pass in queryType and have getValueQuery deref the class
      String queryClass = QueryConstants.getQueryClass(queryType);
      ValueQuery valueQuery = QueryBuilder.getValueQuery(queryClass, xml, config, layer);
      
      if(valueQuery.getCount() == 0)
      {
        String error = "The thematic layer doesn't contain spatial data.";
        throw new NoEntitiesInThematicLayerException(error);        
      }
      
      String sql = valueQuery.getSQL();

      try
      {
        Database.dropView(viewName, sql, false); 
      }
      catch(DatabaseException e)
      {
        // View doesn't exist, but that's okay. It may have never
        // been created or a cleanup task has removed it.
      }
      finally
      {
      // Create a new view that will reflect the current state of the query.
        Database.createView(viewName, sql);
      }
      
      // make sure there are no duplicate geo entities
      String countSQL = "SELECT COUNT(*) " + Database.formatColumnAlias("ct") + " FROM " + viewName;
      countSQL += " GROUP BY "+QueryConstants.GEO_ID_COLUMN + " HAVING COUNT(*) > 1";
      
      ResultSet resultSet = Database.query(countSQL);
      
      try
      {
        if(resultSet.next())
        {
          DuplicateMapDataException ex = new DuplicateMapDataException();
          throw ex;
        }
      }
      catch (SQLException sqlEx1)
      {
        Database.throwDatabaseException(sqlEx1);
      }
      finally
      {
        try
        {
          java.sql.Statement statement = resultSet.getStatement();
          resultSet.close();
          statement.close();
        }
        catch (SQLException sqlEx2)
        {
          Database.throwDatabaseException(sqlEx2);
        }
      }
      
      
      reload(sessionId, viewName, layer.getRenderAs().get(0));
      
      // Update the view name on the layer so the old view can be cleaned up
      String newViewName = Layer.GEO_VIEW_PREFIX + System.currentTimeMillis();
      layer.appLock();
      layer.setViewName(newViewName);     
      layer.apply();
      
      String namespacedView = QueryConstants.MDSS_NAMESPACE + ":" + viewName;
      String sldFile = formatSLD(layer);
      
      // Create the JSON for this layer that will be passed to OpenLayers
      JSONObject layerJSON = new JSONObject();
      try
      {
        layerJSON.put("view", namespacedView);
        layerJSON.put("sld", sldFile);
        layersJSON.put(layerJSON);
        
        if(i == 0)
        {
          // restrict the map bounds to the base layer
          mapData.put("bbox", getThematicBBox(viewName));
        }

      }
      catch (JSONException e)
      {
        String error = "Could not produce the information for the layer ["+layer.getLayerName()+"]";
        throw new ProgrammingErrorException(error, e);
      }

    }
    
    return mapData.toString();
  }

 /**
  * Formats the SLD file (pathing and filename concatenation) for the given layer.
  *
  * @param layer
  * @return
  */
 private static String formatSLD(Layer layer)
 {
   WebFile file = WebFile.get(layer.getSldFile());
   String fullWebDir = LocalProperties.getWebDirectory();

   if(fullWebDir.endsWith("/"))
   {
     fullWebDir = fullWebDir.substring(0, fullWebDir.length()-1);
   }
   String webDir = fullWebDir.substring(fullWebDir.lastIndexOf("/"));
   if(webDir.startsWith("/"))
   {
     webDir = webDir.substring(1);
   }
   String filePath = webDir+ "/" + file.getFilePath() + file.getFileName() + "." + file.getFileExtension();

   // Generated a random query string to force GeoServer to not cache the SLD
   String random = String.valueOf(Math.random());
   random = random.substring(random.length()-6);

   return filePath+ "?a="+random;
 }

 private static final Pattern JSESSIONID_PATTERN = Pattern.compile("(?:.*?)JSESSIONID=(\\w*);.*");

 private static final ResourceBundle bundle = ResourceBundle.getBundle("GeoServer", Locale.getDefault(), Business.class.getClassLoader());

 /**
  * Returns the url to access GeoServer locally.
  * @return
  */
 public static final String getGeoServerLocalURL()
 {
   return bundle.getString("geoserver.local.path");
 }

 /**
  * Returns the url to access GeoServer remotely.
  * @return
  */
 public static final String getGeoServerRemoteURL()
 {
   return bundle.getString("geoserver.remote.path");
 }

 public static void reload(String sessionId, String viewName, AllRenderTypes renderType)
 {
   try
   {
     String geoserverPath = getGeoServerLocalURL();

     // poke the server to get a valid JSESSIONID in the cookie
     GetMethod pokeGet = new GetMethod(geoserverPath+"/welcome.do");
     NameValuePair[] pokeQueryString = new NameValuePair[]{new NameValuePair(CredentialsSingleton.GLOBAL_SESSION_ID, sessionId)};
     pokeGet.setQueryString(pokeQueryString);

     HttpClient pokeClient = new HttpClient();
     int pokeCode = pokeClient.executeMethod(pokeGet);
     printResponse("Poke", pokeCode, pokeGet, false);
     Header cookies = pokeGet.getResponseHeader("Set-Cookie");
     String value = cookies.getValue();

     // A valid jSessionId is required for the next two calls (this is just the way GeoServer does it).
     Matcher matcher = JSESSIONID_PATTERN.matcher(value);
     matcher.matches();

     String jSessionId = matcher.group(1);

     // request a new feature
     PostMethod newPost = new PostMethod(geoserverPath+"/config/data/typeNewSubmit.do");
     newPost.addRequestHeader("Cookie", "JSESSIONID="+jSessionId);
     newPost.addParameter(CredentialsSingleton.GLOBAL_SESSION_ID, sessionId);
     newPost.addParameter("selectedNewFeatureType", QueryConstants.FEATURE_NAMESPACE+":::"+viewName.toLowerCase());

     HttpClient newClient = new HttpClient();
     int newCode = newClient.executeMethod(newPost);
     printResponse("New", newCode, newPost, false);
     newPost.releaseConnection();

     // create the feature
     PostMethod createPost = new PostMethod(geoserverPath+"/config/data/typeEditorSubmit.do");
     createPost.addParameter(CredentialsSingleton.GLOBAL_SESSION_ID, sessionId);
     createPost.addRequestHeader("Cookie", "JSESSIONID="+jSessionId);

     String defaultStyle = renderType == AllRenderTypes.POINT ? "point" : "polygon";

     createPost.addParameter("panelStyleIds", defaultStyle);
     createPost.addParameter("styleId", defaultStyle);

     createPost.addParameter("SRS", "4326");
     createPost.addParameter("abstract", "Generated from MDSS_maps");
     createPost.addParameter("alias", "");
     createPost.addParameter("autoGenerateExtent", "true");
     createPost.addParameter("cacheMaxAge", "");
     createPost.addParameter("keywords", viewName);
     createPost.addParameter("maxFeatures", "0");

     createPost.addParameter("metadataLink[0].content", "");
     createPost.addParameter("metadataLink[0].metadataType", "FGDC");
     createPost.addParameter("metadataLink[0].type", "text/plain");
     createPost.addParameter("metadataLink[1].content", "");
     createPost.addParameter("metadataLink[1].metadataType", "FGDC");
     createPost.addParameter("metadataLink[1].type", "text/plain");

     createPost.addParameter("nameTemplate", "null");
     createPost.addParameter("regionateAttribute", "null");
     createPost.addParameter("regionateFeatureLimit", "15");
     createPost.addParameter("regionateStrategy", "best_guess");
     createPost.addParameter("schemaBase", "--");
     createPost.addParameter("srsHandling", "Force declared SRS (native will be ignored)");
     createPost.addParameter("title", viewName);
     createPost.addParameter("wmsPath", "/");

     createPost.addParameter("action", "Submit");

     HttpClient createClient = new HttpClient();
     int createCode = createClient.executeMethod(createPost);
     printResponse("Create", createCode, createPost, false);
     createPost.releaseConnection();

     // Apply
      PostMethod applyPost = new PostMethod(geoserverPath+"/admin/saveToGeoServer.do");
      applyPost.addParameter(CredentialsSingleton.GLOBAL_SESSION_ID, sessionId);
      applyPost.addParameter("submit", "Apply");

      HttpClient applyClient = new HttpClient();
      int applyResponse = applyClient.executeMethod(applyPost);
      printResponse("Apply", applyResponse, applyPost, false);

     // Save
     PostMethod savePost = new PostMethod(geoserverPath+"/admin/saveToXML.do");
     savePost.addParameter(CredentialsSingleton.GLOBAL_SESSION_ID, sessionId);
     savePost.addParameter("submit", "Save");

     HttpClient saveClient = new HttpClient();
     int saveResponse = saveClient.executeMethod(savePost);
     printResponse("Save", saveResponse, savePost, false);

     // reload the catalog
     GetMethod get1 = new GetMethod(geoserverPath+"/admin/loadFromXML.do");
     NameValuePair[] params = new NameValuePair[] { new NameValuePair(
         CredentialsSingleton.GLOBAL_SESSION_ID, sessionId) };
     get1.setQueryString(params);

     HttpClient client3 = new HttpClient();
     int responseCode3 = client3.executeMethod(get1);
     printResponse("Reload", responseCode3, get1, false);
     get1.releaseConnection();

   }
   catch (Exception e)
   {
     String error = "Could not reload GeoServer.";
     GeoServerReloadException ex = new GeoServerReloadException(error, e);
     throw ex;
   }
 }

 /**
  * Prints the response information to the standard out.
  *
  * @param inStream
  * @throws IOException
  */
 private static void printResponse(String name, int responseCode, HttpMethodBase method, boolean printResponse)
     throws IOException
 {
   System.out.println(name+": " + responseCode);

   if (printResponse)
   {
     InputStream response = method.getResponseBodyAsStream();
     BufferedReader reader = new BufferedReader(new InputStreamReader(response));

     try
     {
       String all = "";
       String line = null;
       while ( ( line = reader.readLine() ) != null)
       {
         all += line;
       }

       System.out.println(all);
     }
     finally
     {
       reader.close();
     }
   }
 }

 /**
  * Gets the bounding box of the thematic layer.
  * 
  * @return
  */
 public static JSONArray getThematicBBox(String viewName)
 {
   ResultSet resultSet = Database.query("SELECT AsText(extent("+viewName+"."+QueryConstants.GEOMETRY_NAME_COLUMN+")) AS bbox FROM "+viewName);
   try
   {
     if(resultSet.next())
     {
       String bbox = resultSet.getString("bbox");
       if(bbox != null)
       {
         Pattern p = Pattern.compile("POLYGON\\(\\((.*)\\)\\)");
         Matcher m = p.matcher(bbox);
         m.matches();
         
         String coordinates = m.group(1);
         List<Coordinate> coords = new LinkedList<Coordinate>();
         
         for(String c : coordinates.split(","))
         {
           String[] xAndY = c.split(" ");
           double x = Double.valueOf(xAndY[0]);
           double y = Double.valueOf(xAndY[1]);

           coords.add(new Coordinate(x,y));
         }
         
         Envelope e = new Envelope(coords.get(0), coords.get(2));

         JSONArray bboxArr = new JSONArray();
         
         try
         {
           bboxArr.put(e.getMinX());
           bboxArr.put(e.getMinY());
           bboxArr.put(e.getMaxX());
           bboxArr.put(e.getMaxY());
         }
         catch(JSONException ex)
         {
           throw new ProgrammingErrorException(ex);
         }
         
         return bboxArr;
       }
     }
   }
   catch (SQLException sqlEx1)
   {
     Database.throwDatabaseException(sqlEx1);
   }
   finally
   {
     try
     {
       java.sql.Statement statement = resultSet.getStatement();
       resultSet.close();
       statement.close();
     }
     catch (SQLException sqlEx2)
     {
       Database.throwDatabaseException(sqlEx2);
     }
   }
   
   // Some problem occured and the bbox couldn't be calculated.
   // Just return the African defaults
   JSONArray bboxArr = new JSONArray();
   
   try
   {
     bboxArr.put(36.718452);
     bboxArr.put(-17.700377000000003);
     bboxArr.put(36.938452);
     bboxArr.put(-17.480376999999997);
   }
   catch(JSONException ex)
   {
     throw new ProgrammingErrorException(ex);
   }
   
   return bboxArr;
 }

}
