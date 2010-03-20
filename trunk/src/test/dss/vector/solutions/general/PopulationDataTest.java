package dss.vector.solutions.general;

import com.runwaysdk.dataaccess.cache.DataNotFoundException;
import com.runwaysdk.dataaccess.database.DuplicateDataDatabaseException;

import junit.extensions.TestSetup;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestResult;
import junit.framework.TestSuite;
import dss.vector.solutions.TestFixture;
import dss.vector.solutions.geo.generated.GeoEntity;

public class PopulationDataTest extends TestCase
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

  private static GeoEntity entity  = null;

  public static Test suite()
  {
    TestSuite suite = new TestSuite();
    suite.addTestSuite(PopulationDataTest.class);

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
    entity.delete();
  }

  protected static void classSetUp()
  {
    entity = TestFixture.createRandomCountry();
  }
  
  public void testDuplicate()
  {
    PopulationData original = TestFixture.createPopulationData(entity, 2000, false, 200L, null);
    
    try
    {
      PopulationData duplicate = TestFixture.createPopulationData(entity, 2000, false, 200L, null);
      
      duplicate.delete();
      
      fail("Able to create duplicate population data");
    }
    catch(DuplicateDataDatabaseException e)
    {
      // This is expected
    }
    finally
    {
      original.delete();
    }
  }
  
  public void testUpdatePopulation()
  {
    PopulationData[] data = createPopulationData();
    
    try
    {
      data[2].setPopulation(400L);
      data[2].apply();
      
      try 
      {
        PopulationData.get(data[3].getId());
        
        fail("Estimated Population was not deleted");
      }
      catch(DataNotFoundException e)
      {
        // This is expected
      }
      
      try 
      {
        PopulationData.get(data[5].getId());
        
        fail("Estimated Population was not deleted");
      }
      catch(DataNotFoundException e)
      {
        // This is expected
      }
      
      PopulationData population = PopulationData.get(data[4].getId());
      
      assertNotNull(population);
      assertNull(population.getPopulation());
      assertEquals(data[4].getGrowthRate(), population.getGrowthRate());
      assertEquals(new Boolean(false), population.getEstimated());

      
      population = PopulationData.get(data[2].getId());
      
      assertNotNull(population);
      assertEquals(data[2].getPopulation(), population.getPopulation());
      assertEquals(data[2].getGrowthRate(), population.getGrowthRate());
      assertEquals(data[2].getEstimated(), population.getEstimated());

      population = PopulationData.get(data[7].getId());
      
      assertNotNull(population);
      assertEquals(data[7].getPopulation(), population.getPopulation());
      assertEquals(data[7].getGrowthRate(), population.getGrowthRate());
      assertEquals(data[7].getEstimated(), population.getEstimated());
    }
    finally
    {
      deletePopulationData(data);
    }
  }
  
  public void testDeletePopulation()
  {
    PopulationData[] data = createPopulationData();
    
    try
    {
      data[2].delete();
      
      try 
      {
        PopulationData.get(data[2].getId());
        
        fail("Population was not deleted");
      }
      catch(DataNotFoundException e)
      {
        // This is expected
      }
      
      try 
      {
        PopulationData.get(data[3].getId());
        
        fail("Estimated Population was not deleted");
      }
      catch(DataNotFoundException e)
      {
        // This is expected
      }
      
      try 
      {
        PopulationData.get(data[5].getId());
        
        fail("Estimated Population was not deleted");
      }
      catch(DataNotFoundException e)
      {
        // This is expected
      }
      
      PopulationData population = PopulationData.get(data[4].getId());
      
      assertNotNull(population);
      assertNull(population.getPopulation());
      assertEquals(data[4].getGrowthRate(), population.getGrowthRate());
      assertEquals(new Boolean(false), population.getEstimated());
      
      population = PopulationData.get(data[7].getId());
      
      assertNotNull(population);
      assertEquals(data[7].getPopulation(), population.getPopulation());
      assertEquals(data[7].getGrowthRate(), population.getGrowthRate());
      assertEquals(data[7].getEstimated(), population.getEstimated());
    }
    finally
    {
      deletePopulationData(data);
    }
  }

  private void deletePopulationData(PopulationData[] data)
  {
    for(PopulationData population : data)
    {
      try
      {
        PopulationData.get(population.getId()).delete();
      }
      catch(Exception e)
      {
        
      }
    }
  }

  private PopulationData[] createPopulationData()
  {
    PopulationData pop2000 = TestFixture.createPopulationData(entity, 2000, false, 200L, null);
    PopulationData pop2001 = TestFixture.createPopulationData(entity, 2001, true, 200L, null);
    PopulationData pop2002 = TestFixture.createPopulationData(entity, 2002, false, 200L, null);
    PopulationData pop2003 = TestFixture.createPopulationData(entity, 2003, true, 200L, null);
    PopulationData pop2004 = TestFixture.createPopulationData(entity, 2004, true, 200L, 0.04D);
    PopulationData pop2005 = TestFixture.createPopulationData(entity, 2005, true, 200L, null);
    PopulationData pop2006 = TestFixture.createPopulationData(entity, 2006, false, 200L, null);
    PopulationData pop2007 = TestFixture.createPopulationData(entity, 2007, true, 200L, null);
    PopulationData pop2008 = TestFixture.createPopulationData(entity, 2008, true, 200L, null);
    PopulationData pop2009 = TestFixture.createPopulationData(entity, 2009, true, 200L, null);
    
    PopulationData[] data = {pop2000, pop2001, pop2002,pop2003,pop2004,pop2005,pop2006,pop2007,pop2008,pop2009};
    
    return data;
  }

}
