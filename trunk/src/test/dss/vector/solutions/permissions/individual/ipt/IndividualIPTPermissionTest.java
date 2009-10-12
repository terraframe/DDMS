package dss.vector.solutions.permissions.individual.ipt;

import java.util.Calendar;
import java.util.Locale;

import junit.framework.TestCase;

import com.terraframe.mojo.ClientSession;
import com.terraframe.mojo.DoNotWeave;
import com.terraframe.mojo.constants.ClientRequestIF;
import com.terraframe.mojo.dataaccess.transaction.Transaction;
import com.terraframe.mojo.session.StartSession;
import com.terraframe.mojo.web.WebClientSession;

import dss.vector.solutions.Person;
import dss.vector.solutions.TestConstants;
import dss.vector.solutions.TestFixture;
import dss.vector.solutions.geo.generated.HealthFacility;
import dss.vector.solutions.ontology.Term;

public abstract class IndividualIPTPermissionTest extends TestCase implements DoNotWeave
{
  protected static ClientSession   clientSession;

  protected static ClientRequestIF request;

  protected static ClientSession   systemSession;

  protected static ClientRequestIF systemRequest;

  protected static String          geoId;

  protected static String          rolename;

  private static Person            person;

  private static String            username;

  private static String            password = "test";

  private static HealthFacility    facility;

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
    Calendar calendar = Calendar.getInstance();
    calendar.clear();
    calendar.set(1983, 5, 11);

    // Create a test user and assign it to the entomology role
    person = TestFixture.createTestPerson(username, password, rolename);
    facility = TestFixture.createRandomFacility();
    term = TestFixture.createRandomTerm();

    geoId = facility.getGeoId();
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
    facility.delete();
    person.deleteDelegates();
    Person.get(person.getId()).delete();
  }

}
