package dss.vector.solutions.permissions;

import java.util.List;

import junit.framework.Test;

import com.runwaysdk.DoNotWeave;
import com.runwaysdk.ProblemExceptionDTO;
import com.runwaysdk.business.ClassQueryDTO;
import com.runwaysdk.business.ComponentDTOIF;
import com.runwaysdk.business.ProblemDTOIF;
import com.runwaysdk.constants.ClientRequestIF;
import com.runwaysdk.generation.loader.LoaderDecorator;
import com.runwaysdk.system.metadata.MdBusinessDTO;

import dss.vector.solutions.MDSSRoleInfo;
import dss.vector.solutions.TestFixture;
import dss.vector.solutions.geo.AllPathsDTO;
import dss.vector.solutions.geo.GeoEntityDefinitionDTO;
import dss.vector.solutions.geo.GeoHierarchyDTO;
import dss.vector.solutions.geo.GeoHierarchyQueryDTO;
import dss.vector.solutions.geo.GeoHierarchyViewDTO;
import dss.vector.solutions.geo.generated.GeoEntityDTO;
import dss.vector.solutions.ontology.TermDTO;

public class GeoEntityCRUDPermissions extends PermissionTest implements DoNotWeave
{
  public static Test suite()
  {
    return TestFixture.getTestSuite(GeoEntityCRUDPermissions.class, MDSSRoleInfo.MDSS_CORRDINATOR, MDSSRoleInfo.ENTOMOLOGIST);
  }

  public void testGeoEntity() throws Exception
  {
    TermDTO term = TermDTO.get(request, termId);

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

        String idLabel = "Geo_Id";
        String entityLabel = "Entity_Name";

        geoEntity.setGeoId(idLabel + i);
        geoEntity.setEntityName(entityLabel + i);
        geoEntity.setTerm(term);
        geoEntity.apply();

        try
        {
          geoEntity.lock();
          geoEntity.setGeoId(idLabel + i + "_" + i);
          geoEntity.apply();

          GeoEntityDTO test = GeoEntityDTO.get(request, geoEntity.getId());

          assertEquals(geoEntity.getGeoId(), test.getGeoId());
        }
        finally
        {
          // Delete the entry in the AllPaths table
          ClassQueryDTO query = request.getQuery(AllPathsDTO.CLASS);

          query.addCondition(AllPathsDTO.PARENTGEOENTITY, "EQ", geoEntity.getId());

          for (ComponentDTOIF component : (List<? extends ComponentDTOIF>) query.getResultSet())
          {
            request.delete(component.getId());
          }

          // Delete the geo entity
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
    String type = "TestType1";

    GeoEntityDefinitionDTO def1 = new GeoEntityDefinitionDTO(request);
    def1.setTypeName(type);
    def1.setPolitical(false);
    def1.setSprayTargetAllowed(false);
    def1.setDisplayLabel("New Geo Entity Type 1");
    def1.setDescription("New Geo Entity Type Description 1");
    def1.setParentGeoHierarchyId(set.get(0).getId());
    def1.setPopulationAllowed(true);

    try
    {
      geoHierarchyId = GeoHierarchyDTO.defineGeoEntity(request, def1);
    }
    catch (ProblemExceptionDTO e)
    {
      for (ProblemDTOIF p : e.getProblems())
      {
        fail(p.getMessage());
      }
    }
    finally
    {
      if (geoHierarchyId != null)
      {
        GeoHierarchyDTO.deleteGeoHierarchy(request, geoHierarchyId);
      }
    }
  }

  public void testUpdateUniversal()
  {
    GeoHierarchyQueryDTO instances = GeoHierarchyDTO.getAllInstances(systemRequest, null, false, 0, 0);
    List<? extends GeoHierarchyDTO> set = instances.getResultSet();

    String geoHierarchyId = null;
    String type = "TestType1";

    GeoEntityDefinitionDTO def1 = new GeoEntityDefinitionDTO(request);
    def1.setTypeName(type);
    def1.setPolitical(false);
    def1.setSprayTargetAllowed(false);
    def1.setDisplayLabel("New Geo Entity Type 1");
    def1.setDescription("New Geo Entity Type Description 1");
    def1.setParentGeoHierarchyId(set.get(0).getId());
    def1.setPopulationAllowed(false);

    try
    {
      geoHierarchyId = GeoHierarchyDTO.defineGeoEntity(request, def1);

      try
      {
        GeoHierarchyDTO.lock(request, geoHierarchyId);
        GeoHierarchyViewDTO view = GeoHierarchyDTO.getViewForGeoHierarchy(request, geoHierarchyId);
        view.setPolitical(false);
        view.setDescription("Test Description 1");
        view.setPopulationAllowed(false);

        GeoHierarchyDTO.updateFromView(request, view);
      }
      catch (Exception e)
      {
        fail(e.getLocalizedMessage());
      }
    }
    catch (ProblemExceptionDTO e)
    {
      for (ProblemDTOIF p : e.getProblems())
      {
        fail(p.getMessage());
      }
    }
    finally
    {
      if (geoHierarchyId != null)
      {
        GeoHierarchyDTO.deleteGeoHierarchy(request, geoHierarchyId);
      }
    }
  }

}
