package dss.vector.solutions.permissions.individual.ipt;

import java.util.Date;

import com.terraframe.mojo.ProblemExceptionDTO;
import com.terraframe.mojo.business.ProblemDTOIF;
import com.terraframe.mojo.dataaccess.ProgrammingErrorExceptionDTO;

import dss.vector.solutions.PersonDTO;
import dss.vector.solutions.PersonViewDTO;
import dss.vector.solutions.entomology.SexDTO;
import dss.vector.solutions.intervention.monitor.IndividualIPTCaseDTO;
import dss.vector.solutions.intervention.monitor.IndividualIPTCaseViewDTO;
import dss.vector.solutions.intervention.monitor.IndividualIPTDTO;
import dss.vector.solutions.intervention.monitor.IndividualIPTViewDTO;
import dss.vector.solutions.ontology.MODTO;

public abstract class IndividualIPTCRUDPermissions extends IndividualIPTPermissionTest
{
  public void testCreatePatient()
  {
    PersonViewDTO dto = new PersonViewDTO(request);

    try
    {
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
    }
    catch (ProblemExceptionDTO e)
    {
      for (ProblemDTOIF message : e.getProblems())
        fail(message.getMessage());
    }

    try
    {
      PersonViewDTO update = PersonDTO.lockView(request, dto.getPersonId());
      update.setFirstName("Testy");
      update.setLastName("Test");
      update.setDateOfBirth(new Date());
      update.addSex(SexDTO.MALE);
      update.apply();

      PersonViewDTO test = PersonDTO.getView(request, dto.getPersonId());
      assertEquals(update.getFirstName(), test.getFirstName());
    }
    catch (ProblemExceptionDTO e)
    {
      for (ProblemDTOIF message : e.getProblems())
        fail(message.getMessage());
    }
    catch (ProgrammingErrorExceptionDTO e)
    {
      fail(e.getLocalizedMessage());
    }
    finally
    {
      PersonDTO.lock(request, dto.getPersonId()).delete();
    }
  }

  public void testIptCase()
  {
    PersonViewDTO dto = new PersonViewDTO(request);
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

      IndividualIPTCaseViewDTO view = new IndividualIPTCaseViewDTO(request);
      view.setPatient(patient);
      view.setServiceDate(new Date());
      view.apply();

      try
      {
        IndividualIPTCaseViewDTO test = IndividualIPTCaseDTO.getView(request, view.getConcreteId());

        assertEquals(view.getPatient().getId(), test.getPatient().getId());
        assertEquals(view.getServiceDate(), test.getServiceDate());
      }
      finally
      {
        view.deleteConcrete();
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
      PersonDTO.get(request, dto.getPersonId()).delete();
    }

  }

  public void testIndividualIPT()
  {
    PersonViewDTO dto = new PersonViewDTO(request);
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

      IndividualIPTCaseViewDTO caseView = new IndividualIPTCaseViewDTO(request);
      caseView.setPatient(patient);
      caseView.setServiceDate(new Date());
      caseView.apply();

      try
      {
        IndividualIPTCaseDTO iptCase = IndividualIPTCaseDTO.get(request, caseView.getConcreteId());

        IndividualIPTViewDTO view = new IndividualIPTViewDTO(request);
        view.setFacility(geoId);
        view.setIptCase(iptCase);
        view.setPatientType(MODTO.get(request, termId));
        view.setIsANCVisit(true);
        view.setVisitNumber(MODTO.get(request, termId));
        view.setDoseNumber(MODTO.get(request, termId));
        view.setDoseType(MODTO.get(request, termId));
        view.setRecievedSupplement(true);
        view.setRecievedITN(true);
        view.setNumberOfRecievedITNs(5);
        view.setAdministratorName("Justin");
        view.setAdministratorSurname("Smethie");
        view.apply();

        try
        {
          IndividualIPTViewDTO edit = IndividualIPTDTO.lockView(request, view.getConcreteId());
          edit.setFacility(geoId);
          edit.setIptCase(iptCase);
          edit.setPatientType(MODTO.get(request, termId));
          edit.setIsANCVisit(true);
          edit.setVisitNumber(MODTO.get(request, termId));
          edit.setDoseNumber(MODTO.get(request, termId));
          edit.setDoseType(MODTO.get(request, termId));
          edit.setRecievedSupplement(true);
          edit.setRecievedITN(true);
          edit.setNumberOfRecievedITNs(2);
          edit.setAdministratorName("Justin");
          edit.setAdministratorSurname("Smethie");
          edit.apply();

          IndividualIPTViewDTO test = IndividualIPTDTO.getView(request, view.getConcreteId());

          assertEquals(edit.getFacility(), test.getFacility());
          assertEquals(edit.getIptCase().getId(), test.getIptCase().getId());
          assertEquals(edit.getPatientType().getId(), test.getPatientType().getId());
          assertEquals(edit.getIsANCVisit(), test.getIsANCVisit());
          assertEquals(edit.getVisitNumber().getId(), test.getVisitNumber().getId());
          assertEquals(edit.getDoseNumber().getId(), test.getDoseNumber().getId());
          assertEquals(edit.getDoseType().getId(), test.getDoseType().getId());
          assertEquals(edit.getRecievedSupplement(), test.getRecievedSupplement());
          assertEquals(edit.getRecievedITN(), test.getRecievedITN());
          assertEquals(edit.getNumberOfRecievedITNs(), test.getNumberOfRecievedITNs());
          assertEquals(edit.getAdministratorName(), test.getAdministratorName());
          assertEquals(edit.getAdministratorSurname(), test.getAdministratorSurname());
        }
        finally
        {
          view.deleteConcrete();
        }
      }
      finally
      {
        caseView.deleteConcrete();
      }
    }
    finally
    {
      PersonDTO.get(request, dto.getPersonId()).delete();
    }
  }
}
