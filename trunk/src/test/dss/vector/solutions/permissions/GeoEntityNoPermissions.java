package dss.vector.solutions.permissions;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import junit.framework.Test;

import com.terraframe.mojo.DoNotWeave;
import com.terraframe.mojo.constants.ClientRequestIF;
import com.terraframe.mojo.generation.loader.LoaderDecorator;
import com.terraframe.mojo.session.CreatePermissionExceptionDTO;
import com.terraframe.mojo.system.metadata.MdBusinessDTO;

import dss.vector.solutions.MDSSRoleInfo;
import dss.vector.solutions.TestFixture;
import dss.vector.solutions.geo.GeoEntityDefinitionDTO;
import dss.vector.solutions.geo.GeoHierarchyDTO;
import dss.vector.solutions.geo.GeoHierarchyQueryDTO;
import dss.vector.solutions.geo.SpatialTypesDTO;
import dss.vector.solutions.geo.generated.GeoEntityDTO;

public class GeoEntityNoPermissions extends PermissionTest implements DoNotWeave
{
  public static Test suite()
  {
    return TestFixture.getTestSuite(GeoEntityNoPermissions.class, MDSSRoleInfo.OPERATIONAL_MANAGER, MDSSRoleInfo.DATACAPTURER, MDSSRoleInfo.MANAGER);
  }

  public void testGeoEntity() 
  {
    GeoHierarchyQueryDTO instances = GeoHierarchyDTO.getAllInstances(systemRequest, null, false, 0, 0);
    List<? extends GeoHierarchyDTO> set = instances.getResultSet();

    for (int i = 0; i < set.size(); i++)
    {
      GeoHierarchyDTO hierarchy = set.get(i);
      MdBusinessDTO mdBusiness = hierarchy.getGeoEntityClass();

      if (!mdBusiness.getIsAbstract() && !mdBusiness.getTypeName().equalsIgnoreCase("earth"))
      {
        Class<?> c = LoaderDecorator.load(mdBusiness.getPackageName() + "." + mdBusiness.getTypeName() + "DTO");

        try
        {

          GeoEntityDTO geoEntity = (GeoEntityDTO) c.getConstructor(ClientRequestIF.class).newInstance(request);
          geoEntity.setGeoId("GeoId" + i);
          geoEntity.setEntityName("EntityName" + i);
          geoEntity.apply();
          
          geoEntity.delete();

          fail("Able to create a new Geo Entity without permissions");
        }
        catch (Exception e)
        {
          if(e instanceof InvocationTargetException)
          {
            // This is expected          

            InvocationTargetException ex = (InvocationTargetException) e;
            assertTrue(ex.getCause() instanceof CreatePermissionExceptionDTO);
          }
          else
          {
            fail(e.getMessage());
          }
        }
      }
    }
  }
  
  public void testUniversal()
  {
    GeoHierarchyQueryDTO instances = GeoHierarchyDTO.getAllInstances(systemRequest, null, false, 0, 0);
    List<? extends GeoHierarchyDTO> set = instances.getResultSet();

    String type = "TestType13";

    GeoEntityDefinitionDTO def1 = new GeoEntityDefinitionDTO(request);
    def1.setTypeName(type);
    def1.setPolitical(false);
    def1.setSprayTargetAllowed(false);
    def1.setDisplayLabel("New Geo Entity Type 1");
    def1.setDescription("New Geo Entity Type Description 1");
    def1.addSpatialType(SpatialTypesDTO.POLYGON);
    def1.setParentGeoHierarchyId(set.get(0).getId());

    try
    {
      GeoHierarchyDTO.defineGeoEntity(request, def1);
      
      fail("Able to create a universal");
    }
    catch(CreatePermissionExceptionDTO e)
    {
      //This is expected
    }
  }

}
