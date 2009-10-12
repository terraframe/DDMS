package dss.vector.solutions.entomology;

import dss.vector.solutions.Property;
import dss.vector.solutions.PropertyInfo;
import junit.framework.TestCase;
import junit.framework.TestResult;

public class ResistantCutOffTest extends TestCase
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

  public Integer getProperty(String name)
  {
    return Property.getInt(PropertyInfo.RESISTANCE_PACKAGE, name);
  }

  public void setPropertyValue(String name, Integer value)
  {
    Property p = Property.getByPackageAndName(PropertyInfo.RESISTANCE_PACKAGE, name);
    p.setPropertyValue(value.toString());
    p.apply();
  }

  public void testGetDefault()
  {
    assertEquals(new Integer(95), getProperty(PropertyInfo.ADULT_DDA_RESISTANCE));
    assertEquals(new Integer(98), getProperty(PropertyInfo.ADULT_DDA_SUSCEPTIBILE));

    assertEquals(new Integer(95), getProperty(PropertyInfo.LARVAE_DDA_RESISTANCE));
    assertEquals(new Integer(98), getProperty(PropertyInfo.LARVAE_DDA_SUSCEPTIBILE));
  }

  public void testSetValues()
  {
    int defaultLow = 95;
    int defaultHigh = 98;
    Integer testLow = 20;
    Integer testHigh = 80;

    setPropertyValue(PropertyInfo.ADULT_DDA_RESISTANCE, testLow);
    setPropertyValue(PropertyInfo.ADULT_DDA_SUSCEPTIBILE, testHigh);
    setPropertyValue(PropertyInfo.LARVAE_DDA_RESISTANCE, testLow);
    setPropertyValue(PropertyInfo.LARVAE_DDA_SUSCEPTIBILE, testHigh);

    try
    {
      assertEquals(testLow, getProperty(PropertyInfo.ADULT_DDA_RESISTANCE));
      assertEquals(testHigh, getProperty(PropertyInfo.ADULT_DDA_SUSCEPTIBILE));

      assertEquals(testLow, getProperty(PropertyInfo.LARVAE_DDA_RESISTANCE));
      assertEquals(testHigh, getProperty(PropertyInfo.LARVAE_DDA_SUSCEPTIBILE));
    }
    finally
    {
      setPropertyValue(PropertyInfo.ADULT_DDA_RESISTANCE, defaultLow);
      setPropertyValue(PropertyInfo.ADULT_DDA_SUSCEPTIBILE, defaultHigh);
      setPropertyValue(PropertyInfo.LARVAE_DDA_RESISTANCE, defaultLow);
      setPropertyValue(PropertyInfo.LARVAE_DDA_SUSCEPTIBILE, defaultHigh);
    }
  }
}
