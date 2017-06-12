package dss.vector.solutions.geoserver;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.commons.httpclient.Credentials;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.UsernamePasswordCredentials;
import org.apache.commons.httpclient.auth.AuthScope;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.runwaysdk.generation.loader.Reloadable;
//import com.runwaysdk.gis.constants.GeoserverProperties;
import com.runwaysdk.system.gis.ConnectionException;
import com.runwaysdk.system.gis.InvalidFormatException;

public abstract class AbstractRequest implements Reloadable
{
  private static final String REST_SERVICE = "/gwc/rest/seed/";

  public static final int     SUCCESS      = 200;

  private String              url;

  private int                 zoomStart;

  private int                 zoomStop;

  private String              layer;

  private String              workspace;

  private int                 code;

  public AbstractRequest(String layer, String workspace)
  {
    this.workspace = workspace;
    this.url = GeoserverProperties.getGeoServerLocalURL() + REST_SERVICE + this.workspace + ":" + layer
        + ".json";
    this.zoomStart = GeoserverProperties.getZoomStart();
    this.zoomStop = GeoserverProperties.getZoomStop();
    this.layer = layer;
    this.code = -1;
  }

  /**
   * Returns the type of request.
   * 
   * @return
   */
  protected abstract String getType();

  public int getCode()
  {
    return this.code;
  }

  public String getURL()
  {
    return this.url;
  }

  public boolean doRequest()
  {
    HttpClient client = new HttpClient();

    URL u;
    try
    {
      u = new URL(this.url);
    }
    catch (MalformedURLException e)
    {
      throw new ConnectionException("Could not connect to [" + this.url + "]", e);
    }

    PostMethod post = new PostMethod(url);

    JSONObject seedRequest = new JSONObject();

    JSONObject content = new JSONObject();
    String type = this.getType();
    try
    {
      seedRequest.put("seedRequest", content);
      content.put("name", this.workspace);

      JSONObject bounds = new JSONObject();
      content.put("bounds", bounds);

      double[] bbox = GeoserverFacade.getBBOX(this.layer);
      JSONArray double_ = new JSONArray();
      double_.put(Double.toString(bbox[GeoserverFacade.MINX_INDEX]));
      double_.put(Double.toString(bbox[GeoserverFacade.MINY_INDEX]));
      double_.put(Double.toString(bbox[GeoserverFacade.MAXX_INDEX]));
      double_.put(Double.toString(bbox[GeoserverFacade.MAXY_INDEX]));

      JSONObject coords = new JSONObject();
      coords.put("double", double_);

      bounds.put("coords", coords);

      JSONObject number = new JSONObject();
      number.put("number", GeoserverFacade.SRS_CODE);
      content.put("srs", number);

      content.put("zoomStart", this.zoomStart);
      content.put("zoomStop", this.zoomStop);

      content.put("format", "image/png");

      content.put("type", type);

      content.put("threadCount", "1");

      String body = seedRequest.toString();

      RequestEntity entity = new StringRequestEntity(body, "application/json", "UTF-8");
      post.setRequestEntity(entity);
    }
    catch (JSONException e)
    {
      throw new InvalidFormatException("Error creating JSON for layer ["+this.layer+"] of request type ["+type+"].", e);
    }
    catch (UnsupportedEncodingException e)
    {
      throw new ConnectionException("Invalid encoding when connecting to ["+this.url+"].", e);
    }


//    Credentials defaultcreds = new UsernamePasswordCredentials(GeoserverProperties.getAdminUser(),
//        GeoserverProperties.getAdminPassword());
//    client.getState().setCredentials(new AuthScope(u.getHost(), u.getPort()), defaultcreds);
//    client.getParams().setAuthenticationPreemptive(true);

    try
    {
      this.code = client.executeMethod(post);
    }
    catch (Throwable t)
    {
      throw new ConnectionException("Error executing POST method to ["+this.url+"].", t);
    }

    return this.code == SUCCESS;
  }
}
