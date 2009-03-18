package dss.vector.solutions.entomology;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import junit.extensions.TestSetup;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import com.terraframe.mojo.ClientSession;
import com.terraframe.mojo.ProblemException;
import com.terraframe.mojo.ProblemIF;
import com.terraframe.mojo.business.InformationDTO;
import com.terraframe.mojo.constants.ClientRequestIF;
import com.terraframe.mojo.constants.DatabaseProperties;
import com.terraframe.mojo.dataaccess.attributes.AttributeValueException;
import com.terraframe.mojo.dataaccess.database.DuplicateDataDatabaseException;
import com.terraframe.mojo.web.WebClientSession;

import dss.vector.solutions.entomology.assay.AdultAgeRange;
import dss.vector.solutions.entomology.assay.AdultDiscriminatingDoseAssay;
import dss.vector.solutions.entomology.assay.AdultDiscriminatingDoseAssayDTO;
import dss.vector.solutions.entomology.assay.DDATestInterval;
import dss.vector.solutions.entomology.assay.DDATestIntervalView;
import dss.vector.solutions.entomology.assay.InvalidAgeProblem;
import dss.vector.solutions.entomology.assay.InvalidAgeRangeProblem;
import dss.vector.solutions.entomology.assay.InvalidDeadQuantityProblem;
import dss.vector.solutions.entomology.assay.InvalidFedQuantityProblem;
import dss.vector.solutions.entomology.assay.InvalidFedSexProblem;
import dss.vector.solutions.entomology.assay.InvalidGenerationProblem;
import dss.vector.solutions.entomology.assay.InvalidGravidQuantityProblem;
import dss.vector.solutions.entomology.assay.InvalidGravidSexProblem;
import dss.vector.solutions.entomology.assay.InvalidIntervalTimeProblem;
import dss.vector.solutions.entomology.assay.InvalidKnockDownQuantityProblem;
import dss.vector.solutions.entomology.assay.InvalidPeriodProblem;
import dss.vector.solutions.entomology.assay.InvalidTestDateProblem;
import dss.vector.solutions.entomology.assay.PotentiallyResistantCollectionDTO;
import dss.vector.solutions.entomology.assay.ResistantCollectionDTO;
import dss.vector.solutions.entomology.assay.SusceptibleCollectionDTO;
import dss.vector.solutions.entomology.assay.Unit;
import dss.vector.solutions.general.Insecticide;
import dss.vector.solutions.general.InsecticideDTO;
import dss.vector.solutions.geo.generated.GeoEntity;
import dss.vector.solutions.geo.generated.SentinalSite;
import dss.vector.solutions.mo.ActiveIngredient;
import dss.vector.solutions.mo.CollectionMethod;
import dss.vector.solutions.mo.Generation;
import dss.vector.solutions.mo.GenerationDTO;
import dss.vector.solutions.mo.IdentificationMethod;
import dss.vector.solutions.mo.IdentificationMethodDTO;
import dss.vector.solutions.mo.ResistanceMethodology;
import dss.vector.solutions.mo.ResistanceMethodologyDTO;
import dss.vector.solutions.mo.Specie;

public class ADDATest extends TestCase
{
  private static GeoEntity             geoEntity            = null;

  private static MosquitoCollection    collection           = null;

  private static CollectionMethod      collectionMethod     = null;

  private static Specie                specie               = null;

  private static IdentificationMethod  identificationMethod = null;

  private static ResistanceMethodology assayMethod          = null;

  private static Insecticide           insecticide          = null;

  private static Generation            F0                   = null;

  private static Generation            F1                   = null;

  private static ClientSession         clientSession;

  private static ClientRequestIF       clientRequest;

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

    collectionMethod = CollectionMethod.getAll()[0];
    specie = Specie.getAll()[0];
    identificationMethod = IdentificationMethod.getAll()[0];
    assayMethod = ResistanceMethodology.getAll()[0];
    F0 = Generation.getAll()[0];
    F1 = Generation.getAll()[1];

