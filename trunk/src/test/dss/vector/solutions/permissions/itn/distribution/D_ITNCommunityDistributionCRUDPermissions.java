package dss.vector.solutions.permissions.itn.distribution;

import junit.extensions.TestSetup;
import junit.framework.Test;
import junit.framework.TestSuite;
import dss.vector.solutions.MDSSRoleInfo;

public class D_ITNCommunityDistributionCRUDPermissions extends ITNCommunityDistributionCRUDPermissions
{
  public static Test suite()
  {
    TestSuite suite = new TestSuite();
    suite.addTestSuite(D_ITNCommunityDistributionCRUDPermissions.class);

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
