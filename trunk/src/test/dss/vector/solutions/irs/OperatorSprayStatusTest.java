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
import dss.vector.solutions.geo.generated.SentinelSite;
import dss.vector.solutions.ontology.Term;

public class OperatorSprayStatusTest extends TestCase
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

  private static SprayOperator    operator         = null;

  private static SprayOperator    operator2        = null;

  private static Person           person           = null;

  private static Person           person2          = null;

  private static Term             activeIngredient = null;

  private static Term             surfaceType      = null;

  private static Term             sex              = null;

  public static Test suite()
  {
    TestSuite suite = new TestSuite();
    suite.addTestSuite(OperatorSprayStatusTest.class);

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
    operator.delete();
    operator2.delete();

    Person.get(person.getId()).delete();
    Person.get(person2.getId()).delete();

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

    geoEntity = new SentinelSite();
    geoEntity.setGeoId(TestConstants.GEO_ID);
    geoEntity.setEntityName("Sentinel Site");
    geoEntity.apply();

    person = new Person();
    person.setSex(sex);
    person.setDateOfBirth(new Date());
    person.setFirstName("Justin");
    person.setLastName("Smethie");
    person.apply();

    operator = new SprayOperator();
    operator.setOperatorId(TestConstants.OPERATOR_ID);
    operator.setPerson(person);
    operator.apply();

    person.setSprayOperatorDelegate(operator);
    person.apply();

    person2 = new Person();
    person2.setSex(sex);
    person2.setDateOfBirth(new Date());
    person2.setFirstName("Justin");
    person2.setLastName("Smethie");
    person2.apply();

    operator2 = new SprayOperator();
    operator2.setOperatorId(TestConstants.OPERATOR_ID_2);
    operator2.setPerson(person2);
    operator2.apply();

    person2.setSprayOperatorDelegate(operator2);
    person2.apply();
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

    try
    {
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
      spray.apply();

      try
      {
        SprayStatus status = new ActorSprayStatus();
        status.setSpray(spray);
        status.setHouseholds(TestConstants.NUM_HOUSEHOLDS);
        status.setStructures(TestConstants.NUM_STRUCTURES);
        status.setSprayedHouseholds(5);
        status.setSprayedStructures(2);
        status.setPrevSprayedHouseholds(1);
        status.setPrevSprayedStructures(3);
        status.setRooms(TestConstants.NUM_ROOMS);
        status.setSprayedRooms(3);
        status.setPeople(3);
        status.setBedNets(1);
        status.setRoomsWithBedNets(4);
        status.setLocked(4);
        status.setOther(1);
        status.setRefused(3);
        status.apply();

        try
        {
          SprayStatus test = SprayStatus.get(status.getId());

          assertNotNull(test);
          assertEquals(spray.getId(), test.getSpray().getId());
          assertEquals(status.getHouseholds(), test.getHouseholds());
          assertEquals(status.getStructures(), test.getStructures());
          assertEquals(status.getSprayedHouseholds(), test.getSprayedHouseholds());
          assertEquals(status.getSprayedStructures(), test.getSprayedStructures());
          assertEquals(status.getPrevSprayedHouseholds(), test.getPrevSprayedHouseholds());
          assertEquals(status.getPrevSprayedStructures(), test.getPrevSprayedStructures());
          assertEquals(status.getRooms(), test.getRooms());
          assertEquals(status.getSprayedRooms(), test.getSprayedRooms());
          assertEquals(status.getPeople(), test.getPeople());
          assertEquals(status.getBedNets(), test.getBedNets());
          assertEquals(status.getRoomsWithBedNets(), test.getRoomsWithBedNets());
          assertEquals(status.getLocked(), test.getLocked());
          assertEquals(status.getOther(), test.getOther());
          assertEquals(status.getRefused(), test.getRefused());
        }
        finally
        {
          status.delete();
        }
      }
      finally
      {
        spray.delete();
      }
    }
    finally
    {
      try
      {
        data.delete();
      }
      catch (Exception e)
      {
      }
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

    try
    {
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
      spray.apply();

      try
      {

        SprayStatus status = new ActorSprayStatus();
        status.setSpray(spray);
        status.setHouseholds(TestConstants.NUM_HOUSEHOLDS);
        status.setStructures(TestConstants.NUM_STRUCTURES);
        status.setSprayedHouseholds(5);
        status.setSprayedStructures(2);
        status.setPrevSprayedHouseholds(1);
        status.setPrevSprayedStructures(3);
        status.setRooms(TestConstants.NUM_ROOMS);
        status.setSprayedRooms(3);
        status.setPeople(3);
        status.setBedNets(1);
        status.setRoomsWithBedNets(4);
        status.setLocked(4);
        status.setOther(1);
        status.setRefused(3);
        status.apply();

        SprayStatus edit = SprayStatus.get(status.getId());
        edit.setSpray(spray);
        edit.setHouseholds(TestConstants.NUM_HOUSEHOLDS);
        edit.setStructures(TestConstants.NUM_STRUCTURES);
        edit.setSprayedHouseholds(1);
        edit.setSprayedStructures(8);
        edit.setPrevSprayedHouseholds(9);
        edit.setPrevSprayedStructures(65);
        edit.setRooms(43);
        edit.setSprayedRooms(13);
        edit.setPeople(43);
        edit.setBedNets(54);
        edit.setRoomsWithBedNets(41);
        edit.setLocked(44);
        edit.setOther(10);
        edit.setRefused(13);
        edit.apply();

        try
        {
          SprayStatus test = SprayStatus.get(status.getId());

          assertNotNull(test);
          assertEquals(spray.getId(), test.getSpray().getId());
          assertEquals(edit.getHouseholds(), test.getHouseholds());
          assertEquals(edit.getStructures(), test.getStructures());
          assertEquals(edit.getSprayedHouseholds(), test.getSprayedHouseholds());
          assertEquals(edit.getSprayedStructures(), test.getSprayedStructures());
          assertEquals(edit.getPrevSprayedHouseholds(), test.getPrevSprayedHouseholds());
          assertEquals(edit.getPrevSprayedStructures(), test.getPrevSprayedStructures());
          assertEquals(edit.getRooms(), test.getRooms());
          assertEquals(edit.getSprayedRooms(), test.getSprayedRooms());
          assertEquals(edit.getPeople(), test.getPeople());
          assertEquals(edit.getBedNets(), test.getBedNets());
          assertEquals(edit.getRoomsWithBedNets(), test.getRoomsWithBedNets());
          assertEquals(edit.getLocked(), test.getLocked());
          assertEquals(edit.getOther(), test.getOther());
          assertEquals(edit.getRefused(), test.getRefused());
        }
        finally
        {
          edit.delete();
        }
      }
      finally
      {
        spray.delete();
      }
    }
    finally
    {
      try
      {
        data.delete();
      }
      catch (Exception e)
      {
      }
    }
  }

  public void testCreateView()
  {
    SprayData data = new SprayData();
    data.setBrand(brand);
    data.setGeoEntity(geoEntity);
    data.setSprayDate(new Date());
    data.addSprayMethod(SprayMethod.MAIN_SPRAY);
    data.setSurfaceType(surfaceType);
    data.apply();

    try
    {
      OperatorSprayStatusView status = new OperatorSprayStatusView();
      status.setSprayData(data);
      status.setHouseholds(TestConstants.NUM_HOUSEHOLDS);
      status.setStructures(TestConstants.NUM_STRUCTURES);
      status.setSprayedHouseholds(5);
      status.setSprayedStructures(2);
      status.setPrevSprayedHouseholds(1);
      status.setPrevSprayedStructures(3);
      status.setRooms(TestConstants.NUM_ROOMS);
      status.setSprayedRooms(3);
      status.setPeople(3);
      status.setBedNets(1);
      status.setRoomsWithBedNets(4);
      status.setLocked(4);
      status.setOther(1);
      status.setRefused(3);
      status.setSprayOperator(operator);
      status.setOperatorSprayWeek(2);
      status.setReceived(2);
      status.setRefills(3);
      status.setReturned(2);
      status.setUsed(3);
      status.apply();

      try
      {
        OperatorSprayStatusView test = (OperatorSprayStatusView) SprayStatus.getView(status.getStatusId());

        assertNotNull(test);
        assertEquals(status.getHouseholds(), test.getHouseholds());
        assertEquals(status.getStructures(), test.getStructures());
        assertEquals(status.getSprayedHouseholds(), test.getSprayedHouseholds());
        assertEquals(status.getSprayedStructures(), test.getSprayedStructures());
        assertEquals(status.getPrevSprayedHouseholds(), test.getPrevSprayedHouseholds());
        assertEquals(status.getPrevSprayedStructures(), test.getPrevSprayedStructures());
        assertEquals(status.getRooms(), test.getRooms());
        assertEquals(status.getSprayedRooms(), test.getSprayedRooms());
        assertEquals(status.getPeople(), test.getPeople());
        assertEquals(status.getBedNets(), test.getBedNets());
        assertEquals(status.getRoomsWithBedNets(), test.getRoomsWithBedNets());
        assertEquals(status.getLocked(), test.getLocked());
        assertEquals(status.getOther(), test.getOther());
        assertEquals(status.getRefused(), test.getRefused());
        assertEquals(operator.getId(), test.getSprayOperator().getId());
        assertEquals(status.getOperatorSprayWeek(), test.getOperatorSprayWeek());
        assertEquals(status.getReceived(), test.getReceived());
        assertEquals(status.getRefills(), test.getRefills());
        assertEquals(status.getReturned(), test.getReturned());
        assertEquals(status.getUsed(), test.getUsed());
      }
      finally
      {
        AbstractSpray spray = status.getSpray();
        status.deleteConcrete();

        spray.delete();
      }
    }
    finally
    {
      try
      {
        data.delete();
      }
      catch (Exception e)
      {
      }
    }
  }

  public void testEditView()
  {
    SprayData data = new SprayData();
    data.setBrand(brand);
    data.setGeoEntity(geoEntity);
    data.setSprayDate(new Date());
    data.addSprayMethod(SprayMethod.MAIN_SPRAY);
    data.setSurfaceType(surfaceType);
    data.apply();

    try
    {
      OperatorSprayStatusView status = new OperatorSprayStatusView();
      status.setSprayData(data);
      status.setHouseholds(TestConstants.NUM_HOUSEHOLDS);
      status.setStructures(TestConstants.NUM_STRUCTURES);
      status.setSprayedHouseholds(5);
      status.setSprayedStructures(2);
      status.setPrevSprayedHouseholds(1);
      status.setPrevSprayedStructures(3);
      status.setRooms(TestConstants.NUM_ROOMS);
      status.setSprayedRooms(3);
      status.setPeople(3);
      status.setBedNets(1);
      status.setRoomsWithBedNets(4);
      status.setLocked(4);
      status.setOther(1);
      status.setRefused(3);
      status.setSprayOperator(operator);
      status.setOperatorSprayWeek(2);
      status.setReceived(2);
      status.setRefills(3);
      status.setReturned(2);
      status.setUsed(3);
      status.apply();

      OperatorSprayStatusView edit = (OperatorSprayStatusView) SprayStatus.lockView(status.getStatusId());
      edit.setHouseholds(TestConstants.NUM_HOUSEHOLDS);
      edit.setStructures(TestConstants.NUM_STRUCTURES);
      edit.setSprayedHouseholds(7);
      edit.setSprayedStructures(8);
      edit.setPrevSprayedHouseholds(11);
      edit.setPrevSprayedStructures(33);
      edit.setRooms(24);
      edit.setSprayedRooms(13);
      edit.setPeople(34);
      edit.setBedNets(11);
      edit.setRoomsWithBedNets(40);
      edit.setLocked(41);
      edit.setOther(12);
      edit.setRefused(33);
      edit.setOperatorSprayWeek(1);
      edit.setReceived(50);
      edit.setRefills(32);
      edit.setReturned(1);
      edit.setUsed(2);
      edit.apply();

      try
      {
        OperatorSprayStatusView test = (OperatorSprayStatusView) SprayStatus.getView(status.getStatusId());

        assertNotNull(test);
        assertEquals(data.getId(), test.getSprayData().getId());
        assertEquals(edit.getHouseholds(), test.getHouseholds());
        assertEquals(edit.getStructures(), test.getStructures());
        assertEquals(edit.getSprayedHouseholds(), test.getSprayedHouseholds());
        assertEquals(edit.getSprayedStructures(), test.getSprayedStructures());
        assertEquals(edit.getPrevSprayedHouseholds(), test.getPrevSprayedHouseholds());
        assertEquals(edit.getPrevSprayedStructures(), test.getPrevSprayedStructures());
        assertEquals(edit.getRooms(), test.getRooms());
        assertEquals(edit.getSprayedRooms(), test.getSprayedRooms());
        assertEquals(edit.getPeople(), test.getPeople());
        assertEquals(edit.getBedNets(), test.getBedNets());
        assertEquals(edit.getRoomsWithBedNets(), test.getRoomsWithBedNets());
        assertEquals(edit.getLocked(), test.getLocked());
        assertEquals(edit.getOther(), test.getOther());
        assertEquals(edit.getRefused(), test.getRefused());
        assertEquals(operator.getId(), test.getSprayOperator().getId());
        assertEquals(edit.getOperatorSprayWeek(), test.getOperatorSprayWeek());
        assertEquals(edit.getReceived(), test.getReceived());
        assertEquals(edit.getRefills(), test.getRefills());
        assertEquals(edit.getReturned(), test.getReturned());
        assertEquals(edit.getUsed(), test.getUsed());
      }
      finally
      {
        AbstractSpray spray = edit.getSpray();
        edit.deleteConcrete();

        spray.delete();
      }
    }
    finally
    {
      try
      {
        data.delete();
      }
      catch (Exception e)
      {
      }
    }
  }

  public void testDeleteView()
  {
    SprayData data = new SprayData();
    data.setBrand(brand);
    data.setGeoEntity(geoEntity);
    data.setSprayDate(new Date());
    data.addSprayMethod(SprayMethod.MAIN_SPRAY);
    data.setSurfaceType(surfaceType);
    data.apply();

    try
    {
      OperatorSprayStatusView status = new OperatorSprayStatusView();
      status.setSprayData(data);
      status.setHouseholds(TestConstants.NUM_HOUSEHOLDS);
      status.setStructures(TestConstants.NUM_STRUCTURES);
      status.setSprayedHouseholds(5);
      status.setSprayedStructures(2);
      status.setPrevSprayedHouseholds(1);
      status.setPrevSprayedStructures(3);
      status.setRooms(TestConstants.NUM_ROOMS);
      status.setSprayedRooms(3);
      status.setPeople(3);
      status.setBedNets(1);
      status.setRoomsWithBedNets(4);
      status.setLocked(4);
      status.setOther(1);
      status.setRefused(3);
      status.setSprayOperator(operator);
      status.setOperatorSprayWeek(2);
      status.setReceived(2);
      status.setRefills(3);
      status.setReturned(2);
      status.setUsed(3);
      status.apply();

      String id = status.getStatusId();
      AbstractSpray spray = status.getSpray();

      status.deleteConcrete();
      spray.delete();

      try
      {
        SprayStatus.getView(id);

        fail("Unabled to delete the concrete spray operator");
      }
      catch (DataNotFoundException e)
      {
        // This is expected
      }
    }
    finally
    {
      try
      {
        data.delete();
      }
      catch (Exception e)
      {
      }
    }
  }

  public void testApplyAll()
  {
    SprayData data = new SprayData();
    data.setBrand(brand);
    data.setGeoEntity(geoEntity);
    data.setSprayDate(new Date());
    data.addSprayMethod(SprayMethod.MAIN_SPRAY);
    data.setSurfaceType(surfaceType);
    data.apply();

    try
    {
      OperatorSprayStatusView status = new OperatorSprayStatusView();
      status.setSprayData(data);
      status.setHouseholds(TestConstants.NUM_HOUSEHOLDS);
      status.setStructures(TestConstants.NUM_STRUCTURES);
      status.setSprayedHouseholds(5);
      status.setSprayedStructures(2);
      status.setPrevSprayedHouseholds(1);
      status.setPrevSprayedStructures(3);
      status.setRooms(TestConstants.NUM_ROOMS);
      status.setSprayedRooms(3);
      status.setPeople(3);
      status.setBedNets(1);
      status.setRoomsWithBedNets(4);
      status.setLocked(4);
      status.setOther(1);
      status.setRefused(3);
      status.setSprayOperator(operator);
      status.setOperatorSprayWeek(2);
      status.setReceived(2);
      status.setRefills(3);
      status.setReturned(2);
      status.setUsed(3);

      OperatorSprayStatusView status2 = new OperatorSprayStatusView();
      status2.setSprayData(data);
      status2.setHouseholds(TestConstants.NUM_HOUSEHOLDS);
      status2.setStructures(TestConstants.NUM_STRUCTURES);
      status2.setSprayedHouseholds(7);
      status2.setSprayedStructures(8);
      status2.setPrevSprayedHouseholds(11);
      status2.setPrevSprayedStructures(33);
      status2.setRooms(24);
      status2.setSprayedRooms(13);
      status2.setPeople(34);
      status2.setBedNets(11);
      status2.setRoomsWithBedNets(40);
      status2.setLocked(41);
      status2.setOther(12);
      status2.setRefused(33);
      status2.setSprayOperator(operator2);
      status2.setOperatorSprayWeek(1);
      status2.setReceived(50);
      status2.setRefills(32);
      status2.setReturned(1);
      status2.setUsed(2);

      OperatorSprayStatusView[] array = new OperatorSprayStatusView[] { status, status2 };
      OperatorSprayStatusView[] test = OperatorSprayStatusView.applyAll(array);

      try
      {
        assertEquals(array.length, test.length);

        for (int i = 0; i < array.length; i++)
        {
          assertEquals(data.getId(), test[i].getSprayData().getId());
          assertEquals(array[i].getHouseholds(), test[i].getHouseholds());
          assertEquals(array[i].getStructures(), test[i].getStructures());
          assertEquals(array[i].getSprayedHouseholds(), test[i].getSprayedHouseholds());
          assertEquals(array[i].getSprayedStructures(), test[i].getSprayedStructures());
          assertEquals(array[i].getPrevSprayedHouseholds(), test[i].getPrevSprayedHouseholds());
          assertEquals(array[i].getPrevSprayedStructures(), test[i].getPrevSprayedStructures());
          assertEquals(array[i].getRooms(), test[i].getRooms());
          assertEquals(array[i].getSprayedRooms(), test[i].getSprayedRooms());
          assertEquals(array[i].getPeople(), test[i].getPeople());
          assertEquals(array[i].getBedNets(), test[i].getBedNets());
          assertEquals(array[i].getRoomsWithBedNets(), test[i].getRoomsWithBedNets());
          assertEquals(array[i].getLocked(), test[i].getLocked());
          assertEquals(array[i].getOther(), test[i].getOther());
          assertEquals(array[i].getRefused(), test[i].getRefused());
          assertEquals(array[i].getSprayOperator().getId(), test[i].getSprayOperator().getId());
          assertEquals(array[i].getOperatorSprayWeek(), test[i].getOperatorSprayWeek());
          assertEquals(array[i].getReceived(), test[i].getReceived());
          assertEquals(array[i].getRefills(), test[i].getRefills());
          assertEquals(array[i].getReturned(), test[i].getReturned());
          assertEquals(array[i].getUsed(), test[i].getUsed());
        }
      }
      finally
      {
        for (OperatorSprayStatusView view : test)
        {
          AbstractSpray spray = view.getSpray();

          view.deleteConcrete();
          spray.delete();
        }
      }
    }
    finally
    {
      try
      {
        data.delete();
      }
      catch (Exception e)
      {
      }
    }
  }

  public void testSearch()
  {
    SprayData data = new SprayData();
    data.setBrand(brand);
    data.setGeoEntity(geoEntity);
    data.setSprayDate(new Date());
    data.addSprayMethod(SprayMethod.MAIN_SPRAY);
    data.setSurfaceType(surfaceType);
    data.apply();

    try
    {
      OperatorSprayStatusView status = new OperatorSprayStatusView();
      status.setSprayData(data);
      status.setHouseholds(TestConstants.NUM_HOUSEHOLDS);
      status.setStructures(TestConstants.NUM_STRUCTURES);
      status.setSprayedHouseholds(5);
      status.setSprayedStructures(2);
      status.setPrevSprayedHouseholds(1);
      status.setPrevSprayedStructures(3);
      status.setRooms(TestConstants.NUM_ROOMS);
      status.setSprayedRooms(3);
      status.setPeople(3);
      status.setBedNets(1);
      status.setRoomsWithBedNets(4);
      status.setLocked(4);
      status.setOther(1);
      status.setRefused(3);
      status.setSprayOperator(operator);
      status.setOperatorSprayWeek(2);
      status.setReceived(2);
      status.setRefills(3);
      status.setReturned(2);
      status.setUsed(3);
      status.apply();

      try
      {
        OperatorSprayStatusView[] array = OperatorSprayStatusView.search(data, operator);

        assertNotNull(array);
        assertEquals(1, array.length);

        OperatorSprayStatusView test = array[0];

        assertEquals(data.getId(), test.getSprayData().getId());
        assertEquals(status.getHouseholds(), test.getHouseholds());
        assertEquals(status.getStructures(), test.getStructures());
        assertEquals(status.getSprayedHouseholds(), test.getSprayedHouseholds());
        assertEquals(status.getSprayedStructures(), test.getSprayedStructures());
        assertEquals(status.getPrevSprayedHouseholds(), test.getPrevSprayedHouseholds());
        assertEquals(status.getPrevSprayedStructures(), test.getPrevSprayedStructures());
        assertEquals(status.getRooms(), test.getRooms());
        assertEquals(status.getSprayedRooms(), test.getSprayedRooms());
        assertEquals(status.getPeople(), test.getPeople());
        assertEquals(status.getBedNets(), test.getBedNets());
        assertEquals(status.getRoomsWithBedNets(), test.getRoomsWithBedNets());
        assertEquals(status.getLocked(), test.getLocked());
        assertEquals(status.getOther(), test.getOther());
        assertEquals(status.getRefused(), test.getRefused());
        assertEquals(operator.getId(), test.getSprayOperator().getId());
        assertEquals(status.getOperatorSprayWeek(), test.getOperatorSprayWeek());
        assertEquals(status.getReceived(), test.getReceived());
        assertEquals(status.getRefills(), test.getRefills());
        assertEquals(status.getReturned(), test.getReturned());
        assertEquals(status.getUsed(), test.getUsed());
      }
      finally
      {
        AbstractSpray spray = status.getSpray();

        status.deleteConcrete();
        spray.delete();
      }
    }
    finally
    {
      try
      {
        data.delete();
      }
      catch (Exception e)
      {
      }
    }
  }

  public void testEmptySearch()
  {
    SprayData data = new SprayData();
    data.setBrand(brand);
    data.setGeoEntity(geoEntity);
    data.setSprayDate(new Date());
    data.addSprayMethod(SprayMethod.MAIN_SPRAY);
    data.setSurfaceType(surfaceType);
    data.apply();

    try
    {
      OperatorSprayStatusView[] array = OperatorSprayStatusView.search(data, operator);

      assertNotNull(array);
      assertEquals(0, array.length);
    }
    finally
    {
      data.delete();
    }
  }
}
