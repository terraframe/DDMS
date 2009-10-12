package dss.vector.solutions.permissions.indicator;

import junit.extensions.TestSetup;
import junit.framework.Test;
import junit.framework.TestSuite;

import com.terraframe.mojo.DoNotWeave;

import dss.vector.solutions.MDSSRoleInfo;

public class C_SurveyCRUDPermissions extends SurveyCRUDPermissions implements DoNotWeave
{
  public static Test suite()
  {
    TestSuite suite = new TestSuite();
    suite.addTestSuite(C_SurveyCRUDPermissions.class);

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
