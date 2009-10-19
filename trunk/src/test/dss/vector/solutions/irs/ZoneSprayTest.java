package dss.vector.solutions.irs;

import java.math.BigDecimal;
import java.util.Date;

import junit.extensions.TestSetup;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestResult;
import junit.framework.TestSuite;

import com.terraframe.mojo.dataaccess.cache.DataNotFoundException;

import dss.vector.solutions.Person;
import dss.vector.solutions.TestConstants;
import dss.vector.solutions.TestFixture;
import dss.vector.solutions.geo.generated.GeoEntity;
import dss.vector.solutions.geo.generated.SprayZone;
import dss.vector.solutions.ontology.Term;

public class ZoneSprayTest extends TestCase
{
  @Override
  public TestResult run()
  {
    return super.run();
  }

  @Override
  public void run(TestResult testResult)
  {
    super.run(testResult);
  }

  private static InsecticideBrand brand            = null;

  private static GeoEntity        geoEntity        = null;

  private static SprayTeam        team             = null;

  private static Person           person           = null;

  private static SprayOperator    operator         = null;

  private static SprayLeader      leader           = null;

  private static Term             activeIngredient = null;
  
  private static Term             surfaceType      = null;

  private static Term             sex              = null;

  public static Test suite()
  {
    TestSuite suite = new TestSuite();
    suite.addTestSuite(ZoneSprayTest.class);

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

  protected static void classTearDown()
  {
    leader.delete();
    operator.delete();
    Person.get(person.getId()).delete();
    SprayTeam.get(team.getId()).delete();

    geoEntity.delete();
    brand.delete();
    
    activeIngredient.delete();
    surfaceType.delete();
    sex.delete();    
  }

  protected static void classSetUp()
  {
    activeIngredient = TestFixture.createRandomTerm();
    surfaceType = TestFixture.createRandomTerm();
    sex = TestFixture.createRandomTerm();

    BigDecimal weight = new BigDecimal("4.50");
    Integer refill = new Integer(20);

    brand = new InsecticideBrand();
    brand.setActiveIngredient(activeIngredient);
    brand.setAmount(57);
    brand.setWeight(weight);
    brand.setSachetsPerRefill(refill);
    brand.setBrandName(TestConstants.BRAND_NAME);
    brand.apply();

    geoEntity = new SprayZone();
    geoEntity.setGeoId(TestConstants.GEO_ID);
    geoEntity.setEntityName(TestConstants.GEO_ID);
    geoEntity.apply();

    person = new Person();
    person.setFirstName("Justin");
    person.setLastName("Smethie");
    person.setDateOfBirth(new Date());
    person.apply();

    operator = new SprayOperator();
    operator.setPerson(person);
    operator.setOperatorId(TestConstants.OPERATOR_ID);
    operator.apply();

    leader = new SprayLeader();
    leader.setPerson(person);
    leader.setLeaderId(TestConstants.LEADER_ID);
    leader.apply();

    person.setSprayLeaderDelegate(leader);
    person.setSprayOperatorDelegate(operator);
    person.apply();

    team = new SprayTeam();
    team.setTeamId(TestConstants.TEAM_ID);
    team.apply();

    team.create(geoEntity.getGeoId(), leader.getId(), new String[] { operator.getId() });
  }

  public void testCreate()
  {
    SprayData data = new SprayData();
    data.setBrand(brand);
    data.setGeoEntity(geoEntity);
    data.setSprayDate(new Date());
    data.addSprayMethod(SprayMethod.MAIN_SPRAY);
    data.setSurfaceType(surfaceType);
    data.apply();

    ZoneSpray spray = new ZoneSpray();
    spray.setSprayData(data);
    spray.apply();

    try
    {
      ZoneSpray test = ZoneSpray.get(spray.getId());

      assertNotNull(test);
      assertEquals(data.getId(), test.getSprayData().getId());
    }
    finally
    {
      spray.delete();
    }
  }

  public void testUpdate()
  {
    SprayData data = new SprayData();
    data.setBrand(brand);
    data.setGeoEntity(geoEntity);
    data.setSprayDate(new Date());
    data.addSprayMethod(SprayMethod.MAIN_SPRAY);
    data.setSurfaceType(surfaceType);
    data.apply();

    ZoneSpray spray = new ZoneSpray();
    spray.setSprayData(data);
    spray.apply();

    ZoneSpray edit = ZoneSpray.get(spray.getId());
    edit.apply();

    try
    {
      ZoneSpray test = ZoneSpray.get(spray.getId());

      assertNotNull(test);
      assertEquals(data.getId(), test.getSprayData().getId());
    }
    finally
    {
      edit.delete();
    }
  }

  public void testEditView()
  {
    ZoneSprayView spray = new ZoneSprayView();
    spray.setBrand(brand);
    spray.setGeoEntity(geoEntity);
    spray.setSprayDate(new Date());
    spray.addSprayMethod(SprayMethod.MAIN_SPRAY);
    spray.setSurfaceType(surfaceType);
    spray.apply();

    ZoneSprayView edit = ZoneSpray.lockView(spray.getSprayId());
    edit.apply();

    try
    {
      ZoneSprayView test = ZoneSpray.getView(spray.getSprayId());

      assertNotNull(test);
      assertEquals(spray.getBrand().getId(), test.getBrand().getId());
      assertEquals(spray.getGeoEntity().getId(), test.getGeoEntity().getId());
      assertEquals(spray.getSprayDate(), test.getSprayDate());
      assertEquals(1, test.getSprayMethod().size());
      assertEquals(SprayMethod.MAIN_SPRAY, test.getSprayMethod().get(0));
      
      assertEquals(surfaceType.getId(), test.getSurfaceType().getId());
    }
    finally
    {
      edit.deleteConcrete();
    }
  }

  public void testCreateView()
  {
    ZoneSprayView spray = new ZoneSprayView();
    spray.setBrand(brand);
    spray.setGeoEntity(geoEntity);
    spray.setSprayDate(new Date());
    spray.addSprayMethod(SprayMethod.MAIN_SPRAY);
    spray.setSurfaceType(surfaceType);
    spray.apply();

    try
    {
      ZoneSprayView test = ZoneSpray.getView(spray.getSprayId());

      assertNotNull(test);
      assertEquals(spray.getBrand().getId(), test.getBrand().getId());
      assertEquals(spray.getGeoEntity().getId(), test.getGeoEntity().getId());
      assertEquals(spray.getSprayDate(), test.getSprayDate());
      assertEquals(1, test.getSprayMethod().size());
      assertEquals(SprayMethod.MAIN_SPRAY, test.getSprayMethod().get(0));
      
      assertEquals(surfaceType.getId(), test.getSurfaceType().getId());
    }
    finally
    {
      spray.deleteConcrete();
    }
  }

  public void testDeleteView()
  {
    ZoneSprayView spray = new ZoneSprayView();
    spray.setBrand(brand);
    spray.setGeoEntity(geoEntity);
    spray.setSprayDate(new Date());
    spray.addSprayMethod(SprayMethod.MAIN_SPRAY);
    spray.setSurfaceType(surfaceType);
    spray.apply();

    String id = spray.getSprayId();

    spray.deleteConcrete();

    try
    {
      ZoneSpray.getView(id);

      fail("Unabled to delete the concrete spray operator");
    }
    catch (DataNotFoundException e)
    {
      // This is expected
    }
  }

  public void testSearch()
  {
    Date date = new Date();
    SprayMethod method = SprayMethod.MAIN_SPRAY;

    ZoneSprayView spray = new ZoneSprayView();
    spray.setBrand(brand);
    spray.setGeoEntity(geoEntity);
    spray.setSprayDate(date);
    spray.addSprayMethod(method);
    spray.setSurfaceType(surfaceType);
    spray.apply();

    try
    {
      String geoId = geoEntity.getGeoId();
      ZoneSprayView test = ZoneSprayView.searchBySprayData(geoId, date, method, brand);

      assertNotNull(test);
      assertEquals(spray.getBrand().getId(), test.getBrand().getId());
      assertEquals(spray.getGeoEntity().getId(), test.getGeoEntity().getId());
      assertEquals(spray.getSprayDate(), test.getSprayDate());
      assertEquals(1, test.getSprayMethod().size());
      assertEquals(method, test.getSprayMethod().get(0));
      
      assertEquals(surfaceType.getId(), test.getSurfaceType().getId());
    }
    finally
    {
      spray.deleteConcrete();
    }

  }

  public void testEmptySearch()
  {
    Date date = new Date();
    SprayMethod method = SprayMethod.MAIN_SPRAY;
    String geoId = geoEntity.getGeoId();

    ZoneSprayView spray = ZoneSprayView.searchBySprayData(geoId, date, method, brand);
    assertFalse(spray.hasConcrete());
  }

  public void testDuplicate()
  {
    Date date = new Date();
    SprayMethod method = SprayMethod.MAIN_SPRAY;

    ZoneSprayView spray = new ZoneSprayView();
    spray.setBrand(brand);
    spray.setGeoEntity(geoEntity);
    spray.setSprayDate(date);
    spray.addSprayMethod(method);
    spray.setSurfaceType(surfaceType);
    spray.apply();

    try
    {
      ZoneSprayView duplicate = new ZoneSprayView();
      duplicate.setBrand(brand);
      duplicate.setGeoEntity(geoEntity);
      duplicate.setSprayDate(date);
      duplicate.addSprayMethod(method);
      duplicate.setSurfaceType(surfaceType);
      duplicate.apply();

      duplicate.deleteConcrete();

      fail("Able to create a duplicate Operator Spray View");
    }
    catch (Exception e)
    {
      // This is excepted
    }
    finally
    {
      spray.deleteConcrete();
    }

  }
}
