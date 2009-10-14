package dss.vector.solutions.permissions.itn.household;

import java.util.Date;

import dss.vector.solutions.intervention.monitor.ITNHouseholdSurveyDTO;
import dss.vector.solutions.intervention.monitor.ITNHouseholdSurveyNetDTO;
import dss.vector.solutions.intervention.monitor.ITNHouseholdSurveyNonUseReasonDTO;
import dss.vector.solutions.intervention.monitor.ITNHouseholdSurveyTargetGroupDTO;
import dss.vector.solutions.intervention.monitor.ITNHouseholdSurveyViewDTO;
import dss.vector.solutions.ontology.TermDTO;

public abstract class ITNHouseholdSurveyCRUDPermissions extends ITNHouseholdSurveyPermissionTest
{

  public void testITNCommunityDistribution()
  {
    TermDTO term = TermDTO.get(request, termId);
    
    Integer different = new Integer(99);
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
    view.setMonthReceived(11);
    view.setYearReceived(2009);
    view.setUsedItns(5);
    view.setUsedEveryNight(false);
    view.setNetsObtained(true);
    view.setFreeProvider(term);
    view.setWashed(term);
    view.setWashFrequency(34);
    view.setWashInterval(term);
    view.setRetreated(true);
    view.setRetreatmentPeriod(term);

    ITNHouseholdSurveyNetDTO[] nets = view.getITNHouseholdSurveyNets();
    ITNHouseholdSurveyTargetGroupDTO[] targetGroups = view.getITNHouseholdSurveyTargetGroups();
    ITNHouseholdSurveyNonUseReasonDTO[] reasons = view.getITNHouseholdSurveyNonUseReason();

    for (ITNHouseholdSurveyNetDTO rel : nets)
    {
      rel.setAmount(different);
    }

    for (ITNHouseholdSurveyTargetGroupDTO rel : targetGroups)
    {
      rel.setAmount(different);
    }

    for (ITNHouseholdSurveyNonUseReasonDTO rel : reasons)
    {
      rel.setAmount(different);
    }

    view.applyAll(nets, targetGroups, reasons);

    try
    {
      ITNHouseholdSurveyViewDTO edit = ITNHouseholdSurveyDTO.lockView(request, view.getConcreteId());
      edit.setStartDate(new Date());
      edit.setEndDate(new Date());
      edit.setSurveyLocation(geoId);
      edit.setAgentFirstName("Justin");
      edit.setAgentSurname("Smethie");
      edit.setQuestionnaireNumber("30");
      edit.setResidents(34);
      edit.setPregnantResidents(12);
      edit.setChildResidents(2);
      edit.setItns(10);
      edit.setDamagedItns(2);
      edit.setHangingItns(3);
      edit.setOtherItns(4);
      edit.setMonthReceived(2);
      edit.setYearReceived(2007);
      edit.setUsedItns(5);
      edit.setUsedEveryNight(false);
      edit.setNetsObtained(true);
      edit.setFreeProvider(term);
      edit.setWashed(term);
      edit.setWashFrequency(34);
      edit.setWashInterval(term);
      edit.setRetreated(true);
      edit.setRetreatmentPeriod(term);

      ITNHouseholdSurveyNetDTO[] lockedNets = edit.getITNHouseholdSurveyNets();
      ITNHouseholdSurveyTargetGroupDTO[] lockedGroups = edit.getITNHouseholdSurveyTargetGroups();
      ITNHouseholdSurveyNonUseReasonDTO[] lockedReasons = edit.getITNHouseholdSurveyNonUseReason();

      for (ITNHouseholdSurveyNetDTO rel : lockedNets)
      {
        rel.setAmount(netAmount);
      }

      for (ITNHouseholdSurveyTargetGroupDTO rel : lockedGroups)
      {
        rel.setAmount(targetGroupAmount);
      }

      for (ITNHouseholdSurveyNonUseReasonDTO rel : lockedReasons)
      {
        rel.setAmount(reasonAmount);
      }

      edit.applyAll(lockedNets, lockedGroups, lockedReasons);

      ITNHouseholdSurveyViewDTO test = ITNHouseholdSurveyDTO.getView(request, view.getConcreteId());

      assertEquals(edit.getStartDate(), test.getStartDate());
      assertEquals(edit.getEndDate(), test.getEndDate());
      assertEquals(edit.getSurveyLocation(), test.getSurveyLocation());
      assertEquals(edit.getAgentFirstName(), test.getAgentFirstName());
      assertEquals(edit.getAgentSurname(), test.getAgentSurname());
      assertEquals(edit.getQuestionnaireNumber(), test.getQuestionnaireNumber());
      assertEquals(edit.getResidents(), test.getResidents());
      assertEquals(edit.getPregnantResidents(), test.getPregnantResidents());
      assertEquals(edit.getChildResidents(), test.getChildResidents());
      assertEquals(edit.getItns(), test.getItns());
      assertEquals(edit.getDamagedItns(), test.getDamagedItns());
      assertEquals(edit.getHangingItns(), test.getHangingItns());
      assertEquals(edit.getOtherItns(), test.getOtherItns());
      assertEquals(edit.getMonthReceived(), test.getMonthReceived());
      assertEquals(edit.getYearReceived(), test.getYearReceived());
      assertEquals(edit.getUsedItns(), test.getUsedItns());
      assertEquals(edit.getUsedEveryNight(), test.getUsedEveryNight());
      assertEquals(edit.getNetsObtained(), test.getNetsObtained());
      assertEquals(edit.getFreeProvider().getId(), test.getFreeProvider().getId());
      assertEquals(null, test.getBoughtProvider());
      assertEquals(edit.getWashed().getId(), test.getWashed().getId());
      assertEquals(edit.getWashFrequency(), test.getWashFrequency());
      assertEquals(edit.getWashInterval().getId(), test.getWashInterval().getId());
      assertEquals(edit.getRetreated(), test.getRetreated());
      assertEquals(edit.getRetreatmentPeriod().getId(), test.getRetreatmentPeriod().getId());

      for (ITNHouseholdSurveyNetDTO s : test.getITNHouseholdSurveyNets())
        assertEquals(netAmount, s.getAmount());

      for (ITNHouseholdSurveyTargetGroupDTO m : test.getITNHouseholdSurveyTargetGroups())
        assertEquals(targetGroupAmount, m.getAmount());

      for (ITNHouseholdSurveyNonUseReasonDTO m : test.getITNHouseholdSurveyNonUseReason())
        assertEquals(reasonAmount, m.getAmount());
    }
    finally
    {
      view.deleteConcrete();
    }

  }
}
