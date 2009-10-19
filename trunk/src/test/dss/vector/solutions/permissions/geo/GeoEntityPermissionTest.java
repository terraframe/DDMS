package dss.vector.solutions.permissions.geo;

import java.util.Locale;

import com.terraframe.mojo.ClientSession;
import com.terraframe.mojo.constants.ClientRequestIF;
import com.terraframe.mojo.dataaccess.transaction.Transaction;
import com.terraframe.mojo.session.StartSession;
import com.terraframe.mojo.web.WebClientSession;

import dss.vector.solutions.Person;
import dss.vector.solutions.TestConstants;
import dss.vector.solutions.TestFixture;
import dss.vector.solutions.geo.generated.SentinelSite;
import dss.vector.solutions.geo.generated.WaterBody;
import dss.vector.solutions.ontology.Term;
import junit.framework.TestCase;

public class GeoEntityPermissionTest extends TestCase
{
  protected static ClientSession   clientSession;

  protected static ClientRequestIF request;

  protected static ClientSession   systemSession;

  protected static ClientRequestIF systemRequest;

  protected static String          geoId;

  protected static String          waterId;

  protected static String          rolename;

  private static Person            person;

  private static String            username;

  private static String            password = "test";

  private static SentinelSite      site;

  private static WaterBody         waterBody;

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
  @Transaction
  protected static void setupVars()
  {
    person = TestFixture.createTestPerson(username, password, rolename);
    site = TestFixture.createRandomSite();
    waterBody = TestFixture.createRandomPermanentWaterBody();
    term = TestFixture.createRandomTerm();

    geoId = site.getId();
    waterId = waterBody.getId();
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
    waterBody.delete();
    term.delete();

    TestFixture.delete(person);
  }
}
