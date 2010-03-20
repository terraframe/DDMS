package dss.vector.solutions.permissions;

import java.math.BigDecimal;
import java.util.Date;

import junit.framework.Test;

import com.runwaysdk.DoNotWeave;
import com.runwaysdk.ProblemExceptionDTO;
import com.runwaysdk.business.ProblemDTOIF;

import dss.vector.solutions.MDSSRoleInfo;
import dss.vector.solutions.MonthOfYearDTO;
import dss.vector.solutions.RefusedResponseDTO;
import dss.vector.solutions.ResponseDTO;
import dss.vector.solutions.TestFixture;
import dss.vector.solutions.intervention.monitor.HouseholdDTO;
import dss.vector.solutions.intervention.monitor.HouseholdViewDTO;
import dss.vector.solutions.intervention.monitor.ITNInstanceDTO;
import dss.vector.solutions.intervention.monitor.ITNInstanceViewDTO;
import dss.vector.solutions.intervention.monitor.SurveyPointDTO;
import dss.vector.solutions.intervention.monitor.SurveyPointViewDTO;
import dss.vector.solutions.intervention.monitor.SurveyedPersonDTO;
import dss.vector.solutions.intervention.monitor.SurveyedPersonViewDTO;
import dss.vector.solutions.ontology.TermDTO;

public class SurveyCRUDPermissions extends PermissionTest implements DoNotWeave
{
  public static Test suite()
  {
    return TestFixture.getTestSuite(SurveyCRUDPermissions.class, MDSSRoleInfo.MDSS_CORRDINATOR, MDSSRoleInfo.DATACAPTURER, MDSSRoleInfo.OPERATIONAL_MANAGER);
  }

  public void testSurveyPoint()
  {
    SurveyPointViewDTO view = new SurveyPointViewDTO(request);
    view.setGeoId(facilityGeoId);
    view.setSurveyDate(new Date());
    view.apply();

    try
    {
      SurveyPointViewDTO update = SurveyPointDTO.lockView(request, view.getConcreteId());
      update.setSurveyDate(new Date());
      update.apply();

      SurveyPointViewDTO test = SurveyPointDTO.getView(request, view.getConcreteId());
      assertEquals(update.getGeoId(), test.getGeoId());
      assertEquals(update.getSurveyDate(), test.getSurveyDate());
    }
    finally
    {
      view.deleteConcrete();
    }
  }

  public void testHousehold()
  {
    TermDTO term = TermDTO.get(request, termId);

    SurveyPointViewDTO view = new SurveyPointViewDTO(request);
    view.setGeoId(facilityGeoId);
    view.setSurveyDate(new Date());
    view.apply();

    try
    {
      HouseholdViewDTO household = new HouseholdViewDTO(request);
      household.setSurveyPoint(SurveyPointDTO.get(request, view.getConcreteId()));
      household.setHasWindows(true);
      household.setWall(term);
      household.setRoof(term);
      household.setHouseholdName("232");
      household.setNets(40);
      household.apply();

      try
      {
        HouseholdViewDTO edit = HouseholdDTO.lockView(request, household.getConcreteId());
        edit.setHasWindows(false);
        edit.apply();


        HouseholdViewDTO test = HouseholdDTO.getView(request, household.getConcreteId());

        assertEquals(household.getConcreteId(), test.getConcreteId());
      }
      finally
      {
        household.deleteConcrete();
      }
    }
    finally
    {
      view.deleteConcrete();
    }
  }
  
