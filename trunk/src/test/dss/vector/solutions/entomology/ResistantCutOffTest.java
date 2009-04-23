package dss.vector.solutions.entomology;

import dss.vector.solutions.Property;
import junit.framework.TestCase;

public class ResistantCutOffTest extends TestCase
{
  public Integer getProperty(String name)
  {
    return Property.getInt(Property.RESISTANCE_PACKAGE, name);
  }

  public void setPropertyValue(String name, Integer value)
  {
    Property p = Property.getByPackageAndName(Property.RESISTANCE_PACKAGE, name);
    p.setPropertyValue(value.toString());
    p.apply();
  }

  public void testGetDefault()
  {
    assertEquals(new Integer(95), getProperty(Property.ADULT_DDA_RESISTANCE));
    assertEquals(new Integer(98), getProperty(Property.ADULT_DDA_SUSCEPTIBILE));

    assertEquals(new Integer(95), getProperty(Property.LARVAE_DDA_RESISTANCE));
    assertEquals(new Integer(98), getProperty(Property.LARVAE_DDA_SUSCEPTIBILE));
  }

  public void testSetValues()
  {
    int defaultLow = 95;
    int defaultHigh = 98;
    Integer testLow = 20;
    Integer testHigh = 80;

    setPropertyValue(Property.ADULT_DDA_RESISTANCE, testLow);
    setPropertyValue(Property.ADULT_DDA_SUSCEPTIBILE, testHigh);
    setPropertyValue(Property.LARVAE_DDA_RESISTANCE, testLow);
    setPropertyValue(Property.LARVAE_DDA_SUSCEPTIBILE, testHigh);

    try
    {
      assertEquals(testLow, getProperty(Property.ADULT_DDA_RESISTANCE));
      assertEquals(testHigh, getProperty(Property.ADULT_DDA_SUSCEPTIBILE));

      assertEquals(testLow, getProperty(Property.LARVAE_DDA_RESISTANCE));
      assertEquals(testHigh, getProperty(Property.LARVAE_DDA_SUSCEPTIBILE));
    }
    finally
    {
      setPropertyValue(Property.ADULT_DDA_RESISTANCE, defaultLow);
      setPropertyValue(Property.ADULT_DDA_SUSCEPTIBILE, defaultHigh);
      setPropertyValue(Property.LARVAE_DDA_RESISTANCE, defaultLow);
      setPropertyValue(Property.LARVAE_DDA_SUSCEPTIBILE, defaultHigh);
    }
  }
}
