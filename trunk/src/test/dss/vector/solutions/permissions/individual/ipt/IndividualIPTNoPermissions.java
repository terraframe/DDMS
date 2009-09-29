package dss.vector.solutions.permissions.individual.ipt;

import java.util.Date;

import com.terraframe.mojo.session.CreatePermissionExceptionDTO;

import dss.vector.solutions.PersonDTO;
import dss.vector.solutions.PersonViewDTO;
import dss.vector.solutions.entomology.SexDTO;
import dss.vector.solutions.intervention.monitor.DoseGridDTO;
import dss.vector.solutions.intervention.monitor.IndividualIPTCaseDTO;
import dss.vector.solutions.intervention.monitor.IndividualIPTCaseViewDTO;
import dss.vector.solutions.intervention.monitor.IndividualIPTViewDTO;
import dss.vector.solutions.intervention.monitor.PatientGridDTO;
import dss.vector.solutions.intervention.monitor.VisitGridDTO;
import dss.vector.solutions.surveillance.TreatmentGridDTO;

public abstract class IndividualIPTNoPermissions extends IndividualIPTPermissionTest
{
  public void testIndividualIPTCase()
  {
    PersonViewDTO dto = new PersonViewDTO(systemRequest);
    dto.setFirstName("Test");
    dto.setLastName("Test");
    dto.setDateOfBirth(new Date());
    dto.addSex(SexDTO.MALE);
    dto.setIsIPTRecipient(true);

    dto.setIsPatient(false);
    dto.setIsMDSSUser(false);
    dto.setIsITNRecipient(false);
    dto.setIsSprayLeader(false);
    dto.setIsSprayOperator(false);
    dto.apply();

    try
    {
      PersonDTO patient = PersonDTO.get(request, dto.getPersonId());

      try
      {
        IndividualIPTCaseViewDTO view = new IndividualIPTCaseViewDTO(request);
        view.setPatient(patient);
        view.apply();

        view.deleteConcrete();

        fail("Able to create a new individual IPT Case without create permissions");
      }
      catch (CreatePermissionExceptionDTO e)
      {
        // This is expected
      }
    }
    finally
    {
      PersonDTO.get(systemRequest, dto.getPersonId()).delete();
    }
  }

  public void testIndividualIPT()
  {
    PersonViewDTO dto = new PersonViewDTO(systemRequest);
    dto.setFirstName("Test");
    dto.setLastName("Test");
    dto.setDateOfBirth(new Date());
    dto.addSex(SexDTO.MALE);
    dto.setIsIPTRecipient(true);

    dto.setIsPatient(false);
    dto.setIsMDSSUser(false);
    dto.setIsITNRecipient(false);
    dto.setIsSprayLeader(false);
    dto.setIsSprayOperator(false);
    dto.apply();

    try
    {
      PersonDTO patient = PersonDTO.get(systemRequest, dto.getPersonId());

      IndividualIPTCaseViewDTO caseView = new IndividualIPTCaseViewDTO(systemRequest);
      caseView.setPatient(patient);
      caseView.setServiceDate(new Date());
      caseView.apply();

      try
      {
        IndividualIPTCaseDTO iptCase = IndividualIPTCaseDTO.get(request, caseView.getConcreteId());

        try
        {
          IndividualIPTViewDTO view = new IndividualIPTViewDTO(request);
          view.setFacility(geoId);
          view.setIptCase(iptCase);
          view.setAge(26);
          view.setPatientType(PatientGridDTO.getAll(request)[0]);
          view.setIsANCVisit(true);
          view.setVisitNumber(VisitGridDTO.getAll(request)[0]);
          view.setDoseNumber(DoseGridDTO.getAll(request)[0]);
          view.setDoseType(TreatmentGridDTO.getAll(request)[0]);
          view.setRecievedSupplement(true);
          view.setRecievedITN(true);
          view.setNumberOfRecievedITNs(5);
          view.setAdministratorName("Justin");
          view.setAdministratorSurname("Smethie");
          view.apply();

          view.deleteConcrete();

          fail("Able to create a new individual IPT without create permissions");
        }
        catch (CreatePermissionExceptionDTO e)
        {
          // This is expected
        }
      }
      finally
      {
        caseView.deleteConcrete();
      }
    }
    finally
    {
      PersonDTO.get(systemRequest, dto.getPersonId()).delete();
    }
  }
}
