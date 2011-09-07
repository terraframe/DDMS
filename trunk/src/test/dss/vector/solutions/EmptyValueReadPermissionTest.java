package dss.vector.solutions;

import java.util.Locale;

import junit.extensions.TestSetup;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import com.runwaysdk.ClientSession;
import com.runwaysdk.DoNotWeave;
import com.runwaysdk.business.BusinessDTO;
import com.runwaysdk.business.rbac.Operation;
import com.runwaysdk.constants.ClientRequestIF;
import com.runwaysdk.constants.CommonProperties;
import com.runwaysdk.constants.UserInfo;
import com.runwaysdk.session.AttributeReadPermissionExceptionDTO;
import com.runwaysdk.system.metadata.MdBusiness;
import com.runwaysdk.web.WebClientSession;

public class EmptyValueReadPermissionTest extends TestCase implements DoNotWeave
{
  protected static ClientSession     systemSession                  = null;
  protected static ClientRequestIF   systemRequest                  = null;

  protected static BusinessDTO       littleBobbyTables              = null;

  public static Test suite()
  {
	TestSuite suite = new TestSuite();
    suite.addTestSuite(EmptyValueReadPermissionTest.class);

    TestSetup wrapper = new TestSetup(suite)
    {
      protected void setUp()
      {
   	    systemSession = WebClientSession.createUserSession("SYSTEM", TestConstants.PASSWORD, new Locale[]{Locale.US});
  	    systemRequest = systemSession.getRequest();
	    classSetUp();
	  }

	  protected void tearDown()
	  {
	    classTearDown();
	  }
	};

    return wrapper;
  }

  public static void classSetUp()
  {
//    littleBobbyTables = new PersonViewDTO(systemRequest);
//    littleBobbyTables.setUsername("Bobby");
//    littleBobbyTables.setPassword("Tables");


	littleBobbyTables = systemRequest.newBusiness(UserInfo.CLASS);
    littleBobbyTables.setValue(UserInfo.USERNAME, "Bobby");
	littleBobbyTables.setValue(UserInfo.PASSWORD, "Tables");
	systemRequest.createBusiness(littleBobbyTables);
  }

  public static void classTearDown()
  {
	systemRequest.delete(littleBobbyTables.getId());
  }

  protected ClientSession createSession(String userName, String password)
  {
    return ClientSession.createUserSession("default", userName, password, new Locale[] { CommonProperties.getDefaultLocale() });
  }

  protected ClientRequestIF getRequest(ClientSession clientSession)
  {
    return clientSession.getRequest();
  }

  public void testEmptyValueNoReadPermissions()
  {
    // Technically you are not supposed to access the business layer in the DTO layer
	MdBusiness propertyMdBusiness = MdBusiness.getMdBusiness(Property.CLASS);

    BusinessDTO testObject = null;
    ClientSession bobbySession = null;
    ClientRequestIF bobbyRequest = null;

    try
    {
      systemRequest.grantTypePermission(littleBobbyTables.getId(), propertyMdBusiness.getId(), Operation.CREATE.name());

      bobbySession = this.createSession("Bobby", "Tables");
      bobbyRequest = getRequest(bobbySession);

      testObject = bobbyRequest.newBusiness(PropertyDTO.CLASS);
      bobbyRequest.createBusiness(testObject);
    }
    // Required attributes should throw read permission exceptions.
    catch (AttributeReadPermissionExceptionDTO e)
    {
    	// this is expected
    }
    catch (Throwable e)
    {
      fail(e.getMessage());
    }
    finally
    {
      systemRequest.revokeTypePermission(littleBobbyTables.getId(), propertyMdBusiness.getId(), Operation.CREATE.name());

      if (bobbySession != null)
      {
        bobbySession.logout();
      }
    }
  }
}
