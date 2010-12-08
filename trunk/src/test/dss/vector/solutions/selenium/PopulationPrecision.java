package dss.vector.solutions.selenium;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.server.SeleniumServer;

import com.thoughtworks.selenium.DefaultSelenium;
import com.thoughtworks.selenium.SeleneseTestCase;

public class PopulationPrecision extends SeleneseTestCase
{
  private SeleniumServer server;

  @Before
  public void setUp() throws Exception
  {
    server = new SeleniumServer();
    server.start();

    selenium = new DefaultSelenium("localhost", 4444, "*chrome", "http://127.0.0.1:8080/");
    selenium.start();
  }

  @After
  public void tearDown() throws Exception
  {
    selenium.stop();

    server.stop();
  }

  @Test
  public void testPopulationPrecision() throws Exception
  {
    selenium.open("/DDMS/com.runwaysdk.defaults.LoginController.logout.mojo");
    selenium.type("username", "ddms");
    selenium.type("password", "ddms");
    selenium.click("submitLogin");
    selenium.waitForPageToLoad("30000");
    selenium.open("/DDMS/dss.vector.solutions.general.PopulationDataController.search.mojo");
    selenium.type("geoId", "800801");
    selenium.type("year", "2010");
    selenium.click("search");
    selenium.waitForPageToLoad("30000");
    selenium.click("//tr[@id='yui-rec1']/td[6]/div");
    selenium.type("//div[@id='yui-textboxceditor4-container']/input", "7");
    selenium.click("//tr[@id='yui-rec1']/td[7]/div");
    selenium.type("//input[@type='text']", "7.1");
    selenium.click("//tr[@id='yui-rec2']/td[6]/div");
    selenium.type("//div[@id='yui-textboxceditor4-container']/input", "14");
    selenium.click("//tr[@id='yui-rec2']/td[7]/div");
    selenium.type("//input[@type='text']", "14.1");
    selenium.click("PopulationData");
    selenium.click("PopulationDataSaverows-button");
    Thread.sleep(5000);
    selenium.open("/DDMS/dss.vector.solutions.general.PopulationDataController.search.mojo");
    selenium.type("geoId", "800801");
    selenium.type("year", "2010");
    selenium.click("search");
    selenium.waitForPageToLoad("30000");
    verifyTrue(selenium.isTextPresent("7"));
    verifyTrue(selenium.isTextPresent("7.10"));
    verifyTrue(selenium.isTextPresent("14"));
    verifyTrue(selenium.isTextPresent("14.10"));
    selenium.click("//tr[@id='yui-rec1']/td[6]/div");
    selenium.type("//div[@id='yui-textboxceditor4-container']/input", "");
    selenium.click("//tr[@id='yui-rec1']/td[7]/div");
    selenium.type("//input[@type='text']", "");
    selenium.click("//tr[@id='yui-rec2']/td[6]/div");
    selenium.type("//div[@id='yui-textboxceditor4-container']/input", "");
    selenium.click("//tr[@id='yui-rec2']/td[7]/div");
    selenium.type("//input[@type='text']", "");
    selenium.click("PopulationData");
    selenium.click("PopulationDataSaverows-button");
    Thread.sleep(5000);    
    selenium.click("link=Log out");
    selenium.waitForPageToLoad("30000");
  }
}
