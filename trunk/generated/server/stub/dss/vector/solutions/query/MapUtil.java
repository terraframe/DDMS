package dss.vector.solutions.query;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
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

import com.terraframe.mojo.constants.LocalProperties;
import com.terraframe.mojo.dataaccess.ProgrammingErrorException;
import com.terraframe.mojo.dataaccess.database.Database;
import com.terraframe.mojo.query.ValueQuery;
import com.terraframe.mojo.session.Session;
import com.terraframe.mojo.system.WebFile;
import com.terraframe.mojo.system.gis.metadata.MdAttributeGeometry;
import com.terraframe.mojo.system.gis.metadata.MdAttributeLineString;
import com.terraframe.mojo.system.gis.metadata.MdAttributeMultiLineString;
import com.terraframe.mojo.system.gis.metadata.MdAttributeMultiPoint;
import com.terraframe.mojo.system.gis.metadata.MdAttributeMultiPolygon;
import com.terraframe.mojo.system.gis.metadata.MdAttributePoint;
import com.terraframe.mojo.system.gis.metadata.MdAttributePolygon;
import com.terraframe.mojo.system.metadata.MdBusiness;

import dss.vector.solutions.geo.GeoHierarchy;
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
  *
  *
  * @param universalLayers
  * @param valueQuery
  * @param savedSearch
  * @param thematicLayer
  * @return
  */
 public static String generateLayers(String[] universalLayers, ValueQuery valueQuery, SavedSearch savedSearch, ThematicLayer thematicLayer)
 {
   String sql = valueQuery.getSQL();

   // reload the view for the thematic layer
   String viewName = thematicLayer.getViewName();

   if(thematicLayer.getViewCreated().booleanValue())
   {
     Database.dropView(viewName, sql, false);
   }

   Database.createView(viewName, sql);

   thematicLayer.appLock();
   thematicLayer.setViewCreated(true);
   thematicLayer.apply();

   String sessionId = Session.getCurrentSession().getId();

   GeoHierarchy thematicGeoH = thematicLayer.getGeoHierarchy();
   MdAttributeGeometry geoAttr = thematicGeoH.getGeometry();

   reload(sessionId, viewName, geoAttr);


   // JSON representation of the layers in the map. This is used
   // in Javascript to generated an OpenLayers call to GeoServer.
   JSONArray layers = new JSONArray();

   try
   {
     String geoserverPath = getGeoServerRemoteURL();
     String baseView = QueryConstants.MDSS_NAMESPACE + ":" + viewName.toLowerCase();
     JSONObject baseLayer = new JSONObject();

     String sldFile = formatSLD(thematicLayer);

     baseLayer.put("view", baseView);
     baseLayer.put("sld", sldFile);
     baseLayer.put("geoserverURL", geoserverPath);
     layers.put(baseLayer);
   }
   catch (JSONException e)
   {
     String error = "Unable to define the base (thematic) layer.";
     throw new ProgrammingErrorException(error, e);
   }

   // create views (if needed) for all other layers
   for (String layerId : universalLayers)
   {
     // TODO return null if count(*) == 0 and don't add
     // to layers (do inside createViewTable())
     UniversalLayer layer = UniversalLayer.get(layerId);
     GeoHierarchy geoH = layer.getGeoHierarchy();

     boolean includeLayer = geoH.createViewTable(sessionId);
     if (includeLayer)
     {
       String layerView = layer.getViewName();
       String namespacedView = QueryConstants.MDSS_NAMESPACE + ":" + layerView;

       JSONObject layerObj = new JSONObject();

       try
       {
         String sldFile = formatSLD(layer);


         layerObj.put("view", namespacedView);
         layerObj.put("sld", sldFile);
       }
       catch (JSONException e)
       {
         MdBusiness md = geoH.getGeoEntityClass();
         String error = "Unable to define the layer for [" + md.getDisplayLabel().getValue() + "].";
         throw new ProgrammingErrorException(error, e);
       }

       layers.put(layerObj);
     }
   }

   return layers.toString();
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

 private static final ResourceBundle bundle = ResourceBundle.getBundle("GeoServer");

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

 public static void reload(String sessionId, String viewName, MdAttributeGeometry geoAttr)
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

     String defaultStyle = "";
     if(geoAttr instanceof MdAttributePoint || geoAttr instanceof MdAttributeMultiPoint)
     {
       defaultStyle = "point";
     }
     else if(geoAttr instanceof MdAttributeLineString || geoAttr instanceof MdAttributeMultiLineString)
     {
       defaultStyle = "line";
     }
     else if(geoAttr instanceof MdAttributePolygon || geoAttr instanceof MdAttributeMultiPolygon)
     {
       defaultStyle = "polygon";
     }

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
     printResponse("Create", createCode, createPost, true);
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


}
