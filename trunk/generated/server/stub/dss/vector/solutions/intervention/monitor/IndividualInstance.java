package dss.vector.solutions.intervention.monitor;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;
import java.util.TreeSet;

import com.terraframe.mojo.dataaccess.transaction.Transaction;
import com.terraframe.mojo.query.AND;
import com.terraframe.mojo.query.Condition;
import com.terraframe.mojo.query.QueryFactory;

import dss.vector.solutions.CurrentDateProblem;
import dss.vector.solutions.general.ThresholdData;
import dss.vector.solutions.geo.generated.HealthFacility;
import dss.vector.solutions.ontology.Term;
import dss.vector.solutions.surveillance.GridComparator;
import dss.vector.solutions.surveillance.IndividualCaseSymptom;

public class IndividualInstance extends IndividualInstanceBase implements com.terraframe.mojo.generation.loader.Reloadable
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

    Condition condition = query.getHealthFacility().EQ(facility);
    condition = AND.get(condition, query.getActivelyDetected().EQ(false));
    condition = AND.get(condition, query.getFacilityVisit().GE(startDate));
    condition = AND.get(condition, query.getFacilityVisit().LE(endDate));

    query.WHERE(condition);

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
  public void applyAll(IndividualCaseSymptom[] symptom)
  {
    this.apply();

    for (IndividualCaseSymptom s : symptom)
    {
      s.overwriteParentId(this.getId());
      s.apply();
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
  public IndividualCaseSymptom[] getSymptoms()
  {
    Set<IndividualCaseSymptom> set = new TreeSet<IndividualCaseSymptom>(new GridComparator());

    for (Term d : Term.getRootChildren(getSymptomMd()))
    {
      set.add(new IndividualCaseSymptom(this.getId(), d.getId()));
    }

    for (IndividualCaseSymptom d : this.getAllSymptomsRel())
    {
      // We will only want grid options methods which are active. All active
      // methods are already in the set. Thus, if the set already contains an
      // entry for the Grid Option replace the default relationship with the
      // actual relationship
      if (set.contains(d))
      {
        set.remove(d);
        set.add(d);
      }
    }

    return set.toArray(new IndividualCaseSymptom[set.size()]);
  }

  @Override
  protected String buildKey()
  {
    Date admissionDate = this.getAdmissionDate();
    Date facilityVisit = this.getFacilityVisit();
    Boolean clinicalDiagnosis = this.getClinicalDiagnosis();
    Term labTest = this.getLabTest();
    Term treatment = this.getTreatment();
    if (admissionDate != null && facilityVisit != null && clinicalDiagnosis != null && labTest != null && treatment != null)
    {
      DateFormat format = SimpleDateFormat.getDateInstance(SimpleDateFormat.SHORT);

      return format.format(admissionDate) + "." + format.format(facilityVisit) + "." + clinicalDiagnosis + "." + labTest.getName() + "." + treatment.getName();
    }
    return this.getId();
  }
}
