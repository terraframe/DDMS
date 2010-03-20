package dss.vector.solutions.general;

import junit.extensions.TestSetup;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestResult;
import junit.framework.TestSuite;

import com.runwaysdk.dataaccess.database.DuplicateDataDatabaseException;

import dss.vector.solutions.TestFixture;
import dss.vector.solutions.ontology.Term;

public class InsecticideTest extends TestCase
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

  private static Term ingredient  = null;

  private static Term ingredient2 = null;
  
  private static Term unit = null;

  public static Test suite()
  {
    TestSuite suite = new TestSuite();
    suite.addTestSuite(InsecticideTest.class);

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
    ingredient.delete();
    ingredient2.delete();
    unit.delete();
  }

  protected static void classSetUp()
  {
    ingredient = TestFixture.createRandomTerm();
    ingredient2 = TestFixture.createRandomTerm();
    unit = TestFixture.createRandomTerm();
  }

  public void testCreateInsecticide()
  {
    Insecticide insecticide = TestFixture.createInsecticide();

    try
    {
      Insecticide test = Insecticide.get(insecticide.getId());

      assertEquals(insecticide.getActiveIngredient().getId(), test.getActiveIngredient().getId());
      assertEquals(insecticide.getAmount(), test.getAmount());
      assertEquals(insecticide.getUnits().getId(), test.getUnits().getId());
      
      
    }
    finally
    {
      TestFixture.delete(insecticide);
    }
  }

  public void testDuplicateInsecticide()
  {
    Insecticide insecticide = TestFixture.createInsecticide();

    try
    {
      Insecticide insecticide2 = TestFixture.createInsecticide();
      
      TestFixture.delete(insecticide2);

      fail("Able to create duplicate insecticides");
    }
    catch (DuplicateDataDatabaseException e)
    {
      // This is expected
    }
    finally
    {
      TestFixture.delete(insecticide);
    }
  }

  public void testDifferentAmounts()
  {
    Insecticide insecticide = new Insecticide();
    insecticide.setActiveIngredient(ingredient);
    insecticide.setAmount(new Double(40.0));
    insecticide.setUnits(unit);
    insecticide.apply();

    Insecticide insecticide2 = new Insecticide();
    insecticide2.setActiveIngredient(ingredient);
    insecticide2.setAmount(new Double(20.0));
    insecticide2.setUnits(unit);
    insecticide2.apply();

    try
    {
      Insecticide test = Insecticide.get(insecticide.getId());

      assertEquals(insecticide.getActiveIngredient().getId(), test.getActiveIngredient().getId());
      assertEquals(insecticide.getAmount(), test.getAmount());
      assertEquals(insecticide.getUnits().getId(), test.getUnits().getId());
      
      

      Insecticide test2 = Insecticide.get(insecticide2.getId());

      assertEquals(insecticide2.getActiveIngredient().getId(), test2.getActiveIngredient().getId());
      assertEquals(insecticide2.getAmount(), test2.getAmount());
      assertEquals(insecticide2.getUnits().getId(), test2.getUnits().getId());
      
      
    }
    finally
    {
      insecticide.delete();
      insecticide2.delete();
    }
  }

  public void testDifferentUnits()
  {
    Insecticide insecticide = new Insecticide();
    insecticide.setActiveIngredient(ingredient);
    insecticide.setAmount(new Double(40.0));
    insecticide.setUnits(unit);
    insecticide.apply();

    Insecticide insecticide2 = new Insecticide();
    insecticide2.setActiveIngredient(ingredient);
    insecticide2.setAmount(new Double(40.0));
    insecticide2.setUnits(unit);
    insecticide2.apply();

    try
    {
      Insecticide test = Insecticide.get(insecticide.getId());

      assertEquals(insecticide.getActiveIngredient().getId(), test.getActiveIngredient().getId());
      assertEquals(insecticide.getAmount(), test.getAmount());
      assertEquals(insecticide.getUnits().getId(), test.getUnits().getId());
      
      

      Insecticide test2 = Insecticide.get(insecticide2.getId());

      assertEquals(insecticide2.getActiveIngredient().getId(), test2.getActiveIngredient().getId());
      assertEquals(insecticide2.getAmount(), test2.getAmount());
      assertEquals(insecticide2.getUnits().getId(), test2.getUnits().getId());
      
      
    }
    finally
    {
      insecticide.delete();
      insecticide2.delete();
    }
  }

  public void testDifferentActiveIngredients()
  {
    Insecticide insecticide = new Insecticide();
    insecticide.setActiveIngredient(ingredient);
    insecticide.setAmount(new Double(40.0));
    insecticide.setUnits(unit);
    insecticide.apply();

    Insecticide insecticide2 = new Insecticide();
    insecticide2.setActiveIngredient(ingredient2);
    insecticide2.setAmount(new Double(40.0));
    insecticide2.setUnits(unit);
    insecticide2.apply();

    try
    {
      Insecticide test = Insecticide.get(insecticide.getId());

      assertEquals(insecticide.getActiveIngredient().getId(), test.getActiveIngredient().getId());
      assertEquals(insecticide.getAmount(), test.getAmount());
      assertEquals(insecticide.getUnits().getId(), test.getUnits().getId());

      Insecticide test2 = Insecticide.get(insecticide2.getId());

      assertEquals(insecticide2.getActiveIngredient().getId(), test2.getActiveIngredient().getId());
      assertEquals(insecticide2.getAmount(), test2.getAmount());
      assertEquals(insecticide2.getUnits().getId(), test2.getUnits().getId());      
    }
    finally
    {
      insecticide.delete();
      insecticide2.delete();
    }
  }
}
