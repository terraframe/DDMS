package dss.vector.solutions.permissions.entomology;

import junit.extensions.TestSetup;
import junit.framework.Test;
import junit.framework.TestSuite;

import com.terraframe.mojo.DoNotWeave;

import dss.vector.solutions.MDSSRoleInfo;

public class E_EntomologyCRUDPermissions extends EntomologyCRUDPermissions implements DoNotWeave
{
  public static Test suite()
  {
    TestSuite suite = new TestSuite();
    suite.addTestSuite(E_EntomologyCRUDPermissions.class);

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
