package dss.vector.solutions.selenium;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Date;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.runwaysdk.generation.loader.LoaderDecorator;
import com.thoughtworks.selenium.DefaultSelenium;
import com.thoughtworks.selenium.Selenium;
import com.thoughtworks.selenium.SeleniumException;

import dss.vector.solutions.TestFixture;
import dss.vector.solutions.TransactionExecuter;
import dss.vector.solutions.entomology.LifeStage;
import dss.vector.solutions.entomology.MosquitoCollectionView;
import dss.vector.solutions.geo.GeoHierarchyView;
import dss.vector.solutions.geo.generated.GeoEntity;

public class PreserveCollectionTest
{
  private static MosquitoCollectionView collection;

  private static GeoEntity              entity;

  private Selenium                      selenium;

  @BeforeClass
  public static void classSetup() throws Exception
  {
    new TransactionExecuter()
    {
      @Override
      protected void executeMethod() throws Exception
      {
        GeoEntity zambia = GeoEntity.searchByGeoId("1107");

        GeoHierarchyView[] universals = GeoHierarchyView.getUrbanHierarchies(zambia.getId());

        String entityType = universals[1].getGeneratedType();

        entity = (GeoEntity) LoaderDecorator.load(entityType).newInstance();
        entity.setEntityName("Test Parent Entity");
        entity.setGeoId("CollectionEntity");
        entity.applyWithParent(zambia.getId(), false, null);

        collection = new MosquitoCollectionView();
        collection.setCollectionMethod(TestFixture.getTerm(MosquitoCollectionView.getCollectionMethodMd()));
        collection.setCollectionDate(new Date());
        collection.setGeoEntity(entity);
        collection.setCollectionId(TestFixture.getRandomId());
        collection.addLifeStage(LifeStage.EGG);
        collection.setAbundance(true);
        collection.apply();
      }
    }.execute();
  }

  @AfterClass
  public static void classTearDown() throws Exception
  {
    new TransactionExecuter()
    {
      @Override
      protected void executeMethod() throws Exception
      {
        collection.deleteConcrete();
        entity.deleteEntity();
      }
    }.execute();
  }

  @Before
  public void setUp() throws Exception
  {
    selenium = new DefaultSelenium("localhost", 4444, "*chrome", "http://127.0.0.1:8080/");
    selenium.start();
  }

  @After
  public void tearDown() throws Exception
  {
    selenium.stop();
  }

  @Test
  public void testADDAPreserveCollection() throws Exception
  {
    try
    {
      selenium.open("/DDMS/com.runwaysdk.defaults.LoginController.logout.mojo");
    }
    catch (SeleniumException e)
    {
      selenium.open("/DDMS/com.runwaysdk.defaults.LoginController.logout.mojo");
    }
    
    selenium.type("username", "ddms");
    selenium.type("password", "ddms");
    selenium.click("submitLogin");
    try
    {
      selenium.waitForPageToLoad("120000");
      selenium.open("/DDMS/dss.vector.solutions.entomology.assay.AdultDiscriminatingDoseAssayController.newInstance.mojo");
      selenium.type("collectionId", collection.getConcreteId());
      selenium.click("name=dss.vector.solutions.entomology.assay.AdultDiscriminatingDoseAssay.form.create.button");
      selenium.waitForPageToLoad("120000");

      assertEquals(collection.getCollectionId(), selenium.getValue("collectionInput"));
    }
    finally
    {
      selenium.click("link=Log out");
      selenium.waitForPageToLoad("120000");
    }
  }

  @Test
  public void testLDDAPreserveCollection() throws Exception
  {
    selenium.open("/DDMS/com.runwaysdk.defaults.LoginController.logout.mojo");
    selenium.type("username", "ddms");
    selenium.type("password", "ddms");
    selenium.click("submitLogin");
    try
    {
      selenium.waitForPageToLoad("120000");
      selenium.open("/DDMS/dss.vector.solutions.entomology.assay.LarvaeDiscriminatingDoseAssayController.newInstance.mojo");
      selenium.type("collectionId", collection.getConcreteId());
      selenium.click("name=dss.vector.solutions.entomology.assay.LarvaeDiscriminatingDoseAssay.form.create.button");
      selenium.waitForPageToLoad("120000");

      assertEquals(collection.getCollectionId(), selenium.getValue("collectionInput"));
    }
    finally
    {
      selenium.click("link=Log out");
      selenium.waitForPageToLoad("120000");
    }
  }

  @Test
  public void testKDAPreserveCollection() throws Exception
  {
    selenium.open("/DDMS/com.runwaysdk.defaults.LoginController.logout.mojo");
    selenium.type("username", "ddms");
    selenium.type("password", "ddms");
    selenium.click("submitLogin");
    try
    {
      selenium.waitForPageToLoad("120000");
      selenium.open("/DDMS/dss.vector.solutions.entomology.assay.KnockDownAssayController.newInstance.mojo");
      selenium.type("collectionId", collection.getConcreteId());
      selenium.click("name=dss.vector.solutions.entomology.assay.KnockDownAssay.form.create.button");
      selenium.waitForPageToLoad("120000");

      assertEquals(collection.getCollectionId(), selenium.getValue("collectionInput"));
    }
    finally
    {
      selenium.click("link=Log out");
      selenium.waitForPageToLoad("120000");
    }
  }
}
