/*******************************************************************************
 * Copyright (C) 2018 IVCC
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 ******************************************************************************/
package dss.vector.solutions.intervention.monitor;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import com.runwaysdk.dataaccess.transaction.SkipIfProblem;
import com.runwaysdk.dataaccess.transaction.Transaction;
import com.runwaysdk.query.OIterator;
import com.runwaysdk.query.QueryFactory;
import com.runwaysdk.session.Session;

import dss.vector.solutions.CurrentDateProblem;
import dss.vector.solutions.Person;
import dss.vector.solutions.RelativeValueProblem;
import dss.vector.solutions.general.Disease;
import dss.vector.solutions.general.ThresholdData;
import dss.vector.solutions.geo.generated.HealthFacility;
import dss.vector.solutions.ontology.Term;
import dss.vector.solutions.ontology.TermComparator;
import dss.vector.solutions.surveillance.IndividualCaseSymptom;
import dss.vector.solutions.util.MDSSProperties;

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
    // validateSymptomOnset();
    validateFacilityVisit();
    validateAdmissionDate();
    validateReleaseDate();
    validateTestSampleDate();
    validateLabTestDate();
    validateTreatmentStartDate();
    validateConfirmedDiagnosisDate();
    validateDateOfDeath();

    super.apply();

    validateFacilityOutbreak();
    
    if (this.getIndividualCase() != null)
    {
      this.getIndividualCase().validateOutbreak();
    }
  }

  @SkipIfProblem
  private void validateFacilityOutbreak()
  {
    if (this.getActivelyDetected() != null && !this.getActivelyDetected())
    {
      HealthFacility facility = this.getHealthFacility();

      Date date = this.getFacilityVisit();

      if (facility != null && date != null)
      {
        Date[] window = IndividualCase.getWindow(date);

        double count = IndividualInstance.getPassiveCount(facility, window[0], window[1]);

        ThresholdData.checkFacilityThresholdViolation(date, facility, count);
      }
    }
  }

  private static double getPassiveCount(HealthFacility facility, Date startDate, Date endDate)
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
  public void validateFacilityVisit()
  {
    if (this.getFacilityVisit() != null)
    {
      if (this.getFacilityVisit().after(new Date()))
      {
        CurrentDateProblem p = new CurrentDateProblem();
        p.setGivenDate(this.getFacilityVisit());
        p.setCurrentDate(new Date());
        p.setNotification(this, FACILITYVISIT);
        p.apply();
        p.throwIt();
      }

      if (this.getIndividualCase() != null && this.getIndividualCase().getPatient() != null && this.getIndividualCase().getPatient().getPerson() != null && this.getIndividualCase().getPatient().getPerson().getDateOfBirth() != null && this.getIndividualCase().getPatient().getPerson().getDateOfBirth().after(this.getFacilityVisit()))
      {
        RelativeValueProblem p = new RelativeValueProblem();
        p.setNotification(this, FACILITYVISIT);
        p.setAttributeDisplayLabel(getFacilityVisitMd().getDisplayLabel(Session.getCurrentLocale()));
        p.setRelation(MDSSProperties.getString("Compare_AE"));
        p.setRelativeAttributeLabel(Person.getDateOfBirthMd().getDisplayLabel(Session.getCurrentLocale()));
        p.apply();

        p.throwIt();
      }
    }
  }

  @Override
  public void validateAdmissionDate()
  {
    if (this.getAdmissionDate() != null)
    {
      if (this.getAdmissionDate().after(new Date()))
      {
        CurrentDateProblem p = new CurrentDateProblem();
        p.setGivenDate(this.getAdmissionDate());
        p.setCurrentDate(new Date());
        p.setNotification(this, ADMISSIONDATE);
        p.apply();
        p.throwIt();
      }

      if (this.getReleaseDate() != null && this.getAdmissionDate().after(this.getReleaseDate()))
      {
        RelativeValueProblem p = new RelativeValueProblem();
        p.setNotification(this, ADMISSIONDATE);
        p.setAttributeDisplayLabel(getAdmissionDateMd().getDisplayLabel(Session.getCurrentLocale()));
        p.setRelation(MDSSProperties.getString("Compare_BE"));
        p.setRelativeAttributeLabel(getReleaseDateMd().getDisplayLabel(Session.getCurrentLocale()));
        p.apply();

        p.throwIt();
      }

      if (this.getIndividualCase() != null && this.getIndividualCase().getPatient() != null && this.getIndividualCase().getPatient().getPerson() != null && this.getIndividualCase().getPatient().getPerson().getDateOfBirth() != null && this.getIndividualCase().getPatient().getPerson().getDateOfBirth().after(this.getAdmissionDate()))
      {
        RelativeValueProblem p = new RelativeValueProblem();
        p.setNotification(this, ADMISSIONDATE);
        p.setAttributeDisplayLabel(getAdmissionDateMd().getDisplayLabel(Session.getCurrentLocale()));
        p.setRelation(MDSSProperties.getString("Compare_AE"));
        p.setRelativeAttributeLabel(Person.getDateOfBirthMd().getDisplayLabel(Session.getCurrentLocale()));
        p.apply();

        p.throwIt();
      }
    }
  }

  @Override
  public void validateReleaseDate()
  {
    if (this.getReleaseDate() != null)
    {
      if (this.getReleaseDate().after(new Date()))
      {
        CurrentDateProblem p = new CurrentDateProblem();
        p.setGivenDate(this.getReleaseDate());
        p.setCurrentDate(new Date());
        p.setNotification(this, RELEASEDATE);
        p.apply();
        p.throwIt();
      }

      if (this.getIndividualCase() != null && this.getIndividualCase().getPatient() != null && this.getIndividualCase().getPatient().getPerson() != null && this.getIndividualCase().getPatient().getPerson().getDateOfBirth() != null && this.getIndividualCase().getPatient().getPerson().getDateOfBirth().after(this.getReleaseDate()))
      {
        RelativeValueProblem p = new RelativeValueProblem();
        p.setNotification(this, RELEASEDATE);
        p.setAttributeDisplayLabel(getReleaseDateMd().getDisplayLabel(Session.getCurrentLocale()));
        p.setRelation(MDSSProperties.getString("Compare_AE"));
        p.setRelativeAttributeLabel(Person.getDateOfBirthMd().getDisplayLabel(Session.getCurrentLocale()));
        p.apply();

        p.throwIt();
      }
    }
  }

  @Override
  public void validateTestSampleDate()
  {
    if (this.getTestSampleDate() != null)
    {
      if (this.getTestSampleDate().after(new Date()))
      {
        CurrentDateProblem p = new CurrentDateProblem();
        p.setGivenDate(this.getTestSampleDate());
        p.setCurrentDate(new Date());
        p.setNotification(this, TESTSAMPLEDATE);
        p.apply();
        p.throwIt();
      }

      if (this.getIndividualCase() != null && this.getIndividualCase().getPatient() != null && this.getIndividualCase().getPatient().getPerson() != null && this.getIndividualCase().getPatient().getPerson().getDateOfBirth() != null && this.getIndividualCase().getPatient().getPerson().getDateOfBirth().after(this.getTestSampleDate()))
      {
        RelativeValueProblem p = new RelativeValueProblem();
        p.setNotification(this, TESTSAMPLEDATE);
        p.setAttributeDisplayLabel(getTestSampleDateMd().getDisplayLabel(Session.getCurrentLocale()));
        p.setRelation(MDSSProperties.getString("Compare_AE"));
        p.setRelativeAttributeLabel(Person.getDateOfBirthMd().getDisplayLabel(Session.getCurrentLocale()));
        p.apply();

        p.throwIt();
      }
    }
  }

  @Override
  public void validateLabTestDate()
  {
    if (this.getLabTestDate() != null)
    {
      if (this.getLabTestDate().after(new Date()))
      {
        CurrentDateProblem p = new CurrentDateProblem();
        p.setGivenDate(this.getLabTestDate());
        p.setCurrentDate(new Date());
        p.setNotification(this, LABTESTDATE);
        p.apply();
        p.throwIt();
      }

      if (this.getIndividualCase() != null && this.getIndividualCase().getPatient() != null && this.getIndividualCase().getPatient().getPerson() != null && this.getIndividualCase().getPatient().getPerson().getDateOfBirth() != null && this.getIndividualCase().getPatient().getPerson().getDateOfBirth().after(this.getLabTestDate()))
      {
        RelativeValueProblem p = new RelativeValueProblem();
        p.setNotification(this, LABTESTDATE);
        p.setAttributeDisplayLabel(getLabTestDateMd().getDisplayLabel(Session.getCurrentLocale()));
        p.setRelation(MDSSProperties.getString("Compare_AE"));
        p.setRelativeAttributeLabel(Person.getDateOfBirthMd().getDisplayLabel(Session.getCurrentLocale()));
        p.apply();

        p.throwIt();
      }
    }
  }

  @Override
  public void validateTreatmentStartDate()
  {
    if (this.getTreatmentStartDate() != null)
    {
      if (this.getTreatmentStartDate().after(new Date()))
      {
        CurrentDateProblem p = new CurrentDateProblem();
        p.setGivenDate(this.getTreatmentStartDate());
        p.setCurrentDate(new Date());
        p.setNotification(this, TREATMENTSTARTDATE);
        p.apply();
        p.throwIt();
      }

      if (this.getIndividualCase() != null && this.getIndividualCase().getPatient() != null && this.getIndividualCase().getPatient().getPerson() != null && this.getIndividualCase().getPatient().getPerson().getDateOfBirth() != null && this.getIndividualCase().getPatient().getPerson().getDateOfBirth().after(this.getTreatmentStartDate()))
      {
        RelativeValueProblem p = new RelativeValueProblem();
        p.setNotification(this, TREATMENTSTARTDATE);
        p.setAttributeDisplayLabel(getTreatmentStartDateMd().getDisplayLabel(Session.getCurrentLocale()));
        p.setRelation(MDSSProperties.getString("Compare_AE"));
        p.setRelativeAttributeLabel(Person.getDateOfBirthMd().getDisplayLabel(Session.getCurrentLocale()));
        p.apply();

        p.throwIt();
      }
    }
  }

  @Override
  public void validateConfirmedDiagnosisDate()
  {
    if (this.getTreatmentStartDate() != null)
    {
      if (this.getTreatmentStartDate().after(new Date()))
      {
        CurrentDateProblem p = new CurrentDateProblem();
        p.setGivenDate(this.getTreatmentStartDate());
        p.setCurrentDate(new Date());
        p.setNotification(this, CONFIRMEDDIAGNOSISDATE);
        p.apply();
        p.throwIt();
      }

      if (this.getIndividualCase() != null && this.getIndividualCase().getPatient() != null && this.getIndividualCase().getPatient().getPerson() != null && this.getIndividualCase().getPatient().getPerson().getDateOfBirth() != null && this.getIndividualCase().getPatient().getPerson().getDateOfBirth().after(this.getTreatmentStartDate()))
      {
        RelativeValueProblem p = new RelativeValueProblem();
        p.setNotification(this, CONFIRMEDDIAGNOSISDATE);
        p.setAttributeDisplayLabel(getConfirmedDiagnosisDateMd().getDisplayLabel(Session.getCurrentLocale()));
        p.setRelation(MDSSProperties.getString("Compare_AE"));
        p.setRelativeAttributeLabel(Person.getDateOfBirthMd().getDisplayLabel(Session.getCurrentLocale()));
        p.apply();

        p.throwIt();
      }
    }
  }

  @Override
  public void validateDateOfDeath()
  {
    if (this.getTreatmentStartDate() != null)
    {
      if (this.getTreatmentStartDate().after(new Date()))
      {
        CurrentDateProblem p = new CurrentDateProblem();
        p.setGivenDate(this.getTreatmentStartDate());
        p.setCurrentDate(new Date());
        p.setNotification(this, DATEOFDEATH);
        p.apply();
        p.throwIt();
      }

      if (this.getIndividualCase() != null && this.getIndividualCase().getPatient() != null && this.getIndividualCase().getPatient().getPerson() != null && this.getIndividualCase().getPatient().getPerson().getDateOfBirth() != null && this.getIndividualCase().getPatient().getPerson().getDateOfBirth().after(this.getTreatmentStartDate()))
      {
        RelativeValueProblem p = new RelativeValueProblem();
        p.setNotification(this, DATEOFDEATH);
        p.setAttributeDisplayLabel(getDateOfDeathMd().getDisplayLabel(Session.getCurrentLocale()));
        p.setRelation(MDSSProperties.getString("Compare_AE"));
        p.setRelativeAttributeLabel(Person.getDateOfBirthMd().getDisplayLabel(Session.getCurrentLocale()));
        p.apply();

        p.throwIt();
      }
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
    // First delete all of the exiting relationships where the Term is not
    // in
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
    // Unlock the grid relationship also, this must be in the same
    // transaction
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
  
  /*
   * NOTE: Removed for #2747. Fixed because the key is never used in any practical manner. Instead we rely
   * on the default behavior which is return the auto-generated id.
   */
  /**
   * Builds the key that would be used by an IndividualInstance if the parameters are valid. If the key cannot
   * be built null is returned.
   * 
   * @param admissionDate
   * @param facilityVisit
   * @param diagnosisType
   * @param labTest
   * @param treatment
   * @return
  public static String buildKey(Date admissionDate, Date facilityVisit, DiagnosisType diagnosisType, Term labTest, Term treatment)
  {
    if (admissionDate != null && facilityVisit != null && diagnosisType != null && labTest != null && treatment != null)
    {
      DateFormat format = SimpleDateFormat.getDateInstance(SimpleDateFormat.SHORT);
      String diagnosisTypeName = diagnosisType.getEnumName();

      return format.format(admissionDate) + "." + format.format(facilityVisit) + "." + diagnosisTypeName + "." + labTest.getName() + "." + treatment.getName();
    }
    
    return null;
  }
   */
  
  /**
   * Builds the key for this IndividualInstance. This MUST delegate to the static buildKey() method
   * for consistency.
  @Override
  protected String buildKey()
  {
    Date admissionDate = this.getAdmissionDate();
    Date facilityVisit = this.getFacilityVisit();
    List<DiagnosisType> diagnosisTypeList = this.getDiagnosisType();
    DiagnosisType diagnosisType = diagnosisTypeList.size() > 0 ? diagnosisTypeList.get(0) : null;
    Term labTest = this.getLabTest();
    Term treatment = this.getTreatment();

    String key = buildKey(admissionDate, facilityVisit, diagnosisType, labTest, treatment);
    return key != null ? key : this.getId();
  }
  */
}
