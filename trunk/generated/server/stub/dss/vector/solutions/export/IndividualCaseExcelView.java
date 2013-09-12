package dss.vector.solutions.export;

import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

import com.runwaysdk.dataaccess.io.ExcelExporter;
import com.runwaysdk.dataaccess.io.ExcelImporter.ImportContext;
import com.runwaysdk.dataaccess.transaction.Transaction;
import com.runwaysdk.query.OIterator;
import com.runwaysdk.query.QueryFactory;
import com.runwaysdk.session.Session;

import dss.vector.solutions.Person;
import dss.vector.solutions.PersonQuery;
import dss.vector.solutions.PersonView;
import dss.vector.solutions.Physician;
import dss.vector.solutions.PhysicianQuery;
import dss.vector.solutions.RequiredAttributeProblem;
import dss.vector.solutions.geo.GeoHierarchy;
import dss.vector.solutions.geo.generated.GeoEntity;
import dss.vector.solutions.geo.generated.HealthFacility;
import dss.vector.solutions.intervention.monitor.DiagnosisType;
import dss.vector.solutions.intervention.monitor.IndividualCase;
import dss.vector.solutions.intervention.monitor.IndividualInstance;
import dss.vector.solutions.intervention.monitor.IndividualInstanceQuery;
import dss.vector.solutions.ontology.Term;
import dss.vector.solutions.util.HierarchyBuilder;

public class IndividualCaseExcelView extends IndividualCaseExcelViewBase implements com.runwaysdk.generation.loader.Reloadable
{
  private static final long serialVersionUID = -969902661;

  private List<Term>        symptoms;

  /**
   * Marks if the patient for this row matched to an existing instance.
   */
  private boolean           patientedExisted;

  public IndividualCaseExcelView()
  {
    super();
    this.symptoms = new LinkedList<Term>();
    this.patientedExisted = false;
  }

  public DiagnosisType convertDiagnosisType()
  {
    String diagnosisTypeString = this.getDiagnosisType();
    return ExcelEnums.getDiagnosisType(diagnosisTypeString);
  }

