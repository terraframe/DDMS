package dss.vector.solutions.entomology;

import java.util.Date;

import junit.extensions.TestSetup;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestResult;
import junit.framework.TestSuite;

import com.runwaysdk.session.Request;

import dss.vector.solutions.TestFixture;
import dss.vector.solutions.geo.generated.GeoEntity;
import dss.vector.solutions.geo.generated.SentinelSite;
import dss.vector.solutions.ontology.Term;

public class MosquitoCollectionTest extends TestCase
{
  private static GeoEntity sentinelSite         = null;

  private static GeoEntity nonSentinelSite      = null;

  private static GeoEntity fixedTrap            = null;

  private static Term      collectionMethod     = null;

  private static Term      specie               = null;

  private static Term      identificationMethod = null;

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

  public static Test suite()
  {
    TestSuite suite = new TestSuite();
    suite.addTestSuite(MosquitoCollectionTest.class);

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
    collectionMethod = TestFixture.createRandomTerm();
    specie = TestFixture.createRandomTerm();
    identificationMethod = TestFixture.createRandomTerm();

    sentinelSite = new SentinelSite();
    sentinelSite.setGeoId("0");
    sentinelSite.setEntityName("Sentinel Site");
    sentinelSite.apply();

    nonSentinelSite = new SentinelSite();
    nonSentinelSite.setGeoId("1");
    nonSentinelSite.setEntityName("Non Sentinel Site");
    nonSentinelSite.apply();

    fixedTrap = new SentinelSite();
    fixedTrap.setGeoId("2");
    fixedTrap.setEntityName("Fixed Trap");
    fixedTrap.apply();
  }

  protected static void classTearDown()
  {
    sentinelSite.delete();
    nonSentinelSite.delete();
    fixedTrap.delete();

    collectionMethod.delete();
    specie.delete();
    identificationMethod.delete();
  }

  public void testSentinelSiteCollection()
  {
    MosquitoCollection collection = new MosquitoCollection();
    collection.setGeoEntity(sentinelSite);
    collection.setCollectionDate(new Date());
    collection.setCollectionMethod(collectionMethod);
    collection.apply();

    String id = collection.getId();

    try
    {
      MosquitoCollection collection2 = MosquitoCollection.get(id);

      assertEquals(collection.getGeoEntity().getId(), collection2.getGeoEntity().getId());
      assertEquals(collection.getCollectionDate(), collection2.getCollectionDate());
      assertEquals(collection.getCollectionMethod().getId(), collection2.getCollectionMethod().getId());
    }
    finally
    {
      collection.delete();
    }
  }

  @Request
  public void testNonSentinelSiteCollection()
  {
    MosquitoCollection collection = new MosquitoCollection();
    collection.setGeoEntity(nonSentinelSite);
    collection.setCollectionDate(new Date());
    collection.setCollectionMethod(collectionMethod);
    collection.apply();

    String id = collection.getId();

    try
    {
      MosquitoCollection collection2 = MosquitoCollection.get(id);

      assertEquals(collection.getGeoEntity().getId(), collection2.getGeoEntity().getId());
      assertEquals(collection.getCollectionDate(), collection2.getCollectionDate());
      assertEquals(collection.getCollectionMethod().getId(), collection2.getCollectionMethod().getId());
    }
    finally
    {
      collection.delete();
    }
  }

  public void testInvalidGeoTerrain()
  {
    MosquitoCollection collection = null;

    try
    {
      collection = new MosquitoCollection();
      collection.setGeoEntity(fixedTrap);
      collection.setCollectionDate(new Date());
      collection.setCollectionMethod(collectionMethod);
      collection.apply();

      fail("Able to create a mosquito collection with a GeoEntity which is not a (non)sentinel site");
    }
    catch (InvalidMosquitoCollectionGeoEntityException e)
    {
      // This is expected
    }
    finally
    {
      if (collection != null && collection.isAppliedToDB())
      {
        collection.delete();
      }
    }
  }

  public void testGeoDateUniquness()
  {
    MosquitoCollection collection = new MosquitoCollection();
    collection.setGeoEntity(nonSentinelSite);
    collection.setCollectionDate(new Date());
    collection.setCollectionMethod(collectionMethod);
    collection.apply();

    MosquitoCollection duplicate = null;

    try
    {
      duplicate = new MosquitoCollection();
      duplicate.setGeoEntity(nonSentinelSite);
      duplicate.setCollectionDate(new Date());
      duplicate.setCollectionMethod(collectionMethod);
      duplicate.apply();

      fail("Able to create mulutiple MosquitoCollections with the same GeoEntity and Date");
    }
    catch (MosquitoCollectionAllreadyExistsException e)
    {
      // This is expected
    }
    finally
    {
      if (duplicate != null && duplicate.isAppliedToDB())
      {
        duplicate.delete();
      }

      collection.delete();
    }
  }

}