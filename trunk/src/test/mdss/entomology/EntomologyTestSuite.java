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

public class EntomologyTestSuite extends TestCase
{
  public static Test suite()
  {
    TestSuite suite = new TestSuite();
    suite.addTestSuite(EntomologyTestSuite.class);

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
  }

  protected static void classTearDown()
  {
  }

  public void testSentinelSiteCollection()
  {
    GeoEntity geoEntity = new GeoEntity();
    geoEntity.setGeoId("5");
    geoEntity.setEntityName("Test Site");
    geoEntity.addTerrain(Terrain.SENTINEL_SITE);
    geoEntity.apply();

    MosquitoCollection collection = new MosquitoCollection();
    collection.setGeoEntity(geoEntity);
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
      geoEntity.delete();
    }
  }

  @StartSession
  public void testNonSentinelSiteCollection()
  {
    GeoEntity geoEntity = new GeoEntity();
    geoEntity.setGeoId("0");
    geoEntity.setEntityName("Test Site");
    geoEntity.addTerrain(Terrain.NON_SENTINEL_SITE);
    geoEntity.apply();

    MosquitoCollection collection = new MosquitoCollection();
    collection.setGeoEntity(geoEntity);
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
      geoEntity.delete();
    }    
  }
  
  public void testInvalidGeoTerrain()
  {
    GeoEntity geoEntity = new GeoEntity();
    geoEntity.setGeoId("0");
    geoEntity.setEntityName("Test Site");
    geoEntity.addTerrain(Terrain.FIXED_TRAP);
    geoEntity.apply();

    MosquitoCollection collection = null;

    try
    {
      collection = new MosquitoCollection();
      collection.setGeoEntity(geoEntity);
      collection.setDateCollected(new Date());
      collection.addCollectionMethod(CollectionMethod.WINDOW_TRAP);
      collection.apply();
      
      fail("Able to create a mosquito collection with a GeoEntity which is not a (non)sentinel site");
    }
    catch(InvalidMosquitoCollectionGeoEntityException e)
    {
      //This is expected
    }
    finally
    {
      if(collection != null && collection.isAppliedToDB())
      {
        collection.delete();
      }
      
      geoEntity.delete();
    }
  }
  
  public void testGetMorphologicalSpecieGroups()
  {
    GeoEntity geoEntity = new GeoEntity();
    geoEntity.setGeoId("0");
    geoEntity.setEntityName("Test Site");
    geoEntity.addTerrain(Terrain.NON_SENTINEL_SITE);
    geoEntity.apply();

    MosquitoCollection collection = new MosquitoCollection();
    collection.setGeoEntity(geoEntity);
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
      OIterator<? extends MorphologicalSpecieGroup> iterator = collection2.getAllMorphologicalSpecieGroup();
      List<MorphologicalSpecieGroup> list = new LinkedList<MorphologicalSpecieGroup>();
      
      while(iterator.hasNext())
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
      geoEntity.delete();
    }    

  }
  
  public void testGeoDateUniquness()
  {
    GeoEntity geoEntity = new GeoEntity();
    geoEntity.setGeoId("0");
    geoEntity.setEntityName("Test Site");
    geoEntity.addTerrain(Terrain.NON_SENTINEL_SITE);
    geoEntity.apply();

    MosquitoCollection collection = new MosquitoCollection();
    collection.setGeoEntity(geoEntity);
    collection.setDateCollected(new Date());
    collection.addCollectionMethod(CollectionMethod.WINDOW_TRAP);
    collection.apply();

    MosquitoCollection duplicate = null;

    try
    {
      duplicate = new MosquitoCollection();
      duplicate.setGeoEntity(geoEntity);
      duplicate.setDateCollected(new Date());
      duplicate.addCollectionMethod(CollectionMethod.WINDOW_TRAP);
      duplicate.apply();      
      
      fail("Able to create mulutiple MosquitoCollections with the same GeoEntity and Date");
    }
    catch(DuplicateDataDatabaseException e)
    {
      //This is expected
    }
    finally
    {
      if(duplicate != null && duplicate.isAppliedToDB())
      {
        duplicate.delete();
      }
      collection.delete();
      geoEntity.delete();
    }
  }

  public void testSearchByGeoEntityAndDate()
  {
    GeoEntity geoEntity = new GeoEntity();
    geoEntity.setGeoId("0");
    geoEntity.setEntityName("Test Site");
    geoEntity.addTerrain(Terrain.NON_SENTINEL_SITE);
    geoEntity.apply();

    MosquitoCollection collection = new MosquitoCollection();
    collection.setGeoEntity(geoEntity);
    collection.setDateCollected(new Date());
    collection.addCollectionMethod(CollectionMethod.WINDOW_TRAP);
    collection.apply();    
    
    try
    {
      MosquitoCollection search = MosquitoCollection.searchByGeoEntityAndDate(geoEntity, new Date());

      assertNotNull(search);
      assertEquals(collection.getId(), search.getId());
    }
    finally
    {
      collection.delete();
      geoEntity.delete();
    }
  }
  
  public void testEmptySearchByGeoEntityAndDate()
  {
    GeoEntity geoEntity = new GeoEntity();
    geoEntity.setGeoId("0");
    geoEntity.setEntityName("Test Site");
    geoEntity.addTerrain(Terrain.NON_SENTINEL_SITE);
    geoEntity.apply();

    try
    {
      assertNull(MosquitoCollection.searchByGeoEntityAndDate(geoEntity, new Date()));
    }
    finally
    {
      geoEntity.delete();
    }    
  }
  
  public void testWrongGeoEntitySearchByGeoEntityAndDate() throws ParseException
  {
    GeoEntity geoEntity = new GeoEntity();
    geoEntity.setGeoId("0");
    geoEntity.setEntityName("Test Site");
    geoEntity.addTerrain(Terrain.NON_SENTINEL_SITE);
    geoEntity.apply();
    
    GeoEntity geoEntity2 = new GeoEntity();
    geoEntity2.setGeoId("1");
    geoEntity2.setEntityName("Test Site 2");
    geoEntity2.addTerrain(Terrain.SENTINEL_SITE);
    geoEntity2.apply();

    MosquitoCollection collection = new MosquitoCollection();
    collection.setGeoEntity(geoEntity);
    collection.setDateCollected(new Date());
    collection.addCollectionMethod(CollectionMethod.WINDOW_TRAP);
    collection.apply();    
    
    try
    {
      assertNull(MosquitoCollection.searchByGeoEntityAndDate(geoEntity2, new Date()));
    }
    finally
    {
      collection.delete();
      geoEntity.delete();
      geoEntity2.delete();
    }
    
  }
  
  public void testWrongDateSearchByGeoEntityAndDate() throws ParseException
  {
    SimpleDateFormat dateTime = new SimpleDateFormat(DatabaseProperties.getDateFormat());

    GeoEntity geoEntity = new GeoEntity();
    geoEntity.setGeoId("0");
    geoEntity.setEntityName("Test Site");
    geoEntity.addTerrain(Terrain.NON_SENTINEL_SITE);
    geoEntity.apply();

    MosquitoCollection collection = new MosquitoCollection();
    collection.setGeoEntity(geoEntity);
    collection.setDateCollected(dateTime.parse("2008-01-01"));
    collection.addCollectionMethod(CollectionMethod.WINDOW_TRAP);
    collection.apply();    
    
    try
    {
      assertNull(MosquitoCollection.searchByGeoEntityAndDate(geoEntity, dateTime.parse("2009-01-01")));
    }
    finally
    {
      collection.delete();
      geoEntity.delete();
    }
    
  }
}