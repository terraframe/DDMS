package mdss.entomology;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import junit.extensions.TestSetup;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import mdss.entomology.assay.ADDATestInterval;
import mdss.entomology.assay.ADDATestIntervalView;
import mdss.entomology.assay.AdultAgeRange;
import mdss.entomology.assay.AdultDiscriminatingDoseAssay;
import mdss.entomology.assay.InvalidAgeProblem;
import mdss.entomology.assay.InvalidAgeRangeProblem;
import mdss.mo.AssayMethod;
import mdss.mo.CollectionMethod;
import mdss.mo.Generation;
import mdss.mo.IdentificationMethod;
import mdss.mo.Insecticide;
import mdss.mo.Specie;
import mdss.test.GeoEntity;
import mdss.test.Terrain;

import com.terraframe.mojo.ProblemException;
import com.terraframe.mojo.ProblemIF;
import com.terraframe.mojo.constants.DatabaseProperties;
import com.terraframe.mojo.dataaccess.attributes.AttributeValueException;
import com.terraframe.mojo.query.OIterator;
import com.terraframe.mojo.query.QueryFactory;
import com.terraframe.mojo.query.OrderBy.SortOrder;

public class ADDATest extends TestCase
{
  private static GeoEntity            geoEntity            = null;

  private static MosquitoCollection   collection           = null;

  private static CollectionMethod     collectionMethod     = null;

  private static Specie               specie               = null;

  private static IdentificationMethod identificationMethod = null;

  private static AssayMethod          assayMethod          = null;

  private static Insecticide          insecticide          = null;

  private static Generation           F0                   = null;

  private static Generation           F1                   = null;

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
    QueryFactory f = new QueryFactory();
    GenerationQuery query = new GenerationQuery(f);
    query.ORDER_BY(query.getTermName(), SortOrder.ASC);

    OIterator<? extends CollectionMethod> cIt = new CollectionMethodQuery(f).getIterator();
    OIterator<? extends Specie> sIt = new SpecieQuery(f).getIterator();
    OIterator<? extends IdentificationMethod> iIt = new IdentificationMethodQuery(f).getIterator();
    OIterator<? extends AssayMethod> aIt = new AssayMethodQuery(f).getIterator();
    OIterator<? extends Insecticide> inIt = new InsecticideQuery(f).getIterator();
    OIterator<? extends Generation> gIt = query.getIterator();

    collectionMethod = cIt.next();
    specie = sIt.next();
    identificationMethod = iIt.next();
    assayMethod = aIt.next();
    insecticide = inIt.next();
    F0 = gIt.next();
    F1 = gIt.next();

    cIt.close();
    sIt.close();
    iIt.close();
    aIt.close();
    inIt.close();
    gIt.close();

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
    assay.setAmount(10);
    assay.setUnits("%");
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
      assertEquals(new Integer(10), assay2.getAmount());
      assertEquals("%", assay2.getUnits());
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
    assay.setAmount(10);
    assay.setUnits("%");
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
      assertEquals(new Integer(10), assay2.getAmount());
      assertEquals("%", assay2.getUnits());
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
      assay.setAmount(10);
      assay.setUnits("%");
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
      assay.setHoldingTime(24);
      assay.setControlTestMortality(new Float(99.99));
      assay.setIsofemale(false);
      assay.setQuantityDead(5);
      assay.setQuantityTested(30);
      assay.getAgeRange().setStartPoint(3);
      assay.getAgeRange().setEndPoint(15);
      assay.setInsecticide(insecticide);
      assay.setAmount(10);
      assay.setUnits("%");
      assay.apply();

