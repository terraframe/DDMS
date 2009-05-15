package dss.vector.solutions.irs;

import java.math.BigDecimal;
import java.util.Date;

import junit.extensions.TestSetup;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import com.terraframe.mojo.dataaccess.cache.DataNotFoundException;

import dss.vector.solutions.Person;
import dss.vector.solutions.geo.generated.GeoEntity;
import dss.vector.solutions.geo.generated.SprayZone;
import dss.vector.solutions.mo.ActiveIngredient;

public class ZoneSprayTest extends TestCase
{
  private static InsecticideBrand brand = null;

  private static GeoEntity geoEntity = null;

  private static SprayData data = null;
  
  private static SprayTeam team = null;
  
  private static Person person = null;
  
  private static SprayOperator operator = null;
  
  private static SprayLeader leader = null;

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
    SprayData.get(data.getId()).delete();
    
    leader.delete();
    operator.delete();
    person.delete();
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
    brand.setBrandName("Test Brand");    
    brand.apply();

    geoEntity = new SprayZone();
    geoEntity.setGeoId("0");
    geoEntity.setEntityName("Spray Zone");
    geoEntity.apply();

    data = new SprayData();
    data.setBrand(brand);
    data.setGeoEntity(geoEntity);
    data.setSprayDate(new Date());
    data.addSprayMethod(SprayMethod.MAIN_SPRAY);
    data.addSurfaceType(SurfaceType.POROUS);
    data.apply();
    
    person = new Person();
    person.setFirstName("Justin");
    person.setLastName("Smethie");
    person.setDateOfBirth(new Date());
    person.apply();
    
    operator = new SprayOperator();
    operator.setPerson(person);
    operator.setOperatorId("0012");
    operator.apply();
    
    leader = new SprayLeader();
    leader.setPerson(person);
    leader.apply();
        
    person.setSprayLeaderDelegate(leader);
    person.setSprayOperatorDelegate(operator);

    team = new SprayTeam();
    team.setTeamId("322");
    team.apply(); 
    
    team.create(geoEntity.getGeoId(), leader.getId(), new String[]{operator.getId()});
  }

  public void testCreate()
  {
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
    spray.addSurfaceType(SurfaceType.POROUS);
    spray.apply();

    ZoneSprayView edit = ZoneSpray.lockView(spray.getSprayId());
    edit.apply();

    try
    {
      ZoneSprayView test = ZoneSpray.getView(spray.getSprayId());

      assertNotNull(test);
      assertEquals(data.getBrand().getId(), test.getBrand().getId());
      assertEquals(data.getGeoEntity().getId(), test.getGeoEntity().getId());
      assertEquals(data.getSprayDate(), test.getSprayDate());
      assertEquals(1, test.getSprayMethod().size());
      assertEquals(SprayMethod.MAIN_SPRAY, test.getSprayMethod().get(0));
      assertEquals(1, test.getSurfaceType().size());
      assertEquals(SurfaceType.POROUS, test.getSurfaceType().get(0));
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
    spray.addSurfaceType(SurfaceType.POROUS);
    spray.apply();

    try
    {
      ZoneSprayView test = ZoneSpray.getView(spray.getSprayId());

      assertNotNull(test);
      assertEquals(data.getBrand().getId(), test.getBrand().getId());
      assertEquals(data.getGeoEntity().getId(), test.getGeoEntity().getId());
      assertEquals(data.getSprayDate(), test.getSprayDate());
      assertEquals(1, test.getSprayMethod().size());
      assertEquals(SprayMethod.MAIN_SPRAY, test.getSprayMethod().get(0));
      assertEquals(1, test.getSurfaceType().size());
      assertEquals(SurfaceType.POROUS, test.getSurfaceType().get(0));
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
    spray.addSurfaceType(SurfaceType.POROUS);
    spray.apply();

    String id = spray.getSprayId();

    spray.deleteConcrete();

    try
    {
      ZoneSpray.getView(id);

      fail("Unabled to delete the concrete spray operator");
    }
    catch(DataNotFoundException e)
    {
      //This is expected
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
    spray.addSurfaceType(SurfaceType.POROUS);
    spray.apply();

    try
    {
      String geoId = geoEntity.getGeoId();
      ZoneSprayView test = ZoneSprayView.searchBySprayData(geoId, date, method, brand);

      assertNotNull(test);
      assertEquals(data.getBrand().getId(), test.getBrand().getId());
      assertEquals(data.getGeoEntity().getId(), test.getGeoEntity().getId());
      assertEquals(data.getSprayDate(), test.getSprayDate());
      assertEquals(1, test.getSprayMethod().size());
      assertEquals(method, test.getSprayMethod().get(0));
      assertEquals(1, test.getSurfaceType().size());
      assertEquals(SurfaceType.POROUS, test.getSurfaceType().get(0));
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

  public void testGetStatus()
  {   
    Date date = new Date();
    SprayMethod method = SprayMethod.MAIN_SPRAY;

    ZoneSprayView spray = new ZoneSprayView();
    spray.setBrand(brand);
    spray.setGeoEntity(geoEntity);
    spray.setSprayDate(date);
    spray.addSprayMethod(method);
    spray.addSurfaceType(SurfaceType.POROUS);
    spray.apply();
    
    try
    {
      TeamSprayStatusView[] status = spray.getStatus();
      
      assertEquals(1, status.length);
    }
    finally
    {
      spray.deleteConcrete();
    }
  }

}
