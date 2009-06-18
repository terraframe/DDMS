package dss.vector.solutions.permissions;

import junit.framework.TestCase;

import com.terraframe.mojo.ClientSession;
import com.terraframe.mojo.constants.ClientRequestIF;
import com.terraframe.mojo.session.CreatePermissionExceptionDTO;

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

public abstract class AggregatedCasesNoPermissions extends TestCase
{
  protected static ClientSession   clientSession;

  protected static ClientRequestIF request;

  protected static String          geoId;

  public void testAncientCase()
  {
    AggregatedAgeGroupDTO[] groups = AggregatedAgeGroupDTO.getAll(request);
    GeoEntityDTO geoEntity = GeoEntityDTO.searchByGeoId(request, geoId);
    PeriodTypeDTO periodType = PeriodTypeDTO.MONTH;

    for (AggregatedAgeGroupDTO ageGroup : groups)
    {
      try
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

        fail("Able to create a aggregated case with no permissions");
      }
      catch (CreatePermissionExceptionDTO e)
      {
        // This is expected
      }
    }
  }

}