  @Override
  @Transaction
  public void apply()
  {
    this.syncAgeAndDateOfBirth();

    Person person = this.getPerson();

    // fetch or create
    IndividualCase individualCase = IndividualCase.searchForExistingCase(this.getDiagnosisDate(), person.getId());

    if (!individualCase.isNew())
    {
      individualCase.appLock();
    }

    individualCase.setCaseReportDate(this.getCaseReportDate());
    individualCase.setDiagnosisDate(this.getDiagnosisDate());
    individualCase.setAge(this.getAge());
    individualCase.setProbableSource(this.getProbableSource());
    individualCase.setOtherSettlements(this.getOtherSettlements());
    individualCase.setOrigin(Term.validateByDisplayLabel(this.getOrigin(), IndividualCase.getOriginMd()));
    individualCase.setPlasmaLeakageOnset(this.getPlasmaLeakageOnset());
    individualCase.setHemorrhagicOnset(this.getHemorrhagicOnset());
    individualCase.setSymptomOnset(this.getSymptomOnset());

    GeoEntity res = this.getResidence();
    if (res != null)
    {
      individualCase.setResidence(res);
    }
    else
    {
      individualCase.setResidence(person.getResidentialGeoEntity());
    }

    GeoEntity work = this.getWorkplace();
    if (work != null)
    {
      individualCase.setWorkplace(work);
    }
    else
    {
      individualCase.setWorkplace(person.getWorkGeoEntity());
    }

    // Search for an existing case if the patient exists
    IndividualInstance instance = null;
    if (this.patientedExisted)
    {
      // 1. Attempt a match on facility visit date + facility name
      IndividualInstanceQuery iiQ = individualCase.getInstances();

      iiQ.WHERE(iiQ.getFacilityVisit().EQ(this.getFacilityVisit()));
      iiQ.WHERE(iiQ.getHealthFacility().EQ(this.getHealthFacility()));

      OIterator<? extends IndividualInstance> iter = iiQ.getIterator();

      try
      {
        if (iter.hasNext())
        {
          // there should be at most one match
          instance = iter.next();
        }
      }
      finally
      {
        iter.close();
      }

      // 2. Attempt a match on facility visit date (looser criteria)
      if (instance == null)
      {
        iiQ = individualCase.getInstances();

        iiQ.WHERE(iiQ.getFacilityVisit().EQ(this.getFacilityVisit()));

        iter = iiQ.getIterator();

        try
        {
          if (iter.hasNext())
          {
            // there should be at most one match
            instance = iter.next();
          }
        }
        finally
        {
          iter.close();
        }
      }
    }

    // Is the instance still null? If so create a new instance
    if (instance == null)
    {
      instance = new IndividualInstance();
    }
    else
    {
      instance.appLock();
    }

    // Date admissionDate = this.getAdmissionDate();
    Date facilityVisit = this.getFacilityVisit();
    String diagnosisTypeString = this.getDiagnosisType();
    DiagnosisType diagType = ExcelEnums.getDiagnosisType(diagnosisTypeString);
    Term labTest = Term.validateByDisplayLabel(this.getLabTest(), IndividualInstance.getLabTestMd());
    Term treatment = Term.validateByDisplayLabel(this.getTreatment(), IndividualInstance.getTreatmentMd());

    instance.setFacilityVisit(facilityVisit);
    instance.setActivelyDetected(this.getActivelyDetected());
    instance.setCaseIdentifier(this.getCaseIdentifier());
    instance.setPhysician(getPhysician());
    instance.setHealthFacility(this.getHealthFacility());
    instance.setDetectedBy(Term.validateByDisplayLabel(this.getDetectedBy(), IndividualInstance.getDetectedByMd()));

    if (diagType == null)
    {
      if (diagnosisTypeString != null && diagnosisTypeString.length() > 0)
      {
        InvalidDiagnosisTypeProblem invalidDiagnosisTypeProblem = new InvalidDiagnosisTypeProblem();
        invalidDiagnosisTypeProblem.setDiagnosisType(diagnosisTypeString);
        invalidDiagnosisTypeProblem.throwIt();
      }
      else
      {
        RequiredAttributeProblem requiredAttributeProblem = new RequiredAttributeProblem();
        requiredAttributeProblem.setAttributeDisplayLabel(getDiagnosisTypeMd().getDisplayLabel(Session.getCurrentLocale()));
        requiredAttributeProblem.throwIt();
      }
    }
    else
    {
      instance.addDiagnosisType(diagType);
    }

    instance.setDiagnosis(Term.validateByDisplayLabel(this.getDiagnosis(), IndividualInstance.getDiagnosisMd()));
    instance.setConfirmedDiagnosis(Term.validateByDisplayLabel(this.getConfirmedDiagnosis(), IndividualInstance.getConfirmedDiagnosisMd()));
    instance.setConfirmedDiagnosisDate(this.getConfirmedDiagnosisDate());
    instance.setPatientCategory(Term.validateByDisplayLabel(this.getPatientCategory(), IndividualInstance.getPatientCategoryMd()));
    instance.setAdmissionDate(this.getAdmissionDate());
    instance.setReleaseDate(this.getReleaseDate());
    instance.setAnaemiaPatient(this.getAnaemiaPatient());
    instance.setPregnant(this.getPregnant());
    instance.setDiedInFacility(this.getDiedInFacility());
    instance.setDateOfDeath(this.getDateOfDeath());
    instance.setProperlyRelease(this.getProperlyRelease());
    instance.setReferredTo(this.getReferredTo());
    instance.setReferredFrom(this.getReferredFrom());
    instance.setReferralReason(Term.validateByDisplayLabel(this.getReferralReason(), IndividualInstance.getReferralReasonMd()));
    instance.setClassification(Term.validateByDisplayLabel(this.getClassification(), IndividualInstance.getClassificationMd()));
    instance.setSampleType(Term.validateByDisplayLabel(this.getSampleType(), IndividualInstance.getSampleTypeMd()));
    instance.setLabTest(labTest);
    instance.setTestSampleDate(this.getTestSampleDate());
    instance.setLabTestDate(this.getLabTestDate());
    instance.setTestResult(Term.validateByDisplayLabel(this.getTestResult(), IndividualInstance.getTestResultMd()));
    instance.setMalariaType(Term.validateByDisplayLabel(this.getMalariaType(), IndividualInstance.getMalariaTypeMd()));
    instance.setPrimaryInfection(Term.validateByDisplayLabel(this.getPrimaryInfection(), IndividualInstance.getPrimaryInfectionMd()));
    instance.setTreatmentMethod(Term.validateByDisplayLabel(this.getTreatmentMethod(), IndividualInstance.getTreatmentMethodMd()));
    instance.setTreatment(treatment);
    instance.setSymptomComments(this.getSymptomComments());

    individualCase.applyWithPersonId(person.getId(), instance, symptoms.toArray(new Term[symptoms.size()]));
  }

