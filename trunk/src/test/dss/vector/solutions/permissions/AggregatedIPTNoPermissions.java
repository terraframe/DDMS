package dss.vector.solutions.permissions;

import junit.framework.Test;

import com.terraframe.mojo.DoNotWeave;
import com.terraframe.mojo.session.CreatePermissionExceptionDTO;

import dss.vector.solutions.MDSSRoleInfo;
import dss.vector.solutions.TestFixture;
import dss.vector.solutions.geo.generated.GeoEntityDTO;
import dss.vector.solutions.intervention.monitor.AggregatedIPTDTO;
import dss.vector.solutions.intervention.monitor.AggregatedIPTViewDTO;
import dss.vector.solutions.intervention.monitor.IPTANCVisitDTO;
import dss.vector.solutions.intervention.monitor.IPTDoseDTO;
import dss.vector.solutions.intervention.monitor.IPTPatientsDTO;
import dss.vector.solutions.intervention.monitor.IPTTreatmentDTO;
import dss.vector.solutions.surveillance.PeriodTypeDTO;

public class AggregatedIPTNoPermissions extends PermissionTest implements DoNotWeave
{
  public static Test suite()
  {
    return TestFixture.getTestSuite(AggregatedIPTNoPermissions.class, MDSSRoleInfo.ENTOMOLOGIST, MDSSRoleInfo.MANAGER);
  }

  public void testAncientCase()
  {
    GeoEntityDTO geoEntity = GeoEntityDTO.searchByGeoId(request, facilityGeoId);
    PeriodTypeDTO periodType = PeriodTypeDTO.MONTH;
    try
    {

      AggregatedIPTViewDTO view = AggregatedIPTDTO.searchByGeoEntityAndEpiDate(request, geoEntity, periodType, 1, 2008);
      view.setNumberNatalCare(30);

      IPTANCVisitDTO[] visits = view.getIPTANCVisits();
      IPTDoseDTO[] doses = view.getIPTDoses();
      IPTPatientsDTO[] patients = view.getIPTPatients();
      IPTTreatmentDTO[] treatments = view.getIPTTreatments();

      view.applyAll(patients, visits, doses, treatments);

      fail("Able to creat an Aggregated IPT without permissions");
    }
    catch (CreatePermissionExceptionDTO d)
    {
      //This is expected
    }
  }
}
