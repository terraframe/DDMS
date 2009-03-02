package mdss.ivcc.mrc.csu.entomology;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import junit.extensions.TestSetup;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import mdss.ivcc.mrc.csu.entomology.assay.AssayTestResult;
import mdss.ivcc.mrc.csu.entomology.assay.biochemical.AEsteraseTestResult;
import mdss.ivcc.mrc.csu.entomology.assay.biochemical.AcHETestResult;
import mdss.ivcc.mrc.csu.entomology.assay.infectivity.PMalariaeTestResult;
import mdss.ivcc.mrc.csu.geo.GeoEntity;
import mdss.ivcc.mrc.csu.geo.Terrain;
import mdss.ivcc.mrc.csu.mo.BiochemicalMethodology;
import mdss.ivcc.mrc.csu.mo.CollectionMethod;
import mdss.ivcc.mrc.csu.mo.Generation;
import mdss.ivcc.mrc.csu.mo.IdentificationMethod;
import mdss.ivcc.mrc.csu.mo.InfectivityMethodology;
import mdss.ivcc.mrc.csu.mo.InsecticideMethodology;
import mdss.ivcc.mrc.csu.mo.MolecularAssayResult;
import mdss.ivcc.mrc.csu.mo.Specie;

import com.terraframe.mojo.constants.DatabaseProperties;

public class MosquitoTest extends TestCase
{

  private static GeoEntity              geoEntity              = null;

  private static MosquitoCollection     collection             = null;

  private static CollectionMethod       collectionMethod       = null;

  private static Specie                 specie                 = null;

  private static IdentificationMethod   identificationMethod   = null;

  private static MolecularAssayResult   result                 = null;

  private static Generation             F0                     = null;

  private static InfectivityMethodology infectivityMethodology = null;
  
  private static InsecticideMethodology insecticideMethodology = null;
  
  private static BiochemicalMethodology biochemicalMethodology = null;

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
    biochemicalMethodology = BiochemicalMethodology.getAll()[0];
    infectivityMethodology = InfectivityMethodology.getAll()[0];
    insecticideMethodology = InsecticideMethodology.getAll()[0];

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

  public void testCreateMosquito() throws ParseException
  {
    SimpleDateFormat dateTime = new SimpleDateFormat(DatabaseProperties.getDateFormat());
    Date date = dateTime.parse("2007-01-01");

    MosquitoView view = new MosquitoView();
    view.setSpecie(specie);
    view.setCollection(collection);
    view.setGeneration(F0);
    view.setIsofemale(false);
    view.setIdentificationMethod(identificationMethod);
    view.addSex(Sex.FEMALE);
    view.setSampleId("0");
    view.setTestDate(date);
    view.setAcHEBiochemical(result);
    view.setAcHEBiochemicalMethod(biochemicalMethodology);
    view.setAcHEMolecular(result);
    view.setAcHEMolecularMethod(insecticideMethodology);
    view.setAEsterase(new Integer(4));
    view.setAEsteraseMethod(biochemicalMethodology);
    view.setPMalariae(true);
    view.setPMalariaeMethod(infectivityMethodology);
    view.apply();

    try
    {
      Mosquito mosquito = Mosquito.get(view.getMosquitoId());

      assertEquals(specie.getId(), mosquito.getSpecie().getId());
      assertEquals(F0.getId(), mosquito.getGeneration().getId());
      assertEquals(view.getMosquitoId(), mosquito.getId());
      assertEquals(identificationMethod.getId(), mosquito.getIdentificationMethod().getId());
      assertEquals(Sex.FEMALE, mosquito.getSex().get(0));
      assertEquals("0", mosquito.getSampleId());
      assertEquals(date, mosquito.getTestDate());
      assertEquals(new Boolean(false), mosquito.getIsofemale());

      List<AssayTestResult> testResults = mosquito.getTestResults();

      assertEquals(4, testResults.size());

      for (AssayTestResult r : testResults)
      {
        if (r instanceof AcHETestResult)
        {
          assertEquals(result.getId(), ( (MolecularAssayResult) r.getTestResult() ).getId());
          assertEquals(biochemicalMethodology.getId(), r.getTestMethod().getId());
        }
        else if (r instanceof mdss.ivcc.mrc.csu.entomology.assay.molecular.AcHETestResult)
        {
          assertEquals(result.getId(), ( (MolecularAssayResult) r.getTestResult() ).getId());
          assertEquals(insecticideMethodology.getId(), r.getTestMethod().getId());
        }
        else if (r instanceof AEsteraseTestResult)
        {
          assertEquals(new Integer(4), (Integer) r.getTestResult());
          assertEquals(biochemicalMethodology.getId(), r.getTestMethod().getId());
        }
        else if (r instanceof PMalariaeTestResult)
        {
          assertEquals(new Boolean(true), (Boolean) r.getTestResult());
          assertEquals(infectivityMethodology.getId(), r.getTestMethod().getId());
        }
      }
    }
    finally
    {
      view.delete();
    }
  }

