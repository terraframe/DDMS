package dss.vector.solutions.permissions;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import junit.framework.TestCase;

import com.terraframe.mojo.ClientSession;
import com.terraframe.mojo.ProblemExceptionDTO;
import com.terraframe.mojo.business.ProblemDTOIF;
import com.terraframe.mojo.constants.ClientRequestIF;
import com.terraframe.mojo.generation.loader.LoaderDecorator;
import com.terraframe.mojo.system.metadata.MdBusinessDTO;

import dss.vector.solutions.geo.GeoEntityDefinitionDTO;
import dss.vector.solutions.geo.GeoHierarchyDTO;
import dss.vector.solutions.geo.GeoHierarchyQueryDTO;
import dss.vector.solutions.geo.SpatialTypesDTO;
import dss.vector.solutions.geo.generated.GeoEntityDTO;

public abstract class GeoEntityCRUDPermissions extends TestCase
{
  protected static ClientSession   clientSession;

  protected static ClientRequestIF request;

  protected static ClientSession   systemSession;

  protected static ClientRequestIF systemRequest;

  public void testGeoEntity() throws IllegalArgumentException, SecurityException,
      InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchMethodException
  {
    GeoHierarchyQueryDTO instances = GeoHierarchyDTO.getAllInstances(systemRequest, null, false, 0, 0);
    List<? extends GeoHierarchyDTO> set = instances.getResultSet();

    for (int i = 0; i < set.size(); i++)
    {
      GeoHierarchyDTO hierarchy = set.get(i);
      MdBusinessDTO mdBusiness = hierarchy.getGeoEntityClass();

      if (!mdBusiness.getIsAbstract() && !mdBusiness.getTypeName().equalsIgnoreCase("earth"))
      {
        Class<?> c = LoaderDecorator.load(mdBusiness.getPackageName() + "." + mdBusiness.getTypeName()
            + "DTO");

        GeoEntityDTO geoEntity = (GeoEntityDTO) c.getConstructor(ClientRequestIF.class).newInstance(
            request);
        geoEntity.setGeoId("GeoId" + i);
        geoEntity.setEntityName("EntityName" + i);
        geoEntity.apply();

        try
        {
          geoEntity.lock();
          geoEntity.setGeoId("GeoId" + i + "_" + i);
          geoEntity.apply();

          GeoEntityDTO test = GeoEntityDTO.get(request, geoEntity.getId());

          assertEquals(geoEntity.getGeoId(), test.getGeoId());
        }
        finally
        {
          geoEntity.delete();
        }
      }
    }
  }

  public void testUniversal()
  {
    GeoHierarchyQueryDTO instances = GeoHierarchyDTO.getAllInstances(systemRequest, null, false, 0, 0);
    List<? extends GeoHierarchyDTO> set = instances.getResultSet();

    String geoHierarchyId = null;
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
      geoHierarchyId = GeoHierarchyDTO.defineGeoEntity(request, def1);
    }
    catch(ProblemExceptionDTO e)
    {
      for(ProblemDTOIF p : e.getProblems())
      {
        fail(p.getMessage());
      }
    }
    finally
    {      
      if(geoHierarchyId != null)
      {
        GeoHierarchyDTO.get(request, geoHierarchyId).delete();      
      }
    }
  }

}
