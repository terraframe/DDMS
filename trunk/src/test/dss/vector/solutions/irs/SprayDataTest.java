package dss.vector.solutions.irs;

import java.math.BigDecimal;
import java.util.Date;

import junit.extensions.TestSetup;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import dss.vector.solutions.geo.generated.GeoEntity;
import dss.vector.solutions.geo.generated.SentinelSite;
import dss.vector.solutions.mo.ActiveIngredient;

public class SprayDataTest extends TestCase
{
  private static InsecticideBrand brand = null;

  private static GeoEntity geoEntity = null;

  public static Test suite()
  {
    TestSuite suite = new TestSuite();
    suite.addTestSuite(SprayDataTest.class);

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
    brand.delete();
    geoEntity.delete();
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

    geoEntity = new SentinelSite();
    geoEntity.setGeoId("0");
    geoEntity.setEntityName("Sentinel Site");
    geoEntity.apply();
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

    try
    {
      SprayData test = SprayData.get(data.getId());

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
      data.delete();
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

    SprayData edit = SprayData.lock(data.getId());
    edit.clearSprayMethod();
    edit.clearSurfaceType();
    edit.addSprayMethod(SprayMethod.MOP_UP);
    edit.addSurfaceType(SurfaceType.NON_POROUS);
    edit.apply();

    try
    {
      SprayData test = SprayData.get(edit.getId());

      assertNotNull(test);
      assertEquals(edit.getBrand().getId(), test.getBrand().getId());
      assertEquals(edit.getGeoEntity().getId(), test.getGeoEntity().getId());
      assertEquals(edit.getSprayDate(), test.getSprayDate());
      assertEquals(1, test.getSprayMethod().size());
      assertEquals(SprayMethod.MOP_UP, test.getSprayMethod().get(0));
      assertEquals(1, test.getSurfaceType().size());
      assertEquals(SurfaceType.NON_POROUS, test.getSurfaceType().get(0));
    }
    finally
    {
      edit.delete();
    }
  }

  public void testSearch()
  {
    Date date = new Date();
    SprayMethod sprayMethod = SprayMethod.MAIN_SPRAY;

    SprayData data = new SprayData();
    data.setBrand(brand);
    data.setGeoEntity(geoEntity);
    data.setSprayDate(date);
    data.addSprayMethod(sprayMethod);
    data.addSurfaceType(SurfaceType.POROUS);
    data.apply();

    try
    {
      SprayData test = SprayData.search(brand, geoEntity, date, sprayMethod);

      assertNotNull(test);
      assertEquals(data.getBrand().getId(), test.getBrand().getId());
      assertEquals(data.getGeoEntity().getId(), test.getGeoEntity().getId());
      assertEquals(data.getSprayDate(), test.getSprayDate());
      assertEquals(1, test.getSprayMethod().size());
      assertEquals(sprayMethod, test.getSprayMethod().get(0));
      assertEquals(1, test.getSurfaceType().size());
      assertEquals(SurfaceType.POROUS, test.getSurfaceType().get(0));
    }
    finally
    {
      data.delete();
    }

  }

}
