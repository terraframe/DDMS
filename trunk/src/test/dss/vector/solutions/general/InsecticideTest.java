package dss.vector.solutions.general;

import com.terraframe.mojo.dataaccess.database.DuplicateDataDatabaseException;

import junit.extensions.TestSetup;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestResult;
import junit.framework.TestSuite;
import dss.vector.solutions.entomology.assay.Unit;
import dss.vector.solutions.mo.ActiveIngredient;

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

  private static ActiveIngredient ingredient = null;
  private static ActiveIngredient ingredient2 = null;

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
  }

  protected static void classSetUp()
  {
    ActiveIngredient[] ingredients = ActiveIngredient.getAll();

    ingredient = ingredients[0];
    ingredient2 = ingredients[1];
  }
  
  public void testCreateInsecticide()
  {
    Insecticide insecticide = new Insecticide();
    insecticide.setActiveIngredient(ingredient);
    insecticide.setAmount(new Double(40.0));
    insecticide.addUnits(Unit.PERCENT);
    insecticide.apply();
    
    try
    {
      Insecticide test = Insecticide.get(insecticide.getId());
      
      assertEquals(insecticide.getActiveIngredient().getId(), test.getActiveIngredient().getId());
      assertEquals(insecticide.getAmount(), test.getAmount());
      assertEquals(insecticide.getUnits().size(), test.getUnits().size());
      assertEquals(1, test.getUnits().size());
      assertEquals(insecticide.getUnits().get(0).getEnumName(), test.getUnits().get(0).getEnumName());
    }
    finally
    {
      insecticide.delete();
    }
  }
  
  public void testDuplicateInsecticide()
  {
    Insecticide insecticide = new Insecticide();
    insecticide.setActiveIngredient(ingredient);
    insecticide.setAmount(new Double(40.0));
    insecticide.addUnits(Unit.PERCENT);
    insecticide.apply();
    
    try
    {
      Insecticide insecticide2 = new Insecticide();
      insecticide2.setActiveIngredient(ingredient);
      insecticide2.setAmount(new Double(40.0));
      insecticide2.addUnits(Unit.PERCENT);
      insecticide2.apply();      
      
      fail("Able to create duplicate insecticides");
    }
    catch(DuplicateDataDatabaseException e)
    {
      //This is expected
    }
    finally
    {
      insecticide.delete();
    }    
  }
  
  public void testDifferentAmounts()
  {
    Insecticide insecticide = new Insecticide();
    insecticide.setActiveIngredient(ingredient);
    insecticide.setAmount(new Double(40.0));
    insecticide.addUnits(Unit.PERCENT);
    insecticide.apply();

    Insecticide insecticide2 = new Insecticide();
    insecticide2.setActiveIngredient(ingredient);
    insecticide2.setAmount(new Double(20.0));
    insecticide2.addUnits(Unit.PERCENT);
    insecticide2.apply();      
    
    try
    {
      Insecticide test = Insecticide.get(insecticide.getId());
      
      assertEquals(insecticide.getActiveIngredient().getId(), test.getActiveIngredient().getId());
      assertEquals(insecticide.getAmount(), test.getAmount());
      assertEquals(insecticide.getUnits().size(), test.getUnits().size());
      assertEquals(1, test.getUnits().size());
      assertEquals(insecticide.getUnits().get(0).getEnumName(), test.getUnits().get(0).getEnumName());

      Insecticide test2 = Insecticide.get(insecticide2.getId());
      
      assertEquals(insecticide2.getActiveIngredient().getId(), test2.getActiveIngredient().getId());
      assertEquals(insecticide2.getAmount(), test2.getAmount());
      assertEquals(insecticide2.getUnits().size(), test2.getUnits().size());
      assertEquals(1, test2.getUnits().size());
      assertEquals(insecticide2.getUnits().get(0).getEnumName(), test2.getUnits().get(0).getEnumName());
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
    insecticide.addUnits(Unit.PERCENT);
    insecticide.apply();

    Insecticide insecticide2 = new Insecticide();
    insecticide2.setActiveIngredient(ingredient);
    insecticide2.setAmount(new Double(40.0));
    insecticide2.addUnits(Unit.MILLIGRAM_PER_LITER);
    insecticide2.apply();      
    
    try
    {
      Insecticide test = Insecticide.get(insecticide.getId());
      
      assertEquals(insecticide.getActiveIngredient().getId(), test.getActiveIngredient().getId());
      assertEquals(insecticide.getAmount(), test.getAmount());
      assertEquals(insecticide.getUnits().size(), test.getUnits().size());
      assertEquals(1, test.getUnits().size());
      assertEquals(insecticide.getUnits().get(0).getEnumName(), test.getUnits().get(0).getEnumName());

      Insecticide test2 = Insecticide.get(insecticide2.getId());
      
      assertEquals(insecticide2.getActiveIngredient().getId(), test2.getActiveIngredient().getId());
      assertEquals(insecticide2.getAmount(), test2.getAmount());
      assertEquals(insecticide2.getUnits().size(), test2.getUnits().size());
      assertEquals(1, test2.getUnits().size());
      assertEquals(insecticide2.getUnits().get(0).getEnumName(), test2.getUnits().get(0).getEnumName());
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
    insecticide.addUnits(Unit.PERCENT);
    insecticide.apply();

    Insecticide insecticide2 = new Insecticide();
    insecticide2.setActiveIngredient(ingredient2);
    insecticide2.setAmount(new Double(40.0));
    insecticide2.addUnits(Unit.PERCENT);
    insecticide2.apply();      
    
    try
    {
      Insecticide test = Insecticide.get(insecticide.getId());
      
      assertEquals(insecticide.getActiveIngredient().getId(), test.getActiveIngredient().getId());
      assertEquals(insecticide.getAmount(), test.getAmount());
      assertEquals(insecticide.getUnits().size(), test.getUnits().size());
      assertEquals(1, test.getUnits().size());
      assertEquals(insecticide.getUnits().get(0).getEnumName(), test.getUnits().get(0).getEnumName());

      Insecticide test2 = Insecticide.get(insecticide2.getId());
      
      assertEquals(insecticide2.getActiveIngredient().getId(), test2.getActiveIngredient().getId());
      assertEquals(insecticide2.getAmount(), test2.getAmount());
      assertEquals(insecticide2.getUnits().size(), test2.getUnits().size());
      assertEquals(1, test2.getUnits().size());
      assertEquals(insecticide2.getUnits().get(0).getEnumName(), test2.getUnits().get(0).getEnumName());
    }
    finally
    {
      insecticide.delete();
      insecticide2.delete();
    }    
  }
}
