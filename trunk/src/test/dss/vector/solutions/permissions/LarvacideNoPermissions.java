package dss.vector.solutions.permissions;

import java.util.Date;

import junit.framework.Test;

import com.terraframe.mojo.DoNotWeave;
import com.terraframe.mojo.session.CreatePermissionExceptionDTO;

import dss.vector.solutions.MDSSRoleInfo;
import dss.vector.solutions.TestFixture;
import dss.vector.solutions.geo.generated.GeoEntityDTO;
import dss.vector.solutions.intervention.monitor.LarvacideDTO;
import dss.vector.solutions.intervention.monitor.LarvacideInstanceViewDTO;
import dss.vector.solutions.ontology.TermDTO;

public class LarvacideNoPermissions extends PermissionTest implements DoNotWeave
{
  public static Test suite()
  {
    return TestFixture.getTestSuite(LarvacideNoPermissions.class, MDSSRoleInfo.ENTOMOLOGIST, MDSSRoleInfo.MANAGER, MDSSRoleInfo.STOCK_STAFF);
  }

  public void testLarvacide()
  {
    try
    {
      LarvacideDTO dto = new LarvacideDTO(request);
      dto.setStartDate(new Date());
      dto.setCompletionDate(new Date());
      dto.setGeoEntity(GeoEntityDTO.searchByGeoId(request, siteGeoId));
      dto.setGeoDescription("Test Value");
      dto.setNatureOfControl(true);
      dto.setPersonCount(34);
      dto.apply();
      
      dto.delete();
      
      fail("Able to create a Larvacide without permissions");
    }
    catch (CreatePermissionExceptionDTO e)
    {
      // This is expected
    }

  }

  public void testLarvacideInstance()
  {
    TermDTO term = TermDTO.get(request, termId);

    LarvacideDTO dto = new LarvacideDTO(systemRequest);
    dto.setStartDate(new Date());
    dto.setCompletionDate(new Date());
    dto.setGeoEntity(GeoEntityDTO.searchByGeoId(request, siteGeoId));
    dto.setGeoDescription("Test Value");
    dto.setNatureOfControl(true);
    dto.setPersonCount(34);
    dto.apply();

    try
    {
      LarvacideInstanceViewDTO view = new LarvacideInstanceViewDTO(request);
      view.setControlId(dto.getId());
      view.setControlMethod(term);
      view.setTarget(term);
      view.setTreated(34);
      view.setUnit(term);
      view.setUnitsUsed(12);
      view.apply();
      
      view.deleteConcrete();
      
      fail("Able to create a Larvacide Instance without permissions");
    }
    catch(CreatePermissionExceptionDTO e)
    {
      //this is expected
    }
    finally
    {
      dto.delete();
    }
  }

}
