package dss.vector.solutions.entomology;

import java.util.LinkedList;
import java.util.List;

import junit.framework.TestResult;

import com.terraframe.mojo.query.OIterator;

import dss.vector.solutions.geo.DeleteEarthException;
import dss.vector.solutions.geo.DuplicateEarthException;
import dss.vector.solutions.geo.DuplicateParentException;
import dss.vector.solutions.geo.GeoEntityView;
import dss.vector.solutions.geo.GeoEntityViewQuery;
import dss.vector.solutions.geo.LocatedInException;
/*
import dss.vector.solutions.geo.generated.AdministrativePost;
import dss.vector.solutions.geo.generated.Country;
import dss.vector.solutions.geo.generated.District;
import dss.vector.solutions.geo.generated.Earth;
import dss.vector.solutions.geo.generated.GeoEntity;
import dss.vector.solutions.geo.generated.Province;
 */

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
}
