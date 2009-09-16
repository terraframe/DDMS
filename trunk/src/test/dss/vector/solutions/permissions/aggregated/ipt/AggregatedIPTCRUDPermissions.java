package dss.vector.solutions.permissions.aggregated.ipt;

import junit.framework.TestCase;

import com.terraframe.mojo.ClientSession;
import com.terraframe.mojo.ProblemExceptionDTO;
import com.terraframe.mojo.business.ProblemDTOIF;
import com.terraframe.mojo.constants.ClientRequestIF;

import dss.vector.solutions.geo.generated.GeoEntityDTO;
import dss.vector.solutions.intervention.monitor.AggregatedIPTDTO;
import dss.vector.solutions.intervention.monitor.AggregatedIPTViewDTO;
import dss.vector.solutions.intervention.monitor.IPTANCVisitDTO;
import dss.vector.solutions.intervention.monitor.IPTDoseDTO;
import dss.vector.solutions.intervention.monitor.IPTPatientsDTO;
import dss.vector.solutions.intervention.monitor.IPTTreatmentDTO;
import dss.vector.solutions.surveillance.PeriodTypeDTO;

public abstract class AggregatedIPTCRUDPermissions extends TestCase
{
  protected static ClientSession   clientSession;

  protected static ClientRequestIF request;

  protected static String          geoId;

  public void testAncientCase()
  {
    GeoEntityDTO geoEntity = GeoEntityDTO.searchByGeoId(request, geoId);
    PeriodTypeDTO periodType = PeriodTypeDTO.MONTH;

    AggregatedIPTViewDTO view = AggregatedIPTDTO.searchByGeoEntityAndEpiDate(request, geoEntity, periodType, 1, 2008);
    view.setNumberNatalCare(30);

    IPTANCVisitDTO[] visits = view.getIPTANCVisits();
    IPTDoseDTO[] doses = view.getIPTDoses();
    IPTPatientsDTO[] patients = view.getIPTPatients();
    IPTTreatmentDTO[] treatments = view.getIPTTreatments();

    view.applyAll(patients, visits, doses, treatments);

    try
    {
      AggregatedIPTViewDTO c = AggregatedIPTDTO.lockView(request, view.getConcreteId());
      c.setNumberNatalCare(20);
      c.apply();

      AggregatedIPTViewDTO test = AggregatedIPTDTO.getView(request, view.getConcreteId());

      assertEquals(c.getNumberNatalCare(), test.getNumberNatalCare());
    }
    catch (ProblemExceptionDTO d)
    {
      for (ProblemDTOIF p : d.getProblems())
      {
        fail(p.getMessage());
      }
    }
    finally
    {
      view.deleteConcrete();
    }
  }
}