      fail("Able to create an adult assay with an test date before the collection date");
    }
    catch (RuntimeException e)
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
    assay.setAmount(10);
    assay.setUnits("%");
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
      assertEquals(new Integer(10), assay2.getAmount());
      assertEquals("%", assay2.getUnits());
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
    assay.setAmount(10);
    assay.setUnits("%");
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
      assertEquals(new Integer(10), assay2.getAmount());
      assertEquals("%", assay2.getUnits());
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
    assay.setAmount(10);
    assay.setUnits("%");
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
      assertEquals(new Integer(10), assay2.getAmount());
      assertEquals("%", assay2.getUnits());
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
      assay.setAmount(10);
      assay.setUnits("%");
      assay.apply();

      fail("Able to create an assay of unknown sex with invalid gravid and fed values");
    }
    catch (RuntimeException e)
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
      assay.setIntervalTime(10);
      assay.setHoldingTime(24);
      assay.setControlTestMortality(new Float(99.99));
      assay.setIsofemale(false);
      assay.setQuantityDead(5);
      assay.setQuantityTested(30);
      assay.getAgeRange().setStartPoint(2);
      assay.getAgeRange().setEndPoint(20);
      assay.setInsecticide(insecticide);
      assay.setAmount(10);
      assay.setUnits("%");
      assay.apply();

      fail("Able to create an assay of male sex with invalid gravid and fed values");
    }
    catch (RuntimeException e)
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

  public void testFedLargerThanQuantityTested() throws ParseException
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
      assay.setGravid(40);
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
      assay.setAmount(10);
      assay.setUnits("%");
      assay.apply();

      fail("Able to create an assay with a larger Gravid value than quantity tested");
    }
    catch (RuntimeException e)
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

  public void testGravidLargerThanQuantityTested() throws ParseException
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
      assay.setFed(40);
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
      assay.setAmount(10);
      assay.setUnits("%");
      assay.apply();

      fail("Able to create an assay with a larger Fed value than quantity tested");
    }
    catch (RuntimeException e)
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

  public void testInsecticideGenericName() throws ParseException
  {
    SimpleDateFormat dateTime = new SimpleDateFormat(DatabaseProperties.getDateFormat());
    Date date = dateTime.parse("2008-01-01");
    String generic = "Sample Insecticide";
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
    assay.setAmount(10);
    assay.setUnits("%");
    assay.setGenericName(generic);
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
      assertEquals(generic, assay2.getGenericName());
      assertEquals(new Integer(10), assay2.getAmount());
      assertEquals("%", assay2.getUnits());
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
    String generic = "Sample Insecticide";
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
    assay.setAmount(10);
    assay.setUnits("%");
    assay.setGenericName(generic);
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
      assertEquals(generic, assay2.getGenericName());
      assertEquals(new Integer(10), assay2.getAmount());
      assertEquals("%", assay2.getUnits());
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
    String generic = "Sample Insecticide";
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
    assay.setAmount(10);
    assay.setUnits("%");
    assay.setGenericName(generic);
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
      assertEquals(generic, assay2.getGenericName());
      assertEquals(new Integer(10), assay2.getAmount());
      assertEquals("%", assay2.getUnits());
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
      assay.setIsofemale(false);
      assay.setQuantityDead(45);
      assay.setQuantityTested(30);
      assay.getAgeRange().setStartPoint(2);
      assay.getAgeRange().setEndPoint(20);
      assay.setInsecticide(insecticide);
      assay.setAmount(10);
      assay.setUnits("%");
      assay.apply();

      fail("Able to set the number dead larger than the total number tested");
    }
    catch (RuntimeException e)
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

  public void testInvalidExposureTime() throws ParseException
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
      assay.setIntervalTime(80);
      assay.setHoldingTime(24);
      assay.setControlTestMortality(new Float(99.99));
      assay.setIsofemale(false);
      assay.setQuantityDead(30);
      assay.setQuantityTested(30);
      assay.getAgeRange().setStartPoint(2);
      assay.getAgeRange().setEndPoint(20);
      assay.setInsecticide(insecticide);
      assay.setAmount(10);
      assay.setUnits("%");
      assay.apply();

      fail("Able to set an interval time larger than the exposure time");
    }
    catch (RuntimeException e)
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

  public void testCreateTestIntervals() throws ParseException
  {
    SimpleDateFormat dateTime = new SimpleDateFormat(DatabaseProperties.getDateFormat());
    Date date = dateTime.parse("2008-01-01");
    String generic = "Sample Insecticide";
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
    assay.setAmount(10);
    assay.setUnits("%");
    assay.setGenericName(generic);
    assay.apply();

    for (int i = 0; i < 6; i++)
    {
      ADDATestInterval interval = new ADDATestInterval();
      interval.setAssay(assay);
      interval.setPeriod(i);
      interval.setKnockedDown(i);
      interval.apply();
    }

    try
    {
      ADDATestIntervalView[] intervals = assay.getTestIntervals();

      assertEquals(6, intervals.length);

      for (int i = 0; i < 6; i++)
      {
        assertEquals(assay.getId(), intervals[i].getAssayId());
        assertEquals(new Integer(i), intervals[i].getPeriod());
        assertEquals(new Integer(i), intervals[i].getKnockedDown());
      }
    }
    finally
    {
      assay.delete();
    }
  }

  public void testIntervalCeiling() throws ParseException
  {
    SimpleDateFormat dateTime = new SimpleDateFormat(DatabaseProperties.getDateFormat());
    Date date = dateTime.parse("2008-01-01");
    String generic = "Sample Insecticide";
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
    assay.setAmount(10);
    assay.setUnits("%");
    assay.setGeneration(F1);
    assay.setGenericName(generic);
    assay.apply();

    for (int i = 0; i < 9; i++)
    {
      ADDATestInterval interval = new ADDATestInterval();
      interval.setAssay(assay);
      interval.setPeriod(i);
      interval.setKnockedDown(i);
      interval.apply();
    }

    try
    {
      ADDATestIntervalView[] intervals = assay.getTestIntervals();

      assertEquals(9, intervals.length);

      for (int i = 0; i < 9; i++)
      {
        assertEquals(assay.getId(), intervals[i].getAssayId());
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
    assay.setGeneration(F1);
    assay.setQuantityDead(30);
    assay.setQuantityTested(30);
    assay.getAgeRange().setStartPoint(2);
    assay.getAgeRange().setEndPoint(20);
    assay.setInsecticide(insecticide);
    assay.setAmount(10);
    assay.setUnits("%");
    assay.setGenericName(generic);
    assay.apply();

    try
    {
      ADDATestInterval interval = new ADDATestInterval();
      interval.setAssay(assay);
      interval.setPeriod(20);
      interval.setKnockedDown(2);
      interval.apply();

      fail("Able to create a test interval with an invalid period");
    }
    catch (RuntimeException e)
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
    assay.setAmount(10);
    assay.setUnits("%");
    assay.setGenericName(generic);
    assay.apply();

    for (int i = 0; i < 9; i++)
    {
      ADDATestInterval interval = new ADDATestInterval();
      interval.setAssay(assay);
      interval.setPeriod(i);
      interval.setKnockedDown(30);
      interval.apply();
    }

    try
    {
      ADDATestIntervalView[] intervals = assay.getTestIntervals();

      assertEquals(9, intervals.length);

      for (int i = 0; i < 9; i++)
      {
        assertEquals(assay.getId(), intervals[i].getAssayId());
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
    assay.setAmount(10);
    assay.setUnits("%");
    assay.setGenericName(generic);
    assay.apply();

    try
    {
      ADDATestInterval interval = new ADDATestInterval();
      interval.setAssay(assay);
      interval.setPeriod(0);
      interval.setKnockedDown(45);
      interval.apply();

      fail("Able to create a set the knocked down value greater than the number tested");
    }
    catch (RuntimeException e)
    {
      // This is expected
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
      assay.setAmount(10);
      assay.setUnits("%");
      assay.apply();

      fail("Able to set the isofemale line to true on a F0 generation");
    }
    catch (RuntimeException e)
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

  public void testGetKD50() throws ParseException
  {
    SimpleDateFormat dateTime = new SimpleDateFormat(DatabaseProperties.getDateFormat());
    Date date = dateTime.parse("2008-01-01");
    String generic = "Sample Insecticide";
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
    assay.setAmount(10);
    assay.setUnits("%");
    assay.setGenericName(generic);
    assay.apply();

    for (int i = 0; i < 9; i++)
    {
      ADDATestInterval interval = new ADDATestInterval();
      interval.setAssay(assay);
      interval.setPeriod(i);
      interval.setKnockedDown(i * 2);
      interval.apply();
    }

    try
    {
      assertEquals(new Integer(30), assay.getKD50());
    }
    finally
    {
      assay.delete();
    }
  }
  
  public void testGetKD100() throws ParseException
  {
    SimpleDateFormat dateTime = new SimpleDateFormat(DatabaseProperties.getDateFormat());
    Date date = dateTime.parse("2008-01-01");
    String generic = "Sample Insecticide";
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
    assay.setAmount(10);
    assay.setUnits("%");
    assay.setGenericName(generic);
    assay.apply();

    for (int i = 0; i < 9; i++)
    {
      ADDATestInterval interval = new ADDATestInterval();
      interval.setAssay(assay);
      interval.setPeriod(i);
      interval.setKnockedDown(i * 2);
      interval.apply();
    }

    try
    {
      assertEquals(new Integer(50), assay.getKD95());
    }
    finally
    {
      assay.delete();
    }
  }
}
