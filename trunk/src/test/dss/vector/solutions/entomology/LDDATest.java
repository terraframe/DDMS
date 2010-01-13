package dss.vector.solutions.entomology;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import junit.extensions.TestSetup;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestResult;
import junit.framework.TestSuite;

import com.terraframe.mojo.ClientSession;
import com.terraframe.mojo.ProblemException;
import com.terraframe.mojo.ProblemIF;
import com.terraframe.mojo.business.InformationDTO;
import com.terraframe.mojo.constants.ClientRequestIF;
import com.terraframe.mojo.constants.DatabaseProperties;
import com.terraframe.mojo.web.WebClientSession;

import dss.vector.solutions.CurrentDateProblem;
import dss.vector.solutions.TestConstants;
import dss.vector.solutions.TestFixture;
import dss.vector.solutions.entomology.assay.InvalidDeadQuantityProblem;
import dss.vector.solutions.entomology.assay.InvalidGenerationProblem;
import dss.vector.solutions.entomology.assay.InvalidIntervalTimeProblem;
import dss.vector.solutions.entomology.assay.InvalidTestDateProblem;
import dss.vector.solutions.entomology.assay.LarvaeDiscriminatingDoseAssay;
import dss.vector.solutions.entomology.assay.LarvaeDiscriminatingDoseAssayDTO;
import dss.vector.solutions.entomology.assay.PotentiallyResistantCollectionDTO;
import dss.vector.solutions.entomology.assay.ResistantCollectionDTO;
import dss.vector.solutions.entomology.assay.SusceptibleCollectionDTO;
import dss.vector.solutions.general.Insecticide;
import dss.vector.solutions.general.InsecticideDTO;
import dss.vector.solutions.geo.generated.GeoEntity;
import dss.vector.solutions.ontology.Term;
import dss.vector.solutions.ontology.TermDTO;

public class LDDATest extends TestCase
{
  private static GeoEntity          geoEntity            = null;

  private static MosquitoCollection collection           = null;

  private static Term               collectionMethod     = null;

  private static Term               specie               = null;

  private static Term               identificationMethod = null;

  private static Term               assayMethod          = null;

  private static Insecticide        insecticide          = null;

  private static Term               F0                   = null;

  private static Term               F1                   = null;

  private static Term               startTime            = null;

  private static Term               endTime              = null;

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
    suite.addTestSuite(LDDATest.class);

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
    clientSession = WebClientSession.createUserSession("SYSTEM", TestConstants.PASSWORD, new Locale[]{Locale.US});
    clientRequest = clientSession.getRequest();
    clientRequest.setKeepMessages(false);

    collectionMethod = TestFixture.createRandomTerm();
    specie = TestFixture.createRandomTerm();
    identificationMethod = TestFixture.createRandomTerm();
    assayMethod = TestFixture.createRandomTerm();
    F0 = TestFixture.createRandomTerm();
    F1 = TestFixture.createRandomTerm();
    startTime = TestFixture.createRandomTerm();
    endTime = TestFixture.createRandomTerm();

