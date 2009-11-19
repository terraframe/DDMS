package dss.vector.solutions.permissions;

import java.util.Date;

import junit.framework.Test;

import com.terraframe.mojo.DoNotWeave;
import com.terraframe.mojo.ProblemExceptionDTO;
import com.terraframe.mojo.business.ProblemDTOIF;
import com.terraframe.mojo.session.CreatePermissionExceptionDTO;

import dss.vector.solutions.MDSSRoleInfo;
import dss.vector.solutions.TestFixture;
import dss.vector.solutions.intervention.monitor.HouseholdDTO;
import dss.vector.solutions.intervention.monitor.HouseholdViewDTO;
import dss.vector.solutions.intervention.monitor.PersonViewDTO;
import dss.vector.solutions.intervention.monitor.SurveyPointDTO;
import dss.vector.solutions.intervention.monitor.SurveyPointViewDTO;
import dss.vector.solutions.ontology.TermDTO;

public class SurveyNoPermissions extends PermissionTest implements DoNotWeave
{
  public static Test suite()
  {
    return TestFixture.getTestSuite(SurveyNoPermissions.class, MDSSRoleInfo.ENTOMOLOGIST, MDSSRoleInfo.MANAGER);
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
        PersonViewDTO person = new PersonViewDTO(request);
        person.setBloodslide(term);
        person.setFever(term);
        person.setMalaria(term);
        person.setPayment(term);
        person.setPerformedRDT(term);
        person.setSex(term);
        person.setAnaemiaTreatment(term);
        person.setFeverTreatment(term);
        person.setHousehold(HouseholdDTO.get(request, household.getConcreteId()));
        person.setPersonId("teste3243");
        person.applyAll(new TermDTO[]{term});

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
