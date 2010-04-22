package dss.vector.solutions.entomology;

import java.util.List;

import com.runwaysdk.query.OIterator;
import com.runwaysdk.query.QueryFactory;

import junit.extensions.TestSetup;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestResult;
import junit.framework.TestSuite;
import dss.vector.solutions.TestFixture;
import dss.vector.solutions.geo.generated.GeoEntity;
import dss.vector.solutions.ontology.Term;

public class ImmatureCollectionTest extends TestCase
{
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

  private static ImmatureCollectionView collection   = null;

  private static GeoEntity              entity       = null;

  private static GeoEntity              entity2      = null;

  private static Term                   taxon1       = null;

  private static Term                   taxon2       = null;

  private static Term                   taxon3       = null;

  private static Term                   premiseType1 = null;

  private static Term                   premiseType2 = null;

  public static Test suite()
  {
    TestSuite suite = new TestSuite();
    suite.addTestSuite(ImmatureCollectionTest.class);

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
    entity = TestFixture.createRandomSite();
    entity2 = TestFixture.createRandomSite();
    premiseType1 = TestFixture.createRandomTerm();
    premiseType2 = TestFixture.createRandomTerm();
    taxon1 = TestFixture.createRandomTerm();
    taxon2 = TestFixture.createRandomTerm();
    taxon3 = TestFixture.createRandomTerm();

    collection = TestFixture.createImmatureCollection(entity, premiseType1, taxon1);
    collection.apply();
  }

  protected static void classTearDown()
  {
    collection.deleteConcrete();

    taxon3.delete();
    taxon2.delete();
    taxon1.delete();
    premiseType2.delete();
    premiseType1.delete();
    entity.delete();
    entity2.delete();
  }

  public void testExactMatch()
  {
    ImmatureCollectionView _collection = ImmatureCollectionView.getCollection(collection);

    assertNotNull(_collection);
    assertEquals(collection.getConcreteId(), _collection.getConcreteId());
    assertEquals(collection.getPremiseId(), _collection.getPremiseId());
    assertEquals(collection.getTaxonId(), _collection.getTaxonId());
  }

  public void testPremiseMatch()
  {
    ImmatureCollectionView search = TestFixture.createImmatureCollection(entity, premiseType1, taxon2);
    ImmatureCollectionView _collection = ImmatureCollectionView.getCollection(search);

    assertNotNull(_collection);
    assertEquals(collection.getConcreteId(), _collection.getConcreteId());
    assertEquals(collection.getPremiseId(), _collection.getPremiseId());
    assertEquals("", _collection.getTaxonId());
  }

  public void testCollectionMatch()
  {
    ImmatureCollectionView search = TestFixture.createImmatureCollection(entity, premiseType2, taxon2);
    ImmatureCollectionView _collection = ImmatureCollectionView.getCollection(search);

    assertNotNull(_collection);
    assertEquals(collection.getConcreteId(), _collection.getConcreteId());
    assertEquals("", _collection.getPremiseId());
    assertEquals("", _collection.getTaxonId());
  }

  public void testTaxonMisMatch()
  {
    ImmatureCollectionView search = TestFixture.createImmatureCollection(entity, premiseType2, taxon1);
    ImmatureCollectionView _collection = ImmatureCollectionView.getCollection(search);

    assertNotNull(_collection);
    assertEquals(collection.getConcreteId(), _collection.getConcreteId());
    assertEquals("", _collection.getPremiseId());
    assertEquals("", _collection.getTaxonId());
  }

  public void testNoMatch()
  {
    ImmatureCollectionView search = TestFixture.createImmatureCollection(entity2, premiseType2, taxon1);
    ImmatureCollectionView _collection = ImmatureCollectionView.getCollection(search);

    assertNotNull(_collection);
    assertEquals("", _collection.getConcreteId());
    assertEquals("", _collection.getPremiseId());
    assertEquals("", _collection.getTaxonId());
  }

  public void testQuery()
  {
    ImmatureCollectionViewQuery query = new ImmatureCollectionViewQuery(new QueryFactory());
    OIterator<? extends ImmatureCollectionView> it = query.getIterator();

    try
    {
      List<? extends ImmatureCollectionView> list = it.getAll();

      assertEquals(1, list.size());
    }
    finally
    {
      it.close();
    }
  }

  public void testMultiQuery()
  {
    ImmatureCollectionView search = TestFixture.createImmatureCollection(entity2, premiseType2, taxon2);
    ImmatureCollectionView _collection = ImmatureCollectionView.getCollection(search);
    _collection.apply();

    try
    {
      ImmatureCollectionViewQuery query = new ImmatureCollectionViewQuery(new QueryFactory());
      OIterator<? extends ImmatureCollectionView> it = query.getIterator();

      try
      {
        List<? extends ImmatureCollectionView> list = it.getAll();

        assertEquals(2, list.size());
      }
      finally
      {
        it.close();
      }
    }
    finally
    {
      _collection.deleteConcrete();
    }
  }  
  
  public void testSearchQuery()
  {
    ImmatureCollectionViewQuery query = ImmatureCollectionView.searchCollections(collection, ImmatureCollectionView.GEOENTITY, false, 20, 1);
    OIterator<? extends ImmatureCollectionView> it = query.getIterator();
    
    try
    {
      List<? extends ImmatureCollectionView> list = it.getAll();
      
      assertEquals(1, list.size());
    }
    finally
    {
      it.close();
    }
  }
  
  public void testNoSearchQuery()
  {
    ImmatureCollectionView search = TestFixture.createImmatureCollection(entity2, premiseType2, taxon1);
    ImmatureCollectionViewQuery query = ImmatureCollectionView.searchCollections(search, ImmatureCollectionView.GEOENTITY, false, 20, 1);
    OIterator<? extends ImmatureCollectionView> it = query.getIterator();

    try
    {
      List<? extends ImmatureCollectionView> list = it.getAll();

      assertEquals(0, list.size());
    }
    finally
    {
      it.close();
    }
  }
}
