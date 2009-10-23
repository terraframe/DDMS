package dss.vector.solutions.permissions;

import java.util.Calendar;
import java.util.Date;

import junit.framework.Test;

import com.terraframe.mojo.DoNotWeave;
import com.terraframe.mojo.ProblemExceptionDTO;
import com.terraframe.mojo.business.ProblemDTOIF;

import dss.vector.solutions.MDSSRoleInfo;
import dss.vector.solutions.PersonDTO;
import dss.vector.solutions.PersonViewDTO;
import dss.vector.solutions.TestFixture;
import dss.vector.solutions.intervention.monitor.IndividualIPTCaseDTO;
import dss.vector.solutions.intervention.monitor.IndividualIPTCaseViewDTO;
import dss.vector.solutions.intervention.monitor.IndividualIPTDTO;
import dss.vector.solutions.intervention.monitor.IndividualIPTViewDTO;
import dss.vector.solutions.ontology.MODTO;
import dss.vector.solutions.ontology.TermDTO;

public class IndividualIPTCRUDPermissions extends PermissionTest implements DoNotWeave
{
  public static Test suite()
  {
    return TestFixture.getTestSuite(IndividualIPTCRUDPermissions.class, MDSSRoleInfo.MDSS_CORRDINATOR, MDSSRoleInfo.DATACAPTURER, MDSSRoleInfo.OPERATIONAL_MANAGER);
  }

  public void testCreatePatient()
  {
    TermDTO term = TermDTO.get(request, termId);

    Calendar calendar = Calendar.getInstance();
    calendar.clear();
    calendar.set(Calendar.YEAR, 1983);
    calendar.set(Calendar.MONTH, 5);
    calendar.set(Calendar.DAY_OF_YEAR, 11);

    PersonViewDTO dto = new PersonViewDTO(request);
    dto.setFirstName("Test");
    dto.setLastName("Test");
    dto.setDateOfBirth(calendar.getTime());
    dto.setSex(term);
    dto.setIsIPTRecipient(true);
    dto.setIsPatient(false);
    dto.setIsMDSSUser(false);
    dto.setIsITNRecipient(false);
    dto.setIsSprayLeader(false);
    dto.setIsSprayOperator(false);
    dto.apply();

    try
    {
      PersonViewDTO update = PersonDTO.lockView(request, dto.getPersonId());
      update.setFirstName("Testy");
      update.setLastName("Test");
      update.setDateOfBirth(new Date());
      update.setSex(term);
      update.apply();

      PersonViewDTO test = PersonDTO.getView(request, dto.getPersonId());
      assertEquals(update.getFirstName(), test.getFirstName());
    }
    catch (ProblemExceptionDTO e)
    {
      for (ProblemDTOIF message : e.getProblems())
      {
        fail(message.getMessage());
      }
    }
    finally
    {
      PersonDTO.lock(request, dto.getPersonId()).delete();
    }
  }

  public void testIptCase()
  {
    TermDTO term = TermDTO.get(request, termId);

    Calendar calendar = Calendar.getInstance();
    calendar.clear();
    calendar.set(Calendar.YEAR, 1983);
    calendar.set(Calendar.MONTH, 5);
    calendar.set(Calendar.DAY_OF_YEAR, 11);

    PersonViewDTO dto = new PersonViewDTO(request);
    dto.setFirstName("Test");
    dto.setLastName("Test");
    dto.setDateOfBirth(calendar.getTime());
    dto.setSex(term);
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
      view.apply();

      try
      {
        IndividualIPTCaseViewDTO test = IndividualIPTCaseDTO.getView(request, view.getConcreteId());

        assertEquals(view.getPatient().getId(), test.getPatient().getId());
      }
      finally
      {
        view.deleteConcrete();
      }
    }
    catch (ProblemExceptionDTO e)
    {
      for (ProblemDTOIF p : e.getProblems())
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
    TermDTO term = TermDTO.get(request, termId);

    Calendar calendar = Calendar.getInstance();
    calendar.clear();
    calendar.set(Calendar.YEAR, 1983);
    calendar.set(Calendar.MONTH, 5);
    calendar.set(Calendar.DAY_OF_YEAR, 11);

    PersonViewDTO dto = new PersonViewDTO(request);
    dto.setFirstName("Test");
    dto.setLastName("Test");
    dto.setDateOfBirth(calendar.getTime());
    dto.setSex(term);
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
      caseView.apply();

      try
      {
        IndividualIPTCaseDTO iptCase = IndividualIPTCaseDTO.get(request, caseView.getConcreteId());

        IndividualIPTViewDTO view = new IndividualIPTViewDTO(request);
        view.setFacility(facilityGeoId);
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
        view.setServiceDate(new Date());
        view.apply();

        try
        {
          IndividualIPTViewDTO edit = IndividualIPTDTO.lockView(request, view.getConcreteId());
          edit.setFacility(facilityGeoId);
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
          assertEquals(edit.getServiceDate(), test.getServiceDate());
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
