package mdss.entomology;

import java.util.Arrays;
import java.util.Locale;

import com.terraframe.mojo.ClientSession;
import com.terraframe.mojo.constants.ClientConstants;
import com.terraframe.mojo.constants.ClientRequestIF;
import com.terraframe.mojo.web.WebClientSession;

import junit.extensions.TestSetup;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class MOTest extends TestCase
{
  private static ClientSession clientSession;
  private static ClientRequestIF clientRequest;
  
  public static Test suite()
  {
    TestSuite suite = new TestSuite();
    suite.addTestSuite(MOTest.class);

    TestSetup wrapper = new TestSetup(suite)
    {
      protected void setUp()
      {
        classSetUp();
      }

      protected void tearDown()
      {
        classTearDown();
      }

    };

    return wrapper;
  }

  protected static void classTearDown()
  {
    clientSession.logout();
  }

  protected static void classSetUp()
  {
    clientSession = WebClientSession.createUserSession("SYSTEM", "SYSTEM", Locale.US);
    clientRequest = clientSession.getRequest();
  }

  
  public void testSpecieGetAllTermNames()
  {
    String[] names = Specie.getAllTermNames();
    String[] dto = SpecieDTO.getAllTermNames(clientRequest);
    
    assertNotNull(names);
    assertNotNull(dto);
    assertEquals(3, names.length);
//    assertEquals(3, dto.length);
  }  
}
