package dss.vector.solutions.permissions.administration;

import java.util.Locale;

import junit.framework.TestCase;

import com.terraframe.mojo.ClientSession;
import com.terraframe.mojo.DoNotWeave;
import com.terraframe.mojo.constants.ClientRequestIF;
import com.terraframe.mojo.session.StartSession;
import com.terraframe.mojo.web.WebClientSession;

import dss.vector.solutions.Person;
import dss.vector.solutions.TestConstants;
import dss.vector.solutions.TestFixture;

public abstract class PersonPermissionTest extends TestCase implements DoNotWeave
{
  private static Person            person;

  private static String            username;

  private static String            password = "test";

  protected static ClientSession   clientSession;

  protected static ClientRequestIF request;

  protected static ClientSession   systemSession;

  protected static ClientRequestIF systemRequest;

  protected static String          rolename;

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
    TestFixture.delete(person);
  }

}
