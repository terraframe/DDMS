package dss.vector.solutions.intervention.monitor;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import com.runwaysdk.dataaccess.transaction.Transaction;
import com.runwaysdk.query.OIterator;
import com.runwaysdk.query.QueryFactory;

import dss.vector.solutions.CurrentDateProblem;
import dss.vector.solutions.general.Disease;
import dss.vector.solutions.general.ThresholdData;
import dss.vector.solutions.geo.generated.HealthFacility;
import dss.vector.solutions.ontology.Term;
import dss.vector.solutions.ontology.TermComparator;
import dss.vector.solutions.surveillance.IndividualCaseSymptom;

public class IndividualInstance extends IndividualInstanceBase implements com.runwaysdk.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1254360074462L;

  public IndividualInstance()
  {
    super();
  }

  @Override
  public String toString()
  {
    if (this.isNew())
    {
      return "New: " + this.getClassDisplayLabel();
    }

    return this.getClassDisplayLabel();
  }

  @Override
  @Transaction
  public void apply()
  {
    validateSymptomOnset();
    validateFacilityVisit();
    validateAdmissionDate();
    validateReleaseDate();
    validateTestSampleDate();
    validateLabTestDate();
    validateTreatmentStartDate();

    super.apply();

    validateFacilityOutbreak();
  }

  private void validateFacilityOutbreak()
  {
    if (this.getActivelyDetected() != null && !this.getActivelyDetected())
    {
      HealthFacility facility = this.getHealthFacility();

      Date date = this.getFacilityVisit();

      if (facility != null && date != null)
      {
        Date[] window = IndividualCase.getWindow(date);

        long count = IndividualInstance.getPassiveCount(facility, window[0], window[1]);

        ThresholdData.checkFacilityThresholdViolation(date, facility, count);
      }
    }
  }

  private static long getPassiveCount(HealthFacility facility, Date startDate, Date endDate)
  {
    QueryFactory factory = new QueryFactory();

    IndividualInstanceQuery query = new IndividualInstanceQuery(factory);
    query.WHERE(query.getIndividualCase().getDisease().EQ(Disease.getCurrent()));
    query.AND(query.getHealthFacility().EQ(facility));
    query.AND(query.getActivelyDetected().EQ(false));
    query.AND(query.getFacilityVisit().GE(startDate));
    query.AND(query.getFacilityVisit().LE(endDate));

    return query.getCount();
  }

  @Override
  public void validateSymptomOnset()
  {
    if (this.getSymptomOnset() != null && this.getSymptomOnset().after(new Date()))
    {
      CurrentDateProblem p = new CurrentDateProblem();
      p.setGivenDate(this.getSymptomOnset());
      p.setCurrentDate(new Date());
      p.setNotification(this, SYMPTOMONSET);
      p.apply();
      p.throwIt();
    }
  }

  @Override
  public void validateFacilityVisit()
  {
    if (this.getFacilityVisit() != null && this.getFacilityVisit().after(new Date()))
    {
      CurrentDateProblem p = new CurrentDateProblem();
      p.setGivenDate(this.getFacilityVisit());
      p.setCurrentDate(new Date());
      p.setNotification(this, FACILITYVISIT);
      p.apply();
      p.throwIt();
    }
  }

  @Override
  public void validateAdmissionDate()
  {
    if (this.getAdmissionDate() != null && this.getAdmissionDate().after(new Date()))
    {
      CurrentDateProblem p = new CurrentDateProblem();
      p.setGivenDate(this.getAdmissionDate());
      p.setCurrentDate(new Date());
      p.setNotification(this, ADMISSIONDATE);
      p.apply();
      p.throwIt();
    }
  }

  @Override
  public void validateReleaseDate()
  {
    if (this.getReleaseDate() != null && this.getReleaseDate().after(new Date()))
    {
      CurrentDateProblem p = new CurrentDateProblem();
      p.setGivenDate(this.getReleaseDate());
      p.setCurrentDate(new Date());
      p.setNotification(this, RELEASEDATE);
      p.apply();
      p.throwIt();
    }
  }

  @Override
  public void validateTestSampleDate()
  {
    if (this.getTestSampleDate() != null && this.getTestSampleDate().after(new Date()))
    {
      CurrentDateProblem p = new CurrentDateProblem();
      p.setGivenDate(this.getTestSampleDate());
      p.setCurrentDate(new Date());
      p.setNotification(this, TESTSAMPLEDATE);
      p.apply();
      p.throwIt();
    }
  }

  @Override
  public void validateLabTestDate()
  {
    if (this.getLabTestDate() != null && this.getLabTestDate().after(new Date()))
    {
      CurrentDateProblem p = new CurrentDateProblem();
      p.setGivenDate(this.getLabTestDate());
      p.setCurrentDate(new Date());
      p.setNotification(this, LABTESTDATE);
      p.apply();
      p.throwIt();
    }
  }

  @Override
  public void validateTreatmentStartDate()
  {
    if (this.getTreatmentStartDate() != null && this.getTreatmentStartDate().after(new Date()))
    {
      CurrentDateProblem p = new CurrentDateProblem();
      p.setGivenDate(this.getTreatmentStartDate());
      p.setCurrentDate(new Date());
      p.setNotification(this, TREATMENTSTARTDATE);
      p.apply();
      p.throwIt();
    }
  }

  @Transaction
  @Override
  public void applyAll(Term[] symptoms)
  {
    this.apply();

    List<Term> list = Arrays.asList(symptoms);
    this.clearSymptoms(list);
    this.setSymptoms(list);
  }

  private void clearSymptoms(List<Term> symptoms)
  {
    // First delete all of the exiting relationships where the Term is not in
    // the result list
    List<? extends IndividualCaseSymptom> relationships = this.getAllSymptomsRel().getAll();

    for (IndividualCaseSymptom relationship : relationships)
    {
      if (!symptoms.contains(relationship.getChild()))
      {
        relationship.delete();
      }
    }
  }

  private void setSymptoms(List<Term> symptoms)
  {
    Set<Term> set = new TreeSet<Term>(new TermComparator());
    set.addAll(symptoms);

    List<? extends Term> existing = this.getAllSymptoms().getAll();

    // Get all of the new results which this Person does not already have
    set.removeAll(existing);

    for (Term result : set)
    {
      IndividualCaseSymptom relationship = this.addSymptoms(result);
      relationship.setHasSymptom(true);
      relationship.apply();
    }
  }

  @Override
  @Transaction
  public void lock()
  {
    super.lock();

    // Lock the grid relationship also, this must be in the same transaction
    for (IndividualCaseSymptom symptom : this.getAllSymptomsRel())
    {
      symptom.lock();
    }
  }

  @Override
  @Transaction
  public void unlock()
  {
    // Unlock the grid relationship also, this must be in the same transaction
    for (IndividualCaseSymptom symptom : this.getAllSymptomsRel())
    {
      symptom.unlock();
    }

    super.unlock();
  }

  /**
   * Gets the symptom relationships associated with this case
   * 
   * @return
   */
  @Override
  public Term[] getSymptoms()
  {
    OIterator<? extends Term> it = this.getAllSymptoms();

    try
    {
      List<? extends Term> results = it.getAll();

      return results.toArray(new Term[results.size()]);
    }
    finally
    {
      it.close();
    }
  }

  @Override
  protected String buildKey()
  {
    Date admissionDate = this.getAdmissionDate();
    Date facilityVisit = this.getFacilityVisit();
    List<DiagnosisType> diagnosisType = this.getDiagnosisType();
    Term labTest = this.getLabTest();
    Term treatment = this.getTreatment();

    if (admissionDate != null && facilityVisit != null && diagnosisType.size() > 0 && labTest != null && treatment != null)
    {
      DateFormat format = SimpleDateFormat.getDateInstance(SimpleDateFormat.SHORT);
      String diagnosisTypeName = diagnosisType.get(0).getEnumName();

      return format.format(admissionDate) + "." + format.format(facilityVisit) + "." + diagnosisTypeName + "." + labTest.getName() + "." + treatment.getName();
    }
    return this.getId();
  }
}
