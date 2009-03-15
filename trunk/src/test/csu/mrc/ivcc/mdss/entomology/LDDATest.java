package csu.mrc.ivcc.mdss.entomology;

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
import com.terraframe.mojo.dataaccess.database.DuplicateDataDatabaseException;
import com.terraframe.mojo.web.WebClientSession;

import csu.mrc.ivcc.mdss.entomology.assay.ADDATestInterval;
import csu.mrc.ivcc.mdss.entomology.assay.ADDATestIntervalView;
import csu.mrc.ivcc.mdss.entomology.assay.InvalidDeadQuantityProblem;
import csu.mrc.ivcc.mdss.entomology.assay.InvalidGenerationProblem;
import csu.mrc.ivcc.mdss.entomology.assay.InvalidIntervalTimeProblem;
import csu.mrc.ivcc.mdss.entomology.assay.InvalidKnockDownQuantityProblem;
import csu.mrc.ivcc.mdss.entomology.assay.InvalidPeriodProblem;
import csu.mrc.ivcc.mdss.entomology.assay.InvalidTestDateProblem;
import csu.mrc.ivcc.mdss.entomology.assay.LarvaeDiscriminatingDoseAssay;
import csu.mrc.ivcc.mdss.entomology.assay.LarvaeDiscriminatingDoseAssayDTO;
import csu.mrc.ivcc.mdss.entomology.assay.PotentiallyResistantCollectionDTO;
import csu.mrc.ivcc.mdss.entomology.assay.ResistantCollectionDTO;
import csu.mrc.ivcc.mdss.entomology.assay.SusceptibleCollectionDTO;
import csu.mrc.ivcc.mdss.entomology.assay.Unit;
import csu.mrc.ivcc.mdss.entomology.assay.UnitDTO;
import csu.mrc.ivcc.mdss.geo.generated.GeoEntity;
import csu.mrc.ivcc.mdss.geo.generated.SentinalSite;
import csu.mrc.ivcc.mdss.mo.CollectionMethod;
import csu.mrc.ivcc.mdss.mo.Generation;
import csu.mrc.ivcc.mdss.mo.GenerationDTO;
import csu.mrc.ivcc.mdss.mo.IdentificationMethod;
import csu.mrc.ivcc.mdss.mo.IdentificationMethodDTO;
import csu.mrc.ivcc.mdss.mo.Insecticide;
import csu.mrc.ivcc.mdss.mo.InsecticideDTO;
import csu.mrc.ivcc.mdss.mo.ResistanceMethodology;
import csu.mrc.ivcc.mdss.mo.ResistanceMethodologyDTO;
import csu.mrc.ivcc.mdss.mo.Specie;

