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
import com.terraframe.mojo.dataaccess.attributes.AttributeValueException;
import com.terraframe.mojo.web.WebClientSession;

import dss.vector.solutions.CurrentDateProblem;
import dss.vector.solutions.TestConstants;
import dss.vector.solutions.TestFixture;
import dss.vector.solutions.entomology.assay.AdultAgeRange;
import dss.vector.solutions.entomology.assay.AdultDiscriminatingDoseAssay;
import dss.vector.solutions.entomology.assay.AdultDiscriminatingDoseAssayDTO;
import dss.vector.solutions.entomology.assay.InvalidAgeProblem;
import dss.vector.solutions.entomology.assay.InvalidAgeRangeProblem;
import dss.vector.solutions.entomology.assay.InvalidDeadQuantityProblem;
import dss.vector.solutions.entomology.assay.InvalidFedQuantityProblem;
import dss.vector.solutions.entomology.assay.InvalidFedSexProblem;
import dss.vector.solutions.entomology.assay.InvalidGenerationProblem;
import dss.vector.solutions.entomology.assay.InvalidGravidQuantityProblem;
import dss.vector.solutions.entomology.assay.InvalidGravidSexProblem;
import dss.vector.solutions.entomology.assay.InvalidIntervalTimeProblem;
import dss.vector.solutions.entomology.assay.InvalidTestDateProblem;
import dss.vector.solutions.entomology.assay.PotentiallyResistantCollectionDTO;
import dss.vector.solutions.entomology.assay.ResistantCollectionDTO;
import dss.vector.solutions.entomology.assay.SusceptibleCollectionDTO;
import dss.vector.solutions.general.Insecticide;
import dss.vector.solutions.general.InsecticideDTO;
import dss.vector.solutions.geo.generated.GeoEntity;
import dss.vector.solutions.ontology.Term;
import dss.vector.solutions.ontology.TermDTO;

