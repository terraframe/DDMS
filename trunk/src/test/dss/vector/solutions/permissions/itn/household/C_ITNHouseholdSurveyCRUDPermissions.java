package dss.vector.solutions.permissions.itn.household;

import dss.vector.solutions.MDSSRoleInfo;
import junit.extensions.TestSetup;
import junit.framework.Test;
import junit.framework.TestSuite;

public class C_ITNHouseholdSurveyCRUDPermissions extends ITNHouseholdSurveyCRUDPermissions
{  
  public static Test suite()
  {
    TestSuite suite = new TestSuite();
    suite.addTestSuite(C_ITNHouseholdSurveyCRUDPermissions.class);

    TestSetup wrapper = new TestSetup(suite)
    {
      protected void setUp()
      {
        rolename = MDSSRoleInfo.MDSS_CORRDINATOR;
        
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
