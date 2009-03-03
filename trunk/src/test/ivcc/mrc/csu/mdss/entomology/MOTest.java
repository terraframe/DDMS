package ivcc.mrc.csu.mdss.entomology;

import ivcc.mrc.csu.mdss.mo.CollectionMethod;
import ivcc.mrc.csu.mdss.mo.CollectionMethodDTO;
import ivcc.mrc.csu.mdss.mo.IdentificationMethod;
import ivcc.mrc.csu.mdss.mo.IdentificationMethodDTO;
import ivcc.mrc.csu.mdss.mo.Specie;
import ivcc.mrc.csu.mdss.mo.SpecieDTO;

import java.util.Locale;

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
    Specie[] names = Specie.getAll();
    SpecieDTO[] dto = SpecieDTO.getAll(clientRequest);
    int expected = 16;
    
    assertNotNull(names);
    assertNotNull(dto);
    assertEquals(expected, names.length);
    assertEquals(expected, dto.length);
  }  
  
  public void testIdentificationMethodGetAllTermNames()
  {
    IdentificationMethod[] names = IdentificationMethod.getAll();
    IdentificationMethodDTO[] dto = IdentificationMethodDTO.getAll(clientRequest);
    int expected = 7;
    
    assertNotNull(names);
    assertNotNull(dto);
    assertEquals(expected, names.length);
    assertEquals(expected, dto.length);
  }
  
  public void testCollectionMethodGetAllTermNames()
  {
    CollectionMethod[] names = CollectionMethod.getAll();
    CollectionMethodDTO[] dto = CollectionMethodDTO.getAll(clientRequest);
    int expected = 11;
    
    assertNotNull(names);
    assertNotNull(dto);
    assertEquals(expected, names.length);
    assertEquals(expected, dto.length);
  }
}