  public void testUpdateMosquito() throws ParseException
  {
    SimpleDateFormat dateTime = new SimpleDateFormat(DatabaseProperties.getDateFormat());
    Date date = dateTime.parse("2007-01-01");

    MosquitoView view = new MosquitoView();
    view.setSpecie(specie);
    view.setCollection(collection);
    view.setGeneration(F0);
    view.setIsofemale(false);
    view.setIdentificationMethod(identificationMethod);
    view.addSex(Sex.FEMALE);
    view.setSampleId("0");
    view.setTestDate(date);
    view.setAcHEBiochemical(result);
    view.setAcHEBiochemicalMethod(biochemicalMethodology);
    view.setAcHEMolecular(result);
    view.setAcHEMolecularMethod(insecticideMethodology);
    view.setAEsterase(new Integer(4));
    view.setAEsteraseMethod(biochemicalMethodology);
    view.setPMalariae(true);
    view.setPMalariaeMethod(infectivityMethodology);

    view.setAEsterase(new Integer(5));
    view.apply();

    try
    {
      Mosquito mosquito = Mosquito.get(view.getMosquitoId());

      assertEquals(specie.getId(), mosquito.getSpecie().getId());
      assertEquals(F0.getId(), mosquito.getGeneration().getId());
      assertEquals(view.getMosquitoId(), mosquito.getId());
      assertEquals(identificationMethod.getId(), mosquito.getIdentificationMethod().getId());
      assertEquals(Sex.FEMALE, mosquito.getSex().get(0));
      assertEquals("0", mosquito.getSampleId());
      assertEquals(date, mosquito.getTestDate());
      assertEquals(new Boolean(false), mosquito.getIsofemale());

      List<AssayTestResult> testResults = mosquito.getTestResults();

      assertEquals(4, testResults.size());

      for (AssayTestResult r : testResults)
      {
        if (r instanceof AcHETestResult)
        {
          assertEquals(result.getId(), ( (MolecularAssayResult) r.getTestResult() ).getId());
          assertEquals(biochemicalMethodology.getId(), r.getTestMethod().getId());
        }
        else if (r instanceof mdss.ivcc.mrc.csu.entomology.assay.molecular.AcHETestResult)
        {
          assertEquals(result.getId(), ( (MolecularAssayResult) r.getTestResult() ).getId());
          assertEquals(insecticideMethodology.getId(), r.getTestMethod().getId());
        }
        else if (r instanceof AEsteraseTestResult)
        {
          assertEquals(new Integer(5), (Integer) r.getTestResult());
          assertEquals(biochemicalMethodology.getId(), r.getTestMethod().getId());
        }
        else if (r instanceof PMalariaeTestResult)
        {
          assertEquals(new Boolean(true), (Boolean) r.getTestResult());
          assertEquals(infectivityMethodology.getId(), r.getTestMethod().getId());
        }
      }
    }
    finally
    {
      view.delete();
    }
  }

