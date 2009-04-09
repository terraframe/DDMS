package dss.vector.solutions.entomology;

import java.util.LinkedList;
import java.util.List;

import com.terraframe.mojo.query.OIterator;

import dss.vector.solutions.geo.DeleteEarthException;
import dss.vector.solutions.geo.DuplicateEarthException;
import dss.vector.solutions.geo.DuplicateParentException;
import dss.vector.solutions.geo.GeoEntityView;
import dss.vector.solutions.geo.GeoEntityViewQuery;
import dss.vector.solutions.geo.LocatedInException;
import dss.vector.solutions.geo.generated.AdminPost;
import dss.vector.solutions.geo.generated.Country;
import dss.vector.solutions.geo.generated.District;
import dss.vector.solutions.geo.generated.Earth;
import dss.vector.solutions.geo.generated.GeoEntity;
import dss.vector.solutions.geo.generated.Province;

public class GeoEntityTest extends GeoTest
{
  /**
   * Tests that the default Earth GeoEntity exists.
   */
  public void testEarthExists()
  {
    try
    {
      Earth earth = Earth.getEarthInstance();

      assertNotNull(earth);
    }
    catch (Exception e)
    {
      fail(e.getLocalizedMessage());
    }
  }

  /**
   * Tests that the single instance of Earth cannot be deleted.
   */
  public void testNoDeleteEarth()
  {
    try
    {
      Earth earth = Earth.getEarthInstance();
      earth.delete();
    }
    catch (DeleteEarthException e)
    {
      // Correct exception. Now make sure there is still an Earth instance
      Earth earth = Earth.getEarthInstance();

      assertNotNull(earth);
    }
  }

  /**
   * Tests that only a single instance of earth can exists.
   */
  public void testNoDuplicateEarth()
  {
    try
    {
      new Earth().apply();
      
      fail("Was able to duplicate Earth, which simply cannot happen, you big silly goose.");
    }
    catch (DuplicateEarthException e)
    {
      // success
    }
  }

  /**
   * Tests the basic operations on a GeoEntity.
   */
  public void testGeoEntity()
  {
    List<GeoEntity> testEntities = new LinkedList<GeoEntity>();

    GeoEntity country = null;

    try
    {
      // create
      country = new Country();
      country.setActivated(true);
      country.setEntityName("Country 1");
      country.setGeoId(genGeoId());
      country.apply();
      testEntities.add(country);

      // edit
      String newGeoId = genGeoId();
      country.lock();
      country.setGeoId(newGeoId);
      country.apply();

      assertEquals(false, country.isNew());
      assertEquals(newGeoId, country.getGeoId());
    }
    finally
    {
      deleteAll(testEntities);
    }
  }

  /**
   * Tests that an entity can located within another.
   */
  public void testEntityLocateIn()
  {
    List<GeoEntity> testEntities = new LinkedList<GeoEntity>();

    Country country = null;
    Province province = null;

    try
    {
      country = new Country();
      country.setActivated(true);
      country.setEntityName("Country 1");
      country.setGeoId(genGeoId());
      country.apply();
      testEntities.add(country);

      province = new Province();
      province.setActivated(true);
      province.setEntityName("Province 1");
      province.setGeoId(genGeoId());
      province.applyWithParent(country.getId(), false);
      testEntities.add(province);

      OIterator<? extends GeoEntity> iter = country.getAllContainsGeoEntity();
      try
      {
        int childCount = 0;
        while(iter.hasNext())
        {
          GeoEntity child = iter.next();
          assertEquals(province.getId(), child.getId());
          
          childCount++;
        }
        
        assertEquals(childCount, 1);
      }
      finally
      {
        iter.close();
      }
    }
    finally
    {
      deleteAll(testEntities);
    }
  }
  
  /**
   * Tests putting a GeoEntity as a child not
   * allowed by the GeoHierarhy.
   */
  public void testEntityLocatedInInvalid()
  {
    List<GeoEntity> testEntities = new LinkedList<GeoEntity>();

    Country country = null;
    Province province = null;

    try
    {
      province = new Province();
      province.setActivated(true);
      province.setEntityName("Province 1");
      province.setGeoId(genGeoId());
      province.apply();
      testEntities.add(province);

      country = new Country();
      country.setActivated(true);
      country.setEntityName("Country 1");
      country.setGeoId(genGeoId());
      country.applyWithParent(province.getId(), false);
      testEntities.add(country);
      
      fail("Was able to create a Country within a Province.");
    }
    catch(LocatedInException e)
    {
      // Success
    }
    finally
    {
      deleteAll(testEntities);
    }
  }
  
