package mdss.ivcc.mrc.csu.entomology;

import junit.framework.TestCase;
import mdss.ivcc.mrc.csu.Property;

public class ResistantCutOffTest extends TestCase
{
  public void testGetDefault()
  {
    assertEquals(new Integer(95), getProperty(Property.ADULT_DDA_RESISTANCE));
    assertEquals(new Integer(98), getProperty(Property.ADULT_DDA_SUSCEPTIBILE));

    assertEquals(new Integer(95), getProperty(Property.LARVAE_DDA_RESISTANCE));
    assertEquals(new Integer(98), getProperty(Property.LARVAE_DDA_SUSCEPTIBILE));

    assertEquals(new Integer(95), getProperty(Property.ADULT_KNOCK_DOWN_RESISTANCE));
    assertEquals(new Integer(98), getProperty(Property.ADULT_KNOCK_DOWN_POTENTIAL_RESISTANCE));

    assertEquals(new Integer(95), getProperty(Property.LARVAE_KNOCK_DOWN_RESISTANCE));
    assertEquals(new Integer(98), getProperty(Property.LARVAE_KNOCK_DOWN_POTENTIAL_RESISTANCE));
  }

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
    setPropertyValue(Property.ADULT_KNOCK_DOWN_RESISTANCE, testLow);
    setPropertyValue(Property.ADULT_KNOCK_DOWN_POTENTIAL_RESISTANCE, testHigh);
    setPropertyValue(Property.LARVAE_KNOCK_DOWN_RESISTANCE, testLow);
    setPropertyValue(Property.LARVAE_KNOCK_DOWN_POTENTIAL_RESISTANCE, testHigh);

