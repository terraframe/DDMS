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
import com.terraframe.mojo.constants.ClientRequestIF;
import com.terraframe.mojo.constants.DatabaseProperties;
import com.terraframe.mojo.web.WebClientSession;

import csu.mrc.ivcc.mdss.SurfacePosition;
import csu.mrc.ivcc.mdss.entomology.assay.EfficacyAssay;
import csu.mrc.ivcc.mdss.entomology.assay.InvalidDeadQuantityProblem;
import csu.mrc.ivcc.mdss.entomology.assay.InvalidFedQuantityProblem;
import csu.mrc.ivcc.mdss.entomology.assay.InvalidFedSexProblem;
import csu.mrc.ivcc.mdss.entomology.assay.InvalidGenerationProblem;
import csu.mrc.ivcc.mdss.entomology.assay.InvalidGravidQuantityProblem;
import csu.mrc.ivcc.mdss.entomology.assay.InvalidGravidSexProblem;
import csu.mrc.ivcc.mdss.geo.GeoEntity;
import csu.mrc.ivcc.mdss.geo.Terrain;
import csu.mrc.ivcc.mdss.mo.CollectionMethod;
import csu.mrc.ivcc.mdss.mo.Generation;
import csu.mrc.ivcc.mdss.mo.IdentificationMethod;
import csu.mrc.ivcc.mdss.mo.Insecticide;
import csu.mrc.ivcc.mdss.mo.ResistanceMethodology;
import csu.mrc.ivcc.mdss.mo.Specie;

