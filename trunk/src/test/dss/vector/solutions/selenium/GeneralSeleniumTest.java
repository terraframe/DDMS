package dss.vector.solutions.selenium;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.thoughtworks.selenium.DefaultSelenium;
import com.thoughtworks.selenium.Selenium;

import dss.vector.solutions.TestFixture;
import dss.vector.solutions.geo.generated.HealthFacility;

public class GeneralSeleniumTest
{
  private Selenium selenium;

  @Before
  public void setUp() throws Exception
  {
    selenium = new DefaultSelenium("localhost", 4444, "*chrome", "http://127.0.0.1:8080/");
    selenium.start();
  }

  @Test
  public void testAgeGroupInvalidInput() throws Exception
  {
    selenium.open("/DDMS/com.runwaysdk.defaults.LoginController.logout.mojo");
    selenium.type("username", "ddms");
    selenium.type("password", "ddms");
    selenium.click("submitLogin");

    try
    {
      selenium.waitForPageToLoad("30000");
      selenium.open("/DDMS/dss.vector.solutions.surveillance.AggregatedAgeGroupController.newInstance.mojo");
      selenium.type("dto.displayLabel", "Test Age Group");
      selenium.type("dto.startAge", "1.5");
      selenium.type("dto.endAge", "5.5");
      selenium.click("create_button");
      selenium.waitForPageToLoad("30000");

      assertTrue(selenium.isTextPresent("The value [1.5] on attribute [From] does not represent a valid integer."));
      assertTrue(selenium.isTextPresent("The value [5.5] on attribute [To] does not represent a valid integer."));
    }
    finally
    {
      selenium.click("link=Log out");
      selenium.waitForPageToLoad("30000");
    }
  }

  @Test
  public void testAggregatedITN() throws Exception
  {
    List<String> expected = TestFixture.getGeoHierarcyLabels(true, HealthFacility.CLASS);
    List<String> unexpected = TestFixture.getInverseLabels(true, HealthFacility.CLASS);

    selenium.open("/DDMS/com.runwaysdk.defaults.LoginController.logout.mojo");
    selenium.type("username", "ddms");
    selenium.type("password", "ddms");
    selenium.click("submitLogin");

    try
    {
      selenium.waitForPageToLoad("30000");
      selenium.open("/DDMS/dss.vector.solutions.intervention.monitor.ITNDataController.search.mojo");
      selenium.click("geoIdGo");
      Thread.sleep(1000);

      for (String label : expected)
      {
        assertTrue("Expected universal [" + label + "] to be present", selenium.isTextPresent(label));
      }

      for (String label : unexpected)
      {
        assertFalse("Expected universal [" + label + "] to not be present", selenium.isTextPresent(label));
      }

      selenium.click("link=Close");
    }
    finally
    {
      selenium.click("link=Log out");
      selenium.waitForPageToLoad("30000");
    }
  }

  @After
  public void tearDown() throws Exception
  {
    selenium.stop();
  }
}
