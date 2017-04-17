package dss.vector.solutions.util;

import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class PostInstallSetupTest
{
  @Before
  public void setUp() throws Exception
  {
  }

  @After
  public void tearDown() throws Exception
  {
  }

  @Test
  public void testNoArguments()
  {
    try
    {
      PostInstallSetup.main(new String[] {});

      fail("Able to run the post-install setup without the proper arguments");
    }
    catch (Exception e)
    {
      // This is expected
    }
  }

  @Test
  public void testUpdateCSS()
  {
    PostInstallSetup.main(new String[] { "-aDDMS", "-n0", "-itrue", "-c", "-m/home/jsmethie/workspace/manager/", "-t/home/jsmethie/apache-tomcat-6.0.26/" });
  }
}
