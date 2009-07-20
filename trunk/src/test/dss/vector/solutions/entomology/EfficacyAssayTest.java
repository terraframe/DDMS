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
import com.terraframe.mojo.constants.DatabaseProperties;
import com.terraframe.mojo.web.WebClientSession;

import dss.vector.solutions.CurrentDateProblem;
import dss.vector.solutions.SurfacePosition;
import dss.vector.solutions.TestConstants;
import dss.vector.solutions.entomology.AssaySex;
import dss.vector.solutions.entomology.MosquitoCollection;
import dss.vector.solutions.entomology.assay.EfficacyAssay;
import dss.vector.solutions.entomology.assay.InvalidDeadQuantityProblem;
import dss.vector.solutions.entomology.assay.InvalidFedQuantityProblem;
import dss.vector.solutions.entomology.assay.InvalidFedSexProblem;
import dss.vector.solutions.entomology.assay.InvalidGravidQuantityProblem;
import dss.vector.solutions.entomology.assay.InvalidGravidSexProblem;
import dss.vector.solutions.entomology.assay.Unit;
import dss.vector.solutions.general.Insecticide;
import dss.vector.solutions.geo.generated.GeoEntity;
import dss.vector.solutions.geo.generated.SentinelSite;
import dss.vector.solutions.mo.CollectionMethod;
import dss.vector.solutions.mo.ActiveIngredient;
import dss.vector.solutions.mo.ResistanceMethodology;
import dss.vector.solutions.mo.Specie;

public class EfficacyAssayTest extends TestCase
{
  private static GeoEntity             geoEntity        = null;

  private static MosquitoCollection    collection       = null;

  private static CollectionMethod      collectionMethod = null;

  private static Specie                specie           = null;

  private static ResistanceMethodology assayMethod      = null;

  private static Insecticide           insecticide      = null;

  private static ClientSession         clientSession;

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
    suite.addTestSuite(EfficacyAssayTest.class);

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

    collectionMethod = CollectionMethod.getAll()[0];
    specie = Specie.getAll()[0];
    assayMethod = ResistanceMethodology.getAll()[0];