    try
    {
      assertEquals(testLow, getProperty(Property.ADULT_DDA_RESISTANCE));
      assertEquals(testHigh, getProperty(Property.ADULT_DDA_SUSCEPTIBILE));

      assertEquals(testLow, getProperty(Property.LARVAE_DDA_RESISTANCE));
      assertEquals(testHigh, getProperty(Property.LARVAE_DDA_SUSCEPTIBILE));

      assertEquals(testLow, getProperty(Property.ADULT_KNOCK_DOWN_RESISTANCE));
      assertEquals(testHigh, getProperty(Property.ADULT_KNOCK_DOWN_POTENTIAL_RESISTANCE));

      assertEquals(testLow, getProperty(Property.LARVAE_KNOCK_DOWN_RESISTANCE));
      assertEquals(testHigh, getProperty(Property.LARVAE_KNOCK_DOWN_POTENTIAL_RESISTANCE));

    }
    finally
    {
      setPropertyValue(Property.ADULT_DDA_RESISTANCE, defaultLow);
      setPropertyValue(Property.ADULT_DDA_SUSCEPTIBILE, defaultHigh);
      setPropertyValue(Property.LARVAE_DDA_RESISTANCE, defaultLow);
      setPropertyValue(Property.LARVAE_DDA_SUSCEPTIBILE, defaultHigh);
      setPropertyValue(Property.ADULT_KNOCK_DOWN_RESISTANCE, defaultLow);
      setPropertyValue(Property.ADULT_KNOCK_DOWN_POTENTIAL_RESISTANCE, defaultHigh);
      setPropertyValue(Property.LARVAE_KNOCK_DOWN_RESISTANCE, defaultLow);
      setPropertyValue(Property.LARVAE_KNOCK_DOWN_POTENTIAL_RESISTANCE, defaultHigh);
    }
  }

  public void testInvalidADDAR()
  {
    int v = getProperty(Property.ADULT_DDA_RESISTANCE);

    try
    {
      setPropertyValue(Property.ADULT_DDA_RESISTANCE, 100);

      fail("Able to set an adult discriminating dose assay % mortality with the resistance value larger than the susceptible value");
    }
    catch (RuntimeException e)
    {
      // This is expected
    }
    finally
    {
      setPropertyValue(Property.ADULT_DDA_RESISTANCE, v);
    }
  }

  public void testInvalidADDAS()
  {
    int v = getProperty(Property.ADULT_DDA_SUSCEPTIBILE);

    try
    {
      setPropertyValue(Property.ADULT_DDA_SUSCEPTIBILE, 30);

      fail("Able to set an adult discriminating dose assay % mortality with the resistance value larger than the susceptible value");
    }
    catch (RuntimeException e)
    {
      // This is expected
    }
    finally
    {
      setPropertyValue(Property.ADULT_DDA_SUSCEPTIBILE, v);
    }
  }

  public void testInvalidLDDAR()
  {
    int v = getProperty(Property.LARVAE_DDA_RESISTANCE);

    try
    {
      setPropertyValue(Property.LARVAE_DDA_RESISTANCE, 100);

      fail("Able to set an larvae discriminating dose assay % mortality with the resistance value larger than the susceptible value");
    }
    catch (RuntimeException e)
    {
      // This is expected
    }
    finally
    {
      setPropertyValue(Property.LARVAE_DDA_RESISTANCE, v);
    }
  }

  public void testInvalidLDDAS()
  {
    int v = getProperty(Property.LARVAE_DDA_SUSCEPTIBILE);

    try
    {
      setPropertyValue(Property.LARVAE_DDA_SUSCEPTIBILE, 30);

      fail("Able to set an larvae discriminating dose assay % mortality with the resistance value larger than the susceptible value");
    }
    catch (RuntimeException e)
    {
      // This is expected
    }
    finally
    {
      setPropertyValue(Property.LARVAE_DDA_RESISTANCE, v);
    }
  }

  public void testInvalidAAKnockDownR()
  {
    int v = getProperty(Property.ADULT_KNOCK_DOWN_RESISTANCE);

    try
    {
      setPropertyValue(Property.ADULT_KNOCK_DOWN_RESISTANCE, 100);

      fail("Able to set an larvae discriminating dose assay % mortality with the resistance value larger than the susceptible value");
    }
    catch (RuntimeException e)
    {
      // This is expected
    }
    finally
    {
      setPropertyValue(Property.ADULT_KNOCK_DOWN_RESISTANCE, v);
    }
  }

  public void testInvalidAAKnockDownPR()
  {
    int v = getProperty(Property.ADULT_KNOCK_DOWN_POTENTIAL_RESISTANCE);

    try
    {
      setPropertyValue(Property.ADULT_KNOCK_DOWN_POTENTIAL_RESISTANCE, 30);

      fail("Able to set an larvae discriminating dose assay % mortality with the resistance value larger than the susceptible value");
    }
    catch (RuntimeException e)
    {
      // This is expected
    }
    finally
    {
      setPropertyValue(Property.ADULT_KNOCK_DOWN_POTENTIAL_RESISTANCE, v);
    }
  }

  public void testInvalidLAKnockDownR()
  {
    int v = getProperty(Property.LARVAE_KNOCK_DOWN_RESISTANCE);

    try
    {
      setPropertyValue(Property.LARVAE_KNOCK_DOWN_RESISTANCE, 100);

      fail("Able to set an larvae discriminating dose assay % mortality with the resistance value larger than the susceptible value");
    }
    catch (RuntimeException e)
    {
      // This is expected
    }
    finally
    {
      setPropertyValue(Property.LARVAE_KNOCK_DOWN_RESISTANCE, v);
    }
  }

  public void testInvalidLAKnockDownPR()
  {
    int v = getProperty(Property.LARVAE_KNOCK_DOWN_POTENTIAL_RESISTANCE);

    try
    {
      setPropertyValue(Property.LARVAE_KNOCK_DOWN_POTENTIAL_RESISTANCE, 30);

      fail("Able to set an larvae discriminating dose assay % mortality with the resistance value larger than the susceptible value");
    }
    catch (RuntimeException e)
    {
      // This is expected
    }
    finally
    {
      setPropertyValue(Property.LARVAE_KNOCK_DOWN_POTENTIAL_RESISTANCE, v);
    }
  }
}
