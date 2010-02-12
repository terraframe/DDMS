package dss.vector.solutions.general;

import java.util.List;

import junit.extensions.TestSetup;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestResult;
import junit.framework.TestSuite;

import com.terraframe.mojo.ProblemException;
import com.terraframe.mojo.ProblemIF;
import com.terraframe.mojo.dataaccess.database.DuplicateDataDatabaseException;

import dss.vector.solutions.TestFixture;

public class KnockDownPropertyTest extends TestCase
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

  private static Insecticide insecticide;

  public static Test suite()
  {
    TestSuite suite = new TestSuite();
    suite.addTestSuite(KnockDownPropertyTest.class);

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
    TestFixture.delete(insecticide);
  }

  protected static void classSetUp()
  {
    insecticide = TestFixture.createInsecticide();
  }

  public void testCreateWithDefaultProperty()
  {
    KnockDownTimeProperty property = new KnockDownTimeProperty();
    property.setInsecticide(insecticide);
    property.setUpperTime(40);
    property.setLowerTime(20);
    property.apply();

    try
    {
      KnockDownTimeProperty test = KnockDownTimeProperty.get(property.getId());

      assertEquals(property.getInsecticide().getId(), test.getInsecticide().getId());
      assertEquals(property.getUpperPercent(), test.getUpperPercent());
      assertEquals(property.getLowerPercent(), test.getLowerPercent());
      assertEquals(property.getUpperTime(), test.getUpperTime());
      assertEquals(property.getLowerTime(), test.getLowerTime());
    }
    finally
    {
      property.delete();
    }
  }

  public void testCreateProperty()
  {
    KnockDownTimeProperty property = new KnockDownTimeProperty();
    property.setInsecticide(insecticide);
    property.setUpperTime(40);
    property.setUpperPercent(85);
    property.setLowerTime(20);
    property.setLowerPercent(30);
    property.apply();

    try
    {
      KnockDownTimeProperty test = KnockDownTimeProperty.get(property.getId());

      assertEquals(property.getInsecticide().getId(), test.getInsecticide().getId());
      assertEquals(property.getUpperPercent(), test.getUpperPercent());
      assertEquals(property.getLowerPercent(), test.getLowerPercent());
      assertEquals(property.getUpperTime(), test.getUpperTime());
      assertEquals(property.getLowerTime(), test.getLowerTime());
    }
    finally
    {
      property.delete();
    }
  }

  public void testSearchProperty()
  {
    KnockDownTimeProperty property = new KnockDownTimeProperty();
    property.setInsecticide(insecticide);
    property.setUpperTime(40);
    property.setLowerTime(20);
    property.apply();

    try
    {
      KnockDownTimeProperty test = KnockDownTimeProperty.searchByInsecticide(insecticide);

      assertEquals(property.getInsecticide().getId(), test.getInsecticide().getId());
      assertEquals(property.getUpperPercent(), test.getUpperPercent());
      assertEquals(property.getLowerPercent(), test.getLowerPercent());
      assertEquals(property.getUpperTime(), test.getUpperTime());
      assertEquals(property.getLowerTime(), test.getLowerTime());
    }
    finally
    {
      property.delete();
    }
  }

  public void testUnknownProperty()
  {
    try
    {
      KnockDownTimeProperty.searchByInsecticide(insecticide);

      fail("Able to find a knock down time property with an unknown insecticide");
    }
    catch (UndefinedKnockDownPropertyException e)
    {
      // This is excpected
    }
  }

  public void testDuplicateProperty()
  {
    KnockDownTimeProperty property = new KnockDownTimeProperty();
    property.setInsecticide(insecticide);
    property.setUpperTime(40);
    property.setUpperPercent(85);
    property.setLowerTime(20);
    property.setLowerPercent(30);
    property.apply();

    try
    {
      KnockDownTimeProperty property2 = new KnockDownTimeProperty();
      property2.setInsecticide(insecticide);
      property2.setUpperTime(50);
      property2.setLowerTime(30);
      property2.apply();

      fail("Able to create a duplicate property");
    }
    catch (DuplicateDataDatabaseException e)
    {
      // This is excepted
    }
    finally
    {
      property.delete();
    }
  }

  public void testBounds()
  {
    KnockDownTimeProperty property = new KnockDownTimeProperty();
    property.setInsecticide(insecticide);
    property.setUpperTime(40);
    property.setUpperPercent(99);
    property.setLowerTime(20);
    property.setLowerPercent(1);
    property.apply();

    try
    {
      KnockDownTimeProperty test = KnockDownTimeProperty.get(property.getId());

      assertEquals(property.getInsecticide().getId(), test.getInsecticide().getId());
      assertEquals(property.getUpperPercent(), test.getUpperPercent());
      assertEquals(property.getLowerPercent(), test.getLowerPercent());
      assertEquals(property.getUpperTime(), test.getUpperTime());
      assertEquals(property.getLowerTime(), test.getLowerTime());
    }
    finally
    {
      property.delete();
    }
  }

  public void testMaximumPercentage()
  {
    KnockDownTimeProperty property = new KnockDownTimeProperty();
    property.setInsecticide(insecticide);
    property.setUpperTime(40);
    property.setUpperPercent(100);
    property.setLowerTime(20);
    property.setLowerPercent(1);
    property.apply();

    try
    {
      KnockDownTimeProperty test = KnockDownTimeProperty.get(property.getId());

      assertEquals(property.getInsecticide().getId(), test.getInsecticide().getId());
      assertEquals(property.getUpperPercent(), test.getUpperPercent());
      assertEquals(property.getLowerPercent(), test.getLowerPercent());
      assertEquals(property.getUpperTime(), test.getUpperTime());
      assertEquals(property.getLowerTime(), test.getLowerTime());
    }
    finally
    {
      property.delete();
    }
  }

  public void testInvalidUpperPercentage()
  {
    KnockDownTimeProperty property = new KnockDownTimeProperty();

    try
    {
      property.setInsecticide(insecticide);
      property.setUpperTime(40);
      property.setUpperPercent(105);
      property.setLowerTime(20);
      property.setLowerPercent(1);
      property.apply();
    }
    catch (ProblemException e)
    {
      List<ProblemIF> problems = e.getProblems();

      assertNotNull(problems);
      assertEquals(1, problems.size());
      assertTrue(problems.get(0) instanceof InvalidPercentageProblem);
    }
    finally
    {
      if (property != null && property.isAppliedToDB())
      {
        property.delete();
      }
    }
  }

  public void testInvalidLowerPercentage()
  {
    KnockDownTimeProperty property = new KnockDownTimeProperty();

    try
    {
      property.setInsecticide(insecticide);
      property.setUpperTime(40);
      property.setUpperPercent(78);
      property.setLowerTime(20);
      property.setLowerPercent(106);
      property.apply();
    }
    catch (ProblemException e)
    {
      List<ProblemIF> problems = e.getProblems();

      assertNotNull(problems);
      assertEquals(1, problems.size());
      assertTrue(problems.get(0) instanceof InvalidPercentageProblem);
    }
    finally
    {
      if (property != null && property.isAppliedToDB())
      {
        property.delete();
      }
    }
  }
}