  public void testITNInstance()
  {
    TermDTO term = TermDTO.get(request, termId);

    SurveyPointViewDTO point = new SurveyPointViewDTO(request);
    point.setGeoId(facilityGeoId);
    point.setSurveyDate(new Date());
    point.apply();

    try
    {

      HouseholdViewDTO household = new HouseholdViewDTO(request);
      household.setSurveyPoint(SurveyPointDTO.get(request, point.getConcreteId()));
      household.setHasWindows(true);
      household.setWall(term);
      household.setRoof(term);
      household.setHouseholdName("232");
      household.setNets(40);
      household.apply();

      try
      {
        ITNInstanceViewDTO view = new ITNInstanceViewDTO(request);
        view.addMonthReceived(MonthOfYearDTO.JANUARY);
        view.addMonthRetreated(MonthOfYearDTO.APRIL);
        view.addWashed(ResponseDTO.YES);
        view.setDamaged(term);
        view.setHanging(term);
        view.setHousehold(HouseholdDTO.get(request, household.getConcreteId()));
        view.setIsNetUsed(true);
        view.setNetBrand(term);
        view.setNetId("netId1231");
        view.setNotUsedForSleeping(true);
        view.setObtained(term);
        view.setPrice(new BigDecimal(4.50000));
        view.setPurpose(term);
        view.setPurposeComments("Ch30248");
        view.setRetreated(true);
        view.setSleptUnderNet(5L);
        view.setWashFrequency(3);
        view.setWashPeriod(term);
        view.setYearReceived(2007);
        view.setYearRetreated(2009);
        view.apply();

        try
        {
          ITNInstanceViewDTO update = ITNInstanceDTO.lockView(request, view.getConcreteId());
          update.setDamaged(term);
          update.setHanging(term);
          update.setHousehold(HouseholdDTO.get(request, household.getConcreteId()));
          update.setIsNetUsed(true);
          update.setNetBrand(term);
          update.setNetId("netId1231");
          update.setNotUsedForSleeping(true);
          update.setObtained(term);
          update.setPrice(new BigDecimal(4.50000));
          update.setPurpose(term);
          update.setPurposeComments("Ch30248");
          update.setRetreated(true);
          update.setSleptUnderNet(5L);
          update.setWashFrequency(3);
          update.setWashPeriod(term);
          update.setYearReceived(2007);
          update.setYearRetreated(2009);
          update.apply();          

          ITNInstanceViewDTO test = ITNInstanceDTO.getView(request, view.getConcreteId());

          assertEquals(update.getConcreteId(), test.getConcreteId());
          assertTrue(test.getMonthReceived().containsAll(update.getMonthReceived()));
          assertTrue(test.getMonthRetreated().containsAll(update.getMonthRetreated()));
          assertTrue(test.getWashed().containsAll(update.getWashed()));
          assertEquals(update.getDamaged().getId(), test.getDamaged().getId());
          assertEquals(update.getHanging().getId(), test.getHanging().getId());
          assertEquals(update.getHousehold().getId(), test.getHousehold().getId());
          assertEquals(update.getIsNetUsed(), test.getIsNetUsed());
          assertEquals(update.getNetBrand().getId(), test.getNetBrand().getId());
          assertEquals(update.getNotUsedForSleeping(), test.getNotUsedForSleeping());
          assertEquals(update.getObtained().getId(), test.getObtained().getId());
          assertEquals(update.getPrice().doubleValue(), test.getPrice().doubleValue());
          assertEquals(update.getPurpose().getId(), test.getPurpose().getId());
          assertEquals(update.getPurposeComments(), test.getPurposeComments());
          assertEquals(update.getRetreated(), test.getRetreated());
          assertEquals(update.getSleptUnderNet(), test.getSleptUnderNet());
          assertEquals(update.getWashFrequency(), test.getWashFrequency());
          assertEquals(update.getWashPeriod(), test.getWashPeriod());
          assertEquals(update.getYearReceived(), test.getYearReceived());
          assertEquals(update.getYearRetreated(), test.getYearRetreated());          
        }
        finally
        {
          view.deleteConcrete();
        }
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
        household.deleteConcrete();
      }
    }
    finally
    {
      point.deleteConcrete();
    }    
  }

