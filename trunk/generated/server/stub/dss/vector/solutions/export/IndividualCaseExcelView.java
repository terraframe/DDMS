package dss.vector.solutions.export;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

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
import dss.vector.solutions.RequiredAttributeProblem;
import dss.vector.solutions.geo.GeoHierarchy;
import dss.vector.solutions.geo.generated.GeoEntity;
import dss.vector.solutions.geo.generated.HealthFacility;
import dss.vector.solutions.intervention.monitor.DiagnosisType;
import dss.vector.solutions.intervention.monitor.IndividualCase;
import dss.vector.solutions.intervention.monitor.IndividualInstance;
import dss.vector.solutions.ontology.Term;
import dss.vector.solutions.util.HierarchyBuilder;

public class IndividualCaseExcelView extends IndividualCaseExcelViewBase implements com.runwaysdk.generation.loader.Reloadable
{
  private static final long serialVersionUID = -969902661;

  private List<Term>        symptoms;
  
  public IndividualCaseExcelView()
  {
    super();
    symptoms = new LinkedList<Term>();
  }

  @Override
  @Transaction
  public void apply()
  {
    Person person = getPerson();
    
    IndividualCase individualCase = IndividualCase.searchForExistingCase(this.getDiagnosisDate(), person.getId());
    if (individualCase.isNew())
    {
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
      if (res!=null)
      {
        individualCase.setResidence(res);
      }
      else
      {
        individualCase.setResidence(person.getResidentialGeoEntity());
      }
      
      GeoEntity work = this.getWorkplace();
      if (work!=null)
      {
        individualCase.setWorkplace(work);
      }
      else
      {
        individualCase.setWorkplace(person.getWorkGeoEntity());
      }
    }
    else
    {
      individualCase.lock();
    }
    
    IndividualInstance instance = new IndividualInstance();
    instance.setActivelyDetected(this.getActivelyDetected());
    instance.setCaseIdentifier(this.getCaseIdentifier());
    instance.setPhysician(getPhysician());
    instance.setHealthFacility(this.getHealthFacility());
    instance.setDetectedBy(Term.validateByDisplayLabel(this.getDetectedBy(), IndividualInstance.getDetectedByMd()));
    
    String diagnosisTypeString = this.getDiagnosisType();
    DiagnosisType diagType = ExcelEnums.getDiagnosisType(diagnosisTypeString);
    if (diagType==null)
    {
      if (diagnosisTypeString!=null && diagnosisTypeString.length()>0)
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
    instance.setFacilityVisit(this.getFacilityVisit());
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
    instance.setLabTest(Term.validateByDisplayLabel(this.getLabTest(), IndividualInstance.getLabTestMd()));
    instance.setTestSampleDate(this.getTestSampleDate());
    instance.setLabTestDate(this.getLabTestDate());
    instance.setTestResult(Term.validateByDisplayLabel(this.getTestResult(), IndividualInstance.getTestResultMd()));
    instance.setMalariaType(Term.validateByDisplayLabel(this.getMalariaType(), IndividualInstance.getMalariaTypeMd()));
    instance.setPrimaryInfection(Term.validateByDisplayLabel(this.getPrimaryInfection(), IndividualInstance.getPrimaryInfectionMd()));
    instance.setTreatmentMethod(Term.validateByDisplayLabel(this.getTreatmentMethod(), IndividualInstance.getTreatmentMethodMd()));
    instance.setTreatment(Term.validateByDisplayLabel(this.getTreatment(), IndividualInstance.getTreatmentMd()));
    instance.setSymptomComments(this.getSymptomComments());
    
    individualCase.applyWithPersonId(person.getId(), instance, symptoms.toArray(new Term[symptoms.size()]));
  }

  private Physician getPhysician()
  {
    String fName = this.getPhysicianFirstName();
    String lName = this.getPhysicianLastName();
    Date dob = this.getPhysicianDateOfBirth();
    Term sexTerm = Term.validateByDisplayLabel(this.getPhysicianSex(), PersonView.getSexMd());
    String ident = this.getPhysicianIdentifier();
    
    PersonQuery query = new PersonQuery(new QueryFactory());
    int conditions = 0;
    if (ident.length()>0)
    {
      query.WHERE(query.getIdentifier().EQ(ident));
      conditions++;
    }
    if (fName.length()>0)
    {
      query.WHERE(query.getFirstName().EQ(fName));
      conditions++;
    }
    if (lName.length()>0)
    {
      query.WHERE(query.getLastName().EQ(lName));
      conditions++;
    }
    if (dob!=null)
    {
      query.WHERE(query.getDateOfBirth().EQ(dob));
      conditions++;
    }
    if (sexTerm!=null)
    {
      query.WHERE(query.getSex().EQ(sexTerm));
      conditions++;
    }
    
    // No physician data specified.  Return null.
    if (conditions==0)
    {
      return null;
    }
    
    OIterator<? extends Person> iterator = query.getIterator();
    if (!iterator.hasNext())
    {
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
    
    Person person = iterator.next();
    if (iterator.hasNext())
    {
      iterator.close();
      AmbiguousRecipientProblem problem = new AmbiguousRecipientProblem();
      problem.setFirstName(fName);
      problem.setLastName(lName);
      problem.setDob(dob);
      problem.throwIt();
    }
    Physician physician = person.getPhysicianDelegate();
    if (physician==null)
    {
      physician = addPhysicianDelegate(person);
    }
    return physician;
  }

  private Physician addPhysicianDelegate(Person person)
  {
    person.lock();
    Physician physician = new Physician();
    physician.setPerson(person);
    physician.apply();
    person.apply();
    return physician;
  }

  /**
   * Searches for a Person with the given attributes, but creates one if none is found.
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
    if (ident.length()>0)
    {
      query.WHERE(query.getIdentifier().EQ(ident));
    }
    if (fName.length()>0)
    {
      query.WHERE(query.getFirstName().EQ(fName));
    }
    if (lName.length()>0)
    {
      query.WHERE(query.getLastName().EQ(lName));
    }
    if (dob!=null)
    {
      query.WHERE(query.getDateOfBirth().EQ(dob));
    }
    if (sexTerm!=null)
    {
      query.WHERE(query.getSex().EQ(sexTerm));
    }
    OIterator<? extends Person> iterator = query.getIterator();
    
    if (!iterator.hasNext())
    {
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
    
    Person person = iterator.next();
    if (iterator.hasNext())
    {
      iterator.close();
      AmbiguousRecipientProblem problem = new AmbiguousRecipientProblem();
      problem.setFirstName(fName);
      problem.setLastName(lName);
      problem.setDob(dob);
      problem.throwIt();
    }
    return person;
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
