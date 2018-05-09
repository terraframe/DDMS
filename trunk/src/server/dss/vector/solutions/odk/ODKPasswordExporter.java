package dss.vector.solutions.odk;

import java.security.NoSuchAlgorithmException;

import org.apache.http.HttpEntity;
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

public class ODKPasswordExporter implements Reloadable
{

  private MDSSUser user;

  private String   password;

  public ODKPasswordExporter(MDSSUser user, String password)
  {
    this.user = user;
    this.password = password;
  }

  @Request
  private void doIt()
  {
    CredentialsInfo credentials = generateCredentials();

    submit(credentials);
  }

  private CredentialsInfo generateCredentials()
  {
    try
    {
      return CredentialsInfoBuilder.build(this.user.getUsername().replaceAll(" ", ""), this.password);
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
    try (CloseableHttpClient client = ODKFacade.getClient())
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

  @Request
  public static void export()
  {
    MDSSUser user = MDSSUser.getByKey("Users.test");

    ODKPasswordExporter.export(user, "test");
  }

  public static void export(MDSSUser user, String password)
  {
    ODKPasswordExporter exporter = new ODKPasswordExporter(user, password);
    exporter.doIt();
  }

  public static void main(String[] args)
  {
    ODKPasswordExporter.export();
  }
}
