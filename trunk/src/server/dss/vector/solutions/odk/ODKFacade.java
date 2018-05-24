package dss.vector.solutions.odk;

import java.io.File;
import java.security.NoSuchAlgorithmException;

import javax.net.ssl.SSLContext;

import org.apache.http.HttpEntity;
import org.apache.http.StatusLine;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import com.runwaysdk.constants.CommonProperties;

import dss.vector.solutions.geoserver.GeoserverProperties;

public class ODKFacade
{
  public static final String USERNAME = "ddms";

  public static final String PASSWORD = "##odk_ddms1";

  public static boolean isInitialize()
  {
    return ODKProperties.getInitialize();
  }

  public static String getBaseURL()
  {
    if (GeoserverProperties.isHttps())
    {
      return "https://127.0.0.1:8443/" + CommonProperties.getDeployAppName() + "Mobile";
    }

    return "http://127.0.0.1:8080/" + CommonProperties.getDeployAppName() + "Mobile/";
  }

  public static CredentialsProvider getCredentialsProvider()
  {
    return getCredentialsProvider(USERNAME, PASSWORD);
  }

  public static CredentialsProvider getCredentialsProvider(String username, String password)
  {
    CredentialsProvider credsProvider = new BasicCredentialsProvider();
    credsProvider.setCredentials(new AuthScope(AuthScope.ANY_HOST, AuthScope.ANY_PORT), new UsernamePasswordCredentials(username, password));

    return credsProvider;
  }

  public static CloseableHttpClient getClient() throws NoSuchAlgorithmException
  {
    CredentialsProvider provider = getCredentialsProvider();

    return getClient(provider);
  }

  @SuppressWarnings("deprecation")
  public static CloseableHttpClient getClient(CredentialsProvider provider) throws NoSuchAlgorithmException
  {
    if (GeoserverProperties.isHttps())
    {
      SSLConnectionSocketFactory factory = new SSLConnectionSocketFactory(SSLContext.getDefault(), SSLConnectionSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
      HttpClientBuilder builder = HttpClients.custom() //
          .setSSLHostnameVerifier(SSLConnectionSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER) //
          .setDefaultCredentialsProvider(provider) //
          .setSSLSocketFactory(factory);

      return builder.build();
    }

    HttpClientBuilder builder = HttpClients.custom().setDefaultCredentialsProvider(provider);

    return builder.build();
  }

  public static boolean existODK()
  {
    // Attempt to hit the ODK endpoint
    try (CloseableHttpClient client = ODKFacade.getClient())
    {
      RequestConfig config = RequestConfig.custom() //
          .setConnectTimeout(1000) //
          .setConnectionRequestTimeout(1000) //
          .setSocketTimeout(1000) //
          .build();

      HttpGet get = new HttpGet(ODKFacade.getBaseURL());
      get.setConfig(config);

      try (CloseableHttpResponse response = client.execute(get))
      {
        HttpEntity entity = response.getEntity();

        EntityUtils.consume(entity);

        StatusLine statusLine = response.getStatusLine();
        int code = statusLine.getStatusCode();

        if (code == 200)
        {
          return true;
        }

        return false;
      }
    }
    catch (Exception e)
    {
      return false;
    }
  }

  public static String getJDBCProperties()
  {
    return CommonProperties.getDeployRoot() + File.separator + "webapps" + File.separator + CommonProperties.getDeployAppName() + "Mobile" + File.separator + "WEB-INF" + File.separator + "classes" + File.separator + "jdbc.properties";
  }
}
