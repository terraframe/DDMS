package dss.vector.solutions.intervention.monitor;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.terraframe.mojo.dataaccess.transaction.Transaction;
import com.terraframe.mojo.query.AND;
import com.terraframe.mojo.query.Condition;
import com.terraframe.mojo.query.OIterator;
import com.terraframe.mojo.query.QueryFactory;

import dss.vector.solutions.Patient;
import dss.vector.solutions.Person;
import dss.vector.solutions.general.EpiDate;
import dss.vector.solutions.general.ThresholdData;
import dss.vector.solutions.geo.generated.GeoEntity;

public class IndividualCase extends IndividualCaseBase implements com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1254360073722L;

  public IndividualCase()
  {
    super();
  }

  @Override
  @Transaction
  public void apply()
  {
    super.apply();

    // Perfrom outbreak notification
    validateOutbreak();
  }

  private void validateOutbreak()
  {
    GeoEntity geoEntity = this.getProbableSource();
    Date date = this.getDiagnosisDate();

    if (geoEntity != null && date != null)
    {
      Date[] window = IndividualCase.getWindow(date);

      // Get all relevant Geo Entities
      List<GeoEntity> entities = geoEntity.getPoliticalAncestors();

      // First get the Threshold data for the relevant GeoEntities
      for (GeoEntity entity : entities)
      {
        long count = IndividualCase.getCount(entity, window[0], window[1]);
        
        ThresholdData.checkThresholdViolation(date, entity, count);
      }
    }
  }

  private static Date[] getWindow(Date date)
  {
    // FIXME once MDSS200 is complete then a global setting will determine if a
    // EpiDate or Sliding window should be used
    boolean slidingWindow = false;
    
    if (!slidingWindow)
    {
      EpiDate week = EpiDate.getEpiWeek(date);

      return new Date[] { week.getStartDate(), week.getEndDate() };
    }
    
    // Use the sliding window approach
    Calendar calendar = Calendar.getInstance();
    calendar.setTime(date);
    calendar.set(Calendar.DAY_OF_YEAR, -7);
    
    return new Date[] {calendar.getTime(), date};
  }

  private static long getCount(GeoEntity entity, Date startDate, Date endDate)
  {
    IndividualCaseQuery query = new IndividualCaseQuery(new QueryFactory());

    Condition condition = query.getProbableSource().EQ(entity);
    condition = AND.get(condition, query.getDiagnosisDate().GE(startDate));
    condition = AND.get(condition, query.getDiagnosisDate().LE(endDate));

    query.WHERE(condition);

    return query.getCount();
  }

  public static IndividualCase searchForExistingCase(Date diagnosisDate, String personId)
  {
    Calendar calendar = Calendar.getInstance();
    calendar.setTime(diagnosisDate);
    calendar.add(Calendar.DAY_OF_MONTH, -28);
    Date fourWeeksAgo = calendar.getTime();

    Person person = Person.get(personId);
    Patient patient = person.getPatientDelegate();
    if (patient == null)
    {
      return null;
    }

    IndividualCaseQuery query = new IndividualCaseQuery(new QueryFactory());
    query.WHERE(query.getDiagnosisDate().GE(fourWeeksAgo));
    query.WHERE(query.getPatient().EQ(patient));
    query.ORDER_BY_DESC(query.getDiagnosisDate());

    IndividualCase individualCase = null;
    OIterator<? extends IndividualCase> iterator = query.getIterator();
    if (iterator.hasNext())
    {
      individualCase = iterator.next();
    }
    iterator.close();

    return individualCase;
  }

  @Override
  @Transaction
  public void applyWithPersonId(String personId)
  {
    Person person = Person.get(personId);
    Patient patient = person.getPatientDelegate();
    if (patient == null)
    {
      patient = new Patient();
      patient.setPerson(person);
      patient.apply();

      person.lock();
      person.setPatientDelegate(patient);
      person.apply();
    }

    this.setPatient(patient);
    this.apply();
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
}
