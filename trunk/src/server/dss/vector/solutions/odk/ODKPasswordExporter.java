package dss.vector.solutions.odk;

import java.security.NoSuchAlgorithmException;

import org.apache.http.HttpEntity;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;

import com.runwaysdk.dataaccess.ProgrammingErrorException;
import com.runwaysdk.dataaccess.transaction.AbortIfProblem;
import com.runwaysdk.generation.loader.Reloadable;
import com.runwaysdk.session.Request;

import dss.vector.solutions.MDSSUser;

public class ODKPasswordExporter implements Reloadable, Runnable
{

  private String              username;

  private String              password;

  private CredentialsProvider provider;

  public ODKPasswordExporter(MDSSUser user, String password)
  {
    if (user instanceof ODKUser)
    {
      this.username = ( (ODKUser) user ).getOdkUsername();
    }
    else
    {
      this.username = user.getUsername();
    }
    this.password = password;
    this.provider = ODKFacade.getCredentialsProvider();
  }

  public ODKPasswordExporter(String username, String password, CredentialsProvider provider)
  {
    this.username = username;
    this.password = password;
    this.provider = provider;
  }

  @Request

  @Override
  public void run()
  {
    CredentialsInfo credentials = generateCredentials();

    submit(credentials);
  }

  private CredentialsInfo generateCredentials()
  {
    try
    {
      return CredentialsInfoBuilder.build(this.username, this.password);
    }
    catch (NoSuchAlgorithmException e)
    {
      throw new ProgrammingErrorException(e);
    }
  }

  @AbortIfProblem
  private String submit(CredentialsInfo credentials)
  {
    // Update the password
    try (CloseableHttpClient client = ODKFacade.getClient(this.provider))
    {
      URIBuilder builder = new URIBuilder(ODKFacade.getBaseURL() + "/ssl/user-manage-passwords");
      builder.setParameter("username", credentials.getUsername());
      builder.setParameter("basicAuthHash", credentials.getBasicAuthHash());
      builder.setParameter("basicAuthSalt", credentials.getBasicAuthSalt());
      builder.setParameter("digestAuthHash", credentials.getDigestAuthHash());

      try (CloseableHttpResponse response = client.execute(new HttpGet(builder.build())))
      {
        HttpEntity entity = response.getEntity();
        String resp = EntityUtils.toString(entity);

        int code = response.getStatusLine().getStatusCode();

        if (code != 200 && code != 202)
        {
          throw new ProgrammingErrorException("ODK returned an error response: " + resp);
        }

        return resp;
      }
    }
    catch (Exception e)
    {
      throw new ProgrammingErrorException(e);
    }
  }

  public static void export(MDSSUser user, String password)
  {
    ODKPasswordExporter exporter = new ODKPasswordExporter(user, password);
    exporter.run();
  }
}