  /**
   * Tests that a GeoEntity cannot be located in itself.
   */
  public void testEntityNotLocatedInSelf()
  {
    List<GeoEntity> testEntities = new LinkedList<GeoEntity>();
    
    Country country = null;
    Province province = null;
    
    try
    {
      country = new Country();
      country.setActivated(true);
      country.setEntityName("Country 1");
      country.setGeoId(genGeoId());
      country.apply();
      testEntities.add(country);

      province = new Province();
      province.setActivated(true);
      province.setEntityName("Province 1");
      province.setGeoId(genGeoId());
      province.applyWithParent(country.getId(), false);
      testEntities.add(province);
      
      province.applyWithParent(province.getId(), false);
      
      fail("Able to add the same province as its own parent.");
    }
    catch(LocatedInException e)
    {
      // Success
    }
    finally
    {
      deleteAll(testEntities);
    }
  }
  
  /**
   * Tests that a parent cannot have the same child twice as immediate
   * children. Immediate children are the first level of the AllowedIn
   * relationship.
   */
  public void testNoDuplicateImmediateChildren()
  {
    List<GeoEntity> testEntities = new LinkedList<GeoEntity>();

    Country country = null;
    Province province = null;

    try
    {
      country = new Country();
      country.setActivated(true);
      country.setEntityName("Country 1");
      country.setGeoId(genGeoId());
      country.apply();
      testEntities.add(country);

      province = new Province();
      province.setActivated(true);
      province.setEntityName("Province 1");
      province.setGeoId(genGeoId());
      province.applyWithParent(country.getId(), false);
      testEntities.add(province);
      
      province.applyWithParent(country.getId(), true);
      
      fail("A parent was able to have two immediate children.");
    }
    catch(DuplicateParentException e)
    {
      // Success
    }
    finally
    {
      deleteAll(testEntities);
    }    
  }

  /**
   * Tests that a child will be deleted when a parent is deleted (recursively).
   */
  public void testChildDeleteWithParent()
  {
    List<GeoEntity> testEntities = new LinkedList<GeoEntity>();
    
    Country country = null;
    Province province = null;
    District district = null;

    try
    {
      country = new Country();
      country.setActivated(true);
      country.setEntityName("Country 1");
      country.setGeoId(genGeoId());
      country.apply();
      testEntities.add(country);

      province = new Province();
      province.setActivated(true);
      province.setEntityName("Province 1");
      province.setGeoId(genGeoId());
      province.applyWithParent(country.getId(), false);
      testEntities.add(province);
      
      district = new District();
      district.setActivated(true);
      district.setEntityName("District 1");
      district.setGeoId(genGeoId());
      district.applyWithParent(province.getId(), false);
      testEntities.add(district);

      country.delete();
      
      assertEquals(false, exists(country));
      assertEquals(false, exists(province));
      assertEquals(false, exists(district));
    }
    finally
    {
      deleteAll(testEntities);
    }
  }

  /**
   * Tests that an entity's activated status will change when its existing
   * parent changes.
   */
  public void testActivatedForOldParent()
  {
    List<GeoEntity> testEntities = new LinkedList<GeoEntity>();

    Country country = null;
    Province province = null;
    District district = null;

    try
    {
      country = new Country();
      country.setActivated(true);
      country.setEntityName("Country 1");
      country.setGeoId(genGeoId());
      country.apply();
      testEntities.add(country);

      province = new Province();
      province.setActivated(true);
      province.setEntityName("Province 1");
      province.setGeoId(genGeoId());
      province.applyWithParent(country.getId(), false);
      testEntities.add(province);
      
      district = new District();
      district.setActivated(true);
      district.setEntityName("District 1");
      district.setGeoId(genGeoId());
      district.applyWithParent(province.getId(), false);
      testEntities.add(district);

      // flip the activated flag on country then test its children
      country.lock();
      country.setActivated(false);
      country.apply();

      Country cRef = Country.get(country.getId());
      Province pRef = Province.get(province.getId());
      District dRef = District.get(district.getId());
      
      assertEquals(Boolean.FALSE, cRef.getActivated());
      assertEquals(Boolean.FALSE, pRef.getActivated());
      assertEquals(Boolean.FALSE, dRef.getActivated());
    }
    finally
    {
      deleteAll(testEntities);
    }
  }

