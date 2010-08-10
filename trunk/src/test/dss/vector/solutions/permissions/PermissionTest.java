package dss.vector.solutions.permissions;

import java.util.Locale;

import junit.framework.TestCase;

import com.runwaysdk.ClientSession;
import com.runwaysdk.DoNotWeave;
import com.runwaysdk.constants.ClientRequestIF;
import com.runwaysdk.dataaccess.transaction.Transaction;
import com.runwaysdk.session.Request;
import com.runwaysdk.web.WebClientSession;

import dss.vector.solutions.Person;
import dss.vector.solutions.TestConstants;
import dss.vector.solutions.TestFixture;
import dss.vector.solutions.geo.generated.CollectionSite;
import dss.vector.solutions.geo.generated.Country;
import dss.vector.solutions.geo.generated.HealthFacility;
import dss.vector.solutions.geo.generated.SentinelSite;
import dss.vector.solutions.geo.generated.SprayZone;
import dss.vector.solutions.geo.generated.StockDepot;
import dss.vector.solutions.ontology.Term;

public abstract class PermissionTest extends TestCase implements DoNotWeave
{
  protected static ClientSession   clientSession;

  protected static ClientRequestIF request;

  protected static ClientSession   systemSession;

  protected static ClientRequestIF systemRequest;

  protected static String          countryGeoId;

  protected static String          facilityGeoId;

  protected static String          collectionSiteGeoId;

  protected static String          siteGeoId;

  protected static String          termId;

  protected static String          zoneGeoId;

  protected static String          depotGeoId;

  private static Person            person;

  private static String            rolename;

  private static String            username;

  private static String            password = "test";

  private static Country           country;

  private static SentinelSite      site;

  private static HealthFacility    facility;

  private static CollectionSite    collectionSite;

  private static SprayZone         sprayZone;

  private static StockDepot        depot;

  private static Term              term;

  protected static void classSetUp(String role)
  {
    rolename = role;
    username = new Long(System.currentTimeMillis()).toString();

    setupVars();

    clientSession = WebClientSession.createUserSession(username, password, new Locale[]{Locale.US});
    request = clientSession.getRequest();

    systemSession = WebClientSession.createUserSession("SYSTEM", TestConstants.PASSWORD, new Locale[]{Locale.US});
    systemRequest = systemSession.getRequest();
  }

  @Request
  @Transaction
  public static void setupVars()
  {
    person = TestFixture.createTestPerson(username, password, rolename);
    country = TestFixture.createRandomCountry();
    site = TestFixture.createRandomSite();
    facility = TestFixture.createRandomFacility();
    collectionSite = TestFixture.createRandomWaterBody();
    sprayZone = TestFixture.createRandomZone();
    depot = TestFixture.createRandomDepot();
    term = TestFixture.createRandomTerm();

    termId = term.getId();
    countryGeoId = country.getGeoId();
    siteGeoId = site.getGeoId();
    facilityGeoId = facility.getGeoId();
    collectionSiteGeoId = collectionSite.getGeoId();
    zoneGeoId = sprayZone.getGeoId();
    depotGeoId = depot.getGeoId();
  }

  protected static void classTearDown()
  {
    clientSession.logout();

    systemSession.logout();

    tearDownVars();
  }

  @Request
  protected static void tearDownVars()
  {
    term.delete();
    site.delete();
    facility.delete();
    country.delete();
    collectionSite.delete();
    sprayZone.delete();
    depot.delete();

    TestFixture.delete(person);
  }

}
