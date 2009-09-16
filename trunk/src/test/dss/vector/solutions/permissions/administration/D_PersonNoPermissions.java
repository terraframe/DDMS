package dss.vector.solutions.permissions.administration;

import junit.extensions.TestSetup;
import junit.framework.Test;
import junit.framework.TestSuite;
import dss.vector.solutions.MDSSRoleInfo;

public class D_PersonNoPermissions extends PersonNoPermissions
{
  public static Test suite()
  {
    TestSuite suite = new TestSuite();
    suite.addTestSuite(D_PersonNoPermissions.class);

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
