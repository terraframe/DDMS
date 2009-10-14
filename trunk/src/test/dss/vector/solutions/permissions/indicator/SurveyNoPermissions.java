package dss.vector.solutions.permissions.indicator;

import java.util.Date;

import com.terraframe.mojo.ProblemExceptionDTO;
import com.terraframe.mojo.business.ProblemDTOIF;
import com.terraframe.mojo.session.CreatePermissionExceptionDTO;

import dss.vector.solutions.intervention.monitor.HouseholdDTO;
import dss.vector.solutions.intervention.monitor.HouseholdNetDTO;
import dss.vector.solutions.intervention.monitor.PersonViewDTO;
import dss.vector.solutions.intervention.monitor.SurveyPointDTO;
import dss.vector.solutions.intervention.monitor.SurveyPointViewDTO;
import dss.vector.solutions.ontology.TermDTO;

public abstract class SurveyNoPermissions extends IndicatorSuveyPermissionTest
{
  public void testSurveyPoint()
  {
    try
    {
      SurveyPointViewDTO view = new SurveyPointViewDTO(request);
      view.setGeoId(geoId);
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
    view.setGeoId(geoId);
    view.setSurveyDate(new Date());
    view.apply();

    try
    {
      HouseholdDTO household = new HouseholdDTO(request);
      household.setSurveyPoint(SurveyPointDTO.get(request, view.getConcreteId()));
      household.setHasWindows(true);
      household.setWall(term);
      household.setRoof(term);
      household.setHouseholdName("232");
      household.setNets(40);

      HouseholdNetDTO[] nets = household.getHouseholdNets();

      household.applyAll(nets);

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
    view.setGeoId(geoId);
    view.setSurveyDate(new Date());
    view.apply();

    try
    {
      HouseholdDTO household = new HouseholdDTO(systemRequest);
      household.setSurveyPoint(SurveyPointDTO.get(request, view.getConcreteId()));
      household.setHasWindows(true);
      household.setWall(term);
      household.setRoof(term);
      household.setHouseholdName("232");
      household.setNets(40);

      HouseholdNetDTO[] nets = household.getHouseholdNets();
      nets[0].setAmount(40);

      household.applyAll(nets);

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
        person.setHousehold(household);
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
        household.delete();
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
