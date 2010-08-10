package dss.vector.solutions.permissions.irs.planning;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import junit.framework.TestCase;

import com.runwaysdk.ClientSession;
import com.runwaysdk.constants.ClientRequestIF;
import com.runwaysdk.dataaccess.transaction.Transaction;
import com.runwaysdk.session.Request;
import com.runwaysdk.web.WebClientSession;

import dss.vector.solutions.Person;
import dss.vector.solutions.TestConstants;
import dss.vector.solutions.TestFixture;
import dss.vector.solutions.general.MalariaSeason;
import dss.vector.solutions.geo.generated.SentinelSite;
import dss.vector.solutions.irs.AreaStandardsView;
import dss.vector.solutions.irs.InsecticideBrand;
import dss.vector.solutions.irs.InsecticideBrandConcentrationQualifier;
import dss.vector.solutions.irs.InsecticideBrandUnitQualifier;
import dss.vector.solutions.irs.InsecticideBrandUse;
import dss.vector.solutions.irs.InsecticideNozzle;
import dss.vector.solutions.irs.Nozzle;
import dss.vector.solutions.irs.TargetUnit;
import dss.vector.solutions.ontology.Term;

public abstract class InterventionPlanningPermissionTest extends TestCase
{
  private static Person            person;

  private static String            username;

  private static String            password = "test";

  private static SentinelSite      site;

  private static MalariaSeason     season;

  private static InsecticideBrand  brand;

  private static Nozzle            nozzle;

  private static InsecticideNozzle configuration;

  private static AreaStandardsView standards;

  private static Term              activeIngredient;

  private static Term              productName;

  protected static ClientSession   clientSession;

  protected static ClientRequestIF request;

  protected static String          geoEntityId;

  protected static String          seasonId;

  protected static String          configurationId;

  protected static String          rolename;

  protected static void classSetUp()
  {
    username = new Long(System.currentTimeMillis()).toString();
    setupVars();

    clientSession = WebClientSession.createUserSession(username, password, new Locale[]{Locale.US});
    request = clientSession.getRequest();
  }

  @Request
  @Transaction
  protected static void setupVars()
  {
    Calendar calendar = Calendar.getInstance();
    calendar.clear();
    calendar.set(1983, 5, 11);

    // Create a test user and assign it to the entomology role
    activeIngredient = TestFixture.createRandomTerm();
    productName = TestFixture.createRandomTerm();
    person = TestFixture.createTestPerson(username, password, rolename);
    site = TestFixture.createRandomSite();

    calendar.setTime(new Date());
    calendar.add(Calendar.MONTH, -2);
    Date endDate = calendar.getTime();
    calendar.add(Calendar.MONTH, -2);
    Date startDate = calendar.getTime();

    season = new MalariaSeason();
    season.setStartDate(startDate);
    season.setEndDate(endDate);
    season.setSeasonName(TestConstants.SEASON_NAME);
    season.apply();

    brand = new InsecticideBrand();
    brand.setProductName(productName);
    brand.addInsecticideUse(InsecticideBrandUse.IRS);
    brand.setActiveIngredient(activeIngredient);
    brand.setConcentrationQuantifier(new BigDecimal("4.50"));
    brand.addConcentrationQualifier(InsecticideBrandConcentrationQualifier.PERCENT);
    brand.setUnitQuantifier(new BigDecimal("100"));
    brand.addUnitQualifier(InsecticideBrandUnitQualifier.GRAMS);
    brand.setUnitsPerApplication(20);
    brand.setEnabled(true);
    brand.apply();

    nozzle = new Nozzle();
    nozzle.setDisplayLabel(TestConstants.NOZZLE_NAME);
    nozzle.setRatio(new BigDecimal(2));
    nozzle.apply();

    configuration = brand.addNozzles(nozzle);
    configuration.apply();

    standards = new AreaStandardsView();
    standards.setUnitNozzleAreaCoverage(new Float(250));
    standards.setRoom(new Float(25));
    standards.setStructureArea(new Float(50));
    standards.setHousehold(new Float(100));
    standards.addTargetUnit(TargetUnit.HOUSEHOLD);
    standards.apply();

    geoEntityId = site.getId();
    seasonId = season.getId();
    configurationId = configuration.getId();
  }

  protected static void classTearDown()
  {
    clientSession.logout();

    tearDownVars();
  }

  @Request
  protected static void tearDownVars()
  {
    site.delete();

    TestFixture.delete(person);

    season.delete();
    configuration.delete();
    brand.delete();
    nozzle.delete();
    standards.deleteConcrete();
    activeIngredient.delete();
    productName.delete();
  }
}