  /**
   * Tests that an entity's activated status will change when dragged to a new
   * parent with a different status.
   */
  public void testActivatedForNewParent()
  {
    List<GeoEntity> testEntities = new LinkedList<GeoEntity>();

    Country country = null;
    Province province = null;
    District district = null;
    AdminPost adminPost = null;

    try
    {
      country = new Country();
      country.setActivated(true);
      country.setEntityName("Country 1");
      country.setGeoId(genGeoId());
      country.apply();
      testEntities.add(country);

      province = new Province();
      province.setActivated(true);
      province.setEntityName("Province 1");
      province.setGeoId(genGeoId());
      province.applyWithParent(country.getId(), false);
      testEntities.add(province);

      province.lock();
      province.setActivated(false);
      province.apply();
      
      district = new District(); // starts under Country
      district.setActivated(true);
      district.setEntityName("District 1");
      district.setGeoId(genGeoId());
      district.applyWithParent(country.getId(), false);
      testEntities.add(district);
      
      adminPost = new AdminPost();
      adminPost.setActivated(true);
      adminPost.setEntityName("Admin Post 1");
      adminPost.setGeoId(genGeoId());
      adminPost.applyWithParent(district.getId(), false);
      
      district.applyWithParent(province.getId(), false);

      Country cRef = Country.get(country.getId());
      Province pRef = Province.get(province.getId());
      District dRef = District.get(district.getId());
      AdminPost aRef = AdminPost.get(adminPost.getId());
      
      assertEquals(Boolean.TRUE, cRef.getActivated());
      assertEquals(Boolean.FALSE, pRef.getActivated());
      assertEquals(Boolean.FALSE, dRef.getActivated());
      assertEquals(Boolean.FALSE, aRef.getActivated());
    }
    finally
    {
      deleteAll(testEntities);
    }
  }
  
  /**
   * Tests that an entity's activated status will change when copied to a new
   * parent with a different status. The status change should be valid
   * because the copied entity will not have any parents with an active status.
   */
  public void testActivatedWithMultipleParents()
  {
    List<GeoEntity> testEntities = new LinkedList<GeoEntity>();

    Country country = null;
    Province province = null;
    District district = null;
    AdminPost adminPost = null;

    try
    {
      country = new Country();
      country.setActivated(false);
      country.setEntityName("Country 1");
      country.setGeoId(genGeoId());
      country.apply();
      testEntities.add(country);

      province = new Province();
      province.setActivated(false);
      province.setEntityName("Province 1");
      province.setGeoId(genGeoId());
      province.applyWithParent(country.getId(), false);
      testEntities.add(province);
      
      province.lock();
      province.setActivated(true);
      province.apply();
      
      district = new District(); // starts under Country
      district.setActivated(false);
      district.setEntityName("District 1");
      district.setGeoId(genGeoId());
      district.applyWithParent(country.getId(), false);
      testEntities.add(district);
      
      adminPost = new AdminPost();
      adminPost.setActivated(false);
      adminPost.setEntityName("Admin Post 1");
      adminPost.setGeoId(genGeoId());
      adminPost.applyWithParent(district.getId(), false);
      
      // now copy the District under the province whose activates status is true
      district.applyWithParent(province.getId(), true);

      Country cRef = Country.get(country.getId());
      Province pRef = Province.get(province.getId());
      District dRef = District.get(district.getId());
      AdminPost aRef = AdminPost.get(adminPost.getId());
      
      assertEquals(Boolean.FALSE, cRef.getActivated());
      assertEquals(Boolean.TRUE, pRef.getActivated());
      assertEquals(Boolean.TRUE, dRef.getActivated());
      assertEquals(Boolean.TRUE, aRef.getActivated());
    }
    finally
    {
      deleteAll(testEntities);
    }
  }
  