public class ADDATest extends TestCase
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

  private static GeoEntity          geoEntity            = null;

  private static MosquitoCollection collection           = null;

  private static Term               collectionMethod     = null;

  private static Term               specie               = null;

  private static Term               identificationMethod = null;

  private static Term               assayMethod          = null;

  private static Insecticide        insecticide          = null;

  private static Term               F0                   = null;

  private static Term               F1                   = null;

  private static Term               sex                  = null;

  private static ClientSession      clientSession;

  private static ClientRequestIF    clientRequest;

  public static Test suite()
  {
    TestSuite suite = new TestSuite();
    suite.addTestSuite(ADDATest.class);

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
    clientSession = WebClientSession.createUserSession("SYSTEM", TestConstants.PASSWORD, Locale.US);
    clientRequest = clientSession.getRequest();
    clientRequest.setKeepMessages(false);

    collectionMethod = TestFixture.createRandomTerm();
    specie = TestFixture.createRandomTerm();
    identificationMethod = TestFixture.createRandomTerm();
    assayMethod = TestFixture.createRandomTerm();
    F0 = TestFixture.createRandomTerm();
    F1 = TestFixture.createRandomTerm();
    sex = TestFixture.createRandomTerm();

    geoEntity = TestFixture.createRandomSite();
    collection = TestFixture.createMosquitoCollection(geoEntity, collectionMethod);
    insecticide = TestFixture.createInsecticide();
  }

  protected static void classTearDown()
  {
    TestFixture.delete(insecticide);
    collection.delete();
    geoEntity.delete();

    specie.delete();
    identificationMethod.delete();
    assayMethod.delete();
    F0.delete();
    F1.delete();
    sex.delete();

    clientSession.logout();
  }

  public void testAgeBoundary()
  {
    AdultAgeRange range = new AdultAgeRange();
    range.setStartPoint(0);
    range.setEndPoint(20);
    range.apply();

    try
    {
      assertEquals(new Integer(0), range.getStartPoint());
      assertEquals(new Integer(20), range.getEndPoint());
    }
    finally
    {
      range.delete();
    }
  }

  public void testEqualAges()
  {
    AdultAgeRange range = new AdultAgeRange();
    range.setStartPoint(10);
    range.setEndPoint(10);
    range.apply();

    try
    {
      assertEquals(new Integer(10), range.getStartPoint());
      assertEquals(new Integer(10), range.getEndPoint());
    }
    finally
    {
      range.delete();
    }
  }

  public void testInvalidAgeRange()
  {
    AdultAgeRange range = new AdultAgeRange();
    range.setStartPoint(15);
    range.setEndPoint(10);

    try
    {
      range.apply();

      fail("Able to create an age range where the starting age is after the begining age");
    }
    catch (ProblemException e)
    {
      // This is expected
      List<ProblemIF> problems = e.getProblems();

      assertEquals(1, problems.size());
      assertTrue(problems.get(0) instanceof InvalidAgeRangeProblem);
    }
    finally
    {
      if (range != null && range.isAppliedToDB())
      {
        range.delete();
      }
    }
  }

  public void testInvalidStartAgeMinimum()
  {
    try
    {
      new AdultAgeRange().setStartPoint(-5);

      fail("Able to create an age range where the begining age is less than the minimum");
    }
    catch (AttributeValueException e)
    {
      // This is expected
    }
  }

  public void testInvalidEndAgeMinimum()
  {
    try
    {
      new AdultAgeRange().setEndPoint(-5);

      fail("Able to create an age range where the ending age is less than the minimum");
    }
    catch (AttributeValueException e)
    {
      // This is expected
    }
  }

  public void testInvalidAgeMaximum()
  {
    AdultAgeRange range = new AdultAgeRange();
    range.setStartPoint(22);
    range.setEndPoint(25);

    try
    {
      range.apply();

      fail("Able to create an age range where the begining and ending age are greater than the maximum age");
    }
    catch (ProblemException e)
    {
      // This is expected
      List<ProblemIF> problems = e.getProblems();

      assertEquals(2, problems.size());
      assertTrue(problems.get(0) instanceof InvalidAgeProblem);
      assertTrue(problems.get(1) instanceof InvalidAgeProblem);
    }
    finally
    {
      if (range != null && range.isAppliedToDB())
      {
        range.delete();
      }
    }
  }

  public void testADDA() throws ParseException
  {
    SimpleDateFormat dateTime = new SimpleDateFormat(DatabaseProperties.getDateFormat());
    Date date = dateTime.parse("2008-01-01");

    AdultDiscriminatingDoseAssay assay = new AdultDiscriminatingDoseAssay();
    assay.setSpecie(specie);
    assay.setCollection(collection);
    assay.setTestDate(date);
    assay.setSex(sex);
    assay.setIdentificationMethod(identificationMethod);
    assay.setTestMethod(assayMethod);
    assay.setFed(10);
    assay.setGravid(10);
    assay.setExposureTime(60);
    assay.setIntervalTime(10);
    assay.setHoldingTime(24);
    assay.setControlTestMortality(new Float(2.99));
    assay.setIsofemale(false);
    assay.setGeneration(F0);
    assay.setQuantityDead(5);
    assay.setQuantityTested(30);
    assay.getAgeRange().setStartPoint(2);
    assay.getAgeRange().setEndPoint(20);
    assay.setInsecticide(insecticide);
    assay.apply();

    try
    {

      AdultDiscriminatingDoseAssay assay2 = AdultDiscriminatingDoseAssay.get(assay.getId());

      assertEquals(collection.getId(), assay2.getCollection().getId());
      assertEquals(specie.getId(), assay2.getSpecie().getId());
      assertEquals(date, assay2.getTestDate());
      assertEquals(sex, assay2.getSex());
      assertEquals(identificationMethod.getId(), assay2.getIdentificationMethod().getId());
      assertEquals(assayMethod.getId(), assay2.getTestMethod().getId());
      assertEquals(new Integer(10), assay2.getFed());
      assertEquals(new Integer(10), assay2.getGravid());
      assertEquals(new Integer(60), assay2.getExposureTime());
      assertEquals(new Integer(10), assay2.getIntervalTime());
      assertEquals(new Integer(24), assay2.getHoldingTime());
      assertEquals(new Integer(5), assay2.getQuantityDead());
      assertEquals(new Integer(30), assay2.getQuantityTested());
      assertEquals(new Float(2.99), assay2.getControlTestMortality());
      assertEquals(new Boolean(false), assay2.getIsofemale());
      assertEquals(insecticide.getId(), assay2.getInsecticide().getId());
      assertEquals(new Integer(2), assay2.getAgeRange().getStartPoint());
      assertEquals(new Integer(20), assay2.getAgeRange().getEndPoint());
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

    AdultDiscriminatingDoseAssay assay = new AdultDiscriminatingDoseAssay();
    assay.setCollection(collection);
    assay.setTestDate(date);
    assay.setSex(sex);
    assay.setIdentificationMethod(identificationMethod);
    assay.setTestMethod(assayMethod);
    assay.setGeneration(F1);
    assay.setFed(10);
    assay.setGravid(10);
    assay.setExposureTime(60);
    assay.setIntervalTime(10);
    assay.setHoldingTime(24);
    assay.setControlTestMortality(new Float(2.99));
    assay.setIsofemale(false);
    assay.setQuantityDead(5);
    assay.setQuantityTested(30);
    assay.setInsecticide(insecticide);
    assay.apply();

    try
    {

      AdultDiscriminatingDoseAssay assay2 = AdultDiscriminatingDoseAssay.get(assay.getId());

      assertEquals(collection.getId(), assay2.getCollection().getId());
      assertEquals(date, assay2.getTestDate());
      assertEquals(sex, assay2.getSex());
      assertEquals(identificationMethod.getId(), assay2.getIdentificationMethod().getId());
      assertEquals(assayMethod.getId(), assay2.getTestMethod().getId());
      assertEquals(new Integer(10), assay2.getFed());
      assertEquals(new Integer(10), assay2.getGravid());
      assertEquals(new Integer(60), assay2.getExposureTime());
      assertEquals(new Integer(10), assay2.getIntervalTime());
      assertEquals(new Integer(24), assay2.getHoldingTime());
      assertEquals(new Integer(5), assay2.getQuantityDead());
      assertEquals(new Integer(30), assay2.getQuantityTested());
      assertEquals(new Float(2.99), assay2.getControlTestMortality());
      assertEquals(new Boolean(false), assay2.getIsofemale());
      assertEquals(insecticide.getId(), assay2.getInsecticide().getId());
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
    AdultDiscriminatingDoseAssay assay = new AdultDiscriminatingDoseAssay();

    try
    {
      assay.setCollection(collection);
      assay.setTestDate(date);
      assay.setSex(sex);
      assay.setIdentificationMethod(identificationMethod);
      assay.setTestMethod(assayMethod);
      assay.setFed(10);
      assay.setGravid(10);
      assay.setExposureTime(60);
      assay.setIntervalTime(10);
      assay.setHoldingTime(24);
      assay.setControlTestMortality(new Float(2.99));
      assay.setIsofemale(false);
      assay.setQuantityDead(5);
      assay.setQuantityTested(30);
      assay.getAgeRange().setStartPoint(24);
      assay.getAgeRange().setEndPoint(25);
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

    AdultDiscriminatingDoseAssay assay = new AdultDiscriminatingDoseAssay();

    try
    {
      assay.setCollection(collection);
      assay.setTestDate(date);
      assay.setSex(sex);
      assay.setIdentificationMethod(identificationMethod);
      assay.setTestMethod(assayMethod);
      assay.setFed(10);
      assay.setGravid(10);
      assay.setExposureTime(60);
      assay.setIntervalTime(10);
      assay.setGeneration(F1);
      assay.setHoldingTime(24);
      assay.setControlTestMortality(new Float(2.99));
      assay.setIsofemale(false);
      assay.setQuantityDead(5);
      assay.setQuantityTested(30);
      assay.getAgeRange().setStartPoint(3);
      assay.getAgeRange().setEndPoint(15);
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
      assertEquals(assay.getCollection().getDateCollected(), problem.getCollectionDate());
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

    AdultDiscriminatingDoseAssay assay = new AdultDiscriminatingDoseAssay();

    try
    {
      assay.setCollection(collection);
      assay.setTestDate(date);
      assay.setSex(sex);
      assay.setIdentificationMethod(identificationMethod);
      assay.setTestMethod(assayMethod);
      assay.setFed(10);
      assay.setGravid(10);
      assay.setExposureTime(60);
      assay.setIntervalTime(10);
      assay.setGeneration(F1);
      assay.setHoldingTime(24);
      assay.setControlTestMortality(new Float(2.99));
      assay.setIsofemale(false);
      assay.setQuantityDead(5);
      assay.setQuantityTested(30);
      assay.getAgeRange().setStartPoint(3);
      assay.getAgeRange().setEndPoint(15);
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

  public void testGravidAndFedWithMixed() throws ParseException
  {
    SimpleDateFormat dateTime = new SimpleDateFormat(DatabaseProperties.getDateFormat());
    Date date = dateTime.parse("2008-01-01");

    AdultDiscriminatingDoseAssay assay = new AdultDiscriminatingDoseAssay();
    assay.setCollection(collection);
    assay.setTestDate(date);
    assay.setSex(sex);
    assay.setIdentificationMethod(identificationMethod);
    assay.setTestMethod(assayMethod);
    assay.setFed(10);
    assay.setGravid(10);
    assay.setExposureTime(60);
    assay.setIntervalTime(10);
    assay.setHoldingTime(24);
    assay.setControlTestMortality(new Float(2.99));
    assay.setIsofemale(false);
    assay.setQuantityDead(5);
    assay.setQuantityTested(30);
    assay.getAgeRange().setStartPoint(2);
    assay.getAgeRange().setEndPoint(20);
    assay.setInsecticide(insecticide);
    assay.setGeneration(F1);
    assay.apply();

    try
    {

      AdultDiscriminatingDoseAssay assay2 = AdultDiscriminatingDoseAssay.get(assay.getId());

      assertEquals(collection.getId(), assay2.getCollection().getId());
      assertEquals(date, assay2.getTestDate());
      assertEquals(sex, assay2.getSex());
      assertEquals(identificationMethod.getId(), assay2.getIdentificationMethod().getId());
      assertEquals(assayMethod.getId(), assay2.getTestMethod().getId());
      assertEquals(new Integer(10), assay2.getFed());
      assertEquals(new Integer(10), assay2.getGravid());
      assertEquals(new Integer(60), assay2.getExposureTime());
      assertEquals(new Integer(10), assay2.getIntervalTime());
      assertEquals(new Integer(24), assay2.getHoldingTime());
      assertEquals(new Integer(5), assay2.getQuantityDead());
      assertEquals(new Integer(30), assay2.getQuantityTested());
      assertEquals(new Float(2.99), assay2.getControlTestMortality());
      assertEquals(new Boolean(false), assay2.getIsofemale());
      assertEquals(insecticide.getId(), assay2.getInsecticide().getId());
      assertEquals(new Integer(2), assay2.getAgeRange().getStartPoint());
      assertEquals(new Integer(20), assay2.getAgeRange().getEndPoint());
    }
    finally
    {
      assay.delete();
    }
  }

  public void testGravidAndFedWithUnknown() throws ParseException
  {
    SimpleDateFormat dateTime = new SimpleDateFormat(DatabaseProperties.getDateFormat());
    Date date = dateTime.parse("2008-01-01");

    AdultDiscriminatingDoseAssay assay = new AdultDiscriminatingDoseAssay();
    assay.setCollection(collection);
    assay.setTestDate(date);
    assay.setSex(sex);
    assay.setIdentificationMethod(identificationMethod);
    assay.setTestMethod(assayMethod);
    assay.setExposureTime(60);
    assay.setIntervalTime(10);
    assay.setHoldingTime(24);
    assay.setControlTestMortality(new Float(2.99));
    assay.setIsofemale(false);
    assay.setQuantityDead(5);
    assay.setQuantityTested(30);
    assay.getAgeRange().setStartPoint(2);
    assay.getAgeRange().setEndPoint(20);
    assay.setInsecticide(insecticide);

    assay.setGeneration(F1);
    assay.apply();

    try
    {

      AdultDiscriminatingDoseAssay assay2 = AdultDiscriminatingDoseAssay.get(assay.getId());

      assertEquals(collection.getId(), assay2.getCollection().getId());
      assertEquals(date, assay2.getTestDate());
      assertEquals(sex, assay2.getSex());
      assertEquals(identificationMethod.getId(), assay2.getIdentificationMethod().getId());
      assertEquals(assayMethod.getId(), assay2.getTestMethod().getId());
      assertEquals(new Integer(60), assay2.getExposureTime());
      assertEquals(new Integer(10), assay2.getIntervalTime());
      assertEquals(new Integer(24), assay2.getHoldingTime());
      assertEquals(new Integer(5), assay2.getQuantityDead());
      assertEquals(new Integer(30), assay2.getQuantityTested());
      assertEquals(new Float(2.99), assay2.getControlTestMortality());
      assertEquals(new Boolean(false), assay2.getIsofemale());
      assertEquals(insecticide.getId(), assay2.getInsecticide().getId());
      assertEquals(new Integer(2), assay2.getAgeRange().getStartPoint());
      assertEquals(new Integer(20), assay2.getAgeRange().getEndPoint());
    }
    finally
    {
      assay.delete();
    }
  }

  public void testGravidAndFedWithMale() throws ParseException
  {
    SimpleDateFormat dateTime = new SimpleDateFormat(DatabaseProperties.getDateFormat());
    Date date = dateTime.parse("2008-01-01");

    AdultDiscriminatingDoseAssay assay = new AdultDiscriminatingDoseAssay();
    assay.setCollection(collection);
    assay.setTestDate(date);
    assay.setSex(sex);
    assay.setIdentificationMethod(identificationMethod);
    assay.setTestMethod(assayMethod);
    assay.setExposureTime(60);
    assay.setIntervalTime(10);
    assay.setHoldingTime(24);
    assay.setControlTestMortality(new Float(2.99));
    assay.setIsofemale(false);
    assay.setQuantityDead(5);
    assay.setQuantityTested(30);
    assay.getAgeRange().setStartPoint(2);
    assay.getAgeRange().setEndPoint(20);
    assay.setInsecticide(insecticide);

    assay.setGeneration(F1);
    assay.apply();

    try
    {

      AdultDiscriminatingDoseAssay assay2 = AdultDiscriminatingDoseAssay.get(assay.getId());

      assertEquals(collection.getId(), assay2.getCollection().getId());
      assertEquals(date, assay2.getTestDate());
      assertEquals(sex, assay2.getSex());
      assertEquals(identificationMethod.getId(), assay2.getIdentificationMethod().getId());
      assertEquals(assayMethod.getId(), assay2.getTestMethod().getId());
      assertEquals(new Integer(60), assay2.getExposureTime());
      assertEquals(new Integer(10), assay2.getIntervalTime());
      assertEquals(new Integer(24), assay2.getHoldingTime());
      assertEquals(new Integer(5), assay2.getQuantityDead());
      assertEquals(new Integer(30), assay2.getQuantityTested());
      assertEquals(new Float(2.99), assay2.getControlTestMortality());
      assertEquals(new Boolean(false), assay2.getIsofemale());
      assertEquals(insecticide.getId(), assay2.getInsecticide().getId());

      assertEquals(new Integer(2), assay2.getAgeRange().getStartPoint());
      assertEquals(new Integer(20), assay2.getAgeRange().getEndPoint());
    }
    finally
    {
      assay.delete();
    }
  }

  public void testInvalidGravidAndFedWithUnknown() throws ParseException
  {
    SimpleDateFormat dateTime = new SimpleDateFormat(DatabaseProperties.getDateFormat());
    Date date = dateTime.parse("2008-01-01");

    AdultDiscriminatingDoseAssay assay = new AdultDiscriminatingDoseAssay();

    try
    {
      assay.setCollection(collection);
      assay.setTestDate(date);
      assay.setSex(sex);
      assay.setIdentificationMethod(identificationMethod);
      assay.setTestMethod(assayMethod);
      assay.setFed(10);
      assay.setGravid(10);
      assay.setGeneration(F1);
      assay.setExposureTime(60);
      assay.setIntervalTime(10);
      assay.setHoldingTime(24);
      assay.setControlTestMortality(new Float(2.99));
      assay.setIsofemale(false);
      assay.setQuantityDead(5);
      assay.setQuantityTested(30);
      assay.getAgeRange().setStartPoint(2);
      assay.getAgeRange().setEndPoint(20);
      assay.setInsecticide(insecticide);

      assay.apply();

      fail("Able to create an assay of unknown sex with invalid gravid and fed values");
    }
    catch (ProblemException e)
    {
      // This is expected
      List<ProblemIF> problems = e.getProblems();

      assertEquals(2, problems.size());
      assertTrue(problems.get(0) instanceof InvalidGravidSexProblem || problems.get(1) instanceof InvalidGravidSexProblem);
      assertTrue(problems.get(0) instanceof InvalidFedSexProblem || problems.get(1) instanceof InvalidFedSexProblem);
    }
    finally
    {
      if (assay != null && assay.isAppliedToDB())
      {
        assay.delete();
      }
    }
  }

  public void testInvalidGravidAndFedWithMale() throws ParseException
  {
    SimpleDateFormat dateTime = new SimpleDateFormat(DatabaseProperties.getDateFormat());
    Date date = dateTime.parse("2008-01-01");

    AdultDiscriminatingDoseAssay assay = new AdultDiscriminatingDoseAssay();

    try
    {
      assay.setCollection(collection);
      assay.setTestDate(date);
      assay.setSex(sex);
      assay.setIdentificationMethod(identificationMethod);
      assay.setTestMethod(assayMethod);
      assay.setFed(10);
      assay.setGravid(10);
      assay.setExposureTime(60);
      assay.setGeneration(F1);
      assay.setIntervalTime(10);
      assay.setHoldingTime(24);
      assay.setControlTestMortality(new Float(2.99));
      assay.setIsofemale(false);
      assay.setQuantityDead(5);
      assay.setQuantityTested(30);
      assay.getAgeRange().setStartPoint(2);
      assay.getAgeRange().setEndPoint(20);
      assay.setInsecticide(insecticide);

      assay.apply();

      fail("Able to create an assay of male sex with invalid gravid and fed values");
    }
    catch (ProblemException e)
    {
      // This is expected
      List<ProblemIF> problems = e.getProblems();

      assertEquals(2, problems.size());
      assertTrue(problems.get(0) instanceof InvalidGravidSexProblem || problems.get(1) instanceof InvalidGravidSexProblem);
      assertTrue(problems.get(0) instanceof InvalidFedSexProblem || problems.get(1) instanceof InvalidFedSexProblem);
    }
    finally
    {
      if (assay != null && assay.isAppliedToDB())
      {
        assay.delete();
      }
    }
  }

  public void testFedLargerThanQuantityTested() throws ParseException
  {
    SimpleDateFormat dateTime = new SimpleDateFormat(DatabaseProperties.getDateFormat());
    Date date = dateTime.parse("2008-01-01");
    AdultDiscriminatingDoseAssay assay = new AdultDiscriminatingDoseAssay();
    int fed = 40;

    try
    {
      assay.setCollection(collection);
      assay.setTestDate(date);
      assay.setSex(sex);
      assay.setIdentificationMethod(identificationMethod);
      assay.setTestMethod(assayMethod);
      assay.setFed(fed);
      assay.setGravid(10);
      assay.setExposureTime(60);
      assay.setGeneration(F1);
      assay.setIntervalTime(10);
      assay.setHoldingTime(24);
      assay.setControlTestMortality(new Float(2.99));
      assay.setIsofemale(false);
      assay.setQuantityDead(5);
      assay.setQuantityTested(30);
      assay.getAgeRange().setStartPoint(2);
      assay.getAgeRange().setEndPoint(20);
      assay.setInsecticide(insecticide);

      assay.apply();

      fail("Able to create an assay with a larger Gravid value than quantity tested");
    }
    catch (ProblemException e)
    {
      // This is expected
      List<ProblemIF> problems = e.getProblems();

      assertEquals(1, problems.size());
      assertTrue(problems.get(0) instanceof InvalidFedQuantityProblem);

      InvalidFedQuantityProblem problem = (InvalidFedQuantityProblem) problems.get(0);

      assertEquals(assay.getId(), problem.getComponentId());
      assertEquals(new Integer(fed), problem.getFed());
    }
    finally
    {
      if (assay != null && assay.isAppliedToDB())
      {
        assay.delete();
      }
    }
  }

  public void testGravidLargerThanQuantityTested() throws ParseException
  {
    SimpleDateFormat dateTime = new SimpleDateFormat(DatabaseProperties.getDateFormat());
    Date date = dateTime.parse("2008-01-01");
    AdultDiscriminatingDoseAssay assay = new AdultDiscriminatingDoseAssay();
    int gravid = 40;

    try
    {
      assay.setCollection(collection);
      assay.setTestDate(date);
      assay.setSex(sex);
      assay.setIdentificationMethod(identificationMethod);
      assay.setTestMethod(assayMethod);
      assay.setFed(10);
      assay.setGravid(gravid);
      assay.setGeneration(F1);
      assay.setExposureTime(60);
      assay.setIntervalTime(10);
      assay.setHoldingTime(24);
      assay.setControlTestMortality(new Float(2.99));
      assay.setIsofemale(false);
      assay.setQuantityDead(5);
      assay.setQuantityTested(30);
      assay.getAgeRange().setStartPoint(2);
      assay.getAgeRange().setEndPoint(20);
      assay.setInsecticide(insecticide);

      assay.apply();

      fail("Able to create an assay with a larger Fed value than quantity tested");
    }
    catch (ProblemException e)
    {
      // This is expected
      List<ProblemIF> problems = e.getProblems();

      assertEquals(1, problems.size());
      assertTrue(problems.get(0) instanceof InvalidGravidQuantityProblem);

      InvalidGravidQuantityProblem problem = (InvalidGravidQuantityProblem) problems.get(0);

      assertEquals(assay.getId(), problem.getComponentId());
      assertEquals(new Integer(gravid), problem.getGravid());
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

    AdultDiscriminatingDoseAssay assay = new AdultDiscriminatingDoseAssay();
    assay.setCollection(collection);
    assay.setTestDate(date);
    assay.setSex(sex);
    assay.setIdentificationMethod(identificationMethod);
    assay.setTestMethod(assayMethod);
    assay.setExposureTime(60);
    assay.setIntervalTime(10);
    assay.setHoldingTime(24);
    assay.setControlTestMortality(new Float(2.99));
    assay.setIsofemale(false);
    assay.setQuantityDead(5);
    assay.setQuantityTested(30);
    assay.getAgeRange().setStartPoint(2);
    assay.getAgeRange().setEndPoint(20);
    assay.setInsecticide(insecticide);
    assay.setGeneration(F1);
    assay.apply();

    try
    {

      AdultDiscriminatingDoseAssay assay2 = AdultDiscriminatingDoseAssay.get(assay.getId());

      assertEquals(collection.getId(), assay2.getCollection().getId());
      assertEquals(date, assay2.getTestDate());
      assertEquals(sex, assay2.getSex());
      assertEquals(identificationMethod.getId(), assay2.getIdentificationMethod().getId());
      assertEquals(assayMethod.getId(), assay2.getTestMethod().getId());
      assertEquals(new Integer(60), assay2.getExposureTime());
      assertEquals(new Integer(10), assay2.getIntervalTime());
      assertEquals(new Integer(24), assay2.getHoldingTime());
      assertEquals(new Integer(5), assay2.getQuantityDead());
      assertEquals(new Integer(30), assay2.getQuantityTested());
      assertEquals(new Float(2.99), assay2.getControlTestMortality());
      assertEquals(new Boolean(false), assay2.getIsofemale());
      assertEquals(insecticide.getId(), assay2.getInsecticide().getId());

      assertEquals(new Integer(2), assay2.getAgeRange().getStartPoint());
      assertEquals(new Integer(20), assay2.getAgeRange().getEndPoint());
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

    AdultDiscriminatingDoseAssay assay = new AdultDiscriminatingDoseAssay();
    assay.setCollection(collection);
    assay.setTestDate(date);
    assay.setSex(sex);
    assay.setIdentificationMethod(identificationMethod);
    assay.setTestMethod(assayMethod);
    assay.setExposureTime(60);
    assay.setIntervalTime(10);
    assay.setHoldingTime(24);
    assay.setControlTestMortality(new Float(2.99));
    assay.setIsofemale(false);
    assay.setQuantityDead(0);
    assay.setQuantityTested(30);
    assay.getAgeRange().setStartPoint(2);
    assay.getAgeRange().setEndPoint(20);
    assay.setInsecticide(insecticide);
    assay.setGeneration(F1);
    assay.apply();

    try
    {

      AdultDiscriminatingDoseAssay assay2 = AdultDiscriminatingDoseAssay.get(assay.getId());

      assertEquals(collection.getId(), assay2.getCollection().getId());
      assertEquals(date, assay2.getTestDate());
      assertEquals(sex, assay2.getSex());
      assertEquals(identificationMethod.getId(), assay2.getIdentificationMethod().getId());
      assertEquals(assayMethod.getId(), assay2.getTestMethod().getId());
      assertEquals(new Integer(60), assay2.getExposureTime());
      assertEquals(new Integer(10), assay2.getIntervalTime());
      assertEquals(new Integer(24), assay2.getHoldingTime());
      assertEquals(new Integer(0), assay2.getQuantityDead());
      assertEquals(new Integer(30), assay2.getQuantityTested());
      assertEquals(new Float(2.99), assay2.getControlTestMortality());
      assertEquals(new Boolean(false), assay2.getIsofemale());
      assertEquals(insecticide.getId(), assay2.getInsecticide().getId());

      assertEquals(new Integer(2), assay2.getAgeRange().getStartPoint());
      assertEquals(new Integer(20), assay2.getAgeRange().getEndPoint());
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

    AdultDiscriminatingDoseAssay assay = new AdultDiscriminatingDoseAssay();
    assay.setCollection(collection);
    assay.setTestDate(date);
    assay.setSex(sex);
    assay.setIdentificationMethod(identificationMethod);
    assay.setTestMethod(assayMethod);
    assay.setExposureTime(60);
    assay.setIntervalTime(10);
    assay.setHoldingTime(24);
    assay.setControlTestMortality(new Float(2.99));
    assay.setIsofemale(false);
    assay.setQuantityDead(30);
    assay.setQuantityTested(30);
    assay.getAgeRange().setStartPoint(2);
    assay.getAgeRange().setEndPoint(20);
    assay.setInsecticide(insecticide);

    assay.setGeneration(F1);
    assay.apply();

    try
    {

      AdultDiscriminatingDoseAssay assay2 = AdultDiscriminatingDoseAssay.get(assay.getId());

      assertEquals(collection.getId(), assay2.getCollection().getId());
      assertEquals(date, assay2.getTestDate());
      assertEquals(sex, assay2.getSex());
      assertEquals(identificationMethod.getId(), assay2.getIdentificationMethod().getId());
      assertEquals(assayMethod.getId(), assay2.getTestMethod().getId());
      assertEquals(new Integer(60), assay2.getExposureTime());
      assertEquals(new Integer(10), assay2.getIntervalTime());
      assertEquals(new Integer(24), assay2.getHoldingTime());
      assertEquals(new Integer(30), assay2.getQuantityDead());
      assertEquals(new Integer(30), assay2.getQuantityTested());
      assertEquals(new Float(2.99), assay2.getControlTestMortality());
      assertEquals(new Boolean(false), assay2.getIsofemale());
      assertEquals(insecticide.getId(), assay2.getInsecticide().getId());

      assertEquals(new Integer(2), assay2.getAgeRange().getStartPoint());
      assertEquals(new Integer(20), assay2.getAgeRange().getEndPoint());
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
    AdultDiscriminatingDoseAssay assay = new AdultDiscriminatingDoseAssay();
    int quantityDead = 45;
    int quantityTested = 30;

    try
    {
      assay.setCollection(collection);
      assay.setTestDate(date);
      assay.setSex(sex);
      assay.setIdentificationMethod(identificationMethod);
      assay.setTestMethod(assayMethod);
      assay.setFed(10);
      assay.setGeneration(F1);
      assay.setGravid(10);
      assay.setExposureTime(60);
      assay.setIntervalTime(10);
      assay.setHoldingTime(24);
      assay.setControlTestMortality(new Float(2.99));
      assay.setIsofemale(false);
      assay.setQuantityDead(quantityDead);
      assay.setQuantityTested(quantityTested);
      assay.getAgeRange().setStartPoint(2);
      assay.getAgeRange().setEndPoint(20);
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
    AdultDiscriminatingDoseAssay assay = new AdultDiscriminatingDoseAssay();
    int exposureTime = 60;
    int intervalTime = 80;

    try
    {
      assay.setCollection(collection);
      assay.setTestDate(date);
      assay.setSex(sex);
      assay.setGeneration(F1);
      assay.setIdentificationMethod(identificationMethod);
      assay.setTestMethod(assayMethod);
      assay.setFed(10);
      assay.setGravid(10);
      assay.setExposureTime(exposureTime);
      assay.setIntervalTime(intervalTime);
      assay.setHoldingTime(24);
      assay.setControlTestMortality(new Float(2.99));
      assay.setIsofemale(false);
      assay.setQuantityDead(30);
      assay.setQuantityTested(30);
      assay.getAgeRange().setStartPoint(2);
      assay.getAgeRange().setEndPoint(20);
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
    AdultDiscriminatingDoseAssay assay = new AdultDiscriminatingDoseAssay();

    try
    {
      assay.setCollection(collection);
      assay.setTestDate(date);
      assay.setSex(sex);
      assay.setIdentificationMethod(identificationMethod);
      assay.setTestMethod(assayMethod);
      assay.setFed(10);
      assay.setGravid(10);
      assay.setExposureTime(60);
      assay.setIntervalTime(10);
      assay.setHoldingTime(24);
      assay.setControlTestMortality(new Float(2.99));
      assay.setIsofemale(true);
      assay.setQuantityDead(20);
      assay.setQuantityTested(30);
      assay.getAgeRange().setStartPoint(2);
      assay.getAgeRange().setEndPoint(20);
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

  public void testGetKD50() throws ParseException
  {
    SimpleDateFormat dateTime = new SimpleDateFormat(DatabaseProperties.getDateFormat());
    Date date = dateTime.parse("2008-01-01");

    AdultDiscriminatingDoseAssay assay = new AdultDiscriminatingDoseAssay();
    assay.setCollection(collection);
    assay.setTestDate(date);
    assay.setSex(sex);
    assay.setIdentificationMethod(identificationMethod);
    assay.setTestMethod(assayMethod);
    assay.setExposureTime(60);
    assay.setIntervalTime(10);
    assay.setGeneration(F1);
    assay.setHoldingTime(24);
    assay.setControlTestMortality(new Float(2.99));
    assay.setIsofemale(false);
    assay.setQuantityDead(30);
    assay.setQuantityTested(30);
    assay.getAgeRange().setStartPoint(2);
    assay.getAgeRange().setEndPoint(20);
    assay.setInsecticide(insecticide);
    assay.apply();

    try
    {
      assertEquals(50, (int) Math.round(assay.getKD50()));
    }
    finally
    {
      assay.delete();
    }
  }

  public void testGetKD95() throws ParseException
  {
    SimpleDateFormat dateTime = new SimpleDateFormat(DatabaseProperties.getDateFormat());
    Date date = dateTime.parse("2008-01-01");

    AdultDiscriminatingDoseAssay assay = new AdultDiscriminatingDoseAssay();
    assay.setCollection(collection);
    assay.setTestDate(date);
    assay.setSex(sex);
    assay.setIdentificationMethod(identificationMethod);
    assay.setTestMethod(assayMethod);
    assay.setExposureTime(60);
    assay.setIntervalTime(10);
    assay.setGeneration(F1);
    assay.setHoldingTime(24);
    assay.setControlTestMortality(new Float(2.99));
    assay.setIsofemale(false);
    assay.setQuantityDead(30);
    assay.setQuantityTested(30);
    assay.getAgeRange().setStartPoint(2);
    assay.getAgeRange().setEndPoint(20);
    assay.setInsecticide(insecticide);
    assay.apply();

    try
    {
      assertEquals(95, (int) Math.round(assay.getKD95()));
    }
    finally
    {
      assay.delete();
    }
  }

  public void testControlMortalityCorrection()
  {
    Calendar calendar = Calendar.getInstance();
    calendar.clear();
    calendar.set(2008, 1, 1);
    Date date = calendar.getTime();

    AdultDiscriminatingDoseAssay assay = new AdultDiscriminatingDoseAssay();
    assay.setSpecie(specie);
    assay.setCollection(collection);
    assay.setTestDate(date);
    assay.setSex(sex);
    assay.setIdentificationMethod(identificationMethod);
    assay.setTestMethod(assayMethod);
    assay.setFed(10);
    assay.setGravid(10);
    assay.setExposureTime(60);
    assay.setIntervalTime(10);
    assay.setHoldingTime(24);
    assay.setControlTestMortality(new Float(15));
    assay.setIsofemale(false);
    assay.setGeneration(F0);
    assay.setQuantityDead(15);
    assay.setQuantityTested(30);
    assay.getAgeRange().setStartPoint(2);
    assay.getAgeRange().setEndPoint(20);
    assay.setInsecticide(insecticide);
    assay.apply();

    try
    {

      AdultDiscriminatingDoseAssay assay2 = AdultDiscriminatingDoseAssay.get(assay.getId());

      assertEquals(collection.getId(), assay2.getCollection().getId());
      assertEquals(specie.getId(), assay2.getSpecie().getId());
      assertEquals(date, assay2.getTestDate());
      assertEquals(sex, assay2.getSex());
      assertEquals(identificationMethod.getId(), assay2.getIdentificationMethod().getId());
      assertEquals(assayMethod.getId(), assay2.getTestMethod().getId());
      assertEquals(new Integer(10), assay2.getFed());
      assertEquals(new Integer(10), assay2.getGravid());
      assertEquals(new Integer(60), assay2.getExposureTime());
      assertEquals(new Integer(10), assay2.getIntervalTime());
      assertEquals(new Integer(24), assay2.getHoldingTime());
      assertEquals(new Integer(15), assay2.getQuantityDead());
      assertEquals(new Integer(30), assay2.getQuantityTested());
      assertEquals(new Float(15.0), assay2.getControlTestMortality());
      assertEquals(new Boolean(false), assay2.getIsofemale());
      assertEquals(insecticide.getId(), assay2.getInsecticide().getId());
      assertEquals(new Integer(2), assay2.getAgeRange().getStartPoint());
      assertEquals(new Integer(20), assay2.getAgeRange().getEndPoint());
      assertEquals(new Float(41.18), assay2.getMortality());
    }
    finally
    {
      assay.delete();
    }

  }

  public void testControlMortalityLimit()
  {
    Calendar calendar = Calendar.getInstance();
    calendar.clear();
    calendar.set(2008, 1, 1);
    Date date = calendar.getTime();

    AdultDiscriminatingDoseAssay assay = new AdultDiscriminatingDoseAssay();

    try
    {
      assay.setCollection(collection);
      assay.setTestDate(date);
      assay.setSex(sex);
      assay.setIdentificationMethod(identificationMethod);
      assay.setTestMethod(assayMethod);
      assay.setFed(10);
      assay.setGravid(10);
      assay.setExposureTime(60);
      assay.setIntervalTime(10);
      assay.setHoldingTime(24);
      assay.setControlTestMortality(new Float(20.1));
      assay.setIsofemale(false);
      assay.setQuantityDead(5);
      assay.setQuantityTested(30);
      assay.getAgeRange().setStartPoint(1);
      assay.getAgeRange().setEndPoint(12);
      assay.setInsecticide(insecticide);
      assay.apply();

      fail("Able to create an adult assay with a control mortality exceeding 20%");
    }
    catch (ControlMortalityException e)
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

  public void testResistant() throws ParseException
  {
    SimpleDateFormat dateTime = new SimpleDateFormat(DatabaseProperties.getDateFormat());
    Date date = dateTime.parse("2008-01-01");

    AdultDiscriminatingDoseAssayDTO assay = new AdultDiscriminatingDoseAssayDTO(clientRequest);
    assay.setCollection(MosquitoCollectionDTO.get(clientRequest, collection.getId()));
    assay.setTestDate(date);
    assay.setSex(TermDTO.get(clientRequest, sex.getId()));
    assay.setIdentificationMethod(TermDTO.get(clientRequest, identificationMethod.getId()));
    assay.setTestMethod(TermDTO.get(clientRequest, assayMethod.getId()));
    assay.setExposureTime(60);
    assay.setIntervalTime(10);
    assay.setGeneration(TermDTO.get(clientRequest, F1.getId()));
    assay.setHoldingTime(24);
    assay.setControlTestMortality(new Float(2.99));
    assay.setIsofemale(false);
    assay.setQuantityDead(0);
    assay.setQuantityTested(30);
    assay.getAgeRange().setStartPoint(2);
    assay.getAgeRange().setEndPoint(20);
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

    AdultDiscriminatingDoseAssayDTO assay = new AdultDiscriminatingDoseAssayDTO(clientRequest);
    assay.setCollection(MosquitoCollectionDTO.get(clientRequest, collection.getId()));
    assay.setTestDate(date);
    assay.setSex(TermDTO.get(clientRequest, sex.getId()));
    assay.setIdentificationMethod(TermDTO.get(clientRequest, identificationMethod.getId()));
    assay.setTestMethod(TermDTO.get(clientRequest, assayMethod.getId()));
    assay.setExposureTime(60);
    assay.setIntervalTime(10);
    assay.setGeneration(TermDTO.get(clientRequest, F1.getId()));
    assay.setHoldingTime(24);
    assay.setControlTestMortality(new Float(2.99));
    assay.setIsofemale(false);
    assay.setQuantityDead(29);
    assay.setQuantityTested(30);
    assay.getAgeRange().setStartPoint(2);
    assay.getAgeRange().setEndPoint(20);
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

    AdultDiscriminatingDoseAssayDTO assay = new AdultDiscriminatingDoseAssayDTO(clientRequest);
    assay.setCollection(MosquitoCollectionDTO.get(clientRequest, collection.getId()));
    assay.setTestDate(date);
    assay.setSex(TermDTO.get(clientRequest, sex.getId()));
    assay.setIdentificationMethod(TermDTO.get(clientRequest, identificationMethod.getId()));
    assay.setTestMethod(TermDTO.get(clientRequest, assayMethod.getId()));
    assay.setExposureTime(60);
    assay.setIntervalTime(10);
    assay.setGeneration(TermDTO.get(clientRequest, F1.getId()));
    assay.setHoldingTime(24);
    assay.setControlTestMortality(new Float(2.99));
    assay.setIsofemale(false);
    assay.setQuantityDead(30);
    assay.setQuantityTested(30);
    assay.getAgeRange().setStartPoint(2);
    assay.getAgeRange().setEndPoint(20);
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
