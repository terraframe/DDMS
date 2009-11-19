package dss.vector.solutions.permissions;

import java.util.Date;

import junit.framework.Test;

import com.terraframe.mojo.DoNotWeave;

import dss.vector.solutions.MDSSRoleInfo;
import dss.vector.solutions.TestFixture;
import dss.vector.solutions.intervention.monitor.HouseholdDTO;
import dss.vector.solutions.intervention.monitor.HouseholdViewDTO;
import dss.vector.solutions.intervention.monitor.PersonDTO;
import dss.vector.solutions.intervention.monitor.PersonViewDTO;
import dss.vector.solutions.intervention.monitor.SurveyPointDTO;
import dss.vector.solutions.intervention.monitor.SurveyPointViewDTO;
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

        try
        {
          PersonViewDTO update = PersonDTO.lockView(request, person.getConcreteId());
          update.applyAll(new TermDTO[]{});

          PersonViewDTO test = PersonDTO.getView(request, person.getConcreteId());

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
