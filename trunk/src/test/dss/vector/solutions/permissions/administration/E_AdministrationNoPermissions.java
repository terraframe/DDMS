package dss.vector.solutions.permissions.administration;

import dss.vector.solutions.MDSSRoleInfo;
import junit.extensions.TestSetup;
import junit.framework.Test;
import junit.framework.TestSuite;

public class E_AdministrationNoPermissions extends AdministrationNoPermissions
{
  public static Test suite()
  {
    TestSuite suite = new TestSuite();
    suite.addTestSuite(E_AdministrationNoPermissions.class);

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
