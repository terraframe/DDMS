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
import dss.vector.solutions.geo.generated.GeoEntityDTO;
import dss.vector.solutions.geo.generated.HealthFacilityDTO;
import dss.vector.solutions.intervention.monitor.IndividualCaseDTO;
import dss.vector.solutions.intervention.monitor.IndividualInstanceDTO;
import dss.vector.solutions.ontology.TermDTO;
import dss.vector.solutions.surveillance.IndividualCaseSymptomDTO;

public class IndividualCaseCRUDPermissions extends PermissionTest implements DoNotWeave
{
  public static Test suite()
  {
    return TestFixture.getTestSuite(IndividualCaseCRUDPermissions.class, MDSSRoleInfo.MDSS_CORRDINATOR, MDSSRoleInfo.DATACAPTURER, MDSSRoleInfo.OPERATIONAL_MANAGER);
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
    dto.setIsIPTRecipient(false);
    dto.setIsPatient(true);
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
    GeoEntityDTO geoEntity = GeoEntityDTO.searchByGeoId(request, facilityGeoId);

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

      try
      {
        IndividualCaseDTO edit = IndividualCaseDTO.lock(request, view.getId());
        edit.setResidenceText("Test Residence 2");
        edit.setProbableSourceText("Test Source 2");
        edit.setDiagnosisDate(calendar.getTime());
        edit.setCaseReportDate(calendar.getTime());
        edit.setWorkplaceText("Test Workplace 2");
        edit.apply();
        
        IndividualCaseDTO test = IndividualCaseDTO.get(request, view.getId());
        
        assertEquals(edit.getPatient().getId(), test.getPatient().getId());
        assertEquals(edit.getResidence().getId(), test.getResidence().getId());
        assertEquals(edit.getResidenceText(), test.getResidenceText());
        assertEquals(edit.getProbableSource().getId(), test.getProbableSource().getId());
        assertEquals(edit.getProbableSourceText(), test.getProbableSourceText());
        assertEquals(edit.getWorkplace().getId(), test.getWorkplace().getId());
        assertEquals(edit.getWorkplaceText(), test.getWorkplaceText());
        assertEquals(edit.getDiagnosisDate(), test.getDiagnosisDate());
        assertEquals(edit.getCaseReportDate(), test.getCaseReportDate());
      }
      finally
      {
        view.lock();
        view.delete();
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
    HealthFacilityDTO entity = (HealthFacilityDTO) GeoEntityDTO.searchByGeoId(request, facilityGeoId);

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

      IndividualCaseDTO c = new IndividualCaseDTO(request);
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
        
        IndividualCaseSymptomDTO[] symptoms = view.getSymptoms();
        
        for(IndividualCaseSymptomDTO symptom : symptoms)
        {
          symptom.setHasSymptom(true);
        }
        
        view.applyAll(symptoms);

        try
        {
          IndividualInstanceDTO edit = IndividualInstanceDTO.lock(request, view.getId());
          edit.setActivelyDetected(false);
          edit.setAdmissionDate(calendar.getTime());
          edit.setAnaemiaPatient(false);
          edit.setClinicalDiagnosis(false);
          edit.setDetectedBy(term);
          edit.setDiedInFacility(false);
          edit.setFacilityVisit(calendar.getTime());
          edit.setHealthFacility(entity);
          edit.setIndividualCase(c);
          edit.setLabTest(term);
          edit.setLabTestDate(calendar.getTime());
          edit.setMalariaType(term);
          edit.setPatientCategory(term);
          edit.setPregnant(false);
          edit.setProperlyRelease(false);
          edit.setReferralReason(term);
          edit.setReferredFrom(false);
          edit.setReferredTo(false);
          edit.setReleaseDate(calendar.getTime());
          edit.setSampleType(term);
          edit.setSymptom(term);
          edit.setSymptomComments("Test Comments 2");
          edit.setSymptomOnset(calendar.getTime());
          edit.setTestSampleDate(calendar.getTime());
          edit.setTreatment(term);
          edit.setTreatmentMethod(term);
          edit.setTreatmentStartDate(calendar.getTime());
          
          IndividualCaseSymptomDTO[] editSymptoms = edit.getSymptoms();
          
          for(IndividualCaseSymptomDTO symptom : editSymptoms)
          {
            symptom.setHasSymptom(false);
          }
          
          edit.applyAll(editSymptoms);
          
          IndividualInstanceDTO test = IndividualInstanceDTO.get(request, view.getId());
          
          assertEquals(edit.getActivelyDetected(), test.getActivelyDetected());
          assertEquals(edit.getAdmissionDate(), test.getAdmissionDate());
          assertEquals(edit.getAnaemiaPatient(), test.getAnaemiaPatient());
          assertEquals(edit.getClinicalDiagnosis(), test.getClinicalDiagnosis());
          assertEquals(edit.getDetectedBy().getId(), test.getDetectedBy().getId());
          assertEquals(edit.getDiedInFacility(), test.getDiedInFacility());
          assertEquals(edit.getFacilityVisit(), test.getFacilityVisit());
          assertEquals(edit.getHealthFacility().getId(), test.getHealthFacility().getId());
          assertEquals(edit.getLabTest().getId(), test.getLabTest().getId());
          assertEquals(edit.getLabTestDate(), test.getLabTestDate());
          assertEquals(edit.getMalariaType().getId(), test.getMalariaType().getId());
          assertEquals(edit.getPatientCategory().getId(), test.getPatientCategory().getId());
          assertEquals(edit.getPregnant(), test.getPregnant());
          assertEquals(edit.getProperlyRelease(), test.getProperlyRelease());
          assertEquals(edit.getReferralReason().getId(), test.getReferralReason().getId());
          assertEquals(edit.getReferredFrom(), test.getReferredFrom());
          assertEquals(edit.getReferredTo(), test.getReferredTo());
          assertEquals(edit.getReleaseDate(), test.getReleaseDate());
          assertEquals(edit.getSampleType(), test.getSampleType());
          assertEquals(edit.getSymptom().getId(), test.getSymptom().getId());
          assertEquals(edit.getSymptomComments(), test.getSymptomComments());
          assertEquals(edit.getSymptomOnset(), test.getSymptomOnset());
          assertEquals(edit.getTestSampleDate(), test.getTestSampleDate());
          assertEquals(edit.getTreatment().getId(), test.getTreatment().getId());
          assertEquals(edit.getTreatmentMethod().getId(), test.getTreatmentMethod().getId());
          assertEquals(edit.getTreatmentStartDate(), test.getTreatmentStartDate());

          IndividualCaseSymptomDTO[] testSymptoms = test.getSymptoms();
          
          for(int i = 0; i < testSymptoms.length; i++)
          {
            assertEquals(editSymptoms[i].getHasSymptom(), testSymptoms[i].getHasSymptom());
          }          
        }
        finally
        {
          view.lock();
          view.delete();
        }
      }
      finally
      {
        c.delete();
      }
    }
    finally
    {
      PersonDTO.get(request, dto.getPersonId()).delete();
    }
  }

}
