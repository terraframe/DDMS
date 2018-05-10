package dss.vector.solutions.etl;

import java.io.IOException;
import java.io.InputStream;
import java.security.NoSuchAlgorithmException;

import javax.net.ssl.SSLContext;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import dss.vector.solutions.etl.dhis2.response.HTTPResponse;
import dss.vector.solutions.geoserver.GeoserverProperties;

public class SecureHttpConnector
{
  CloseableHttpClient client;
  
  Logger logger = LoggerFactory.getLogger(SecureHttpConnector.class);
  
  EndpointUrl endpoint;
  
  String username;
  
  String password;
  
  public SecureHttpConnector(EndpointUrl endpoint, String username, String password)
  {
    this.setEndpointUrl(endpoint);
    this.setCredentials(username, password);
  }
  
  public void setCredentials(String username, String password)
  {
    this.username = username;
    this.password = password;
  }
  
  public EndpointUrl getEndpointUrl()
  {
    return endpoint;
  }
  
  public void setEndpointUrl(EndpointUrl url)
  {
    this.endpoint = url;
  }
  
  synchronized public void initialize()
  {
    try
    {
      CredentialsProvider credsProvider = new BasicCredentialsProvider();
      credsProvider.setCredentials(new AuthScope(AuthScope.ANY_HOST, AuthScope.ANY_PORT), new UsernamePasswordCredentials(this.username, this.password));
      
      if (this.endpoint.getProtocol().equals("https"))
      {
        SSLConnectionSocketFactory factory = new SSLConnectionSocketFactory(SSLContext.getDefault(),SSLConnectionSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
        client = HttpClients.custom()
            .setSSLHostnameVerifier(SSLConnectionSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER)
            .setDefaultCredentialsProvider(credsProvider)
            .setSSLSocketFactory(factory).build();
      }
      else
      {
        client = HttpClients.custom().setDefaultCredentialsProvider(credsProvider).build();
      }
    }
    catch (NoSuchAlgorithmException e)
    {
      throw new RuntimeException(e);
    }
  }
  
  public boolean isInitialized()
  {
    return client != null;
  }
  
  public HTTPResponse httpPost(String resourcePath, HttpEntity multipart)
  {
    try {
      if (!isInitialized())
      {
        initialize();
      }
      
      this.endpoint.setResourcePath(resourcePath);
      HttpPost post = new HttpPost(this.endpoint.toString());

      try
      {
        if (multipart != null)
        {
          post.setEntity(multipart);
        }
        CloseableHttpResponse response = client.execute(post);
        
        try
        {
          HttpEntity responseEntity = response.getEntity();
          InputStream is = responseEntity.getContent();
          String htmlResp = IOUtils.toString(is, "UTF-8");
          
          if (htmlResp.length() == 0)
          {
            throw new RuntimeException("Expected a response.");
          }
          
          return new HTTPResponse(htmlResp, response.getStatusLine().getStatusCode());
        }
        finally
        {
          response.close();
        }
      }
      finally
      {
        client.close();
      }
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
  
  public HTTPResponse httpGet(String resourcePath)
  {
    try {
      if (!isInitialized())
      {
        initialize();
      }
      
      this.endpoint.setResourcePath(resourcePath);
      HttpGet get = new HttpGet(this.endpoint.toString());

      try
      {
        CloseableHttpResponse response = client.execute(get);
        
        try
        {
          HttpEntity responseEntity = response.getEntity();
          InputStream is = responseEntity.getContent();
          String htmlResp = IOUtils.toString(is, "UTF-8");
          
          if (htmlResp.length() == 0)
          {
            throw new RuntimeException("Expected a response.");
          }
          
          return new HTTPResponse(htmlResp, response.getStatusLine().getStatusCode());
        }
        finally
        {
          response.close();
        }
      }
      finally
      {
        client.close();
      }
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
}
