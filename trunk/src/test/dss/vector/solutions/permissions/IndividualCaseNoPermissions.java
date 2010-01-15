package dss.vector.solutions.permissions;

import java.util.Calendar;
import java.util.Date;

import junit.framework.Test;

import com.terraframe.mojo.DoNotWeave;
import com.terraframe.mojo.session.CreatePermissionExceptionDTO;

import dss.vector.solutions.MDSSRoleInfo;
import dss.vector.solutions.PersonDTO;
import dss.vector.solutions.PersonViewDTO;
import dss.vector.solutions.TestFixture;
import dss.vector.solutions.geo.generated.GeoEntityDTO;
import dss.vector.solutions.geo.generated.HealthFacilityDTO;
import dss.vector.solutions.intervention.monitor.IndividualCaseDTO;
import dss.vector.solutions.intervention.monitor.IndividualInstanceDTO;
import dss.vector.solutions.ontology.TermDTO;

public class IndividualCaseNoPermissions extends PermissionTest implements DoNotWeave
{
  public static Test suite()
  {
    return TestFixture.getTestSuite(IndividualCaseNoPermissions.class, MDSSRoleInfo.ENTOMOLOGIST, MDSSRoleInfo.MANAGER, MDSSRoleInfo.STOCK_STAFF);
  }

  public void testIndividualCase()
  {
    TermDTO term = TermDTO.get(request, termId);
    GeoEntityDTO geoEntity = GeoEntityDTO.searchByGeoId(request, facilityGeoId);

    Calendar calendar = Calendar.getInstance();
    calendar.clear();
    calendar.set(Calendar.YEAR, 1983);
    calendar.set(Calendar.MONTH, 5);
    calendar.set(Calendar.DAY_OF_YEAR, 11);

    PersonViewDTO dto = new PersonViewDTO(systemRequest);
    dto.setFirstName("Test");
    dto.setLastName("Test");
    dto.setDateOfBirth(calendar.getTime());
    dto.setSex(term);
    dto.setIsIPTRecipient(false);
    dto.setIsPatient(true);
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
        IndividualCaseDTO view = new IndividualCaseDTO(request);
        view.setPatient(patient.getPatientDelegate());
        view.setResidence(geoEntity);
        view.setResidenceText("Test Residence");
        view.setProbableSource(geoEntity);
        view.setProbableSourceText("Test Source");
        view.setDiagnosisDate(new Date());
        view.setCaseReportDate(new Date());
        view.setWorkplace(geoEntity);
        view.setWorkplaceText("Test Workplace");
        view.apply();

        view.delete();

        fail("Able to create an individual case without permissions");
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

  public void testIndividualInstance()
  {
    TermDTO term = TermDTO.get(request, termId);
    HealthFacilityDTO entity = (HealthFacilityDTO) GeoEntityDTO.searchByGeoId(request, facilityGeoId);

    Calendar calendar = Calendar.getInstance();
    calendar.clear();
    calendar.set(Calendar.YEAR, 1983);
    calendar.set(Calendar.MONTH, 5);
    calendar.set(Calendar.DAY_OF_YEAR, 11);

    PersonViewDTO dto = new PersonViewDTO(systemRequest);
    dto.setFirstName("Test");
    dto.setLastName("Test");
    dto.setDateOfBirth(calendar.getTime());
    dto.setSex(term);
    dto.setIsIPTRecipient(false);
    dto.setIsPatient(true);
    dto.setIsMDSSUser(false);
    dto.setIsITNRecipient(false);
    dto.setIsSprayLeader(false);
    dto.setIsSprayOperator(false);
    dto.apply();

    try
    {
      PersonDTO patient = PersonDTO.get(systemRequest, dto.getPersonId());

      IndividualCaseDTO c = new IndividualCaseDTO(systemRequest);
      c.setPatient(patient.getPatientDelegate());
      c.setResidence(entity);
      c.setResidenceText("Test Residence");
      c.setProbableSource(entity);
      c.setProbableSourceText("Test Source");
      c.setDiagnosisDate(new Date());
      c.setCaseReportDate(new Date());
      c.setWorkplace(entity);
      c.setWorkplaceText("Test Workplace");
      c.apply();

      try
      {
        IndividualInstanceDTO view = new IndividualInstanceDTO(request);
        view.setActivelyDetected(true);
        view.setAdmissionDate(new Date());
        view.setAnaemiaPatient(true);
        view.setClinicalDiagnosis(true);
        view.setDetectedBy(term);
        view.setDiedInFacility(true);
        view.setFacilityVisit(new Date());
        view.setHealthFacility(entity);
        view.setIndividualCase(c);
        view.setLabTest(term);
        view.setLabTestDate(new Date());
        view.setMalariaType(term);
        view.setPatientCategory(term);
        view.setPregnant(true);
        view.setProperlyRelease(true);
        view.setReferralReason(term);
        view.setReferredFrom(true);
        view.setReferredTo(true);
        view.setReleaseDate(new Date());
        view.setSampleType(term);
        view.setSymptom(term);
        view.setSymptomComments("Test Comments");
        view.setSymptomOnset(new Date());
        view.setTestSampleDate(new Date());
        view.setTreatment(term);
        view.setTreatmentMethod(term);
        view.setTreatmentStartDate(new Date());
        view.applyAll(new TermDTO[]{term});

        view.delete();
        
        fail("Able to create an individual case instance without permissions");
      }
      catch(CreatePermissionExceptionDTO e)
      {
        //This is expected
      }
      finally
      {
        c.delete();
      }
    }
    finally
    {
      PersonDTO.get(systemRequest, dto.getPersonId()).delete();
    }
  }
}