  /**
   * Uses age and Date of Birth to approximate each other if one is missing
   */
  private void syncAgeAndDateOfBirth()
  {
    Integer age2 = this.getAge();
    Date dob = this.getDateOfBirth();
    Date diagnosis = this.getDiagnosisDate();

    if (dob == null && age2 != null)
    {
      Calendar c = Calendar.getInstance();
      c.add(Calendar.YEAR, -1 * age2);
      // Ticket #2326 says dob should be January 1st
      c.set(Calendar.DAY_OF_YEAR, 1);
      this.setDateOfBirth(c.getTime());
    }
    else if (dob != null && diagnosis != null && age2 == null)
    {
      // We subtract from diagnosis date because we want to know the age at the
      // time of diagnosis, not the age now
      long difference = diagnosis.getTime() - dob.getTime();
      // Divide by the number of milliseconds in a year
      long age = difference / 31556926000l;
      this.setAge((int) age);
    }
  }

  private void updatePhysicianPerson(Physician physician, String ident, String fName, String lName, Date dob, Term sex)
  {
    Person person = physician.getPerson();
    person.appLock();

    if (ident.length() > 0)
    {
      person.setIdentifier(ident);
    }

    if (fName.length() > 0)
    {
      person.setFirstName(fName);
    }

    if (lName.length() > 0)
    {
      person.setLastName(lName);
    }

    if (dob != null)
    {
      person.setDateOfBirth(dob);
    }

    if (sex != null)
    {
      person.setSex(sex);
    }

    person.setPhysicianDelegate(physician);
    person.apply();
  }

  private Physician getPhysician()
  {
    String fName = this.getPhysicianFirstName();
    String lName = this.getPhysicianLastName();
    Date dob = this.getPhysicianDateOfBirth();
    Term sexTerm = Term.validateByDisplayLabel(this.getPhysicianSex(), PersonView.getSexMd());
    String ident = this.getPhysicianIdentifier();

    if (ident.length() > 0 || fName.length() > 0 || lName.length() > 0 || dob != null || sexTerm != null)
    {

      PhysicianQuery query = new PhysicianQuery(new QueryFactory());

      // First match on the identifier. Perform an update if one is found
      if (ident != null && ident.trim().length() > 0)
      {
        query.WHERE(query.getPerson().getIdentifier().EQ(ident));
      }
      else
      {
        if (fName.length() > 0)
        {
          query.WHERE(query.getPerson().getFirstName().EQ(fName));
        }
        if (lName.length() > 0)
        {
          query.WHERE(query.getPerson().getLastName().EQ(lName));
        }

        if (dob != null)
        {
          query.WHERE(query.getPerson().getDateOfBirth().EQ(dob));
        }

        if (sexTerm != null)
        {
          query.WHERE(query.getPerson().getSex().EQ(sexTerm));
        }
      }
      // first check to see if a physician exists with the given itentity

      OIterator<? extends Physician> iterator = query.getIterator();

      try
      {
        if (iterator.hasNext())
        {
          // physician found, perform an update
          Physician physician = iterator.next();

          // do a check for another double
          if (iterator.hasNext())
          {
            AmbiguousRecipientProblem problem = new AmbiguousRecipientProblem();
            problem.setFirstName(fName);
            problem.setLastName(lName);
            problem.setDob(dob);
            problem.setLocale(Session.getCurrentLocale());
            problem.throwIt();
          }

          updatePhysicianPerson(physician, ident, fName, lName, dob, sexTerm);

          return physician;
        }
        else
        {
          // no physician found, so create a new one
          Person person = new Person();
          person.setIdentifier(ident);
          person.setFirstName(fName);
          person.setLastName(lName);
          person.setDateOfBirth(dob);
          person.setSex(sexTerm);
          person.apply();

          Physician physician = addPhysicianDelegate(person);

          return physician;
        }
      }
      finally
      {
        iterator.close();
      }
    }

    return null;
  }

