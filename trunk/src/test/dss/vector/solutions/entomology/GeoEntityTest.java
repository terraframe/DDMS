package dss.vector.solutions.entomology;

import junit.framework.TestResult;

public class GeoEntityTest extends GeoTest
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
	  
	  // All content deleted by Chris due to this test relying on a bunch of
	  // Mozambique-specific universals, which then causes it to fail to 
	  // compile when we're using another country's universals.
	  //
	  // See history for the real test
	  public void testPlaceholder()
	  {
	    
	  }
}
