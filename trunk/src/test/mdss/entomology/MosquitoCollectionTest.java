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

import com.terraframe.mojo.ProblemException;
import com.terraframe.mojo.ProblemIF;
import com.terraframe.mojo.constants.DatabaseProperties;
import com.terraframe.mojo.dataaccess.attributes.AttributeValueException;
import com.terraframe.mojo.dataaccess.database.DuplicateDataDatabaseException;
import com.terraframe.mojo.query.OIterator;
import com.terraframe.mojo.session.StartSession;

public class MosquitoCollectionTest extends TestCase
{
  private static GeoEntity            sentinelSite         = null;

  private static GeoEntity            nonSentinelSite      = null;

  private static GeoEntity            fixedTrap            = null;

  private static CollectionMethod     collectionMethod     = null;

  private static Specie               specie               = null;

  private static IdentificationMethod identificationMethod = null;

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
    OIterator<? extends CollectionMethod> cIt = CollectionMethod.getAllInstances(null, false, 0, 0).getIterator();
    OIterator<? extends Specie> sIt = Specie.getAllInstances(null, false, 0, 0).getIterator();
    OIterator<? extends IdentificationMethod> iIt = IdentificationMethod.getAllInstances(null, false, 0, 0).getIterator();

    collectionMethod = cIt.next();
    specie = sIt.next();
    identificationMethod = iIt.next();
    
    cIt.close();
    sIt.close();
    iIt.close();

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
    collection.setCollectionMethod(collectionMethod);
    collection.apply();

    String id = collection.getId();

