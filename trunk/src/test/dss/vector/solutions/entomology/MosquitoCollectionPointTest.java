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
import com.terraframe.mojo.dataaccess.database.DuplicateDataDatabaseException;

import dss.vector.solutions.geo.generated.GeoEntity;
import dss.vector.solutions.geo.generated.PermanentWaterBody;
import dss.vector.solutions.geo.generated.SentinalSite;
import dss.vector.solutions.geo.generated.Trap;
import dss.vector.solutions.mo.IdentificationMethod;
import dss.vector.solutions.mo.Specie;

public class MosquitoCollectionPointTest extends TestCase
{
  private static GeoEntity                   sentinelSite         = null;

  private static GeoEntity                   waterBody            = null;

  private static GeoEntity                   fixedTrap            = null;

  private static Specie                      specie               = null;

  private static IdentificationMethod        identificationMethod = null;

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

    sentinelSite = new SentinalSite();
    sentinelSite.setGeoId("0");
    sentinelSite.setEntityName("Sentinel Site");
    sentinelSite.apply();

    waterBody = new PermanentWaterBody();
    waterBody.setGeoId("1");
    waterBody.setEntityName("Water Body");
    waterBody.apply();

    fixedTrap = new Trap();
    fixedTrap.setGeoId("2");
    fixedTrap.setEntityName("Fixed Trap");
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
      assertEquals(specie.getId(), list[0].getSpecie().getId());
      assertEquals(identificationMethod.getId(), list[0].getIdentificationMethod().getId());
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
    
    collection.apply();

    MorphologicalSpecieGroupView group = new MorphologicalSpecieGroupView();
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

  public void testSearchByGeoEntityAndDate() throws ParseException
  {
    SimpleDateFormat dateTime = new SimpleDateFormat(DatabaseProperties.getDateFormat());
    Date startDate = dateTime.parse("2008-01-01");
    Date endDate = dateTime.parse("2009-01-01");
    Date date = dateTime.parse("2008-03-01");
    Date date2 = dateTime.parse("2008-05-01");

    MosquitoCollectionPoint collection = new MosquitoCollectionPoint();
    collection.setGeoEntity(fixedTrap);
    collection.setDateCollected(date);    
    collection.apply();

    MorphologicalSpecieGroup group = new MorphologicalSpecieGroup();
    group.setQuantity(20);
    group.setSpecie(specie);
    group.setIdentificationMethod(identificationMethod);
    group.setCollection(collection);
    group.apply();
    
    MosquitoCollectionPoint collection2 = new MosquitoCollectionPoint();
    collection2.setGeoEntity(fixedTrap);
    collection2.setDateCollected(date2);    
    collection2.apply();

    MorphologicalSpecieGroup group2 = new MorphologicalSpecieGroup();
    group2.setQuantity(20);
    group2.setSpecie(specie);
    group2.setIdentificationMethod(identificationMethod);
    group2.setCollection(collection2);
    group2.apply();

    MorphologicalSpecieGroup group3 = new MorphologicalSpecieGroup();
    group3.setQuantity(20);
    group3.setSpecie(specie);
    group3.setIdentificationMethod(identificationMethod);
    group3.setCollection(collection2);
    group3.apply();


    try
    {
      MorphologicalSpecieGroupView[] groups = MosquitoCollectionPoint.searchByGeoEntityAndDate(fixedTrap, startDate, endDate);

      assertNotNull(groups);
      assertEquals(3, groups.length);
      assertEquals(collection.getId(), groups[0].getCollection().getId());
      assertEquals(group.getId(), groups[0].getGroupId());
      assertEquals(collection2.getId(), groups[1].getCollection().getId());
      assertEquals(group2.getId(), groups[1].getGroupId());
      assertEquals(collection2.getId(), groups[2].getCollection().getId());
      assertEquals(group3.getId(), groups[2].getGroupId());
    }
    finally
    {
      collection.delete();
    }
  }

  public void testEmptySearchByGeoEntityAndDate()
  {
    MorphologicalSpecieGroupView[] groups = MosquitoCollectionPoint.searchByGeoEntityAndDate(fixedTrap, new Date(), new Date());

    assertNotNull(groups);
    assertEquals(0, groups.length);
  }

  public void testWrongGeoEntitySearchByGeoEntityAndDate() throws ParseException
  {
    MosquitoCollectionPoint collection = new MosquitoCollectionPoint();
    collection.setGeoEntity(fixedTrap);
    collection.setDateCollected(new Date());    
    collection.apply();
    
    MorphologicalSpecieGroup group = new MorphologicalSpecieGroup();
    group.setQuantity(20);
    group.setSpecie(specie);
    group.setIdentificationMethod(identificationMethod);
    group.setCollection(collection);
    group.apply();


    try
    {
      MorphologicalSpecieGroupView[] groups = MosquitoCollectionPoint.searchByGeoEntityAndDate(waterBody, new Date(), new Date());

      assertNotNull(groups);
      assertEquals(0, groups.length);
    }
    finally
    {
      collection.delete();
    }

  }

  public void testWrongDateSearchByGeoEntityAndDate() throws ParseException
  {
    SimpleDateFormat dateTime = new SimpleDateFormat(DatabaseProperties.getDateFormat());
    Date searchDate = dateTime.parse("2009-01-01");
    Date date = dateTime.parse("2008-01-01");

    MosquitoCollectionPoint collection = new MosquitoCollectionPoint();
    collection.setGeoEntity(waterBody);
    collection.setDateCollected(date);    
    collection.apply();

    try
    {
      MorphologicalSpecieGroupView[] groups = MosquitoCollectionPoint.searchByGeoEntityAndDate(waterBody, searchDate, searchDate);

      assertNotNull(groups);
      assertEquals(0, groups.length);
    }
    finally
    {
      collection.delete();
    }
  }  
}