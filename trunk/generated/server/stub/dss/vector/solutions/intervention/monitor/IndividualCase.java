package dss.vector.solutions.intervention.monitor;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.runwaysdk.dataaccess.MdAttributeDAOIF;
import com.runwaysdk.dataaccess.MdClassDAOIF;
import com.runwaysdk.dataaccess.metadata.MdClassDAO;
import com.runwaysdk.dataaccess.transaction.SkipIfProblem;
import com.runwaysdk.dataaccess.transaction.Transaction;
import com.runwaysdk.query.OIterator;
import com.runwaysdk.query.QueryFactory;
import com.runwaysdk.query.ValueQuery;
import com.runwaysdk.session.Session;

import dss.vector.solutions.CurrentDateProblem;
import dss.vector.solutions.Patient;
import dss.vector.solutions.Person;
import dss.vector.solutions.Property;
import dss.vector.solutions.RelativeValueProblem;
import dss.vector.solutions.RequiredAttributeException;
import dss.vector.solutions.general.Disease;
import dss.vector.solutions.general.EpiCache;
import dss.vector.solutions.general.EpiDate;
import dss.vector.solutions.general.OutbreakCalculation;
import dss.vector.solutions.general.ThresholdAlertCalculationType;
import dss.vector.solutions.general.ThresholdData;
import dss.vector.solutions.geo.generated.GeoEntity;
import dss.vector.solutions.geo.generated.GeoEntityQuery;
import dss.vector.solutions.ontology.Term;
import dss.vector.solutions.query.Layer;
import dss.vector.solutions.querybuilder.IndividualCaseQB;
import dss.vector.solutions.threshold.PoliticalThresholdCalculator;
import dss.vector.solutions.util.MDSSProperties;

