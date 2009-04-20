package dss.vector.solutions.geo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethodBase;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;

import dss.vector.solutions.global.CredentialsSingleton;

/**
 * Reloads GeoServer to make it aware of new database map views.
 */
public class GeoServerReloader
{
  public static void main(String[] args)
  {
    reload();
  }

  private static String getId()
  {
    return "xpi4k4phj8hc8grv6p23od4bv5ee6t4z";
  }

  public static void reload()
  {
    try
    {
      // request a new feature
      PostMethod newPost = new PostMethod("http://127.0.0.1:8080/geoserver/config/data/typeNewSubmit.do");
      newPost.addRequestHeader("Cookie", "JSESSIONID=C361B247973432E278D24286D06CD90C");
      newPost.addParameter(CredentialsSingleton.GLOBAL_SESSION_ID, getId());
      newPost.addParameter("selectedNewFeatureType", "MDSS_maps:::mdsstest"); // Why is :::[lowercase] ?

      HttpClient newClient = new HttpClient();
      int newCode = newClient.executeMethod(newPost);
      printResponse("New", newCode, newPost, false);
      newPost.releaseConnection();

      System.out
          .println("---------------------------------------------------------------------------------");

      // create the feature
      PostMethod createPost = new PostMethod(
          "http://127.0.0.1:8080/geoserver/config/data/typeEditorSubmit.do");
      createPost.addParameter(CredentialsSingleton.GLOBAL_SESSION_ID, getId());
      createPost.addRequestHeader("Cookie", "JSESSIONID=C361B247973432E278D24286D06CD90C");

//      createPost.addParameter("JSESSIONID","C361B247973432E278D24286D06CD90C");

      createPost.addParameter("SRS", "4326");
      createPost.addParameter("abstract", "Generated from MDSS_maps");
      createPost.addParameter("alias", "");
      createPost.addParameter("autoGenerateExtent", "true");
      createPost.addParameter("cacheMaxAge", "");
//      createPost.addParameter("dataMaxX", "36.828452");
//      createPost.addParameter("dataMaxY", "-17.590377");
//      createPost.addParameter("dataMinX", "36.828452");
//      createPost.addParameter("dataMinY", "-17.590377");
      createPost.addParameter("keywords", "MDSS_maps mdsstest");
      createPost.addParameter("maxFeatures", "0");
//      createPost.addParameter("maxX", "36.828452");
//      createPost.addParameter("maxY", "-17.590377");

      createPost.addParameter("metadataLink[0].content", "");
      createPost.addParameter("metadataLink[0].metadataType", "FGDC");
      createPost.addParameter("metadataLink[0].type", "text/plain");
      createPost.addParameter("metadataLink[1].content", "");
      createPost.addParameter("metadataLink[1].metadataType", "FGDC");
      createPost.addParameter("metadataLink[1].type", "text/plain");

//      createPost.addParameter("minX", "36.828452");
//      createPost.addParameter("minY", "-17.590377");
      createPost.addParameter("nameTemplate", "null");
//      createPost.addParameter("nativeMaxX", "36.828452");
//      createPost.addParameter("nativeMaxY", "-17.590377");
//      createPost.addParameter("nativeMinX", "36.828452");
//      createPost.addParameter("nativeMinY", "-17.590377");
      createPost.addParameter("panelStyleIds", "point");
      createPost.addParameter("regionateAttribute", "null");
      createPost.addParameter("regionateFeatureLimit", "15");
      createPost.addParameter("regionateStrategy", "best_guess");
      createPost.addParameter("schemaBase", "--");
      createPost.addParameter("srsHandling", "Force declared SRS (native will be ignored)");
      createPost.addParameter("styleId", "point");
      createPost.addParameter("title", "mdsstest_Type");
      createPost.addParameter("keywords", "");
      createPost.addParameter("wmsPath", "/");

      createPost.addParameter("action", "Submit");

      HttpClient createClient = new HttpClient();
      int createCode = createClient.executeMethod(createPost);
      printResponse("Create", createCode, createPost, false);
      createPost.releaseConnection();

      System.out
          .println("---------------------------------------------------------------------------------");

      // Apply
       PostMethod applyPost = new PostMethod("http://127.0.0.1:8080/geoserver/admin/saveToGeoServer.do");
       applyPost.addRequestHeader("Cookie", "JSESSIONID=C361B247973432E278D24286D06CD90C");
       applyPost.addParameter(CredentialsSingleton.GLOBAL_SESSION_ID, getId());
       applyPost.addParameter("submit", "Apply");

       HttpClient applyClient = new HttpClient();
       int applyResponse = applyClient.executeMethod(applyPost);
       printResponse("Apply", applyResponse, applyPost, false);

       System.out
       .println("---------------------------------------------------------------------------------");

      // Save
      PostMethod savePost = new PostMethod("http://127.0.0.1:8080/geoserver/admin/saveToXML.do");
      savePost.addRequestHeader("Cookie", "JSESSIONID=C361B247973432E278D24286D06CD90C");
      savePost.addParameter(CredentialsSingleton.GLOBAL_SESSION_ID, getId());
      savePost.addParameter("submit", "Save");

      HttpClient saveClient = new HttpClient();
      int saveResponse = saveClient.executeMethod(savePost);
      printResponse("Save", saveResponse, savePost, false);

      System.out
      .println("---------------------------------------------------------------------------------");

      // reload the catalog
      GetMethod get1 = new GetMethod("http://127.0.0.1:8080/geoserver/admin/loadFromXML.do");
      NameValuePair[] params = new NameValuePair[] { new NameValuePair(
          CredentialsSingleton.GLOBAL_SESSION_ID, getId()) };
      get1.setQueryString(params);

      HttpClient client3 = new HttpClient();
      int responseCode3 = client3.executeMethod(get1);
      printResponse("Reload", responseCode3, get1, false);
      get1.releaseConnection();

      System.out
          .println("---------------------------------------------------------------------------------");

      // URL url3 = new
      // URL("http://127.0.0.1:8080/geoserver/admin/loadFromXML.do?"+getId());
      // URLConnection conn3 = url3.openConnection();
      // conn3.connect();
      // InputStream inStream2 = conn3.getInputStream();
      // printDebug(inStream2);

    }
    catch (Exception e)
    {
      e.printStackTrace();
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
        String line = null;
        while ( ( line = reader.readLine() ) != null)
        {
          System.out.println(line);
        }
      }
      finally
      {
        reader.close();
      }
    }
  }
}
