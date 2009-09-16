package dss.vector.solutions.permissions.itn.distribution;

import dss.vector.solutions.MDSSRoleInfo;
import junit.extensions.TestSetup;
import junit.framework.Test;
import junit.framework.TestSuite;

public class E_ITNCommunityDistributionNoPermissions extends ITNCommunityDistributionNoPermissions
{
  public static Test suite()
  {
    TestSuite suite = new TestSuite();
    suite.addTestSuite(E_ITNCommunityDistributionNoPermissions.class);

    TestSetup wrapper = new TestSetup(suite)
    {
      protected void setUp()
      {
        rolename = MDSSRoleInfo.ENTOMOLOGIST;
        
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
