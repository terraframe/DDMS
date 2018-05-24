package dss.vector.solutions.odk;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;

import com.runwaysdk.business.rbac.RoleDAO;
import com.runwaysdk.business.rbac.RoleDAOIF;
import com.runwaysdk.business.rbac.UserDAO;
import com.runwaysdk.business.rbac.UserDAOIF;
import com.runwaysdk.dataaccess.ProgrammingErrorException;
import com.runwaysdk.dataaccess.transaction.AbortIfProblem;
import com.runwaysdk.generation.loader.Reloadable;
import com.runwaysdk.query.OIterator;
import com.runwaysdk.query.QueryFactory;
import com.runwaysdk.session.Request;

import au.com.bytecode.opencsv.CSVWriter;
import dss.vector.solutions.MDSSUser;
import dss.vector.solutions.MDSSUserQuery;
import dss.vector.solutions.Person;
import dss.vector.solutions.general.SystemURL;
import dss.vector.solutions.permission.PermissionOption;

public class ODKPermissionExporter implements Reloadable
{
  private boolean setup = false;

  public ODKPermissionExporter(boolean setup)
  {
    this.setup = setup;
  }

  public boolean isSetup()
  {
    return setup;
  }

  public void setSetup(boolean setup)
  {
    this.setup = setup;
  }

  @Request
  private void doIt()
  {
    File file = generateCSV();

    try
    {
      submit(file);
    }
    finally
    {
      FileUtils.deleteQuietly(file);
    }
  }

  private File generateCSV()
  {
    try
    {
      File file = File.createTempFile("odk-permission", ".csv");
      CSVWriter writer = new CSVWriter(new FileWriter(file));

      try
      {
        // Write the header
        writer.writeNext(new String[] { "Username", "Full Name", "Account Type", "Data Collector", "Data Viewer", "Form Manager", "Synchronize Tables", "Tables Super-user", "Administer Tables", "Site Administrator" });

        // Write the ddms user
        writer.writeNext(new String[] { "ddms", "ddms", "ODK", "", "", "", "", "", "", "X" });

        if (!this.isSetup())
        {
          SystemURL captureURL = SystemURL.getByName(SystemURL.ODK_DATA_CAPTURE);
          SystemURL adminURL = SystemURL.getByName(SystemURL.ODK_ADMINISTRATOR);

          RoleDAO read = captureURL.getRole(PermissionOption.READ);
          RoleDAO write = captureURL.getRole(PermissionOption.WRITE);
          RoleDAO admin = adminURL.getRole(PermissionOption.WRITE);

          List<? extends MDSSUser> users = this.getUsers();

          for (MDSSUser user : users)
          {
            UserDAOIF userDAO = UserDAO.get(user.getId());
            Set<RoleDAOIF> roles = userDAO.authorizedRoles();

            if (roles != null)
            {
              boolean isRead = roles.contains(read);
              boolean isWrite = roles.contains(write);
              boolean isAdmin = roles.contains(admin);

              if (isRead || isWrite || isAdmin)
              {
                /*
                 * ODK users can not have spaces in them
                 */
                String username = user.getUsername();
                Person person = user.getPerson();
                String fullName = person.getFirstName() + " " + person.getLastName();
                String writeValue = isWrite ? "X" : "";
                String readValue = isRead ? "X" : "";
                String adminValue = isAdmin ? "X" : "";

                if (username.contains(" "))
                {
                  BadODKUsername ex = new BadODKUsername();
                  ex.setUsername(username);

                  throw ex;
                }

                writer.writeNext(new String[] { username, fullName, "ODK", writeValue, readValue, adminValue, adminValue, adminValue, adminValue, adminValue });
              }
            }
          }
        }
      }
      finally
      {
        writer.close();
      }

      return file;
    }
    catch (IOException e)
    {
      throw new ProgrammingErrorException(e);
    }
  }

  @AbortIfProblem
  private String submit(File file)
  {
    // 3. Push the permission csv
    try (CloseableHttpClient client = ODKFacade.getClient())
    {
      MultipartEntityBuilder builder = MultipartEntityBuilder.create();
      builder.addBinaryBody("access_def_file", file);

      HttpPost post = new HttpPost(ODKFacade.getBaseURL() + "/ssl/reset-users-and-permissions");
      post.setEntity(builder.build());

      try (CloseableHttpResponse response = client.execute(post))
      {
        HttpEntity entity = response.getEntity();
        String resp = EntityUtils.toString(entity);

        if (response.getStatusLine().getStatusCode() != 200)
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

  private List<? extends MDSSUser> getUsers()
  {
    MDSSUserQuery query = new MDSSUserQuery(new QueryFactory());
    OIterator<? extends MDSSUser> it = query.getIterator();

    try
    {
      return it.getAll();
    }
    finally
    {
      it.close();
    }
  }

  public static void export()
  {
    export(false);
  }

  public static void export(boolean setup)
  {
    ODKPermissionExporter odkExp = new ODKPermissionExporter(setup);
    odkExp.doIt();
  }

  public static void main(String[] args)
  {
    ODKPermissionExporter.export();
  }

}
