package dss.vector.solutions.geo;

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

import com.terraframe.mojo.system.gis.metadata.MdAttributeGeometry;
import com.terraframe.mojo.system.gis.metadata.MdAttributeLineString;
import com.terraframe.mojo.system.gis.metadata.MdAttributeMultiLineString;
import com.terraframe.mojo.system.gis.metadata.MdAttributeMultiPoint;
import com.terraframe.mojo.system.gis.metadata.MdAttributeMultiPolygon;
import com.terraframe.mojo.system.gis.metadata.MdAttributePoint;
import com.terraframe.mojo.system.gis.metadata.MdAttributePolygon;

import dss.vector.solutions.global.CredentialsSingleton;
import dss.vector.solutions.query.QueryConstants;


/**
 * Reloads GeoServer to make it aware of new database map views.
 */
public class GeoServerReloader
{
  private static final Pattern JSESSIONID_PATTERN = Pattern.compile("(?:.*?)JSESSIONID=(\\w*);.*");

  private static final ResourceBundle bundle = ResourceBundle.getBundle("GeoServer");

  public static void reload(String sessionId, String viewName, MdAttributeGeometry geoAttr)
  {
    try
    {
      String geoserverPath = bundle.getString("geoserver.path");

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

      System.out
      .println("---------------------------------------------------------------------------------");

      // request a new feature
      PostMethod newPost = new PostMethod(geoserverPath+"/config/data/typeNewSubmit.do");
      newPost.addRequestHeader("Cookie", "JSESSIONID="+jSessionId);
      newPost.addParameter(CredentialsSingleton.GLOBAL_SESSION_ID, sessionId);
      newPost.addParameter("selectedNewFeatureType", QueryConstants.FEATURE_NAMESPACE+":::"+viewName.toLowerCase());

      HttpClient newClient = new HttpClient();
      int newCode = newClient.executeMethod(newPost);
      printResponse("New", newCode, newPost, false);
      newPost.releaseConnection();

      System.out
          .println("---------------------------------------------------------------------------------");

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
