package dss.vector.solutions.permissions.itn.household;

import junit.extensions.TestSetup;
import junit.framework.Test;
import junit.framework.TestSuite;
import dss.vector.solutions.MDSSRoleInfo;

public class D_ITNHouseholdSurveyCRUDPermissions extends ITNHouseholdSurveyCRUDPermissions
{
  public static Test suite()
  {
    TestSuite suite = new TestSuite();
    suite.addTestSuite(D_ITNHouseholdSurveyCRUDPermissions.class);

    TestSetup wrapper = new TestSetup(suite)
    {
      protected void setUp()
      {
        rolename = MDSSRoleInfo.DATACAPTURER;
        
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
