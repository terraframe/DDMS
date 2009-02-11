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
import com.terraframe.mojo.dataaccess.database.DuplicateDataDatabaseException;
import com.terraframe.mojo.query.OIterator;
import com.terraframe.mojo.session.StartSession;

public class MosquitoCollectionPointTest extends TestCase
{
  private static GeoEntity sentinelSite = null;

  private static GeoEntity waterBody    = null;

  private static GeoEntity fixedTrap    = null;

  public static Test suite()
  {
    TestSuite suite = new TestSuite();
    suite.addTestSuite(MosquitoCollectionPointTest.class);

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

    waterBody = new GeoEntity();
    waterBody.setGeoId("1");
    waterBody.setEntityName("Water Body");
    waterBody.addTerrain(Terrain.PERMANENT_WATER_BODY);
    waterBody.apply();

    fixedTrap = new GeoEntity();
    fixedTrap.setGeoId("2");
    fixedTrap.setEntityName("Fixed Trap");
    fixedTrap.addTerrain(Terrain.FIXED_TRAP);
    fixedTrap.apply();
  }

  protected static void classTearDown()
  {
    sentinelSite.delete();
    waterBody.delete();
    fixedTrap.delete();
  }

  public void testFixedTrapCollection()
  {
    MosquitoCollectionPoint collection = new MosquitoCollectionPoint();
    collection.setGeoEntity(fixedTrap);
    collection.setDateCollected(new Date());
    collection.apply();

    String id = collection.getId();

    try
    {
      MosquitoCollectionPoint collection2 = MosquitoCollectionPoint.get(id);

      assertEquals(collection.getGeoEntity().getId(), collection2.getGeoEntity().getId());
      assertEquals(collection.getDateCollected(), collection2.getDateCollected());
    }
    finally
    {
      collection.delete();
    }
  }

  @StartSession
  public void testWaterBodyCollection()
  {
    MosquitoCollectionPoint collection = new MosquitoCollectionPoint();
    collection.setGeoEntity(waterBody);
    collection.setDateCollected(new Date());
    collection.apply();

    String id = collection.getId();

    try
    {
      MosquitoCollectionPoint collection2 = MosquitoCollectionPoint.get(id);

      assertEquals(collection.getGeoEntity().getId(), collection2.getGeoEntity().getId());
      assertEquals(collection.getDateCollected(), collection2.getDateCollected());
    }
    finally
    {
      collection.delete();
    }
  }