  /**
   * Tests that an entity's activated status will not change when copied to a new
   * parent with a different status. The status should not change because
   * the entity has another parent whose status is true
   */
  public void testActivatedWithMultipleParentsInvalid()
  {
    List<GeoEntity> testEntities = new LinkedList<GeoEntity>();

    Country country = null;
    Province province = null;
    District district = null;
    AdminPost adminPost = null;

    try
    {
      country = new Country();
      country.setActivated(true);
      country.setEntityName("Country 1");
      country.setGeoId(genGeoId());
      country.apply();
      testEntities.add(country);

      province = new Province();
      province.setActivated(true);
      province.setEntityName("Province 1");
      province.setGeoId(genGeoId());
      province.applyWithParent(country.getId(), false);
      testEntities.add(province);
      
      province.lock();
      province.setActivated(false);
      province.apply();
      
      district = new District(); // starts under Country
      district.setActivated(true);
      district.setEntityName("District 1");
      district.setGeoId(genGeoId());
      district.applyWithParent(country.getId(), false);
      testEntities.add(district);
      
      adminPost = new AdminPost();
      adminPost.setActivated(true);
      adminPost.setEntityName("Admin Post 1");
      adminPost.setGeoId(genGeoId());
      adminPost.applyWithParent(district.getId(), false);
      
      district.applyWithParent(province.getId(), true);

      Country cRef = Country.get(country.getId());
      Province pRef = Province.get(province.getId());
      District dRef = District.get(district.getId());
      AdminPost aRef = AdminPost.get(adminPost.getId());
      
      assertEquals(Boolean.TRUE, cRef.getActivated());
      assertEquals(Boolean.FALSE, pRef.getActivated());
      assertEquals(Boolean.TRUE, dRef.getActivated());
      assertEquals(Boolean.TRUE, aRef.getActivated());
    }
    finally
    {
      deleteAll(testEntities);
    }
  }
  
  /**
   * Tests that an entity's activated status will change when
   * its parent's status is changed.
   */
  public void testActivatedWithExistingMultipleParents()
  {
    List<GeoEntity> testEntities = new LinkedList<GeoEntity>();

    Country country = null;
    Province province = null;
    District district = null;
    AdminPost adminPost = null;

    try
    {
      country = new Country();
      country.setActivated(false);
      country.setEntityName("Country 1");
      country.setGeoId(genGeoId());
      country.apply();
      testEntities.add(country);

      province = new Province();
      province.setActivated(false);
      province.setEntityName("Province 1");
      province.setGeoId(genGeoId());
      province.applyWithParent(country.getId(), false);
      testEntities.add(province);
      
      district = new District(); // starts under Country
      district.setActivated(false);
      district.setEntityName("District 1");
      district.setGeoId(genGeoId());
      district.applyWithParent(country.getId(), false);
      testEntities.add(district);
      
      adminPost = new AdminPost();
      adminPost.setActivated(false);
      adminPost.setEntityName("Admin Post 1");
      adminPost.setGeoId(genGeoId());
      adminPost.applyWithParent(district.getId(), false);
      
      district.applyWithParent(province.getId(), true);
      
      // change the Province's status to true, which should
      // flip its children's status too.
      province.appLock();
      province.setActivated(true);
      province.apply();
      

      Country cRef = Country.get(country.getId());
      Province pRef = Province.get(province.getId());
      District dRef = District.get(district.getId());
      AdminPost aRef = AdminPost.get(adminPost.getId());
      
      assertEquals(Boolean.FALSE, cRef.getActivated());
      assertEquals(Boolean.TRUE, pRef.getActivated());
      assertEquals(Boolean.TRUE, dRef.getActivated());
      assertEquals(Boolean.TRUE, aRef.getActivated());
    }
    finally
    {
      deleteAll(testEntities);
    }
  }
  
