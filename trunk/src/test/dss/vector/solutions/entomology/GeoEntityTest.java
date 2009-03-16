package dss.vector.solutions.entomology;

import java.util.LinkedList;
import java.util.List;

import com.terraframe.mojo.query.OIterator;

import dss.vector.solutions.geo.DeleteEarthException;
import dss.vector.solutions.geo.DuplicateEarthException;
import dss.vector.solutions.geo.LocatedInException;
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
      new Earth();
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
      country.setGeoId("123456");
      country.apply();
      testEntities.add(country);

      // edit
      country.lock();
      country.setGeoId("654321");
      country.apply();

      assertEquals(false, country.isNew());
      assertEquals("654321", country.getGeoId());
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
      country.setGeoId("123456");
      country.apply();
      testEntities.add(country);

      province = new Province();
      province.setActivated(true);
      province.setEntityName("Province 1");
      province.setGeoId("654321");
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
      province.setGeoId("654321");
      province.apply();
      testEntities.add(province);

      country = new Country();
      country.setActivated(true);
      country.setEntityName("Country 1");
      country.setGeoId("123456");
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
      country.setGeoId("123456");
      country.apply();
      testEntities.add(country);

      province = new Province();
      province.setActivated(true);
      province.setEntityName("Province 1");
      province.setGeoId("654321");
      province.applyWithParent(country.getId(), false);
      testEntities.add(province);
      
      district = new District();
      district.setActivated(true);
      district.setEntityName("District 1");
      district.setGeoId("123412");
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
      country.setGeoId("123456");
      country.apply();
      testEntities.add(country);

      province = new Province();
      province.setActivated(true);
      province.setEntityName("Province 1");
      province.setGeoId("654321");
      province.applyWithParent(country.getId(), false);
      testEntities.add(province);
      
      district = new District();
      district.setActivated(true);
      district.setEntityName("District 1");
      district.setGeoId("123412");
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

    try
    {
      country = new Country();
      country.setActivated(true); // Default is false
      country.setEntityName("Country 1");
      country.setGeoId("123456");
      country.apply();
      testEntities.add(country);

      province = new Province();
      province.setActivated(true);
      province.setEntityName("Province 1");
      province.setGeoId("654321");
      province.applyWithParent(country.getId(), false);
      testEntities.add(province);
      
      district = new District(); // starts under Country
      district.setActivated(true);
      district.setEntityName("District 1");
      district.setGeoId("123412");
      district.applyWithParent(country.getId(), false);
      testEntities.add(district);
      
      // now move the District under the province whose activates status is false
      province = Province.get(province.getId());
      province.lock();
      province.setActivated(false);
      province.apply();
      
      district.applyWithParent(province.getId(), false);

      Country cRef = Country.get(country.getId());
      Province pRef = Province.get(province.getId());
      District dRef = District.get(district.getId());
      
      assertEquals(Boolean.TRUE, cRef.getActivated());
      assertEquals(Boolean.FALSE, pRef.getActivated());
      assertEquals(Boolean.FALSE, dRef.getActivated());
    }
    finally
    {
      deleteAll(testEntities);
    }
  }
}
