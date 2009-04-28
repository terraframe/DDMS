package dss.vector.solutions.irs;

import java.math.BigDecimal;
import java.util.Date;

import com.terraframe.mojo.dataaccess.cache.DataNotFoundException;

import dss.vector.solutions.entomology.assay.Unit;
import dss.vector.solutions.geo.generated.GeoEntity;
import dss.vector.solutions.geo.generated.SentinalSite;
import dss.vector.solutions.mo.ActiveIngredient;

import junit.extensions.TestSetup;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class TeamSprayStatusTest extends TestCase
{
  private static InsecticideBrand brand = null;

  private static GeoEntity geoEntity = null;

  private static SprayData data = null;

  private static Team team = null;

  private static TeamSpray spray = null;


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
    spray.delete();
    team.delete();
    SprayData.get(brand, geoEntity, new Date(), SprayMethod.MAIN_SPRAY).delete();
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
    brand.addUnits(Unit.PERCENT);
    brand.setWeight(weight);
    brand.setSachetsPerRefill(refill);
    brand.apply();

    geoEntity = new SentinalSite();
    geoEntity.setGeoId("1");
    geoEntity.setEntityName("Sentinel Site");
    geoEntity.apply();

    data = new SprayData();
    data.setBrand(brand);
    data.setGeoEntity(geoEntity);
    data.setSprayDate(new Date());
    data.addSprayMethod(SprayMethod.MAIN_SPRAY);
    data.addSurfaceType(SurfaceType.POROUS);
    data.apply();

    team = new Team();
    team.apply();

    spray = new TeamSpray();
    spray.setSprayData(data);
    spray.setSprayTeam(team);
    spray.setTarget(232);
    spray.setTeamSprayWeek(24);
    spray.apply();
  }

  public void testCreateView()
  {
    TeamSprayStatusView status = new TeamSprayStatusView();
    status.setSpray(spray);
    status.setSprayData(data);
    status.setHouseholds(2);
    status.setStructures(3);
    status.setSprayedHouseholds(5);
    status.setSprayedStructures(2);
    status.setPrevSprayedHouseholds(1);
    status.setPrevSprayedStructures(3);
    status.setRooms(2);
    status.setSprayedRooms(3);
    status.setPeople(3);
    status.setBedNets(1);
    status.setRoomsWithBedNets(4);
    status.setLocked(4);
    status.setOther(1);
    status.setRefused(3);
    status.setTeamSprayWeek(2);
    status.apply();

    try
    {
      TeamSprayStatusView test = (TeamSprayStatusView) SprayStatus.getView(status.getStatusId());

      assertNotNull(test);
      assertEquals(spray.getId(), test.getSpray().getId());
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
      status.deleteConcrete();
    }
  }

  public void testEditView()
  {
    TeamSprayStatusView status = new TeamSprayStatusView();
    status.setSpray(spray);
    status.setSprayData(data);
    status.setHouseholds(2);
    status.setStructures(3);
    status.setSprayedHouseholds(5);
    status.setSprayedStructures(2);
    status.setPrevSprayedHouseholds(1);
    status.setPrevSprayedStructures(3);
    status.setRooms(2);
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
    edit.setHouseholds(4);
    edit.setStructures(6);
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
      assertEquals(spray.getId(), test.getSpray().getId());
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
      edit.deleteConcrete();
    }
  }

  public void testDeleteView()
  {
    TeamSprayStatusView status = new TeamSprayStatusView();
    status.setSpray(spray);
    status.setSprayData(data);
    status.setHouseholds(2);
    status.setStructures(3);
    status.setSprayedHouseholds(5);
    status.setSprayedStructures(2);
    status.setPrevSprayedHouseholds(1);
    status.setPrevSprayedStructures(3);
    status.setRooms(2);
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

    status.deleteConcrete();

    try
    {
      SprayStatus.getView(id);

      fail("Unabled to delete the concrete spray operator");
    }
    catch(DataNotFoundException e)
    {
      //This is expected
    }
  }

  public void testApplyAll()
  {
    TeamSprayStatusView status = new TeamSprayStatusView();
    status.setSpray(spray);
    status.setSprayData(data);
    status.setHouseholds(2);
    status.setStructures(3);
    status.setSprayedHouseholds(5);
    status.setSprayedStructures(2);
    status.setPrevSprayedHouseholds(1);
    status.setPrevSprayedStructures(3);
    status.setRooms(2);
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
    status2.setSpray(spray);
    status2.setSprayData(data);
    status2.setHouseholds(4);
    status2.setStructures(6);
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
    status2.setSprayTeam(team);
    status2.setTeamSprayWeek(1);

    TeamSprayStatusView[] array = new TeamSprayStatusView[]{status, status2};
    TeamSprayStatusView[] test = TeamSprayStatusView.applyAll(array);

    try
    {
      assertEquals(array.length, test.length);

      for(int i = 0; i < array.length; i++)
      {
        assertEquals(spray.getId(), test[i].getSpray().getId());
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
        assertEquals(team.getId(), test[i].getSprayTeam().getId());
        assertEquals(array[i].getTeamSprayWeek(), test[i].getTeamSprayWeek());
      }
    }
    finally
    {
      for(TeamSprayStatusView view : test)
      {
        view.deleteConcrete();
      }
    }
  }

}
