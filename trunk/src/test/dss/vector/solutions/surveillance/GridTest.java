package dss.vector.solutions.surveillance;

import junit.extensions.TestSetup;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class GridTest extends TestCase
{

  public static Test suite()
  {
    TestSuite suite = new TestSuite();
    suite.addTestSuite(GridTest.class);

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
  }
  
  public void testGetAll()
  {
    assertEquals(8, TreatmentGrid.getAll().length);
    assertEquals(4, ReferralGrid.getAll().length);
    assertEquals(5, TreatmentMethodGrid.getAll().length);
    assertEquals(4, DiagnosticGrid.getAll().length);    
  }
  
  public void testCreateDeactivatedTreatmentOption()
  {
    int before = TreatmentGrid.getAll().length;
    
    TreatmentGrid newOption = new TreatmentGrid();
    newOption.setActive(false);
    newOption.setOptionName("testOption");
    newOption.setDisplayLabel("Test Option");
    newOption.apply();
    
    try
    {
      TreatmentGrid test = TreatmentGrid.get(newOption.getId());
      
      assertEquals(newOption.getActive(), test.getActive());
      assertEquals(newOption.getOptionName(), test.getOptionName());
      assertEquals(newOption.getDisplayLabel(), test.getDisplayLabel());
      assertEquals(before, TreatmentGrid.getAll().length);
    }
    finally
    {
      newOption.delete();
    }
  }
  
  public void testCreateNewTreatmentOption()
  {
    int before = TreatmentGrid.getAll().length;
    
    TreatmentGrid newOption = new TreatmentGrid();
    newOption.setActive(true);
    newOption.setOptionName("testOption");
    newOption.setDisplayLabel("Test Option");
    newOption.apply();
    
    try
    {
      TreatmentGrid test = TreatmentGrid.get(newOption.getId());
      
      assertEquals(newOption.getActive(), test.getActive());
      assertEquals(newOption.getOptionName(), test.getOptionName());
      assertEquals(newOption.getDisplayLabel(), test.getDisplayLabel());
      assertEquals(before + 1, TreatmentGrid.getAll().length);
    }
    finally
    {
      newOption.delete();
    }
  }
  
  public void testCreateDeactivatedReferralOption()
  {
    int before = ReferralGrid.getAll().length;
    
    ReferralGrid newOption = new ReferralGrid();
    newOption.setActive(false);
    newOption.setOptionName("testOption");
    newOption.setDisplayLabel("Test Option");
    newOption.apply();
    
    try
    {
      ReferralGrid test = ReferralGrid.get(newOption.getId());
      
      assertEquals(newOption.getActive(), test.getActive());
      assertEquals(newOption.getOptionName(), test.getOptionName());
      assertEquals(newOption.getDisplayLabel(), test.getDisplayLabel());
      assertEquals(before, ReferralGrid.getAll().length);
    }
    finally
    {
      newOption.delete();
    }
  }
  
  public void testCreateNewReferralOption()
  {
    int before = ReferralGrid.getAll().length;
    
    ReferralGrid newOption = new ReferralGrid();
    newOption.setActive(true);
    newOption.setOptionName("testOption");
    newOption.setDisplayLabel("Test Option");
    newOption.apply();
    
    try
    {
      ReferralGrid test = ReferralGrid.get(newOption.getId());
      
      assertEquals(newOption.getActive(), test.getActive());
      assertEquals(newOption.getOptionName(), test.getOptionName());
      assertEquals(newOption.getDisplayLabel(), test.getDisplayLabel());
      assertEquals(before + 1, ReferralGrid.getAll().length);
    }
    finally
    {
      newOption.delete();
    }
  }
  
  public void testCreateNewTreatmentMethodOption()
  {
    int before = TreatmentMethodGrid.getAll().length;
    
    TreatmentMethodGrid newOption = new TreatmentMethodGrid();
    newOption.setActive(true);
    newOption.setOptionName("testOption");
    newOption.setDisplayLabel("Test Option");
    newOption.apply();
    
    try
    {
      TreatmentMethodGrid test = TreatmentMethodGrid.get(newOption.getId());
      
      assertEquals(newOption.getActive(), test.getActive());
      assertEquals(newOption.getOptionName(), test.getOptionName());
      assertEquals(newOption.getDisplayLabel(), test.getDisplayLabel());
      assertEquals(before + 1, TreatmentMethodGrid.getAll().length);
    }
    finally
    {
      newOption.delete();
    }
  }
  
  public void testCreateDeactivatedTreatmentMethodOption()
  {
    int before = TreatmentMethodGrid.getAll().length;
    
    TreatmentMethodGrid newOption = new TreatmentMethodGrid();
    newOption.setActive(false);
    newOption.setOptionName("testOption");
    newOption.setDisplayLabel("Test Option");
    newOption.apply();
    
    try
    {
      TreatmentMethodGrid test = TreatmentMethodGrid.get(newOption.getId());
      
      assertEquals(newOption.getActive(), test.getActive());
      assertEquals(newOption.getOptionName(), test.getOptionName());
      assertEquals(newOption.getDisplayLabel(), test.getDisplayLabel());
      assertEquals(before, TreatmentMethodGrid.getAll().length);
    }
    finally
    {
      newOption.delete();
    }
  }
  
  public void testCreateNewDiagnosticOption()
  {
    int before = DiagnosticGrid.getAll().length;
    
    DiagnosticGrid newOption = new DiagnosticGrid();
    newOption.setActive(true);
    newOption.setOptionName("testOption");
    newOption.setDisplayLabel("Test Option");
    newOption.apply();

    try
    {
      DiagnosticGrid test = DiagnosticGrid.get(newOption.getId());
      
      assertEquals(newOption.getActive(), test.getActive());
      assertEquals(newOption.getOptionName(), test.getOptionName());
      assertEquals(newOption.getDisplayLabel(), test.getDisplayLabel());
      assertEquals(before + 1, DiagnosticGrid.getAll().length);
    }
    finally
    {
      newOption.delete();
    }
  }
  
  public void testCreateDeactivatedDiagnosticOption()
  {
    int before = DiagnosticGrid.getAll().length;
    
    DiagnosticGrid newOption = new DiagnosticGrid();
    newOption.setActive(false);
    newOption.setOptionName("testOption");
    newOption.setDisplayLabel("Test Option");
    newOption.apply();

    try
    {
      DiagnosticGrid test = DiagnosticGrid.get(newOption.getId());
      
      assertEquals(newOption.getActive(), test.getActive());
      assertEquals(newOption.getOptionName(), test.getOptionName());
      assertEquals(newOption.getDisplayLabel(), test.getDisplayLabel());
      assertEquals(before, DiagnosticGrid.getAll().length);
    }
    finally
    {
      newOption.delete();
    }    
  }

  public void testDeactivateGridOption()
  {
    DiagnosticGrid[] all = DiagnosticGrid.getAll();    
    int before = all.length;
    
    all[0].setActive(false);
    all[0].apply();

    try
    {
      assertEquals(before - 1, DiagnosticGrid.getAll().length);
    }
    finally
    {
      all[0].setActive(true);
      all[0].apply();
    }        
  }
}
