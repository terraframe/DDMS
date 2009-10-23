package dss.vector.solutions.permissions;

import junit.framework.Test;

import com.terraframe.mojo.DoNotWeave;
import com.terraframe.mojo.ProblemExceptionDTO;
import com.terraframe.mojo.business.ProblemDTOIF;

import dss.vector.solutions.MDSSRoleInfo;
import dss.vector.solutions.TestFixture;
import dss.vector.solutions.geo.generated.GeoEntityDTO;
import dss.vector.solutions.surveillance.AggregatedAgeGroupDTO;
import dss.vector.solutions.surveillance.AggregatedCaseDTO;
import dss.vector.solutions.surveillance.AggregatedCaseViewDTO;
import dss.vector.solutions.surveillance.CaseDiagnosticDTO;
import dss.vector.solutions.surveillance.CaseReferralDTO;
import dss.vector.solutions.surveillance.CaseTreatmentDTO;
import dss.vector.solutions.surveillance.CaseTreatmentMethodDTO;
import dss.vector.solutions.surveillance.CaseTreatmentStockDTO;
import dss.vector.solutions.surveillance.PeriodTypeDTO;

public class AggregatedCasesCRUDPermissions extends PermissionTest implements DoNotWeave
{
  public static Test suite()
  {
    return TestFixture.getTestSuite(AggregatedCasesCRUDPermissions.class, MDSSRoleInfo.MDSS_CORRDINATOR, MDSSRoleInfo.DATACAPTURER);
  }

  public void testAncientCase()
  {
    AggregatedAgeGroupDTO[] groups = AggregatedAgeGroupDTO.getAll(request);
    GeoEntityDTO geoEntity = GeoEntityDTO.searchByGeoId(request, siteGeoId);
    PeriodTypeDTO periodType = PeriodTypeDTO.MONTH;

    for (AggregatedAgeGroupDTO ageGroup : groups)
    {
      AggregatedCaseViewDTO view = AggregatedCaseDTO.searchByGeoEntityAndEpiDate(request, geoEntity, periodType, 1, 2008, ageGroup);
      view.setCases(343);

      CaseDiagnosticDTO[] diagnostic = view.getDiagnosticMethods();
      CaseReferralDTO[] referrals = view.getReferrals();
      CaseTreatmentMethodDTO[] methods = view.getTreatmentMethods();
      CaseTreatmentStockDTO[] stocks = view.getTreatmentStocks();
      CaseTreatmentDTO[] treatments = view.getTreatments();

      for (CaseTreatmentStockDTO stock : stocks)
      {
        stock.setOutOfStock(false);
      }

      view.applyAll(treatments, methods, stocks, diagnostic, referrals);

      try
      {
        AggregatedCaseViewDTO c = AggregatedCaseDTO.lockView(request, view.getCaseId());
        c.setCases(23);
        c.apply();

        AggregatedCaseDTO test = AggregatedCaseDTO.get(request, view.getCaseId());

        assertEquals(c.getCases(), test.getCases());
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
        if (view.hasCaseId())
        {
          view.deleteConcrete();
        }
      }
    }
  }
}
