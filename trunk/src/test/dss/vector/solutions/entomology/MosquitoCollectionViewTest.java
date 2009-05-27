package dss.vector.solutions.entomology;

import java.util.Date;
import java.util.List;

import com.terraframe.mojo.ProblemException;
import com.terraframe.mojo.ProblemIF;

import junit.extensions.TestSetup;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import dss.vector.solutions.AmbigiousGeoEntityException;
import dss.vector.solutions.UnknownGeoEntityException;
import dss.vector.solutions.UnknownTermException;
import dss.vector.solutions.export.entomology.MosquitoCollectionView;
import dss.vector.solutions.geo.generated.AdminPost;
import dss.vector.solutions.geo.generated.Country;
import dss.vector.solutions.geo.generated.District;
import dss.vector.solutions.geo.generated.Locality;
import dss.vector.solutions.geo.generated.NonSentinelSite;
import dss.vector.solutions.geo.generated.Province;
import dss.vector.solutions.geo.generated.SentinelSite;
import dss.vector.solutions.mo.CollectionMethod;

public class MosquitoCollectionViewTest extends TestCase
{
  private static Country          country                  = null;

  private static Province         province                 = null;
  
  private static District         district                 = null;

  private static AdminPost        adminPost                = null;

  private static Locality         locality                 = null;

  private static SentinelSite     sentinelSite             = null;

  private static NonSentinelSite  nonSentinelSite          = null;

  private static SentinelSite     twinSentinelSite         = null;

  private static SentinelSite     twinSentinelSite2        = null;

  private static SentinelSite     duplicateSentinelSite    = null;

  private static NonSentinelSite  duplicateNonSentinelSite = null;

  private static NonSentinelSite  twinNonSentinelSite      = null;

  private static NonSentinelSite  twinNonSentinelSite2     = null;

  private static CollectionMethod collectionMethod         = null;
  

  private static String           countryName              = "Test Country";

  private static String           provinceName             = "Test Province";

  private static String           districtName             = "Test District";             

  private static String           localityName             = "Test Town";

  private static String           sentinelSiteName         = "Test Sentinel Site";

  private static String           nonSentinelSiteName  = "Test Non Sentinel Site";

  private static String           duplicateSiteName        = "Duplicate Site";

  private static String           twinSentinelSiteName     = "Twin Sentinel Site";

  private static String           twinNonSentinelSiteName  = "Twin Non Sentinel Site";

  private static String           adminName                = "Test Admin Post";

  private static Integer          base                     = new Integer(420);

