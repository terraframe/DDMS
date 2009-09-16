package dss.vector.solutions.permissions.aggregated.ipt;

import java.util.Calendar;
import java.util.Locale;

import junit.extensions.TestSetup;
import junit.framework.Test;
import junit.framework.TestSuite;

import com.terraframe.mojo.business.rbac.RoleDAO;
import com.terraframe.mojo.business.rbac.UserDAO;
import com.terraframe.mojo.session.StartSession;
import com.terraframe.mojo.web.WebClientSession;

import dss.vector.solutions.MDSSRoleInfo;
import dss.vector.solutions.MDSSUser;
import dss.vector.solutions.Person;
import dss.vector.solutions.TestConstants;
import dss.vector.solutions.entomology.Sex;
import dss.vector.solutions.geo.generated.Hospital;

public class E_AggregatedIPTNoPermissions extends AggregatedIPTNoPermissions
{
  private static Person          person;

  private static String          username;

  private static String          password = "test";

  private static Hospital site;

  public static Test suite()
  {
    TestSuite suite = new TestSuite();
    suite.addTestSuite(E_AggregatedIPTNoPermissions.class);

    TestSetup wrapper = new TestSetup(suite)
    {
      protected void setUp()
      {
        classSetUp();
      }

      protected void tearDown()
      {
        classTearDown();
      }

    };

    return wrapper;
  }

  protected static void classSetUp()
  {
    username = new Long(System.currentTimeMillis()).toString();
    setupVars();

    clientSession = WebClientSession.createUserSession(username, password, Locale.US);
    request = clientSession.getRequest();
  }

  @StartSession
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

    // Create MDSS User
    MDSSUser user = new MDSSUser();
    user.setPerson(person);
    user.setUsername(username);
    user.setPassword(password);
    user.apply();

    // Assign the MDSS User to the Entomologist role
    RoleDAO role = RoleDAO.findRole(MDSSRoleInfo.ENTOMOLOGIST).getBusinessDAO();
    role.assignMember(UserDAO.get(user.getId()));

    person.setUserDelegate(user);
    person.apply();

    site = new Hospital();
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
