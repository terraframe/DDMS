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
import dss.vector.solutions.geo.AllowedInSelfException;
import dss.vector.solutions.geo.GeoEntityDefinition;
import dss.vector.solutions.geo.GeoHierarchy;
import dss.vector.solutions.geo.GeoHierarchyView;
import dss.vector.solutions.geo.LocatedInException;
import dss.vector.solutions.geo.ModifyHierarchyWithInstancesException;
import dss.vector.solutions.geo.SpatialTypeDefinedException;
import dss.vector.solutions.geo.SpatialTypeRequiredException;
import dss.vector.solutions.geo.SpatialTypes;
import dss.vector.solutions.geo.generated.Country;
import dss.vector.solutions.geo.generated.Earth;
import dss.vector.solutions.geo.generated.GeoEntity;

public class GeoHierarchyTest extends GeoTest
{
  private static final String NEW_TYPE_NAME = "NewGEType";

  private static final String NEW_TYPE      = MDSSInfo.GENERATED_GEO_PACKAGE + "." + NEW_TYPE_NAME;

  private static Earth        earth         = Earth.getEarthInstance();

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
    GeoHierarchy geoEntityH = GeoHierarchy.getGeoHierarchyFromType(GeoEntity.CLASS);

    // Add a new GeoEntity type as a child of Country
    GeoHierarchy countryH = GeoHierarchy.getGeoHierarchyFromType(Country.CLASS);

    GeoEntityDefinition def = new GeoEntityDefinition();
    def.setTypeName(NEW_TYPE_NAME);
    def.setPolitical(true);
    def.setSprayTargetAllowed(true);
    def.setDisplayLabel("New Geo Entity Type");
    def.setDescription("New Geo Entity Type Description");
    def.addSpatialType(SpatialTypes.POLYGON);
    def.setParentGeoHierarchyId(countryH.getId());
    def.setParentTypeGeoHierarchyId(geoEntityH.getId());

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
      country.setGeoId(genGeoId());
      country.apply();
      testEntities.add(country);

      Class<?> newTypeClass = LoaderDecorator.load(NEW_TYPE);
      GeoEntity geo = (GeoEntity) newTypeClass.newInstance();
      geo.setEntityName("New Type 1");
      geo.setActivated(true);
      geo.setGeoId(genGeoId());
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
   * Tests that a new universal that extends GeoEntity must define a geometry.
   */
  public void testSpatialTypeRequiredException()
  {
    String geoHierarchyId = null;

    try
    {
      GeoHierarchy earthH = GeoHierarchy.getGeoHierarchyFromType(Earth.CLASS);

      GeoEntityDefinition def = new GeoEntityDefinition();
      def.setTypeName("TempTypeInvalid");
      def.setPolitical(true);
      def.setSprayTargetAllowed(true);
      def.setDisplayLabel("New Geo Entity Type");
      def.setDescription("New Geo Entity Type Description");
      def.setParentGeoHierarchyId(earthH.getId());
      def.setParentTypeGeoHierarchyId(null); // will extend GeoEntity

      geoHierarchyId = GeoHierarchy.defineGeoEntity(def);

      fail("Able to create a new universal without a geometry that extends GeoEntity.");
    }
    catch (SpatialTypeRequiredException e)
    {
      // success
    }
    finally
    {
      if (geoHierarchyId != null)
      {
        GeoHierarchy.deleteGeoHierarchy(geoHierarchyId);
      }
    }
  }

