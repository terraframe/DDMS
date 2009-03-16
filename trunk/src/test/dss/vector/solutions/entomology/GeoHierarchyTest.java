package dss.vector.solutions.entomology;

import java.util.LinkedList;
import java.util.List;

import junit.extensions.TestSetup;
import junit.framework.Test;
import junit.framework.TestSuite;

import com.terraframe.mojo.dataaccess.cache.DataNotFoundException;
import com.terraframe.mojo.generation.loader.LoaderDecorator;
import com.terraframe.mojo.query.QueryFactory;
import com.terraframe.mojo.system.metadata.MdBusiness;
import com.terraframe.mojo.system.metadata.MdBusinessQuery;

import dss.vector.solutions.MDSSInfo;
import dss.vector.solutions.geo.GeoEntityDefinition;
import dss.vector.solutions.geo.GeoHierarchy;
import dss.vector.solutions.geo.GeoHierarchyView;
import dss.vector.solutions.geo.LocatedInException;
import dss.vector.solutions.geo.ModifyHierarchyWithInstancesException;
import dss.vector.solutions.geo.SpatialTypes;
import dss.vector.solutions.geo.generated.Country;
import dss.vector.solutions.geo.generated.Earth;
import dss.vector.solutions.geo.generated.GeoEntity;

public class GeoHierarchyTest extends GeoTest
{
  private static final String NEW_TYPE_NAME = "NewGEType";

  private static final String NEW_TYPE      = MDSSInfo.GENERATED_GEO_PACKAGE + "." + NEW_TYPE_NAME;

