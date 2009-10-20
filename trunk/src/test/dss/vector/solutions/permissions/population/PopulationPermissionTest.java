package dss.vector.solutions.permissions.population;

import java.util.Locale;

import junit.framework.TestCase;

import com.terraframe.mojo.ClientSession;
import com.terraframe.mojo.constants.ClientRequestIF;
import com.terraframe.mojo.dataaccess.transaction.Transaction;
import com.terraframe.mojo.session.StartSession;
import com.terraframe.mojo.web.WebClientSession;

import dss.vector.solutions.Person;
import dss.vector.solutions.TestFixture;
import dss.vector.solutions.geo.generated.Country;

public abstract class PopulationPermissionTest extends TestCase
{
  protected static ClientSession   clientSession;

  protected static ClientRequestIF request;

  protected static String          geoId;

  protected static String          rolename;

  protected static String          termId;

  private static Person            person;

  private static String            username;

  private static String            password = "test";

  private static Country           country;

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
    country = TestFixture.createRandomCountry();

    geoId = country.getGeoId();
  }

  protected static void classTearDown()
  {
    clientSession.logout();

    tearDownVars();
  }

  @StartSession
  protected static void tearDownVars()
  {
    country.delete();
    TestFixture.delete(person);
  }

}
