package dss.vector.solutions.etl.dhis2;

import org.apache.commons.httpclient.NameValuePair;

import com.runwaysdk.generation.loader.Reloadable;

import dss.vector.solutions.etl.HTTPConnector;
import dss.vector.solutions.etl.dhis2.response.HTTPResponse;

public class DHIS2HTTPConnector extends HTTPConnector implements Reloadable
{
  String apiVersion = "26"; // TODO : This should be configurable
  
  public HTTPResponse apiGet(String url, NameValuePair[] params)
  {
    if (!url.contains("?") && !url.endsWith(".json"))
    {
      url = url + ".json";
    }
    
    return httpGet("api/" + apiVersion + "/" + url, params);
  }
  
  public HTTPResponse apiPost(String url, String body)
  {
    if (!url.contains("?") && !url.endsWith(".json"))
    {
      url = url + ".json";
    }
    
    return httpPost("api/" + apiVersion + "/" + url, body);
  }
  
  public void readConfigFromDB()
  {
    DHIS2HTTPConfiguration config = DHIS2HTTPConfiguration.getByKey("DEFAULT");
    this.setServerUrl(config.getUrl());
    this.setCredentials(config.getUsername(), config.getPazzword());
  }
}
