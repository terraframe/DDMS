package dss.vector.solutions.export;

@com.terraframe.mojo.business.ClassSignature(hash = -1948249074)
/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to IndividualCaseExcelView.java
 *
 * @author Autogenerated by TerraFrame
 */
public  abstract  class IndividualCaseExcelViewQueryBase extends com.terraframe.mojo.query.GeneratedViewQuery
 implements com.terraframe.mojo.generation.loader.Reloadable
{
private static final long serialVersionUID = -1948249074;

  public IndividualCaseExcelViewQueryBase(com.terraframe.mojo.query.QueryFactory componentQueryFactory)
  {
    super(componentQueryFactory);
  }

  public IndividualCaseExcelViewQueryBase(com.terraframe.mojo.query.QueryFactory componentQueryFactory, com.terraframe.mojo.query.ViewQueryBuilder viewQueryBuilder)
  {
    super(componentQueryFactory, viewQueryBuilder);
  }
  public String getClassType()
  {
    return dss.vector.solutions.export.IndividualCaseExcelView.CLASS;
  }
  public com.terraframe.mojo.query.AttributeBoolean getActivelyDetected()
  {
    return getActivelyDetected(null);

  }
 
  public com.terraframe.mojo.query.AttributeBoolean getActivelyDetected(String alias)
  {
    return (com.terraframe.mojo.query.AttributeBoolean)this.getSelectable(dss.vector.solutions.export.IndividualCaseExcelView.ACTIVELYDETECTED, alias, null);

  }
 
  public com.terraframe.mojo.query.AttributeBoolean getActivelyDetected(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.AttributeBoolean)this.getSelectable(dss.vector.solutions.export.IndividualCaseExcelView.ACTIVELYDETECTED, alias, displayLabel);

  }
 
  public com.terraframe.mojo.query.AttributeMoment getAdmissionDate()
  {
    return getAdmissionDate(null);

  }
 
  public com.terraframe.mojo.query.AttributeMoment getAdmissionDate(String alias)
  {
    return (com.terraframe.mojo.query.AttributeMoment)this.getSelectable(dss.vector.solutions.export.IndividualCaseExcelView.ADMISSIONDATE, alias, null);

  }
 
  public com.terraframe.mojo.query.AttributeMoment getAdmissionDate(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.AttributeMoment)this.getSelectable(dss.vector.solutions.export.IndividualCaseExcelView.ADMISSIONDATE, alias, displayLabel);

  }
 
  public com.terraframe.mojo.query.AttributeInteger getAge()
  {
    return getAge(null);

  }
 
  public com.terraframe.mojo.query.AttributeInteger getAge(String alias)
  {
    return (com.terraframe.mojo.query.AttributeInteger)this.getSelectable(dss.vector.solutions.export.IndividualCaseExcelView.AGE, alias, null);

  }
 
  public com.terraframe.mojo.query.AttributeInteger getAge(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.AttributeInteger)this.getSelectable(dss.vector.solutions.export.IndividualCaseExcelView.AGE, alias, displayLabel);

  }
 
  public com.terraframe.mojo.query.AttributeBoolean getAnaemiaPatient()
  {
    return getAnaemiaPatient(null);

  }
 
  public com.terraframe.mojo.query.AttributeBoolean getAnaemiaPatient(String alias)
  {
    return (com.terraframe.mojo.query.AttributeBoolean)this.getSelectable(dss.vector.solutions.export.IndividualCaseExcelView.ANAEMIAPATIENT, alias, null);

  }
 
  public com.terraframe.mojo.query.AttributeBoolean getAnaemiaPatient(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.AttributeBoolean)this.getSelectable(dss.vector.solutions.export.IndividualCaseExcelView.ANAEMIAPATIENT, alias, displayLabel);

  }
 
  public com.terraframe.mojo.query.AttributeMoment getCaseReportDate()
  {
    return getCaseReportDate(null);

  }
 
  public com.terraframe.mojo.query.AttributeMoment getCaseReportDate(String alias)
  {
    return (com.terraframe.mojo.query.AttributeMoment)this.getSelectable(dss.vector.solutions.export.IndividualCaseExcelView.CASEREPORTDATE, alias, null);

  }
 
  public com.terraframe.mojo.query.AttributeMoment getCaseReportDate(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.AttributeMoment)this.getSelectable(dss.vector.solutions.export.IndividualCaseExcelView.CASEREPORTDATE, alias, displayLabel);

  }
 
  public com.terraframe.mojo.query.AttributeBoolean getClinicalDiagnosis()
  {
    return getClinicalDiagnosis(null);

  }
 
  public com.terraframe.mojo.query.AttributeBoolean getClinicalDiagnosis(String alias)
  {
    return (com.terraframe.mojo.query.AttributeBoolean)this.getSelectable(dss.vector.solutions.export.IndividualCaseExcelView.CLINICALDIAGNOSIS, alias, null);

  }
 
  public com.terraframe.mojo.query.AttributeBoolean getClinicalDiagnosis(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.AttributeBoolean)this.getSelectable(dss.vector.solutions.export.IndividualCaseExcelView.CLINICALDIAGNOSIS, alias, displayLabel);

  }
 
  public com.terraframe.mojo.query.AttributeMoment getDateOfBirth()
  {
    return getDateOfBirth(null);

  }
 
  public com.terraframe.mojo.query.AttributeMoment getDateOfBirth(String alias)
  {
    return (com.terraframe.mojo.query.AttributeMoment)this.getSelectable(dss.vector.solutions.export.IndividualCaseExcelView.DATEOFBIRTH, alias, null);

  }
 
  public com.terraframe.mojo.query.AttributeMoment getDateOfBirth(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.AttributeMoment)this.getSelectable(dss.vector.solutions.export.IndividualCaseExcelView.DATEOFBIRTH, alias, displayLabel);

  }
 
  public com.terraframe.mojo.query.AttributeChar getDetectedBy()
  {
    return getDetectedBy(null);

  }
 
  public com.terraframe.mojo.query.AttributeChar getDetectedBy(String alias)
  {
    return (com.terraframe.mojo.query.AttributeChar)this.getSelectable(dss.vector.solutions.export.IndividualCaseExcelView.DETECTEDBY, alias, null);

  }
 
  public com.terraframe.mojo.query.AttributeChar getDetectedBy(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.AttributeChar)this.getSelectable(dss.vector.solutions.export.IndividualCaseExcelView.DETECTEDBY, alias, displayLabel);

  }
 
  public com.terraframe.mojo.query.AttributeMoment getDiagnosisDate()
  {
    return getDiagnosisDate(null);

  }
 
  public com.terraframe.mojo.query.AttributeMoment getDiagnosisDate(String alias)
  {
    return (com.terraframe.mojo.query.AttributeMoment)this.getSelectable(dss.vector.solutions.export.IndividualCaseExcelView.DIAGNOSISDATE, alias, null);

  }
 
  public com.terraframe.mojo.query.AttributeMoment getDiagnosisDate(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.AttributeMoment)this.getSelectable(dss.vector.solutions.export.IndividualCaseExcelView.DIAGNOSISDATE, alias, displayLabel);

  }
 
  public com.terraframe.mojo.query.AttributeBoolean getDiedInFacility()
  {
    return getDiedInFacility(null);

  }
 
  public com.terraframe.mojo.query.AttributeBoolean getDiedInFacility(String alias)
  {
    return (com.terraframe.mojo.query.AttributeBoolean)this.getSelectable(dss.vector.solutions.export.IndividualCaseExcelView.DIEDINFACILITY, alias, null);

  }
 
  public com.terraframe.mojo.query.AttributeBoolean getDiedInFacility(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.AttributeBoolean)this.getSelectable(dss.vector.solutions.export.IndividualCaseExcelView.DIEDINFACILITY, alias, displayLabel);

  }
 
  public com.terraframe.mojo.query.AttributeMoment getFacilityVisit()
  {
    return getFacilityVisit(null);

  }
 
  public com.terraframe.mojo.query.AttributeMoment getFacilityVisit(String alias)
  {
    return (com.terraframe.mojo.query.AttributeMoment)this.getSelectable(dss.vector.solutions.export.IndividualCaseExcelView.FACILITYVISIT, alias, null);

  }
 
  public com.terraframe.mojo.query.AttributeMoment getFacilityVisit(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.AttributeMoment)this.getSelectable(dss.vector.solutions.export.IndividualCaseExcelView.FACILITYVISIT, alias, displayLabel);

  }
 
  public com.terraframe.mojo.query.AttributeChar getFirstName()
  {
    return getFirstName(null);

  }
 
  public com.terraframe.mojo.query.AttributeChar getFirstName(String alias)
  {
    return (com.terraframe.mojo.query.AttributeChar)this.getSelectable(dss.vector.solutions.export.IndividualCaseExcelView.FIRSTNAME, alias, null);

  }
 
  public com.terraframe.mojo.query.AttributeChar getFirstName(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.AttributeChar)this.getSelectable(dss.vector.solutions.export.IndividualCaseExcelView.FIRSTNAME, alias, displayLabel);

  }
 
  public dss.vector.solutions.geo.generated.HealthFacilityQuery.HealthFacilityQueryReferenceIF getHealthFacility()
  {
    return getHealthFacility(null);

  }
 
  public dss.vector.solutions.geo.generated.HealthFacilityQuery.HealthFacilityQueryReferenceIF getHealthFacility(String alias)
  {

    return (dss.vector.solutions.geo.generated.HealthFacilityQuery.HealthFacilityQueryReferenceIF)this.getSelectable(dss.vector.solutions.export.IndividualCaseExcelView.HEALTHFACILITY, alias, null);

  }
 
  public dss.vector.solutions.geo.generated.HealthFacilityQuery.HealthFacilityQueryReferenceIF getHealthFacility(String alias, String displayLabel)
  {

    return (dss.vector.solutions.geo.generated.HealthFacilityQuery.HealthFacilityQueryReferenceIF)this.getSelectable(dss.vector.solutions.export.IndividualCaseExcelView.HEALTHFACILITY, alias, displayLabel);

  }
  public com.terraframe.mojo.query.AttributeChar getId()
  {
    return getId(null);

  }
 
  public com.terraframe.mojo.query.AttributeChar getId(String alias)
  {
    return (com.terraframe.mojo.query.AttributeChar)this.getSelectable(dss.vector.solutions.export.IndividualCaseExcelView.ID, alias, null);

  }
 
  public com.terraframe.mojo.query.AttributeChar getId(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.AttributeChar)this.getSelectable(dss.vector.solutions.export.IndividualCaseExcelView.ID, alias, displayLabel);

  }
 
  public com.terraframe.mojo.query.AttributeChar getLabTest()
  {
    return getLabTest(null);

  }
 
  public com.terraframe.mojo.query.AttributeChar getLabTest(String alias)
  {
    return (com.terraframe.mojo.query.AttributeChar)this.getSelectable(dss.vector.solutions.export.IndividualCaseExcelView.LABTEST, alias, null);

  }
 
  public com.terraframe.mojo.query.AttributeChar getLabTest(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.AttributeChar)this.getSelectable(dss.vector.solutions.export.IndividualCaseExcelView.LABTEST, alias, displayLabel);

  }
 
  public com.terraframe.mojo.query.AttributeMoment getLabTestDate()
  {
    return getLabTestDate(null);

  }
 
  public com.terraframe.mojo.query.AttributeMoment getLabTestDate(String alias)
  {
    return (com.terraframe.mojo.query.AttributeMoment)this.getSelectable(dss.vector.solutions.export.IndividualCaseExcelView.LABTESTDATE, alias, null);

  }
 
  public com.terraframe.mojo.query.AttributeMoment getLabTestDate(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.AttributeMoment)this.getSelectable(dss.vector.solutions.export.IndividualCaseExcelView.LABTESTDATE, alias, displayLabel);

  }
 
  public com.terraframe.mojo.query.AttributeChar getLastName()
  {
    return getLastName(null);

  }
 
  public com.terraframe.mojo.query.AttributeChar getLastName(String alias)
  {
    return (com.terraframe.mojo.query.AttributeChar)this.getSelectable(dss.vector.solutions.export.IndividualCaseExcelView.LASTNAME, alias, null);

  }
 
  public com.terraframe.mojo.query.AttributeChar getLastName(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.AttributeChar)this.getSelectable(dss.vector.solutions.export.IndividualCaseExcelView.LASTNAME, alias, displayLabel);

  }
 
  public com.terraframe.mojo.query.AttributeChar getMalariaType()
  {
    return getMalariaType(null);

  }
 
  public com.terraframe.mojo.query.AttributeChar getMalariaType(String alias)
  {
    return (com.terraframe.mojo.query.AttributeChar)this.getSelectable(dss.vector.solutions.export.IndividualCaseExcelView.MALARIATYPE, alias, null);

  }
 
  public com.terraframe.mojo.query.AttributeChar getMalariaType(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.AttributeChar)this.getSelectable(dss.vector.solutions.export.IndividualCaseExcelView.MALARIATYPE, alias, displayLabel);

  }
 
  public com.terraframe.mojo.query.AttributeChar getPatientCategory()
  {
    return getPatientCategory(null);

  }
 
  public com.terraframe.mojo.query.AttributeChar getPatientCategory(String alias)
  {
    return (com.terraframe.mojo.query.AttributeChar)this.getSelectable(dss.vector.solutions.export.IndividualCaseExcelView.PATIENTCATEGORY, alias, null);

  }
 
  public com.terraframe.mojo.query.AttributeChar getPatientCategory(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.AttributeChar)this.getSelectable(dss.vector.solutions.export.IndividualCaseExcelView.PATIENTCATEGORY, alias, displayLabel);

  }
 
  public com.terraframe.mojo.query.AttributeBoolean getPregnant()
  {
    return getPregnant(null);

  }
 
  public com.terraframe.mojo.query.AttributeBoolean getPregnant(String alias)
  {
    return (com.terraframe.mojo.query.AttributeBoolean)this.getSelectable(dss.vector.solutions.export.IndividualCaseExcelView.PREGNANT, alias, null);

  }
 
  public com.terraframe.mojo.query.AttributeBoolean getPregnant(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.AttributeBoolean)this.getSelectable(dss.vector.solutions.export.IndividualCaseExcelView.PREGNANT, alias, displayLabel);

  }
 
  public dss.vector.solutions.geo.generated.GeoEntityQuery.GeoEntityQueryReferenceIF getProbableSource()
  {
    return getProbableSource(null);

  }
 
  public dss.vector.solutions.geo.generated.GeoEntityQuery.GeoEntityQueryReferenceIF getProbableSource(String alias)
  {

    return (dss.vector.solutions.geo.generated.GeoEntityQuery.GeoEntityQueryReferenceIF)this.getSelectable(dss.vector.solutions.export.IndividualCaseExcelView.PROBABLESOURCE, alias, null);

  }
 
  public dss.vector.solutions.geo.generated.GeoEntityQuery.GeoEntityQueryReferenceIF getProbableSource(String alias, String displayLabel)
  {

    return (dss.vector.solutions.geo.generated.GeoEntityQuery.GeoEntityQueryReferenceIF)this.getSelectable(dss.vector.solutions.export.IndividualCaseExcelView.PROBABLESOURCE, alias, displayLabel);

  }
  public com.terraframe.mojo.query.AttributeBoolean getProperlyRelease()
  {
    return getProperlyRelease(null);

  }
 
  public com.terraframe.mojo.query.AttributeBoolean getProperlyRelease(String alias)
  {
    return (com.terraframe.mojo.query.AttributeBoolean)this.getSelectable(dss.vector.solutions.export.IndividualCaseExcelView.PROPERLYRELEASE, alias, null);

  }
 
  public com.terraframe.mojo.query.AttributeBoolean getProperlyRelease(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.AttributeBoolean)this.getSelectable(dss.vector.solutions.export.IndividualCaseExcelView.PROPERLYRELEASE, alias, displayLabel);

  }
 
  public com.terraframe.mojo.query.AttributeChar getReferralReason()
  {
    return getReferralReason(null);

  }
 
  public com.terraframe.mojo.query.AttributeChar getReferralReason(String alias)
  {
    return (com.terraframe.mojo.query.AttributeChar)this.getSelectable(dss.vector.solutions.export.IndividualCaseExcelView.REFERRALREASON, alias, null);

  }
 
  public com.terraframe.mojo.query.AttributeChar getReferralReason(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.AttributeChar)this.getSelectable(dss.vector.solutions.export.IndividualCaseExcelView.REFERRALREASON, alias, displayLabel);

  }
 
  public com.terraframe.mojo.query.AttributeBoolean getReferredFrom()
  {
    return getReferredFrom(null);

  }
 
  public com.terraframe.mojo.query.AttributeBoolean getReferredFrom(String alias)
  {
    return (com.terraframe.mojo.query.AttributeBoolean)this.getSelectable(dss.vector.solutions.export.IndividualCaseExcelView.REFERREDFROM, alias, null);

  }
 
  public com.terraframe.mojo.query.AttributeBoolean getReferredFrom(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.AttributeBoolean)this.getSelectable(dss.vector.solutions.export.IndividualCaseExcelView.REFERREDFROM, alias, displayLabel);

  }
 
  public com.terraframe.mojo.query.AttributeBoolean getReferredTo()
  {
    return getReferredTo(null);

  }
 
  public com.terraframe.mojo.query.AttributeBoolean getReferredTo(String alias)
  {
    return (com.terraframe.mojo.query.AttributeBoolean)this.getSelectable(dss.vector.solutions.export.IndividualCaseExcelView.REFERREDTO, alias, null);

  }
 
  public com.terraframe.mojo.query.AttributeBoolean getReferredTo(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.AttributeBoolean)this.getSelectable(dss.vector.solutions.export.IndividualCaseExcelView.REFERREDTO, alias, displayLabel);

  }
 
  public com.terraframe.mojo.query.AttributeMoment getReleaseDate()
  {
    return getReleaseDate(null);

  }
 
  public com.terraframe.mojo.query.AttributeMoment getReleaseDate(String alias)
  {
    return (com.terraframe.mojo.query.AttributeMoment)this.getSelectable(dss.vector.solutions.export.IndividualCaseExcelView.RELEASEDATE, alias, null);

  }
 
  public com.terraframe.mojo.query.AttributeMoment getReleaseDate(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.AttributeMoment)this.getSelectable(dss.vector.solutions.export.IndividualCaseExcelView.RELEASEDATE, alias, displayLabel);

  }
 
  public dss.vector.solutions.geo.generated.GeoEntityQuery.GeoEntityQueryReferenceIF getResidence()
  {
    return getResidence(null);

  }
 
  public dss.vector.solutions.geo.generated.GeoEntityQuery.GeoEntityQueryReferenceIF getResidence(String alias)
  {

    return (dss.vector.solutions.geo.generated.GeoEntityQuery.GeoEntityQueryReferenceIF)this.getSelectable(dss.vector.solutions.export.IndividualCaseExcelView.RESIDENCE, alias, null);

  }
 
  public dss.vector.solutions.geo.generated.GeoEntityQuery.GeoEntityQueryReferenceIF getResidence(String alias, String displayLabel)
  {

    return (dss.vector.solutions.geo.generated.GeoEntityQuery.GeoEntityQueryReferenceIF)this.getSelectable(dss.vector.solutions.export.IndividualCaseExcelView.RESIDENCE, alias, displayLabel);

  }
  public com.terraframe.mojo.query.AttributeChar getSampleType()
  {
    return getSampleType(null);

  }
 
  public com.terraframe.mojo.query.AttributeChar getSampleType(String alias)
  {
    return (com.terraframe.mojo.query.AttributeChar)this.getSelectable(dss.vector.solutions.export.IndividualCaseExcelView.SAMPLETYPE, alias, null);

  }
 
  public com.terraframe.mojo.query.AttributeChar getSampleType(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.AttributeChar)this.getSelectable(dss.vector.solutions.export.IndividualCaseExcelView.SAMPLETYPE, alias, displayLabel);

  }
 
  public com.terraframe.mojo.query.AttributeChar getSex()
  {
    return getSex(null);

  }
 
  public com.terraframe.mojo.query.AttributeChar getSex(String alias)
  {
    return (com.terraframe.mojo.query.AttributeChar)this.getSelectable(dss.vector.solutions.export.IndividualCaseExcelView.SEX, alias, null);

  }
 
  public com.terraframe.mojo.query.AttributeChar getSex(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.AttributeChar)this.getSelectable(dss.vector.solutions.export.IndividualCaseExcelView.SEX, alias, displayLabel);

  }
 
  public com.terraframe.mojo.query.AttributeChar getSymptomComments()
  {
    return getSymptomComments(null);

  }
 
  public com.terraframe.mojo.query.AttributeChar getSymptomComments(String alias)
  {
    return (com.terraframe.mojo.query.AttributeChar)this.getSelectable(dss.vector.solutions.export.IndividualCaseExcelView.SYMPTOMCOMMENTS, alias, null);

  }
 
  public com.terraframe.mojo.query.AttributeChar getSymptomComments(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.AttributeChar)this.getSelectable(dss.vector.solutions.export.IndividualCaseExcelView.SYMPTOMCOMMENTS, alias, displayLabel);

  }
 
  public com.terraframe.mojo.query.AttributeMoment getSymptomOnset()
  {
    return getSymptomOnset(null);

  }
 
  public com.terraframe.mojo.query.AttributeMoment getSymptomOnset(String alias)
  {
    return (com.terraframe.mojo.query.AttributeMoment)this.getSelectable(dss.vector.solutions.export.IndividualCaseExcelView.SYMPTOMONSET, alias, null);

  }
 
  public com.terraframe.mojo.query.AttributeMoment getSymptomOnset(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.AttributeMoment)this.getSelectable(dss.vector.solutions.export.IndividualCaseExcelView.SYMPTOMONSET, alias, displayLabel);

  }
 
  public com.terraframe.mojo.query.AttributeMoment getTestSampleDate()
  {
    return getTestSampleDate(null);

  }
 
  public com.terraframe.mojo.query.AttributeMoment getTestSampleDate(String alias)
  {
    return (com.terraframe.mojo.query.AttributeMoment)this.getSelectable(dss.vector.solutions.export.IndividualCaseExcelView.TESTSAMPLEDATE, alias, null);

  }
 
  public com.terraframe.mojo.query.AttributeMoment getTestSampleDate(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.AttributeMoment)this.getSelectable(dss.vector.solutions.export.IndividualCaseExcelView.TESTSAMPLEDATE, alias, displayLabel);

  }
 
  public com.terraframe.mojo.query.AttributeChar getTreatment()
  {
    return getTreatment(null);

  }
 
  public com.terraframe.mojo.query.AttributeChar getTreatment(String alias)
  {
    return (com.terraframe.mojo.query.AttributeChar)this.getSelectable(dss.vector.solutions.export.IndividualCaseExcelView.TREATMENT, alias, null);

  }
 
  public com.terraframe.mojo.query.AttributeChar getTreatment(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.AttributeChar)this.getSelectable(dss.vector.solutions.export.IndividualCaseExcelView.TREATMENT, alias, displayLabel);

  }
 
  public com.terraframe.mojo.query.AttributeChar getTreatmentMethod()
  {
    return getTreatmentMethod(null);

  }
 
  public com.terraframe.mojo.query.AttributeChar getTreatmentMethod(String alias)
  {
    return (com.terraframe.mojo.query.AttributeChar)this.getSelectable(dss.vector.solutions.export.IndividualCaseExcelView.TREATMENTMETHOD, alias, null);

  }
 
  public com.terraframe.mojo.query.AttributeChar getTreatmentMethod(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.AttributeChar)this.getSelectable(dss.vector.solutions.export.IndividualCaseExcelView.TREATMENTMETHOD, alias, displayLabel);

  }
 
  public com.terraframe.mojo.query.AttributeMoment getTreatmentStartDate()
  {
    return getTreatmentStartDate(null);

  }
 
  public com.terraframe.mojo.query.AttributeMoment getTreatmentStartDate(String alias)
  {
    return (com.terraframe.mojo.query.AttributeMoment)this.getSelectable(dss.vector.solutions.export.IndividualCaseExcelView.TREATMENTSTARTDATE, alias, null);

  }
 
  public com.terraframe.mojo.query.AttributeMoment getTreatmentStartDate(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.AttributeMoment)this.getSelectable(dss.vector.solutions.export.IndividualCaseExcelView.TREATMENTSTARTDATE, alias, displayLabel);

  }
 
  public dss.vector.solutions.geo.generated.GeoEntityQuery.GeoEntityQueryReferenceIF getWorkplace()
  {
    return getWorkplace(null);

  }
 
  public dss.vector.solutions.geo.generated.GeoEntityQuery.GeoEntityQueryReferenceIF getWorkplace(String alias)
  {

    return (dss.vector.solutions.geo.generated.GeoEntityQuery.GeoEntityQueryReferenceIF)this.getSelectable(dss.vector.solutions.export.IndividualCaseExcelView.WORKPLACE, alias, null);

  }
 
  public dss.vector.solutions.geo.generated.GeoEntityQuery.GeoEntityQueryReferenceIF getWorkplace(String alias, String displayLabel)
  {

    return (dss.vector.solutions.geo.generated.GeoEntityQuery.GeoEntityQueryReferenceIF)this.getSelectable(dss.vector.solutions.export.IndividualCaseExcelView.WORKPLACE, alias, displayLabel);

  }
  /**  
   * Returns an iterator of Business objects that match the query criteria specified
   * on this query object. 
   * @return iterator of Business objects that match the query criteria specified
   * on this query object.
   */
  @java.lang.SuppressWarnings("unchecked")
  public com.terraframe.mojo.query.OIterator<? extends IndividualCaseExcelView> getIterator()
  {
    com.terraframe.mojo.query.ValueIterator valueIterator;
    if (_pageSize != null && _pageNumber != null)
    {
      valueIterator = (com.terraframe.mojo.query.ValueIterator<com.terraframe.mojo.dataaccess.ValueObject>)this.getComponentQuery().getIterator(_pageSize, _pageNumber);
    }
    else
    {
      valueIterator = (com.terraframe.mojo.query.ValueIterator<com.terraframe.mojo.dataaccess.ValueObject>)this.getComponentQuery().getIterator();
    }
    return new com.terraframe.mojo.query.ViewIterator<IndividualCaseExcelView>(this.getMdClassIF(), valueIterator);
  }

}
