package dss.vector.solutions.selenium;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.runwaysdk.dataaccess.transaction.Transaction;
import com.runwaysdk.generation.loader.LoaderDecorator;
import com.runwaysdk.query.QueryFactory;
import com.runwaysdk.session.Request;
import com.thoughtworks.selenium.DefaultSelenium;
import com.thoughtworks.selenium.Selenium;

import dss.vector.solutions.general.PopulationData;
import dss.vector.solutions.general.PopulationDataQuery;
import dss.vector.solutions.geo.GeoHierarchyView;
import dss.vector.solutions.geo.generated.GeoEntity;

public class PopulationDataTest
{
  private Selenium selenium;

  private GeoEntity      parentEntity;

  private GeoEntity      childEntity;

  private GeoEntity      siblingEntity;

  private PopulationData population;

  @Before
  public void setUp() throws Exception
  {
    buildData();

    selenium = new DefaultSelenium("localhost", 4444, "*chrome", "http://127.0.0.1:8080/");
    selenium.start();
  }

  @Request
  private void buildData() throws Exception
  {
    buildDataInTransaction();
  }

  @Transaction
  private void buildDataInTransaction() throws Exception
  {
    GeoEntity zambia = GeoEntity.searchByGeoId("1107");

    GeoHierarchyView[] universals = GeoHierarchyView.getUrbanHierarchies(zambia.getId());

    String parentType = universals[1].getGeneratedType();
    String childType = universals[2].getGeneratedType();

    parentEntity = (GeoEntity) LoaderDecorator.load(parentType).newInstance();
    parentEntity.setEntityName("Test Parent Entity");
    parentEntity.setGeoId("ParentId1");
    parentEntity.applyWithParent(zambia.getId(), false, null);

    childEntity = (GeoEntity) LoaderDecorator.load(childType).newInstance();
    childEntity.setEntityName("Test Child Entity");
    childEntity.setGeoId("ChildId1");
    childEntity.applyWithParent(parentEntity.getId(), false, null);

    siblingEntity = (GeoEntity) LoaderDecorator.load(childType).newInstance();
    siblingEntity.setEntityName("Test Child Entity 2");
    siblingEntity.setGeoId("ChildId2");
    siblingEntity.applyWithParent(parentEntity.getId(), false, null);

    GeoEntity.buildAllPathsFast();

    population = new PopulationData();
    population.setGeoEntity(childEntity);
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
    PopulationDataQuery query = new PopulationDataQuery(new QueryFactory());
    query.WHERE(query.getGeoEntity().EQ(parentEntity));
    query.OR(query.getGeoEntity().EQ(childEntity));
    query.OR(query.getGeoEntity().EQ(siblingEntity));

    List<? extends PopulationData> list = query.getIterator().getAll();

    for (PopulationData data : list)
    {
      data.delete();
    }

    childEntity.deleteEntity();
    siblingEntity.deleteEntity();
    parentEntity.deleteEntity();
  }
  
  @Test
  public void testParentPresent() throws Exception
  {
    selenium.open("/DDMS/com.runwaysdk.defaults.LoginController.logout.mojo");
    selenium.type("username", "ddms");
    selenium.type("password", "ddms");
    selenium.click("submitLogin");
    selenium.waitForPageToLoad("30000");
    
    try
    {
      selenium.open("/DDMS/dss.vector.solutions.general.PopulationDataController.search.mojo");
      selenium.type("geoId", parentEntity.getGeoId());
      selenium.type("year", population.getYearOfData().toString());
      selenium.click("search");
      selenium.waitForPageToLoad("30000");
      
      String text = parentEntity.getLabel(); 

      assertEquals(text, selenium.getText("link=" + text));
      assertFalse(selenium.isTextPresent("An unspecified error has occurred"));      
    }
    finally
    {
      selenium.click("link=Log out");
      selenium.waitForPageToLoad("30000");
    }
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
      selenium.type("geoId", parentEntity.getGeoId());
      selenium.type("year", population.getYearOfData().toString());
      selenium.click("search");
      selenium.waitForPageToLoad("30000");

      assertTrue("Expected 7 in the DOM", selenium.isTextPresent("7"));
      assertTrue("Expected 14.00 (Precision of two decimal places)", selenium.isTextPresent("14.00"));
      assertFalse("Precision exceeded 2 decimals", selenium.isTextPresent("14.000"));
      assertFalse(selenium.isTextPresent("An unspecified error has occurred"));
    }
    finally
    {
      selenium.click("link=Log out");
      selenium.waitForPageToLoad("30000");
    }
  }

  @Test
  public void testPopulationDoubleApply() throws Exception
  {
    Integer year = population.getYearOfData() - 1;

    selenium.open("/DDMS/com.runwaysdk.defaults.LoginController.logout.mojo");
    selenium.type("username", "ddms");
    selenium.type("password", "ddms");
    selenium.click("submitLogin");
    selenium.waitForPageToLoad("30000");

    try
    {
      selenium.open("/DDMS/dss.vector.solutions.general.PopulationDataController.search.mojo");
      selenium.type("geoId", parentEntity.getGeoId());
      selenium.type("year", year.toString());
      selenium.click("search");
      selenium.waitForPageToLoad("30000");

      selenium.click("//tr[@id='yui-rec0']/td[6]/div");
      selenium.type("//div[@id='yui-textboxceditor4-container']/input", "344");
      selenium.click("//div[11]");
      selenium.click("//tr[@id='yui-rec0']/td[7]/div");
      selenium.type("//input[@type='text']", "2.21");
      selenium.click("//div[11]");
      selenium.click("PopulationDataSaverows-button");

      Thread.sleep(2000L);

      selenium.click("//tr[@id='yui-rec1']/td[6]/div");
      selenium.type("//div[@id='yui-textboxceditor4-container']/input", "123");
      selenium.click("//div[11]");
      selenium.click("//tr[@id='yui-rec1']/td[7]/div");
      selenium.type("//input[@type='text']", "2.14");
      selenium.click("//div[11]");
      selenium.click("PopulationDataSaverows-button");

      Thread.sleep(2000L);

      selenium.open("/DDMS/dss.vector.solutions.general.PopulationDataController.search.mojo");
      selenium.type("geoId", parentEntity.getGeoId());
      selenium.type("year", year.toString());
      selenium.click("search");
      selenium.waitForPageToLoad("30000");

      assertTrue("Expecting text 344", selenium.isTextPresent("344"));
      assertTrue("Expecting text 2.21", selenium.isTextPresent("2.21"));
      assertTrue("Expecting text 123", selenium.isTextPresent("123"));
      assertTrue("Expecting text 2.14", selenium.isTextPresent("2.14"));
      assertFalse(selenium.isTextPresent("An unspecified error has occurred"));      
    }
    finally
    {
      selenium.click("link=Log out");
      selenium.waitForPageToLoad("30000");
    }
  }
}
