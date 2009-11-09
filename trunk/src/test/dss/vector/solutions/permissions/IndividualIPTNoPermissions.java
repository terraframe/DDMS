package dss.vector.solutions.permissions;

import java.util.Date;

import junit.framework.Test;

import com.terraframe.mojo.DoNotWeave;
import com.terraframe.mojo.session.CreatePermissionExceptionDTO;

import dss.vector.solutions.MDSSRoleInfo;
import dss.vector.solutions.PersonDTO;
import dss.vector.solutions.PersonViewDTO;
import dss.vector.solutions.TestFixture;
import dss.vector.solutions.intervention.monitor.IndividualIPTCaseDTO;
import dss.vector.solutions.intervention.monitor.IndividualIPTCaseViewDTO;
import dss.vector.solutions.intervention.monitor.IndividualIPTViewDTO;
import dss.vector.solutions.ontology.TermDTO;

public class IndividualIPTNoPermissions extends PermissionTest implements DoNotWeave
{
  public static Test suite()
  {
    return TestFixture.getTestSuite(IndividualIPTNoPermissions.class, MDSSRoleInfo.ENTOMOLOGIST, MDSSRoleInfo.MANAGER);
  }

  public void testIndividualIPTCase()
  {
    TermDTO term = TermDTO.get(request, termId);

    PersonViewDTO dto = new PersonViewDTO(systemRequest);
    dto.setFirstName("Test");
    dto.setLastName("Test");
    dto.setDateOfBirth(new Date());
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
    TermDTO term = TermDTO.get(request, termId);

    PersonViewDTO dto = new PersonViewDTO(systemRequest);
    dto.setFirstName("Test");
    dto.setLastName("Test");
    dto.setDateOfBirth(new Date());
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
          view.setFacility(facilityGeoId);
          view.setIptCase(iptCase);
          view.setPatientType(TermDTO.get(request, termId));
          view.setIsANCVisit(true);
          view.setVisitNumber(TermDTO.get(request, termId));
          view.setDoseNumber(TermDTO.get(request, termId));
          view.setDoseType(TermDTO.get(request, termId));
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
