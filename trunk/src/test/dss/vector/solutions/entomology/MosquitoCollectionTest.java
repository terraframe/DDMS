package dss.vector.solutions.entomology;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import junit.extensions.TestSetup;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import com.terraframe.mojo.ProblemException;
import com.terraframe.mojo.ProblemIF;
import com.terraframe.mojo.constants.DatabaseProperties;
import com.terraframe.mojo.dataaccess.CannotDeleteReferencedObject;
import com.terraframe.mojo.session.StartSession;

import dss.vector.solutions.export.entomology.MosquitoCollectionView;
import dss.vector.solutions.geo.generated.GeoEntity;
import dss.vector.solutions.geo.generated.NonSentinelSite;
import dss.vector.solutions.geo.generated.SentinelSite;
import dss.vector.solutions.geo.generated.Trap;
import dss.vector.solutions.mo.CollectionMethod;
import dss.vector.solutions.mo.IdentificationMethod;
import dss.vector.solutions.mo.Specie;

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
    collectionMethod = CollectionMethod.getAll()[0];
    specie = Specie.getAll()[0];
    identificationMethod = IdentificationMethod.getAll()[0];

    sentinelSite = new SentinelSite();
    sentinelSite.setGeoId("0");
    sentinelSite.setEntityName("Sentinel Site");
    sentinelSite.apply();

    nonSentinelSite = new NonSentinelSite();
    nonSentinelSite.setGeoId("1");
    nonSentinelSite.setEntityName("Non Sentinel Site");
    nonSentinelSite.apply();

    fixedTrap = new Trap();
    fixedTrap.setGeoId("2");
    fixedTrap.setEntityName("Fixed Trap");
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

  public void testMosquitoCollectionView()
  {
    MosquitoCollectionView view = new MosquitoCollectionView();

    view.setCollectionMethod(collectionMethod.getTermName());
    view.setDateCollected(new Date());
    //view.setGeoEntity(nonSentinelSite.getGeoId());
    view.apply();

    try
    {
      MosquitoCollection collection = MosquitoCollection.get(view.getCollectionId());

      assertEquals(collectionMethod.getId(), collection.getCollectionMethod().getId());
      assertEquals(view.getDateCollected(), collection.getDateCollected());
      assertEquals(nonSentinelSite.getId(), collection.getGeoEntity().getId());
    }
    finally
    {
      MosquitoCollection.get(view.getCollectionId()).delete();
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
    group.setQuantityFemale(10);
    group.setQuantityMale(10);
    group.setSpecie(specie);
    group.setIdentificationMethod(identificationMethod);
    group.setCollection(collection);
    group.apply();

    MorphologicalSpecieGroup group2 = new MorphologicalSpecieGroup();
    group2.setQuantity(10);
    group2.setQuantityFemale(5);
    group2.setQuantityMale(5);
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
      assertEquals(new Integer(10), list[0].getQuantityFemale());
      assertEquals(new Integer(10), list[0].getQuantityMale());
      assertEquals(new Integer(10), list[1].getQuantity());
      assertEquals(new Integer(5), list[1].getQuantityFemale());
      assertEquals(new Integer(5), list[1].getQuantityMale());
      assertEquals(specie.getId(), list[0].getSpecie().getId());
      assertEquals(identificationMethod.getId(), list[0].getIdentificationMethod().getId());
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

    try
    {
      MorphologicalSpecieGroupView group = new MorphologicalSpecieGroupView();
      group.setQuantity(20);
      group.setSpecie(specie);
      group.setIdentificationMethod(identificationMethod);
      group.setCollection(collection);
      group.apply();

      MorphologicalSpecieGroupView group2 = new MorphologicalSpecieGroupView();
      group2.setQuantity(10);
      group2.setSpecie(specie);
      group2.setIdentificationMethod(identificationMethod);
      group2.setCollection(collection);
      group2.apply();

      MosquitoCollection collection2 = MosquitoCollection.get(collection.getId());
      MorphologicalSpecieGroupView[] list = collection2.getMorphologicalSpecieGroups();

      assertEquals(2, list.length);
      assertEquals(new Integer(20), list[0].getQuantity());
      assertEquals(new Integer(10), list[1].getQuantity());
      assertEquals(specie.getId(), list[0].getSpecie().getId());
      assertEquals(identificationMethod.getId(), list[0].getIdentificationMethod().getId());
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
    array[0].setSpecie(specie);
    array[0].setIdentificationMethod(identificationMethod);
    array[0].setCollection(collection);
    array[1] = new MorphologicalSpecieGroupView();
    array[1].setQuantity(10);
    array[1].setSpecie(specie);
    array[1].setIdentificationMethod(identificationMethod);
    array[1].setCollection(collection);

    MorphologicalSpecieGroupView.saveAll(array);

    try
    {
      MosquitoCollection collection2 = MosquitoCollection.get(collection.getId());
      MorphologicalSpecieGroupView[] list = collection2.getMorphologicalSpecieGroups();

      assertEquals(2, list.length);
      assertEquals(new Integer(20), list[0].getQuantity());
      assertEquals(new Integer(10), list[1].getQuantity());
      assertEquals(specie.getId(), list[0].getSpecie().getId());
      assertEquals(identificationMethod.getId(), list[0].getIdentificationMethod().getId());
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
    view.setSpecie(specie);
    view.setIdentificationMethod(identificationMethod);
    view.setCollection(collection);
    view.setGroupId(group.getId());
    view.apply();

    try
    {
      MosquitoCollection collection2 = MosquitoCollection.get(collection.getId());
      MorphologicalSpecieGroupView[] list = collection2.getMorphologicalSpecieGroups();

      assertEquals(1, list.length);
      assertEquals(new Integer(10), list[0].getQuantity());
      assertEquals(specie.getId(), list[0].getSpecie().getId());
      assertEquals(identificationMethod.getId(), list[0].getIdentificationMethod().getId());
    }
    finally
    {
      collection.delete();
    }
  }

  public void testZeroMorphologicalQuantity()
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
    catch (ProblemException e)
    {
      // This is expected
      List<ProblemIF> problems = e.getProblems();

      assertEquals(1, problems.size());

      assertTrue("Problem is not of type EmptyValueProblem", problems.get(0) instanceof com.terraframe.mojo.dataaccess.attributes.EmptyValueProblem);
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

  public void testDeleteReferencedGeoEntity() throws ParseException
  {
    SimpleDateFormat dateTime = new SimpleDateFormat(DatabaseProperties.getDateFormat());

    SentinelSite entity = new SentinelSite();
    entity.setGeoId("490");
    entity.setEntityName("Sentinel Site");
    entity.apply();

    MosquitoCollection collection = new MosquitoCollection();
    collection.setGeoEntity(entity);
    collection.setDateCollected(dateTime.parse("2008-01-01"));
    collection.setCollectionMethod(collectionMethod);
    collection.apply();

    String entityId = entity.getId();
    String collectionId = collection.getId();

    try
    {
      entity.delete();

      fail("Able to delete Geo Entity which is referenced in a mosquito collection");
    }
    catch (CannotDeleteReferencedObject e)
    {
      // This is expected
    }
    finally
    {
      MosquitoCollection m = MosquitoCollection.get(collectionId);

      if (m != null && m.isAppliedToDB())
      {
        m.delete();
      }

      SentinelSite s = SentinelSite.get(entityId);

      if (s != null && s.isAppliedToDB())
      {
        s.delete();
      }

    }
  }

  public void testInvalidMaleAndFemale()
  {
    MosquitoCollection collection = new MosquitoCollection();
    collection.setGeoEntity(sentinelSite);
    collection.setDateCollected(new Date());
    collection.setCollectionMethod(collectionMethod);
    collection.apply();

    try
    {
      MorphologicalSpecieGroup group = new MorphologicalSpecieGroup();
      group.setQuantity(20);
      group.setQuantityFemale(12);
      group.setQuantityMale(10);
      group.setSpecie(specie);
      group.setIdentificationMethod(identificationMethod);
      group.setCollection(collection);
      group.apply();

      fail("Able to create a morphological specie group with an quantity mismatch");
    }
    catch (ProblemException e)
    {
      List<ProblemIF> problems = e.getProblems();

      assertEquals(1, problems.size());
      assertTrue(problems.get(0) instanceof QuantityMismatchProblem);
    }
    finally
    {
      collection.delete();
    }
  }

  public void testInvalidMale()
  {
    MosquitoCollection collection = new MosquitoCollection();
    collection.setGeoEntity(sentinelSite);
    collection.setDateCollected(new Date());
    collection.setCollectionMethod(collectionMethod);
    collection.apply();

    try
    {
      MorphologicalSpecieGroup group = new MorphologicalSpecieGroup();
      group.setQuantity(20);
      group.setQuantityMale(30);
      group.setSpecie(specie);
      group.setIdentificationMethod(identificationMethod);
      group.setCollection(collection);
      group.apply();

      fail("Able to create a morphological specie group with an invalid male quantity");
    }
    catch (ProblemException e)
    {
      List<ProblemIF> problems = e.getProblems();

      assertEquals(1, problems.size());
      assertTrue(problems.get(0) instanceof InvalidMaleQuantityProblem);
    }
    finally
    {
      collection.delete();
    }
  }

  public void testInvalidFemale()
  {
    MosquitoCollection collection = new MosquitoCollection();
    collection.setGeoEntity(sentinelSite);
    collection.setDateCollected(new Date());
    collection.setCollectionMethod(collectionMethod);
    collection.apply();

    try
    {
      MorphologicalSpecieGroup group = new MorphologicalSpecieGroup();
      group.setQuantity(20);
      group.setQuantityFemale(30);
      group.setSpecie(specie);
      group.setIdentificationMethod(identificationMethod);
      group.setCollection(collection);
      group.apply();

      fail("Able to create a morphological specie group with an invalid female quantity");
    }
    catch (ProblemException e)
    {
      List<ProblemIF> problems = e.getProblems();

      assertEquals(1, problems.size());
      assertTrue(problems.get(0) instanceof InvalidFemaleQuantityProblem);
    }
    finally
    {
      collection.delete();
    }
  }
}