  public void testPerson()
  {
    TermDTO term = TermDTO.get(request, termId);

    SurveyPointViewDTO view = new SurveyPointViewDTO(request);
    view.setGeoId(facilityGeoId);
    view.setSurveyDate(new Date());
    view.apply();

    try
    {

      HouseholdViewDTO household = new HouseholdViewDTO(request);
      household.setSurveyPoint(SurveyPointDTO.get(request, view.getConcreteId()));
      household.setHasWindows(true);
      household.setWall(term);
      household.setRoof(term);
      household.setHouseholdName("232");
      household.setNets(40);
      household.apply();

      try
      {
        SurveyedPersonViewDTO person = new SurveyedPersonViewDTO(request);
        person.addFever(ResponseDTO.YES);
        person.addHaemoglobinMeasured(RefusedResponseDTO.YES);
        person.addMalaria(ResponseDTO.YES);
        person.addPerformedRDT(RefusedResponseDTO.YES);
        person.setAge(26);
        person.setAnaemiaTreatment(term);
        person.setBloodslideDetail(term);
        person.setBloodslideReason(null);
        person.setBloodslideResult(true);
        person.setHaemoglobin(new BigDecimal(99.9));
        person.setHeadOfHousehold(term);
        person.setHousehold(HouseholdDTO.get(request, household.getConcreteId()));
        person.setImmuneCompromised(term);
        person.setIron(true);
        person.setMalariaConformationTechnique(term);
        person.setPayment(term);
        person.setPerformedBloodslide(true);
        person.setPersonId("teste3243");
        person.setPregnant(true);
        person.setRdtDetail(term);
        person.setRdtResult(true);
        person.setRdtTreatment(term);
        person.setSex(term);
        person.applyAll(new TermDTO[]{term}, new TermDTO[]{term});

        try
        {
          SurveyedPersonViewDTO update = SurveyedPersonDTO.lockView(request, person.getConcreteId());
          update.setAge(26);
          update.setAnaemiaTreatment(term);
          update.setBloodslideDetail(term);
          update.setBloodslideReason(null);
          update.setBloodslideResult(true);
          update.setHaemoglobin(new BigDecimal(99.9));
          update.setHeadOfHousehold(term);
          update.setHousehold(HouseholdDTO.get(request, household.getConcreteId()));
          update.setImmuneCompromised(term);
          update.setIron(true);
          update.setMalariaConformationTechnique(term);
          update.setPayment(term);
          update.setPerformedBloodslide(true);
          update.setPersonId("teste3243");
          update.setPregnant(true);
          update.setRdtDetail(term);
          update.setRdtResult(true);
          update.setRdtTreatment(term);
          update.setSex(term);          
          update.applyAll(new TermDTO[]{term}, new TermDTO[]{term});

          SurveyedPersonViewDTO test = SurveyedPersonDTO.getView(request, person.getConcreteId());

          assertEquals(update.getConcreteId(), test.getConcreteId());
          assertTrue(test.getFever().containsAll(update.getFever()));
          assertTrue(test.getHaemoglobinMeasured().containsAll(update.getHaemoglobinMeasured()));
          assertTrue(test.getMalaria().containsAll(update.getMalaria()));
          assertTrue(test.getPerformedRDT().containsAll(update.getPerformedRDT()));
          assertEquals(update.getAge(), test.getAge());
          assertEquals(update.getAnaemiaTreatment().getId(), test.getAnaemiaTreatment().getId());
          assertEquals(update.getBloodslideDetail().getId(), test.getBloodslideDetail().getId());
          assertEquals(update.getBloodslideReason(), test.getBloodslideReason());
          assertEquals(update.getBloodslideResult(), test.getBloodslideResult());
          assertEquals(update.getHaemoglobin(), test.getHaemoglobin());
          assertEquals(update.getHeadOfHousehold().getId(), test.getHeadOfHousehold().getId());
          assertEquals(update.getHousehold().getId(), test.getHousehold().getId());
          assertEquals(update.getImmuneCompromised().getId(), test.getImmuneCompromised().getId());
          assertEquals(update.getIron(), test.getIron());
          assertEquals(update.getMalariaConformationTechnique().getId(), test.getMalariaConformationTechnique().getId());
          assertEquals(update.getPayment().getId(), test.getPayment().getId());
          assertEquals(update.getPerformedBloodslide(), test.getPerformedBloodslide());
          assertEquals(update.getPersonId(), test.getPersonId());
          assertEquals(update.getPregnant(), test.getPregnant());
          assertEquals(update.getRdtDetail().getId(), test.getRdtDetail().getId());
          assertEquals(update.getRdtResult(), test.getRdtResult());
          assertEquals(update.getSex().getId(), test.getSex().getId());
          assertEquals(1, test.getLocations().length);
          assertEquals(1, test.getTreatments().length);
        }
        finally
        {
          person.deleteConcrete();
        }
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
        household.deleteConcrete();
      }
    }
    finally
    {
      view.deleteConcrete();
    }
  }
}