  /**
   * Tests that a new universal that extends a non-GeoEntity class must not
   * define a geometry.
   */
  public void testSpatialTypeDefinedException()
  {
    String geoHierarchyId = null;

    try
    {
      GeoHierarchy earthH = GeoHierarchy.getGeoHierarchyFromType(Earth.CLASS);
      GeoHierarchy newType = GeoHierarchy.getGeoHierarchyFromType(NEW_TYPE);

      GeoEntityDefinition def = new GeoEntityDefinition();
      def.setTypeName("TempTypeInvalid");
      def.setPolitical(true);
      def.setSprayTargetAllowed(true);
      def.setDisplayLabel("New Geo Entity Type");
      def.setDescription("New Geo Entity Type Description");
      def.addSpatialType(SpatialTypes.LINE); // this will cause an error
      def.setParentGeoHierarchyId(earthH.getId());
      def.setParentTypeGeoHierarchyId(newType.getId());

      geoHierarchyId = GeoHierarchy.defineGeoEntity(def);
      
      fail("Able to create a new universal that overwrote its parent geometry.");
    }
    catch (SpatialTypeDefinedException e)
    {
      // success
    }
    finally
    {
      if (geoHierarchyId != null)
      {
        GeoHierarchy.deleteGeoHierarchy(geoHierarchyId);
      }
    }
  }