    try
    {
      ActiveIngredient activeIngredient = ActiveIngredient.getAll()[0];
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
      
      insecticide = new Insecticide();
      insecticide.setActiveIngredient(activeIngredient);
      insecticide.setAmount(new Double(40.0));
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

  public void testGravidAndFedWithMixed() throws ParseException
  {
    SimpleDateFormat dateTime = new SimpleDateFormat(DatabaseProperties.getDateFormat());
    Date date = dateTime.parse("2008-01-01");
    AssaySex sex = AssaySex.MIXED;
    SurfacePosition position = SurfacePosition.BOTTOM;

    EfficacyAssay assay = new EfficacyAssay();
    assay.setTestDate(date);
    assay.addSex(sex);

    assay.setTestMethod(assayMethod);
    assay.setFed(10);
    assay.setGravid(10);
    assay.setExposureTime(60);
    assay.setHoldingTime(24);

    assay.setQuantityDead(5);
    assay.setQuantityTested(30);
    assay.getAgeRange().setStartPoint(2);
    assay.getAgeRange().setEndPoint(20);
    assay.setInsecticide(insecticide);

    assay.addSurfacePostion(position);
    assay.setGeoEntity(geoEntity);

    assay.setColonyName("Colony Name");
    assay.apply();

    try
    {

      EfficacyAssay assay2 = EfficacyAssay.get(assay.getId());

      assertEquals(date, assay2.getTestDate());
      assertEquals(sex, assay2.getSex().get(0));

      assertEquals(assayMethod.getId(), assay2.getTestMethod().getId());
      assertEquals(new Integer(10), assay2.getFed());
      assertEquals(new Integer(10), assay2.getGravid());
      assertEquals(new Integer(60), assay2.getExposureTime());
      assertEquals(new Integer(24), assay2.getHoldingTime());
      assertEquals(new Integer(5), assay2.getQuantityDead());
      assertEquals(new Integer(30), assay2.getQuantityTested());

      assertEquals(insecticide.getId(), assay2.getInsecticide().getId());
      assertEquals(new Integer(2), assay2.getAgeRange().getStartPoint());
      assertEquals(new Integer(20), assay2.getAgeRange().getEndPoint());
      assertEquals(new Integer(60), assay2.getExposureTime());
      assertEquals(position, assay2.getSurfacePostion().get(0));
      assertEquals(geoEntity.getId(), assay2.getGeoEntity().getId());
      assertEquals("Colony Name", assay2.getColonyName());
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
    SurfacePosition position = SurfacePosition.BOTTOM;

    EfficacyAssay assay = new EfficacyAssay();
    assay.setTestDate(date);
    assay.addSex(sex);

    assay.setTestMethod(assayMethod);
    assay.setExposureTime(60);
    assay.setHoldingTime(24);

    assay.setQuantityDead(5);
    assay.setQuantityTested(30);
    assay.getAgeRange().setStartPoint(2);
    assay.getAgeRange().setEndPoint(20);
    assay.setInsecticide(insecticide);

    assay.addSurfacePostion(position);
    assay.setGeoEntity(geoEntity);

    assay.setColonyName("Colony Name");
    assay.apply();

    try
    {

      EfficacyAssay assay2 = EfficacyAssay.get(assay.getId());

      assertEquals(date, assay2.getTestDate());
      assertEquals(sex, assay2.getSex().get(0));

      assertEquals(assayMethod.getId(), assay2.getTestMethod().getId());
      assertEquals(new Integer(60), assay2.getExposureTime());
      assertEquals(new Integer(24), assay2.getHoldingTime());
      assertEquals(new Integer(5), assay2.getQuantityDead());
      assertEquals(new Integer(30), assay2.getQuantityTested());

      assertEquals(insecticide.getId(), assay2.getInsecticide().getId());
      assertEquals(new Integer(2), assay2.getAgeRange().getStartPoint());
      assertEquals(new Integer(20), assay2.getAgeRange().getEndPoint());
      assertEquals(new Integer(60), assay2.getExposureTime());
      assertEquals(position, assay2.getSurfacePostion().get(0));
      assertEquals(geoEntity.getId(), assay2.getGeoEntity().getId());
      assertEquals("Colony Name", assay2.getColonyName());
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
    SurfacePosition position = SurfacePosition.BOTTOM;

    EfficacyAssay assay = new EfficacyAssay();
    assay.setTestDate(date);
    assay.addSex(sex);

    assay.setTestMethod(assayMethod);
    assay.setExposureTime(60);
    assay.setHoldingTime(24);

    assay.setQuantityDead(5);
    assay.setQuantityTested(30);
    assay.getAgeRange().setStartPoint(2);
    assay.getAgeRange().setEndPoint(20);
    assay.setInsecticide(insecticide);

    assay.addSurfacePostion(position);
    assay.setGeoEntity(geoEntity);

    assay.setColonyName("Colony Name");
    assay.apply();

    try
    {

      EfficacyAssay assay2 = EfficacyAssay.get(assay.getId());

      assertEquals(date, assay2.getTestDate());
      assertEquals(sex, assay2.getSex().get(0));

      assertEquals(assayMethod.getId(), assay2.getTestMethod().getId());
      assertEquals(new Integer(60), assay2.getExposureTime());
      assertEquals(new Integer(24), assay2.getHoldingTime());
      assertEquals(new Integer(5), assay2.getQuantityDead());
      assertEquals(new Integer(30), assay2.getQuantityTested());

      assertEquals(insecticide.getId(), assay2.getInsecticide().getId());
      assertEquals(new Integer(2), assay2.getAgeRange().getStartPoint());
      assertEquals(new Integer(20), assay2.getAgeRange().getEndPoint());
      assertEquals(new Integer(60), assay2.getExposureTime());
      assertEquals(position, assay2.getSurfacePostion().get(0));
      assertEquals(geoEntity.getId(), assay2.getGeoEntity().getId());
      assertEquals("Colony Name", assay2.getColonyName());
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
    EfficacyAssay assay = new EfficacyAssay();
    SurfacePosition position = SurfacePosition.BOTTOM;

    try
    {
      assay.setTestDate(date);
      assay.addSex(sex);

      assay.setTestMethod(assayMethod);
      assay.setFed(10);
      assay.setGravid(10);

      assay.setExposureTime(60);
      assay.setHoldingTime(24);

      assay.setQuantityDead(5);
      assay.setQuantityTested(30);
      assay.getAgeRange().setStartPoint(2);
      assay.getAgeRange().setEndPoint(20);
      assay.setInsecticide(insecticide);
      assay.addSurfacePostion(position);
      assay.setGeoEntity(geoEntity);

      assay.setColonyName("Colony Name");
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
    EfficacyAssay assay = new EfficacyAssay();
    SurfacePosition position = SurfacePosition.BOTTOM;

    try
    {
      assay.setTestDate(date);
      assay.addSex(sex);

      assay.setTestMethod(assayMethod);
      assay.setFed(10);
      assay.setGravid(10);
      assay.setExposureTime(60);

      assay.setHoldingTime(24);

      assay.setQuantityDead(5);
      assay.setQuantityTested(30);
      assay.getAgeRange().setStartPoint(2);
      assay.getAgeRange().setEndPoint(20);
      assay.setInsecticide(insecticide);
      assay.addSurfacePostion(position);
      assay.setGeoEntity(geoEntity);

      assay.setColonyName("Colony Name");
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

  public void testCurrentDateProblem() throws ParseException
  {
    Calendar calendar = Calendar.getInstance();
    calendar.add(Calendar.DAY_OF_YEAR, 99);
    Date date = calendar.getTime();
    
    AssaySex sex = AssaySex.FEMALE;
    EfficacyAssay assay = new EfficacyAssay();
    SurfacePosition position = SurfacePosition.BOTTOM;
    
    try
    {
      assay.setTestDate(date);
      assay.addSex(sex);      
      assay.setTestMethod(assayMethod);
      assay.setFed(10);
      assay.setGravid(10);
      assay.setExposureTime(60);      
      assay.setHoldingTime(24);      
      assay.setQuantityDead(5);
      assay.setQuantityTested(30);
      assay.getAgeRange().setStartPoint(2);
      assay.getAgeRange().setEndPoint(20);
      assay.setInsecticide(insecticide);
      assay.addSurfacePostion(position);
      assay.setGeoEntity(geoEntity);      
      assay.setColonyName("Colony Name");
      assay.apply();
      
      fail("Able to create an assay with a test after the current date");
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
  
  public void testFedLargerThanQuantityTested() throws ParseException
  {
    SimpleDateFormat dateTime = new SimpleDateFormat(DatabaseProperties.getDateFormat());
    Date date = dateTime.parse("2008-01-01");
    AssaySex sex = AssaySex.FEMALE;
    EfficacyAssay assay = new EfficacyAssay();
    int fed = 40;
    SurfacePosition position = SurfacePosition.BOTTOM;

    try
    {
      assay.setTestDate(date);
      assay.addSex(sex);

      assay.setTestMethod(assayMethod);
      assay.setFed(fed);
      assay.setGravid(10);
      assay.setExposureTime(60);
      assay.setHoldingTime(24);
      assay.setQuantityDead(5);
      assay.setQuantityTested(30);
      assay.getAgeRange().setStartPoint(2);
      assay.getAgeRange().setEndPoint(20);
      assay.setInsecticide(insecticide);
      assay.addSurfacePostion(position);
      assay.setGeoEntity(geoEntity);
      assay.setColonyName("Colony Name");
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
    AssaySex sex = AssaySex.FEMALE;
    EfficacyAssay assay = new EfficacyAssay();
    int gravid = 40;
    SurfacePosition position = SurfacePosition.BOTTOM;

    try
    {
      assay.setTestDate(date);
      assay.addSex(sex);
      assay.setTestMethod(assayMethod);
      assay.setFed(10);
      assay.setGravid(gravid);
      assay.setExposureTime(60);
      assay.setHoldingTime(24);
      assay.setQuantityDead(5);
      assay.setQuantityTested(30);
      assay.getAgeRange().setStartPoint(2);
      assay.getAgeRange().setEndPoint(20);
      assay.setInsecticide(insecticide);
      assay.addSurfacePostion(position);
      assay.setGeoEntity(geoEntity);
      assay.setColonyName("Colony Name");
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

  public void testNumberDeadLowerBoundary() throws ParseException
  {
    SimpleDateFormat dateTime = new SimpleDateFormat(DatabaseProperties.getDateFormat());
    Date date = dateTime.parse("2008-01-01");
    AssaySex sex = AssaySex.MALE;
    SurfacePosition position = SurfacePosition.BOTTOM;

    EfficacyAssay assay = new EfficacyAssay();
    assay.setTestDate(date);
    assay.addSex(sex);

    assay.setTestMethod(assayMethod);
    assay.setExposureTime(60);
    assay.setHoldingTime(24);

    assay.setQuantityDead(0);
    assay.setQuantityTested(30);
    assay.getAgeRange().setStartPoint(2);
    assay.getAgeRange().setEndPoint(20);
    assay.setInsecticide(insecticide);

    assay.addSurfacePostion(position);
    assay.setGeoEntity(geoEntity);

    assay.setColonyName("Colony Name");
    assay.apply();

    try
    {

      EfficacyAssay assay2 = EfficacyAssay.get(assay.getId());

      assertEquals(date, assay2.getTestDate());
      assertEquals(sex, assay2.getSex().get(0));

      assertEquals(assayMethod.getId(), assay2.getTestMethod().getId());
      assertEquals(new Integer(60), assay2.getExposureTime());
      assertEquals(new Integer(24), assay2.getHoldingTime());
      assertEquals(new Integer(0), assay2.getQuantityDead());
      assertEquals(new Integer(30), assay2.getQuantityTested());

      assertEquals(insecticide.getId(), assay2.getInsecticide().getId());
      assertEquals(new Integer(2), assay2.getAgeRange().getStartPoint());
      assertEquals(new Integer(20), assay2.getAgeRange().getEndPoint());
      assertEquals(new Integer(60), assay2.getExposureTime());
      assertEquals(position, assay2.getSurfacePostion().get(0));
      assertEquals(geoEntity.getId(), assay2.getGeoEntity().getId());
      assertEquals("Colony Name", assay2.getColonyName());

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
    SurfacePosition position = SurfacePosition.BOTTOM;

    EfficacyAssay assay = new EfficacyAssay();
    assay.setTestDate(date);
    assay.addSex(sex);

    assay.setTestMethod(assayMethod);
    assay.setExposureTime(60);
    assay.setHoldingTime(24);

    assay.setQuantityDead(30);
    assay.setQuantityTested(30);
    assay.getAgeRange().setStartPoint(2);
    assay.getAgeRange().setEndPoint(20);
    assay.setInsecticide(insecticide);

    assay.addSurfacePostion(position);
    assay.setGeoEntity(geoEntity);

    assay.setColonyName("Colony Name");
    assay.apply();

    try
    {

      EfficacyAssay assay2 = EfficacyAssay.get(assay.getId());

      assertEquals(date, assay2.getTestDate());
      assertEquals(sex, assay2.getSex().get(0));

      assertEquals(assayMethod.getId(), assay2.getTestMethod().getId());
      assertEquals(new Integer(60), assay2.getExposureTime());
      assertEquals(new Integer(24), assay2.getHoldingTime());
      assertEquals(new Integer(30), assay2.getQuantityDead());
      assertEquals(new Integer(30), assay2.getQuantityTested());

      assertEquals(insecticide.getId(), assay2.getInsecticide().getId());
      assertEquals(new Integer(2), assay2.getAgeRange().getStartPoint());
      assertEquals(new Integer(20), assay2.getAgeRange().getEndPoint());
      assertEquals(new Integer(60), assay2.getExposureTime());
      assertEquals(position, assay2.getSurfacePostion().get(0));
      assertEquals(geoEntity.getId(), assay2.getGeoEntity().getId());
      assertEquals("Colony Name", assay2.getColonyName());

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
    EfficacyAssay assay = new EfficacyAssay();
    int quantityDead = 45;
    int quantityTested = 30;
    SurfacePosition position = SurfacePosition.BOTTOM;

    try
    {
      assay.setTestDate(date);
      assay.addSex(sex);

      assay.setTestMethod(assayMethod);
      assay.setFed(10);

      assay.setGravid(10);
      assay.setExposureTime(60);
      assay.setHoldingTime(24);

      assay.setQuantityDead(quantityDead);
      assay.setQuantityTested(quantityTested);
      assay.getAgeRange().setStartPoint(2);
      assay.getAgeRange().setEndPoint(20);
      assay.setInsecticide(insecticide);
      assay.addSurfacePostion(position);
      assay.setGeoEntity(geoEntity);

      assay.setColonyName("Colony Name");
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

  public void testEfficacyAssay() throws ParseException
  {
    SimpleDateFormat dateTime = new SimpleDateFormat(DatabaseProperties.getDateFormat());
    Date date = dateTime.parse("2008-01-01");
    SurfacePosition position = SurfacePosition.BOTTOM;

    EfficacyAssay assay = new EfficacyAssay();
    assay.setSpecie(specie);
    assay.setTestDate(date);
    assay.addSex(AssaySex.FEMALE);

    assay.setTestMethod(assayMethod);
    assay.setFed(10);
    assay.setGravid(10);
    assay.setExposureTime(60);
    assay.setHoldingTime(24);

    assay.setQuantityDead(5);
    assay.setQuantityTested(30);
    assay.getAgeRange().setStartPoint(2);
    assay.getAgeRange().setEndPoint(20);
    assay.setInsecticide(insecticide);
    assay.addSurfacePostion(position);
    assay.setGeoEntity(geoEntity);

    assay.setColonyName("Colony Name");
    assay.apply();

    try
    {

      EfficacyAssay assay2 = EfficacyAssay.get(assay.getId());

      assertEquals(specie.getId(), assay2.getSpecie().getId());
      assertEquals(date, assay2.getTestDate());
      assertEquals(AssaySex.FEMALE, assay2.getSex().get(0));

      assertEquals(assayMethod.getId(), assay2.getTestMethod().getId());
      assertEquals(new Integer(10), assay2.getFed());
      assertEquals(new Integer(10), assay2.getGravid());
      assertEquals(new Integer(60), assay2.getExposureTime());
      assertEquals(new Integer(24), assay2.getHoldingTime());
      assertEquals(new Integer(5), assay2.getQuantityDead());
      assertEquals(new Integer(30), assay2.getQuantityTested());

      assertEquals(insecticide.getId(), assay2.getInsecticide().getId());
      assertEquals(new Integer(2), assay2.getAgeRange().getStartPoint());
      assertEquals(new Integer(20), assay2.getAgeRange().getEndPoint());
      assertEquals(new Integer(60), assay2.getExposureTime());
      assertEquals(position, assay2.getSurfacePostion().get(0));
      assertEquals(geoEntity.getId(), assay2.getGeoEntity().getId());
      assertEquals("Colony Name", assay2.getColonyName());
    }
    finally
    {
      assay.delete();
    }
  }

  public void testGetAllEfficacyAssay() throws ParseException
  {
    SimpleDateFormat dateTime = new SimpleDateFormat(DatabaseProperties.getDateFormat());
    Date date = dateTime.parse("2008-01-01");
    SurfacePosition position = SurfacePosition.BOTTOM;

    EfficacyAssay assay = new EfficacyAssay();
    assay.setSpecie(specie);
    assay.setTestDate(date);
    assay.addSex(AssaySex.FEMALE);
    assay.setTestMethod(assayMethod);
    assay.setFed(10);
    assay.setGravid(10);
    assay.setExposureTime(60);
    assay.setHoldingTime(24);
    assay.setQuantityDead(5);
    assay.setQuantityTested(30);
    assay.getAgeRange().setStartPoint(2);
    assay.getAgeRange().setEndPoint(20);
    assay.setInsecticide(insecticide);
    assay.addSurfacePostion(position);
    assay.setGeoEntity(geoEntity);

    assay.setColonyName("Colony Name");
    assay.apply();

    try
    {

      EfficacyAssay[] assays = EfficacyAssay.searchByGeoEntityAndDate(geoEntity, date);

      assertEquals(1, assays.length);
      EfficacyAssay assay2 = assays[0];

      assertEquals(specie.getId(), assay2.getSpecie().getId());
      assertEquals(date, assay2.getTestDate());
      assertEquals(AssaySex.FEMALE, assay2.getSex().get(0));

      assertEquals(assayMethod.getId(), assay2.getTestMethod().getId());
      assertEquals(new Integer(10), assay2.getFed());
      assertEquals(new Integer(10), assay2.getGravid());
      assertEquals(new Integer(24), assay2.getHoldingTime());
      assertEquals(new Integer(5), assay2.getQuantityDead());
      assertEquals(new Integer(30), assay2.getQuantityTested());
      assertEquals(insecticide.getId(), assay2.getInsecticide().getId());
      assertEquals(new Integer(2), assay2.getAgeRange().getStartPoint());
      assertEquals(new Integer(20), assay2.getAgeRange().getEndPoint());
      assertEquals(new Integer(60), assay2.getExposureTime());
      assertEquals(position, assay2.getSurfacePostion().get(0));
      assertEquals(geoEntity.getId(), assay2.getGeoEntity().getId());
      assertEquals("Colony Name", assay2.getColonyName());
    }
    finally
    {
      assay.delete();
    }
  }

  public void testOverallMortality() throws ParseException
  {
    SimpleDateFormat dateTime = new SimpleDateFormat(DatabaseProperties.getDateFormat());
    Date date = dateTime.parse("2008-01-01");
    SurfacePosition position = SurfacePosition.BOTTOM;

    EfficacyAssay assay = new EfficacyAssay();
    assay.setSpecie(specie);
    assay.setTestDate(date);
    assay.addSex(AssaySex.FEMALE);

    assay.setTestMethod(assayMethod);
    assay.setFed(10);
    assay.setGravid(10);
    assay.setExposureTime(60);
    assay.setHoldingTime(24);

    assay.setQuantityDead(5);
    assay.setQuantityTested(30);
    assay.getAgeRange().setStartPoint(2);
    assay.getAgeRange().setEndPoint(20);
    assay.setInsecticide(insecticide);
    assay.addSurfacePostion(position);
    assay.setGeoEntity(geoEntity);

    assay.setColonyName("Colony Name");
    assay.apply();

    EfficacyAssay assay2 = new EfficacyAssay();
    assay2.setSpecie(specie);
    assay2.setTestDate(date);
    assay2.addSex(AssaySex.FEMALE);
    assay2.setTestMethod(assayMethod);
    assay2.setFed(10);
    assay2.setGravid(10);
    assay2.setExposureTime(60);
    assay2.setHoldingTime(24);
    assay2.setQuantityDead(25);
    assay2.setQuantityTested(30);
    assay2.getAgeRange().setStartPoint(2);
    assay2.getAgeRange().setEndPoint(20);
    assay2.setInsecticide(insecticide);
    assay2.addSurfacePostion(position);
    assay2.setGeoEntity(geoEntity);
    assay2.setColonyName("Colony Name");
    assay2.apply();

    try
    {
      assertEquals(50F, assay.getOverallMortalityRate());
      assertEquals(50F, assay2.getOverallMortalityRate());
    }
    finally
    {
      assay.delete();
      assay2.delete();
    }
  }

}
