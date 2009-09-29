package dss.vector.solutions.permissions.itn.household;

import java.util.Calendar;
import java.util.Locale;

import junit.framework.TestCase;

import com.terraframe.mojo.ClientSession;
import com.terraframe.mojo.DoNotWeave;
import com.terraframe.mojo.business.rbac.RoleDAO;
import com.terraframe.mojo.business.rbac.UserDAO;
import com.terraframe.mojo.constants.ClientRequestIF;
import com.terraframe.mojo.dataaccess.transaction.Transaction;
import com.terraframe.mojo.session.StartSession;
import com.terraframe.mojo.web.WebClientSession;

import dss.vector.solutions.MDSSUser;
import dss.vector.solutions.Person;
import dss.vector.solutions.TestConstants;
import dss.vector.solutions.entomology.Sex;
import dss.vector.solutions.geo.generated.SentinelSite;

public abstract class ITNHouseholdSurveyPermissionTest extends TestCase implements DoNotWeave
{
  protected static ClientSession   clientSession;

  protected static ClientRequestIF request;

  protected static String          geoId;

  protected static String          rolename;

  private static Person            person;

  private static String            username;

  private static String            password = "test";

  private static SentinelSite      site;

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
    Calendar calendar = Calendar.getInstance();
    calendar.clear();
    calendar.set(1983, 5, 11);

    // Create a test user and assign it to the entomology role
    person = new Person();
    person.setFirstName("Justin");
    person.setLastName("Smethie");
    person.setDateOfBirth(calendar.getTime());
    person.addSex(Sex.MALE);
    person.apply();
    person.lock();

    // Create MDSS User
    MDSSUser user = new MDSSUser();
    user.setPerson(person);
    user.setUsername(username);
    user.setPassword(password);
    user.apply();

    // Assign the MDSS User to the Entomologist role
    RoleDAO role = RoleDAO.findRole(rolename).getBusinessDAO();
    role.assignMember(UserDAO.get(user.getId()));

    person.setUserDelegate(user);
    person.apply();

    site = new SentinelSite();
    site.setGeoId(TestConstants.GEO_ID);
    site.setEntityName("Test Site");
    site.apply();

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
    person.deleteDelegates();
    Person.get(person.getId()).delete();
  }
}