  public static Test suite()
  {
    TestSuite suite = new TestSuite();
    suite.addTestSuite(MosquitoCollectionViewTest.class);

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

  private static String next()
  {
    base = new Integer(base + 1);

    return base.toString();
  }

  protected static void classSetUp()
  {
    collectionMethod = CollectionMethod.getAll()[0];

    country = new Country();
    country.setGeoId(next());
    country.setEntityName(countryName);
    country.apply();

    province = new Province();
    province.setGeoId(next());
    province.setEntityName(provinceName);
    province.applyWithParent(country.getId(), false);

    district = new District();
    district.setGeoId(next());
    district.setEntityName(districtName);
    district.applyWithParent(province.getId(), false);
    
    adminPost = new AdminPost();
    adminPost.setGeoId(next());
    adminPost.setEntityName(adminName);
    adminPost.applyWithParent(district.getId(), false);

    locality = new Locality();
    locality.setGeoId(next());
    locality.setEntityName(localityName);
    locality.applyWithParent(adminPost.getId(), false);

    sentinelSite = new SentinelSite();
    sentinelSite.setGeoId(next());
    sentinelSite.setEntityName(sentinelSiteName);
    sentinelSite.applyWithParent(locality.getId(), false);

    nonSentinelSite = new NonSentinelSite();
    nonSentinelSite.setGeoId(next());
    nonSentinelSite.setEntityName(nonSentinelSiteName);
    nonSentinelSite.applyWithParent(locality.getId(), false);

    duplicateSentinelSite = new SentinelSite();
    duplicateSentinelSite.setGeoId(next());
    duplicateSentinelSite.setEntityName(duplicateSiteName);
    duplicateSentinelSite.applyWithParent(locality.getId(), false);

    duplicateNonSentinelSite = new NonSentinelSite();
    duplicateNonSentinelSite.setGeoId(next());
    duplicateNonSentinelSite.setEntityName(duplicateSiteName);
    duplicateNonSentinelSite.applyWithParent(locality.getId(), false);

    twinSentinelSite = new SentinelSite();
    twinSentinelSite.setGeoId(next());
    twinSentinelSite.setEntityName(twinSentinelSiteName);
    twinSentinelSite.applyWithParent(locality.getId(), false);

    twinSentinelSite2 = new SentinelSite();
    twinSentinelSite2.setGeoId(next());
    twinSentinelSite2.setEntityName(twinSentinelSiteName);
    twinSentinelSite2.applyWithParent(locality.getId(), false);

    twinNonSentinelSite = new NonSentinelSite();
    twinNonSentinelSite.setGeoId(next());
    twinNonSentinelSite.setEntityName(twinNonSentinelSiteName);
    twinNonSentinelSite.applyWithParent(locality.getId(), false);

    twinNonSentinelSite2 = new NonSentinelSite();
    twinNonSentinelSite2.setGeoId(next());
    twinNonSentinelSite2.setEntityName(twinNonSentinelSiteName);
    twinNonSentinelSite2.applyWithParent(locality.getId(), false);
  }

  protected static void classTearDown()
  {
    sentinelSite.delete();
    nonSentinelSite.delete();
    twinNonSentinelSite.delete();
    twinNonSentinelSite2.delete();
    duplicateNonSentinelSite.delete();
    duplicateSentinelSite.delete();
    twinSentinelSite.delete();
    twinSentinelSite2.delete();
    locality.delete();
    adminPost.delete();
    district.delete();
    province.delete();
    country.delete();
  }

  public void testSentinelSite()
  {
    MosquitoCollectionView view = new MosquitoCollectionView();

    view.setCollectionMethod(collectionMethod.getTermName());
    view.setDateCollected(new Date());
    view.setGeoEntity_0(countryName);
    view.setGeoEntity_1(provinceName);
    view.setGeoEntity_2(districtName);
    view.setGeoEntity_3(localityName);
    view.setGeoEntity_4(sentinelSiteName);
    view.apply();

    try
    {
      MosquitoCollection collection = MosquitoCollection.get(view.getConcreteId());

      assertEquals(collectionMethod.getId(), collection.getCollectionMethod().getId());
      assertEquals(view.getDateCollected(), collection.getDateCollected());
      assertEquals(sentinelSite.getId(), collection.getGeoEntity().getId());
    }
    finally
    {
      view.deleteConcrete();
    }
  }
  
  public void testNonSentinelSite()
  {
    MosquitoCollectionView view = new MosquitoCollectionView();
    
    view.setCollectionMethod(collectionMethod.getTermName());
    view.setDateCollected(new Date());
    view.setGeoEntity_0(countryName);
    view.setGeoEntity_1(provinceName);
    view.setGeoEntity_2(districtName);
    view.setGeoEntity_3(localityName);
    view.setGeoEntity_4(nonSentinelSiteName);
    view.apply();
    
    try
    {
      MosquitoCollection collection = MosquitoCollection.get(view.getConcreteId());
      
      assertEquals(collectionMethod.getId(), collection.getCollectionMethod().getId());
      assertEquals(view.getDateCollected(), collection.getDateCollected());
      assertEquals(nonSentinelSite.getId(), collection.getGeoEntity().getId());
    }
    finally
    {
      view.deleteConcrete();
    }
  }
  
  public void testDuplicateSite()
  {
    MosquitoCollectionView view = new MosquitoCollectionView();        
    view.setCollectionMethod(collectionMethod.getTermName());
    view.setDateCollected(new Date());
    view.setGeoEntity_0(countryName);
    view.setGeoEntity_1(provinceName);
    view.setGeoEntity_2(districtName);
    view.setGeoEntity_3(localityName);
    view.setGeoEntity_4(duplicateSiteName);

    try
    {
      view.apply();
    
      view.deleteConcrete();
      
      fail("Able to import an ambigious Geo Entity");
    }
    catch(AmbigiousGeoEntityException e)
    {
      //This is expected
    }
  }
  
  public void testTwinSentinelSite()
  {
    MosquitoCollectionView view = new MosquitoCollectionView();        
    view.setCollectionMethod(collectionMethod.getTermName());
    view.setDateCollected(new Date());
    view.setGeoEntity_0(countryName);
    view.setGeoEntity_1(provinceName);
    view.setGeoEntity_2(districtName);
    view.setGeoEntity_3(localityName);
    view.setGeoEntity_4(twinSentinelSiteName);
    
    try
    {
      view.apply();
      
      view.deleteConcrete();
      
      fail("Able to import an ambigious Geo Entity");
    }
    catch(AmbigiousGeoEntityException e)
    {
      //This is expected
    }
  }
  
  public void testTwinNonSentinelSite()
  {
    MosquitoCollectionView view = new MosquitoCollectionView();        
    view.setCollectionMethod(collectionMethod.getTermName());
    view.setDateCollected(new Date());
    view.setGeoEntity_0(countryName);
    view.setGeoEntity_1(provinceName);
    view.setGeoEntity_2(districtName);
    view.setGeoEntity_3(localityName);
    view.setGeoEntity_4(twinNonSentinelSiteName);
    
    try
    {
      view.apply();
      
      view.deleteConcrete();
      
      fail("Able to import an ambigious Geo Entity");
    }
    catch(AmbigiousGeoEntityException e)
    {
      //This is expected
    }
  }
  
  public void testUnknownSite()
  {
    MosquitoCollectionView view = new MosquitoCollectionView();        
    view.setCollectionMethod(collectionMethod.getTermName());
    view.setDateCollected(new Date());
    view.setGeoEntity_0(countryName);
    view.setGeoEntity_1(provinceName);
    view.setGeoEntity_2(districtName);
    view.setGeoEntity_3(localityName);
    view.setGeoEntity_4("babga");
    
    try
    {
      view.apply();
      
      view.deleteConcrete();
      
      fail("Able to import an ambigious Geo Entity");
    }
    catch(UnknownGeoEntityException e)
    {
      //This is expected
    }
  }
  
  public void testUnknownLocality()
  {
    MosquitoCollectionView view = new MosquitoCollectionView();        
    view.setCollectionMethod(collectionMethod.getTermName());
    view.setDateCollected(new Date());
    view.setGeoEntity_0(countryName);
    view.setGeoEntity_1(provinceName);
    view.setGeoEntity_2(districtName);
    view.setGeoEntity_3("baddata");
    view.setGeoEntity_4(sentinelSiteName);
    
    try
    {
      view.apply();
      
      view.deleteConcrete();
      
      fail("Able to import an ambigious Geo Entity");
    }
    catch(UnknownGeoEntityException e)
    {
      //This is expected
    }
  }
  
  public void testInvalidCollectionMethod()
  {
    MosquitoCollectionView view = new MosquitoCollectionView();        
    view.setCollectionMethod("bad name");
    view.setDateCollected(new Date());
    view.setGeoEntity_0(countryName);
    view.setGeoEntity_1(provinceName);
    view.setGeoEntity_2(districtName);
    view.setGeoEntity_3(localityName);
    view.setGeoEntity_4(sentinelSiteName);
    
    try
    {
      view.apply();
      
      view.deleteConcrete();
      
      fail("Able to import an undefined collection method");
    }
    catch(UnknownTermException e)
    {
      //This is expected
    }
  }
  
  public void testEmptyCollectionDate()
  {
    MosquitoCollectionView view = new MosquitoCollectionView();        
    view.setCollectionMethod(collectionMethod.getTermName());
    view.setGeoEntity_0(countryName);
    view.setGeoEntity_1(provinceName);
    view.setGeoEntity_2(districtName);
    view.setGeoEntity_3(localityName);
    view.setGeoEntity_4(sentinelSiteName);
    
    try
    {
      view.apply();      
      view.deleteConcrete();
      
      fail("Able to import an ambigious Geo Entity");
    }
    catch(ProblemException e)
    {
      //This is expected
      List<ProblemIF> p = e.getProblems();
      
      assertEquals(1, p.size());
    }    
  }
  
  public void testEmptyCollectionMethod()
  {
    MosquitoCollectionView view = new MosquitoCollectionView();        
    view.setDateCollected(new Date());
    view.setGeoEntity_0(countryName);
    view.setGeoEntity_1(provinceName);
    view.setGeoEntity_2(districtName);
    view.setGeoEntity_3(localityName);
    view.setGeoEntity_4(sentinelSiteName);
    
    try
    {
      view.apply();      
      view.deleteConcrete();
      
      fail("Able to import an ambigious Geo Entity");
    }
    catch(ProblemException e)
    {
      //This is expected
      List<ProblemIF> p = e.getProblems();
      
      assertEquals(1, p.size());
    }    
  }

}
