package dss.vector.solutions.permissions.indicator;

import java.util.Date;

import dss.vector.solutions.intervention.RDTResultDTO;
import dss.vector.solutions.intervention.monitor.HouseholdDTO;
import dss.vector.solutions.intervention.monitor.HouseholdNetDTO;
import dss.vector.solutions.intervention.monitor.PersonDTO;
import dss.vector.solutions.intervention.monitor.PersonViewDTO;
import dss.vector.solutions.intervention.monitor.SurveyPointDTO;
import dss.vector.solutions.intervention.monitor.SurveyPointViewDTO;
import dss.vector.solutions.ontology.TermDTO;

public abstract class SurveyCRUDPermissions extends IndicatorSuveyPermissionTest
{
  public void testSurveyPoint()
  {
    SurveyPointViewDTO view = new SurveyPointViewDTO(request);
    view.setGeoId(geoId);
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
      nets[0].setAmount(40);

      household.applyAll(nets);

      try
      {
        household.lock();
        household.setNetsUsed(20);
        HouseholdNetDTO[] lockedNets = household.getHouseholdNets();

        household.applyAll(lockedNets);

        HouseholdDTO test = HouseholdDTO.get(request, household.getId());

        assertEquals(household.getId(), test.getId());
      }
      finally
      {
        household.delete();
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
        person.addRDTResult(RDTResultDTO.MALARIAE_POSITIVE);
        person.setSex(term);
        person.setAnaemiaTreatment(term);
        person.setFeverTreatment(term);
        person.setHousehold(household);
        person.setPersonId("teste3243");
        person.apply();

        try
        {
          PersonViewDTO update = PersonDTO.lockView(request, person.getConcreteId());
          update.apply();

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
        household.delete();
      }
    }
    finally
    {
      view.deleteConcrete();
    }
  }
}
