package dss.vector.solutions.permissions.administration;

import dss.vector.solutions.MDSSRoleInfo;
import junit.extensions.TestSetup;
import junit.framework.Test;
import junit.framework.TestSuite;

public class OM_PersonCRUDPermissions extends PersonCRUDPermissions
{
  public static Test suite()
  {
    TestSuite suite = new TestSuite();
    suite.addTestSuite(OM_PersonCRUDPermissions.class);

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
