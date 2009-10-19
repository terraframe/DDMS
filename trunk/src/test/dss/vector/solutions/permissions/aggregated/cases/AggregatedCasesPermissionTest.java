package dss.vector.solutions.permissions.aggregated.cases;

import java.util.Locale;

import junit.framework.TestCase;

import com.terraframe.mojo.ClientSession;
import com.terraframe.mojo.constants.ClientRequestIF;
import com.terraframe.mojo.session.StartSession;
import com.terraframe.mojo.web.WebClientSession;

import dss.vector.solutions.Person;
import dss.vector.solutions.TestConstants;
import dss.vector.solutions.TestFixture;
import dss.vector.solutions.geo.generated.SentinelSite;
import dss.vector.solutions.ontology.Term;

public abstract class AggregatedCasesPermissionTest extends TestCase
{
  private static Person            person;

  private static String            username;

  private static String            password = "test";

  protected static String          rolename;

  protected static ClientSession   clientSession;

  protected static ClientRequestIF request;

  protected static ClientSession   systemSession;

  protected static ClientRequestIF systemRequest;

  protected static String          geoId;

  private static SentinelSite      site;

  private static Term              term;

  protected static String          termId;

  protected static void classSetUp()
  {
    username = new Long(System.currentTimeMillis()).toString();
    setupVars();

    clientSession = WebClientSession.createUserSession(username, password, Locale.US);
    request = clientSession.getRequest();

    systemSession = WebClientSession.createUserSession("SYSTEM", TestConstants.PASSWORD, Locale.US);
    systemRequest = systemSession.getRequest();
  }

  @StartSession
  protected static void setupVars()
  {
    // Create a test user and assign it to the entomology role
    person = TestFixture.createTestPerson(username, password, rolename);

    site = TestFixture.createRandomSite();
    term = TestFixture.createRandomTerm();

    geoId = site.getId();
    termId = term.getId();

  }

  protected static void classTearDown()
  {
    clientSession.logout();
    systemSession.logout();

    tearDownVars();
  }

  @StartSession
  protected static void tearDownVars()
  {
    site.delete();
    term.delete();

    TestFixture.delete(person);
  }

}
