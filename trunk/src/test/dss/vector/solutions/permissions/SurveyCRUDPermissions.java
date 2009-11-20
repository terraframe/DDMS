package dss.vector.solutions.permissions;

import java.math.BigDecimal;
import java.util.Date;

import junit.framework.Test;

import com.terraframe.mojo.DoNotWeave;

import dss.vector.solutions.MDSSRoleInfo;
import dss.vector.solutions.RefusedResponseDTO;
import dss.vector.solutions.ResponseDTO;
import dss.vector.solutions.TestFixture;
import dss.vector.solutions.intervention.monitor.HouseholdDTO;
import dss.vector.solutions.intervention.monitor.HouseholdViewDTO;
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
        person.setAge(26);
        person.setAnaemiaTreatment(term);
        person.setBloodslideDetail(term);
        person.setBloodslideReason(term);
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
          update.addFever(ResponseDTO.YES);
          update.addHaemoglobinMeasured(RefusedResponseDTO.YES);
          update.addMalaria(ResponseDTO.YES);
          update.setAge(26);
          update.setAnaemiaTreatment(term);
          update.setBloodslideDetail(term);
          update.setBloodslideReason(term);
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
          update.applyAll(new TermDTO[]{}, new TermDTO[]{});

          SurveyedPersonViewDTO test = SurveyedPersonDTO.getView(request, person.getConcreteId());

          assertEquals(update.getConcreteId(), test.getConcreteId());
        }
        finally
        {
          person.delete();
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
