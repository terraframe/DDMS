package dss.vector.solutions.odk;

import org.apache.http.HttpEntity;

import com.runwaysdk.constants.CommonProperties;
import com.runwaysdk.generation.loader.Reloadable;

import dss.vector.solutions.etl.EndpointUrl;
import dss.vector.solutions.etl.SecureHttpConnector;
import dss.vector.solutions.etl.dhis2.response.HTTPResponse;
import dss.vector.solutions.geoserver.GeoserverProperties;

public class ODKConnector implements Reloadable
{
  public static HTTPResponse postToOdk(String resourcePath, HttpEntity multipart)
  {
    EndpointUrl endpoint;
    if (GeoserverProperties.isHttps())
    {
      endpoint = new EndpointUrl("https", "127.0.0.1", 8443, CommonProperties.getDeployAppName() + "Mobile");
    }
    else
    {
      endpoint = new EndpointUrl("http", "127.0.0.1", 8080, CommonProperties.getDeployAppName() + "Mobile");
    }
    
    ODKUser user = ODKUser.getUser();
    HTTPResponse resp = new SecureHttpConnector(endpoint, user.getOdkUsername(), user.getOdkPassword()).httpPost(resourcePath, multipart);
    
    if (resp.getResponse().length() == 0)
    {
      throw new RuntimeException("Expected a response from ODK.");
    }
    
    return resp;
  }
  
  public static HTTPResponse getFromOdk(String resourcePath)
  {
    EndpointUrl endpoint;
    if (GeoserverProperties.isHttps())
    {
      endpoint = new EndpointUrl("https", "127.0.0.1", 8443, CommonProperties.getDeployAppName() + "Mobile");
    }
    else
    {
      endpoint = new EndpointUrl("http", "127.0.0.1", 8080, CommonProperties.getDeployAppName() + "Mobile");
    }
    
    ODKUser user = ODKUser.getUser();
    HTTPResponse resp = new SecureHttpConnector(endpoint, user.getOdkUsername(), user.getOdkPassword()).httpGet(resourcePath);
    
    if (resp.getResponse().length() == 0)
    {
      throw new RuntimeException("Expected a response from ODK.");
    }
    
    return resp;
  }
}
