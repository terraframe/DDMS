package dss.vector.solutions.permissions.itn.household;

import junit.extensions.TestSetup;
import junit.framework.Test;
import junit.framework.TestSuite;
import dss.vector.solutions.MDSSRoleInfo;

public class E_ITNHouseholdSurveyNoPermissions extends ITNHouseholdSurveyNoPermissions
{
  public static Test suite()
  {
    TestSuite suite = new TestSuite();
    suite.addTestSuite(E_ITNHouseholdSurveyNoPermissions.class);

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