  private Physician addPhysicianDelegate(Person person)
  {
    person.lock();
    Physician physician = new Physician();
    physician.setPerson(person);
    physician.apply();
    person.setPhysicianDelegate(physician);
    person.apply();
    return physician;
  }

  /**
   * Searches for a Person with the given attributes, but creates one if none is
   * found.
   * 
   * @return
   */
  private Person getPerson()
  {
    String fName = this.getFirstName();
    String lName = this.getLastName();
    Date dob = this.getDateOfBirth();
    Term sexTerm = Term.validateByDisplayLabel(this.getSex(), PersonView.getSexMd());
    String ident = this.getIdentifier();

    PersonQuery query = new PersonQuery(new QueryFactory());
    if (ident.length() > 0)
    {
      query.WHERE(query.getIdentifier().EQ(ident));
    }
    else if (fName.length() > 0 && lName.length() > 0 && dob != null && sexTerm != null)
    {
      query.WHERE(query.getFirstName().EQ(fName));
      query.WHERE(query.getLastName().EQ(lName));
      query.WHERE(query.getDateOfBirth().EQ(dob));
      query.WHERE(query.getSex().EQ(sexTerm));

      // include the residence geo entity if it is specified
      GeoEntity residence = this.getResidence();
      if (residence != null)
      {
        query.WHERE(query.getResidentialGeoEntity().EQ(residence));
      }
    }
    else
    {
      Locale currentLocale = Session.getCurrentLocale();

      InsufficientPatientDataException ex = new InsufficientPatientDataException();
      ex.setTypeLabel(IndividualCase.getPatientMd().getDisplayLabel(currentLocale));
      ex.setIdentifierLabel(getIdentifierMd().getDisplayLabel(currentLocale));
      ex.setFirstNameLabel(getFirstNameMd().getDisplayLabel(currentLocale));
      ex.setLastNameLabel(getLastNameMd().getDisplayLabel(currentLocale));
      ex.setDobLabel(getDateOfBirthMd().getDisplayLabel(currentLocale));
      ex.setSexLabel(getSexMd().getDisplayLabel(currentLocale));
      throw ex;
    }

    OIterator<? extends Person> iterator = query.getIterator();
    try
    {
      if (iterator.hasNext())
      {
        Person person = iterator.next();
        this.patientedExisted = true;

        // we have found a Person that matches the patient.
        // Make sure this person is not ambiguous by checking
        // for another match
        if (iterator.hasNext())
        {
          AmbiguousRecipientProblem problem = new AmbiguousRecipientProblem();
          problem.setFirstName(fName);
          problem.setLastName(lName);
          problem.setDob(dob);
          problem.setLocale(Session.getCurrentLocale());
          problem.throwIt();
        }

        return person;
      }
      else
      {
        // no match found. Create a new person from a
        // template.
        this.patientedExisted = false;

        Person person = new Person();
        person.setIdentifier(ident);
        person.setFirstName(fName);
        person.setLastName(lName);
        person.setDateOfBirth(dob);
        person.setSex(sexTerm);
        person.setResidentialGeoEntity(this.getResidence());
        person.setWorkGeoEntity(this.getWorkplace());
        person.setBirthEntity(this.getBirthEntity());
        person.apply();

        return person;
      }
    }
    finally
    {
      iterator.close();
    }
  }

