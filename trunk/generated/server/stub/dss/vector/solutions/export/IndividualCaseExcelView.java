package dss.vector.solutions.export;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import com.terraframe.mojo.dataaccess.io.ExcelExporter;
import com.terraframe.mojo.dataaccess.io.ExcelImporter;
import com.terraframe.mojo.dataaccess.transaction.Transaction;
import com.terraframe.mojo.query.OIterator;
import com.terraframe.mojo.query.QueryFactory;

import dss.vector.solutions.Person;
import dss.vector.solutions.PersonQuery;
import dss.vector.solutions.PersonView;
import dss.vector.solutions.geo.GeoHierarchy;
import dss.vector.solutions.geo.generated.GeoEntity;
import dss.vector.solutions.geo.generated.HealthFacility;
import dss.vector.solutions.intervention.monitor.IndividualCase;
import dss.vector.solutions.intervention.monitor.IndividualInstance;
import dss.vector.solutions.ontology.Term;
import dss.vector.solutions.util.HierarchyBuilder;

public class IndividualCaseExcelView extends IndividualCaseExcelViewBase implements com.terraframe.mojo.generation.loader.Reloadable
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
    
    IndividualInstance instance = new IndividualInstance();
    instance.setIndividualCase(individualCase);
    instance.setActivelyDetected(this.getActivelyDetected());
    instance.setHealthFacility(this.getHealthFacility());
    instance.setDetectedBy(Term.validateByDisplayLabel(this.getDetectedBy(), IndividualInstance.getDetectedByMd()));
    instance.setClinicalDiagnosis(this.getClinicalDiagnosis());
    instance.setSymptomOnset(this.getSymptomOnset());
    instance.setFacilityVisit(this.getFacilityVisit());
    instance.setPatientCategory(Term.validateByDisplayLabel(this.getPatientCategory(), IndividualInstance.getPatientCategoryMd()));
    instance.setAdmissionDate(this.getAdmissionDate());
    instance.setReleaseDate(this.getReleaseDate());
    instance.setAnaemiaPatient(this.getAnaemiaPatient());
    instance.setPregnant(this.getPregnant());
    instance.setDiedInFacility(this.getDiedInFacility());
    instance.setProperlyRelease(this.getProperlyRelease());
    instance.setReferredTo(this.getReferredTo());
    instance.setReferredFrom(this.getReferredFrom());
    instance.setReferralReason(Term.validateByDisplayLabel(this.getReferralReason(), IndividualInstance.getReferralReasonMd()));
    instance.setSampleType(Term.validateByDisplayLabel(this.getSampleType(), IndividualInstance.getSampleTypeMd()));
    instance.setLabTest(Term.validateByDisplayLabel(this.getLabTest(), IndividualInstance.getLabTestMd()));
    instance.setTestSampleDate(this.getTestSampleDate());
    instance.setLabTestDate(this.getLabTestDate());
    instance.setMalariaType(Term.validateByDisplayLabel(this.getMalariaType(), IndividualInstance.getMalariaTypeMd()));
    instance.setTreatmentMethod(Term.validateByDisplayLabel(this.getTreatmentMethod(), IndividualInstance.getTreatmentMethodMd()));
    instance.setTreatment(Term.validateByDisplayLabel(this.getTreatment(), IndividualInstance.getTreatmentMd()));
    instance.setSymptomComments(this.getSymptomComments());
    
    individualCase.applyWithPersonId(person.getId(), instance, symptoms.toArray(new Term[symptoms.size()]));
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
    
    PersonQuery query = new PersonQuery(new QueryFactory());
    query.WHERE(query.getFirstName().EQ(fName));
    query.WHERE(query.getLastName().EQ(lName));
    query.WHERE(query.getDateOfBirth().EQ(dob));
    query.WHERE(query.getSex().EQ(sexTerm));
    OIterator<? extends Person> iterator = query.getIterator();
    
    if (!iterator.hasNext())
    {
      Person person = new Person();
      person.setFirstName(fName);
      person.setLastName(lName);
      person.setDateOfBirth(dob);
      person.setSex(sexTerm);
      person.setResidentialGeoEntity(this.getResidence());
      person.setWorkGeoEntity(this.getWorkplace());
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
    list.add(FIRSTNAME);
    list.add(LASTNAME);
    list.add(DATEOFBIRTH);
    list.add(SEX);
    list.add(ACTIVELYDETECTED);
    list.add(HEALTHFACILITY);
    list.add(DETECTEDBY);
    list.add(CLINICALDIAGNOSIS);
    list.add(SYMPTOMONSET);
    list.add(FACILITYVISIT);
    list.add(PATIENTCATEGORY);
    list.add(ADMISSIONDATE);
    list.add(RELEASEDATE);
    list.add(ANAEMIAPATIENT);
    list.add(PREGNANT);
    list.add(DIEDINFACILITY);
    list.add(PROPERLYRELEASE);
    list.add(REFERREDTO);
    list.add(REFERREDFROM);
    list.add(REFERRALREASON);
    list.add(SAMPLETYPE);
    list.add(LABTEST);
    list.add(TESTSAMPLEDATE);
    list.add(LABTESTDATE);
    list.add(MALARIATYPE);
    list.add(TREATMENTMETHOD);
    list.add(TREATMENT);
    list.add(TREATMENTSTARTDATE);
    list.add(SYMPTOMCOMMENTS);
    return list;
  }

  public static void setupImportListener(ExcelImporter importer, String... params)
  {
    importer.addListener(new SymptomListener());
    importer.addListener(createSettlementSubdivisionListener(PROBABLESOURCE));
    importer.addListener(createSettlementSubdivisionListener(RESIDENCE));
    importer.addListener(createSettlementSubdivisionListener(WORKPLACE));
    importer.addListener(createHealthFacilityListener());
  }

  public static void setupExportListener(ExcelExporter exporter, String... params)
  {
    exporter.addListener(new SymptomListener());
    exporter.addListener(createSettlementSubdivisionListener(PROBABLESOURCE));
    exporter.addListener(createSettlementSubdivisionListener(RESIDENCE));
    exporter.addListener(createSettlementSubdivisionListener(WORKPLACE));
    exporter.addListener(createHealthFacilityListener());
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