  /**
   * Tests a universal class that is AllowedIn Earth and is_a NewType.
   * 
   * @throws IllegalAccessException
   * @throws InstantiationException
   */
  public void testExtendsNewType() throws InstantiationException, IllegalAccessException
  {
    List<GeoEntity> testEntities = new LinkedList<GeoEntity>();

    GeoHierarchy earthH = GeoHierarchy.getGeoHierarchyFromType(Earth.CLASS);
    GeoHierarchy newType = GeoHierarchy.getGeoHierarchyFromType(NEW_TYPE);

    String tempTypeName = "TempTypeExt";
    String tempType = MDSSInfo.GENERATED_GEO_PACKAGE + "." + tempTypeName;
    String newTypeId = null;

    try
    {
      GeoEntityDefinition def = new GeoEntityDefinition();
      def.setTypeName(tempTypeName);
      def.setPolitical(true);
      def.setSprayTargetAllowed(true);
      def.setDisplayLabel("New Geo Entity Type");
      def.setDescription("New Geo Entity Type Description");
      def.setParentGeoHierarchyId(earthH.getId());
      def.setParentTypeGeoHierarchyId(newType.getId());

      String geoHierarchyId = GeoHierarchy.defineGeoEntity(def);

      GeoHierarchyView view = GeoHierarchy.getViewForGeoHierarchy(geoHierarchyId);
      newTypeId = view.getGeoHierarchyId();

      // create an instance of the new type and check the is_a relationships.
      Class<?> tempClass = LoaderDecorator.load(tempType);
      GeoEntity geo = (GeoEntity) tempClass.newInstance();
      geo.setEntityName("Temp Type Ext 1");
      geo.setActivated(true);
      geo.setGeoId(genGeoId());
      geo.apply();
      testEntities.add(geo);

      Class<?> newTypeClass = LoaderDecorator.load(NEW_TYPE);

      assertTrue(tempClass.isInstance(geo));
      assertTrue(newTypeClass.isInstance(geo));
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
   * Tests that an exception is thrown when a universal is added as its
   * own parent.
   */
  public void testAllowedInSelfException()
  {
    String geoHierarchyId = null;
    
    try
    {
      GeoHierarchy earthH = GeoHierarchy.getGeoHierarchyFromType(Earth.CLASS);
      GeoHierarchy newType = GeoHierarchy.getGeoHierarchyFromType(NEW_TYPE);
      
      GeoEntityDefinition def = new GeoEntityDefinition();
      def.setTypeName("TempTypeInvalid");
      def.setPolitical(true);
      def.setSprayTargetAllowed(true);
      def.setDisplayLabel("New Geo Entity Type");
      def.setDescription("New Geo Entity Type Description");
      def.setParentGeoHierarchyId(earthH.getId());
      def.setParentTypeGeoHierarchyId(newType.getId());
      geoHierarchyId = GeoHierarchy.defineGeoEntity(def);

      GeoHierarchy.applyExistingWithParent(geoHierarchyId, geoHierarchyId, false);
    }
    catch(AllowedInSelfException e)
    {
      // success
    }
    finally
    {
      if(geoHierarchyId != null)
      {
        GeoHierarchy.deleteGeoHierarchy(geoHierarchyId);
      }
    }
  }
  
  /**
   * Tests that a child cannot have the same immediate parent.
   */
  public void testDuplicateParentException()
  {
    String geoHierarchyId = null;
    
    try
    {
      GeoHierarchy earthH = GeoHierarchy.getGeoHierarchyFromType(Earth.CLASS);
      GeoHierarchy newType = GeoHierarchy.getGeoHierarchyFromType(NEW_TYPE);
      
      GeoEntityDefinition def = new GeoEntityDefinition();
      def.setTypeName("TempTypeInvalid");
      def.setPolitical(true);
      def.setSprayTargetAllowed(true);
      def.setDisplayLabel("New Geo Entity Type");
      def.setDescription("New Geo Entity Type Description");
      def.setParentGeoHierarchyId(earthH.getId());
      def.setParentTypeGeoHierarchyId(newType.getId());
      geoHierarchyId = GeoHierarchy.defineGeoEntity(def);
      
      GeoHierarchy.applyExistingWithParent(geoHierarchyId, earthH.getId(), false);
    }
    catch(AllowedInSelfException e)
    {
      // success
    }
    finally
    {
      if(geoHierarchyId != null)
      {
        GeoHierarchy.deleteGeoHierarchy(geoHierarchyId);
      }
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
      geo.setGeoId(genGeoId());
      geo.apply();
      testEntities.add(geo);

      Country country = new Country();
      country.setEntityName("Country 1");
      country.setActivated(true);
      country.setGeoId(genGeoId());
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

    GeoHierarchy geoEntityH = GeoHierarchy.getGeoHierarchyFromType(GeoEntity.CLASS);

    try
    {
      // Add a new GeoEntity type as a child of Country
      GeoHierarchy countryH = GeoHierarchy.getGeoHierarchyFromType(Country.CLASS);

      GeoEntityDefinition def = new GeoEntityDefinition();
      def.setTypeName(tempTypeName);
      def.setPolitical(true);
      def.setSprayTargetAllowed(true);
      def.setDisplayLabel("New Geo Entity Type");
      def.setDescription("New Geo Entity Type Description");
      def.addSpatialType(SpatialTypes.POLYGON);
      def.setParentGeoHierarchyId(countryH.getId());
      def.setParentTypeGeoHierarchyId(geoEntityH.getId());

      String geoHierarchyId = GeoHierarchy.defineGeoEntity(def);
      GeoHierarchyView view = GeoHierarchy.getViewForGeoHierarchy(geoHierarchyId);
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
   * Tests that a GeoHierarchy cannot change parents if pre-existing (non-earth)
   * if geoentities of the new type.
   * 
   * @throws IllegalAccessException
   * @throws InstantiationException
   */
  public void testChangeParentInvalid() throws InstantiationException, IllegalAccessException
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
      def.setSprayTargetAllowed(true);
      def.setDisplayLabel("New Geo Entity Type");
      def.setDescription("New Geo Entity Type Description");
      def.addSpatialType(SpatialTypes.POLYGON);
      def.setParentGeoHierarchyId(countryH.getId());
      def.setParentTypeGeoHierarchyId(null);

      String geoHierarchyId = GeoHierarchy.defineGeoEntity(def);
      GeoHierarchyView view = GeoHierarchy.getViewForGeoHierarchy(geoHierarchyId);
      newTypeId = view.getGeoHierarchyId();

      // create a geo entity which should make the parent change fail
      Class<?> newTypeClass = LoaderDecorator.load(MDSSInfo.GENERATED_GEO_PACKAGE + "." + tempTypeName);
      GeoEntity geo = (GeoEntity) newTypeClass.newInstance();
      geo.setEntityName("New Type 1");
      geo.setActivated(true);
      geo.setGeoId(genGeoId());
      geo.applyWithParent(earth.getId(), false);
      testEntities.add(geo);

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
    GeoHierarchy geoEntityH = GeoHierarchy.getGeoHierarchyFromType(GeoEntity.CLASS);

    String tempTypeName1 = "TempType1";
    String tempType1 = MDSSInfo.GENERATED_GEO_PACKAGE + "." + tempTypeName1;

    String tempTypeName2 = "TempType2";
    String tempType2 = MDSSInfo.GENERATED_GEO_PACKAGE + "." + tempTypeName2;

    try
    {
      // Add a new GeoEntity type as a child of Country
      GeoHierarchy countryH = GeoHierarchy.getGeoHierarchyFromType(Country.CLASS);

      GeoEntityDefinition def1 = new GeoEntityDefinition();
      def1.setTypeName(tempTypeName1);
      def1.setPolitical(true);
      def1.setSprayTargetAllowed(true);
      def1.setDisplayLabel("New Geo Entity Type 1");
      def1.setDescription("New Geo Entity Type Description 1");
      def1.addSpatialType(SpatialTypes.POLYGON);
      def1.setParentGeoHierarchyId(countryH.getId());
      def1.setParentTypeGeoHierarchyId(geoEntityH.getId());

      String geoHierarchyId = GeoHierarchy.defineGeoEntity(def1);
      GeoHierarchyView view = GeoHierarchy.getViewForGeoHierarchy(geoHierarchyId);

      // Add a new GeoEntity type as a child of TempType1
      GeoEntityDefinition def2 = new GeoEntityDefinition();
      def2.setTypeName(tempTypeName2);
      def2.setPolitical(true);
      def2.setSprayTargetAllowed(true);
      def2.setDisplayLabel("New Geo Entity Type 2");
      def2.setDescription("New Geo Entity Type Description 2");
      def2.addSpatialType(SpatialTypes.POLYGON);
      def2.setParentGeoHierarchyId(view.getGeoHierarchyId());
      def2.setParentTypeGeoHierarchyId(geoEntityH.getId());

      GeoHierarchy.defineGeoEntity(def2);

      // create an instance of each type ... just for fun and append them to
      // Earth
      Class<?> type1Class = LoaderDecorator.load(tempType1);
      GeoEntity type1Instance = (GeoEntity) type1Class.newInstance();
      type1Instance.setEntityName("Type 1");
      type1Instance.setGeoId(genGeoId());
      type1Instance.setActivated(true);
      type1Instance.applyWithParent(earth.getId(), false);

      Class<?> type2Class = LoaderDecorator.load(tempType2);
      GeoEntity type2Instance = (GeoEntity) type2Class.newInstance();
      type2Instance.setEntityName("Type 2");
      type2Instance.setGeoId(genGeoId());
      type2Instance.setActivated(true);
      type2Instance.applyWithParent(earth.getId(), false);

      // make sure neither parent nor child exist after deletion
      GeoHierarchy toDelete = GeoHierarchy.getGeoHierarchyFromType(tempType1);

      // we could use delete(), but we want to use the deleteGeoHierarchy
      // MdMethod
      // which is what the UI will call
      GeoHierarchy.deleteGeoHierarchy(toDelete.getId());

      // see if the GeoHierarchies exist and the MdBusinesses they wrap
      try
      {
        GeoHierarchy.getGeoHierarchyFromType(tempType1);
        fail("Able to retrieve [" + tempType1 + "] even though it was deleted.");
      }
      catch (DataNotFoundException e)
      {
        // this okay
      }
      try
      {
        GeoHierarchy.getGeoHierarchyFromType(tempType2);
        fail("Able to retrieve [" + tempType2 + "] even though it was deleted.");
      }
      catch (DataNotFoundException e)
      {
        // this okay
      }

      QueryFactory f = new QueryFactory();
      MdBusinessQuery q = new MdBusinessQuery(f);
      q.WHERE(q.getTypeName().IN(new String[] { tempTypeName1, tempTypeName2 }));

      if (q.getCount() > 0)
      {
        fail("MdBusinesses associated with GeoHierarchies were deleted.");
      }
    }
    finally
    {
      // now delete the types if they still exist (they shouldn't, but just to
      // be safe ...)
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
