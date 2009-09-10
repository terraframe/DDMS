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
import dss.vector.solutions.entomology.Sex;
import dss.vector.solutions.geo.generated.GeoEntity;
import dss.vector.solutions.geo.generated.SentinelSite;
import dss.vector.solutions.mo.ActiveIngredient;

public class OperatorSprayTest extends TestCase
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

  private static InsecticideBrand brand     = null;

  private static GeoEntity        geoEntity = null;

  private static SprayOperator    operator  = null;

  private static Person           person    = null;

  private static SprayTeam        team      = null;

  private static SprayLeader      leader    = null;

  public static Test suite()
  {
    TestSuite suite = new TestSuite();
    suite.addTestSuite(OperatorSprayTest.class);

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
  }

  protected static void classSetUp()
  {
    ActiveIngredient activeIngredient = ActiveIngredient.getAll()[0];
    BigDecimal weight = new BigDecimal("4.50");
    Integer refill = new Integer(20);

    brand = new InsecticideBrand();
    brand.setActiveIngredient(activeIngredient);
    brand.setAmount(57);
    brand.setWeight(weight);
    brand.setSachetsPerRefill(refill);
    brand.setBrandName(TestConstants.BRAND_NAME);
    brand.apply();

    geoEntity = new SentinelSite();
    geoEntity.setGeoId(TestConstants.GEO_ID);
    geoEntity.setEntityName("Sentinel Site");
    geoEntity.apply();

    person = new Person();
    person.addSex(Sex.MALE);
    person.setDateOfBirth(new Date());
    person.setFirstName("Justin");
    person.setLastName("Smethie");
    person.apply();

    operator = new SprayOperator();
    operator.setOperatorId(TestConstants.OPERATOR_ID);
    operator.setPerson(person);
    operator.apply();

    leader = new SprayLeader();
    leader.setLeaderId(TestConstants.LEADER_ID);
    leader.setPerson(person);
    leader.apply();

    person.setSprayLeaderDelegate(leader);
    person.setSprayOperatorDelegate(operator);
    person.apply();

    team = new SprayTeam();
    team.setTeamId(TestConstants.TEAM_ID);
    team.apply();
  }

  public void testCreate()
  {
    SprayData data = new SprayData();
    data.setBrand(brand);
    data.setGeoEntity(geoEntity);
    data.setSprayDate(new Date());
    data.addSprayMethod(SprayMethod.MAIN_SPRAY);
    data.addSurfaceType(SurfaceType.POROUS);
    data.apply();

    OperatorSpray spray = new OperatorSpray();
    spray.setSprayData(data);
    spray.setOperatorSprayWeek(2);
    spray.setReceived(2);
    spray.setRefills(3);
    spray.setReturned(2);
    spray.setSprayOperator(operator);
    spray.setTarget(232);
    spray.setTeamSprayWeek(24);
    spray.setUsed(3);
    spray.setTeamLeader(operator);
    spray.apply();

    try
    {
      OperatorSpray test = OperatorSpray.get(spray.getId());

      assertNotNull(test);
      assertEquals(data.getId(), test.getSprayData().getId());
      assertEquals(spray.getOperatorSprayWeek(), test.getOperatorSprayWeek());
      assertEquals(spray.getReceived(), test.getReceived());
      assertEquals(spray.getRefills(), test.getRefills());
      assertEquals(spray.getReturned(), test.getReturned());
      assertEquals(operator.getId(), test.getSprayOperator().getId());
      assertEquals(operator.getId(), test.getTeamLeader().getId());
      assertEquals(spray.getTarget(), test.getTarget());
      assertEquals(spray.getTeamSprayWeek(), test.getTeamSprayWeek());
      assertEquals(spray.getUsed(), test.getUsed());
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
    data.addSurfaceType(SurfaceType.POROUS);
    data.apply();

    OperatorSpray spray = new OperatorSpray();
    spray.setSprayData(data);
    spray.setOperatorSprayWeek(2);
    spray.setReceived(2);
    spray.setRefills(3);
    spray.setReturned(2);
    spray.setSprayOperator(operator);
    spray.setTarget(232);
    spray.setTeamSprayWeek(24);
    spray.setUsed(3);
    spray.setTeamLeader(operator);
    spray.apply();

    OperatorSpray edit = OperatorSpray.get(spray.getId());
    edit.setOperatorSprayWeek(4);
    edit.setReceived(1);
    edit.setRefills(5);
    edit.setReturned(6);
    edit.setTarget(23);
    edit.setTeamSprayWeek(12);
    edit.setUsed(3);
    edit.apply();

    try
    {
      OperatorSpray test = OperatorSpray.get(spray.getId());

      assertNotNull(test);
      assertEquals(data.getId(), test.getSprayData().getId());
      assertEquals(edit.getOperatorSprayWeek(), test.getOperatorSprayWeek());
      assertEquals(edit.getReceived(), test.getReceived());
      assertEquals(edit.getRefills(), test.getRefills());
      assertEquals(edit.getReturned(), test.getReturned());
      assertEquals(operator.getId(), test.getSprayOperator().getId());
      assertEquals(operator.getId(), test.getTeamLeader().getId());
      assertEquals(edit.getTarget(), test.getTarget());
      assertEquals(edit.getTeamSprayWeek(), test.getTeamSprayWeek());
      assertEquals(edit.getUsed(), test.getUsed());
    }
    finally
    {
      edit.delete();
    }
  }

  public void testEditView()
  {
    OperatorSprayView spray = new OperatorSprayView();
    spray.setBrand(brand);
    spray.setGeoEntity(geoEntity);
    spray.setSprayDate(new Date());
    spray.addSprayMethod(SprayMethod.MAIN_SPRAY);
    spray.addSurfaceType(SurfaceType.POROUS);
    spray.setOperatorSprayWeek(2);
    spray.setReceived(2);
    spray.setRefills(3);
    spray.setReturned(2);
    spray.setSprayOperator(operator);
    spray.setTeamLeader(operator);
    spray.setTarget(232);
    spray.setTeamSprayWeek(24);
    spray.setUsed(3);
    spray.apply();

    OperatorSprayView edit = OperatorSpray.lockView(spray.getSprayId());
    edit.setOperatorSprayWeek(4);
    edit.setReceived(1);
    edit.setRefills(2);
    edit.setReturned(4);
    edit.setTarget(43);
    edit.setTeamSprayWeek(1);
    edit.setUsed(43);
    edit.apply();

    try
    {
      OperatorSprayView test = OperatorSpray.getView(spray.getSprayId());

      assertNotNull(test);
      assertEquals(edit.getBrand().getId(), test.getBrand().getId());
      assertEquals(edit.getGeoEntity().getId(), test.getGeoEntity().getId());
      assertEquals(edit.getSprayDate(), test.getSprayDate());
      assertEquals(1, test.getSprayMethod().size());
      assertEquals(SprayMethod.MAIN_SPRAY, test.getSprayMethod().get(0));
      assertEquals(1, test.getSurfaceType().size());
      assertEquals(SurfaceType.POROUS, test.getSurfaceType().get(0));
      assertEquals(edit.getOperatorSprayWeek(), test.getOperatorSprayWeek());
      assertEquals(edit.getReceived(), test.getReceived());
      assertEquals(edit.getRefills(), test.getRefills());
      assertEquals(edit.getReturned(), test.getReturned());
      assertEquals(operator.getId(), test.getSprayOperator().getId());
      assertEquals(operator.getId(), test.getTeamLeader().getId());
      assertEquals(edit.getTarget(), test.getTarget());
      assertEquals(edit.getTeamSprayWeek(), test.getTeamSprayWeek());
      assertEquals(edit.getUsed(), test.getUsed());
    }
    finally
    {
      edit.deleteConcrete();
    }
  }

  public void testCreateView()
  {
    OperatorSprayView spray = new OperatorSprayView();
    spray.setBrand(brand);
    spray.setGeoEntity(geoEntity);
    spray.setSprayDate(new Date());
    spray.addSprayMethod(SprayMethod.MAIN_SPRAY);
    spray.addSurfaceType(SurfaceType.POROUS);
    spray.setOperatorSprayWeek(2);
    spray.setReceived(2);
    spray.setRefills(3);
    spray.setReturned(2);
    spray.setSprayOperator(operator);
    spray.setTeamLeader(operator);
    spray.setTarget(232);
    spray.setTeamSprayWeek(24);
    spray.setUsed(3);
    spray.apply();

    try
    {
      OperatorSprayView test = OperatorSpray.getView(spray.getSprayId());

      assertNotNull(test);
      assertEquals(spray.getBrand().getId(), test.getBrand().getId());
      assertEquals(spray.getGeoEntity().getId(), test.getGeoEntity().getId());
      assertEquals(spray.getSprayDate(), test.getSprayDate());
      assertEquals(1, test.getSprayMethod().size());
      assertEquals(SprayMethod.MAIN_SPRAY, test.getSprayMethod().get(0));
      assertEquals(1, test.getSurfaceType().size());
      assertEquals(SurfaceType.POROUS, test.getSurfaceType().get(0));
      assertEquals(spray.getOperatorSprayWeek(), test.getOperatorSprayWeek());
      assertEquals(spray.getReceived(), test.getReceived());
      assertEquals(spray.getRefills(), test.getRefills());
      assertEquals(spray.getReturned(), test.getReturned());
      assertEquals(operator.getId(), test.getSprayOperator().getId());
      assertEquals(operator.getId(), test.getTeamLeader().getId());
      assertEquals(spray.getTarget(), test.getTarget());
      assertEquals(spray.getTeamSprayWeek(), test.getTeamSprayWeek());
      assertEquals(spray.getUsed(), test.getUsed());
    }
    finally
    {
      spray.deleteConcrete();
    }
  }

  public void testDeleteView()
  {
    OperatorSprayView spray = new OperatorSprayView();
    spray.setBrand(brand);
    spray.setGeoEntity(geoEntity);
    spray.setSprayDate(new Date());
    spray.addSprayMethod(SprayMethod.MAIN_SPRAY);
    spray.addSurfaceType(SurfaceType.POROUS);
    spray.setOperatorSprayWeek(2);
    spray.setReceived(2);
    spray.setRefills(3);
    spray.setReturned(2);
    spray.setSprayOperator(operator);
    spray.setTeamLeader(operator);
    spray.setTarget(232);
    spray.setTeamSprayWeek(24);
    spray.setUsed(3);
    spray.apply();

    String id = spray.getSprayId();

    spray.deleteConcrete();

    try
    {
      OperatorSpray.getView(id);

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

    OperatorSprayView spray = new OperatorSprayView();
    spray.setBrand(brand);
    spray.setGeoEntity(geoEntity);
    spray.setSprayDate(date);
    spray.addSprayMethod(method);
    spray.addSurfaceType(SurfaceType.POROUS);
    spray.setOperatorSprayWeek(2);
    spray.setReceived(2);
    spray.setRefills(3);
    spray.setReturned(2);
    spray.setSprayOperator(operator);
    spray.setTeamLeader(operator);
    spray.setTarget(232);
    spray.setTeamSprayWeek(24);
    spray.setUsed(3);
    spray.apply();

    try
    {
      String geoId = geoEntity.getGeoId();
      OperatorSprayView test = OperatorSprayView.searchBySprayData(geoId, date, method, brand, operator.getId());

      assertNotNull(test);
      assertEquals(spray.getBrand().getId(), test.getBrand().getId());
      assertEquals(spray.getGeoEntity().getId(), test.getGeoEntity().getId());
      assertEquals(spray.getSprayDate(), test.getSprayDate());
      assertEquals(1, test.getSprayMethod().size());
      assertEquals(method, test.getSprayMethod().get(0));
      assertEquals(1, test.getSurfaceType().size());
      assertEquals(SurfaceType.POROUS, test.getSurfaceType().get(0));
      assertEquals(spray.getOperatorSprayWeek(), test.getOperatorSprayWeek());
      assertEquals(spray.getReceived(), test.getReceived());
      assertEquals(spray.getRefills(), test.getRefills());
      assertEquals(spray.getReturned(), test.getReturned());
      assertEquals(operator.getId(), test.getSprayOperator().getId());
      assertEquals(operator.getId(), test.getTeamLeader().getId());
      assertEquals(spray.getTarget(), test.getTarget());
      assertEquals(spray.getTeamSprayWeek(), test.getTeamSprayWeek());
      assertEquals(spray.getUsed(), test.getUsed());
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

    OperatorSprayView spray = OperatorSprayView.searchBySprayData(geoId, date, method, brand, operator.getId());
    assertFalse(spray.hasConcrete());
  }

  public void testDuplicate()
  {
    OperatorSprayView spray = new OperatorSprayView();
    spray.setBrand(brand);
    spray.setGeoEntity(geoEntity);
    spray.setSprayDate(new Date());
    spray.addSprayMethod(SprayMethod.MAIN_SPRAY);
    spray.addSurfaceType(SurfaceType.POROUS);
    spray.setOperatorSprayWeek(2);
    spray.setReceived(2);
    spray.setRefills(3);
    spray.setReturned(2);
    spray.setSprayOperator(operator);
    spray.setTeamLeader(operator);
    spray.setTarget(232);
    spray.setTeamSprayWeek(24);
    spray.setUsed(3);
    spray.apply();

    try
    {
      OperatorSprayView duplicate = new OperatorSprayView();
      duplicate.setBrand(brand);
      duplicate.setGeoEntity(geoEntity);
      duplicate.setSprayDate(new Date());
      duplicate.addSprayMethod(SprayMethod.MAIN_SPRAY);
      duplicate.addSurfaceType(SurfaceType.POROUS);
      duplicate.setOperatorSprayWeek(2);
      duplicate.setReceived(2);
      duplicate.setRefills(3);
      duplicate.setReturned(2);
      duplicate.setSprayOperator(operator);
      duplicate.setTeamLeader(operator);
      duplicate.setTarget(232);
      duplicate.setTeamSprayWeek(24);
      duplicate.setUsed(3);
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