  public void testInvalidGeoTerrain()
  {
    MosquitoCollectionPoint collection = null;

    try
    {
      collection = new MosquitoCollectionPoint();
      collection.setGeoEntity(sentinelSite);
      collection.setDateCollected(new Date());
      collection.apply();

      fail("Able to create a mosquito collection point with a GeoEntity which is not a fixed trap or permenant water body");
    }
    catch (InvalidMosquitoCollectionPointGeoEntityException e)
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
    MosquitoCollectionPoint collection = new MosquitoCollectionPoint();
    collection.setGeoEntity(fixedTrap);
    collection.setDateCollected(new Date());
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
      MosquitoCollectionPoint c = MosquitoCollectionPoint.get(collection.getId());
      OIterator<? extends MorphologicalSpecieGroup> iterator = c.getAllMorphologicalSpecieGroup();
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

  public void testEmptyMorpohologicalQuantity()
  {
    MosquitoCollectionPoint collection = new MosquitoCollectionPoint();
    collection.setGeoEntity(fixedTrap);
    collection.setDateCollected(new Date());
    collection.apply();

    MorphologicalSpecieGroup group = new MorphologicalSpecieGroup();
    group.setQuanity(0);
    group.setCollection(collection);
    group.apply();

    try
    {
      MosquitoCollectionPoint c = MosquitoCollectionPoint.get(collection.getId());
      OIterator<? extends MorphologicalSpecieGroup> iterator = c.getAllMorphologicalSpecieGroup();
      List<MorphologicalSpecieGroup> list = new LinkedList<MorphologicalSpecieGroup>();

      while (iterator.hasNext())
      {
        list.add(iterator.next());
      }

      assertEquals(1, list.size());
      assertEquals(new Integer(0), list.get(0).getQuanity());
    }
    finally
    {
      group.delete();
      collection.delete();
    }
  }

  public void testZeroQuantitySetSpecie()
  {
    MorphologicalSpecieGroup group = null;
    MosquitoCollectionPoint collection = new MosquitoCollectionPoint();
    collection.setGeoEntity(fixedTrap);
    collection.setDateCollected(new Date());
    collection.apply();

    try
    {
      group = new MorphologicalSpecieGroup();
      group.setQuanity(0);
      group.addSpecie(Specie.TEST_SPECIE);
      group.setCollection(collection);
      group.apply();

      fail("Able to create a Morphological Group with a quantity of zero but a set specie");
    }
    catch (ProblemException e)
    {
      // This is expected
      List<ProblemIF> problems = e.getProblems();

      assertEquals(1, problems.size());
      assertTrue(problems.get(0) instanceof InvalidMorphologicalSpecieProblem);
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

  public void testZeroQuantitySetIdentificationMethod()
  {
    MorphologicalSpecieGroup group = null;
    MosquitoCollectionPoint collection = new MosquitoCollectionPoint();
    collection.setGeoEntity(fixedTrap);
    collection.setDateCollected(new Date());
    collection.apply();

    try
    {
      group = new MorphologicalSpecieGroup();
      group.setQuanity(0);
      group.addIdentificationMethod(IdentificationMethod.TEST_METHOD);
      group.setCollection(collection);
      group.apply();

      fail("Able to create a Morphological Group with a quantity of zero but set a identification method");
    }
    catch (ProblemException e)
    {
      // This is expected
      List<ProblemIF> problems = e.getProblems();

      assertEquals(1, problems.size());
      assertTrue(problems.get(0) instanceof InvalidMorphologicalSpecieProblem);
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
    MosquitoCollectionPoint collection = new MosquitoCollectionPoint();
    collection.setGeoEntity(waterBody);
    collection.setDateCollected(new Date());
    collection.apply();

    MosquitoCollectionPoint duplicate = null;

    try
    {
      duplicate = new MosquitoCollectionPoint();
      duplicate.setGeoEntity(waterBody);
      duplicate.setDateCollected(new Date());
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
    MosquitoCollectionPoint collection = new MosquitoCollectionPoint();
    collection.setGeoEntity(fixedTrap);
    collection.setDateCollected(new Date());
    collection.apply();

    try
    {
      MosquitoCollectionPoint search = MosquitoCollectionPoint.searchByGeoEntityAndDate(fixedTrap,
          new Date());

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
    assertNull(MosquitoCollectionPoint.searchByGeoEntityAndDate(waterBody, new Date()));
  }

  public void testWrongGeoEntitySearchByGeoEntityAndDate() throws ParseException
  {
    MosquitoCollectionPoint collection = new MosquitoCollectionPoint();
    collection.setGeoEntity(fixedTrap);
    collection.setDateCollected(new Date());
    collection.apply();

    try
    {
      assertNull(MosquitoCollectionPoint.searchByGeoEntityAndDate(waterBody, new Date()));
    }
    finally
    {
      collection.delete();
    }

  }

  public void testWrongDateSearchByGeoEntityAndDate() throws ParseException
  {
    SimpleDateFormat dateTime = new SimpleDateFormat(DatabaseProperties.getDateFormat());

    MosquitoCollectionPoint collection = new MosquitoCollectionPoint();
    collection.setGeoEntity(waterBody);
    collection.setDateCollected(dateTime.parse("2008-01-01"));
    collection.apply();

    try
    {
      assertNull(MosquitoCollectionPoint.searchByGeoEntityAndDate(waterBody, dateTime
          .parse("2009-01-01")));
    }
    finally
    {
      collection.delete();
    }
  }
}