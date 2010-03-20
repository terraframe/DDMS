package dss.vector.solutions.permissions;

import java.util.Calendar;
import java.util.Date;

import junit.framework.Test;

import com.runwaysdk.DoNotWeave;

import dss.vector.solutions.MDSSRoleInfo;
import dss.vector.solutions.TestFixture;
import dss.vector.solutions.geo.generated.GeoEntityDTO;
import dss.vector.solutions.intervention.monitor.LarvacideDTO;
import dss.vector.solutions.intervention.monitor.LarvacideInstanceDTO;
import dss.vector.solutions.intervention.monitor.LarvacideInstanceViewDTO;
import dss.vector.solutions.ontology.TermDTO;

public class LarvacideCRUDPermissions extends PermissionTest implements DoNotWeave
{
  public static Test suite()
  {
    return TestFixture.getTestSuite(LarvacideCRUDPermissions.class, MDSSRoleInfo.MDSS_CORRDINATOR, MDSSRoleInfo.OPERATIONAL_MANAGER, MDSSRoleInfo.DATACAPTURER);
  }

  public void testLarvacide()
  {
    Calendar calendar = Calendar.getInstance();
    calendar.set(Calendar.DAY_OF_YEAR, -1);

    LarvacideDTO dto = new LarvacideDTO(request);
    dto.setStartDate(new Date());
    dto.setCompletionDate(new Date());
    dto.setGeoEntity(GeoEntityDTO.searchByGeoId(request, siteGeoId));
    dto.setGeoDescription("Test Value");
    dto.setNatureOfControl(true);
    dto.setPersonCount(34);
    dto.apply();

    try
    {
      LarvacideDTO update = LarvacideDTO.lock(request, dto.getId());
      update.setStartDate(calendar.getTime());
      update.setCompletionDate(calendar.getTime());
      update.setGeoDescription("Test Value 2");
      update.setNatureOfControl(false);
      update.setPersonCount(341);
      update.apply();

      LarvacideDTO test = LarvacideDTO.get(request, dto.getId());

      assertEquals(update.getStartDate(), test.getStartDate());
      assertEquals(update.getCompletionDate(), test.getCompletionDate());
      assertEquals(update.getGeoEntity().getId(), test.getGeoEntity().getId());
      assertEquals(update.getGeoDescription(), test.getGeoDescription());
      assertEquals(update.getNatureOfControl(), test.getNatureOfControl());
      assertEquals(update.getPersonCount(), test.getPersonCount());
    }
    finally
    {
      dto.delete();
    }
  }

  public void testLarvacideInstance()
  {
    TermDTO term = TermDTO.get(request, termId);

    LarvacideDTO dto = new LarvacideDTO(request);
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

      try
      {
        LarvacideInstanceViewDTO update = LarvacideInstanceDTO.lockView(request, view.getConcreteId());
        update.setControlMethod(term);
        update.setTarget(term);
        update.setTreated(12);
        update.setUnit(term);
        update.setUnitsUsed(54);
        update.apply();        
        
        LarvacideInstanceViewDTO test = LarvacideInstanceDTO.getView(request, view.getConcreteId());

        assertEquals(update.getControlMethod().getId(), test.getControlMethod().getId());
        assertEquals(update.getTarget().getId(), test.getTarget().getId());
        assertEquals(update.getTreated(), test.getTreated());
        assertEquals(update.getUnit().getId(), test.getUnit().getId());
        assertEquals(update.getUnitsUsed(), test.getUnitsUsed());
      }
      finally
      {
        view.deleteConcrete();
      }
    }
    finally
    {
      dto.delete();
    }
  }
}
