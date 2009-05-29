package dss.vector.solutions.entomology;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import junit.extensions.TestSetup;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestResult;
import junit.framework.TestSuite;

import com.terraframe.mojo.ClientSession;
import com.terraframe.mojo.DoNotWeave;
import com.terraframe.mojo.constants.ClientRequestIF;
import com.terraframe.mojo.constants.DatabaseProperties;
import com.terraframe.mojo.session.StartSession;
import com.terraframe.mojo.web.WebClientSession;

import dss.vector.solutions.TestConstants;
import dss.vector.solutions.entomology.assay.AssayTestResultDTO;
import dss.vector.solutions.entomology.assay.biochemical.AAcetateTestResultDTO;
import dss.vector.solutions.entomology.assay.biochemical.P450TestResultDTO;
import dss.vector.solutions.entomology.assay.infectivity.PMalariaeTestResultDTO;
import dss.vector.solutions.entomology.assay.molecular.IAcHETestResultDTO;
import dss.vector.solutions.geo.generated.GeoEntity;
import dss.vector.solutions.geo.generated.SentinelSite;
import dss.vector.solutions.mo.CollectionMethod;
import dss.vector.solutions.mo.Generation;
import dss.vector.solutions.mo.GenerationDTO;
import dss.vector.solutions.mo.IdentificationMethod;
import dss.vector.solutions.mo.IdentificationMethodDTO;
import dss.vector.solutions.mo.InfectivityMethodology;
import dss.vector.solutions.mo.InfectivityMethodologyDTO;
import dss.vector.solutions.mo.InsecticideMethodology;
import dss.vector.solutions.mo.InsecticideMethodologyDTO;
import dss.vector.solutions.mo.MolecularAssayResult;
import dss.vector.solutions.mo.MolecularAssayResultDTO;
import dss.vector.solutions.mo.Specie;
import dss.vector.solutions.mo.SpecieDTO;

public class MosquitoTestDTO extends TestCase implements DoNotWeave
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

  private static ClientSession          clientSession;

  private static ClientRequestIF        clientRequest;

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

  public static Test suite()
  {
    TestSuite suite = new TestSuite();
    suite.addTestSuite(MosquitoTestDTO.class);

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
    tearDownVars();
    clientSession.logout();
  }

  @StartSession
  protected static void tearDownVars()
  {
    collection.delete();
    geoEntity.delete();
  }

  protected static void classSetUp()
  {
    clientSession = WebClientSession.createUserSession("SYSTEM", TestConstants.PASSWORD, Locale.US);
    clientRequest = clientSession.getRequest();

    setupVars();
  }

  @StartSession
  protected static void setupVars()
  {
    collectionMethod = CollectionMethod.getAll()[0];
    specie = Specie.getAll()[0];
    identificationMethod = IdentificationMethod.getAll()[0];
    F0 = Generation.getAll()[0];
    result = MolecularAssayResult.getAll()[0];
    infectivityMethodology = InfectivityMethodology.getAll()[0];
    insecticideMethodology = InsecticideMethodology.getAll()[0];

    try
    {
      SimpleDateFormat dateTime = new SimpleDateFormat(DatabaseProperties.getDateFormat());
      Date date = dateTime.parse("2006-01-01");

      geoEntity = new SentinelSite();
      geoEntity.setGeoId("0");
      geoEntity.setEntityName("GeoEntity");
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

  public void testCreateMosquitoDTO() throws ParseException
  {
    SimpleDateFormat dateTime = new SimpleDateFormat(DatabaseProperties.getDateFormat());
    Date date = dateTime.parse("2007-01-01");

    MosquitoViewDTO view = new MosquitoViewDTO(clientRequest);
    view.setSpecie(SpecieDTO.get(clientRequest, specie.getId()));
    view.setCollection(MosquitoCollectionDTO.get(clientRequest, collection.getId()));
    view.setGeneration(GenerationDTO.get(clientRequest, F0.getId()));
    view.setIsofemale(false);
    view.setSampleId("0");
    view.setIdentificationMethod(IdentificationMethodDTO
        .get(clientRequest, identificationMethod.getId()));
    view.addSex(SexDTO.FEMALE);
    view.setTestDate(date);
    view.setP450(true);
    view.setIAcHE(MolecularAssayResultDTO.get(clientRequest, result.getId()));
    view.setIAcHEMethod(InsecticideMethodologyDTO.get(clientRequest, insecticideMethodology.getId()));
    view.setAAcetate(false);
    view.setPMalariae(true);
    view
        .setPMalariaeMethod(InfectivityMethodologyDTO.get(clientRequest, infectivityMethodology.getId()));
    view.apply();

    try
    {
      MosquitoDTO mosquito = MosquitoDTO.get(clientRequest, view.getMosquitoId());

      assertEquals(specie.getId(), mosquito.getSpecie().getId());
      assertEquals(F0.getId(), mosquito.getGeneration().getId());
      assertEquals(view.getMosquitoId(), mosquito.getId());
      assertEquals(identificationMethod.getId(), mosquito.getIdentificationMethod().getId());
      assertEquals(SexDTO.FEMALE, mosquito.getSex().get(0));
      assertEquals(date, mosquito.getTestDate());
      assertEquals(new Boolean(false), mosquito.getIsofemale());

      AssayTestResultDTO[] testResults = mosquito.getTestResults();

      assertEquals(4, testResults.length);

      for (AssayTestResultDTO r : testResults)
      {
        if (r instanceof P450TestResultDTO)
        {
          assertEquals(new Boolean(true), r.getTestResult());
        }
        else if (r instanceof IAcHETestResultDTO)
        {
          assertEquals(view.getIAcHE().getId(), ( (MolecularAssayResultDTO) r.getTestResult() ).getId());
          assertEquals(view.getIAcHEMethod().getId(), r.getTestMethod().getId());
        }
        else if (r instanceof AAcetateTestResultDTO)
        {
          assertEquals(new Boolean(false), r.getTestResult());
        }
        else if (r instanceof PMalariaeTestResultDTO)
        {
          assertEquals(view.getPMalariae(), (Boolean) r.getTestResult());
          assertEquals(view.getPMalariaeMethod().getId(), r.getTestMethod().getId());
        }
      }

      assertNull(view.getEKDR());
    }
    finally
    {
      view.deleteConcrete();
    }
  }

}
