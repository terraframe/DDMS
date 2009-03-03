package ivcc.mrc.csu.mdss.entomology;

import ivcc.mrc.csu.mdss.entomology.CompositeMosquitoCollection;
import ivcc.mrc.csu.mdss.entomology.InvalidMorphologicalSpecieProblem;
import ivcc.mrc.csu.mdss.entomology.InvalidMosquitoCollectionPointGeoEntityException;
import ivcc.mrc.csu.mdss.entomology.MorphologicalSpecieGroup;
import ivcc.mrc.csu.mdss.entomology.MorphologicalSpecieGroupView;
import ivcc.mrc.csu.mdss.entomology.MosquitoCollectionPoint;
import ivcc.mrc.csu.mdss.geo.GeoEntity;
import ivcc.mrc.csu.mdss.geo.Terrain;
import ivcc.mrc.csu.mdss.mo.IdentificationMethod;
import ivcc.mrc.csu.mdss.mo.Specie;

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
import com.terraframe.mojo.dataaccess.database.DuplicateDataDatabaseException;

public class MosquitoCollectionPointTest extends TestCase
{
  private static GeoEntity                   sentinelSite         = null;

  private static GeoEntity                   waterBody            = null;

  private static GeoEntity                   fixedTrap            = null;

  private static Specie                      specie               = null;

  private static IdentificationMethod        identificationMethod = null;

  private static CompositeMosquitoCollection composite            = null;

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
    specie = Specie.getAll()[0];
    identificationMethod = IdentificationMethod.getAll()[0];

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
    
    composite = new CompositeMosquitoCollection();
    composite.apply();
  }

  protected static void classTearDown()
  {
    composite.delete();
    sentinelSite.delete();
    waterBody.delete();
    fixedTrap.delete();
  }

  public void testFixedTrapCollection()
  {
    MosquitoCollectionPoint collection = new MosquitoCollectionPoint();
    collection.setGeoEntity(fixedTrap);
    collection.setDateCollected(new Date());
    collection.setCompositeCollection(composite);
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

  public void testWaterBodyCollection()
  {
    MosquitoCollectionPoint collection = new MosquitoCollectionPoint();
    collection.setGeoEntity(waterBody);
    collection.setDateCollected(new Date());
    collection.setCompositeCollection(composite);
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
      collection.setCompositeCollection(composite);
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
    collection.setCompositeCollection(composite);
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
      MosquitoCollectionPoint collection2 = MosquitoCollectionPoint.get(collection.getId());
      MorphologicalSpecieGroupView[] list = collection2.getMorphologicalSpecieGroups();

      assertEquals(2, list.length);
      assertEquals(new Integer(20), list[0].getQuantity());
      assertEquals(new Integer(10), list[1].getQuantity());
      assertEquals(specie.getId(), list[0].getSpecie());
      assertEquals(identificationMethod.getId(), list[0].getIdentificationMethod());
    }
    finally
    {
      collection.delete();
    }
  }

  public void testEmptyMorpohologicalQuantity()
  {
    MosquitoCollectionPoint collection = new MosquitoCollectionPoint();
    collection.setGeoEntity(fixedTrap);
    collection.setDateCollected(new Date());
    collection.setCompositeCollection(composite);
    collection.apply();

    MorphologicalSpecieGroup group = new MorphologicalSpecieGroup();
    group.setQuantity(0);
    group.setCollection(collection);
    group.apply();

    try
    {
      MosquitoCollectionPoint collection2 = MosquitoCollectionPoint.get(collection.getId());
      MorphologicalSpecieGroupView[] list = collection2.getMorphologicalSpecieGroups();

      assertEquals(1, list.length);
      assertEquals(new Integer(0), list[0].getQuantity());
    }
    finally
    {
      collection.delete();
    }
  }

  public void testEmptyMorpohologicalQuantityView()
  {
    MosquitoCollectionPoint collection = new MosquitoCollectionPoint();
    collection.setGeoEntity(fixedTrap);
    collection.setDateCollected(new Date());
    collection.setCompositeCollection(composite);
    collection.apply();

    MorphologicalSpecieGroupView group = new MorphologicalSpecieGroupView();
    group.setQuantity(0);
    group.setCollectionId(collection.getId());
    group.apply();

    try
    {
      MosquitoCollectionPoint collection2 = MosquitoCollectionPoint.get(collection.getId());
      MorphologicalSpecieGroupView[] list = collection2.getMorphologicalSpecieGroups();

      assertEquals(1, list.length);
      assertEquals(new Integer(0), list[0].getQuantity());
    }
    finally
    {
      collection.delete();
    }
  }

  public void testZeroQuantitySetSpecie()
  {
    MorphologicalSpecieGroup group = null;
    MosquitoCollectionPoint collection = new MosquitoCollectionPoint();
    collection.setGeoEntity(fixedTrap);
    collection.setDateCollected(new Date());
    collection.setCompositeCollection(composite);
    collection.apply();

    try
    {
      group = new MorphologicalSpecieGroup();
      group.setQuantity(0);
      group.setSpecie(specie);
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
      collection.delete();
    }
  }

  public void testZeroQuantitySetIdentificationMethod()
  {
    MorphologicalSpecieGroup group = null;
    MosquitoCollectionPoint collection = new MosquitoCollectionPoint();
    collection.setGeoEntity(fixedTrap);
    collection.setDateCollected(new Date());
    collection.setCompositeCollection(composite);
    collection.apply();

    try
    {
      group = new MorphologicalSpecieGroup();
      group.setQuantity(0);
      group.setIdentificationMethod(identificationMethod);
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
      collection.delete();
    }
  }

  public void testGeoDateUniquness()
  {
    MosquitoCollectionPoint collection = new MosquitoCollectionPoint();
    collection.setGeoEntity(waterBody);
    collection.setDateCollected(new Date());
    collection.setCompositeCollection(composite);
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
    collection.setCompositeCollection(composite);
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
    collection.setCompositeCollection(composite);
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
    collection.setCompositeCollection(composite);
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
  
  public void testGetCollectionPoints()
  {
    MosquitoCollectionPoint collection = new MosquitoCollectionPoint();
    collection.setGeoEntity(waterBody);
    collection.setDateCollected(new Date());
    collection.setCompositeCollection(composite);
    collection.apply();
    
    try
    {
      
     MosquitoCollectionPointView[] collections = composite.getCollections();     

     assertEquals(1, collections.length);

     assertEquals(collection.getGeoEntity().getId(), collections[0].getGeoEntity().getId());
     assertEquals(collection.getDateCollected(), collections[0].getDateCollected());
    }
    finally
    {
      collection.delete();
    }

  }
}