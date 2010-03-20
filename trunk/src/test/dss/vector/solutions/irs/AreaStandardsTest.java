package dss.vector.solutions.irs;

import com.runwaysdk.dataaccess.cache.DataNotFoundException;

import junit.extensions.TestSetup;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestResult;
import junit.framework.TestSuite;

public class AreaStandardsTest extends TestCase
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
    suite.addTestSuite(AreaStandardsTest.class);

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
    Float value = new Float(343.22);

    AreaStandards standards = new AreaStandards();
    standards.setRoom(value);
    standards.setStructureArea(value);
    standards.setHousehold(value);
    standards.addTargetUnit(TargetUnit.HOUSEHOLD);
    standards.apply();

    try
    {
      AreaStandards test = AreaStandards.get(standards.getId());

      assertNotNull(test);
      assertEquals(standards.getRoom(), test.getRoom());
      assertEquals(standards.getStructureArea(), test.getStructureArea());
      assertEquals(standards.getHousehold(), test.getHousehold());
      assertEquals(1, test.getTargetUnit().size());
      assertEquals(TargetUnit.HOUSEHOLD, test.getTargetUnit().get(0));
    }
    finally
    {
      standards.delete();
    }
  }

  public void testUpdate()
  {
    Float value = new Float(343.22);
    Float value2 = new Float(23.21);

    AreaStandards standards = new AreaStandards();
    standards.setRoom(value);
    standards.setStructureArea(value);
    standards.setHousehold(value);
    standards.addTargetUnit(TargetUnit.HOUSEHOLD);
    standards.apply();

    AreaStandards edit = AreaStandards.lock(standards.getId());
    edit.setRoom(value2);
    edit.setStructureArea(value2);
    edit.setHousehold(value2);
    edit.apply();

    try
    {
      AreaStandards test = AreaStandards.get(standards.getId());

      assertNotNull(test);
      assertEquals(edit.getRoom(), test.getRoom());
      assertEquals(edit.getStructureArea(), test.getStructureArea());
      assertEquals(edit.getHousehold(), test.getHousehold());
      assertEquals(1, test.getTargetUnit().size());
      assertEquals(TargetUnit.HOUSEHOLD, test.getTargetUnit().get(0));
    }
    finally
    {
      edit.delete();
    }
  }

  public void testCreateView()
  {
    Float value = new Float(343.22);

    AreaStandardsView standards = new AreaStandardsView();
    standards.setRoom(value);
    standards.setStructureArea(value);
    standards.setHousehold(value);
    standards.addTargetUnit(TargetUnit.HOUSEHOLD);    
    standards.apply();

    try
    {
      AreaStandardsView test = AreaStandards.getView(standards.getAreaStandardsId());

      assertNotNull(test);
      assertEquals(standards.getRoom(), test.getRoom());
      assertEquals(standards.getStructureArea(), test.getStructureArea());
      assertEquals(standards.getHousehold(), test.getHousehold());
      assertEquals(1, test.getTargetUnit().size());
      assertEquals(TargetUnit.HOUSEHOLD, test.getTargetUnit().get(0));
    }
    finally
    {
      standards.deleteConcrete();
    }
  }

  public void testUpdateView()
  {
    Float value = new Float(343.22);
    Float value2 = new Float(23.21);

    AreaStandardsView standards = new AreaStandardsView();
    standards.setRoom(value);
    standards.setStructureArea(value);
    standards.setHousehold(value);
    standards.addTargetUnit(TargetUnit.HOUSEHOLD);
    standards.apply();

    AreaStandardsView edit = AreaStandards.lockView(standards.getAreaStandardsId());
    edit.setRoom(value2);
    edit.setStructureArea(value2);
    edit.setHousehold(value2);
    edit.apply();

    try
    {
      AreaStandardsView test = AreaStandards.getView(standards.getAreaStandardsId());

      assertNotNull(test);
      assertEquals(edit.getRoom(), test.getRoom());
      assertEquals(edit.getStructureArea(), test.getStructureArea());
      assertEquals(edit.getHousehold(), test.getHousehold());
      assertEquals(1, test.getTargetUnit().size());
      assertEquals(TargetUnit.HOUSEHOLD, test.getTargetUnit().get(0));
    }
    finally
    {
      edit.deleteConcrete();
    }
  }

  public void testDeleteView()
  {
    Float value = new Float(343.22);

    AreaStandardsView standards = new AreaStandardsView();
    standards.setRoom(value);
    standards.setStructureArea(value);
    standards.setHousehold(value);
    standards.addTargetUnit(TargetUnit.HOUSEHOLD);
    standards.apply();

    String id = standards.getAreaStandardsId();

    standards.deleteConcrete();

    try
    {
      AreaStandards.getView(id);

      fail("Unable to delete the concrete representation of the view");
    }
    catch(DataNotFoundException e)
    {
      //This is expected
    }
  }
}
