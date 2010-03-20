package dss.vector.solutions.permissions;

import java.math.BigDecimal;
import java.util.Date;

import junit.framework.Test;

import com.runwaysdk.DoNotWeave;
import com.runwaysdk.ProblemExceptionDTO;
import com.runwaysdk.business.ProblemDTOIF;
import com.runwaysdk.session.CreatePermissionExceptionDTO;

import dss.vector.solutions.MDSSRoleInfo;
import dss.vector.solutions.MonthOfYearDTO;
import dss.vector.solutions.RefusedResponseDTO;
import dss.vector.solutions.ResponseDTO;
import dss.vector.solutions.TestFixture;
import dss.vector.solutions.intervention.monitor.HouseholdDTO;
import dss.vector.solutions.intervention.monitor.HouseholdViewDTO;
import dss.vector.solutions.intervention.monitor.ITNInstanceViewDTO;
import dss.vector.solutions.intervention.monitor.SurveyPointDTO;
import dss.vector.solutions.intervention.monitor.SurveyPointViewDTO;
import dss.vector.solutions.intervention.monitor.SurveyedPersonViewDTO;
import dss.vector.solutions.ontology.TermDTO;

public class SurveyNoPermissions extends PermissionTest implements DoNotWeave
{
  public static Test suite()
  {
    return TestFixture.getTestSuite(SurveyNoPermissions.class, MDSSRoleInfo.ENTOMOLOGIST, MDSSRoleInfo.MANAGER, MDSSRoleInfo.STOCK_STAFF);
  }

  public void testSurveyPoint()
  {
    try
    {
      SurveyPointViewDTO view = new SurveyPointViewDTO(request);
      view.setGeoId(facilityGeoId);
      view.setSurveyDate(new Date());
      view.apply();

      fail("Able to create a survey point without permissions");
    }
    catch (CreatePermissionExceptionDTO e)
    {
      // This is expected
    }
  }

  public void testHousehold()
  {
    TermDTO term = TermDTO.get(systemRequest, termId);

    SurveyPointViewDTO view = new SurveyPointViewDTO(systemRequest);
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

      fail("Able to create a household without permissions");
    }
    catch (CreatePermissionExceptionDTO e)
    {
      // This is expected
    }
    finally
    {
      view.deleteConcrete();
    }
  }

  public void testITNInstance()
  {
    TermDTO term = TermDTO.get(systemRequest, termId);
    
    SurveyPointViewDTO point = new SurveyPointViewDTO(systemRequest);
    point.setGeoId(facilityGeoId);
    point.setSurveyDate(new Date());
    point.apply();
    
    try
    {
      HouseholdViewDTO household = new HouseholdViewDTO(systemRequest);
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
        
        fail("Able to create an ITN instance without permissions");
      }
      catch (CreatePermissionExceptionDTO e)
      {
        // This is expected
      }
      finally
      {
        household.deleteConcrete();
      }
    }
    catch (ProblemExceptionDTO e)
    {
      for(ProblemDTOIF p : e.getProblems())
      {
        fail(p.getMessage());
      }
    }
    finally
    {
      point.deleteConcrete();
    }
  }
  
  public void testPerson()
  {
    TermDTO term = TermDTO.get(systemRequest, termId);

    SurveyPointViewDTO view = new SurveyPointViewDTO(systemRequest);
    view.setGeoId(facilityGeoId);
    view.setSurveyDate(new Date());
    view.apply();

    try
    {
      HouseholdViewDTO household = new HouseholdViewDTO(systemRequest);
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

        fail("Able to create a person without permissions");
      }
      catch (CreatePermissionExceptionDTO e)
      {
        // This is expected
      }
      finally
      {
        household.deleteConcrete();
      }
    }
    catch (ProblemExceptionDTO e)
    {
      for(ProblemDTOIF p : e.getProblems())
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
