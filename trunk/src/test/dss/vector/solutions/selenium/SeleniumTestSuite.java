package dss.vector.solutions.selenium;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;
import org.openqa.selenium.server.SeleniumServer;

@RunWith(Suite.class)
@SuiteClasses( {PreserveCollectionTest.class, PopulationDataTest.class, GeneralSeleniumTest.class})
public class SeleniumTestSuite
{
  private static SeleniumServer server;

  @BeforeClass
  public static void setUp() throws Exception
  {
    server = new SeleniumServer();
    server.start();
  }

  @AfterClass
  public static void tearDown() throws Exception
  {
    server.stop();
  }
}
