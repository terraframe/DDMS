package dss.vector.solutions.selenium;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.thoughtworks.selenium.DefaultSelenium;
import com.thoughtworks.selenium.SeleneseTestCase;

public class AgeGroupInvalidInputTest extends SeleneseTestCase
{
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
    selenium.waitForPageToLoad("30000");
    selenium.open("/DDMS/dss.vector.solutions.surveillance.AggregatedAgeGroupController.newInstance.mojo");
    selenium.type("dto.displayLabel", "Test Age Group");
    selenium.type("dto.startAge", "1.5");
    selenium.type("dto.endAge", "5.5");
    selenium.click("create_button");
    selenium.waitForPageToLoad("30000");
    verifyTrue(selenium.isTextPresent("The value [1.5] on attribute [From] does not represent a valid integer."));
    verifyTrue(selenium.isTextPresent("The value [5.5] on attribute [To] does not represent a valid integer."));
    selenium.click("link=Log out");
    selenium.waitForPageToLoad("30000");
  }

  @After
  public void tearDown() throws Exception
  {
    selenium.stop();
  }
}
