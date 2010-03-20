package dss.vector.solutions.irs;

import java.math.BigDecimal;

import junit.extensions.TestSetup;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestResult;
import junit.framework.TestSuite;

import com.runwaysdk.dataaccess.cache.DataNotFoundException;

import dss.vector.solutions.TestConstants;
import dss.vector.solutions.TestFixture;
import dss.vector.solutions.ontology.Term;

public class InsecticideBrandTest extends TestCase
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

  private static Term activeIngredient = null;

  public static Test suite()
  {
    TestSuite suite = new TestSuite();
    suite.addTestSuite(InsecticideBrandTest.class);

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
    activeIngredient.delete();
  }

  protected static void classSetUp()
  {
    activeIngredient = TestFixture.createRandomTerm();
  }

  public void testCreate()
  {
    BigDecimal weight = new BigDecimal("4.50");
    Integer refill = new Integer(20);

    InsecticideBrand brand = new InsecticideBrand();
    brand.setActiveIngredient(activeIngredient);
    brand.setAmount(57);

    brand.setWeight(weight);
    brand.setSachetsPerRefill(refill);
    brand.setBrandName(TestConstants.BRAND_NAME);
    brand.apply();

    try
    {
      InsecticideBrand test = InsecticideBrand.get(brand.getId());

      assertEquals(activeIngredient.getId(), test.getActiveIngredient().getId());
      assertEquals(brand.getAmount(), test.getAmount());

      assertEquals(weight, test.getWeight());
      assertEquals(refill, test.getSachetsPerRefill());
      assertEquals(brand.getBrandName(), test.getBrandName());
    }
    finally
    {
      brand.delete();
    }
  }

  public void testUpdate()
  {
    BigDecimal weight = new BigDecimal("4.50");
    Integer refill = new Integer(20);

    InsecticideBrand brand = new InsecticideBrand();
    brand.setActiveIngredient(activeIngredient);
    brand.setAmount(57);

    brand.setWeight(weight);
    brand.setSachetsPerRefill(refill);
    brand.setBrandName(TestConstants.BRAND_NAME);
    brand.apply();

    InsecticideBrand edit = InsecticideBrand.lock(brand.getId());
    edit.setAmount(35);
    edit.apply();

    try
    {
      InsecticideBrand test = InsecticideBrand.get(brand.getId());

      assertEquals(activeIngredient.getId(), test.getActiveIngredient().getId());
      assertEquals(edit.getAmount(), test.getAmount());

      assertEquals(weight, test.getWeight());
      assertEquals(refill, test.getSachetsPerRefill());
      assertEquals(brand.getBrandName(), test.getBrandName());
    }
    finally
    {
      edit.delete();
    }
  }

  public void testCreateView()
  {
    BigDecimal weight = new BigDecimal("4.50");
    Integer refill = new Integer(20);

    InsecticideBrandView brand = new InsecticideBrandView();
    brand.setActiveIngredient(activeIngredient);
    brand.setAmount(57);

    brand.setWeight(weight);
    brand.setSachetsPerRefill(refill);
    brand.setBrandName(TestConstants.BRAND_NAME);
    brand.apply();

    try
    {
      InsecticideBrandView test = InsecticideBrand.getView(brand.getInsecticdeId());

      assertEquals(activeIngredient.getId(), test.getActiveIngredient().getId());
      assertEquals(brand.getAmount(), test.getAmount());
      assertEquals(weight, test.getWeight());
      assertEquals(refill, test.getSachetsPerRefill());
      assertEquals(brand.getBrandName(), test.getBrandName());
    }
    finally
    {
      brand.deleteConcrete();
    }
  }

  public void testUpdateView()
  {
    BigDecimal weight = new BigDecimal("4.50");
    Integer refill = new Integer(20);

    InsecticideBrandView brand = new InsecticideBrandView();
    brand.setActiveIngredient(activeIngredient);
    brand.setAmount(57);

    brand.setWeight(weight);
    brand.setSachetsPerRefill(refill);
    brand.setBrandName(TestConstants.BRAND_NAME);
    brand.apply();

    InsecticideBrandView edit = InsecticideBrand.lockView(brand.getInsecticdeId());
    edit.setAmount(35);
    edit.setEnabled(false);
    edit.apply();

    try
    {
      InsecticideBrandView test = InsecticideBrand.getView(brand.getInsecticdeId());

      assertEquals(activeIngredient.getId(), test.getActiveIngredient().getId());
      assertEquals(edit.getAmount(), test.getAmount());
      assertEquals(new Boolean(false), test.getEnabled());

      assertEquals(weight, test.getWeight());
      assertEquals(refill, test.getSachetsPerRefill());
      assertEquals(edit.getBrandName(), test.getBrandName());
    }
    finally
    {
      edit.deleteConcrete();
    }
  }

  public void testDeleteView()
  {
    BigDecimal weight = new BigDecimal("4.50");
    Integer refill = new Integer(20);

    InsecticideBrandView brand = new InsecticideBrandView();
    brand.setActiveIngredient(activeIngredient);
    brand.setAmount(57);

    brand.setWeight(weight);
    brand.setSachetsPerRefill(refill);
    brand.setBrandName(TestConstants.BRAND_NAME);
    brand.apply();

    String id = brand.getInsecticdeId();

    brand.deleteConcrete();

    try
    {
      InsecticideBrand.get(id);

      fail("Failed to delete the concrete representation of a view");
    }
    catch (DataNotFoundException e)
    {
      // This is expected
    }
  }

  public void testGetAll()
  {
    BigDecimal weight = new BigDecimal("4.50");
    Integer refill = new Integer(20);

    InsecticideBrandView brand = new InsecticideBrandView();
    brand.setActiveIngredient(activeIngredient);
    brand.setAmount(57);

    brand.setWeight(weight);
    brand.setSachetsPerRefill(refill);
    brand.setBrandName(TestConstants.BRAND_NAME);
    brand.apply();

    InsecticideBrandView brand2 = new InsecticideBrandView();
    brand2.setActiveIngredient(activeIngredient);
    brand2.setAmount(57);

    brand2.setWeight(weight);
    brand2.setSachetsPerRefill(refill);
    brand2.setBrandName(TestConstants.BRAND_NAME_2);

    brand2.apply();

    try
    {
      InsecticideBrandView[] all = InsecticideBrandView.getAll();

      assertEquals(2, all.length);
    }
    finally
    {
      brand.deleteConcrete();
      brand2.deleteConcrete();
    }
  }

  public void testInactiveGetAllActive()
  {
    BigDecimal weight = new BigDecimal("4.50");
    Integer refill = new Integer(20);

    InsecticideBrandView brand = new InsecticideBrandView();
    brand.setActiveIngredient(activeIngredient);
    brand.setAmount(57);

    brand.setWeight(weight);
    brand.setSachetsPerRefill(refill);
    brand.setBrandName(TestConstants.BRAND_NAME);
    brand.apply();

    InsecticideBrandView brand2 = new InsecticideBrandView();
    brand2.setActiveIngredient(activeIngredient);
    brand2.setAmount(57);

    brand2.setWeight(weight);
    brand2.setSachetsPerRefill(refill);
    brand2.setEnabled(false);
    brand2.setBrandName(TestConstants.BRAND_NAME_2);
    brand2.apply();

    try
    {
      InsecticideBrandView[] all = InsecticideBrandView.getAllActive();

      assertEquals(1, all.length);
    }
    finally
    {
      brand.deleteConcrete();
      brand2.deleteConcrete();
    }
  }

  public void testInactiveGetAll()
  {
    BigDecimal weight = new BigDecimal("4.50");
    Integer refill = new Integer(20);

    InsecticideBrandView brand = new InsecticideBrandView();
    brand.setActiveIngredient(activeIngredient);
    brand.setAmount(57);

    brand.setWeight(weight);
    brand.setSachetsPerRefill(refill);
    brand.setBrandName(TestConstants.BRAND_NAME);
    brand.apply();

    InsecticideBrandView brand2 = new InsecticideBrandView();
    brand2.setActiveIngredient(activeIngredient);
    brand2.setAmount(57);

    brand2.setWeight(weight);
    brand2.setSachetsPerRefill(refill);
    brand2.setEnabled(false);
    brand2.setBrandName(TestConstants.BRAND_NAME_2);
    brand2.apply();

    try
    {
      InsecticideBrandView[] all = InsecticideBrandView.getAll();

      assertEquals(2, all.length);
    }
    finally
    {
      brand.deleteConcrete();
      brand2.deleteConcrete();
    }
  }

  public void testApplyAll()
  {
    BigDecimal weight = new BigDecimal("4.50");
    Integer refill = new Integer(20);

    InsecticideBrandView brand = new InsecticideBrandView();
    brand.setActiveIngredient(activeIngredient);
    brand.setAmount(57);
    brand.setWeight(weight);
    brand.setSachetsPerRefill(refill);
    brand.setBrandName(TestConstants.BRAND_NAME);

    InsecticideBrandView brand2 = new InsecticideBrandView();
    brand2.setActiveIngredient(activeIngredient);
    brand2.setAmount(23);
    brand2.setWeight(weight);
    brand2.setSachetsPerRefill(refill);
    brand2.setBrandName(TestConstants.BRAND_NAME_2);

    InsecticideBrandView[] array = new InsecticideBrandView[] { brand, brand2 };
    InsecticideBrandView[] applied = InsecticideBrandView.applyAll(array);

    try
    {
      assertEquals(array.length, applied.length);

      for (int i = 0; i < array.length; i++)
      {
        InsecticideBrandView test = InsecticideBrand.getView(applied[i].getInsecticdeId());

        assertEquals(activeIngredient.getId(), test.getActiveIngredient().getId());
        assertEquals(applied[i].getAmount(), test.getAmount());

        assertEquals(weight, test.getWeight());
        assertEquals(refill, test.getSachetsPerRefill());
        assertEquals(applied[i].getBrandName(), test.getBrandName());
      }
    }
    finally
    {
      for (InsecticideBrandView view : applied)
      {
        view.deleteConcrete();
      }
    }
  }
}
