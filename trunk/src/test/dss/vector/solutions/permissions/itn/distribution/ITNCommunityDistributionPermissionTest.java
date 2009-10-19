package dss.vector.solutions.permissions.itn.distribution;

import java.util.Locale;

import junit.framework.TestCase;

import com.terraframe.mojo.ClientSession;
import com.terraframe.mojo.DoNotWeave;
import com.terraframe.mojo.constants.ClientRequestIF;
import com.terraframe.mojo.dataaccess.transaction.Transaction;
import com.terraframe.mojo.session.StartSession;
import com.terraframe.mojo.web.WebClientSession;

import dss.vector.solutions.Person;
import dss.vector.solutions.TestFixture;
import dss.vector.solutions.geo.generated.SentinelSite;

public abstract class ITNCommunityDistributionPermissionTest extends TestCase implements DoNotWeave
{
  private static Person            person;

  private static String            username;

  private static String            password = "test";

  private static SentinelSite      site;

  protected static ClientSession   clientSession;

  protected static ClientRequestIF request;

  protected static String          geoId;

  protected static String          rolename;

  protected static void classSetUp()
  {
    username = new Long(System.currentTimeMillis()).toString();
    setupVars();

    clientSession = WebClientSession.createUserSession(username, password, Locale.US);
    request = clientSession.getRequest();
  }

  @StartSession
  @Transaction
  protected static void setupVars()
  {
    person = TestFixture.createTestPerson(username, password, rolename);
    site = TestFixture.createRandomSite();
    
    geoId = site.getGeoId();
  }

  protected static void classTearDown()
  {
    clientSession.logout();

    tearDownVars();
  }

  @StartSession
  protected static void tearDownVars()
  {
    site.delete();
    TestFixture.delete(person);
  }

}
