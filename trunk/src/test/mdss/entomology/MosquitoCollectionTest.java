package mdss.entomology;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import junit.extensions.TestSetup;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import mdss.test.GeoEntity;
import mdss.test.Terrain;

import com.terraframe.mojo.constants.DatabaseProperties;
import com.terraframe.mojo.dataaccess.database.DuplicateDataDatabaseException;
import com.terraframe.mojo.query.OIterator;
import com.terraframe.mojo.session.StartSession;

public class MosquitoCollectionTest extends TestCase
{
  private static GeoEntity sentinelSite    = null;

  private static GeoEntity nonSentinelSite = null;

  private static GeoEntity fixedTrap       = null;

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
    sentinelSite = new GeoEntity();
    sentinelSite.setGeoId("0");
    sentinelSite.setEntityName("Sentinel Site");
    sentinelSite.addTerrain(Terrain.SENTINEL_SITE);
    sentinelSite.apply();

    nonSentinelSite = new GeoEntity();
    nonSentinelSite.setGeoId("1");
    nonSentinelSite.setEntityName("Non Sentinel Site");
    nonSentinelSite.addTerrain(Terrain.NON_SENTINEL_SITE);
    nonSentinelSite.apply();

    fixedTrap = new GeoEntity();
    fixedTrap.setGeoId("2");
    fixedTrap.setEntityName("Fixed Trap");
    fixedTrap.addTerrain(Terrain.FIXED_TRAP);
    fixedTrap.apply();
  }

  protected static void classTearDown()
  {
    sentinelSite.delete();
    nonSentinelSite.delete();
    fixedTrap.delete();
  }

  public void testSentinelSiteCollection()
  {
    MosquitoCollection collection = new MosquitoCollection();
    collection.setGeoEntity(sentinelSite);
    collection.setDateCollected(new Date());
    collection.addCollectionMethod(CollectionMethod.WINDOW_TRAP);
    collection.apply();

    String id = collection.getId();

    try
    {
      MosquitoCollection collection2 = MosquitoCollection.get(id);

      assertEquals(collection.getGeoEntity().getId(), collection2.getGeoEntity().getId());
      assertEquals(collection.getDateCollected(), collection2.getDateCollected());
      assertEquals(collection.getCollectionMethod().size(), collection2.getCollectionMethod().size(), 1);
      assertEquals(collection.getCollectionMethod().get(0), collection2.getCollectionMethod().get(0));
    }
    finally
    {
      collection.delete();
    }
  }

  @StartSession
  public void testNonSentinelSiteCollection()
  {
    MosquitoCollection collection = new MosquitoCollection();
    collection.setGeoEntity(nonSentinelSite);
    collection.setDateCollected(new Date());
    collection.addCollectionMethod(CollectionMethod.WINDOW_TRAP);
    collection.apply();

    String id = collection.getId();

    try
    {
      MosquitoCollection collection2 = MosquitoCollection.get(id);

      assertEquals(collection.getGeoEntity().getId(), collection2.getGeoEntity().getId());
      assertEquals(collection.getDateCollected(), collection2.getDateCollected());
      assertEquals(collection.getCollectionMethod().size(), collection2.getCollectionMethod().size(), 1);
      assertEquals(collection.getCollectionMethod().get(0), collection2.getCollectionMethod().get(0));
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
      collection.setDateCollected(new Date());
      collection.addCollectionMethod(CollectionMethod.WINDOW_TRAP);
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

  public void testGetMorphologicalSpecieGroups()
  {
    MosquitoCollection collection = new MosquitoCollection();
    collection.setGeoEntity(sentinelSite);
    collection.setDateCollected(new Date());
    collection.addCollectionMethod(CollectionMethod.WINDOW_TRAP);
    collection.apply();

    MorphologicalSpecieGroup group = new MorphologicalSpecieGroup();
    group.setQuanity(20);
    group.addSpecie(Specie.TEST_SPECIE);
    group.addIdentificationMethod(IdentificationMethod.TEST_METHOD);
    group.setCollection(collection);
    group.apply();

    MorphologicalSpecieGroup group2 = new MorphologicalSpecieGroup();
    group2.setQuanity(10);
    group2.addSpecie(Specie.TEST_SPECIE);
    group2.addIdentificationMethod(IdentificationMethod.TEST_METHOD);
    group2.setCollection(collection);
    group2.apply();

    try
    {
      MosquitoCollection collection2 = MosquitoCollection.get(collection.getId());
      OIterator<? extends MorphologicalSpecieGroup> iterator = collection2
          .getAllMorphologicalSpecieGroup();
      List<MorphologicalSpecieGroup> list = new LinkedList<MorphologicalSpecieGroup>();

      while (iterator.hasNext())
      {
        list.add(iterator.next());
      }

      assertEquals(2, list.size());
      assertEquals(new Integer(20), list.get(0).getQuanity());
      assertEquals(new Integer(10), list.get(1).getQuanity());
      assertEquals(Specie.TEST_SPECIE, list.get(0).getSpecie().get(0));
      assertEquals(IdentificationMethod.TEST_METHOD, list.get(0).getIdentificationMethod().get(0));
    }
    finally
    {
      group.delete();
      group2.delete();
      collection.delete();
    }

  }

  public void testGeoDateUniquness()
  {
    MosquitoCollection collection = new MosquitoCollection();
    collection.setGeoEntity(nonSentinelSite);
    collection.setDateCollected(new Date());
    collection.addCollectionMethod(CollectionMethod.WINDOW_TRAP);
    collection.apply();

    MosquitoCollection duplicate = null;

    try
    {
      duplicate = new MosquitoCollection();
      duplicate.setGeoEntity(nonSentinelSite);
      duplicate.setDateCollected(new Date());
      duplicate.addCollectionMethod(CollectionMethod.WINDOW_TRAP);
      duplicate.apply();

      fail("Able to create mulutiple MosquitoCollections with the same GeoEntity and Date");
    }
    catch (DuplicateDataDatabaseException e)
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

  public void testSearchByGeoEntityAndDate()
  {
    MosquitoCollection collection = new MosquitoCollection();
    collection.setGeoEntity(sentinelSite);
    collection.setDateCollected(new Date());
    collection.addCollectionMethod(CollectionMethod.WINDOW_TRAP);
    collection.apply();

    try
    {
      MosquitoCollection search = MosquitoCollection.searchByGeoEntityAndDate(sentinelSite, new Date());

      assertNotNull(search);
      assertEquals(collection.getId(), search.getId());
    }
    finally
    {
      collection.delete();
    }
  }

  public void testEmptySearchByGeoEntityAndDate()
  {
    assertNull(MosquitoCollection.searchByGeoEntityAndDate(nonSentinelSite, new Date()));
  }

  public void testWrongGeoEntitySearchByGeoEntityAndDate() throws ParseException
  {
    MosquitoCollection collection = new MosquitoCollection();
    collection.setGeoEntity(sentinelSite);
    collection.setDateCollected(new Date());
    collection.addCollectionMethod(CollectionMethod.WINDOW_TRAP);
    collection.apply();

    try
    {
      assertNull(MosquitoCollection.searchByGeoEntityAndDate(nonSentinelSite, new Date()));
    }
    finally
    {
      collection.delete();
    }

  }

  public void testWrongDateSearchByGeoEntityAndDate() throws ParseException
  {
    SimpleDateFormat dateTime = new SimpleDateFormat(DatabaseProperties.getDateFormat());

    MosquitoCollection collection = new MosquitoCollection();
    collection.setGeoEntity(nonSentinelSite);
    collection.setDateCollected(dateTime.parse("2008-01-01"));
    collection.addCollectionMethod(CollectionMethod.WINDOW_TRAP);
    collection.apply();

    try
    {
      assertNull(MosquitoCollection.searchByGeoEntityAndDate(nonSentinelSite, dateTime.parse("2009-01-01")));
    }
    finally
    {
      collection.delete();
    }
  }
}