    try
    {
      MosquitoCollection collection2 = MosquitoCollection.get(id);

      assertEquals(collection.getGeoEntity().getId(), collection2.getGeoEntity().getId());
      assertEquals(collection.getDateCollected(), collection2.getDateCollected());
      assertEquals(collection.getCollectionMethod().getId(), collection2.getCollectionMethod().getId());
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
    collection.setCollectionMethod(collectionMethod);
    collection.apply();

    String id = collection.getId();

    try
    {
      MosquitoCollection collection2 = MosquitoCollection.get(id);

      assertEquals(collection.getGeoEntity().getId(), collection2.getGeoEntity().getId());
      assertEquals(collection.getDateCollected(), collection2.getDateCollected());
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
      collection.setDateCollected(new Date());
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

  public void testGetMorphologicalSpecieGroups()
  {
    MosquitoCollection collection = new MosquitoCollection();
    collection.setGeoEntity(sentinelSite);
    collection.setDateCollected(new Date());
    collection.setCollectionMethod(collectionMethod);
    collection.apply();

    MorphologicalSpecieGroup group = new MorphologicalSpecieGroup();
    group.setQuanity(20);
    group.setSpecie(specie);
    group.setIdentificationMethod(identificationMethod);
    group.setCollection(collection);
    group.apply();

    MorphologicalSpecieGroup group2 = new MorphologicalSpecieGroup();
    group2.setQuanity(10);
    group2.setSpecie(specie);
    group2.setIdentificationMethod(identificationMethod);
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
      assertEquals(specie.getId(), list.get(0).getSpecie().getId());
      assertEquals(identificationMethod.getId(), list.get(0).getIdentificationMethod().getId());
    }
    finally
    {
      group.delete();
      group2.delete();
      collection.delete();
    }
  }

  public void testEmptyMorpohologicalQuantity()
  {
    MorphologicalSpecieGroup group = null;
    MosquitoCollection collection = new MosquitoCollection();
    collection.setGeoEntity(sentinelSite);
    collection.setDateCollected(new Date());
    collection.setCollectionMethod(collectionMethod);
    collection.apply();

    try
    {
      group = new MorphologicalSpecieGroup();
      group.setQuanity(0);
      group.setSpecie(specie);
      group.setIdentificationMethod(identificationMethod);
      group.setCollection(collection);
      group.apply();

      fail("Able to create a Morphological Group with an invalid quantity");
    }
    catch (ProblemException e)
    {
      // This is expected, ensure that an invalid quantity problem was thrown
      List<ProblemIF> problems = e.getProblems();
      assertEquals(1, problems.size());
      assertTrue(problems.get(0) instanceof InvalidMorphologicalQuantityProblem);
    }
    finally
    {
      if (group != null && group.isAppliedToDB())
      {
        group.delete();
      }

      collection.delete();
    }
  }

  public void testEmptyMorphologicalQuantity()
  {
    MorphologicalSpecieGroup group = null;
    MosquitoCollection collection = new MosquitoCollection();
    collection.setGeoEntity(sentinelSite);
    collection.setDateCollected(new Date());
    collection.setCollectionMethod(collectionMethod);
    collection.apply();

    try
    {
      group = new MorphologicalSpecieGroup();
      group.setSpecie(specie);
      group.setIdentificationMethod(identificationMethod);
      group.setCollection(collection);
      group.apply();

      fail("Able to create a Morphological Group with an empty quantity");
    }
    catch (AttributeValueException e)
    {
      // This is expected
    }
    finally
    {
      if (group != null && group.isAppliedToDB())
      {
        group.delete();
      }

      collection.delete();
    }
  }

  public void testEmptyMorphologicalSpeicie()
  {
    MorphologicalSpecieGroup group = null;
    MosquitoCollection collection = new MosquitoCollection();
    collection.setGeoEntity(sentinelSite);
    collection.setDateCollected(new Date());
    collection.setCollectionMethod(collectionMethod);
    collection.apply();

    try
    {
      group = new MorphologicalSpecieGroup();
      group.setQuanity(10);
      group.setIdentificationMethod(identificationMethod);
      group.setCollection(collection);
      group.apply();

      fail("Able to create a Morphological Group with an invalid specie");
    }
    catch (ProblemException e)
    {
      // This is expected, ensure that an empty value problem was thrown
      List<ProblemIF> problems = e.getProblems();
      assertEquals(1, problems.size());
      assertTrue(problems.get(0) instanceof EmptyValueProblem);
    }
    finally
    {
      if (group != null && group.isAppliedToDB())
      {
        group.delete();
      }

      collection.delete();
    }
  }

  public void testEmptyMorphologicalIdentificationMethod()
  {
    MorphologicalSpecieGroup group = null;
    MosquitoCollection collection = new MosquitoCollection();
    collection.setGeoEntity(sentinelSite);
    collection.setDateCollected(new Date());
    collection.setCollectionMethod(collectionMethod);
    collection.apply();

    try
    {
      group = new MorphologicalSpecieGroup();
      group.setQuanity(20);
      group.setSpecie(specie);
      group.setCollection(collection);
      group.apply();

      fail("Able to create a Morphological Group with an invalid identification method");
    }
    catch (ProblemException e)
    {
      // This is expected, ensure that an empty value problem was thrown
      List<ProblemIF> problems = e.getProblems();
      assertEquals(1, problems.size());
      assertTrue(problems.get(0) instanceof EmptyValueProblem);
    }
    finally
    {
      if (group != null && group.isAppliedToDB())
      {
        group.delete();
      }

      collection.delete();
    }
  }

  public void testGeoDateUniquness()
  {
    MosquitoCollection collection = new MosquitoCollection();
    collection.setGeoEntity(nonSentinelSite);
    collection.setDateCollected(new Date());
    collection.setCollectionMethod(collectionMethod);
    collection.apply();

    MosquitoCollection duplicate = null;

    try
    {
      duplicate = new MosquitoCollection();
      duplicate.setGeoEntity(nonSentinelSite);
      duplicate.setDateCollected(new Date());
      duplicate.setCollectionMethod(collectionMethod);
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
    collection.setCollectionMethod(collectionMethod);
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
    collection.setCollectionMethod(collectionMethod);
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
    collection.setCollectionMethod(collectionMethod);
    collection.apply();

    try
    {
      assertNull(MosquitoCollection.searchByGeoEntityAndDate(nonSentinelSite, dateTime
          .parse("2009-01-01")));
    }
    finally
    {
      collection.delete();
    }
  }
}