public class IndividualCase extends IndividualCaseBase implements com.runwaysdk.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1254360073722L;

  public IndividualCase()
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
    validateDiagnosisDate();
    validateCaseEntryDate();
    validateCaseReportDate();
    validateHemorrhagicOnset();
    validatePlasmaLeakageOnset();

    if (this.isNew() && this.getDisease() == null)
    {
      this.setDisease(Disease.getCurrent());
    }

    if (this.getProbableSource() == null)
    {
      this.setProbableSource(this.getResidence());
    }

    if (this.isNew())
    {
      // Truncate the createdByDate and store it in entry date
      this.setCaseEntryDate(new Date());
    }

    // If no age is specified, calculate it
    if (this.getAge() == null && this.getDiagnosisDate() != null && this.getPatient() != null)
    {
      Date dob = this.getPatient().getPerson().getDateOfBirth();
      if (dob != null)
      {
        long difference = this.getDiagnosisDate().getTime() - dob.getTime();
        // Divide by the number of milliseconds in a year
        long age = difference / 31556926000l;
        this.setAge((int) age);
      }
    }

    super.apply();

    if (this.getSymptomOnset() == null)
    {
      this.setSymptomOnset(this.getDiagnosisDate());

      super.apply();
    }

    // Perfrom outbreak notification
    // validateOutbreak();
  }

  @Override
  public void validateSymptomOnset()
  {
    if (this.getSymptomOnset() != null)
    {
      if (this.getSymptomOnset().after(new Date()))
      {
        CurrentDateProblem p = new CurrentDateProblem();
        p.setGivenDate(this.getSymptomOnset());
        p.setCurrentDate(new Date());
        p.setNotification(this, SYMPTOMONSET);
        p.apply();
        p.throwIt();
      }

      if (this.getPatient() != null && this.getPatient().getPerson() != null && this.getPatient().getPerson().getDateOfBirth() != null && this.getPatient().getPerson().getDateOfBirth().after(this.getSymptomOnset()))
      {
        RelativeValueProblem p = new RelativeValueProblem();
        p.setNotification(this, SYMPTOMONSET);
        p.setAttributeDisplayLabel(getSymptomOnsetMd().getDisplayLabel(Session.getCurrentLocale()));
        p.setRelation(MDSSProperties.getString("Compare_AE"));
        p.setRelativeAttributeLabel(Person.getDateOfBirthMd().getDisplayLabel(Session.getCurrentLocale()));
        p.apply();

        p.throwIt();
      }
    }
  }

  @Override
  public void validateCaseReportDate()
  {
    if (this.getCaseReportDate() != null)
    {
      if (this.getCaseReportDate().after(new Date()))
      {
        CurrentDateProblem p = new CurrentDateProblem();
        p.setGivenDate(this.getCaseReportDate());
        p.setCurrentDate(new Date());
        p.setNotification(this, CASEREPORTDATE);
        p.apply();
        p.throwIt();
      }

      if (this.getDiagnosisDate() != null && this.getCaseReportDate().before(this.getDiagnosisDate()))
      {
        RelativeValueProblem p = new RelativeValueProblem();
        p.setNotification(this, CASEREPORTDATE);
        p.setAttributeDisplayLabel(getCaseReportDateMd().getDisplayLabel(Session.getCurrentLocale()));
        p.setRelation(MDSSProperties.getString("Compare_AE"));
        p.setRelativeAttributeLabel(getDiagnosisDateMd().getDisplayLabel(Session.getCurrentLocale()));
        p.apply();

        p.throwIt();
      }

      if (this.getPatient() != null && this.getPatient().getPerson() != null && this.getPatient().getPerson().getDateOfBirth() != null && this.getPatient().getPerson().getDateOfBirth().after(this.getCaseReportDate()))
      {
        RelativeValueProblem p = new RelativeValueProblem();
        p.setNotification(this, CASEREPORTDATE);
        p.setAttributeDisplayLabel(getCaseReportDateMd().getDisplayLabel(Session.getCurrentLocale()));
        p.setRelation(MDSSProperties.getString("Compare_AE"));
        p.setRelativeAttributeLabel(Person.getDateOfBirthMd().getDisplayLabel(Session.getCurrentLocale()));
        p.apply();

        p.throwIt();
      }
    }
  }

  @Override
  public void validateCaseEntryDate()
  {
    if (this.getCaseEntryDate() != null)
    {
      if (this.getCaseEntryDate().after(new Date()))
      {
        CurrentDateProblem p = new CurrentDateProblem();
        p.setGivenDate(this.getCaseEntryDate());
        p.setCurrentDate(new Date());
        p.setNotification(this, CASEENTRYDATE);
        p.apply();
        p.throwIt();
      }

      if (this.getPatient() != null && this.getPatient().getPerson() != null && this.getPatient().getPerson().getDateOfBirth() != null && this.getPatient().getPerson().getDateOfBirth().after(this.getCaseEntryDate()))
      {
        RelativeValueProblem p = new RelativeValueProblem();
        p.setNotification(this, CASEENTRYDATE);
        p.setAttributeDisplayLabel(getCaseEntryDateMd().getDisplayLabel(Session.getCurrentLocale()));
        p.setRelation(MDSSProperties.getString("Compare_AE"));
        p.setRelativeAttributeLabel(Person.getDateOfBirthMd().getDisplayLabel(Session.getCurrentLocale()));
        p.apply();

        p.throwIt();
      }
    }
  }

  @Override
  public void validateDiagnosisDate()
  {
    if (this.getDiagnosisDate() != null)
    {
      if (this.getDiagnosisDate().after(new Date()))
      {
        CurrentDateProblem p = new CurrentDateProblem();
        p.setGivenDate(this.getDiagnosisDate());
        p.setCurrentDate(new Date());
        p.setNotification(this, DIAGNOSISDATE);
        p.apply();
        p.throwIt();
      }

      if (this.getPatient() != null && this.getPatient().getPerson() != null && this.getPatient().getPerson().getDateOfBirth() != null && this.getPatient().getPerson().getDateOfBirth().after(this.getDiagnosisDate()))
      {
        RelativeValueProblem p = new RelativeValueProblem();
        p.setNotification(this, DIAGNOSISDATE);
        p.setAttributeDisplayLabel(getDiagnosisDateMd().getDisplayLabel(Session.getCurrentLocale()));
        p.setRelation(MDSSProperties.getString("Compare_AE"));
        p.setRelativeAttributeLabel(Person.getDateOfBirthMd().getDisplayLabel(Session.getCurrentLocale()));
        p.apply();

        p.throwIt();
      }
    }
  }

  @Override
  public void validateHemorrhagicOnset()
  {
    if (this.getHemorrhagicOnset() != null)
    {
      if (this.getHemorrhagicOnset().after(new Date()))
      {
        CurrentDateProblem p = new CurrentDateProblem();
        p.setGivenDate(this.getHemorrhagicOnset());
        p.setCurrentDate(new Date());
        p.setNotification(this, HEMORRHAGICONSET);
        p.apply();
        p.throwIt();
      }

      if (this.getPatient() != null && this.getPatient().getPerson() != null && this.getPatient().getPerson().getDateOfBirth() != null && this.getPatient().getPerson().getDateOfBirth().after(this.getHemorrhagicOnset()))
      {
        RelativeValueProblem p = new RelativeValueProblem();
        p.setNotification(this, HEMORRHAGICONSET);
        p.setAttributeDisplayLabel(getHemorrhagicOnsetMd().getDisplayLabel(Session.getCurrentLocale()));
        p.setRelation(MDSSProperties.getString("Compare_AE"));
        p.setRelativeAttributeLabel(Person.getDateOfBirthMd().getDisplayLabel(Session.getCurrentLocale()));
        p.apply();

        p.throwIt();
      }
    }
  }

  @Override
  public void validatePlasmaLeakageOnset()
  {
    if (this.getPlasmaLeakageOnset() != null)
    {
      if (this.getPlasmaLeakageOnset().after(new Date()))
      {
        CurrentDateProblem p = new CurrentDateProblem();
        p.setGivenDate(this.getPlasmaLeakageOnset());
        p.setCurrentDate(new Date());
        p.setNotification(this, PLASMALEAKAGEONSET);
        p.apply();
        p.throwIt();
      }

      if (this.getPatient() != null && this.getPatient().getPerson() != null && this.getPatient().getPerson().getDateOfBirth() != null && this.getPatient().getPerson().getDateOfBirth().after(this.getPlasmaLeakageOnset()))
      {
        RelativeValueProblem p = new RelativeValueProblem();
        p.setNotification(this, PLASMALEAKAGEONSET);
        p.setAttributeDisplayLabel(getPlasmaLeakageOnsetMd().getDisplayLabel(Session.getCurrentLocale()));
        p.setRelation(MDSSProperties.getString("Compare_AE"));
        p.setRelativeAttributeLabel(Person.getDateOfBirthMd().getDisplayLabel(Session.getCurrentLocale()));
        p.apply();

        p.throwIt();
      }
    }
  }

  @SkipIfProblem
  public void validateOutbreak()
  {
    GeoEntity geoEntity = this.getProbableSource();
    Date date = this.getDiagnosisDate();

    if (geoEntity != null && date != null)
    {
      Date[] window = IndividualCase.getWindow(date);

      // Get all relevant Geo Entities
      GeoEntity[] entities = geoEntity.getPopulationAncestors();

      // First get the Threshold data for the relevant GeoEntities
      for (GeoEntity entity : entities)
      {
        double count = IndividualCase.getCount(entity, window[0], window[1]);

        ThresholdData.checkThresholdViolation(date, entity, count, this.getSymptomOnset());
      }
    }
  }

  protected static Date[] getWindow(Date date)
  {
    ThresholdAlertCalculationType config = ThresholdAlertCalculationType.getCurrent();

    if (config.getCountingMethod().contains(OutbreakCalculation.SLIDING_WEEK))
    {
      // Use the sliding window approach
      Calendar calendar = Calendar.getInstance();
      calendar.setTime(date);
      calendar.set(Calendar.DAY_OF_YEAR, -6);

      return new Date[] { calendar.getTime(), date };
    }

    // Use the Epi week approach
    EpiDate week = EpiCache.getDate(date);

    return new Date[] { week.getStartDate(), week.getEndDate() };
  }

  private static double getCount(GeoEntity entity, Date startDate, Date endDate)
  {
    QueryFactory factory = new QueryFactory();

    GeoEntityQuery entityQuery = entity.getPoliticalDecendants(factory);

    PoliticalThresholdCalculator calc = new PoliticalThresholdCalculator();
    double[] counts = calc.getIndividualCounts(factory, entityQuery, startDate, endDate);

    ThresholdAlertCalculationType config = ThresholdAlertCalculationType.getCurrent();

    double ratio = (double) config.getClinicalPositivePercentage() / 100.0d;
    return counts[PoliticalThresholdCalculator.POSITIVE_COUNT_INDEX] + ( counts[PoliticalThresholdCalculator.CLINICAL_COUNT_INDEX] * ratio );
  }

  public static IndividualCase searchForExistingCase(Date diagnosisDate, String personId)
  {
    if (diagnosisDate == null)
    {
      diagnosisDate = new Date();
    }

    if (personId == null || personId.length() == 0)
    {
      MdClassDAOIF mdClass = MdClassDAO.getMdClassDAO(IndividualCase.CLASS);
      MdAttributeDAOIF mdAttribute = mdClass.definesAttribute(IndividualCase.PATIENT);

      RequiredAttributeException exception = new RequiredAttributeException();
      exception.setAttributeLabel(mdAttribute.getDisplayLabel(Session.getCurrentLocale()));

      throw exception;
    }

    int newCasePeriod = Property.getInt("dss.vector.solutions.intervention.monitor", "newCasePeriod");
    Calendar calendar = Calendar.getInstance();
    calendar.setTime(diagnosisDate);
    calendar.add(Calendar.DAY_OF_MONTH, -7 * newCasePeriod);

    Date newCasePeriodCutoff = calendar.getTime();

    Person person = Person.get(personId);
    Patient patient = person.getPatientDelegate();

    if (patient != null)
    {
      IndividualCaseQuery query = new IndividualCaseQuery(new QueryFactory());
      query.WHERE(query.getDisease().EQ(Disease.getCurrent()));
      query.AND(query.getDiagnosisDate().GE(newCasePeriodCutoff));
      query.AND(query.getDiagnosisDate().LE(diagnosisDate));
      query.AND(query.getPatient().EQ(patient));
      query.ORDER_BY_DESC(query.getDiagnosisDate());

      OIterator<? extends IndividualCase> iterator = query.getIterator();
      try
      {
        if (iterator.hasNext())
        {
          // match found, return it
          return iterator.next();
        }
      }
      finally
      {
        iterator.close();
      }
    }

    // no match found, so create a new instance and give it defaults from Person
    IndividualCase individualCase = new IndividualCase();
    if (individualCase.getResidence() == null)
    {
      individualCase.setResidence(person.getResidentialGeoEntity());
    }
    if (individualCase.getResidenceText() == null)
    {
      individualCase.setResidenceText(person.getResidentialInformation());
    }
    if (individualCase.getWorkplace() == null)
    {
      individualCase.setWorkplace(person.getWorkGeoEntity());
    }
    if (individualCase.getWorkplaceText() == null)
    {
      individualCase.setWorkplaceText(person.getWorkInformation());
    }

    return individualCase;
  }

  @Transaction
  public void applyWithPersonId(Person person, IndividualInstance instance, Term[] symptoms)
  {
    Patient patient = person.getPatientDelegate();
    if (patient == null)
    {
      patient = new Patient();
      patient.setPerson(person);
      patient.apply();

      person.appLock();
      person.setPatientDelegate(patient);
      person.apply();
    }

    this.setPatient(patient);
    this.apply();

    instance.setIndividualCase(this);
    instance.applyAll(symptoms);
  }

  @Override
  @Transaction
  public void applyWithPersonId(String personId, IndividualInstance instance, Term[] symptoms)
  {
    Person person = Person.get(personId);
    this.applyWithPersonId(person, instance, symptoms);
  }

  public IndividualInstanceQuery getInstances()
  {
    IndividualInstanceQuery query = new IndividualInstanceQuery(new QueryFactory());
    query.WHERE(query.getIndividualCase().EQ(this));
    return query;
  }

  @Override
  protected String buildKey()
  {
    Patient patient = this.getPatient();
    GeoEntity source = this.getProbableSource();
    Date date = this.getDiagnosisDate();
    if (patient != null && date != null && source != null)
    {
      DateFormat format = SimpleDateFormat.getDateInstance(SimpleDateFormat.SHORT);
      Person person = patient.getPerson();

      return person.getFirstName() + "." + person.getLastName() + "." + source.getGeoId() + "." + format.format(date);
    }
    return this.getId();
  }

  /**
   * Takes in an XML string and returns a ValueQuery representing the structured
   * query in the XML.
   * 
   * @param xml
   * @return
   */
  public static ValueQuery xmlToValueQuery(String xml, String config, Layer layer, Integer pageNumber, Integer pageSize)
  {
    return new IndividualCaseQB(xml, config, layer, pageSize, pageSize).construct();
  }
}
