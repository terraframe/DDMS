package mdss.entomology;

import java.util.Date;

import com.terraframe.mojo.dataaccess.attributes.AttributeValueException;

import junit.extensions.TestSetup;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import mdss.entomology.assay.AdultAgeRange;
import mdss.entomology.assay.AdultDiscriminatingDoseAssay;
import mdss.test.GeoEntity;
import mdss.test.Terrain;

public class ADDATest extends TestCase
{
  private static GeoEntity          geoEntity  = null;

  private static MosquitoCollection collection = null;

  public static Test suite()
  {
    TestSuite suite = new TestSuite();
    suite.addTestSuite(ADDATest.class);

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

  protected static void classSetUp()
  {
    geoEntity = new GeoEntity();
    geoEntity.setGeoId("0");
    geoEntity.setEntityName("GeoEntity");
    geoEntity.addTerrain(Terrain.SENTINEL_SITE);
    geoEntity.apply();

    collection = new MosquitoCollection();
    collection.setGeoEntity(geoEntity);
    collection.addCollectionMethod(CollectionMethod.WINDOW_TRAP);
    collection.setDateCollected(new Date());
    collection.apply();
  }

  protected static void classTearDown()
  {
    collection.delete();
    geoEntity.delete();
  }

  public void testAgeBoundary()
  {
    AdultAgeRange range = new AdultAgeRange();
    range.setStartPoint(0);
    range.setEndPoint(20);
    range.apply();

    try
    {
      assertEquals(new Integer(0), range.getStartPoint());
      assertEquals(new Integer(20), range.getEndPoint());
    }
    finally
    {
      range.delete();
    }
  }

  public void testEqualAges()
  {
    AdultAgeRange range = new AdultAgeRange();
    range.setStartPoint(10);
    range.setEndPoint(10);
    range.apply();

    try
    {

      assertEquals(new Integer(10), range.getStartPoint());
      assertEquals(new Integer(10), range.getEndPoint());
    }
    finally
    {
      range.delete();
    }
  }

  public void testInvalidAgeRange()
  {
    AdultAgeRange range = new AdultAgeRange();
    range.setStartPoint(15);
    range.setEndPoint(10);

    try
    {
      range.apply();

      fail("Able to create an age range where the starting age is after the begining age");
    }
    catch (RuntimeException e)
    {
      // This is expected
    }
    finally
    {
      if (range != null && range.isAppliedToDB())
      {
        range.delete();
      }
    }
  }

  public void testInvalidStartAgeMinimum()
  {
    try
    {
      new AdultAgeRange().setStartPoint(-5);

      fail("Able to create an age range where the begining age is less than the minimum");
    }
    catch (AttributeValueException e)
    {
      // This is expected
    }
  }

  public void testInvalidEndAgeMinimum()
  {
    try
    {
      new AdultAgeRange().setEndPoint(-5);

      fail("Able to create an age range where the ending age is less than the minimum");
    }
    catch (AttributeValueException e)
    {
      // This is expected
    }
  }

  public void testInvalidAgeMaximum()
  {
    AdultAgeRange range = new AdultAgeRange();
    range.setStartPoint(22);
    range.setEndPoint(25);

    try
    {
      range.apply();

      fail("Able to create an age range where the begining and ending age are greater than the maximum age");
    }
    catch (RuntimeException e)
    {
      // This is expected
    }
    finally
    {
      if (range != null && range.isAppliedToDB())
      {
        range.delete();
      }
    }
  }

  public void testUnkownAge()
  {
    AdultDiscriminatingDoseAssay assay = new AdultDiscriminatingDoseAssay();
    assay.setCollection(collection);
    assay.setTestDate(new Date());
    assay.addSex(AssaySex.FEMALE);
    assay.setFed(10);
    assay.setGravid(0);
  }

  public void testTestDate()
  {

  }

  public void testInvalidTestDate()
  {

  }

  public void testFedWithFemales()
  {

  }

  public void testFedWithoutFemales()
  {

  }

  public void testGravidWithFemales()
  {

  }

  public void testGravidWithoutFemales()
  {

  }

  public void testNumberDeadLowerBoundary()
  {

  }

  public void testNumberDeadUpperBoundary()
  {

  }

  public void testValidNumberDead()
  {

  }

  public void testExposureTime()
  {

  }

  public void testInvalidExposureTime()
  {

  }

  public void testCreateTestIntervals()
  {

  }

  public void testValidNumberKnockedDown()
  {

  }

  public void testNumberKnockedUpperBoundary()
  {

  }
}
