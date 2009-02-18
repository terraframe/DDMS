package mdss.entomology;

import junit.framework.TestCase;

public class ResistantCutOffTest extends TestCase
{
  public void testGetDefault()
  {
    ResistantCutOff instance = ResistantCutOff.instance();

    assertEquals(new Integer(95), instance.getADDAR());
    assertEquals(new Integer(98), instance.getADDAS());

    assertEquals(new Integer(95), instance.getLDDAR());
    assertEquals(new Integer(98), instance.getLDDAS());

    assertEquals(new Integer(95), instance.getAAKnockDownR());
    assertEquals(new Integer(98), instance.getAAKnockDownPR());

    assertEquals(new Integer(95), instance.getLAKnockDownR());
    assertEquals(new Integer(98), instance.getLAKnockDownPR());
  }

  public void testSetValues()
  {
    ResistantCutOff instance = ResistantCutOff.instance();

    int defaultLow = 95;
    int defaultHigh = 98;
    int testLow = 20;
    int testHigh = 80;
    
    instance.setADDAR(testLow);
    instance.setADDAS(testHigh);
    instance.setLDDAR(testLow);
    instance.setLDDAS(testHigh);
    instance.setAAKnockDownR(testLow);
    instance.setAAKnockDownPR(testHigh);
    instance.setLAKnockDownR(testLow);
    instance.setLAKnockDownPR(testHigh);
    instance.apply();
    
    try
    {
      assertEquals(new Integer(testLow), instance.getADDAR());
      assertEquals(new Integer(testHigh), instance.getADDAS());

      assertEquals(new Integer(testLow), instance.getLDDAR());
      assertEquals(new Integer(testHigh), instance.getLDDAS());

      assertEquals(new Integer(testLow), instance.getAAKnockDownR());
      assertEquals(new Integer(testHigh), instance.getAAKnockDownPR());

      assertEquals(new Integer(testLow), instance.getLAKnockDownR());
      assertEquals(new Integer(testHigh), instance.getLAKnockDownPR());
    }
    finally
    {
      instance.setADDAR(defaultLow);
      instance.setADDAS(defaultHigh);
      instance.setLDDAR(defaultLow);
      instance.setLDDAS(defaultHigh);
      instance.setAAKnockDownR(defaultLow);
      instance.setAAKnockDownPR(defaultHigh);
      instance.setLAKnockDownR(defaultLow);
      instance.setLAKnockDownPR(defaultHigh);
      instance.apply();
    }
  }
  
  public void testInvalidADDAR()
  {
    ResistantCutOff instance = ResistantCutOff.instance();
    int v = instance.getADDAR();
    
    try
    {
      instance.setADDAR(100);
      instance.apply();
      
      fail("Able to set an adult discriminating dose assay % mortality with the resistance value larger than the susceptible value");
    }
    catch(RuntimeException e)
    {
     //This is expected 
    }
    finally
    {
      instance.setADDAR(v);
      instance.apply();
    }    
  }
  
  public void testInvalidADDAS()
  {
    ResistantCutOff instance = ResistantCutOff.instance();
    int v = instance.getADDAS();
    
    try
    {
      instance.setADDAS(30);
      instance.apply();
      
      fail("Able to set an adult discriminating dose assay % mortality with the resistance value larger than the susceptible value");
    }
    catch(RuntimeException e)
    {
     //This is expected 
    }
    finally
    {
      instance.setADDAS(v);
      instance.apply();
    }    
  }

  public void testInvalidLDDAR()
  {
    ResistantCutOff instance = ResistantCutOff.instance();
    int v = instance.getLDDAR();
    
    try
    {
      instance.setLDDAR(100);
      instance.apply();
      
      fail("Able to set an larvae discriminating dose assay % mortality with the resistance value larger than the susceptible value");
    }
    catch(RuntimeException e)
    {
     //This is expected 
    }
    finally
    {
      instance.setLDDAR(v);
      instance.apply();
    }    
  }
  
  public void testInvalidLDDAS()
  {
    ResistantCutOff instance = ResistantCutOff.instance();
    int v = instance.getLDDAS();
    
    try
    {
      instance.setLDDAS(30);
      instance.apply();
      
      fail("Able to set an larvae discriminating dose assay % mortality with the resistance value larger than the susceptible value");
    }
    catch(RuntimeException e)
    {
     //This is expected 
    }
    finally
    {
      instance.setLDDAS(v);
      instance.apply();
    }    
  }

  public void testInvalidAAKnockDownR()
  {
    ResistantCutOff instance = ResistantCutOff.instance();
    int v = instance.getAAKnockDownR();
    
    try
    {
      instance.setAAKnockDownR(100);
      instance.apply();
      
      fail("Able to set an larvae discriminating dose assay % mortality with the resistance value larger than the susceptible value");
    }
    catch(RuntimeException e)
    {
     //This is expected 
    }
    finally
    {
      instance.setAAKnockDownR(v);
      instance.apply();
    }    
  }
  
  public void testInvalidAAKnockDownPR()
  {
    ResistantCutOff instance = ResistantCutOff.instance();
    int v = instance.getAAKnockDownPR();
    
    try
    {
      instance.setAAKnockDownPR(30);
      instance.apply();
      
      fail("Able to set an larvae discriminating dose assay % mortality with the resistance value larger than the susceptible value");
    }
    catch(RuntimeException e)
    {
     //This is expected 
    }
    finally
    {
      instance.setAAKnockDownPR(v);
      instance.apply();
    }    
  }
  
  public void testInvalidLAKnockDownR()
  {
    ResistantCutOff instance = ResistantCutOff.instance();
    int v = instance.getLAKnockDownR();
    
    try
    {
      instance.setLAKnockDownR(100);
      instance.apply();
      
      fail("Able to set an larvae discriminating dose assay % mortality with the resistance value larger than the susceptible value");
    }
    catch(RuntimeException e)
    {
     //This is expected 
    }
    finally
    {
      instance.setLAKnockDownR(v);
      instance.apply();
    }    
  }

  public void testInvalidLAKnockDownPR()
  {
    ResistantCutOff instance = ResistantCutOff.instance();
    int v = instance.getLAKnockDownPR();
    
    try
    {
      instance.setLAKnockDownPR(30);
      instance.apply();
      
      fail("Able to set an larvae discriminating dose assay % mortality with the resistance value larger than the susceptible value");
    }
    catch(RuntimeException e)
    {
     //This is expected 
    }
    finally
    {
      instance.setLAKnockDownPR(v);
      instance.apply();
    }    
  }
}