public class EfficacyAssayTest extends TestCase
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

  protected static void classTearDown()
  {
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
    assay.setIdentificationMethod(identificationMethod);
    assay.setTestMethod(assayMethod);
    assay.setFed(10);
    assay.setGravid(10);
    assay.setExposureTime(60);
    assay.setHoldingTime(24);
    assay.setIsofemale(false);
    assay.setQuantityDead(5);
    assay.setQuantityTested(30);
    assay.getAgeRange().setStartPoint(2);
    assay.getAgeRange().setEndPoint(20);
    assay.setInsecticide(insecticide);
    assay.setGeneration(F1);
    assay.addSurfacePostion(position);
    assay.setGeoEntity(geoEntity);
    assay.setInsecticideLength(2);
    assay.setColonyName("Colony Name");
    assay.apply();

    try
    {

      EfficacyAssay assay2 = EfficacyAssay.get(assay.getId());

      assertEquals(date, assay2.getTestDate());
      assertEquals(sex, assay2.getSex().get(0));
      assertEquals(identificationMethod.getId(), assay2.getIdentificationMethod().getId());
      assertEquals(assayMethod.getId(), assay2.getTestMethod().getId());
      assertEquals(new Integer(10), assay2.getFed());
      assertEquals(new Integer(10), assay2.getGravid());
      assertEquals(new Integer(60), assay2.getExposureTime());
      assertEquals(new Integer(24), assay2.getHoldingTime());
      assertEquals(new Integer(5), assay2.getQuantityDead());
      assertEquals(new Integer(30), assay2.getQuantityTested());
      assertEquals(new Boolean(false), assay2.getIsofemale());
      assertEquals(insecticide.getId(), assay2.getInsecticide().getId());
      assertEquals(new Integer(2), assay2.getAgeRange().getStartPoint());
      assertEquals(new Integer(20), assay2.getAgeRange().getEndPoint());
      assertEquals(new Integer(2), assay2.getInsecticideLength());
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
    assay.setIdentificationMethod(identificationMethod);
    assay.setTestMethod(assayMethod);
    assay.setExposureTime(60);
    assay.setHoldingTime(24);
    assay.setIsofemale(false);
    assay.setQuantityDead(5);
    assay.setQuantityTested(30);
    assay.getAgeRange().setStartPoint(2);
    assay.getAgeRange().setEndPoint(20);
    assay.setInsecticide(insecticide);
    assay.setGeneration(F1);
    assay.addSurfacePostion(position);
    assay.setGeoEntity(geoEntity);
    assay.setInsecticideLength(2);
    assay.setColonyName("Colony Name");
    assay.apply();

    try
    {

      EfficacyAssay assay2 = EfficacyAssay.get(assay.getId());

      assertEquals(date, assay2.getTestDate());
      assertEquals(sex, assay2.getSex().get(0));
      assertEquals(identificationMethod.getId(), assay2.getIdentificationMethod().getId());
      assertEquals(assayMethod.getId(), assay2.getTestMethod().getId());
      assertEquals(new Integer(60), assay2.getExposureTime());
      assertEquals(new Integer(24), assay2.getHoldingTime());
      assertEquals(new Integer(5), assay2.getQuantityDead());
      assertEquals(new Integer(30), assay2.getQuantityTested());
      assertEquals(new Boolean(false), assay2.getIsofemale());
      assertEquals(insecticide.getId(), assay2.getInsecticide().getId());
      assertEquals(new Integer(2), assay2.getAgeRange().getStartPoint());
      assertEquals(new Integer(20), assay2.getAgeRange().getEndPoint());
      assertEquals(new Integer(2), assay2.getInsecticideLength());
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
    assay.setIdentificationMethod(identificationMethod);
    assay.setTestMethod(assayMethod);
    assay.setExposureTime(60);
    assay.setHoldingTime(24);
    assay.setIsofemale(false);
    assay.setQuantityDead(5);
    assay.setQuantityTested(30);
    assay.getAgeRange().setStartPoint(2);
    assay.getAgeRange().setEndPoint(20);
    assay.setInsecticide(insecticide);
    assay.setGeneration(F1);
    assay.addSurfacePostion(position);
    assay.setGeoEntity(geoEntity);
    assay.setInsecticideLength(2);
    assay.setColonyName("Colony Name");
    assay.apply();

    try
    {

      EfficacyAssay assay2 = EfficacyAssay.get(assay.getId());

      assertEquals(date, assay2.getTestDate());
      assertEquals(sex, assay2.getSex().get(0));
      assertEquals(identificationMethod.getId(), assay2.getIdentificationMethod().getId());
      assertEquals(assayMethod.getId(), assay2.getTestMethod().getId());
      assertEquals(new Integer(60), assay2.getExposureTime());
      assertEquals(new Integer(24), assay2.getHoldingTime());
      assertEquals(new Integer(5), assay2.getQuantityDead());
      assertEquals(new Integer(30), assay2.getQuantityTested());
      assertEquals(new Boolean(false), assay2.getIsofemale());
      assertEquals(insecticide.getId(), assay2.getInsecticide().getId());
      assertEquals(new Integer(2), assay2.getAgeRange().getStartPoint());
      assertEquals(new Integer(20), assay2.getAgeRange().getEndPoint());
      assertEquals(new Integer(2), assay2.getInsecticideLength());
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
      assay.setIdentificationMethod(identificationMethod);
      assay.setTestMethod(assayMethod);
      assay.setFed(10);
      assay.setGravid(10);
      assay.setGeneration(F1);
      assay.setExposureTime(60);
      assay.setHoldingTime(24);
      assay.setIsofemale(false);
      assay.setQuantityDead(5);
      assay.setQuantityTested(30);
      assay.getAgeRange().setStartPoint(2);
      assay.getAgeRange().setEndPoint(20);
      assay.setInsecticide(insecticide);
      assay.addSurfacePostion(position);
      assay.setGeoEntity(geoEntity);
      assay.setInsecticideLength(2);
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
      assay.setIdentificationMethod(identificationMethod);
      assay.setTestMethod(assayMethod);
      assay.setFed(10);
      assay.setGravid(10);
      assay.setExposureTime(60);
      assay.setGeneration(F1);
      assay.setHoldingTime(24);
      assay.setIsofemale(false);
      assay.setQuantityDead(5);
      assay.setQuantityTested(30);
      assay.getAgeRange().setStartPoint(2);
      assay.getAgeRange().setEndPoint(20);
      assay.setInsecticide(insecticide);
      assay.addSurfacePostion(position);
      assay.setGeoEntity(geoEntity);
      assay.setInsecticideLength(2);
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
      assay.setIdentificationMethod(identificationMethod);
      assay.setTestMethod(assayMethod);
      assay.setFed(fed);
      assay.setGravid(10);
      assay.setExposureTime(60);
      assay.setGeneration(F1);
      assay.setHoldingTime(24);
      assay.setIsofemale(false);
      assay.setQuantityDead(5);
      assay.setQuantityTested(30);
      assay.getAgeRange().setStartPoint(2);
      assay.getAgeRange().setEndPoint(20);
      assay.setInsecticide(insecticide);
      assay.addSurfacePostion(position);
      assay.setGeoEntity(geoEntity);
      assay.setInsecticideLength(2);
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
    EfficacyAssay assay = new EfficacyAssay();
    int gravid = 40;
    SurfacePosition position = SurfacePosition.BOTTOM;

    try
    {
      assay.setTestDate(date);
      assay.addSex(sex);
      assay.setIdentificationMethod(identificationMethod);
      assay.setTestMethod(assayMethod);
      assay.setFed(10);
      assay.setGravid(gravid);
      assay.setGeneration(F1);
      assay.setExposureTime(60);
      assay.setHoldingTime(24);
      assay.setIsofemale(false);
      assay.setQuantityDead(5);
      assay.setQuantityTested(30);
      assay.getAgeRange().setStartPoint(2);
      assay.getAgeRange().setEndPoint(20);
      assay.setInsecticide(insecticide);
      assay.addSurfacePostion(position);
      assay.setGeoEntity(geoEntity);
      assay.setInsecticideLength(2);
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

  public void testNumberDeadLowerBoundary() throws ParseException
  {
    SimpleDateFormat dateTime = new SimpleDateFormat(DatabaseProperties.getDateFormat());
    Date date = dateTime.parse("2008-01-01");
    AssaySex sex = AssaySex.MALE;
    SurfacePosition position = SurfacePosition.BOTTOM;

    EfficacyAssay assay = new EfficacyAssay();
    assay.setTestDate(date);
    assay.addSex(sex);
    assay.setIdentificationMethod(identificationMethod);
    assay.setTestMethod(assayMethod);
    assay.setExposureTime(60);
    assay.setHoldingTime(24);
    assay.setIsofemale(false);
    assay.setQuantityDead(0);
    assay.setQuantityTested(30);
    assay.getAgeRange().setStartPoint(2);
    assay.getAgeRange().setEndPoint(20);
    assay.setInsecticide(insecticide);
    assay.setGeneration(F1);
    assay.addSurfacePostion(position);
    assay.setGeoEntity(geoEntity);
    assay.setInsecticideLength(2);
    assay.setColonyName("Colony Name");
    assay.apply();

    try
    {

      EfficacyAssay assay2 = EfficacyAssay.get(assay.getId());

      assertEquals(date, assay2.getTestDate());
      assertEquals(sex, assay2.getSex().get(0));
      assertEquals(identificationMethod.getId(), assay2.getIdentificationMethod().getId());
      assertEquals(assayMethod.getId(), assay2.getTestMethod().getId());
      assertEquals(new Integer(60), assay2.getExposureTime());
      assertEquals(new Integer(24), assay2.getHoldingTime());
      assertEquals(new Integer(0), assay2.getQuantityDead());
      assertEquals(new Integer(30), assay2.getQuantityTested());
      assertEquals(new Boolean(false), assay2.getIsofemale());
      assertEquals(insecticide.getId(), assay2.getInsecticide().getId());
      assertEquals(new Integer(2), assay2.getAgeRange().getStartPoint());
      assertEquals(new Integer(20), assay2.getAgeRange().getEndPoint());
      assertEquals(new Integer(2), assay2.getInsecticideLength());
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
    assay.setIdentificationMethod(identificationMethod);
    assay.setTestMethod(assayMethod);
    assay.setExposureTime(60);
    assay.setHoldingTime(24);
    assay.setIsofemale(false);
    assay.setQuantityDead(30);
    assay.setQuantityTested(30);
    assay.getAgeRange().setStartPoint(2);
    assay.getAgeRange().setEndPoint(20);
    assay.setInsecticide(insecticide);
    assay.setGeneration(F1);
    assay.addSurfacePostion(position);
    assay.setGeoEntity(geoEntity);
    assay.setInsecticideLength(2);
    assay.setColonyName("Colony Name");
    assay.apply();

    try
    {

      EfficacyAssay assay2 = EfficacyAssay.get(assay.getId());

      assertEquals(date, assay2.getTestDate());
      assertEquals(sex, assay2.getSex().get(0));
      assertEquals(identificationMethod.getId(), assay2.getIdentificationMethod().getId());
      assertEquals(assayMethod.getId(), assay2.getTestMethod().getId());
      assertEquals(new Integer(60), assay2.getExposureTime());
      assertEquals(new Integer(24), assay2.getHoldingTime());
      assertEquals(new Integer(30), assay2.getQuantityDead());
      assertEquals(new Integer(30), assay2.getQuantityTested());
      assertEquals(new Boolean(false), assay2.getIsofemale());
      assertEquals(insecticide.getId(), assay2.getInsecticide().getId());
      assertEquals(new Integer(2), assay2.getAgeRange().getStartPoint());
      assertEquals(new Integer(20), assay2.getAgeRange().getEndPoint());
      assertEquals(new Integer(2), assay2.getInsecticideLength());
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
      assay.setIdentificationMethod(identificationMethod);
      assay.setTestMethod(assayMethod);
      assay.setFed(10);
      assay.setGeneration(F1);
      assay.setGravid(10);
      assay.setExposureTime(60);
      assay.setHoldingTime(24);
      assay.setIsofemale(false);
      assay.setQuantityDead(quantityDead);
      assay.setQuantityTested(quantityTested);
      assay.getAgeRange().setStartPoint(2);
      assay.getAgeRange().setEndPoint(20);
      assay.setInsecticide(insecticide);
      assay.addSurfacePostion(position);
      assay.setGeoEntity(geoEntity);
      assay.setInsecticideLength(2);
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

  public void testGenerationOfIsofemale() throws ParseException
  {
    SimpleDateFormat dateTime = new SimpleDateFormat(DatabaseProperties.getDateFormat());
    Date date = dateTime.parse("2008-01-01");
    AssaySex sex = AssaySex.FEMALE;
    EfficacyAssay assay = new EfficacyAssay();
    SurfacePosition position = SurfacePosition.BOTTOM;

    try
    {
      assay.setTestDate(date);
      assay.addSex(sex);
      assay.setIdentificationMethod(identificationMethod);
      assay.setTestMethod(assayMethod);
      assay.setFed(10);
      assay.setGravid(10);
      assay.setExposureTime(60);
      assay.setHoldingTime(24);
      assay.setIsofemale(true);
      assay.setQuantityDead(20);
      assay.setQuantityTested(30);
      assay.getAgeRange().setStartPoint(2);
      assay.getAgeRange().setEndPoint(20);
      assay.setInsecticide(insecticide);
      assay.setGeneration(F0);
      assay.addSurfacePostion(position);
      assay.setGeoEntity(geoEntity);
      assay.setInsecticideLength(2);
      assay.setColonyName("Colony Name");
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

  public void testEfficacyAssay() throws ParseException
  {
    SimpleDateFormat dateTime = new SimpleDateFormat(DatabaseProperties.getDateFormat());
    Date date = dateTime.parse("2008-01-01");
    SurfacePosition position = SurfacePosition.BOTTOM;

    EfficacyAssay assay = new EfficacyAssay();
    assay.setSpecie(specie);
    assay.setTestDate(date);
    assay.addSex(AssaySex.FEMALE);
    assay.setIdentificationMethod(identificationMethod);
    assay.setTestMethod(assayMethod);
    assay.setFed(10);
    assay.setGravid(10);
    assay.setExposureTime(60);
    assay.setHoldingTime(24);
    assay.setIsofemale(false);
    assay.setGeneration(F0);
    assay.setQuantityDead(5);
    assay.setQuantityTested(30);
    assay.getAgeRange().setStartPoint(2);
    assay.getAgeRange().setEndPoint(20);
    assay.setInsecticide(insecticide);
    assay.addSurfacePostion(position);
    assay.setGeoEntity(geoEntity);
    assay.setInsecticideLength(2);
    assay.setColonyName("Colony Name");
    assay.apply();

    try
    {

      EfficacyAssay assay2 = EfficacyAssay.get(assay.getId());

      assertEquals(specie.getId(), assay2.getSpecie().getId());
      assertEquals(date, assay2.getTestDate());
      assertEquals(AssaySex.FEMALE, assay2.getSex().get(0));
      assertEquals(identificationMethod.getId(), assay2.getIdentificationMethod().getId());
      assertEquals(assayMethod.getId(), assay2.getTestMethod().getId());
      assertEquals(new Integer(10), assay2.getFed());
      assertEquals(new Integer(10), assay2.getGravid());
      assertEquals(new Integer(60), assay2.getExposureTime());
      assertEquals(new Integer(24), assay2.getHoldingTime());
      assertEquals(new Integer(5), assay2.getQuantityDead());
      assertEquals(new Integer(30), assay2.getQuantityTested());
      assertEquals(new Boolean(false), assay2.getIsofemale());
      assertEquals(insecticide.getId(), assay2.getInsecticide().getId());
      assertEquals(new Integer(2), assay2.getAgeRange().getStartPoint());
      assertEquals(new Integer(20), assay2.getAgeRange().getEndPoint());
      assertEquals(new Integer(2), assay2.getInsecticideLength());
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
    assay.setIdentificationMethod(identificationMethod);
    assay.setTestMethod(assayMethod);
    assay.setFed(10);
    assay.setGravid(10);
    assay.setExposureTime(60);
    assay.setHoldingTime(24);
    assay.setIsofemale(false);
    assay.setGeneration(F0);
    assay.setQuantityDead(5);
    assay.setQuantityTested(30);
    assay.getAgeRange().setStartPoint(2);
    assay.getAgeRange().setEndPoint(20);
    assay.setInsecticide(insecticide);
    assay.addSurfacePostion(position);
    assay.setGeoEntity(geoEntity);
    assay.setInsecticideLength(2);
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
      assertEquals(identificationMethod.getId(), assay2.getIdentificationMethod().getId());
      assertEquals(assayMethod.getId(), assay2.getTestMethod().getId());
      assertEquals(new Integer(10), assay2.getFed());
      assertEquals(new Integer(10), assay2.getGravid());
      assertEquals(new Integer(60), assay2.getExposureTime());
      assertEquals(new Integer(24), assay2.getHoldingTime());
      assertEquals(new Integer(5), assay2.getQuantityDead());
      assertEquals(new Integer(30), assay2.getQuantityTested());
      assertEquals(new Boolean(false), assay2.getIsofemale());
      assertEquals(insecticide.getId(), assay2.getInsecticide().getId());
      assertEquals(new Integer(2), assay2.getAgeRange().getStartPoint());
      assertEquals(new Integer(20), assay2.getAgeRange().getEndPoint());
      assertEquals(new Integer(2), assay2.getInsecticideLength());
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
    assay.setIdentificationMethod(identificationMethod);
    assay.setTestMethod(assayMethod);
    assay.setFed(10);
    assay.setGravid(10);
    assay.setExposureTime(60);
    assay.setHoldingTime(24);
    assay.setIsofemale(false);
    assay.setGeneration(F0);
    assay.setQuantityDead(5);
    assay.setQuantityTested(30);
    assay.getAgeRange().setStartPoint(2);
    assay.getAgeRange().setEndPoint(20);
    assay.setInsecticide(insecticide);
    assay.addSurfacePostion(position);
    assay.setGeoEntity(geoEntity);
    assay.setInsecticideLength(2);
    assay.setColonyName("Colony Name");
    assay.apply();

    EfficacyAssay assay2 = new EfficacyAssay();
    assay2.setSpecie(specie);
    assay2.setTestDate(date);
    assay2.addSex(AssaySex.FEMALE);
    assay2.setIdentificationMethod(identificationMethod);
    assay2.setTestMethod(assayMethod);
    assay2.setFed(10);
    assay2.setGravid(10);
    assay2.setExposureTime(60);
    assay2.setHoldingTime(24);
    assay2.setIsofemale(false);
    assay2.setGeneration(F0);
    assay2.setQuantityDead(25);
    assay2.setQuantityTested(30);
    assay2.getAgeRange().setStartPoint(2);
    assay2.getAgeRange().setEndPoint(20);
    assay2.setInsecticide(insecticide);
    assay2.addSurfacePostion(position);
    assay2.setGeoEntity(geoEntity);
    assay2.setInsecticideLength(2);
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
