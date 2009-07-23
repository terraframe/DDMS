package dss.vector.solutions.permissions;

import java.util.Date;

import junit.framework.TestCase;

import com.terraframe.mojo.ClientSession;
import com.terraframe.mojo.ProblemExceptionDTO;
import com.terraframe.mojo.business.ProblemDTOIF;
import com.terraframe.mojo.constants.ClientRequestIF;
import com.terraframe.mojo.session.CreatePermissionExceptionDTO;

import dss.vector.solutions.intervention.BloodslideResponseDTO;
import dss.vector.solutions.intervention.FeverResponseDTO;
import dss.vector.solutions.intervention.FeverTreatmentDTO;
import dss.vector.solutions.intervention.HumanSexDTO;
import dss.vector.solutions.intervention.RDTResponseDTO;
import dss.vector.solutions.intervention.RDTResultDTO;
import dss.vector.solutions.intervention.monitor.HouseholdDTO;
import dss.vector.solutions.intervention.monitor.HouseholdNetDTO;
import dss.vector.solutions.intervention.monitor.PersonViewDTO;
import dss.vector.solutions.intervention.monitor.RoofDTO;
import dss.vector.solutions.intervention.monitor.RoofViewDTO;
import dss.vector.solutions.intervention.monitor.SurveyPointDTO;
import dss.vector.solutions.intervention.monitor.SurveyPointViewDTO;
import dss.vector.solutions.intervention.monitor.WallDTO;
import dss.vector.solutions.intervention.monitor.WallViewDTO;
import dss.vector.solutions.surveillance.TreatmentGridDTO;

public abstract class SurveyNoPermissions extends TestCase
{
  protected static ClientSession   systemSession;

  protected static ClientRequestIF systemRequest;

  protected static ClientSession   clientSession;

  protected static ClientRequestIF request;

  protected static String          geoId;

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
    SurveyPointViewDTO view = new SurveyPointViewDTO(systemRequest);
    view.setGeoId(geoId);
    view.setSurveyDate(new Date());
    view.apply();

    WallViewDTO[] walls = WallViewDTO.getAll(request);
    RoofViewDTO[] roofs = RoofViewDTO.getAll(request);

    try
    {
      HouseholdDTO household = new HouseholdDTO(request);
      household.setSurveyPoint(SurveyPointDTO.get(request, view.getConcreteId()));
      household.setHasWindows(true);
      household.setWall(WallDTO.get(request, walls[0].getWallId()));
      household.setRoof(RoofDTO.get(request, roofs[0].getRoofId()));
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
    WallViewDTO[] walls = WallViewDTO.getAll(request);
    RoofViewDTO[] roofs = RoofViewDTO.getAll(request);
    TreatmentGridDTO[] treatments = TreatmentGridDTO.getAll(request);
    FeverTreatmentDTO[] fever = FeverTreatmentDTO.getAllActive(request);

    SurveyPointViewDTO view = new SurveyPointViewDTO(systemRequest);
    view.setGeoId(geoId);
    view.setSurveyDate(new Date());
    view.apply();

    try
    {
      HouseholdDTO household = new HouseholdDTO(systemRequest);
      household.setSurveyPoint(SurveyPointDTO.get(request, view.getConcreteId()));
      household.setHasWindows(true);
      household.setWall(WallDTO.get(request, walls[0].getWallId()));
      household.setRoof(RoofDTO.get(request, roofs[0].getRoofId()));
      household.setHouseholdName("232");
      household.setNets(40);

      HouseholdNetDTO[] nets = household.getHouseholdNets();
      nets[0].setAmount(40);

      household.applyAll(nets);

      try
      {
        PersonViewDTO person = new PersonViewDTO(request);
        person.addBloodslide(BloodslideResponseDTO.DONE);
        person.addFever(FeverResponseDTO.DONT_KNOW);
        person.addMalaria(FeverResponseDTO.DONT_KNOW);
        person.addPayment(FeverResponseDTO.DONT_KNOW);
        person.addPerformedRDT(RDTResponseDTO.NO);
        person.addRDTResult(RDTResultDTO.MALARIAE_POSITIVE);
        person.addSex(HumanSexDTO.FEMALE);
        person.setAnaemiaTreatment(treatments[0]);
        person.setFeverTreatment(fever[0]);
        person.setHousehold(household);
        person.setPersonId("teste3243");
        person.apply();

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
