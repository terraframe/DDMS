package mdss.entomology;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
    OIterator<? extends CollectionMethod> cIt = CollectionMethod.getAllInstances(null, false, 0, 0)
        .getIterator();
    OIterator<? extends Specie> sIt = Specie.getAllInstances(null, false, 0, 0).getIterator();
    OIterator<? extends IdentificationMethod> iIt = IdentificationMethod.getAllInstances(null, false, 0,
        0).getIterator();

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
    group.setQuantity(20);
    group.setSpecie(specie);
    group.setIdentificationMethod(identificationMethod);
    group.setCollection(collection);
    group.apply();

    MorphologicalSpecieGroup group2 = new MorphologicalSpecieGroup();
    group2.setQuantity(10);
    group2.setSpecie(specie);
    group2.setIdentificationMethod(identificationMethod);
    group2.setCollection(collection);
    group2.apply();

    try
    {
      MosquitoCollection collection2 = MosquitoCollection.get(collection.getId());
      MorphologicalSpecieGroupView[] list = collection2.getMorphologicalSpecieGroups();

      assertEquals(2, list.length);
      assertEquals(new Integer(20), list[0].getQuantity());
      assertEquals(new Integer(10), list[1].getQuantity());
      assertEquals(specie.getTermName(), list[0].getSpecie());
      assertEquals(identificationMethod.getTermName(), list[0].getIdentificationMethod());
    }
    finally
    {
      collection.delete();
    }
  }

  public void testApplyMorphologicalSpecieGroupView()
  {
    MosquitoCollection collection = new MosquitoCollection();
    collection.setGeoEntity(sentinelSite);
    collection.setDateCollected(new Date());
    collection.setCollectionMethod(collectionMethod);
    collection.apply();

    MorphologicalSpecieGroupView group = new MorphologicalSpecieGroupView();
    group.setQuantity(20);
    group.setSpecie(specie.getTermName());
    group.setIdentificationMethod(identificationMethod.getTermName());
    group.setCollectionId(collection.getId());
    group.apply();

    MorphologicalSpecieGroupView group2 = new MorphologicalSpecieGroupView();
    group2.setQuantity(10);
    group2.setSpecie(specie.getTermName());
    group2.setIdentificationMethod(identificationMethod.getTermName());
    group2.setCollectionId(collection.getId());
    group2.apply();

    try
    {
      MosquitoCollection collection2 = MosquitoCollection.get(collection.getId());
      MorphologicalSpecieGroupView[] list = collection2.getMorphologicalSpecieGroups();

      assertEquals(2, list.length);
      assertEquals(new Integer(20), list[0].getQuantity());
      assertEquals(new Integer(10), list[1].getQuantity());
      assertEquals(specie.getTermName(), list[0].getSpecie());
      assertEquals(identificationMethod.getTermName(), list[0].getIdentificationMethod());
    }
    finally
    {
      collection.delete();
    }
  }
  
  public void testSaveAllMorphologicalSpecieGroupView()
  {
    MosquitoCollection collection = new MosquitoCollection();
    collection.setGeoEntity(sentinelSite);
    collection.setDateCollected(new Date());
    collection.setCollectionMethod(collectionMethod);
    collection.apply();

    MorphologicalSpecieGroupView[] array = new MorphologicalSpecieGroupView[2];    

    array[0] = new MorphologicalSpecieGroupView();
    array[0].setQuantity(20);
    array[0].setSpecie(specie.getTermName());
    array[0].setIdentificationMethod(identificationMethod.getTermName());
    array[0].setCollectionId(collection.getId());
    array[1] = new MorphologicalSpecieGroupView();
    array[1].setQuantity(10);
    array[1].setSpecie(specie.getTermName());
    array[1].setIdentificationMethod(identificationMethod.getTermName());
    array[1].setCollectionId(collection.getId());
    
    MorphologicalSpecieGroupView.saveAll(array);

    try
    {
      MosquitoCollection collection2 = MosquitoCollection.get(collection.getId());
      MorphologicalSpecieGroupView[] list = collection2.getMorphologicalSpecieGroups();

      assertEquals(2, list.length);
      assertEquals(new Integer(20), list[0].getQuantity());
      assertEquals(new Integer(10), list[1].getQuantity());
      assertEquals(specie.getTermName(), list[0].getSpecie());
      assertEquals(identificationMethod.getTermName(), list[0].getIdentificationMethod());
    }
    finally
    {
      collection.delete();
    }    
  }

  public void testReapplyMorphologicalSpecieGroupView()
  {
    MosquitoCollection collection = new MosquitoCollection();
    collection.setGeoEntity(sentinelSite);
    collection.setDateCollected(new Date());
    collection.setCollectionMethod(collectionMethod);
    collection.apply();

    MorphologicalSpecieGroup group = new MorphologicalSpecieGroup();
    group.setQuantity(20);
    group.setSpecie(specie);
    group.setIdentificationMethod(identificationMethod);
    group.setCollection(collection);
    group.apply();

    MorphologicalSpecieGroupView view = new MorphologicalSpecieGroupView();
    view.setQuantity(10);
    view.setSpecie(specie.getTermName());
    view.setIdentificationMethod(identificationMethod.getTermName());
    view.setCollectionId(collection.getId());
    view.setGroupId(group.getId());
    view.apply();

    try
    {
      MosquitoCollection collection2 = MosquitoCollection.get(collection.getId());
      MorphologicalSpecieGroupView[] list = collection2.getMorphologicalSpecieGroups();

      assertEquals(1, list.length);
      assertEquals(new Integer(10), list[0].getQuantity());
      assertEquals(specie.getTermName(), list[0].getSpecie());
      assertEquals(identificationMethod.getTermName(), list[0].getIdentificationMethod());
    }
    finally
    {
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
      group.setQuantity(0);
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
      group.setQuantity(10);
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
      group.setQuantity(20);
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