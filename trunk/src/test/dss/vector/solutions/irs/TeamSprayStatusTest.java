package dss.vector.solutions.irs;

import java.math.BigDecimal;
import java.util.Date;

import junit.extensions.TestSetup;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestResult;
import junit.framework.TestSuite;

import com.terraframe.mojo.dataaccess.cache.DataNotFoundException;

import dss.vector.solutions.TestConstants;
import dss.vector.solutions.TestFixture;
import dss.vector.solutions.geo.generated.GeoEntity;
import dss.vector.solutions.geo.generated.SentinelSite;
import dss.vector.solutions.ontology.Term;

public class TeamSprayStatusTest extends TestCase
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

  private static SprayTeam        team2            = null;

  private static Term             activeIngredient = null;

  public static Test suite()
  {
    TestSuite suite = new TestSuite();
    suite.addTestSuite(TeamSprayStatusTest.class);

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
    team.delete();
    team2.delete();

    geoEntity.delete();
    brand.delete();
    
    activeIngredient.delete();
  }

  protected static void classSetUp()
  {
    activeIngredient = TestFixture.createRandomTerm();
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

    team = new SprayTeam();
    team.setTeamId(TestConstants.TEAM_ID);
    team.apply();

    team2 = new SprayTeam();
    team2.setTeamId(TestConstants.TEAM_ID_2);
    team2.apply();
  }

  public void testCreateView()
  {
    SprayData data = new SprayData();
    data.setBrand(brand);
    data.setGeoEntity(geoEntity);
    data.setSprayDate(new Date());
    data.addSprayMethod(SprayMethod.MAIN_SPRAY);
    data.addSurfaceType(SurfaceType.POROUS);
    data.apply();

    try
    {
      TeamSprayStatusView status = new TeamSprayStatusView();
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
      status.setTeamSprayWeek(2);
      status.setSprayTeam(team);
      status.apply();

      try
      {
        TeamSprayStatusView test = (TeamSprayStatusView) SprayStatus.getView(status.getStatusId());

        assertNotNull(test);
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
        assertEquals(team.getId(), test.getSprayTeam().getId());
        assertEquals(status.getTeamSprayWeek(), test.getTeamSprayWeek());
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
    data.addSurfaceType(SurfaceType.POROUS);
    data.apply();

    try
    {

      TeamSprayStatusView status = new TeamSprayStatusView();
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
      status.setSprayTeam(team);
      status.setTeamSprayWeek(2);
      status.apply();

      TeamSprayStatusView edit = (TeamSprayStatusView) SprayStatus.lockView(status.getStatusId());
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
      edit.setTeamSprayWeek(1);
      edit.apply();

      try
      {
        TeamSprayStatusView test = (TeamSprayStatusView) SprayStatus.getView(status.getStatusId());

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
        assertEquals(team.getId(), test.getSprayTeam().getId());
        assertEquals(edit.getTeamSprayWeek(), test.getTeamSprayWeek());
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

  public void testDeleteView()
  {
    SprayData data = new SprayData();
    data.setBrand(brand);
    data.setGeoEntity(geoEntity);
    data.setSprayDate(new Date());
    data.addSprayMethod(SprayMethod.MAIN_SPRAY);
    data.addSurfaceType(SurfaceType.POROUS);
    data.apply();

    try
    {
      TeamSprayStatusView status = new TeamSprayStatusView();
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
      status.setSprayTeam(team);
      status.setTeamSprayWeek(2);
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
    data.addSurfaceType(SurfaceType.POROUS);
    data.apply();

    try
    {

      TeamSprayStatusView status = new TeamSprayStatusView();
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
      status.setSprayTeam(team);
      status.setTeamSprayWeek(2);

      TeamSprayStatusView status2 = new TeamSprayStatusView();
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
      status2.setSprayTeam(team2);
      status2.setTeamSprayWeek(1);

      TeamSprayStatusView[] array = new TeamSprayStatusView[] { status, status2 };
      TeamSprayStatusView[] test = TeamSprayStatusView.applyAll(array);

      try
      {
        assertEquals(array.length, test.length);

        for (int i = 0; i < array.length; i++)
        {
          assertEquals(array[i].getSpray().getId(), test[i].getSpray().getId());
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
          assertEquals(array[i].getSprayTeam().getId(), test[i].getSprayTeam().getId());
          assertEquals(array[i].getTeamSprayWeek(), test[i].getTeamSprayWeek());
        }
      }
      finally
      {
        for (TeamSprayStatusView view : test)
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
    data.addSurfaceType(SurfaceType.POROUS);
    data.apply();

    try
    {
      TeamSprayStatusView status = new TeamSprayStatusView();
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
      status.setTeamSprayWeek(2);
      status.setSprayTeam(team);
      status.apply();

      try
      {
        TeamSprayStatusView[] array = TeamSprayStatusView.search(data, team);

        assertNotNull(array);
        assertEquals(1, array.length);

        TeamSprayStatusView test = array[0];

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
        assertEquals(team.getId(), test.getSprayTeam().getId());
        assertEquals(status.getTeamSprayWeek(), test.getTeamSprayWeek());
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
    data.addSurfaceType(SurfaceType.POROUS);
    data.apply();

    try
    {
      TeamSprayStatusView[] array = TeamSprayStatusView.search(data, team);

      assertNotNull(array);
      assertEquals(0, array.length);
    }
    finally
    {
      data.delete();
    }
  }
}
