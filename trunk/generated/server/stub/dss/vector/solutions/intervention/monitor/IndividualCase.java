package dss.vector.solutions.intervention.monitor;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.terraframe.mojo.dataaccess.transaction.Transaction;
import com.terraframe.mojo.query.AND;
import com.terraframe.mojo.query.Condition;
import com.terraframe.mojo.query.OIterator;
import com.terraframe.mojo.query.QueryFactory;

import dss.vector.solutions.Patient;
import dss.vector.solutions.Person;
import dss.vector.solutions.Property;
import dss.vector.solutions.PropertyInfo;
import dss.vector.solutions.general.EpiDate;
import dss.vector.solutions.general.OutbreakCalculation;
import dss.vector.solutions.general.ThresholdData;
import dss.vector.solutions.geo.generated.GeoEntity;
import dss.vector.solutions.geo.generated.GeoEntityQuery;

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
      GeoEntity[] entities = geoEntity.getPoliticalAncestors();

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
    Property property = Property.getByPackageAndName(PropertyInfo.GENERAL_PACKAGE, PropertyInfo.IS_EPI_WEEK);

    OutbreakCalculation method = OutbreakCalculation.valueOf(property.getPropertyValue());
    
    if (method.equals(OutbreakCalculation.EPI_WEEK))
    {
      EpiDate week = EpiDate.getEpiWeek(date);

      return new Date[] { week.getStartDate(), week.getEndDate() };
    }
    
    // Use the sliding window approach
    Calendar calendar = Calendar.getInstance();
    calendar.setTime(date);
    calendar.set(Calendar.DAY_OF_YEAR, -6);
    
    return new Date[] {calendar.getTime(), date};
  }

  private static long getCount(GeoEntity entity, Date startDate, Date endDate)
  {
    QueryFactory factory = new QueryFactory();

    GeoEntityQuery entityQuery = entity.getPoliticalDecendants(factory);
    
    IndividualCaseQuery query = new IndividualCaseQuery(factory);

    Condition condition = query.getProbableSource().EQ(entityQuery);
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

    IndividualCase individualCase = new IndividualCase();
    OIterator<? extends IndividualCase> iterator = query.getIterator();
    if (iterator.hasNext())
    {
      individualCase = iterator.next();
    }
    else
    {
      // If values don't exist on the case, give them defaults from the person
      if (individualCase.getResidence()==null)
      {
        individualCase.setResidence(person.getResidentialGeoEntity());
      }
      if (individualCase.getResidenceText()==null)
      {
        individualCase.setResidenceText(person.getResidentialInformation());
      }
      if (individualCase.getWorkplace()==null)
      {
        individualCase.setWorkplace(person.getWorkGeoEntity());
      }
      if (individualCase.getWorkplaceText()==null)
      {
        individualCase.setWorkplaceText(person.getWorkInformation());
      }
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