    try
    {
      ActiveIngredient activeIngredient = ActiveIngredient.getAll()[0];
      SimpleDateFormat dateTime = new SimpleDateFormat(DatabaseProperties.getDateFormat());
      Date date = dateTime.parse("2006-01-01");

      geoEntity = new SentinalSite();
      geoEntity.setGeoId("0");
      geoEntity.setEntityName("GeoEntity");
      geoEntity.apply();

      collection = new MosquitoCollection();
      collection.setGeoEntity(geoEntity);
      collection.setCollectionMethod(collectionMethod);
      collection.setDateCollected(date);
      collection.apply();

      insecticide = new Insecticide();
      insecticide.setActiveIngredient(activeIngredient);
      insecticide.setAmount(40);
      insecticide.addUnits(Unit.PERCENT);
      insecticide.apply();
    }
    catch (ParseException e)
    {
      throw new RuntimeException(e);
    }
  }

  protected static void classTearDown()
  {
    insecticide.delete();
    collection.delete();
    geoEntity.delete();

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
    assay.addSex(AssaySex.FEMALE);
    assay.setIdentificationMethod(identificationMethod);
    assay.setTestMethod(assayMethod);
    assay.setFed(10);
    assay.setGravid(10);
    assay.setExposureTime(60);
    assay.setIntervalTime(10);
    assay.setHoldingTime(24);
    assay.setControlTestMortality(new Float(99.99));
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
      assertEquals(AssaySex.FEMALE, assay2.getSex().get(0));
      assertEquals(identificationMethod.getId(), assay2.getIdentificationMethod().getId());
      assertEquals(assayMethod.getId(), assay2.getTestMethod().getId());
      assertEquals(new Integer(10), assay2.getFed());
      assertEquals(new Integer(10), assay2.getGravid());
      assertEquals(new Integer(60), assay2.getExposureTime());
      assertEquals(new Integer(10), assay2.getIntervalTime());
      assertEquals(new Integer(24), assay2.getHoldingTime());
      assertEquals(new Integer(5), assay2.getQuantityDead());
      assertEquals(new Integer(30), assay2.getQuantityTested());
      assertEquals(new Float(99.99), assay2.getControlTestMortality());
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
    assay.addSex(AssaySex.FEMALE);
    assay.setIdentificationMethod(identificationMethod);
    assay.setTestMethod(assayMethod);
    assay.setGeneration(F1);
    assay.setFed(10);
    assay.setGravid(10);
    assay.setExposureTime(60);
    assay.setIntervalTime(10);
    assay.setHoldingTime(24);
    assay.setControlTestMortality(new Float(99.99));
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
      assertEquals(AssaySex.FEMALE, assay2.getSex().get(0));
      assertEquals(identificationMethod.getId(), assay2.getIdentificationMethod().getId());
      assertEquals(assayMethod.getId(), assay2.getTestMethod().getId());
      assertEquals(new Integer(10), assay2.getFed());
      assertEquals(new Integer(10), assay2.getGravid());
      assertEquals(new Integer(60), assay2.getExposureTime());
      assertEquals(new Integer(10), assay2.getIntervalTime());
      assertEquals(new Integer(24), assay2.getHoldingTime());
      assertEquals(new Integer(5), assay2.getQuantityDead());
      assertEquals(new Integer(30), assay2.getQuantityTested());
      assertEquals(new Float(99.99), assay2.getControlTestMortality());
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
      assay.addSex(AssaySex.FEMALE);
      assay.setIdentificationMethod(identificationMethod);
      assay.setTestMethod(assayMethod);
      assay.setFed(10);
      assay.setGravid(10);
      assay.setExposureTime(60);
      assay.setIntervalTime(10);
      assay.setHoldingTime(24);
      assay.setControlTestMortality(new Float(99.99));
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
      assay.addSex(AssaySex.FEMALE);
      assay.setIdentificationMethod(identificationMethod);
      assay.setTestMethod(assayMethod);
      assay.setFed(10);
      assay.setGravid(10);
      assay.setExposureTime(60);
      assay.setIntervalTime(10);
      assay.setGeneration(F1);
      assay.setHoldingTime(24);
      assay.setControlTestMortality(new Float(99.99));
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

      assertEquals(assay.getId(), problem.getAssayId());
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

  public void testGravidAndFedWithMixed() throws ParseException
  {
    SimpleDateFormat dateTime = new SimpleDateFormat(DatabaseProperties.getDateFormat());
    Date date = dateTime.parse("2008-01-01");
    AssaySex sex = AssaySex.MIXED;

    AdultDiscriminatingDoseAssay assay = new AdultDiscriminatingDoseAssay();
    assay.setCollection(collection);
    assay.setTestDate(date);
    assay.addSex(sex);
    assay.setIdentificationMethod(identificationMethod);
    assay.setTestMethod(assayMethod);
    assay.setFed(10);
    assay.setGravid(10);
    assay.setExposureTime(60);
    assay.setIntervalTime(10);
    assay.setHoldingTime(24);
    assay.setControlTestMortality(new Float(99.99));
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
      assertEquals(sex, assay2.getSex().get(0));
      assertEquals(identificationMethod.getId(), assay2.getIdentificationMethod().getId());
      assertEquals(assayMethod.getId(), assay2.getTestMethod().getId());
      assertEquals(new Integer(10), assay2.getFed());
      assertEquals(new Integer(10), assay2.getGravid());
      assertEquals(new Integer(60), assay2.getExposureTime());
      assertEquals(new Integer(10), assay2.getIntervalTime());
      assertEquals(new Integer(24), assay2.getHoldingTime());
      assertEquals(new Integer(5), assay2.getQuantityDead());
      assertEquals(new Integer(30), assay2.getQuantityTested());
      assertEquals(new Float(99.99), assay2.getControlTestMortality());
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
    AssaySex sex = AssaySex.UNKNOWN;

    AdultDiscriminatingDoseAssay assay = new AdultDiscriminatingDoseAssay();
    assay.setCollection(collection);
    assay.setTestDate(date);
    assay.addSex(sex);
    assay.setIdentificationMethod(identificationMethod);
    assay.setTestMethod(assayMethod);
    assay.setExposureTime(60);
    assay.setIntervalTime(10);
    assay.setHoldingTime(24);
    assay.setControlTestMortality(new Float(99.99));
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
      assertEquals(sex, assay2.getSex().get(0));
      assertEquals(identificationMethod.getId(), assay2.getIdentificationMethod().getId());
      assertEquals(assayMethod.getId(), assay2.getTestMethod().getId());
      assertEquals(new Integer(60), assay2.getExposureTime());
      assertEquals(new Integer(10), assay2.getIntervalTime());
      assertEquals(new Integer(24), assay2.getHoldingTime());
      assertEquals(new Integer(5), assay2.getQuantityDead());
      assertEquals(new Integer(30), assay2.getQuantityTested());
      assertEquals(new Float(99.99), assay2.getControlTestMortality());
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
    AssaySex sex = AssaySex.MALE;

    AdultDiscriminatingDoseAssay assay = new AdultDiscriminatingDoseAssay();
    assay.setCollection(collection);
    assay.setTestDate(date);
    assay.addSex(sex);
    assay.setIdentificationMethod(identificationMethod);
    assay.setTestMethod(assayMethod);
    assay.setExposureTime(60);
    assay.setIntervalTime(10);
    assay.setHoldingTime(24);
    assay.setControlTestMortality(new Float(99.99));
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
      assertEquals(sex, assay2.getSex().get(0));
      assertEquals(identificationMethod.getId(), assay2.getIdentificationMethod().getId());
      assertEquals(assayMethod.getId(), assay2.getTestMethod().getId());
      assertEquals(new Integer(60), assay2.getExposureTime());
      assertEquals(new Integer(10), assay2.getIntervalTime());
      assertEquals(new Integer(24), assay2.getHoldingTime());
      assertEquals(new Integer(5), assay2.getQuantityDead());
      assertEquals(new Integer(30), assay2.getQuantityTested());
      assertEquals(new Float(99.99), assay2.getControlTestMortality());
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
    AssaySex sex = AssaySex.UNKNOWN;
    AdultDiscriminatingDoseAssay assay = new AdultDiscriminatingDoseAssay();

    try
    {
      assay.setCollection(collection);
      assay.setTestDate(date);
      assay.addSex(sex);
      assay.setIdentificationMethod(identificationMethod);
      assay.setTestMethod(assayMethod);
      assay.setFed(10);
      assay.setGravid(10);
      assay.setGeneration(F1);
      assay.setExposureTime(60);
      assay.setIntervalTime(10);
      assay.setHoldingTime(24);
      assay.setControlTestMortality(new Float(99.99));
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
      assertTrue(problems.get(0) instanceof InvalidGravidSexProblem
          || problems.get(1) instanceof InvalidGravidSexProblem);
      assertTrue(problems.get(0) instanceof InvalidFedSexProblem
          || problems.get(1) instanceof InvalidFedSexProblem);
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
    AssaySex sex = AssaySex.MALE;
    AdultDiscriminatingDoseAssay assay = new AdultDiscriminatingDoseAssay();

    try
    {
      assay.setCollection(collection);
      assay.setTestDate(date);
      assay.addSex(sex);
      assay.setIdentificationMethod(identificationMethod);
      assay.setTestMethod(assayMethod);
      assay.setFed(10);
      assay.setGravid(10);
      assay.setExposureTime(60);
      assay.setGeneration(F1);
      assay.setIntervalTime(10);
      assay.setHoldingTime(24);
      assay.setControlTestMortality(new Float(99.99));
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
      assertTrue(problems.get(0) instanceof InvalidGravidSexProblem
          || problems.get(1) instanceof InvalidGravidSexProblem);
      assertTrue(problems.get(0) instanceof InvalidFedSexProblem
          || problems.get(1) instanceof InvalidFedSexProblem);
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
    AssaySex sex = AssaySex.FEMALE;
    AdultDiscriminatingDoseAssay assay = new AdultDiscriminatingDoseAssay();
    int fed = 40;

    try
    {
      assay.setCollection(collection);
      assay.setTestDate(date);
      assay.addSex(sex);
      assay.setIdentificationMethod(identificationMethod);
      assay.setTestMethod(assayMethod);
      assay.setFed(fed);
      assay.setGravid(10);
      assay.setExposureTime(60);
      assay.setGeneration(F1);
      assay.setIntervalTime(10);
      assay.setHoldingTime(24);
      assay.setControlTestMortality(new Float(99.99));
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

      assertEquals(assay.getId(), problem.getAssayId());
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
    AssaySex sex = AssaySex.FEMALE;
    AdultDiscriminatingDoseAssay assay = new AdultDiscriminatingDoseAssay();
    int gravid = 40;

    try
    {
      assay.setCollection(collection);
      assay.setTestDate(date);
      assay.addSex(sex);
      assay.setIdentificationMethod(identificationMethod);
      assay.setTestMethod(assayMethod);
      assay.setFed(10);
      assay.setGravid(gravid);
      assay.setGeneration(F1);
      assay.setExposureTime(60);
      assay.setIntervalTime(10);
      assay.setHoldingTime(24);
      assay.setControlTestMortality(new Float(99.99));
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

      assertEquals(assay.getId(), problem.getAssayId());
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

    AssaySex sex = AssaySex.MALE;

    AdultDiscriminatingDoseAssay assay = new AdultDiscriminatingDoseAssay();
    assay.setCollection(collection);
    assay.setTestDate(date);
    assay.addSex(sex);
    assay.setIdentificationMethod(identificationMethod);
    assay.setTestMethod(assayMethod);
    assay.setExposureTime(60);
    assay.setIntervalTime(10);
    assay.setHoldingTime(24);
    assay.setControlTestMortality(new Float(99.99));
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
      assertEquals(sex, assay2.getSex().get(0));
      assertEquals(identificationMethod.getId(), assay2.getIdentificationMethod().getId());
      assertEquals(assayMethod.getId(), assay2.getTestMethod().getId());
      assertEquals(new Integer(60), assay2.getExposureTime());
      assertEquals(new Integer(10), assay2.getIntervalTime());
      assertEquals(new Integer(24), assay2.getHoldingTime());
      assertEquals(new Integer(5), assay2.getQuantityDead());
      assertEquals(new Integer(30), assay2.getQuantityTested());
      assertEquals(new Float(99.99), assay2.getControlTestMortality());
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

    AssaySex sex = AssaySex.MALE;

    AdultDiscriminatingDoseAssay assay = new AdultDiscriminatingDoseAssay();
    assay.setCollection(collection);
    assay.setTestDate(date);
    assay.addSex(sex);
    assay.setIdentificationMethod(identificationMethod);
    assay.setTestMethod(assayMethod);
    assay.setExposureTime(60);
    assay.setIntervalTime(10);
    assay.setHoldingTime(24);
    assay.setControlTestMortality(new Float(99.99));
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
      assertEquals(sex, assay2.getSex().get(0));
      assertEquals(identificationMethod.getId(), assay2.getIdentificationMethod().getId());
      assertEquals(assayMethod.getId(), assay2.getTestMethod().getId());
      assertEquals(new Integer(60), assay2.getExposureTime());
      assertEquals(new Integer(10), assay2.getIntervalTime());
      assertEquals(new Integer(24), assay2.getHoldingTime());
      assertEquals(new Integer(0), assay2.getQuantityDead());
      assertEquals(new Integer(30), assay2.getQuantityTested());
      assertEquals(new Float(99.99), assay2.getControlTestMortality());
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

    AssaySex sex = AssaySex.MALE;

    AdultDiscriminatingDoseAssay assay = new AdultDiscriminatingDoseAssay();
    assay.setCollection(collection);
    assay.setTestDate(date);
    assay.addSex(sex);
    assay.setIdentificationMethod(identificationMethod);
    assay.setTestMethod(assayMethod);
    assay.setExposureTime(60);
    assay.setIntervalTime(10);
    assay.setHoldingTime(24);
    assay.setControlTestMortality(new Float(99.99));
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
      assertEquals(sex, assay2.getSex().get(0));
      assertEquals(identificationMethod.getId(), assay2.getIdentificationMethod().getId());
      assertEquals(assayMethod.getId(), assay2.getTestMethod().getId());
      assertEquals(new Integer(60), assay2.getExposureTime());
      assertEquals(new Integer(10), assay2.getIntervalTime());
      assertEquals(new Integer(24), assay2.getHoldingTime());
      assertEquals(new Integer(30), assay2.getQuantityDead());
      assertEquals(new Integer(30), assay2.getQuantityTested());
      assertEquals(new Float(99.99), assay2.getControlTestMortality());
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
    AssaySex sex = AssaySex.FEMALE;
    AdultDiscriminatingDoseAssay assay = new AdultDiscriminatingDoseAssay();
    int quantityDead = 45;
    int quantityTested = 30;

    try
    {
      assay.setCollection(collection);
      assay.setTestDate(date);
      assay.addSex(sex);
      assay.setIdentificationMethod(identificationMethod);
      assay.setTestMethod(assayMethod);
      assay.setFed(10);
      assay.setGeneration(F1);
      assay.setGravid(10);
      assay.setExposureTime(60);
      assay.setIntervalTime(10);
      assay.setHoldingTime(24);
      assay.setControlTestMortality(new Float(99.99));
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

      assertEquals(assay.getId(), problem.getAssayId());
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
    AssaySex sex = AssaySex.FEMALE;
    AdultDiscriminatingDoseAssay assay = new AdultDiscriminatingDoseAssay();
    int exposureTime = 60;
    int intervalTime = 80;

    try
    {
      assay.setCollection(collection);
      assay.setTestDate(date);
      assay.addSex(sex);
      assay.setGeneration(F1);
      assay.setIdentificationMethod(identificationMethod);
      assay.setTestMethod(assayMethod);
      assay.setFed(10);
      assay.setGravid(10);
      assay.setExposureTime(exposureTime);
      assay.setIntervalTime(intervalTime);
      assay.setHoldingTime(24);
      assay.setControlTestMortality(new Float(99.99));
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

      assertEquals(assay.getId(), problem.getAssayId());
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

  public void testCreateTestIntervals() throws ParseException
  {
    SimpleDateFormat dateTime = new SimpleDateFormat(DatabaseProperties.getDateFormat());
    Date date = dateTime.parse("2008-01-01");

    AssaySex sex = AssaySex.MALE;

    AdultDiscriminatingDoseAssay assay = new AdultDiscriminatingDoseAssay();
    assay.setCollection(collection);
    assay.setTestDate(date);
    assay.addSex(sex);
    assay.setIdentificationMethod(identificationMethod);
    assay.setTestMethod(assayMethod);
    assay.setExposureTime(60);
    assay.setGeneration(F1);
    assay.setIntervalTime(10);
    assay.setHoldingTime(24);
    assay.setControlTestMortality(new Float(99.99));
    assay.setIsofemale(false);
    assay.setQuantityDead(30);
    assay.setQuantityTested(30);
    assay.setGeneration(F1);
    assay.getAgeRange().setStartPoint(2);
    assay.getAgeRange().setEndPoint(20);
    assay.setInsecticide(insecticide);

    assay.apply();

    try
    {
      int max = assay.calculatePeriod();
      DDATestIntervalView[] intervals = assay.getTestIntervals();

      assertEquals(max, intervals.length);

      for (int i = 0; i < max; i++)
      {
        assertEquals(assay.getId(), intervals[i].getAssay().getId());
        assertEquals(new Integer(i), intervals[i].getPeriod());
        assertEquals(new Integer(0), intervals[i].getKnockedDown());
      }
    }
    finally
    {
      assay.delete();
    }
  }

  public void testIntervalUpdate() throws ParseException
  {
    SimpleDateFormat dateTime = new SimpleDateFormat(DatabaseProperties.getDateFormat());
    Date date = dateTime.parse("2008-01-01");

    AssaySex sex = AssaySex.MALE;

    AdultDiscriminatingDoseAssay assay = new AdultDiscriminatingDoseAssay();
    assay.setCollection(collection);
    assay.setTestDate(date);
    assay.addSex(sex);
    assay.setIdentificationMethod(identificationMethod);
    assay.setTestMethod(assayMethod);
    assay.setExposureTime(60);
    assay.setIntervalTime(7);
    assay.setHoldingTime(24);
    assay.setControlTestMortality(new Float(99.99));
    assay.setIsofemale(false);
    assay.setQuantityDead(30);
    assay.setQuantityTested(30);
    assay.getAgeRange().setStartPoint(2);
    assay.getAgeRange().setEndPoint(20);
    assay.setInsecticide(insecticide);

    assay.setGeneration(F1);

    assay.apply();

    DDATestIntervalView[] intervals = assay.getTestIntervals();

    for (int i = 0; i < intervals.length; i++)
    {
      intervals[i].setKnockedDown(i);
      intervals[i].apply();
    }

    try
    {
      intervals = assay.getTestIntervals();

      assertEquals(assay.calculatePeriod(), new Integer(intervals.length));

      for (int i = 0; i < 9; i++)
      {
        assertEquals(assay.getId(), intervals[i].getAssay().getId());
        assertEquals(new Integer(i), intervals[i].getPeriod());
        assertEquals(new Integer(i), intervals[i].getKnockedDown());
      }
    }
    finally
    {
      assay.delete();
    }
  }

  public void testIntervalSaveAll() throws ParseException
  {
    SimpleDateFormat dateTime = new SimpleDateFormat(DatabaseProperties.getDateFormat());
    Date date = dateTime.parse("2008-01-01");

    AssaySex sex = AssaySex.MALE;

    AdultDiscriminatingDoseAssay assay = new AdultDiscriminatingDoseAssay();
    assay.setCollection(collection);
    assay.setTestDate(date);
    assay.addSex(sex);
    assay.setIdentificationMethod(identificationMethod);
    assay.setTestMethod(assayMethod);
    assay.setExposureTime(60);
    assay.setIntervalTime(7);
    assay.setHoldingTime(24);
    assay.setControlTestMortality(new Float(99.99));
    assay.setIsofemale(false);
    assay.setQuantityDead(30);
    assay.setQuantityTested(30);
    assay.getAgeRange().setStartPoint(2);
    assay.getAgeRange().setEndPoint(20);
    assay.setInsecticide(insecticide);

    assay.setGeneration(F1);

    assay.apply();

    DDATestIntervalView[] intervals = assay.getTestIntervals();

    for (int i = 0; i < intervals.length; i++)
    {
      intervals[i].setKnockedDown(i);
    }

    DDATestIntervalView.saveAll(intervals);

    try
    {
      intervals = assay.getTestIntervals();

      assertEquals(assay.calculatePeriod(), new Integer(intervals.length));

      for (int i = 0; i < 9; i++)
      {
        assertEquals(assay.getId(), intervals[i].getAssay().getId());
        assertEquals(new Integer(i), intervals[i].getPeriod());
        assertEquals(new Integer(i), intervals[i].getKnockedDown());
      }
    }
    finally
    {
      assay.delete();
    }
  }

  public void testInvalidPeriod() throws ParseException
  {
    SimpleDateFormat dateTime = new SimpleDateFormat(DatabaseProperties.getDateFormat());
    Date date = dateTime.parse("2008-01-01");

    AssaySex sex = AssaySex.MALE;
    DDATestInterval interval = new DDATestInterval();
    int period = 20;

    AdultDiscriminatingDoseAssay assay = new AdultDiscriminatingDoseAssay();
    assay.setCollection(collection);
    assay.setTestDate(date);
    assay.addSex(sex);
    assay.setIdentificationMethod(identificationMethod);
    assay.setTestMethod(assayMethod);
    assay.setExposureTime(60);
    assay.setIntervalTime(30);
    assay.setGeneration(F1);
    assay.setHoldingTime(24);
    assay.setControlTestMortality(new Float(99.99));
    assay.setIsofemale(false);
    assay.setGeneration(F1);
    assay.setQuantityDead(30);
    assay.setQuantityTested(30);
    assay.getAgeRange().setStartPoint(2);
    assay.getAgeRange().setEndPoint(period);
    assay.setInsecticide(insecticide);

    assay.apply();

    try
    {
      interval.setAssay(assay);
      interval.setPeriod(period);
      interval.setKnockedDown(2);
      interval.apply();

      fail("Able to create a test interval with an invalid period");
    }
    catch (ProblemException e)
    {
      // This is expected
      List<ProblemIF> problems = e.getProblems();

      assertEquals(1, problems.size());
      assertTrue(problems.get(0) instanceof InvalidPeriodProblem);

      InvalidPeriodProblem problem = (InvalidPeriodProblem) problems.get(0);

      assertEquals(interval.getId(), problem.getIntervalId());
      assertEquals(new Integer(period), problem.getPeriod());
    }
    finally
    {
      assay.delete();
    }
  }

  public void testDuplicatePeriod() throws ParseException
  {
    SimpleDateFormat dateTime = new SimpleDateFormat(DatabaseProperties.getDateFormat());
    Date date = dateTime.parse("2008-01-01");

    AssaySex sex = AssaySex.MALE;
    int period = 20;

    AdultDiscriminatingDoseAssay assay = new AdultDiscriminatingDoseAssay();
    assay.setCollection(collection);
    assay.setTestDate(date);
    assay.addSex(sex);
    assay.setIdentificationMethod(identificationMethod);
    assay.setTestMethod(assayMethod);
    assay.setExposureTime(60);
    assay.setIntervalTime(30);
    assay.setGeneration(F1);
    assay.setHoldingTime(24);
    assay.setControlTestMortality(new Float(99.99));
    assay.setIsofemale(false);
    assay.setGeneration(F1);
    assay.setQuantityDead(30);
    assay.setQuantityTested(30);
    assay.getAgeRange().setStartPoint(2);
    assay.getAgeRange().setEndPoint(period);
    assay.setInsecticide(insecticide);

    assay.apply();

    try
    {
      DDATestInterval interval = new DDATestInterval();
      interval.setAssay(assay);
      interval.setPeriod(0);
      interval.setKnockedDown(2);
      interval.apply();

      fail("Able to create a test interval with an duplicate period");
    }
    catch (DuplicateDataDatabaseException e)
    {
      // This is expected
    }
    finally
    {
      assay.delete();
    }
  }

  public void testNumberKnockedUpperBoundary() throws ParseException
  {
    SimpleDateFormat dateTime = new SimpleDateFormat(DatabaseProperties.getDateFormat());
    Date date = dateTime.parse("2008-01-01");

    AssaySex sex = AssaySex.MALE;

    AdultDiscriminatingDoseAssay assay = new AdultDiscriminatingDoseAssay();
    assay.setCollection(collection);
    assay.setTestDate(date);
    assay.addSex(sex);
    assay.setIdentificationMethod(identificationMethod);
    assay.setTestMethod(assayMethod);
    assay.setExposureTime(60);
    assay.setIntervalTime(7);
    assay.setGeneration(F1);
    assay.setHoldingTime(24);
    assay.setControlTestMortality(new Float(99.99));
    assay.setIsofemale(false);
    assay.setQuantityDead(30);
    assay.setQuantityTested(30);
    assay.getAgeRange().setStartPoint(2);
    assay.getAgeRange().setEndPoint(20);
    assay.setInsecticide(insecticide);

    assay.apply();

    DDATestIntervalView[] intervals = assay.getTestIntervals();

    for (int i = 0; i < intervals.length; i++)
    {
      intervals[i].setKnockedDown(30);
      intervals[i].apply();
    }

    try
    {
      intervals = assay.getTestIntervals();

      assertEquals(assay.calculatePeriod(), new Integer(intervals.length));

      for (int i = 0; i < 9; i++)
      {
        assertEquals(assay.getId(), intervals[i].getAssay().getId());
        assertEquals(new Integer(i), intervals[i].getPeriod());
        assertEquals(new Integer(30), intervals[i].getKnockedDown());
      }
    }
    finally
    {
      assay.delete();
    }
  }

  public void testNumberKnockedDownGreaterThanTotalNumber() throws ParseException
  {
    SimpleDateFormat dateTime = new SimpleDateFormat(DatabaseProperties.getDateFormat());
    Date date = dateTime.parse("2008-01-01");

    AssaySex sex = AssaySex.MALE;
    DDATestInterval interval = null;
    int quantityTested = 30;
    int quantityKnockedDown = 45;

    AdultDiscriminatingDoseAssay assay = new AdultDiscriminatingDoseAssay();
    assay.setCollection(collection);
    assay.setTestDate(date);
    assay.addSex(sex);
    assay.setIdentificationMethod(identificationMethod);
    assay.setTestMethod(assayMethod);
    assay.setExposureTime(60);
    assay.setIntervalTime(7);
    assay.setGeneration(F1);
    assay.setHoldingTime(24);
    assay.setControlTestMortality(new Float(99.99));
    assay.setIsofemale(false);
    assay.setGeneration(F1);
    assay.setQuantityDead(30);
    assay.setQuantityTested(30);
    assay.getAgeRange().setStartPoint(2);
    assay.getAgeRange().setEndPoint(20);
    assay.setInsecticide(insecticide);

    assay.apply();

    try
    {
      DDATestIntervalView[] intervals = assay.getTestIntervals();
      interval = DDATestInterval.get(intervals[0].getIntervalId());

      interval.setAssay(assay);
      interval.setPeriod(0);
      interval.setKnockedDown(quantityKnockedDown);
      interval.apply();

      fail("Able to create a set the knocked down value greater than the number tested");
    }
    catch (ProblemException e)
    {
      // This is expected
      List<ProblemIF> problems = e.getProblems();

      assertEquals(1, problems.size());
      assertTrue(problems.get(0) instanceof InvalidKnockDownQuantityProblem);

      InvalidKnockDownQuantityProblem problem = (InvalidKnockDownQuantityProblem) problems.get(0);

      assertEquals(interval.getId(), problem.getIntervalId());
      assertEquals(new Integer(quantityKnockedDown), problem.getQuantityKnockDown());
      assertEquals(new Integer(quantityTested), problem.getQuantityTested());
    }
    finally
    {
      assay.delete();
    }
  }

  public void testGenerationOfIsofemale() throws ParseException
  {
    SimpleDateFormat dateTime = new SimpleDateFormat(DatabaseProperties.getDateFormat());
    Date date = dateTime.parse("2008-01-01");
    AssaySex sex = AssaySex.FEMALE;
    AdultDiscriminatingDoseAssay assay = new AdultDiscriminatingDoseAssay();

    try
    {
      assay.setCollection(collection);
      assay.setTestDate(date);
      assay.addSex(sex);
      assay.setIdentificationMethod(identificationMethod);
      assay.setTestMethod(assayMethod);
      assay.setFed(10);
      assay.setGravid(10);
      assay.setExposureTime(60);
      assay.setIntervalTime(10);
      assay.setHoldingTime(24);
      assay.setControlTestMortality(new Float(99.99));
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

      assertEquals(assay.getId(), problem.getAssayId());
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

    AssaySex sex = AssaySex.MALE;

    AdultDiscriminatingDoseAssay assay = new AdultDiscriminatingDoseAssay();
    assay.setCollection(collection);
    assay.setTestDate(date);
    assay.addSex(sex);
    assay.setIdentificationMethod(identificationMethod);
    assay.setTestMethod(assayMethod);
    assay.setExposureTime(60);
    assay.setIntervalTime(10);
    assay.setGeneration(F1);
    assay.setHoldingTime(24);
    assay.setControlTestMortality(new Float(99.99));
    assay.setIsofemale(false);
    assay.setQuantityDead(30);
    assay.setQuantityTested(30);
    assay.getAgeRange().setStartPoint(2);
    assay.getAgeRange().setEndPoint(20);
    assay.setInsecticide(insecticide);

    assay.apply();

    DDATestIntervalView[] intervals = assay.getTestIntervals();

    for (int i = 0; i < intervals.length; i++)
    {
      intervals[i].setKnockedDown(i * 3);
    }

    DDATestIntervalView.saveAll(intervals);

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

    AssaySex sex = AssaySex.MALE;

    AdultDiscriminatingDoseAssay assay = new AdultDiscriminatingDoseAssay();
    assay.setCollection(collection);
    assay.setTestDate(date);
    assay.addSex(sex);
    assay.setIdentificationMethod(identificationMethod);
    assay.setTestMethod(assayMethod);
    assay.setExposureTime(60);
    assay.setIntervalTime(10);
    assay.setGeneration(F1);
    assay.setHoldingTime(24);
    assay.setControlTestMortality(new Float(99.99));
    assay.setIsofemale(false);
    assay.setQuantityDead(30);
    assay.setQuantityTested(30);
    assay.getAgeRange().setStartPoint(2);
    assay.getAgeRange().setEndPoint(20);
    assay.setInsecticide(insecticide);

    assay.apply();

    DDATestIntervalView[] intervals = assay.getTestIntervals();

    for (int i = 0; i < intervals.length; i++)
    {
      intervals[i].setKnockedDown(i * 3);
    }

    DDATestIntervalView.saveAll(intervals);

    try
    {
      assertEquals(95, (int) Math.round(assay.getKD95()));
    }
    finally
    {
      assay.delete();
    }
  }

  public void testResistant() throws ParseException
  {
    SimpleDateFormat dateTime = new SimpleDateFormat(DatabaseProperties.getDateFormat());
    Date date = dateTime.parse("2008-01-01");

    AssaySexDTO sex = AssaySexDTO.MALE;

    AdultDiscriminatingDoseAssayDTO assay = new AdultDiscriminatingDoseAssayDTO(clientRequest);
    assay.setCollection(MosquitoCollectionDTO.get(clientRequest, collection.getId()));
    assay.setTestDate(date);
    assay.addSex(sex);
    assay.setIdentificationMethod(IdentificationMethodDTO.get(clientRequest, identificationMethod
        .getId()));
    assay.setTestMethod(ResistanceMethodologyDTO.get(clientRequest, assayMethod.getId()));
    assay.setExposureTime(60);
    assay.setIntervalTime(10);
    assay.setGeneration(GenerationDTO.get(clientRequest, F1.getId()));
    assay.setHoldingTime(24);
    assay.setControlTestMortality(new Float(99.99));
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

    AssaySexDTO sex = AssaySexDTO.MALE;

    AdultDiscriminatingDoseAssayDTO assay = new AdultDiscriminatingDoseAssayDTO(clientRequest);
    assay.setCollection(MosquitoCollectionDTO.get(clientRequest, collection.getId()));
    assay.setTestDate(date);
    assay.addSex(sex);
    assay.setIdentificationMethod(IdentificationMethodDTO.get(clientRequest, identificationMethod
        .getId()));
    assay.setTestMethod(ResistanceMethodologyDTO.get(clientRequest, assayMethod.getId()));
    assay.setExposureTime(60);
    assay.setIntervalTime(10);
    assay.setGeneration(GenerationDTO.get(clientRequest, F1.getId()));
    assay.setHoldingTime(24);
    assay.setControlTestMortality(new Float(99.99));
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

    AssaySexDTO sex = AssaySexDTO.MALE;

    AdultDiscriminatingDoseAssayDTO assay = new AdultDiscriminatingDoseAssayDTO(clientRequest);
    assay.setCollection(MosquitoCollectionDTO.get(clientRequest, collection.getId()));
    assay.setTestDate(date);
    assay.addSex(sex);
    assay.setIdentificationMethod(IdentificationMethodDTO.get(clientRequest, identificationMethod
        .getId()));
    assay.setTestMethod(ResistanceMethodologyDTO.get(clientRequest, assayMethod.getId()));
    assay.setExposureTime(60);
    assay.setIntervalTime(10);
    assay.setGeneration(GenerationDTO.get(clientRequest, F1.getId()));
    assay.setHoldingTime(24);
    assay.setControlTestMortality(new Float(99.99));
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