  public void testGetMosquitos() throws ParseException
  {
    SimpleDateFormat dateTime = new SimpleDateFormat(DatabaseProperties.getDateFormat());
    Date date = dateTime.parse("2007-01-01");

    MosquitoView view = new MosquitoView();
    view.setSpecie(specie);
    view.setCollection(collection);
    view.setGeneration(F0);
    view.setIsofemale(false);
    view.setIdentificationMethod(identificationMethod);
    view.addSex(Sex.FEMALE);
    view.setSampleId("0");
    view.setTestDate(date);
    view.setAcHEBiochemical(result);
    view.setAcHEBiochemicalMethod(biochemicalMethodology);
    view.setAcHEMolecular(result);
    view.setAcHEMolecularMethod(insecticideMethodology);
    view.setAEsterase(new Integer(4));
    view.setAEsteraseMethod(biochemicalMethodology);
    view.setPMalariae(true);
    view.setPMalariaeMethod(infectivityMethodology);
    view.apply();

    try
    {
      MosquitoView[] mosquitos = collection.getMosquitos();

      assertEquals(1, mosquitos.length);
      assertEquals(specie.getId(), mosquitos[0].getSpecie().getId());
      assertEquals(F0.getId(), mosquitos[0].getGeneration().getId());
      assertEquals(view.getMosquitoId(), mosquitos[0].getMosquitoId());
      assertEquals(identificationMethod.getId(), mosquitos[0].getIdentificationMethod().getId());
      assertEquals(Sex.FEMALE, mosquitos[0].getSex().get(0));
      assertEquals("0", mosquitos[0].getSampleId());
      assertEquals(date, mosquitos[0].getTestDate());
      assertEquals(new Boolean(false), mosquitos[0].getIsofemale());
      assertEquals(result.getId(), mosquitos[0].getAcHEBiochemical().getId());
      assertEquals(biochemicalMethodology.getId(), mosquitos[0].getAcHEBiochemicalMethod().getId());
      assertEquals(result.getId(), mosquitos[0].getAcHEMolecular().getId());
      assertEquals(insecticideMethodology.getId(), mosquitos[0].getAcHEMolecularMethod().getId());
      assertEquals(null, mosquitos[0].getGABA());
      assertEquals(new Integer(4), mosquitos[0].getAEsterase());
      assertEquals(biochemicalMethodology.getId(), mosquitos[0].getAEsteraseMethod().getId());
      assertEquals(new Boolean(true), mosquitos[0].getPMalariae());
      assertEquals(infectivityMethodology.getId(), mosquitos[0].getPMalariaeMethod().getId());
    }
    finally
    {
      view.delete();
    }
  }

  public void testUninterestingSpecieGroup()
  {
    UninterestingSpecieGroupView view = new UninterestingSpecieGroupView();
    view.setSpecie(specie);
    view.setCollection(collection);
    view.setIdentificationMethod(identificationMethod);
    view.setSampleId("0");
    view.setQuanity(200);
    view.apply();

    try
    {
      UninterestingSpecieGroup group = UninterestingSpecieGroup.get(view.getGroupId());

      assertEquals(specie.getId(), group.getSpecie().getId());
      assertEquals(view.getGroupId(), group.getId());
      assertEquals(identificationMethod.getId(), group.getIdentificationMethod().getId());
      assertEquals("0", group.getSampleId());
      assertEquals(new Integer(200), group.getQuanity());
    }
    finally
    {
      view.delete();
    }
  }

  public void testUpdateUninterestingSpecieGroup()
  {
    UninterestingSpecieGroupView view = new UninterestingSpecieGroupView();
    view.setSpecie(specie);
    view.setCollection(collection);
    view.setIdentificationMethod(identificationMethod);
    view.setSampleId("0");
    view.setQuanity(200);
    view.apply();

    view.setQuanity(400);
    view.apply();

    try
    {
      UninterestingSpecieGroup group = UninterestingSpecieGroup.get(view.getGroupId());

      assertEquals(specie.getId(), group.getSpecie().getId());
      assertEquals(view.getGroupId(), group.getId());
      assertEquals(identificationMethod.getId(), group.getIdentificationMethod().getId());
      assertEquals("0", group.getSampleId());
      assertEquals(new Integer(400), group.getQuanity());
    }
    finally
    {
      view.delete();
    }

  }
}