    geoEntity = TestFixture.createRandomSite();
    collection = TestFixture.createMosquitoCollection(geoEntity, collectionMethod);
    insecticide = TestFixture.createInsecticide();
  }

  protected static void classTearDown()
  {
    TestFixture.delete(insecticide);
    collection.delete();
    geoEntity.delete();

    collectionMethod.delete();
    specie.delete();
    identificationMethod.delete();
    assayMethod.delete();
    F0.delete();
    F1.delete();
    startTime.delete();
    endTime.delete();

    clientSession.logout();
  }

  public void testLDDA() throws ParseException
  {
    SimpleDateFormat dateTime = new SimpleDateFormat(DatabaseProperties.getDateFormat());
    Date date = dateTime.parse("2008-01-01");

    LarvaeDiscriminatingDoseAssay assay = new LarvaeDiscriminatingDoseAssay();
    assay.setSpecie(specie);
    assay.setCollection(collection);
    assay.setTestDate(date);
    assay.setIdentificationMethod(identificationMethod);
    assay.setTestMethod(assayMethod);
    assay.setExposureTime(60);
    
    assay.setHoldingTime(24);
    assay.setControlTestMortality(new Float(1.34));
    assay.setIsofemale(false);
    assay.setGeneration(F0);
    assay.setQuantityDead(5);
    assay.setQuantityTested(30);
    assay.setInsecticide(insecticide);
    assay.setStartPoint(startTime);
    assay.setEndPoint(endTime);
    assay.apply();

    try
    {

      LarvaeDiscriminatingDoseAssay test = LarvaeDiscriminatingDoseAssay.get(assay.getId());

      assertEquals(collection.getId(), test.getCollection().getId());
      assertEquals(specie.getId(), test.getSpecie().getId());
      assertEquals(date, test.getTestDate());
      assertEquals(identificationMethod.getId(), test.getIdentificationMethod().getId());
      assertEquals(assayMethod.getId(), test.getTestMethod().getId());
      assertEquals(new Integer(60), test.getExposureTime());
      
      assertEquals(new Integer(24), test.getHoldingTime());
      assertEquals(new Integer(5), test.getQuantityDead());
      assertEquals(new Integer(30), test.getQuantityTested());
      assertEquals(assay.getControlTestMortality(), test.getControlTestMortality());
      assertEquals(new Boolean(false), test.getIsofemale());
      assertEquals(insecticide.getId(), test.getInsecticide().getId());
      assertEquals(startTime.getId(), test.getStartPoint().getId());
      assertEquals(endTime.getId(), test.getEndPoint().getId());
    }
    finally
    {
      assay.delete();
    }
  }

  public void testUnkownAge() throws ParseException
  {
    SimpleDateFormat dateTime = new SimpleDateFormat(DatabaseProperties.getDateFormat());
    Date date = dateTime.parse("2008-01-01");

    LarvaeDiscriminatingDoseAssay assay = new LarvaeDiscriminatingDoseAssay();
    assay.setCollection(collection);
    assay.setTestDate(date);
    assay.setIdentificationMethod(identificationMethod);
    assay.setTestMethod(assayMethod);
    assay.setGeneration(F1);
    assay.setExposureTime(60);
    
    assay.setHoldingTime(24);
    assay.setControlTestMortality(new Float(1.34));
    assay.setIsofemale(false);
    assay.setQuantityDead(5);
    assay.setQuantityTested(30);
    assay.setInsecticide(insecticide);

    assay.apply();

    try
    {

      LarvaeDiscriminatingDoseAssay test = LarvaeDiscriminatingDoseAssay.get(assay.getId());

      assertEquals(collection.getId(), test.getCollection().getId());
      assertEquals(date, test.getTestDate());

      assertEquals(identificationMethod.getId(), test.getIdentificationMethod().getId());
      assertEquals(assayMethod.getId(), test.getTestMethod().getId());

      assertEquals(new Integer(60), test.getExposureTime());
      
      assertEquals(new Integer(24), test.getHoldingTime());
      assertEquals(new Integer(5), test.getQuantityDead());
      assertEquals(new Integer(30), test.getQuantityTested());
      assertEquals(assay.getControlTestMortality(), test.getControlTestMortality());
      assertEquals(new Boolean(false), test.getIsofemale());
      assertEquals(insecticide.getId(), test.getInsecticide().getId());

    }
    finally
    {
      assay.delete();
    }
  }

  public void testInvalidAgeInADDA() throws ParseException
  {
    SimpleDateFormat dateTime = new SimpleDateFormat(DatabaseProperties.getDateFormat());
    Date date = dateTime.parse("2008-01-01");
    LarvaeDiscriminatingDoseAssay assay = new LarvaeDiscriminatingDoseAssay();

    try
    {
      assay.setCollection(collection);
      assay.setTestDate(date);

      assay.setIdentificationMethod(identificationMethod);
      assay.setTestMethod(assayMethod);

      assay.setExposureTime(60);
      
      assay.setHoldingTime(24);
      assay.setControlTestMortality(new Float(1.34));
      assay.setIsofemale(false);
      assay.setQuantityDead(5);
      assay.setQuantityTested(30);

      assay.setInsecticide(insecticide);

      assay.apply();

      fail("Able to create an adult assay with an invalid age range");
    }
    catch (ProblemException e)
    {
      // This is expected
    }
    finally
    {
      if (assay != null && assay.isAppliedToDB())
      {
        assay.delete();
      }
    }
  }

  public void testInvalidTestDate() throws ParseException
  {
    SimpleDateFormat dateTime = new SimpleDateFormat(DatabaseProperties.getDateFormat());
    Date date = dateTime.parse("2003-01-01");

    LarvaeDiscriminatingDoseAssay assay = new LarvaeDiscriminatingDoseAssay();

    try
    {
      assay.setCollection(collection);
      assay.setTestDate(date);
      assay.setIdentificationMethod(identificationMethod);
      assay.setTestMethod(assayMethod);
      assay.setExposureTime(60);
      
      assay.setGeneration(F1);
      assay.setHoldingTime(24);
      assay.setControlTestMortality(new Float(1.34));
      assay.setIsofemale(false);
      assay.setQuantityDead(5);
      assay.setQuantityTested(30);
      assay.setInsecticide(insecticide);
      assay.apply();

      fail("Able to create an adult assay with an test date before the collection date");
    }
    catch (ProblemException e)
    {
      // This is expected
      List<ProblemIF> problems = e.getProblems();

      assertEquals(1, problems.size());
      assertTrue(problems.get(0) instanceof InvalidTestDateProblem);

      InvalidTestDateProblem problem = (InvalidTestDateProblem) problems.get(0);

      assertEquals(assay.getId(), problem.getComponentId());
      assertEquals(date, problem.getTestDate());
      assertEquals(assay.getCollection().getCollectionDate(), problem.getCollectionDate());
    }
    finally
    {
      if (assay != null && assay.isAppliedToDB())
      {
        assay.delete();
      }
    }
  }

  public void testCurrentDateProblem() throws ParseException
  {
    Calendar calendar = Calendar.getInstance();
    calendar.add(Calendar.DAY_OF_YEAR, 99);
    Date date = calendar.getTime();

    LarvaeDiscriminatingDoseAssay assay = new LarvaeDiscriminatingDoseAssay();

    try
    {
      assay.setCollection(collection);
      assay.setTestDate(date);
      assay.setIdentificationMethod(identificationMethod);
      assay.setTestMethod(assayMethod);
      assay.setExposureTime(60);
      
      assay.setGeneration(F1);
      assay.setHoldingTime(24);
      assay.setControlTestMortality(new Float(1.34));
      assay.setIsofemale(false);
      assay.setQuantityDead(5);
      assay.setQuantityTested(30);
      assay.setInsecticide(insecticide);
      assay.apply();

      fail("Able to create an assay with an test date after the current date");
    }
    catch (ProblemException e)
    {
      // This is expected
      List<ProblemIF> problems = e.getProblems();

      assertEquals(1, problems.size());
      assertTrue(problems.get(0) instanceof CurrentDateProblem);
    }
    finally
    {
      if (assay != null && assay.isAppliedToDB())
      {
        assay.delete();
      }
    }
  }

  public void testInsecticideGenericName() throws ParseException
  {
    SimpleDateFormat dateTime = new SimpleDateFormat(DatabaseProperties.getDateFormat());
    Date date = dateTime.parse("2008-01-01");

    LarvaeDiscriminatingDoseAssay assay = new LarvaeDiscriminatingDoseAssay();
    assay.setCollection(collection);
    assay.setTestDate(date);
    assay.setIdentificationMethod(identificationMethod);
    assay.setTestMethod(assayMethod);
    assay.setExposureTime(60);
    
    assay.setHoldingTime(24);
    assay.setControlTestMortality(new Float(1.34));
    assay.setIsofemale(false);
    assay.setQuantityDead(5);
    assay.setQuantityTested(30);
    assay.setInsecticide(insecticide);
    assay.setGeneration(F1);
    assay.apply();

    try
    {
      LarvaeDiscriminatingDoseAssay test = LarvaeDiscriminatingDoseAssay.get(assay.getId());

      assertEquals(collection.getId(), test.getCollection().getId());
      assertEquals(date, test.getTestDate());
      assertEquals(identificationMethod.getId(), test.getIdentificationMethod().getId());
      assertEquals(assayMethod.getId(), test.getTestMethod().getId());
      assertEquals(assay.getExposureTime(), test.getExposureTime());      
      assertEquals(assay.getHoldingTime(), test.getHoldingTime());
      assertEquals(assay.getQuantityDead(), test.getQuantityDead());
      assertEquals(assay.getQuantityTested(), test.getQuantityTested());
      assertEquals(assay.getControlTestMortality(), test.getControlTestMortality());
      assertEquals(assay.getIsofemale(), test.getIsofemale());
      assertEquals(insecticide.getId(), test.getInsecticide().getId());
    }
    finally
    {
      assay.delete();
    }
  }

  public void testNumberDeadLowerBoundary() throws ParseException
  {
    SimpleDateFormat dateTime = new SimpleDateFormat(DatabaseProperties.getDateFormat());
    Date date = dateTime.parse("2008-01-01");

    LarvaeDiscriminatingDoseAssay assay = new LarvaeDiscriminatingDoseAssay();
    assay.setCollection(collection);
    assay.setTestDate(date);
    assay.setIdentificationMethod(identificationMethod);
    assay.setTestMethod(assayMethod);
    assay.setExposureTime(60);
    
    assay.setHoldingTime(24);
    assay.setControlTestMortality(new Float(1.34));
    assay.setIsofemale(false);
    assay.setQuantityDead(0);
    assay.setQuantityTested(30);
    assay.setInsecticide(insecticide);
    assay.setGeneration(F1);
    assay.apply();

    try
    {

      LarvaeDiscriminatingDoseAssay test = LarvaeDiscriminatingDoseAssay.get(assay.getId());

      assertEquals(collection.getId(), test.getCollection().getId());
      assertEquals(date, test.getTestDate());
      assertEquals(identificationMethod.getId(), test.getIdentificationMethod().getId());
      assertEquals(assayMethod.getId(), test.getTestMethod().getId());
      assertEquals(new Integer(60), test.getExposureTime());
      
      assertEquals(new Integer(24), test.getHoldingTime());
      assertEquals(new Integer(0), test.getQuantityDead());
      assertEquals(new Integer(30), test.getQuantityTested());
      assertEquals(assay.getControlTestMortality(), test.getControlTestMortality());
      assertEquals(new Boolean(false), test.getIsofemale());
      assertEquals(insecticide.getId(), test.getInsecticide().getId());
    }
    finally
    {
      assay.delete();
    }
  }

  public void testNumberDeadUpperBoundary() throws ParseException
  {
    SimpleDateFormat dateTime = new SimpleDateFormat(DatabaseProperties.getDateFormat());
    Date date = dateTime.parse("2008-01-01");

    LarvaeDiscriminatingDoseAssay assay = new LarvaeDiscriminatingDoseAssay();
    assay.setCollection(collection);
    assay.setTestDate(date);

    assay.setIdentificationMethod(identificationMethod);
    assay.setTestMethod(assayMethod);
    assay.setExposureTime(60);
    
    assay.setHoldingTime(24);
    assay.setControlTestMortality(new Float(1.34));
    assay.setIsofemale(false);
    assay.setQuantityDead(30);
    assay.setQuantityTested(30);

    assay.setInsecticide(insecticide);

    assay.setGeneration(F1);
    assay.apply();

    try
    {

      LarvaeDiscriminatingDoseAssay test = LarvaeDiscriminatingDoseAssay.get(assay.getId());

      assertEquals(collection.getId(), test.getCollection().getId());
      assertEquals(date, test.getTestDate());

      assertEquals(identificationMethod.getId(), test.getIdentificationMethod().getId());
      assertEquals(assayMethod.getId(), test.getTestMethod().getId());
      assertEquals(new Integer(60), test.getExposureTime());      
      assertEquals(new Integer(24), test.getHoldingTime());
      assertEquals(new Integer(30), test.getQuantityDead());
      assertEquals(new Integer(30), test.getQuantityTested());
      assertEquals(assay.getControlTestMortality(), test.getControlTestMortality());
      assertEquals(new Boolean(false), test.getIsofemale());
      assertEquals(insecticide.getId(), test.getInsecticide().getId());

    }
    finally
    {
      assay.delete();
    }
  }

  public void testNumberDeadGreaterThanTotalTested() throws ParseException
  {
    SimpleDateFormat dateTime = new SimpleDateFormat(DatabaseProperties.getDateFormat());
    Date date = dateTime.parse("2008-01-01");

    LarvaeDiscriminatingDoseAssay assay = new LarvaeDiscriminatingDoseAssay();
    int quantityDead = 45;
    int quantityTested = 30;

    try
    {
      assay.setCollection(collection);
      assay.setTestDate(date);
      assay.setIdentificationMethod(identificationMethod);
      assay.setTestMethod(assayMethod);
      assay.setGeneration(F1);
      assay.setExposureTime(60);
      
      assay.setHoldingTime(24);
      assay.setControlTestMortality(new Float(1.34));
      assay.setIsofemale(false);
      assay.setQuantityDead(quantityDead);
      assay.setQuantityTested(quantityTested);
      assay.setInsecticide(insecticide);
      assay.apply();

      fail("Able to set the number dead larger than the total number tested");
    }
    catch (ProblemException e)
    {
      // This is expected
      List<ProblemIF> problems = e.getProblems();

      assertEquals(1, problems.size());
      assertTrue(problems.get(0) instanceof InvalidDeadQuantityProblem);

      InvalidDeadQuantityProblem problem = (InvalidDeadQuantityProblem) problems.get(0);

      assertEquals(assay.getId(), problem.getComponentId());
      assertEquals(new Integer(quantityDead), problem.getQuantityDead());
      assertEquals(new Integer(quantityTested), problem.getQuantityTested());
    }
    finally
    {
      if (assay != null && assay.isAppliedToDB())
      {
        assay.delete();
      }
    }
  }

  public void testInvalidExposureTime() throws ParseException
  {
    SimpleDateFormat dateTime = new SimpleDateFormat(DatabaseProperties.getDateFormat());
    Date date = dateTime.parse("2008-01-01");

    LarvaeDiscriminatingDoseAssay assay = new LarvaeDiscriminatingDoseAssay();
    int exposureTime = 60;
    int intervalTime = 80;

    try
    {
      assay.setCollection(collection);
      assay.setTestDate(date);

      assay.setGeneration(F1);
      assay.setIdentificationMethod(identificationMethod);
      assay.setTestMethod(assayMethod);
      assay.setExposureTime(exposureTime);      
      assay.setHoldingTime(24);
      assay.setControlTestMortality(new Float(1.34));
      assay.setIsofemale(false);
      assay.setQuantityDead(30);
      assay.setQuantityTested(30);

      assay.setInsecticide(insecticide);

      assay.apply();

      fail("Able to set an interval time larger than the exposure time");
    }
    catch (ProblemException e)
    {
      // This is expected
      List<ProblemIF> problems = e.getProblems();

      assertEquals(1, problems.size());
      assertTrue(problems.get(0) instanceof InvalidIntervalTimeProblem);

      InvalidIntervalTimeProblem problem = (InvalidIntervalTimeProblem) problems.get(0);

      assertEquals(assay.getId(), problem.getComponentId());
      assertEquals(new Integer(exposureTime), problem.getExposureTime());
      assertEquals(new Integer(intervalTime), problem.getIntervalTime());
    }
    finally
    {
      if (assay != null && assay.isAppliedToDB())
      {
        assay.delete();
      }
    }
  }

  public void testGenerationOfIsofemale() throws ParseException
  {
    SimpleDateFormat dateTime = new SimpleDateFormat(DatabaseProperties.getDateFormat());
    Date date = dateTime.parse("2008-01-01");
    LarvaeDiscriminatingDoseAssay assay = new LarvaeDiscriminatingDoseAssay();

    try
    {
      assay.setCollection(collection);
      assay.setTestDate(date);

      assay.setIdentificationMethod(identificationMethod);
      assay.setTestMethod(assayMethod);

      assay.setExposureTime(60);
      
      assay.setHoldingTime(24);
      assay.setControlTestMortality(new Float(1.34));
      assay.setIsofemale(true);
      assay.setQuantityDead(20);
      assay.setQuantityTested(30);

      assay.setInsecticide(insecticide);
      assay.setGeneration(F0);

      assay.apply();

      fail("Able to set the isofemale line to true on a F0 generation");
    }
    catch (ProblemException e)
    {
      // This is expected
      List<ProblemIF> problems = e.getProblems();

      assertEquals(1, problems.size());
      assertTrue(problems.get(0) instanceof InvalidGenerationProblem);

      InvalidGenerationProblem problem = (InvalidGenerationProblem) problems.get(0);

      assertEquals(assay.getId(), problem.getComponentId());
    }
    finally
    {
      if (assay != null && assay.isAppliedToDB())
      {
        assay.delete();
      }
    }
  }

  public void testResistant() throws ParseException
  {
    SimpleDateFormat dateTime = new SimpleDateFormat(DatabaseProperties.getDateFormat());
    Date date = dateTime.parse("2008-01-01");

    LarvaeDiscriminatingDoseAssayDTO assay = new LarvaeDiscriminatingDoseAssayDTO(clientRequest);
    assay.setCollection(MosquitoCollectionDTO.get(clientRequest, collection.getId()));
    assay.setTestDate(date);
    assay.setIdentificationMethod(TermDTO.get(clientRequest, identificationMethod.getId()));
    assay.setTestMethod(TermDTO.get(clientRequest, assayMethod.getId()));
    assay.setExposureTime(60);
    
    assay.setGeneration(TermDTO.get(clientRequest, F1.getId()));
    assay.setHoldingTime(24);
    assay.setControlTestMortality(new Float(1.34));
    assay.setIsofemale(false);
    assay.setQuantityDead(0);
    assay.setQuantityTested(30);
    assay.setInsecticide(InsecticideDTO.get(clientRequest, insecticide.getId()));
    assay.apply();

    try
    {
      List<InformationDTO> information = clientRequest.getInformation();

      assertEquals(1, information.size());
      assertTrue(information.get(0) instanceof ResistantCollectionDTO);
    }
    finally
    {
      assay.delete();
    }
  }

  public void testPotentiallyResistant() throws ParseException
  {
    SimpleDateFormat dateTime = new SimpleDateFormat(DatabaseProperties.getDateFormat());
    Date date = dateTime.parse("2008-01-01");

    LarvaeDiscriminatingDoseAssayDTO assay = new LarvaeDiscriminatingDoseAssayDTO(clientRequest);
    assay.setCollection(MosquitoCollectionDTO.get(clientRequest, collection.getId()));
    assay.setTestDate(date);

    assay.setIdentificationMethod(TermDTO.get(clientRequest, identificationMethod.getId()));
    assay.setTestMethod(TermDTO.get(clientRequest, assayMethod.getId()));
    assay.setExposureTime(60);
    
    assay.setGeneration(TermDTO.get(clientRequest, F1.getId()));
    assay.setHoldingTime(24);
    assay.setControlTestMortality(new Float(1.34));
    assay.setIsofemale(false);
    assay.setQuantityDead(29);
    assay.setQuantityTested(30);

    assay.setInsecticide(InsecticideDTO.get(clientRequest, insecticide.getId()));

    assay.apply();

    try
    {
      List<InformationDTO> information = clientRequest.getInformation();

      assertEquals(1, information.size());
      assertTrue(information.get(0) instanceof PotentiallyResistantCollectionDTO);
    }
    finally
    {
      assay.delete();
    }

  }

  public void testSusceptible() throws ParseException
  {
    SimpleDateFormat dateTime = new SimpleDateFormat(DatabaseProperties.getDateFormat());
    Date date = dateTime.parse("2008-01-01");

    LarvaeDiscriminatingDoseAssayDTO assay = new LarvaeDiscriminatingDoseAssayDTO(clientRequest);
    assay.setCollection(MosquitoCollectionDTO.get(clientRequest, collection.getId()));
    assay.setTestDate(date);

    assay.setIdentificationMethod(TermDTO.get(clientRequest, identificationMethod.getId()));
    assay.setTestMethod(TermDTO.get(clientRequest, assayMethod.getId()));
    assay.setExposureTime(60);
    
    assay.setGeneration(TermDTO.get(clientRequest, F1.getId()));
    assay.setHoldingTime(24);
    assay.setControlTestMortality(new Float(1.34));
    assay.setIsofemale(false);
    assay.setQuantityDead(30);
    assay.setQuantityTested(30);
    assay.setInsecticide(InsecticideDTO.get(clientRequest, insecticide.getId()));
    assay.apply();

    try
    {
      List<InformationDTO> information = clientRequest.getInformation();

      assertEquals(1, information.size());
      assertTrue(information.get(0) instanceof SusceptibleCollectionDTO);
    }
    finally
    {
      assay.delete();
    }

  }
}
