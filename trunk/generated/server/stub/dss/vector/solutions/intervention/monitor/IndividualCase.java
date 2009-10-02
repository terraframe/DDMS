package dss.vector.solutions.intervention.monitor;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.terraframe.mojo.dataaccess.transaction.Transaction;
import com.terraframe.mojo.query.OIterator;
import com.terraframe.mojo.query.QueryFactory;

import dss.vector.solutions.Patient;
import dss.vector.solutions.Person;
import dss.vector.solutions.geo.generated.GeoEntity;

public class IndividualCase extends IndividualCaseBase implements com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1254360073722L;
  
  public IndividualCase()
  {
    super();
  }
  
  public static IndividualCase searchForExistingCase(Date diagnosisDate, String personId)
  {
    Calendar calendar = Calendar.getInstance();
    calendar.setTime(diagnosisDate);
    calendar.add(Calendar.DAY_OF_MONTH, -28);
    Date fourWeeksAgo = calendar.getTime();
    
    Person person = Person.get(personId);
    Patient patient = person.getPatientDelegate();
    if (patient==null)
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
    if (patient==null)
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
    if(patient != null && date != null && source != null)
    {
      DateFormat format = SimpleDateFormat.getDateInstance(SimpleDateFormat.SHORT);
      Person person = patient.getPerson();
      
      return person.getFirstName() + "." + person.getLastName() + "." + source.getGeoId() + "." + format.format(date);
    }
    return this.getId();
  }
}