  /**
   * Tests that an entity's activated status will not change when
   * its parent's status is changed.
   */
  public void testActivatedWithExistingMultipleParentsInvalid()
  {
    List<GeoEntity> testEntities = new LinkedList<GeoEntity>();
    
    Country country = null;
    Province province = null;
    District district = null;
    AdminPost adminPost = null;
    
    try
    {
      country = new Country();
      country.setActivated(true);
      country.setEntityName("Country 1");
      country.setGeoId(genGeoId());
      country.apply();
      testEntities.add(country);
      
      province = new Province();
      province.setActivated(true);
      province.setEntityName("Province 1");
      province.setGeoId(genGeoId());
      province.applyWithParent(country.getId(), false);
      testEntities.add(province);
      
      district = new District(); // starts under Country
      district.setActivated(true);
      district.setEntityName("District 1");
      district.setGeoId(genGeoId());
      district.applyWithParent(country.getId(), false);
      testEntities.add(district);
      
      adminPost = new AdminPost();
      adminPost.setActivated(true);
      adminPost.setEntityName("Admin Post 1");
      adminPost.setGeoId(genGeoId());
      adminPost.applyWithParent(district.getId(), false);
      
      district.applyWithParent(province.getId(), true);
      
      // change the Province's status to false, which
      // should not flip its children's status
      province.appLock();
      province.setActivated(false);
      province.apply();
      
      
      Country cRef = Country.get(country.getId());
      Province pRef = Province.get(province.getId());
      District dRef = District.get(district.getId());
      AdminPost aRef = AdminPost.get(adminPost.getId());
      
      assertEquals(Boolean.TRUE, cRef.getActivated());
      assertEquals(Boolean.FALSE, pRef.getActivated());
      assertEquals(Boolean.TRUE, dRef.getActivated());
      assertEquals(Boolean.TRUE, aRef.getActivated());
    }
    finally
    {
      deleteAll(testEntities);
    }
  }
  
  /**
   * Tests that an entity cannot be its own parent/child
   * in the LocatedIn relationship.
   */
  public void testCannotLocateEntityInItself()
  {
    List<GeoEntity> testEntities = new LinkedList<GeoEntity>();
    Country country = null;
    
    try
    {
      country = new Country();
      country.setActivated(true);
      country.setEntityName("Country 1");
      country.setGeoId(genGeoId());
      country.apply();
      testEntities.add(country);
      
      country.applyWithParent(country.getId(), false);
      
      fail("Able to add an entity as its own parent/child.");
    }
    catch(LocatedInException e)
    {
      // success
    }
    finally
    {
      deleteAll(testEntities);
    }
  }
  
  /**
   * Tests that getting immediate ordered children of an entity.
   */
  public void testGetChildren()
  {
    List<GeoEntity> testEntities = new LinkedList<GeoEntity>();

    Country country = null;
    Province province = null;
    District district = null;
    AdminPost adminPost = null;
    
    try
    {
      
      country = new Country();
      country.setActivated(true);
      country.setEntityName("Country 1");
      country.setGeoId(genGeoId());
      country.apply();
      testEntities.add(country);
      
      province = new Province();
      province.setActivated(true);
      province.setEntityName("Province 1");
      province.setGeoId(genGeoId());
      province.applyWithParent(country.getId(), false);
      testEntities.add(province);
      
      district = new District(); // starts under Country
      district.setActivated(true);
      district.setEntityName("District 1");
      district.setGeoId(genGeoId());
      district.applyWithParent(country.getId(), false);
      testEntities.add(district);
      
      adminPost = new AdminPost();
      adminPost.setActivated(true);
      adminPost.setEntityName("Admin Post 1");
      adminPost.setGeoId(genGeoId());
      adminPost.applyWithParent(country.getId(), false);

      List<GeoEntity> ordered = new LinkedList<GeoEntity>();
      ordered.add(adminPost);
      ordered.add(district);
      ordered.add(province);
      
      GeoEntityViewQuery query = country.getOrderedChildren("");
      
      assertEquals(query.getCount(), 3L);
      
      // make sure the entities are in order
      OIterator<? extends GeoEntityView> iter = query.getIterator();
      
      try
      {
        int count = 0;
        while(iter.hasNext())
        {
          GeoEntityView view = iter.next();
          GeoEntity entity = ordered.get(count);
          
          assertEquals(entity.getId(), view.getGeoEntityId());
          assertEquals(entity.getGeoId(), view.getGeoId());
          assertEquals(entity.getActivated(), view.getActivated());
          assertEquals(entity.getEntityName(), view.getEntityName());
          count++;
        }
      }
      finally
      {
        iter.close();
      }
    }
    finally
    {
      deleteAll(testEntities);
    }
  }
}
