package dss.vector.solutions.permissions.irs.planning;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import junit.framework.TestCase;

import com.terraframe.mojo.ClientSession;
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
import dss.vector.solutions.general.MalariaSeason;
import dss.vector.solutions.geo.generated.SentinelSite;
import dss.vector.solutions.irs.AreaStandardsView;
import dss.vector.solutions.irs.InsecticideBrand;
import dss.vector.solutions.irs.InsecticideNozzle;
import dss.vector.solutions.irs.Nozzle;
import dss.vector.solutions.irs.TargetUnit;
import dss.vector.solutions.mo.ActiveIngredient;

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
    brand.setActiveIngredient(ActiveIngredient.getAllActive()[0]);
    brand.setAmount(30);
    brand.setBrandName(TestConstants.BRAND_NAME);
    brand.setEnabled(true);
    brand.setWeight(new BigDecimal(3.4));
    brand.setSachetsPerRefill(5);
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

  @StartSession
  protected static void tearDownVars()
  {
    site.delete();
    person.deleteDelegates();
    Person.get(person.getId()).delete();
    season.delete();
    configuration.delete();
    brand.delete();
    nozzle.delete();
    standards.deleteConcrete();
  }
}
