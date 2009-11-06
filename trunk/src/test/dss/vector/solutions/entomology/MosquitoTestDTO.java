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
import dss.vector.solutions.TestFixture;
import dss.vector.solutions.entomology.assay.AssayTestResultDTO;
import dss.vector.solutions.entomology.assay.biochemical.AAcetateTestResultDTO;
import dss.vector.solutions.entomology.assay.biochemical.P450TestResultDTO;
import dss.vector.solutions.entomology.assay.infectivity.PMalariaeTestResultDTO;
import dss.vector.solutions.entomology.assay.molecular.IAcHETestResultDTO;
import dss.vector.solutions.geo.generated.GeoEntity;
import dss.vector.solutions.ontology.Term;
import dss.vector.solutions.ontology.TermDTO;

public class MosquitoTestDTO extends TestCase implements DoNotWeave
{

  private static GeoEntity          geoEntity              = null;

  private static MosquitoCollection collection             = null;

  private static Term               collectionMethod       = null;

  private static Term               specie                 = null;

  private static Term               identificationMethod   = null;

  private static Term               result                 = null;

  private static Term               F0                     = null;

  private static Term               infectivityMethodology = null;

  private static Term               insecticideMethodology = null;

  private static Term               sex                    = null;

  private static ClientSession      clientSession;

  private static ClientRequestIF    clientRequest;

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

    collectionMethod.delete();
    specie.delete();
    identificationMethod.delete();
    F0.delete();
    result.delete();
    infectivityMethodology.delete();
    insecticideMethodology.delete();
    sex.delete();
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
    collectionMethod = TestFixture.createRandomTerm();
    specie = TestFixture.createRandomTerm();
    identificationMethod = TestFixture.createRandomTerm();
    F0 = TestFixture.createRandomTerm();
    result = TestFixture.createRandomTerm();
    infectivityMethodology = TestFixture.createRandomTerm();
    insecticideMethodology = TestFixture.createRandomTerm();
    sex = TestFixture.createRandomTerm();

    geoEntity = TestFixture.createRandomSite();
    collection = TestFixture.createMosquitoCollection(geoEntity, collectionMethod);
  }

  public void testCreateMosquitoDTO() throws ParseException
  {
    SimpleDateFormat dateTime = new SimpleDateFormat(DatabaseProperties.getDateFormat());
    Date date = dateTime.parse("2007-01-01");

    MosquitoViewDTO view = new MosquitoViewDTO(clientRequest);
    view.setSpecie(TermDTO.get(clientRequest, specie.getId()));
    view.setCollection(MosquitoCollectionDTO.get(clientRequest, collection.getId()));
    view.setGeneration(TermDTO.get(clientRequest, F0.getId()));
    view.setIsofemale(false);
    view.setSampleId("0");
    view.setIdentificationMethod(TermDTO.get(clientRequest, identificationMethod.getId()));
    view.setSex(TermDTO.get(clientRequest, sex.getId()));
    view.setTestDate(date);
    view.setP450(true);
    view.setIAcHE(TermDTO.get(clientRequest, result.getId()));
    view.setIAcHEMethod(TermDTO.get(clientRequest, insecticideMethodology.getId()));
    view.setAAcetate(false);
    view.setPMalariae(true);
    view.setPMalariaeMethod(TermDTO.get(clientRequest, infectivityMethodology.getId()));
    view.apply();

    try
    {
      MosquitoDTO mosquito = MosquitoDTO.get(clientRequest, view.getMosquitoId());

      assertEquals(specie.getId(), mosquito.getSpecie().getId());
      assertEquals(F0.getId(), mosquito.getGeneration().getId());
      assertEquals(view.getMosquitoId(), mosquito.getId());
      assertEquals(identificationMethod.getId(), mosquito.getIdentificationMethod().getId());
      assertEquals(SexDTO.FEMALE, mosquito.getSex());
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
          /* FIXME MO REFACTOR
          assertEquals(view.getIAcHE().getId(), ( (MolecularAssayResultDTO) r.getTestResult() ).getId());
          */
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