  public static List<String> customAttributeOrder()
  {
    LinkedList<String> list = new LinkedList<String>();
    list.add(CASEREPORTDATE);
    list.add(DIAGNOSISDATE);
    list.add(AGE);
    list.add(PROBABLESOURCE);
    list.add(IDENTIFIER);
    list.add(FIRSTNAME);
    list.add(LASTNAME);
    list.add(DATEOFBIRTH);
    list.add(SEX);
    list.add(RESIDENCETEXT);
    list.add(WORKPLACETEXT);
    list.add(BIRTHLOCATION);
    list.add(PROBABLESOURCETEXT);
    list.add(OTHERSETTLEMENTS);
    list.add(ACTIVELYDETECTED);
    list.add(CASEIDENTIFIER);
    list.add(PHYSICIANIDENTIFIER);
    list.add(PHYSICIANFIRSTNAME);
    list.add(PHYSICIANLASTNAME);
    list.add(PHYSICIANDATEOFBIRTH);
    list.add(PHYSICIANSEX);
    list.add(HEALTHFACILITY);
    list.add(DETECTEDBY);
    list.add(DIAGNOSISTYPE);
    list.add(DIAGNOSIS);
    list.add(DIAGNOSISDATE);
    list.add(CONFIRMEDDIAGNOSIS);
    list.add(CONFIRMEDDIAGNOSISDATE);
    list.add(SYMPTOMONSET);
    list.add(FACILITYVISIT);
    list.add(ORIGIN);
    list.add(PLASMALEAKAGEONSET);
    list.add(HEMORRHAGICONSET);
    list.add(PATIENTCATEGORY);
    list.add(ADMISSIONDATE);
    list.add(RELEASEDATE);
    list.add(ANAEMIAPATIENT);
    list.add(PREGNANT);
    list.add(DIEDINFACILITY);
    list.add(DATEOFDEATH);
    list.add(PROPERLYRELEASE);
    list.add(REFERREDTO);
    list.add(REFERREDFROM);
    list.add(REFERRALREASON);
    list.add(CLASSIFICATION);
    list.add(SAMPLETYPE);
    list.add(LABTEST);
    list.add(TESTSAMPLEDATE);
    list.add(LABTESTDATE);
    list.add(TESTRESULT);
    list.add(MALARIATYPE);
    list.add(PRIMARYINFECTION);
    list.add(TREATMENTMETHOD);
    list.add(TREATMENT);
    list.add(TREATMENTSTARTDATE);
    list.add(SYMPTOMCOMMENTS);

    return list;
  }

  public static void setupImportListener(ImportContext context, String... params)
  {
    context.addListener(createSettlementSubdivisionListener(RESIDENCE));
    context.addListener(createSettlementSubdivisionListener(WORKPLACE));
    context.addListener(createSettlementSubdivisionListener(PROBABLESOURCE));
    context.addListener(createSettlementSubdivisionListener(BIRTHENTITY));
    context.addListener(createHealthFacilityListener());
    context.addListener(new SymptomListener());
  }

  public static void setupExportListener(ExcelExporter exporter, String... params)
  {
    exporter.addListener(createSettlementSubdivisionListener(PROBABLESOURCE));
    exporter.addListener(createSettlementSubdivisionListener(RESIDENCE));
    exporter.addListener(createSettlementSubdivisionListener(WORKPLACE));
    exporter.addListener(createSettlementSubdivisionListener(BIRTHENTITY));
    exporter.addListener(createHealthFacilityListener());
    exporter.addListener(new SymptomListener());
  }

  private static DynamicGeoColumnListener createSettlementSubdivisionListener(String attribute)
  {
    HierarchyBuilder builder = new HierarchyBuilder();
    for (GeoHierarchy hierarchy : GeoHierarchy.getAllPoliticals())
    {
      builder.add(hierarchy);
    }
    return new DynamicGeoColumnListener(CLASS, attribute, builder);
  }

  private static DynamicGeoColumnListener createHealthFacilityListener()
  {
    HierarchyBuilder builder = new HierarchyBuilder();
    builder.add(GeoHierarchy.getGeoHierarchyFromType(HealthFacility.CLASS));
    return new DynamicGeoColumnListener(CLASS, HEALTHFACILITY, builder);
  }

  public void addSymptom(Term term)
  {
    symptoms.add(term);
  }
}
