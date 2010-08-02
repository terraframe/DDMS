package dss.vector.solutions.irs;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import junit.extensions.TestSetup;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestResult;
import junit.framework.TestSuite;

import com.runwaysdk.ProblemException;
import com.runwaysdk.ProblemIF;
import com.runwaysdk.dataaccess.cache.DataNotFoundException;

import dss.vector.solutions.Person;
import dss.vector.solutions.TestConstants;
import dss.vector.solutions.TestFixture;
import dss.vector.solutions.geo.generated.GeoEntity;
import dss.vector.solutions.geo.generated.SentinelSite;
import dss.vector.solutions.ontology.Term;

public class HouseholdSprayStatusTest extends TestCase
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

  private static TeamMember       operator         = null;

  private static OperatorSpray    spray            = null;

  private static OperatorSpray    mopupSpray       = null;

  private static Person           person           = null;

  private static Term             activeIngredient = null;

  private static Term             productName      = null;	

  private static Term             surfaceType      = null;

  private static Term             sex              = null;

  public static Test suite()
  {
    TestSuite suite = new TestSuite();
    suite.addTestSuite(HouseholdSprayStatusTest.class);

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
    mopupSpray.delete();
    spray.delete();
    operator.delete();
    person.delete();
    geoEntity.delete();
    brand.delete();

    activeIngredient.delete();
    productName.delete();
    surfaceType.delete();
    sex.delete();
  }

  protected static void classSetUp()
  {
	    activeIngredient = TestFixture.createRandomTerm();
	    productName = TestFixture.createRandomTerm();
    surfaceType = TestFixture.createRandomTerm();
    sex = TestFixture.createRandomTerm();

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

    operator = new TeamMember();
    operator.setMemberId(TestConstants.OPERATOR_ID);
    operator.setPerson(person);
    operator.apply();

    spray = new OperatorSpray();
    spray.setBrand(brand);
    spray.setGeoEntity(geoEntity);
    spray.setSprayDate(new Date());
    spray.addSprayMethod(SprayMethod.MAIN_SPRAY);
    spray.setSurfaceType(surfaceType);
    spray.setReceived(2);
    spray.setRefills(3);
    spray.setReturned(2);
    spray.setSprayOperator(operator);
    spray.setTarget(232);
    spray.setUsed(3);
    spray.apply();

    mopupSpray = new OperatorSpray();
    mopupSpray.setBrand(brand);
    mopupSpray.setGeoEntity(geoEntity);
    mopupSpray.setSprayDate(new Date());
    mopupSpray.addSprayMethod(SprayMethod.MOP_UP);
    mopupSpray.setSurfaceType(surfaceType);
    mopupSpray.setReceived(2);
    mopupSpray.setRefills(3);
    mopupSpray.setReturned(2);
    mopupSpray.setSprayOperator(operator);
    mopupSpray.setTarget(232);
    mopupSpray.setUsed(3);
    mopupSpray.apply();
  }

  public void testCreate()
  {
    HouseholdSprayStatus status = new HouseholdSprayStatus();
    status.setSpray(spray);
    status.setHouseholds(1);
    status.setStructures(1);
    status.setSprayedHouseholds(1);
    status.setSprayedStructures(0);
    status.setPrevSprayedHouseholds(1);
    status.setPrevSprayedStructures(0);
    status.setRooms(TestConstants.NUM_ROOMS);
    status.setSprayedRooms(2);
    status.setPeople(3);
    status.setBedNets(3);
    status.setRoomsWithBedNets(5);
    status.setLocked(2);
    status.setOther(3);
    status.setRefused(3);
    status.setHouseholdId(TestConstants.HOUSEHOLD_ID);
    status.setStructureId(TestConstants.STRUCTURE_ID);
    status.apply();

    try
    {
      HouseholdSprayStatus test = HouseholdSprayStatus.get(status.getId());

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
      assertEquals(status.getHouseholdId(), test.getHouseholdId());
      assertEquals(status.getStructureId(), test.getStructureId());
    }
    finally
    {
      status.delete();
    }
  }

  public void testUpdate()
  {
    HouseholdSprayStatus status = new HouseholdSprayStatus();
    status.setSpray(spray);
    status.setHouseholds(1);
    status.setStructures(1);
    status.setSprayedHouseholds(1);
    status.setSprayedStructures(0);
    status.setPrevSprayedHouseholds(0);
    status.setPrevSprayedStructures(1);
    status.setRooms(TestConstants.NUM_ROOMS);
    status.setSprayedRooms(3);
    status.setPeople(3);
    status.setBedNets(1);
    status.setRoomsWithBedNets(4);
    status.setLocked(4);
    status.setOther(1);
    status.setRefused(3);
    status.setHouseholdId(TestConstants.HOUSEHOLD_ID);
    status.setStructureId(TestConstants.STRUCTURE_ID);
    status.apply();

    HouseholdSprayStatus edit = HouseholdSprayStatus.lock(status.getId());
    edit.setHouseholds(1);
    edit.setStructures(1);
    edit.setSprayedHouseholds(1);
    edit.setSprayedStructures(0);
    edit.setPrevSprayedHouseholds(1);
    edit.setPrevSprayedStructures(0);
    edit.setRooms(TestConstants.NUM_ROOMS);
    edit.setSprayedRooms(2);
    edit.setPeople(3);
    edit.setBedNets(3);
    edit.setRoomsWithBedNets(5);
    edit.setLocked(2);
    edit.setOther(3);
    edit.setRefused(3);
    edit.setHouseholdId(TestConstants.HOUSEHOLD_ID);
    edit.setStructureId(TestConstants.STRUCTURE_ID);
    edit.apply();

    try
    {
      HouseholdSprayStatus test = HouseholdSprayStatus.get(status.getId());

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
      assertEquals(status.getHouseholdId(), test.getHouseholdId());
      assertEquals(status.getStructureId(), test.getStructureId());
    }
    finally
    {
      edit.delete();
    }
  }

  public void testCreateView()
  {
    HouseholdSprayStatusView status = new HouseholdSprayStatusView();
    status.setSpray(spray);
    status.setHouseholds(1);
    status.setStructures(1);
    status.setSprayedHouseholds(0);
    status.setSprayedStructures(1);
    status.setPrevSprayedHouseholds(0);
    status.setPrevSprayedStructures(1);
    status.setRooms(TestConstants.NUM_ROOMS);
    status.setSprayedRooms(3);
    status.setPeople(3);
    status.setBedNets(1);
    status.setRoomsWithBedNets(4);
    status.setLocked(4);
    status.setOther(1);
    status.setRefused(3);
    status.setHouseholdId(TestConstants.HOUSEHOLD_ID);
    status.setStructureId(TestConstants.STRUCTURE_ID);
    status.apply();

    try
    {
      HouseholdSprayStatusView test = HouseholdSprayStatus.get(status.getConcreteId()).getView();

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
      assertEquals(status.getHouseholdId(), test.getHouseholdId());
      assertEquals(status.getStructureId(), test.getStructureId());
    }
    finally
    {
      status.deleteConcrete();
    }
  }

  public void testEditView()
  {
    HouseholdSprayStatusView status = new HouseholdSprayStatusView();
    status.setSpray(spray);
    status.setHouseholds(1);
    status.setStructures(1);
    status.setSprayedHouseholds(0);
    status.setSprayedStructures(1);
    status.setPrevSprayedHouseholds(1);
    status.setPrevSprayedStructures(1);
    status.setRooms(TestConstants.NUM_ROOMS);
    status.setSprayedRooms(3);
    status.setPeople(3);
    status.setBedNets(1);
    status.setRoomsWithBedNets(4);
    status.setLocked(4);
    status.setOther(1);
    status.setRefused(3);
    status.setHouseholdId(TestConstants.HOUSEHOLD_ID);
    status.setStructureId(TestConstants.STRUCTURE_ID);
    status.apply();

    HouseholdSprayStatusView edit = HouseholdSprayStatus.get(status.getConcreteId()).getView();
    edit.setHouseholds(1);
    edit.setStructures(1);
    edit.setSprayedHouseholds(1);
    edit.setSprayedStructures(0);
    edit.setPrevSprayedHouseholds(1);
    edit.setPrevSprayedStructures(0);
    edit.setRooms(24);
    edit.setSprayedRooms(13);
    edit.setPeople(34);
    edit.setBedNets(11);
    edit.setRoomsWithBedNets(40);
    edit.setLocked(41);
    edit.setOther(12);
    edit.setRefused(33);
    edit.apply();

    try
    {
      HouseholdSprayStatusView test = HouseholdSprayStatus.get(status.getConcreteId()).getView();

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
      assertEquals(status.getHouseholdId(), test.getHouseholdId());
      assertEquals(status.getStructureId(), test.getStructureId());
    }
    finally
    {
      edit.deleteConcrete();
    }
  }

  public void testDeleteView()
  {
    HouseholdSprayStatusView status = new HouseholdSprayStatusView();
    status.setSpray(spray);
    status.setHouseholds(1);
    status.setStructures(1);
    status.setSprayedHouseholds(0);
    status.setSprayedStructures(1);
    status.setPrevSprayedHouseholds(1);
    status.setPrevSprayedStructures(1);
    status.setRooms(TestConstants.NUM_ROOMS);
    status.setSprayedRooms(3);
    status.setPeople(3);
    status.setBedNets(1);
    status.setRoomsWithBedNets(4);
    status.setLocked(4);
    status.setOther(1);
    status.setRefused(3);
    status.apply();
    status.deleteConcrete();

    try
    {
      HouseholdSprayStatus.get(status.getConcreteId());

      fail("Unabled to delete the concrete spray operator");
    }
    catch (DataNotFoundException e)
    {
      // This is expected
    }
  }

  public void testApplyAll()
  {
    HouseholdSprayStatusView status = new HouseholdSprayStatusView();
    status.setHouseholdId(TestConstants.HOUSEHOLD_ID);
    status.setStructureId(TestConstants.STRUCTURE_ID);
    status.setSpray(spray);
    status.setHouseholds(1);
    status.setStructures(1);
    status.setSprayedHouseholds(1);
    status.setSprayedStructures(1);
    status.setPrevSprayedHouseholds(1);
    status.setPrevSprayedStructures(1);
    status.setRooms(TestConstants.NUM_ROOMS);
    status.setSprayedRooms(3);
    status.setPeople(3);
    status.setBedNets(1);
    status.setRoomsWithBedNets(4);
    status.setLocked(4);
    status.setOther(1);
    status.setRefused(3);

    HouseholdSprayStatusView status2 = new HouseholdSprayStatusView();
    status2.setHouseholdId(TestConstants.HOUSEHOLD_ID);
    status2.setStructureId(TestConstants.STRUCTURE_ID_2);
    status2.setSpray(spray);
    status2.setHouseholds(0);
    status2.setStructures(0);
    status2.setSprayedHouseholds(0);
    status2.setSprayedStructures(0);
    status2.setPrevSprayedHouseholds(0);
    status2.setPrevSprayedStructures(0);
    status2.setRooms(24);
    status2.setSprayedRooms(13);
    status2.setPeople(34);
    status2.setBedNets(11);
    status2.setRoomsWithBedNets(40);
    status2.setLocked(41);
    status2.setOther(12);
    status2.setRefused(33);

    HouseholdSprayStatusView[] array = new HouseholdSprayStatusView[] { status, status2 };
    HouseholdSprayStatusView[] test = HouseholdSprayStatusView.applyAll(array);

    try
    {
      assertEquals(array.length, test.length);

      for (int i = 0; i < array.length; i++)
      {
        assertEquals(spray.getId(), test[i].getSpray().getId());
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
      }
    }
    finally
    {
      for (HouseholdSprayStatusView view : test)
      {
        view.deleteConcrete();
      }
    }
  }

  public void testMopUpHouseholdValue()
  {
    try
    {

      HouseholdSprayStatus status = new HouseholdSprayStatus();
      status.setSpray(mopupSpray);
      status.setHouseholds(1);
      status.setHouseholdId(TestConstants.HOUSEHOLD_ID);
      status.setStructureId(TestConstants.STRUCTURE_ID);
      status.apply();

      status.delete();

      fail("Able to set a households value on a mop up spray ");
    }
    catch (ProblemException e)
    {
      List<ProblemIF> problems = e.getProblems();

      assertEquals(1, problems.size());
      assertTrue(problems.get(0) instanceof ValueNotApplicableProblem);
    }
  }

  public void testPrevMopUpSprayedHouseholdValue()
  {
    try
    {
      HouseholdSprayStatus status = new HouseholdSprayStatus();
      status.setSpray(mopupSpray);
      status.setPrevSprayedHouseholds(1);
      status.setHouseholdId(TestConstants.HOUSEHOLD_ID);
      status.setStructureId(TestConstants.STRUCTURE_ID);
      status.apply();

      status.delete();

      fail("Able to set a households value on a mop up spray ");
    }
    catch (ProblemException e)
    {
      List<ProblemIF> problems = e.getProblems();

      assertEquals(1, problems.size());
      assertTrue(problems.get(0) instanceof ValueNotApplicableProblem);
    }
  }

  public void testMopUpRoomValue()
  {
    try
    {

      HouseholdSprayStatus status = new HouseholdSprayStatus();
      status.setSpray(mopupSpray);
      status.setRooms(TestConstants.NUM_ROOMS);
      status.setSprayedRooms(2);
      status.setHouseholdId(TestConstants.HOUSEHOLD_ID);
      status.setStructureId(TestConstants.STRUCTURE_ID);
      status.apply();

      status.delete();

      fail("Able to set a households value on a mop up spray ");
    }
    catch (ProblemException e)
    {
      List<ProblemIF> problems = e.getProblems();

      assertEquals(1, problems.size());
      assertTrue(problems.get(0) instanceof ValueNotApplicableProblem);
    }
  }

  public void testBadHouseholdValue()
  {
    try
    {

      HouseholdSprayStatus status = new HouseholdSprayStatus();
      status.setSpray(spray);
      status.setHouseholds(2);
      status.setRooms(TestConstants.NUM_ROOMS);
      status.setSprayedRooms(2);
      status.setPeople(3);
      status.setBedNets(3);
      status.setRoomsWithBedNets(5);
      status.setLocked(2);
      status.setOther(3);
      status.setRefused(3);
      status.setHouseholdId(TestConstants.HOUSEHOLD_ID);
      status.setStructureId(TestConstants.STRUCTURE_ID);
      status.apply();

      status.delete();

      fail("Able to set a households value on a mop up spray ");
    }
    catch (ProblemException e)
    {
      List<ProblemIF> problems = e.getProblems();

      assertEquals(1, problems.size());
      assertTrue(problems.get(0) instanceof ValueProblem);
    }
  }

  public void testBadStructuresValue()
  {
    try
    {

      HouseholdSprayStatus status = new HouseholdSprayStatus();
      status.setSpray(spray);
      status.setStructures(2);
      status.setRooms(TestConstants.NUM_ROOMS);
      status.setSprayedRooms(2);
      status.setPeople(3);
      status.setBedNets(3);
      status.setRoomsWithBedNets(5);
      status.setLocked(2);
      status.setOther(3);
      status.setRefused(3);
      status.setHouseholdId(TestConstants.HOUSEHOLD_ID);
      status.setStructureId(TestConstants.STRUCTURE_ID);
      status.apply();

      status.delete();

      fail("Able to set a households value on a mop up spray ");
    }
    catch (ProblemException e)
    {
      List<ProblemIF> problems = e.getProblems();

      assertEquals(1, problems.size());
      assertTrue(problems.get(0) instanceof ValueProblem);
    }
  }

  public void testBadSprayedHouseholdValue()
  {
    try
    {

      HouseholdSprayStatus status = new HouseholdSprayStatus();
      status.setSpray(spray);
      status.setSprayedHouseholds(2);
      status.setRooms(TestConstants.NUM_ROOMS);
      status.setSprayedRooms(2);
      status.setPeople(3);
      status.setBedNets(3);
      status.setRoomsWithBedNets(5);
      status.setLocked(2);
      status.setOther(3);
      status.setRefused(3);
      status.setHouseholdId(TestConstants.HOUSEHOLD_ID);
      status.setStructureId(TestConstants.STRUCTURE_ID);
      status.apply();

      status.delete();

      fail("Able to set a households value on a mop up spray ");
    }
    catch (ProblemException e)
    {
      List<ProblemIF> problems = e.getProblems();

      assertEquals(1, problems.size());
      assertTrue(problems.get(0) instanceof ValueProblem);
    }
  }

  public void testBadSprayedStructuresValue()
  {
    try
    {

      HouseholdSprayStatus status = new HouseholdSprayStatus();
      status.setSpray(spray);
      status.setSprayedStructures(2);
      status.setRooms(TestConstants.NUM_ROOMS);
      status.setSprayedRooms(2);
      status.setPeople(3);
      status.setBedNets(3);
      status.setRoomsWithBedNets(5);
      status.setLocked(2);
      status.setOther(3);
      status.setRefused(3);
      status.setHouseholdId(TestConstants.HOUSEHOLD_ID);
      status.setStructureId(TestConstants.STRUCTURE_ID);
      status.apply();

      status.delete();

      fail("Able to set a households value on a mop up spray ");
    }
    catch (ProblemException e)
    {
      List<ProblemIF> problems = e.getProblems();

      assertEquals(1, problems.size());
      assertTrue(problems.get(0) instanceof ValueProblem);
    }
  }

  public void testBadPrevSprayedHouseholdValue()
  {
    try
    {

      HouseholdSprayStatus status = new HouseholdSprayStatus();
      status.setSpray(spray);
      status.setPrevSprayedHouseholds(2);
      status.setRooms(TestConstants.NUM_ROOMS);
      status.setSprayedRooms(2);
      status.setPeople(3);
      status.setBedNets(3);
      status.setRoomsWithBedNets(5);
      status.setLocked(2);
      status.setOther(3);
      status.setRefused(3);
      status.setHouseholdId(TestConstants.HOUSEHOLD_ID);
      status.setStructureId(TestConstants.STRUCTURE_ID);
      status.apply();

      status.delete();

      fail("Able to set a households value on a mop up spray ");
    }
    catch (ProblemException e)
    {
      List<ProblemIF> problems = e.getProblems();

      assertEquals(1, problems.size());
      assertTrue(problems.get(0) instanceof ValueProblem);
    }
  }

  public void testBadPrevSprayedStructureValue()
  {
    try
    {

      HouseholdSprayStatus status = new HouseholdSprayStatus();
      status.setSpray(spray);
      status.setPrevSprayedStructures(2);
      status.setRooms(TestConstants.NUM_ROOMS);
      status.setSprayedRooms(2);
      status.setPeople(3);
      status.setBedNets(3);
      status.setRoomsWithBedNets(5);
      status.setLocked(2);
      status.setOther(3);
      status.setRefused(3);
      status.setHouseholdId(TestConstants.HOUSEHOLD_ID);
      status.setStructureId(TestConstants.STRUCTURE_ID);
      status.apply();

      status.delete();

      fail("Able to set a households value on a mop up spray ");
    }
    catch (ProblemException e)
    {
      List<ProblemIF> problems = e.getProblems();

      assertEquals(1, problems.size());
      assertTrue(problems.get(0) instanceof ValueProblem);
    }
  }

  public void testSetHousehold()
  {
    HouseholdSprayStatus status = new HouseholdSprayStatus();
    status.setSpray(spray);
    status.setHouseholds(1);
    status.setRooms(TestConstants.NUM_ROOMS);
    status.setSprayedRooms(2);
    status.setPeople(3);
    status.setBedNets(3);
    status.setRoomsWithBedNets(5);
    status.setLocked(2);
    status.setOther(3);
    status.setRefused(3);
    status.setHouseholdId(TestConstants.HOUSEHOLD_ID);
    status.setStructureId(TestConstants.STRUCTURE_ID);
    status.apply();

    try
    {

      HouseholdSprayStatus invalid = new HouseholdSprayStatus();
      invalid.setSpray(spray);
      invalid.setHouseholds(1);
      invalid.setRooms(TestConstants.NUM_ROOMS);
      invalid.setSprayedRooms(2);
      invalid.setPeople(3);
      invalid.setBedNets(3);
      invalid.setRoomsWithBedNets(5);
      invalid.setLocked(2);
      invalid.setOther(3);
      invalid.setRefused(3);
      invalid.setHouseholdId(TestConstants.HOUSEHOLD_ID);
      invalid.setStructureId(TestConstants.STRUCTURE_ID_2);
      invalid.apply();

      invalid.delete();

      fail("Able to set a households value on a mop up spray ");
    }
    catch (ProblemException e)
    {
      List<ProblemIF> problems = e.getProblems();

      assertEquals(1, problems.size());
      assertTrue(problems.get(0) instanceof CountProblem);
    }
    finally
    {
      status.delete();
    }

  }

  public void testSetSprayedHousehold()
  {
    HouseholdSprayStatus status = new HouseholdSprayStatus();
    status.setSpray(spray);
    status.setSprayedHouseholds(1);
    status.setRooms(TestConstants.NUM_ROOMS);
    status.setSprayedRooms(2);
    status.setPeople(3);
    status.setBedNets(3);
    status.setRoomsWithBedNets(5);
    status.setLocked(2);
    status.setOther(3);
    status.setRefused(3);
    status.setHouseholdId(TestConstants.HOUSEHOLD_ID);
    status.setStructureId(TestConstants.STRUCTURE_ID);
    status.apply();

    try
    {

      HouseholdSprayStatus invalid = new HouseholdSprayStatus();
      invalid.setSpray(spray);
      invalid.setSprayedHouseholds(1);
      invalid.setRooms(TestConstants.NUM_ROOMS);
      invalid.setSprayedRooms(2);
      invalid.setPeople(3);
      invalid.setBedNets(3);
      invalid.setRoomsWithBedNets(5);
      invalid.setLocked(2);
      invalid.setOther(3);
      invalid.setRefused(3);
      invalid.setHouseholdId(TestConstants.HOUSEHOLD_ID);
      invalid.setStructureId(TestConstants.STRUCTURE_ID_2);
      invalid.apply();

      invalid.delete();

      fail("Able to set a households value on a mop up spray ");
    }
    catch (ProblemException e)
    {
      List<ProblemIF> problems = e.getProblems();

      assertEquals(1, problems.size());
      assertTrue(problems.get(0) instanceof SprayedCountProblem);
    }
    finally
    {
      status.delete();
    }
  }

  public void testSetPrevSprayedHousehold()
  {
    HouseholdSprayStatus status = new HouseholdSprayStatus();
    status.setSpray(spray);
    status.setPrevSprayedHouseholds(1);
    status.setRooms(TestConstants.NUM_ROOMS);
    status.setSprayedRooms(2);
    status.setPeople(3);
    status.setBedNets(3);
    status.setRoomsWithBedNets(5);
    status.setLocked(2);
    status.setOther(3);
    status.setRefused(3);
    status.setHouseholdId(TestConstants.HOUSEHOLD_ID);
    status.setStructureId(TestConstants.STRUCTURE_ID);
    status.apply();

    try
    {

      HouseholdSprayStatus invalid = new HouseholdSprayStatus();
      invalid.setSpray(spray);
      invalid.setPrevSprayedHouseholds(1);
      invalid.setRooms(TestConstants.NUM_ROOMS);
      invalid.setSprayedRooms(2);
      invalid.setPeople(3);
      invalid.setBedNets(3);
      invalid.setRoomsWithBedNets(5);
      invalid.setLocked(2);
      invalid.setOther(3);
      invalid.setRefused(3);
      invalid.setHouseholdId(TestConstants.HOUSEHOLD_ID);
      invalid.setStructureId(TestConstants.STRUCTURE_ID_2);
      invalid.apply();

      invalid.delete();

      fail("Able to set a households value on a mop up spray ");
    }
    catch (ProblemException e)
    {
      List<ProblemIF> problems = e.getProblems();

      assertEquals(1, problems.size());
      assertTrue(problems.get(0) instanceof PrevSprayedCountProblem);
    }
    finally
    {
      status.delete();
    }
  }
}
