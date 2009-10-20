package dss.vector.solutions.permissions.population;

import junit.extensions.TestSetup;
import junit.framework.Test;
import junit.framework.TestSuite;
import dss.vector.solutions.MDSSRoleInfo;

public class M_PopulationNoPermission extends PopulationNoPermission
{
  public static Test suite()
  {
    TestSuite suite = new TestSuite();
    suite.addTestSuite(M_PopulationNoPermission.class);

    TestSetup wrapper = new TestSetup(suite)
    {
      protected void setUp()
      {
        rolename = MDSSRoleInfo.MANAGER;
        
        classSetUp();
      }

      protected void tearDown()
      {
        classTearDown();
      }

    };

    return wrapper;
  }

}