  public static Test suite()
  {

    TestSuite suite = new TestSuite();
    suite.addTestSuite(GeoHierarchyTest.class);

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

  public static void classSetUp()
  {
    // Add a new GeoEntity type as a child of Country
    GeoHierarchy countryH = GeoHierarchy.getGeoHierarchyFromType(Country.CLASS);

    GeoEntityDefinition def = new GeoEntityDefinition();
    def.setTypeName(NEW_TYPE_NAME);
    def.setPolitical(true);
    def.setDisplayLabel("New Geo Entity Type");
    def.setDescription("New Geo Entity Type Description");
    def.addSpatialType(SpatialTypes.POLYGON);
    def.setParentGeoHierarchyId(countryH.getId());

    GeoHierarchy.defineGeoEntity(def);
  }

  public static void classTearDown()
  {
    GeoHierarchy newType = GeoHierarchy.getGeoHierarchyFromType(NEW_TYPE);
    newType.delete();
  }

  /**
   * Tests the static (default) hierarchy, mainly that Earth and Country have
   * been initialized which are the default hooks for other GeoEntity subtypes.
   */
  public void testStaticHierarchy()
  {
    GeoHierarchy earthH = GeoHierarchy.getGeoHierarchyFromType(Earth.CLASS);

    // Child should be Country
    List<GeoHierarchy> earthChildren = earthH.getImmediateChildren();

    assertEquals(1, earthChildren.size());

    GeoHierarchy countryH = earthChildren.get(0);
    MdBusiness country = countryH.getGeoEntityClass();
    String type = country.getPackageName() + "." + country.getTypeName();
    assertEquals(Country.CLASS, type);
  }

  /**
   * Tests the new hierarchy entry created in the test setup.
   */
  public void testDynamicHierarchy()
  {
    // cheeck the new hierarchy entry exists and extends Country
    GeoHierarchy newType = GeoHierarchy.getGeoHierarchyFromType(NEW_TYPE);
    List<GeoHierarchy> parents = newType.getImmediateParents();

    assertEquals(1, parents.size());

    MdBusiness parentMd = parents.get(0).getGeoEntityClass();
    String parentType = parentMd.getPackageName() + "." + parentMd.getTypeName();
    assertEquals(Country.CLASS, parentType);
  }

  /**
   * Tests creating a new GeoEntity type and inserting it into the hierarchy.
   * 
   * @throws IllegalAccessException
   * @throws InstantiationException
   */
  public void testNewTypeInstance() throws InstantiationException, IllegalAccessException
  {
    List<GeoEntity> testEntities = new LinkedList<GeoEntity>();
    try
    {
      Country country = new Country();
      country.setEntityName("Country 1");
      country.setActivated(true);
      country.setGeoId("892749");
      country.apply();
      testEntities.add(country);

      Class<?> newTypeClass = LoaderDecorator.load(NEW_TYPE);
      GeoEntity geo = (GeoEntity) newTypeClass.newInstance();
      geo.setEntityName("New Type 1");
      geo.setActivated(true);
      geo.setGeoId("481017");
      geo.applyWithParent(country.getId(), false);
      testEntities.add(geo);

      // check that Country 1 is the only parent
      List<GeoEntity> parents = geo.getImmediateParents();
      assertEquals(1, parents.size());

      GeoEntity parent = parents.get(0);
      assertEquals(country.getId(), parent.getId());
    }
    finally
    {
      deleteAll(testEntities);
    }
  }

  /**
   * Attempts to set an instance of a country as a child of the new GeoEntity
   * type, which is invalid.
   * 
   * @throws IllegalAccessException
   * @throws InstantiationException
   */
  public void testNewTypeInstanceInvalidChild() throws InstantiationException, IllegalAccessException
  {
    List<GeoEntity> testEntities = new LinkedList<GeoEntity>();
    try
    {
      Class<?> newTypeClass = LoaderDecorator.load(NEW_TYPE);
      GeoEntity geo = (GeoEntity) newTypeClass.newInstance();
      geo.setEntityName("New Type 1");
      geo.setActivated(true);
      geo.setGeoId("481017");
      geo.apply();
      testEntities.add(geo);

      Country country = new Country();
      country.setEntityName("Country 1");
      country.setActivated(true);
      country.setGeoId("892749");
      country.applyWithParent(geo.getId(), false);
      testEntities.add(country);

      fail("Added Country as a child of [" + NEW_TYPE_NAME + "] which is not allowed by the hierarchy");
    }
    catch (LocatedInException e)
    {
      // success
    }
    finally
    {
      deleteAll(testEntities);
    }
  }

  /**
   * Tests changing an existing GeoEntity type to a new AllowedIn parent.
   */
  public void testChangeParent()
  {
    String tempTypeName = "TempType";
    String tempType = MDSSInfo.GENERATED_GEO_PACKAGE + "." + tempTypeName;
    String newTypeId = null;

    try
    {
      // Add a new GeoEntity type as a child of Country
      GeoHierarchy countryH = GeoHierarchy.getGeoHierarchyFromType(Country.CLASS);

      GeoEntityDefinition def = new GeoEntityDefinition();
      def.setTypeName(tempTypeName);
      def.setPolitical(true);
      def.setDisplayLabel("New Geo Entity Type");
      def.setDescription("New Geo Entity Type Description");
      def.addSpatialType(SpatialTypes.POLYGON);
      def.setParentGeoHierarchyId(countryH.getId());

      GeoHierarchyView view = GeoHierarchy.defineGeoEntity(def);
      newTypeId = view.getGeoHierarchyId();

      // move TempType to NewGEType
      GeoHierarchy newTypeH = GeoHierarchy.getGeoHierarchyFromType(NEW_TYPE);

      GeoHierarchy.applyExistingWithParent(newTypeId, newTypeH.getId(), false);

      // check the new parent is NewGEType
      GeoHierarchy tempTypeH = GeoHierarchy.getGeoHierarchyFromType(tempType);
      List<GeoHierarchy> parents = tempTypeH.getImmediateParents();

      assertEquals(1, parents.size());

      GeoHierarchy parent = parents.get(0);
      assertEquals(newTypeH.getId(), parent.getId());
    }
    finally
    {
      if (newTypeId != null)
      {
        GeoHierarchy newTypeH = GeoHierarchy.get(newTypeId);
        newTypeH.delete();
      }
    }
  }

  /**
   * Tests adding that adding a new hierarchy entry fails if any non-earth geo
   * entities exist.
   */
  public void testNewParentInvalid()
  {
    List<GeoEntity> testEntities = new LinkedList<GeoEntity>();

    String tempType = "TempType";
    String newTypeId = null;

    try
    {
      // create a geo entity which should make the new entity definition fail
      Country country = new Country();
      country.setEntityName("Country 1");
      country.setGeoId("123321");
      country.setActivated(true);
      country.apply();
      testEntities.add(country);

      // Add a new GeoEntity type as a child of Country
      GeoHierarchy countryH = GeoHierarchy.getGeoHierarchyFromType(Country.CLASS);

      GeoEntityDefinition def = new GeoEntityDefinition();
      def.setTypeName(tempType);
      def.setPolitical(true);
      def.setDisplayLabel("New Geo Entity Type");
      def.setDescription("New Geo Entity Type Description");
      def.addSpatialType(SpatialTypes.POLYGON);
      def.setParentGeoHierarchyId(countryH.getId());

      GeoHierarchyView view = GeoHierarchy.defineGeoEntity(def);
      newTypeId = view.getGeoHierarchyId();

      fail("Able to change hierarchy when geo entities exist.");
    }
    catch (ModifyHierarchyWithInstancesException e)
    {
      // Success
    }
    finally
    {
      deleteAll(testEntities);

      if (newTypeId != null)
      {
        GeoHierarchy newTypeH = GeoHierarchy.get(newTypeId);
        newTypeH.delete();
      }
    }
  }

  /**
   * Tests that a GeoHierarchy cannot change parents if pre-existing (non-earth)
   * geo entities exist.
   */
  public void testChangeParentInvalid()
  {
    List<GeoEntity> testEntities = new LinkedList<GeoEntity>();

    String tempTypeName = "TempType";

    String newTypeId = null;

    try
    {
      // Add a new GeoEntity type as a child of Country
      GeoHierarchy countryH = GeoHierarchy.getGeoHierarchyFromType(Country.CLASS);

      GeoEntityDefinition def = new GeoEntityDefinition();
      def.setTypeName(tempTypeName);
      def.setPolitical(true);
      def.setDisplayLabel("New Geo Entity Type");
      def.setDescription("New Geo Entity Type Description");
      def.addSpatialType(SpatialTypes.POLYGON);
      def.setParentGeoHierarchyId(countryH.getId());

      GeoHierarchyView view = GeoHierarchy.defineGeoEntity(def);
      newTypeId = view.getGeoHierarchyId();

      // create a geo entity which should make the parent change fail
      Country country = new Country();
      country.setEntityName("Country 1");
      country.setGeoId("123321");
      country.setActivated(true);
      country.apply();
      testEntities.add(country);

      // move TempType to NewGEType
      GeoHierarchy newTypeH = GeoHierarchy.getGeoHierarchyFromType(NEW_TYPE);

      GeoHierarchy.applyExistingWithParent(newTypeId, newTypeH.getId(), false);

      fail("Able to change hierarchy when geo entities exist.");
    }
    catch (ModifyHierarchyWithInstancesException e)
    {
      // Success
    }
    finally
    {
      deleteAll(testEntities);

      if (newTypeId != null)
      {
        GeoHierarchy newTypeH = GeoHierarchy.get(newTypeId);
        newTypeH.delete();
      }
    }
  }

  /**
   * Tests deleting a hierarchy entry with instance data and AllowedIn children.
   * 
   * @throws IllegalAccessException
   * @throws InstantiationException
   */
  public void testDeleteHierarchy() throws InstantiationException, IllegalAccessException
  {
    String tempTypeName1 = "TempType1";
    String tempType1 = MDSSInfo.GENERATED_GEO_PACKAGE + "." + tempTypeName1;

    String tempTypeName2 = "TempType2";
    String tempType2 = MDSSInfo.GENERATED_GEO_PACKAGE + "." + tempTypeName2;

    try
    {
      Earth earth = Earth.getEarthInstance();

      // Add a new GeoEntity type as a child of Country
      GeoHierarchy countryH = GeoHierarchy.getGeoHierarchyFromType(Country.CLASS);

      GeoEntityDefinition def1 = new GeoEntityDefinition();
      def1.setTypeName(tempTypeName1);
      def1.setPolitical(true);
      def1.setDisplayLabel("New Geo Entity Type 1");
      def1.setDescription("New Geo Entity Type Description 1");
      def1.addSpatialType(SpatialTypes.POLYGON);
      def1.setParentGeoHierarchyId(countryH.getId());

      GeoHierarchyView view = GeoHierarchy.defineGeoEntity(def1);

      // Add a new GeoEntity type as a child of TempType1
      GeoEntityDefinition def2 = new GeoEntityDefinition();
      def2.setTypeName(tempTypeName2);
      def2.setPolitical(true);
      def2.setDisplayLabel("New Geo Entity Type 2");
      def2.setDescription("New Geo Entity Type Description 2");
      def2.addSpatialType(SpatialTypes.POLYGON);
      def2.setParentGeoHierarchyId(view.getGeoHierarchyId());

      GeoHierarchy.defineGeoEntity(def2);

      // create an instance of each type ... just for fun and append them to
      // Earth
      Class<?> type1Class = LoaderDecorator.load(tempType1);
      GeoEntity type1Instance = (GeoEntity) type1Class.newInstance();
      type1Instance.setEntityName("Type 1");
      type1Instance.setGeoId("923749");
      type1Instance.setActivated(true);
      type1Instance.applyWithParent(earth.getId(), false);

      Class<?> type2Class = LoaderDecorator.load(tempType2);
      GeoEntity type2Instance = (GeoEntity) type2Class.newInstance();
      type2Instance.setEntityName("Type 2");
      type2Instance.setGeoId("921749");
      type2Instance.setActivated(true);
      type2Instance.applyWithParent(earth.getId(), false);

      // make sure neither parent nor child exist after deletion
      GeoHierarchy toDelete = GeoHierarchy.getGeoHierarchyFromType(tempType1);
      
      // we could use delete(), but we want to use the deleteGeoHierarchy MdMethod
      // which is what the UI will call
      GeoHierarchy.deleteGeoHierarchy(toDelete.getId());
      
      // see if the GeoHierarchies exist and the MdBusinesses they wrap
      try
      {
        GeoHierarchy.getGeoHierarchyFromType(tempType1);
        fail("Able to retrieve ["+tempType1+"] even though it was deleted.");
      }
      catch(DataNotFoundException e)
      {
        // this okay
      }
      try
      {
        GeoHierarchy.getGeoHierarchyFromType(tempType2);
        fail("Able to retrieve ["+tempType2+"] even though it was deleted.");
      }
      catch(DataNotFoundException e)
      {
        // this okay
      }
      
      QueryFactory f = new QueryFactory();
      MdBusinessQuery q = new MdBusinessQuery(f);
      q.WHERE(q.getTypeName().IN(new String[]{tempTypeName1, tempTypeName2}));
      
      if(q.getCount() > 0)
      {
        fail("MdBusinesses associated with GeoHierarchies were deleted.");
      }
    }
    finally
    {
      // now delete the types if they still exist (they shouldn't, but just to be safe ...)
      try
      {
        GeoHierarchy type2 = GeoHierarchy.getGeoHierarchyFromType(tempType2);
        if (type2 != null)
        {
          type2.delete();
        }
      }
      catch (DataNotFoundException e)
      {
        // this is okay
      }

      try
      {
        GeoHierarchy type1 = GeoHierarchy.getGeoHierarchyFromType(tempType1);
        if (type1 != null)
        {
          type1.delete();
        }
      }
      catch (DataNotFoundException e)
      {
        // this is okay
      }
    }
  }
}
