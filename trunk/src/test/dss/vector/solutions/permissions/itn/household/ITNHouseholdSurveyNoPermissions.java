package dss.vector.solutions.permissions.itn.household;

import java.util.Date;

import com.terraframe.mojo.session.CreatePermissionExceptionDTO;

import dss.vector.solutions.intervention.FeverResponseDTO;
import dss.vector.solutions.intervention.TimeIntervalDTO;
import dss.vector.solutions.intervention.monitor.FreeITNProviderDTO;
import dss.vector.solutions.intervention.monitor.ITNHouseholdSurveyNetDTO;
import dss.vector.solutions.intervention.monitor.ITNHouseholdSurveyNonUseReasonDTO;
import dss.vector.solutions.intervention.monitor.ITNHouseholdSurveyTargetGroupDTO;
import dss.vector.solutions.intervention.monitor.ITNHouseholdSurveyViewDTO;
import dss.vector.solutions.intervention.monitor.ITNRetreatmentPeriodDTO;

public abstract class ITNHouseholdSurveyNoPermissions extends ITNHouseholdSurveyPermissionTest
{
  public void testITNCommunityDistribution()
  {
    Integer netAmount = new Integer(50);
    Integer targetGroupAmount = new Integer(10);
    Integer reasonAmount = new Integer(20);

    ITNHouseholdSurveyViewDTO view = new ITNHouseholdSurveyViewDTO(request);
    view.setStartDate(new Date());
    view.setEndDate(new Date());
    view.setSurveyLocation(geoId);
    view.setAgentFirstName("Justin");
    view.setAgentSurname("Smethie");
    view.setQuestionnaireNumber("30");
    view.setResidents(34);
    view.setPregnantResidents(12);
    view.setChildResidents(2);
    view.setItns(10);
    view.setDamagedItns(2);
    view.setHangingItns(3);
    view.setOtherItns(4);
    view.setMonthReceived(12);
    view.setYearReceived(2009);
    view.setUsedItns(5);
    view.setUsedEveryNight(false);
    view.setNetsObtained(true);
    view.setFreeProvider(FreeITNProviderDTO.getAllActive(request)[0]);
    view.addWashed(FeverResponseDTO.YES);
    view.setWashFrequency(34);
    view.addWashInterval(TimeIntervalDTO.PER_YEAR);
    view.setRetreated(true);
    view.setRetreatmentPeriod(ITNRetreatmentPeriodDTO.getAllActive(request)[0]);

    ITNHouseholdSurveyNetDTO[] nets = view.getITNHouseholdSurveyNets();
    ITNHouseholdSurveyTargetGroupDTO[] targetGroups = view.getITNHouseholdSurveyTargetGroups();
    ITNHouseholdSurveyNonUseReasonDTO[] reasons = view.getITNHouseholdSurveyNonUseReason();

    for (ITNHouseholdSurveyNetDTO rel : nets)
    {
      rel.setAmount(netAmount);
    }

    for (ITNHouseholdSurveyTargetGroupDTO rel : targetGroups)
    {
      rel.setAmount(targetGroupAmount);
    }

    for (ITNHouseholdSurveyNonUseReasonDTO rel : reasons)
    {
      rel.setAmount(reasonAmount);
    }

    try
    {
      view.applyAll(nets, targetGroups, reasons);

      view.deleteConcrete();
      
      fail("Able to create a ITN Household survey without permissions");
    }
    catch (CreatePermissionExceptionDTO e)
    {
      //This is expected
    }
  }
}
