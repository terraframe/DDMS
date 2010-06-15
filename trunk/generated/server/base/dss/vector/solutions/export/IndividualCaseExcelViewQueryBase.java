package dss.vector.solutions.export;

@com.runwaysdk.business.ClassSignature(hash = 946719021)
/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to IndividualCaseExcelView.java
 *
 * @author Autogenerated by RunwaySDK
 */
public  abstract  class IndividualCaseExcelViewQueryBase extends com.runwaysdk.query.GeneratedViewQuery
 implements com.runwaysdk.generation.loader.Reloadable
{
private static final long serialVersionUID = 946719021;

  public IndividualCaseExcelViewQueryBase(com.runwaysdk.query.QueryFactory componentQueryFactory)
  {
    super(componentQueryFactory);
  }

  public IndividualCaseExcelViewQueryBase(com.runwaysdk.query.QueryFactory componentQueryFactory, com.runwaysdk.query.ViewQueryBuilder viewQueryBuilder)
  {
    super(componentQueryFactory, viewQueryBuilder);
  }
  public String getClassType()
  {
    return dss.vector.solutions.export.IndividualCaseExcelView.CLASS;
  }
  public com.runwaysdk.query.SelectableBoolean getActivelyDetected()
  {
    return getActivelyDetected(null);

  }
 
  public com.runwaysdk.query.SelectableBoolean getActivelyDetected(String alias)
  {
    return (com.runwaysdk.query.SelectableBoolean)this.getSelectable(dss.vector.solutions.export.IndividualCaseExcelView.ACTIVELYDETECTED, alias, null);

  }
 
  public com.runwaysdk.query.SelectableBoolean getActivelyDetected(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableBoolean)this.getSelectable(dss.vector.solutions.export.IndividualCaseExcelView.ACTIVELYDETECTED, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableMoment getAdmissionDate()
  {
    return getAdmissionDate(null);

  }
 
  public com.runwaysdk.query.SelectableMoment getAdmissionDate(String alias)
  {
    return (com.runwaysdk.query.SelectableMoment)this.getSelectable(dss.vector.solutions.export.IndividualCaseExcelView.ADMISSIONDATE, alias, null);

  }
 
  public com.runwaysdk.query.SelectableMoment getAdmissionDate(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableMoment)this.getSelectable(dss.vector.solutions.export.IndividualCaseExcelView.ADMISSIONDATE, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableInteger getAge()
  {
    return getAge(null);

  }
 
  public com.runwaysdk.query.SelectableInteger getAge(String alias)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.export.IndividualCaseExcelView.AGE, alias, null);

  }
 
  public com.runwaysdk.query.SelectableInteger getAge(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.export.IndividualCaseExcelView.AGE, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableBoolean getAnaemiaPatient()
  {
    return getAnaemiaPatient(null);

  }
 
  public com.runwaysdk.query.SelectableBoolean getAnaemiaPatient(String alias)
  {
    return (com.runwaysdk.query.SelectableBoolean)this.getSelectable(dss.vector.solutions.export.IndividualCaseExcelView.ANAEMIAPATIENT, alias, null);

  }
 
  public com.runwaysdk.query.SelectableBoolean getAnaemiaPatient(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableBoolean)this.getSelectable(dss.vector.solutions.export.IndividualCaseExcelView.ANAEMIAPATIENT, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableMoment getCaseReportDate()
  {
    return getCaseReportDate(null);

  }
 
  public com.runwaysdk.query.SelectableMoment getCaseReportDate(String alias)
  {
    return (com.runwaysdk.query.SelectableMoment)this.getSelectable(dss.vector.solutions.export.IndividualCaseExcelView.CASEREPORTDATE, alias, null);

  }
 
  public com.runwaysdk.query.SelectableMoment getCaseReportDate(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableMoment)this.getSelectable(dss.vector.solutions.export.IndividualCaseExcelView.CASEREPORTDATE, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableMoment getDateOfBirth()
  {
    return getDateOfBirth(null);

  }
 
  public com.runwaysdk.query.SelectableMoment getDateOfBirth(String alias)
  {
    return (com.runwaysdk.query.SelectableMoment)this.getSelectable(dss.vector.solutions.export.IndividualCaseExcelView.DATEOFBIRTH, alias, null);

  }
 
  public com.runwaysdk.query.SelectableMoment getDateOfBirth(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableMoment)this.getSelectable(dss.vector.solutions.export.IndividualCaseExcelView.DATEOFBIRTH, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableChar getDetectedBy()
  {
    return getDetectedBy(null);

  }
 
  public com.runwaysdk.query.SelectableChar getDetectedBy(String alias)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(dss.vector.solutions.export.IndividualCaseExcelView.DETECTEDBY, alias, null);

  }
 
  public com.runwaysdk.query.SelectableChar getDetectedBy(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(dss.vector.solutions.export.IndividualCaseExcelView.DETECTEDBY, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableMoment getDiagnosisDate()
  {
    return getDiagnosisDate(null);

  }
 
  public com.runwaysdk.query.SelectableMoment getDiagnosisDate(String alias)
  {
    return (com.runwaysdk.query.SelectableMoment)this.getSelectable(dss.vector.solutions.export.IndividualCaseExcelView.DIAGNOSISDATE, alias, null);

  }
 
  public com.runwaysdk.query.SelectableMoment getDiagnosisDate(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableMoment)this.getSelectable(dss.vector.solutions.export.IndividualCaseExcelView.DIAGNOSISDATE, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableBoolean getDiedInFacility()
  {
    return getDiedInFacility(null);

  }
 
  public com.runwaysdk.query.SelectableBoolean getDiedInFacility(String alias)
  {
    return (com.runwaysdk.query.SelectableBoolean)this.getSelectable(dss.vector.solutions.export.IndividualCaseExcelView.DIEDINFACILITY, alias, null);

  }
 
  public com.runwaysdk.query.SelectableBoolean getDiedInFacility(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableBoolean)this.getSelectable(dss.vector.solutions.export.IndividualCaseExcelView.DIEDINFACILITY, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableMoment getFacilityVisit()
  {
    return getFacilityVisit(null);

  }
 
  public com.runwaysdk.query.SelectableMoment getFacilityVisit(String alias)
  {
    return (com.runwaysdk.query.SelectableMoment)this.getSelectable(dss.vector.solutions.export.IndividualCaseExcelView.FACILITYVISIT, alias, null);

  }
 
  public com.runwaysdk.query.SelectableMoment getFacilityVisit(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableMoment)this.getSelectable(dss.vector.solutions.export.IndividualCaseExcelView.FACILITYVISIT, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableChar getFirstName()
  {
    return getFirstName(null);

  }
 
  public com.runwaysdk.query.SelectableChar getFirstName(String alias)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(dss.vector.solutions.export.IndividualCaseExcelView.FIRSTNAME, alias, null);

  }
 
  public com.runwaysdk.query.SelectableChar getFirstName(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(dss.vector.solutions.export.IndividualCaseExcelView.FIRSTNAME, alias, displayLabel);

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
  public com.runwaysdk.query.SelectableChar getId()
  {
    return getId(null);

  }
 
  public com.runwaysdk.query.SelectableChar getId(String alias)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(dss.vector.solutions.export.IndividualCaseExcelView.ID, alias, null);

  }
 
  public com.runwaysdk.query.SelectableChar getId(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(dss.vector.solutions.export.IndividualCaseExcelView.ID, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableChar getLabTest()
  {
    return getLabTest(null);

  }
 
  public com.runwaysdk.query.SelectableChar getLabTest(String alias)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(dss.vector.solutions.export.IndividualCaseExcelView.LABTEST, alias, null);

  }
 
  public com.runwaysdk.query.SelectableChar getLabTest(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(dss.vector.solutions.export.IndividualCaseExcelView.LABTEST, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableMoment getLabTestDate()
  {
    return getLabTestDate(null);

  }
 
  public com.runwaysdk.query.SelectableMoment getLabTestDate(String alias)
  {
    return (com.runwaysdk.query.SelectableMoment)this.getSelectable(dss.vector.solutions.export.IndividualCaseExcelView.LABTESTDATE, alias, null);

  }
 
  public com.runwaysdk.query.SelectableMoment getLabTestDate(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableMoment)this.getSelectable(dss.vector.solutions.export.IndividualCaseExcelView.LABTESTDATE, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableChar getLastName()
  {
    return getLastName(null);

  }
 
  public com.runwaysdk.query.SelectableChar getLastName(String alias)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(dss.vector.solutions.export.IndividualCaseExcelView.LASTNAME, alias, null);

  }
 
  public com.runwaysdk.query.SelectableChar getLastName(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(dss.vector.solutions.export.IndividualCaseExcelView.LASTNAME, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableChar getMalariaType()
  {
    return getMalariaType(null);

  }
 
  public com.runwaysdk.query.SelectableChar getMalariaType(String alias)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(dss.vector.solutions.export.IndividualCaseExcelView.MALARIATYPE, alias, null);

  }
 
  public com.runwaysdk.query.SelectableChar getMalariaType(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(dss.vector.solutions.export.IndividualCaseExcelView.MALARIATYPE, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableChar getPatientCategory()
  {
    return getPatientCategory(null);

  }
 
  public com.runwaysdk.query.SelectableChar getPatientCategory(String alias)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(dss.vector.solutions.export.IndividualCaseExcelView.PATIENTCATEGORY, alias, null);

  }
 
  public com.runwaysdk.query.SelectableChar getPatientCategory(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(dss.vector.solutions.export.IndividualCaseExcelView.PATIENTCATEGORY, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableBoolean getPregnant()
  {
    return getPregnant(null);

  }
 
  public com.runwaysdk.query.SelectableBoolean getPregnant(String alias)
  {
    return (com.runwaysdk.query.SelectableBoolean)this.getSelectable(dss.vector.solutions.export.IndividualCaseExcelView.PREGNANT, alias, null);

  }
 
  public com.runwaysdk.query.SelectableBoolean getPregnant(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableBoolean)this.getSelectable(dss.vector.solutions.export.IndividualCaseExcelView.PREGNANT, alias, displayLabel);

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
  public com.runwaysdk.query.SelectableBoolean getProperlyRelease()
  {
    return getProperlyRelease(null);

  }
 
  public com.runwaysdk.query.SelectableBoolean getProperlyRelease(String alias)
  {
    return (com.runwaysdk.query.SelectableBoolean)this.getSelectable(dss.vector.solutions.export.IndividualCaseExcelView.PROPERLYRELEASE, alias, null);

  }
 
  public com.runwaysdk.query.SelectableBoolean getProperlyRelease(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableBoolean)this.getSelectable(dss.vector.solutions.export.IndividualCaseExcelView.PROPERLYRELEASE, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableChar getReferralReason()
  {
    return getReferralReason(null);

  }
 
  public com.runwaysdk.query.SelectableChar getReferralReason(String alias)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(dss.vector.solutions.export.IndividualCaseExcelView.REFERRALREASON, alias, null);

  }
 
  public com.runwaysdk.query.SelectableChar getReferralReason(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(dss.vector.solutions.export.IndividualCaseExcelView.REFERRALREASON, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableBoolean getReferredFrom()
  {
    return getReferredFrom(null);

  }
 
  public com.runwaysdk.query.SelectableBoolean getReferredFrom(String alias)
  {
    return (com.runwaysdk.query.SelectableBoolean)this.getSelectable(dss.vector.solutions.export.IndividualCaseExcelView.REFERREDFROM, alias, null);

  }
 
  public com.runwaysdk.query.SelectableBoolean getReferredFrom(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableBoolean)this.getSelectable(dss.vector.solutions.export.IndividualCaseExcelView.REFERREDFROM, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableBoolean getReferredTo()
  {
    return getReferredTo(null);

  }
 
  public com.runwaysdk.query.SelectableBoolean getReferredTo(String alias)
  {
    return (com.runwaysdk.query.SelectableBoolean)this.getSelectable(dss.vector.solutions.export.IndividualCaseExcelView.REFERREDTO, alias, null);

  }
 
  public com.runwaysdk.query.SelectableBoolean getReferredTo(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableBoolean)this.getSelectable(dss.vector.solutions.export.IndividualCaseExcelView.REFERREDTO, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableMoment getReleaseDate()
  {
    return getReleaseDate(null);

  }
 
  public com.runwaysdk.query.SelectableMoment getReleaseDate(String alias)
  {
    return (com.runwaysdk.query.SelectableMoment)this.getSelectable(dss.vector.solutions.export.IndividualCaseExcelView.RELEASEDATE, alias, null);

  }
 
  public com.runwaysdk.query.SelectableMoment getReleaseDate(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableMoment)this.getSelectable(dss.vector.solutions.export.IndividualCaseExcelView.RELEASEDATE, alias, displayLabel);

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
  public com.runwaysdk.query.SelectableChar getSampleType()
  {
    return getSampleType(null);

  }
 
  public com.runwaysdk.query.SelectableChar getSampleType(String alias)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(dss.vector.solutions.export.IndividualCaseExcelView.SAMPLETYPE, alias, null);

  }
 
  public com.runwaysdk.query.SelectableChar getSampleType(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(dss.vector.solutions.export.IndividualCaseExcelView.SAMPLETYPE, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableChar getSex()
  {
    return getSex(null);

  }
 
  public com.runwaysdk.query.SelectableChar getSex(String alias)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(dss.vector.solutions.export.IndividualCaseExcelView.SEX, alias, null);

  }
 
  public com.runwaysdk.query.SelectableChar getSex(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(dss.vector.solutions.export.IndividualCaseExcelView.SEX, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableChar getSymptomComments()
  {
    return getSymptomComments(null);

  }
 
  public com.runwaysdk.query.SelectableChar getSymptomComments(String alias)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(dss.vector.solutions.export.IndividualCaseExcelView.SYMPTOMCOMMENTS, alias, null);

  }
 
  public com.runwaysdk.query.SelectableChar getSymptomComments(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(dss.vector.solutions.export.IndividualCaseExcelView.SYMPTOMCOMMENTS, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableMoment getSymptomOnset()
  {
    return getSymptomOnset(null);

  }
 
  public com.runwaysdk.query.SelectableMoment getSymptomOnset(String alias)
  {
    return (com.runwaysdk.query.SelectableMoment)this.getSelectable(dss.vector.solutions.export.IndividualCaseExcelView.SYMPTOMONSET, alias, null);

  }
 
  public com.runwaysdk.query.SelectableMoment getSymptomOnset(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableMoment)this.getSelectable(dss.vector.solutions.export.IndividualCaseExcelView.SYMPTOMONSET, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableMoment getTestSampleDate()
  {
    return getTestSampleDate(null);

  }
 
  public com.runwaysdk.query.SelectableMoment getTestSampleDate(String alias)
  {
    return (com.runwaysdk.query.SelectableMoment)this.getSelectable(dss.vector.solutions.export.IndividualCaseExcelView.TESTSAMPLEDATE, alias, null);

  }
 
  public com.runwaysdk.query.SelectableMoment getTestSampleDate(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableMoment)this.getSelectable(dss.vector.solutions.export.IndividualCaseExcelView.TESTSAMPLEDATE, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableChar getTreatment()
  {
    return getTreatment(null);

  }
 
  public com.runwaysdk.query.SelectableChar getTreatment(String alias)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(dss.vector.solutions.export.IndividualCaseExcelView.TREATMENT, alias, null);

  }
 
  public com.runwaysdk.query.SelectableChar getTreatment(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(dss.vector.solutions.export.IndividualCaseExcelView.TREATMENT, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableChar getTreatmentMethod()
  {
    return getTreatmentMethod(null);

  }
 
  public com.runwaysdk.query.SelectableChar getTreatmentMethod(String alias)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(dss.vector.solutions.export.IndividualCaseExcelView.TREATMENTMETHOD, alias, null);

  }
 
  public com.runwaysdk.query.SelectableChar getTreatmentMethod(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(dss.vector.solutions.export.IndividualCaseExcelView.TREATMENTMETHOD, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableMoment getTreatmentStartDate()
  {
    return getTreatmentStartDate(null);

  }
 
  public com.runwaysdk.query.SelectableMoment getTreatmentStartDate(String alias)
  {
    return (com.runwaysdk.query.SelectableMoment)this.getSelectable(dss.vector.solutions.export.IndividualCaseExcelView.TREATMENTSTARTDATE, alias, null);

  }
 
  public com.runwaysdk.query.SelectableMoment getTreatmentStartDate(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableMoment)this.getSelectable(dss.vector.solutions.export.IndividualCaseExcelView.TREATMENTSTARTDATE, alias, displayLabel);

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
  public com.runwaysdk.query.OIterator<? extends IndividualCaseExcelView> getIterator()
  {
    com.runwaysdk.query.ValueIterator valueIterator;
    if (_pageSize != null && _pageNumber != null)
    {
      valueIterator = (com.runwaysdk.query.ValueIterator<com.runwaysdk.dataaccess.ValueObject>)this.getComponentQuery().getIterator(_pageSize, _pageNumber);
    }
    else
    {
      valueIterator = (com.runwaysdk.query.ValueIterator<com.runwaysdk.dataaccess.ValueObject>)this.getComponentQuery().getIterator();
    }
    return new com.runwaysdk.query.ViewIterator<IndividualCaseExcelView>(this.getMdClassIF(), valueIterator);
  }

}
