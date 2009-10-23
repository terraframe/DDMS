package dss.vector.solutions.permissions;

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
import dss.vector.solutions.geo.generated.Country;
import dss.vector.solutions.geo.generated.HealthFacility;
import dss.vector.solutions.geo.generated.SentinelSite;
import dss.vector.solutions.geo.generated.SprayZone;
import dss.vector.solutions.geo.generated.WaterBody;
import dss.vector.solutions.ontology.Term;

public abstract class PermissionTest extends TestCase implements DoNotWeave
{
  protected static ClientSession   clientSession;

  protected static ClientRequestIF request;

  protected static ClientSession   systemSession;

  protected static ClientRequestIF systemRequest;

  protected static String          countryGeoId;

  protected static String          facilityGeoId;

  protected static String          waterGeoId;

  protected static String          siteGeoId;

  protected static String          termId;
  
  protected static String          zoneGeoId;

  private static Person            person;

  private static String            rolename;

  private static String            username;

  private static String            password = "test";

  private static Country           country;

  private static SentinelSite      site;

  private static HealthFacility    facility;

  private static WaterBody         water;

  private static SprayZone         sprayZone;

  private static Term              term;

  protected static void classSetUp(String role)
  {
    rolename = role;
    username = new Long(System.currentTimeMillis()).toString();

    setupVars();

    clientSession = WebClientSession.createUserSession(username, password, Locale.US);
    request = clientSession.getRequest();

    systemSession = WebClientSession.createUserSession("SYSTEM", TestConstants.PASSWORD, Locale.US);
    systemRequest = systemSession.getRequest();
  }

  @StartSession
  @Transaction
  public static void setupVars()
  {
    person = TestFixture.createTestPerson(username, password, rolename);
    country = TestFixture.createRandomCountry();
    site = TestFixture.createRandomSite();
    facility = TestFixture.createRandomFacility();
    water = TestFixture.createRandomWaterBody();
    sprayZone = TestFixture.createRandomZone();
    term = TestFixture.createRandomTerm();

    termId = term.getId();
    countryGeoId = country.getGeoId();
    siteGeoId = site.getGeoId();
    facilityGeoId = facility.getGeoId();
    waterGeoId = water.getGeoId();
    zoneGeoId = sprayZone.getGeoId();
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
    term.delete();
    site.delete();
    facility.delete();
    country.delete();
    water.delete();
    sprayZone.delete();
    
    TestFixture.delete(person);
  }

}
