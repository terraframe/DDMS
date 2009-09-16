package dss.vector.solutions.permissions.itn.distribution;

import dss.vector.solutions.MDSSRoleInfo;
import junit.extensions.TestSetup;
import junit.framework.Test;
import junit.framework.TestSuite;

public class OM_ITNCommunityDistributionCRUDPermissions extends ITNCommunityDistributionCRUDPermissions
{
  public static Test suite()
  {
    TestSuite suite = new TestSuite();
    suite.addTestSuite(OM_ITNCommunityDistributionCRUDPermissions.class);

    TestSetup wrapper = new TestSetup(suite)
    {
      protected void setUp()
      {
        rolename = MDSSRoleInfo.OPERATIONAL_MANAGER;
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
