package dss.vector.solutions.general;

import java.util.List;

import junit.extensions.TestSetup;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import com.terraframe.mojo.ProblemException;
import com.terraframe.mojo.ProblemIF;
import com.terraframe.mojo.dataaccess.database.DuplicateDataDatabaseException;

import dss.vector.solutions.entomology.assay.Unit;
import dss.vector.solutions.mo.ActiveIngredient;

public class LethalTimePropertyTest extends TestCase
{
  private static Insecticide insecticide;
  
  public static Test suite()
  {
    TestSuite suite = new TestSuite();
    suite.addTestSuite(LethalTimePropertyTest.class);

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
    insecticide.delete();
  }

  protected static void classSetUp()
  {
    ActiveIngredient[] ingredients = ActiveIngredient.getAll();
    
    insecticide = new Insecticide();
    insecticide.setActiveIngredient(ingredients[0]);
    insecticide.setAmount(40);
    insecticide.addUnits(Unit.PERCENT);
    insecticide.apply();
  }
  
  public void testCreateWithDefaultProperty()
  {
    LethalTimeProperty property = new LethalTimeProperty();
    property.setInsecticide(insecticide);
    property.setUpperTime(40);
    property.setLowerTime(20);
    property.apply();
    
    try
    {
      LethalTimeProperty test = LethalTimeProperty.get(property.getId());
      
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
    LethalTimeProperty property = new LethalTimeProperty();
    property.setInsecticide(insecticide);
    property.setUpperTime(40);
    property.setUpperPercent(85);
    property.setLowerTime(20);
    property.setLowerPercent(30);
    property.apply();
    
    try
    {
      LethalTimeProperty test = LethalTimeProperty.get(property.getId());
      
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
    LethalTimeProperty property = new LethalTimeProperty();
    property.setInsecticide(insecticide);
    property.setUpperTime(40);
    property.setLowerTime(20);
    property.apply();
    
    try
    {
      LethalTimeProperty test = LethalTimeProperty.searchByInsecticide(insecticide);

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
      LethalTimeProperty.searchByInsecticide(insecticide);
      
      fail("Able to find a knock down time property with an unknown insecticide");
    }
    catch(UndefinedLethalTimePropertyException e)
    {
      //This is excpected
    }
  }
  
  public void testDuplicateProperty()
  {
    LethalTimeProperty property = new LethalTimeProperty();
    property.setInsecticide(insecticide);
    property.setUpperTime(40);
    property.setUpperPercent(85);
    property.setLowerTime(20);
    property.setLowerPercent(30);
    property.apply();
    
    try
    {
      LethalTimeProperty property2 = new LethalTimeProperty();
      property2.setInsecticide(insecticide);
      property2.setUpperTime(50);
      property2.setLowerTime(30);
      property2.apply();
      
      fail("Able to create a duplicate property");
    }
    catch(DuplicateDataDatabaseException e)
    {
      //This is excepted
    }
    finally
    {
      property.delete();
    }    
  }
  
  public void testBounds()
  {
    LethalTimeProperty property = new LethalTimeProperty();
    property.setInsecticide(insecticide);
    property.setUpperTime(40);
    property.setUpperPercent(99);
    property.setLowerTime(20);
    property.setLowerPercent(1);
    property.apply();
    
    try
    {
      LethalTimeProperty test = LethalTimeProperty.get(property.getId());
      
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
    LethalTimeProperty property = new LethalTimeProperty();
    property.setInsecticide(insecticide);
    property.setUpperTime(40);
    property.setUpperPercent(100);
    property.setLowerTime(20);
    property.setLowerPercent(1);
    property.apply();
    
    try
    {
      LethalTimeProperty test = LethalTimeProperty.get(property.getId());
      
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
    LethalTimeProperty property = new LethalTimeProperty();
    
    try
    {
      property.setInsecticide(insecticide);
      property.setUpperTime(40);
      property.setUpperPercent(105);
      property.setLowerTime(20);
      property.setLowerPercent(1);
      property.apply();
    }
    catch(ProblemException e)
    {
      List<ProblemIF> problems = e.getProblems();
      
      assertNotNull(problems);
      assertEquals(1, problems.size());
      assertTrue(problems.get(0) instanceof InvalidPercentageProblem);
    }
    finally
    {
      if(property != null && property.isAppliedToDB())
      {
        property.delete();
      }
    }    
  }
  
  public void testInvalidLowerPercentage()
  {
    LethalTimeProperty property = new LethalTimeProperty();
    
    try
    {
      property.setInsecticide(insecticide);
      property.setUpperTime(40);
      property.setUpperPercent(78);
      property.setLowerTime(20);
      property.setLowerPercent(106);
      property.apply();
    }
    catch(ProblemException e)
    {
      List<ProblemIF> problems = e.getProblems();
      
      assertNotNull(problems);
      assertEquals(1, problems.size());
      assertTrue(problems.get(0) instanceof InvalidPercentageProblem);
    }
    finally
    {
      if(property != null && property.isAppliedToDB())
      {
        property.delete();
      }
    }    
  }
}
