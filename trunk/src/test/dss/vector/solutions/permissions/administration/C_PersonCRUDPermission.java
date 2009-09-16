package dss.vector.solutions.permissions.administration;

import junit.extensions.TestSetup;
import junit.framework.Test;
import junit.framework.TestSuite;
import dss.vector.solutions.MDSSRoleInfo;

public class C_PersonCRUDPermission extends PersonCRUDPermissions
{
  public static Test suite()
  {
    TestSuite suite = new TestSuite();
    suite.addTestSuite(C_PersonCRUDPermission.class);

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
