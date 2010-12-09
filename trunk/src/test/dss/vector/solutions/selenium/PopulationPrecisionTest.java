package dss.vector.solutions.selenium;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.server.SeleniumServer;

import com.runwaysdk.dataaccess.transaction.Transaction;
import com.runwaysdk.session.Request;
import com.thoughtworks.selenium.DefaultSelenium;
import com.thoughtworks.selenium.SeleneseTestCase;

import dss.vector.solutions.general.PopulationData;
import dss.vector.solutions.geo.generated.District;
import dss.vector.solutions.geo.generated.Earth;
import dss.vector.solutions.geo.generated.GeoEntity;
import dss.vector.solutions.geo.generated.Country;

public class PopulationPrecisionTest extends SeleneseTestCase
{
  private SeleniumServer server;

  private Country        country;

  private District       district;

  private PopulationData population;

  @Before
  public void setUp() throws Exception
  {
    buildData();

    server = new SeleniumServer();
    server.start();

    selenium = new DefaultSelenium("localhost", 4444, "*chrome", "http://127.0.0.1:8080/");
    selenium.start();
  }

  @Request
  private void buildData()
  {
    buildDataInTransaction();
  }

  @Transaction
  private void buildDataInTransaction()
  {
    country = new Country();
    country.setEntityName("Test Country");
    country.setGeoId("Country1");
    country.applyWithParent(Earth.getEarthInstance().getId(), false, null);

    district = new District();
    district.setEntityName("Test District");
    district.setGeoId("District1");
    district.applyWithParent(country.getId(), false, null);

    GeoEntity.buildAllPathsFast();

    population = new PopulationData();
    population.setGeoEntity(district);
    population.setYearOfData(2010);
    population.setPopulation(7L);
    population.setGrowthRate(.14D);
    population.setEstimated(false);
    population.apply();
  }

  @After
  public void tearDown() throws Exception
  {
    selenium.stop();

    server.stop();

    deleteData();
  }

  @Request
  private void deleteData()
  {
    deleteDataInTransaction();
  }

  @Transaction
  private void deleteDataInTransaction()
  {
    population.delete();

    district.deleteEntity();
    country.deleteEntity();
  }

  @Test
  public void testPopulationPrecision() throws Exception
  {
    selenium.open("/DDMS/com.runwaysdk.defaults.LoginController.logout.mojo");
    selenium.type("username", "ddms");
    selenium.type("password", "ddms");
    selenium.click("submitLogin");
    selenium.waitForPageToLoad("30000");

    try
    {
      selenium.open("/DDMS/dss.vector.solutions.general.PopulationDataController.search.mojo");
      selenium.type("geoId", country.getGeoId());
      selenium.type("year", population.getYearOfData().toString());
      selenium.click("search");
      selenium.waitForPageToLoad("30000");

      assertTrue("Expected 7 in the DOM", selenium.isTextPresent("7"));
      assertTrue("Expected 14.00 (Precision of two decimal places)", selenium.isTextPresent("14.00"));
      assertFalse("Precision exceeded 2 decimals", selenium.isTextPresent("14.000"));
    }
    finally
    {
      selenium.click("link=Log out");
      selenium.waitForPageToLoad("30000");
    }
  }
}