public class LDDATest extends TestCase
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
    clientSession = WebClientSession.createUserSession("SYSTEM", "SYSTEM", Locale.US);
    clientRequest = clientSession.getRequest();

    collectionMethod = CollectionMethod.getAll()[0];
    specie = Specie.getAll()[0];
    identificationMethod = IdentificationMethod.getAll()[0];
    assayMethod = ResistanceMethodology.getAll()[0];
    insecticide = Insecticide.getAll()[0];
    F0 = Generation.getAll()[0];
    F1 = Generation.getAll()[1];

    try
    {
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
    }
    catch (ParseException e)
    {
      throw new RuntimeException(e);
    }
  }

  protected static void classTearDown()
  {
    collection.delete();
    geoEntity.delete();

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
    assay.setIntervalTime(10);
    assay.setHoldingTime(24);
    assay.setControlTestMortality(new Float(99.99));
    assay.setIsofemale(false);
    assay.setGeneration(F0);
    assay.setQuantityDead(5);
    assay.setQuantityTested(30);
    
    
    assay.setInsecticide(insecticide);
    assay.setAmount(10);
    assay.addUnits(Unit.PERCENT);
    assay.apply();

    try
    {

      LarvaeDiscriminatingDoseAssay assay2 = LarvaeDiscriminatingDoseAssay.get(assay.getId());

      assertEquals(collection.getId(), assay2.getCollection().getId());
      assertEquals(specie.getId(), assay2.getSpecie().getId());
      assertEquals(date, assay2.getTestDate());
      
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
      assertEquals(new Integer(10), assay2.getAmount());
      assertEquals(Unit.PERCENT, assay2.getUnits().get(0));
//      assertEquals(new Integer(2), assay2.getAgeRange().getStartPoint());
//      assertEquals(new Integer(20), assay2.getAgeRange().getEndPoint());
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
    assay.setIntervalTime(10);
    assay.setHoldingTime(24);
    assay.setControlTestMortality(new Float(99.99));
    assay.setIsofemale(false);
    assay.setQuantityDead(5);
    assay.setQuantityTested(30);
    assay.setInsecticide(insecticide);
    assay.setAmount(10);
    assay.addUnits(Unit.PERCENT);
    assay.apply();

    try
    {

      LarvaeDiscriminatingDoseAssay assay2 = LarvaeDiscriminatingDoseAssay.get(assay.getId());

      assertEquals(collection.getId(), assay2.getCollection().getId());
      assertEquals(date, assay2.getTestDate());
      
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
      assertEquals(new Integer(10), assay2.getAmount());
      assertEquals(Unit.PERCENT, assay2.getUnits().get(0));
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
      assay.setIntervalTime(10);
      assay.setHoldingTime(24);
      assay.setControlTestMortality(new Float(99.99));
      assay.setIsofemale(false);
      assay.setQuantityDead(5);
      assay.setQuantityTested(30);
      
      
      assay.setInsecticide(insecticide);
      assay.setAmount(10);
      assay.addUnits(Unit.PERCENT);
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
      assay.setIntervalTime(10);
      assay.setGeneration(F1);
      assay.setHoldingTime(24);
      assay.setControlTestMortality(new Float(99.99));
      assay.setIsofemale(false);
      assay.setQuantityDead(5);
      assay.setQuantityTested(30);
      
      
      assay.setInsecticide(insecticide);
      assay.setAmount(10);
      assay.addUnits(Unit.PERCENT);
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



  public void testInsecticideGenericName() throws ParseException
  {
    SimpleDateFormat dateTime = new SimpleDateFormat(DatabaseProperties.getDateFormat());
    Date date = dateTime.parse("2008-01-01");
    String generic = "Sample Insecticide";
    

    LarvaeDiscriminatingDoseAssay assay = new LarvaeDiscriminatingDoseAssay();
    assay.setCollection(collection);
    assay.setTestDate(date);

    assay.setIdentificationMethod(identificationMethod);
    assay.setTestMethod(assayMethod);
    assay.setExposureTime(60);
    assay.setIntervalTime(10);
    assay.setHoldingTime(24);
    assay.setControlTestMortality(new Float(99.99));
    assay.setIsofemale(false);
    assay.setQuantityDead(5);
    assay.setQuantityTested(30);
    
    
    assay.setInsecticide(insecticide);
    assay.setAmount(10);
    assay.addUnits(Unit.PERCENT);
    assay.setGenericName(generic);
    assay.setGeneration(F1);
    assay.apply();

    try
    {

      LarvaeDiscriminatingDoseAssay assay2 = LarvaeDiscriminatingDoseAssay.get(assay.getId());

      assertEquals(collection.getId(), assay2.getCollection().getId());
      assertEquals(date, assay2.getTestDate());
      
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
      assertEquals(generic, assay2.getGenericName());
      assertEquals(new Integer(10), assay2.getAmount());
      assertEquals(Unit.PERCENT, assay2.getUnits().get(0));
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
    String generic = "Sample Insecticide";
    

    LarvaeDiscriminatingDoseAssay assay = new LarvaeDiscriminatingDoseAssay();
    assay.setCollection(collection);
    assay.setTestDate(date);

    assay.setIdentificationMethod(identificationMethod);
    assay.setTestMethod(assayMethod);
    assay.setExposureTime(60);
    assay.setIntervalTime(10);
    assay.setHoldingTime(24);
    assay.setControlTestMortality(new Float(99.99));
    assay.setIsofemale(false);
    assay.setQuantityDead(0);
    assay.setQuantityTested(30);
    
    
    assay.setInsecticide(insecticide);
    assay.setAmount(10);
    assay.addUnits(Unit.PERCENT);
    assay.setGenericName(generic);
    assay.setGeneration(F1);
    assay.apply();

    try
    {

      LarvaeDiscriminatingDoseAssay assay2 = LarvaeDiscriminatingDoseAssay.get(assay.getId());

      assertEquals(collection.getId(), assay2.getCollection().getId());
      assertEquals(date, assay2.getTestDate());
      
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
      assertEquals(generic, assay2.getGenericName());
      assertEquals(new Integer(10), assay2.getAmount());
      assertEquals(Unit.PERCENT, assay2.getUnits().get(0));
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
    String generic = "Sample Insecticide";
    

    LarvaeDiscriminatingDoseAssay assay = new LarvaeDiscriminatingDoseAssay();
    assay.setCollection(collection);
    assay.setTestDate(date);

    assay.setIdentificationMethod(identificationMethod);
    assay.setTestMethod(assayMethod);
    assay.setExposureTime(60);
    assay.setIntervalTime(10);
    assay.setHoldingTime(24);
    assay.setControlTestMortality(new Float(99.99));
    assay.setIsofemale(false);
    assay.setQuantityDead(30);
    assay.setQuantityTested(30);
    
    
    assay.setInsecticide(insecticide);
    assay.setAmount(10);
    assay.addUnits(Unit.PERCENT);
    assay.setGenericName(generic);
    assay.setGeneration(F1);
    assay.apply();

    try
    {

      LarvaeDiscriminatingDoseAssay assay2 = LarvaeDiscriminatingDoseAssay.get(assay.getId());

      assertEquals(collection.getId(), assay2.getCollection().getId());
      assertEquals(date, assay2.getTestDate());
      
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
      assertEquals(generic, assay2.getGenericName());
      assertEquals(new Integer(10), assay2.getAmount());
      assertEquals(Unit.PERCENT, assay2.getUnits().get(0));
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
      assay.setIntervalTime(10);
      assay.setHoldingTime(24);
      assay.setControlTestMortality(new Float(99.99));
      assay.setIsofemale(false);
      assay.setQuantityDead(quantityDead);
      assay.setQuantityTested(quantityTested);
      
      
      assay.setInsecticide(insecticide);
      assay.setAmount(10);
      assay.addUnits(Unit.PERCENT);
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
      assay.setIntervalTime(intervalTime);
      assay.setHoldingTime(24);
      assay.setControlTestMortality(new Float(99.99));
      assay.setIsofemale(false);
      assay.setQuantityDead(30);
      assay.setQuantityTested(30);
      
      
      assay.setInsecticide(insecticide);
      assay.setAmount(10);
      assay.addUnits(Unit.PERCENT);
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
    String generic = "Sample Insecticide";
    

    LarvaeDiscriminatingDoseAssay assay = new LarvaeDiscriminatingDoseAssay();
    assay.setCollection(collection);
    assay.setTestDate(date);

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
    
    
    assay.setInsecticide(insecticide);
    assay.setAmount(10);
    assay.addUnits(Unit.PERCENT);
    assay.setGenericName(generic);
    assay.apply();

    try
    {
      int max = assay.calculatePeriod();
      ADDATestIntervalView[] intervals = assay.getTestIntervals();

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
    String generic = "Sample Insecticide";
    

    LarvaeDiscriminatingDoseAssay assay = new LarvaeDiscriminatingDoseAssay();
    assay.setCollection(collection);
    assay.setTestDate(date);

    assay.setIdentificationMethod(identificationMethod);
    assay.setTestMethod(assayMethod);
    assay.setExposureTime(60);
    assay.setIntervalTime(7);
    assay.setHoldingTime(24);
    assay.setControlTestMortality(new Float(99.99));
    assay.setIsofemale(false);
    assay.setQuantityDead(30);
    assay.setQuantityTested(30);
    
    
    assay.setInsecticide(insecticide);
    assay.setAmount(10);
    assay.addUnits(Unit.PERCENT);
    assay.setGeneration(F1);
    assay.setGenericName(generic);
    assay.apply();

    ADDATestIntervalView[] intervals = assay.getTestIntervals();

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
    String generic = "Sample Insecticide";
    

    LarvaeDiscriminatingDoseAssay assay = new LarvaeDiscriminatingDoseAssay();
    assay.setCollection(collection);
    assay.setTestDate(date);

    assay.setIdentificationMethod(identificationMethod);
    assay.setTestMethod(assayMethod);
    assay.setExposureTime(60);
    assay.setIntervalTime(7);
    assay.setHoldingTime(24);
    assay.setControlTestMortality(new Float(99.99));
    assay.setIsofemale(false);
    assay.setQuantityDead(30);
    assay.setQuantityTested(30);
    
    
    assay.setInsecticide(insecticide);
    assay.setAmount(10);
    assay.addUnits(Unit.PERCENT);
    assay.setGeneration(F1);
    assay.setGenericName(generic);
    assay.apply();

    ADDATestIntervalView[] intervals = assay.getTestIntervals();

    for (int i = 0; i < intervals.length; i++)
    {
      intervals[i].setKnockedDown(i);
    }

    ADDATestIntervalView.saveAll(intervals);

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
    String generic = "Sample Insecticide";
    
    ADDATestInterval interval = new ADDATestInterval();
    int period = 20;

    LarvaeDiscriminatingDoseAssay assay = new LarvaeDiscriminatingDoseAssay();
    assay.setCollection(collection);
    assay.setTestDate(date);

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
    
    assay.setInsecticide(insecticide);
    assay.setAmount(10);
    assay.addUnits(Unit.PERCENT);
    assay.setGenericName(generic);
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
    String generic = "Sample Insecticide";
    
    LarvaeDiscriminatingDoseAssay assay = new LarvaeDiscriminatingDoseAssay();
    assay.setCollection(collection);
    assay.setTestDate(date);

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
    
    assay.setInsecticide(insecticide);
    assay.setAmount(10);
    assay.addUnits(Unit.PERCENT);
    assay.setGenericName(generic);
    assay.apply();

    try
    {
      ADDATestInterval interval = new ADDATestInterval();
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
    String generic = "Sample Insecticide";
    

    LarvaeDiscriminatingDoseAssay assay = new LarvaeDiscriminatingDoseAssay();
    assay.setCollection(collection);
    assay.setTestDate(date);

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
    
    
    assay.setInsecticide(insecticide);
    assay.setAmount(10);
    assay.addUnits(Unit.PERCENT);
    assay.setGenericName(generic);
    assay.apply();

    ADDATestIntervalView[] intervals = assay.getTestIntervals();

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
    String generic = "Sample Insecticide";
    
    ADDATestInterval interval = null;
    int quantityTested = 30;
    int quantityKnockedDown = 45;

    LarvaeDiscriminatingDoseAssay assay = new LarvaeDiscriminatingDoseAssay();
    assay.setCollection(collection);
    assay.setTestDate(date);

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
    
    
    assay.setInsecticide(insecticide);
    assay.setAmount(10);
    assay.addUnits(Unit.PERCENT);
    assay.setGenericName(generic);
    assay.apply();

    try
    {
      ADDATestIntervalView[] intervals = assay.getTestIntervals();
      interval = ADDATestInterval.get(intervals[0].getIntervalId());

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
    LarvaeDiscriminatingDoseAssay assay = new LarvaeDiscriminatingDoseAssay();

    try
    {
      assay.setCollection(collection);
      assay.setTestDate(date);

      assay.setIdentificationMethod(identificationMethod);
      assay.setTestMethod(assayMethod);
      
      
      assay.setExposureTime(60);
      assay.setIntervalTime(10);
      assay.setHoldingTime(24);
      assay.setControlTestMortality(new Float(99.99));
      assay.setIsofemale(true);
      assay.setQuantityDead(20);
      assay.setQuantityTested(30);
      
      
      assay.setInsecticide(insecticide);
      assay.setGeneration(F0);
      assay.setAmount(10);
      assay.addUnits(Unit.PERCENT);
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
    String generic = "Sample Insecticide";

    LarvaeDiscriminatingDoseAssay assay = new LarvaeDiscriminatingDoseAssay();
    assay.setCollection(collection);
    assay.setTestDate(date);

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
    
    
    assay.setInsecticide(insecticide);
    assay.setAmount(10);
    assay.addUnits(Unit.PERCENT);
    assay.setGenericName(generic);
    assay.apply();

    ADDATestIntervalView[] intervals = assay.getTestIntervals();

    for (int i = 0; i < intervals.length; i++)
    {
      intervals[i].setKnockedDown(i * 3);
    }

    ADDATestIntervalView.saveAll(intervals);

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
    String generic = "Sample Insecticide";

    LarvaeDiscriminatingDoseAssay assay = new LarvaeDiscriminatingDoseAssay();
    assay.setCollection(collection);
    assay.setTestDate(date);

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
    
    
    assay.setInsecticide(insecticide);
    assay.setAmount(10);
    assay.addUnits(Unit.PERCENT);
    assay.setGenericName(generic);
    assay.apply();

    ADDATestIntervalView[] intervals = assay.getTestIntervals();

    for (int i = 0; i < intervals.length; i++)
    {
      intervals[i].setKnockedDown(i * 3);
    }

    ADDATestIntervalView.saveAll(intervals);

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
    String generic = "Sample Insecticide";

    LarvaeDiscriminatingDoseAssayDTO assay = new LarvaeDiscriminatingDoseAssayDTO(clientRequest);
    assay.setCollection(MosquitoCollectionDTO.get(clientRequest, collection.getId()));
    assay.setTestDate(date);

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
    
    
    assay.setInsecticide(InsecticideDTO.get(clientRequest, insecticide.getId()));
    assay.setAmount(10);
    assay.addUnits(UnitDTO.PERCENT);
    assay.setGenericName(generic);
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
    String generic = "Sample Insecticide";

    LarvaeDiscriminatingDoseAssayDTO assay = new LarvaeDiscriminatingDoseAssayDTO(clientRequest);
    assay.setCollection(MosquitoCollectionDTO.get(clientRequest, collection.getId()));
    assay.setTestDate(date);

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
    
    
    assay.setInsecticide(InsecticideDTO.get(clientRequest, insecticide.getId()));
    assay.setAmount(10);
    assay.addUnits(UnitDTO.PERCENT);
    assay.setGenericName(generic);
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
    String generic = "Sample Insecticide";

    LarvaeDiscriminatingDoseAssayDTO assay = new LarvaeDiscriminatingDoseAssayDTO(clientRequest);
    assay.setCollection(MosquitoCollectionDTO.get(clientRequest, collection.getId()));
    assay.setTestDate(date);

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
    
    
    assay.setInsecticide(InsecticideDTO.get(clientRequest, insecticide.getId()));
    assay.setAmount(10);
    assay.addUnits(UnitDTO.PERCENT);
    assay.setGenericName(generic);
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
