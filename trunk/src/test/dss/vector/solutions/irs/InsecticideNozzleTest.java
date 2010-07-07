package dss.vector.solutions.irs;

import java.math.BigDecimal;

import junit.extensions.TestSetup;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestResult;
import junit.framework.TestSuite;
import dss.vector.solutions.TestConstants;
import dss.vector.solutions.TestFixture;
import dss.vector.solutions.ontology.Term;

public class InsecticideNozzleTest extends TestCase
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

  private static Nozzle           nozzle;

  private static InsecticideBrand brand;

  private static InsecticideBrand brand2;

  private static Term activeIngredient;

  private static Term productName;

  private static Term productName2;

  private static Term productName3;

  public static Test suite()
  {
    TestSuite suite = new TestSuite();
    suite.addTestSuite(InsecticideNozzleTest.class);

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
    brand.delete();
    brand2.delete();
    nozzle.delete();
    activeIngredient.delete();
    productName.delete();
    productName2.delete();
    productName3.delete();
  }

  protected static void classSetUp()
  {
    BigDecimal ratio = new BigDecimal("0.30");
    BigDecimal weight = new BigDecimal("4.50");
    Integer refill = new Integer(20);

    activeIngredient = TestFixture.createRandomTerm();
    productName = TestFixture.createRandomTerm();
    productName2 = TestFixture.createRandomTerm();
    productName3 = TestFixture.createRandomTerm();

    brand = new InsecticideBrand();
    brand.setProductName(productName);
    brand.addInsecticideUse(InsecticideBrandUse.IRS);
    brand.setActiveIngredient(activeIngredient);
    brand.setConcentrationQuantifier(new BigDecimal("4.50"));
    brand.addConcentrationQualifier(InsecticideBrandConcentrationQualifier.PERCENT);
    brand.setUnitQuantifier(new BigDecimal("100"));
    brand.addUnitQualifier(InsecticideBrandUnitQualifier.GRAMS);
    brand.setUnitsPerApplication(20);
    brand.setEnabled(true);
    brand.apply();

    brand2 = new InsecticideBrand();
    brand2.setProductName(productName2);
    brand2.addInsecticideUse(InsecticideBrandUse.IRS);
    brand2.setActiveIngredient(activeIngredient);
    brand2.setConcentrationQuantifier(new BigDecimal("9.50"));
    brand2.addConcentrationQualifier(InsecticideBrandConcentrationQualifier.PERCENT);
    brand2.setUnitQuantifier(new BigDecimal("50"));
    brand2.addUnitQualifier(InsecticideBrandUnitQualifier.GRAMS);
    brand2.setUnitsPerApplication(13);
    brand2.setEnabled(true);
    brand2.apply();

    nozzle = new Nozzle();
    nozzle.setDisplayLabel(TestConstants.NOZZLE_NAME);
    nozzle.setEnabled(true);
    nozzle.setRatio(ratio);
    nozzle.apply();
  }

  public void testCreate()
  {
    InsecticideNozzle insecticideNozzle = new InsecticideNozzle(brand, nozzle);
    insecticideNozzle.setEnabled(true);
    insecticideNozzle.apply();

    try
    {
      InsecticideNozzle test = InsecticideNozzle.get(insecticideNozzle.getId());

      assertNotNull(test);
      assertEquals(brand.getId(), test.getParentId());
      assertEquals(nozzle.getId(), test.getChildId());
      assertEquals(insecticideNozzle.getEnabled(), test.getEnabled());
    }
    finally
    {
      insecticideNozzle.delete();
    }
  }

  public void testUpdate()
  {
    InsecticideNozzle insecticideNozzle = new InsecticideNozzle(brand, nozzle);
    insecticideNozzle.setEnabled(true);
    insecticideNozzle.apply();

    InsecticideNozzle edit = InsecticideNozzle.lock(insecticideNozzle.getId());
    edit.setEnabled(false);
    edit.apply();

    try
    {
      InsecticideNozzle test = InsecticideNozzle.get(insecticideNozzle.getId());

      assertNotNull(test);
      assertEquals(brand.getId(), test.getParentId());
      assertEquals(nozzle.getId(), test.getChildId());
      assertEquals(edit.getEnabled(), test.getEnabled());
    }
    finally
    {
      edit.delete();
    }
  }

  public void testGetAll()
  {
    InsecticideNozzle insecticideNozzle = new InsecticideNozzle(brand, nozzle);
    insecticideNozzle.setEnabled(true);
    insecticideNozzle.apply();

    InsecticideNozzle insecticideNozzle2 = new InsecticideNozzle(brand2, nozzle);
    insecticideNozzle2.setEnabled(true);
    insecticideNozzle2.apply();

    try
    {
      InsecticideNozzle[] all = InsecticideNozzle.getAll();

      assertEquals(2, all.length);
    }
    finally
    {
      insecticideNozzle.delete();
      insecticideNozzle2.delete();
    }
  }

  public void testInactiveGetAll()
  {
    InsecticideNozzle insecticideNozzle = new InsecticideNozzle(brand, nozzle);
    insecticideNozzle.setEnabled(true);
    insecticideNozzle.apply();

    InsecticideNozzle insecticideNozzle2 = new InsecticideNozzle(brand2, nozzle);
    insecticideNozzle2.setEnabled(false);
    insecticideNozzle2.apply();

    try
    {
      InsecticideNozzle[] all = InsecticideNozzle.getAll();

      assertEquals(1, all.length);
    }
    finally
    {
      insecticideNozzle.delete();
      insecticideNozzle2.delete();
    }
  }

  public void testInactiveParentGetAll()
  {
    BigDecimal weight = new BigDecimal("4.50");
    Integer refill = new Integer(20);

    InsecticideBrand testBrand = new InsecticideBrand();
    testBrand.setProductName(productName3);
    testBrand.addInsecticideUse(InsecticideBrandUse.IRS);
    testBrand.setActiveIngredient(activeIngredient);
    testBrand.setConcentrationQuantifier(new BigDecimal("4.50"));
    testBrand.addConcentrationQualifier(InsecticideBrandConcentrationQualifier.PERCENT);
    testBrand.setUnitQuantifier(new BigDecimal("100"));
    testBrand.addUnitQualifier(InsecticideBrandUnitQualifier.GRAMS);
    testBrand.setUnitsPerApplication(20);
    testBrand.setEnabled(false);
    testBrand.apply();

    InsecticideNozzle insecticideNozzle = new InsecticideNozzle(brand, nozzle);
    insecticideNozzle.setEnabled(true);
    insecticideNozzle.apply();

    InsecticideNozzle insecticideNozzle2 = new InsecticideNozzle(testBrand, nozzle);
    insecticideNozzle2.setEnabled(true);
    insecticideNozzle2.apply();

    try
    {
      InsecticideNozzle[] all = InsecticideNozzle.getAll();

      assertEquals(1, all.length);
    }
    finally
    {
      insecticideNozzle.delete();
      insecticideNozzle2.delete();
      testBrand.delete();
    }
  }

  public void testInactiveChildGetAll()
  {
    BigDecimal ratio = new BigDecimal("0.30");

    Nozzle testNozzle = new Nozzle();
    testNozzle.setDisplayLabel(TestConstants.NOZZLE_NAME_2);
    testNozzle.setEnabled(false);
    testNozzle.setRatio(ratio);
    testNozzle.apply();

    InsecticideNozzle insecticideNozzle = new InsecticideNozzle(brand, nozzle);
    insecticideNozzle.setEnabled(true);
    insecticideNozzle.apply();

    InsecticideNozzle insecticideNozzle2 = new InsecticideNozzle(brand2, testNozzle);
    insecticideNozzle2.setEnabled(false);
    insecticideNozzle2.apply();

    try
    {
      InsecticideNozzle[] all = InsecticideNozzle.getAll();

      assertEquals(1, all.length);
    }
    finally
    {
      insecticideNozzle.delete();
      insecticideNozzle2.delete();
      testNozzle.delete();
    }
  }

  public void testApplyAll()
  {
    InsecticideNozzle insecticideNozzle = new InsecticideNozzle(brand, nozzle);
    insecticideNozzle.setEnabled(true);

    InsecticideNozzle insecticideNozzle2 = new InsecticideNozzle(brand2, nozzle);
    insecticideNozzle2.setEnabled(true);

    InsecticideNozzle[] array = new InsecticideNozzle[] { insecticideNozzle, insecticideNozzle2 };
    InsecticideNozzle[] applied = InsecticideNozzle.applyAll(array);

    try
    {
      assertEquals(array.length, applied.length);

      for (int i = 0; i < applied.length; i++)
      {
        InsecticideNozzle test = InsecticideNozzle.get(applied[i].getId());

        assertNotNull(test);
        assertEquals(applied[i].getParentId(), test.getParentId());
        assertEquals(applied[i].getChildId(), test.getChildId());
        assertEquals(applied[i].getEnabled(), test.getEnabled());
      }
    }
    finally
    {
      insecticideNozzle.delete();
      insecticideNozzle2.delete();
    }

  }

}
