package dss.vector.solutions.irs;

import java.math.BigDecimal;

import com.terraframe.mojo.dataaccess.cache.DataNotFoundException;

import junit.extensions.TestSetup;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestResult;
import junit.framework.TestSuite;

public class NozzleTest extends TestCase
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

  public static Test suite()
  {
    TestSuite suite = new TestSuite();
    suite.addTestSuite(NozzleTest.class);

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

  public void testCreate()
  {
    BigDecimal ratio = new BigDecimal("0.300");

    Nozzle nozzle = new Nozzle();
    nozzle.setDisplayLabel("test Nozzle");
    nozzle.setEnabled(true);
    nozzle.setRatio(ratio);
    nozzle.apply();

    try
    {
      Nozzle test = Nozzle.get(nozzle.getId());

      assertNotNull(test);
      assertEquals(nozzle.getDisplayLabel(), test.getDisplayLabel());
      assertEquals(nozzle.getEnabled(), test.getEnabled());
      assertEquals(nozzle.getRatio(), test.getRatio());

    }
    finally
    {
      nozzle.delete();
    }
  }

  public void testUpdate()
  {
    BigDecimal ratio = new BigDecimal("0.30");

    Nozzle nozzle = new Nozzle();
    nozzle.setDisplayLabel("test Nozzle");
    nozzle.setEnabled(true);
    nozzle.setRatio(ratio);
    nozzle.apply();

    Nozzle edit = Nozzle.lock(nozzle.getId());
    edit.setDisplayLabel("Nozzle Label");
    edit.apply();

    try
    {
      Nozzle test = Nozzle.get(nozzle.getId());

      assertNotNull(test);
      assertEquals(edit.getDisplayLabel(), test.getDisplayLabel());
      assertEquals(edit.getEnabled(), test.getEnabled());
      assertEquals(edit.getRatio(), test.getRatio());

    }
    finally
    {
      edit.delete();
    }
  }

  public void testCreateView()
  {
    BigDecimal ratio = new BigDecimal("0.300");

    NozzleView nozzle = new NozzleView();
    nozzle.setDisplayLabel("test NozzleView");
    nozzle.setEnabled(true);
    nozzle.setRatio(ratio);
    nozzle.apply();

    try
    {
      NozzleView test = Nozzle.getView(nozzle.getNozzleId());

      assertNotNull(test);
      assertEquals(nozzle.getDisplayLabel(), test.getDisplayLabel());
      assertEquals(nozzle.getEnabled(), test.getEnabled());
      assertEquals(nozzle.getRatio(), test.getRatio());
    }
    finally
    {
      nozzle.deleteConcrete();
    }
  }

  public void testUpdateView()
  {
    BigDecimal ratio = new BigDecimal("0.30");

    NozzleView nozzle = new NozzleView();
    nozzle.setDisplayLabel("test NozzleView");
    nozzle.setEnabled(true);
    nozzle.setRatio(ratio);
    nozzle.apply();

    NozzleView edit = Nozzle.lockView(nozzle.getNozzleId());
    edit.setDisplayLabel("NozzleView Label");
    edit.apply();

    try
    {
      NozzleView test = Nozzle.getView(nozzle.getNozzleId());

      assertNotNull(test);
      assertEquals(edit.getDisplayLabel(), test.getDisplayLabel());
      assertEquals(edit.getEnabled(), test.getEnabled());
      assertEquals(edit.getRatio(), test.getRatio());

    }
    finally
    {
      edit.deleteConcrete();
    }
  }

  public void testDeleteView()
  {
    BigDecimal ratio = new BigDecimal("0.30");

    NozzleView nozzle = new NozzleView();
    nozzle.setDisplayLabel("test NozzleView");
    nozzle.setEnabled(true);
    nozzle.setRatio(ratio);
    nozzle.apply();

    String id = nozzle.getNozzleId();

    nozzle.deleteConcrete();

    try
    {
      Nozzle.get(id);

      fail("Unable to delete a nozzle view");
    }
    catch (DataNotFoundException e)
    {
      // This is expected
    }

  }

  public void testGetAll()
  {
    BigDecimal ratio = new BigDecimal("0.30");

    NozzleView nozzle = new NozzleView();
    nozzle.setDisplayLabel("test NozzleView");
    nozzle.setEnabled(true);
    nozzle.setRatio(ratio);
    nozzle.apply();

    NozzleView nozzle2 = new NozzleView();
    nozzle2.setDisplayLabel("test NozzleView 2");
    nozzle2.setEnabled(true);
    nozzle2.setRatio(ratio);
    nozzle2.apply();

    try
    {
      NozzleView[] all = NozzleView.getAll();

      assertEquals(2, all.length);
    }
    finally
    {
      nozzle.deleteConcrete();
      nozzle2.deleteConcrete();
    }
  }
  
  public void testInactiveGetAll()
  {
    BigDecimal ratio = new BigDecimal("0.30");
    
    NozzleView nozzle = new NozzleView();
    nozzle.setDisplayLabel("test NozzleView");
    nozzle.setEnabled(true);
    nozzle.setRatio(ratio);
    nozzle.apply();
    
    NozzleView nozzle2 = new NozzleView();
    nozzle2.setDisplayLabel("test NozzleView 2");
    nozzle2.setEnabled(false);
    nozzle2.setRatio(ratio);
    nozzle2.apply();
    
    try
    {
      NozzleView[] all = NozzleView.getAll();
      
      assertEquals(2, all.length);
    }
    finally
    {
      nozzle.deleteConcrete();
      nozzle2.deleteConcrete();
    }
  }

  public void testInactiveGetAllActive()
  {
    BigDecimal ratio = new BigDecimal("0.30");

    NozzleView nozzle = new NozzleView();
    nozzle.setDisplayLabel("test NozzleView");
    nozzle.setEnabled(true);
    nozzle.setRatio(ratio);
    nozzle.apply();

    NozzleView nozzle2 = new NozzleView();
    nozzle2.setDisplayLabel("test NozzleView 2");
    nozzle2.setEnabled(false);
    nozzle2.setRatio(ratio);
    nozzle2.apply();

    try
    {
      NozzleView[] all = NozzleView.getAllActive();

      assertEquals(1, all.length);
    }
    finally
    {
      nozzle.deleteConcrete();
      nozzle2.deleteConcrete();
    }
  }

  public void testApplyAll()
  {
    BigDecimal ratio = new BigDecimal("0.300");

    NozzleView nozzle = new NozzleView();
    nozzle.setDisplayLabel("test NozzleView");
    nozzle.setEnabled(true);
    nozzle.setRatio(ratio);

    NozzleView nozzle2 = new NozzleView();
    nozzle2.setDisplayLabel("test NozzleView 2");
    nozzle2.setEnabled(true);
    nozzle2.setRatio(ratio);

    NozzleView[] array = new NozzleView[] { nozzle, nozzle2 };

    NozzleView[] applied = NozzleView.applyAll(array);

    try
    {
      assertEquals(array.length, applied.length);

      for (int i = 0; i < applied.length; i++)
      {
        NozzleView test = Nozzle.getView(applied[i].getNozzleId());

        assertNotNull(test);
        assertEquals(applied[i].getDisplayLabel(), test.getDisplayLabel());
        assertEquals(applied[i].getEnabled(), test.getEnabled());
        assertEquals(applied[i].getRatio(), test.getRatio());
      }
    }
    finally
    {
      for(NozzleView view : applied)
      {
        view.deleteConcrete();
      }
    }

  }

}
