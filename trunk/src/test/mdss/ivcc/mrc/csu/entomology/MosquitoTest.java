package mdss.ivcc.mrc.csu.entomology;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import junit.extensions.TestSetup;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import mdss.ivcc.mrc.csu.geo.GeoEntity;
import mdss.ivcc.mrc.csu.geo.Terrain;
import mdss.ivcc.mrc.csu.mo.CollectionMethod;
import mdss.ivcc.mrc.csu.mo.Generation;
import mdss.ivcc.mrc.csu.mo.IdentificationMethod;
import mdss.ivcc.mrc.csu.mo.MolecularAssayResult;
import mdss.ivcc.mrc.csu.mo.Specie;

import com.terraframe.mojo.constants.DatabaseProperties;

public class MosquitoTest extends TestCase
{

  private static GeoEntity             geoEntity            = null;

  private static MosquitoCollection    collection           = null;

  private static CollectionMethod      collectionMethod     = null;

  private static Specie                specie               = null;

  private static IdentificationMethod  identificationMethod = null;

  private static MolecularAssayResult  result               = null;

  private static Generation            F0                   = null;

  public static Test suite()
  {
    TestSuite suite = new TestSuite();
    suite.addTestSuite(MosquitoTest.class);

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

  protected static void classTearDown()
  {
    collection.delete();
    geoEntity.delete();
  }

  protected static void classSetUp()
  {
    collectionMethod = CollectionMethod.getAll()[0];
    specie = Specie.getAll()[0];
    identificationMethod = IdentificationMethod.getAll()[0];
    F0 = Generation.getAll()[0];
    result = MolecularAssayResult.getAll()[0];

    try
    {
      SimpleDateFormat dateTime = new SimpleDateFormat(DatabaseProperties.getDateFormat());
      Date date = dateTime.parse("2006-01-01");

      geoEntity = new GeoEntity();
      geoEntity.setGeoId("0");
      geoEntity.setEntityName("GeoEntity");
      geoEntity.addTerrain(Terrain.SENTINEL_SITE);
      geoEntity.apply();

      collection = new MosquitoCollection();
      collection.setGeoEntity(geoEntity);
      collection.setCollectionMethod(collectionMethod);
      collection.setDateCollected(date);
      collection.apply();
    }
    catch (ParseException e)
    {
      throw new RuntimeException(e);
    }
  }

  public void testCreateMosquito()
  {
    MosquitoView view = new MosquitoView();
    view.setSpecie(specie);
    view.setCollection(collection);
    view.setGeneration(F0);
    view.setIsofemale(false);
    view.setIdentificationMethod(identificationMethod);
    view.addSex(Sex.FEMALE);
    view.setSampleId("0");
    view.setTestDate(new Date());
    view.setAcHEBiochemical(result);
    view.setAcHEMolecular(result);
//    view.setAEsterase(new Integer(4));
    view.setPMalariae(true);
    view.apply();    
    
    try
    {
      
    }
    finally
    {
      view.delete();
    }
  }
}
