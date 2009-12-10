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
import dss.vector.solutions.geo.generated.HealthFacility;
import dss.vector.solutions.geo.generated.SettlementSubdivision;
import dss.vector.solutions.intervention.monitor.IndividualCase;
import dss.vector.solutions.intervention.monitor.IndividualInstance;
import dss.vector.solutions.ontology.Term;
import dss.vector.solutions.surveillance.IndividualCaseSymptom;
import dss.vector.solutions.util.HierarchyBuilder;

public class IndividualCaseExcelView extends IndividualCaseExcelViewBase implements com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long serialVersionUID = -969902661;

  private List<Term>        symptomNames;
  private List<Boolean>     symptomValues;
  
  public IndividualCaseExcelView()
  {
    super();
    symptomNames = new LinkedList<Term>();
    symptomValues = new LinkedList<Boolean>();
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
      individualCase.setResidence(person.getResidentialGeoEntity());
      individualCase.applyWithPersonId(person.getId());
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
    
    IndividualCaseSymptom[] symptomArray = new IndividualCaseSymptom[symptomNames.size()];
    for (int i = 0; i < symptomArray.length; i++)
    {
      if (i < symptomValues.size())
      {
        symptomArray[i] = new IndividualCaseSymptom(instance, symptomNames.get(i));
        symptomArray[i].setHasSymptom(symptomValues.get(i));
      }
    }
    
    instance.applyAll(symptomArray);
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

  public static void setupImportListener(ExcelImporter importer, String... params)
  {
    importer.addListener(new SymptomListener());
    importer.addListener(createProbableSourceListener());
    importer.addListener(createHealthFacilityListener());
  }

  public static void setupExportListener(ExcelExporter exporter, String... params)
  {
    exporter.addListener(new SymptomListener());
    exporter.addListener(createProbableSourceListener());
    exporter.addListener(createHealthFacilityListener());
  }
  
  private static DynamicGeoColumnListener createProbableSourceListener()
  {
    HierarchyBuilder builder = new HierarchyBuilder();
    builder.add(GeoHierarchy.getGeoHierarchyFromType(SettlementSubdivision.CLASS));
    return new DynamicGeoColumnListener(CLASS, PROBABLESOURCE, builder);
  }

  private static DynamicGeoColumnListener createHealthFacilityListener()
  {
    HierarchyBuilder builder = new HierarchyBuilder();
    builder.add(GeoHierarchy.getGeoHierarchyFromType(HealthFacility.CLASS));
    return new DynamicGeoColumnListener(CLASS, HEALTHFACILITY, builder);
  }

  public void addSymptom(Term term, boolean patientHasSymptom)
  {
    symptomNames.add(term);
    symptomValues.add(patientHasSymptom);
  }
}
