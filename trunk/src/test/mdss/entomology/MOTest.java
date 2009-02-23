package mdss.entomology;

import java.util.Locale;

import mdss.mo.CollectionMethod;
import mdss.mo.CollectionMethodDTO;
import mdss.mo.IdentificationMethod;
import mdss.mo.IdentificationMethodDTO;
import mdss.mo.Specie;
import mdss.mo.SpecieDTO;

import junit.extensions.TestSetup;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import com.terraframe.mojo.ClientSession;
import com.terraframe.mojo.constants.ClientRequestIF;
import com.terraframe.mojo.web.WebClientSession;

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
    int expected = 16;
    
    assertNotNull(names);
    assertNotNull(dto);
    assertEquals(expected, names.length);
    assertEquals(expected, dto.length);
  }  
  
  public void testIdentificationMethodGetAllTermNames()
  {
    String[] names = IdentificationMethod.getAllTermNames();
    String[] dto = IdentificationMethodDTO.getAllTermNames(clientRequest);
    int expected = 7;
    
    assertNotNull(names);
    assertNotNull(dto);
    assertEquals(expected, names.length);
    assertEquals(expected, dto.length);
  }
  
  public void testCollectionMethodGetAllTermNames()
  {
    String[] names = CollectionMethod.getAllTermNames();
    String[] dto = CollectionMethodDTO.getAllTermNames(clientRequest);
    int expected = 11;
    
    assertNotNull(names);
    assertNotNull(dto);
    assertEquals(expected, names.length);
    assertEquals(expected, dto.length);
  }
}
